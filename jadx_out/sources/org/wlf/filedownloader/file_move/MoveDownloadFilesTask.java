package org.wlf.filedownloader.file_move;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.base.Stoppable;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.Pauseable;
import org.wlf.filedownloader.listener.OnMoveDownloadFileListener;
import org.wlf.filedownloader.listener.OnMoveDownloadFilesListener;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class MoveDownloadFilesTask implements Runnable, Stoppable {
    private static final String TAG = MoveDownloadFilesTask.class.getSimpleName();
    private DownloadFileMover mDownloadFileMover;
    private Pauseable mDownloadTaskPauseable;
    private String mNewDirPath;
    private OnMoveDownloadFilesListener mOnMoveDownloadFilesListener;
    private ExecutorService mTaskEngine;
    private List<String> mUrls;
    private Map<String, String> mOldFileDir = new HashMap();
    private boolean mIsStop = false;
    private AtomicBoolean mIsNotifyFinish = new AtomicBoolean(false);
    private Object mLock = new Object();
    private final List<DownloadFileInfo> mDownloadFilesNeedMove = new ArrayList();
    private final List<DownloadFileInfo> mDownloadFilesMoved = new ArrayList();
    private final List<DownloadFileInfo> mDownloadFilesSkip = new ArrayList();

    public MoveDownloadFilesTask(List<String> urls, String newDirPath, ExecutorService taskEngine, DownloadFileMover downloadFileMover, Pauseable downloadTaskPauseable) {
        this.mUrls = urls;
        this.mNewDirPath = newDirPath;
        this.mTaskEngine = taskEngine;
        this.mDownloadFileMover = downloadFileMover;
        this.mDownloadTaskPauseable = downloadTaskPauseable;
    }

    public void setOnMoveDownloadFilesListener(OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
        this.mOnMoveDownloadFilesListener = onMoveDownloadFilesListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadFileMover.getDownloadFile(url);
    }

    @Override // org.wlf.filedownloader.base.Stoppable
    public void stop() {
        this.mIsStop = true;
    }

    @Override // org.wlf.filedownloader.base.Stoppable
    public boolean isStopped() {
        return this.mIsStop;
    }

    @Override // java.lang.Runnable
    public void run() {
        DownloadFileInfo downloadFileInfo;
        try {
            try {
                this.mDownloadFilesNeedMove.clear();
                this.mDownloadFilesMoved.clear();
                this.mDownloadFilesSkip.clear();
                for (String url : this.mUrls) {
                    if (UrlUtil.isUrl(url) && (downloadFileInfo = getDownloadFile(url)) != null) {
                        this.mDownloadFilesNeedMove.add(downloadFileInfo);
                        this.mOldFileDir.put(downloadFileInfo.getUrl(), downloadFileInfo.getFileDir());
                    }
                }
                notifyMoveDownloadFilesPrepared();
                final OnMoveDownloadFileListener onMoveEverySingleDownloadFileListener = new OnMoveSingleDownloadFileListener();
                for (int i = 0; i < this.mDownloadFilesNeedMove.size(); i++) {
                    DownloadFileInfo downloadFileInfo2 = this.mDownloadFilesNeedMove.get(i);
                    if (downloadFileInfo2 == null) {
                        synchronized (this.mLock) {
                            this.mDownloadFilesSkip.add(downloadFileInfo2);
                        }
                    } else {
                        final String finalUrl = downloadFileInfo2.getUrl();
                        if (isStopped()) {
                            Log.m5156d(TAG, TAG + ".run 批量移动任务被取消，无法继续移动，任务即将结束");
                            if (isStopped()) {
                                notifyMoveDownloadFilesCompleted();
                            }
                            Log.m5156d(TAG, TAG + ".run 批量移动文件主任务【已结束】，但是通过暂停下载中的文件移动任务可能还没有结束");
                            return;
                        }
                        if (this.mDownloadTaskPauseable.isDownloading(finalUrl)) {
                            Log.m5156d(TAG, TAG + ".run 需要先暂停单个下载任务后移动，url:" + finalUrl);
                            this.mDownloadTaskPauseable.pause(finalUrl, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_move.MoveDownloadFilesTask.1
                                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                                public void onStopFileDownloadTaskSucceed(String url2) {
                                    Log.m5156d(MoveDownloadFilesTask.TAG, MoveDownloadFilesTask.TAG + ".run 暂停单个下载任务成功，开始移动，url:" + finalUrl);
                                    if (MoveDownloadFilesTask.this.isStopped()) {
                                        Log.m5156d(MoveDownloadFilesTask.TAG, MoveDownloadFilesTask.TAG + ".run 批量移动任务被取消，无法继续移动，任务即将结束");
                                        MoveDownloadFilesTask.this.notifyMoveDownloadFilesCompleted();
                                    } else {
                                        MoveDownloadFilesTask.this.runSingleMoveTask(url2, onMoveEverySingleDownloadFileListener, false);
                                    }
                                }

                                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                                public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                                    if (MoveDownloadFilesTask.this.isStopped()) {
                                        Log.m5156d(MoveDownloadFilesTask.TAG, MoveDownloadFilesTask.TAG + ".run 批量移动任务被取消，无法继续移动，任务即将结束");
                                        MoveDownloadFilesTask.this.notifyMoveDownloadFilesCompleted();
                                    } else {
                                        if (failReason == null || !OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED.equals(failReason.getType())) {
                                            Log.m5156d(MoveDownloadFilesTask.TAG, MoveDownloadFilesTask.TAG + ".run 暂停单个下载任务失败，无法移动，url:" + finalUrl);
                                            synchronized (MoveDownloadFilesTask.this.mLock) {
                                                MoveDownloadFilesTask.this.mDownloadFilesSkip.add(MoveDownloadFilesTask.this.getDownloadFile(finalUrl));
                                            }
                                            return;
                                        }
                                        MoveDownloadFilesTask.this.runSingleMoveTask(url2, onMoveEverySingleDownloadFileListener, false);
                                    }
                                }
                            });
                        } else {
                            runSingleMoveTask(finalUrl, onMoveEverySingleDownloadFileListener, true);
                        }
                    }
                }
                if (isStopped()) {
                    notifyMoveDownloadFilesCompleted();
                }
                Log.m5156d(TAG, TAG + ".run 批量移动文件主任务【已结束】，但是通过暂停下载中的文件移动任务可能还没有结束");
            } catch (Exception e) {
                e.printStackTrace();
                if (isStopped()) {
                    notifyMoveDownloadFilesCompleted();
                }
                Log.m5156d(TAG, TAG + ".run 批量移动文件主任务【已结束】，但是通过暂停下载中的文件移动任务可能还没有结束");
            }
        } catch (Throwable th) {
            if (isStopped()) {
                notifyMoveDownloadFilesCompleted();
            }
            Log.m5156d(TAG, TAG + ".run 批量移动文件主任务【已结束】，但是通过暂停下载中的文件移动任务可能还没有结束");
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runSingleMoveTask(String url, OnMoveDownloadFileListener onMoveEverySingleDownloadFileListener, boolean sync) {
        MoveDownloadFileTask moveSingleDownloadFileTask = new MoveDownloadFileTask(url, this.mNewDirPath, this.mDownloadFileMover);
        moveSingleDownloadFileTask.enableSyncCallback();
        moveSingleDownloadFileTask.setOnMoveDownloadFileListener(onMoveEverySingleDownloadFileListener);
        if (sync) {
            moveSingleDownloadFileTask.run();
        } else {
            this.mTaskEngine.execute(moveSingleDownloadFileTask);
        }
    }

    private class OnMoveSingleDownloadFileListener implements OnMoveDownloadFileListener {
        private OnMoveSingleDownloadFileListener() {
        }

        @Override // org.wlf.filedownloader.listener.OnMoveDownloadFileListener
        public void onMoveDownloadFilePrepared(DownloadFileInfo downloadFileNeedToMove) {
            MoveDownloadFilesTask.this.notifyMovingDownloadFiles(downloadFileNeedToMove);
        }

        @Override // org.wlf.filedownloader.listener.OnMoveDownloadFileListener
        public void onMoveDownloadFileSuccess(DownloadFileInfo downloadFileMoved) {
            String url = null;
            if (downloadFileMoved != null) {
                url = downloadFileMoved.getUrl();
            }
            synchronized (MoveDownloadFilesTask.this.mLock) {
                MoveDownloadFilesTask.this.mDownloadFilesMoved.add(downloadFileMoved);
            }
            Log.m5156d(MoveDownloadFilesTask.TAG, MoveDownloadFilesTask.TAG + ".run 移动单个成功，已移动数量：" + MoveDownloadFilesTask.this.mDownloadFilesMoved.size() + "，总共需要移动数量" + MoveDownloadFilesTask.this.mDownloadFilesNeedMove.size() + "，url：" + url);
            if (MoveDownloadFilesTask.this.mDownloadFilesMoved.size() + MoveDownloadFilesTask.this.mDownloadFilesSkip.size() == MoveDownloadFilesTask.this.mDownloadFilesNeedMove.size()) {
                MoveDownloadFilesTask.this.notifyMoveDownloadFilesCompleted();
            }
        }

        @Override // org.wlf.filedownloader.listener.OnMoveDownloadFileListener
        public void onMoveDownloadFileFailed(DownloadFileInfo downloadFileInfo, OnMoveDownloadFileListener.MoveDownloadFileFailReason failReason) {
            String url = null;
            if (downloadFileInfo != null) {
                url = downloadFileInfo.getUrl();
            }
            String msg = null;
            if (failReason != null) {
                msg = failReason.getMessage();
            }
            Log.m5156d(MoveDownloadFilesTask.TAG, MoveDownloadFilesTask.TAG + ".run 移动单个成功，已移动数量：" + MoveDownloadFilesTask.this.mDownloadFilesMoved.size() + "，总共需要移动数量" + MoveDownloadFilesTask.this.mDownloadFilesNeedMove.size() + "，失败原因：" + msg + "，url：" + url);
            synchronized (MoveDownloadFilesTask.this.mLock) {
                MoveDownloadFilesTask.this.mDownloadFilesSkip.add(downloadFileInfo);
            }
            if (MoveDownloadFilesTask.this.mDownloadFilesMoved.size() + MoveDownloadFilesTask.this.mDownloadFilesSkip.size() == MoveDownloadFilesTask.this.mDownloadFilesNeedMove.size()) {
                MoveDownloadFilesTask.this.notifyMoveDownloadFilesCompleted();
            }
        }
    }

    private void notifyMoveDownloadFilesPrepared() {
        Log.m5156d(TAG, TAG + ".run 准备批量移动，大小：" + this.mDownloadFilesNeedMove.size());
        OnMoveDownloadFilesListener.MainThreadHelper.onMoveDownloadFilesPrepared(this.mDownloadFilesNeedMove, this.mOnMoveDownloadFilesListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMovingDownloadFiles(DownloadFileInfo downloadFileInfo) {
        String url = null;
        if (downloadFileInfo != null) {
            url = downloadFileInfo.getUrl();
        }
        Log.m5156d(TAG, TAG + ".run 准备移动单个，url：" + url);
        OnMoveDownloadFilesListener.MainThreadHelper.onMovingDownloadFiles(this.mDownloadFilesNeedMove, this.mDownloadFilesMoved, this.mDownloadFilesSkip, downloadFileInfo, this.mOnMoveDownloadFilesListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMoveDownloadFilesCompleted() {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true)) {
            checkRollback();
            OnMoveDownloadFilesListener.MainThreadHelper.onMoveDownloadFilesCompleted(this.mDownloadFilesNeedMove, this.mDownloadFilesMoved, this.mOnMoveDownloadFilesListener);
            this.mIsStop = true;
            int failedSize = this.mDownloadFilesNeedMove.size() - this.mDownloadFilesMoved.size();
            Log.m5156d(TAG, TAG + ".run，批量移动文件主任务和其它相关任务全部【已结束】，总共需要移动：" + this.mDownloadFilesNeedMove.size() + "，已移动：" + this.mDownloadFilesMoved.size() + "，失败：" + failedSize + "，跳过：" + this.mDownloadFilesSkip.size() + "，跳过数量是否等于失败数量：" + (failedSize == this.mDownloadFilesSkip.size()));
        }
    }

    private void checkRollback() {
        if (!CollectionUtil.isEmpty(this.mDownloadFilesSkip)) {
            for (DownloadFileInfo downloadFileInfo : this.mDownloadFilesSkip) {
                if (downloadFileInfo != null) {
                    String oldDirPath = this.mOldFileDir.get(downloadFileInfo.getUrl());
                    if (FileUtil.isFilePath(oldDirPath) && !oldDirPath.equals(downloadFileInfo.getFileDir())) {
                        try {
                            this.mDownloadFileMover.moveDownloadFile(downloadFileInfo.getUrl(), oldDirPath);
                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                this.mDownloadFileMover.moveDownloadFile(downloadFileInfo.getUrl(), oldDirPath);
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }
}

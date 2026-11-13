package org.wlf.filedownloader.file_delete;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.base.Stoppable;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.Pauseable;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class DeleteDownloadFilesTask implements Runnable, Stoppable {
    private static final String TAG = DeleteDownloadFilesTask.class.getSimpleName();
    private boolean mDeleteDownloadedFile;
    private DownloadFileDeleter mDownloadFileDeleter;
    private Pauseable mDownloadTaskPauseable;
    private OnDeleteDownloadFilesListener mOnDeleteDownloadFilesListener;
    private ExecutorService mTaskEngine;
    private List<String> mUrls;
    private boolean mIsStop = false;
    private AtomicBoolean mIsNotifyFinish = new AtomicBoolean(false);
    private Object mLock = new Object();
    private final List<DownloadFileInfo> mDownloadFilesNeedDelete = new ArrayList();
    private final List<DownloadFileInfo> mDownloadFilesDeleted = new ArrayList();
    private final List<DownloadFileInfo> mDownloadFilesSkip = new ArrayList();

    public DeleteDownloadFilesTask(List<String> urls, boolean deleteDownloadedFile, ExecutorService taskEngine, DownloadFileDeleter downloadFileDeleter, Pauseable downloadTaskPauseable) {
        this.mUrls = urls;
        this.mTaskEngine = taskEngine;
        this.mDeleteDownloadedFile = deleteDownloadedFile;
        this.mDownloadFileDeleter = downloadFileDeleter;
        this.mDownloadTaskPauseable = downloadTaskPauseable;
    }

    public void setOnDeleteDownloadFilesListener(OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
        this.mOnDeleteDownloadFilesListener = onDeleteDownloadFilesListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadFileDeleter.getDownloadFile(url);
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
                this.mDownloadFilesNeedDelete.clear();
                this.mDownloadFilesDeleted.clear();
                this.mDownloadFilesSkip.clear();
                for (String url : this.mUrls) {
                    if (UrlUtil.isUrl(url) && (downloadFileInfo = getDownloadFile(url)) != null) {
                        this.mDownloadFilesNeedDelete.add(downloadFileInfo);
                    }
                }
                notifyDeleteDownloadFilesPrepared();
                final OnDeleteDownloadFileListener onDeleteEverySingleDownloadFileListener = new OnDeleteSingleDownloadFileListener();
                for (int i = 0; i < this.mDownloadFilesNeedDelete.size(); i++) {
                    DownloadFileInfo downloadFileInfo2 = this.mDownloadFilesNeedDelete.get(i);
                    if (downloadFileInfo2 == null) {
                        synchronized (this.mLock) {
                            this.mDownloadFilesSkip.add(downloadFileInfo2);
                        }
                    } else {
                        final String finalUrl = downloadFileInfo2.getUrl();
                        if (isStopped()) {
                            Log.m5156d(TAG, TAG + ".run 批量删除任务被取消，无法继续删除，任务即将结束");
                            if (isStopped()) {
                                notifyDeleteDownloadFilesCompleted();
                            }
                            Log.m5156d(TAG, TAG + ".run 批量删除文件主任务【已结束】，但是通过暂停下载中的文件删除任务可能还没有结束");
                            return;
                        }
                        if (this.mDownloadTaskPauseable.isDownloading(finalUrl)) {
                            Log.m5156d(TAG, TAG + ".run 需要先暂停单个下载任务后删除，url:" + finalUrl);
                            this.mDownloadTaskPauseable.pause(finalUrl, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_delete.DeleteDownloadFilesTask.1
                                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                                public void onStopFileDownloadTaskSucceed(String url2) {
                                    Log.m5156d(DeleteDownloadFilesTask.TAG, DeleteDownloadFilesTask.TAG + ".run 暂停单个下载任务成功，开始删除，url:" + finalUrl);
                                    if (DeleteDownloadFilesTask.this.isStopped()) {
                                        Log.m5156d(DeleteDownloadFilesTask.TAG, DeleteDownloadFilesTask.TAG + ".run 批量删除任务被取消，无法继续删除，任务即将结束");
                                        DeleteDownloadFilesTask.this.notifyDeleteDownloadFilesCompleted();
                                    } else {
                                        DeleteDownloadFilesTask.this.runSingleDeleteTask(finalUrl, onDeleteEverySingleDownloadFileListener, false);
                                    }
                                }

                                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                                public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                                    if (DeleteDownloadFilesTask.this.isStopped()) {
                                        Log.m5156d(DeleteDownloadFilesTask.TAG, DeleteDownloadFilesTask.TAG + ".run 批量删除任务被取消，无法继续删除，任务即将结束");
                                        DeleteDownloadFilesTask.this.notifyDeleteDownloadFilesCompleted();
                                    } else {
                                        if (failReason == null || !OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED.equals(failReason.getType())) {
                                            Log.m5156d(DeleteDownloadFilesTask.TAG, DeleteDownloadFilesTask.TAG + ".run 暂停单个下载任务失败，无法删除，url:" + finalUrl);
                                            synchronized (DeleteDownloadFilesTask.this.mLock) {
                                                DeleteDownloadFilesTask.this.mDownloadFilesSkip.add(DeleteDownloadFilesTask.this.getDownloadFile(finalUrl));
                                            }
                                            return;
                                        }
                                        DeleteDownloadFilesTask.this.runSingleDeleteTask(finalUrl, onDeleteEverySingleDownloadFileListener, false);
                                    }
                                }
                            });
                        } else {
                            runSingleDeleteTask(finalUrl, onDeleteEverySingleDownloadFileListener, true);
                        }
                    }
                }
                if (isStopped()) {
                    notifyDeleteDownloadFilesCompleted();
                }
                Log.m5156d(TAG, TAG + ".run 批量删除文件主任务【已结束】，但是通过暂停下载中的文件删除任务可能还没有结束");
            } catch (Exception e) {
                e.printStackTrace();
                if (isStopped()) {
                    notifyDeleteDownloadFilesCompleted();
                }
                Log.m5156d(TAG, TAG + ".run 批量删除文件主任务【已结束】，但是通过暂停下载中的文件删除任务可能还没有结束");
            }
        } catch (Throwable th) {
            if (isStopped()) {
                notifyDeleteDownloadFilesCompleted();
            }
            Log.m5156d(TAG, TAG + ".run 批量删除文件主任务【已结束】，但是通过暂停下载中的文件删除任务可能还没有结束");
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void runSingleDeleteTask(String url, OnDeleteDownloadFileListener onDeleteEverySingleDownloadFileListener, boolean sync) {
        DeleteDownloadFileTask deleteSingleDownloadFileTask = new DeleteDownloadFileTask(url, this.mDeleteDownloadedFile, this.mDownloadFileDeleter);
        deleteSingleDownloadFileTask.enableSyncCallback();
        deleteSingleDownloadFileTask.setOnDeleteDownloadFileListener(onDeleteEverySingleDownloadFileListener);
        if (sync) {
            deleteSingleDownloadFileTask.run();
        } else {
            this.mTaskEngine.execute(deleteSingleDownloadFileTask);
        }
    }

    private class OnDeleteSingleDownloadFileListener implements OnDeleteDownloadFileListener {
        private OnDeleteSingleDownloadFileListener() {
        }

        @Override // org.wlf.filedownloader.listener.OnDeleteDownloadFileListener
        public void onDeleteDownloadFilePrepared(DownloadFileInfo downloadFileNeedDelete) {
            DeleteDownloadFilesTask.this.notifyDeletingDownloadFiles(downloadFileNeedDelete);
        }

        @Override // org.wlf.filedownloader.listener.OnDeleteDownloadFileListener
        public void onDeleteDownloadFileSuccess(DownloadFileInfo downloadFileDeleted) {
            String url = null;
            if (downloadFileDeleted != null) {
                url = downloadFileDeleted.getUrl();
            }
            synchronized (DeleteDownloadFilesTask.this.mLock) {
                DeleteDownloadFilesTask.this.mDownloadFilesDeleted.add(downloadFileDeleted);
            }
            Log.m5156d(DeleteDownloadFilesTask.TAG, DeleteDownloadFilesTask.TAG + ".run 删除单个成功，已删除数量：" + DeleteDownloadFilesTask.this.mDownloadFilesDeleted.size() + "，总共需要删除数量" + DeleteDownloadFilesTask.this.mDownloadFilesNeedDelete.size() + "，url：" + url);
            if (DeleteDownloadFilesTask.this.mDownloadFilesDeleted.size() + DeleteDownloadFilesTask.this.mDownloadFilesSkip.size() == DeleteDownloadFilesTask.this.mDownloadFilesNeedDelete.size()) {
                DeleteDownloadFilesTask.this.notifyDeleteDownloadFilesCompleted();
            }
        }

        @Override // org.wlf.filedownloader.listener.OnDeleteDownloadFileListener
        public void onDeleteDownloadFileFailed(DownloadFileInfo downloadFileInfo, OnDeleteDownloadFileListener.DeleteDownloadFileFailReason failReason) {
            String url = null;
            if (downloadFileInfo != null) {
                url = downloadFileInfo.getUrl();
            }
            String msg = null;
            if (failReason != null) {
                msg = failReason.getMessage();
            }
            Log.m5156d(DeleteDownloadFilesTask.TAG, DeleteDownloadFilesTask.TAG + ".run 删除单个成功，已删除数量：" + DeleteDownloadFilesTask.this.mDownloadFilesDeleted.size() + "，总共需要删除数量" + DeleteDownloadFilesTask.this.mDownloadFilesNeedDelete.size() + "，失败原因：" + msg + "，url：" + url);
            synchronized (DeleteDownloadFilesTask.this.mLock) {
                DeleteDownloadFilesTask.this.mDownloadFilesSkip.add(downloadFileInfo);
            }
            if (DeleteDownloadFilesTask.this.mDownloadFilesDeleted.size() + DeleteDownloadFilesTask.this.mDownloadFilesSkip.size() == DeleteDownloadFilesTask.this.mDownloadFilesNeedDelete.size()) {
                DeleteDownloadFilesTask.this.notifyDeleteDownloadFilesCompleted();
            }
        }
    }

    private void notifyDeleteDownloadFilesPrepared() {
        Log.m5156d(TAG, TAG + ".run 准备批量删除，大小：" + this.mDownloadFilesNeedDelete.size());
        OnDeleteDownloadFilesListener.MainThreadHelper.onDeleteDownloadFilesPrepared(this.mDownloadFilesNeedDelete, this.mOnDeleteDownloadFilesListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeletingDownloadFiles(DownloadFileInfo downloadFileInfo) {
        String url = null;
        if (downloadFileInfo != null) {
            url = downloadFileInfo.getUrl();
        }
        Log.m5156d(TAG, TAG + ".run 准备删除单个，url：" + url);
        OnDeleteDownloadFilesListener.MainThreadHelper.onDeletingDownloadFiles(this.mDownloadFilesNeedDelete, this.mDownloadFilesDeleted, this.mDownloadFilesSkip, downloadFileInfo, this.mOnDeleteDownloadFilesListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeleteDownloadFilesCompleted() {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true)) {
            OnDeleteDownloadFilesListener.MainThreadHelper.onDeleteDownloadFilesCompleted(this.mDownloadFilesNeedDelete, this.mDownloadFilesDeleted, this.mOnDeleteDownloadFilesListener);
            this.mIsStop = true;
            int failedSize = this.mDownloadFilesNeedDelete.size() - this.mDownloadFilesDeleted.size();
            Log.m5156d(TAG, TAG + ".run 批量删除文件主任务和其它相关任务全部【已结束】，总共需要删除：" + this.mDownloadFilesNeedDelete.size() + "，已删除：" + this.mDownloadFilesDeleted.size() + "，失败：" + failedSize + "，跳过：" + this.mDownloadFilesSkip.size() + "，跳过数量是否等于失败数量：" + (failedSize == this.mDownloadFilesSkip.size()));
        }
    }
}

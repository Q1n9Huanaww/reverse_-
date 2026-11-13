package org.wlf.filedownloader.file_move;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Control;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.Pauseable;
import org.wlf.filedownloader.listener.OnMoveDownloadFileListener;
import org.wlf.filedownloader.listener.OnMoveDownloadFilesListener;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadMoveManager {
    private static final String TAG = DownloadMoveManager.class.getSimpleName();
    private DownloadFileMover mDownloadFileMover;
    private Pauseable mDownloadTaskPauseable;
    private MoveControl mMoveControl;
    private ExecutorService mTaskEngine;

    public DownloadMoveManager(ExecutorService taskEngine, DownloadFileMover downloadFileMover, Pauseable downloadTaskPauseable) {
        this.mTaskEngine = taskEngine;
        this.mDownloadFileMover = downloadFileMover;
        this.mDownloadTaskPauseable = downloadTaskPauseable;
    }

    private void addAndRunTask(Runnable task) {
        this.mTaskEngine.execute(task);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadFileMover.getDownloadFile(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void singleMoveInternal(String url, String newDirPath, OnMoveDownloadFileListener onMoveDownloadFileListener) {
        MoveDownloadFileTask moveDownloadFileTask = new MoveDownloadFileTask(url, newDirPath, this.mDownloadFileMover);
        moveDownloadFileTask.setOnMoveDownloadFileListener(onMoveDownloadFileListener);
        addAndRunTask(moveDownloadFileTask);
    }

    public void move(final String url, final String newDirPath, final OnMoveDownloadFileListener onMoveDownloadFileListener) {
        if (!this.mDownloadTaskPauseable.isDownloading(url)) {
            Log.m5156d(TAG, TAG + ".move 下载任务已经暂停，可以直接移动，url:" + url);
            singleMoveInternal(url, newDirPath, onMoveDownloadFileListener);
        } else {
            Log.m5156d(TAG, TAG + ".move 需要先暂停下载任务后移动，url:" + url);
            this.mDownloadTaskPauseable.pause(url, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_move.DownloadMoveManager.1
                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskSucceed(String url2) {
                    Log.m5156d(DownloadMoveManager.TAG, DownloadMoveManager.TAG + ".move 暂停下载任务成功，开始移动，url:" + url);
                    DownloadMoveManager.this.singleMoveInternal(url, newDirPath, onMoveDownloadFileListener);
                }

                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                    if (failReason == null || !OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED.equals(failReason.getType())) {
                        Log.m5156d(DownloadMoveManager.TAG, DownloadMoveManager.TAG + ".move 暂停下载任务失败，无法移动，url:" + url);
                        DownloadMoveManager.this.notifyMoveDownloadFileFailed(DownloadMoveManager.this.getDownloadFile(url), new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(url, failReason), onMoveDownloadFileListener);
                    } else {
                        DownloadMoveManager.this.singleMoveInternal(url, newDirPath, onMoveDownloadFileListener);
                    }
                }
            });
        }
    }

    public Control move(List<String> urls, String newDirPath, OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
        if (this.mMoveControl != null && !this.mMoveControl.isStopped()) {
            return this.mMoveControl;
        }
        MoveDownloadFilesTask moveDownloadFilesTask = new MoveDownloadFilesTask(urls, newDirPath, this.mTaskEngine, this.mDownloadFileMover, this.mDownloadTaskPauseable);
        moveDownloadFilesTask.setOnMoveDownloadFilesListener(onMoveDownloadFilesListener);
        addAndRunTask(moveDownloadFilesTask);
        this.mMoveControl = new MoveControl(moveDownloadFilesTask);
        return this.mMoveControl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyMoveDownloadFileFailed(DownloadFileInfo downloadFileInfo, OnMoveDownloadFileListener.MoveDownloadFileFailReason failReason, OnMoveDownloadFileListener onMoveDownloadFileListener) {
        OnMoveDownloadFileListener.MainThreadHelper.onMoveDownloadFileFailed(downloadFileInfo, failReason, onMoveDownloadFileListener);
    }

    private static class MoveControl implements Control {
        private MoveDownloadFilesTask mMoveDownloadFilesTask;

        private MoveControl(MoveDownloadFilesTask moveDownloadFilesTask) {
            this.mMoveDownloadFilesTask = moveDownloadFilesTask;
        }

        @Override // org.wlf.filedownloader.base.Control
        public void stop() {
            if (this.mMoveDownloadFilesTask != null) {
                this.mMoveDownloadFilesTask.stop();
            }
        }

        @Override // org.wlf.filedownloader.base.Control
        public boolean isStopped() {
            if (this.mMoveDownloadFilesTask == null) {
                return true;
            }
            return this.mMoveDownloadFilesTask.isStopped();
        }
    }
}

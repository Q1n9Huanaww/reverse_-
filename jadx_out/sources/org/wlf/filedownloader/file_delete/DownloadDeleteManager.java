package org.wlf.filedownloader.file_delete;

import java.util.List;
import java.util.concurrent.ExecutorService;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Control;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.Pauseable;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadDeleteManager {
    private static final String TAG = DownloadDeleteManager.class.getSimpleName();
    private DeleteControl mDeleteControl;
    private DownloadFileDeleter mDownloadFileDeleter;
    private Pauseable mDownloadTaskPauseable;
    private ExecutorService mTaskEngine;

    public DownloadDeleteManager(ExecutorService taskEngine, DownloadFileDeleter downloadFileDeleter, Pauseable downloadTaskPauseable) {
        this.mTaskEngine = taskEngine;
        this.mDownloadFileDeleter = downloadFileDeleter;
        this.mDownloadTaskPauseable = downloadTaskPauseable;
    }

    private void addAndRunTask(Runnable task) {
        this.mTaskEngine.execute(task);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadFileDeleter.getDownloadFile(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void singleDeleteInternal(String url, boolean deleteDownloadedFileInPath, OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
        DeleteDownloadFileTask deleteDownloadFileTask = new DeleteDownloadFileTask(url, deleteDownloadedFileInPath, this.mDownloadFileDeleter);
        deleteDownloadFileTask.setOnDeleteDownloadFileListener(onDeleteDownloadFileListener);
        addAndRunTask(deleteDownloadFileTask);
    }

    public void delete(final String url, final boolean deleteDownloadedFileInPath, final OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
        if (!this.mDownloadTaskPauseable.isDownloading(url)) {
            Log.m5156d(TAG, TAG + ".delete 下载任务已经暂停，可以直接删除，url:" + url);
            singleDeleteInternal(url, deleteDownloadedFileInPath, onDeleteDownloadFileListener);
        } else {
            Log.m5156d(TAG, TAG + ".delete 需要先暂停下载任务后删除,url:" + url);
            this.mDownloadTaskPauseable.pause(url, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_delete.DownloadDeleteManager.1
                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskSucceed(String url2) {
                    Log.m5156d(DownloadDeleteManager.TAG, DownloadDeleteManager.TAG + ".delete 暂停下载任务成功，开始删除，url:" + url);
                    DownloadDeleteManager.this.singleDeleteInternal(url, deleteDownloadedFileInPath, onDeleteDownloadFileListener);
                }

                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                    if (failReason == null || !OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED.equals(failReason.getType())) {
                        Log.m5156d(DownloadDeleteManager.TAG, DownloadDeleteManager.TAG + ".delete 暂停下载任务失败，无法删除，url:" + url);
                        DownloadDeleteManager.this.notifyDeleteDownloadFileFailed(DownloadDeleteManager.this.getDownloadFile(url), new OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason(url, failReason), onDeleteDownloadFileListener);
                    } else {
                        DownloadDeleteManager.this.singleDeleteInternal(url, deleteDownloadedFileInPath, onDeleteDownloadFileListener);
                    }
                }
            });
        }
    }

    public Control delete(List<String> urls, boolean deleteDownloadedFile, OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
        if (this.mDeleteControl != null && !this.mDeleteControl.isStopped()) {
            return this.mDeleteControl;
        }
        DeleteDownloadFilesTask deleteDownloadFilesTask = new DeleteDownloadFilesTask(urls, deleteDownloadedFile, this.mTaskEngine, this.mDownloadFileDeleter, this.mDownloadTaskPauseable);
        deleteDownloadFilesTask.setOnDeleteDownloadFilesListener(onDeleteDownloadFilesListener);
        addAndRunTask(deleteDownloadFilesTask);
        this.mDeleteControl = new DeleteControl(deleteDownloadFilesTask);
        return this.mDeleteControl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDeleteDownloadFileFailed(DownloadFileInfo downloadFileInfo, OnDeleteDownloadFileListener.DeleteDownloadFileFailReason failReason, OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
        OnDeleteDownloadFileListener.MainThreadHelper.onDeleteDownloadFileFailed(downloadFileInfo, failReason, onDeleteDownloadFileListener);
    }

    private class DeleteControl implements Control {
        private DeleteDownloadFilesTask mDeleteDownloadFilesTask;

        private DeleteControl(DeleteDownloadFilesTask deleteDownloadFilesTask) {
            this.mDeleteDownloadFilesTask = deleteDownloadFilesTask;
        }

        @Override // org.wlf.filedownloader.base.Control
        public void stop() {
            if (this.mDeleteDownloadFilesTask != null) {
                this.mDeleteDownloadFilesTask.stop();
            }
        }

        @Override // org.wlf.filedownloader.base.Control
        public boolean isStopped() {
            if (this.mDeleteDownloadFilesTask == null) {
                return true;
            }
            return this.mDeleteDownloadFilesTask.isStopped();
        }
    }
}

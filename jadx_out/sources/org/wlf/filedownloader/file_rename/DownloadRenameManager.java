package org.wlf.filedownloader.file_rename;

import java.util.concurrent.ExecutorService;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener;
import org.wlf.filedownloader.file_download.base.Pauseable;
import org.wlf.filedownloader.listener.OnRenameDownloadFileListener;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadRenameManager {
    private static final String TAG = DownloadRenameManager.class.getSimpleName();
    private DownloadFileRenamer mDownloadFileRenamer;
    private Pauseable mDownloadTaskPauseable;
    private ExecutorService mTaskEngine;

    public DownloadRenameManager(ExecutorService taskEngine, DownloadFileRenamer downloadFileRenamer, Pauseable downloadTaskPauseable) {
        this.mTaskEngine = taskEngine;
        this.mDownloadFileRenamer = downloadFileRenamer;
        this.mDownloadTaskPauseable = downloadTaskPauseable;
    }

    private void addAndRunTask(Runnable task) {
        this.mTaskEngine.execute(task);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadFileInfo getDownloadFile(String url) {
        return this.mDownloadFileRenamer.getDownloadFile(url);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void renameInternal(String url, String newFileName, boolean includedSuffix, OnRenameDownloadFileListener onRenameDownloadFileListener) {
        RenameDownloadFileTask task = new RenameDownloadFileTask(url, newFileName, includedSuffix, this.mDownloadFileRenamer);
        task.setOnRenameDownloadFileListener(onRenameDownloadFileListener);
        addAndRunTask(task);
    }

    public void rename(final String url, final String newFileName, final boolean includedSuffix, final OnRenameDownloadFileListener onRenameDownloadFileListener) {
        if (!this.mDownloadTaskPauseable.isDownloading(url)) {
            Log.m5156d(TAG, TAG + ".rename 下载任务已经暂停，可以直接重命名，url:" + url);
            renameInternal(url, newFileName, includedSuffix, onRenameDownloadFileListener);
        } else {
            Log.m5156d(TAG, TAG + ".rename 需要先暂停下载任务后重命名,url:" + url);
            this.mDownloadTaskPauseable.pause(url, new OnStopFileDownloadTaskListener() { // from class: org.wlf.filedownloader.file_rename.DownloadRenameManager.1
                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskSucceed(String url2) {
                    Log.m5156d(DownloadRenameManager.TAG, DownloadRenameManager.TAG + ".rename 暂停下载任务成功，开始重命名，url:" + url);
                    DownloadRenameManager.this.renameInternal(url, newFileName, includedSuffix, onRenameDownloadFileListener);
                }

                @Override // org.wlf.filedownloader.file_download.base.OnStopFileDownloadTaskListener
                public void onStopFileDownloadTaskFailed(String url2, OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason failReason) {
                    if (failReason == null || !OnStopFileDownloadTaskListener.StopDownloadFileTaskFailReason.TYPE_TASK_HAS_BEEN_STOPPED.equals(failReason.getType())) {
                        Log.m5156d(DownloadRenameManager.TAG, DownloadRenameManager.TAG + ".rename 暂停下载任务失败，无法重命名，url:" + url);
                        DownloadRenameManager.this.notifyRenameDownloadFileFailed(DownloadRenameManager.this.getDownloadFile(url), new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(url, failReason), onRenameDownloadFileListener);
                    } else {
                        DownloadRenameManager.this.renameInternal(url, newFileName, includedSuffix, onRenameDownloadFileListener);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRenameDownloadFileFailed(DownloadFileInfo downloadFileInfo, OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason, OnRenameDownloadFileListener onRenameDownloadFileListener) {
        OnRenameDownloadFileListener.MainThreadHelper.onRenameDownloadFileFailed(downloadFileInfo, failReason, onRenameDownloadFileListener);
    }
}

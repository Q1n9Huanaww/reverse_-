package org.wlf.filedownloader.file_delete;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.listener.OnDeleteDownloadFileListener;
import org.wlf.filedownloader.util.DownloadFileUtil;

/* JADX INFO: loaded from: classes2.dex */
class DeleteDownloadFileTask implements Runnable {
    private static final String TAG = DeleteDownloadFileTask.class.getSimpleName();
    private boolean mDeleteDownloadedFileInPath;
    private DownloadFileDeleter mDownloadFileDeleter;
    private OnDeleteDownloadFileListener mOnDeleteDownloadFileListener;
    private String mUrl;
    private boolean mIsSyncCallback = false;
    private AtomicBoolean mIsNotifyFinish = new AtomicBoolean(false);

    public DeleteDownloadFileTask(String url, boolean deleteDownloadedFileInPath, DownloadFileDeleter downloadFileDeleter) {
        this.mUrl = url;
        this.mDeleteDownloadedFileInPath = deleteDownloadedFileInPath;
        this.mDownloadFileDeleter = downloadFileDeleter;
    }

    public void setOnDeleteDownloadFileListener(OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
        this.mOnDeleteDownloadFileListener = onDeleteDownloadFileListener;
    }

    public void enableSyncCallback() {
        this.mIsSyncCallback = true;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean z = true;
        DownloadFileInfo downloadFileInfo = null;
        OnDeleteDownloadFileListener.DeleteDownloadFileFailReason failReason = null;
        try {
            downloadFileInfo = this.mDownloadFileDeleter.getDownloadFile(this.mUrl);
            if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
                failReason = new OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason(this.mUrl, "the download file not exist !", OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason.TYPE_FILE_RECORD_IS_NOT_EXIST);
                return;
            }
            notifyPrepared(downloadFileInfo);
            if (!DownloadFileUtil.canDelete(downloadFileInfo)) {
                failReason = new OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason(this.mUrl, "the download file status error !", OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason.TYPE_FILE_STATUS_ERROR);
                return;
            }
            boolean deleteResult = false;
            try {
                this.mDownloadFileDeleter.deleteDownloadFile(this.mUrl);
                deleteResult = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!deleteResult) {
                failReason = new OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason(this.mUrl, "delete file in record failed !", OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason.TYPE_UNKNOWN);
                return;
            }
            Log.m5156d(TAG, TAG + ".run 数据库删除成功url：" + this.mUrl);
            if (this.mDeleteDownloadedFileInPath) {
                File file = new File(downloadFileInfo.getFileDir(), downloadFileInfo.getFileName());
                if (file == null || !file.exists()) {
                    File file2 = new File(downloadFileInfo.getFileDir(), downloadFileInfo.getTempFileName());
                    if (file2.exists()) {
                        deleteResult = file2.delete();
                    }
                } else {
                    deleteResult = file.delete();
                }
            }
            if (deleteResult) {
                Log.m5156d(TAG, TAG + ".run 文件删除成功url：" + this.mUrl);
            } else {
                failReason = new OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason(this.mUrl, "delete file in path failed !", OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason.TYPE_UNKNOWN);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            failReason = new OnDeleteDownloadFileListener.OnDeleteDownloadFileFailReason(this.mUrl, e2);
        } finally {
            if (failReason == null) {
                notifySuccess(downloadFileInfo);
                Log.m5156d(TAG, TAG + ".run 删除成功，url：" + this.mUrl);
            } else {
                notifyFailed(downloadFileInfo, failReason);
                Log.m5156d(TAG, TAG + ".run 删除失败，url：" + this.mUrl + "，failReason:" + failReason.getType());
            }
            Log.m5156d(TAG, TAG + ".run 文件删除任务【已结束】，是否有异常：" + (failReason == null) + "，url：" + this.mUrl);
        }
    }

    private void notifyPrepared(DownloadFileInfo downloadFileInfo) {
        if (this.mOnDeleteDownloadFileListener != null) {
            if (this.mIsSyncCallback) {
                this.mOnDeleteDownloadFileListener.onDeleteDownloadFilePrepared(downloadFileInfo);
            } else {
                OnDeleteDownloadFileListener.MainThreadHelper.onDeleteDownloadFilePrepared(downloadFileInfo, this.mOnDeleteDownloadFileListener);
            }
        }
    }

    private void notifySuccess(DownloadFileInfo downloadFileInfo) {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true) && this.mOnDeleteDownloadFileListener != null) {
            if (this.mIsSyncCallback) {
                this.mOnDeleteDownloadFileListener.onDeleteDownloadFileSuccess(downloadFileInfo);
            } else {
                OnDeleteDownloadFileListener.MainThreadHelper.onDeleteDownloadFileSuccess(downloadFileInfo, this.mOnDeleteDownloadFileListener);
            }
        }
    }

    private void notifyFailed(DownloadFileInfo downloadFileInfo, OnDeleteDownloadFileListener.DeleteDownloadFileFailReason failReason) {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true) && this.mOnDeleteDownloadFileListener != null) {
            if (this.mIsSyncCallback) {
                this.mOnDeleteDownloadFileListener.onDeleteDownloadFileFailed(downloadFileInfo, failReason);
            } else {
                OnDeleteDownloadFileListener.MainThreadHelper.onDeleteDownloadFileFailed(downloadFileInfo, failReason, this.mOnDeleteDownloadFileListener);
            }
        }
    }
}

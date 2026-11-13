package org.wlf.filedownloader.file_move;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.listener.OnMoveDownloadFileListener;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.FileUtil;

/* JADX INFO: loaded from: classes2.dex */
class MoveDownloadFileTask implements Runnable {
    private static final String TAG = MoveDownloadFileTask.class.getSimpleName();
    private DownloadFileMover mDownloadFileMover;
    private String mNewDirPath;
    private OnMoveDownloadFileListener mOnMoveDownloadFileListener;
    private String mUrl;
    private boolean mIsSyncCallback = false;
    private AtomicBoolean mIsNotifyFinish = new AtomicBoolean(false);

    public MoveDownloadFileTask(String url, String newDirPath, DownloadFileMover downloadFileMover) {
        this.mUrl = url;
        this.mNewDirPath = newDirPath;
        this.mDownloadFileMover = downloadFileMover;
    }

    public void setOnMoveDownloadFileListener(OnMoveDownloadFileListener onMoveDownloadFileListener) {
        this.mOnMoveDownloadFileListener = onMoveDownloadFileListener;
    }

    public void enableSyncCallback() {
        this.mIsSyncCallback = true;
    }

    @Override // java.lang.Runnable
    public void run() {
        File oldFile;
        File newFile;
        boolean z = true;
        DownloadFileInfo downloadFileInfo = null;
        OnMoveDownloadFileListener.MoveDownloadFileFailReason failReason = null;
        try {
            downloadFileInfo = this.mDownloadFileMover.getDownloadFile(this.mUrl);
            if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
                failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, "the DownloadFile is empty !", OnMoveDownloadFileListener.OnMoveDownloadFileFailReason.TYPE_NULL_POINTER);
                return;
            }
            notifyPrepared(downloadFileInfo);
            if (!DownloadFileUtil.canMove(downloadFileInfo)) {
                failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, "the download file status error !", OnMoveDownloadFileListener.OnMoveDownloadFileFailReason.TYPE_FILE_STATUS_ERROR);
                return;
            }
            if (DownloadFileUtil.isCompleted(downloadFileInfo)) {
                oldFile = new File(downloadFileInfo.getFileDir(), downloadFileInfo.getFileName());
                newFile = new File(this.mNewDirPath, downloadFileInfo.getFileName());
            } else {
                oldFile = new File(downloadFileInfo.getFileDir(), downloadFileInfo.getTempFileName());
                newFile = new File(this.mNewDirPath, downloadFileInfo.getTempFileName());
            }
            if (oldFile == null || !oldFile.exists()) {
                failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, "the original file does not exist !", OnMoveDownloadFileListener.OnMoveDownloadFileFailReason.TYPE_ORIGINAL_FILE_NOT_EXIST);
                return;
            }
            if (newFile != null && newFile.exists()) {
                failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, "the target file exist !", OnMoveDownloadFileListener.OnMoveDownloadFileFailReason.TYPE_TARGET_FILE_EXIST);
                return;
            }
            if (newFile != null && newFile.getParentFile() != null && !newFile.getParentFile().exists()) {
                FileUtil.createFileParentDir(newFile.getAbsolutePath());
            }
            String oldDirPath = downloadFileInfo.getFileDir();
            boolean moveResult = false;
            try {
                this.mDownloadFileMover.moveDownloadFile(downloadFileInfo.getUrl(), this.mNewDirPath);
                moveResult = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!moveResult) {
                failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, "update record error !", OnMoveDownloadFileListener.OnMoveDownloadFileFailReason.TYPE_UPDATE_RECORD_ERROR);
                return;
            }
            boolean moveResult2 = oldFile.renameTo(newFile);
            if (moveResult2) {
                return;
            }
            try {
                this.mDownloadFileMover.moveDownloadFile(downloadFileInfo.getUrl(), oldDirPath);
            } catch (Exception e2) {
                e2.printStackTrace();
                try {
                    this.mDownloadFileMover.moveDownloadFile(downloadFileInfo.getUrl(), oldDirPath);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
            failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, "update record error !", OnMoveDownloadFileListener.OnMoveDownloadFileFailReason.TYPE_UPDATE_RECORD_ERROR);
        } catch (Exception e3) {
            e3.printStackTrace();
            failReason = new OnMoveDownloadFileListener.OnMoveDownloadFileFailReason(this.mUrl, e3);
        } finally {
            if (failReason == null) {
                notifySuccess(downloadFileInfo);
                Log.m5156d(TAG, TAG + ".run 移动成功，url：" + this.mUrl);
            } else {
                notifyFailed(downloadFileInfo, failReason);
                Log.m5156d(TAG, TAG + ".run 移动失败，url：" + this.mUrl + ",failReason:" + failReason.getType());
            }
            Log.m5156d(TAG, TAG + ".run 文件移动任务【已结束】，是否有异常：" + (failReason == null) + "，url：" + this.mUrl);
        }
    }

    private void notifyPrepared(DownloadFileInfo downloadFileInfo) {
        if (this.mOnMoveDownloadFileListener != null) {
            if (this.mIsSyncCallback) {
                this.mOnMoveDownloadFileListener.onMoveDownloadFilePrepared(downloadFileInfo);
            } else {
                OnMoveDownloadFileListener.MainThreadHelper.onMoveDownloadFilePrepared(downloadFileInfo, this.mOnMoveDownloadFileListener);
            }
        }
    }

    private void notifySuccess(DownloadFileInfo downloadFileInfo) {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true) && this.mOnMoveDownloadFileListener != null) {
            if (this.mIsSyncCallback) {
                this.mOnMoveDownloadFileListener.onMoveDownloadFileSuccess(downloadFileInfo);
            } else {
                OnMoveDownloadFileListener.MainThreadHelper.onMoveDownloadFileSuccess(downloadFileInfo, this.mOnMoveDownloadFileListener);
            }
        }
    }

    private void notifyFailed(DownloadFileInfo downloadFileInfo, OnMoveDownloadFileListener.MoveDownloadFileFailReason failReason) {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true) && this.mOnMoveDownloadFileListener != null) {
            if (this.mIsSyncCallback) {
                this.mOnMoveDownloadFileListener.onMoveDownloadFileFailed(downloadFileInfo, failReason);
            } else {
                OnMoveDownloadFileListener.MainThreadHelper.onMoveDownloadFileFailed(downloadFileInfo, failReason, this.mOnMoveDownloadFileListener);
            }
        }
    }
}

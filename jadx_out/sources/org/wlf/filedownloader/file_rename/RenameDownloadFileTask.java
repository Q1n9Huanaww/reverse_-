package org.wlf.filedownloader.file_rename;

import android.text.TextUtils;
import java.io.File;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.listener.OnRenameDownloadFileListener;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;

/* JADX INFO: loaded from: classes2.dex */
class RenameDownloadFileTask implements Runnable {
    private static final String TAG = RenameDownloadFileTask.class.getSimpleName();
    private boolean includedSuffix;
    private DownloadFileRenamer mDownloadFileRenamer;
    private AtomicBoolean mIsNotifyFinish = new AtomicBoolean(false);
    private String mNewFileName;
    private OnRenameDownloadFileListener mOnRenameDownloadFileListener;
    private String mUrl;

    public RenameDownloadFileTask(String url, String newFileName, boolean includedSuffix, DownloadFileRenamer downloadFileRenamer) {
        this.mUrl = url;
        this.mNewFileName = newFileName;
        this.includedSuffix = includedSuffix;
        this.mDownloadFileRenamer = downloadFileRenamer;
    }

    public void setOnRenameDownloadFileListener(OnRenameDownloadFileListener onRenameDownloadFileListener) {
        this.mOnRenameDownloadFileListener = onRenameDownloadFileListener;
    }

    /* JADX WARN: Code duplicated, block: B:127:0x05df  */
    /* JADX WARN: Code duplicated, block: B:130:0x0621  */
    /* JADX WARN: Code duplicated, block: B:133:0x0644  */
    /* JADX WARN: Code duplicated, block: B:134:0x067c  */
    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Throwable th;
        OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason;
        boolean z;
        boolean renameResult;
        int index;
        DownloadFileInfo downloadFileInfo = null;
        OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason2 = null;
        try {
            try {
                downloadFileInfo = this.mDownloadFileRenamer.getDownloadFile(this.mUrl);
                if (!DownloadFileUtil.isLegal(downloadFileInfo)) {
                    OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason3 = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "the download file is not exist !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_FILE_RECORD_IS_NOT_EXIST);
                    if (failReason3 == null) {
                        notifySuccess(downloadFileInfo);
                        Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                    } else {
                        notifyFailed(downloadFileInfo, failReason3);
                        Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason3.getType());
                    }
                    Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason3 == null) + "，url：" + this.mUrl);
                    return;
                }
                notifyPrepared(downloadFileInfo);
                if (!DownloadFileUtil.canRename(downloadFileInfo)) {
                    OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason4 = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "the download file status error !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_FILE_STATUS_ERROR);
                    if (failReason4 == null) {
                        notifySuccess(downloadFileInfo);
                        Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                    } else {
                        notifyFailed(downloadFileInfo, failReason4);
                        Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason4.getType());
                    }
                    Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason4 == null) + "，url：" + this.mUrl);
                    return;
                }
                String dirPath = downloadFileInfo.getFileDir();
                String oldFileName = downloadFileInfo.getFileName();
                String suffix = "";
                if (oldFileName != null && oldFileName.contains(".") && (index = oldFileName.lastIndexOf(".")) != -1) {
                    suffix = oldFileName.substring(index, oldFileName.length());
                }
                if (!this.includedSuffix) {
                    this.mNewFileName += suffix;
                }
                File newFile = new File(dirPath, this.mNewFileName);
                if (TextUtils.isEmpty(this.mNewFileName)) {
                    OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason5 = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "new file name is empty !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_NEW_FILE_NAME_IS_EMPTY);
                    if (failReason5 == null) {
                        notifySuccess(downloadFileInfo);
                        Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                    } else {
                        notifyFailed(downloadFileInfo, failReason5);
                        Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason5.getType());
                    }
                    Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason5 == null) + "，url：" + this.mUrl);
                    return;
                }
                if (checkNewFileExist(newFile)) {
                    OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason6 = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "the new file has been exist !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_NEW_FILE_HAS_BEEN_EXIST);
                    if (failReason6 == null) {
                        notifySuccess(downloadFileInfo);
                        Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                    } else {
                        notifyFailed(downloadFileInfo, failReason6);
                        Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason6.getType());
                    }
                    Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason6 == null) + "，url：" + this.mUrl);
                    return;
                }
                boolean renameResult2 = false;
                try {
                    this.mDownloadFileRenamer.renameDownloadFile(downloadFileInfo.getUrl(), this.mNewFileName);
                    renameResult2 = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!renameResult2) {
                    OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason7 = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "rename file in db failed !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_UNKNOWN);
                    if (failReason7 == null) {
                        notifySuccess(downloadFileInfo);
                        Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                    } else {
                        notifyFailed(downloadFileInfo, failReason7);
                        Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason7.getType());
                    }
                    Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason7 == null) + "，url：" + this.mUrl);
                    return;
                }
                if (DownloadFileUtil.isCompleted(downloadFileInfo)) {
                    File oldSaveFile = new File(dirPath, oldFileName);
                    if (oldSaveFile.exists()) {
                        renameResult = oldSaveFile.renameTo(newFile);
                        failReason = null;
                    } else {
                        renameResult = false;
                        failReason = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "the original file not exist !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_ORIGINAL_FILE_NOT_EXIST);
                    }
                    if (!renameResult) {
                        try {
                            try {
                                this.mDownloadFileRenamer.renameDownloadFile(downloadFileInfo.getUrl(), oldFileName);
                            } catch (Exception e2) {
                                try {
                                    e2.printStackTrace();
                                    try {
                                        this.mDownloadFileRenamer.renameDownloadFile(downloadFileInfo.getUrl(), oldFileName);
                                    } catch (Exception e1) {
                                        e1.printStackTrace();
                                    }
                                } catch (Exception e3) {
                                    e = e3;
                                    e.printStackTrace();
                                    OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason8 = new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, e);
                                    if (failReason8 == null) {
                                        notifySuccess(downloadFileInfo);
                                        Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                                    } else {
                                        notifyFailed(downloadFileInfo, failReason8);
                                        Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason8.getType());
                                    }
                                    Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason8 == null) + "，url：" + this.mUrl);
                                    return;
                                }
                            }
                            OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason9 = failReason == null ? new OnRenameDownloadFileListener.OnRenameDownloadFileFailReason(this.mUrl, "rename file in file system failed !", OnRenameDownloadFileListener.OnRenameDownloadFileFailReason.TYPE_UNKNOWN) : failReason;
                            if (failReason9 == null) {
                                notifySuccess(downloadFileInfo);
                                Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                            } else {
                                notifyFailed(downloadFileInfo, failReason9);
                                Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason9.getType());
                            }
                            Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason9 == null) + "，url：" + this.mUrl);
                            return;
                        } catch (Throwable th2) {
                            th = th2;
                            failReason2 = failReason;
                            if (failReason2 == null) {
                                notifySuccess(downloadFileInfo);
                                Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                            } else {
                                notifyFailed(downloadFileInfo, failReason2);
                                Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason2.getType());
                            }
                            String str = TAG;
                            StringBuilder sbAppend = new StringBuilder().append(TAG).append(".run 重命名任务【已结束】，是否有异常：");
                            if (failReason2 == null) {
                                z = true;
                            } else {
                                z = false;
                            }
                            Log.m5156d(str, sbAppend.append(z).append("，url：").append(this.mUrl).toString());
                            throw th;
                        }
                    }
                    failReason2 = failReason;
                }
                if (failReason2 == null) {
                    notifySuccess(downloadFileInfo);
                    Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
                } else {
                    notifyFailed(downloadFileInfo, failReason2);
                    Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason2.getType());
                }
                Log.m5156d(TAG, TAG + ".run 重命名任务【已结束】，是否有异常：" + (failReason2 == null) + "，url：" + this.mUrl);
            } catch (Exception e4) {
                e = e4;
                failReason = null;
            }
        } catch (Throwable th3) {
            th = th3;
            if (failReason2 == null) {
                notifySuccess(downloadFileInfo);
                Log.m5156d(TAG, TAG + ".run 重命名成功，url：" + this.mUrl);
            } else {
                notifyFailed(downloadFileInfo, failReason2);
                Log.m5156d(TAG, TAG + ".run 重命名失败，url：" + this.mUrl + ",failReason:" + failReason2.getType());
            }
            String str2 = TAG;
            StringBuilder sbAppend2 = new StringBuilder().append(TAG).append(".run 重命名任务【已结束】，是否有异常：");
            if (failReason2 == null) {
                z = true;
            } else {
                z = false;
            }
            Log.m5156d(str2, sbAppend2.append(z).append("，url：").append(this.mUrl).toString());
            throw th;
        }
    }

    private boolean checkNewFileExist(File newFile) {
        if (newFile != null && newFile.exists()) {
            return true;
        }
        List<DownloadFileInfo> downloadFileInfos = this.mDownloadFileRenamer.getDownloadFiles();
        if (!CollectionUtil.isEmpty(downloadFileInfos)) {
            for (DownloadFileInfo info : downloadFileInfos) {
                if (info != null) {
                    String path = info.getFilePath();
                    if (!TextUtils.isEmpty(path) && path.equals(newFile.getAbsolutePath())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void notifyPrepared(DownloadFileInfo downloadFileInfo) {
        if (this.mOnRenameDownloadFileListener != null) {
            OnRenameDownloadFileListener.MainThreadHelper.onRenameDownloadFilePrepared(downloadFileInfo, this.mOnRenameDownloadFileListener);
        }
    }

    private void notifySuccess(DownloadFileInfo downloadFileInfo) {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true) && this.mOnRenameDownloadFileListener != null) {
            OnRenameDownloadFileListener.MainThreadHelper.onRenameDownloadFileSuccess(downloadFileInfo, this.mOnRenameDownloadFileListener);
        }
    }

    private void notifyFailed(DownloadFileInfo downloadFileInfo, OnRenameDownloadFileListener.RenameDownloadFileFailReason failReason) {
        if (!this.mIsNotifyFinish.get() && this.mIsNotifyFinish.compareAndSet(false, true) && this.mOnRenameDownloadFileListener != null) {
            OnRenameDownloadFileListener.MainThreadHelper.onRenameDownloadFileFailed(downloadFileInfo, failReason, this.mOnRenameDownloadFileListener);
        }
    }
}

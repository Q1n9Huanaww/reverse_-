package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.FailReason;

/* JADX INFO: loaded from: classes2.dex */
public interface OnRenameDownloadFileListener {
    void onRenameDownloadFileFailed(DownloadFileInfo downloadFileInfo, RenameDownloadFileFailReason renameDownloadFileFailReason);

    void onRenameDownloadFilePrepared(DownloadFileInfo downloadFileInfo);

    void onRenameDownloadFileSuccess(DownloadFileInfo downloadFileInfo);

    public static class MainThreadHelper {
        public static void onRenameDownloadFilePrepared(final DownloadFileInfo downloadFileNeedRename, final OnRenameDownloadFileListener onRenameDownloadFileListener) {
            if (onRenameDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnRenameDownloadFileListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onRenameDownloadFileListener != null) {
                            onRenameDownloadFileListener.onRenameDownloadFilePrepared(downloadFileNeedRename);
                        }
                    }
                });
            }
        }

        public static void onRenameDownloadFileSuccess(final DownloadFileInfo downloadFileRenamed, final OnRenameDownloadFileListener onRenameDownloadFileListener) {
            if (onRenameDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnRenameDownloadFileListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onRenameDownloadFileListener != null) {
                            onRenameDownloadFileListener.onRenameDownloadFileSuccess(downloadFileRenamed);
                        }
                    }
                });
            }
        }

        public static void onRenameDownloadFileFailed(final DownloadFileInfo downloadFileInfo, final RenameDownloadFileFailReason failReason, final OnRenameDownloadFileListener onRenameDownloadFileListener) {
            if (onRenameDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnRenameDownloadFileListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onRenameDownloadFileListener != null) {
                            onRenameDownloadFileListener.onRenameDownloadFileFailed(downloadFileInfo, failReason);
                        }
                    }
                });
            }
        }
    }

    @Deprecated
    public static class OnRenameDownloadFileFailReason extends RenameDownloadFileFailReason {
        public OnRenameDownloadFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public OnRenameDownloadFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }

    public static class RenameDownloadFileFailReason extends FailReason {
        public static final String TYPE_FILE_RECORD_IS_NOT_EXIST = RenameDownloadFileFailReason.class.getName() + "_TYPE_FILE_RECORD_IS_NOT_EXIST";
        public static final String TYPE_ORIGINAL_FILE_NOT_EXIST = RenameDownloadFileFailReason.class.getName() + "_TYPE_ORIGINAL_FILE_NOT_EXIST";
        public static final String TYPE_NEW_FILE_NAME_IS_EMPTY = RenameDownloadFileFailReason.class.getName() + "_TYPE_NEW_FILE_NAME_IS_EMPTY";
        public static final String TYPE_FILE_STATUS_ERROR = RenameDownloadFileFailReason.class.getName() + "_TYPE_FILE_STATUS_ERROR";
        public static final String TYPE_NEW_FILE_HAS_BEEN_EXIST = RenameDownloadFileFailReason.class.getName() + "_TYPE_NEW_FILE_HAS_BEEN_EXIST";

        public RenameDownloadFileFailReason(String url, String detailMessage, String type) {
            super(detailMessage, type);
        }

        public RenameDownloadFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }
}

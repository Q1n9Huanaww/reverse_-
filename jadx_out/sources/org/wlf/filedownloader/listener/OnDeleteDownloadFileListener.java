package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.UrlFailReason;

/* JADX INFO: loaded from: classes2.dex */
public interface OnDeleteDownloadFileListener {
    void onDeleteDownloadFileFailed(DownloadFileInfo downloadFileInfo, DeleteDownloadFileFailReason deleteDownloadFileFailReason);

    void onDeleteDownloadFilePrepared(DownloadFileInfo downloadFileInfo);

    void onDeleteDownloadFileSuccess(DownloadFileInfo downloadFileInfo);

    public static class MainThreadHelper {
        public static void onDeleteDownloadFilePrepared(final DownloadFileInfo downloadFileNeedDelete, final OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
            if (onDeleteDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDeleteDownloadFileListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDeleteDownloadFileListener != null) {
                            onDeleteDownloadFileListener.onDeleteDownloadFilePrepared(downloadFileNeedDelete);
                        }
                    }
                });
            }
        }

        public static void onDeleteDownloadFileSuccess(final DownloadFileInfo downloadFileDeleted, final OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
            if (onDeleteDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDeleteDownloadFileListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDeleteDownloadFileListener != null) {
                            onDeleteDownloadFileListener.onDeleteDownloadFileSuccess(downloadFileDeleted);
                        }
                    }
                });
            }
        }

        public static void onDeleteDownloadFileFailed(final DownloadFileInfo downloadFileInfo, final DeleteDownloadFileFailReason failReason, final OnDeleteDownloadFileListener onDeleteDownloadFileListener) {
            if (onDeleteDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDeleteDownloadFileListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDeleteDownloadFileListener != null) {
                            onDeleteDownloadFileListener.onDeleteDownloadFileFailed(downloadFileInfo, failReason);
                        }
                    }
                });
            }
        }
    }

    @Deprecated
    public static class OnDeleteDownloadFileFailReason extends DeleteDownloadFileFailReason {
        public OnDeleteDownloadFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public OnDeleteDownloadFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }

    public static class DeleteDownloadFileFailReason extends UrlFailReason {
        public static final String TYPE_FILE_RECORD_IS_NOT_EXIST = DeleteDownloadFileFailReason.class.getName() + "_TYPE_FILE_RECORD_IS_NOT_EXIST";
        public static final String TYPE_FILE_STATUS_ERROR = DeleteDownloadFileFailReason.class.getName() + "_TYPE_RECORD_FILE_STATUS_ERROR";

        public DeleteDownloadFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public DeleteDownloadFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }
}

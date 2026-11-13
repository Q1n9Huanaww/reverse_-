package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.UrlFailReason;

/* JADX INFO: loaded from: classes2.dex */
public interface OnMoveDownloadFileListener {
    void onMoveDownloadFileFailed(DownloadFileInfo downloadFileInfo, MoveDownloadFileFailReason moveDownloadFileFailReason);

    void onMoveDownloadFilePrepared(DownloadFileInfo downloadFileInfo);

    void onMoveDownloadFileSuccess(DownloadFileInfo downloadFileInfo);

    public static class MainThreadHelper {
        public static void onMoveDownloadFilePrepared(final DownloadFileInfo downloadFileNeedToMove, final OnMoveDownloadFileListener onMoveDownloadFileListener) {
            if (onMoveDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnMoveDownloadFileListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onMoveDownloadFileListener != null) {
                            onMoveDownloadFileListener.onMoveDownloadFilePrepared(downloadFileNeedToMove);
                        }
                    }
                });
            }
        }

        public static void onMoveDownloadFileSuccess(final DownloadFileInfo downloadFileMoved, final OnMoveDownloadFileListener onMoveDownloadFileListener) {
            if (onMoveDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnMoveDownloadFileListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onMoveDownloadFileListener != null) {
                            onMoveDownloadFileListener.onMoveDownloadFileSuccess(downloadFileMoved);
                        }
                    }
                });
            }
        }

        public static void onMoveDownloadFileFailed(final DownloadFileInfo downloadFileInfo, final MoveDownloadFileFailReason failReason, final OnMoveDownloadFileListener onMoveDownloadFileListener) {
            if (onMoveDownloadFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnMoveDownloadFileListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onMoveDownloadFileListener != null) {
                            onMoveDownloadFileListener.onMoveDownloadFileFailed(downloadFileInfo, failReason);
                        }
                    }
                });
            }
        }
    }

    @Deprecated
    public static class OnMoveDownloadFileFailReason extends MoveDownloadFileFailReason {
        public OnMoveDownloadFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public OnMoveDownloadFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }

    public static class MoveDownloadFileFailReason extends UrlFailReason {
        public static final String TYPE_TARGET_FILE_EXIST = MoveDownloadFileFailReason.class.getName() + "_TYPE_TARGET_FILE_EXIST";
        public static final String TYPE_ORIGINAL_FILE_NOT_EXIST = MoveDownloadFileFailReason.class.getName() + "_TYPE_ORIGINAL_FILE_NOT_EXIST";
        public static final String TYPE_UPDATE_RECORD_ERROR = MoveDownloadFileFailReason.class.getName() + "_TYPE_UPDATE_RECORD_ERROR";
        public static final String TYPE_FILE_STATUS_ERROR = MoveDownloadFileFailReason.class.getName() + "_TYPE_FILE_STATUS_ERROR";

        public MoveDownloadFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public MoveDownloadFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }
}

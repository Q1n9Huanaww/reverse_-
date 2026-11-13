package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.file_download.base.HttpFailReason;

/* JADX INFO: loaded from: classes2.dex */
public interface OnDetectBigUrlFileListener {
    void onDetectNewDownloadFile(String str, String str2, String str3, long j);

    void onDetectUrlFileExist(String str);

    void onDetectUrlFileFailed(String str, DetectBigUrlFileFailReason detectBigUrlFileFailReason);

    public static class MainThreadHelper {
        public static void onDetectNewDownloadFile(final String url, final String fileName, final String saveDir, final long fileSize, final OnDetectBigUrlFileListener nnDetectBigUrlFileListener) {
            if (nnDetectBigUrlFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDetectBigUrlFileListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (nnDetectBigUrlFileListener != null) {
                            nnDetectBigUrlFileListener.onDetectNewDownloadFile(url, fileName, saveDir, fileSize);
                        }
                    }
                });
            }
        }

        public static void onDetectUrlFileExist(final String url, final OnDetectBigUrlFileListener nnDetectBigUrlFileListener) {
            if (nnDetectBigUrlFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDetectBigUrlFileListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (nnDetectBigUrlFileListener != null) {
                            nnDetectBigUrlFileListener.onDetectUrlFileExist(url);
                        }
                    }
                });
            }
        }

        public static void onDetectUrlFileFailed(final String url, final DetectBigUrlFileFailReason failReason, final OnDetectBigUrlFileListener nnDetectBigUrlFileListener) {
            if (nnDetectBigUrlFileListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDetectBigUrlFileListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (nnDetectBigUrlFileListener != null) {
                            nnDetectBigUrlFileListener.onDetectUrlFileFailed(url, failReason);
                        }
                    }
                });
            }
        }
    }

    public static class DetectBigUrlFileFailReason extends HttpFailReason {
        public static final String TYPE_URL_ILLEGAL = DetectBigUrlFileFailReason.class.getName() + "_TYPE_URL_ILLEGAL";
        public static final String TYPE_URL_OVER_REDIRECT_COUNT = DetectBigUrlFileFailReason.class.getName() + "_TYPE_URL_OVER_REDIRECT_COUNT";
        public static final String TYPE_BAD_HTTP_RESPONSE_CODE = DetectBigUrlFileFailReason.class.getName() + "_TYPE_BAD_HTTP_RESPONSE_CODE";
        public static final String TYPE_HTTP_FILE_NOT_EXIST = DetectBigUrlFileFailReason.class.getName() + "_TYPE_HTTP_FILE_NOT_EXIST";

        public DetectBigUrlFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public DetectBigUrlFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }
}

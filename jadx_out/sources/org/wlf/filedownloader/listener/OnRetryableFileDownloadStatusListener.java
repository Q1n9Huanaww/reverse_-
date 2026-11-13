package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface OnRetryableFileDownloadStatusListener extends OnFileDownloadStatusListener {
    void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int i);

    public static class MainThreadHelper {
        public static void onFileDownloadStatusRetrying(final DownloadFileInfo downloadFileInfo, final int retryTimes, final OnRetryableFileDownloadStatusListener onRetryableFileDownloadStatusListener) {
            if (onRetryableFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onRetryableFileDownloadStatusListener != null) {
                            onRetryableFileDownloadStatusListener.onFileDownloadStatusRetrying(downloadFileInfo, retryTimes);
                        }
                    }
                });
            }
        }
    }
}

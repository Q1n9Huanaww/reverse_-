package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface OnDownloadFileChangeListener {

    public enum Type {
        DOWNLOAD_STATUS,
        DOWNLOADED_SIZE,
        SAVE_DIR,
        SAVE_FILE_NAME,
        OTHER
    }

    void onDownloadFileCreated(DownloadFileInfo downloadFileInfo);

    void onDownloadFileDeleted(DownloadFileInfo downloadFileInfo);

    void onDownloadFileUpdated(DownloadFileInfo downloadFileInfo, Type type);

    public static class MainThreadHelper {
        public static void onDownloadFileCreated(final DownloadFileInfo downloadFileInfo, final OnDownloadFileChangeListener onDownloadFileChangeListener) {
            if (onDownloadFileChangeListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDownloadFileChangeListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDownloadFileChangeListener != null) {
                            onDownloadFileChangeListener.onDownloadFileCreated(downloadFileInfo);
                        }
                    }
                });
            }
        }

        public static void onDownloadFileUpdated(final DownloadFileInfo downloadFileInfo, final Type type, final OnDownloadFileChangeListener onDownloadFileChangeListener) {
            if (onDownloadFileChangeListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDownloadFileChangeListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDownloadFileChangeListener != null) {
                            onDownloadFileChangeListener.onDownloadFileUpdated(downloadFileInfo, type);
                        }
                    }
                });
            }
        }

        public static void onDownloadFileDeleted(final DownloadFileInfo downloadFileInfo, final OnDownloadFileChangeListener onDownloadFileChangeListener) {
            if (onDownloadFileChangeListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDownloadFileChangeListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDownloadFileChangeListener != null) {
                            onDownloadFileChangeListener.onDownloadFileDeleted(downloadFileInfo);
                        }
                    }
                });
            }
        }
    }
}

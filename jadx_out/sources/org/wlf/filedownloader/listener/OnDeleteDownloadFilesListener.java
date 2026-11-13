package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import java.util.List;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface OnDeleteDownloadFilesListener {
    void onDeleteDownloadFilesCompleted(List<DownloadFileInfo> list, List<DownloadFileInfo> list2);

    void onDeleteDownloadFilesPrepared(List<DownloadFileInfo> list);

    void onDeletingDownloadFiles(List<DownloadFileInfo> list, List<DownloadFileInfo> list2, List<DownloadFileInfo> list3, DownloadFileInfo downloadFileInfo);

    public static class MainThreadHelper {
        public static void onDeleteDownloadFilesPrepared(final List<DownloadFileInfo> downloadFilesNeedDelete, final OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
            if (onDeleteDownloadFilesListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDeleteDownloadFilesListener != null) {
                            onDeleteDownloadFilesListener.onDeleteDownloadFilesPrepared(downloadFilesNeedDelete);
                        }
                    }
                });
            }
        }

        public static void onDeletingDownloadFiles(final List<DownloadFileInfo> downloadFilesNeedDelete, final List<DownloadFileInfo> downloadFilesDeleted, final List<DownloadFileInfo> downloadFilesSkip, final DownloadFileInfo downloadFileDeleting, final OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
            if (onDeleteDownloadFilesListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDeleteDownloadFilesListener != null) {
                            onDeleteDownloadFilesListener.onDeletingDownloadFiles(downloadFilesNeedDelete, downloadFilesDeleted, downloadFilesSkip, downloadFileDeleting);
                        }
                    }
                });
            }
        }

        public static void onDeleteDownloadFilesCompleted(final List<DownloadFileInfo> downloadFilesNeedDelete, final List<DownloadFileInfo> downloadFilesDeleted, final OnDeleteDownloadFilesListener onDeleteDownloadFilesListener) {
            if (onDeleteDownloadFilesListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnDeleteDownloadFilesListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onDeleteDownloadFilesListener != null) {
                            onDeleteDownloadFilesListener.onDeleteDownloadFilesCompleted(downloadFilesNeedDelete, downloadFilesDeleted);
                        }
                    }
                });
            }
        }
    }
}

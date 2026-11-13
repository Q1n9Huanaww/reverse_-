package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import java.util.List;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface OnMoveDownloadFilesListener {
    void onMoveDownloadFilesCompleted(List<DownloadFileInfo> list, List<DownloadFileInfo> list2);

    void onMoveDownloadFilesPrepared(List<DownloadFileInfo> list);

    void onMovingDownloadFiles(List<DownloadFileInfo> list, List<DownloadFileInfo> list2, List<DownloadFileInfo> list3, DownloadFileInfo downloadFileInfo);

    public static class MainThreadHelper {
        public static void onMoveDownloadFilesPrepared(final List<DownloadFileInfo> downloadFilesNeedMove, final OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
            if (onMoveDownloadFilesListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnMoveDownloadFilesListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onMoveDownloadFilesListener != null) {
                            onMoveDownloadFilesListener.onMoveDownloadFilesPrepared(downloadFilesNeedMove);
                        }
                    }
                });
            }
        }

        public static void onMovingDownloadFiles(final List<DownloadFileInfo> downloadFilesNeedMove, final List<DownloadFileInfo> downloadFilesMoved, final List<DownloadFileInfo> downloadFilesSkip, final DownloadFileInfo downloadFileMoving, final OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
            if (onMoveDownloadFilesListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnMoveDownloadFilesListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onMoveDownloadFilesListener != null) {
                            onMoveDownloadFilesListener.onMovingDownloadFiles(downloadFilesNeedMove, downloadFilesMoved, downloadFilesSkip, downloadFileMoving);
                        }
                    }
                });
            }
        }

        public static void onMoveDownloadFilesCompleted(final List<DownloadFileInfo> downloadFilesNeedMove, final List<DownloadFileInfo> downloadFilesMoved, final OnMoveDownloadFilesListener onMoveDownloadFilesListener) {
            if (onMoveDownloadFilesListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnMoveDownloadFilesListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onMoveDownloadFilesListener != null) {
                            onMoveDownloadFilesListener.onMoveDownloadFilesCompleted(downloadFilesNeedMove, downloadFilesMoved);
                        }
                    }
                });
            }
        }
    }
}

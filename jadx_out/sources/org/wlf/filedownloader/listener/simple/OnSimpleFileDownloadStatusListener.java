package org.wlf.filedownloader.listener.simple;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloadConfiguration;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener;

/* JADX INFO: loaded from: classes2.dex */
public abstract class OnSimpleFileDownloadStatusListener implements OnRetryableFileDownloadStatusListener {
    @Override // org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener
    public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long remainingTime) {
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
        Context appContext = getAppContext();
        if (appContext != null) {
            String fileName = downloadFileInfo != null ? downloadFileInfo.getFileName() : null;
            if (!TextUtils.isEmpty(fileName)) {
                Toast.makeText(appContext, "Download  " + fileName + "  completed !", 0).show();
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
        Context appContext = getAppContext();
        if (appContext != null) {
            String fileName = downloadFileInfo != null ? downloadFileInfo.getFileName() : null;
            if (!TextUtils.isEmpty(fileName)) {
                Toast.makeText(appContext, "Download  " + fileName + "  error !", 0).show();
            }
        }
    }

    private Context getAppContext() {
        FileDownloadConfiguration configuration;
        if (!FileDownloader.isInit() || (configuration = FileDownloader.getFileDownloadConfiguration()) == null) {
            return null;
        }
        Context appContext = configuration.getContext();
        return appContext;
    }
}

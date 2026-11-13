package org.wlf.filedownloader.file_download;

import android.support.v4.os.EnvironmentCompat;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.DownloadStatusConfiguration;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class DownloadStatusObserver implements OnRetryableFileDownloadStatusListener {
    private static final String TAG = DownloadStatusObserver.class.getSimpleName();
    private Set<DownloadStatusListenerInfo> mDownloadStatusListenerInfos = new CopyOnWriteArraySet();

    DownloadStatusObserver() {
    }

    public void addOnFileDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener, DownloadStatusConfiguration downloadStatusConfiguration) {
        if (onFileDownloadStatusListener != null) {
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo == null || listenerInfo.mListener == onFileDownloadStatusListener) {
                    return;
                }
            }
            this.mDownloadStatusListenerInfos.add(new DownloadStatusListenerInfo(downloadStatusConfiguration, onFileDownloadStatusListener));
            String urls = (downloadStatusConfiguration == null || CollectionUtil.isEmpty(downloadStatusConfiguration.getListenUrls())) ? "all" : downloadStatusConfiguration.getListenUrls().toString();
            Log.m5160i(TAG, "file-downloader-listener 添加【文件下载状态监听器】成功，该listener监听的urls：" + urls);
        }
    }

    public void removeOnFileDownloadStatusListener(OnFileDownloadStatusListener onFileDownloadStatusListener) {
        String urls;
        if (onFileDownloadStatusListener != null) {
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener == onFileDownloadStatusListener) {
                    this.mDownloadStatusListenerInfos.remove(listenerInfo);
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        urls = "all";
                    } else {
                        urls = listenerInfo.mDownloadStatusConfiguration.getListenUrls().toString();
                    }
                    Log.m5160i(TAG, "file-downloader-listener 移除【文件下载状态监听器】成功，该listener监听的urls：" + urls);
                    return;
                }
            }
        }
    }

    private void notifyStatusWaiting(DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusWaiting(downloadFileInfo, listener);
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为等待】，文件的url：" + url);
    }

    private void notifyStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes, OnFileDownloadStatusListener listener) {
        if (listener instanceof OnRetryableFileDownloadStatusListener) {
            OnRetryableFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusRetrying(downloadFileInfo, retryTimes, (OnRetryableFileDownloadStatusListener) listener);
            String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
            Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为重试】，重试次数：" + retryTimes + "，文件的url：" + url);
        }
    }

    private void notifyStatusPreparing(DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusPreparing(downloadFileInfo, listener);
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为准备中（正在连接）】，文件的url：" + url);
    }

    private void notifyStatusPrepared(DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusPrepared(downloadFileInfo, listener);
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为已准备（已连接）】，文件的url：" + url);
    }

    private void notifyStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long remainingTime, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusDownloading(downloadFileInfo, downloadSpeed, remainingTime, listener);
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为正在下载】，文件的url：" + url);
    }

    private void notifyStatusPaused(DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusPaused(downloadFileInfo, listener);
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为暂停】，文件的url：" + url);
    }

    private void notifyStatusCompleted(DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusCompleted(downloadFileInfo, listener);
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为完成】，文件的url：" + url);
    }

    private void notifyStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason, OnFileDownloadStatusListener listener) {
        OnFileDownloadStatusListener.MainThreadHelper.onFileDownloadStatusFailed(url, downloadFileInfo, failReason, listener);
        String downloadFileUrl = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        String failMsg = failReason != null ? failReason.getMessage() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【文件下载状态为失败】，文件的url：" + url + "，downloadFileUrl：" + downloadFileUrl + "，失败原因：" + failMsg);
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusWaiting(downloadFileInfo, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusWaiting(downloadFileInfo, listenerInfo.mListener);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnRetryableFileDownloadStatusListener
    public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this && (listenerInfo.mListener instanceof OnRetryableFileDownloadStatusListener)) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusRetrying(downloadFileInfo, retryTimes, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusRetrying(downloadFileInfo, retryTimes, listenerInfo.mListener);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusPreparing(downloadFileInfo, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusPreparing(downloadFileInfo, listenerInfo.mListener);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusPrepared(downloadFileInfo, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusPrepared(downloadFileInfo, listenerInfo.mListener);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long remainingTime) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusDownloading(downloadFileInfo, downloadSpeed, remainingTime, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusDownloading(downloadFileInfo, downloadSpeed, remainingTime, listenerInfo.mListener);
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusPaused(downloadFileInfo, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusPaused(downloadFileInfo, listenerInfo.mListener);
                                if (listenerInfo.mDownloadStatusConfiguration.isAutoRelease()) {
                                    this.mDownloadStatusListenerInfos.remove(listenerInfo);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusCompleted(downloadFileInfo, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusCompleted(downloadFileInfo, listenerInfo.mListener);
                                if (listenerInfo.mDownloadStatusConfiguration.isAutoRelease()) {
                                    this.mDownloadStatusListenerInfos.remove(listenerInfo);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener
    public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, OnFileDownloadStatusListener.FileDownloadStatusFailReason failReason) {
        if (UrlUtil.isUrl(url)) {
            for (DownloadStatusListenerInfo listenerInfo : this.mDownloadStatusListenerInfos) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadStatusConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadStatusConfiguration.getListenUrls())) {
                        notifyStatusFailed(url, downloadFileInfo, failReason, listenerInfo.mListener);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadStatusConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyStatusFailed(url, downloadFileInfo, failReason, listenerInfo.mListener);
                                if (listenerInfo.mDownloadStatusConfiguration.isAutoRelease()) {
                                    this.mDownloadStatusListenerInfos.remove(listenerInfo);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void release() {
        this.mDownloadStatusListenerInfos.clear();
    }

    private static class DownloadStatusListenerInfo {
        private DownloadStatusConfiguration mDownloadStatusConfiguration;
        private OnFileDownloadStatusListener mListener;

        public DownloadStatusListenerInfo(DownloadStatusConfiguration downloadStatusConfiguration, OnFileDownloadStatusListener listener) {
            this.mDownloadStatusConfiguration = downloadStatusConfiguration;
            this.mListener = listener;
        }
    }
}

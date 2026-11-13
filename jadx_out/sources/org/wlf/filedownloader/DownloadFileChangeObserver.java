package org.wlf.filedownloader;

import android.support.v4.os.EnvironmentCompat;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.listener.OnDownloadFileChangeListener;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class DownloadFileChangeObserver implements OnDownloadFileChangeListener {
    private static final String TAG = DownloadFileChangeObserver.class.getSimpleName();
    private Set<DownloadFileChangeListenerInfo> mOnDownloadFileChangeListeners = new CopyOnWriteArraySet();

    DownloadFileChangeObserver() {
    }

    public void addOnDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener, DownloadFileChangeConfiguration downloadFileChangeConfiguration) {
        if (onDownloadFileChangeListener != null) {
            for (DownloadFileChangeListenerInfo listenerInfo : this.mOnDownloadFileChangeListeners) {
                if (listenerInfo == null || listenerInfo.mListener == onDownloadFileChangeListener) {
                    return;
                }
            }
            this.mOnDownloadFileChangeListeners.add(new DownloadFileChangeListenerInfo(downloadFileChangeConfiguration, onDownloadFileChangeListener));
            String urls = (downloadFileChangeConfiguration == null || CollectionUtil.isEmpty(downloadFileChangeConfiguration.getListenUrls())) ? "all" : downloadFileChangeConfiguration.getListenUrls().toString();
            Log.m5160i(TAG, "file-downloader-listener 添加【下载文件改变监听器】成功，该listener监听的urls：" + urls);
        }
    }

    public void removeOnDownloadFileChangeListener(OnDownloadFileChangeListener onDownloadFileChangeListener) {
        String urls;
        if (onDownloadFileChangeListener != null) {
            for (DownloadFileChangeListenerInfo listenerInfo : this.mOnDownloadFileChangeListeners) {
                if (listenerInfo != null && listenerInfo.mListener == onDownloadFileChangeListener) {
                    this.mOnDownloadFileChangeListeners.remove(listenerInfo);
                    if (listenerInfo.mDownloadFileChangeConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadFileChangeConfiguration.getListenUrls())) {
                        urls = "all";
                    } else {
                        urls = listenerInfo.mDownloadFileChangeConfiguration.getListenUrls().toString();
                    }
                    Log.m5160i(TAG, "file-downloader-listener 移除【下载文件改变监听器】成功，该listener监听的urls：" + urls);
                    return;
                }
            }
        }
    }

    private void notifyDownloadFileCreated(DownloadFileInfo downloadFileInfo, OnDownloadFileChangeListener listener, boolean sync) {
        if (sync) {
            if (listener != null) {
                try {
                    listener.onDownloadFileCreated(downloadFileInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            OnDownloadFileChangeListener.MainThreadHelper.onDownloadFileCreated(downloadFileInfo, listener);
        }
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【下载文件被创建】，被创建文件的url：" + url);
    }

    private void notifyDownloadFileUpdated(DownloadFileInfo downloadFileInfo, OnDownloadFileChangeListener.Type type, OnDownloadFileChangeListener listener, boolean sync) {
        if (sync) {
            if (listener != null) {
                try {
                    listener.onDownloadFileUpdated(downloadFileInfo, type);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            OnDownloadFileChangeListener.MainThreadHelper.onDownloadFileUpdated(downloadFileInfo, type, listener);
        }
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        String typeName = type != null ? type.name() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【下载文件被更新】，更新类型：" + typeName + "，被更新文件的url：" + url);
    }

    private void notifyDownloadFileDeleted(DownloadFileInfo downloadFileInfo, OnDownloadFileChangeListener listener, boolean sync) {
        if (sync) {
            if (listener != null) {
                try {
                    listener.onDownloadFileDeleted(downloadFileInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else {
            OnDownloadFileChangeListener.MainThreadHelper.onDownloadFileDeleted(downloadFileInfo, listener);
        }
        String url = downloadFileInfo != null ? downloadFileInfo.getUrl() : EnvironmentCompat.MEDIA_UNKNOWN;
        Log.m5160i(TAG, "file-downloader-listener 通知【下载文件被删除】，被删除文件的url：" + url);
    }

    @Override // org.wlf.filedownloader.listener.OnDownloadFileChangeListener
    public void onDownloadFileCreated(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadFileChangeListenerInfo listenerInfo : this.mOnDownloadFileChangeListeners) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadFileChangeConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadFileChangeConfiguration.getListenUrls())) {
                        boolean isSyncCallback = false;
                        if (listenerInfo.mDownloadFileChangeConfiguration != null) {
                            isSyncCallback = listenerInfo.mDownloadFileChangeConfiguration.isTreadCallback();
                        }
                        notifyDownloadFileCreated(downloadFileInfo, listenerInfo.mListener, isSyncCallback);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadFileChangeConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyDownloadFileCreated(downloadFileInfo, listenerInfo.mListener, listenerInfo.mDownloadFileChangeConfiguration.isTreadCallback());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnDownloadFileChangeListener
    public void onDownloadFileUpdated(DownloadFileInfo downloadFileInfo, OnDownloadFileChangeListener.Type type) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadFileChangeListenerInfo listenerInfo : this.mOnDownloadFileChangeListeners) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadFileChangeConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadFileChangeConfiguration.getListenUrls())) {
                        boolean isSyncCallback = false;
                        if (listenerInfo.mDownloadFileChangeConfiguration != null) {
                            isSyncCallback = listenerInfo.mDownloadFileChangeConfiguration.isTreadCallback();
                        }
                        notifyDownloadFileUpdated(downloadFileInfo, type, listenerInfo.mListener, isSyncCallback);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadFileChangeConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyDownloadFileUpdated(downloadFileInfo, type, listenerInfo.mListener, listenerInfo.mDownloadFileChangeConfiguration.isTreadCallback());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override // org.wlf.filedownloader.listener.OnDownloadFileChangeListener
    public void onDownloadFileDeleted(DownloadFileInfo downloadFileInfo) {
        if (DownloadFileUtil.isLegal(downloadFileInfo)) {
            String url = downloadFileInfo.getUrl();
            for (DownloadFileChangeListenerInfo listenerInfo : this.mOnDownloadFileChangeListeners) {
                if (listenerInfo != null && listenerInfo.mListener != null && listenerInfo.mListener != this) {
                    if (listenerInfo.mDownloadFileChangeConfiguration == null || CollectionUtil.isEmpty(listenerInfo.mDownloadFileChangeConfiguration.getListenUrls())) {
                        boolean isSyncCallback = false;
                        if (listenerInfo.mDownloadFileChangeConfiguration != null) {
                            isSyncCallback = listenerInfo.mDownloadFileChangeConfiguration.isTreadCallback();
                        }
                        notifyDownloadFileDeleted(downloadFileInfo, listenerInfo.mListener, isSyncCallback);
                    } else {
                        for (String listenUrl : listenerInfo.mDownloadFileChangeConfiguration.getListenUrls()) {
                            if (UrlUtil.isUrl(listenUrl) && (url.equals(listenUrl) || url.trim().equals(listenUrl.trim()))) {
                                notifyDownloadFileDeleted(downloadFileInfo, listenerInfo.mListener, listenerInfo.mDownloadFileChangeConfiguration.isTreadCallback());
                            }
                        }
                    }
                }
            }
        }
    }

    public void release() {
        this.mOnDownloadFileChangeListeners.clear();
    }

    private class DownloadFileChangeListenerInfo {
        private DownloadFileChangeConfiguration mDownloadFileChangeConfiguration;
        private OnDownloadFileChangeListener mListener;

        public DownloadFileChangeListenerInfo(DownloadFileChangeConfiguration downloadFileChangeConfiguration, OnDownloadFileChangeListener listener) {
            this.mDownloadFileChangeConfiguration = downloadFileChangeConfiguration;
            this.mListener = listener;
        }
    }
}

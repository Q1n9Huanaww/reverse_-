package org.wlf.filedownloader.file_download.base;

/* JADX INFO: loaded from: classes2.dex */
public interface Pauseable {
    boolean isDownloading(String str);

    void pause(String str, OnStopFileDownloadTaskListener onStopFileDownloadTaskListener);
}

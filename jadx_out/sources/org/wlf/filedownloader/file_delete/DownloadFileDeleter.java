package org.wlf.filedownloader.file_delete;

import org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbRecorder;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadFileDeleter extends DownloadFileDbRecorder {
    void deleteDownloadFile(String str) throws Exception;
}

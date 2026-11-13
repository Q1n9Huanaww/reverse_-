package org.wlf.filedownloader.file_rename;

import org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbRecorder;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadFileRenamer extends DownloadFileDbRecorder {
    void renameDownloadFile(String str, String str2) throws Exception;
}

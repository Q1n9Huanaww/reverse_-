package org.wlf.filedownloader.file_download.base;

import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.file_download.DetectUrlFileInfo;
import org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbRecorder;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadRecorder extends DownloadFileDbRecorder {
    DownloadFileInfo createDownloadFileInfo(DetectUrlFileInfo detectUrlFileInfo);

    void resetDownloadFile(String str, boolean z) throws Exception;

    void resetDownloadSize(String str, long j) throws Exception;
}

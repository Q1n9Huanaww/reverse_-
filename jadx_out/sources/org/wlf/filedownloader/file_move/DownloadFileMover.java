package org.wlf.filedownloader.file_move;

import org.wlf.filedownloader.file_download.db_recorder.DownloadFileDbRecorder;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadFileMover extends DownloadFileDbRecorder {
    void moveDownloadFile(String str, String str2) throws Exception;
}

package org.wlf.filedownloader.file_download.db_recorder;

import java.util.List;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadFileDbRecorder extends Record {
    DownloadFileInfo getDownloadFile(String str);

    List<DownloadFileInfo> getDownloadFiles();
}

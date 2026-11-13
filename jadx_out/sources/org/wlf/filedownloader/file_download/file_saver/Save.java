package org.wlf.filedownloader.file_download.file_saver;

import org.wlf.filedownloader.file_download.http_downloader.ContentLengthInputStream;

/* JADX INFO: loaded from: classes2.dex */
public interface Save {
    void saveData(ContentLengthInputStream contentLengthInputStream, long j) throws Exception;
}

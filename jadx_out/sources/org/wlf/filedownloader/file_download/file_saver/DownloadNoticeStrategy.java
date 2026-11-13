package org.wlf.filedownloader.file_download.file_saver;

/* JADX INFO: loaded from: classes2.dex */
public enum DownloadNoticeStrategy {
    NOTICE_AUTO(-1),
    NOTICE_BY_SIZE(1048576),
    NOTICE_BY_TIME(2000);

    private long mValue;

    DownloadNoticeStrategy(long value) {
        this.mValue = value;
    }

    public long getValue() {
        return this.mValue;
    }
}

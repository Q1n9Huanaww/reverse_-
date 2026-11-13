package org.wlf.filedownloader.listener;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public interface OnDetectUrlFileListener {
    void onDetectNewDownloadFile(String str, String str2, String str3, int i);

    void onDetectUrlFileExist(String str);

    void onDetectUrlFileFailed(String str, DetectUrlFileFailReason detectUrlFileFailReason);

    @Deprecated
    public static class DetectUrlFileFailReason extends OnDetectBigUrlFileListener.DetectBigUrlFileFailReason {
        public DetectUrlFileFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public DetectUrlFileFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }
}

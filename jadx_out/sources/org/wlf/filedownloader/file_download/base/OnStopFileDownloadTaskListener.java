package org.wlf.filedownloader.file_download.base;

import org.wlf.filedownloader.base.FailReason;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;

/* JADX INFO: loaded from: classes2.dex */
public interface OnStopFileDownloadTaskListener {
    void onStopFileDownloadTaskFailed(String str, StopDownloadFileTaskFailReason stopDownloadFileTaskFailReason);

    void onStopFileDownloadTaskSucceed(String str);

    public static class StopDownloadFileTaskFailReason extends OnFileDownloadStatusListener.FileDownloadStatusFailReason {
        public static final String TYPE_TASK_HAS_BEEN_STOPPED = StopDownloadFileTaskFailReason.class.getName() + "_TYPE_TASK_HAS_BEEN_STOPPED";

        public StopDownloadFileTaskFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        @Override // org.wlf.filedownloader.listener.OnFileDownloadStatusListener.FileDownloadStatusFailReason, org.wlf.filedownloader.base.FailReason
        protected void onInitTypeWithFailReason(FailReason failReason) {
            super.onInitTypeWithFailReason(failReason);
            if (failReason != null && (failReason instanceof OnFileDownloadStatusListener.FileDownloadStatusFailReason)) {
                OnFileDownloadStatusListener.FileDownloadStatusFailReason fileDownloadStatusFailReason = (OnFileDownloadStatusListener.FileDownloadStatusFailReason) failReason;
                setType(fileDownloadStatusFailReason.getType());
            }
        }
    }
}

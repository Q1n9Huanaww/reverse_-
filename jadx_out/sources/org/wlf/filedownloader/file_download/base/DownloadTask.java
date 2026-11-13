package org.wlf.filedownloader.file_download.base;

import org.wlf.filedownloader.base.Stoppable;

/* JADX INFO: loaded from: classes2.dex */
public interface DownloadTask extends Runnable, Stoppable {
    String getUrl();

    void setOnStopFileDownloadTaskListener(OnStopFileDownloadTaskListener onStopFileDownloadTaskListener);

    void setOnTaskRunFinishListener(OnTaskRunFinishListener onTaskRunFinishListener);
}

package org.wlf.filedownloader.listener;

import android.os.Handler;
import android.os.Looper;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.FailReason;
import org.wlf.filedownloader.file_download.base.HttpFailReason;
import org.wlf.filedownloader.file_download.file_saver.FileSaver;
import org.wlf.filedownloader.file_download.http_downloader.HttpDownloader;

/* JADX INFO: loaded from: classes2.dex */
public interface OnFileDownloadStatusListener {
    void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo);

    void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float f, long j);

    void onFileDownloadStatusFailed(String str, DownloadFileInfo downloadFileInfo, FileDownloadStatusFailReason fileDownloadStatusFailReason);

    void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo);

    void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo);

    void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo);

    void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo);

    public static class MainThreadHelper {
        public static void onFileDownloadStatusWaiting(final DownloadFileInfo downloadFileInfo, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusWaiting(downloadFileInfo);
                        }
                    }
                });
            }
        }

        public static void onFileDownloadStatusPreparing(final DownloadFileInfo downloadFileInfo, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.2
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusPreparing(downloadFileInfo);
                        }
                    }
                });
            }
        }

        public static void onFileDownloadStatusPrepared(final DownloadFileInfo downloadFileInfo, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.3
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusPrepared(downloadFileInfo);
                        }
                    }
                });
            }
        }

        public static void onFileDownloadStatusDownloading(final DownloadFileInfo downloadFileInfo, final float downloadSpeed, final long remainingTime, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.4
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusDownloading(downloadFileInfo, downloadSpeed, remainingTime);
                        }
                    }
                });
            }
        }

        public static void onFileDownloadStatusPaused(final DownloadFileInfo downloadFileInfo, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.5
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusPaused(downloadFileInfo);
                        }
                    }
                });
            }
        }

        public static void onFileDownloadStatusCompleted(final DownloadFileInfo downloadFileInfo, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.6
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusCompleted(downloadFileInfo);
                        }
                    }
                });
            }
        }

        public static void onFileDownloadStatusFailed(final String url, final DownloadFileInfo downloadFileInfo, final FileDownloadStatusFailReason failReason, final OnFileDownloadStatusListener onFileDownloadStatusListener) {
            if (onFileDownloadStatusListener != null) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() { // from class: org.wlf.filedownloader.listener.OnFileDownloadStatusListener.MainThreadHelper.7
                    @Override // java.lang.Runnable
                    public void run() {
                        if (onFileDownloadStatusListener != null) {
                            onFileDownloadStatusListener.onFileDownloadStatusFailed(url, downloadFileInfo, failReason);
                        }
                    }
                });
            }
        }
    }

    @Deprecated
    public static class OnFileDownloadStatusFailReason extends FileDownloadStatusFailReason {
        public OnFileDownloadStatusFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public OnFileDownloadStatusFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }
    }

    public static class FileDownloadStatusFailReason extends HttpFailReason {
        public static final String TYPE_URL_ILLEGAL = FileDownloadStatusFailReason.class.getName() + "_TYPE_URL_ILLEGAL";
        public static final String TYPE_URL_OVER_REDIRECT_COUNT = FileDownloadStatusFailReason.class.getName() + "_TYPE_URL_OVER_REDIRECT_COUNT";
        public static final String TYPE_BAD_HTTP_RESPONSE_CODE = FileDownloadStatusFailReason.class.getName() + "_TYPE_BAD_HTTP_RESPONSE_CODE";
        public static final String TYPE_HTTP_FILE_NOT_EXIST = FileDownloadStatusFailReason.class.getName() + "_TYPE_HTTP_FILE_NOT_EXIST";
        public static final String TYPE_FILE_SAVE_PATH_ILLEGAL = FileDownloadStatusFailReason.class.getName() + "_TYPE_FILE_SAVE_PATH_ILLEGAL";
        public static final String TYPE_STORAGE_SPACE_CAN_NOT_WRITE = FileDownloadStatusFailReason.class.getName() + "_TYPE_STORAGE_SPACE_CAN_NOT_WRITE";
        public static final String TYPE_RENAME_TEMP_FILE_ERROR = FileDownloadStatusFailReason.class.getName() + "_TYPE_RENAME_TEMP_FILE_ERROR";
        public static final String TYPE_STORAGE_SPACE_IS_FULL = FileDownloadStatusFailReason.class.getName() + "_TYPE_STORAGE_SPACE_IS_FULL";
        public static final String TYPE_SAVE_FILE_NOT_EXIST = FileDownloadStatusFailReason.class.getName() + "_TYPE_SAVE_FILE_NOT_EXIST";
        public static final String TYPE_FILE_NOT_DETECT = FileDownloadStatusFailReason.class.getName() + "_TYPE_FILE_NOT_DETECT";
        public static final String TYPE_DOWNLOAD_FILE_ERROR = FileDownloadStatusFailReason.class.getName() + "_TYPE_DOWNLOAD_FILE_ERROR";
        public static final String TYPE_URL_FILE_CHANGED = FileDownloadStatusFailReason.class.getName() + "_TYPE_URL_FILE_CHANGED";

        @Deprecated
        public static final String TYPE_FILE_IS_DOWNLOADING = FileDownloadStatusFailReason.class.getName() + "_TYPE_FILE_IS_DOWNLOADING";

        public FileDownloadStatusFailReason(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public FileDownloadStatusFailReason(String url, Throwable throwable) {
            super(url, throwable);
        }

        @Override // org.wlf.filedownloader.base.FailReason
        protected void onInitTypeWithFailReason(FailReason failReason) {
            super.onInitTypeWithFailReason(failReason);
            if (failReason != null) {
                if (failReason instanceof HttpFailReason) {
                    HttpFailReason httpFailReason = (HttpFailReason) failReason;
                    setType(httpFailReason.getType());
                    if (!isTypeInit()) {
                        if (failReason instanceof HttpDownloader.HttpDownloadException) {
                            HttpDownloader.HttpDownloadException httpDownloadException = (HttpDownloader.HttpDownloadException) failReason;
                            String type = httpDownloadException.getType();
                            if (!HttpDownloader.HttpDownloadException.TYPE_CONTENT_RANGE_VALIDATE_FAIL.equals(type)) {
                                if (HttpDownloader.HttpDownloadException.TYPE_ETAG_CHANGED.equals(type)) {
                                    setType(TYPE_URL_FILE_CHANGED);
                                    return;
                                }
                                if (HttpDownloader.HttpDownloadException.TYPE_REDIRECT_COUNT_OVER_LIMITS.equals(type)) {
                                    setType(TYPE_URL_OVER_REDIRECT_COUNT);
                                    return;
                                } else if (HttpDownloader.HttpDownloadException.TYPE_RESOURCES_SIZE_ILLEGAL.equals(type)) {
                                    setType(TYPE_DOWNLOAD_FILE_ERROR);
                                    return;
                                } else {
                                    if (HttpDownloader.HttpDownloadException.TYPE_RESPONSE_CODE_ERROR.equals(type)) {
                                        setType(TYPE_BAD_HTTP_RESPONSE_CODE);
                                        return;
                                    }
                                    return;
                                }
                            }
                            return;
                        }
                        if (failReason instanceof OnDetectUrlFileListener.DetectUrlFileFailReason) {
                            OnDetectUrlFileListener.DetectUrlFileFailReason detectUrlFileFailReason = (OnDetectUrlFileListener.DetectUrlFileFailReason) failReason;
                            String type2 = detectUrlFileFailReason.getType();
                            if (OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_BAD_HTTP_RESPONSE_CODE.equals(type2)) {
                                setType(TYPE_BAD_HTTP_RESPONSE_CODE);
                                return;
                            }
                            if (OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_HTTP_FILE_NOT_EXIST.equals(type2)) {
                                setType(TYPE_FILE_NOT_DETECT);
                                return;
                            } else if (OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_URL_ILLEGAL.equals(type2)) {
                                setType(TYPE_URL_ILLEGAL);
                                return;
                            } else {
                                if (OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_URL_OVER_REDIRECT_COUNT.equals(type2)) {
                                    setType(TYPE_URL_OVER_REDIRECT_COUNT);
                                    return;
                                }
                                return;
                            }
                        }
                        return;
                    }
                    return;
                }
                if (failReason instanceof FileSaver.FileSaveException) {
                    FileSaver.FileSaveException fileSaveException = (FileSaver.FileSaveException) failReason;
                    String type3 = fileSaveException.getType();
                    if (FileSaver.FileSaveException.TYPE_FILE_CAN_NOT_STORAGE.equals(type3)) {
                        setType(TYPE_STORAGE_SPACE_CAN_NOT_WRITE);
                        return;
                    }
                    if (FileSaver.FileSaveException.TYPE_RENAME_TEMP_FILE_ERROR.equals(type3)) {
                        setType(TYPE_RENAME_TEMP_FILE_ERROR);
                    } else if (!FileSaver.FileSaveException.TYPE_SAVER_HAS_BEEN_STOPPED.equals(type3) && FileSaver.FileSaveException.TYPE_TEMP_FILE_DOES_NOT_EXIST.equals(type3)) {
                        setType(TYPE_SAVE_FILE_NOT_EXIST);
                    }
                }
            }
        }
    }
}

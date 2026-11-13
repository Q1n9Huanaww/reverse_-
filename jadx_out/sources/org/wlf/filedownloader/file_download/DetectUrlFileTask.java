package org.wlf.filedownloader.file_download;

import android.text.TextUtils;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.base.DownloadRecorder;
import org.wlf.filedownloader.listener.OnDetectBigUrlFileListener;
import org.wlf.filedownloader.listener.OnDetectUrlFileListener;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class DetectUrlFileTask implements Runnable {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    private static final String DEFAULT_REQUEST_METHOD = "GET";
    private static final int MAX_REDIRECT_TIMES = 5;
    private static final String TAG = DetectUrlFileTask.class.getSimpleName();
    private ExecutorService mCloseConnectionEngine;
    private DetectUrlFileCacher mDetectUrlFileCacher;
    private DownloadRecorder mDownloadRecorder;
    private String mDownloadSaveDir;
    private Map<String, String> mHeaders;
    private OnDetectBigUrlFileListener mOnDetectBigUrlFileListener;
    private String mUrl;
    private int mConnectTimeout = 15000;
    private String mCharset = "UTF-8";
    private String mRequestMethod = "GET";
    private boolean mIsForceDetect = false;

    public DetectUrlFileTask(String url, String downloadSaveDir, DetectUrlFileCacher detectUrlFileCacher, DownloadRecorder downloadRecorder) {
        this.mUrl = url;
        this.mDownloadSaveDir = downloadSaveDir;
        this.mDetectUrlFileCacher = detectUrlFileCacher;
        this.mDownloadRecorder = downloadRecorder;
    }

    public void setOnDetectBigUrlFileListener(OnDetectBigUrlFileListener onDetectBigUrlFileListener) {
        this.mOnDetectBigUrlFileListener = onDetectBigUrlFileListener;
    }

    public void setCloseConnectionEngine(ExecutorService closeConnectionEngine) {
        this.mCloseConnectionEngine = closeConnectionEngine;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
    }

    public void setRequestMethod(String requestMethod) {
        this.mRequestMethod = requestMethod;
    }

    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    public void enableForceDetect() {
        this.mIsForceDetect = true;
    }

    /* JADX WARN: Code duplicated, block: B:101:0x0432 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:104:0x0459 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:105:0x045b  */
    /* JADX WARN: Code duplicated, block: B:108:0x0476  */
    /* JADX WARN: Code duplicated, block: B:148:0x0638  */
    /* JADX WARN: Code duplicated, block: B:151:0x0650  */
    /* JADX WARN: Code duplicated, block: B:182:0x075a  */
    /* JADX WARN: Code duplicated, block: B:192:0x079e A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:193:0x07a0  */
    /* JADX WARN: Code duplicated, block: B:196:0x07bd  */
    /* JADX WARN: Code duplicated, block: B:199:0x0812  */
    /* JADX WARN: Code duplicated, block: B:202:0x082a  */
    /* JADX WARN: Code duplicated, block: B:94:0x041b  */
    /* JADX WARN: Code duplicated, block: B:97:0x0426  */
    @Override // java.lang.Runnable
    public void run() throws Throwable {
        DetectUrlFileInfo detectUrlFileInfo;
        CloseConnectionTask closeConnectionTask;
        boolean isNotify;
        boolean hasException;
        OnDetectUrlFileListener.DetectUrlFileFailReason failReason;
        CloseConnectionTask closeConnectionTask2;
        boolean isNotify2;
        boolean hasException2;
        OnDetectUrlFileListener.DetectUrlFileFailReason failReason2;
        OnDetectUrlFileListener.DetectUrlFileFailReason failReason3;
        HttpURLConnection conn = null;
        DownloadFileInfo downloadFileInfo = null;
        DetectUrlFileInfo detectUrlFileInfo2 = null;
        OnDetectUrlFileListener.DetectUrlFileFailReason failReason4 = null;
        try {
            if (!UrlUtil.isUrl(this.mUrl)) {
                OnDetectUrlFileListener.DetectUrlFileFailReason failReason5 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "url illegal !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_URL_ILLEGAL);
                CloseConnectionTask closeConnectionTask3 = new CloseConnectionTask(null, null);
                if (this.mCloseConnectionEngine != null) {
                    this.mCloseConnectionEngine.execute(closeConnectionTask3);
                } else {
                    closeConnectionTask3.run();
                }
                boolean isNotify3 = false;
                if (0 != 0 && !this.mIsForceDetect) {
                    isNotify3 = notifyDetectUrlFileExist();
                }
                if (isNotify3 || 0 == 0) {
                    failReason3 = failReason5;
                } else {
                    try {
                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                        isNotify3 = notifyDetectNewDownloadFile(detectUrlFileInfo2.getFileName(), detectUrlFileInfo2.getFileDir(), detectUrlFileInfo2.getFileSizeLong());
                        failReason3 = failReason5;
                    } catch (Exception e) {
                        e.printStackTrace();
                        failReason3 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e);
                    }
                }
                if (!isNotify3) {
                    if (failReason3 == null) {
                        failReason3 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                    }
                    isNotify3 = notifyDetectUrlFileFailed(failReason3);
                }
                boolean hasException3 = failReason3 == null;
                Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException3 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify3 + "，url：" + this.mUrl);
                return;
            }
            downloadFileInfo = this.mDownloadRecorder.getDownloadFile(this.mUrl);
            if (downloadFileInfo != null && !this.mIsForceDetect) {
                CloseConnectionTask closeConnectionTask4 = new CloseConnectionTask(null, null);
                if (this.mCloseConnectionEngine != null) {
                    this.mCloseConnectionEngine.execute(closeConnectionTask4);
                } else {
                    closeConnectionTask4.run();
                }
                boolean isNotify4 = false;
                if (downloadFileInfo != null && !this.mIsForceDetect) {
                    isNotify4 = notifyDetectUrlFileExist();
                }
                if (!isNotify4 && 0 != 0) {
                    try {
                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                        isNotify4 = notifyDetectNewDownloadFile(detectUrlFileInfo2.getFileName(), detectUrlFileInfo2.getFileDir(), detectUrlFileInfo2.getFileSizeLong());
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e2);
                    }
                }
                if (!isNotify4) {
                    if (failReason4 == null) {
                        failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                    }
                    isNotify4 = notifyDetectUrlFileFailed(failReason4);
                }
                boolean hasException4 = failReason4 == null;
                Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException4 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify4 + "，url：" + this.mUrl);
                return;
            }
            String connectUrl = this.mUrl;
            conn = HttpConnectionHelper.createDetectConnection(connectUrl, this.mConnectTimeout, this.mCharset, this.mRequestMethod, this.mHeaders);
            int redirectCount = 0;
            while (conn.getResponseCode() / 100 == 3 && redirectCount < 5) {
                connectUrl = conn.getHeaderField("Location");
                conn = HttpConnectionHelper.createDetectConnection(connectUrl, this.mConnectTimeout, this.mCharset, this.mRequestMethod, this.mHeaders);
                redirectCount++;
            }
            Log.m5156d(TAG, TAG + ".run 探测文件，重定向：" + redirectCount + "次，最大重定向次数：5，url：" + this.mUrl);
            if (redirectCount > 5) {
                OnDetectUrlFileListener.DetectUrlFileFailReason failReason6 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "over max redirect:5!", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_URL_OVER_REDIRECT_COUNT);
                CloseConnectionTask closeConnectionTask5 = new CloseConnectionTask(conn, null);
                if (this.mCloseConnectionEngine != null) {
                    this.mCloseConnectionEngine.execute(closeConnectionTask5);
                } else {
                    closeConnectionTask5.run();
                }
                boolean isNotify5 = false;
                if (downloadFileInfo != null && !this.mIsForceDetect) {
                    isNotify5 = notifyDetectUrlFileExist();
                }
                if (isNotify5 || 0 == 0) {
                    failReason2 = failReason6;
                } else {
                    try {
                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                        isNotify5 = notifyDetectNewDownloadFile(detectUrlFileInfo2.getFileName(), detectUrlFileInfo2.getFileDir(), detectUrlFileInfo2.getFileSizeLong());
                        failReason2 = failReason6;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        failReason2 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e3);
                    }
                }
                if (!isNotify5) {
                    if (failReason2 == null) {
                        failReason2 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                    }
                    isNotify5 = notifyDetectUrlFileFailed(failReason2);
                }
                boolean hasException5 = failReason2 == null;
                Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException5 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify5 + "，url：" + this.mUrl);
                return;
            }
            Log.m5160i(TAG, TAG + ".run Response Headers:" + HttpConnectionHelper.getStringHeaders(conn.getHeaderFields()));
            switch (conn.getResponseCode()) {
                case 200:
                    String fileName = HttpConnectionHelper.getFileNameFromResponseHeader(conn.getHeaderFields());
                    if (TextUtils.isEmpty(fileName)) {
                        fileName = UrlUtil.getFileNameByUrl(connectUrl, this.mCharset);
                    } else if (UrlUtil.isEncoded(fileName, this.mCharset)) {
                        fileName = UrlUtil.decode(fileName, this.mCharset);
                    }
                    String eTag = conn.getHeaderField("ETag");
                    String acceptRangeType = conn.getHeaderField("Accept-Ranges");
                    String lastModified = HttpConnectionHelper.getLastModifiedFromResponseHeader(conn.getHeaderFields());
                    long fileSize = conn.getContentLength();
                    if (fileSize <= 0) {
                        fileSize = HttpConnectionHelper.getFileSizeFromResponseHeader(conn.getHeaderFields());
                    }
                    if (fileSize > 0) {
                        detectUrlFileInfo = new DetectUrlFileInfo(this.mUrl, fileSize, eTag, lastModified, acceptRangeType, this.mDownloadSaveDir, fileName);
                        try {
                            try {
                                this.mDetectUrlFileCacher.addOrUpdateDetectUrlFile(detectUrlFileInfo);
                                CloseConnectionTask closeConnectionTask6 = new CloseConnectionTask(conn, null);
                                if (this.mCloseConnectionEngine != null) {
                                    this.mCloseConnectionEngine.execute(closeConnectionTask6);
                                } else {
                                    closeConnectionTask6.run();
                                }
                                boolean isNotify6 = false;
                                if (downloadFileInfo != null && !this.mIsForceDetect) {
                                    isNotify6 = notifyDetectUrlFileExist();
                                }
                                if (!isNotify6 && detectUrlFileInfo != null) {
                                    try {
                                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                                        isNotify6 = notifyDetectNewDownloadFile(detectUrlFileInfo.getFileName(), detectUrlFileInfo.getFileDir(), detectUrlFileInfo.getFileSizeLong());
                                    } catch (Exception e4) {
                                        e4.printStackTrace();
                                        failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e4);
                                    }
                                    break;
                                }
                                if (!isNotify6) {
                                    if (failReason4 == null) {
                                        failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                                    }
                                    isNotify6 = notifyDetectUrlFileFailed(failReason4);
                                }
                                boolean hasException6 = failReason4 == null;
                                Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException6 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify6 + "，url：" + this.mUrl);
                                return;
                            } catch (Exception e5) {
                                e = e5;
                                e.printStackTrace();
                                OnDetectUrlFileListener.DetectUrlFileFailReason failReason7 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e);
                                CloseConnectionTask closeConnectionTask7 = new CloseConnectionTask(conn, null);
                                if (this.mCloseConnectionEngine != null) {
                                    this.mCloseConnectionEngine.execute(closeConnectionTask7);
                                } else {
                                    closeConnectionTask7.run();
                                }
                                boolean isNotify7 = false;
                                if (downloadFileInfo != null && !this.mIsForceDetect) {
                                    isNotify7 = notifyDetectUrlFileExist();
                                }
                                if (isNotify7 || detectUrlFileInfo == null) {
                                    failReason = failReason7;
                                } else {
                                    try {
                                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                                        isNotify7 = notifyDetectNewDownloadFile(detectUrlFileInfo.getFileName(), detectUrlFileInfo.getFileDir(), detectUrlFileInfo.getFileSizeLong());
                                        failReason = failReason7;
                                    } catch (Exception e6) {
                                        e6.printStackTrace();
                                        failReason = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e6);
                                    }
                                }
                                if (!isNotify7) {
                                    if (failReason == null) {
                                        failReason = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                                    }
                                    isNotify7 = notifyDetectUrlFileFailed(failReason);
                                }
                                boolean hasException7 = failReason == null;
                                Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException7 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify7 + "，url：" + this.mUrl);
                                return;
                            }
                        } catch (Throwable th) {
                            th = th;
                            closeConnectionTask = new CloseConnectionTask(conn, null);
                            if (this.mCloseConnectionEngine != null) {
                                this.mCloseConnectionEngine.execute(closeConnectionTask);
                            } else {
                                closeConnectionTask.run();
                            }
                            isNotify = false;
                            if (downloadFileInfo != null && !this.mIsForceDetect) {
                                isNotify = notifyDetectUrlFileExist();
                            }
                            if (!isNotify && detectUrlFileInfo != null) {
                                try {
                                    this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                                    isNotify = notifyDetectNewDownloadFile(detectUrlFileInfo.getFileName(), detectUrlFileInfo.getFileDir(), detectUrlFileInfo.getFileSizeLong());
                                } catch (Exception e7) {
                                    e7.printStackTrace();
                                    failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e7);
                                }
                                break;
                            }
                            if (!isNotify) {
                                if (failReason4 == null) {
                                    failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                                }
                                isNotify = notifyDetectUrlFileFailed(failReason4);
                            }
                            if (failReason4 == null) {
                                hasException = true;
                            } else {
                                hasException = false;
                            }
                            Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify + "，url：" + this.mUrl);
                            throw th;
                        }
                    }
                    closeConnectionTask2 = new CloseConnectionTask(conn, null);
                    if (this.mCloseConnectionEngine != null) {
                        this.mCloseConnectionEngine.execute(closeConnectionTask2);
                    } else {
                        closeConnectionTask2.run();
                    }
                    isNotify2 = false;
                    if (downloadFileInfo != null && !this.mIsForceDetect) {
                        isNotify2 = notifyDetectUrlFileExist();
                    }
                    if (!isNotify2 && 0 != 0) {
                        try {
                            this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                            isNotify2 = notifyDetectNewDownloadFile(detectUrlFileInfo2.getFileName(), detectUrlFileInfo2.getFileDir(), detectUrlFileInfo2.getFileSizeLong());
                        } catch (Exception e8) {
                            e8.printStackTrace();
                            failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, e8);
                        }
                        break;
                    }
                    if (!isNotify2) {
                        if (failReason4 == null) {
                            failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                        }
                        isNotify2 = notifyDetectUrlFileFailed(failReason4);
                    }
                    if (failReason4 == null) {
                        hasException2 = true;
                    } else {
                        hasException2 = false;
                    }
                    Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException2 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify2 + "，url：" + this.mUrl);
                    return;
                case GET_TEXT_VALUE:
                    failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "url file does not exist !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_HTTP_FILE_NOT_EXIST);
                    closeConnectionTask2 = new CloseConnectionTask(conn, null);
                    if (this.mCloseConnectionEngine != null) {
                        this.mCloseConnectionEngine.execute(closeConnectionTask2);
                    } else {
                        closeConnectionTask2.run();
                    }
                    isNotify2 = false;
                    if (downloadFileInfo != null) {
                        isNotify2 = notifyDetectUrlFileExist();
                    }
                    if (!isNotify2) {
                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                        isNotify2 = notifyDetectNewDownloadFile(detectUrlFileInfo2.getFileName(), detectUrlFileInfo2.getFileDir(), detectUrlFileInfo2.getFileSizeLong());
                        break;
                    }
                    if (!isNotify2) {
                        if (failReason4 == null) {
                            failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                        }
                        isNotify2 = notifyDetectUrlFileFailed(failReason4);
                    }
                    if (failReason4 == null) {
                        hasException2 = true;
                    } else {
                        hasException2 = false;
                    }
                    Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException2 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify2 + "，url：" + this.mUrl);
                    return;
                default:
                    failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "ResponseCode:" + conn.getResponseCode() + " error, can not read data !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_BAD_HTTP_RESPONSE_CODE);
                    closeConnectionTask2 = new CloseConnectionTask(conn, null);
                    if (this.mCloseConnectionEngine != null) {
                        this.mCloseConnectionEngine.execute(closeConnectionTask2);
                    } else {
                        closeConnectionTask2.run();
                    }
                    isNotify2 = false;
                    if (downloadFileInfo != null) {
                        isNotify2 = notifyDetectUrlFileExist();
                    }
                    if (!isNotify2) {
                        this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                        isNotify2 = notifyDetectNewDownloadFile(detectUrlFileInfo2.getFileName(), detectUrlFileInfo2.getFileDir(), detectUrlFileInfo2.getFileSizeLong());
                        break;
                    }
                    if (!isNotify2) {
                        if (failReason4 == null) {
                            failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                        }
                        isNotify2 = notifyDetectUrlFileFailed(failReason4);
                    }
                    if (failReason4 == null) {
                        hasException2 = true;
                    } else {
                        hasException2 = false;
                    }
                    Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException2 + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify2 + "，url：" + this.mUrl);
                    return;
            }
        } catch (Exception e9) {
            e = e9;
            detectUrlFileInfo = null;
        } catch (Throwable th2) {
            th = th2;
            detectUrlFileInfo = null;
            closeConnectionTask = new CloseConnectionTask(conn, null);
            if (this.mCloseConnectionEngine != null) {
                this.mCloseConnectionEngine.execute(closeConnectionTask);
            } else {
                closeConnectionTask.run();
            }
            isNotify = false;
            if (downloadFileInfo != null) {
                isNotify = notifyDetectUrlFileExist();
            }
            if (!isNotify) {
                this.mDownloadRecorder.resetDownloadFile(this.mUrl, true);
                isNotify = notifyDetectNewDownloadFile(detectUrlFileInfo.getFileName(), detectUrlFileInfo.getFileDir(), detectUrlFileInfo.getFileSizeLong());
            }
            if (!isNotify) {
                if (failReason4 == null) {
                    failReason4 = new OnDetectUrlFileListener.DetectUrlFileFailReason(this.mUrl, "the file need to download may not access !", OnDetectUrlFileListener.DetectUrlFileFailReason.TYPE_UNKNOWN);
                }
                isNotify = notifyDetectUrlFileFailed(failReason4);
            }
            if (failReason4 == null) {
                hasException = true;
            } else {
                hasException = false;
            }
            Log.m5156d(TAG, TAG + ".run 探测文件任务【已结束】，是否有异常：" + hasException + "，是否强制探测模式：" + this.mIsForceDetect + "，是否成功通知了调用者（如果没有设置监听者认为没有通知成功）：" + isNotify + "，url：" + this.mUrl);
            throw th;
        }
    }

    private boolean notifyDetectUrlFileExist() {
        Log.m5156d(TAG, "探测文件已存在，url：" + this.mUrl);
        if (this.mOnDetectBigUrlFileListener != null) {
            OnDetectBigUrlFileListener.MainThreadHelper.onDetectUrlFileExist(this.mUrl, this.mOnDetectBigUrlFileListener);
            return true;
        }
        return true;
    }

    private boolean notifyDetectNewDownloadFile(String fileName, String saveDir, long fileSize) {
        Log.m5156d(TAG, "探测文件不存在，需要创建，url：" + this.mUrl);
        if (this.mOnDetectBigUrlFileListener != null) {
            OnDetectBigUrlFileListener.MainThreadHelper.onDetectNewDownloadFile(this.mUrl, fileName, saveDir, fileSize, this.mOnDetectBigUrlFileListener);
            return true;
        }
        return true;
    }

    private boolean notifyDetectUrlFileFailed(OnDetectBigUrlFileListener.DetectBigUrlFileFailReason failReason) {
        Log.m5156d(TAG, "探测文件失败，url：" + this.mUrl);
        if (this.mOnDetectBigUrlFileListener != null) {
            OnDetectBigUrlFileListener.MainThreadHelper.onDetectUrlFileFailed(this.mUrl, failReason, this.mOnDetectBigUrlFileListener);
            return true;
        }
        return true;
    }
}

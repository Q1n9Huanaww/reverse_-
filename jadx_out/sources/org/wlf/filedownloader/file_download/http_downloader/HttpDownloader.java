package org.wlf.filedownloader.file_download.http_downloader;

import android.text.TextUtils;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.wlf.filedownloader.base.FailReason;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.file_download.CloseConnectionTask;
import org.wlf.filedownloader.file_download.HttpConnectionHelper;
import org.wlf.filedownloader.file_download.base.HttpFailReason;
import org.wlf.filedownloader.file_download.file_saver.FileSaver;

/* JADX INFO: loaded from: classes2.dex */
public class HttpDownloader implements Download {
    private static final int CONNECT_TIMEOUT = 15000;
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_REQUEST_METHOD = "GET";
    private static final int MAX_REDIRECT_TIMES = 5;
    private static final String TAG = HttpDownloader.class.getSimpleName();
    private String mAcceptRangeType;
    private ExecutorService mCloseConnectionEngine;
    private String mETag;
    private Map<String, String> mHeaders;
    private String mLastModified;
    private OnHttpDownloadListener mOnHttpDownloadListener;
    private OnRangeChangeListener mOnRangeChangeListener;
    private Range mRange;
    private String mUrl;
    private int mConnectTimeout = 15000;
    private String mCharset = "UTF-8";
    private String mRequestMethod = "GET";

    public interface OnHttpDownloadListener {
        void onDownloadConnected(ContentLengthInputStream contentLengthInputStream, long j);
    }

    public interface OnRangeChangeListener {
        boolean onRangeChanged(Range range, Range range2);
    }

    public HttpDownloader(String url, Range range, String acceptRangeType, String eTag, String lastModified) {
        this.mUrl = url;
        this.mRange = range;
        this.mAcceptRangeType = acceptRangeType;
        this.mETag = eTag;
        this.mLastModified = lastModified;
    }

    public void setOnHttpDownloadListener(OnHttpDownloadListener onHttpDownloadListener) {
        this.mOnHttpDownloadListener = onHttpDownloadListener;
    }

    public void setOnRangeChangeListener(OnRangeChangeListener onRangeChangeListener) {
        this.mOnRangeChangeListener = onRangeChangeListener;
    }

    public void setCloseConnectionEngine(ExecutorService closeConnectionEngine) {
        this.mCloseConnectionEngine = closeConnectionEngine;
    }

    public void setRequestMethod(String requestMethod) {
        this.mRequestMethod = requestMethod;
    }

    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;
    }

    @Override // org.wlf.filedownloader.file_download.http_downloader.Download
    public void download() throws Throwable {
        String url = this.mUrl;
        HttpURLConnection conn = null;
        ContentLengthInputStream inputStream = null;
        try {
            try {
                HttpConnectionHelper.RequestParam requestParam = new HttpConnectionHelper.RequestParam(url, this.mConnectTimeout, this.mCharset, this.mRange.startPos, this.mRange.endPos, this.mETag, this.mLastModified);
                requestParam.setRequestMethod(this.mRequestMethod);
                requestParam.setHeaders(this.mHeaders);
                conn = HttpConnectionHelper.createDownloadFileConnection(requestParam);
                int redirectTimes = 0;
                while (conn != null && conn.getResponseCode() / 100 == 3 && redirectTimes < 5) {
                    requestParam.setUrl(conn.getHeaderField("Location"));
                    conn = HttpConnectionHelper.createDownloadFileConnection(requestParam);
                    redirectTimes++;
                }
                Log.m5156d(TAG, TAG + ".download 1、准备下载，重定向：" + redirectTimes + "次，最大重定向次数：5，url：" + url);
                if (redirectTimes > 5) {
                    throw new HttpDownloadException(url, "over max redirect:5!", HttpDownloadException.TYPE_REDIRECT_COUNT_OVER_LIMITS);
                }
                if (conn == null) {
                    throw new HttpDownloadException(url, "the connection is null:5!", HttpDownloadException.TYPE_NULL_POINTER);
                }
                Log.m5160i(TAG, TAG + ".download Response Headers:" + HttpConnectionHelper.getStringHeaders(conn.getHeaderFields()));
                int responseCode = conn.getResponseCode();
                if (responseCode == 200 || responseCode == 206) {
                    long contentLength = conn.getContentLength();
                    if (contentLength <= 0) {
                        contentLength = HttpConnectionHelper.getFileSizeFromResponseHeader(conn.getHeaderFields());
                    }
                    Log.m5156d(TAG, TAG + ".download 2、得到服务器返回的资源contentLength：" + contentLength + "，传入的range：" + this.mRange.toString() + "，url：" + url);
                    if (contentLength <= 0) {
                        throw new HttpDownloadException(url, "content length illegal,get url file failed!", HttpDownloadException.TYPE_RESOURCES_SIZE_ILLEGAL);
                    }
                    if (responseCode == 200) {
                        if (!Range.isLegal(this.mRange) || (this.mRange != null && (this.mRange.startPos != 0 || this.mRange.getLength() != contentLength))) {
                            Range newRange = new Range(0L, contentLength);
                            boolean continueDownload = notifyRangeChanged(this.mRange, newRange);
                            if (continueDownload) {
                                this.mRange = new Range(newRange.startPos, newRange.endPos);
                            } else {
                                throw new HttpDownloadException(url, "contentRange validate failed!", HttpDownloadException.TYPE_CONTENT_RANGE_VALIDATE_FAIL);
                            }
                        } else {
                            this.mRange = new Range(0L, contentLength);
                        }
                    } else if (responseCode == 206) {
                        if (!TextUtils.isEmpty(this.mETag)) {
                            String eTag = conn.getHeaderField("ETag");
                            Log.m5156d(TAG, TAG + ".download 3、得到服务器返回的资源eTag：" + eTag + "，传入的eTag：" + this.mETag + "，url：" + url);
                            if (TextUtils.isEmpty(eTag) || !this.mETag.equals(eTag)) {
                                throw new HttpDownloadException(url, "eTag is not equal,please delete the old one then re-download!", HttpDownloadException.TYPE_ETAG_CHANGED);
                            }
                        }
                        if (!Range.isLegal(this.mRange) || (this.mRange != null && this.mRange.getLength() > contentLength)) {
                            Range newRange2 = new Range(0L, contentLength);
                            boolean continueDownload2 = notifyRangeChanged(this.mRange, newRange2);
                            if (continueDownload2) {
                                this.mRange = new Range(newRange2.startPos, newRange2.endPos);
                            } else {
                                throw new HttpDownloadException(url, "contentRange validate failed!", HttpDownloadException.TYPE_CONTENT_RANGE_VALIDATE_FAIL);
                            }
                        }
                        if (this.mRange != null && !TextUtils.isEmpty(this.mAcceptRangeType)) {
                            boolean isRangeValidateSucceed = false;
                            String contentRange = conn.getHeaderField("Content-Range");
                            ContentRangeInfo contentRangeInfo = ContentRangeInfo.getContentRangeInfo(contentRange);
                            if (contentRangeInfo != null) {
                                Range serverResponseRange = new Range(contentRangeInfo.startPos, contentRangeInfo.endPos);
                                if (this.mRange.equals(serverResponseRange) && this.mAcceptRangeType.equals(contentRangeInfo.contentType) && serverResponseRange.getLength() == contentLength) {
                                    isRangeValidateSucceed = true;
                                }
                            }
                            if (!isRangeValidateSucceed) {
                                throw new HttpDownloadException(url, "contentRange validate failed!", HttpDownloadException.TYPE_CONTENT_RANGE_VALIDATE_FAIL);
                            }
                        }
                    }
                    if (!TextUtils.isEmpty(this.mETag)) {
                        String eTag2 = conn.getHeaderField("ETag");
                        Log.m5156d(TAG, TAG + ".download 3、得到服务器返回的资源eTag：" + eTag2 + "，传入的eTag：" + this.mETag + "，url：" + url);
                        if (TextUtils.isEmpty(eTag2) || !this.mETag.equals(eTag2)) {
                            throw new HttpDownloadException(url, "eTag is not equal,please delete the old one then re-download!", HttpDownloadException.TYPE_ETAG_CHANGED);
                        }
                    }
                    InputStream serverInputStream = conn.getInputStream();
                    ContentLengthInputStream inputStream2 = new ContentLengthInputStream(serverInputStream, contentLength);
                    try {
                        Log.m5156d(TAG, TAG + ".download 4、准备处理数据，获取服务器返回的资源长度为：" + contentLength + "，获取服务器返回的输入流长度为：" + inputStream2.available() + "，需要处理的区域为：" + this.mRange.toString() + "，url：" + url);
                        notifyDownloadConnected(inputStream2, this.mRange.startPos);
                        CloseConnectionTask closeConnectionTask = new CloseConnectionTask(conn, inputStream2);
                        if (this.mCloseConnectionEngine != null) {
                            this.mCloseConnectionEngine.execute(closeConnectionTask);
                        } else {
                            closeConnectionTask.run();
                        }
                        Log.m5156d(TAG, TAG + ".download 5、Http文件下载【已结束】，是否有异常：false，url：" + url);
                        return;
                    } catch (Exception e) {
                        e = e;
                        e.printStackTrace();
                        if (e instanceof HttpDownloadException) {
                            throw ((HttpDownloadException) e);
                        }
                        throw new HttpDownloadException(url, e);
                    } catch (Throwable th) {
                        th = th;
                        inputStream = inputStream2;
                        CloseConnectionTask closeConnectionTask2 = new CloseConnectionTask(conn, inputStream);
                        if (this.mCloseConnectionEngine != null) {
                            this.mCloseConnectionEngine.execute(closeConnectionTask2);
                        } else {
                            closeConnectionTask2.run();
                        }
                        Log.m5156d(TAG, TAG + ".download 5、Http文件下载【已结束】，是否有异常：true，url：" + url);
                        throw th;
                    }
                }
                throw new HttpDownloadException(url, "ResponseCode:" + responseCode + " error,can not read server data!", HttpDownloadException.TYPE_RESPONSE_CODE_ERROR);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    private boolean notifyRangeChanged(Range oldRange, Range newRange) {
        if (this.mOnRangeChangeListener != null) {
            return this.mOnRangeChangeListener.onRangeChanged(oldRange, newRange);
        }
        return true;
    }

    private void notifyDownloadConnected(ContentLengthInputStream inputStream, long startPosInTotal) {
        if (this.mOnHttpDownloadListener != null) {
            this.mOnHttpDownloadListener.onDownloadConnected(inputStream, startPosInTotal);
        }
    }

    public static class HttpDownloadException extends HttpFailReason {
        public static final String TYPE_REDIRECT_COUNT_OVER_LIMITS = HttpDownloadException.class.getName() + "_TYPE_REDIRECT_COUNT_OVER_LIMITS";
        public static final String TYPE_RESOURCES_SIZE_ILLEGAL = HttpDownloadException.class.getName() + "_TYPE_RESOURCES_SIZE_ILLEGAL";
        public static final String TYPE_ETAG_CHANGED = HttpDownloadException.class.getName() + "_TYPE_ETAG_CHANGED";
        public static final String TYPE_CONTENT_RANGE_VALIDATE_FAIL = HttpDownloadException.class.getName() + "_TYPE_CONTENT_RANGE_VALIDATE_FAIL";
        public static final String TYPE_RESPONSE_CODE_ERROR = HttpDownloadException.class.getName() + "_TYPE_RESPONSE_CODE_ERROR";

        public HttpDownloadException(String url, String detailMessage, String type) {
            super(url, detailMessage, type);
        }

        public HttpDownloadException(String url, Throwable throwable) {
            super(url, throwable);
        }

        @Override // org.wlf.filedownloader.base.FailReason
        protected void onInitTypeWithFailReason(FailReason failReason) {
            super.onInitTypeWithFailReason(failReason);
            if (failReason != null && (failReason instanceof FileSaver.FileSaveException)) {
                FileSaver.FileSaveException fileSaveException = (FileSaver.FileSaveException) failReason;
                String type = fileSaveException.getType();
                if (FileSaver.FileSaveException.TYPE_FILE_CAN_NOT_STORAGE.equals(type) || FileSaver.FileSaveException.TYPE_RENAME_TEMP_FILE_ERROR.equals(type) || FileSaver.FileSaveException.TYPE_SAVER_HAS_BEEN_STOPPED.equals(type) || FileSaver.FileSaveException.TYPE_TEMP_FILE_DOES_NOT_EXIST.equals(type)) {
                }
            }
        }
    }
}

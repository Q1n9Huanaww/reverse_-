package org.wlf.filedownloader.file_download;

import java.util.Map;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: loaded from: classes2.dex */
class FileDownloadTaskParam {
    private String mAcceptRangeType;
    private String mETag;
    private String mFilePath;
    private long mFileTotalSize;
    private Map<String, String> mHeaders;
    private String mLastModified;
    private String mRequestMethod = "GET";
    private long mStartPosInTotal;
    private String mTempFilePath;
    private String mUrl;

    public FileDownloadTaskParam(String url, long startPosInTotal, long fileTotalSize, String ETag, String lastModified, String acceptRangeType, String tempFilePath, String filePath) {
        this.mUrl = url;
        this.mStartPosInTotal = startPosInTotal;
        this.mFileTotalSize = fileTotalSize;
        this.mETag = ETag;
        this.mLastModified = lastModified;
        this.mAcceptRangeType = acceptRangeType;
        this.mTempFilePath = tempFilePath;
        this.mFilePath = filePath;
    }

    public static FileDownloadTaskParam createByDownloadFile(DownloadFileInfo downloadFileInfo, String requestMethod, Map<String, String> headers) {
        if (downloadFileInfo == null) {
            return null;
        }
        FileDownloadTaskParam fileDownloadTaskParam = new FileDownloadTaskParam(downloadFileInfo.getUrl(), downloadFileInfo.getDownloadedSizeLong(), downloadFileInfo.getFileSizeLong(), downloadFileInfo.getETag(), downloadFileInfo.getLastModified(), downloadFileInfo.getAcceptRangeType(), downloadFileInfo.getTempFilePath(), downloadFileInfo.getFilePath());
        fileDownloadTaskParam.mRequestMethod = requestMethod;
        fileDownloadTaskParam.mHeaders = headers;
        return fileDownloadTaskParam;
    }

    public void setRequestMethod(String requestMethod) {
        this.mRequestMethod = requestMethod;
    }

    public void setHeaders(Map<String, String> headers) {
        this.mHeaders = headers;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public long getStartPosInTotal() {
        return this.mStartPosInTotal;
    }

    public long getFileTotalSize() {
        return this.mFileTotalSize;
    }

    public String getETag() {
        return this.mETag;
    }

    public String getLastModified() {
        return this.mLastModified;
    }

    public String getAcceptRangeType() {
        return this.mAcceptRangeType;
    }

    public String getTempFilePath() {
        return this.mTempFilePath;
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public String getRequestMethod() {
        return this.mRequestMethod;
    }

    public Map<String, String> getHeaders() {
        return this.mHeaders;
    }
}

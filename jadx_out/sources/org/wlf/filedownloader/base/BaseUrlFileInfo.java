package org.wlf.filedownloader.base;

import java.io.File;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BaseUrlFileInfo {
    public static final String RANGE_TYPE_BYTES = "bytes";
    protected String mAcceptRangeType;
    protected String mCreateDatetime;
    protected String mETag;
    protected String mFileDir;
    protected String mFileName;
    protected long mFileSize;
    protected String mLastModified;
    protected String mUrl;

    public String getUrl() {
        return this.mUrl;
    }

    @Deprecated
    public int getFileSize() {
        return (int) this.mFileSize;
    }

    public long getFileSizeLong() {
        return this.mFileSize;
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

    public String getFileDir() {
        return this.mFileDir;
    }

    public String getFileName() {
        return this.mFileName;
    }

    public String getCreateDatetime() {
        return this.mCreateDatetime;
    }

    public String getFilePath() {
        return getFileDir() + File.separator + this.mFileName;
    }

    public String toString() {
        return "BaseUrlFileInfo{mUrl='" + this.mUrl + "', mFileSize=" + this.mFileSize + ", mETag='" + this.mETag + "', mLastModified='" + this.mLastModified + "', mAcceptRangeType='" + this.mAcceptRangeType + "', mFileDir='" + this.mFileDir + "', mFileName='" + this.mFileName + "', mCreateDatetime='" + this.mCreateDatetime + "'}";
    }
}

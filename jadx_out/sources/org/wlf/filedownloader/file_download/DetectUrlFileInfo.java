package org.wlf.filedownloader.file_download;

import android.text.TextUtils;
import java.util.Date;
import org.wlf.filedownloader.base.BaseUrlFileInfo;
import org.wlf.filedownloader.util.DateUtil;
import org.wlf.filedownloader.util.FileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DetectUrlFileInfo extends BaseUrlFileInfo {
    private DetectUrlFileInfo() {
    }

    DetectUrlFileInfo(String url, long fileSize, String eTag, String lastModified, String acceptRangeType, String fileDir, String fileName) {
        this.mUrl = url;
        this.mFileSize = fileSize;
        this.mETag = eTag;
        this.mLastModified = lastModified;
        this.mAcceptRangeType = acceptRangeType;
        this.mFileDir = fileDir;
        this.mFileName = fileName;
        this.mCreateDatetime = DateUtil.date2String_yyyy_MM_dd_HH_mm_ss(new Date());
    }

    void update(DetectUrlFileInfo detectUrlFileInfo) {
        if (UrlUtil.isUrl(detectUrlFileInfo.mUrl)) {
            this.mUrl = detectUrlFileInfo.mUrl;
        }
        if (detectUrlFileInfo.mFileSize > 0 && detectUrlFileInfo.mFileSize != this.mFileSize) {
            this.mFileSize = detectUrlFileInfo.mFileSize;
        }
        if (!TextUtils.isEmpty(detectUrlFileInfo.mETag)) {
            this.mETag = detectUrlFileInfo.mETag;
        }
        if (!TextUtils.isEmpty(detectUrlFileInfo.mLastModified)) {
            this.mLastModified = detectUrlFileInfo.mLastModified;
        }
        if (!TextUtils.isEmpty(detectUrlFileInfo.mAcceptRangeType)) {
            this.mAcceptRangeType = detectUrlFileInfo.mAcceptRangeType;
        }
        if (FileUtil.isFilePath(detectUrlFileInfo.mFileDir)) {
            this.mFileDir = detectUrlFileInfo.mFileDir;
        }
        if (!TextUtils.isEmpty(detectUrlFileInfo.mFileName)) {
            this.mFileName = detectUrlFileInfo.mFileName;
        }
        if (!TextUtils.isEmpty(detectUrlFileInfo.mCreateDatetime)) {
            this.mCreateDatetime = detectUrlFileInfo.mCreateDatetime;
        }
    }

    void setFileDir(String fileDir) {
        this.mFileDir = fileDir;
    }

    void setFileName(String fileName) {
        this.mFileName = fileName;
    }
}

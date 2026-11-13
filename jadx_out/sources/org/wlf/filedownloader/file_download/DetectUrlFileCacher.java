package org.wlf.filedownloader.file_download;

import android.text.TextUtils;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import org.wlf.filedownloader.util.DateUtil;
import org.wlf.filedownloader.util.DownloadFileUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
class DetectUrlFileCacher {
    private Map<String, DetectUrlFileInfo> mDetectUrlFileInfoMap = new HashMap();
    private Object mModifyLock = new Object();

    DetectUrlFileCacher() {
    }

    public boolean addOrUpdateDetectUrlFile(DetectUrlFileInfo detectUrlFileInfo) {
        boolean z = true;
        z = false;
        if (detectUrlFileInfo != null) {
            String url = detectUrlFileInfo.getUrl();
            if (UrlUtil.isUrl(url)) {
                DetectUrlFileInfo urlFileInfo = this.mDetectUrlFileInfoMap.get(url);
                synchronized (this.mModifyLock) {
                    try {
                        if (urlFileInfo != null) {
                            urlFileInfo.update(detectUrlFileInfo);
                        } else {
                            this.mDetectUrlFileInfoMap.put(url, detectUrlFileInfo);
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
            }
        }
        return z;
    }

    public void removeDetectUrlFile(String url) {
        synchronized (this.mModifyLock) {
            this.mDetectUrlFileInfoMap.remove(url);
        }
    }

    public DetectUrlFileInfo getDetectUrlFile(String url) {
        Date createDate;
        DetectUrlFileInfo detectUrlFileInfo = this.mDetectUrlFileInfoMap.get(url);
        if (DownloadFileUtil.isLegal(detectUrlFileInfo)) {
            String createDatetime = detectUrlFileInfo.getCreateDatetime();
            if (!TextUtils.isEmpty(detectUrlFileInfo.getCreateDatetime()) && (createDate = DateUtil.string2Date_yyyy_MM_dd_HH_mm_ss(createDatetime)) != null) {
                GregorianCalendar createDateCalendar = new GregorianCalendar();
                createDateCalendar.setTime(createDate);
                GregorianCalendar curDateCalendar = new GregorianCalendar();
                curDateCalendar.setTime(new Date());
                createDateCalendar.add(6, 1);
                if (curDateCalendar.after(createDateCalendar)) {
                    removeDetectUrlFile(detectUrlFileInfo.getUrl());
                    return null;
                }
                return detectUrlFileInfo;
            }
            return detectUrlFileInfo;
        }
        return detectUrlFileInfo;
    }

    public void release() {
        synchronized (this.mModifyLock) {
            this.mDetectUrlFileInfoMap.clear();
        }
    }
}

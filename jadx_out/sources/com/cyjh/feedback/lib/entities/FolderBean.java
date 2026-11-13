package com.cyjh.feedback.lib.entities;

import android.text.TextUtils;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
public class FolderBean {
    private int dirCount;
    private String dirName;
    private String dirPath;
    private String firstImgPath;
    private boolean isSelect;

    public String getDirPath() {
        return this.dirPath;
    }

    public void setDirPath(String str) {
        this.dirPath = str;
        if (TextUtils.isEmpty(this.dirName)) {
            this.dirName = str.substring(str.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1);
        }
    }

    public boolean isSelect() {
        return this.isSelect;
    }

    public void setIsSelect(boolean z) {
        this.isSelect = z;
    }

    public int getDirCount() {
        return this.dirCount;
    }

    public void setDirCount(int i) {
        this.dirCount = i;
    }

    public String getFirstImgPath() {
        return this.firstImgPath;
    }

    public void setFirstImgPath(String str) {
        this.firstImgPath = str;
    }

    public String getDirName() {
        return this.dirName;
    }

    public void setDirName(String str) {
        this.dirName = str;
    }

    public String toString() {
        return "FolderBean{dirPath='" + this.dirPath + "', dirName='" + this.dirName + "', firstImgPath='" + this.firstImgPath + "', dirCount=" + this.dirCount + ", isSelect=" + this.isSelect + '}';
    }
}

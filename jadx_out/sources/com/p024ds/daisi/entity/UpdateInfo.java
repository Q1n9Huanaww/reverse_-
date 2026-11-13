package com.p024ds.daisi.entity;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class UpdateInfo {
    public static final String APPID = "AppId";
    public static final String DOWNLOAD_PATH = "DownLoadPath";
    public static final String PACKAGENAME = "PackageName";
    public static final int UPDATETYPE_FORCE = 0;
    public static final int UPDATETYPE_OPTIONAL = 1;
    public static final String UPDATE_NOTICE = "UpdateNotice";
    public static final String UPDATE_TYPE = "UpdateType";
    public static final String VERSION = "Version";
    private String DownLoadPath;
    private String FileSize;
    private String UpdateNotice;
    private int UpdateType;
    public boolean isDismissing = false;
    public boolean isFloat;
    public Context mContext;

    public String getUpdateNotice() {
        return this.UpdateNotice;
    }

    public void setUpdateNotice(String str) {
        this.UpdateNotice = str;
    }

    public int getUpdateType() {
        return this.UpdateType;
    }

    public void setUpdateType(int i) {
        this.UpdateType = i;
    }

    public String getDownLoadPath() {
        return this.DownLoadPath;
    }

    public void setDownLoadPath(String str) {
        this.DownLoadPath = str;
    }

    public String getFileSize() {
        return this.FileSize;
    }

    public void setFileSize(String str) {
        this.FileSize = str;
    }
}

package com.p024ds.daisi.entity;

/* JADX INFO: loaded from: classes.dex */
public class AdEvent {
    public static final int APK_DOWNLOAD_SUCCESS = 100;
    public static final int APK_INSTALL_SUCCESS = 101;
    public static final int APK_REMOVE_SUCCESS = 102;
    String packageName;
    int type;

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public AdEvent(int i) {
        this.type = i;
    }

    public AdEvent(int i, String str) {
        this.type = i;
        this.packageName = str;
    }

    public String getPackageName() {
        return this.packageName;
    }
}

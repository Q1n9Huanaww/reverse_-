package com.p024ds.daisi.entity;

import org.litepal.crud.LitePalSupport;

/* JADX INFO: loaded from: classes.dex */
public class FeLingAdReportInfo extends LitePalSupport {
    public String adPid;
    public String apkPageName;
    public int postState;
    public int state;
    public int type;
    private String url;

    public FeLingAdReportInfo(String str, String str2, int i, int i2, int i3) {
        this.adPid = str;
        this.url = str2;
        this.type = i;
        this.state = i2;
        this.postState = i3;
    }

    public String getAdPid() {
        return this.adPid;
    }

    public void setAdPid(String str) {
        this.adPid = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public String getApkPageName() {
        return this.apkPageName;
    }

    public void setApkPageName(String str) {
        this.apkPageName = str;
    }

    public int getState() {
        return this.state;
    }

    public void setState(int i) {
        this.state = i;
    }

    public int getPostState() {
        return this.postState;
    }

    public void setPostState(int i) {
        this.postState = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }
}

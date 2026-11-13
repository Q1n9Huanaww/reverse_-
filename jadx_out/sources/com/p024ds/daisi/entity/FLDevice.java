package com.p024ds.daisi.entity;

/* JADX INFO: loaded from: classes.dex */
public class FLDevice {
    public String androidId;
    public String appVer;
    public String carrier;
    public String imei;

    /* JADX INFO: renamed from: ip */
    public String f1703ip;
    public String lan;
    public String mac;

    /* JADX INFO: renamed from: nt */
    public String f1704nt;
    public String osv;
    public String platform;
    public String rslt;
    public String sendtime;
    public String timezone;

    /* JADX INFO: renamed from: ua */
    public String f1705ua;

    public FLDevice(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14) {
        this.imei = str;
        this.androidId = str2;
        this.f1703ip = str3;
        this.f1704nt = str4;
        this.platform = str5;
        this.lan = str6;
        this.rslt = str7;
        this.osv = str8;
        this.carrier = str9;
        this.sendtime = str10;
        this.timezone = str11;
        this.mac = str12;
        this.appVer = str13;
        this.f1705ua = str14;
    }
}

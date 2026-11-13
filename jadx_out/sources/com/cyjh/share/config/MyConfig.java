package com.cyjh.share.config;

import android.net.Uri;
import android.text.TextUtils;

/* JADX INFO: loaded from: classes.dex */
public class MyConfig {
    private static String APP_2_DOMAIN_NAME = null;
    private static String APP_3_DOMAIN_NAME = "http://auth2.mobileanjian.com/";
    private static final String SCHEME = "http";

    /* JADX INFO: renamed from: c4 */
    private static String f1684c4;

    /* JADX INFO: renamed from: c5 */
    private static String f1685c5;
    private static String sAppInfo;
    private static int sAppType;
    private static String sAppVersion;
    private static long sAppid;
    private static String sAuthority;
    public static boolean sIsOpenLogger;
    private static String sTempVersionNumber;
    private static long sTemplateFileId;

    public static void setAuthority(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.startsWith("http://")) {
            str = str.substring("http://".length());
        }
        sAuthority = str;
    }

    public static Uri.Builder getBuilderPay(String str) {
        return new Uri.Builder().scheme("http").encodedAuthority(sAuthority).appendPath(str);
    }

    public static Uri.Builder getBuilderPay(String str, String str2) {
        return new Uri.Builder().scheme("http").encodedAuthority(sAuthority).appendPath(str).appendPath(str2);
    }

    public static boolean isOutputLog() {
        return sIsOpenLogger;
    }

    public static void init(boolean z, String str, long j, long j2, String str2, int i, String str3, String str4, String str5, String str6) {
        sAuthority = str;
        sIsOpenLogger = z;
        sAppid = j;
        sTemplateFileId = j2;
        sAppVersion = str2;
        sAppType = i;
        sTempVersionNumber = str3;
        f1684c4 = str4;
        f1685c5 = str5;
        sAppInfo = str6;
    }

    public static String getAppVersion() {
        return sAppVersion;
    }

    public static long getTemplateFileId() {
        return sTemplateFileId;
    }

    public static long getAppid() {
        return sAppid;
    }

    public static int getAppType() {
        return sAppType;
    }

    public static String getTempVersionNumber() {
        return sTempVersionNumber;
    }

    public static String getC4() {
        return f1684c4;
    }

    public static String getC5() {
        return f1685c5;
    }

    public static String getAppInfo() {
        return sAppInfo;
    }

    public static String getApp2DomainName() {
        return APP_2_DOMAIN_NAME;
    }

    public static void setApp2DomainName(String str) {
        APP_2_DOMAIN_NAME = str;
    }

    public static String getApp3DomainName() {
        return APP_3_DOMAIN_NAME;
    }

    public static void setApp3DomainName(String str) {
        APP_3_DOMAIN_NAME = str;
    }
}

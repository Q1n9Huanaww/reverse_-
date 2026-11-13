package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.utils.UMUtils;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.internal.a */
/* JADX INFO: compiled from: HeaderHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class C1842a {

    /* JADX INFO: renamed from: a */
    private static Context f4616a;

    /* JADX INFO: renamed from: b */
    private String f4617b;

    /* JADX INFO: renamed from: c */
    private String f4618c;

    private C1842a() {
        this.f4617b = null;
        this.f4618c = null;
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.internal.a$a */
    /* JADX INFO: compiled from: HeaderHelper.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1842a f4619a = new C1842a();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1842a m4491a(Context context) {
        if (f4616a == null && context != null) {
            f4616a = context.getApplicationContext();
        }
        return a.f4619a;
    }

    /* JADX INFO: renamed from: a */
    public boolean m4495a(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.startsWith(C1799v.f4296ah);
    }

    /* JADX INFO: renamed from: b */
    public void m4497b(String str) {
        String strSubstring = str.substring(0, str.indexOf(95));
        m4493d(strSubstring);
        m4492c(strSubstring);
    }

    /* JADX INFO: renamed from: c */
    private void m4492c(String str) {
        try {
            this.f4617b = str.replaceAll("&=", " ").replaceAll("&&", " ").replaceAll("==", InternalZipConstants.ZIP_FILE_SEPARATOR) + InternalZipConstants.ZIP_FILE_SEPARATOR + "Android" + InternalZipConstants.ZIP_FILE_SEPARATOR + Build.DISPLAY + InternalZipConstants.ZIP_FILE_SEPARATOR + Build.MODEL + InternalZipConstants.ZIP_FILE_SEPARATOR + Build.VERSION.RELEASE + " " + HelperUtils.getUmengMD5(UMUtils.getAppkey(f4616a));
        } catch (Throwable th) {
            C1782e.m3972a(f4616a, th);
        }
    }

    /* JADX INFO: renamed from: d */
    private void m4493d(String str) {
        try {
            String str2 = str.split("&&")[0];
            if (TextUtils.isEmpty(str2)) {
                return;
            }
            String[] strArrSplit = str2.split("&=");
            StringBuilder sb = new StringBuilder();
            sb.append(C1799v.f4306ar);
            for (String str3 : strArrSplit) {
                if (!TextUtils.isEmpty(str3)) {
                    String strSubstring = str3.substring(0, 2);
                    if (strSubstring.endsWith("=")) {
                        strSubstring = strSubstring.replace("=", "");
                    }
                    sb.append(strSubstring);
                }
            }
            this.f4618c = sb.toString();
        } catch (Throwable th) {
            C1782e.m3972a(f4616a, th);
        }
    }

    /* JADX INFO: renamed from: a */
    public String m4494a() {
        return this.f4618c;
    }

    /* JADX INFO: renamed from: b */
    public String m4496b() {
        return this.f4617b;
    }
}

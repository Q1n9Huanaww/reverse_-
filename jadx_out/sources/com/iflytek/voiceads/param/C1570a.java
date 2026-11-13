package com.iflytek.voiceads.param;

import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;

/* JADX INFO: renamed from: com.iflytek.voiceads.param.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1570a {

    /* JADX INFO: renamed from: a */
    private String f3367a;

    /* JADX INFO: renamed from: b */
    private C1575f f3368b = new C1575f();

    public C1570a(String str) {
        this.f3367a = str;
        m3075d();
    }

    /* JADX INFO: renamed from: d */
    private void m3075d() {
        m3080a(AdKeys.DEBUG_MODE, false);
        m3080a(AdKeys.BACK_KEY_ENABLE, true);
        m3080a(AdKeys.DOWNLOAD_ALERT, false);
        m3080a(AdKeys.HTTP_REQUEST_TIMEOUT, Integer.valueOf(BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT));
        m3080a(AdKeys.LOCK_SCREEN_AD, false);
        m3080a(AdKeys.BANNER_RECYCLE, false);
        m3080a(AdKeys.BANNER_INTERVAL, 30);
        m3080a(AdKeys.REQUEST_ID, m3076e());
        m3080a(AdKeys.CURRENCY, "CNY");
        m3088f(this.f3367a);
    }

    /* JADX INFO: renamed from: e */
    private String m3076e() {
        return "AD_" + System.currentTimeMillis();
    }

    /* JADX INFO: renamed from: a */
    public int m3077a() {
        return ((Integer) this.f3368b.m3140a("adw")).intValue();
    }

    /* JADX INFO: renamed from: a */
    public Object m3078a(String str) {
        return this.f3368b.m3140a(str);
    }

    /* JADX INFO: renamed from: a */
    public void m3079a(int i) {
        this.f3368b.m3141a("adw", Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: a */
    public void m3080a(String str, Object obj) {
        this.f3368b.m3141a(str, obj);
    }

    /* JADX INFO: renamed from: b */
    public int m3081b() {
        return ((Integer) this.f3368b.m3140a("adh")).intValue();
    }

    /* JADX INFO: renamed from: b */
    public String m3082b(String str) {
        try {
            return (String) this.f3368b.m3140a(str);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get str param :" + e.getMessage());
            return "";
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3083b(int i) {
        this.f3368b.m3141a("adh", Integer.valueOf(i));
    }

    /* JADX INFO: renamed from: c */
    public String m3084c() {
        return (String) this.f3368b.m3140a("adunit_id");
    }

    /* JADX INFO: renamed from: c */
    public boolean m3085c(String str) {
        try {
            return ((Boolean) this.f3368b.m3140a(str)).booleanValue();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get boolean param :" + e.getMessage());
            return false;
        }
    }

    /* JADX INFO: renamed from: d */
    public int m3086d(String str) {
        try {
            return ((Integer) this.f3368b.m3140a(str)).intValue();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get int param :" + e.getMessage());
            return 0;
        }
    }

    /* JADX INFO: renamed from: e */
    public double m3087e(String str) {
        try {
            if (this.f3368b.m3140a(str) != null) {
                return ((Double) this.f3368b.m3140a(str)).doubleValue();
            }
            return 0.0d;
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get double param :" + e.getMessage());
            return 0.0d;
        }
    }

    /* JADX INFO: renamed from: f */
    public void m3088f(String str) {
        this.f3368b.m3141a("adunit_id", str);
    }
}

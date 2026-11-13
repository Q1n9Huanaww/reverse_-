package com.iflytek.voiceads.request;

import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import java.security.SecureRandom;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.g */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1584g {

    /* JADX INFO: renamed from: a */
    public static HostnameVerifier f3397a = new C1586i();

    /* JADX INFO: renamed from: a */
    public static SSLContext m3163a() {
        SSLContext sSLContext;
        Exception e;
        try {
            sSLContext = SSLContext.getInstance("TLS");
            try {
                sSLContext.init(null, new TrustManager[]{new C1585h()}, new SecureRandom());
            } catch (Exception e2) {
                e = e2;
                C1593g.m3190b(SDKConstants.TAG, "getSLLContext:" + e.getMessage());
            }
        } catch (Exception e3) {
            sSLContext = null;
            e = e3;
        }
        return sSLContext;
    }
}

package com.iflytek.voiceads.download.p067b.p068a;

import android.os.Process;
import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.iflytek.voiceads.download.p067b.InterfaceC1531a;
import com.iflytek.voiceads.download.p071d.C1542a;
import com.iflytek.voiceads.download.p072e.C1545a;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.b.a.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class RunnableC1532a implements Runnable {

    /* JADX INFO: renamed from: a */
    private InterfaceC1531a f3205a;

    /* JADX INFO: renamed from: b */
    private C1542a f3206b;

    /* JADX INFO: renamed from: c */
    private a f3207c;

    /* JADX INFO: renamed from: d */
    private final int f3208d = Constants.REQUEST_CODE_APP_INSTALL;

    /* JADX INFO: renamed from: e */
    private final int f3209e = Constants.REQUEST_CODE_APP_INSTALL;

    /* JADX INFO: renamed from: com.iflytek.voiceads.download.b.a.a$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo2951a(long j, boolean z);

        /* JADX INFO: renamed from: b */
        void mo2952b();
    }

    public RunnableC1532a(InterfaceC1531a interfaceC1531a, C1542a c1542a, a aVar) {
        this.f3205a = interfaceC1531a;
        this.f3206b = c1542a;
        this.f3207c = aVar;
    }

    /* JADX INFO: renamed from: a */
    private void m2949a() throws Throwable {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.f3206b.m3011b()).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                    httpURLConnection2.setReadTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                    httpURLConnection2.setRequestMethod("GET");
                    httpURLConnection2.setRequestProperty(HttpHeaders.RANGE, "bytes=0-");
                    int responseCode = httpURLConnection2.getResponseCode();
                    if (responseCode == 200) {
                        m2950a(httpURLConnection2, false);
                    } else {
                        if (responseCode != 206) {
                            throw new C1545a(3, "UnSupported response code:" + responseCode);
                        }
                        m2950a(httpURLConnection2, true);
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (MalformedURLException e) {
                    e = e;
                    throw new C1545a(2, "Bad url.", e);
                } catch (ProtocolException e2) {
                    e = e2;
                    throw new C1545a(4, "Protocol error", e);
                } catch (IOException e3) {
                    e = e3;
                    throw new C1545a(5, "IO error", e);
                } catch (Exception e4) {
                    e = e4;
                    throw new C1545a(5, "Unknown error", e);
                } catch (Throwable th) {
                    httpURLConnection = httpURLConnection2;
                    th = th;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (MalformedURLException e5) {
            e = e5;
        } catch (ProtocolException e6) {
            e = e6;
        } catch (IOException e7) {
            e = e7;
        } catch (Exception e8) {
            e = e8;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m2950a(HttpURLConnection httpURLConnection, boolean z) throws C1545a {
        String headerField = httpURLConnection.getHeaderField("Content-Length");
        long contentLength = (TextUtils.isEmpty(headerField) || headerField.equals(AccountManager.TYPE_LOGIN) || headerField.equals(ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG)) ? httpURLConnection.getContentLength() : Long.parseLong(headerField);
        if (contentLength <= 0) {
            throw new C1545a(6, "length <= 0");
        }
        if (this.f3206b.m3023k()) {
            throw new C1545a(7);
        }
        this.f3207c.mo2951a(contentLength, z);
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Process.setThreadPriority(10);
        try {
            m2949a();
        } catch (C1545a e) {
            this.f3207c.mo2952b();
            this.f3206b.m3007a(e);
            this.f3206b.m3004a(5);
            this.f3205a.mo2948a(this.f3206b);
        }
    }
}

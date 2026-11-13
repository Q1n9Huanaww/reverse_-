package com.iflytek.voiceads.download.p067b.p069b;

import android.os.Process;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.github.kevinsawicki.http.HttpRequest;
import com.iflytek.voiceads.download.p067b.InterfaceC1531a;
import com.iflytek.voiceads.download.p071d.C1542a;
import com.iflytek.voiceads.download.p071d.C1543b;
import com.iflytek.voiceads.download.p072e.C1545a;
import com.p024ds.daisi.constant.Constants;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.b.b.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class RunnableC1534a implements Runnable {

    /* JADX INFO: renamed from: a */
    private C1543b f3212a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1531a f3213b;

    /* JADX INFO: renamed from: c */
    private C1542a f3214c;

    /* JADX INFO: renamed from: d */
    private a f3215d;

    /* JADX INFO: renamed from: e */
    private InputStream f3216e;

    /* JADX INFO: renamed from: f */
    private long f3217f;

    /* JADX INFO: renamed from: com.iflytek.voiceads.download.b.b.a$a */
    public interface a {
        /* JADX INFO: renamed from: c */
        void mo2955c();

        /* JADX INFO: renamed from: d */
        void mo2956d();

        /* JADX INFO: renamed from: e */
        void mo2957e();
    }

    public RunnableC1534a(C1543b c1543b, InterfaceC1531a interfaceC1531a, C1542a c1542a, a aVar) {
        this.f3212a = c1543b;
        this.f3213b = interfaceC1531a;
        this.f3214c = c1542a;
        this.f3217f = c1543b.m3031d();
        this.f3215d = aVar;
    }

    /* JADX INFO: renamed from: a */
    private void m2953a() throws Throwable {
        HttpURLConnection httpURLConnection = null;
        try {
            try {
                HttpURLConnection httpURLConnection2 = (HttpURLConnection) new URL(this.f3212a.m3027a()).openConnection();
                try {
                    httpURLConnection2.setConnectTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                    httpURLConnection2.setReadTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                    httpURLConnection2.setRequestMethod("GET");
                    httpURLConnection2.setRequestProperty(HttpRequest.HEADER_ACCEPT, "image/gif, image/jpeg, image/pjpeg, image/pjpeg, application/x-shockwave-flash, application/xaml+xml, application/vnd.ms-xpsdocument, application/x-ms-xbap, application/x-ms-application, application/vnd.ms-excel, application/vnd.ms-powerpoint, application/msword, */*");
                    httpURLConnection2.setRequestProperty("Accept-Language", "zh-CN");
                    httpURLConnection2.setRequestProperty("Charset", "UTF-8");
                    httpURLConnection2.setRequestProperty("Connection", "Keep-Alive");
                    long jM3029b = this.f3212a.m3029b() + this.f3217f;
                    if (this.f3214c.m3021i()) {
                        httpURLConnection2.setRequestProperty(HttpHeaders.RANGE, "bytes=" + jM3029b + "-" + this.f3212a.m3030c());
                    }
                    int responseCode = httpURLConnection2.getResponseCode();
                    if (responseCode != 206 && responseCode != 200) {
                        throw new C1545a(8, "UnSupported response code:" + responseCode);
                    }
                    m2954b();
                    this.f3216e = httpURLConnection2.getInputStream();
                    RandomAccessFile randomAccessFile = new RandomAccessFile(this.f3214c.m3015c(), "rwd");
                    randomAccessFile.seek(jM3029b);
                    byte[] bArr = new byte[4096];
                    int i = 0;
                    while (true) {
                        int i2 = this.f3216e.read(bArr);
                        if (i2 == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, i2);
                        i += i2;
                        synchronized (this.f3215d) {
                            this.f3212a.m3028a(this.f3217f + ((long) i));
                            this.f3215d.mo2955c();
                        }
                    }
                    this.f3215d.mo2956d();
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (C1545a e) {
                    e = e;
                    throw new C1545a(7, "illegal error", e);
                } catch (ProtocolException e2) {
                    e = e2;
                    throw new C1545a(4, "Protocol error", e);
                } catch (IOException e3) {
                    e = e3;
                    throw new C1545a(5, "IO error", e);
                } catch (Exception e4) {
                    e = e4;
                    throw new C1545a(9, "other error", e);
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
        } catch (C1545a e5) {
            e = e5;
        } catch (ProtocolException e6) {
            e = e6;
        } catch (IOException e7) {
            e = e7;
        } catch (Exception e8) {
            e = e8;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m2954b() {
        if (this.f3214c != null && this.f3214c.m3023k()) {
            throw new C1545a(7);
        }
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        Process.setThreadPriority(10);
        try {
            m2954b();
            m2953a();
        } catch (C1545a e) {
            this.f3215d.mo2957e();
            this.f3214c.m3007a(e);
            this.f3214c.m3004a(5);
            this.f3213b.mo2948a(this.f3214c);
        }
    }
}

package com.umeng.commonsdk.statistics.internal;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.C1811b;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.noise.ABTest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import org.apache.http.conn.ssl.X509HostnameVerifier;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.internal.c */
/* JADX INFO: compiled from: NetworkHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class C1844c {

    /* JADX INFO: renamed from: e */
    private static boolean f4620e;

    /* JADX INFO: renamed from: a */
    private String f4621a = "10.0.0.172";

    /* JADX INFO: renamed from: b */
    private int f4622b = 80;

    /* JADX INFO: renamed from: c */
    private Context f4623c;

    /* JADX INFO: renamed from: d */
    private InterfaceC1843b f4624d;

    public C1844c(Context context) {
        this.f4623c = context;
    }

    /* JADX INFO: renamed from: a */
    public void m4501a(InterfaceC1843b interfaceC1843b) {
        this.f4624d = interfaceC1843b;
    }

    /* JADX INFO: renamed from: a */
    private void m4499a() {
        String strImprintProperty = UMEnvelopeBuild.imprintProperty(this.f4623c, "domain_p", "");
        String strImprintProperty2 = UMEnvelopeBuild.imprintProperty(this.f4623c, "domain_s", "");
        if (!TextUtils.isEmpty(strImprintProperty)) {
            UMServerURL.DEFAULT_URL = DataHelper.assembleURL(strImprintProperty);
        }
        if (!TextUtils.isEmpty(strImprintProperty2)) {
            UMServerURL.SECONDARY_URL = DataHelper.assembleURL(strImprintProperty2);
        }
        C1811b.f4487e = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
        int testPolicy = ABTest.getService(this.f4623c).getTestPolicy();
        if (testPolicy != -1) {
            if (testPolicy == 0) {
                C1811b.f4487e = new String[]{UMServerURL.DEFAULT_URL, UMServerURL.SECONDARY_URL};
            } else if (testPolicy == 1) {
                C1811b.f4487e = new String[]{UMServerURL.SECONDARY_URL, UMServerURL.DEFAULT_URL};
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public byte[] m4503a(byte[] bArr, boolean z) throws Throwable {
        m4499a();
        byte[] bArrM4502a = null;
        for (int i = 0; i < C1811b.f4487e.length; i++) {
            bArrM4502a = m4502a(bArr, C1811b.f4487e[i]);
            if (bArrM4502a != null) {
                if (this.f4624d == null) {
                    break;
                }
                this.f4624d.onRequestSucceed(z);
                break;
            }
            if (this.f4624d != null) {
                this.f4624d.onRequestFailed();
            }
        }
        return bArrM4502a;
    }

    /* JADX INFO: renamed from: b */
    private boolean m4500b() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.f4623c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f4623c.getPackageName()) != 0) {
            return false;
        }
        try {
            return (!DeviceConfig.checkPermission(this.f4623c, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) this.f4623c.getSystemService("connectivity")).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) ? false : true;
        } catch (Throwable th) {
            C1782e.m3972a(this.f4623c, th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Not initialized variable reg: 3, insn: 0x0156: MOVE (r0 I:??[OBJECT, ARRAY]) = (r3 I:??[OBJECT, ARRAY]), block:B:61:0x0156 */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v11 */
    /* JADX WARN: Type inference failed for: r8v7, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX INFO: renamed from: a */
    public byte[] m4502a(byte[] bArr, String str) throws Throwable {
        OutputStream outputStream;
        OutputStream outputStream2;
        OutputStream outputStream3 = null;
        try {
            try {
                if (this.f4624d != null) {
                    this.f4624d.onRequestStart();
                }
                if (m4500b()) {
                    str = (HttpsURLConnection) new URL(str).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4621a, this.f4622b)));
                } else {
                    str = (HttpsURLConnection) new URL(str).openConnection();
                }
                try {
                    if (!f4620e) {
                        HttpsURLConnection.setDefaultHostnameVerifier(new X509HostnameVerifier() { // from class: com.umeng.commonsdk.statistics.internal.c.1
                            @Override // org.apache.http.conn.ssl.X509HostnameVerifier
                            public void verify(String str2, X509Certificate x509Certificate) throws SSLException {
                            }

                            @Override // org.apache.http.conn.ssl.X509HostnameVerifier
                            public void verify(String str2, SSLSocket sSLSocket) throws IOException {
                            }

                            @Override // org.apache.http.conn.ssl.X509HostnameVerifier
                            public void verify(String str2, String[] strArr, String[] strArr2) throws SSLException {
                            }

                            @Override // org.apache.http.conn.ssl.X509HostnameVerifier, javax.net.ssl.HostnameVerifier
                            public boolean verify(String str2, SSLSession sSLSession) {
                                return true;
                            }
                        });
                        SSLContext sSLContext = SSLContext.getInstance("TLS");
                        sSLContext.init(null, null, new SecureRandom());
                        HttpsURLConnection.setDefaultSSLSocketFactory(sSLContext.getSocketFactory());
                        f4620e = true;
                    }
                    str.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                    str.setRequestProperty("X-Umeng-Sdk", C1842a.m4491a(this.f4623c).m4496b());
                    str.setRequestProperty("Content-Type", C1842a.m4491a(this.f4623c).m4494a());
                    str.setRequestProperty("Msg-Type", "envelope/json");
                    str.setConnectTimeout(30000);
                    str.setReadTimeout(30000);
                    str.setRequestMethod("POST");
                    str.setDoOutput(true);
                    str.setDoInput(true);
                    boolean z = false;
                    str.setUseCaches(false);
                    outputStream = str.getOutputStream();
                    try {
                        outputStream.write(bArr);
                        outputStream.flush();
                        str.connect();
                        if (this.f4624d != null) {
                            this.f4624d.onRequestEnd();
                        }
                        int responseCode = str.getResponseCode();
                        String headerField = str.getHeaderField("Content-Type");
                        if (!TextUtils.isEmpty(headerField) && headerField.equalsIgnoreCase("application/thrift")) {
                            z = true;
                        }
                        if (C1811b.f4488f) {
                            MLog.m4331d("status code : " + responseCode + "; isThrifit:" + z);
                        }
                        if (responseCode != 200 || !z) {
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused) {
                                }
                            }
                            if (str != 0) {
                                str.disconnect();
                            }
                            return null;
                        }
                        MLog.m4343i("Send message to server. status code is: " + responseCode);
                        InputStream inputStream = str.getInputStream();
                        try {
                            byte[] streamToByteArray = HelperUtils.readStreamToByteArray(inputStream);
                            HelperUtils.safeClose(inputStream);
                            if (outputStream != null) {
                                try {
                                    outputStream.close();
                                } catch (Exception unused2) {
                                }
                            }
                            if (str != 0) {
                                str.disconnect();
                            }
                            return streamToByteArray;
                        } catch (Throwable th) {
                            HelperUtils.safeClose(inputStream);
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        MLog.m4339e("IOException,Failed to send message.", th);
                        C1782e.m3972a(this.f4623c, th);
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception unused3) {
                            }
                        }
                        if (str != 0) {
                            str.disconnect();
                        }
                        return null;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    outputStream = null;
                }
            } catch (Throwable th4) {
                th = th4;
                outputStream3 = outputStream2;
            }
        } catch (Throwable th5) {
            th = th5;
            str = 0;
        }
    }
}

package com.umeng.commonsdk.stateless;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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

/* JADX INFO: renamed from: com.umeng.commonsdk.stateless.e */
/* JADX INFO: compiled from: UMSLNetWorkSenderHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class C1808e {

    /* JADX INFO: renamed from: a */
    private String f4474a = "10.0.0.172";

    /* JADX INFO: renamed from: b */
    private int f4475b = 80;

    /* JADX INFO: renamed from: c */
    private Context f4476c;

    public C1808e(Context context) {
        this.f4476c = context;
    }

    /* JADX INFO: renamed from: a */
    private boolean m4306a() {
        NetworkInfo activeNetworkInfo;
        String extraInfo;
        if (this.f4476c == null || this.f4476c.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.f4476c.getPackageName()) != 0) {
            return false;
        }
        try {
            return (!DeviceConfig.checkPermission(this.f4476c, "android.permission.ACCESS_NETWORK_STATE") || (activeNetworkInfo = ((ConnectivityManager) this.f4476c.getSystemService("connectivity")).getActiveNetworkInfo()) == null || activeNetworkInfo.getType() == 1 || (extraInfo = activeNetworkInfo.getExtraInfo()) == null || (!extraInfo.equals("cmwap") && !extraInfo.equals("3gwap") && !extraInfo.equals("uniwap"))) ? false : true;
        } catch (Throwable th) {
            C1782e.m3972a(this.f4476c, th);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v12, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r10v16 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4 */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX INFO: renamed from: a */
    public boolean m4307a(byte[] bArr, String str) throws Throwable {
        boolean z = false;
        if (bArr == null || str == 0) {
            C1817e.m4397a("walle", "[stateless] sendMessage, envelopeByte == null || path == null ");
            return false;
        }
        ?? r2 = 0;
        r2 = 0;
        r2 = 0;
        r2 = 0;
        try {
            try {
                if (m4306a()) {
                    str = (HttpsURLConnection) new URL("https://plbslog.umeng.com/" + ((String) str)).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4474a, this.f4475b)));
                } else {
                    str = (HttpsURLConnection) new URL("https://plbslog.umeng.com/" + ((String) str)).openConnection();
                }
                try {
                    HttpsURLConnection.setDefaultHostnameVerifier(new X509HostnameVerifier() { // from class: com.umeng.commonsdk.stateless.e.1
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
                    str.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                    str.setRequestProperty("Msg-Type", "envelope/json");
                    str.setConnectTimeout(30000);
                    str.setReadTimeout(30000);
                    str.setRequestMethod("POST");
                    str.setDoOutput(true);
                    str.setDoInput(true);
                    str.setUseCaches(false);
                    OutputStream outputStream = str.getOutputStream();
                    try {
                        outputStream.write(bArr);
                        outputStream.flush();
                        str.connect();
                        r2 = 200;
                        r2 = 200;
                        z = str.getResponseCode() == 200;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        if (str != 0) {
                            str.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r2 = outputStream;
                        C1782e.m3972a(this.f4476c, th);
                        if (r2 != 0) {
                            try {
                                r2.close();
                            } catch (Exception unused2) {
                            }
                        }
                        if (str != 0) {
                        }
                        return z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            str = 0;
        }
        return z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v3, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v5 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v11, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4 */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r9v6 */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.net.HttpURLConnection] */
    /* JADX INFO: renamed from: b */
    public boolean m4308b(byte[] bArr, String str) throws Throwable {
        boolean z = false;
        if (bArr == null || str == 0) {
            return false;
        }
        ?? r1 = 0;
        r1 = 0;
        r1 = 0;
        r1 = 0;
        try {
            try {
                if (m4306a()) {
                    str = (HttpURLConnection) new URL("https://plbslog.umeng.com/" + ((String) str)).openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.f4474a, this.f4475b)));
                } else {
                    str = (HttpURLConnection) new URL("https://plbslog.umeng.com/" + ((String) str)).openConnection();
                }
                try {
                    str.setRequestProperty("X-Umeng-UTC", String.valueOf(System.currentTimeMillis()));
                    str.setRequestProperty("Msg-Type", "envelope/json");
                    str.setConnectTimeout(30000);
                    str.setReadTimeout(30000);
                    str.setRequestMethod("POST");
                    str.setDoOutput(true);
                    str.setDoInput(true);
                    str.setUseCaches(false);
                    OutputStream outputStream = str.getOutputStream();
                    try {
                        outputStream.write(bArr);
                        outputStream.flush();
                        str.connect();
                        r1 = 200;
                        r1 = 200;
                        z = str.getResponseCode() == 200;
                        if (outputStream != null) {
                            try {
                                outputStream.close();
                            } catch (Exception unused) {
                            }
                        }
                        if (str != 0) {
                            str.disconnect();
                        }
                    } catch (Throwable th) {
                        th = th;
                        r1 = outputStream;
                        C1782e.m3972a(this.f4476c, th);
                        if (r1 != 0) {
                            try {
                                r1.close();
                            } catch (Exception unused2) {
                            }
                        }
                        if (str != 0) {
                        }
                        return z;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            str = 0;
        }
        return z;
    }
}

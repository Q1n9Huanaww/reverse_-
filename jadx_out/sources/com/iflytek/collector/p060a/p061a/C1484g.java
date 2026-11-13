package com.iflytek.collector.p060a.p061a;

import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.config.SDKLogger;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.g */
/* JADX INFO: loaded from: classes.dex */
public class C1484g extends Thread {

    /* JADX INFO: renamed from: a */
    private int f3038a = 20000;

    /* JADX INFO: renamed from: b */
    private a f3039b = null;

    /* JADX INFO: renamed from: c */
    private volatile boolean f3040c = false;

    /* JADX INFO: renamed from: d */
    private URL f3041d = null;

    /* JADX INFO: renamed from: e */
    private ArrayList<byte[]> f3042e = new ArrayList<>();

    /* JADX INFO: renamed from: f */
    private Object f3043f = null;

    /* JADX INFO: renamed from: g */
    private int f3044g = 0;

    /* JADX INFO: renamed from: com.iflytek.collector.a.a.g$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo2808a(C1484g c1484g, byte[] bArr);

        /* JADX INFO: renamed from: a */
        void mo2809a(Exception exc);
    }

    /* JADX INFO: renamed from: a */
    public static URL m2813a(String str, String str2) throws MalformedURLException {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!str.endsWith("?")) {
                str = str + "?";
            }
            str = str + str2;
        }
        SDKLogger.m2900d("Collector", "url:" + str);
        return new URL(str);
    }

    /* JADX INFO: renamed from: a */
    private void m2814a(Exception exc) {
        if (this.f3039b == null || this.f3040c) {
            return;
        }
        this.f3039b.mo2809a(exc);
    }

    /* JADX INFO: renamed from: a */
    public static boolean m2815a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return jSONObject.has("rsp") && jSONObject.getJSONObject("rsp").getInt("code") != 0;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private byte[] m2816a(InputStream inputStream) throws IOException {
        int i;
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        byte[] bArr = new byte[1024];
        while (!this.f3040c && (i = bufferedInputStream.read(bArr, 0, 1024)) != -1) {
            byteArrayOutputStream.write(bArr, 0, i);
        }
        return byteArrayOutputStream.toByteArray();
    }

    /* JADX WARN: Code duplicated, block: B:35:0x007b A[Catch: Exception -> 0x007e, TRY_LEAVE, TryCatch #0 {Exception -> 0x007e, blocks: (B:33:0x0076, B:35:0x007b), top: B:39:0x0076 }] */
    /* JADX INFO: renamed from: b */
    private void m2817b() throws Throwable {
        Throwable th;
        HttpURLConnection httpURLConnection;
        Exception e;
        InputStream inputStream = null;
        try {
            try {
                try {
                    httpURLConnection = (HttpURLConnection) this.f3041d.openConnection();
                    try {
                        httpURLConnection.setConnectTimeout(this.f3038a);
                        httpURLConnection.setReadTimeout(this.f3038a);
                        httpURLConnection.setRequestMethod("GET");
                        int responseCode = httpURLConnection.getResponseCode();
                        if (200 == responseCode) {
                            InputStream inputStream2 = httpURLConnection.getInputStream();
                            try {
                                m2818b(m2816a(inputStream2));
                                inputStream = inputStream2;
                            } catch (Exception e2) {
                                e = e2;
                                inputStream = inputStream2;
                                ThrowableExtension.printStackTrace(e);
                                m2814a(e);
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (httpURLConnection == null) {
                                    return;
                                }
                            } catch (Throwable th2) {
                                th = th2;
                                inputStream = inputStream2;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                    } catch (Exception unused) {
                                        throw th;
                                    }
                                } else if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th;
                            }
                        } else {
                            m2814a(new Exception("HttpRequest Failed: " + responseCode));
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection == null) {
                            return;
                        }
                    } catch (Exception e3) {
                        e = e3;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception e4) {
                e = e4;
                httpURLConnection = null;
            } catch (Throwable th4) {
                th = th4;
                httpURLConnection = null;
            }
            httpURLConnection.disconnect();
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m2818b(byte[] bArr) {
        if (this.f3039b == null || this.f3040c) {
            return;
        }
        this.f3039b.mo2808a(this, bArr);
    }

    /* JADX INFO: renamed from: c */
    private int m2819c() {
        int length = 0;
        for (int i = 0; i < this.f3042e.size(); i++) {
            length += this.f3042e.get(i).length;
        }
        return length;
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00d0 A[Catch: Exception -> 0x00d3, TRY_LEAVE, TryCatch #7 {Exception -> 0x00d3, blocks: (B:38:0x00cb, B:40:0x00d0), top: B:48:0x00cb }] */
    /* JADX INFO: renamed from: a */
    public void m2820a() throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream = null;
        try {
            try {
                try {
                    httpURLConnection = (HttpURLConnection) this.f3041d.openConnection();
                    try {
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setDoInput(true);
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setConnectTimeout(this.f3038a);
                        httpURLConnection.setReadTimeout(this.f3038a);
                        httpURLConnection.setRequestProperty("Content-length", "" + m2819c());
                        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                        httpURLConnection.setRequestProperty("Charset", "utf-8");
                        httpURLConnection.setRequestProperty("Content-Type", "application/x-gzip");
                        OutputStream outputStream = httpURLConnection.getOutputStream();
                        Iterator<byte[]> it2 = this.f3042e.iterator();
                        while (it2.hasNext()) {
                            outputStream.write(it2.next());
                        }
                        outputStream.flush();
                        outputStream.close();
                        int responseCode = httpURLConnection.getResponseCode();
                        if (200 == responseCode) {
                            InputStream inputStream2 = httpURLConnection.getInputStream();
                            try {
                                m2818b(m2816a(inputStream2));
                                inputStream = inputStream2;
                            } catch (Exception e) {
                                e = e;
                                inputStream = inputStream2;
                                m2814a(e);
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (httpURLConnection == null) {
                                    return;
                                }
                            } catch (Throwable th) {
                                th = th;
                                inputStream = inputStream2;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                    } catch (Exception unused) {
                                        throw th;
                                    }
                                } else if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th;
                            }
                        } else {
                            m2814a(new Exception("Http Request Failed: " + responseCode));
                        }
                        if (inputStream != null) {
                            inputStream.close();
                        }
                        if (httpURLConnection == null) {
                            return;
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e3) {
                e = e3;
                httpURLConnection = null;
            } catch (Throwable th3) {
                th = th3;
                httpURLConnection = null;
            }
            httpURLConnection.disconnect();
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2821a(int i) {
        this.f3044g = i;
    }

    /* JADX INFO: renamed from: a */
    public void m2822a(a aVar) {
        this.f3039b = aVar;
    }

    /* JADX INFO: renamed from: a */
    public void m2823a(String str, String str2, byte[] bArr) {
        this.f3042e.clear();
        m2824a(bArr);
        try {
            this.f3041d = m2813a(str, str2);
        } catch (MalformedURLException e) {
            SDKLogger.m2902e("Collector", "url error:" + e);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2824a(byte[] bArr) {
        if (bArr != null) {
            this.f3042e.add(bArr);
        }
    }

    /* JADX INFO: renamed from: b */
    public void m2825b(int i) {
        this.f3038a = i;
    }

    /* JADX INFO: renamed from: b */
    public void m2826b(a aVar) {
        this.f3039b = aVar;
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        if (this.f3044g == 1) {
            m2820a();
        } else {
            m2817b();
        }
    }
}

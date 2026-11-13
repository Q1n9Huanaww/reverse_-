package com.iflytek.voiceads.request;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.param.C1573d;
import com.iflytek.voiceads.utils.C1589c;
import com.iflytek.voiceads.utils.C1593g;
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
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1578a extends Thread {

    /* JADX INFO: renamed from: a */
    private URL f3385a;

    /* JADX INFO: renamed from: b */
    private Context f3386b;

    /* JADX INFO: renamed from: d */
    private int f3388d;

    /* JADX INFO: renamed from: e */
    private int f3389e;

    /* JADX INFO: renamed from: f */
    private long f3390f;

    /* JADX INFO: renamed from: c */
    private int f3387c = 0;

    /* JADX INFO: renamed from: g */
    private boolean f3391g = true;

    /* JADX INFO: renamed from: h */
    private ArrayList<byte[]> f3392h = new ArrayList<>();

    /* JADX INFO: renamed from: i */
    private a f3393i = null;

    /* JADX INFO: renamed from: com.iflytek.voiceads.request.a$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo2892a(Exception exc, int i);

        /* JADX INFO: renamed from: a */
        void mo2893a(byte[] bArr);
    }

    /* JADX INFO: renamed from: a */
    public static URL m3146a(String str, String str2) throws MalformedURLException {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (!str.endsWith("?")) {
                str = str + "?";
            }
            str = str + str2;
        }
        if (str.contains(" ")) {
            str = str.replaceAll(" ", "%20");
        }
        return new URL(str);
    }

    /* JADX WARN: Code duplicated, block: B:31:0x00c3 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:32:0x00c5 A[Catch: Exception -> 0x00c9, TRY_LEAVE, TryCatch #3 {Exception -> 0x00c9, blocks: (B:30:0x00c0, B:32:0x00c5), top: B:49:0x00c0 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00c0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    private void m3147a() throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        InputStream inputStream2 = null;
        InputStream inputStream3 = null;
        inputStream2 = null;
        HttpURLConnection httpURLConnection2 = null;
        try {
            HttpURLConnection httpURLConnection3 = (HttpURLConnection) this.f3385a.openConnection();
            try {
                try {
                    httpURLConnection3.setRequestMethod("GET");
                    m3149a(httpURLConnection3);
                    httpURLConnection3.setConnectTimeout(this.f3389e);
                    httpURLConnection3.setReadTimeout(this.f3389e);
                    httpURLConnection3.setRequestProperty("User-Agent", C1573d.m3093b(null));
                    int responseCode = httpURLConnection3.getResponseCode();
                    if (200 == responseCode) {
                        inputStream3 = httpURLConnection3.getInputStream();
                        try {
                            m3153b(m3151a(inputStream3));
                        } catch (Exception e) {
                            inputStream = inputStream3;
                            httpURLConnection2 = httpURLConnection3;
                            e = e;
                            try {
                                C1593g.m3190b(SDKConstants.TAG, "runGet error!\n" + e.toString());
                                m3148a(e, ErrorCode.ERROR_NETWORK);
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e2) {
                                        C1593g.m3190b(SDKConstants.TAG, "in close1:" + e2.getMessage());
                                        return;
                                    }
                                }
                                if (httpURLConnection2 != null) {
                                    httpURLConnection2.disconnect();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                                InputStream inputStream4 = inputStream;
                                httpURLConnection = httpURLConnection2;
                                inputStream2 = inputStream4;
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                    } catch (Exception e3) {
                                        C1593g.m3190b(SDKConstants.TAG, "in close1:" + e3.getMessage());
                                        throw th;
                                    }
                                } else if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th;
                            }
                        }
                    } else {
                        m3148a(new Exception("Http Request Failed!"), responseCode);
                    }
                    if (inputStream3 != null) {
                        try {
                            inputStream3.close();
                        } catch (Exception e4) {
                            C1593g.m3190b(SDKConstants.TAG, "in close1:" + e4.getMessage());
                            return;
                        }
                    }
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                } catch (Exception e5) {
                    inputStream = null;
                    httpURLConnection2 = httpURLConnection3;
                    e = e5;
                }
            } catch (Throwable th2) {
                httpURLConnection = httpURLConnection3;
                th = th2;
                if (inputStream2 != null) {
                    inputStream2.close();
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                } else if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
        } catch (Throwable th3) {
            th = th3;
            httpURLConnection = null;
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3148a(Exception exc, int i) {
        if (this.f3393i != null) {
            this.f3393i.mo2892a(exc, i);
        }
        if (this.f3391g) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f3390f;
            switch (this.f3388d) {
                case 0:
                    C1589c.m3174a(this.f3386b, "reqDuration", jCurrentTimeMillis);
                    C1589c.m3174a(this.f3386b, "reqFailCnt", C1589c.m3172a(this.f3386b, "reqFailCnt") + 1);
                    break;
                case 1:
                    C1589c.m3174a(this.f3386b, "impDuration", jCurrentTimeMillis);
                    C1589c.m3174a(this.f3386b, "impFailCnt", C1589c.m3172a(this.f3386b, "impFailCnt") + 1);
                    break;
                case 2:
                    C1589c.m3174a(this.f3386b, "clkDuration", jCurrentTimeMillis);
                    C1589c.m3174a(this.f3386b, "clkFailCnt", C1589c.m3172a(this.f3386b, "clkFailCnt") + 1);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3149a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection instanceof HttpsURLConnection) {
            SSLContext sSLContextM3163a = C1584g.m3163a();
            if (sSLContextM3163a != null) {
                ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(sSLContextM3163a.getSocketFactory());
            }
            if (C1584g.f3397a != null) {
                ((HttpsURLConnection) httpURLConnection).setHostnameVerifier(C1584g.f3397a);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3150a(byte[] bArr) {
        if (bArr != null) {
            this.f3392h.add(bArr);
        }
    }

    /* JADX INFO: renamed from: a */
    private byte[] m3151a(InputStream inputStream) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = bufferedInputStream.read(bArr, 0, bArr.length);
            if (i == -1) {
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0108 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x010a A[Catch: Exception -> 0x0132, TryCatch #11 {Exception -> 0x0132, blocks: (B:39:0x0105, B:41:0x010a, B:43:0x010f), top: B:69:0x0105 }] */
    /* JADX WARN: Code duplicated, block: B:43:0x010f A[Catch: Exception -> 0x0132, TRY_LEAVE, TryCatch #11 {Exception -> 0x0132, blocks: (B:39:0x0105, B:41:0x010a, B:43:0x010f), top: B:69:0x0105 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x0105 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: b */
    private void m3152b() throws Throwable {
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        OutputStream outputStream = null;
        InputStream inputStream2 = null;
        outputStream = null;
        outputStream = null;
        outputStream = null;
        try {
            HttpURLConnection httpURLConnection2 = (HttpURLConnection) this.f3385a.openConnection();
            try {
                httpURLConnection2.setDoOutput(true);
                httpURLConnection2.setDoInput(true);
                httpURLConnection2.setUseCaches(false);
                httpURLConnection2.setRequestMethod("POST");
                m3149a(httpURLConnection2);
                httpURLConnection2.setConnectTimeout(this.f3389e);
                httpURLConnection2.setReadTimeout(this.f3389e);
                httpURLConnection2.setRequestProperty("Charset", "utf-8");
                httpURLConnection2.setRequestProperty("Content-Type", "application/json");
                httpURLConnection2.setRequestProperty("User-Agent", C1573d.m3093b(null));
                OutputStream outputStream2 = httpURLConnection2.getOutputStream();
                try {
                    Iterator<byte[]> it2 = this.f3392h.iterator();
                    while (it2.hasNext()) {
                        outputStream2.write(it2.next());
                    }
                    outputStream2.flush();
                    outputStream2.close();
                    int responseCode = httpURLConnection2.getResponseCode();
                    C1593g.m3188a(SDKConstants.TAG, "HttpRequest response code: " + responseCode);
                    if (200 == responseCode) {
                        inputStream2 = httpURLConnection2.getInputStream();
                        try {
                            m3153b(m3151a(inputStream2));
                        } catch (Exception e) {
                            httpURLConnection = httpURLConnection2;
                            e = e;
                            inputStream = inputStream2;
                            outputStream = outputStream2;
                            try {
                                C1593g.m3190b(SDKConstants.TAG, "runPost error!\n" + e.toString());
                                m3148a(e, ErrorCode.ERROR_NETWORK);
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                    } catch (Exception e2) {
                                        C1593g.m3190b(SDKConstants.TAG, "in close2:" + e2.getMessage());
                                        return;
                                    }
                                }
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                    return;
                                }
                                return;
                            } catch (Throwable th) {
                                th = th;
                                if (inputStream != null) {
                                    try {
                                        inputStream.close();
                                        if (outputStream != null) {
                                            outputStream.close();
                                        }
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                    } catch (Exception e3) {
                                        C1593g.m3190b(SDKConstants.TAG, "in close2:" + e3.getMessage());
                                        throw th;
                                    }
                                } else {
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            httpURLConnection = httpURLConnection2;
                            th = th2;
                            inputStream = inputStream2;
                            outputStream = outputStream2;
                            if (inputStream != null) {
                                inputStream.close();
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            } else {
                                if (outputStream != null) {
                                    outputStream.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                            }
                            throw th;
                        }
                    } else {
                        m3148a(new Exception("Http Request Failed."), responseCode);
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (Exception e4) {
                            C1593g.m3190b(SDKConstants.TAG, "in close2:" + e4.getMessage());
                            return;
                        }
                    }
                    if (outputStream2 != null) {
                        outputStream2.close();
                    }
                    if (httpURLConnection2 != null) {
                        httpURLConnection2.disconnect();
                    }
                } catch (Exception e5) {
                    httpURLConnection = httpURLConnection2;
                    e = e5;
                    inputStream = null;
                    outputStream = outputStream2;
                } catch (Throwable th3) {
                    httpURLConnection = httpURLConnection2;
                    th = th3;
                    inputStream = null;
                    outputStream = outputStream2;
                }
            } catch (Exception e6) {
                inputStream = null;
                e = e6;
                httpURLConnection = httpURLConnection2;
            } catch (Throwable th4) {
                inputStream = null;
                th = th4;
                httpURLConnection = httpURLConnection2;
            }
        } catch (Exception e7) {
            e = e7;
            httpURLConnection = null;
            inputStream = null;
        } catch (Throwable th5) {
            th = th5;
            httpURLConnection = null;
            inputStream = null;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m3153b(byte[] bArr) {
        if (this.f3393i != null) {
            this.f3393i.mo2893a(bArr);
        }
        if (this.f3391g) {
            long jCurrentTimeMillis = System.currentTimeMillis() - this.f3390f;
            switch (this.f3388d) {
                case 0:
                    C1589c.m3174a(this.f3386b, "reqDuration", jCurrentTimeMillis);
                    C1589c.m3174a(this.f3386b, "reqFailCnt", 0L);
                    break;
                case 1:
                    C1589c.m3174a(this.f3386b, "impDuration", jCurrentTimeMillis);
                    C1589c.m3174a(this.f3386b, "impFailCnt", 0L);
                    break;
                case 2:
                    C1589c.m3174a(this.f3386b, "clkDuration", jCurrentTimeMillis);
                    C1589c.m3174a(this.f3386b, "clkFailCnt", 0L);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3154a(int i) {
        this.f3388d = i;
    }

    /* JADX INFO: renamed from: a */
    public void m3155a(Context context) {
        this.f3386b = context.getApplicationContext();
    }

    /* JADX INFO: renamed from: a */
    public void m3156a(a aVar) {
        if (this.f3391g) {
            this.f3390f = System.currentTimeMillis();
        }
        this.f3393i = aVar;
        start();
    }

    /* JADX INFO: renamed from: a */
    public void m3157a(String str, String str2, byte[] bArr) {
        this.f3392h.clear();
        m3150a(bArr);
        try {
            this.f3385a = m3146a(str, str2);
        } catch (MalformedURLException e) {
            C1593g.m3190b(SDKConstants.TAG, "url error:" + e);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3158a(boolean z) {
        this.f3391g = z;
    }

    /* JADX INFO: renamed from: b */
    public void m3159b(int i) {
        this.f3387c = i;
    }

    /* JADX INFO: renamed from: c */
    public void m3160c(int i) {
        this.f3389e = i;
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        if (this.f3387c == 1) {
            m3152b();
        } else {
            m3147a();
        }
    }
}

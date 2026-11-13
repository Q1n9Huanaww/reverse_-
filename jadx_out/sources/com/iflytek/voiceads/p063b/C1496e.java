package com.iflytek.voiceads.p063b;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Looper;
import android.text.TextUtils;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;

/* JADX INFO: renamed from: com.iflytek.voiceads.b.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1496e extends Thread {

    /* JADX INFO: renamed from: a */
    private String f3067a;

    /* JADX INFO: renamed from: b */
    private b f3068b;

    /* JADX INFO: renamed from: com.iflytek.voiceads.b.e$a */
    static class a extends FilterInputStream {
        public a(InputStream inputStream) {
            super(inputStream);
        }

        @Override // java.io.FilterInputStream, java.io.InputStream
        public long skip(long j) throws IOException {
            long j2 = 0;
            while (j2 < j) {
                long jSkip = this.in.skip(j - j2);
                if (jSkip == 0) {
                    if (read() < 0) {
                        break;
                    }
                    jSkip = 1;
                }
                j2 = jSkip + j2;
            }
            return j2;
        }
    }

    /* JADX INFO: renamed from: com.iflytek.voiceads.b.e$b */
    public interface b {
        /* JADX INFO: renamed from: a */
        void mo2840a(Bitmap bitmap);

        /* JADX INFO: renamed from: a */
        void mo2841a(Exception exc);
    }

    public C1496e(String str, b bVar) {
        this.f3067a = str;
        this.f3068b = bVar;
    }

    /* JADX INFO: Removed unreachable split cross block B:45:0x0008 */
    /* JADX WARN: Code duplicated, block: B:32:0x00a1  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v23 */
    /* JADX WARN: Type inference failed for: r0v24 */
    /* JADX WARN: Type inference failed for: r0v25 */
    /* JADX WARN: Type inference failed for: r0v26 */
    /* JADX WARN: Type inference failed for: r0v27 */
    /* JADX WARN: Type inference failed for: r0v28 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.net.HttpURLConnection] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX INFO: renamed from: b */
    private void m2847b() throws Throwable {
        HttpURLConnection httpURLConnection;
        ?? IsEmpty = TextUtils.isEmpty(this.f3067a);
        if (IsEmpty != 0) {
            return;
        }
        ?? r1 = 0;
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(this.f3067a).openConnection();
                try {
                    httpURLConnection.setRequestMethod("GET");
                    httpURLConnection.setConnectTimeout(BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT);
                    httpURLConnection.setReadTimeout(BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT);
                    if (200 != httpURLConnection.getResponseCode()) {
                        this.f3068b.mo2841a(new Exception("network error!"));
                        if (httpURLConnection != null) {
                            IsEmpty = httpURLConnection;
                            httpURLConnection.disconnect();
                            IsEmpty = httpURLConnection;
                        }
                    } else {
                        InputStream inputStream = httpURLConnection.getInputStream();
                        a aVar = new a(inputStream);
                        Looper.prepare();
                        Looper.getMainLooper();
                        this.f3068b.mo2840a(BitmapFactory.decodeStream(aVar));
                        inputStream.close();
                        if (httpURLConnection != null) {
                            IsEmpty = httpURLConnection;
                            httpURLConnection.disconnect();
                            IsEmpty = httpURLConnection;
                        }
                    }
                } catch (IOException e) {
                    this.f3068b.mo2841a(new Exception("I/O error!"));
                    IsEmpty = httpURLConnection;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        IsEmpty = httpURLConnection;
                    }
                } catch (IllegalStateException e2) {
                    this.f3068b.mo2841a(new Exception("Incorrect URL!"));
                    IsEmpty = httpURLConnection;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        IsEmpty = httpURLConnection;
                    }
                } catch (Exception e3) {
                    this.f3068b.mo2841a(new Exception("network error!"));
                    IsEmpty = httpURLConnection;
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        IsEmpty = httpURLConnection;
                    }
                }
            } catch (Throwable th) {
                r1 = IsEmpty;
                th = th;
                if (r1 != 0) {
                    r1.disconnect();
                }
                throw th;
            }
        } catch (IOException e4) {
            httpURLConnection = null;
        } catch (IllegalStateException e5) {
            httpURLConnection = null;
        } catch (Exception e6) {
            httpURLConnection = null;
        } catch (Throwable th2) {
            th = th2;
            if (r1 != 0) {
                r1.disconnect();
            }
            throw th;
        }
        IsEmpty = httpURLConnection;
        IsEmpty = httpURLConnection;
    }

    /* JADX INFO: renamed from: a */
    public void m2848a() {
        start();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        m2847b();
    }
}

package com.iflytek.voiceads.dex;

import com.iflytek.voiceads.config.SDKLogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: renamed from: com.iflytek.voiceads.dex.d */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC1524d implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f3185a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f3186b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ String f3187c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ String f3188d;

    RunnableC1524d(String str, String str2, String str3, String str4) {
        this.f3185a = str;
        this.f3186b = str2;
        this.f3187c = str3;
        this.f3188d = str4;
    }

    @Override // java.lang.Runnable
    public void run() {
        InputStream inputStream;
        SDKLogger.m2899d("checking update");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f3185a).openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty("Charset", "utf-8");
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i <= -1) {
                        break;
                    } else {
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                }
                C1522b c1522b = new C1522b(new String(byteArrayOutputStream.toByteArray()));
                if (c1522b.m2918b() != null && c1522b.m2917a() != null) {
                    if (C1523c.m2925b(c1522b.m2918b(), this.f3186b) > C1523c.m2925b(this.f3187c, this.f3186b)) {
                        SDKLogger.m2899d("find new version:" + c1522b.m2918b());
                        C1523c.m2927b(this.f3188d, c1522b.m2918b(), c1522b.m2917a());
                    } else {
                        SDKLogger.m2899d("no new version dex");
                    }
                }
                SDKLogger.m2899d("no version");
                return;
            }
            inputStream = null;
            if (inputStream != null) {
                inputStream.close();
            }
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
        } catch (IOException e) {
            SDKLogger.m2901e("checkDexUpdate thread" + e.getMessage());
        }
    }
}

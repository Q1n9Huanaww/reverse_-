package com.iflytek.voiceads.dex;

import com.iflytek.voiceads.config.SDKLogger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/* JADX INFO: renamed from: com.iflytek.voiceads.dex.e */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC1525e implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ String f3189a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f3190b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ String f3191c;

    RunnableC1525e(String str, String str2, String str3) {
        this.f3189a = str;
        this.f3190b = str2;
        this.f3191c = str3;
    }

    @Override // java.lang.Runnable
    public void run() {
        FileOutputStream fileOutputStream;
        SDKLogger.m2899d("downloading new version:" + this.f3189a);
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(this.f3190b).openConnection();
            httpURLConnection.setReadTimeout(5000);
            httpURLConnection.setConnectTimeout(5000);
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = null;
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                fileOutputStream = new FileOutputStream(this.f3191c + this.f3189a);
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = inputStream.read(bArr);
                    if (i <= -1) {
                        break;
                    } else {
                        fileOutputStream.write(bArr, 0, i);
                    }
                }
                fileOutputStream.flush();
                SDKLogger.m2899d("download " + this.f3189a + " completed, it will be loaded next time");
            } else {
                fileOutputStream = null;
            }
            if (inputStream != null) {
                inputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
        } catch (IOException e) {
            SDKLogger.m2901e("downloadRemote thread:" + e.getMessage());
        }
    }
}

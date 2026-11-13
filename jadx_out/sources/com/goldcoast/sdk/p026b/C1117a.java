package com.goldcoast.sdk.p026b;

import android.content.Context;
import com.goldcoast.sdk.p027c.C1123e;
import com.goldcoast.sdk.p027c.C1125g;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.mail.EmailConstants;

/* JADX INFO: renamed from: com.goldcoast.sdk.b.a */
/* JADX INFO: compiled from: NetWork.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1117a {
    /* JADX INFO: renamed from: a */
    public static String m1402a(String str, Context context, String str2) throws IOException {
        String str3 = context.getFilesDir().getPath() + File.separator + "ota" + File.separator + "elf" + File.separator + str2;
        C1125g.m1428a();
        C1125g.m1430a(str);
        C1125g.m1428a();
        C1125g.m1430a("download filename=" + str2);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setConnectTimeout(EmailConstants.SOCKET_TIMEOUT_MS);
        httpURLConnection.setReadTimeout(EmailConstants.SOCKET_TIMEOUT_MS);
        httpURLConnection.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36");
        System.setProperty("http.keepAlive", "false");
        httpURLConnection.connect();
        if (httpURLConnection.getResponseCode() == 200) {
            FileOutputStream fileOutputStream = new FileOutputStream(str3);
            InputStream inputStream = httpURLConnection.getInputStream();
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    break;
                }
                fileOutputStream.write(bArr, 0, i);
            }
            fileOutputStream.close();
            C1123e.m1422a(str3, 493);
            httpURLConnection.disconnect();
        }
        return str3;
    }
}

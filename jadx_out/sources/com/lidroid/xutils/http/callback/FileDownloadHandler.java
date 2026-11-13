package com.lidroid.xutils.http.callback;

import android.text.TextUtils;
import com.lidroid.xutils.util.IOUtils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.http.HttpEntity;

/* JADX INFO: loaded from: classes.dex */
public class FileDownloadHandler {
    public File handleEntity(HttpEntity httpEntity, RequestCallBackHandler requestCallBackHandler, String str, boolean z, String str2) throws Throwable {
        Throwable th;
        BufferedInputStream bufferedInputStream;
        FileOutputStream fileOutputStream;
        BufferedOutputStream bufferedOutputStream = null;
        if (httpEntity == null || TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (parentFile.exists() || parentFile.mkdirs()) {
                file.createNewFile();
            }
        }
        long length = 0;
        try {
            if (z) {
                length = file.length();
                fileOutputStream = new FileOutputStream(str, true);
            } else {
                fileOutputStream = new FileOutputStream(str);
            }
            long j = length;
            long contentLength = httpEntity.getContentLength() + j;
            bufferedInputStream = new BufferedInputStream(httpEntity.getContent());
            try {
                BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                if (requestCallBackHandler != null) {
                    try {
                        if (!requestCallBackHandler.updateProgress(contentLength, j, true)) {
                            IOUtils.closeQuietly(bufferedInputStream);
                            IOUtils.closeQuietly(bufferedOutputStream2);
                            return file;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedOutputStream = bufferedOutputStream2;
                        IOUtils.closeQuietly(bufferedInputStream);
                        IOUtils.closeQuietly(bufferedOutputStream);
                        throw th;
                    }
                }
                byte[] bArr = new byte[4096];
                long j2 = j;
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (i == -1) {
                        bufferedOutputStream2.flush();
                        if (requestCallBackHandler != null) {
                            requestCallBackHandler.updateProgress(contentLength, j2, true);
                        }
                        IOUtils.closeQuietly(bufferedInputStream);
                        IOUtils.closeQuietly(bufferedOutputStream2);
                        if (!file.exists() || TextUtils.isEmpty(str2)) {
                            return file;
                        }
                        File file2 = new File(file.getParent(), str2);
                        while (file2.exists()) {
                            file2 = new File(file.getParent(), String.valueOf(System.currentTimeMillis()) + str2);
                        }
                        return file.renameTo(file2) ? file2 : file;
                    }
                    bufferedOutputStream2.write(bArr, 0, i);
                    long j3 = j2 + ((long) i);
                    if (requestCallBackHandler != null && !requestCallBackHandler.updateProgress(contentLength, j3, false)) {
                        IOUtils.closeQuietly(bufferedInputStream);
                        IOUtils.closeQuietly(bufferedOutputStream2);
                        return file;
                    }
                    j2 = j3;
                    bArr = bArr;
                }
            } catch (Throwable th3) {
                th = th3;
            }
        } catch (Throwable th4) {
            th = th4;
            bufferedInputStream = null;
        }
    }
}

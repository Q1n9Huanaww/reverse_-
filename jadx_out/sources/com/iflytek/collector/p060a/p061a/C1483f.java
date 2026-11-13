package com.iflytek.collector.p060a.p061a;

import android.os.Build;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.config.SDKLogger;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.f */
/* JADX INFO: loaded from: classes.dex */
public class C1483f {
    /* JADX INFO: renamed from: a */
    private static byte[] m2810a(int i) {
        return new byte[]{(byte) ((i >> 24) & 255), (byte) ((i >> 16) & 255), (byte) ((i >> 8) & 255), (byte) (i & 255)};
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m2811a(byte[] bArr) {
        try {
            byte[] encoded = C1478a.m2786a().getEncoded();
            byte[] bArrM2787a = bArr != null ? C1478a.m2787a(m2812b(bArr), encoded) : null;
            byte[] bArrM2828a = C1485h.m2828a(encoded, C1485h.m2827a("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCt8H0BF3SquJmk6xIo2bTldgvtazLIeSbR4cle\np7zeUAtI/mC7UgFl8xXFCTemVambyQFnM5GsZOI1BpAMJO7N/YHRX7hvCZG6D0rEXQEdKXhKFIBQ\nmOYRYZP042vWRcKZ6iQLdLYmyg6tIzjYVfH0f6YX8OLIU7fy0TA/c88rzwIDAQAB"));
            if (bArrM2828a == null || bArrM2787a == null) {
                return null;
            }
            int length = bArrM2828a.length;
            int length2 = bArrM2787a.length;
            byte[] bArr2 = new byte[length + length2 + 10];
            bArr2[0] = 1;
            System.arraycopy(m2810a(length), 0, bArr2, 1, 4);
            System.arraycopy(bArrM2828a, 0, bArr2, 5, length);
            int i = 5 + length;
            bArr2[i] = 3;
            int i2 = i + 1;
            System.arraycopy(m2810a(length2), 0, bArr2, i2, 4);
            System.arraycopy(bArrM2787a, 0, bArr2, i2 + 4, length2);
            return bArr2;
        } catch (Exception unused) {
            SDKLogger.m2902e("Collector", "encrypt error");
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:46:0x004f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX INFO: renamed from: b */
    private static byte[] m2812b(byte[] bArr) throws Throwable {
        GZIPOutputStream gZIPOutputStream;
        if (bArr == null) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                try {
                    gZIPOutputStream.write(bArr);
                    gZIPOutputStream.finish();
                    if (Build.VERSION.SDK_INT < 19) {
                        gZIPOutputStream.flush();
                    }
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (IOException e) {
                            ThrowableExtension.printStackTrace(e);
                            return byteArray;
                        }
                    }
                    byteArrayOutputStream.close();
                    return byteArray;
                } catch (IOException e2) {
                    e = e2;
                    ThrowableExtension.printStackTrace(e);
                    if (gZIPOutputStream != null) {
                        try {
                            gZIPOutputStream.close();
                        } catch (IOException e3) {
                            ThrowableExtension.printStackTrace(e3);
                            return null;
                        }
                    }
                    byteArrayOutputStream.close();
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (IOException e4) {
                        ThrowableExtension.printStackTrace(e4);
                        throw th;
                    }
                }
                byteArrayOutputStream.close();
                throw th;
            }
        } catch (IOException e5) {
            e = e5;
            gZIPOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            gZIPOutputStream = null;
            if (gZIPOutputStream != null) {
                gZIPOutputStream.close();
            }
            byteArrayOutputStream.close();
            throw th;
        }
    }
}

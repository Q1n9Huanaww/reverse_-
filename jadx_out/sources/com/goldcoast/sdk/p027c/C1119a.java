package com.goldcoast.sdk.p027c;

import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.a */
/* JADX INFO: compiled from: AESUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1119a {

    /* JADX INFO: renamed from: a */
    private static String f1786a = "";

    /* JADX INFO: renamed from: a */
    public static String m1404a(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            int blockSize = cipher.getBlockSize();
            byte[] bytes = str.getBytes();
            int length = bytes.length;
            if (length % blockSize != 0) {
                length += blockSize - (length % blockSize);
            }
            byte[] bArr = new byte[length];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            if (f1786a.equals("")) {
                f1786a = new String(Base64.decode("Y2NiOWRmN2IyYjA5M2E2Yw==\n".getBytes(), 0));
            }
            cipher.init(1, new SecretKeySpec(f1786a.getBytes("UTF-8"), "AES"), new IvParameterSpec(f1786a.getBytes()));
            return Base64.encodeToString(cipher.doFinal(bArr), 4).trim();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m1405b(String str) {
        if (f1786a.equals("")) {
            f1786a = new String(Base64.decode("Y2NiOWRmN2IyYjA5M2E2Yw==\n".getBytes(), 0));
        }
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, new SecretKeySpec(f1786a.getBytes("UTF-8"), "AES"), new IvParameterSpec(f1786a.getBytes()));
            return new String(cipher.doFinal(Base64.decode(str, 4)), "UTF-8");
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}

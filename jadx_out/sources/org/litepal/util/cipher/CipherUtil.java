package org.litepal.util.cipher;

import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1770bg;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: loaded from: classes.dex */
public class CipherUtil {
    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    public static String aesKey = "LitePalKey";

    public static String aesEncrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return AESCrypt.encrypt(aesKey, str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String aesDecrypt(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return AESCrypt.decrypt(aesKey, str);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String md5Encrypt(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes(Charset.defaultCharset()));
            return new String(toHex(messageDigest.digest()));
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    private static char[] toHex(byte[] bArr) {
        char[] cArr = DIGITS_UPPER;
        int length = bArr.length;
        char[] cArr2 = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr2[i] = cArr[(bArr[i2] & 240) >>> 4];
            i = i3 + 1;
            cArr2[i3] = cArr[bArr[i2] & C1770bg.f4125m];
        }
        return cArr2;
    }
}

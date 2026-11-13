package com.cyjh.share.util;

import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1770bg;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: classes.dex */
public class DesUtil {

    /* JADX INFO: renamed from: iv */
    static AlgorithmParameterSpec f1689iv;
    private static Key key;
    private static byte[] DESkey = {C1770bg.f4123k, 24, 46, 110, 38, 102, 12, 36};
    private static byte[] DESIV = {32, 47, 11, -128, 29, 77, 105, 21};

    public static String encode(String str) throws Exception {
        init(DESkey, DESIV);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(1, key);
        byte[] bytes = str.getBytes("utf-8");
        if (bytes.length % 8 != 0) {
            byte[] bArr = new byte[(bytes.length + 8) - (bytes.length % 8)];
            System.arraycopy(bytes, 0, bArr, 0, bytes.length);
            bytes = bArr;
        }
        return Base64.encodeToString(cipher.doFinal(bytes), 0);
    }

    public static String encodeToKey(String str, byte[] bArr) throws Exception {
        init(bArr, DESIV);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(1, key);
        byte[] bytes = str.getBytes("utf-8");
        if (bytes.length % 8 != 0) {
            byte[] bArr2 = new byte[(bytes.length + 8) - (bytes.length % 8)];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            bytes = bArr2;
        }
        return Base64.encodeToString(cipher.doFinal(bytes), 2);
    }

    public static String encodeToKey1(String str, byte[] bArr) throws Exception {
        init(bArr, DESIV);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(1, key);
        byte[] bytes = str.getBytes("utf-8");
        if (bytes.length % 8 != 0) {
            byte[] bArr2 = new byte[(bytes.length + 8) - (bytes.length % 8)];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            bytes = bArr2;
        }
        return Base64.encodeToString(cipher.doFinal(bytes), 2);
    }

    private static void init(byte[] bArr, byte[] bArr2) {
        try {
            DESKeySpec dESKeySpec = new DESKeySpec(bArr);
            f1689iv = new IvParameterSpec(bArr2);
            key = SecretKeyFactory.getInstance("DES").generateSecret(dESKeySpec);
        } catch (InvalidKeyException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchAlgorithmException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (InvalidKeySpecException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
    }

    public static String decode(String str) throws Exception {
        init(DESkey, DESIV);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(2, key);
        return new String(cipher.doFinal(Base64.decode(str, 1)), "UTF-8").trim();
    }

    public static String decodeToKey(String str, byte[] bArr) throws Exception {
        init(bArr, DESIV);
        Cipher cipher = Cipher.getInstance("DES/ECB/NoPadding");
        cipher.init(2, key);
        return new String(cipher.doFinal(Base64.decode(str, 1)), "UTF-8").trim();
    }
}

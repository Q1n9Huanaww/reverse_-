package com.cyjh.share.Encrypt;

import android.util.Base64;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
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
    private static AlgorithmParameterSpec f1677iv;
    private static Key key;

    public static String encode(String str, byte[] bArr, byte[] bArr2) throws Exception {
        if (key == null || f1677iv == null) {
            init(bArr, bArr2);
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(1, key, f1677iv);
        return Base64.encodeToString(cipher.doFinal(str.getBytes("utf-8")), 2);
    }

    private static void init(byte[] bArr, byte[] bArr2) {
        try {
            DESKeySpec dESKeySpec = new DESKeySpec(bArr);
            f1677iv = new IvParameterSpec(bArr2);
            key = SecretKeyFactory.getInstance("DES").generateSecret(dESKeySpec);
        } catch (InvalidKeyException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchAlgorithmException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (InvalidKeySpecException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
    }

    public static String decode(String str, byte[] bArr, byte[] bArr2) throws Exception {
        if (key == null || f1677iv == null) {
            init(bArr, bArr2);
        }
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
        cipher.init(2, key, f1677iv);
        return new String(cipher.doFinal(Base64.decode(str, 0)), "UTF-8");
    }
}

package com.iflytek.collector.p060a.p061a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.a */
/* JADX INFO: loaded from: classes.dex */
public class C1478a {
    /* JADX INFO: renamed from: a */
    public static SecretKeySpec m2786a() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(128, new SecureRandom());
            return new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m2787a(byte[] bArr, byte[] bArr2) {
        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, new SecretKeySpec(bArr2, "AES"));
            return cipher.doFinal(bArr);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}

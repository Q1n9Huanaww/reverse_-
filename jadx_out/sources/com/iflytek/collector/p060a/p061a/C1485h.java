package com.iflytek.collector.p060a.p061a;

import android.util.Base64;
import com.iflytek.voiceads.config.SDKLogger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.h */
/* JADX INFO: loaded from: classes.dex */
public class C1485h {
    /* JADX INFO: renamed from: a */
    public static RSAPublicKey m2827a(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (Exception e) {
            SDKLogger.m2901e("loadPublicKey:" + e.getMessage());
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m2828a(byte[] bArr, RSAPublicKey rSAPublicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(1, rSAPublicKey);
        return cipher.doFinal(bArr);
    }
}

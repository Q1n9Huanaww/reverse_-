package com.iflytek.voiceads.utils;

import android.text.TextUtils;
import android.util.Base64;
import com.iflytek.voiceads.config.SDKConstants;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.proguard.C1770bg;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1590d {
    /* JADX INFO: renamed from: a */
    public static synchronized String m3179a(String str) {
        String string;
        if (TextUtils.isEmpty(str)) {
            string = "";
        } else {
            try {
                byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(str.getBytes());
                StringBuilder sb = new StringBuilder();
                for (byte b : bArrDigest) {
                    String hexString = Integer.toHexString(b & 255);
                    if (hexString.length() == 1) {
                        hexString = AccountManager.TYPE_LOGIN + hexString;
                    }
                    sb.append(hexString);
                }
                string = sb.toString();
            } catch (NoSuchAlgorithmException e) {
                C1593g.m3190b(SDKConstants.TAG, "md5 error:" + e.getMessage());
                string = "";
            }
        }
        return string;
    }

    /* JADX INFO: renamed from: b */
    public static String m3180b(String str) {
        try {
            byte[] bytes = "5120aba7dd8880ba".getBytes("utf-8");
            byte[] bArr = new byte[16];
            byte[] bArr2 = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, C1770bg.f4123k, C1770bg.f4124l, C1770bg.f4125m};
            for (int i = 0; i < 16; i++) {
                if (bytes.length > i) {
                    bArr[i] = bytes[i];
                } else {
                    bArr[i] = bArr2[0];
                }
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(1, secretKeySpec);
            return Base64.encodeToString(cipher.doFinal(str.getBytes("utf-8")), 8);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getEncPrice:" + e.getMessage());
            return "";
        }
    }
}

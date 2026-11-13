package com.cyjh.share.util;

import android.util.Base64;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/* JADX INFO: loaded from: classes.dex */
public class MyRas {
    public static final String CIPHER_ALGORITHM = "RSA/ECB/NoPadding";
    public static final String KEY_ALGORITHM = "RSA";
    public static final int KEY_SIZE = 2048;
    public static final String PLAIN_TEXT = "MANUTD is the greatest club in the world";
    public static final String PRIVATE_KEY = "2";
    public static final String PUBLIC_KEY = "1";

    public static KeyPair genKeyPair(int i) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static Map<String, byte[]> generateKeyBytes() {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPairGenerateKeyPair = keyPairGenerator.generateKeyPair();
            RSAPublicKey rSAPublicKey = (RSAPublicKey) keyPairGenerateKeyPair.getPublic();
            RSAPrivateKey rSAPrivateKey = (RSAPrivateKey) keyPairGenerateKeyPair.getPrivate();
            HashMap map = new HashMap();
            map.put("1", rSAPublicKey.getEncoded());
            map.put("2", rSAPrivateKey.getEncoded());
            Log.e("ffff", "fuck.1...     " + Base64.encodeToString(rSAPublicKey.getEncoded(), 2));
            Log.e("ffff", "fuck.2...     " + Base64.encodeToString(rSAPrivateKey.getEncoded(), 2));
            return map;
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static PublicKey restorePublicKey(byte[] bArr) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public PublicKey restorePublicKeyKey(byte[] bArr) {
        try {
            return KeyFactory.getInstance("RSA").generatePublic(new PKCS8EncodedKeySpec(bArr));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static byte[] RSAEncode(PublicKey publicKey, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(1, publicKey);
            return cipher.doFinal(bArr);
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String RSADecode(PrivateKey privateKey, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(2, privateKey);
            return new String(cipher.doFinal(bArr));
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String RSADecode(PublicKey publicKey, byte[] bArr) {
        try {
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(2, publicKey);
            return new String(cipher.doFinal(bArr));
        } catch (InvalidKeyException | NoSuchAlgorithmException | BadPaddingException | IllegalBlockSizeException | NoSuchPaddingException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static RSAPublicKey loadPublicKeyByStr(String str) throws Exception {
        try {
            return (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NullPointerException unused) {
            throw new Exception("公钥数据为空");
        } catch (NoSuchAlgorithmException unused2) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException unused3) {
            throw new Exception("公钥非法");
        }
    }
}

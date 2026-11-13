package com.umeng.commonsdk.statistics.common;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.proguard.C1770bg;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: loaded from: classes.dex */
public class DataHelper {
    public static long ENVELOPE_ENTITY_RAW_LENGTH_MAX = 2097152;
    public static long ENVELOPE_EXTRA_LENGTH = 1024;
    public static long ENVELOPE_LENGTH_MAX = 204800;

    /* JADX INFO: renamed from: iv */
    private static final byte[] f4497iv = {10, 1, 11, 5, 4, C1770bg.f4125m, 7, 9, 23, 3, 1, 6, 8, 12, C1770bg.f4123k, 91};

    public static boolean largeThanMaxSize(long j, long j2) {
        return j > j2;
    }

    public static byte[] reverseHexString(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length % 2 != 0) {
            return null;
        }
        byte[] bArr = new byte[length / 2];
        int i = 0;
        while (i < length) {
            int i2 = i + 2;
            bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i2), 16).intValue();
            i = i2;
        }
        return bArr;
    }

    public static String toHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(String.format("%02X", Byte.valueOf(b)));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    public static byte[] hash(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static byte[] encrypt(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f4497iv));
        return cipher.doFinal(bArr);
    }

    public static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
        cipher.init(2, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f4497iv));
        return cipher.doFinal(bArr);
    }

    public static int random(int i, String str) throws Throwable {
        if (new Random().nextFloat() < 0.001d) {
            int i2 = 0;
            if (str == null) {
                MLog.m4340e("--->", "null signature..");
            }
            try {
                i2 = Integer.parseInt(str.substring(9, 11), 16);
            } catch (Exception unused) {
            }
            return (i2 | 128) * 1000;
        }
        int iNextInt = new Random().nextInt(i);
        if (iNextInt > 255000 || iNextInt < 128000) {
            return iNextInt;
        }
        return 127000;
    }

    public static String convertExceptionToString(Throwable th) {
        String str = null;
        if (th == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            ThrowableExtension.printStackTrace(th, printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                ThrowableExtension.printStackTrace(cause, printWriter);
            }
            String string = stringWriter.toString();
            try {
                printWriter.close();
                stringWriter.close();
                return string;
            } catch (Exception e) {
                str = string;
                e = e;
                ThrowableExtension.printStackTrace(e);
                return str;
            }
        } catch (Exception e2) {
            e = e2;
        }
    }

    public static String assembleURL(String str) {
        return "https://" + str + ".umeng.com/unify_logs";
    }

    public static String encryptBySHA1(String str) {
        byte[] bytes = str.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(bytes);
            return bytes2Hex(messageDigest.digest());
        } catch (Exception unused) {
            return null;
        }
    }

    static String bytes2Hex(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                str = str + AccountManager.TYPE_LOGIN;
            }
            str = str + hexString;
        }
        return str;
    }
}

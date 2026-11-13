package mobi.oneway.export.p087g;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* JADX INFO: renamed from: mobi.oneway.export.g.a */
/* JADX INFO: loaded from: classes.dex */
public class C1947a {

    /* JADX INFO: renamed from: a */
    private static final Map<String, Cipher> f4998a = new ConcurrentHashMap();

    /* JADX INFO: renamed from: a */
    private static String m4959a(String str, int i, boolean z) {
        char[] charArray = str.toCharArray();
        char[] cArr = new char[i];
        int i2 = !z ? 1 : 0;
        for (int i3 = 0; i3 < i; i3++) {
            cArr[i3] = charArray[(i3 * 2) + i2];
        }
        return String.valueOf(cArr);
    }

    /* JADX INFO: renamed from: a */
    public static String m4960a(String str, String str2, String str3) {
        String[] strArrM4964a = m4964a(str2, str3);
        return m4966c(str, strArrM4964a[0], strArrM4964a[1]);
    }

    /* JADX INFO: renamed from: a */
    private static Cipher m4961a(String str, String str2, int i) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
        String str3 = str + ":" + str2 + ":" + i;
        Cipher cipher = f4998a.get(str3);
        if (cipher != null) {
            return cipher;
        }
        SecretKeySpec secretKeySpecM4962a = m4962a(str);
        Cipher cipher2 = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher2.init(i, secretKeySpecM4962a, new IvParameterSpec(str2.getBytes("utf-8")));
        f4998a.put(str3, cipher2);
        return cipher2;
    }

    /* JADX INFO: renamed from: a */
    private static SecretKeySpec m4962a(String str) throws UnsupportedEncodingException {
        byte[] bytes = str.getBytes("utf-8");
        byte[] bArr = new byte[16];
        for (int i = 0; i < bytes.length && i < bArr.length; i++) {
            bArr[i] = bytes[i];
        }
        return new SecretKeySpec(bArr, "AES");
    }

    /* JADX INFO: renamed from: a */
    private static boolean m4963a(int i) {
        return (i & 1) == 1;
    }

    /* JADX INFO: renamed from: a */
    private static String[] m4964a(String str, String str2) {
        char cCharAt = str.charAt(2);
        char cCharAt2 = str2.charAt(5);
        String strM4959a = m4959a(str2, 8, m4963a(cCharAt));
        String strM4959a2 = m4959a(str, 8, m4963a(cCharAt2));
        return new String[]{strM4959a + strM4959a2, strM4959a2 + strM4959a};
    }

    /* JADX INFO: renamed from: b */
    public static String m4965b(String str, String str2, String str3) {
        String[] strArrM4964a = m4964a(str2, str3);
        return m4967d(str, strArrM4964a[0], strArrM4964a[1]);
    }

    /* JADX INFO: renamed from: c */
    private static String m4966c(String str, String str2, String str3) {
        return new String(C1948b.m4974a(m4961a(str2, str3, 1).doFinal(str.getBytes("utf-8"))), "utf-8");
    }

    /* JADX INFO: renamed from: d */
    private static String m4967d(String str, String str2, String str3) {
        return new String(m4961a(str2, str3, 2).doFinal(C1948b.m4980b(str.getBytes("utf-8"))));
    }
}

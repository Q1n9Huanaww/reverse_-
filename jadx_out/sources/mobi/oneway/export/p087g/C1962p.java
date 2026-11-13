package mobi.oneway.export.p087g;

import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX INFO: renamed from: mobi.oneway.export.g.p */
/* JADX INFO: loaded from: classes.dex */
public class C1962p {
    /* JADX INFO: renamed from: a */
    public static String m5058a(File file) throws Throwable {
        BufferedInputStream bufferedInputStream;
        try {
            bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = bufferedInputStream.read(bArr);
                    if (i == -1) {
                        String strM5061b = m5061b(messageDigest.digest());
                        C1956j.m5037a(bufferedInputStream);
                        return strM5061b;
                    }
                    messageDigest.update(bArr, 0, i);
                }
            } catch (Throwable th) {
                th = th;
                C1956j.m5037a(bufferedInputStream);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedInputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m5059a(String str) {
        return m5060a(str.getBytes("utf-8"));
    }

    /* JADX INFO: renamed from: a */
    public static String m5060a(byte[] bArr) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(bArr, 0, bArr.length);
        return m5061b(messageDigest.digest());
    }

    /* JADX INFO: renamed from: b */
    private static String m5061b(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            int i = b & 255;
            if (i <= 15) {
                str = str + AccountManager.TYPE_LOGIN;
            }
            str = str + Integer.toHexString(i);
        }
        return str;
    }
}

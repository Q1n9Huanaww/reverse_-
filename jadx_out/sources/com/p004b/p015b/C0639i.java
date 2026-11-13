package com.p004b.p015b;

import com.umeng.commonsdk.proguard.C1770bg;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/* JADX INFO: renamed from: com.b.b.i */
/* JADX INFO: compiled from: ByteString.java */
/* JADX INFO: loaded from: classes.dex */
public class C0639i implements Serializable, Comparable<C0639i> {

    /* JADX INFO: renamed from: a */
    static final char[] f815a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* JADX INFO: renamed from: b */
    public static final C0639i f816b = m576a(new byte[0]);

    /* JADX INFO: renamed from: c */
    final byte[] f817c;

    /* JADX INFO: renamed from: d */
    transient int f818d;

    /* JADX INFO: renamed from: e */
    transient String f819e;

    @Override // java.lang.Comparable
    public /* synthetic */ int compareTo(C0639i c0639i) {
        C0639i c0639i2 = c0639i;
        int iMo590g = mo590g();
        int iMo590g2 = c0639i2.mo590g();
        int iMin = Math.min(iMo590g, iMo590g2);
        for (int i = 0; i < iMin; i++) {
            int iMo579a = mo579a(i) & 255;
            int iMo579a2 = c0639i2.mo579a(i) & 255;
            if (iMo579a != iMo579a2) {
                return iMo579a < iMo579a2 ? -1 : 1;
            }
        }
        if (iMo590g == iMo590g2) {
            return 0;
        }
        return iMo590g < iMo590g2 ? -1 : 1;
    }

    C0639i(byte[] bArr) {
        this.f817c = bArr;
    }

    /* JADX INFO: renamed from: a */
    public static C0639i m576a(byte... bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("data == null");
        }
        return new C0639i((byte[]) bArr.clone());
    }

    /* JADX INFO: renamed from: a */
    public static C0639i m575a(String str) {
        if (str == null) {
            throw new IllegalArgumentException("s == null");
        }
        C0639i c0639i = new C0639i(str.getBytes(C0631aa.f805a));
        c0639i.f819e = str;
        return c0639i;
    }

    /* JADX INFO: renamed from: a */
    public String mo581a() {
        String str = this.f819e;
        if (str != null) {
            return str;
        }
        String str2 = new String(this.f817c, C0631aa.f805a);
        this.f819e = str2;
        return str2;
    }

    /* JADX INFO: renamed from: b */
    public String mo585b() {
        return C0635e.m522a(this.f817c);
    }

    /* JADX INFO: renamed from: c */
    public C0639i mo586c() {
        return m578c("SHA-1");
    }

    /* JADX INFO: renamed from: d */
    public C0639i mo587d() {
        return m578c("SHA-256");
    }

    /* JADX INFO: renamed from: c */
    private C0639i m578c(String str) {
        try {
            return m576a(MessageDigest.getInstance(str).digest(this.f817c));
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(e);
        }
    }

    /* JADX INFO: renamed from: e */
    public String mo588e() {
        char[] cArr = new char[this.f817c.length * 2];
        int i = 0;
        for (byte b : this.f817c) {
            int i2 = i + 1;
            cArr[i] = f815a[(b >> 4) & 15];
            i = i2 + 1;
            cArr[i2] = f815a[b & C1770bg.f4125m];
        }
        return new String(cArr);
    }

    /* JADX INFO: renamed from: b */
    public static C0639i m577b(String str) {
        if (str.length() % 2 != 0) {
            throw new IllegalArgumentException("Unexpected hex string: " + str);
        }
        byte[] bArr = new byte[str.length() / 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) ((m574a(str.charAt(i2)) << 4) + m574a(str.charAt(i2 + 1)));
        }
        return m576a(bArr);
    }

    /* JADX INFO: renamed from: a */
    private static int m574a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c >= 'A' && c <= 'F') {
            return (c - 'A') + 10;
        }
        throw new IllegalArgumentException("Unexpected hex digit: " + c);
    }

    /* JADX INFO: renamed from: f */
    public C0639i mo589f() {
        for (int i = 0; i < this.f817c.length; i++) {
            byte b = this.f817c[i];
            if (b >= 65 && b <= 90) {
                byte[] bArr = (byte[]) this.f817c.clone();
                bArr[i] = (byte) (b + 32);
                for (int i2 = i + 1; i2 < bArr.length; i2++) {
                    byte b2 = bArr[i2];
                    if (b2 >= 65 && b2 <= 90) {
                        bArr[i2] = (byte) (b2 + 32);
                    }
                }
                return new C0639i(bArr);
            }
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C0639i mo580a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0");
        }
        if (i2 > this.f817c.length) {
            throw new IllegalArgumentException("endIndex > length(" + this.f817c.length + ")");
        }
        int i3 = i2 - i;
        if (i3 < 0) {
            throw new IllegalArgumentException("endIndex < beginIndex");
        }
        if (i == 0 && i2 == this.f817c.length) {
            return this;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.f817c, i, bArr, 0, i3);
        return new C0639i(bArr);
    }

    /* JADX INFO: renamed from: a */
    public byte mo579a(int i) {
        return this.f817c[i];
    }

    /* JADX INFO: renamed from: g */
    public int mo590g() {
        return this.f817c.length;
    }

    /* JADX INFO: renamed from: h */
    public byte[] mo591h() {
        return (byte[]) this.f817c.clone();
    }

    /* JADX INFO: renamed from: a */
    void mo582a(C0636f c0636f) {
        c0636f.mo544b(this.f817c, 0, this.f817c.length);
    }

    /* JADX INFO: renamed from: a */
    public boolean mo583a(int i, C0639i c0639i, int i2, int i3) {
        return c0639i.mo584a(0, this.f817c, 0, i3);
    }

    /* JADX INFO: renamed from: a */
    public boolean mo584a(int i, byte[] bArr, int i2, int i3) {
        return i >= 0 && i <= this.f817c.length - i3 && i2 >= 0 && i2 <= bArr.length - i3 && C0631aa.m521a(this.f817c, i, bArr, i2, i3);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof C0639i) {
            C0639i c0639i = (C0639i) obj;
            if (c0639i.mo590g() == this.f817c.length && c0639i.mo584a(0, this.f817c, 0, this.f817c.length)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        int i = this.f818d;
        if (i != 0) {
            return i;
        }
        int iHashCode = Arrays.hashCode(this.f817c);
        this.f818d = iHashCode;
        return iHashCode;
    }

    public String toString() {
        StringBuilder sb;
        String str;
        if (this.f817c.length == 0) {
            return "[size=0]";
        }
        String strMo581a = mo581a();
        int length = strMo581a.length();
        int length2 = 0;
        int i = 0;
        while (true) {
            if (length2 >= length) {
                length2 = strMo581a.length();
                break;
            }
            if (i != 64) {
                int iCodePointAt = strMo581a.codePointAt(length2);
                if ((Character.isISOControl(iCodePointAt) && iCodePointAt != 10 && iCodePointAt != 13) || iCodePointAt == 65533) {
                    length2 = -1;
                    break;
                }
                i++;
                length2 += Character.charCount(iCodePointAt);
            } else {
                break;
            }
        }
        if (length2 == -1) {
            if (this.f817c.length <= 64) {
                return "[hex=" + mo588e() + "]";
            }
            return "[size=" + this.f817c.length + " hex=" + mo580a(0, 64).mo588e() + "…]";
        }
        String strReplace = strMo581a.substring(0, length2).replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "\\r");
        if (length2 < strMo581a.length()) {
            sb = new StringBuilder("[size=");
            sb.append(this.f817c.length);
            sb.append(" text=");
            sb.append(strReplace);
            str = "…]";
        } else {
            sb = new StringBuilder("[text=");
            sb.append(strReplace);
            str = "]";
        }
        sb.append(str);
        return sb.toString();
    }
}

package com.p004b.p005a.p006a;

import android.support.v4.media.session.PlaybackStateCompat;
import com.p004b.p005a.AbstractC0597aq;
import com.p004b.p005a.AbstractC0601au;
import com.p004b.p005a.C0583ac;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.C0639i;
import com.p004b.p015b.InterfaceC0638h;
import com.p004b.p015b.InterfaceC0654x;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.reflect.Array;
import java.net.IDN;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.b.a.a.c */
/* JADX INFO: compiled from: Util.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0503c {

    /* JADX INFO: renamed from: a */
    public static final byte[] f169a = new byte[0];

    /* JADX INFO: renamed from: b */
    public static final String[] f170b = new String[0];

    /* JADX INFO: renamed from: c */
    public static final AbstractC0601au f171c = AbstractC0601au.m443a(f169a);

    /* JADX INFO: renamed from: d */
    public static final AbstractC0597aq f172d = AbstractC0597aq.m414a(f169a);

    /* JADX INFO: renamed from: g */
    private static final C0639i f175g = C0639i.m577b("efbbbf");

    /* JADX INFO: renamed from: h */
    private static final C0639i f176h = C0639i.m577b("feff");

    /* JADX INFO: renamed from: i */
    private static final C0639i f177i = C0639i.m577b("fffe");

    /* JADX INFO: renamed from: j */
    private static final C0639i f178j = C0639i.m577b("0000ffff");

    /* JADX INFO: renamed from: k */
    private static final C0639i f179k = C0639i.m577b("ffff0000");

    /* JADX INFO: renamed from: e */
    public static final Charset f173e = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: l */
    private static final Charset f180l = Charset.forName("UTF-16BE");

    /* JADX INFO: renamed from: m */
    private static final Charset f181m = Charset.forName("UTF-16LE");

    /* JADX INFO: renamed from: n */
    private static final Charset f182n = Charset.forName("UTF-32BE");

    /* JADX INFO: renamed from: o */
    private static final Charset f183o = Charset.forName("UTF-32LE");

    /* JADX INFO: renamed from: f */
    public static final TimeZone f174f = TimeZone.getTimeZone("GMT");

    /* JADX INFO: renamed from: p */
    private static final Pattern f184p = Pattern.compile("([0-9a-fA-F]*:[0-9a-fA-F:.]*)|([\\d.]+)");

    /* JADX INFO: renamed from: a */
    public static void m115a(long j, long j2) {
        if ((j2 | 0) < 0 || 0 > j || j - 0 < j2) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m121a(Object obj, Object obj2) {
        if (obj != obj2) {
            return obj != null && obj.equals(obj2);
        }
        return true;
    }

    /* JADX INFO: renamed from: a */
    public static void m116a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m117a(Socket socket) {
        if (socket != null) {
            try {
                socket.close();
            } catch (AssertionError e) {
                if (!m120a(e)) {
                    throw e;
                }
            } catch (RuntimeException e2) {
                throw e2;
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m119a(InterfaceC0654x interfaceC0654x, TimeUnit timeUnit) {
        try {
            return m118a(interfaceC0654x, 100, timeUnit);
        } catch (IOException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m118a(InterfaceC0654x interfaceC0654x, int i, TimeUnit timeUnit) {
        long jNanoTime = System.nanoTime();
        long jMo598d = interfaceC0654x.mo48a().mo597c_() ? interfaceC0654x.mo48a().mo598d() - jNanoTime : Long.MAX_VALUE;
        interfaceC0654x.mo48a().mo594a(jNanoTime + Math.min(jMo598d, timeUnit.toNanos(i)));
        try {
            C0636f c0636f = new C0636f();
            while (interfaceC0654x.mo47a(c0636f, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
                c0636f.m572o();
            }
            if (jMo598d == Long.MAX_VALUE) {
                interfaceC0654x.mo48a().mo600e_();
                return true;
            }
            interfaceC0654x.mo48a().mo594a(jNanoTime + jMo598d);
            return true;
        } catch (InterruptedIOException unused) {
            if (jMo598d == Long.MAX_VALUE) {
                interfaceC0654x.mo48a().mo600e_();
                return false;
            }
            interfaceC0654x.mo48a().mo594a(jNanoTime + jMo598d);
            return false;
        } catch (Throwable th) {
            if (jMo598d == Long.MAX_VALUE) {
                interfaceC0654x.mo48a().mo600e_();
            } else {
                interfaceC0654x.mo48a().mo594a(jNanoTime + jMo598d);
            }
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    public static <T> List<T> m112a(List<T> list) {
        return Collections.unmodifiableList(new ArrayList(list));
    }

    /* JADX INFO: renamed from: a */
    public static <T> List<T> m113a(T... tArr) {
        return Collections.unmodifiableList(Arrays.asList((Object[]) tArr.clone()));
    }

    /* JADX INFO: renamed from: a */
    public static ThreadFactory m114a(String str, boolean z) {
        return new ThreadFactoryC0517d(str, z);
    }

    /* JADX INFO: renamed from: a */
    public static String m108a(C0583ac c0583ac, boolean z) {
        String strM351f;
        if (c0583ac.m351f().contains(":")) {
            strM351f = "[" + c0583ac.m351f() + "]";
        } else {
            strM351f = c0583ac.m351f();
        }
        if (!z && c0583ac.m352g() == C0583ac.m333a(c0583ac.m345b())) {
            return strM351f;
        }
        return strM351f + ":" + c0583ac.m352g();
    }

    /* JADX INFO: renamed from: a */
    public static boolean m120a(AssertionError assertionError) {
        return (assertionError.getCause() == null || assertionError.getMessage() == null || !assertionError.getMessage().contains("getsockname failed")) ? false : true;
    }

    /* JADX INFO: renamed from: a */
    public static <T> int m107a(T[] tArr, T t) {
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            if (m121a(tArr[i], t)) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: renamed from: a */
    public static String[] m123a(String[] strArr, String str) {
        String[] strArr2 = new String[strArr.length + 1];
        System.arraycopy(strArr, 0, strArr2, 0, strArr.length);
        strArr2[strArr2.length - 1] = str;
        return strArr2;
    }

    /* JADX INFO: renamed from: a */
    public static int m104a(String str, int i, int i2) {
        while (i < i2) {
            switch (str.charAt(i)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    i++;
                    break;
                default:
                    return i;
            }
        }
        return i2;
    }

    /* JADX INFO: renamed from: b */
    public static int m125b(String str, int i, int i2) {
        for (int i3 = i2 - 1; i3 >= i; i3--) {
            switch (str.charAt(i3)) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                    break;
                default:
                    return i3 + 1;
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: c */
    public static String m126c(String str, int i, int i2) {
        int iM104a = m104a(str, i, i2);
        return str.substring(iM104a, m125b(str, iM104a, i2));
    }

    /* JADX INFO: renamed from: a */
    public static int m106a(String str, int i, int i2, String str2) {
        while (i < i2) {
            if (str2.indexOf(str.charAt(i)) != -1) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    public static int m105a(String str, int i, int i2, char c) {
        while (i < i2) {
            if (str.charAt(i) == c) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    public static String m109a(String str) {
        boolean z;
        try {
            String lowerCase = IDN.toASCII(str).toLowerCase(Locale.US);
            if (lowerCase.isEmpty()) {
                return null;
            }
            int i = 0;
            while (true) {
                z = true;
                if (i >= lowerCase.length()) {
                    z = false;
                    break;
                }
                char cCharAt = lowerCase.charAt(i);
                if (cCharAt <= 31 || cCharAt >= 127 || " #%/:?@[\\]".indexOf(cCharAt) != -1) {
                    break;
                    break;
                    break;
                }
                i++;
            }
            if (z) {
                return null;
            }
            return lowerCase;
        } catch (IllegalArgumentException unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static int m124b(String str) {
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt <= 31 || cCharAt >= 127) {
                return i;
            }
        }
        return -1;
    }

    /* JADX INFO: renamed from: c */
    public static boolean m127c(String str) {
        return f184p.matcher(str).matches();
    }

    /* JADX INFO: renamed from: a */
    public static String m110a(String str, Object... objArr) {
        return String.format(Locale.US, str, objArr);
    }

    /* JADX INFO: renamed from: a */
    public static Charset m111a(InterfaceC0638h interfaceC0638h, Charset charset) {
        if (interfaceC0638h.mo545b(f175g)) {
            interfaceC0638h.mo557f(f175g.mo590g());
            return f173e;
        }
        if (interfaceC0638h.mo545b(f176h)) {
            interfaceC0638h.mo557f(f176h.mo590g());
            return f180l;
        }
        if (interfaceC0638h.mo545b(f177i)) {
            interfaceC0638h.mo557f(f177i.mo590g());
            return f181m;
        }
        if (interfaceC0638h.mo545b(f178j)) {
            interfaceC0638h.mo557f(f178j.mo590g());
            return f182n;
        }
        if (!interfaceC0638h.mo545b(f179k)) {
            return charset;
        }
        interfaceC0638h.mo557f(f179k.mo590g());
        return f183o;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: a */
    public static <T> T[] m122a(Class<T> cls, T[] tArr, T[] tArr2) {
        ArrayList arrayList = new ArrayList();
        for (Object[] objArr : tArr) {
            for (T t : tArr2) {
                if (objArr.equals(t)) {
                    arrayList.add(t);
                    break;
                }
            }
        }
        return (T[]) arrayList.toArray((Object[]) Array.newInstance((Class<?>) cls, arrayList.size()));
    }
}

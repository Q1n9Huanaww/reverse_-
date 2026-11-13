package com.p004b.p005a.p006a.p009c;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0583ac;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0621r;
import com.p004b.p005a.InterfaceC0622s;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.b.a.a.c.f */
/* JADX INFO: compiled from: HttpHeaders.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0509f {

    /* JADX INFO: renamed from: a */
    private static final Pattern f190a = Pattern.compile(" +([^ \"=]*)=(:?\"([^\"]*)\"|([^ \"=]*)) *(:?,|$)");

    /* JADX INFO: renamed from: a */
    public static long m139a(C0599as c0599as) {
        return m138a(c0599as.m422d());
    }

    /* JADX INFO: renamed from: a */
    public static long m138a(C0581aa c0581aa) {
        return m140a(c0581aa.m323a("Content-Length"));
    }

    /* JADX INFO: renamed from: a */
    private static long m140a(String str) {
        if (str == null) {
            return -1L;
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m141a(InterfaceC0622s interfaceC0622s, C0583ac c0583ac, C0581aa c0581aa) {
        if (interfaceC0622s == InterfaceC0622s.f781a || C0621r.m487a(c0583ac, c0581aa).isEmpty()) {
            return;
        }
        interfaceC0622s.mo490a();
    }

    /* JADX INFO: renamed from: b */
    public static boolean m143b(C0599as c0599as) {
        if (c0599as.m418a().m400b().equals("HEAD")) {
            return false;
        }
        int iM420b = c0599as.m420b();
        return (((iM420b >= 100 && iM420b < 200) || iM420b == 204 || iM420b == 304) && m138a(c0599as.m422d()) == -1 && !"chunked".equalsIgnoreCase(c0599as.m419a("Transfer-Encoding"))) ? false : true;
    }

    /* JADX INFO: renamed from: a */
    public static int m137a(String str, int i, String str2) {
        while (i < str.length() && str2.indexOf(str.charAt(i)) == -1) {
            i++;
        }
        return i;
    }

    /* JADX INFO: renamed from: a */
    public static int m136a(String str, int i) {
        char cCharAt;
        while (i < str.length() && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
            i++;
        }
        return i;
    }

    /* JADX INFO: renamed from: b */
    public static int m142b(String str, int i) {
        try {
            long j = Long.parseLong(str);
            if (j > 2147483647L) {
                return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            }
            if (j < 0) {
                return 0;
            }
            return (int) j;
        } catch (NumberFormatException unused) {
            return i;
        }
    }
}

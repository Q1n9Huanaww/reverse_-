package com.p004b.p005a;

import android.support.v7.widget.ActivityChooserView;
import com.p004b.p005a.p006a.p009c.C0509f;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.e */
/* JADX INFO: compiled from: CacheControl.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0608e {

    /* JADX INFO: renamed from: a */
    public static final C0608e f604a;

    /* JADX INFO: renamed from: b */
    public static final C0608e f605b;

    /* JADX INFO: renamed from: c */
    String f606c;

    /* JADX INFO: renamed from: d */
    private final boolean f607d;

    /* JADX INFO: renamed from: e */
    private final boolean f608e;

    /* JADX INFO: renamed from: f */
    private final int f609f;

    /* JADX INFO: renamed from: g */
    private final int f610g;

    /* JADX INFO: renamed from: h */
    private final boolean f611h;

    /* JADX INFO: renamed from: i */
    private final boolean f612i;

    /* JADX INFO: renamed from: j */
    private final boolean f613j;

    /* JADX INFO: renamed from: k */
    private final int f614k;

    /* JADX INFO: renamed from: l */
    private final int f615l;

    /* JADX INFO: renamed from: m */
    private final boolean f616m;

    /* JADX INFO: renamed from: n */
    private final boolean f617n;

    static {
        C0609f c0609f = new C0609f();
        c0609f.f618a = true;
        f604a = c0609f.m461a();
        C0609f c0609f2 = new C0609f();
        c0609f2.f623f = true;
        long seconds = TimeUnit.SECONDS.toSeconds(2147483647L);
        c0609f2.f621d = seconds > 2147483647L ? ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED : (int) seconds;
        f605b = c0609f2.m461a();
    }

    private C0608e(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.f607d = z;
        this.f608e = z2;
        this.f609f = i;
        this.f610g = i2;
        this.f611h = z3;
        this.f612i = z4;
        this.f613j = z5;
        this.f614k = i3;
        this.f615l = i4;
        this.f616m = z6;
        this.f617n = z7;
        this.f606c = str;
    }

    C0608e(C0609f c0609f) {
        this.f607d = c0609f.f618a;
        this.f608e = c0609f.f619b;
        this.f609f = c0609f.f620c;
        this.f610g = -1;
        this.f611h = false;
        this.f612i = false;
        this.f613j = false;
        this.f614k = c0609f.f621d;
        this.f615l = c0609f.f622e;
        this.f616m = c0609f.f623f;
        this.f617n = c0609f.f624g;
    }

    /* JADX INFO: renamed from: a */
    public final boolean m452a() {
        return this.f607d;
    }

    /* JADX INFO: renamed from: b */
    public final boolean m453b() {
        return this.f608e;
    }

    /* JADX INFO: renamed from: c */
    public final int m454c() {
        return this.f609f;
    }

    /* JADX INFO: renamed from: d */
    public final boolean m455d() {
        return this.f611h;
    }

    /* JADX INFO: renamed from: e */
    public final boolean m456e() {
        return this.f612i;
    }

    /* JADX INFO: renamed from: f */
    public final boolean m457f() {
        return this.f613j;
    }

    /* JADX INFO: renamed from: g */
    public final int m458g() {
        return this.f614k;
    }

    /* JADX INFO: renamed from: h */
    public final int m459h() {
        return this.f615l;
    }

    /* JADX INFO: renamed from: i */
    public final boolean m460i() {
        return this.f616m;
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0041  */
    /* JADX WARN: Code duplicated, block: B:28:0x009c  */
    /* JADX WARN: Code duplicated, block: B:39:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:42:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:54:0x010b  */
    /* JADX WARN: Code duplicated, block: B:57:0x0119  */
    /* JADX WARN: Code duplicated, block: B:62:0x012f  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ad A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x00b8 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:74:0x00aa A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:75:0x00b5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:76:0x00de A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:77:0x00ea A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:78:0x00f6 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:79:0x0122 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x00c0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:81:0x00d0 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:82:0x0103 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:83:0x0113 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:84:0x0127 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:85:0x00fb A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:86:0x00ee A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:87:0x00e2 A[SYNTHETIC] */
    /* JADX INFO: renamed from: a */
    public static C0608e m451a(C0581aa c0581aa) {
        int iM137a;
        int iM137a2;
        String strTrim;
        String strTrim2;
        int iM321a = c0581aa.m321a();
        boolean z = true;
        String str = null;
        boolean z2 = false;
        boolean z3 = false;
        int iM142b = -1;
        int iM142b2 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int iM142b3 = -1;
        int iM142b4 = -1;
        boolean z7 = false;
        boolean z8 = false;
        for (int i = 0; i < iM321a; i++) {
            String strM322a = c0581aa.m322a(i);
            String strM325b = c0581aa.m325b(i);
            if (!strM322a.equalsIgnoreCase("Cache-Control")) {
                if (strM322a.equalsIgnoreCase("Pragma")) {
                }
            } else {
                if (str == null) {
                    str = strM325b;
                }
                iM137a = 0;
                while (iM137a < strM325b.length()) {
                    iM137a2 = C0509f.m137a(strM325b, iM137a, "=,;");
                    strTrim = strM325b.substring(iM137a, iM137a2).trim();
                    if (iM137a2 != strM325b.length() || strM325b.charAt(iM137a2) == ',' || strM325b.charAt(iM137a2) == ';') {
                        iM137a = iM137a2 + 1;
                        strTrim2 = null;
                    } else {
                        int iM136a = C0509f.m136a(strM325b, iM137a2 + 1);
                        if (iM136a < strM325b.length() && strM325b.charAt(iM136a) == '\"') {
                            int i2 = iM136a + 1;
                            int iM137a3 = C0509f.m137a(strM325b, i2, "\"");
                            strTrim2 = strM325b.substring(i2, iM137a3);
                            iM137a = iM137a3 + 1;
                        } else {
                            iM137a = C0509f.m137a(strM325b, iM136a, ",;");
                            strTrim2 = strM325b.substring(iM136a, iM137a).trim();
                        }
                    }
                    if ("no-cache".equalsIgnoreCase(strTrim)) {
                        z2 = true;
                    } else if ("no-store".equalsIgnoreCase(strTrim)) {
                        z3 = true;
                    } else if ("max-age".equalsIgnoreCase(strTrim)) {
                        iM142b = C0509f.m142b(strTrim2, -1);
                    } else if ("s-maxage".equalsIgnoreCase(strTrim)) {
                        iM142b2 = C0509f.m142b(strTrim2, -1);
                    } else if ("private".equalsIgnoreCase(strTrim)) {
                        z4 = true;
                    } else if ("public".equalsIgnoreCase(strTrim)) {
                        z5 = true;
                    } else if ("must-revalidate".equalsIgnoreCase(strTrim)) {
                        z6 = true;
                    } else if ("max-stale".equalsIgnoreCase(strTrim)) {
                        iM142b3 = C0509f.m142b(strTrim2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                    } else if ("min-fresh".equalsIgnoreCase(strTrim)) {
                        iM142b4 = C0509f.m142b(strTrim2, -1);
                    } else if ("only-if-cached".equalsIgnoreCase(strTrim)) {
                        z7 = true;
                    } else if ("no-transform".equalsIgnoreCase(strTrim)) {
                        z8 = true;
                    }
                }
            }
            z = false;
            iM137a = 0;
            while (iM137a < strM325b.length()) {
                iM137a2 = C0509f.m137a(strM325b, iM137a, "=,;");
                strTrim = strM325b.substring(iM137a, iM137a2).trim();
                if (iM137a2 != strM325b.length()) {
                    iM137a = iM137a2 + 1;
                    strTrim2 = null;
                } else {
                    iM137a = iM137a2 + 1;
                    strTrim2 = null;
                }
                if ("no-cache".equalsIgnoreCase(strTrim)) {
                    z2 = true;
                } else if ("no-store".equalsIgnoreCase(strTrim)) {
                    z3 = true;
                } else if ("max-age".equalsIgnoreCase(strTrim)) {
                    iM142b = C0509f.m142b(strTrim2, -1);
                } else if ("s-maxage".equalsIgnoreCase(strTrim)) {
                    iM142b2 = C0509f.m142b(strTrim2, -1);
                } else if ("private".equalsIgnoreCase(strTrim)) {
                    z4 = true;
                } else if ("public".equalsIgnoreCase(strTrim)) {
                    z5 = true;
                } else if ("must-revalidate".equalsIgnoreCase(strTrim)) {
                    z6 = true;
                } else if ("max-stale".equalsIgnoreCase(strTrim)) {
                    iM142b3 = C0509f.m142b(strTrim2, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
                } else if ("min-fresh".equalsIgnoreCase(strTrim)) {
                    iM142b4 = C0509f.m142b(strTrim2, -1);
                } else if ("only-if-cached".equalsIgnoreCase(strTrim)) {
                    z7 = true;
                } else if ("no-transform".equalsIgnoreCase(strTrim)) {
                    z8 = true;
                }
            }
        }
        return new C0608e(z2, z3, iM142b, iM142b2, z4, z5, z6, iM142b3, iM142b4, z7, z8, !z ? null : str);
    }

    public final String toString() {
        String string;
        String str = this.f606c;
        if (str != null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (this.f607d) {
            sb.append("no-cache, ");
        }
        if (this.f608e) {
            sb.append("no-store, ");
        }
        if (this.f609f != -1) {
            sb.append("max-age=");
            sb.append(this.f609f);
            sb.append(", ");
        }
        if (this.f610g != -1) {
            sb.append("s-maxage=");
            sb.append(this.f610g);
            sb.append(", ");
        }
        if (this.f611h) {
            sb.append("private, ");
        }
        if (this.f612i) {
            sb.append("public, ");
        }
        if (this.f613j) {
            sb.append("must-revalidate, ");
        }
        if (this.f614k != -1) {
            sb.append("max-stale=");
            sb.append(this.f614k);
            sb.append(", ");
        }
        if (this.f615l != -1) {
            sb.append("min-fresh=");
            sb.append(this.f615l);
            sb.append(", ");
        }
        if (this.f616m) {
            sb.append("only-if-cached, ");
        }
        if (this.f617n) {
            sb.append("no-transform, ");
        }
        if (sb.length() == 0) {
            string = "";
        } else {
            sb.delete(sb.length() - 2, sb.length());
            string = sb.toString();
        }
        this.f606c = string;
        return string;
    }
}

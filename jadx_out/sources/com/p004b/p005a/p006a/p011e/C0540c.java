package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0639i;
import okhttp366.internal.http2.Header;

/* JADX INFO: renamed from: com.b.a.a.e.c */
/* JADX INFO: compiled from: Header.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0540c {

    /* JADX INFO: renamed from: a */
    public static final C0639i f288a = C0639i.m575a(":");

    /* JADX INFO: renamed from: b */
    public static final C0639i f289b = C0639i.m575a(Header.RESPONSE_STATUS_UTF8);

    /* JADX INFO: renamed from: c */
    public static final C0639i f290c = C0639i.m575a(Header.TARGET_METHOD_UTF8);

    /* JADX INFO: renamed from: d */
    public static final C0639i f291d = C0639i.m575a(Header.TARGET_PATH_UTF8);

    /* JADX INFO: renamed from: e */
    public static final C0639i f292e = C0639i.m575a(Header.TARGET_SCHEME_UTF8);

    /* JADX INFO: renamed from: f */
    public static final C0639i f293f = C0639i.m575a(Header.TARGET_AUTHORITY_UTF8);

    /* JADX INFO: renamed from: g */
    public final C0639i f294g;

    /* JADX INFO: renamed from: h */
    public final C0639i f295h;

    /* JADX INFO: renamed from: i */
    final int f296i;

    public C0540c(String str, String str2) {
        this(C0639i.m575a(str), C0639i.m575a(str2));
    }

    public C0540c(C0639i c0639i, String str) {
        this(c0639i, C0639i.m575a(str));
    }

    public C0540c(C0639i c0639i, C0639i c0639i2) {
        this.f294g = c0639i;
        this.f295h = c0639i2;
        this.f296i = c0639i.mo590g() + 32 + c0639i2.mo590g();
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0540c)) {
            return false;
        }
        C0540c c0540c = (C0540c) obj;
        return this.f294g.equals(c0540c.f294g) && this.f295h.equals(c0540c.f295h);
    }

    public final int hashCode() {
        return ((this.f294g.hashCode() + 527) * 31) + this.f295h.hashCode();
    }

    public final String toString() {
        return C0503c.m110a("%s: %s", this.f294g.mo581a(), this.f295h.mo581a());
    }
}

package com.p004b.p005a;

import com.p004b.p005a.p006a.p009c.C0510g;

/* JADX INFO: renamed from: com.b.a.ap */
/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0596ap {

    /* JADX INFO: renamed from: a */
    C0583ac f554a;

    /* JADX INFO: renamed from: b */
    String f555b;

    /* JADX INFO: renamed from: c */
    C0582ab f556c;

    /* JADX INFO: renamed from: d */
    AbstractC0597aq f557d;

    /* JADX INFO: renamed from: e */
    Object f558e;

    public C0596ap() {
        this.f555b = "GET";
        this.f556c = new C0582ab();
    }

    C0596ap(C0595ao c0595ao) {
        this.f554a = c0595ao.f548a;
        this.f555b = c0595ao.f549b;
        this.f557d = c0595ao.f551d;
        this.f558e = c0595ao.f552e;
        this.f556c = c0595ao.f550c.m324b();
    }

    /* JADX INFO: renamed from: a */
    public final C0596ap m408a(C0583ac c0583ac) {
        if (c0583ac == null) {
            throw new NullPointerException("url == null");
        }
        this.f554a = c0583ac;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0596ap m410a(String str) {
        if (str == null) {
            throw new NullPointerException("url == null");
        }
        if (str.regionMatches(true, 0, "ws:", 0, 3)) {
            str = "http:" + str.substring(3);
        } else if (str.regionMatches(true, 0, "wss:", 0, 4)) {
            str = "https:" + str.substring(4);
        }
        C0583ac c0583acM343e = C0583ac.m343e(str);
        if (c0583acM343e == null) {
            throw new IllegalArgumentException("unexpected url: " + str);
        }
        return m408a(c0583acM343e);
    }

    /* JADX INFO: renamed from: a */
    public final C0596ap m412a(String str, String str2) {
        this.f556c.m331c(str, str2);
        return this;
    }

    /* JADX INFO: renamed from: b */
    public final C0596ap m413b(String str) {
        this.f556c.m328a(str);
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0596ap m407a(C0581aa c0581aa) {
        this.f556c = c0581aa.m324b();
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0596ap m409a(AbstractC0597aq abstractC0597aq) {
        return m411a("POST", abstractC0597aq);
    }

    /* JADX INFO: renamed from: a */
    public final C0596ap m411a(String str, AbstractC0597aq abstractC0597aq) {
        if (str == null) {
            throw new NullPointerException("method == null");
        }
        if (str.length() == 0) {
            throw new IllegalArgumentException("method.length() == 0");
        }
        if (abstractC0597aq != null && !C0510g.m145b(str)) {
            throw new IllegalArgumentException("method " + str + " must not have a request body.");
        }
        if (abstractC0597aq == null && C0510g.m144a(str)) {
            throw new IllegalArgumentException("method " + str + " must have a request body.");
        }
        this.f555b = str;
        this.f557d = abstractC0597aq;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0595ao m406a() {
        if (this.f554a == null) {
            throw new IllegalStateException("url == null");
        }
        return new C0595ao(this);
    }
}

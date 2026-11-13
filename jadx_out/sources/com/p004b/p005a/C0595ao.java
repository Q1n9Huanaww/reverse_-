package com.p004b.p005a;

/* JADX INFO: renamed from: com.b.a.ao */
/* JADX INFO: compiled from: Request.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0595ao {

    /* JADX INFO: renamed from: a */
    final C0583ac f548a;

    /* JADX INFO: renamed from: b */
    final String f549b;

    /* JADX INFO: renamed from: c */
    final C0581aa f550c;

    /* JADX INFO: renamed from: d */
    final AbstractC0597aq f551d;

    /* JADX INFO: renamed from: e */
    final Object f552e;

    /* JADX INFO: renamed from: f */
    private volatile C0608e f553f;

    C0595ao(C0596ap c0596ap) {
        this.f548a = c0596ap.f554a;
        this.f549b = c0596ap.f555b;
        this.f550c = c0596ap.f556c.m327a();
        this.f551d = c0596ap.f557d;
        this.f552e = c0596ap.f558e != null ? c0596ap.f558e : this;
    }

    /* JADX INFO: renamed from: a */
    public final C0583ac m398a() {
        return this.f548a;
    }

    /* JADX INFO: renamed from: b */
    public final String m400b() {
        return this.f549b;
    }

    /* JADX INFO: renamed from: c */
    public final C0581aa m401c() {
        return this.f550c;
    }

    /* JADX INFO: renamed from: a */
    public final String m399a(String str) {
        return this.f550c.m323a(str);
    }

    /* JADX INFO: renamed from: d */
    public final AbstractC0597aq m402d() {
        return this.f551d;
    }

    /* JADX INFO: renamed from: e */
    public final C0596ap m403e() {
        return new C0596ap(this);
    }

    /* JADX INFO: renamed from: f */
    public final C0608e m404f() {
        C0608e c0608e = this.f553f;
        if (c0608e != null) {
            return c0608e;
        }
        C0608e c0608eM451a = C0608e.m451a(this.f550c);
        this.f553f = c0608eM451a;
        return c0608eM451a;
    }

    /* JADX INFO: renamed from: g */
    public final boolean m405g() {
        return this.f548a.m347c();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Request{method=");
        sb.append(this.f549b);
        sb.append(", url=");
        sb.append(this.f548a);
        sb.append(", tag=");
        sb.append(this.f552e != this ? this.f552e : null);
        sb.append('}');
        return sb.toString();
    }
}

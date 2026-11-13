package com.p004b.p005a;

/* JADX INFO: renamed from: com.b.a.at */
/* JADX INFO: compiled from: Response.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0600at {

    /* JADX INFO: renamed from: a */
    C0595ao f576a;

    /* JADX INFO: renamed from: b */
    EnumC0592al f577b;

    /* JADX INFO: renamed from: c */
    int f578c;

    /* JADX INFO: renamed from: d */
    String f579d;

    /* JADX INFO: renamed from: e */
    C0629z f580e;

    /* JADX INFO: renamed from: f */
    C0582ab f581f;

    /* JADX INFO: renamed from: g */
    AbstractC0601au f582g;

    /* JADX INFO: renamed from: h */
    C0599as f583h;

    /* JADX INFO: renamed from: i */
    C0599as f584i;

    /* JADX INFO: renamed from: j */
    C0599as f585j;

    /* JADX INFO: renamed from: k */
    long f586k;

    /* JADX INFO: renamed from: l */
    long f587l;

    public C0600at() {
        this.f578c = -1;
        this.f581f = new C0582ab();
    }

    C0600at(C0599as c0599as) {
        this.f578c = -1;
        this.f576a = c0599as.f563a;
        this.f577b = c0599as.f564b;
        this.f578c = c0599as.f565c;
        this.f579d = c0599as.f566d;
        this.f580e = c0599as.f567e;
        this.f581f = c0599as.f568f.m324b();
        this.f582g = c0599as.f569g;
        this.f583h = c0599as.f570h;
        this.f584i = c0599as.f571i;
        this.f585j = c0599as.f572j;
        this.f586k = c0599as.f573k;
        this.f587l = c0599as.f574l;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m434a(C0595ao c0595ao) {
        this.f576a = c0595ao;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m433a(EnumC0592al enumC0592al) {
        this.f577b = enumC0592al;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m430a(int i) {
        this.f578c = i;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m438a(String str) {
        this.f579d = str;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m437a(C0629z c0629z) {
        this.f580e = c0629z;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m439a(String str, String str2) {
        this.f581f.m329a(str, str2);
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m432a(C0581aa c0581aa) {
        this.f581f = c0581aa.m324b();
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m436a(AbstractC0601au abstractC0601au) {
        this.f582g = abstractC0601au;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m435a(C0599as c0599as) {
        if (c0599as != null) {
            m428a("networkResponse", c0599as);
        }
        this.f583h = c0599as;
        return this;
    }

    /* JADX INFO: renamed from: b */
    public final C0600at m441b(C0599as c0599as) {
        if (c0599as != null) {
            m428a("cacheResponse", c0599as);
        }
        this.f584i = c0599as;
        return this;
    }

    /* JADX INFO: renamed from: a */
    private static void m428a(String str, C0599as c0599as) {
        if (c0599as.f569g != null) {
            throw new IllegalArgumentException(str + ".body != null");
        }
        if (c0599as.f570h != null) {
            throw new IllegalArgumentException(str + ".networkResponse != null");
        }
        if (c0599as.f571i != null) {
            throw new IllegalArgumentException(str + ".cacheResponse != null");
        }
        if (c0599as.f572j != null) {
            throw new IllegalArgumentException(str + ".priorResponse != null");
        }
    }

    /* JADX INFO: renamed from: a */
    public final C0600at m431a(long j) {
        this.f586k = j;
        return this;
    }

    /* JADX INFO: renamed from: b */
    public final C0600at m440b(long j) {
        this.f587l = j;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0599as m429a() {
        if (this.f576a == null) {
            throw new IllegalStateException("request == null");
        }
        if (this.f577b == null) {
            throw new IllegalStateException("protocol == null");
        }
        if (this.f578c >= 0) {
            return new C0599as(this);
        }
        throw new IllegalStateException("code < 0: " + this.f578c);
    }

    /* JADX INFO: renamed from: c */
    public final C0600at m442c(C0599as c0599as) {
        if (c0599as == null || c0599as.f569g == null) {
            this.f585j = c0599as;
            return this;
        }
        throw new IllegalArgumentException("priorResponse.body != null");
    }
}

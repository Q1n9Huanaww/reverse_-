package com.p004b.p005a;

/* JADX INFO: renamed from: com.b.a.q */
/* JADX INFO: compiled from: ConnectionSpec.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0620q {

    /* JADX INFO: renamed from: a */
    boolean f764a;

    /* JADX INFO: renamed from: b */
    String[] f765b;

    /* JADX INFO: renamed from: c */
    String[] f766c;

    /* JADX INFO: renamed from: d */
    boolean f767d;

    C0620q(boolean z) {
        this.f764a = z;
    }

    public C0620q(C0619p c0619p) {
        this.f764a = c0619p.f760d;
        this.f765b = c0619p.f762f;
        this.f766c = c0619p.f763g;
        this.f767d = c0619p.f761e;
    }

    /* JADX INFO: renamed from: a */
    public final C0620q m481a(String... strArr) {
        if (!this.f764a) {
            throw new IllegalStateException("no cipher suites for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one cipher suite is required");
        }
        this.f765b = (String[]) strArr.clone();
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0620q m480a(EnumC0604ax... enumC0604axArr) {
        if (!this.f764a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        String[] strArr = new String[enumC0604axArr.length];
        for (int i = 0; i < enumC0604axArr.length; i++) {
            strArr[i] = enumC0604axArr[i].f600f;
        }
        return m483b(strArr);
    }

    /* JADX INFO: renamed from: b */
    public final C0620q m483b(String... strArr) {
        if (!this.f764a) {
            throw new IllegalStateException("no TLS versions for cleartext connections");
        }
        if (strArr.length == 0) {
            throw new IllegalArgumentException("At least one TLS version is required");
        }
        this.f766c = (String[]) strArr.clone();
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final C0620q m479a() {
        if (!this.f764a) {
            throw new IllegalStateException("no TLS extensions for cleartext connections");
        }
        this.f767d = true;
        return this;
    }

    /* JADX INFO: renamed from: b */
    public final C0619p m482b() {
        return new C0619p(this);
    }
}

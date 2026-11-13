package com.umeng.commonsdk.statistics.idtracking;

import android.support.v4.os.EnvironmentCompat;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.statistics.proto.C1852a;
import com.umeng.commonsdk.statistics.proto.C1853b;
import com.umeng.commonsdk.statistics.proto.C1854c;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.a */
/* JADX INFO: compiled from: AbstractIdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private final int f4545a = 10;

    /* JADX INFO: renamed from: b */
    private final int f4546b = 20;

    /* JADX INFO: renamed from: c */
    private final String f4547c;

    /* JADX INFO: renamed from: d */
    private List<C1852a> f4548d;

    /* JADX INFO: renamed from: e */
    private C1853b f4549e;

    /* JADX INFO: renamed from: f */
    public abstract String mo4443f();

    public AbstractC1820a(String str) {
        this.f4547c = str;
    }

    /* JADX INFO: renamed from: a */
    public boolean m4438a() {
        return m4433g();
    }

    /* JADX INFO: renamed from: b */
    public String m4439b() {
        return this.f4547c;
    }

    /* JADX INFO: renamed from: c */
    public boolean m4440c() {
        return this.f4549e == null || this.f4549e.m4572h() <= 20;
    }

    /* JADX INFO: renamed from: g */
    private boolean m4433g() {
        C1853b c1853b = this.f4549e;
        String strM4564b = c1853b == null ? null : c1853b.m4564b();
        int iM4572h = c1853b == null ? 0 : c1853b.m4572h();
        String strM4434a = m4434a(mo4443f());
        if (strM4434a == null || strM4434a.equals(strM4564b)) {
            return false;
        }
        if (c1853b == null) {
            c1853b = new C1853b();
        }
        c1853b.m4561a(strM4434a);
        c1853b.m4560a(System.currentTimeMillis());
        c1853b.m4559a(iM4572h + 1);
        C1852a c1852a = new C1852a();
        c1852a.m4523a(this.f4547c);
        c1852a.m4528c(strM4434a);
        c1852a.m4525b(strM4564b);
        c1852a.m4522a(c1853b.m4569e());
        if (this.f4548d == null) {
            this.f4548d = new ArrayList(2);
        }
        this.f4548d.add(c1852a);
        if (this.f4548d.size() > 10) {
            this.f4548d.remove(0);
        }
        this.f4549e = c1853b;
        return true;
    }

    /* JADX INFO: renamed from: d */
    public C1853b m4441d() {
        return this.f4549e;
    }

    /* JADX INFO: renamed from: a */
    public void m4435a(C1853b c1853b) {
        this.f4549e = c1853b;
    }

    /* JADX INFO: renamed from: e */
    public List<C1852a> m4442e() {
        return this.f4548d;
    }

    /* JADX INFO: renamed from: a */
    public void m4437a(List<C1852a> list) {
        this.f4548d = list;
    }

    /* JADX INFO: renamed from: a */
    public String m4434a(String str) {
        if (str == null) {
            return null;
        }
        String strTrim = str.trim();
        if (strTrim.length() == 0 || AccountManager.TYPE_LOGIN.equals(strTrim) || EnvironmentCompat.MEDIA_UNKNOWN.equals(strTrim.toLowerCase(Locale.US))) {
            return null;
        }
        return strTrim;
    }

    /* JADX INFO: renamed from: a */
    public void m4436a(C1854c c1854c) {
        this.f4549e = c1854c.m4601c().get(this.f4547c);
        List<C1852a> listM4607h = c1854c.m4607h();
        if (listM4607h == null || listM4607h.size() <= 0) {
            return;
        }
        if (this.f4548d == null) {
            this.f4548d = new ArrayList();
        }
        for (C1852a c1852a : listM4607h) {
            if (this.f4547c.equals(c1852a.f4642a)) {
                this.f4548d.add(c1852a);
            }
        }
    }
}

package com.p004b.p005a.p006a.p007a;

import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0582ab;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0600at;
import com.p004b.p005a.C0608e;
import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.p009c.C0507d;
import com.p004b.p005a.p006a.p009c.C0509f;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.a.a.e */
/* JADX INFO: compiled from: CacheStrategy.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0489e {

    /* JADX INFO: renamed from: a */
    final long f90a;

    /* JADX INFO: renamed from: b */
    final C0595ao f91b;

    /* JADX INFO: renamed from: c */
    final C0599as f92c;

    /* JADX INFO: renamed from: d */
    private Date f93d;

    /* JADX INFO: renamed from: e */
    private String f94e;

    /* JADX INFO: renamed from: f */
    private Date f95f;

    /* JADX INFO: renamed from: g */
    private String f96g;

    /* JADX INFO: renamed from: h */
    private Date f97h;

    /* JADX INFO: renamed from: i */
    private long f98i;

    /* JADX INFO: renamed from: j */
    private long f99j;

    /* JADX INFO: renamed from: k */
    private String f100k;

    /* JADX INFO: renamed from: l */
    private int f101l;

    public C0489e(long j, C0595ao c0595ao, C0599as c0599as) {
        this.f101l = -1;
        this.f90a = j;
        this.f91b = c0595ao;
        this.f92c = c0599as;
        if (c0599as != null) {
            this.f98i = c0599as.m426h();
            this.f99j = c0599as.m427i();
            C0581aa c0581aaM422d = c0599as.m422d();
            int iM321a = c0581aaM422d.m321a();
            for (int i = 0; i < iM321a; i++) {
                String strM322a = c0581aaM422d.m322a(i);
                String strM325b = c0581aaM422d.m325b(i);
                if ("Date".equalsIgnoreCase(strM322a)) {
                    this.f93d = C0507d.m135a(strM325b);
                    this.f94e = strM325b;
                } else if ("Expires".equalsIgnoreCase(strM322a)) {
                    this.f97h = C0507d.m135a(strM325b);
                } else if ("Last-Modified".equalsIgnoreCase(strM322a)) {
                    this.f95f = C0507d.m135a(strM325b);
                    this.f96g = strM325b;
                } else if ("ETag".equalsIgnoreCase(strM322a)) {
                    this.f100k = strM325b;
                } else if ("Age".equalsIgnoreCase(strM322a)) {
                    this.f101l = C0509f.m142b(strM325b, -1);
                }
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:54:0x0103  */
    /* JADX WARN: Code duplicated, block: B:85:0x0197  */
    /* JADX WARN: Code duplicated, block: B:87:0x019b  */
    /* JADX WARN: Code duplicated, block: B:88:0x01a0  */
    /* JADX WARN: Code duplicated, block: B:90:0x01a4  */
    /* JADX WARN: Code duplicated, block: B:91:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:93:0x01ad  */
    /* JADX WARN: Code duplicated, block: B:96:0x01db  */
    /* JADX WARN: Code duplicated, block: B:97:0x01e3  */
    /* JADX INFO: renamed from: a */
    public final C0488d m51a() {
        C0488d c0488d;
        long jMin;
        String str;
        String str2;
        C0488d c0488d2;
        if (this.f92c == null) {
            c0488d = new C0488d(this.f91b, null);
        } else if ((this.f91b.m405g() && this.f92c.m421c() == null) || !C0488d.m50a(this.f92c, this.f91b)) {
            c0488d = new C0488d(this.f91b, null);
        } else {
            C0608e c0608eM404f = this.f91b.m404f();
            if (c0608eM404f.m452a()) {
                c0488d = new C0488d(this.f91b, null);
            } else {
                C0595ao c0595ao = this.f91b;
                if ((c0595ao.m399a("If-Modified-Since") == null && c0595ao.m399a("If-None-Match") == null) ? false : true) {
                    c0488d = new C0488d(this.f91b, null);
                } else {
                    long millis = 0;
                    long jMax = this.f93d != null ? Math.max(0L, this.f99j - this.f93d.getTime()) : 0L;
                    if (this.f101l != -1) {
                        jMax = Math.max(jMax, TimeUnit.SECONDS.toMillis(this.f101l));
                    }
                    long j = jMax + (this.f99j - this.f98i) + (this.f90a - this.f99j);
                    C0608e c0608eM425g = this.f92c.m425g();
                    if (c0608eM425g.m454c() != -1) {
                        jMin = TimeUnit.SECONDS.toMillis(c0608eM425g.m454c());
                    } else if (this.f97h != null) {
                        long time = this.f97h.getTime() - (this.f93d != null ? this.f93d.getTime() : this.f99j);
                        if (time > 0) {
                            jMin = time;
                        } else {
                            jMin = 0;
                        }
                    } else if (this.f95f == null || this.f92c.m418a().m398a().m356k() != null) {
                        jMin = 0;
                    } else {
                        long time2 = (this.f93d != null ? this.f93d.getTime() : this.f98i) - this.f95f.getTime();
                        if (time2 > 0) {
                            jMin = time2 / 10;
                        } else {
                            jMin = 0;
                        }
                    }
                    if (c0608eM404f.m454c() != -1) {
                        jMin = Math.min(jMin, TimeUnit.SECONDS.toMillis(c0608eM404f.m454c()));
                    }
                    long millis2 = c0608eM404f.m459h() != -1 ? TimeUnit.SECONDS.toMillis(c0608eM404f.m459h()) : 0L;
                    C0608e c0608eM425g2 = this.f92c.m425g();
                    if (!c0608eM425g2.m457f() && c0608eM404f.m458g() != -1) {
                        millis = TimeUnit.SECONDS.toMillis(c0608eM404f.m458g());
                    }
                    if (c0608eM425g2.m452a()) {
                        if (this.f100k != null) {
                            str = "If-None-Match";
                            str2 = this.f100k;
                        } else if (this.f95f != null) {
                            str = "If-Modified-Since";
                            str2 = this.f96g;
                        } else if (this.f93d != null) {
                            str = "If-Modified-Since";
                            str2 = this.f94e;
                        } else {
                            c0488d = new C0488d(this.f91b, null);
                        }
                        C0582ab c0582abM324b = this.f91b.m401c().m324b();
                        AbstractC0484a.f81a.mo39a(c0582abM324b, str, str2);
                        c0488d2 = new C0488d(this.f91b.m403e().m407a(c0582abM324b.m327a()).m406a(), this.f92c);
                        c0488d = c0488d2;
                    } else {
                        long j2 = j + millis2;
                        if (j2 < jMin + millis) {
                            C0600at c0600atM424f = this.f92c.m424f();
                            if (j2 >= jMin) {
                                c0600atM424f.m439a("Warning", "110 HttpURLConnection \"Response is stale\"");
                            }
                            if (j > 86400000) {
                                if (this.f92c.m425g().m454c() == -1 && this.f97h == null) {
                                    c0600atM424f.m439a("Warning", "113 HttpURLConnection \"Heuristic expiration\"");
                                }
                            }
                            c0488d2 = new C0488d(null, c0600atM424f.m429a());
                        } else {
                            if (this.f100k != null) {
                                str = "If-None-Match";
                                str2 = this.f100k;
                            } else if (this.f95f != null) {
                                str = "If-Modified-Since";
                                str2 = this.f96g;
                            } else if (this.f93d != null) {
                                str = "If-Modified-Since";
                                str2 = this.f94e;
                            } else {
                                c0488d = new C0488d(this.f91b, null);
                            }
                            C0582ab c0582abM324b2 = this.f91b.m401c().m324b();
                            AbstractC0484a.f81a.mo39a(c0582abM324b2, str, str2);
                            c0488d2 = new C0488d(this.f91b.m403e().m407a(c0582abM324b2.m327a()).m406a(), this.f92c);
                        }
                        c0488d = c0488d2;
                    }
                }
            }
        }
        return (c0488d.f88a == null || !this.f91b.m404f().m460i()) ? c0488d : new C0488d(null, null);
    }
}

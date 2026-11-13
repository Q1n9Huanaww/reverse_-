package com.iflytek.voiceads.download.p071d;

import com.iflytek.voiceads.download.p066a.InterfaceC1528a;
import com.iflytek.voiceads.download.p072e.C1545a;
import java.io.Serializable;
import java.util.List;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.d.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1542a implements Serializable {

    /* JADX INFO: renamed from: a */
    private InterfaceC1528a f3260a;

    /* JADX INFO: renamed from: b */
    private C1545a f3261b;

    /* JADX INFO: renamed from: c */
    private int f3262c;

    /* JADX INFO: renamed from: d */
    private String f3263d;

    /* JADX INFO: renamed from: e */
    private String f3264e;

    /* JADX INFO: renamed from: f */
    private long f3265f;

    /* JADX INFO: renamed from: g */
    private long f3266g;

    /* JADX INFO: renamed from: h */
    private int f3267h;

    /* JADX INFO: renamed from: i */
    private int f3268i;

    /* JADX INFO: renamed from: j */
    private List<C1543b> f3269j;

    /* JADX INFO: renamed from: com.iflytek.voiceads.download.d.a$a */
    public static final class a {

        /* JADX INFO: renamed from: a */
        private String f3270a;

        /* JADX INFO: renamed from: b */
        private String f3271b;

        /* JADX INFO: renamed from: a */
        public a m3024a(String str) {
            this.f3270a = str;
            return this;
        }

        /* JADX INFO: renamed from: a */
        public C1542a m3025a() {
            C1542a c1542a = new C1542a();
            c1542a.m3008a(this.f3270a);
            c1542a.m3014b(this.f3271b);
            c1542a.m3012b(Math.abs(this.f3270a.hashCode()));
            return c1542a;
        }

        /* JADX INFO: renamed from: b */
        public a m3026b(String str) {
            this.f3271b = str;
            return this;
        }
    }

    /* JADX INFO: renamed from: a */
    public C1545a m3003a() {
        return this.f3261b;
    }

    /* JADX INFO: renamed from: a */
    public void m3004a(int i) {
        this.f3267h = i;
    }

    /* JADX INFO: renamed from: a */
    public void m3005a(long j) {
        this.f3265f = j;
    }

    /* JADX INFO: renamed from: a */
    public void m3006a(InterfaceC1528a interfaceC1528a) {
        this.f3260a = interfaceC1528a;
    }

    /* JADX INFO: renamed from: a */
    public void m3007a(C1545a c1545a) {
        this.f3261b = c1545a;
    }

    /* JADX INFO: renamed from: a */
    public void m3008a(String str) {
        this.f3263d = str;
    }

    /* JADX INFO: renamed from: a */
    public void m3009a(List<C1543b> list) {
        this.f3269j = list;
    }

    /* JADX INFO: renamed from: a */
    public void m3010a(boolean z) {
        this.f3268i = z ? 0 : 1;
    }

    /* JADX INFO: renamed from: b */
    public String m3011b() {
        return this.f3263d;
    }

    /* JADX INFO: renamed from: b */
    public void m3012b(int i) {
        this.f3262c = i;
    }

    /* JADX INFO: renamed from: b */
    public void m3013b(long j) {
        this.f3266g = j;
    }

    /* JADX INFO: renamed from: b */
    public void m3014b(String str) {
        this.f3264e = str;
    }

    /* JADX INFO: renamed from: c */
    public String m3015c() {
        return this.f3264e;
    }

    /* JADX INFO: renamed from: d */
    public long m3016d() {
        return this.f3265f;
    }

    /* JADX INFO: renamed from: e */
    public long m3017e() {
        return this.f3266g;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.f3262c == ((C1542a) obj).f3262c;
    }

    /* JADX INFO: renamed from: f */
    public InterfaceC1528a m3018f() {
        return this.f3260a;
    }

    /* JADX INFO: renamed from: g */
    public int m3019g() {
        return this.f3267h;
    }

    /* JADX INFO: renamed from: h */
    public int m3020h() {
        return this.f3262c;
    }

    public int hashCode() {
        return this.f3262c;
    }

    /* JADX INFO: renamed from: i */
    public boolean m3021i() {
        return this.f3268i == 0;
    }

    /* JADX INFO: renamed from: j */
    public List<C1543b> m3022j() {
        return this.f3269j;
    }

    /* JADX INFO: renamed from: k */
    public boolean m3023k() {
        return this.f3267h == 5;
    }
}

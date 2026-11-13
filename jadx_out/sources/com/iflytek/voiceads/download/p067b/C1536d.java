package com.iflytek.voiceads.download.p067b;

import com.iflytek.voiceads.download.p067b.p068a.RunnableC1532a;
import com.iflytek.voiceads.download.p067b.p069b.RunnableC1534a;
import com.iflytek.voiceads.download.p071d.C1542a;
import com.iflytek.voiceads.download.p071d.C1543b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.b.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1536d implements RunnableC1532a.a, RunnableC1534a.a {

    /* JADX INFO: renamed from: a */
    private ExecutorService f3219a;

    /* JADX INFO: renamed from: b */
    private InterfaceC1531a f3220b;

    /* JADX INFO: renamed from: c */
    private C1542a f3221c;

    /* JADX INFO: renamed from: e */
    private a f3223e;

    /* JADX INFO: renamed from: g */
    private long f3225g;

    /* JADX INFO: renamed from: f */
    private long f3224f = System.currentTimeMillis();

    /* JADX INFO: renamed from: h */
    private volatile AtomicBoolean f3226h = new AtomicBoolean(false);

    /* JADX INFO: renamed from: d */
    private List<RunnableC1534a> f3222d = new ArrayList();

    /* JADX INFO: renamed from: com.iflytek.voiceads.download.b.d$a */
    public interface a {
        /* JADX INFO: renamed from: c */
        void mo2942c(C1542a c1542a);

        /* JADX INFO: renamed from: d */
        void mo2943d(C1542a c1542a);
    }

    public C1536d(ExecutorService executorService, InterfaceC1531a interfaceC1531a, C1542a c1542a, a aVar) {
        this.f3219a = executorService;
        this.f3220b = interfaceC1531a;
        this.f3221c = c1542a;
        this.f3223e = aVar;
    }

    /* JADX INFO: renamed from: f */
    private void m2958f() {
        this.f3225g = 0L;
        Iterator<C1543b> it2 = this.f3221c.m3022j().iterator();
        while (it2.hasNext()) {
            this.f3225g += it2.next().m3031d();
        }
        this.f3221c.m3013b(this.f3225g);
    }

    /* JADX INFO: renamed from: a */
    public void m2959a() {
        if (this.f3221c.m3016d() <= 0) {
            this.f3219a.submit(new RunnableC1532a(this.f3220b, this.f3221c, this));
            return;
        }
        Iterator<C1543b> it2 = this.f3221c.m3022j().iterator();
        while (it2.hasNext()) {
            RunnableC1534a runnableC1534a = new RunnableC1534a(it2.next(), this.f3220b, this.f3221c, this);
            this.f3219a.submit(runnableC1534a);
            this.f3222d.add(runnableC1534a);
        }
        this.f3221c.m3004a(2);
        this.f3220b.mo2948a(this.f3221c);
    }

    @Override // com.iflytek.voiceads.download.p067b.p068a.RunnableC1532a.a
    /* JADX INFO: renamed from: a */
    public void mo2951a(long j, boolean z) {
        this.f3221c.m3010a(z);
        this.f3221c.m3005a(j);
        ArrayList arrayList = new ArrayList();
        if (z) {
            long jM3016d = this.f3221c.m3016d();
            long j2 = jM3016d / ((long) 2);
            int i = 0;
            while (i < 2) {
                long j3 = j2 * ((long) i);
                C1543b c1543b = new C1543b(this.f3221c.m3011b(), j3, i == 1 ? jM3016d : (j3 + j2) - 1);
                arrayList.add(c1543b);
                RunnableC1534a runnableC1534a = new RunnableC1534a(c1543b, this.f3220b, this.f3221c, this);
                this.f3219a.submit(runnableC1534a);
                this.f3222d.add(runnableC1534a);
                i++;
            }
        } else {
            C1543b c1543b2 = new C1543b(this.f3221c.m3011b(), 0L, this.f3221c.m3016d());
            arrayList.add(c1543b2);
            RunnableC1534a runnableC1534a2 = new RunnableC1534a(c1543b2, this.f3220b, this.f3221c, this);
            this.f3219a.submit(runnableC1534a2);
            this.f3222d.add(runnableC1534a2);
        }
        this.f3221c.m3009a(arrayList);
        this.f3221c.m3004a(2);
        this.f3220b.mo2948a(this.f3221c);
    }

    @Override // com.iflytek.voiceads.download.p067b.p068a.RunnableC1532a.a
    /* JADX INFO: renamed from: b */
    public void mo2952b() {
        this.f3223e.mo2943d(this.f3221c);
    }

    @Override // com.iflytek.voiceads.download.p067b.p069b.RunnableC1534a.a
    /* JADX INFO: renamed from: c */
    public void mo2955c() {
        if (this.f3226h.get()) {
            return;
        }
        synchronized (this) {
            if (!this.f3226h.get()) {
                this.f3226h.set(true);
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.f3224f > 1000) {
                    m2958f();
                    this.f3220b.mo2948a(this.f3221c);
                    this.f3224f = jCurrentTimeMillis;
                }
                this.f3226h.set(false);
            }
        }
    }

    @Override // com.iflytek.voiceads.download.p067b.p069b.RunnableC1534a.a
    /* JADX INFO: renamed from: d */
    public void mo2956d() {
        m2958f();
        if (this.f3221c.m3017e() == this.f3221c.m3016d()) {
            this.f3221c.m3004a(4);
            this.f3220b.mo2948a(this.f3221c);
            if (this.f3223e != null) {
                this.f3223e.mo2942c(this.f3221c);
            }
        }
    }

    @Override // com.iflytek.voiceads.download.p067b.p069b.RunnableC1534a.a
    /* JADX INFO: renamed from: e */
    public void mo2957e() {
        this.f3223e.mo2943d(this.f3221c);
    }
}

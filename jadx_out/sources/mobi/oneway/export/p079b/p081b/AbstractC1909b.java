package mobi.oneway.export.p079b.p081b;

import com.umeng.commonsdk.amap.UMAmapConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.p084e.C1928b;
import mobi.oneway.export.p087g.C1959m;

/* JADX INFO: renamed from: mobi.oneway.export.b.b.b */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1909b<T> implements InterfaceC1911d {

    /* JADX INFO: renamed from: a */
    protected T f4861a;

    /* JADX INFO: renamed from: b */
    protected AdType f4862b;

    /* JADX INFO: renamed from: c */
    private long f4863c;

    /* JADX INFO: renamed from: d */
    private Map<String, Boolean> f4864d;

    /* JADX INFO: renamed from: e */
    private Map<String, Boolean> f4865e;

    /* JADX INFO: renamed from: f */
    private volatile boolean f4866f;

    /* JADX INFO: renamed from: g */
    private Timer f4867g;

    /* JADX INFO: renamed from: h */
    private TimerTask f4868h;

    /* JADX INFO: renamed from: i */
    private boolean f4869i;

    public AbstractC1909b(AdType adType, List<String> list, long j) {
        this.f4863c = UMAmapConfig.AMAP_CACHE_WRITE_TIME;
        if (j != 0) {
            this.f4863c = j;
        }
        this.f4862b = adType;
        this.f4864d = new ConcurrentHashMap();
        this.f4865e = new ConcurrentHashMap();
        m4767a(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f */
    public void m4762f() {
        C1959m.m5043a((Object) "load ad timeout");
        this.f4866f = true;
        m4765i();
        C1928b.m4863a().m4870c(new Runnable() { // from class: mobi.oneway.export.b.b.b.1
            @Override // java.lang.Runnable
            public void run() {
                if (AbstractC1909b.this.m4763g()) {
                    AbstractC1909b.this.m4768a(true);
                    AbstractC1909b.this.mo4750a();
                } else {
                    AbstractC1909b.this.m4768a(false);
                    AbstractC1909b.this.mo4751a("load ad timeout");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: g */
    public boolean m4763g() {
        Iterator<Map.Entry<String, Boolean>> it2 = this.f4864d.entrySet().iterator();
        while (it2.hasNext()) {
            if (it2.next().getValue().booleanValue()) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: h */
    private boolean m4764h() {
        Iterator<Map.Entry<String, Boolean>> it2 = this.f4865e.entrySet().iterator();
        while (it2.hasNext()) {
            if (!it2.next().getValue().booleanValue()) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: i */
    private void m4765i() {
        if (this.f4868h != null) {
            this.f4868h.cancel();
            this.f4868h = null;
        }
        if (this.f4867g != null) {
            this.f4867g.cancel();
            this.f4867g = null;
        }
    }

    /* JADX INFO: renamed from: a */
    protected abstract void mo4750a();

    /* JADX INFO: renamed from: a */
    public void m4766a(T t) {
        this.f4861a = t;
    }

    /* JADX INFO: renamed from: a */
    protected abstract void mo4751a(String str);

    /* JADX INFO: renamed from: a */
    protected void m4767a(List<String> list) {
        if (list == null) {
            return;
        }
        for (String str : list) {
            this.f4864d.put(str, false);
            this.f4865e.put(str, false);
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m4768a(boolean z) {
        this.f4869i = z;
    }

    /* JADX INFO: renamed from: b */
    public boolean m4769b() {
        return this.f4869i;
    }

    /* JADX INFO: renamed from: c */
    public void mo4770c() {
        Iterator<Map.Entry<String, Boolean>> it2 = this.f4864d.entrySet().iterator();
        while (it2.hasNext()) {
            this.f4864d.put(it2.next().getKey(), false);
        }
        Iterator<Map.Entry<String, Boolean>> it3 = this.f4865e.entrySet().iterator();
        while (it3.hasNext()) {
            this.f4865e.put(it3.next().getKey(), false);
        }
        if (this.f4867g == null) {
            this.f4866f = false;
            this.f4868h = new TimerTask() { // from class: mobi.oneway.export.b.b.b.2
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    AbstractC1909b.this.m4762f();
                }
            };
            this.f4867g = new Timer();
            this.f4867g.schedule(this.f4868h, this.f4863c);
        }
    }

    /* JADX INFO: renamed from: d */
    protected boolean m4771d() {
        if (!m4764h() || this.f4866f) {
            return false;
        }
        m4765i();
        m4768a(true);
        return true;
    }

    /* JADX INFO: renamed from: e */
    protected boolean m4772e() {
        if (this.f4866f || !m4764h()) {
            return false;
        }
        m4765i();
        if (!m4763g()) {
            return true;
        }
        m4768a(true);
        mo4750a();
        return false;
    }

    /* JADX INFO: renamed from: f */
    protected void m4773f(String str) {
        this.f4864d.put(str, true);
        this.f4865e.put(str, true);
    }

    /* JADX INFO: renamed from: g */
    protected void m4774g(String str) {
        this.f4865e.put(str, true);
    }
}

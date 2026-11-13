package mobi.oneway.export.p085f.p086a;

import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p079b.p081b.InterfaceC1911d;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.C1943e;

/* JADX INFO: renamed from: mobi.oneway.export.f.a.a */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1932a {

    /* JADX INFO: renamed from: a */
    protected int f4956a;

    /* JADX INFO: renamed from: b */
    protected String f4957b;

    /* JADX INFO: renamed from: c */
    protected String f4958c;

    /* JADX INFO: renamed from: d */
    protected C1943e f4959d;

    /* JADX INFO: renamed from: e */
    protected InterfaceC1911d f4960e;

    public AbstractC1932a(C1931a c1931a) {
        this.f4956a = c1931a.m4879a();
        this.f4957b = c1931a.m4900f();
        this.f4958c = c1931a.m4901g();
        this.f4959d = new C1943e(mo4908c(), c1931a);
    }

    /* JADX INFO: renamed from: a */
    protected String m4902a() {
        return this.f4957b;
    }

    /* JADX INFO: renamed from: a */
    public void m4903a(InterfaceC1911d interfaceC1911d) {
        this.f4960e = interfaceC1911d;
    }

    /* JADX INFO: renamed from: a */
    protected void m4904a(EventType eventType) {
        this.f4959d.m4944a(eventType, null);
    }

    /* JADX INFO: renamed from: a */
    protected void m4905a(OnewaySdkError onewaySdkError, String str) {
        this.f4959d.m4945a(EventType.adFail, onewaySdkError, str);
    }

    /* JADX INFO: renamed from: b */
    protected String m4906b() {
        return this.f4958c;
    }

    /* JADX INFO: renamed from: b */
    public void m4907b(OnewaySdkError onewaySdkError, String str) {
        if (this.f4960e != null) {
            this.f4960e.mo4755a(this.f4957b, onewaySdkError, str);
        }
    }

    /* JADX INFO: renamed from: c */
    protected abstract AdType mo4908c();
}

package mobi.oneway.export.p079b.p081b;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import mobi.oneway.export.AdListener.feed.OWFeedAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewayAdCloseType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.feed.C1946a;
import mobi.oneway.export.feed.IFeedAd;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.C1942d;

/* JADX INFO: renamed from: mobi.oneway.export.b.b.c */
/* JADX INFO: loaded from: classes.dex */
public class C1910c extends AbstractC1909b<OWFeedAdListener> {

    /* JADX INFO: renamed from: c */
    private Map<String, List<IFeedAd>> f4872c;

    /* JADX INFO: renamed from: d */
    private List<String> f4873d;

    /* JADX INFO: renamed from: e */
    private List<C1931a> f4874e;

    public C1910c(AdType adType, List<C1931a> list, List<String> list2, long j) {
        super(adType, list2, j);
        this.f4872c = new HashMap();
        this.f4874e = list;
        this.f4873d = list2;
    }

    /* JADX INFO: renamed from: f */
    private List<IFeedAd> m4775f() {
        C1931a c1931aM4933a;
        List<IFeedAd> arrayList = new ArrayList<>();
        Iterator<String> it2 = this.f4873d.iterator();
        while (true) {
            if (!it2.hasNext()) {
                c1931aM4933a = null;
                break;
            }
            String next = it2.next();
            List<IFeedAd> list = this.f4872c.get(next);
            if (list != null) {
                c1931aM4933a = C1942d.m4933a(this.f4874e, next);
                arrayList = list;
                break;
            }
            arrayList = list;
        }
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, new C1946a(arrayList.get(i), c1931aM4933a));
        }
        return arrayList;
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: a */
    protected void mo4750a() {
        if (this.f4861a != 0) {
            ((OWFeedAdListener) this.f4861a).onAdLoad(m4775f());
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: a */
    protected void mo4751a(String str) {
        if (this.f4861a != 0) {
            ((OWFeedAdListener) this.f4861a).onError(OnewaySdkError.LOAD_ERROR, str);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4752a(String str, List<IFeedAd> list) {
        m4773f(str);
        this.f4872c.put(str, list);
        if (!m4771d() || this.f4861a == 0) {
            return;
        }
        ((OWFeedAdListener) this.f4861a).onAdLoad(m4775f());
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4753a(String str, OnewayAdCloseType onewayAdCloseType) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4754a(String str, OnewayAdCloseType onewayAdCloseType, String str2) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4755a(String str, OnewaySdkError onewaySdkError, String str2) {
        m4774g(str);
        if (!m4772e() || this.f4861a == 0) {
            return;
        }
        ((OWFeedAdListener) this.f4861a).onError(onewaySdkError, str2);
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: b */
    public void mo4756b(String str) {
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: c */
    public void mo4770c() {
        super.mo4770c();
        this.f4872c.clear();
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: c */
    public void mo4757c(String str) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: d */
    public void mo4758d(String str) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: e */
    public void mo4759e(String str) {
    }
}

package mobi.oneway.export.p085f.p086a;

import java.util.HashSet;
import java.util.Set;
import mobi.oneway.export.AdListener.feed.OWFeedAdEventListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.feed.IFeedAd;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.C1943e;

/* JADX INFO: renamed from: mobi.oneway.export.f.a.b */
/* JADX INFO: loaded from: classes.dex */
public class C1933b implements OWFeedAdEventListener {

    /* JADX INFO: renamed from: a */
    private static Set<String> f4961a = new HashSet();

    /* JADX INFO: renamed from: b */
    private static Set<String> f4962b = new HashSet();

    /* JADX INFO: renamed from: c */
    private C1943e f4963c;

    /* JADX INFO: renamed from: d */
    private OWFeedAdEventListener f4964d;

    /* JADX INFO: renamed from: e */
    private String f4965e;

    /* JADX INFO: renamed from: f */
    private String f4966f;

    public C1933b(C1931a c1931a, OWFeedAdEventListener oWFeedAdEventListener) {
        this.f4963c = new C1943e(AdType.feed, c1931a);
        this.f4964d = oWFeedAdEventListener;
        this.f4965e = c1931a.m4901g();
        this.f4966f = c1931a.m4900f();
    }

    @Override // mobi.oneway.export.AdListener.feed.OWFeedAdEventListener
    public void onClicked(IFeedAd iFeedAd) {
        String string = iFeedAd.toString();
        if (!f4962b.contains(string)) {
            this.f4963c.m4944a(EventType.click, null);
            f4962b.add(string);
        }
        if (this.f4964d != null) {
            this.f4964d.onClicked(iFeedAd);
        }
    }

    @Override // mobi.oneway.export.AdListener.feed.OWFeedAdEventListener
    public void onExposured(IFeedAd iFeedAd) {
        String string = iFeedAd.toString();
        if (!f4961a.contains(string)) {
            C1904a.m4727a().m4735a(C1904a.a.TYPE_SHOW, this.f4965e, this.f4966f);
            this.f4963c.m4944a(EventType.show, null);
            f4961a.add(string);
        }
        if (this.f4964d != null) {
            this.f4964d.onExposured(iFeedAd);
        }
    }
}

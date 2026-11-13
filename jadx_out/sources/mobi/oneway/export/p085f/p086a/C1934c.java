package mobi.oneway.export.p085f.p086a;

import java.util.List;
import mobi.oneway.export.AdListener.feed.OWFeedAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.feed.IFeedAd;
import mobi.oneway.export.p085f.C1931a;

/* JADX INFO: renamed from: mobi.oneway.export.f.a.c */
/* JADX INFO: loaded from: classes.dex */
public class C1934c extends AbstractC1932a implements OWFeedAdListener {
    public C1934c(C1931a c1931a) {
        super(c1931a);
    }

    @Override // mobi.oneway.export.p085f.p086a.AbstractC1932a
    /* JADX INFO: renamed from: c */
    protected AdType mo4908c() {
        return AdType.feed;
    }

    @Override // mobi.oneway.export.AdListener.feed.OWFeedAdListener
    public void onAdLoad(List<IFeedAd> list) {
        m4904a(EventType.ready);
        if (this.f4960e != null) {
            this.f4960e.mo4752a(this.f4957b, list);
        }
    }

    @Override // mobi.oneway.export.AdListener.feed.OWFeedAdListener
    public void onError(OnewaySdkError onewaySdkError, String str) {
        m4905a(onewaySdkError, str);
        if (this.f4960e != null) {
            this.f4960e.mo4755a(this.f4957b, onewaySdkError, str);
        }
    }
}

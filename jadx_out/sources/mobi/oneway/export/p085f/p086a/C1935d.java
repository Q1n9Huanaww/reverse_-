package mobi.oneway.export.p085f.p086a;

import mobi.oneway.export.AdListener.OWInteractiveAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.enums.OnewayAdCloseType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p085f.C1931a;

/* JADX INFO: renamed from: mobi.oneway.export.f.a.d */
/* JADX INFO: loaded from: classes.dex */
public class C1935d extends AbstractC1932a implements OWInteractiveAdListener {
    public C1935d(C1931a c1931a) {
        super(c1931a);
    }

    @Override // mobi.oneway.export.p085f.p086a.AbstractC1932a
    /* JADX INFO: renamed from: c */
    protected AdType mo4908c() {
        return AdType.interactive;
    }

    @Override // mobi.oneway.export.AdListener.OWInteractiveAdListener
    public void onAdClose(String str, OnewayAdCloseType onewayAdCloseType) {
        m4904a(EventType.close);
        if (this.f4960e != null) {
            this.f4960e.mo4753a(str, onewayAdCloseType);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWInteractiveAdListener
    public void onAdReady() {
        m4904a(EventType.ready);
        if (this.f4960e != null) {
            this.f4960e.mo4756b(this.f4957b);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWInteractiveAdListener
    public void onAdShow(String str) {
        C1904a.m4727a().m4735a(C1904a.a.TYPE_SHOW, m4906b(), m4902a());
        m4904a(EventType.show);
        if (this.f4960e != null) {
            this.f4960e.mo4757c(str);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWInteractiveAdListener
    public void onReward(String str) {
        m4904a(EventType.reward);
        if (this.f4960e != null) {
            this.f4960e.mo4759e(str);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWInteractiveAdListener
    public void onSdkError(OnewaySdkError onewaySdkError, String str) {
        m4905a(onewaySdkError, str);
        if (this.f4960e != null) {
            this.f4960e.mo4755a(this.f4957b, onewaySdkError, str);
        }
    }
}

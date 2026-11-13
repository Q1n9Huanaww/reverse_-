package mobi.oneway.export.p085f.p086a;

import mobi.oneway.export.AdListener.OWSplashAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.enums.OnewayAdCloseType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p085f.C1931a;

/* JADX INFO: renamed from: mobi.oneway.export.f.a.h */
/* JADX INFO: loaded from: classes.dex */
public class C1939h extends AbstractC1932a implements OWSplashAdListener {
    public C1939h(C1931a c1931a) {
        super(c1931a);
    }

    @Override // mobi.oneway.export.p085f.p086a.AbstractC1932a
    /* JADX INFO: renamed from: c */
    protected AdType mo4908c() {
        return AdType.splash;
    }

    @Override // mobi.oneway.export.AdListener.OWSplashAdListener
    public void onAdClick() {
        m4904a(EventType.click);
        if (this.f4960e != null) {
            this.f4960e.mo4758d(null);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWSplashAdListener
    public void onAdError(OnewaySdkError onewaySdkError, String str) {
        m4905a(onewaySdkError, str);
        if (this.f4960e != null) {
            this.f4960e.mo4755a(this.f4957b, onewaySdkError, str);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWSplashAdListener
    public void onAdFinish() {
        m4904a(EventType.end);
        if (this.f4960e != null) {
            this.f4960e.mo4754a((String) null, (OnewayAdCloseType) null, (String) null);
        }
    }

    @Override // mobi.oneway.export.AdListener.OWSplashAdListener
    public void onAdShow() {
        C1904a.m4727a().m4735a(C1904a.a.TYPE_SHOW, m4906b(), m4902a());
        m4904a(EventType.show);
        if (this.f4960e != null) {
            this.f4960e.mo4757c(null);
        }
    }
}

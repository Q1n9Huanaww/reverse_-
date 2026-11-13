package mobi.oneway.export.p085f.p086a;

import mobi.oneway.export.AdListener.OWInterstitialImageAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.enums.OnewayAdCloseType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p085f.C1931a;

/* JADX INFO: renamed from: mobi.oneway.export.f.a.e */
/* JADX INFO: loaded from: classes.dex */
public class C1936e extends AbstractC1932a implements OWInterstitialImageAdListener {
    public C1936e(C1931a c1931a) {
        super(c1931a);
    }

    @Override // mobi.oneway.export.p085f.p086a.AbstractC1932a
    /* JADX INFO: renamed from: c */
    protected AdType mo4908c() {
        return AdType.interstitialimage;
    }

    @Override // mobi.oneway.export.AdListener.AdMonitor
    public void onAdClick(String str) {
        m4904a(EventType.click);
        if (this.f4960e != null) {
            this.f4960e.mo4758d(str);
        }
    }

    @Override // mobi.oneway.export.AdListener.AdMonitor
    public void onAdClose(String str, OnewayAdCloseType onewayAdCloseType) {
        m4904a(EventType.close);
        if (this.f4960e != null) {
            this.f4960e.mo4753a(str, onewayAdCloseType);
        }
    }

    @Override // mobi.oneway.export.AdListener.AdMonitor
    public void onAdFinish(String str, OnewayAdCloseType onewayAdCloseType, String str2) {
        m4904a(EventType.end);
        if (this.f4960e != null) {
            this.f4960e.mo4754a(str, onewayAdCloseType, str2);
        }
    }

    @Override // mobi.oneway.export.AdListener.AdMonitor
    public void onAdReady() {
        m4904a(EventType.ready);
        if (this.f4960e != null) {
            this.f4960e.mo4756b(this.f4957b);
        }
    }

    @Override // mobi.oneway.export.AdListener.AdMonitor
    public void onAdShow(String str) {
        C1904a.m4727a().m4735a(C1904a.a.TYPE_SHOW, m4906b(), m4902a());
        m4904a(EventType.show);
        if (this.f4960e != null) {
            this.f4960e.mo4757c(str);
        }
    }

    @Override // mobi.oneway.export.AdListener.AdMonitor
    public void onSdkError(OnewaySdkError onewaySdkError, String str) {
        m4905a(onewaySdkError, str);
        if (this.f4960e != null) {
            this.f4960e.mo4755a(this.f4957b, onewaySdkError, str);
        }
    }
}

package mobi.oneway.export.p079b.p081b;

import java.util.List;
import mobi.oneway.export.AdListener.OWSplashAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewayAdCloseType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.feed.IFeedAd;

/* JADX INFO: renamed from: mobi.oneway.export.b.b.i */
/* JADX INFO: loaded from: classes.dex */
public class C1916i extends AbstractC1909b<OWSplashAdListener> {
    public C1916i(AdType adType, List<String> list, long j) {
        super(adType, list, j);
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: a */
    protected void mo4750a() {
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: a */
    protected void mo4751a(String str) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4752a(String str, List<IFeedAd> list) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4753a(String str, OnewayAdCloseType onewayAdCloseType) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4754a(String str, OnewayAdCloseType onewayAdCloseType, String str2) {
        if (this.f4861a != 0) {
            ((OWSplashAdListener) this.f4861a).onAdFinish();
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4755a(String str, OnewaySdkError onewaySdkError, String str2) {
        if (this.f4861a != 0) {
            ((OWSplashAdListener) this.f4861a).onAdError(onewaySdkError, str2);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: b */
    public void mo4756b(String str) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: c */
    public void mo4757c(String str) {
        if (this.f4861a != 0) {
            ((OWSplashAdListener) this.f4861a).onAdShow();
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: d */
    public void mo4758d(String str) {
        if (this.f4861a != 0) {
            ((OWSplashAdListener) this.f4861a).onAdClick();
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: e */
    public void mo4759e(String str) {
    }
}

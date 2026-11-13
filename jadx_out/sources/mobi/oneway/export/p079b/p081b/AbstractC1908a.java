package mobi.oneway.export.p079b.p081b;

import java.util.List;
import mobi.oneway.export.AdListener.AdMonitor;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewayAdCloseType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.feed.IFeedAd;

/* JADX INFO: renamed from: mobi.oneway.export.b.b.a */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1908a extends AbstractC1909b<AdMonitor> {
    public AbstractC1908a(AdType adType, List<String> list, long j) {
        super(adType, list, j);
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: a */
    protected void mo4750a() {
        if (this.f4861a != 0) {
            ((AdMonitor) this.f4861a).onAdReady();
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.AbstractC1909b
    /* JADX INFO: renamed from: a */
    protected void mo4751a(String str) {
        if (this.f4861a != 0) {
            ((AdMonitor) this.f4861a).onSdkError(OnewaySdkError.LOAD_ERROR, str);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4752a(String str, List<IFeedAd> list) {
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4753a(String str, OnewayAdCloseType onewayAdCloseType) {
        if (this.f4861a != 0) {
            ((AdMonitor) this.f4861a).onAdClose(str, onewayAdCloseType);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4754a(String str, OnewayAdCloseType onewayAdCloseType, String str2) {
        if (this.f4861a != 0) {
            ((AdMonitor) this.f4861a).onAdFinish(str, onewayAdCloseType, str2);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: a */
    public void mo4755a(String str, OnewaySdkError onewaySdkError, String str2) {
        m4774g(str);
        if (!m4772e() || this.f4861a == 0) {
            return;
        }
        ((AdMonitor) this.f4861a).onSdkError(onewaySdkError, str2);
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: b */
    public void mo4756b(String str) {
        m4773f(str);
        if (!m4771d() || this.f4861a == 0) {
            return;
        }
        ((AdMonitor) this.f4861a).onAdReady();
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: c */
    public void mo4757c(String str) {
        m4768a(false);
        if (this.f4861a != 0) {
            ((AdMonitor) this.f4861a).onAdShow(str);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: d */
    public void mo4758d(String str) {
        if (this.f4861a != 0) {
            ((AdMonitor) this.f4861a).onAdClick(str);
        }
    }

    @Override // mobi.oneway.export.p079b.p081b.InterfaceC1911d
    /* JADX INFO: renamed from: e */
    public void mo4759e(String str) {
    }
}

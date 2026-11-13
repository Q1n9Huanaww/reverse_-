package mobi.oneway.export.p077Ad;

import android.app.Activity;
import mobi.oneway.export.AdListener.OWInterstitialAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.p079b.p080a.C1905a;
import mobi.oneway.export.p084e.C1928b;

/* JADX INFO: loaded from: classes.dex */
public class OWInterstitialAd {

    /* JADX INFO: renamed from: a */
    private C1905a f4775a;

    /* JADX INFO: renamed from: b */
    private final AdType f4776b = AdType.interstitial;

    public OWInterstitialAd(final Activity activity, final String str, final OWInterstitialAdListener oWInterstitialAdListener) {
        OnewaySdk.checkSdkConfigured();
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInterstitialAd.1
            @Override // java.lang.Runnable
            public void run() {
                OWInterstitialAd.this.f4775a = new C1905a(OWInterstitialAd.this.f4776b, str);
                OWInterstitialAd.this.f4775a.m4743a(activity, oWInterstitialAdListener);
            }
        });
    }

    public void destory() {
        if (this.f4775a != null) {
            this.f4775a.m4747c();
            this.f4775a = null;
        }
    }

    public boolean isReady() {
        if (this.f4775a == null) {
            return false;
        }
        return this.f4775a.m4746b();
    }

    public void loadAd() {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInterstitialAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (OWInterstitialAd.this.f4775a != null) {
                    OWInterstitialAd.this.f4775a.m4742a();
                }
            }
        });
    }

    public void setListener(final OWInterstitialAdListener oWInterstitialAdListener) {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInterstitialAd.3
            @Override // java.lang.Runnable
            public void run() {
                if (OWInterstitialAd.this.f4775a != null) {
                    OWInterstitialAd.this.f4775a.m4745a(oWInterstitialAdListener);
                }
            }
        });
    }

    public void show(Activity activity) {
        show(activity, null);
    }

    public void show(Activity activity, String str) {
        if (this.f4775a != null) {
            this.f4775a.m4744a(activity, str);
        }
    }
}

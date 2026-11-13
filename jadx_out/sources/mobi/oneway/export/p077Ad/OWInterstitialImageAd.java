package mobi.oneway.export.p077Ad;

import android.app.Activity;
import mobi.oneway.export.AdListener.OWInterstitialImageAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.p079b.p080a.C1905a;
import mobi.oneway.export.p084e.C1928b;

/* JADX INFO: loaded from: classes.dex */
public class OWInterstitialImageAd {

    /* JADX INFO: renamed from: a */
    private C1905a f4784a;

    /* JADX INFO: renamed from: b */
    private final AdType f4785b = AdType.interstitialimage;

    public OWInterstitialImageAd(final Activity activity, final String str, final OWInterstitialImageAdListener oWInterstitialImageAdListener) {
        OnewaySdk.checkSdkConfigured();
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInterstitialImageAd.1
            @Override // java.lang.Runnable
            public void run() {
                OWInterstitialImageAd.this.f4784a = new C1905a(OWInterstitialImageAd.this.f4785b, str);
                OWInterstitialImageAd.this.f4784a.m4743a(activity, oWInterstitialImageAdListener);
            }
        });
    }

    public void destory() {
        if (this.f4784a != null) {
            this.f4784a.m4747c();
            this.f4784a = null;
        }
    }

    public boolean isReady() {
        if (this.f4784a == null) {
            return false;
        }
        return this.f4784a.m4746b();
    }

    public void loadAd() {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInterstitialImageAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (OWInterstitialImageAd.this.f4784a != null) {
                    OWInterstitialImageAd.this.f4784a.m4742a();
                }
            }
        });
    }

    public void setListener(final OWInterstitialImageAdListener oWInterstitialImageAdListener) {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInterstitialImageAd.3
            @Override // java.lang.Runnable
            public void run() {
                if (OWInterstitialImageAd.this.f4784a != null) {
                    OWInterstitialImageAd.this.f4784a.m4745a(oWInterstitialImageAdListener);
                }
            }
        });
    }

    public void show(Activity activity) {
        show(activity, null);
    }

    public void show(Activity activity, String str) {
        if (this.f4784a != null) {
            this.f4784a.m4744a(activity, str);
        }
    }
}

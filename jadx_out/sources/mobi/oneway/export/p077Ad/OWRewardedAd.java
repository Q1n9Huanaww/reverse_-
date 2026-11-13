package mobi.oneway.export.p077Ad;

import android.app.Activity;
import mobi.oneway.export.AdListener.OWRewardedAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.p079b.p080a.C1905a;
import mobi.oneway.export.p084e.C1928b;

/* JADX INFO: loaded from: classes.dex */
public class OWRewardedAd {

    /* JADX INFO: renamed from: a */
    private C1905a f4793a;

    /* JADX INFO: renamed from: b */
    private final AdType f4794b = AdType.rewarded;

    public OWRewardedAd(final Activity activity, final String str, final OWRewardedAdListener oWRewardedAdListener) {
        OnewaySdk.checkSdkConfigured();
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWRewardedAd.1
            @Override // java.lang.Runnable
            public void run() {
                OWRewardedAd.this.f4793a = new C1905a(OWRewardedAd.this.f4794b, str);
                OWRewardedAd.this.f4793a.m4743a(activity, oWRewardedAdListener);
            }
        });
    }

    public void destory() {
        if (this.f4793a != null) {
            this.f4793a.m4747c();
            this.f4793a = null;
        }
    }

    public boolean isReady() {
        if (this.f4793a == null) {
            return false;
        }
        return this.f4793a.m4746b();
    }

    public void loadAd() {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWRewardedAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (OWRewardedAd.this.f4793a != null) {
                    OWRewardedAd.this.f4793a.m4742a();
                }
            }
        });
    }

    public void setListener(final OWRewardedAdListener oWRewardedAdListener) {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWRewardedAd.3
            @Override // java.lang.Runnable
            public void run() {
                if (OWRewardedAd.this.f4793a != null) {
                    OWRewardedAd.this.f4793a.m4745a(oWRewardedAdListener);
                }
            }
        });
    }

    public void show(Activity activity) {
        show(activity, null);
    }

    public void show(Activity activity, String str) {
        if (this.f4793a != null) {
            this.f4793a.m4744a(activity, str);
        }
    }
}

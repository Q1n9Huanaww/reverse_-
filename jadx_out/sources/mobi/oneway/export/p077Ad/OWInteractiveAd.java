package mobi.oneway.export.p077Ad;

import android.app.Activity;
import mobi.oneway.export.AdListener.OWInteractiveAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.p079b.p080a.C1905a;
import mobi.oneway.export.p084e.C1928b;

/* JADX INFO: loaded from: classes.dex */
public class OWInteractiveAd {

    /* JADX INFO: renamed from: a */
    private C1905a f4766a;

    /* JADX INFO: renamed from: b */
    private final AdType f4767b = AdType.interactive;

    public OWInteractiveAd(final Activity activity, final String str, final OWInteractiveAdListener oWInteractiveAdListener) {
        OnewaySdk.checkSdkConfigured();
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInteractiveAd.1
            @Override // java.lang.Runnable
            public void run() {
                OWInteractiveAd.this.f4766a = new C1905a(OWInteractiveAd.this.f4767b, str);
                OWInteractiveAd.this.f4766a.m4743a(activity, oWInteractiveAdListener);
            }
        });
    }

    public void destory() {
        if (this.f4766a != null) {
            this.f4766a.m4747c();
            this.f4766a = null;
        }
    }

    public boolean isReady() {
        if (this.f4766a == null) {
            return false;
        }
        return this.f4766a.m4746b();
    }

    public void loadAd() {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInteractiveAd.2
            @Override // java.lang.Runnable
            public void run() {
                if (OWInteractiveAd.this.f4766a != null) {
                    OWInteractiveAd.this.f4766a.m4742a();
                }
            }
        });
    }

    public void setListener(final OWInteractiveAdListener oWInteractiveAdListener) {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWInteractiveAd.3
            @Override // java.lang.Runnable
            public void run() {
                if (OWInteractiveAd.this.f4766a != null) {
                    OWInteractiveAd.this.f4766a.m4745a(oWInteractiveAdListener);
                }
            }
        });
    }

    public void show(Activity activity) {
        show(activity, null);
    }

    public void show(Activity activity, String str) {
        if (this.f4766a != null) {
            this.f4766a.m4744a(activity, str);
        }
    }
}

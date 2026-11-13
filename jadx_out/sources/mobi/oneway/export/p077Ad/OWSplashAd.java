package mobi.oneway.export.p077Ad;

import android.app.Activity;
import android.view.ViewGroup;
import mobi.oneway.export.AdListener.OWSplashAdListener;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p079b.p080a.C1907c;
import mobi.oneway.export.p084e.C1928b;
import mobi.oneway.export.p084e.C1930d;

/* JADX INFO: loaded from: classes.dex */
public class OWSplashAd {

    /* JADX INFO: renamed from: a */
    private C1907c f4802a;

    public OWSplashAd(String str) {
        this.f4802a = new C1907c(str);
    }

    public void show(Activity activity, ViewGroup viewGroup, OWSplashAdListener oWSplashAdListener) {
        show(activity, viewGroup, oWSplashAdListener, 0L);
    }

    public void show(final Activity activity, final ViewGroup viewGroup, final OWSplashAdListener oWSplashAdListener, final long j) {
        OnewaySdk.checkSdkConfigured();
        final Runnable runnable = new Runnable() { // from class: mobi.oneway.export.Ad.OWSplashAd.1
            @Override // java.lang.Runnable
            public void run() {
                OWSplashAd.this.f4802a.m4749a(activity, viewGroup, oWSplashAdListener, j);
            }
        };
        C1928b.m4863a().m4866a(runnable);
        new C1930d(new C1930d.a() { // from class: mobi.oneway.export.Ad.OWSplashAd.2
            @Override // mobi.oneway.export.p084e.C1930d.a
            public void timeout() {
                oWSplashAdListener.onAdError(OnewaySdkError.SHOW_ERROR, "Show splash ad timeout");
                C1928b.m4863a().m4868b(runnable);
            }
        }, j).start();
    }
}

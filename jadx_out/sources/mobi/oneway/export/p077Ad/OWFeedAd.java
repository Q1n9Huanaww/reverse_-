package mobi.oneway.export.p077Ad;

import android.content.Context;
import mobi.oneway.export.AdListener.feed.OWFeedAdListener;
import mobi.oneway.export.p079b.p080a.C1906b;
import mobi.oneway.export.p084e.C1928b;

/* JADX INFO: loaded from: classes.dex */
public class OWFeedAd {

    /* JADX INFO: renamed from: a */
    private C1906b f4763a;

    public OWFeedAd(Context context, String str) {
        OnewaySdk.checkSdkConfigured();
        this.f4763a = new C1906b(context, str);
    }

    public void load(final OWFeedAdListener oWFeedAdListener) {
        C1928b.m4863a().m4866a(new Runnable() { // from class: mobi.oneway.export.Ad.OWFeedAd.1
            @Override // java.lang.Runnable
            public void run() {
                OWFeedAd.this.f4763a.m4748a(oWFeedAdListener);
            }
        });
    }
}

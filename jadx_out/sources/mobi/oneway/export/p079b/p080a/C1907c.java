package mobi.oneway.export.p079b.p080a;

import android.app.Activity;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;
import mobi.oneway.export.AdListener.OWSplashAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p079b.p081b.C1916i;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.C1942d;

/* JADX INFO: renamed from: mobi.oneway.export.b.a.c */
/* JADX INFO: loaded from: classes.dex */
public class C1907c {

    /* JADX INFO: renamed from: a */
    private final AdType f4859a = AdType.splash;

    /* JADX INFO: renamed from: b */
    private String f4860b;

    public C1907c(String str) {
        this.f4860b = str;
    }

    /* JADX INFO: renamed from: a */
    public void m4749a(Activity activity, ViewGroup viewGroup, OWSplashAdListener oWSplashAdListener, long j) {
        List<C1931a> listM4930a = C1942d.m4930a(this.f4860b, this.f4859a);
        if (listM4930a == null) {
            if (oWSplashAdListener != null) {
                oWSplashAdListener.onAdError(OnewaySdkError.INITIALIZE_FAILED, this.f4859a.name() + C1899a.f4830m);
                return;
            }
            return;
        }
        if (listM4930a.size() >= 1) {
            C1916i c1916i = new C1916i(this.f4859a, Collections.singletonList(listM4930a.get(0).m4900f()), j);
            c1916i.m4766a(oWSplashAdListener);
            c1916i.mo4770c();
            listM4930a.get(0).m4880a(activity, viewGroup, c1916i, j);
        }
    }
}

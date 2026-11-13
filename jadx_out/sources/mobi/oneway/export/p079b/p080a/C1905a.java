package mobi.oneway.export.p079b.p080a;

import android.app.Activity;
import java.util.Iterator;
import java.util.List;
import mobi.oneway.export.AdListener.AdMonitor;
import mobi.oneway.export.AdListener.OWInteractiveAdListener;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p079b.p081b.AbstractC1909b;
import mobi.oneway.export.p079b.p081b.C1912e;
import mobi.oneway.export.p079b.p081b.C1913f;
import mobi.oneway.export.p079b.p081b.C1914g;
import mobi.oneway.export.p079b.p081b.C1915h;
import mobi.oneway.export.p085f.C1931a;
import mobi.oneway.export.p085f.C1942d;

/* JADX INFO: renamed from: mobi.oneway.export.b.a.a */
/* JADX INFO: loaded from: classes.dex */
public class C1905a {

    /* JADX INFO: renamed from: a */
    private Object f4851a;

    /* JADX INFO: renamed from: b */
    private AdType f4852b;

    /* JADX INFO: renamed from: c */
    private List<C1931a> f4853c;

    /* JADX INFO: renamed from: d */
    private AbstractC1909b f4854d;

    public C1905a(AdType adType, String str) {
        this.f4852b = adType;
        this.f4853c = C1942d.m4930a(str, adType);
    }

    /* JADX INFO: renamed from: a */
    private void m4740a(OnewaySdkError onewaySdkError, String str) {
        if (this.f4851a == null) {
        }
        switch (this.f4852b) {
            case rewarded:
            case interstitial:
            case interstitialimage:
                ((AdMonitor) this.f4851a).onSdkError(onewaySdkError, str);
                break;
            case interactive:
                ((OWInteractiveAdListener) this.f4851a).onSdkError(onewaySdkError, str);
                break;
        }
    }

    /* JADX INFO: renamed from: d */
    private void m4741d() {
        AbstractC1909b c1915h;
        List<String> listM4931a = C1942d.m4931a(this.f4853c);
        long jM4739c = C1904a.m4727a().m4739c();
        switch (this.f4852b) {
            case rewarded:
                c1915h = new C1915h(this.f4852b, listM4931a, jM4739c);
                break;
            case interstitial:
                c1915h = new C1913f(this.f4852b, listM4931a, jM4739c);
                break;
            case interactive:
                c1915h = new C1912e(this.f4852b, listM4931a, jM4739c);
                break;
            case interstitialimage:
                c1915h = new C1914g(this.f4852b, listM4931a, jM4739c);
                break;
            default:
                return;
        }
        this.f4854d = c1915h;
    }

    /* JADX INFO: renamed from: a */
    public void m4742a() {
        if (this.f4853c == null) {
            m4740a(OnewaySdkError.LOAD_ERROR, this.f4852b.name() + C1899a.f4830m);
            return;
        }
        if (this.f4854d != null) {
            this.f4854d.mo4770c();
        }
        Iterator<C1931a> it2 = this.f4853c.iterator();
        while (it2.hasNext()) {
            it2.next().m4890a(this.f4852b);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4743a(Activity activity, Object obj) {
        this.f4851a = obj;
        if (this.f4853c == null) {
            return;
        }
        m4741d();
        if (this.f4854d != null) {
            this.f4854d.m4766a(obj);
        }
        for (C1931a c1931a : this.f4853c) {
            switch (this.f4852b) {
                case rewarded:
                    c1931a.m4884a(activity, (C1915h) this.f4854d);
                    break;
                case interstitial:
                    c1931a.m4882a(activity, (C1913f) this.f4854d);
                    break;
                case interactive:
                    c1931a.m4881a(activity, (C1912e) this.f4854d);
                    break;
                case interstitialimage:
                    c1931a.m4883a(activity, (C1914g) this.f4854d);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4744a(Activity activity, String str) {
        if (str == null) {
            str = "";
        }
        if (this.f4853c == null) {
            return;
        }
        for (C1931a c1931a : this.f4853c) {
            if (c1931a.m4894b(this.f4852b)) {
                c1931a.m4891a(this.f4852b, activity, str);
                return;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4745a(Object obj) {
        this.f4851a = obj;
        if (this.f4853c == null) {
            return;
        }
        if (this.f4854d != null) {
            this.f4854d.m4766a(obj);
        }
        for (C1931a c1931a : this.f4853c) {
            switch (this.f4852b) {
                case rewarded:
                    c1931a.m4889a((C1915h) this.f4854d);
                    break;
                case interstitial:
                    c1931a.m4887a((C1913f) this.f4854d);
                    break;
                case interactive:
                    c1931a.m4886a((C1912e) this.f4854d);
                    break;
                case interstitialimage:
                    c1931a.m4888a((C1914g) this.f4854d);
                    break;
            }
        }
    }

    /* JADX INFO: renamed from: b */
    public boolean m4746b() {
        if (this.f4853c == null || this.f4854d == null || !this.f4854d.m4769b()) {
            return false;
        }
        Iterator<C1931a> it2 = this.f4853c.iterator();
        while (it2.hasNext()) {
            if (it2.next().m4894b(this.f4852b)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: c */
    public void m4747c() {
        this.f4851a = null;
        if (this.f4854d != null) {
            this.f4854d.m4766a((Object) null);
            this.f4854d = null;
        }
        if (this.f4853c == null) {
            return;
        }
        Iterator<C1931a> it2 = this.f4853c.iterator();
        while (it2.hasNext()) {
            it2.next().m4896c(this.f4852b);
        }
    }
}

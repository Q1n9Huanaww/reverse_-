package mobi.oneway.export.p085f;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.util.Map;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.enums.PluginErrorType;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p079b.p081b.AbstractC1909b;
import mobi.oneway.export.p079b.p081b.C1910c;
import mobi.oneway.export.p079b.p081b.C1912e;
import mobi.oneway.export.p079b.p081b.C1913f;
import mobi.oneway.export.p079b.p081b.C1914g;
import mobi.oneway.export.p079b.p081b.C1915h;
import mobi.oneway.export.p079b.p081b.C1916i;
import mobi.oneway.export.p079b.p081b.InterfaceC1911d;
import mobi.oneway.export.p084e.C1929c;
import mobi.oneway.export.p085f.p086a.AbstractC1932a;
import mobi.oneway.export.p085f.p086a.C1934c;
import mobi.oneway.export.p085f.p086a.C1935d;
import mobi.oneway.export.p085f.p086a.C1936e;
import mobi.oneway.export.p085f.p086a.C1937f;
import mobi.oneway.export.p085f.p086a.C1938g;
import mobi.oneway.export.p085f.p086a.C1939h;
import mobi.oneway.export.p087g.C1953g;
import mobi.oneway.export.p087g.C1959m;
import mobi.oneway.export.plugin.BaseAd;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.f.a */
/* JADX INFO: loaded from: classes.dex */
public class C1931a {

    /* JADX INFO: renamed from: a */
    private int f4943a;

    /* JADX INFO: renamed from: b */
    private BaseAd f4944b;

    /* JADX INFO: renamed from: c */
    private Class f4945c;

    /* JADX INFO: renamed from: d */
    private long f4946d = -1;

    /* JADX INFO: renamed from: e */
    private long f4947e;

    /* JADX INFO: renamed from: f */
    private String f4948f;

    /* JADX INFO: renamed from: g */
    private ClassLoader f4949g;

    /* JADX INFO: renamed from: h */
    private String f4950h;

    /* JADX INFO: renamed from: i */
    private String f4951i;

    /* JADX INFO: renamed from: j */
    private String f4952j;

    /* JADX INFO: renamed from: k */
    private String f4953k;

    /* JADX INFO: renamed from: l */
    private AbstractC1932a f4954l;

    public C1931a(C1945g c1945g, String str, String str2) {
        this.f4943a = c1945g.m4947a();
        this.f4945c = c1945g.m4953b();
        this.f4948f = c1945g.m4956d();
        this.f4949g = c1945g.m4955c();
        this.f4947e = c1945g.m4957e();
        this.f4952j = c1945g.m4958f();
        this.f4950h = str2;
        this.f4951i = str;
        if (TextUtils.isEmpty(str2) || str.equals(str2)) {
            this.f4953k = str;
        } else {
            this.f4953k = str + "-" + str2;
        }
        m4876a(this.f4952j, (Map<String, String>) null);
        m4892a(C1959m.m5048a());
    }

    /* JADX WARN: Code duplicated, block: B:14:0x0035  */
    /* JADX INFO: renamed from: a */
    private AbstractC1932a m4874a(AdType adType, AbstractC1909b abstractC1909b) {
        AbstractC1932a c1938g;
        switch (adType) {
            case rewarded:
                c1938g = new C1938g(this);
                break;
            case interstitial:
                c1938g = new C1937f(this);
                break;
            case interstitialimage:
                c1938g = new C1936e(this);
                break;
            case interactive:
                c1938g = new C1935d(this);
                break;
            case feed:
                c1938g = new C1934c(this);
                break;
            case splash:
                c1938g = new C1939h(this);
                break;
            default:
                if (this.f4954l != null) {
                    this.f4954l.m4903a(abstractC1909b);
                }
                return this.f4954l;
        }
        this.f4954l = c1938g;
        if (this.f4954l != null) {
            this.f4954l.m4903a(abstractC1909b);
        }
        return this.f4954l;
    }

    /* JADX INFO: renamed from: a */
    private void m4875a(Exception exc) {
        C1929c.m4872a(PluginErrorType.shell_error_reflectMethod, C1953g.m4999a((Throwable) exc));
    }

    /* JADX INFO: renamed from: a */
    private void m4876a(String str, Map<String, String> map) {
        try {
            this.f4944b = (BaseAd) this.f4945c.getConstructor(String.class, Map.class).newInstance(str, map);
            this.f4944b.setAdConfig(m4878i());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            m4875a(e);
        }
    }

    /* JADX INFO: renamed from: h */
    private boolean m4877h() {
        if (!C1904a.m4727a().m4737a(this.f4951i, this.f4950h)) {
            C1904a.m4727a().m4735a(C1904a.a.TYPE_REQUEST, this.f4951i, this.f4950h);
            return false;
        }
        if (this.f4954l == null) {
            return true;
        }
        this.f4954l.m4907b(OnewaySdkError.LOAD_ERROR, C1899a.f4831n);
        return true;
    }

    /* JADX INFO: renamed from: i */
    private String m4878i() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cacheAd", C1904a.m4727a().m4738b());
            jSONObject.put("placements", new JSONArray(C1942d.m4929a()));
            return jSONObject.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    public int m4879a() {
        return this.f4943a;
    }

    /* JADX INFO: renamed from: a */
    public void m4880a(Activity activity, ViewGroup viewGroup, C1916i c1916i, long j) {
        if (this.f4944b == null) {
            return;
        }
        C1939h c1939h = (C1939h) m4874a(AdType.splash, c1916i);
        if (m4877h()) {
            return;
        }
        this.f4944b.showSplashAd(activity, this.f4953k, viewGroup, c1939h, j);
    }

    /* JADX INFO: renamed from: a */
    public void m4881a(Activity activity, C1912e c1912e) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.initInteractiveAd(activity, this.f4953k, (C1935d) m4874a(AdType.interactive, c1912e));
    }

    /* JADX INFO: renamed from: a */
    public void m4882a(Activity activity, C1913f c1913f) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.initInterstitialAd(activity, this.f4953k, (C1937f) m4874a(AdType.interstitial, c1913f));
    }

    /* JADX INFO: renamed from: a */
    public void m4883a(Activity activity, C1914g c1914g) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.initInterstitialImageAd(activity, this.f4953k, (C1936e) m4874a(AdType.interstitialimage, c1914g));
    }

    /* JADX INFO: renamed from: a */
    public void m4884a(Activity activity, C1915h c1915h) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.initRewardAd(activity, this.f4953k, (C1938g) m4874a(AdType.rewarded, c1915h));
    }

    /* JADX INFO: renamed from: a */
    public void m4885a(Context context, C1910c c1910c) {
        if (this.f4944b == null) {
            return;
        }
        C1934c c1934c = (C1934c) m4874a(AdType.feed, c1910c);
        if (m4877h()) {
            return;
        }
        this.f4944b.loadFeedAd(context, this.f4953k, c1934c);
    }

    /* JADX INFO: renamed from: a */
    public void m4886a(C1912e c1912e) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.setInteractiveAdListener(this.f4953k, (C1935d) m4874a(AdType.interactive, c1912e));
    }

    /* JADX INFO: renamed from: a */
    public void m4887a(C1913f c1913f) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.setInterstitialAdListener(this.f4953k, (C1937f) m4874a(AdType.interstitial, c1913f));
    }

    /* JADX INFO: renamed from: a */
    public void m4888a(C1914g c1914g) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.setInterstitialImageAdListener(this.f4953k, (C1936e) m4874a(AdType.interstitialimage, c1914g));
    }

    /* JADX INFO: renamed from: a */
    public void m4889a(C1915h c1915h) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.setRewardAdListener(this.f4953k, (C1938g) m4874a(AdType.rewarded, c1915h));
    }

    /* JADX INFO: renamed from: a */
    public void m4890a(AdType adType) {
        if (this.f4944b == null || m4877h()) {
            return;
        }
        switch (adType) {
            case rewarded:
                this.f4944b.loadRewardAd(this.f4953k);
                break;
            case interstitial:
                this.f4944b.loadInterstitialAd(this.f4953k);
                break;
            case interstitialimage:
                this.f4944b.loadInterstitialImageAd(this.f4953k);
                break;
            case interactive:
                this.f4944b.loadInteractiveAd(this.f4953k);
                break;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4891a(AdType adType, Activity activity, String str) {
        if (this.f4944b == null) {
        }
        switch (adType) {
            case rewarded:
                this.f4944b.showRewardAd(activity, this.f4953k, str);
                break;
            case interstitial:
                this.f4944b.showInterstitialAd(activity, this.f4953k, str);
                break;
            case interstitialimage:
                this.f4944b.showInterstitialImageAd(activity, this.f4953k, str);
                break;
            case interactive:
                this.f4944b.showInteractiveAd(activity, this.f4953k, str);
                break;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4892a(boolean z) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.setDebugMode(z);
    }

    /* JADX INFO: renamed from: b */
    public long m4893b() {
        if (this.f4946d != -1 || this.f4944b == null) {
            return 0L;
        }
        return this.f4944b.getCrc();
    }

    /* JADX INFO: renamed from: b */
    public boolean m4894b(AdType adType) {
        if (this.f4944b == null) {
            return false;
        }
        switch (adType) {
            case rewarded:
                return this.f4944b.isRewardAdReady(this.f4953k);
            case interstitial:
                return this.f4944b.isInterstitialAdReady(this.f4953k);
            case interstitialimage:
                return this.f4944b.isInterstitialImageAdReady(this.f4953k);
            case interactive:
                return this.f4944b.isInteractiveAdReady(this.f4953k);
            default:
                return false;
        }
    }

    /* JADX INFO: renamed from: c */
    public long m4895c() {
        return this.f4947e;
    }

    /* JADX INFO: renamed from: c */
    public void m4896c(AdType adType) {
        if (this.f4944b == null) {
            return;
        }
        this.f4944b.destoryAd(adType, this.f4953k);
        if (this.f4954l != null) {
            this.f4954l.m4903a((InterfaceC1911d) null);
            this.f4954l = null;
        }
    }

    /* JADX INFO: renamed from: d */
    public String m4897d() {
        return this.f4948f;
    }

    /* JADX INFO: renamed from: d */
    public boolean m4898d(AdType adType) {
        if (adType == AdType.splash) {
            return true;
        }
        String str = "";
        switch (adType) {
            case rewarded:
                str = "hasRewardAd";
                break;
            case interstitial:
                str = "hasInterstitialAd";
                break;
            case interstitialimage:
                str = "hasInterstitialImageAd";
                break;
            case interactive:
                str = "hasInteractiveAd";
                break;
            case feed:
                str = "hasFeedAd";
                break;
        }
        try {
            if (this.f4944b != null) {
                Field declaredField = this.f4944b.getClass().getSuperclass().getDeclaredField(str);
                declaredField.setAccessible(true);
                return ((Boolean) declaredField.get(this.f4944b)).booleanValue();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            m4875a(e);
        }
        return false;
    }

    /* JADX INFO: renamed from: e */
    public ClassLoader m4899e() {
        return this.f4949g;
    }

    /* JADX INFO: renamed from: f */
    public String m4900f() {
        return this.f4950h;
    }

    /* JADX INFO: renamed from: g */
    public String m4901g() {
        return this.f4951i;
    }
}

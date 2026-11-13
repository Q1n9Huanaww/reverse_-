package mobi.oneway.export.p085f;

import android.os.Build;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mobi.oneway.export.C1898a;
import mobi.oneway.export.enums.AdType;
import mobi.oneway.export.enums.EventType;
import mobi.oneway.export.enums.OnewaySdkError;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p078a.C1900b;
import mobi.oneway.export.p083d.C1926f;
import mobi.oneway.export.p087g.C1947a;
import mobi.oneway.export.p087g.C1951e;
import mobi.oneway.export.p087g.C1952f;
import mobi.oneway.export.p087g.C1958l;
import mobi.oneway.export.p087g.C1960n;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.f.e */
/* JADX INFO: loaded from: classes.dex */
public class C1943e {

    /* JADX INFO: renamed from: g */
    private static ExecutorService f4980g = Executors.newCachedThreadPool();

    /* JADX INFO: renamed from: a */
    private Long f4981a = 0L;

    /* JADX INFO: renamed from: b */
    private AdType f4982b;

    /* JADX INFO: renamed from: c */
    private String f4983c;

    /* JADX INFO: renamed from: d */
    private C1931a f4984d;

    /* JADX INFO: renamed from: e */
    private String f4985e;

    /* JADX INFO: renamed from: f */
    private String f4986f;

    /* JADX INFO: renamed from: mobi.oneway.export.f.e$a */
    class a implements Runnable {

        /* JADX INFO: renamed from: a */
        public EventType f4988a;

        public a(EventType eventType) {
            this.f4988a = eventType;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            try {
                C1926f c1926fM4823a = new C1926f(C1899a.f4822e, C1899a.f4821d).m4823a("eventName", (Object) this.f4988a.toString()).m4823a("publishId", (Object) C1900b.m4710b()).m4823a("token", (Object) C1900b.m4714d()).m4823a("adType", Integer.valueOf(C1943e.this.f4982b.value())).m4823a("dmd", (Object) Build.MODEL).m4823a("dmk", (Object) Build.BRAND).m4823a("ip", (Object) C1960n.m5055a(C1900b.m4706a())).m4823a("osv", (Object) Build.VERSION.RELEASE);
                if (C1900b.m4713c()) {
                    c1926fM4823a.m4823a("e", Boolean.valueOf(C1900b.m4713c()));
                }
                c1926fM4823a.m4836c("POST").m4822a(C1943e.this.m4939a()).m4848n();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public C1943e(AdType adType, C1931a c1931a) {
        this.f4982b = adType;
        this.f4984d = c1931a;
        this.f4985e = c1931a.m4901g();
        this.f4986f = c1931a.m4900f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public String m4939a() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("cid", C1940b.m4909a(this.f4984d.m4879a(), this.f4985e, this.f4986f));
            jSONObject.put("v", this.f4984d.m4897d());
            jSONObject.put("sv", C1898a.f4817f);
            jSONObject.put("ssv", C1952f.m4989c());
            jSONObject.put("wv", "");
            jSONObject.put("ts", System.currentTimeMillis());
            jSONObject.put("did", C1952f.m4985a());
            jSONObject.put("bdid", C1952f.m4988b());
            jSONObject.put("dos", "Android");
            jSONObject.put("ua", C1952f.m4990d());
            jSONObject.put("ct", C1952f.m4992f());
            jSONObject.put("nt", C1952f.m4993g());
            jSONObject.put("osv", Build.VERSION.RELEASE);
            jSONObject.put("dmd", Build.MODEL);
            jSONObject.put("dmk", Build.BRAND);
            jSONObject.put("aid", C1952f.m4991e());
            jSONObject.put("bssid", C1952f.m4995i());
            jSONObject.put("ssid", C1952f.m4994h());
            jSONObject.put("simopt", C1952f.m4996j());
            jSONObject.put("simoptn", C1952f.m4997k());
            jSONObject.put("lng", C1958l.m5041b());
            jSONObject.put("lat", C1958l.m5039a());
            jSONObject.put("adp", this.f4984d.m4879a());
            jSONObject.put("ip", C1960n.m5055a(C1900b.m4706a()));
            jSONObject.put("adts", this.f4981a);
            jSONObject.put("crc", this.f4984d.m4893b());
            jSONObject.put("sc", C1900b.m4715e());
            jSONObject.put("slt", this.f4984d.m4895c());
            jSONObject.put("dt", this.f4983c);
            jSONObject.put("pid", this.f4985e);
            jSONObject.put("thirdPid", this.f4986f);
            jSONObject.put("oaid", m4943c());
            return C1900b.m4713c() ? C1947a.m4960a(jSONObject.toString(), C1900b.m4714d(), C1900b.m4710b()) : jSONObject.toString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: b */
    private void m4942b() {
        this.f4981a = Long.valueOf(System.currentTimeMillis());
        C1940b.m4910a(this.f4984d.m4879a(), this.f4985e, this.f4986f, C1951e.m4984a(C1900b.m4710b() + "-" + this.f4982b.value() + "-" + this.f4981a).longValue());
        this.f4983c = "";
    }

    /* JADX INFO: renamed from: c */
    private String m4943c() {
        try {
            Field declaredField = this.f4984d.m4899e().loadClass("mobi.oneway.third_common.util.oaid.OaidHolder").getDeclaredField("oaid");
            declaredField.setAccessible(true);
            return (String) declaredField.get(null);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4944a(EventType eventType, String str) {
        if (this.f4984d.m4879a() == 1) {
            return;
        }
        switch (eventType) {
            case show:
                if (this.f4982b.equals(AdType.splash)) {
                }
            case ready:
                m4942b();
                break;
            case adFail:
                m4942b();
                this.f4983c = str;
                break;
        }
        f4980g.execute(new a(eventType));
    }

    /* JADX INFO: renamed from: a */
    public void m4945a(EventType eventType, OnewaySdkError onewaySdkError, String str) {
        m4944a(eventType, onewaySdkError + "--" + str);
    }
}

package mobi.oneway.export.p079b;

import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import mobi.oneway.export.p087g.C1959m;
import mobi.oneway.export.p087g.C1964r;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.b.a */
/* JADX INFO: loaded from: classes.dex */
public class C1904a {

    /* JADX INFO: renamed from: i */
    private static C1904a f4839i;

    /* JADX INFO: renamed from: a */
    private final String f4840a = "sp_ad_c_info";

    /* JADX INFO: renamed from: b */
    private final String f4841b = "ld";

    /* JADX INFO: renamed from: c */
    private final String f4842c = "rc";

    /* JADX INFO: renamed from: d */
    private final String f4843d = "sc";

    /* JADX INFO: renamed from: e */
    private final String f4844e = "msc";

    /* JADX INFO: renamed from: f */
    private final String f4845f = "mrc";

    /* JADX INFO: renamed from: g */
    private boolean f4846g;

    /* JADX INFO: renamed from: h */
    private long f4847h;

    /* JADX INFO: renamed from: mobi.oneway.export.b.a$a */
    public enum a {
        TYPE_REQUEST,
        TYPE_SHOW
    }

    private C1904a() {
    }

    /* JADX INFO: renamed from: a */
    public static C1904a m4727a() {
        if (f4839i == null) {
            f4839i = new C1904a();
        }
        return f4839i;
    }

    /* JADX INFO: renamed from: a */
    private void m4728a(JSONObject jSONObject) {
        C1964r.m5062a().m5065a("sp_ad_c_info", jSONObject.toString());
    }

    /* JADX INFO: renamed from: a */
    private void m4729a(JSONObject jSONObject, String str) {
        try {
            C1959m.m5043a((Object) "resume ad control count");
            jSONObject.put("ld", str);
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                Object obj = jSONObject.get(itKeys.next());
                if (obj instanceof JSONObject) {
                    JSONObject jSONObject2 = (JSONObject) obj;
                    jSONObject2.put("rc", 0);
                    jSONObject2.put("sc", 0);
                }
            }
            m4728a(jSONObject);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: b */
    private String m4730b(String str, String str2) {
        if (TextUtils.isEmpty(str2) || str.equals(str2)) {
            return str;
        }
        return str + "-" + str2;
    }

    /* JADX INFO: renamed from: d */
    private JSONObject m4731d() {
        String str = (String) C1964r.m5062a().m5066b("sp_ad_c_info", "");
        return TextUtils.isEmpty(str) ? new JSONObject() : new JSONObject(str);
    }

    /* JADX INFO: renamed from: e */
    private String m4732e() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date(System.currentTimeMillis()));
    }

    /* JADX INFO: renamed from: a */
    public void m4733a(long j) {
        this.f4847h = j;
    }

    /* JADX INFO: renamed from: a */
    public void m4734a(String str, String str2, int i, int i2) {
        JSONObject jSONObject;
        String strM4730b = m4730b(str, str2);
        try {
            JSONObject jSONObjectM4731d = m4731d();
            if (jSONObjectM4731d.has(strM4730b)) {
                jSONObject = jSONObjectM4731d.getJSONObject(strM4730b);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObjectM4731d.put(strM4730b, jSONObject2);
                jSONObject = jSONObject2;
            }
            jSONObject.put("mrc", i);
            jSONObject.put("msc", i2);
            m4728a(jSONObjectM4731d);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4735a(a aVar, String str, String str2) {
        JSONObject jSONObject;
        try {
            String strM4730b = m4730b(str, str2);
            JSONObject jSONObjectM4731d = m4731d();
            if (jSONObjectM4731d.has(strM4730b)) {
                jSONObject = jSONObjectM4731d.getJSONObject(strM4730b);
            } else {
                JSONObject jSONObject2 = new JSONObject();
                jSONObjectM4731d.put(strM4730b, jSONObject2);
                jSONObject = jSONObject2;
            }
            if (aVar == a.TYPE_REQUEST) {
                jSONObject.put("rc", jSONObject.optInt("rc") + 1);
            }
            if (aVar == a.TYPE_SHOW) {
                jSONObject.put("sc", jSONObject.optInt("sc") + 1);
            }
            m4728a(jSONObjectM4731d);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4736a(boolean z) {
        this.f4846g = z;
    }

    /* JADX INFO: renamed from: a */
    public boolean m4737a(String str, String str2) {
        String strM4732e = m4732e();
        String strM4730b = m4730b(str, str2);
        try {
            JSONObject jSONObjectM4731d = m4731d();
            if (!strM4732e.equals(jSONObjectM4731d.optString("ld"))) {
                m4729a(jSONObjectM4731d, strM4732e);
                return false;
            }
            if (jSONObjectM4731d.has(strM4730b)) {
                JSONObject jSONObject = jSONObjectM4731d.getJSONObject(strM4730b);
                int iOptInt = jSONObject.optInt("rc");
                int iOptInt2 = jSONObject.optInt("sc");
                int iOptInt3 = jSONObject.optInt("mrc");
                int iOptInt4 = jSONObject.optInt("msc");
                C1959m.m5043a((Object) String.format("adtype=%s,maxRequest=%d,curRequest=%d;maxShow=%d,curShow=%d", strM4730b, Integer.valueOf(iOptInt3), Integer.valueOf(iOptInt), Integer.valueOf(iOptInt4), Integer.valueOf(iOptInt2)));
                return (iOptInt3 != 0 && iOptInt >= iOptInt3) || (iOptInt4 != 0 && iOptInt2 >= iOptInt4);
            }
            return false;
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: b */
    public boolean m4738b() {
        return this.f4846g;
    }

    /* JADX INFO: renamed from: c */
    public long m4739c() {
        return this.f4847h;
    }
}

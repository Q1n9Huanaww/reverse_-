package mobi.oneway.export.p083d;

import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.d.a */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1921a<Data> implements C1926f.b {
    /* JADX INFO: renamed from: a */
    public abstract void m4781a(int i, String str);

    /* JADX INFO: renamed from: a */
    public abstract void m4782a(Data data);

    @Override // mobi.oneway.export.p083d.C1926f.b
    /* JADX INFO: renamed from: a */
    public void mo4783a(Throwable th, C1922b c1922b) {
        m4781a(-1, "请求失败，请稍后再试");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // mobi.oneway.export.p083d.C1926f.b
    /* JADX INFO: renamed from: a */
    public void mo4784a(C1922b c1922b) {
        try {
            JSONObject jSONObjectM4797e = c1922b.m4797e();
            int iOptInt = jSONObjectM4797e.optInt("code", -1);
            String strOptString = jSONObjectM4797e.optString("message", "请求失败");
            Object obj = jSONObjectM4797e.get("data");
            if (iOptInt == 0) {
                m4782a(obj);
            } else {
                m4781a(iOptInt, strOptString);
            }
        } catch (Throwable unused) {
            m4781a(-1, "请求失败，请稍后再试");
        }
    }
}

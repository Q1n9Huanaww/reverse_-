package mobi.oneway.export.p085f;

import java.util.ArrayList;
import java.util.List;
import mobi.oneway.export.p079b.C1904a;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.f.c */
/* JADX INFO: loaded from: classes.dex */
public class C1941c {

    /* JADX INFO: renamed from: a */
    private String f4968a;

    /* JADX INFO: renamed from: b */
    private List<a> f4969b;

    /* JADX INFO: renamed from: mobi.oneway.export.f.c$a */
    public static class a {

        /* JADX INFO: renamed from: a */
        private int f4970a;

        /* JADX INFO: renamed from: b */
        private String f4971b;

        /* JADX INFO: renamed from: c */
        private int f4972c;

        /* JADX INFO: renamed from: d */
        private int f4973d;

        /* JADX INFO: renamed from: e */
        private int f4974e;

        /* JADX INFO: renamed from: f */
        private int f4975f;

        /* JADX INFO: renamed from: g */
        private int f4976g;

        public a(String str, JSONObject jSONObject) {
            this.f4970a = jSONObject.optInt("adp");
            this.f4971b = jSONObject.optString("pid");
            this.f4972c = jSONObject.optInt("dayReqLimit");
            this.f4973d = jSONObject.optInt("dayShowLimit");
            this.f4974e = jSONObject.optInt("fsc");
            this.f4975f = jSONObject.optInt("hvcb");
            this.f4976g = jSONObject.optInt("ocb");
            C1904a.m4727a().m4734a(str, this.f4971b, this.f4972c, this.f4973d);
        }

        /* JADX INFO: renamed from: a */
        public int m4915a() {
            return this.f4970a;
        }

        /* JADX INFO: renamed from: a */
        public void m4916a(int i) {
            this.f4970a = i;
        }

        /* JADX INFO: renamed from: a */
        public void m4917a(String str) {
            this.f4971b = str;
        }

        /* JADX INFO: renamed from: b */
        public String m4918b() {
            return this.f4971b;
        }

        /* JADX INFO: renamed from: b */
        public void m4919b(int i) {
            this.f4972c = i;
        }

        /* JADX INFO: renamed from: c */
        public int m4920c() {
            return this.f4972c;
        }

        /* JADX INFO: renamed from: c */
        public void m4921c(int i) {
            this.f4973d = i;
        }

        /* JADX INFO: renamed from: d */
        public int m4922d() {
            return this.f4973d;
        }

        /* JADX INFO: renamed from: d */
        public void m4923d(int i) {
            this.f4974e = i;
        }

        /* JADX INFO: renamed from: e */
        public int m4924e() {
            return this.f4974e;
        }

        /* JADX INFO: renamed from: e */
        public void m4925e(int i) {
            this.f4975f = i;
        }

        /* JADX INFO: renamed from: f */
        public int m4926f() {
            return this.f4975f;
        }

        /* JADX INFO: renamed from: f */
        public void m4927f(int i) {
            this.f4976g = i;
        }

        /* JADX INFO: renamed from: g */
        public int m4928g() {
            return this.f4976g;
        }
    }

    public C1941c(JSONObject jSONObject) {
        this.f4968a = jSONObject.optString("pid");
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("pidConfigs");
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            return;
        }
        this.f4969b = new ArrayList();
        for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
            this.f4969b.add(new a(this.f4968a, jSONArrayOptJSONArray.optJSONObject(i)));
        }
    }

    /* JADX INFO: renamed from: a */
    public String m4911a() {
        return this.f4968a;
    }

    /* JADX INFO: renamed from: a */
    public void m4912a(String str) {
        this.f4968a = str;
    }

    /* JADX INFO: renamed from: a */
    public void m4913a(List<a> list) {
        this.f4969b = list;
    }

    /* JADX INFO: renamed from: b */
    public List<a> m4914b() {
        return this.f4969b;
    }
}

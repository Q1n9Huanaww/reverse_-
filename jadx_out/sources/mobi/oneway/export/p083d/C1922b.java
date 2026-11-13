package mobi.oneway.export.p083d;

import java.io.File;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.d.b */
/* JADX INFO: loaded from: classes.dex */
public class C1922b {

    /* JADX INFO: renamed from: a */
    private C1926f f4881a;

    /* JADX INFO: renamed from: b */
    private int f4882b;

    /* JADX INFO: renamed from: c */
    private byte[] f4883c;

    /* JADX INFO: renamed from: d */
    private Map<String, List<String>> f4884d;

    /* JADX INFO: renamed from: e */
    private Throwable f4885e;

    /* JADX INFO: renamed from: f */
    private long f4886f;

    /* JADX INFO: renamed from: g */
    private File f4887g;

    C1922b(C1926f c1926f) {
        m4792a(c1926f);
    }

    /* JADX INFO: renamed from: a */
    public String m4785a() {
        return m4800h().m4840f();
    }

    /* JADX INFO: renamed from: a */
    public String m4786a(String str) {
        List<String> list;
        if (this.f4884d == null || (list = this.f4884d.get(str)) == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /* JADX INFO: renamed from: a */
    public C1922b m4787a(Throwable th) {
        this.f4885e = th;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public void m4788a(int i) {
        this.f4882b = i;
    }

    /* JADX INFO: renamed from: a */
    public void m4789a(long j) {
        this.f4886f = j;
    }

    /* JADX INFO: renamed from: a */
    public void m4790a(File file) {
        this.f4887g = file;
    }

    /* JADX INFO: renamed from: a */
    public void m4791a(Map<String, List<String>> map) {
        this.f4884d = map;
    }

    /* JADX INFO: renamed from: a */
    public void m4792a(C1926f c1926f) {
        this.f4881a = c1926f;
    }

    /* JADX INFO: renamed from: a */
    public void m4793a(byte[] bArr) {
        this.f4883c = bArr;
    }

    /* JADX INFO: renamed from: b */
    public boolean m4794b() {
        return this.f4885e == null;
    }

    /* JADX INFO: renamed from: c */
    public byte[] m4795c() {
        return this.f4883c;
    }

    /* JADX INFO: renamed from: d */
    public String m4796d() {
        if (this.f4883c == null) {
            return null;
        }
        return new String(this.f4883c);
    }

    /* JADX INFO: renamed from: e */
    public JSONObject m4797e() {
        return new JSONObject(m4796d());
    }

    /* JADX INFO: renamed from: f */
    public long m4798f() {
        if (this.f4883c != null) {
            return this.f4883c.length;
        }
        if (this.f4887g != null) {
            return this.f4887g.length();
        }
        return -1L;
    }

    /* JADX INFO: renamed from: g */
    public File m4799g() {
        return this.f4887g;
    }

    /* JADX INFO: renamed from: h */
    public C1926f m4800h() {
        return this.f4881a;
    }

    /* JADX INFO: renamed from: i */
    public Map<String, List<String>> m4801i() {
        return this.f4884d;
    }

    /* JADX INFO: renamed from: j */
    public long m4802j() {
        return this.f4886f;
    }

    /* JADX INFO: renamed from: k */
    public boolean m4803k() {
        return this.f4881a != null && this.f4881a.m4839e();
    }

    /* JADX INFO: renamed from: l */
    public int m4804l() {
        return this.f4882b;
    }

    /* JADX INFO: renamed from: m */
    public Throwable m4805m() {
        return this.f4885e;
    }
}

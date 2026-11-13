package mobi.oneway.export.p087g;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.g.i */
/* JADX INFO: loaded from: classes.dex */
public class C1955i {

    /* JADX INFO: renamed from: a */
    static final String f5013a = "POST";

    /* JADX INFO: renamed from: b */
    static final String f5014b = "GET";

    /* JADX INFO: renamed from: c */
    static final String f5015c = "HEAD";

    /* JADX INFO: renamed from: d */
    static final String f5016d = "Content-Type";

    /* JADX INFO: renamed from: e */
    static final String f5017e = "application/json";

    /* JADX INFO: renamed from: f */
    static final long f5018f = 15000;

    /* JADX INFO: renamed from: g */
    private String f5019g;

    /* JADX INFO: renamed from: h */
    private String f5020h;

    /* JADX INFO: renamed from: i */
    private long f5021i;

    /* JADX INFO: renamed from: a */
    public C1955i m5031a(long j) {
        this.f5021i = j;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1955i m5032a(String str) {
        this.f5019g = str;
        return this;
    }

    /* JADX INFO: renamed from: a */
    public JSONObject m5033a() {
        JSONObject jSONObject = new JSONObject();
        m5034b("GET");
        try {
            return jSONObject;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return jSONObject;
        }
    }

    /* JADX INFO: renamed from: b */
    public C1955i m5034b(String str) {
        this.f5020h = str;
        return this;
    }
}

package mobi.oneway.export.p082c;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p087g.C1953g;
import mobi.oneway.export.p087g.C1954h;
import mobi.oneway.export.p087g.C1957k;
import mobi.oneway.export.p087g.C1961o;
import mobi.oneway.export.p087g.C1965s;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.c.a */
/* JADX INFO: loaded from: classes.dex */
class C1917a {

    /* JADX INFO: renamed from: a */
    private File f4875a;

    /* JADX INFO: renamed from: b */
    private ExecutorService f4876b = Executors.newFixedThreadPool(5);

    public C1917a(Context context) {
        this.f4875a = context.getDir(C1899a.f4826i, 0);
        m4776b();
    }

    /* JADX INFO: renamed from: b */
    private void m4776b() {
        File[] fileArrListFiles;
        if (this.f4875a == null || (fileArrListFiles = this.f4875a.listFiles()) == null || fileArrListFiles.length == 0) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        for (File file : fileArrListFiles) {
            if (jCurrentTimeMillis - file.lastModified() >= 604800000) {
                file.delete();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4777a() {
        File[] fileArrListFiles;
        if (this.f4875a == null || (fileArrListFiles = this.f4875a.listFiles()) == null || fileArrListFiles.length == 0) {
            return;
        }
        for (File file : fileArrListFiles) {
            this.f4876b.execute(new RunnableC1918b(file));
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4778a(Throwable th) {
        if (this.f4875a == null) {
            return;
        }
        try {
            String strM4999a = C1953g.m4999a(th);
            File file = new File(this.f4875a, System.currentTimeMillis() + ".txt");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("createTime", C1965s.m5085b(new Date()));
            jSONObject.put("info", strM4999a);
            C1957k.m5038a(jSONObject, C1961o.m5056a());
            C1957k.m5038a(jSONObject, C1961o.m5057b());
            C1954h.m5013b(file, jSONObject.toString());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

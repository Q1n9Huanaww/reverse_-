package mobi.oneway.export.p084e;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import mobi.oneway.export.enums.PluginErrorType;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p083d.C1926f;
import mobi.oneway.export.p087g.C1961o;
import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.util.Const;

/* JADX INFO: renamed from: mobi.oneway.export.e.c */
/* JADX INFO: loaded from: classes.dex */
public class C1929c extends Thread {

    /* JADX INFO: renamed from: a */
    private JSONObject f4937a;

    public C1929c(JSONObject jSONObject) {
        this.f4937a = jSONObject;
    }

    /* JADX INFO: renamed from: a */
    public static void m4872a(PluginErrorType pluginErrorType, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("type", pluginErrorType);
            jSONObject.put(Const.TableSchema.COLUMN_NAME, "Error");
            jSONObject.put("stack", str);
            jSONObject.put("zc", C1961o.m5056a());
            jSONObject.put("zm", C1961o.m5057b());
            new C1929c(jSONObject).start();
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        super.run();
        try {
            new C1926f(C1899a.f4823f, C1899a.f4819b).m4827a(this.f4937a).m4836c("POST").m4848n();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

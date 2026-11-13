package mobi.oneway.export.p087g;

import android.os.Build;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import mobi.oneway.export.C1898a;
import mobi.oneway.export.p078a.C1900b;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.g.o */
/* JADX INFO: loaded from: classes.dex */
public class C1961o {
    /* JADX INFO: renamed from: a */
    public static JSONObject m5056a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("publishId", C1900b.m4710b());
            jSONObject.put("bundleId", C1952f.m4988b());
            jSONObject.put("bundleVersion", C1952f.m4989c());
            jSONObject.put("sdkVersion", C1898a.f4817f);
            jSONObject.put("platform", Constants.ANDROID);
            return jSONObject;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return jSONObject;
        }
    }

    /* JADX INFO: renamed from: b */
    public static JSONObject m5057b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("apiLevel", Build.VERSION.SDK_INT);
            jSONObject.put("imei", C1952f.m4985a());
            jSONObject.put("androidId", C1952f.m4991e());
            jSONObject.put("deviceMake", Build.BRAND);
            jSONObject.put("deviceModel", Build.MODEL);
            jSONObject.put("osVersion", Build.VERSION.RELEASE);
            return jSONObject;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return jSONObject;
        }
    }
}

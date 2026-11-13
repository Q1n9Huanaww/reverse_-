package mobi.oneway.export.p087g;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: mobi.oneway.export.g.k */
/* JADX INFO: loaded from: classes.dex */
public class C1957k {
    /* JADX INFO: renamed from: a */
    public static void m5038a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null || jSONObject2 == null) {
            return;
        }
        try {
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                jSONObject.put(next, jSONObject2.get(next));
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

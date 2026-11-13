package mobi.oneway.export.p085f;

import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: mobi.oneway.export.f.b */
/* JADX INFO: loaded from: classes.dex */
public class C1940b {

    /* JADX INFO: renamed from: a */
    private static Map<String, Long> f4967a = new HashMap();

    /* JADX INFO: renamed from: a */
    public static long m4909a(int i, String str, String str2) {
        Long l = f4967a.get(i + "-" + str + str2);
        if (l == null) {
            return 0L;
        }
        return l.longValue();
    }

    /* JADX INFO: renamed from: a */
    public static void m4910a(int i, String str, String str2, long j) {
        f4967a.put(i + "-" + str + str2, Long.valueOf(j));
    }
}

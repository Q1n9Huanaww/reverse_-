package com.goldcoast.sdk.p027c;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.f */
/* JADX INFO: compiled from: IOUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1124f {
    /* JADX INFO: renamed from: a */
    public static String m1426a(InputStream inputStream, Map map) {
        StringBuilder sb = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        boolean z = map != null && map.size() > 0;
        while (true) {
            try {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    if (map != null && line.contains(":")) {
                        String[] strArrSplit = line.split(":");
                        if (z) {
                            m1427a(map, strArrSplit[0], strArrSplit[1]);
                        } else if (strArrSplit[0].trim().equals("processor")) {
                            if (strArrSplit.length > 1) {
                                map.put("processorcnt", strArrSplit[1].trim());
                            } else {
                                map.put("processorcnt", strArrSplit[0].trim());
                            }
                        } else if (strArrSplit.length > 1) {
                            map.put(strArrSplit[0].trim(), strArrSplit[1].trim());
                        } else {
                            map.put(strArrSplit[0].trim(), strArrSplit[0].trim());
                        }
                    }
                    sb.append(line);
                    sb.append("\n");
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (IOException unused) {
                }
                throw th;
            }
        }
        bufferedReader.close();
        return sb.toString();
        try {
            bufferedReader.close();
        } catch (IOException unused2) {
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static void m1427a(Map<String, String> map, String str, String str2) {
        String strTrim = str.replace("[", "").replace("]", "").trim();
        String strTrim2 = str2.replace("[", "").replace("]", "").trim();
        Iterator<String> it2 = map.keySet().iterator();
        if (it2 == null || !it2.hasNext()) {
            return;
        }
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.equals(strTrim)) {
                map.put(next, strTrim2);
                return;
            }
        }
    }
}

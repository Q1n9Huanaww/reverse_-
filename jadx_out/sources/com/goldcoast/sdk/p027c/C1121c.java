package com.goldcoast.sdk.p027c;

import android.content.Context;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.c */
/* JADX INFO: compiled from: CrashRepository.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1121c {

    /* JADX INFO: renamed from: c */
    private static C1121c f1788c;

    /* JADX INFO: renamed from: d */
    private static Context f1789d;

    /* JADX INFO: renamed from: b */
    private String f1791b = "noend.ini";

    /* JADX INFO: renamed from: a */
    private String f1790a = f1789d.getFilesDir().getAbsolutePath();

    private C1121c() {
        File file = new File(this.f1790a);
        if (!file.exists()) {
            try {
                file.mkdirs();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        File file2 = new File(this.f1790a, this.f1791b);
        if (file2.exists()) {
            return;
        }
        try {
            file2.createNewFile();
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1121c m1410a(Context context) {
        if (f1788c == null) {
            f1789d = context;
            f1788c = new C1121c();
        }
        return f1788c;
    }

    /* JADX INFO: renamed from: a */
    public final void m1412a(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(this.f1790a, this.f1791b));
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    public final String m1411a() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(this.f1790a, this.f1791b)));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            bufferedReader.close();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: b */
    public final void m1413b(String str) {
        String strM1411a = m1411a();
        if (TextUtils.isEmpty(strM1411a)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(strM1411a);
            if (jSONArray.length() > 0) {
                JSONArray jSONArray2 = new JSONArray();
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (!str.equals(jSONArray.get(i).toString())) {
                        jSONArray2.put(jSONArray.get(i).toString());
                    }
                }
                if (jSONArray2.length() > 0) {
                    m1412a(jSONArray2.toString());
                } else {
                    m1412a("");
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: c */
    public final boolean m1414c(String str) {
        String strM1411a = m1411a();
        if (TextUtils.isEmpty(strM1411a)) {
            return false;
        }
        try {
            JSONArray jSONArray = new JSONArray(strM1411a);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    if (str.equals(jSONArray.get(i).toString())) {
                        return true;
                    }
                }
            }
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return false;
    }
}

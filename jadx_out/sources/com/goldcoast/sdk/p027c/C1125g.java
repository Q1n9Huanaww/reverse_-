package com.goldcoast.sdk.p027c;

import android.content.Context;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.g */
/* JADX INFO: compiled from: LogUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1125g {

    /* JADX INFO: renamed from: a */
    private static C1125g f1796a;

    /* JADX INFO: renamed from: b */
    private static Context f1797b;

    /* JADX INFO: renamed from: c */
    private static boolean f1798c;

    private C1125g() {
    }

    /* JADX INFO: renamed from: a */
    public static void m1429a(Context context) {
        f1797b = context;
    }

    /* JADX INFO: renamed from: a */
    public static C1125g m1428a() {
        if (f1796a == null) {
            f1796a = new C1125g();
        }
        return f1796a;
    }

    /* JADX INFO: renamed from: a */
    public static void m1430a(String str) {
        if (f1798c) {
            Log.i("GOLD_COAST", str);
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m1431b(String str) {
        try {
            FileWriter fileWriter = new FileWriter(new File(f1797b.getFilesDir().getAbsolutePath() + File.separator + "dump"), true);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

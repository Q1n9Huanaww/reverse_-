package mobi.oneway.export.p082c;

import android.content.Context;

/* JADX INFO: renamed from: mobi.oneway.export.c.c */
/* JADX INFO: loaded from: classes.dex */
public class C1919c {

    /* JADX INFO: renamed from: a */
    private static C1919c f4878a;

    private C1919c() {
    }

    /* JADX INFO: renamed from: a */
    public static C1919c m4779a() {
        if (f4878a == null) {
            f4878a = new C1919c();
        }
        return f4878a;
    }

    /* JADX INFO: renamed from: a */
    public void m4780a(Context context) {
        C1917a c1917a = new C1917a(context);
        c1917a.m4777a();
        Thread.setDefaultUncaughtExceptionHandler(new C1920d(c1917a));
    }
}

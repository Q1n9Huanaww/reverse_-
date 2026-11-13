package mobi.oneway.export.p082c;

import android.support.annotation.NonNull;

/* JADX INFO: renamed from: mobi.oneway.export.c.d */
/* JADX INFO: loaded from: classes.dex */
public class C1920d implements Thread.UncaughtExceptionHandler {

    /* JADX INFO: renamed from: a */
    private Thread.UncaughtExceptionHandler f4879a = Thread.getDefaultUncaughtExceptionHandler();

    /* JADX INFO: renamed from: b */
    private C1917a f4880b;

    public C1920d(C1917a c1917a) {
        this.f4880b = c1917a;
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(@NonNull Thread thread, @NonNull Throwable th) {
        this.f4880b.m4778a(th);
        if (this.f4879a != null) {
            this.f4879a.uncaughtException(thread, th);
        }
    }
}

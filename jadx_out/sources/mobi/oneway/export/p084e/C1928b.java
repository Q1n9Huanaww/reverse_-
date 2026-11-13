package mobi.oneway.export.p084e;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: mobi.oneway.export.e.b */
/* JADX INFO: loaded from: classes.dex */
public class C1928b {

    /* JADX INFO: renamed from: a */
    private static List<Runnable> f4933a;

    /* JADX INFO: renamed from: c */
    private static C1928b f4934c;

    /* JADX INFO: renamed from: d */
    private static Handler f4935d = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: b */
    private volatile boolean f4936b;

    private C1928b() {
    }

    /* JADX INFO: renamed from: a */
    public static C1928b m4863a() {
        if (f4934c == null) {
            f4934c = new C1928b();
        }
        return f4934c;
    }

    /* JADX INFO: renamed from: e */
    private static void m4864e() {
        if (f4933a == null) {
            return;
        }
        Iterator<Runnable> it2 = f4933a.iterator();
        while (it2.hasNext()) {
            f4935d.post(it2.next());
        }
        f4933a.clear();
    }

    /* JADX INFO: renamed from: f */
    private void m4865f() {
        if (f4933a == null) {
            f4933a = new ArrayList();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m4866a(Runnable runnable) {
        try {
            if (this.f4936b) {
                f4935d.post(runnable);
            } else {
                m4865f();
                f4933a.add(runnable);
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    public void m4867b() {
        this.f4936b = false;
        m4865f();
    }

    /* JADX INFO: renamed from: b */
    public void m4868b(Runnable runnable) {
        try {
            f4933a.remove(runnable);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    public void m4869c() {
        this.f4936b = true;
        m4864e();
    }

    /* JADX INFO: renamed from: c */
    public void m4870c(Runnable runnable) {
        f4935d.post(runnable);
    }

    /* JADX INFO: renamed from: d */
    public boolean m4871d() {
        return this.f4936b;
    }
}

package mobi.oneway.export.p082c;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p083d.C1926f;
import mobi.oneway.export.p087g.C1954h;

/* JADX INFO: renamed from: mobi.oneway.export.c.b */
/* JADX INFO: loaded from: classes.dex */
public class RunnableC1918b implements Runnable {

    /* JADX INFO: renamed from: a */
    private File f4877a;

    public RunnableC1918b(File file) {
        this.f4877a = file;
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            new C1926f(C1899a.f4822e, C1899a.f4820c).m4822a(C1954h.m5027i(this.f4877a)).m4836c("POST").m4848n();
            this.f4877a.delete();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

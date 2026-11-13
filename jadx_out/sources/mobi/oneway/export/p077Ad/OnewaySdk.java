package mobi.oneway.export.p077Ad;

import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import mobi.oneway.export.C1898a;
import mobi.oneway.export.p078a.C1900b;
import mobi.oneway.export.p082c.C1919c;
import mobi.oneway.export.p084e.C1927a;
import mobi.oneway.export.p084e.C1928b;
import mobi.oneway.export.p087g.C1959m;

/* JADX INFO: loaded from: classes.dex */
public class OnewaySdk {

    /* JADX INFO: renamed from: a */
    private static AtomicBoolean f4811a = new AtomicBoolean(false);

    public static void checkSdkConfigured() {
        if (isConfigured()) {
            return;
        }
        C1959m.m5053d(" OnewaySdk.configure method has not invoked first ");
    }

    public static void configure(Context context, String str) {
        if (!f4811a.compareAndSet(false, true)) {
            C1959m.m5053d("config method has been called");
            return;
        }
        if (context == null) {
            C1959m.m5053d("Context must not be null");
            return;
        }
        C1900b.m4708a(str);
        C1900b.m4707a(context.getApplicationContext());
        C1919c.m4779a().m4780a(context);
        C1928b.m4863a().m4867b();
        new C1927a(str).start();
    }

    public static String getVersion() {
        return C1898a.f4817f;
    }

    public static boolean isConfigured() {
        return f4811a.get();
    }

    public static void setDebugMode(boolean z) {
        C1959m.m5046a(z);
    }
}

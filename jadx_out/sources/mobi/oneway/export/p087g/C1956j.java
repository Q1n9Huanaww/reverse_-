package mobi.oneway.export.p087g;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.Closeable;
import java.io.IOException;
import java.net.HttpURLConnection;

/* JADX INFO: renamed from: mobi.oneway.export.g.j */
/* JADX INFO: loaded from: classes.dex */
public class C1956j {
    /* JADX INFO: renamed from: a */
    private static void m5035a(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m5036a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection == null) {
            return;
        }
        httpURLConnection.disconnect();
    }

    /* JADX INFO: renamed from: a */
    public static void m5037a(Closeable... closeableArr) {
        if (closeableArr == null) {
            return;
        }
        for (Closeable closeable : closeableArr) {
            m5035a(closeable);
        }
    }
}

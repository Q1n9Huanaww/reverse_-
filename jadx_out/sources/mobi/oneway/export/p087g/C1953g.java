package mobi.oneway.export.p087g;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: renamed from: mobi.oneway.export.g.g */
/* JADX INFO: loaded from: classes.dex */
public class C1953g {
    /* JADX INFO: renamed from: a */
    public static String m4998a(Exception exc) {
        return exc.getMessage();
    }

    /* JADX INFO: renamed from: a */
    public static String m4999a(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        try {
            ThrowableExtension.printStackTrace(th, printWriter);
            return stringWriter.toString();
        } finally {
            printWriter.close();
        }
    }
}

package com.umeng.commonsdk.proguard;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.PrintWriter;
import java.io.StringWriter;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.f */
/* JADX INFO: compiled from: UMCrashUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C1783f {
    /* JADX INFO: renamed from: a */
    public static String m3973a(Throwable th) {
        if (th == null) {
            return null;
        }
        try {
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            ThrowableExtension.printStackTrace(th, printWriter);
            for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                ThrowableExtension.printStackTrace(cause, printWriter);
            }
            String string = stringWriter.toString();
            try {
                printWriter.close();
                stringWriter.close();
                return string;
            } catch (Exception unused) {
                return string;
            }
        } catch (Exception unused2) {
            return null;
        }
    }
}

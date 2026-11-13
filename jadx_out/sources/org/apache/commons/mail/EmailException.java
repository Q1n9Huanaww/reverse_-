package org.apache.commons.mail;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/* JADX INFO: loaded from: classes.dex */
public class EmailException extends Exception {
    private static final long serialVersionUID = 5550674499282474616L;

    public EmailException() {
    }

    public EmailException(String str) {
        super(str);
    }

    public EmailException(Throwable th) {
        super(th);
    }

    public EmailException(String str, Throwable th) {
        super(str, th);
    }

    @Override // java.lang.Throwable
    public void printStackTrace() {
        ThrowableExtension.printStackTrace(this, System.err);
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintStream printStream) {
        synchronized (printStream) {
            PrintWriter printWriter = new PrintWriter((OutputStream) printStream, false);
            ThrowableExtension.printStackTrace(this, printWriter);
            printWriter.flush();
        }
    }

    @Override // java.lang.Throwable
    public void printStackTrace(PrintWriter printWriter) {
        synchronized (printWriter) {
            super.printStackTrace(printWriter);
        }
    }
}

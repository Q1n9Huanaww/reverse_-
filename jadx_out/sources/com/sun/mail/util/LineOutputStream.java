package com.sun.mail.util;

import com.umeng.commonsdk.proguard.C1770bg;
import java.io.FilterOutputStream;
import java.io.OutputStream;
import javax.mail.MessagingException;

/* JADX INFO: loaded from: classes.dex */
public class LineOutputStream extends FilterOutputStream {
    private static byte[] newline = {C1770bg.f4123k, 0};

    static {
        newline[1] = 10;
    }

    public LineOutputStream(OutputStream outputStream) {
        super(outputStream);
    }

    public void writeln(String str) throws MessagingException {
        try {
            this.out.write(ASCIIUtility.getBytes(str));
            this.out.write(newline);
        } catch (Exception e) {
            throw new MessagingException("IOException", e);
        }
    }

    public void writeln() throws MessagingException {
        try {
            this.out.write(newline);
        } catch (Exception e) {
            throw new MessagingException("IOException", e);
        }
    }
}

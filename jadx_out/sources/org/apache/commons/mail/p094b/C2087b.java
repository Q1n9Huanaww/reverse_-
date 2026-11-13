package org.apache.commons.mail.p094b;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

/* JADX INFO: renamed from: org.apache.commons.mail.b.b */
/* JADX INFO: compiled from: MimeMessageUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C2087b {
    private C2087b() {
    }

    /* JADX INFO: renamed from: a */
    private static MimeMessage m5145a(Session session, byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                MimeMessage mimeMessage = new MimeMessage(session, byteArrayInputStream2);
                byteArrayInputStream2.close();
                return mimeMessage;
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = byteArrayInputStream2;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: a */
    private static MimeMessage m5142a(Session session, File file) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                MimeMessage mimeMessageM5143a = m5143a(session, fileInputStream2);
                fileInputStream2.close();
                return mimeMessageM5143a;
            } catch (Throwable th) {
                th = th;
                fileInputStream = fileInputStream2;
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: a */
    private static MimeMessage m5143a(Session session, InputStream inputStream) throws MessagingException {
        return new MimeMessage(session, inputStream);
    }

    /* JADX INFO: renamed from: a */
    private static MimeMessage m5144a(Session session, String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(str.getBytes());
            try {
                MimeMessage mimeMessageM5143a = m5143a(session, byteArrayInputStream2);
                byteArrayInputStream2.close();
                return mimeMessageM5143a;
            } catch (Throwable th) {
                th = th;
                byteArrayInputStream = byteArrayInputStream2;
                if (byteArrayInputStream != null) {
                    byteArrayInputStream.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m5146a(MimeMessage mimeMessage, File file) throws Throwable {
        FileOutputStream fileOutputStream = null;
        try {
            if (!file.getParentFile().exists() && !file.getParentFile().mkdirs()) {
                throw new IOException("Failed to create the following parent directories: " + file.getParentFile());
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                mimeMessage.writeTo(fileOutputStream2);
                fileOutputStream2.flush();
                fileOutputStream2.close();
            } catch (Throwable th) {
                th = th;
                fileOutputStream = fileOutputStream2;
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}

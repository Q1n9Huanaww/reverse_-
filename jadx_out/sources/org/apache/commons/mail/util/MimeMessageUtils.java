package org.apache.commons.mail.util;

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

/* JADX INFO: loaded from: classes.dex */
public final class MimeMessageUtils {
    private MimeMessageUtils() {
    }

    public static MimeMessage createMimeMessage(Session session, byte[] bArr) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bArr);
            try {
                MimeMessage mimeMessage = new MimeMessage(session, byteArrayInputStream2);
                if (byteArrayInputStream2 != null) {
                    byteArrayInputStream2.close();
                }
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

    public static MimeMessage createMimeMessage(Session session, File file) throws Throwable {
        FileInputStream fileInputStream = null;
        try {
            FileInputStream fileInputStream2 = new FileInputStream(file);
            try {
                MimeMessage mimeMessageCreateMimeMessage = createMimeMessage(session, fileInputStream2);
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                }
                return mimeMessageCreateMimeMessage;
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

    public static MimeMessage createMimeMessage(Session session, InputStream inputStream) throws MessagingException {
        return new MimeMessage(session, inputStream);
    }

    public static MimeMessage createMimeMessage(Session session, String str) throws Throwable {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(str.getBytes());
            try {
                MimeMessage mimeMessageCreateMimeMessage = createMimeMessage(session, byteArrayInputStream2);
                if (byteArrayInputStream2 != null) {
                    byteArrayInputStream2.close();
                }
                return mimeMessageCreateMimeMessage;
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

    public static void writeMimeMessage(MimeMessage mimeMessage, File file) throws Throwable {
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

package mobi.oneway.export.p087g;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: renamed from: mobi.oneway.export.g.b */
/* JADX INFO: loaded from: classes.dex */
public class C1948b {
    /* JADX INFO: renamed from: a */
    public static String m4968a(String str) {
        return new String(m4974a(str.getBytes("utf-8")), "ASCII");
    }

    /* JADX INFO: renamed from: a */
    public static String m4969a(String str, String str2) {
        try {
            try {
                return new String(m4974a(str.getBytes(str2)), "ASCII");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("ASCII is not supported!", e);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("Unsupported charset: " + str2, e2);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4970a(File file, File file2) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    m4972a(fileInputStream, fileOutputStream2);
                    C1956j.m5037a(fileOutputStream2);
                    C1956j.m5037a(fileInputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    C1956j.m5037a(fileOutputStream);
                    C1956j.m5037a(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4971a(File file, File file2, int i) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    m4973a(fileInputStream, fileOutputStream2, i);
                    C1956j.m5037a(fileOutputStream2);
                    C1956j.m5037a(fileInputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    C1956j.m5037a(fileOutputStream);
                    C1956j.m5037a(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4972a(InputStream inputStream, OutputStream outputStream) throws IOException {
        m4973a(inputStream, outputStream, 0);
    }

    /* JADX INFO: renamed from: a */
    public static void m4973a(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        C1950d c1950d = new C1950d(outputStream, i);
        m4981c(inputStream, c1950d);
        c1950d.m4983a();
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m4974a(byte[] bArr) {
        return m4975a(bArr, 0);
    }

    /* JADX INFO: renamed from: a */
    public static byte[] m4975a(byte[] bArr, int i) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                m4973a(byteArrayInputStream, byteArrayOutputStream, i);
                C1956j.m5037a(byteArrayInputStream, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("Unexpected I/O error", e);
            }
        } catch (Throwable th) {
            C1956j.m5037a(byteArrayInputStream, byteArrayOutputStream);
            throw th;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m4976b(String str) {
        try {
            return new String(m4980b(str.getBytes("ASCII")));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("ASCII is not supported!", e);
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m4977b(String str, String str2) {
        try {
            try {
                return new String(m4980b(str.getBytes("ASCII")), str2);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException("Unsupported charset: " + str2, e);
            }
        } catch (UnsupportedEncodingException e2) {
            throw new RuntimeException("ASCII is not supported!", e2);
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m4978b(File file, File file2) throws Throwable {
        FileInputStream fileInputStream;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                FileOutputStream fileOutputStream2 = new FileOutputStream(file2);
                try {
                    m4979b(fileInputStream, fileOutputStream2);
                    C1956j.m5037a(fileOutputStream2);
                    C1956j.m5037a(fileInputStream);
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = fileOutputStream2;
                    C1956j.m5037a(fileOutputStream);
                    C1956j.m5037a(fileInputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            fileInputStream = null;
        }
    }

    /* JADX INFO: renamed from: b */
    public static void m4979b(InputStream inputStream, OutputStream outputStream) throws IOException {
        m4981c(new C1949c(inputStream), outputStream);
    }

    /* JADX INFO: renamed from: b */
    public static byte[] m4980b(byte[] bArr) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            try {
                m4979b(byteArrayInputStream, byteArrayOutputStream);
                C1956j.m5037a(byteArrayInputStream);
                C1956j.m5037a(byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new RuntimeException("Unexpected I/O error", e);
            }
        } catch (Throwable th) {
            C1956j.m5037a(byteArrayInputStream);
            C1956j.m5037a(byteArrayOutputStream);
            throw th;
        }
    }

    /* JADX INFO: renamed from: c */
    private static void m4981c(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }
}

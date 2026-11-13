package com.goldcoast.sdk.p027c;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.e */
/* JADX INFO: compiled from: FileUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1123e {

    /* JADX INFO: renamed from: a */
    private static Method f1794a;

    /* JADX INFO: renamed from: b */
    private static final Pattern f1795b;

    static {
        try {
            Method method = Class.forName("android.os.FileUtils").getMethod("setPermissions", String.class, Integer.TYPE, Integer.TYPE, Integer.TYPE);
            f1794a = method;
            method.setAccessible(true);
        } catch (Exception unused) {
        }
        f1795b = Pattern.compile("[\\w%+,./=_-]+");
    }

    /* JADX INFO: renamed from: a */
    public static int m1421a(File file) {
        try {
            return ((Integer) f1794a.invoke(null, file.getAbsolutePath(), 493, -1, -1)).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX INFO: renamed from: a */
    public static int m1422a(String str, int i) {
        try {
            return ((Integer) f1794a.invoke(null, str, Integer.valueOf(i), -1, -1)).intValue();
        } catch (Exception unused) {
            return -1;
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m1424a(File file, File file2) {
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                return m1425a(fileInputStream, file2);
            } finally {
                fileInputStream.close();
            }
        } catch (IOException unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1425a(InputStream inputStream, File file) {
        FileOutputStream fileOutputStream;
        byte[] bArr;
        if (file.exists()) {
            file.delete();
        }
        try {
            try {
                try {
                    file.createNewFile();
                    while (true) {
                        int i = inputStream.read(bArr);
                        if (i >= 0) {
                            fileOutputStream.write(bArr, 0, i);
                        } else {
                            fileOutputStream.flush();
                            fileOutputStream.getFD().sync();
                            fileOutputStream.close();
                            return true;
                        }
                    }
                } catch (IOException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                bArr = new byte[4096];
            } catch (Exception e2) {
                e = e2;
                ThrowableExtension.printStackTrace(e);
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3) {
                        ThrowableExtension.printStackTrace(e3);
                    }
                }
                return false;
            }
            fileOutputStream = new FileOutputStream(file);
        } catch (Exception e4) {
            e = e4;
            fileOutputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m1423a(String[] strArr, File file) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            StringBuilder sb = new StringBuilder("#!/system/bin/sh\n");
            for (String str : strArr) {
                sb.append(String.format("%s\n", str));
            }
            fileWriter.write(sb.toString());
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

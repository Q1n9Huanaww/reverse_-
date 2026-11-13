package com.goldcoast.sdk.p027c;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.b */
/* JADX INFO: compiled from: CPUTypeUtil.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1120b {

    /* JADX INFO: renamed from: a */
    private static boolean f1787a;

    /* JADX INFO: renamed from: a */
    public static String m1406a() {
        byte[] bArrM1408a;
        byte[] bArrM1408a2;
        if (m1407a("ro.product.cpu.abilist64", "").length() > 0 || m1409b()) {
            return "64";
        }
        File file = new File("/system/lib/libc.so");
        boolean z = true;
        if (!file.exists() || (bArrM1408a2 = m1408a(file)) == null || bArrM1408a2[4] != 2) {
            File file2 = new File("/system/lib64/libc.so");
            if (!file2.exists() || (bArrM1408a = m1408a(file2)) == null || bArrM1408a[4] != 2) {
                z = false;
            }
        }
        return z ? "64" : "32";
    }

    /* JADX INFO: renamed from: a */
    private static String m1407a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            return (String) cls.getMethod("get", String.class, String.class).invoke(cls, str, "");
        } catch (Exception unused) {
            return str2;
        }
    }

    /* JADX INFO: renamed from: b */
    private static boolean m1409b() throws Throwable {
        FileInputStream fileInputStream;
        File file = new File("/proc/cpuinfo");
        if (!file.exists()) {
            return false;
        }
        BufferedReader bufferedReader = null;
        try {
            try {
                fileInputStream = new FileInputStream(file);
                try {
                    BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(fileInputStream), 512);
                    try {
                        String line = bufferedReader2.readLine();
                        if (line == null || line.length() <= 0 || !line.toLowerCase(Locale.US).contains("arch64")) {
                            try {
                                bufferedReader2.close();
                            } catch (Exception e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                            fileInputStream.close();
                            return false;
                        }
                        try {
                            bufferedReader2.close();
                        } catch (Exception e2) {
                            ThrowableExtension.printStackTrace(e2);
                        }
                        try {
                            fileInputStream.close();
                            return true;
                        } catch (Exception e3) {
                            ThrowableExtension.printStackTrace(e3);
                            return true;
                        }
                    } catch (Throwable th) {
                        bufferedReader = bufferedReader2;
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e4) {
                                ThrowableExtension.printStackTrace(e4);
                            }
                        }
                        if (fileInputStream == null) {
                            throw th;
                        }
                        try {
                            fileInputStream.close();
                            throw th;
                        } catch (Exception e5) {
                            ThrowableExtension.printStackTrace(e5);
                            throw th;
                        }
                    }
                } catch (Throwable unused) {
                }
            } catch (Exception e6) {
                ThrowableExtension.printStackTrace(e6);
                return false;
            }
        } catch (Throwable unused2) {
            fileInputStream = null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static byte[] m1408a(File file) throws Throwable {
        FileInputStream fileInputStream;
        try {
            if (file.exists()) {
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        byte[] bArr = new byte[16];
                        if (fileInputStream.read(bArr, 0, 16) == 16) {
                            try {
                                fileInputStream.close();
                            } catch (Exception e) {
                                ThrowableExtension.printStackTrace(e);
                            }
                            return bArr;
                        }
                        fileInputStream.close();
                    } catch (Throwable unused) {
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        return null;
                    }
                } catch (Throwable unused2) {
                    fileInputStream = null;
                }
            }
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        return null;
    }
}

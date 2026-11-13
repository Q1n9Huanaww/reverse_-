package com.cyjh.p019mq.p023d;

import android.os.Environment;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: renamed from: com.cyjh.mq.d.a */
/* JADX INFO: compiled from: CLog.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0836a {

    /* JADX INFO: renamed from: a */
    private static final boolean f1607a = false;

    /* JADX INFO: renamed from: b */
    private static final String f1608b = "IPC_ANDROID";

    /* JADX INFO: renamed from: a */
    private static boolean m1271a() {
        return false;
    }

    /* JADX INFO: renamed from: c */
    private static void m1273c() {
    }

    /* JADX INFO: renamed from: d */
    private static void m1274d() {
    }

    /* JADX INFO: renamed from: e */
    private static void m1275e() {
    }

    /* JADX INFO: renamed from: f */
    private static void m1276f() {
    }

    /* JADX INFO: renamed from: g */
    private static void m1277g() {
    }

    /* JADX INFO: renamed from: h */
    private static void m1278h() {
    }

    /* JADX INFO: renamed from: i */
    private static void m1279i() {
    }

    /* JADX INFO: renamed from: j */
    private static void m1280j() {
    }

    /* JADX INFO: renamed from: k */
    private static void m1281k() {
    }

    /* JADX INFO: renamed from: l */
    private static void m1282l() {
    }

    /* JADX INFO: renamed from: b */
    private static String m1272b() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(C0836a.class.getName())) {
                return "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")";
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static String m1270a(String str) {
        String str2 = Environment.getExternalStorageDirectory().getPath() + File.separator + "rootAbort" + File.separator;
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date(System.currentTimeMillis())) + ":" + str);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2 + "save.log", true);
            fileOutputStream.write(sb.toString().getBytes());
            fileOutputStream.close();
            return "save.log";
        } catch (Exception unused) {
            return str2;
        }
    }
}

package com.iflytek.voiceads.utils;

import android.content.Context;
import android.os.Environment;
import com.iflytek.voiceads.config.SDKConstants;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1592f {

    /* JADX INFO: renamed from: a */
    private static Context f3417a;

    /* JADX INFO: renamed from: b */
    private static C1592f f3418b;

    /* JADX INFO: renamed from: c */
    private static File f3419c;

    /* JADX INFO: renamed from: d */
    private static SimpleDateFormat f3420d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    /* JADX INFO: renamed from: a */
    private static long m3182a(File file) {
        if (!file.exists()) {
            return 0L;
        }
        try {
            return new FileInputStream(file).available();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "getFileSize:" + e.getMessage());
            return 0L;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m3183a() {
        File file = new File(f3419c.getParent() + "/ifly_ad_lastLog.txt");
        if (file.exists()) {
            file.delete();
        }
        f3419c.renameTo(file);
        try {
            f3419c.createNewFile();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "resetLogFile:" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m3184a(Context context) {
        if (f3417a == null || f3418b == null || f3419c == null || !f3419c.exists()) {
            f3417a = context;
            f3418b = new C1592f();
            f3419c = m3186b();
            if (52428800 < m3182a(f3419c)) {
                m3183a();
            }
        }
    }

    /* JADX WARN: Failed to analyze thrown exceptions
    java.util.ConcurrentModificationException
    	at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1095)
    	at java.base/java.util.ArrayList$Itr.next(ArrayList.java:1049)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:130)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.checkInsn(MethodThrowsVisitor.java:178)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.processInstructions(MethodThrowsVisitor.java:131)
    	at jadx.core.dex.visitors.MethodThrowsVisitor.visit(MethodThrowsVisitor.java:68)
     */
    /* JADX INFO: renamed from: a */
    public static void m3185a(Object obj) {
        if (f3417a == null || f3418b == null || f3419c == null || !f3419c.exists()) {
            return;
        }
        String str = m3187c() + " - " + obj.toString();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f3419c, true));
            bufferedWriter.write(str);
            bufferedWriter.write(IOUtils.LINE_SEPARATOR_WINDOWS);
            bufferedWriter.flush();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "write:" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: b */
    private static File m3186b() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "IFlyAdDownload" + File.separator);
        if (!file.exists()) {
            file.mkdir();
        }
        File file2 = new File(file.getPath() + "/ifly_ad_logs.txt");
        if (!file2.exists()) {
            try {
                file2.createNewFile();
            } catch (Exception e) {
                C1593g.m3190b(SDKConstants.TAG, "getLogFile:" + e.getMessage());
            }
        }
        return file2;
    }

    /* JADX INFO: renamed from: c */
    private static String m3187c() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            return null;
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(f3418b.getClass().getName())) {
                return "[" + f3420d.format(new Date()) + "]";
            }
        }
        return null;
    }
}

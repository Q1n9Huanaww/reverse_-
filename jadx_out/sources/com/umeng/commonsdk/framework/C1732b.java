package com.umeng.commonsdk.framework;

import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Process;
import android.text.TextUtils;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.internal.C1842a;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.umeng.commonsdk.framework.b */
/* JADX INFO: compiled from: UMFrUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C1732b {

    /* JADX INFO: renamed from: a */
    private static final String f3951a = "last_successful_build_time";

    /* JADX INFO: renamed from: c */
    private static String f3953c = "envelope";

    /* JADX INFO: renamed from: d */
    private static String f3954d;

    /* JADX INFO: renamed from: b */
    private static Object f3952b = new Object();

    /* JADX INFO: renamed from: e */
    private static Object f3955e = new Object();

    /* JADX INFO: renamed from: a */
    public static String m3715a(Context context) {
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses;
        try {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null || (runningAppProcesses = activityManager.getRunningAppProcesses()) == null || runningAppProcesses.size() <= 0) {
                return "";
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return runningAppProcessInfo.processName;
                }
            }
            return "";
        } catch (Throwable th) {
            C1782e.m3972a(context.getApplicationContext(), th);
            return "";
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m3717a(Context context, String str) {
        if (context == null) {
            return false;
        }
        Context applicationContext = context.getApplicationContext();
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                if (((Integer) Class.forName("android.content.Context").getMethod("checkSelfPermission", String.class).invoke(context, str)).intValue() != 0) {
                    return false;
                }
            } catch (Throwable th) {
                C1782e.m3972a(applicationContext, th);
                return false;
            }
        } else {
            try {
                if (applicationContext.getPackageManager().checkPermission(str, applicationContext.getPackageName()) != 0) {
                    return false;
                }
            } catch (Throwable th2) {
                C1782e.m3972a(applicationContext, th2);
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3721b(Context context) {
        ConnectivityManager connectivityManager;
        NetworkInfo activeNetworkInfo;
        try {
            if (!m3717a(context, "android.permission.ACCESS_NETWORK_STATE") || (connectivityManager = (ConnectivityManager) context.getSystemService("connectivity")) == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null) {
                return false;
            }
            return activeNetworkInfo.isConnectedOrConnecting();
        } catch (Throwable th) {
            C1782e.m3972a(context.getApplicationContext(), th);
            return false;
        }
    }

    /* JADX INFO: renamed from: c */
    public static int m3722c(Context context) {
        String[] list;
        if (context == null) {
            return 0;
        }
        try {
            File file = new File(m3728h(context));
            synchronized (f3955e) {
                if (!file.isDirectory() || (list = file.list()) == null) {
                    return 0;
                }
                return list.length;
            }
        } catch (Throwable th) {
            C1782e.m3972a(context, th);
        }
    }

    /* JADX INFO: renamed from: a */
    private static long m3714a(long j, long j2) {
        return (j < j2 ? j2 - j : j - j2) / 86400000;
    }

    /* JADX INFO: renamed from: d */
    public static void m3724d(final Context context) {
        File file = new File(m3728h(context));
        synchronized (f3955e) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length > 100) {
                Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.umeng.commonsdk.framework.b.1
                    @Override // java.util.Comparator
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        String name = file2.getName();
                        String name2 = file3.getName();
                        String strM3723c = C1732b.m3723c(name);
                        String strM3723c2 = C1732b.m3723c(name2);
                        if (TextUtils.isEmpty(strM3723c) || TextUtils.isEmpty(strM3723c2)) {
                            return 1;
                        }
                        try {
                            long jLongValue = Long.valueOf(strM3723c).longValue() - Long.valueOf(strM3723c2).longValue();
                            if (jLongValue > 0) {
                                return 1;
                            }
                            return jLongValue == 0 ? 0 : -1;
                        } catch (NumberFormatException e) {
                            C1782e.m3972a(context, e);
                            return 1;
                        }
                    }
                });
                if (fileArrListFiles.length > 100) {
                    try {
                        C1817e.m4400b("--->>> biger than 10");
                        for (int i = 0; i < fileArrListFiles.length - 100; i++) {
                            if (!fileArrListFiles[i].delete()) {
                                C1817e.m4400b("--->>> remove [" + i + "] file fail.");
                            }
                        }
                    } catch (Throwable th) {
                        C1782e.m3972a(context, th);
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public static String m3723c(String str) {
        Context contextM3733a = C1733c.m3733a();
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        int iIndexOf = str.indexOf(95) + 1;
        try {
            return str.substring(iIndexOf, str.indexOf(95, iIndexOf));
        } catch (IndexOutOfBoundsException e) {
            C1782e.m3972a(contextM3733a, e);
            return "";
        }
    }

    /* JADX INFO: renamed from: e */
    public static File m3725e(final Context context) {
        if (context == null) {
            return null;
        }
        File file = new File(m3728h(context));
        synchronized (f3955e) {
            File[] fileArrListFiles = file.listFiles();
            if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                Arrays.sort(fileArrListFiles, new Comparator<File>() { // from class: com.umeng.commonsdk.framework.b.2
                    @Override // java.util.Comparator
                    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
                    public int compare(File file2, File file3) {
                        String name = file2.getName();
                        String name2 = file3.getName();
                        String strM3723c = C1732b.m3723c(name);
                        String strM3723c2 = C1732b.m3723c(name2);
                        if (TextUtils.isEmpty(strM3723c) || TextUtils.isEmpty(strM3723c2)) {
                            return 1;
                        }
                        try {
                            long jLongValue = Long.valueOf(strM3723c).longValue() - Long.valueOf(strM3723c2).longValue();
                            if (jLongValue > 0) {
                                return 1;
                            }
                            return jLongValue == 0 ? 0 : -1;
                        } catch (NumberFormatException e) {
                            C1782e.m3972a(context, e);
                            return 1;
                        }
                    }
                });
                return fileArrListFiles[0];
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: f */
    public static void m3726f(Context context) {
        if (context == null) {
            return;
        }
        try {
            String strM3727g = m3727g(context);
            if (TextUtils.isEmpty(strM3727g) || strM3727g.equals(f3953c)) {
                return;
            }
            File file = new File(context.getFilesDir().getAbsolutePath() + "/." + strM3727g);
            if (file.exists()) {
                File[] fileArrListFiles = file.listFiles();
                if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                    try {
                        if (file.isDirectory()) {
                            file.delete();
                            return;
                        }
                        return;
                    } catch (Throwable th) {
                        C1782e.m3972a(context, th);
                        return;
                    }
                }
                try {
                    String strM3728h = m3728h(context);
                    for (int i = 0; i < fileArrListFiles.length; i++) {
                        fileArrListFiles[i].renameTo(new File(strM3728h + InternalZipConstants.ZIP_FILE_SEPARATOR + fileArrListFiles[i].getName()));
                    }
                    if (file.isDirectory()) {
                        file.delete();
                        return;
                    }
                    return;
                } catch (Throwable th2) {
                    C1782e.m3972a(context, th2);
                    return;
                }
                C1782e.m3972a(context, th);
            }
        } catch (Throwable th3) {
            C1782e.m3972a(context, th3);
        }
    }

    /* JADX INFO: renamed from: g */
    public static String m3727g(Context context) throws Throwable {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (activityManager != null) {
            List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses == null) {
                C1817e.m4400b("--->>> getEnvelopeDir: can't get process name, use default envelope directory.");
                return f3953c;
            }
            if (runningAppProcesses.size() == 0) {
                return f3953c;
            }
            try {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.pid == Process.myPid()) {
                        String strReplace = runningAppProcessInfo.processName.replace(':', '_');
                        C1817e.m4400b("--->>> getEnvelopeDir: use current process name as envelope directory.");
                        return strReplace;
                    }
                }
            } catch (Throwable th) {
                C1782e.m3972a(context, th);
            }
        }
        return f3953c;
    }

    /* JADX INFO: renamed from: h */
    public static String m3728h(Context context) {
        String str;
        synchronized (f3955e) {
            try {
                if (f3954d == null) {
                    f3954d = context.getFilesDir().getAbsolutePath() + "/." + f3953c;
                }
                File file = new File(f3954d);
                if (!file.exists() && !file.mkdir()) {
                    C1817e.m4400b("--->>> Create Envelope Directory failed!!!");
                }
            } catch (Throwable th) {
                C1782e.m3972a(context, th);
            }
            str = f3954d;
        }
        return str;
    }

    /* JADX INFO: renamed from: i */
    public static long m3729i(Context context) {
        long j;
        synchronized (f3952b) {
            j = PreferenceWrapper.getDefault(context).getLong(f3951a, 0L);
        }
        return j;
    }

    /* JADX INFO: renamed from: j */
    private static void m3730j(Context context) {
        synchronized (f3952b) {
            SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(context);
            sharedPreferences.edit().putLong(f3951a, System.currentTimeMillis()).commit();
        }
    }

    /* JADX INFO: renamed from: a */
    public static int m3713a(Context context, String str, byte[] bArr) {
        if (bArr == null) {
            return 101;
        }
        File file = new File(m3728h(context) + InternalZipConstants.ZIP_FILE_SEPARATOR + str);
        synchronized (f3955e) {
            FileOutputStream fileOutputStream = null;
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        fileOutputStream2.write(bArr);
                        fileOutputStream2.close();
                        if (C1842a.m4491a(context).m4495a(str)) {
                            m3730j(context);
                        }
                        return 0;
                    } catch (IOException e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        C1782e.m3972a(context, e);
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th) {
                                C1782e.m3972a(context, th);
                            }
                        }
                        return 101;
                    } catch (Throwable th2) {
                        th = th2;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (Throwable th3) {
                                C1782e.m3972a(context, th3);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e2) {
                    e = e2;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3718a(File file) {
        Context contextM3733a = C1733c.m3733a();
        synchronized (f3955e) {
            try {
                if (file != null) {
                    try {
                        if (file.exists()) {
                            return file.delete();
                        }
                    } catch (Throwable th) {
                        C1782e.m3972a(contextM3733a, th);
                    }
                }
                return true;
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v5 */
    /* JADX INFO: renamed from: a */
    public static byte[] m3719a(String str) throws IOException {
        IOException e;
        byte[] bArr;
        Context contextM3733a = C1733c.m3733a();
        synchronized (f3955e) {
            try {
                try {
                    FileChannel channel = new RandomAccessFile(str, "r").getChannel();
                    try {
                        MappedByteBuffer mappedByteBufferLoad = channel.map(FileChannel.MapMode.READ_ONLY, 0L, channel.size()).load();
                        System.out.println(mappedByteBufferLoad.isLoaded());
                        bArr = new byte[(int) channel.size()];
                        if (mappedByteBufferLoad.remaining() > 0) {
                            mappedByteBufferLoad.get(bArr, 0, mappedByteBufferLoad.remaining());
                        }
                        try {
                            channel.close();
                        } catch (Throwable th) {
                            C1782e.m3972a(contextM3733a, th);
                        }
                    } catch (IOException e2) {
                        e = e2;
                        C1782e.m3972a(contextM3733a, e);
                        throw e;
                    }
                } catch (IOException e3) {
                    e = e3;
                } catch (Throwable th2) {
                    th = th2;
                    str = 0;
                    try {
                        str.close();
                    } catch (Throwable th3) {
                        C1782e.m3972a(contextM3733a, th3);
                    }
                    throw th;
                }
            } catch (Throwable th4) {
                th = th4;
            }
        }
        return bArr;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3716a(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        String str = C1799v.f4296ah;
        if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_DPLUS) {
            str = C1799v.f4297ai;
        } else if (uMBusinessType == UMLogDataProtocol.UMBusinessType.U_INTERNAL) {
            str = C1799v.f4301am;
        }
        File file = new File(m3728h(context));
        synchronized (f3955e) {
            try {
                try {
                    File[] fileArrListFiles = file.listFiles();
                    if (fileArrListFiles != null && fileArrListFiles.length != 0) {
                        for (File file2 : fileArrListFiles) {
                            if (file2.getName().startsWith(str)) {
                                return true;
                            }
                        }
                        return false;
                    }
                    return false;
                } catch (Throwable th) {
                    C1782e.m3972a(context, th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }
}

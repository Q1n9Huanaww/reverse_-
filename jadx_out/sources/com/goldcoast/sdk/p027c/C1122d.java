package com.goldcoast.sdk.p027c;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.goldcoast.sdk.p025a.C1110a;
import com.goldcoast.sdk.p025a.C1111b;
import com.goldcoast.sdk.p025a.C1114e;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.goldcoast.sdk.c.d */
/* JADX INFO: compiled from: DeviceUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1122d {

    /* JADX INFO: renamed from: a */
    private static C1122d f1792a;

    /* JADX INFO: renamed from: b */
    private static Context f1793b;

    private C1122d() {
    }

    /* JADX INFO: renamed from: a */
    public static C1122d m1416a(Context context) {
        f1793b = context;
        if (f1792a == null) {
            f1792a = new C1122d();
        }
        return f1792a;
    }

    /* JADX INFO: renamed from: a */
    public static C1111b m1415a() {
        String str;
        C1111b c1111b = new C1111b();
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        String str2 = "";
        C1114e c1114eM1418c = m1418c();
        try {
            map2.put("MemTotal", "");
            C1124f.m1426a(new FileInputStream("/proc/meminfo"), map2);
            str = (String) map2.get("MemTotal");
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            str = "";
        }
        C1110a c1110a = null;
        try {
            str2 = C1124f.m1426a(new FileInputStream("/proc/version"), null).split(" ")[2];
        } catch (FileNotFoundException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        try {
            C1124f.m1426a(new FileInputStream("/proc/cpuinfo"), map);
            c1110a = new C1110a(map);
        } catch (FileNotFoundException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
        c1111b.m1361a(c1110a);
        c1111b.m1362a(c1114eM1418c);
        c1111b.m1366b(str2);
        c1111b.m1364a(str);
        c1111b.m1367c(m1419d());
        c1111b.m1369e(m1417b());
        c1111b.m1368d(m1420e());
        c1111b.m1371g(C1120b.m1406a());
        c1111b.m1370f(String.valueOf(System.currentTimeMillis()));
        return c1111b;
    }

    /* JADX INFO: renamed from: d */
    private static String m1419d() {
        try {
            return ((TelephonyManager) f1793b.getSystemService(URLConstant.USER_ACCOUNT)).getDeviceId();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return AccountManager.TYPE_LOGIN;
        }
    }

    /* JADX INFO: renamed from: b */
    public static String m1417b() {
        try {
            return Settings.Secure.getString(f1793b.getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return AccountManager.TYPE_LOGIN;
        }
    }

    /* JADX INFO: renamed from: e */
    private static String m1420e() {
        try {
            return ((WifiManager) f1793b.getSystemService("wifi")).getConnectionInfo().getMacAddress();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return AccountManager.TYPE_LOGIN;
        }
    }

    /* JADX INFO: renamed from: c */
    public static C1114e m1418c() {
        HashMap map = new HashMap();
        String[] strArr = {"ro.product.brand", "ro.product.name", "ro.product.model", "ro.build.fingerprint", "ro.build.version.sdk", "ro.build.version.release", "ro.build.date", "ro.build.date.utc", "ro.boot.cpuid", "ro.btconfig.vendor", "persist.sys.timezone", "persist.sys.country", "persist.sys.language", "persist.sys.dalvik.vm.lib", "ro.build.description", "ro.runtime.firstboot", "ro.serialno", "ro.hardware", "ro.product.board", "ro.product.locale.language", "ro.product.locale.region", "ro.product.cpu.abi", "ro.board.platform", "ro.build.selinux", "ro.build.selinux.enforce"};
        for (int i = 0; i < 25; i++) {
            map.put(strArr[i], "");
        }
        for (Map.Entry entry : map.entrySet()) {
            map.put(entry.getKey(), C1127i.m1436a((String) entry.getKey()));
        }
        return new C1114e(map);
    }
}

package com.cyjh.mobileanjian.ipc.utils;

import android.os.Build;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes.dex */
public class RomUtils {

    /* JADX INFO: renamed from: a */
    private static final C0805b f1415a = new C0805b();

    public static boolean isMIUI() {
        return f1415a.m1074a((Object) "ro.miui.ui.version.name");
    }

    public static int getMUIUVersion() {
        if (!isMIUI()) {
            return -1;
        }
        String strM1073a = f1415a.m1073a("ro.miui.ui.version.name", "V0");
        if (strM1073a.equals("V8")) {
            return 8;
        }
        if (strM1073a.equals("V7")) {
            return 7;
        }
        if (strM1073a.equals("V6")) {
            return 6;
        }
        return strM1073a.equals("V5") ? 5 : 0;
    }

    public static String getMUIUDetailVersion() {
        return !isMIUI() ? "" : String.format("MIUI %s %s", f1415a.m1072a("ro.miui.ui.version.name"), Build.VERSION.INCREMENTAL);
    }

    public static boolean isFlyme() {
        return f1415a.m1074a((Object) "ro.flyme.published");
    }

    public static String getFlymeVersion() {
        return Build.DISPLAY;
    }

    public static boolean isEmui() {
        return f1415a.m1074a((Object) "ro.build.version.emui");
    }

    public static String getEmuiVersion() {
        return f1415a.m1072a("ro.build.version.emui");
    }

    public static boolean isOppoR9S() {
        String strM1073a = f1415a.m1073a("ro.product.name", "");
        String strM1073a2 = f1415a.m1073a("ro.product.model", "");
        return strM1073a != null && strM1073a.contains("R9s") && strM1073a2 != null && strM1073a2.contains("R9s");
    }

    public static boolean isHaimawanVM() {
        return f1415a.m1074a((Object) "droid4x.inited") || f1415a.m1074a((Object) "ro.droid4x.host.mac") || f1415a.m1074a((Object) "droid4x.battery.status") || f1415a.m1074a((Object) "persist.droid4x.op_alpha");
    }

    public static boolean isTiantianVM() {
        return f1415a.m1074a((Object) "ttVM.inited") || f1415a.m1074a((Object) "ttvmd.battery.status") || f1415a.m1074a((Object) "ttVM.vbox_dpi") || f1415a.m1074a((Object) "ttvmd.battery.mode");
    }

    public static boolean isNoxVM() {
        return f1415a.m1074a((Object) "nox.inited") || f1415a.m1074a((Object) "nox.vbox_dpi") || f1415a.m1074a((Object) "ro.nox.host.mac") || f1415a.m1074a((Object) "nox.battery.status");
    }

    public static boolean isXiaoyaoVM() {
        return f1415a.m1074a((Object) "microvirt.inited") || f1415a.m1074a((Object) "microvirt.channel") || f1415a.m1074a((Object) "ro.microvirt.hmac") || f1415a.m1074a((Object) "microvirt.vbox_dpi");
    }

    public static boolean isXXZS() {
        return f1415a.m1074a((Object) "ro.xxzs.DeviceId") || f1415a.m1074a((Object) "ro.xxzs.origDeviceId");
    }

    public static boolean isEmulator() {
        return isHaimawanVM() || isTiantianVM() || isNoxVM() || isXiaoyaoVM() || isXXZS();
    }

    public static String getKnownRomVersion() {
        if (isMIUI()) {
            return getMUIUDetailVersion();
        }
        if (isFlyme()) {
            return getFlymeVersion();
        }
        if (isEmui()) {
            return getEmuiVersion();
        }
        if (isHaimawanVM()) {
            return "海马玩模拟器";
        }
        if (isTiantianVM()) {
            return "天天模拟器";
        }
        if (isNoxVM()) {
            return "夜神模拟器";
        }
        if (isXiaoyaoVM()) {
            return "逍遥模拟器";
        }
        return isXXZS() ? "猩猩助手" : "undefined";
    }

    public static boolean isARMCpu() {
        byte[] bArr = new byte[20];
        try {
            return new FileInputStream(new File("/system/lib/libc.so")).read(bArr) == -1 || bArr[18] != 3;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return true;
        }
    }
}

package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import com.cyjh.mobileanjian.ipc.utils.RomUtils;
import com.cyjh.p019mq.p023d.C0839d;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.p092io.FileUtils;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.log.b */
/* JADX INFO: compiled from: MetaData.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0748b {

    /* JADX INFO: renamed from: n */
    private static C0748b f1026n = null;

    /* JADX INFO: renamed from: o */
    private static final File f1027o = new File(Environment.getExternalStorageDirectory(), ".oldmarkuser");

    /* JADX INFO: renamed from: p */
    private static final String f1028p = "com.cyjh.mobileanjian";

    /* JADX INFO: renamed from: a */
    public final String f1029a;

    /* JADX INFO: renamed from: b */
    public final String f1030b = Build.VERSION.RELEASE;

    /* JADX INFO: renamed from: c */
    public final String f1031c;

    /* JADX INFO: renamed from: d */
    public final String f1032d;

    /* JADX INFO: renamed from: e */
    public final String f1033e;

    /* JADX INFO: renamed from: f */
    public final String f1034f;

    /* JADX INFO: renamed from: g */
    public final String f1035g;

    /* JADX INFO: renamed from: h */
    public final String f1036h;

    /* JADX INFO: renamed from: i */
    public final boolean f1037i;

    /* JADX INFO: renamed from: j */
    public final String f1038j;

    /* JADX INFO: renamed from: k */
    public final String f1039k;

    /* JADX INFO: renamed from: l */
    public final boolean f1040l;

    /* JADX INFO: renamed from: m */
    public final boolean f1041m;

    /* JADX INFO: renamed from: a */
    public static void m750a(Context context, String str, String str2) {
        if (f1026n != null) {
            return;
        }
        if (str == null) {
            str = "";
        }
        if (str2 == null) {
            str2 = "";
        }
        C0748b c0748b = new C0748b(context, str, str2);
        f1026n = c0748b;
        c0748b.toString();
    }

    /* JADX INFO: renamed from: a */
    public static C0748b m747a() {
        return f1026n;
    }

    private C0748b(Context context, String str, String str2) throws IOException {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        String macAddress = (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "UNKNOWN" : connectionInfo.getMacAddress();
        this.f1031c = m749a(macAddress);
        this.f1032d = RomUtils.getKnownRomVersion();
        if (RomUtils.isEmulator()) {
            this.f1029a = this.f1032d;
        } else {
            this.f1029a = String.format("%s %s", Build.BRAND, Build.MODEL);
        }
        this.f1035g = C0839d.m1288a(context);
        this.f1039k = C0839d.m1289b(context);
        this.f1034f = context.getPackageName();
        this.f1041m = "com.cyjh.mobileanjian".equals(this.f1034f);
        this.f1037i = false;
        this.f1036h = str;
        this.f1038j = str2;
        boolean z = !f1027o.exists();
        this.f1040l = z;
        if (z) {
            try {
                f1027o.createNewFile();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        String fileToString = null;
        try {
            File file = new File("/proc/version");
            if (file.exists() && file.canRead()) {
                fileToString = FileUtils.readFileToString(file);
            }
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        this.f1033e = TextUtils.isEmpty(fileToString) ? "Unknown" : fileToString;
    }

    /* JADX INFO: renamed from: a */
    private static String m748a(Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return (connectionInfo == null || connectionInfo.getMacAddress() == null) ? "UNKNOWN" : connectionInfo.getMacAddress();
    }

    /* JADX INFO: renamed from: a */
    private static final String m749a(String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                String hexString = Integer.toHexString(b & 255);
                while (hexString.length() < 2) {
                    hexString = AccountManager.TYPE_LOGIN + hexString;
                }
                sb.append(hexString);
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
            return str;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("手机型号: ");
        sb.append(this.f1029a);
        sb.append("\nLinux: ");
        sb.append(this.f1033e);
        sb.append("\nMAC的MD5: ");
        sb.append(this.f1031c);
        sb.append("\n安卓版本: ");
        sb.append(this.f1030b);
        sb.append("\n定制安卓: ");
        sb.append(this.f1032d);
        sb.append("\n应用名称: ");
        sb.append(this.f1035g);
        sb.append("\n应用包名: ");
        sb.append(this.f1034f);
        sb.append("\n应用版本: ");
        sb.append(this.f1039k);
        sb.append("\n唯一标识: ");
        sb.append(this.f1036h);
        sb.append("\n精灵版本: ");
        sb.append(this.f1038j);
        sb.append("\n产品形态: UNKNOWN\n是否付费: ");
        sb.append(this.f1037i ? "免费" : "收费");
        sb.append("\n");
        return sb.toString();
    }
}

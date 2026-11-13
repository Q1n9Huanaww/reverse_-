package com.cyjh.mobileanjian.ipc;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.KeyguardManager;
import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.PowerManager;
import android.os.Vibrator;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.ime.input.C1631a;
import com.p024ds.daisi.net.URL.URLConstant;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.a */
/* JADX INFO: compiled from: AppAgent.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0741a {

    /* JADX INFO: renamed from: c */
    public static final String f942c = "NO NETWORK";

    /* JADX INFO: renamed from: d */
    public static final String f943d = "WiFi";

    /* JADX INFO: renamed from: e */
    public static final String f944e = "2G";

    /* JADX INFO: renamed from: f */
    public static final String f945f = "3G";

    /* JADX INFO: renamed from: g */
    public static final String f946g = "4G";

    /* JADX INFO: renamed from: k */
    private static MediaPlayer f947k = new MediaPlayer();

    /* JADX INFO: renamed from: a */
    public Context f948a;

    /* JADX INFO: renamed from: b */
    public C1631a f949b;

    /* JADX INFO: renamed from: h */
    private PowerManager.WakeLock f950h;

    /* JADX INFO: renamed from: i */
    private PowerManager.WakeLock f951i;

    /* JADX INFO: renamed from: j */
    private KeyguardManager.KeyguardLock f952j;

    public C0741a(Context context) {
        this.f948a = null;
        this.f948a = context;
        this.f950h = ((PowerManager) this.f948a.getSystemService("power")).newWakeLock(6, "fzcyjh");
        this.f950h.setReferenceCounted(false);
        this.f949b = C1631a.m3367a();
        this.f952j = ((KeyguardManager) this.f948a.getSystemService("keyguard")).newKeyguardLock("unlock");
        this.f951i = ((PowerManager) this.f948a.getSystemService("power")).newWakeLock(268435462, "lock_unlock_screen");
    }

    /* JADX INFO: renamed from: l */
    private void m667l() {
        this.f950h = ((PowerManager) this.f948a.getSystemService("power")).newWakeLock(6, "fzcyjh");
        this.f950h.setReferenceCounted(false);
    }

    /* JADX INFO: renamed from: m */
    private boolean m668m() {
        try {
            return ((Vibrator) this.f948a.getSystemService("vibrator")).hasVibrator();
        } catch (Throwable unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m671a(int i) {
        if (m668m()) {
            m670a();
            ((Vibrator) this.f948a.getSystemService("vibrator")).vibrate(i);
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m670a() {
        ((Vibrator) this.f948a.getSystemService("vibrator")).cancel();
    }

    /* JADX INFO: renamed from: a */
    public final boolean m672a(String str) {
        if (str == null || str.trim().equals("")) {
            return false;
        }
        try {
            Intent launchIntentForPackage = this.f948a.getPackageManager().getLaunchIntentForPackage(str);
            if (launchIntentForPackage == null) {
                return false;
            }
            launchIntentForPackage.setFlags(270532608);
            launchIntentForPackage.setPackage(null);
            this.f948a.startActivity(launchIntentForPackage);
            StringBuilder sb = new StringBuilder("runApp(");
            sb.append(str);
            sb.append(") OK.");
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    /* JADX INFO: renamed from: f */
    private static void m666f(int i) {
        if (i > 0) {
            C0746d.m728a().m743a("kill " + i);
        }
    }

    /* JADX INFO: renamed from: b */
    public final boolean m675b(String str) {
        boolean z = false;
        if (str == null || str.trim().equals("")) {
            return false;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) this.f948a.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(str)) {
                int i = runningAppProcessInfo.pid;
                if (i > 0) {
                    C0746d.m728a().m743a("kill " + i);
                }
                z = true;
            }
        }
        return z;
    }

    /* JADX INFO: renamed from: n */
    private List<String> m669n() {
        ArrayList arrayList = new ArrayList();
        Iterator<InputMethodInfo> it2 = ((InputMethodManager) this.f948a.getSystemService("input_method")).getInputMethodList().iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getId());
        }
        return arrayList;
    }

    /* JADX INFO: renamed from: b */
    public final String m673b() {
        String string = Settings.Secure.getString(this.f948a.getContentResolver(), "default_input_method");
        if (!string.contains("com.cyjh")) {
            return string;
        }
        ArrayList<String> arrayList = new ArrayList();
        Iterator<InputMethodInfo> it2 = ((InputMethodManager) this.f948a.getSystemService("input_method")).getInputMethodList().iterator();
        while (it2.hasNext()) {
            arrayList.add(it2.next().getId());
        }
        for (String str : arrayList) {
            if (!str.contains("com.cyjh")) {
                return str;
            }
        }
        return string;
    }

    /* JADX INFO: renamed from: e */
    private void m664e(String str) {
        InputConnection currentInputConnection;
        C1631a c1631a = this.f949b;
        if (c1631a.f3579a == null || (currentInputConnection = c1631a.f3579a.getCurrentInputConnection()) == null) {
            return;
        }
        currentInputConnection.commitText(str, str.length());
    }

    /* JADX INFO: renamed from: c */
    public final String m676c() {
        WifiInfo connectionInfo = ((WifiManager) this.f948a.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo == null ? "" : connectionInfo.getMacAddress();
    }

    @SuppressLint({"MissingPermission"})
    /* JADX INFO: renamed from: d */
    public final String m678d() {
        String deviceId;
        String strM676c = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) this.f948a.getSystemService(URLConstant.USER_ACCOUNT);
            if (Build.VERSION.SDK_INT >= 29) {
                deviceId = Settings.System.getString(this.f948a.getContentResolver(), InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE);
            } else {
                deviceId = telephonyManager.getDeviceId();
            }
            strM676c = deviceId;
            if (strM676c == null || strM676c.equals("")) {
                strM676c = m676c();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return (strM676c == null || strM676c.equals("")) ? m676c() : strM676c;
    }

    /* JADX INFO: renamed from: b */
    public final void m674b(int i) {
        if (i > 0) {
            this.f950h.acquire();
        } else if (this.f950h != null) {
            this.f950h.release();
        }
    }

    /* JADX INFO: renamed from: e */
    public final void m681e() {
        String packageName = this.f948a.getPackageName();
        ActivityManager activityManager = (ActivityManager) this.f948a.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses != null) {
            for (int i = 0; i < runningAppProcesses.size(); i++) {
                ActivityManager.RunningAppProcessInfo runningAppProcessInfo = runningAppProcesses.get(i);
                if (!runningAppProcessInfo.processName.contains(packageName) && runningAppProcessInfo.importance > 200) {
                    for (String str : runningAppProcessInfo.pkgList) {
                        activityManager.killBackgroundProcesses(str);
                    }
                }
            }
        }
    }

    /* JADX INFO: renamed from: c */
    public static void m663c(String str) {
        if (f947k == null) {
            f947k = new MediaPlayer();
        }
        m665f();
        try {
            f947k.reset();
            f947k.setDataSource(str);
            f947k.prepare();
            f947k.start();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            f947k.release();
            f947k = null;
        }
    }

    /* JADX INFO: renamed from: f */
    public static void m665f() {
        if (f947k == null || !f947k.isPlaying()) {
            return;
        }
        f947k.stop();
    }

    /* JADX INFO: renamed from: g */
    public final void m683g() {
        ((DevicePolicyManager) this.f948a.getSystemService("device_policy")).lockNow();
        this.f952j.reenableKeyguard();
    }

    /* JADX INFO: renamed from: h */
    public final void m684h() {
        this.f952j.disableKeyguard();
        this.f951i.acquire();
        this.f951i.release();
    }

    /* JADX INFO: renamed from: c */
    public final void m677c(int i) {
        Settings.System.putInt(this.f948a.getContentResolver(), "screen_off_timeout", i * 1000);
    }

    /* JADX INFO: renamed from: d */
    public final void m680d(int i) {
        Settings.System.putInt(this.f948a.getContentResolver(), "screen_brightness", (int) (((double) (i * 255)) / 100.0d));
    }

    /* JADX INFO: renamed from: e */
    public final void m682e(int i) {
        AudioManager audioManager = (AudioManager) this.f948a.getSystemService("audio");
        audioManager.setStreamVolume(4, (int) (((double) (audioManager.getStreamMaxVolume(4) * i)) / 100.0d), 8);
        audioManager.setStreamVolume(3, (int) (((double) (audioManager.getStreamMaxVolume(3) * i)) / 100.0d), 8);
        audioManager.setStreamVolume(5, (int) (((double) (audioManager.getStreamMaxVolume(5) * i)) / 100.0d), 8);
        audioManager.setStreamVolume(2, (int) (((double) (audioManager.getStreamMaxVolume(2) * i)) / 100.0d), 8);
        audioManager.setStreamVolume(1, (int) (((double) (audioManager.getStreamMaxVolume(1) * i)) / 100.0d), 8);
        audioManager.setStreamVolume(0, (int) (((double) (audioManager.getStreamMaxVolume(0) * i)) / 100.0d), 8);
        audioManager.setStreamVolume(8, (int) (((double) (i * audioManager.getStreamMaxVolume(8))) / 100.0d), 8);
    }

    /* JADX INFO: renamed from: a */
    private void m661a(boolean z) {
        Settings.System.putInt(this.f948a.getContentResolver(), "airplane_mode_on", z ? 1 : 0);
        Intent intent = new Intent("android.intent.action.AIRPLANE_MODE");
        intent.putExtra("state", z);
        this.f948a.sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: b */
    private void m662b(boolean z) {
        Settings.System.putInt(this.f948a.getContentResolver(), "accelerometer_rotation", !z ? 1 : 0);
    }

    /* JADX INFO: renamed from: i */
    public final String m685i() {
        NetworkInfo activeNetworkInfo;
        NetworkInfo.State state;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.f948a.getSystemService("connectivity");
        if (connectivityManager == null || (activeNetworkInfo = connectivityManager.getActiveNetworkInfo()) == null || !activeNetworkInfo.isAvailable()) {
            return f942c;
        }
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(1);
        if (networkInfo != null && (state = networkInfo.getState()) != null && (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING)) {
            return f943d;
        }
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(0);
        if (networkInfo2 == null) {
            return f942c;
        }
        NetworkInfo.State state2 = networkInfo2.getState();
        String subtypeName = networkInfo2.getSubtypeName();
        if (state2 == null) {
            return f942c;
        }
        if (state2 != NetworkInfo.State.CONNECTED && state2 != NetworkInfo.State.CONNECTING) {
            return f942c;
        }
        switch (activeNetworkInfo.getSubtype()) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return f944e;
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return f945f;
            case 13:
                return f946g;
            default:
                return (subtypeName.equalsIgnoreCase("TD-SCDMA") || subtypeName.equalsIgnoreCase("WCDMA") || subtypeName.equalsIgnoreCase("CDMA2000")) ? f945f : f944e;
        }
    }

    /* JADX INFO: renamed from: d */
    public final String m679d(String str) {
        try {
            return this.f948a.getPackageManager().getApplicationInfo(str, 0).sourceDir;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: j */
    public final String m686j() {
        try {
            return this.f948a.getFilesDir().getAbsolutePath();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    /* JADX INFO: renamed from: k */
    public final void m687k() {
        Intent intent = new Intent("android.net.vpn.SETTINGS");
        intent.setFlags(268435456);
        this.f948a.startActivity(intent);
    }
}

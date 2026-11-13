package com.cyjh.logcatdex;

import android.app.ActivityManager;
import android.app.ActivityManagerNative;
import android.app.IActivityManager;
import android.os.Build;
import android.os.Process;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: assets/AppReboot.dex */
public class LogcatDexMain {
    private static final String TAG = "LogcatDexMain";
    private static String fileName;
    private static String packageName;
    private static Thread thread;
    private static boolean isLive = true;
    private static final ReaderRunable readerRunable = new ReaderRunable();
    private static String mCommand = "logcat -v time";

    public static void main(String[] args) {
        Log.i(TAG, "LogcatDexMain -- start pid:" + Process.myPid());
        for (int i = 0; i < args.length; i++) {
            Log.d(TAG, i + "=====arg: " + args[i]);
        }
        if (args.length > 0) {
            packageName = args[0];
        }
        if (args.length > 1 && !TextUtils.isEmpty(args[1])) {
            mCommand = args[1];
        }
        try {
            Runtime.getRuntime().exec("su");
            Runtime.getRuntime().exec("am broadcast -a com.cyjh.logcat.LOGCAT_ACTION --es pid " + Process.myPid() + " --es packageName " + packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        restartReader();
        while (isLive) {
            Log.i(TAG, "=====定时轮询：" + packageName);
            if (getAliveForPackname(packageName)) {
                Log.i(TAG, "=====appAlive: " + packageName);
            } else {
                Log.i(TAG, "=====appDie: " + packageName);
                isLive = false;
                SystemClock.sleep(5000L);
                stopReader();
            }
            SystemClock.sleep(3000L);
        }
    }

    private static void restartReader() {
        stopReader();
        clearLogcat();
        thread = new Thread(readerRunable);
        thread.start();
        fileName = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date()) + ".txt";
        String deviceInfo = "PackageName:" + packageName + "\nBRAND:" + Build.BRAND + " DEVICE:" + Build.DEVICE + " MODEL:" + Build.MODEL + " PRODUCT:" + Build.PRODUCT + " MANUFACTURER:" + Build.MANUFACTURER + " REALEASE:" + Build.VERSION.RELEASE + " SDK:" + Build.VERSION.SDK_INT + "\n";
        LogcatUtils.writeFile("/sdcard/Logcat/", fileName, Arrays.asList(deviceInfo), true);
    }

    private static void clearLogcat() {
        try {
            String[] command = {"logcat", "-c"};
            Runtime.getRuntime().exec(command);
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    private static void stopReader() {
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    private static class ReaderRunable implements Runnable {
        private ReaderRunable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Reader reader = new LocalReader();
            reader.read(LogcatDexMain.mCommand, new Reader.UpdateHandler() { // from class: com.cyjh.logcatdex.LogcatDexMain.ReaderRunable.1
                @Override // com.cyjh.logcatdex.Reader.UpdateHandler
                public boolean isCancelled() {
                    return LogcatDexMain.thread == null;
                }

                @Override // com.cyjh.logcatdex.Reader.UpdateHandler
                public void update(int status, List<String> lines) {
                    if (lines != null) {
                        LogcatUtils.writeFile("/sdcard/Logcat/", LogcatDexMain.fileName, lines, true);
                    }
                }
            });
        }
    }

    public static boolean getAliveForPackname(String packageName2) {
        IActivityManager iam = ActivityManagerNative.getDefault();
        try {
            List<ActivityManager.RunningAppProcessInfo> rapis = iam.getRunningAppProcesses();
            for (ActivityManager.RunningAppProcessInfo info : rapis) {
                if (info.processName.contains(packageName2)) {
                    return true;
                }
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return false;
    }
}

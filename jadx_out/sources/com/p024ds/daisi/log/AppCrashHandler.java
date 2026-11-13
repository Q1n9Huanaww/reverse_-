package com.p024ds.daisi.log;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class AppCrashHandler implements Thread.UncaughtExceptionHandler {
    private static AppCrashHandler sInstance = new AppCrashHandler();
    private AppContext appContext;
    private Thread.UncaughtExceptionHandler mDefaultCrashHandler;

    private AppCrashHandler() {
    }

    public static AppCrashHandler getInstance() {
        return sInstance;
    }

    public void init(AppContext appContext) {
        this.appContext = appContext;
        this.mDefaultCrashHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        try {
            saveCrashLogToSDCard(th);
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
        ThrowableExtension.printStackTrace(th);
        if (this.mDefaultCrashHandler != null) {
            this.mDefaultCrashHandler.uncaughtException(thread, th);
        } else {
            Process.killProcess(Process.myPid());
        }
    }

    private void saveCrashLogToSDCard(Throwable th) throws IOException {
        if (Environment.getExternalStorageState().equals("mounted")) {
            File file = new File(Environment.getExternalStorageDirectory() + File.separator + this.appContext.getPackageName() + File.separator + "crashlog");
            if (!file.exists()) {
                file.mkdirs();
            }
            String str = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date(System.currentTimeMillis()));
            try {
                PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(file, str + ".crashlog"))));
                printWriter.println(str);
                dumpPhoneInfo(printWriter);
                printWriter.println();
                ThrowableExtension.printStackTrace(th, printWriter);
                printWriter.close();
            } catch (Exception unused) {
            }
        }
    }

    private void dumpPhoneInfo(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        WindowManager windowManager = (WindowManager) this.appContext.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        PackageInfo packageInfo = this.appContext.getPackageManager().getPackageInfo(this.appContext.getPackageName(), 0);
        printWriter.println("---------------手机硬件信息-------------------");
        printWriter.println("BOARD : " + Build.BOARD);
        printWriter.println("BOOTLOADER : " + Build.BOOTLOADER);
        printWriter.println("BRAND : " + Build.BRAND);
        printWriter.println("CPU_ABI1: " + Build.CPU_ABI);
        printWriter.println("CPU_ABI2: " + Build.CPU_ABI2);
        printWriter.println("DEVICE : " + Build.DEVICE);
        printWriter.println("DISPLAY : " + Build.DISPLAY);
        printWriter.println("FINGERPRINT : " + Build.FINGERPRINT);
        printWriter.println("HARDWARE : " + Build.HARDWARE);
        printWriter.println("HOST : " + Build.HOST);
        printWriter.println("ID : " + Build.ID);
        printWriter.println("MANUFACTURER  : " + Build.MANUFACTURER);
        printWriter.println("MODEL: " + Build.MODEL);
        printWriter.println("PRODUCT : " + Build.PRODUCT);
        printWriter.println("getRadioVersion : " + Build.getRadioVersion());
        printWriter.println("SERIAL : " + Build.SERIAL);
        printWriter.println("TAGS : " + Build.TAGS);
        printWriter.println("TIME : " + Build.TIME);
        printWriter.println("TYPE : " + Build.TYPE);
        printWriter.println("UNKNOWN : unknown");
        printWriter.println("VERSION.CODENAME : " + Build.VERSION.CODENAME);
        printWriter.println("VERSION.INCREMENTAL : " + Build.VERSION.INCREMENTAL);
        printWriter.println("VERSION.RELEASE : " + Build.VERSION.RELEASE);
        printWriter.println("System.getProperty()" + System.getProperty("ro.miui.ui.version.name"));
        printWriter.println("------------------------------------");
        printWriter.println("ScreenSize: " + displayMetrics.widthPixels + "x" + displayMetrics.heightPixels);
        StringBuilder sb = new StringBuilder();
        sb.append("versionName: ");
        sb.append(packageInfo.versionName);
        printWriter.println(sb.toString());
        printWriter.println("versionCode:" + packageInfo.versionCode);
        printWriter.println("OS Version: " + Build.VERSION.RELEASE);
        printWriter.println("API Level: " + Build.VERSION.SDK_INT);
        printWriter.println("Vendor: " + Build.MANUFACTURER);
    }
}

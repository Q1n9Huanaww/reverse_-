package com.cyjh.share.util;

import android.content.Context;
import android.util.Log;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: classes.dex */
public enum WebInjectFileHelper {
    INSTANCE;

    public void copyWebInjectFile(Context context) {
        boolean z;
        byte[] bArr = new byte[20];
        try {
            z = new FileInputStream(new File("/system/lib/libc.so")).read(bArr) == -1 || bArr[18] != 3;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        boolean zCheckRootPermission = ShellUtils.checkRootPermission();
        Log.e("xygtest", "isRoot=" + zCheckRootPermission);
        if (!z) {
            if (zCheckRootPermission) {
                try {
                    ShellUtils.execCommand("mkdir -p /sdcard/data/local/tmp/", true, 0);
                    ShellUtils.execCommand("mkdir -p /data/local/tmp/", true, 0);
                    ShellUtils.execCommand("mkdir -p /sdcard/com.cyjh.mrpa/script/", true, 0);
                    InternalFileUtils.getInstance(context).copyAssetsToSD("inject/x86/Inject", "data/local/tmp/a");
                    InternalFileUtils.getInstance(context).copyAssetsToSD("inject/x86/libnullso2.so", "data/local/tmp/c");
                    InternalFileUtils.getInstance(context).copyAssetsToSD("inject/test.apk", "test.apk");
                    if (!new File("/sdcard/com.cyjh.mrpa/script/.config").exists()) {
                        File file = new File("/sdcard/com.cyjh.mrpa/script/");
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        InternalFileUtils.getInstance(context).copyAssetsToSD("inject/config.txt", "com.cyjh.mrpa/script/.config");
                    }
                    Log.e("xygtest", "result=" + ShellUtils.execCommand("cp /sdcard/data/local/tmp/* /data/local/tmp/", true, 0).result);
                    Log.e("xygtest", "result=" + ShellUtils.execCommand("chmod 777 /data/local/tmp/*", true, 0).result);
                    return;
                } catch (Exception unused) {
                    return;
                }
            }
            return;
        }
        ShellUtils.CommandResult commandResultExecCommand = ShellUtils.execCommand("getprop phone.id", true, 100);
        if (commandResultExecCommand.successMsg == null || commandResultExecCommand.successMsg.length() <= 0) {
            if (zCheckRootPermission) {
                try {
                    ShellUtils.execCommand("mkdir -p /sdcard/data/local/tmp/", true, 0);
                    ShellUtils.execCommand("mkdir -p /data/local/tmp/", true, 0);
                    ShellUtils.execCommand("mkdir -p /sdcard/com.cyjh.mrpa/script/", true, 0);
                    InternalFileUtils.getInstance(context).copyAssetsToSD("inject/armeabi-v7a/Inject", "data/local/tmp/a");
                    InternalFileUtils.getInstance(context).copyAssetsToSD("inject/armeabi-v7a/libnullso2.so", "data/local/tmp/c");
                    InternalFileUtils.getInstance(context).copyAssetsToSD("inject/test.apk", "test.apk");
                    if (!new File("/sdcard/com.cyjh.mrpa/script/.config").exists()) {
                        File file2 = new File("/sdcard/com.cyjh.mrpa/script/");
                        if (!file2.exists()) {
                            file2.mkdirs();
                        }
                        InternalFileUtils.getInstance(context).copyAssetsToSD("inject/config.txt", "com.cyjh.mrpa/script/.config");
                    }
                    Log.e("xygtest", "result=" + ShellUtils.execCommand("cp /sdcard/data/local/tmp/* /data/local/tmp/", true, 0).result);
                    Log.e("xygtest", "result=" + ShellUtils.execCommand("chmod 777 /data/local/tmp/*", true, 0).result);
                    return;
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                    return;
                }
            }
            return;
        }
        if (zCheckRootPermission) {
            try {
                Log.e("xygtest", "result=" + ShellUtils.execCommand("mkdir -p /sdcard/data/local/tmp/", true, 0).result);
                InternalFileUtils.getInstance(context).copyAssetsToSD("injectDDY/test.apk", "data/local/tmp/test.apk");
                InternalFileUtils.getInstance(context).copyAssetsToSD("injectDDY/ddyinject.apk", "data/local/tmp/ddyinject.apk");
                if (!new File("/sdcard/com.cyjh.mrpa/script/.config").exists()) {
                    File file3 = new File("/sdcard/com.cyjh.mrpa/script/");
                    if (!file3.exists()) {
                        file3.mkdirs();
                    }
                    InternalFileUtils.getInstance(context).copyAssetsToSD("injectDDY/config.txt", "com.cyjh.mrpa/script/.config");
                }
                File file4 = new File("/data/local/tmp/ddyinject.apk");
                File file5 = new File("/data/local/tmp/test.apk");
                if (file4.exists() && FileUtil.getFileMD5(file4).trim().equals(FileUtil.getFileMD5(new File("/sdcard/data/local/tmp/ddyinject.apk"))) && file5.exists() && FileUtil.getFileMD5(file5).trim().equals(FileUtil.getFileMD5(new File("/sdcard/data/local/tmp/test.apk")))) {
                    return;
                }
                Log.e("xygtest", "cp result=" + ShellUtils.execCommand("cp /sdcard/data/local/tmp/* /data/local/tmp/", true, 0).result);
                Log.e("xygtest", "chmod result=" + ShellUtils.execCommand("chmod 777 /data/local/tmp/*", true, 0).result);
            } catch (Exception e3) {
                ThrowableExtension.printStackTrace(e3);
            }
        }
    }
}

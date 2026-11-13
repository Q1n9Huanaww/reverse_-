package com.cyjh.share.util;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1799v;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class CommonUtils {
    public static String getLocalApkFileInfo(Context context, String str) {
        PackageInfo packageArchiveInfo = context.getPackageManager().getPackageArchiveInfo(str, 1);
        if (packageArchiveInfo != null) {
            return packageArchiveInfo.packageName;
        }
        return null;
    }

    public static boolean isAppInstalled(Context context, String str) {
        try {
            context.getPackageManager().getPackageInfo(str, 1);
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean isSimulator(Context context) {
        return notHasBlueTooth() || notHasLightSensorManager(context).booleanValue() || checkBasic();
    }

    public static boolean notHasBlueTooth() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        return defaultAdapter == null || TextUtils.isEmpty(defaultAdapter.getName());
    }

    public static Boolean notHasLightSensorManager(Context context) {
        if (((SensorManager) context.getSystemService(C1799v.f4284W)).getDefaultSensor(5) == null) {
            return true;
        }
        return false;
    }

    public static boolean checkIsNotRealPhone() {
        String cpuInfo = readCpuInfo();
        return cpuInfo.contains("intel") || cpuInfo.contains("amd");
    }

    private static String readCpuInfo() {
        try {
            Process processStart = new ProcessBuilder("/system/bin/cat", "/proc/cpuinfo").start();
            StringBuffer stringBuffer = new StringBuffer();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processStart.getInputStream(), "utf-8"));
            while (true) {
                String line = bufferedReader.readLine();
                if (line != null) {
                    stringBuffer.append(line);
                } else {
                    bufferedReader.close();
                    return stringBuffer.toString().toLowerCase();
                }
            }
        } catch (IOException unused) {
            return "";
        }
    }

    private static boolean checkBasic() {
        boolean z = false;
        boolean z2 = Build.FINGERPRINT.startsWith("generic") || Build.MODEL.contains("google_sdk") || Build.MODEL.toLowerCase().contains("droid4x") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.PRODUCT.equals("sdk") || Build.PRODUCT.equals("google_sdk") || Build.PRODUCT.equals("sdk_x86") || Build.PRODUCT.equals("vbox86p") || Build.BOARD.toLowerCase().contains("nox") || Build.BOOTLOADER.toLowerCase().contains("nox") || Build.HARDWARE.toLowerCase().contains("nox") || Build.PRODUCT.toLowerCase().contains("nox") || Build.SERIAL.toLowerCase().contains("nox");
        if (z2) {
            return true;
        }
        if (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) {
            z = true;
        }
        boolean z3 = z2 | z;
        if (z3) {
            return true;
        }
        return z3 | "google_sdk".equals(Build.PRODUCT);
    }

    public static boolean saveLogFile2SDcard(String str, boolean z) {
        if (!isSdCardExist()) {
            return false;
        }
        try {
            File file = new File(Environment.getExternalStorageDirectory().getPath(), "elfinParams.txt");
            if (file.exists()) {
                file.delete();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file, z);
            fileOutputStream.write(str.getBytes("UTF-8"));
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean isSdCardExist() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* JADX WARN: Code duplicated, block: B:69:0x0071 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    public static synchronized boolean getRootAhth() {
        Process processExec;
        DataOutputStream dataOutputStream;
        Exception e;
        DataOutputStream dataOutputStream2 = null;
        try {
            try {
                processExec = Runtime.getRuntime().exec("su");
                try {
                    dataOutputStream = new DataOutputStream(processExec.getOutputStream());
                    try {
                        try {
                            dataOutputStream.writeBytes("exit\n");
                            dataOutputStream.flush();
                            if (processExec.waitFor() == 0) {
                                if (dataOutputStream != null) {
                                    try {
                                        dataOutputStream.close();
                                    } catch (Exception e2) {
                                        ThrowableExtension.printStackTrace(e2);
                                    }
                                }
                                processExec.destroy();
                                return true;
                            }
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (Exception e3) {
                                    ThrowableExtension.printStackTrace(e3);
                                }
                            }
                            processExec.destroy();
                            return false;
                        } catch (Exception e4) {
                            e = e4;
                            ThrowableExtension.printStackTrace(e);
                            if (dataOutputStream != null) {
                                try {
                                    dataOutputStream.close();
                                } catch (Exception e5) {
                                    ThrowableExtension.printStackTrace(e5);
                                    return false;
                                }
                            }
                            processExec.destroy();
                            return false;
                        }
                    } catch (Throwable th) {
                        th = th;
                        dataOutputStream2 = dataOutputStream;
                        if (dataOutputStream2 != null) {
                            try {
                                dataOutputStream2.close();
                            } catch (Exception e6) {
                                ThrowableExtension.printStackTrace(e6);
                                throw th;
                            }
                        }
                        processExec.destroy();
                        throw th;
                    }
                } catch (Exception e7) {
                    dataOutputStream = null;
                    e = e7;
                } catch (Throwable th2) {
                    th = th2;
                    if (dataOutputStream2 != null) {
                        dataOutputStream2.close();
                    }
                    processExec.destroy();
                    throw th;
                }
            } catch (Throwable th3) {
                throw th3;
            }
        } catch (Exception e8) {
            dataOutputStream = null;
            e = e8;
            processExec = null;
        } catch (Throwable th4) {
            th = th4;
            processExec = null;
        }
        throw th3;
    }

    public static boolean getRootWithoutDialog() {
        if (new File("/system/bin/su").exists() && isExecutable("/system/bin/su")) {
            return true;
        }
        return new File("/system/xbin/su").exists() && isExecutable("/system/xbin/su");
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0068  */
    private static boolean isExecutable(String str) throws Throwable {
        char cCharAt;
        Process process = null;
        try {
            try {
                Process processExec = Runtime.getRuntime().exec("ls -l " + str);
                try {
                    String line = new BufferedReader(new InputStreamReader(processExec.getInputStream())).readLine();
                    if (line != null && line.length() >= 4 && ((cCharAt = line.charAt(3)) == 's' || cCharAt == 'x')) {
                        if (processExec != null) {
                            processExec.destroy();
                        }
                        return true;
                    }
                    if (processExec == null) {
                        return false;
                    }
                    processExec.destroy();
                    return false;
                } catch (IOException e) {
                    process = processExec;
                    e = e;
                    ThrowableExtension.printStackTrace(e);
                    if (process == null) {
                        return false;
                    }
                    process.destroy();
                    return false;
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                } catch (Throwable th) {
                    process = processExec;
                    th = th;
                    if (process != null) {
                        process.destroy();
                    }
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static String getPublicInternetIP() {
        String line;
        Exception e;
        String str = "0.0.0.0";
        try {
            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL("http://pv.sohu.com/cityjson?ie=utf-8").openConnection();
            if (httpURLConnection.getResponseCode() != 200) {
                return "0.0.0.0";
            }
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
            StringBuilder sb = new StringBuilder();
            while (true) {
                line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                try {
                    sb.append(line + "\n");
                    str = line;
                } catch (Exception e2) {
                    e = e2;
                }
                e = e2;
            }
            inputStream.close();
            String strSubstring = sb.substring(sb.indexOf("{"), sb.indexOf("}") + 1);
            if (strSubstring != null) {
                try {
                    return new JSONObject(strSubstring).optString("cip");
                } catch (JSONException e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            }
            return line;
        } catch (Exception e4) {
            line = str;
            e = e4;
        }
        ThrowableExtension.printStackTrace(e);
        return line;
    }

    public static String getWebViewUserAgent(Context context) {
        try {
            if (Build.VERSION.SDK_INT < 19) {
                return new WebView(context).getSettings().getUserAgentString();
            }
            return WebSettings.getDefaultUserAgent(context);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }
}

package com.cyjh.share.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.media.projection.MediaProjectionManager;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.p028a.p035c.C1209l;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: loaded from: classes.dex */
public class CommonUtil {
    private static int REQUEST_CODE = 1000;
    private static final String TAG = "CommonUtil";

    public static String dealWith(String str) {
        return str == null ? "" : str;
    }

    public static String getUUIDStr() {
        return UUID.randomUUID().toString();
    }

    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    public static boolean isNewUser(Context context) {
        try {
            File userMarkFile = getUserMarkFile(context);
            if (userMarkFile.exists()) {
                return false;
            }
            userMarkFile.createNewFile();
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void writeDataToFile(File file, String str, boolean z) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            writeStringToFile(file, str, z);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private static void writeStringToFile(File file, String str, boolean z) {
        try {
            FileWriter fileWriter = new FileWriter(file, z);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    private static String getFirstLineStringFromFile(File file) {
        try {
            ArrayList arrayList = new ArrayList();
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                arrayList.add(line);
            }
            fileInputStream.close();
            if (arrayList.size() > 0) {
                return (String) arrayList.get(0);
            }
            return null;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static File getUserMarkFile(Context context) {
        if (checkSDCardAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), ".userMark");
        }
        return new File(context.getFilesDir(), ".userMark");
    }

    public static boolean isFirstShowStopScriptTips(Context context) {
        try {
            File file = new File(context.getFilesDir(), ".stopScriptTips");
            if (file.exists()) {
                return false;
            }
            file.createNewFile();
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return true;
        }
    }

    public static File getDeviceIdFile(Context context) {
        if (checkSDCardAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), ".deviceInfo");
        }
        return new File(context.getFilesDir(), ".deviceInfo");
    }

    public static String getDeviceId(Context context) {
        String firstLineStringFromFile = null;
        try {
            File deviceIdFile = getDeviceIdFile(context);
            firstLineStringFromFile = deviceIdFile.exists() ? getFirstLineStringFromFile(deviceIdFile) : null;
            if (firstLineStringFromFile != null) {
                return firstLineStringFromFile;
            }
            String uUIDStr = getUUIDStr();
            try {
                writeDataToFile(deviceIdFile, uUIDStr, true);
                return uUIDStr;
            } catch (Exception e) {
                e = e;
                firstLineStringFromFile = uUIDStr;
            }
        } catch (Exception e2) {
            e = e2;
        }
        ThrowableExtension.printStackTrace(e);
        return firstLineStringFromFile;
    }

    public static File getDeviceNameFile(Context context) {
        if (checkSDCardAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), ".elfinDevice");
        }
        return new File(context.getFilesDir(), ".elfinDevice");
    }

    public static String getDeviceName(Context context) {
        String firstLineStringFromFile = null;
        try {
            File deviceNameFile = getDeviceNameFile(context);
            firstLineStringFromFile = deviceNameFile.exists() ? getFirstLineStringFromFile(deviceNameFile) : null;
            if (firstLineStringFromFile != null) {
                return firstLineStringFromFile;
            }
            String str = Build.BRAND + getUUIDStr().substring(0, 8);
            try {
                writeDataToFile(deviceNameFile, str, false);
                return str;
            } catch (Exception e) {
                e = e;
                firstLineStringFromFile = str;
            }
        } catch (Exception e2) {
            e = e2;
        }
        ThrowableExtension.printStackTrace(e);
        return firstLineStringFromFile;
    }

    public static boolean setDeviceName(Context context, String str) {
        try {
            writeDataToFile(getDeviceNameFile(context), str, false);
            return true;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static File getBaseParamsFile(Context context) {
        if (checkSDCardAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), "elfinParams.txt");
        }
        return new File(context.getFilesDir(), "elfinParams.txt");
    }

    public static String getBase64String(String str) {
        return getBase64String(str, null);
    }

    public static String getBase64String(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        try {
            return Base64.encodeToString(str.getBytes(str2), 2);
        } catch (UnsupportedEncodingException e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String parseBase64String(String str) {
        return parseBase64String(str, null);
    }

    public static String parseBase64String(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            str2 = "UTF-8";
        }
        try {
            return new String(Base64.decode(str, 2), str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String stringToAscii(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        char[] charArray = str.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            if (i != charArray.length - 1) {
                stringBuffer.append((int) charArray[i]);
                stringBuffer.append(",");
            } else {
                stringBuffer.append((int) charArray[i]);
            }
        }
        return stringBuffer.toString();
    }

    public static String getEncoding(String str) {
        try {
            if (str.equals(new String(str.getBytes(C1209l.f2171b), C1209l.f2171b))) {
                return C1209l.f2171b;
            }
        } catch (Exception unused) {
        }
        try {
            if (str.equals(new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                return "ISO-8859-1";
            }
        } catch (Exception unused2) {
        }
        try {
            if (str.equals(new String(str.getBytes("UTF-8"), "UTF-8"))) {
                return "UTF-8";
            }
        } catch (Exception unused3) {
        }
        try {
            return str.equals(new String(str.getBytes("GBK"), "GBK")) ? "GBK" : "";
        } catch (Exception unused4) {
            return "";
        }
    }

    @RequiresApi(api = 21)
    public static MediaProjectionManager getScreenCapPath(Context context) {
        MediaProjectionManager mediaProjectionManager = (MediaProjectionManager) context.getSystemService("media_projection");
        ((Activity) context).startActivityForResult(mediaProjectionManager.createScreenCaptureIntent(), REQUEST_CODE);
        return mediaProjectionManager;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenDensity(Context context) {
        return (int) context.getResources().getDisplayMetrics().density;
    }

    public static boolean saveBitmap(Bitmap bitmap, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static File getErrorFile(Context context) {
        if (checkSDCardAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), ".elfinError");
        }
        return new File(context.getFilesDir(), ".elfinError");
    }

    public static boolean copyAssetsFile(Context context, String str, String str2) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            InputStream inputStreamOpen = context.getAssets().open(str);
            byte[] bArr = new byte[1024];
            for (int i = inputStreamOpen.read(bArr); i > 0; i = inputStreamOpen.read(bArr)) {
                fileOutputStream.write(bArr, 0, i);
            }
            fileOutputStream.flush();
            inputStreamOpen.close();
            fileOutputStream.close();
            return true;
        } catch (Exception e) {
            SlLog.m1334i(TAG, "copyAssetsFile --> e =" + e.getMessage());
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static void setElfinPackageNames(String str, boolean z) {
        File fileByPath = AppUtils.getFileByPath(InterfaceRelatedConstants.UUID_SDCARD_DIR, InterfaceRelatedConstants.ELFIN_REBOOT);
        if (z) {
            FileUtil.writeStringToFile(fileByPath, str, "UTF-8", true);
        } else {
            FileUtil.writeStringToFile(fileByPath, str, "UTF-8");
        }
    }

    public static String getElfinPackageNames() {
        return FileUtil.readStringFromFile(AppUtils.getFileByPath(InterfaceRelatedConstants.UUID_SDCARD_DIR, InterfaceRelatedConstants.ELFIN_REBOOT));
    }

    public static void operateElfinPackageNames(Context context, boolean z) {
        boolean z2;
        String packageName = context.getPackageName();
        String elfinPackageNames = getElfinPackageNames();
        Log.i("jason", "operateElfinPackageNames --> isAdd=" + z + ",elfinFreePackageNames=" + elfinPackageNames);
        if (z) {
            if (TextUtils.isEmpty(elfinPackageNames)) {
                setElfinPackageNames(packageName, true);
                return;
            } else {
                if (elfinPackageNames.contains(packageName)) {
                    return;
                }
                setElfinPackageNames("," + packageName, true);
                return;
            }
        }
        String str = "," + packageName;
        String str2 = packageName + ",";
        if (TextUtils.isEmpty(elfinPackageNames)) {
            return;
        }
        if (elfinPackageNames.contains(str)) {
            elfinPackageNames = elfinPackageNames.replace(str, "");
            z2 = true;
        } else {
            z2 = false;
        }
        if (elfinPackageNames.contains(str2)) {
            elfinPackageNames = elfinPackageNames.replace(str2, "");
            z2 = true;
        }
        if (elfinPackageNames.contains(packageName)) {
            elfinPackageNames = elfinPackageNames.replace(packageName, "");
            z2 = true;
        }
        if (z2) {
            Log.i("jason", "operateElfinPackageNames --> hasReplace=true, result=" + elfinPackageNames);
            setElfinPackageNames(elfinPackageNames, false);
        }
    }

    public static String getAppVersion() {
        String appVersion = MyConfig.getAppVersion();
        String appVersion2 = SpUtil.getInstance().getAppVersion();
        if (TextUtils.isEmpty(appVersion2)) {
            return appVersion;
        }
        try {
            return Long.valueOf(Long.parseLong(appVersion2)).longValue() >= Long.valueOf(Long.parseLong(appVersion)).longValue() ? appVersion2 : appVersion;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return appVersion;
        }
    }

    public static List<String> parseJsonArray(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                try {
                    arrayList.add((String) jSONArray.get(i));
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
        return arrayList;
    }

    public static Point getResolution(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public static void setImageViewMathParent(Activity activity, LinearLayout linearLayout, ImageView imageView, int i) {
        BitmapFactory.decodeResource(activity.getResources(), i);
        activity.getWindowManager().getDefaultDisplay().getMetrics(new DisplayMetrics());
    }
}

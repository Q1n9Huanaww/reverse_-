package com.cyjh.share.util;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class ErrorUtil {
    private static final String TAG = "ErrorUtil";
    public static boolean debugMode = true;

    public static File getErrorFile(Context context) {
        if (checkSDCardAvailable()) {
            return new File(Environment.getExternalStorageDirectory(), ".elfinErrorRecord");
        }
        return new File(context.getFilesDir(), ".elfinErrorRecord");
    }

    public static String timeStampToDate(long j, String... strArr) {
        SimpleDateFormat simpleDateFormat;
        if (strArr.length < 1) {
            simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss E", Locale.getDefault());
        } else {
            simpleDateFormat = new SimpleDateFormat(strArr[0], Locale.getDefault());
        }
        return simpleDateFormat.format(Long.valueOf(j));
    }

    public static boolean isFileMoreThan1M(File file) {
        return file != null && file.length() > ((long) 1048576);
    }

    public static boolean checkSDCardAvailable() {
        try {
            return Environment.getExternalStorageState().equals("mounted");
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static void writeStringToFile(File file, String str) {
        try {
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static boolean writeStringToFile(File file, String str, String str2) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "UTF-8";
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), str2));
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean writeProcessedStringToFile(File file, String str, String str2, boolean z) {
        return writeStringToFile(file, timeStampToDate(System.currentTimeMillis(), new String[0]) + "\n" + str + "\n\n", str2, z);
    }

    public static boolean writeStringToFile(File file, String str, String str2, boolean z) {
        try {
            if (TextUtils.isEmpty(str2)) {
                str2 = "UTF-8";
            }
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, z), str2));
            bufferedWriter.write(str);
            bufferedWriter.flush();
            bufferedWriter.close();
            return true;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static boolean deleteSingleFile(File file) {
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                SlLog.m1334i(TAG, "deleteSingleFile --> 删除单个文件成功！");
                return true;
            }
            SlLog.m1334i(TAG, "deleteSingleFile --> 删除单个文件失败！");
            return false;
        }
        SlLog.m1334i(TAG, "deleteSingleFile --> 文件不存在！ ");
        return false;
    }
}

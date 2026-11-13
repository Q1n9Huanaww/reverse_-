package com.cyjh.share.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import net.lingala.zip4j.util.InternalZipConstants;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class FileUtil {
    private static final String TAG = "FileUtil";

    public static File createNewFile(File file) {
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
        return file;
    }

    public static File createDataDataFileDir(Context context, String str) {
        File file;
        try {
            file = new File(context.getFilesDir() + File.separator + str);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e) {
                e = e;
                ThrowableExtension.printStackTrace(e);
            }
        } catch (Exception e2) {
            e = e2;
            file = null;
        }
        return file;
    }

    public static File createFileDir(String str) {
        File file;
        try {
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + str);
            try {
                if (!file.exists()) {
                    file.mkdirs();
                }
            } catch (Exception e) {
                e = e;
                ThrowableExtension.printStackTrace(e);
            }
        } catch (Exception e2) {
            e = e2;
            file = null;
        }
        return file;
    }

    public static String readStringFromFile(File file, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "utf-8";
        }
        StringBuilder sb = new StringBuilder();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(file), str);
            while (true) {
                int i = inputStreamReader.read();
                if (i == -1) {
                    break;
                }
                sb.append((char) i);
            }
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
        String string = sb.toString();
        int iIndexOf = string.indexOf(IOUtils.LINE_SEPARATOR_WINDOWS);
        int iIndexOf2 = string.indexOf("\r");
        if (iIndexOf != -1) {
            return string.replaceAll(IOUtils.LINE_SEPARATOR_WINDOWS, "\n");
        }
        return iIndexOf2 != -1 ? string.replaceAll("\r", "\n") : string;
    }

    public static String convertEncodingFormat(String str, String str2, String str3) {
        if (str != null && str.length() != 0) {
            try {
                return new String(str.getBytes(str2), str3);
            } catch (UnsupportedEncodingException e) {
                SlLog.m1334i(TAG, "convertEncodingFormat --> str=" + str + ",ex=" + e.getMessage());
                ThrowableExtension.printStackTrace(e);
                return str;
            }
        }
        SlLog.m1334i(TAG, "convertEncodingFormat --> str=" + str + ",formatFrom=" + str2 + ",formatTo=" + str3);
        return str;
    }

    public static String readStringFromFile(File file) {
        return readStringFromFile(file, null);
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

    public static boolean clearFileContent(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.close();
            return true;
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static File getFileByPath(Context context, String str, String str2) {
        File file = null;
        try {
            File file2 = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2, str2);
            try {
                if (!file3.exists()) {
                    file3.createNewFile();
                }
                return file3;
            } catch (IOException e) {
                e = e;
                file = file3;
                ThrowableExtension.printStackTrace(e);
                return file;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static File getFileByAbsolutePath(String str, String str2) {
        File file = null;
        try {
            File file2 = new File(str);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(file2, str2);
            try {
                if (file3.exists()) {
                    return file3;
                }
                file3.createNewFile();
                return file3;
            } catch (IOException e) {
                file = file3;
                e = e;
                ThrowableExtension.printStackTrace(e);
                return file;
            }
        } catch (IOException e2) {
            e = e2;
        }
    }

    public static boolean deleteSingleFile(String str) {
        File file = new File(str);
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                SlLog.m1334i(TAG, "deleteSingleFile --> 删除单个文件 " + str + "成功！");
                return true;
            }
            SlLog.m1334i(TAG, "deleteSingleFile --> 删除单个文件 " + str + "失败！");
            return false;
        }
        SlLog.m1334i(TAG, "deleteSingleFile --> 删除单个文件失败: " + str + "不存在！");
        return false;
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

    public static boolean deleteDir(String str) {
        try {
            if (str.endsWith(File.separator)) {
                str = str + File.separator;
            }
            File file = new File(str);
            if (file.exists() && file.isDirectory()) {
                File[] fileArrListFiles = file.listFiles();
                for (File file2 : fileArrListFiles) {
                    deleteSingleFile(file2.getAbsolutePath());
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static String toUtf8(String str) {
        try {
            return new String(str.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static boolean isFileExist(String str) {
        try {
            File file = new File(str);
            return file != null && file.exists();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static String saveBitmap(Bitmap bitmap, String str) {
        try {
            File file = new File(str);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file.getAbsolutePath();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static void copyFile(String str, String str2) {
        try {
            SlLog.m1334i(TAG, "copyFile --> targetFileAbsolutePath=" + str + ",toPath=" + str2);
            File file = new File(str);
            File file2 = new File(str2);
            if (!file2.isFile()) {
                file2.mkdirs();
            }
            outByte(new FileInputStream(file), new FileOutputStream(file2));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            SlLog.m1334i(TAG, "copyFile --> ex=" + e.getMessage());
        }
    }

    public static void outByte(FileInputStream fileInputStream, FileOutputStream fileOutputStream) {
        try {
            byte[] bArr = new byte[102400];
            while (true) {
                int i = fileInputStream.read(bArr, 0, bArr.length);
                if (i == -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    return;
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    public static String getFileMD5(File file) {
        if (!file.isFile()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            FileInputStream fileInputStream = new FileInputStream(file);
            while (true) {
                int i = fileInputStream.read(bArr, 0, 1024);
                if (i != -1) {
                    messageDigest.update(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    return bytesToHexString(messageDigest.digest());
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || bArr.length <= 0) {
            return null;
        }
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static String getFileNameByPath(String str) {
        String str2 = System.currentTimeMillis() + "";
        try {
            return str.substring(str.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return str2;
        }
    }

    public static Bitmap getLocalBitmap(String str) {
        try {
            return BitmapFactory.decodeStream(new FileInputStream(str));
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}

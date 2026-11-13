package org.wlf.filedownloader.util;

import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class FileUtil {
    public static final boolean createFileParentDir(String filePath) {
        File file = new File(filePath);
        if (file != null) {
            if (file.exists()) {
                return true;
            }
            File parentFile = file.getParentFile();
            if (parentFile != null) {
                if (parentFile.exists()) {
                    return true;
                }
                return parentFile.mkdirs();
            }
        }
        return false;
    }

    public static String getFileSuffix(String filePath) {
        int start;
        if (TextUtils.isEmpty(filePath) || (start = filePath.lastIndexOf(".")) == -1) {
            return null;
        }
        return filePath.substring(start + 1);
    }

    public static boolean isFilePath(String path) {
        return !TextUtils.isEmpty(path) && path.startsWith(File.separator);
    }

    public static final boolean canWrite(String path) {
        return !isSDCardPath(path) || "mounted".equals(Environment.getExternalStorageState());
    }

    public static final boolean isSDCardPath(String path) {
        if (TextUtils.isEmpty(path)) {
            return false;
        }
        String sdRootPath = Environment.getExternalStorageDirectory().getAbsolutePath();
        return path.startsWith(sdRootPath);
    }

    public static long getAvailableSpace(String fileDirPath) {
        try {
            File file = new File(fileDirPath);
            if (!file.exists()) {
                file.mkdirs();
            }
            StatFs stats = new StatFs(fileDirPath);
            return ((long) stats.getBlockSize()) * ((long) stats.getAvailableBlocks());
        } catch (Exception e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static boolean copyFile(File fromFile, File toFile, boolean forceOverwrite) {
        if (fromFile == null || !fromFile.exists() || toFile == null) {
            return false;
        }
        if (toFile.exists() && !forceOverwrite) {
            return false;
        }
        try {
            InputStream fosFrom = new FileInputStream(fromFile);
            OutputStream fosTo = new FileOutputStream(toFile);
            byte[] bytes = new byte[1024];
            int writeSize = 0;
            while (true) {
                int readCount = fosFrom.read(bytes);
                if (readCount == -1) {
                    break;
                }
                fosTo.write(bytes, 0, readCount);
                writeSize += readCount - 0;
            }
            fosFrom.close();
            fosTo.close();
            return writeSize > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isFileExist(String filePath) {
        File file;
        return isFilePath(filePath) && (file = new File(filePath)) != null && file.exists() && file.isFile();
    }
}

package com.cyjh.share.util;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/* JADX INFO: loaded from: classes.dex */
public class ZipUtil {
    private static final int BUFFER_LEN = 512;
    private static final String TAG = "ZipUtil";

    public static boolean unzipFile(File file, File file2) {
        boolean z;
        SlLog.m1334i(TAG, "unzipFile --> zipFile= " + file + ",destDir=" + file2);
        if (file == null || file2 == null) {
            return false;
        }
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));
            byte[] bArr = new byte[512];
            z = false;
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    if (nextEntry.isDirectory()) {
                        File file3 = new File(file2.getAbsolutePath() + File.separator + nextEntry.getName());
                        if (!file3.exists()) {
                            file3.mkdirs();
                            SlLog.m1334i(TAG, "unzipFile --> mkdirs: " + file3.getCanonicalPath());
                        }
                    }
                    File fileCreateNewFile = FileUtil.createNewFile(new File(file2.getAbsolutePath(), nextEntry.getName()));
                    FileOutputStream fileOutputStream = new FileOutputStream(fileCreateNewFile);
                    while (true) {
                        int i = zipInputStream.read(bArr, 0, 512);
                        if (i == -1) {
                            break;
                        }
                        fileOutputStream.write(bArr, 0, i);
                    }
                    fileOutputStream.close();
                    z = true;
                    SlLog.m1334i(TAG, "unzipFile --> success: fileName=" + fileCreateNewFile.getName());
                } catch (Exception e) {
                    e = e;
                    SlLog.m1334i(TAG, "unzipFile --> exception: " + e.getMessage());
                    ThrowableExtension.printStackTrace(e);
                }
            }
        } catch (Exception e2) {
            e = e2;
            z = false;
        }
        return z;
    }
}

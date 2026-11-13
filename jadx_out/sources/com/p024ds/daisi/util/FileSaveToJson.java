package com.p024ds.daisi.util;

import android.content.Context;
import android.os.Environment;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class FileSaveToJson {
    public static void saveJsonToSdcard(String str, Context context) throws Throwable {
        if (!Environment.getExternalStorageState().equals("mounted") || !Environment.getExternalStorageState().equals("mounted")) {
            return;
        }
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + context.getPackageName() + File.separator + "trycount");
        if (!file.exists()) {
            file.mkdirs();
        }
        FileOutputStream fileOutputStream = null;
        try {
            try {
                try {
                    FileOutputStream fileOutputStream2 = new FileOutputStream(new File(file, new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss", Locale.getDefault()).format(new Date(System.currentTimeMillis())) + ".txt"));
                    try {
                        fileOutputStream2.write(str.getBytes());
                        fileOutputStream2.flush();
                        if (fileOutputStream2 != null) {
                            fileOutputStream2.close();
                        }
                    } catch (Exception e) {
                        e = e;
                        fileOutputStream = fileOutputStream2;
                        ThrowableExtension.printStackTrace(e);
                        if (fileOutputStream == null) {
                        } else {
                            fileOutputStream.close();
                        }
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = fileOutputStream2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e2) {
                                ThrowableExtension.printStackTrace(e2);
                            }
                        }
                        throw th;
                    }
                } catch (IOException e3) {
                    ThrowableExtension.printStackTrace(e3);
                }
            } catch (Exception e4) {
                e = e4;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }
}

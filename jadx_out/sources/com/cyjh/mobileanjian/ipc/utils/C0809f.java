package com.cyjh.mobileanjian.ipc.utils;

import android.os.Environment;
import android.text.format.DateFormat;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.f */
/* JADX INFO: compiled from: FileLogger.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0809f {

    /* JADX INFO: renamed from: a */
    private File f1423a;

    /* JADX INFO: renamed from: b */
    private FileWriter f1424b;

    private C0809f(String str) {
        this.f1423a = new File(Environment.getExternalStorageDirectory(), str);
        try {
            this.f1424b = new FileWriter(this.f1423a);
        } catch (FileNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IOException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1081a(String str) {
        if (this.f1424b == null) {
            return;
        }
        String string = DateFormat.format("yyyy-MM-hh HH:mm:ss", System.currentTimeMillis()).toString();
        try {
            this.f1424b.write(string + "  " + str + "\n");
            this.f1424b.flush();
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

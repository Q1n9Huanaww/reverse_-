package com.p024ds.daisi.async;

import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class ExitLoginMessageTask extends AsyncTask<File, Void, Void> {
    private String message;

    public ExitLoginMessageTask(String str) {
        this.message = str;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX INFO: Removed unreachable split cross block B:36:0x003e */
    @Override // android.os.AsyncTask
    public Void doInBackground(File... fileArr) throws Throwable {
        FileOutputStream fileOutputStream;
        File file = fileArr[0];
        try {
            try {
                if (file != null) {
                    try {
                        fileOutputStream = new FileOutputStream(file);
                        try {
                            fileOutputStream.write(this.message.getBytes());
                            fileOutputStream.flush();
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        } catch (Exception e) {
                            e = e;
                            ThrowableExtension.printStackTrace(e);
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                        }
                    } catch (Exception e2) {
                        e = e2;
                        fileOutputStream = null;
                    } catch (Throwable th) {
                        th = th;
                        fileOutputStream = null;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e3) {
                                ThrowableExtension.printStackTrace(e3);
                            }
                        }
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (IOException e4) {
            ThrowableExtension.printStackTrace(e4);
        }
        return null;
    }
}

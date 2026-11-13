package com.p024ds.daisi.async;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.constant.Constants;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class NetworkReqMessageTask extends AsyncTask<File, Void, Void> {
    private String errorMessage;
    private SharedPreferences sharedPreferences;

    public NetworkReqMessageTask(String str, Context context) {
        this.errorMessage = str;
        this.sharedPreferences = context.getSharedPreferences(Constants.USER_MESSAGE, 0);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:38:0x00bf A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v1 */
    /* JADX WARN: Type inference failed for: r3v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r3v13 */
    /* JADX WARN: Type inference failed for: r3v14 */
    /* JADX WARN: Type inference failed for: r3v15 */
    /* JADX WARN: Type inference failed for: r3v16 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r3v3 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v5 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:12:0x004f -> B:40:0x00bb). Please report as a decompilation issue!!! */
    @Override // android.os.AsyncTask
    public Void doInBackground(File... fileArr) throws Throwable {
        ?? r3;
        Throwable th;
        FileOutputStream fileOutputStream;
        File file = fileArr[0];
        if (!this.sharedPreferences.getBoolean(Constants.ISEXIST_NETWORK_REQ_FILE, false)) {
            this.sharedPreferences.edit().putLong(Constants.NETWORK_RES_MESSAGE_TIME, System.currentTimeMillis() + 604800000).commit();
            SharedPreferences.Editor editorEdit = this.sharedPreferences.edit();
            r3 = Constants.ISEXIST_NETWORK_REQ_FILE;
            editorEdit.putBoolean(Constants.ISEXIST_NETWORK_REQ_FILE, true).commit();
        }
        try {
            try {
                try {
                    fileOutputStream = new FileOutputStream(file, true);
                    try {
                        fileOutputStream.write(this.errorMessage.getBytes());
                        fileOutputStream.flush();
                        r3 = fileOutputStream;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        }
                    } catch (FileNotFoundException e) {
                        e = e;
                        ThrowableExtension.printStackTrace(e);
                        Logger.m3442e("FileNotFoundException:" + e.getMessage() + "\t," + e.toString(), new Object[0]);
                        r3 = fileOutputStream;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        }
                    } catch (IOException e2) {
                        e = e2;
                        ThrowableExtension.printStackTrace(e);
                        Logger.m3442e("IOException:" + e.getMessage() + "\t," + e.toString(), new Object[0]);
                        r3 = fileOutputStream;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                            r3 = fileOutputStream;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (r3 != 0) {
                        try {
                            r3.close();
                        } catch (IOException e3) {
                            ThrowableExtension.printStackTrace(e3);
                        }
                    }
                    throw th;
                }
            } catch (FileNotFoundException e4) {
                e = e4;
                fileOutputStream = null;
            } catch (IOException e5) {
                e = e5;
                fileOutputStream = null;
            } catch (Throwable th3) {
                th = th3;
                r3 = 0;
                if (r3 != 0) {
                    r3.close();
                }
                throw th;
            }
        } catch (IOException e6) {
            ThrowableExtension.printStackTrace(e6);
            r3 = r3;
        }
        return null;
    }
}

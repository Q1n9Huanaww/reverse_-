package com.p024ds.daisi.async;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.util.DesUtil;
import com.p024ds.daisi.util.LogUtils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class SaveUseCountAsyncTask extends AsyncTask<File, Void, Void> {
    public static final int PAY_CHECKAPP_TRIAL = 1;
    private Context mContext;
    private int timeType;

    public SaveUseCountAsyncTask(Context context, int i) {
        this.mContext = context;
        this.timeType = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code duplicated, block: B:45:0x0160 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // android.os.AsyncTask
    public Void doInBackground(File... fileArr) throws Throwable {
        long jCurrentTimeMillis;
        FileOutputStream fileOutputStream;
        SharedPreferences sharedPreferences = ((AppContext) this.mContext).mSharedUsed;
        String string = sharedPreferences.getString(Constants.SCRIPT_USER_TYPE, "");
        String string2 = sharedPreferences.getString(Constants.SCRIPT_TRY_COUNT, "");
        if (this.timeType == 1 && !TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
            int i = Integer.parseInt(string2);
            Logger.m3442e("使用类型：userType=" + string + ",tryCount=" + i, new Object[0]);
            if (string.equals(String.valueOf(1)) && i > 0) {
                int i2 = sharedPreferences.getInt(Constants.SCRIPT_TRY_USE_COUNT, 0) + 1;
                sharedPreferences.edit().putInt(Constants.SCRIPT_TRY_USE_COUNT, i2).commit();
                boolean z = sharedPreferences.getBoolean(Constants.IS_TIMEOUT_FLAG, true);
                LogUtils.logError("SaveUseCountAsyncTask====isTimeout=" + z);
                if (z) {
                    jCurrentTimeMillis = System.currentTimeMillis() + 86400000;
                    sharedPreferences.edit().putBoolean(Constants.IS_TIMEOUT_FLAG, false).commit();
                    sharedPreferences.edit().putLong(Constants.SCRIPT_DAY_COUNT, jCurrentTimeMillis).commit();
                    LogUtils.logError("SaveUseCountAsyncTask====delayTime=" + jCurrentTimeMillis + ",==isTimeout" + z);
                } else {
                    jCurrentTimeMillis = sharedPreferences.getLong(Constants.SCRIPT_DAY_COUNT, 0L);
                    LogUtils.logError("SaveUseCountAsyncTask==else==delayTime=" + jCurrentTimeMillis + "==isTimeout" + z);
                }
                String str = "{\"Code\":\"" + string + "\",\"TryCount\":\"" + i2 + "\",\"Time\":\"" + jCurrentTimeMillis + "\"}";
                Logger.m3442e("tryData=" + str, new Object[0]);
                File file = fileArr[0];
                try {
                    if (file != null) {
                        try {
                            fileOutputStream = new FileOutputStream(file);
                            try {
                                try {
                                    fileOutputStream.write(DesUtil.encode(str, Constants.DESkey, Constants.DESIV).getBytes());
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
                                    return null;
                                }
                            } catch (Throwable th) {
                                th = th;
                                if (fileOutputStream != null) {
                                    try {
                                        fileOutputStream.close();
                                    } catch (IOException e2) {
                                        ThrowableExtension.printStackTrace(e2);
                                    }
                                }
                                throw th;
                            }
                        } catch (Exception e3) {
                            e = e3;
                            fileOutputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = null;
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    ThrowableExtension.printStackTrace(e4);
                }
            }
        }
        return null;
    }
}

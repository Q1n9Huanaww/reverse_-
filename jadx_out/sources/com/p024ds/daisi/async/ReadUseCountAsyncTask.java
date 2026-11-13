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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ReadUseCountAsyncTask extends AsyncTask<File, Void, Void> {
    private Context context;

    public ReadUseCountAsyncTask(Context context) {
        this.context = context;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(File... fileArr) {
        if (fileArr[0] != null) {
            try {
                FileInputStream fileInputStream = new FileInputStream(fileArr[0]);
                if (fileInputStream != null) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
                    String str = null;
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        str = line;
                    }
                    if (!TextUtils.isEmpty(str)) {
                        Logger.m3442e("加密后的数据：" + str, new Object[0]);
                        JSONObject jSONObject = new JSONObject(DesUtil.decode(str, Constants.DESkey, Constants.DESIV));
                        Logger.m3442e("jsonObject=" + jSONObject.toString(), new Object[0]);
                        String string = jSONObject.getString("TryCount");
                        String string2 = jSONObject.getString("Time");
                        if (!TextUtils.isEmpty(string2)) {
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            long j = Long.parseLong(string2);
                            SharedPreferences.Editor editorEdit = ((AppContext) this.context).mSharedUsed.edit();
                            if (jCurrentTimeMillis >= j) {
                                editorEdit.putInt(Constants.SCRIPT_TRY_USE_COUNT, 0);
                                editorEdit.putBoolean(Constants.IS_TIMEOUT_FLAG, true);
                            } else if (!TextUtils.isEmpty(string)) {
                                editorEdit.putInt(Constants.SCRIPT_TRY_USE_COUNT, Integer.parseInt(string));
                                editorEdit.putBoolean(Constants.IS_TIMEOUT_FLAG, false);
                                editorEdit.putLong(Constants.SCRIPT_DAY_COUNT, j);
                            }
                            editorEdit.commit();
                        }
                    }
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return null;
    }
}

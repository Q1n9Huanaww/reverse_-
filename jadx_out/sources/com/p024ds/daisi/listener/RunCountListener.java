package com.p024ds.daisi.listener;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.Constants;

/* JADX INFO: loaded from: classes.dex */
public class RunCountListener {
    private CountListener countListener;

    public interface CountListener {
        void onEmptyValueListener();

        void onMoreCountListener();

        void onZeroOfCountListener();
    }

    public boolean scriptCountHandler(Context context) {
        AppContext appContext = (AppContext) context;
        if (appContext.payCheckType == 1) {
            SharedPreferences sharedPreferences = appContext.mSharedUsed;
            String string = sharedPreferences.getString(Constants.SCRIPT_USER_TYPE, "");
            String string2 = sharedPreferences.getString(Constants.SCRIPT_TRY_COUNT, "");
            if (!TextUtils.isEmpty(string) && !TextUtils.isEmpty(string2)) {
                int i = Integer.parseInt(string2);
                if (string.equals(String.valueOf(1)) && i >= 0) {
                    if (i == 0) {
                        this.countListener.onZeroOfCountListener();
                        return true;
                    }
                    int i2 = sharedPreferences.getInt(Constants.SCRIPT_TRY_USE_COUNT, 0);
                    Log.e("xys", "useCount=" + i2 + ",scriptTryCount=" + string2);
                    if (i2 > i - 1) {
                        this.countListener.onMoreCountListener();
                        return true;
                    }
                }
            } else {
                this.countListener.onEmptyValueListener();
                return true;
            }
        }
        return false;
    }

    public void setCountListener(CountListener countListener) {
        this.countListener = countListener;
    }
}

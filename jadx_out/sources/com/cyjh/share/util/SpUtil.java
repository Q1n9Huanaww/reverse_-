package com.cyjh.share.util;

import android.content.SharedPreferences;
import com.cyjh.share.config.MyConfig;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: loaded from: classes.dex */
public class SpUtil {
    public static final String APP_VERSION = "app_version";
    public static final String LAST_APP_VERSION = "last_app_version";
    private static final String TAG = "SpUtil";
    private static SpUtil instance;
    private SharedPreferences mSharedPreferences;

    private void SpUtil() {
    }

    public static SpUtil getInstance() {
        if (instance == null) {
            synchronized (SpUtil.class) {
                if (instance == null) {
                    instance = new SpUtil();
                }
            }
        }
        return instance;
    }

    public void init(SharedPreferences sharedPreferences) {
        this.mSharedPreferences = sharedPreferences;
    }

    public String getAppVersion() {
        return this.mSharedPreferences != null ? this.mSharedPreferences.getString("app_version", MyConfig.getAppVersion()) : MyConfig.getAppVersion();
    }

    public void setAppVersion(String str) {
        if (this.mSharedPreferences != null) {
            try {
                this.mSharedPreferences.edit().putString("app_version", str).commit();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }

    public String getLastAppVersion() {
        return this.mSharedPreferences != null ? this.mSharedPreferences.getString(LAST_APP_VERSION, MyConfig.getAppVersion()) : MyConfig.getAppVersion();
    }

    public void setLastAppVersion(String str) {
        if (this.mSharedPreferences != null) {
            try {
                this.mSharedPreferences.edit().putString(LAST_APP_VERSION, str).commit();
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
    }
}

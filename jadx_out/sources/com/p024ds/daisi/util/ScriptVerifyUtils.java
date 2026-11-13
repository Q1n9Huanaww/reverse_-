package com.p024ds.daisi.util;

import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.Constants;

/* JADX INFO: loaded from: classes.dex */
public class ScriptVerifyUtils {
    public static void saveCodes(String str) {
        AppContext.getInstance().getSharedPreferences(Constants.USER_MESSAGE, 0).edit().putString(Constants.TOKEN_VERIFY_CODES, str).commit();
    }

    public static String acquireCodes() {
        return AppContext.getInstance().getSharedPreferences(Constants.USER_MESSAGE, 0).getString(Constants.TOKEN_VERIFY_CODES, "");
    }

    public static void saveTokenKey(String str) {
        AppContext.getInstance().getSharedPreferences(Constants.USER_MESSAGE, 0).edit().putString(Constants.TOKEN_VERIFY_KEY, str).commit();
    }

    public static String acquireTokenKey() {
        return AppContext.getInstance().getSharedPreferences(Constants.USER_MESSAGE, 0).getString(Constants.TOKEN_VERIFY_KEY, "");
    }
}

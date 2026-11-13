package com.cyjh.share.util;

import com.cyjh.share.C0854R;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: loaded from: classes.dex */
public class SignUtil {
    public static boolean rsaVerify(String str, String str2) {
        try {
            return RSAUtils.verify(str.getBytes(), MyConfig.getC4(), str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public static String rsaSign(String str) {
        try {
            return RSAUtils.sign(str.getBytes("utf-8"), MyConfig.getC5());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String des(String str, int i) throws Exception {
        return DesUtil.encodeToKey(str, VariableAndConstantsManager.getInstance().getContext().getResources().getStringArray(C0854R.array.test_signs)[i].getBytes());
    }

    public static String decode(String str, int i) {
        try {
            return DesUtil.decodeToKey(str, VariableAndConstantsManager.getInstance().getContext().getResources().getStringArray(C0854R.array.test_signs)[i].getBytes());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}

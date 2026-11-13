package com.iflytek.voiceads.utils;

import android.content.Context;
import com.iflytek.voiceads.config.SDKConstants;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1591e {
    /* JADX INFO: renamed from: a */
    public static int m3181a(Context context, String str, String str2) {
        try {
            Class<?>[] classes = Class.forName(context.getPackageName() + ".R").getClasses();
            Class<?> cls = null;
            for (int i = 0; i < classes.length; i++) {
                if (classes[i].getName().split("\\$")[1].equals(str)) {
                    cls = classes[i];
                    break;
                }
            }
            if (cls != null) {
                return cls.getField(str2).getInt(cls);
            }
            return 0;
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "FindResource:" + e.getMessage());
            return 0;
        }
    }
}

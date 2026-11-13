package com.iflytek.voiceads.utils;

import android.content.Context;

/* JADX INFO: renamed from: com.iflytek.voiceads.utils.l */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1598l {
    /* JADX INFO: renamed from: a */
    public static boolean m3210a(Context context, String str) {
        if (!C1595i.m3201b(context, str)) {
            return false;
        }
        try {
            switch (str) {
                case "android.permission.RECORD_AUDIO":
                    return new C1599m(context).m3211a();
                case "android.permission.WRITE_EXTERNAL_STORAGE":
                    return new C1600n().m3212a();
                default:
                    return false;
            }
        } catch (Throwable th) {
            return false;
        }
    }
}

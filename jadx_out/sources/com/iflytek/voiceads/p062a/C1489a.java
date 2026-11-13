package com.iflytek.voiceads.p062a;

import java.util.Locale;

/* JADX WARN: Classes with same name are omitted, all sources:
  assets/AdDex.4.0.1.dex
  classes.dex
 */
/* JADX INFO: renamed from: com.iflytek.voiceads.a.a */
/* JADX INFO: loaded from: classes.dex */
public class C1489a {

    /* JADX INFO: renamed from: a */
    private static Locale f3052a = Locale.US;

    /* JADX INFO: renamed from: a */
    public static String m2834a(int i) {
        String[] strArr = C1490b.f3053a;
        if (f3052a.equals(Locale.US)) {
            strArr = C1491c.f3055a;
        }
        return (i <= 0 || i >= strArr.length) ? m2835b(1) : strArr[i];
    }

    /* JADX INFO: renamed from: b */
    public static String m2835b(int i) {
        String[] strArr = C1490b.f3054b;
        if (f3052a.equals(Locale.US)) {
            strArr = C1491c.f3056b;
        }
        return (i < 0 || i >= strArr.length) ? "" : strArr[i];
    }
}

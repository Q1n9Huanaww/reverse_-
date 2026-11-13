package com.umeng.commonsdk.framework;

import android.content.Context;
import java.util.HashMap;

/* JADX INFO: renamed from: com.umeng.commonsdk.framework.c */
/* JADX INFO: compiled from: UMModuleRegister.java */
/* JADX INFO: loaded from: classes.dex */
public class C1733c {

    /* JADX INFO: renamed from: a */
    public static final String f3958a = "analytics";

    /* JADX INFO: renamed from: b */
    public static final String f3959b = "push";

    /* JADX INFO: renamed from: c */
    public static final String f3960c = "share";

    /* JADX INFO: renamed from: d */
    public static final String f3961d = "internal";

    /* JADX INFO: renamed from: e */
    private static HashMap<String, UMLogDataProtocol> f3962e = null;

    /* JADX INFO: renamed from: f */
    private static Context f3963f = null;

    /* JADX INFO: renamed from: g */
    private static final int f3964g = 16385;

    /* JADX INFO: renamed from: h */
    private static final int f3965h = 20480;

    /* JADX INFO: renamed from: i */
    private static final int f3966i = 24577;

    /* JADX INFO: renamed from: j */
    private static final int f3967j = 28672;

    /* JADX INFO: renamed from: k */
    private static final int f3968k = 32769;

    /* JADX INFO: renamed from: l */
    private static final int f3969l = 36864;

    /* JADX INFO: renamed from: a */
    public static String m3735a(int i) {
        String str = "analytics";
        if (i >= f3964g && i <= f3965h) {
            str = "push";
        }
        if (i >= f3966i && i <= f3967j) {
            str = "share";
        }
        return (i < 32769 || i > f3969l) ? str : f3961d;
    }

    /* JADX INFO: renamed from: a */
    public static boolean m3737a(int i, UMLogDataProtocol uMLogDataProtocol) {
        if (f3962e == null) {
            f3962e = new HashMap<>();
        }
        String strM3735a = m3735a(i);
        if (f3962e.containsKey(strM3735a)) {
            return true;
        }
        if (!m3733a().getPackageName().equals(C1732b.m3715a(m3733a().getApplicationContext()))) {
            return false;
        }
        f3962e.put(strM3735a, uMLogDataProtocol);
        return true;
    }

    /* JADX INFO: renamed from: a */
    public static void m3736a(Context context) {
        if (f3963f == null) {
            f3963f = context.getApplicationContext();
        }
    }

    /* JADX INFO: renamed from: a */
    public static UMLogDataProtocol m3734a(String str) {
        if (f3962e.containsKey(str)) {
            return f3962e.get(str);
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    public static Context m3733a() {
        return f3963f;
    }
}

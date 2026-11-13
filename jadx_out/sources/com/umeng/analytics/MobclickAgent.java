package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import java.util.HashMap;
import java.util.Map;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public class MobclickAgent {

    /* JADX INFO: renamed from: a */
    private static final String f3622a = "input map is null";

    public static void enableEncrypt(boolean z) {
    }

    public static void setCheckDevice(boolean z) {
    }

    public static void setDebugMode(boolean z) {
    }

    public static void setLatencyWindow(long j) {
    }

    public static void setLocation(double d, double d2) {
        C1703b.m3462a().m3465a(d, d2);
    }

    public static void setCatchUncaughtExceptions(boolean z) {
        C1703b.m3462a().m3484a(z);
    }

    public static void setSecret(Context context, String str) throws Throwable {
        C1703b.m3462a().m3487b(context, str);
    }

    public static void setScenarioType(Context context, EScenarioType eScenarioType) throws Throwable {
        C1703b.m3462a().m3469a(context, eScenarioType);
    }

    public static void setSessionContinueMillis(long j) {
        C1703b.m3462a().m3466a(j);
    }

    public static C1703b getAgent() {
        return C1703b.m3462a();
    }

    public static void setOpenGLContext(GL10 gl10) {
        C1703b.m3462a().m3483a(gl10);
    }

    public static void openActivityDurationTrack(boolean z) {
        C1703b.m3462a().m3489b(z);
    }

    public static void onPageStart(String str) throws Throwable {
        if (!TextUtils.isEmpty(str)) {
            C1703b.m3462a().m3480a(str);
        } else {
            MLog.m4337e("pageName is null or empty");
        }
    }

    public static void onPageEnd(String str) throws Throwable {
        if (!TextUtils.isEmpty(str)) {
            C1703b.m3462a().m3488b(str);
        } else {
            MLog.m4337e("pageName is null or empty");
        }
    }

    public static void onPause(Context context) throws Throwable {
        C1703b.m3462a().m3486b(context);
    }

    public static void onResume(Context context) throws Throwable {
        if (context == null) {
            MLog.m4337e("unexpected null context in onResume");
        } else {
            C1703b.m3462a().m3467a(context);
        }
    }

    public static void reportError(Context context, String str) throws Throwable {
        C1703b.m3462a().m3470a(context, str);
    }

    public static void reportError(Context context, Throwable th) throws Throwable {
        C1703b.m3462a().m3476a(context, th);
    }

    public static void onEvent(Context context, String str) throws Throwable {
        C1703b.m3462a().m3472a(context, str, null, -1L, 1);
    }

    public static void onEvent(Context context, String str, String str2) throws Throwable {
        if (TextUtils.isEmpty(str2)) {
            MLog.m4355w("label is null or empty");
        } else {
            C1703b.m3462a().m3472a(context, str, str2, -1L, 1);
        }
    }

    public static void onEvent(Context context, String str, Map<String, String> map) throws Throwable {
        if (map == null) {
            MLog.m4337e(f3622a);
        } else {
            C1703b.m3462a().m3475a(context, str, new HashMap(map), -1L);
        }
    }

    public static void onEventValue(Context context, String str, Map<String, String> map, int i) throws Throwable {
        HashMap map2;
        if (map == null) {
            map2 = new HashMap();
        } else {
            map2 = new HashMap(map);
        }
        map2.put("__ct__", Integer.valueOf(i));
        C1703b.m3462a().m3475a(context, str, map2, -1L);
    }

    public static void onKillProcess(Context context) {
        C1703b.m3462a().m3491c(context);
    }

    public static void onProfileSignIn(String str) throws Throwable {
        onProfileSignIn("_adhoc", str);
    }

    public static void onProfileSignIn(String str, String str2) throws Throwable {
        if (TextUtils.isEmpty(str2)) {
            MLog.m4355w("uid is null");
            return;
        }
        if (str2.length() > 64) {
            MLog.m4355w("uid is Illegal(length bigger then  legitimate length).");
            return;
        }
        if (TextUtils.isEmpty(str)) {
            C1703b.m3462a().m3481a("_adhoc", str2);
        } else if (str.length() > 32) {
            MLog.m4355w("provider is Illegal(length bigger then  legitimate length).");
        } else {
            C1703b.m3462a().m3481a(str, str2);
        }
    }

    public static void onProfileSignOff() throws Throwable {
        C1703b.m3462a().m3499g();
    }

    public enum EScenarioType {
        E_UM_NORMAL(0),
        E_UM_GAME(1),
        E_DUM_NORMAL(160),
        E_DUM_GAME(161);


        /* JADX INFO: renamed from: a */
        private int f3624a;

        EScenarioType(int i) {
            this.f3624a = i;
        }

        public int toValue() {
            return this.f3624a;
        }
    }
}

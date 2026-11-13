package com.umeng.analytics.dplus;

import android.content.Context;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.statistics.common.MLog;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UMADplus {
    public static void track(Context context, String str) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        } else {
            MobclickAgent.getAgent().m3474a(context, str, (Map<String, Object>) null);
        }
    }

    public static void track(Context context, String str, Map<String, Object> map) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
            return;
        }
        if (map == null || map.size() <= 0) {
            MLog.m4337e("the map is null!");
        }
        MobclickAgent.getAgent().m3474a(context, str, map);
    }

    public static void registerSuperProperty(Context context, String str, Object obj) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        } else {
            MobclickAgent.getAgent().m3471a(context, str, obj);
        }
    }

    public static void unregisterSuperProperty(Context context, String str) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        } else {
            MobclickAgent.getAgent().m3492c(context, str);
        }
    }

    public static Object getSuperProperty(Context context, String str) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
            return null;
        }
        return MobclickAgent.getAgent().m3493d(context, str);
    }

    public static String getSuperProperties(Context context) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
            return null;
        }
        return MobclickAgent.getAgent().m3494d(context);
    }

    public static void clearSuperProperties(Context context) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        } else {
            MobclickAgent.getAgent().m3497e(context);
        }
    }

    public static void setFirstLaunchEvent(Context context, List<String> list) throws Throwable {
        if (!AnalyticsConfig.FLAG_DPLUS) {
            MLog.m4337e("UMADplus class is Dplus API, can't be use in no-Dplus scenario.");
        } else {
            MobclickAgent.getAgent().m3477a(context, list);
        }
    }
}

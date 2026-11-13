package com.iflytek.voiceads.dex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.text.TextUtils;
import com.didi.virtualapk.internal.Constants;
import com.iflytek.voiceads.config.SDKLogger;
import com.iflytek.voiceads.config.Version;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.lang.reflect.Array;
import java.lang.reflect.Field;

/* JADX INFO: renamed from: com.iflytek.voiceads.dex.a */
/* JADX INFO: loaded from: classes.dex */
public class C1521a {

    /* JADX INFO: renamed from: a */
    static DexClassLoader f3180a;

    /* JADX INFO: renamed from: a */
    public static DexClassLoader m2907a(Context context) {
        String str = Version.getVersion().split("_")[0];
        String strM2920a = C1523c.m2920a(context, str);
        SDKLogger.m2899d("sdkVersion=" + str + ";localVersion=" + strM2920a);
        return m2914b(context, strM2920a);
    }

    /* JADX INFO: renamed from: a */
    public static Class<?> m2908a(Context context, String str) throws ClassNotFoundException {
        SDKLogger.m2899d("loadAdClass");
        try {
            if (f3180a == null) {
                f3180a = m2907a(context);
            }
            return f3180a.loadClass(str);
        } catch (Exception unused) {
            if (f3180a == null) {
                f3180a = m2913b(context);
            }
            return f3180a.loadClass(str);
        }
    }

    /* JADX INFO: renamed from: a */
    public static Object m2909a(Object obj) throws IllegalAccessException, NoSuchFieldException, ClassNotFoundException, IllegalArgumentException {
        return m2910a(obj, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    /* JADX INFO: renamed from: a */
    public static Object m2910a(Object obj, Class<?> cls, String str) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    /* JADX INFO: renamed from: a */
    public static Object m2911a(Object obj, Object obj2) {
        Class<?> componentType = obj.getClass().getComponentType();
        int length = Array.getLength(obj);
        int length2 = Array.getLength(obj2) + length;
        Object objNewInstance = Array.newInstance(componentType, length2);
        int i = 0;
        while (i < length2) {
            Array.set(objNewInstance, i, i < length ? Array.get(obj, i) : Array.get(obj2, i - length));
            i++;
        }
        return objNewInstance;
    }

    /* JADX INFO: renamed from: a */
    public static void m2912a(Object obj, Class<?> cls, String str, Object obj2) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        Field declaredField = cls.getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }

    /* JADX INFO: renamed from: b */
    public static DexClassLoader m2913b(Context context) {
        String str = Version.getVersion().split("_")[0];
        String strM2926b = C1523c.m2926b(context, str);
        SDKLogger.m2899d("sdkVersion=" + str + ";rawVersion=" + strM2926b);
        return m2914b(context, strM2926b);
    }

    /* JADX INFO: renamed from: b */
    private static DexClassLoader m2914b(Context context, String str) {
        return new DexClassLoader(context.getFilesDir().getAbsolutePath() + File.separator + str, context.getDir(Constants.OPTIMIZE_DIR, 0).getAbsolutePath(), null, context.getClassLoader());
    }

    /* JADX INFO: renamed from: b */
    public static Object m2915b(Object obj) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        return m2910a(obj, obj.getClass(), "dexElements");
    }

    /* JADX INFO: renamed from: c */
    public static String m2916c(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                String string = applicationInfo.metaData.get("IFLYTEK_APPKEY_AD").toString();
                if (!TextUtils.isEmpty(string)) {
                    return string.trim();
                }
            }
        } catch (Exception e) {
            SDKLogger.m2901e("getAppId:" + e.getMessage());
        }
        return "";
    }
}

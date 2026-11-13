package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.commonsdk.amap.C1730b;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.stateless.UMSLEnvelopeBuild;
import com.umeng.commonsdk.statistics.C1810a;
import com.umeng.commonsdk.statistics.C1811b;
import com.umeng.commonsdk.statistics.C1812c;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.UMUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public class UMConfigure {
    public static final int DEVICE_TYPE_BOX = 2;
    public static final int DEVICE_TYPE_PHONE = 1;
    private static final String KEY_FILE_NAME_APPKEY = "APPKEY";
    private static final String KEY_FILE_NAME_LOG = "LOG";
    private static final String KEY_METHOD_NAME_PUSH_SETCHANNEL = "setMessageChannel";
    private static final String KEY_METHOD_NAME_PUSH_SET_SECRET = "setSecret";
    private static final String KEY_METHOD_NAME_SETAPPKEY = "setAppkey";
    private static final String KEY_METHOD_NAME_SETCHANNEL = "setChannel";
    private static final String KEY_METHOD_NAME_SETDEBUGMODE = "setDebugMode";
    private static final String TAG = "UMConfigure";
    private static boolean debugLog;
    private static C1730b readUMAmapTimer;
    private static C1730b writeUMAmapTimer;

    private static Class<?> getClass(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException e) {
            if (debugLog) {
                Log.e(TAG, "getClass e is " + e.toString());
            }
            return null;
        }
    }

    private Object getInstanceObject(Class<?> cls) {
        if (cls != null) {
            try {
                return cls.newInstance();
            } catch (IllegalAccessException e) {
                if (debugLog) {
                    Log.e(TAG, "getInstanceObject e is " + e.toString());
                }
            } catch (InstantiationException e2) {
                if (debugLog) {
                    Log.e(TAG, "getInstanceObject e is " + e2.toString());
                }
            }
        } else if (debugLog) {
            Log.e(TAG, "getInstanceObject cla is null");
        }
        return null;
    }

    private static Object getDecInstanceObject(Class<?> cls) {
        Constructor<?> declaredConstructor;
        if (cls != null) {
            try {
                declaredConstructor = cls.getDeclaredConstructor(new Class[0]);
            } catch (NoSuchMethodException e) {
                if (debugLog) {
                    Log.e(TAG, "getDecInstanceObject e is " + e.toString());
                }
                declaredConstructor = null;
            }
            if (declaredConstructor != null) {
                declaredConstructor.setAccessible(true);
                try {
                    return declaredConstructor.newInstance(new Object[0]);
                } catch (IllegalAccessException e2) {
                    if (debugLog) {
                        Log.e(TAG, "getDecInstanceObject e is " + e2.toString());
                    }
                } catch (IllegalArgumentException e3) {
                    if (debugLog) {
                        Log.e(TAG, "getDecInstanceObject e is " + e3.toString());
                    }
                } catch (InstantiationException e4) {
                    if (debugLog) {
                        Log.e(TAG, "getDecInstanceObject e is " + e4.toString());
                    }
                } catch (InvocationTargetException e5) {
                    if (debugLog) {
                        Log.e(TAG, "getDecInstanceObject e is " + e5.toString());
                    }
                }
            } else if (debugLog) {
                Log.e(TAG, "getDecInstanceObject constructor is  null");
            }
        }
        return null;
    }

    private static Method getDecMethod(Class<?> cls, String str, Class<?>[] clsArr) throws NoSuchMethodException {
        Method declaredMethod = null;
        if (cls != null) {
            try {
                declaredMethod = cls.getDeclaredMethod(str, clsArr);
            } catch (NoSuchMethodException e) {
                if (debugLog) {
                    Log.e(TAG, "getDecMethod e is " + e.toString());
                }
            }
            if (declaredMethod != null) {
                declaredMethod.setAccessible(true);
            } else if (debugLog) {
                Log.e(TAG, "getDecMethod met is null");
            }
        } else if (debugLog) {
            Log.e(TAG, "getDecMethod className is null");
        }
        return declaredMethod;
    }

    private static void invoke(Method method, Object obj, Object[] objArr) {
        if (method == null || obj == null) {
            return;
        }
        try {
            method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            if (debugLog) {
                Log.e(TAG, "invoke e is " + e.toString());
            }
        } catch (IllegalArgumentException e2) {
            if (debugLog) {
                Log.e(TAG, "invoke e is " + e2.toString());
            }
        } catch (InvocationTargetException e3) {
            if (debugLog) {
                Log.e(TAG, "invoke e is " + e3.toString());
            }
        }
    }

    private static void invoke(Method method, Object[] objArr) {
        if (method != null) {
            try {
                method.invoke(null, objArr);
            } catch (IllegalAccessException e) {
                if (debugLog) {
                    Log.e(TAG, "invoke e is " + e.toString());
                }
            } catch (IllegalArgumentException e2) {
                if (debugLog) {
                    Log.e(TAG, "invoke e is " + e2.toString());
                }
            } catch (InvocationTargetException e3) {
                if (debugLog) {
                    Log.e(TAG, "invoke e is " + e3.toString());
                }
            }
        }
    }

    private static void setFile(Class<?> cls, String str, String str2) {
        if (cls != null) {
            try {
                Field field = cls.getField(str);
                field.set(str, str2);
                if (debugLog) {
                    Log.i(TAG, "setFile value is " + field.get(str).toString());
                }
            } catch (Exception e) {
                if (debugLog) {
                    Log.e(TAG, "setFile e is " + e.toString());
                }
            }
        }
    }

    private static void setFile(Class<?> cls, String str, boolean z) {
        if (cls != null) {
            try {
                Field field = cls.getField(str);
                field.set(str, Boolean.valueOf(z));
                if (debugLog) {
                    Log.i(TAG, "setFile value is " + field.get(str).toString());
                }
            } catch (Exception e) {
                if (debugLog) {
                    Log.e(TAG, "setFile e is " + e.toString());
                }
            }
        }
    }

    private static void saveSDKComponent() {
        StringBuffer stringBuffer = new StringBuffer();
        if (getClass("com.umeng.analytics.MobclickAgent") != null) {
            stringBuffer.append(C1799v.f4296ah);
        }
        if (getClass("com.umeng.message.PushAgent") != null) {
            stringBuffer.append(C1799v.f4299ak);
        }
        if (getClass("com.umeng.socialize.UMShareAPI") != null) {
            stringBuffer.append(C1799v.f4300al);
        }
        if (getClass("com.umeng.error.UMError") != null) {
            stringBuffer.append("e");
        }
        if (getClass("com.umeng.commonsdk.UMConfigureImpl") != null) {
            stringBuffer.append(C1799v.f4301am);
        }
        if (getClass("com.umeng.commonsdk.amap.UMAmapConfig") != null) {
            stringBuffer.append("o");
        }
        if (TextUtils.isEmpty(stringBuffer)) {
            return;
        }
        C1812c.f4490a = stringBuffer.toString();
        UMSLEnvelopeBuild.module = stringBuffer.toString();
    }

    public static void init(Context context, int i, String str) {
        init(context, null, null, i, str);
    }

    public static void init(Context context, String str, String str2, int i, String str3) {
        Method declaredMethod;
        Method declaredMethod2;
        Method declaredMethod3;
        Method declaredMethod4;
        Method declaredMethod5;
        Method declaredMethod6;
        Object objInvoke;
        Method declaredMethod7;
        Method declaredMethod8;
        Object objInvoke2;
        Method declaredMethod9;
        try {
            try {
                if (context == null) {
                    if (debugLog) {
                        Log.e(TAG, "context is null !!!");
                        return;
                    }
                    return;
                }
                Context applicationContext = context.getApplicationContext();
                if (TextUtils.isEmpty(str)) {
                    str = UMUtils.getAppkeyByXML(applicationContext);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = UMUtils.getChannelByXML(applicationContext);
                }
                if (TextUtils.isEmpty(str2)) {
                    str2 = "Unknown";
                }
                UMUtils.setChannel(applicationContext, str2);
                if (debugLog) {
                    Log.i(TAG, "channel is " + str2);
                }
                saveSDKComponent();
                try {
                    Class<?> cls = Class.forName("com.umeng.message.PushAgent");
                    if (cls != null && (declaredMethod8 = cls.getDeclaredMethod("getInstance", Context.class)) != null && (objInvoke2 = declaredMethod8.invoke(cls, applicationContext)) != null && (declaredMethod9 = cls.getDeclaredMethod(KEY_METHOD_NAME_SETAPPKEY, String.class)) != null) {
                        declaredMethod9.setAccessible(true);
                        declaredMethod9.invoke(objInvoke2, str);
                    }
                } catch (Exception e) {
                    if (debugLog) {
                        Log.e(TAG, "e is " + e);
                    }
                }
                try {
                    Class<?> cls2 = Class.forName("com.umeng.message.PushAgent");
                    if (cls2 != null && (declaredMethod6 = cls2.getDeclaredMethod("getInstance", Context.class)) != null && (objInvoke = declaredMethod6.invoke(cls2, applicationContext)) != null && (declaredMethod7 = cls2.getDeclaredMethod(KEY_METHOD_NAME_PUSH_SETCHANNEL, String.class)) != null) {
                        declaredMethod7.setAccessible(true);
                        declaredMethod7.invoke(objInvoke, str2);
                    }
                } catch (Exception e2) {
                    if (debugLog) {
                        Log.e(TAG, "e is " + e2);
                    }
                }
                try {
                    Class<?> cls3 = getClass("com.umeng.socialize.UMShareAPI");
                    setFile(cls3, KEY_FILE_NAME_APPKEY, str);
                    if (cls3 != null && (declaredMethod5 = cls3.getDeclaredMethod("init", Context.class, String.class)) != null) {
                        declaredMethod5.setAccessible(true);
                        declaredMethod5.invoke(cls3, applicationContext, str);
                    }
                } catch (Throwable th) {
                    if (debugLog) {
                        Log.e(TAG, "e is " + th);
                    }
                }
                if (TextUtils.isEmpty(str)) {
                    if (debugLog) {
                        Log.e(TAG, "appkey is null !!!");
                        return;
                    }
                    return;
                }
                UMUtils.setAppkey(applicationContext, str);
                String lastAppkey = UMUtils.getLastAppkey(applicationContext);
                if (!TextUtils.isEmpty(str) && !str.equals(lastAppkey)) {
                    if (!TextUtils.isEmpty(lastAppkey) && debugLog) {
                        Log.i(TAG, "appkey is change !!!");
                    }
                    UMUtils.setLastAppkey(applicationContext, str);
                }
                if (debugLog) {
                    Log.i(TAG, "current appkey is " + str + ", last appkey is " + lastAppkey);
                }
                C1811b.m4322a(i);
                if (TextUtils.isEmpty(str3)) {
                    if (debugLog) {
                        Log.e(TAG, "push secret 非法 ！！！");
                    }
                } else {
                    if (debugLog) {
                        Log.i(TAG, "push secret is " + str3);
                    }
                    try {
                        Class<?> cls4 = Class.forName("com.umeng.message.PushAgent");
                        if (cls4 != null && (declaredMethod = cls4.getDeclaredMethod("getInstance", Context.class)) != null) {
                            declaredMethod.setAccessible(true);
                            Object objInvoke3 = declaredMethod.invoke(cls4, applicationContext);
                            if (objInvoke3 != null && (declaredMethod2 = cls4.getDeclaredMethod(KEY_METHOD_NAME_PUSH_SET_SECRET, String.class)) != null) {
                                declaredMethod2.setAccessible(true);
                                declaredMethod2.invoke(objInvoke3, str3);
                            }
                        }
                    } catch (Throwable th2) {
                        if (debugLog) {
                            Log.e(TAG, "push secret e is " + th2);
                        }
                    }
                }
                try {
                    Class<?> cls5 = Class.forName("com.umeng.error.UMError");
                    if (cls5 != null && (declaredMethod4 = cls5.getDeclaredMethod("init", Context.class)) != null) {
                        declaredMethod4.setAccessible(true);
                        declaredMethod4.invoke(cls5, applicationContext);
                    }
                } catch (Throwable th3) {
                    if (debugLog) {
                        Log.e(TAG, "crash e is " + th3);
                    }
                }
                try {
                    Class<?> cls6 = Class.forName("com.umeng.commonsdk.UMConfigureImpl");
                    if (cls6 == null || (declaredMethod3 = cls6.getDeclaredMethod("init", Context.class)) == null) {
                        return;
                    }
                    declaredMethod3.setAccessible(true);
                    declaredMethod3.invoke(cls6, applicationContext);
                } catch (Throwable th4) {
                    if (debugLog) {
                        Log.e(TAG, "crash e is " + th4);
                    }
                }
            } catch (Throwable th5) {
                if (debugLog) {
                    Log.e(TAG, "init e is " + th5);
                }
            }
        } catch (Exception e3) {
            if (debugLog) {
                Log.e(TAG, "init e is " + e3);
            }
        }
    }

    public static void setLogEnabled(boolean z) {
        try {
            debugLog = z;
            if (debugLog) {
                Log.i(TAG, "common sdk version is 1.3.2");
            }
            MLog.DEBUG = z;
            Class<?> cls = getClass("com.umeng.message.PushAgent");
            invoke(getDecMethod(cls, KEY_METHOD_NAME_SETDEBUGMODE, new Class[]{Boolean.TYPE}), getDecInstanceObject(cls), new Object[]{Boolean.valueOf(z)});
            setFile(getClass("com.umeng.socialize.Config"), "DEBUG", z);
        } catch (Exception e) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + e);
            }
        } catch (Throwable th) {
            if (debugLog) {
                Log.e(TAG, "set log enabled e is " + th);
            }
        }
    }

    public static void setEncryptEnabled(boolean z) {
        C1812c.m4328a(z);
        UMSLEnvelopeBuild.setEncryptEnabled(z);
    }

    public static String getUMIDString(Context context) {
        if (context != null) {
            return UMUtils.getUMId(context.getApplicationContext());
        }
        return null;
    }

    private static void setLatencyWindow(long j) {
        C1810a.f4481a = ((int) j) * 1000;
    }

    private static void setCheckDevice(boolean z) {
        C1811b.f4486d = z;
    }
}

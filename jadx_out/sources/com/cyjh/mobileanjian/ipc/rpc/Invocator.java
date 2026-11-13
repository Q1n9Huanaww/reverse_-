package com.cyjh.mobileanjian.ipc.rpc;

import com.cyjh.mobileanjian.ipc.utils.C0817n;
import com.cyjh.mobileanjian.ipc.utils.C0820q;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Invocator {
    public static final String TAG = "Invocator";

    public static boolean isListEmpty(List list) {
        return list == null || list.size() == 0;
    }

    public static int invoke(String str, String str2, String str3, List<String> list, List<String> list2) {
        StringBuilder sb = new StringBuilder("want method: ");
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        sb.append(".");
        sb.append(str3);
        new StringBuilder("types: ").append(list);
        new StringBuilder("value: ").append(list2);
        if (list == null && list2 != null) {
            return 6;
        }
        if (list != null && list2 == null) {
            return 6;
        }
        if (list != null && list2 != null && list.size() != list2.size()) {
            return 6;
        }
        Class<?>[] clsArr = null;
        if (list != null) {
            try {
                if (list.size() > 0) {
                    clsArr = new Class[list.size()];
                    for (int i = 0; i < clsArr.length; i++) {
                        clsArr[i] = C0817n.f1448j.m1085a(list.get(i));
                    }
                }
            } catch (ClassNotFoundException unused) {
                return 1;
            } catch (IllegalAccessException e) {
                ThrowableExtension.printStackTrace(e);
                return 3;
            } catch (IllegalArgumentException e2) {
                ThrowableExtension.printStackTrace(e2);
                return 4;
            } catch (NoSuchMethodException unused2) {
                return 2;
            } catch (InvocationTargetException e3) {
                ThrowableExtension.printStackTrace(e3);
                return 5;
            }
        }
        Class<?> cls = Class.forName(str + "." + str2);
        Method declaredMethod = cls.getDeclaredMethod(str3, clsArr);
        new StringBuilder("Get method: ").append(declaredMethod);
        Object[] objArrM1116a = C0820q.m1116a(list, list2);
        if (clsArr != null && objArrM1116a == null) {
            return 7;
        }
        declaredMethod.invoke(cls, objArrM1116a);
        return 0;
    }

    /* JADX WARN: Code duplicated, block: B:25:0x006f  */
    public static Object invoke(String str, String str2, String str3, List<String> list, List<String> list2, int i) {
        Class<?>[] clsArr;
        StringBuilder sb = new StringBuilder("want method: ");
        sb.append(str);
        sb.append(".");
        sb.append(str2);
        sb.append(".");
        sb.append(str3);
        new StringBuilder("types: ").append(list);
        new StringBuilder("value: ").append(list2);
        if ((list == null && list2 != null) || ((list != null && list2 == null) || (list != null && list2 != null && list.size() != list2.size()))) {
            return null;
        }
        if (list != null) {
            try {
                if (list.size() > 0) {
                    clsArr = new Class[list.size()];
                    for (int i2 = 0; i2 < clsArr.length; i2++) {
                        clsArr[i2] = C0817n.f1448j.m1085a(list.get(i2));
                    }
                } else {
                    clsArr = null;
                }
            } catch (ClassNotFoundException | NoSuchMethodException unused) {
                return null;
            } catch (IllegalAccessException e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            } catch (IllegalArgumentException e2) {
                ThrowableExtension.printStackTrace(e2);
                return null;
            } catch (InvocationTargetException e3) {
                ThrowableExtension.printStackTrace(e3);
                return null;
            }
        } else {
            clsArr = null;
        }
        Class<?> cls = Class.forName(str + "." + str2);
        Method declaredMethod = cls.getDeclaredMethod(str3, clsArr);
        new StringBuilder("Get method: ").append(declaredMethod);
        Object[] objArrM1116a = C0820q.m1116a(list, list2);
        if (clsArr != null && objArrM1116a == null) {
            return null;
        }
        Object objInvoke = declaredMethod.invoke(cls, objArrM1116a);
        new StringBuilder("result: ").append(objInvoke);
        return objInvoke;
    }
}

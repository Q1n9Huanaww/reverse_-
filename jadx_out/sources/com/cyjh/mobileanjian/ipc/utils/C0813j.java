package com.cyjh.mobileanjian.ipc.utils;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.j */
/* JADX INFO: compiled from: RefInvoke.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0813j {
    /* JADX INFO: renamed from: a */
    private static Object m1097a(String str, String str2, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(null, objArr);
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
            return null;
        } catch (NoSuchMethodException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        } catch (SecurityException e5) {
            ThrowableExtension.printStackTrace(e5);
            return null;
        } catch (InvocationTargetException e6) {
            ThrowableExtension.printStackTrace(e6);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static Object m1096a(String str, String str2, Object obj, Class[] clsArr, Object[] objArr) {
        try {
            return Class.forName(str).getMethod(str2, clsArr).invoke(obj, objArr);
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
            return null;
        } catch (NoSuchMethodException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        } catch (SecurityException e5) {
            ThrowableExtension.printStackTrace(e5);
            return null;
        } catch (InvocationTargetException e6) {
            ThrowableExtension.printStackTrace(e6);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static Object m1094a(String str, Object obj, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(obj);
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
            return null;
        } catch (NoSuchFieldException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        } catch (SecurityException e5) {
            ThrowableExtension.printStackTrace(e5);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static Object m1095a(String str, String str2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            return declaredField.get(null);
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
            return null;
        } catch (NoSuchFieldException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        } catch (SecurityException e5) {
            ThrowableExtension.printStackTrace(e5);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1099a(String str, String str2, Object obj, Object obj2) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            declaredField.set(obj, obj2);
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (NoSuchFieldException e4) {
            ThrowableExtension.printStackTrace(e4);
        } catch (SecurityException e5) {
            ThrowableExtension.printStackTrace(e5);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1098a(String str, String str2, Object obj) {
        try {
            Field declaredField = Class.forName(str).getDeclaredField(str2);
            declaredField.setAccessible(true);
            declaredField.set(null, obj);
        } catch (ClassNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IllegalArgumentException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (NoSuchFieldException e4) {
            ThrowableExtension.printStackTrace(e4);
        } catch (SecurityException e5) {
            ThrowableExtension.printStackTrace(e5);
        }
    }
}

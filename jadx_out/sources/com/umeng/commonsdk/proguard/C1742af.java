package com.umeng.commonsdk.proguard;

import java.lang.reflect.InvocationTargetException;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.af */
/* JADX INFO: compiled from: TEnumHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class C1742af {
    /* JADX INFO: renamed from: a */
    public static InterfaceC1741ae m3805a(Class<? extends InterfaceC1741ae> cls, int i) {
        try {
            return (InterfaceC1741ae) cls.getMethod("findByValue", Integer.TYPE).invoke(null, Integer.valueOf(i));
        } catch (IllegalAccessException unused) {
            return null;
        } catch (NoSuchMethodException unused2) {
            return null;
        } catch (InvocationTargetException unused3) {
            return null;
        }
    }
}

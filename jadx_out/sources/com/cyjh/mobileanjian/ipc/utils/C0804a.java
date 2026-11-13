package com.cyjh.mobileanjian.ipc.utils;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.a */
/* JADX INFO: compiled from: ArrayUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0804a {
    /* JADX INFO: renamed from: a */
    public static boolean m1062a(Object[] objArr) {
        return objArr == null || objArr.length == 0;
    }

    /* JADX INFO: renamed from: b */
    public static boolean m1063b(Object[] objArr) {
        for (Object obj : objArr) {
            if (obj == null) {
                return true;
            }
        }
        return false;
    }
}

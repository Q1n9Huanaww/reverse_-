package com.cyjh.mobileanjian.ipc.utils;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.m */
/* JADX INFO: compiled from: RpcError.java */
/* JADX INFO: loaded from: classes.dex */
public class C0816m {

    /* JADX INFO: renamed from: a */
    public static final String f1430a = "m";

    /* JADX INFO: renamed from: b */
    public static final int f1431b = 0;

    /* JADX INFO: renamed from: c */
    public static final int f1432c = 1;

    /* JADX INFO: renamed from: d */
    public static final int f1433d = 2;

    /* JADX INFO: renamed from: e */
    public static final int f1434e = 3;

    /* JADX INFO: renamed from: f */
    public static final int f1435f = 4;

    /* JADX INFO: renamed from: g */
    public static final int f1436g = 5;

    /* JADX INFO: renamed from: h */
    public static final int f1437h = 6;

    /* JADX INFO: renamed from: i */
    public static final int f1438i = 7;

    /* JADX INFO: renamed from: a */
    private static String m1110a(int i) {
        return C0816m.class.getFields()[i].getName();
    }

    /* JADX INFO: renamed from: b */
    private static void m1111b(int i) {
        StringBuilder sb = new StringBuilder("errno=");
        sb.append(i);
        sb.append("; ");
        sb.append(C0816m.class.getFields()[i].getName());
    }
}

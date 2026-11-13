package com.cyjh.mobileanjian.ipc.utils;

import android.content.Context;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.k */
/* JADX INFO: compiled from: ResourceUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0814k {
    /* JADX INFO: renamed from: a */
    private static int m1100a(Context context, String str) {
        return context.getResources().getIdentifier(str, "layout", context.getPackageName());
    }

    /* JADX INFO: renamed from: b */
    private static int m1101b(Context context, String str) {
        return context.getResources().getIdentifier(str, "string", context.getPackageName());
    }

    /* JADX INFO: renamed from: c */
    private static int m1102c(Context context, String str) {
        return context.getResources().getIdentifier(str, "bool", context.getPackageName());
    }

    /* JADX INFO: renamed from: d */
    private static int m1103d(Context context, String str) {
        return context.getResources().getIdentifier(str, "drawable", context.getPackageName());
    }

    /* JADX INFO: renamed from: e */
    private static int m1104e(Context context, String str) {
        return context.getResources().getIdentifier(str, "style", context.getPackageName());
    }

    /* JADX INFO: renamed from: f */
    private static Object m1105f(Context context, String str) {
        return Integer.valueOf(context.getResources().getIdentifier(str, "styleable", context.getPackageName()));
    }

    /* JADX INFO: renamed from: g */
    private static int m1106g(Context context, String str) {
        return context.getResources().getIdentifier(str, "anim", context.getPackageName());
    }

    /* JADX INFO: renamed from: h */
    private static int m1107h(Context context, String str) {
        return context.getResources().getIdentifier(str, "id", context.getPackageName());
    }

    /* JADX INFO: renamed from: i */
    private static int m1108i(Context context, String str) {
        return context.getResources().getIdentifier(str, "color", context.getPackageName());
    }
}

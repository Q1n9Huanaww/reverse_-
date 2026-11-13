package com.cyjh.mobileanjian.ipc.utils;

import java.util.HashMap;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.h */
/* JADX INFO: compiled from: MyHashMap.java */
/* JADX INFO: loaded from: classes.dex */
public class C0811h {

    /* JADX INFO: renamed from: c */
    private static final long f1425c = 2749416527681438711L;

    /* JADX INFO: renamed from: d */
    private HashMap<String, Class> f1428d = new HashMap<>();

    /* JADX INFO: renamed from: a */
    HashMap<String, Integer> f1426a = new HashMap<>();

    /* JADX INFO: renamed from: b */
    HashMap<Class, Integer> f1427b = new HashMap<>();

    /* JADX INFO: renamed from: a */
    public final void m1086a(String str, Class cls, Integer num) {
        this.f1428d.put(str, cls);
        this.f1426a.put(str, num);
        this.f1427b.put(cls, num);
    }

    /* JADX INFO: renamed from: a */
    public final Class m1085a(String str) {
        return this.f1428d.get(str);
    }

    /* JADX INFO: renamed from: b */
    private int m1084b(String str) {
        return this.f1426a.get(str).intValue();
    }

    /* JADX INFO: renamed from: a */
    private int m1083a(Class cls) {
        return this.f1427b.get(cls).intValue();
    }
}

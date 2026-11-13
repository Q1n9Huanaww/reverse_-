package com.cyjh.mobileanjian.ipc.utils;

import android.os.Environment;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.utils.b */
/* JADX INFO: compiled from: BuildProperties.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0805b {

    /* JADX INFO: renamed from: a */
    private final Properties f1417a = new Properties();

    C0805b() {
        try {
            this.f1417a.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException unused) {
        }
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1074a(Object obj) {
        return this.f1417a.containsKey(obj);
    }

    /* JADX INFO: renamed from: b */
    private boolean m1066b(Object obj) {
        return this.f1417a.containsValue(obj);
    }

    /* JADX INFO: renamed from: a */
    private Set<Map.Entry<Object, Object>> m1064a() {
        return this.f1417a.entrySet();
    }

    /* JADX INFO: renamed from: a */
    public final String m1072a(String str) {
        return this.f1417a.getProperty(str);
    }

    /* JADX INFO: renamed from: a */
    public final String m1073a(String str, String str2) {
        return this.f1417a.getProperty(str, str2);
    }

    /* JADX INFO: renamed from: b */
    private boolean m1065b() {
        return this.f1417a.isEmpty();
    }

    /* JADX INFO: renamed from: c */
    private Enumeration<Object> m1067c() {
        return this.f1417a.keys();
    }

    /* JADX INFO: renamed from: d */
    private Set<Object> m1068d() {
        return this.f1417a.keySet();
    }

    /* JADX INFO: renamed from: e */
    private int m1069e() {
        return this.f1417a.size();
    }

    /* JADX INFO: renamed from: f */
    private Collection<Object> m1070f() {
        return this.f1417a.values();
    }

    /* JADX INFO: renamed from: g */
    private static C0805b m1071g() {
        return new C0805b();
    }
}

package com.umeng.commonsdk.proguard;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.am */
/* JADX INFO: compiled from: FieldMetaData.java */
/* JADX INFO: loaded from: classes.dex */
public class C1749am implements Serializable {

    /* JADX INFO: renamed from: d */
    private static Map<Class<? extends InterfaceC1737aa>, Map<? extends InterfaceC1744ah, C1749am>> f4021d = new HashMap();

    /* JADX INFO: renamed from: a */
    public final String f4022a;

    /* JADX INFO: renamed from: b */
    public final byte f4023b;

    /* JADX INFO: renamed from: c */
    public final C1750an f4024c;

    public C1749am(String str, byte b, C1750an c1750an) {
        this.f4022a = str;
        this.f4023b = b;
        this.f4024c = c1750an;
    }

    /* JADX INFO: renamed from: a */
    public static void m3842a(Class<? extends InterfaceC1737aa> cls, Map<? extends InterfaceC1744ah, C1749am> map) {
        f4021d.put(cls, map);
    }

    /* JADX INFO: renamed from: a */
    public static Map<? extends InterfaceC1744ah, C1749am> m3841a(Class<? extends InterfaceC1737aa> cls) {
        if (!f4021d.containsKey(cls)) {
            try {
                cls.newInstance();
            } catch (IllegalAccessException e) {
                throw new RuntimeException("IllegalAccessException for TBase class: " + cls.getName() + ", message: " + e.getMessage());
            } catch (InstantiationException e2) {
                throw new RuntimeException("InstantiationException for TBase class: " + cls.getName() + ", message: " + e2.getMessage());
            }
        }
        return f4021d.get(cls);
    }
}

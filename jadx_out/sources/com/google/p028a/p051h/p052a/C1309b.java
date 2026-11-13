package com.google.p028a.p051h.p052a;

import com.google.p028a.p051h.C1304a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.h.a.b */
/* JADX INFO: compiled from: BarcodeValue.java */
/* JADX INFO: loaded from: classes.dex */
final class C1309b {

    /* JADX INFO: renamed from: a */
    final Map<Integer, Integer> f2565a = new HashMap();

    C1309b() {
    }

    /* JADX INFO: renamed from: a */
    final void m2253a(int i) {
        Integer num = this.f2565a.get(Integer.valueOf(i));
        if (num == null) {
            num = 0;
        }
        this.f2565a.put(Integer.valueOf(i), Integer.valueOf(num.intValue() + 1));
    }

    /* JADX INFO: renamed from: a */
    final int[] m2254a() {
        ArrayList arrayList = new ArrayList();
        int iIntValue = -1;
        for (Map.Entry<Integer, Integer> entry : this.f2565a.entrySet()) {
            if (entry.getValue().intValue() > iIntValue) {
                iIntValue = entry.getValue().intValue();
                arrayList.clear();
                arrayList.add(entry.getKey());
            } else if (entry.getValue().intValue() == iIntValue) {
                arrayList.add(entry.getKey());
            }
        }
        return C1304a.m2221a(arrayList);
    }

    /* JADX INFO: renamed from: b */
    private Integer m2252b(int i) {
        return this.f2565a.get(Integer.valueOf(i));
    }
}

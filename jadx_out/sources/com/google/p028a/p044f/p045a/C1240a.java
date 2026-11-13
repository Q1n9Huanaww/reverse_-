package com.google.p028a.p044f.p045a;

import com.google.p028a.AbstractC1362q;
import com.google.p028a.C1189c;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.EnumC1364s;
import com.google.p028a.p035c.C1202e;
import com.google.p028a.p035c.C1204g;
import com.google.p028a.p044f.InterfaceC1244c;
import com.google.p028a.p044f.p045a.p046a.C1241a;
import com.google.p028a.p056i.C1331a;
import com.google.p028a.p056i.p057a.C1340i;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.f.a.a */
/* JADX INFO: compiled from: QRCodeMultiReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1240a extends C1331a implements InterfaceC1244c {

    /* JADX INFO: renamed from: b */
    private static final C1363r[] f2312b = new C1363r[0];

    /* JADX INFO: renamed from: c */
    private static final C1365t[] f2313c = new C1365t[0];

    @Override // com.google.p028a.p044f.InterfaceC1244c
    /* JADX INFO: renamed from: a_ */
    public final C1363r[] mo2023a_(C1189c c1189c) throws C1358m {
        return mo2024b(c1189c);
    }

    @Override // com.google.p028a.p044f.InterfaceC1244c
    /* JADX INFO: renamed from: b */
    public final C1363r[] mo2024b(C1189c c1189c) throws C1358m {
        ArrayList arrayList = new ArrayList();
        for (C1204g c1204g : new C1241a(c1189c.m1741c()).m2026a()) {
            try {
                C1202e c1202eM2458a = this.f2712a.m2458a(c1204g.f2146d, (Map<EnumC1233e, ?>) null);
                C1365t[] c1365tArr = c1204g.f2147e;
                if (c1202eM2458a.f2143h instanceof C1340i) {
                    ((C1340i) c1202eM2458a.f2143h).m2470a(c1365tArr);
                }
                C1363r c1363r = new C1363r(c1202eM2458a.f2138c, c1202eM2458a.f2136a, c1365tArr, EnumC1138a.QR_CODE);
                List<byte[]> list = c1202eM2458a.f2139d;
                if (list != null) {
                    c1363r.m2624a(EnumC1364s.BYTE_SEGMENTS, list);
                }
                String str = c1202eM2458a.f2140e;
                if (str != null) {
                    c1363r.m2624a(EnumC1364s.ERROR_CORRECTION_LEVEL, str);
                }
                if (c1202eM2458a.m1843a()) {
                    c1363r.m2624a(EnumC1364s.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(c1202eM2458a.f2145j));
                    c1363r.m2624a(EnumC1364s.STRUCTURED_APPEND_PARITY, Integer.valueOf(c1202eM2458a.f2144i));
                }
                arrayList.add(c1363r);
            } catch (AbstractC1362q unused) {
            }
        }
        if (arrayList.isEmpty()) {
            return f2312b;
        }
        List<C1363r> listM2022a = m2022a(arrayList);
        return (C1363r[]) listM2022a.toArray(new C1363r[listM2022a.size()]);
    }

    /* JADX INFO: renamed from: a */
    private static List<C1363r> m2022a(List<C1363r> list) {
        byte b;
        boolean z;
        Iterator<C1363r> it2 = list.iterator();
        while (true) {
            b = 0;
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            if (it2.next().f2844f.containsKey(EnumC1364s.STRUCTURED_APPEND_SEQUENCE)) {
                z = true;
                break;
            }
        }
        if (!z) {
            return list;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList<C1363r> arrayList2 = new ArrayList();
        for (C1363r c1363r : list) {
            arrayList.add(c1363r);
            if (c1363r.f2844f.containsKey(EnumC1364s.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(c1363r);
            }
        }
        Collections.sort(arrayList2, new a(b));
        StringBuilder sb = new StringBuilder();
        int length = 0;
        int length2 = 0;
        for (C1363r c1363r2 : arrayList2) {
            sb.append(c1363r2.f2839a);
            length += c1363r2.f2840b.length;
            if (c1363r2.f2844f.containsKey(EnumC1364s.BYTE_SEGMENTS)) {
                Iterator it3 = ((Iterable) c1363r2.f2844f.get(EnumC1364s.BYTE_SEGMENTS)).iterator();
                while (it3.hasNext()) {
                    length2 += ((byte[]) it3.next()).length;
                }
            }
        }
        byte[] bArr = new byte[length];
        byte[] bArr2 = new byte[length2];
        int length3 = 0;
        int length4 = 0;
        for (C1363r c1363r3 : arrayList2) {
            System.arraycopy(c1363r3.f2840b, 0, bArr, length3, c1363r3.f2840b.length);
            length3 += c1363r3.f2840b.length;
            if (c1363r3.f2844f.containsKey(EnumC1364s.BYTE_SEGMENTS)) {
                for (byte[] bArr3 : (Iterable) c1363r3.f2844f.get(EnumC1364s.BYTE_SEGMENTS)) {
                    System.arraycopy(bArr3, 0, bArr2, length4, bArr3.length);
                    length4 += bArr3.length;
                }
            }
        }
        C1363r c1363r4 = new C1363r(sb.toString(), bArr, f2313c, EnumC1138a.QR_CODE);
        if (length2 > 0) {
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(bArr2);
            c1363r4.m2624a(EnumC1364s.BYTE_SEGMENTS, arrayList3);
        }
        arrayList.add(c1363r4);
        return arrayList;
    }

    /* JADX INFO: renamed from: com.google.a.f.a.a$a */
    /* JADX INFO: compiled from: QRCodeMultiReader.java */
    private static final class a implements Serializable, Comparator<C1363r> {
        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // java.util.Comparator
        public final /* synthetic */ int compare(C1363r c1363r, C1363r c1363r2) {
            return Integer.compare(((Integer) c1363r.f2844f.get(EnumC1364s.STRUCTURED_APPEND_SEQUENCE)).intValue(), ((Integer) c1363r2.f2844f.get(EnumC1364s.STRUCTURED_APPEND_SEQUENCE)).intValue());
        }

        /* JADX INFO: renamed from: a */
        private static int m2025a(C1363r c1363r, C1363r c1363r2) {
            return Integer.compare(((Integer) c1363r.f2844f.get(EnumC1364s.STRUCTURED_APPEND_SEQUENCE)).intValue(), ((Integer) c1363r2.f2844f.get(EnumC1364s.STRUCTURED_APPEND_SEQUENCE)).intValue());
        }
    }
}

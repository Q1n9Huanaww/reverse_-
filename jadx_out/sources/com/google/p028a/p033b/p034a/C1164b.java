package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.ArrayList;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.b.a.b */
/* JADX INFO: compiled from: AddressBookAUResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1164b extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1166d m1621c(C1363r c1363r) {
        String strB = m1715b(c1363r);
        if (!strB.contains("MEMORY") || !strB.contains(IOUtils.LINE_SEPARATOR_WINDOWS)) {
            return null;
        }
        String strB2 = m1716b("NAME1:", strB, '\r', true);
        String strB3 = m1716b("NAME2:", strB, '\r', true);
        String[] strArrM1620a = m1620a("TEL", strB);
        String[] strArrM1620a2 = m1620a("MAIL", strB);
        String strB4 = m1716b("MEMORY:", strB, '\r', false);
        String strB5 = m1716b("ADD:", strB, '\r', true);
        return new C1166d(m1717b(strB2), null, strB3, strArrM1620a, null, strArrM1620a2, null, null, strB4, strB5 != null ? new String[]{strB5} : null, null, null, null, null, null, null);
    }

    /* JADX INFO: renamed from: a */
    private static String[] m1620a(String str, String str2) {
        ArrayList arrayList = null;
        for (int i = 1; i <= 3; i++) {
            String strB = m1716b(str + i + ':', str2, '\r', true);
            if (strB == null) {
                break;
            }
            if (arrayList == null) {
                arrayList = new ArrayList(3);
            }
            arrayList.add(strB);
        }
        if (arrayList == null) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String strB = m1715b(c1363r);
        if (!strB.contains("MEMORY") || !strB.contains(IOUtils.LINE_SEPARATOR_WINDOWS)) {
            return null;
        }
        String strB2 = m1716b("NAME1:", strB, '\r', true);
        String strB3 = m1716b("NAME2:", strB, '\r', true);
        String[] strArrM1620a = m1620a("TEL", strB);
        String[] strArrM1620a2 = m1620a("MAIL", strB);
        String strB4 = m1716b("MEMORY:", strB, '\r', false);
        String strB5 = m1716b("ADD:", strB, '\r', true);
        return new C1166d(m1717b(strB2), null, strB3, strArrM1620a, null, strArrM1620a2, null, null, strB4, strB5 != null ? new String[]{strB5} : null, null, null, null, null, null, null);
    }
}

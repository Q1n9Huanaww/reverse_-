package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.b.a.v */
/* JADX INFO: compiled from: SMSMMSResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1184v extends AbstractC1183u {
    /* JADX INFO: renamed from: c */
    private static C1185w m1722c(C1363r c1363r) {
        String str;
        String strSubstring;
        String strB = m1715b(c1363r);
        String str2 = null;
        if (!strB.startsWith("sms:") && !strB.startsWith("SMS:") && !strB.startsWith("mms:") && !strB.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> mapC = m1719c(strB);
        boolean z = false;
        if (mapC == null || mapC.isEmpty()) {
            str = null;
        } else {
            str2 = mapC.get("subject");
            str = mapC.get("body");
            z = true;
        }
        int iIndexOf = strB.indexOf(63, 4);
        if (iIndexOf < 0 || !z) {
            strSubstring = strB.substring(4);
        } else {
            strSubstring = strB.substring(4, iIndexOf);
        }
        int i = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i2 = i + 1;
            int iIndexOf2 = strSubstring.indexOf(44, i2);
            if (iIndexOf2 > i) {
                m1721a(arrayList, arrayList2, strSubstring.substring(i2, iIndexOf2));
                i = iIndexOf2;
            } else {
                m1721a(arrayList, arrayList2, strSubstring.substring(i2));
                return new C1185w((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1721a(Collection<String> collection, Collection<String> collection2, String str) {
        int iIndexOf = str.indexOf(59);
        if (iIndexOf < 0) {
            collection.add(str);
            collection2.add(null);
        } else {
            collection.add(str.substring(0, iIndexOf));
            String strSubstring = str.substring(iIndexOf + 1);
            collection2.add(strSubstring.startsWith("via=") ? strSubstring.substring(4) : null);
        }
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        String str;
        String strSubstring;
        String strB = m1715b(c1363r);
        String str2 = null;
        if (!strB.startsWith("sms:") && !strB.startsWith("SMS:") && !strB.startsWith("mms:") && !strB.startsWith("MMS:")) {
            return null;
        }
        Map<String, String> mapC = m1719c(strB);
        boolean z = false;
        if (mapC == null || mapC.isEmpty()) {
            str = null;
        } else {
            str2 = mapC.get("subject");
            str = mapC.get("body");
            z = true;
        }
        int iIndexOf = strB.indexOf(63, 4);
        if (iIndexOf < 0 || !z) {
            strSubstring = strB.substring(4);
        } else {
            strSubstring = strB.substring(4, iIndexOf);
        }
        int i = -1;
        ArrayList arrayList = new ArrayList(1);
        ArrayList arrayList2 = new ArrayList(1);
        while (true) {
            int i2 = i + 1;
            int iIndexOf2 = strSubstring.indexOf(44, i2);
            if (iIndexOf2 > i) {
                m1721a(arrayList, arrayList2, strSubstring.substring(i2, iIndexOf2));
                i = iIndexOf2;
            } else {
                m1721a(arrayList, arrayList2, strSubstring.substring(i2));
                return new C1185w((String[]) arrayList.toArray(new String[arrayList.size()]), (String[]) arrayList2.toArray(new String[arrayList2.size()]), str2, str);
            }
        }
    }
}

package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.i */
/* JADX INFO: compiled from: EmailAddressResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1171i extends AbstractC1183u {

    /* JADX INFO: renamed from: a */
    private static final Pattern f2009a = Pattern.compile(",");

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        return m1668c(c1363r);
    }

    /* JADX INFO: renamed from: c */
    private static C1170h m1668c(C1363r c1363r) {
        String[] strArr;
        String[] strArr2;
        String[] strArr3;
        String str;
        String str2;
        String str3;
        String strB = m1715b(c1363r);
        if (strB.startsWith("mailto:") || strB.startsWith("MAILTO:")) {
            String strSubstring = strB.substring(7);
            int iIndexOf = strSubstring.indexOf(63);
            if (iIndexOf >= 0) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
            try {
                String strD = m1720d(strSubstring);
                String[] strArrSplit = !strD.isEmpty() ? f2009a.split(strD) : null;
                Map<String, String> mapC = m1719c(strB);
                if (mapC != null) {
                    if (strArrSplit == null && (str3 = mapC.get("to")) != null) {
                        strArrSplit = f2009a.split(str3);
                    }
                    String str4 = mapC.get("cc");
                    String[] strArrSplit2 = str4 != null ? f2009a.split(str4) : null;
                    String str5 = mapC.get("bcc");
                    String[] strArrSplit3 = str5 != null ? f2009a.split(str5) : null;
                    String str6 = mapC.get("subject");
                    str2 = mapC.get("body");
                    strArr = strArrSplit;
                    strArr3 = strArrSplit3;
                    strArr2 = strArrSplit2;
                    str = str6;
                } else {
                    strArr = strArrSplit;
                    strArr2 = null;
                    strArr3 = null;
                    str = null;
                    str2 = null;
                }
                return new C1170h(strArr, strArr2, strArr3, str, str2);
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }
        if (C1172j.m1669a(strB)) {
            return new C1170h(strB);
        }
        return null;
    }
}

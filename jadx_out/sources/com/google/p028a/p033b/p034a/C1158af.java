package com.google.p028a.p033b.p034a;

import com.didi.virtualapk.delegate.RemoteContentProvider;
import com.google.p028a.C1363r;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.af */
/* JADX INFO: compiled from: VCardResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1158af extends AbstractC1183u {

    /* JADX INFO: renamed from: a */
    private static final Pattern f1945a = Pattern.compile("BEGIN:VCARD", 2);

    /* JADX INFO: renamed from: b */
    private static final Pattern f1946b = Pattern.compile("\\d{4}-?\\d{2}-?\\d{2}");

    /* JADX INFO: renamed from: c */
    private static final Pattern f1947c = Pattern.compile("\r\n[ \t]");

    /* JADX INFO: renamed from: d */
    private static final Pattern f1948d = Pattern.compile("\\\\[nN]");

    /* JADX INFO: renamed from: e */
    private static final Pattern f1949e = Pattern.compile("\\\\([,;\\\\])");

    /* JADX INFO: renamed from: f */
    private static final Pattern f1950f = Pattern.compile("=");

    /* JADX INFO: renamed from: g */
    private static final Pattern f1951g = Pattern.compile(";");

    /* JADX INFO: renamed from: h */
    private static final Pattern f1952h = Pattern.compile("(?<!\\\\);+");

    /* JADX INFO: renamed from: i */
    private static final Pattern f1953i = Pattern.compile(",");

    /* JADX INFO: renamed from: j */
    private static final Pattern f1954j = Pattern.compile("[;,]");

    /* JADX INFO: renamed from: c */
    private static C1166d m1590c(C1363r c1363r) {
        int iIndexOf;
        String strB = m1715b(c1363r);
        Matcher matcher = f1945a.matcher(strB);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> listM1582a = m1582a((CharSequence) "FN", strB, true, false);
        if (listM1582a == null && (listM1582a = m1582a((CharSequence) "N", strB, true, false)) != null) {
            Iterator<T> it2 = listM1582a.iterator();
            while (it2.hasNext()) {
                List list = (List) it2.next();
                String str = (String) list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (iIndexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, iIndexOf);
                    i++;
                    i2 = iIndexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                m1585a(strArr, 3, sb);
                m1585a(strArr, 1, sb);
                m1585a(strArr, 2, sb);
                m1585a(strArr, 0, sb);
                m1585a(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
        List<String> listM1588b = m1588b((CharSequence) "NICKNAME", strB, true, false);
        String[] strArrSplit = listM1588b == null ? null : f1953i.split(listM1588b.get(0));
        List<List<String>> listM1582a2 = m1582a((CharSequence) "TEL", strB, true, false);
        List<List<String>> listM1582a3 = m1582a((CharSequence) "EMAIL", strB, true, false);
        List<String> listM1588b2 = m1588b((CharSequence) "NOTE", strB, false, false);
        List<List<String>> listM1582a4 = m1582a((CharSequence) "ADR", strB, true, true);
        List<String> listM1588b3 = m1588b((CharSequence) "ORG", strB, true, true);
        List<String> listM1588b4 = m1588b((CharSequence) "BDAY", strB, true, false);
        if (listM1588b4 != null) {
            String str2 = listM1588b4.get(0);
            if (!(str2 == null || f1946b.matcher(str2).matches())) {
                listM1588b4 = null;
            }
        }
        List<String> listM1588b5 = m1588b((CharSequence) "TITLE", strB, true, false);
        List<List<String>> listM1582a5 = m1582a((CharSequence) "URL", strB, true, false);
        List<String> listM1588b6 = m1588b((CharSequence) "IMPP", strB, true, false);
        List<String> listM1588b7 = m1588b((CharSequence) "GEO", strB, true, false);
        String[] strArrSplit2 = listM1588b7 == null ? null : f1954j.split(listM1588b7.get(0));
        return new C1166d(m1587a((Collection<List<String>>) listM1582a), strArrSplit, null, m1587a((Collection<List<String>>) listM1582a2), m1589b(listM1582a2), m1587a((Collection<List<String>>) listM1582a3), m1589b(listM1582a3), m1581a(listM1588b6), m1581a(listM1588b2), m1587a((Collection<List<String>>) listM1582a4), m1589b(listM1582a4), m1581a(listM1588b3), m1581a(listM1588b4), m1581a(listM1588b5), m1587a((Collection<List<String>>) listM1582a5), (strArrSplit2 == null || strArrSplit2.length == 2) ? strArrSplit2 : null);
    }

    /* JADX INFO: renamed from: a */
    static List<List<String>> m1582a(CharSequence charSequence, String str, boolean z, boolean z2) {
        String str2;
        ArrayList arrayList;
        boolean z3;
        String str3;
        int iIndexOf;
        int i;
        String strReplaceAll;
        String schemeSpecificPart;
        int length = str.length();
        int i2 = 0;
        int i3 = 0;
        ArrayList arrayList2 = null;
        while (i3 < length) {
            int i4 = 2;
            Matcher matcher = Pattern.compile("(?:^|\n)" + ((Object) charSequence) + "(?:;([^:]*))?:", 2).matcher(str);
            if (i3 > 0) {
                i3--;
            }
            if (!matcher.find(i3)) {
                break;
            }
            int iEnd = matcher.end(i2);
            String strGroup = matcher.group(1);
            if (strGroup != null) {
                String[] strArrSplit = f1951g.split(strGroup);
                int length2 = strArrSplit.length;
                int i5 = 0;
                arrayList = null;
                z3 = false;
                str3 = null;
                String str4 = null;
                while (i5 < length2) {
                    String str5 = strArrSplit[i5];
                    if (arrayList == null) {
                        arrayList = new ArrayList(1);
                    }
                    arrayList.add(str5);
                    String[] strArrSplit2 = f1950f.split(str5, i4);
                    if (strArrSplit2.length > 1) {
                        String str6 = strArrSplit2[0];
                        String str7 = strArrSplit2[1];
                        if ("ENCODING".equalsIgnoreCase(str6) && "QUOTED-PRINTABLE".equalsIgnoreCase(str7)) {
                            z3 = true;
                        } else if ("CHARSET".equalsIgnoreCase(str6)) {
                            str3 = str7;
                        } else if ("VALUE".equalsIgnoreCase(str6)) {
                            str4 = str7;
                        }
                    }
                    i5++;
                    i4 = 2;
                }
                str2 = str4;
            } else {
                str2 = null;
                arrayList = null;
                z3 = false;
                str3 = null;
            }
            int i6 = iEnd;
            while (true) {
                iIndexOf = str.indexOf(10, i6);
                if (iIndexOf < 0) {
                    break;
                }
                if (iIndexOf < str.length() - 1) {
                    int i7 = iIndexOf + 1;
                    if (str.charAt(i7) == ' ' || str.charAt(i7) == '\t') {
                        i6 = iIndexOf + 2;
                    }
                }
                if (!z3 || ((iIndexOf <= 0 || str.charAt(iIndexOf - 1) != '=') && (iIndexOf < 2 || str.charAt(iIndexOf - 2) != '='))) {
                    break;
                }
                i6 = iIndexOf + 1;
            }
            if (iIndexOf < 0) {
                i3 = length;
            } else {
                if (iIndexOf > iEnd) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList(1);
                    }
                    if (iIndexOf > 0 && str.charAt(iIndexOf - 1) == '\r') {
                        iIndexOf--;
                    }
                    String strSubstring = str.substring(iEnd, iIndexOf);
                    if (z) {
                        strSubstring = strSubstring.trim();
                    }
                    if (z3) {
                        strReplaceAll = m1580a(strSubstring, str3);
                        if (z2) {
                            strReplaceAll = f1952h.matcher(strReplaceAll).replaceAll("\n").trim();
                        }
                    } else {
                        if (z2) {
                            strSubstring = f1952h.matcher(strSubstring).replaceAll("\n").trim();
                        }
                        strReplaceAll = f1949e.matcher(f1948d.matcher(f1947c.matcher(strSubstring).replaceAll("")).replaceAll("\n")).replaceAll("$1");
                    }
                    if (RemoteContentProvider.KEY_URI.equals(str2)) {
                        try {
                            schemeSpecificPart = URI.create(strReplaceAll).getSchemeSpecificPart();
                        } catch (IllegalArgumentException unused) {
                            schemeSpecificPart = strReplaceAll;
                        }
                    } else {
                        schemeSpecificPart = strReplaceAll;
                    }
                    if (arrayList == null) {
                        ArrayList arrayList3 = new ArrayList(1);
                        arrayList3.add(schemeSpecificPart);
                        arrayList2.add(arrayList3);
                    } else {
                        arrayList.add(0, schemeSpecificPart);
                        arrayList2.add(arrayList);
                    }
                    i = iIndexOf + 1;
                } else {
                    i = iIndexOf + 1;
                }
                i3 = i;
            }
            i2 = 0;
        }
        return arrayList2;
    }

    /* JADX INFO: renamed from: a */
    private static String m1580a(CharSequence charSequence, String str) {
        char cCharAt;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < length) {
            char cCharAt2 = charSequence.charAt(i);
            if (cCharAt2 != '\n' && cCharAt2 != '\r') {
                if (cCharAt2 != '=') {
                    m1583a(byteArrayOutputStream, str, sb);
                    sb.append(cCharAt2);
                } else if (i < length - 2 && (cCharAt = charSequence.charAt(i + 1)) != '\r' && cCharAt != '\n') {
                    i += 2;
                    char cCharAt3 = charSequence.charAt(i);
                    int iA = m1706a(cCharAt);
                    int iA2 = m1706a(cCharAt3);
                    if (iA >= 0 && iA2 >= 0) {
                        byteArrayOutputStream.write((iA << 4) + iA2);
                    }
                }
            }
            i++;
        }
        m1583a(byteArrayOutputStream, str, sb);
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static void m1583a(ByteArrayOutputStream byteArrayOutputStream, String str, StringBuilder sb) {
        String str2;
        if (byteArrayOutputStream.size() > 0) {
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            if (str == null) {
                str2 = new String(byteArray, StandardCharsets.UTF_8);
            } else {
                try {
                    str2 = new String(byteArray, str);
                } catch (UnsupportedEncodingException unused) {
                    str2 = new String(byteArray, StandardCharsets.UTF_8);
                }
            }
            byteArrayOutputStream.reset();
            sb.append(str2);
        }
    }

    /* JADX INFO: renamed from: b */
    static List<String> m1588b(CharSequence charSequence, String str, boolean z, boolean z2) {
        List<List<String>> listM1582a = m1582a(charSequence, str, z, z2);
        if (listM1582a == null || listM1582a.isEmpty()) {
            return null;
        }
        return listM1582a.get(0);
    }

    /* JADX INFO: renamed from: a */
    private static String m1581a(List<String> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    /* JADX INFO: renamed from: a */
    private static String[] m1587a(Collection<List<String>> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<List<String>> it2 = collection.iterator();
        while (it2.hasNext()) {
            String str = it2.next().get(0);
            if (str != null && !str.isEmpty()) {
                arrayList.add(str);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX INFO: renamed from: b */
    private static String[] m1589b(Collection<List<String>> collection) {
        String strSubstring;
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList(collection.size());
        for (List<String> list : collection) {
            String str = list.get(0);
            if (str != null && !str.isEmpty()) {
                int i = 1;
                while (true) {
                    if (i >= list.size()) {
                        strSubstring = null;
                        break;
                    }
                    String str2 = list.get(i);
                    int iIndexOf = str2.indexOf(61);
                    if (iIndexOf < 0) {
                        strSubstring = str2;
                        break;
                    }
                    if ("TYPE".equalsIgnoreCase(str2.substring(0, iIndexOf))) {
                        strSubstring = str2.substring(iIndexOf + 1);
                        break;
                    }
                    i++;
                }
                arrayList.add(strSubstring);
            }
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1586a(CharSequence charSequence) {
        return charSequence == null || f1946b.matcher(charSequence).matches();
    }

    /* JADX INFO: renamed from: a */
    private static void m1584a(Iterable<List<String>> iterable) {
        int iIndexOf;
        if (iterable != null) {
            for (List<String> list : iterable) {
                String str = list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (iIndexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, iIndexOf);
                    i++;
                    i2 = iIndexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                m1585a(strArr, 3, sb);
                m1585a(strArr, 1, sb);
                m1585a(strArr, 2, sb);
                m1585a(strArr, 0, sb);
                m1585a(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1585a(String[] strArr, int i, StringBuilder sb) {
        if (strArr[i] == null || strArr[i].isEmpty()) {
            return;
        }
        if (sb.length() > 0) {
            sb.append(' ');
        }
        sb.append(strArr[i]);
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        int iIndexOf;
        String strB = m1715b(c1363r);
        Matcher matcher = f1945a.matcher(strB);
        if (!matcher.find() || matcher.start() != 0) {
            return null;
        }
        List<List<String>> listM1582a = m1582a((CharSequence) "FN", strB, true, false);
        if (listM1582a == null && (listM1582a = m1582a((CharSequence) "N", strB, true, false)) != null) {
            Iterator<T> it2 = listM1582a.iterator();
            while (it2.hasNext()) {
                List list = (List) it2.next();
                String str = (String) list.get(0);
                String[] strArr = new String[5];
                int i = 0;
                int i2 = 0;
                while (i < 4 && (iIndexOf = str.indexOf(59, i2)) >= 0) {
                    strArr[i] = str.substring(i2, iIndexOf);
                    i++;
                    i2 = iIndexOf + 1;
                }
                strArr[i] = str.substring(i2);
                StringBuilder sb = new StringBuilder(100);
                m1585a(strArr, 3, sb);
                m1585a(strArr, 1, sb);
                m1585a(strArr, 2, sb);
                m1585a(strArr, 0, sb);
                m1585a(strArr, 4, sb);
                list.set(0, sb.toString().trim());
            }
        }
        List<String> listM1588b = m1588b((CharSequence) "NICKNAME", strB, true, false);
        String[] strArrSplit = listM1588b == null ? null : f1953i.split(listM1588b.get(0));
        List<List<String>> listM1582a2 = m1582a((CharSequence) "TEL", strB, true, false);
        List<List<String>> listM1582a3 = m1582a((CharSequence) "EMAIL", strB, true, false);
        List<String> listM1588b2 = m1588b((CharSequence) "NOTE", strB, false, false);
        List<List<String>> listM1582a4 = m1582a((CharSequence) "ADR", strB, true, true);
        List<String> listM1588b3 = m1588b((CharSequence) "ORG", strB, true, true);
        List<String> listM1588b4 = m1588b((CharSequence) "BDAY", strB, true, false);
        if (listM1588b4 != null) {
            String str2 = listM1588b4.get(0);
            if (!(str2 == null || f1946b.matcher(str2).matches())) {
                listM1588b4 = null;
            }
        }
        List<String> listM1588b5 = m1588b((CharSequence) "TITLE", strB, true, false);
        List<List<String>> listM1582a5 = m1582a((CharSequence) "URL", strB, true, false);
        List<String> listM1588b6 = m1588b((CharSequence) "IMPP", strB, true, false);
        List<String> listM1588b7 = m1588b((CharSequence) "GEO", strB, true, false);
        String[] strArrSplit2 = listM1588b7 == null ? null : f1954j.split(listM1588b7.get(0));
        return new C1166d(m1587a((Collection<List<String>>) listM1582a), strArrSplit, null, m1587a((Collection<List<String>>) listM1582a2), m1589b(listM1582a2), m1587a((Collection<List<String>>) listM1582a3), m1589b(listM1582a3), m1581a(listM1588b6), m1581a(listM1588b2), m1587a((Collection<List<String>>) listM1582a4), m1589b(listM1582a4), m1581a(listM1588b3), m1581a(listM1588b4), m1581a(listM1588b5), m1587a((Collection<List<String>>) listM1582a5), (strArrSplit2 == null || strArrSplit2.length == 2) ? strArrSplit2 : null);
    }
}

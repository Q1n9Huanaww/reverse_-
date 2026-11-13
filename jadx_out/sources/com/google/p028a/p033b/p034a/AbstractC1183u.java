package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.u */
/* JADX INFO: compiled from: ResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1183u {

    /* JADX INFO: renamed from: a */
    private static final AbstractC1183u[] f2050a = {new C1168f(), new C1165c(), new C1172j(), new C1164b(), new C1158af(), new C1167e(), new C1159ag(), new C1171i(), new C1187y(), new C1153aa(), new C1184v(), new C1186x(), new C1176n(), new C1163ak(), new C1157ae(), new C1156ad(), new C1178p(), new C1182t(), new C1174l(), new C1161ai()};

    /* JADX INFO: renamed from: b */
    private static final Pattern f2051b = Pattern.compile("\\d+");

    /* JADX INFO: renamed from: c */
    private static final Pattern f2052c = Pattern.compile("&");

    /* JADX INFO: renamed from: d */
    private static final Pattern f2053d = Pattern.compile("=");

    /* JADX INFO: renamed from: e */
    private static final String f2054e = "\ufeff";

    /* JADX INFO: renamed from: a */
    protected static int m1706a(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        }
        if (c >= 'a' && c <= 'f') {
            return (c - 'a') + 10;
        }
        if (c < 'A' || c > 'F') {
            return -1;
        }
        return (c - 'A') + 10;
    }

    /* JADX INFO: renamed from: a */
    public abstract AbstractC1179q mo1568a(C1363r c1363r);

    /* JADX INFO: renamed from: c */
    private static AbstractC1179q m1718c(C1363r c1363r) {
        for (AbstractC1183u abstractC1183u : f2050a) {
            AbstractC1179q abstractC1179qMo1568a = abstractC1183u.mo1568a(c1363r);
            if (abstractC1179qMo1568a != null) {
                return abstractC1179qMo1568a;
            }
        }
        return new C1154ab(c1363r.f2839a);
    }

    /* JADX INFO: renamed from: a */
    private static void m1709a(String str, StringBuilder sb) {
        if (str != null) {
            sb.append('\n');
            sb.append(str);
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1710a(String[] strArr, StringBuilder sb) {
        if (strArr != null) {
            for (String str : strArr) {
                sb.append('\n');
                sb.append(str);
            }
        }
    }

    /* JADX INFO: renamed from: b */
    protected static String[] m1717b(String str) {
        if (str == null) {
            return null;
        }
        return new String[]{str};
    }

    /* JADX INFO: renamed from: a */
    private static String m1707a(String str) {
        int iIndexOf = str.indexOf(92);
        if (iIndexOf < 0) {
            return str;
        }
        int length = str.length();
        StringBuilder sb = new StringBuilder(length - 1);
        sb.append(str.toCharArray(), 0, iIndexOf);
        boolean z = false;
        while (iIndexOf < length) {
            char cCharAt = str.charAt(iIndexOf);
            if (z || cCharAt != '\\') {
                sb.append(cCharAt);
                z = false;
            } else {
                z = true;
            }
            iIndexOf++;
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1711a(CharSequence charSequence, int i) {
        return charSequence != null && i > 0 && i == charSequence.length() && f2051b.matcher(charSequence).matches();
    }

    /* JADX INFO: renamed from: a */
    protected static boolean m1712a(CharSequence charSequence, int i, int i2) {
        int i3;
        return charSequence != null && i2 > 0 && charSequence.length() >= (i3 = i2 + i) && f2051b.matcher(charSequence.subSequence(i, i3)).matches();
    }

    /* JADX INFO: renamed from: c */
    static Map<String, String> m1719c(String str) {
        int iIndexOf = str.indexOf(63);
        if (iIndexOf < 0) {
            return null;
        }
        HashMap map = new HashMap(3);
        for (String str2 : f2052c.split(str.substring(iIndexOf + 1))) {
            String[] strArrSplit = f2053d.split(str2, 2);
            if (strArrSplit.length == 2) {
                try {
                    map.put(strArrSplit[0], m1720d(strArrSplit[1]));
                } catch (IllegalArgumentException unused) {
                }
            }
        }
        return map;
    }

    /* JADX INFO: renamed from: a */
    private static void m1708a(CharSequence charSequence, Map<String, String> map) {
        String[] strArrSplit = f2053d.split(charSequence, 2);
        if (strArrSplit.length == 2) {
            try {
                map.put(strArrSplit[0], m1720d(strArrSplit[1]));
            } catch (IllegalArgumentException unused) {
            }
        }
    }

    /* JADX INFO: renamed from: d */
    static String m1720d(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException(e);
        }
    }

    /* JADX INFO: renamed from: a */
    static String[] m1713a(String str, String str2, char c, boolean z) {
        int length = str2.length();
        ArrayList arrayList = null;
        int i = 0;
        while (i < length) {
            int iIndexOf = str2.indexOf(str, i);
            if (iIndexOf < 0) {
                break;
            }
            int length2 = iIndexOf + str.length();
            boolean z2 = true;
            ArrayList arrayList2 = arrayList;
            int length3 = length2;
            while (z2) {
                int iIndexOf2 = str2.indexOf(c, length3);
                if (iIndexOf2 < 0) {
                    length3 = str2.length();
                } else {
                    int i2 = 0;
                    for (int i3 = iIndexOf2 - 1; i3 >= 0 && str2.charAt(i3) == '\\'; i3--) {
                        i2++;
                    }
                    if (i2 % 2 != 0) {
                        length3 = iIndexOf2 + 1;
                    } else {
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList(3);
                        }
                        String strM1707a = m1707a(str2.substring(length2, iIndexOf2));
                        if (z) {
                            strM1707a = strM1707a.trim();
                        }
                        if (!strM1707a.isEmpty()) {
                            arrayList2.add(strM1707a);
                        }
                        length3 = iIndexOf2 + 1;
                    }
                }
                z2 = false;
            }
            i = length3;
            arrayList = arrayList2;
        }
        if (arrayList == null || arrayList.isEmpty()) {
            return null;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* JADX INFO: renamed from: b */
    private static int m1714b(CharSequence charSequence, int i) {
        int i2 = 0;
        for (int i3 = i - 1; i3 >= 0 && charSequence.charAt(i3) == '\\'; i3--) {
            i2++;
        }
        return i2;
    }

    /* JADX INFO: renamed from: b */
    static String m1716b(String str, String str2, char c, boolean z) {
        String[] strArrM1713a = m1713a(str, str2, c, z);
        if (strArrM1713a == null) {
            return null;
        }
        return strArrM1713a[0];
    }

    /* JADX INFO: renamed from: b */
    protected static String m1715b(C1363r c1363r) {
        String str = c1363r.f2839a;
        return str.startsWith(f2054e) ? str.substring(1) : str;
    }
}

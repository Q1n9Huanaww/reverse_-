package com.p004b.p005a;

import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p009c.C0507d;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.b.a.r */
/* JADX INFO: compiled from: Cookie.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0621r {

    /* JADX INFO: renamed from: a */
    private static final Pattern f768a = Pattern.compile("(\\d{2,4})[^\\d]*");

    /* JADX INFO: renamed from: b */
    private static final Pattern f769b = Pattern.compile("(?i)(jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec).*");

    /* JADX INFO: renamed from: c */
    private static final Pattern f770c = Pattern.compile("(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: d */
    private static final Pattern f771d = Pattern.compile("(\\d{1,2}):(\\d{1,2}):(\\d{1,2})[^\\d]*");

    /* JADX INFO: renamed from: e */
    private final String f772e;

    /* JADX INFO: renamed from: f */
    private final String f773f;

    /* JADX INFO: renamed from: g */
    private final long f774g;

    /* JADX INFO: renamed from: h */
    private final String f775h;

    /* JADX INFO: renamed from: i */
    private final String f776i;

    /* JADX INFO: renamed from: j */
    private final boolean f777j;

    /* JADX INFO: renamed from: k */
    private final boolean f778k;

    /* JADX INFO: renamed from: l */
    private final boolean f779l;

    /* JADX INFO: renamed from: m */
    private final boolean f780m;

    private C0621r(String str, String str2, long j, String str3, String str4, boolean z, boolean z2, boolean z3, boolean z4) {
        this.f772e = str;
        this.f773f = str2;
        this.f774g = j;
        this.f775h = str3;
        this.f776i = str4;
        this.f777j = z;
        this.f778k = z2;
        this.f780m = z3;
        this.f779l = z4;
    }

    /* JADX INFO: renamed from: a */
    public final String m488a() {
        return this.f772e;
    }

    /* JADX INFO: renamed from: b */
    public final String m489b() {
        return this.f773f;
    }

    /* JADX WARN: Code duplicated, block: B:123:0x025e A[PHI: r3
  0x025e: PHI (r3v13 long) = (r3v2 long), (r3v6 long) binds: [B:122:0x025c, B:133:0x0282] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:149:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:44:0x012e  */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r6v13 */
    /* JADX INFO: renamed from: a */
    private static C0621r m486a(long j, C0583ac c0583ac, String str) {
        long j2;
        boolean z;
        String str2;
        String strSubstring;
        int i;
        String str3 = str;
        int length = str.length();
        char c = ';';
        ?? r6 = 0;
        int iM105a = C0503c.m105a(str3, 0, length, ';');
        char c2 = '=';
        int iM105a2 = C0503c.m105a(str3, 0, iM105a, '=');
        String str4 = null;
        if (iM105a2 == iM105a) {
            return null;
        }
        String strM126c = C0503c.m126c(str3, 0, iM105a2);
        if (!strM126c.isEmpty()) {
            int i2 = -1;
            if (C0503c.m124b(strM126c) == -1) {
                String strM126c2 = C0503c.m126c(str3, iM105a2 + 1, iM105a);
                if (C0503c.m124b(strM126c2) != -1) {
                    return null;
                }
                int i3 = iM105a + 1;
                String str5 = null;
                long timeInMillis = 253402300799999L;
                long jM485a = -1;
                boolean z2 = false;
                boolean z3 = false;
                boolean z4 = true;
                boolean z5 = false;
                while (i3 < length) {
                    int iM105a3 = C0503c.m105a(str3, i3, length, c);
                    int iM105a4 = C0503c.m105a(str3, i3, iM105a3, c2);
                    String strM126c3 = C0503c.m126c(str3, i3, iM105a4);
                    String strM126c4 = iM105a4 < iM105a3 ? C0503c.m126c(str3, iM105a4 + 1, iM105a3) : "";
                    if (strM126c3.equalsIgnoreCase("expires")) {
                        try {
                            int length2 = strM126c4.length();
                            int iM484a = m484a(strM126c4, r6, length2, r6);
                            Matcher matcher = f771d.matcher(strM126c4);
                            int i4 = -1;
                            int iIndexOf = -1;
                            int i5 = -1;
                            int i6 = -1;
                            int i7 = -1;
                            while (iM484a < length2) {
                                int iM484a2 = m484a(strM126c4, iM484a + 1, length2, true);
                                matcher.region(iM484a, iM484a2);
                                if (i2 == -1 && matcher.usePattern(f771d).matches()) {
                                    int i8 = Integer.parseInt(matcher.group(1));
                                    i = length;
                                    i6 = Integer.parseInt(matcher.group(2));
                                    i7 = Integer.parseInt(matcher.group(3));
                                    i2 = i8;
                                } else {
                                    i5 = i5;
                                    if (i5 == -1 && matcher.usePattern(f770c).matches()) {
                                        i5 = Integer.parseInt(matcher.group(1));
                                        i = length;
                                    } else {
                                        i = length;
                                        int i9 = iIndexOf;
                                        int i10 = -1;
                                        if (i9 == -1) {
                                            if (matcher.usePattern(f769b).matches()) {
                                                i5 = i5;
                                                iIndexOf = f769b.pattern().indexOf(matcher.group(1).toLowerCase(Locale.US)) / 4;
                                            } else {
                                                i10 = -1;
                                                if (i4 != i10) {
                                                }
                                                iIndexOf = i9;
                                            }
                                        } else {
                                            if (i4 != i10 && matcher.usePattern(f768a).matches()) {
                                                i4 = Integer.parseInt(matcher.group(1));
                                            }
                                            iIndexOf = i9;
                                        }
                                    }
                                }
                                try {
                                    iM484a = m484a(strM126c4, iM484a2 + 1, length2, false);
                                    length = i;
                                } catch (IllegalArgumentException unused) {
                                    i3 = iM105a3 + 1;
                                    length = i;
                                    str3 = str;
                                    c = ';';
                                    r6 = 0;
                                    c2 = '=';
                                    i2 = -1;
                                }
                            }
                            i = length;
                            int i11 = iIndexOf;
                            int i12 = i5;
                            if (i4 >= 70 && i4 <= 99) {
                                i4 += 1900;
                            }
                            if (i4 >= 0 && i4 <= 69) {
                                i4 += ExToast.LENGTH_SHORT;
                            }
                            if (i4 < 1601) {
                                throw new IllegalArgumentException();
                            }
                            if (i11 == -1) {
                                throw new IllegalArgumentException();
                            }
                            if (i12 <= 0 || i12 > 31) {
                                throw new IllegalArgumentException();
                            }
                            if (i2 < 0 || i2 > 23) {
                                throw new IllegalArgumentException();
                            }
                            int i13 = i6;
                            if (i13 >= 0 && i13 <= 59) {
                                int i14 = i7;
                                if (i14 < 0 || i14 > 59) {
                                    throw new IllegalArgumentException();
                                }
                                try {
                                    GregorianCalendar gregorianCalendar = new GregorianCalendar(C0503c.f174f);
                                    gregorianCalendar.setLenient(false);
                                    gregorianCalendar.set(1, i4);
                                    gregorianCalendar.set(2, i11 - 1);
                                    gregorianCalendar.set(5, i12);
                                    gregorianCalendar.set(11, i2);
                                    gregorianCalendar.set(12, i13);
                                    gregorianCalendar.set(13, i14);
                                    gregorianCalendar.set(14, 0);
                                    timeInMillis = gregorianCalendar.getTimeInMillis();
                                    z5 = true;
                                } catch (NumberFormatException | IllegalArgumentException unused2) {
                                }
                            }
                            throw new IllegalArgumentException();
                        } catch (IllegalArgumentException unused3) {
                            i = length;
                        }
                    } else {
                        i = length;
                        if (strM126c3.equalsIgnoreCase("max-age")) {
                            jM485a = m485a(strM126c4);
                            z5 = true;
                        } else if (!strM126c3.equalsIgnoreCase("domain")) {
                            if (strM126c3.equalsIgnoreCase("path")) {
                                str5 = strM126c4;
                            } else if (strM126c3.equalsIgnoreCase("secure")) {
                                z2 = true;
                            } else if (strM126c3.equalsIgnoreCase("httponly")) {
                                z3 = true;
                            }
                        } else {
                            if (strM126c4.endsWith(".")) {
                                throw new IllegalArgumentException();
                            }
                            if (strM126c4.startsWith(".")) {
                                strM126c4 = strM126c4.substring(1);
                            }
                            String strM109a = C0503c.m109a(strM126c4);
                            if (strM109a == null) {
                                throw new IllegalArgumentException();
                            }
                            str4 = strM109a;
                            z4 = false;
                        }
                    }
                    i3 = iM105a3 + 1;
                    length = i;
                    str3 = str;
                    c = ';';
                    r6 = 0;
                    c2 = '=';
                    i2 = -1;
                }
                long j3 = Long.MIN_VALUE;
                if (jM485a == Long.MIN_VALUE) {
                    j2 = j3;
                } else if (jM485a != -1) {
                    j3 = j + (jM485a <= 9223372036854775L ? jM485a * 1000 : Long.MAX_VALUE);
                    if (j3 < j || j3 > 253402300799999L) {
                        j2 = 253402300799999L;
                    } else {
                        j2 = j3;
                    }
                } else {
                    j2 = timeInMillis;
                }
                if (str4 == null) {
                    str2 = c0583ac.f453b;
                } else {
                    String str6 = c0583ac.f453b;
                    if (str6.equals(str4)) {
                        z = true;
                    } else if (str6.endsWith(str4)) {
                        z = true;
                        if (str6.charAt((str6.length() - str4.length()) - 1) != '.' || C0503c.m127c(str6)) {
                            z = false;
                        }
                    } else {
                        z = false;
                    }
                    if (!z) {
                        return null;
                    }
                    str2 = str4;
                }
                String str7 = str5;
                if (str7 == null || !str7.startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR)) {
                    String strM353h = c0583ac.m353h();
                    int iLastIndexOf = strM353h.lastIndexOf(47);
                    strSubstring = iLastIndexOf != 0 ? strM353h.substring(0, iLastIndexOf) : InternalZipConstants.ZIP_FILE_SEPARATOR;
                } else {
                    strSubstring = str7;
                }
                return new C0621r(strM126c, strM126c2, j2, str2, strSubstring, z2, z3, z4, z5);
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: a */
    private static int m484a(String str, int i, int i2, boolean z) {
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || (cCharAt >= '0' && cCharAt <= '9') || ((cCharAt >= 'a' && cCharAt <= 'z') || ((cCharAt >= 'A' && cCharAt <= 'Z') || cCharAt == ':'))) == (!z)) {
                return i;
            }
            i++;
        }
        return i2;
    }

    /* JADX INFO: renamed from: a */
    private static long m485a(String str) {
        try {
            long j = Long.parseLong(str);
            if (j <= 0) {
                return Long.MIN_VALUE;
            }
            return j;
        } catch (NumberFormatException e) {
            if (str.matches("-?\\d+")) {
                return str.startsWith("-") ? Long.MIN_VALUE : Long.MAX_VALUE;
            }
            throw e;
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0621r)) {
            return false;
        }
        C0621r c0621r = (C0621r) obj;
        return c0621r.f772e.equals(this.f772e) && c0621r.f773f.equals(this.f773f) && c0621r.f775h.equals(this.f775h) && c0621r.f776i.equals(this.f776i) && c0621r.f774g == this.f774g && c0621r.f777j == this.f777j && c0621r.f778k == this.f778k && c0621r.f779l == this.f779l && c0621r.f780m == this.f780m;
    }

    public final int hashCode() {
        return ((((((((((((((((this.f772e.hashCode() + 527) * 31) + this.f773f.hashCode()) * 31) + this.f775h.hashCode()) * 31) + this.f776i.hashCode()) * 31) + ((int) (this.f774g ^ (this.f774g >>> 32)))) * 31) + (!this.f777j ? 1 : 0)) * 31) + (!this.f778k ? 1 : 0)) * 31) + (!this.f779l ? 1 : 0)) * 31) + (!this.f780m ? 1 : 0);
    }

    /* JADX INFO: renamed from: a */
    public static List<C0621r> m487a(C0583ac c0583ac, C0581aa c0581aa) {
        List listEmptyList;
        int iM321a = c0581aa.m321a();
        ArrayList arrayList = null;
        ArrayList arrayList2 = null;
        for (int i = 0; i < iM321a; i++) {
            if ("Set-Cookie".equalsIgnoreCase(c0581aa.m322a(i))) {
                if (arrayList2 == null) {
                    arrayList2 = new ArrayList(2);
                }
                arrayList2.add(c0581aa.m325b(i));
            }
        }
        if (arrayList2 != null) {
            listEmptyList = Collections.unmodifiableList(arrayList2);
        } else {
            listEmptyList = Collections.emptyList();
        }
        int size = listEmptyList.size();
        for (int i2 = 0; i2 < size; i2++) {
            C0621r c0621rM486a = m486a(System.currentTimeMillis(), c0583ac, (String) listEmptyList.get(i2));
            if (c0621rM486a != null) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(c0621rM486a);
            }
        }
        if (arrayList != null) {
            return Collections.unmodifiableList(arrayList);
        }
        return Collections.emptyList();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.f772e);
        sb.append('=');
        sb.append(this.f773f);
        if (this.f779l) {
            if (this.f774g == Long.MIN_VALUE) {
                sb.append("; max-age=0");
            } else {
                sb.append("; expires=");
                sb.append(C0507d.m134a(new Date(this.f774g)));
            }
        }
        if (!this.f780m) {
            sb.append("; domain=");
            sb.append(this.f775h);
        }
        sb.append("; path=");
        sb.append(this.f776i);
        if (this.f777j) {
            sb.append("; secure");
        }
        if (this.f778k) {
            sb.append("; httponly");
        }
        return sb.toString();
    }
}

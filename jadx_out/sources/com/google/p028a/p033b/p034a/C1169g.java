package com.google.p028a.p033b.p034a;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.g */
/* JADX INFO: compiled from: CalendarParsedResult.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1169g extends AbstractC1179q {

    /* JADX INFO: renamed from: a */
    private static final Pattern f1990a = Pattern.compile("P(?:(\\d+)W)?(?:(\\d+)D)?(?:T(?:(\\d+)H)?(?:(\\d+)M)?(?:(\\d+)S)?)?");

    /* JADX INFO: renamed from: b */
    private static final long[] f1991b = {604800000, 86400000, 3600000, 60000, 1000};

    /* JADX INFO: renamed from: c */
    private static final Pattern f1992c = Pattern.compile("[0-9]{8}(T[0-9]{6}Z?)?");

    /* JADX INFO: renamed from: d */
    private final String f1993d;

    /* JADX INFO: renamed from: e */
    private final long f1994e;

    /* JADX INFO: renamed from: f */
    private final boolean f1995f;

    /* JADX INFO: renamed from: g */
    private final long f1996g;

    /* JADX INFO: renamed from: h */
    private final boolean f1997h;

    /* JADX INFO: renamed from: i */
    private final String f1998i;

    /* JADX INFO: renamed from: j */
    private final String f1999j;

    /* JADX INFO: renamed from: k */
    private final String[] f2000k;

    /* JADX INFO: renamed from: l */
    private final String f2001l;

    /* JADX INFO: renamed from: m */
    private final double f2002m;

    /* JADX INFO: renamed from: n */
    private final double f2003n;

    public C1169g(String str, String str2, String str3, String str4, String str5, String str6, String[] strArr, String str7, double d, double d2) {
        super(C1180r.f2043i);
        this.f1993d = str;
        try {
            this.f1994e = m1645a(str2);
            if (str3 == null) {
                long jM1644a = m1644a((CharSequence) str4);
                this.f1996g = jM1644a < 0 ? -1L : this.f1994e + jM1644a;
            } else {
                try {
                    this.f1996g = m1645a(str3);
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e.toString());
                }
            }
            boolean z = false;
            this.f1995f = str2.length() == 8;
            if (str3 != null && str3.length() == 8) {
                z = true;
            }
            this.f1997h = z;
            this.f1998i = str5;
            this.f1999j = str6;
            this.f2000k = strArr;
            this.f2001l = str7;
            this.f2002m = d;
            this.f2003n = d2;
        } catch (ParseException e2) {
            throw new IllegalArgumentException(e2.toString());
        }
    }

    /* JADX INFO: renamed from: b */
    private String m1648b() {
        return this.f1993d;
    }

    @Deprecated
    /* JADX INFO: renamed from: c */
    private Date m1649c() {
        return new Date(this.f1994e);
    }

    /* JADX INFO: renamed from: d */
    private long m1650d() {
        return this.f1994e;
    }

    /* JADX INFO: renamed from: e */
    private boolean m1651e() {
        return this.f1995f;
    }

    @Deprecated
    /* JADX INFO: renamed from: f */
    private Date m1652f() {
        if (this.f1996g < 0) {
            return null;
        }
        return new Date(this.f1996g);
    }

    /* JADX INFO: renamed from: g */
    private long m1653g() {
        return this.f1996g;
    }

    /* JADX INFO: renamed from: h */
    private boolean m1654h() {
        return this.f1997h;
    }

    /* JADX INFO: renamed from: i */
    private String m1655i() {
        return this.f1998i;
    }

    /* JADX INFO: renamed from: j */
    private String m1656j() {
        return this.f1999j;
    }

    /* JADX INFO: renamed from: k */
    private String[] m1657k() {
        return this.f2000k;
    }

    /* JADX INFO: renamed from: l */
    private String m1658l() {
        return this.f2001l;
    }

    /* JADX INFO: renamed from: m */
    private double m1659m() {
        return this.f2002m;
    }

    /* JADX INFO: renamed from: n */
    private double m1660n() {
        return this.f2003n;
    }

    @Override // com.google.p028a.p033b.p034a.AbstractC1179q
    /* JADX INFO: renamed from: a */
    public final String mo1571a() {
        StringBuilder sb = new StringBuilder(100);
        m1699a(this.f1993d, sb);
        m1699a(m1646a(this.f1995f, this.f1994e), sb);
        m1699a(m1646a(this.f1997h, this.f1996g), sb);
        m1699a(this.f1998i, sb);
        m1699a(this.f1999j, sb);
        m1700a(this.f2000k, sb);
        m1699a(this.f2001l, sb);
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    private static long m1645a(String str) throws ParseException {
        if (!f1992c.matcher(str).matches()) {
            throw new ParseException(str, 0);
        }
        if (str.length() == 8) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
            return simpleDateFormat.parse(str).getTime();
        }
        if (str.length() == 16 && str.charAt(15) == 'Z') {
            long jM1647b = m1647b(str.substring(0, 15));
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            long j = jM1647b + ((long) gregorianCalendar.get(15));
            gregorianCalendar.setTime(new Date(j));
            return j + ((long) gregorianCalendar.get(16));
        }
        return m1647b(str);
    }

    /* JADX INFO: renamed from: a */
    private static String m1646a(boolean z, long j) {
        DateFormat dateTimeInstance;
        if (j < 0) {
            return null;
        }
        if (z) {
            dateTimeInstance = DateFormat.getDateInstance(2);
        } else {
            dateTimeInstance = DateFormat.getDateTimeInstance(2, 2);
        }
        return dateTimeInstance.format(Long.valueOf(j));
    }

    /* JADX INFO: renamed from: a */
    private static long m1644a(CharSequence charSequence) {
        if (charSequence == null) {
            return -1L;
        }
        Matcher matcher = f1990a.matcher(charSequence);
        if (!matcher.matches()) {
            return -1L;
        }
        long j = 0;
        int i = 0;
        while (i < f1991b.length) {
            int i2 = i + 1;
            String strGroup = matcher.group(i2);
            if (strGroup != null) {
                j += f1991b[i] * ((long) Integer.parseInt(strGroup));
            }
            i = i2;
        }
        return j;
    }

    /* JADX INFO: renamed from: b */
    private static long m1647b(String str) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd'T'HHmmss", Locale.ENGLISH).parse(str).getTime();
    }
}

package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.p006a.C0503c;
import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* JADX INFO: renamed from: com.b.a.a.c.d */
/* JADX INFO: compiled from: HttpDate.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0507d {

    /* JADX INFO: renamed from: a */
    private static final ThreadLocal<DateFormat> f187a = new C0508e();

    /* JADX INFO: renamed from: b */
    private static final String[] f188b = {"EEE, dd MMM yyyy HH:mm:ss zzz", "EEEE, dd-MMM-yy HH:mm:ss zzz", "EEE MMM d HH:mm:ss yyyy", "EEE, dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MMM-yyyy HH-mm-ss z", "EEE, dd MMM yy HH:mm:ss z", "EEE dd-MMM-yyyy HH:mm:ss z", "EEE dd MMM yyyy HH:mm:ss z", "EEE dd-MMM-yyyy HH-mm-ss z", "EEE dd-MMM-yy HH:mm:ss z", "EEE dd MMM yy HH:mm:ss z", "EEE,dd-MMM-yy HH:mm:ss z", "EEE,dd-MMM-yyyy HH:mm:ss z", "EEE, dd-MM-yyyy HH:mm:ss z", "EEE MMM d yyyy HH:mm:ss z"};

    /* JADX INFO: renamed from: c */
    private static final DateFormat[] f189c = new DateFormat[15];

    /* JADX INFO: renamed from: a */
    public static Date m135a(String str) {
        if (str.length() == 0) {
            return null;
        }
        ParsePosition parsePosition = new ParsePosition(0);
        Date date = f187a.get().parse(str, parsePosition);
        if (parsePosition.getIndex() == str.length()) {
            return date;
        }
        synchronized (f188b) {
            int length = f188b.length;
            for (int i = 0; i < length; i++) {
                DateFormat simpleDateFormat = f189c[i];
                if (simpleDateFormat == null) {
                    simpleDateFormat = new SimpleDateFormat(f188b[i], Locale.US);
                    simpleDateFormat.setTimeZone(C0503c.f174f);
                    f189c[i] = simpleDateFormat;
                }
                parsePosition.setIndex(0);
                Date date2 = simpleDateFormat.parse(str, parsePosition);
                if (parsePosition.getIndex() != 0) {
                    return date2;
                }
            }
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public static String m134a(Date date) {
        return f187a.get().format(date);
    }
}

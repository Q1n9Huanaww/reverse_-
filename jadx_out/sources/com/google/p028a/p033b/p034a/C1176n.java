package com.google.p028a.p033b.p034a;

import com.google.p028a.C1363r;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.google.a.b.a.n */
/* JADX INFO: compiled from: GeoResultParser.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1176n extends AbstractC1183u {

    /* JADX INFO: renamed from: a */
    private static final Pattern f2032a = Pattern.compile("geo:([\\-0-9.]+),([\\-0-9.]+)(?:,([\\-0-9.]+))?(?:\\?(.*))?", 2);

    @Override // com.google.p028a.p033b.p034a.AbstractC1183u
    /* JADX INFO: renamed from: a */
    public final /* synthetic */ AbstractC1179q mo1568a(C1363r c1363r) {
        return m1696c(c1363r);
    }

    /* JADX INFO: renamed from: c */
    private static C1175m m1696c(C1363r c1363r) {
        Matcher matcher = f2032a.matcher(m1715b(c1363r));
        if (!matcher.matches()) {
            return null;
        }
        String strGroup = matcher.group(4);
        try {
            double d = Double.parseDouble(matcher.group(1));
            if (d <= 90.0d && d >= -90.0d) {
                double d2 = Double.parseDouble(matcher.group(2));
                if (d2 <= 180.0d && d2 >= -180.0d) {
                    double d3 = 0.0d;
                    if (matcher.group(3) != null) {
                        double d4 = Double.parseDouble(matcher.group(3));
                        if (d4 < 0.0d) {
                            return null;
                        }
                        d3 = d4;
                    }
                    return new C1175m(d, d2, d3, strGroup);
                }
                return null;
            }
            return null;
        } catch (NumberFormatException unused) {
            return null;
        }
    }
}

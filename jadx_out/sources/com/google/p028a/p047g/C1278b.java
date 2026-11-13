package com.google.p028a.p047g;

import org.apache.commons.p092io.FilenameUtils;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.g.b */
/* JADX INFO: compiled from: CodaBarWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1278b extends AbstractC1295s {

    /* JADX INFO: renamed from: a */
    private static final char[] f2442a = {'A', 'B', 'C', 'D'};

    /* JADX INFO: renamed from: b */
    private static final char[] f2443b = {'T', 'N', '*', 'E'};

    /* JADX INFO: renamed from: c */
    private static final char[] f2444c = {IOUtils.DIR_SEPARATOR_UNIX, ':', '+', FilenameUtils.EXTENSION_SEPARATOR};

    /* JADX INFO: renamed from: d */
    private static final char f2445d = f2442a[0];

    @Override // com.google.p028a.p047g.AbstractC1295s
    /* JADX INFO: renamed from: a */
    public final boolean[] mo2159a(String str) {
        int i;
        if (str.length() < 2) {
            str = f2445d + str + f2445d;
        } else {
            char upperCase = Character.toUpperCase(str.charAt(0));
            char upperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
            boolean zM2034a = C1246a.m2034a(f2442a, upperCase);
            boolean zM2034a2 = C1246a.m2034a(f2442a, upperCase2);
            boolean zM2034a3 = C1246a.m2034a(f2443b, upperCase);
            boolean zM2034a4 = C1246a.m2034a(f2443b, upperCase2);
            if (zM2034a) {
                if (!zM2034a2) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
            } else if (!zM2034a3) {
                if (zM2034a2 || zM2034a4) {
                    throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
                }
                str = f2445d + str + f2445d;
            } else if (!zM2034a4) {
                throw new IllegalArgumentException("Invalid start/end guards: ".concat(String.valueOf(str)));
            }
        }
        int i2 = 20;
        for (int i3 = 1; i3 < str.length() - 1; i3++) {
            if (Character.isDigit(str.charAt(i3)) || str.charAt(i3) == '-' || str.charAt(i3) == '$') {
                i2 += 9;
            } else {
                if (!C1246a.m2034a(f2444c, str.charAt(i3))) {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i3) + '\'');
                }
                i2 += 10;
            }
        }
        boolean[] zArr = new boolean[i2 + (str.length() - 1)];
        int i4 = 0;
        for (int i5 = 0; i5 < str.length(); i5++) {
            char upperCase3 = Character.toUpperCase(str.charAt(i5));
            if (i5 == 0 || i5 == str.length() - 1) {
                if (upperCase3 == '*') {
                    upperCase3 = 'C';
                } else if (upperCase3 == 'E') {
                    upperCase3 = 'D';
                } else if (upperCase3 == 'N') {
                    upperCase3 = 'B';
                } else if (upperCase3 == 'T') {
                    upperCase3 = 'A';
                }
            }
            int i6 = 0;
            while (true) {
                if (i6 >= C1246a.f2336a.length) {
                    i = 0;
                    break;
                }
                if (upperCase3 == C1246a.f2336a[i6]) {
                    i = C1246a.f2337b[i6];
                    break;
                }
                i6++;
            }
            int i7 = i4;
            int i8 = 0;
            boolean z = true;
            while (true) {
                int i9 = 0;
                while (i8 < 7) {
                    zArr[i7] = z;
                    i7++;
                    if (((i >> (6 - i8)) & 1) == 0 || i9 == 1) {
                        z = !z;
                        i8++;
                    } else {
                        i9++;
                    }
                }
                break;
            }
            if (i5 < str.length() - 1) {
                zArr[i7] = false;
                i7++;
            }
            i4 = i7;
        }
        return zArr;
    }
}

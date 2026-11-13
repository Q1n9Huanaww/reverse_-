package com.google.p028a.p047g;

import android.support.v7.widget.ActivityChooserView;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.a */
/* JADX INFO: compiled from: CodaBarReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1246a extends AbstractC1294r {

    /* JADX INFO: renamed from: c */
    private static final float f2338c = 2.0f;

    /* JADX INFO: renamed from: d */
    private static final float f2339d = 1.5f;

    /* JADX INFO: renamed from: f */
    private static final int f2341f = 3;

    /* JADX INFO: renamed from: h */
    private final StringBuilder f2343h = new StringBuilder(20);

    /* JADX INFO: renamed from: i */
    private int[] f2344i = new int[80];

    /* JADX INFO: renamed from: j */
    private int f2345j = 0;

    /* JADX INFO: renamed from: e */
    private static final String f2340e = "0123456789-$:/.+ABCD";

    /* JADX INFO: renamed from: a */
    static final char[] f2336a = f2340e.toCharArray();

    /* JADX INFO: renamed from: b */
    static final int[] f2337b = {3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};

    /* JADX INFO: renamed from: g */
    private static final char[] f2342g = {'A', 'B', 'C', 'D'};

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1358m {
        Arrays.fill(this.f2344i, 0);
        this.f2345j = 0;
        int iM1762d = c1190a.m1762d(0);
        int i2 = c1190a.f2065b;
        if (iM1762d >= i2) {
            throw C1358m.m2611a();
        }
        boolean z = true;
        int i3 = 0;
        while (iM1762d < i2) {
            if (c1190a.m1755a(iM1762d) != z) {
                i3++;
            } else {
                m2036b(i3);
                z = !z;
                i3 = 1;
            }
            iM1762d++;
        }
        m2036b(i3);
        int i4 = 1;
        while (i4 < this.f2345j) {
            int iM2037c = m2037c(i4);
            if (iM2037c != -1 && m2034a(f2342g, f2336a[iM2037c])) {
                int i5 = 0;
                for (int i6 = i4; i6 < i4 + 7; i6++) {
                    i5 += this.f2344i[i6];
                }
                if (i4 == 1 || this.f2344i[i4 - 1] >= i5 / 2) {
                    this.f2343h.setLength(0);
                    int i7 = i4;
                    do {
                        int iM2037c2 = m2037c(i7);
                        if (iM2037c2 == -1) {
                            throw C1358m.m2611a();
                        }
                        this.f2343h.append((char) iM2037c2);
                        i7 += 8;
                        if (this.f2343h.length() > 1 && m2034a(f2342g, f2336a[iM2037c2])) {
                            break;
                        }
                    } while (i7 < this.f2345j);
                    int i8 = i7 - 1;
                    int i9 = this.f2344i[i8];
                    int i10 = 0;
                    for (int i11 = -8; i11 < -1; i11++) {
                        i10 += this.f2344i[i7 + i11];
                    }
                    if (i7 < this.f2345j && i9 < i10 / 2) {
                        throw C1358m.m2611a();
                    }
                    int[] iArr = {0, 0, 0, 0};
                    int[] iArr2 = {0, 0, 0, 0};
                    int length = this.f2343h.length() - 1;
                    int i12 = i4;
                    int i13 = 0;
                    while (true) {
                        int i14 = f2337b[this.f2343h.charAt(i13)];
                        for (int i15 = 6; i15 >= 0; i15--) {
                            int i16 = (i15 & 1) + ((i14 & 1) << 1);
                            iArr[i16] = iArr[i16] + this.f2344i[i12 + i15];
                            iArr2[i16] = iArr2[i16] + 1;
                            i14 >>= 1;
                        }
                        if (i13 >= length) {
                            break;
                        }
                        i12 += 8;
                        i13++;
                    }
                    float[] fArr = new float[4];
                    float[] fArr2 = new float[4];
                    int i17 = 0;
                    for (int i18 = 2; i17 < i18; i18 = 2) {
                        fArr2[i17] = 0.0f;
                        int i19 = i17 + 2;
                        fArr2[i19] = ((iArr[i17] / iArr2[i17]) + (iArr[i19] / iArr2[i19])) / f2338c;
                        fArr[i17] = fArr2[i19];
                        fArr[i19] = ((iArr[i19] * f2338c) + f2339d) / iArr2[i19];
                        i17++;
                    }
                    int i20 = i4;
                    int i21 = 0;
                    while (true) {
                        int i22 = f2337b[this.f2343h.charAt(i21)];
                        for (int i23 = 6; i23 >= 0; i23--) {
                            int i24 = (i23 & 1) + ((i22 & 1) << 1);
                            float f = this.f2344i[i20 + i23];
                            if (f < fArr2[i24] || f > fArr[i24]) {
                                throw C1358m.m2611a();
                            }
                            i22 >>= 1;
                        }
                        if (i21 >= length) {
                            for (int i25 = 0; i25 < this.f2343h.length(); i25++) {
                                this.f2343h.setCharAt(i25, f2336a[this.f2343h.charAt(i25)]);
                            }
                            if (!m2034a(f2342g, this.f2343h.charAt(0))) {
                                throw C1358m.m2611a();
                            }
                            if (!m2034a(f2342g, this.f2343h.charAt(this.f2343h.length() - 1))) {
                                throw C1358m.m2611a();
                            }
                            if (this.f2343h.length() <= 3) {
                                throw C1358m.m2611a();
                            }
                            if (map == null || !map.containsKey(EnumC1233e.RETURN_CODABAR_START_END)) {
                                this.f2343h.deleteCharAt(this.f2343h.length() - 1);
                                this.f2343h.deleteCharAt(0);
                            }
                            int i26 = 0;
                            for (int i27 = 0; i27 < i4; i27++) {
                                i26 += this.f2344i[i27];
                            }
                            float f2 = i26;
                            while (i4 < i8) {
                                i26 += this.f2344i[i4];
                                i4++;
                            }
                            float f3 = i;
                            return new C1363r(this.f2343h.toString(), null, new C1365t[]{new C1365t(f2, f3), new C1365t(i26, f3)}, EnumC1138a.CODABAR);
                        }
                        i20 += 8;
                        i21++;
                    }
                }
            }
            i4 += 2;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private void m2032a(int i) throws C1358m {
        int[] iArr = {0, 0, 0, 0};
        int[] iArr2 = {0, 0, 0, 0};
        int length = this.f2343h.length() - 1;
        int i2 = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = f2337b[this.f2343h.charAt(i4)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) << 1);
                iArr[i7] = iArr[i7] + this.f2344i[i3 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i4 >= length) {
                break;
            }
            i3 += 8;
            i4++;
        }
        float[] fArr = new float[4];
        float[] fArr2 = new float[4];
        for (int i8 = 0; i8 < 2; i8++) {
            fArr2[i8] = 0.0f;
            int i9 = i8 + 2;
            fArr2[i9] = ((iArr[i8] / iArr2[i8]) + (iArr[i9] / iArr2[i9])) / f2338c;
            fArr[i8] = fArr2[i9];
            fArr[i9] = ((iArr[i9] * f2338c) + f2339d) / iArr2[i9];
        }
        loop3: while (true) {
            int i10 = f2337b[this.f2343h.charAt(i2)];
            for (int i11 = 6; i11 >= 0; i11--) {
                int i12 = (i11 & 1) + ((i10 & 1) << 1);
                float f = this.f2344i[i + i11];
                if (f < fArr2[i12] || f > fArr[i12]) {
                    break loop3;
                }
                i10 >>= 1;
            }
            if (i2 >= length) {
                return;
            }
            i += 8;
            i2++;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private void m2033a(C1190a c1190a) throws C1358m {
        this.f2345j = 0;
        int iM1762d = c1190a.m1762d(0);
        int i = c1190a.f2065b;
        if (iM1762d >= i) {
            throw C1358m.m2611a();
        }
        boolean z = true;
        int i2 = 0;
        while (iM1762d < i) {
            if (c1190a.m1755a(iM1762d) != z) {
                i2++;
            } else {
                m2036b(i2);
                z = !z;
                i2 = 1;
            }
            iM1762d++;
        }
        m2036b(i2);
    }

    /* JADX INFO: renamed from: b */
    private void m2036b(int i) {
        this.f2344i[this.f2345j] = i;
        this.f2345j++;
        if (this.f2345j >= this.f2344i.length) {
            int[] iArr = new int[this.f2345j << 1];
            System.arraycopy(this.f2344i, 0, iArr, 0, this.f2345j);
            this.f2344i = iArr;
        }
    }

    /* JADX INFO: renamed from: b */
    private int m2035b() throws C1358m {
        for (int i = 1; i < this.f2345j; i += 2) {
            int iM2037c = m2037c(i);
            if (iM2037c != -1 && m2034a(f2342g, f2336a[iM2037c])) {
                int i2 = 0;
                for (int i3 = i; i3 < i + 7; i3++) {
                    i2 += this.f2344i[i3];
                }
                if (i == 1 || this.f2344i[i - 1] >= i2 / 2) {
                    return i;
                }
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    static boolean m2034a(char[] cArr, char c) {
        if (cArr != null) {
            for (char c2 : cArr) {
                if (c2 == c) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: c */
    private int m2037c(int i) {
        int i2 = i + 7;
        if (i2 >= this.f2345j) {
            return -1;
        }
        int[] iArr = this.f2344i;
        int i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i4 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
        int i5 = 0;
        for (int i6 = i; i6 < i2; i6 += 2) {
            int i7 = iArr[i6];
            if (i7 < i4) {
                i4 = i7;
            }
            if (i7 > i5) {
                i5 = i7;
            }
        }
        int i8 = (i4 + i5) / 2;
        int i9 = 0;
        for (int i10 = i + 1; i10 < i2; i10 += 2) {
            int i11 = iArr[i10];
            if (i11 < i3) {
                i3 = i11;
            }
            if (i11 > i9) {
                i9 = i11;
            }
        }
        int i12 = (i3 + i9) / 2;
        int i13 = 128;
        int i14 = 0;
        for (int i15 = 0; i15 < 7; i15++) {
            i13 >>= 1;
            if (iArr[i + i15] > ((i15 & 1) == 0 ? i8 : i12)) {
                i14 |= i13;
            }
        }
        for (int i16 = 0; i16 < f2337b.length; i16++) {
            if (f2337b[i16] == i14) {
                return i16;
            }
        }
        return -1;
    }
}

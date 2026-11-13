package com.google.p028a.p047g;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.p035c.C1194b;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.d */
/* JADX INFO: compiled from: Code128Writer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1280d extends AbstractC1295s {

    /* JADX INFO: renamed from: a */
    private static final int f2462a = 103;

    /* JADX INFO: renamed from: b */
    private static final int f2463b = 104;

    /* JADX INFO: renamed from: c */
    private static final int f2464c = 105;

    /* JADX INFO: renamed from: d */
    private static final int f2465d = 101;

    /* JADX INFO: renamed from: e */
    private static final int f2466e = 100;

    /* JADX INFO: renamed from: f */
    private static final int f2467f = 99;

    /* JADX INFO: renamed from: g */
    private static final int f2468g = 106;

    /* JADX INFO: renamed from: h */
    private static final char f2469h = 241;

    /* JADX INFO: renamed from: i */
    private static final char f2470i = 242;

    /* JADX INFO: renamed from: j */
    private static final char f2471j = 243;

    /* JADX INFO: renamed from: k */
    private static final char f2472k = 244;

    /* JADX INFO: renamed from: l */
    private static final int f2473l = 102;

    /* JADX INFO: renamed from: m */
    private static final int f2474m = 97;

    /* JADX INFO: renamed from: n */
    private static final int f2475n = 96;

    /* JADX INFO: renamed from: o */
    private static final int f2476o = 101;

    /* JADX INFO: renamed from: p */
    private static final int f2477p = 100;

    /* JADX WARN: $VALUES field not found */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: renamed from: com.google.a.g.d$a */
    /* JADX INFO: compiled from: Code128Writer.java */
    private static final class a {

        /* JADX INFO: renamed from: a */
        public static final int f2478a = 1;

        /* JADX INFO: renamed from: b */
        public static final int f2479b = 2;

        /* JADX INFO: renamed from: c */
        public static final int f2480c = 3;

        /* JADX INFO: renamed from: d */
        public static final int f2481d = 4;

        /* JADX INFO: renamed from: e */
        private static final /* synthetic */ int[] f2482e = {f2478a, f2479b, f2480c, f2481d};

        private a(String str, int i) {
            super(str, i);
        }

        /* JADX INFO: renamed from: a */
        private static int[] m2164a() {
            return (int[]) f2482e.clone();
        }
    }

    @Override // com.google.p028a.p047g.AbstractC1295s, com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (enumC1138a != EnumC1138a.CODE_128) {
            throw new IllegalArgumentException("Can only encode CODE_128, but got ".concat(String.valueOf(enumC1138a)));
        }
        return super.mo1517a(str, enumC1138a, i, i2, map);
    }

    @Override // com.google.p028a.p047g.AbstractC1295s
    /* JADX INFO: renamed from: a */
    public final boolean[] mo2159a(String str) {
        int length = str.length();
        if (length <= 0 || length > 80) {
            throw new IllegalArgumentException("Contents length should be between 1 and 80 characters, but got ".concat(String.valueOf(length)));
        }
        int iA = 0;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            switch (cCharAt) {
                case 241:
                case 242:
                case 243:
                case 244:
                    break;
                default:
                    if (cCharAt > 127) {
                        throw new IllegalArgumentException("Bad character in input: ".concat(String.valueOf(cCharAt)));
                    }
                    break;
                    break;
            }
        }
        ArrayList<int[]> arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        int i5 = 1;
        while (true) {
            int i6 = 103;
            if (i2 < length) {
                int iM2163a = m2163a(str, i2, i3);
                if (iM2163a == i3) {
                    int iCharAt = 101;
                    switch (str.charAt(i2)) {
                        case 241:
                            iCharAt = 102;
                            break;
                        case 242:
                            iCharAt = 97;
                            break;
                        case 243:
                            iCharAt = 96;
                            break;
                        case 244:
                            if (i3 != 101) {
                                iCharAt = 100;
                            }
                            break;
                        default:
                            switch (i3) {
                                case 100:
                                    iCharAt = str.charAt(i2) - ' ';
                                    break;
                                case 101:
                                    iCharAt = str.charAt(i2) - ' ';
                                    if (iCharAt < 0) {
                                        iCharAt += 96;
                                    }
                                    break;
                                default:
                                    iCharAt = Integer.parseInt(str.substring(i2, i2 + 2));
                                    i2++;
                                    break;
                            }
                            break;
                    }
                    i2++;
                    i6 = iCharAt;
                } else {
                    if (i3 == 0) {
                        switch (iM2163a) {
                            case 100:
                                i6 = 104;
                                break;
                            case 101:
                                break;
                            default:
                                i6 = 105;
                                break;
                        }
                    } else {
                        i6 = iM2163a;
                    }
                    i3 = iM2163a;
                }
                arrayList.add(C1279c.f2446a[i6]);
                i4 += i6 * i5;
                if (i2 != 0) {
                    i5++;
                }
            } else {
                arrayList.add(C1279c.f2446a[i4 % 103]);
                arrayList.add(C1279c.f2446a[106]);
                int i7 = 0;
                for (int[] iArr : arrayList) {
                    int i8 = i7;
                    for (int i9 : iArr) {
                        i8 += i9;
                    }
                    i7 = i8;
                }
                boolean[] zArr = new boolean[i7];
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    iA += m2196a(zArr, iA, (int[]) it2.next(), true);
                }
                return zArr;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static int m2162a(CharSequence charSequence, int i) {
        int length = charSequence.length();
        if (i >= length) {
            return a.f2478a;
        }
        char cCharAt = charSequence.charAt(i);
        if (cCharAt == 241) {
            return a.f2481d;
        }
        if (cCharAt < '0' || cCharAt > '9') {
            return a.f2478a;
        }
        int i2 = i + 1;
        if (i2 >= length) {
            return a.f2479b;
        }
        char cCharAt2 = charSequence.charAt(i2);
        if (cCharAt2 < '0' || cCharAt2 > '9') {
            return a.f2479b;
        }
        return a.f2480c;
    }

    /* JADX INFO: renamed from: a */
    private static int m2163a(CharSequence charSequence, int i, int i2) {
        int iM2162a;
        int iM2162a2;
        char cCharAt;
        int iM2162a3 = m2162a(charSequence, i);
        if (iM2162a3 == a.f2479b) {
            return 100;
        }
        if (iM2162a3 == a.f2478a) {
            return (i >= charSequence.length() || ((cCharAt = charSequence.charAt(i)) >= ' ' && (i2 != 101 || cCharAt >= '`'))) ? 100 : 101;
        }
        if (i2 == 99) {
            return 99;
        }
        if (i2 == 100) {
            if (iM2162a3 == a.f2481d || (iM2162a = m2162a(charSequence, i + 2)) == a.f2478a || iM2162a == a.f2479b) {
                return 100;
            }
            if (iM2162a == a.f2481d) {
                return m2162a(charSequence, i + 3) == a.f2480c ? 99 : 100;
            }
            int i3 = i + 4;
            while (true) {
                iM2162a2 = m2162a(charSequence, i3);
                if (iM2162a2 != a.f2480c) {
                    break;
                }
                i3 += 2;
            }
            return iM2162a2 == a.f2479b ? 100 : 99;
        }
        if (iM2162a3 == a.f2481d) {
            iM2162a3 = m2162a(charSequence, i + 1);
        }
        return iM2162a3 == a.f2480c ? 99 : 100;
    }
}

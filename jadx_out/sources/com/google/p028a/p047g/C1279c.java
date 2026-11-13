package com.google.p028a.p047g;

import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1190a;

/* JADX INFO: renamed from: com.google.a.g.c */
/* JADX INFO: compiled from: Code128Reader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1279c extends AbstractC1294r {

    /* JADX INFO: renamed from: a */
    static final int[][] f2446a = {new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};

    /* JADX INFO: renamed from: b */
    private static final float f2447b = 0.25f;

    /* JADX INFO: renamed from: c */
    private static final float f2448c = 0.7f;

    /* JADX INFO: renamed from: d */
    private static final int f2449d = 98;

    /* JADX INFO: renamed from: e */
    private static final int f2450e = 99;

    /* JADX INFO: renamed from: f */
    private static final int f2451f = 100;

    /* JADX INFO: renamed from: g */
    private static final int f2452g = 101;

    /* JADX INFO: renamed from: h */
    private static final int f2453h = 102;

    /* JADX INFO: renamed from: i */
    private static final int f2454i = 97;

    /* JADX INFO: renamed from: j */
    private static final int f2455j = 96;

    /* JADX INFO: renamed from: k */
    private static final int f2456k = 101;

    /* JADX INFO: renamed from: l */
    private static final int f2457l = 100;

    /* JADX INFO: renamed from: m */
    private static final int f2458m = 103;

    /* JADX INFO: renamed from: n */
    private static final int f2459n = 104;

    /* JADX INFO: renamed from: o */
    private static final int f2460o = 105;

    /* JADX INFO: renamed from: p */
    private static final int f2461p = 106;

    /* JADX INFO: renamed from: a */
    private static int m2160a(C1190a c1190a, int[] iArr, int i) throws C1358m {
        m2193a(c1190a, i, iArr);
        float f = f2447b;
        int i2 = -1;
        for (int i3 = 0; i3 < f2446a.length; i3++) {
            float fA = m2192a(iArr, f2446a[i3], f2448c);
            if (fA < f) {
                i2 = i3;
                f = fA;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw C1358m.m2611a();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:100:0x0180 A[PHI: r18
  0x0180: PHI (r18v14 boolean) = (r18v8 boolean), (r18v8 boolean), (r18v18 boolean), (r18v18 boolean) binds: [B:97:0x0178, B:98:0x017a, B:73:0x0130, B:74:0x0132] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:101:0x0183 A[PHI: r18
  0x0183: PHI (r18v12 boolean) = (r18v8 boolean), (r18v18 boolean) binds: [B:87:0x0159, B:63:0x0112] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:102:0x0187  */
    /* JADX WARN: Code duplicated, block: B:103:0x0189  */
    /* JADX WARN: Code duplicated, block: B:105:0x018d  */
    /* JADX WARN: Code duplicated, block: B:107:0x0191  */
    /* JADX WARN: Code duplicated, block: B:109:0x019a  */
    /* JADX WARN: Code duplicated, block: B:111:0x019e  */
    /* JADX WARN: Code duplicated, block: B:113:0x01a2  */
    /* JADX WARN: Code duplicated, block: B:115:0x01a6  */
    /* JADX WARN: Code duplicated, block: B:116:0x01a8  */
    /* JADX WARN: Code duplicated, block: B:118:0x01ae  */
    /* JADX WARN: Code duplicated, block: B:119:0x01b4  */
    /* JADX WARN: Code duplicated, block: B:120:0x01b8 A[PHI: r18
  0x01b8: PHI (r18v6 boolean) = (r18v4 boolean), (r18v8 boolean) binds: [B:113:0x01a2, B:87:0x0159] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:122:0x01bc A[PHI: r18
  0x01bc: PHI (r18v11 boolean) = (r18v4 boolean), (r18v18 boolean) binds: [B:113:0x01a2, B:63:0x0112] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:124:0x01c0 A[PHI: r18
  0x01c0: PHI (r18v10 boolean) = (r18v4 boolean), (r18v8 boolean), (r18v18 boolean) binds: [B:112:0x01a0, B:86:0x0157, B:62:0x0110] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:125:0x01c1 A[FALL_THROUGH, PHI: r5 r18
  0x01c1: PHI (r5v12 boolean) = 
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v13 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
  (r5v4 boolean)
 binds: [B:45:0x00d6, B:113:0x01a2, B:115:0x01a6, B:119:0x01b4, B:118:0x01ae, B:108:0x0196, B:87:0x0159, B:89:0x015e, B:93:0x016c, B:92:0x0166, B:124:0x01c0, B:63:0x0112, B:65:0x0117, B:69:0x0126, B:68:0x011f] A[DONT_GENERATE, DONT_INLINE]
  0x01c1: PHI (r18v3 boolean) = 
  (r18v2 boolean)
  (r18v4 boolean)
  (r18v4 boolean)
  (r18v4 boolean)
  (r18v4 boolean)
  (r18v2 boolean)
  (r18v8 boolean)
  (r18v8 boolean)
  (r18v8 boolean)
  (r18v8 boolean)
  (r18v10 boolean)
  (r18v18 boolean)
  (r18v18 boolean)
  (r18v18 boolean)
  (r18v18 boolean)
 binds: [B:45:0x00d6, B:113:0x01a2, B:115:0x01a6, B:119:0x01b4, B:118:0x01ae, B:108:0x0196, B:87:0x0159, B:89:0x015e, B:93:0x016c, B:92:0x0166, B:124:0x01c0, B:63:0x0112, B:65:0x0117, B:69:0x0126, B:68:0x011f] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:127:0x01c4  */
    /* JADX WARN: Code duplicated, block: B:129:0x01c8  */
    /* JADX WARN: Code duplicated, block: B:130:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:132:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:171:0x00db A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:35:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:37:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:39:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ca A[LOOP:3: B:41:0x00c8->B:42:0x00ca, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:44:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:49:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:51:0x00e4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:52:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:53:0x00ed  */
    /* JADX WARN: Code duplicated, block: B:54:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:56:0x00fa A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:57:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:58:0x0103  */
    /* JADX WARN: Code duplicated, block: B:59:0x010a  */
    /* JADX WARN: Code duplicated, block: B:61:0x010e  */
    /* JADX WARN: Code duplicated, block: B:63:0x0112  */
    /* JADX WARN: Code duplicated, block: B:65:0x0117  */
    /* JADX WARN: Code duplicated, block: B:66:0x0119  */
    /* JADX WARN: Code duplicated, block: B:68:0x011f  */
    /* JADX WARN: Code duplicated, block: B:69:0x0126  */
    /* JADX WARN: Code duplicated, block: B:70:0x012b  */
    /* JADX WARN: Code duplicated, block: B:71:0x012d A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:74:0x0132 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:76:0x0135  */
    /* JADX WARN: Code duplicated, block: B:77:0x0138  */
    /* JADX WARN: Code duplicated, block: B:79:0x013c A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:80:0x013e  */
    /* JADX WARN: Code duplicated, block: B:81:0x0145  */
    /* JADX WARN: Code duplicated, block: B:83:0x0151  */
    /* JADX WARN: Code duplicated, block: B:85:0x0155  */
    /* JADX WARN: Code duplicated, block: B:87:0x0159  */
    /* JADX WARN: Code duplicated, block: B:89:0x015e  */
    /* JADX WARN: Code duplicated, block: B:90:0x0160  */
    /* JADX WARN: Code duplicated, block: B:92:0x0166  */
    /* JADX WARN: Code duplicated, block: B:93:0x016c  */
    /* JADX WARN: Code duplicated, block: B:94:0x0170  */
    /* JADX WARN: Code duplicated, block: B:95:0x0172 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:96:0x0174 A[PHI: r18
  0x0174: PHI (r18v16 boolean) = (r18v8 boolean), (r18v18 boolean) binds: [B:95:0x0172, B:71:0x012d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:98:0x017a A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:99:0x017c A[PHI: r18
  0x017c: PHI (r18v15 boolean) = (r18v8 boolean), (r18v18 boolean) binds: [B:98:0x017a, B:74:0x0132] A[DONT_GENERATE, DONT_INLINE]] */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached at block B:77:0x0138
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final com.google.p028a.C1363r mo2038a(int r23, com.google.p028a.p035c.C1190a r24, java.util.Map<com.google.p028a.EnumC1233e, ?> r25) throws com.google.p028a.C1358m, com.google.p028a.C1303h, com.google.p028a.C1210d {
        /*
            Method dump skipped, instruction units count: 740
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.p028a.p047g.C1279c.mo2038a(int, com.google.a.c.a, java.util.Map):com.google.a.r");
    }

    /* JADX INFO: renamed from: a */
    private static int[] m2161a(C1190a c1190a) throws C1358m {
        int i = c1190a.f2065b;
        int iM1760c = c1190a.m1760c(0);
        int[] iArr = new int[6];
        int i2 = iM1760c;
        boolean z = false;
        int i3 = 0;
        while (iM1760c < i) {
            if (c1190a.m1755a(iM1760c) != z) {
                iArr[i3] = iArr[i3] + 1;
            } else {
                if (i3 == 5) {
                    float f = f2447b;
                    int i4 = -1;
                    for (int i5 = 103; i5 <= 105; i5++) {
                        float fA = m2192a(iArr, f2446a[i5], f2448c);
                        if (fA < f) {
                            i4 = i5;
                            f = fA;
                        }
                    }
                    if (i4 >= 0 && c1190a.m1756a(Math.max(0, i2 - ((iM1760c - i2) / 2)), i2)) {
                        return new int[]{i2, iM1760c, i4};
                    }
                    i2 += iArr[0] + iArr[1];
                    int i6 = i3 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i6);
                    iArr[i6] = 0;
                    iArr[i3] = 0;
                    i3--;
                } else {
                    i3++;
                }
                iArr[i3] = 1;
                z = !z;
            }
            iM1760c++;
        }
        throw C1358m.m2611a();
    }
}

package com.google.p028a.p029a.p032c;

import com.google.p028a.p035c.C1190a;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: renamed from: com.google.a.a.c.d */
/* JADX INFO: compiled from: HighLevelEncoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1147d {

    /* JADX INFO: renamed from: b */
    static final int f1918b = 0;

    /* JADX INFO: renamed from: c */
    static final int f1919c = 1;

    /* JADX INFO: renamed from: d */
    static final int f1920d = 2;

    /* JADX INFO: renamed from: e */
    static final int f1921e = 3;

    /* JADX INFO: renamed from: f */
    static final int f1922f = 4;

    /* JADX INFO: renamed from: h */
    static final int[][] f1924h;

    /* JADX INFO: renamed from: j */
    private static final int[][] f1925j;

    /* JADX INFO: renamed from: i */
    final byte[] f1926i;

    /* JADX INFO: renamed from: a */
    static final String[] f1917a = {"UPPER", "LOWER", "DIGIT", "MIXED", "PUNCT"};

    /* JADX INFO: renamed from: g */
    static final int[][] f1923g = {new int[]{0, 327708, 327710, 327709, 656318}, new int[]{590318, 0, 327710, 327709, 656318}, new int[]{262158, 590300, 0, 590301, 932798}, new int[]{327709, 327708, 656318, 0, 327710}, new int[]{327711, 656380, 656382, 656381, 0}};

    static {
        int[][] iArr = (int[][]) Array.newInstance((Class<?>) int.class, 5, 256);
        f1925j = iArr;
        iArr[0][32] = 1;
        for (int i = 65; i <= 90; i++) {
            f1925j[0][i] = (i - 65) + 2;
        }
        f1925j[1][32] = 1;
        for (int i2 = 97; i2 <= 122; i2++) {
            f1925j[1][i2] = (i2 - 97) + 2;
        }
        f1925j[2][32] = 1;
        for (int i3 = 48; i3 <= 57; i3++) {
            f1925j[2][i3] = (i3 - 48) + 2;
        }
        f1925j[2][44] = 12;
        f1925j[2][46] = 13;
        int[] iArr2 = {0, 32, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 27, 28, 29, 30, 31, 64, 92, 94, 95, 96, 124, 126, 127};
        for (int i4 = 0; i4 < 28; i4++) {
            f1925j[3][iArr2[i4]] = i4;
        }
        int[] iArr3 = {0, 13, 0, 0, 0, 0, 33, 39, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 58, 59, 60, 61, 62, 63, 91, 93, 123, 125};
        for (int i5 = 0; i5 < 31; i5++) {
            if (iArr3[i5] > 0) {
                f1925j[4][iArr3[i5]] = i5;
            }
        }
        int[][] iArr4 = (int[][]) Array.newInstance((Class<?>) int.class, 6, 6);
        f1924h = iArr4;
        for (int[] iArr5 : iArr4) {
            Arrays.fill(iArr5, -1);
        }
        f1924h[0][4] = 0;
        f1924h[1][4] = 0;
        f1924h[1][0] = 28;
        f1924h[3][4] = 0;
        f1924h[2][4] = 0;
        f1924h[2][0] = 15;
    }

    public C1147d(byte[] bArr) {
        this.f1926i = bArr;
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0034  */
    /* JADX INFO: renamed from: a */
    private C1190a m1539a() {
        int i;
        Collection<C1149f> collectionSingletonList = Collections.singletonList(C1149f.f1930a);
        int i2 = 0;
        while (i2 < this.f1926i.length) {
            int i3 = i2 + 1;
            byte b = i3 < this.f1926i.length ? this.f1926i[i3] : (byte) 0;
            byte b2 = this.f1926i[i2];
            if (b2 != 13) {
                if (b2 != 44) {
                    if (b2 != 46) {
                        if (b2 == 58 && b == 32) {
                            i = 5;
                        } else {
                            i = 0;
                        }
                    } else if (b == 32) {
                        i = 3;
                    } else {
                        i = 0;
                    }
                } else if (b == 32) {
                    i = 4;
                } else {
                    i = 0;
                }
            } else if (b == 10) {
                i = 2;
            } else {
                i = 0;
            }
            if (i <= 0) {
                collectionSingletonList = m1541a(collectionSingletonList, i2);
            } else {
                LinkedList linkedList = new LinkedList();
                for (C1149f c1149f : collectionSingletonList) {
                    C1149f c1149fM1554b = c1149f.m1554b(i2);
                    linkedList.add(c1149fM1554b.m1551a(4, i));
                    if (c1149f.f1931b != 4) {
                        linkedList.add(c1149fM1554b.m1555b(4, i));
                    }
                    if (i == 3 || i == 4) {
                        linkedList.add(c1149fM1554b.m1551a(2, 16 - i).m1551a(2, 1));
                    }
                    if (c1149f.f1932c > 0) {
                        linkedList.add(c1149f.m1550a(i2).m1550a(i3));
                    }
                }
                collectionSingletonList = m1540a(linkedList);
                i2 = i3;
            }
            i2++;
        }
        return ((C1149f) Collections.min(collectionSingletonList, new AnonymousClass1())).m1552a(this.f1926i);
    }

    /* JADX INFO: renamed from: com.google.a.a.c.d$1, reason: invalid class name */
    /* JADX INFO: compiled from: HighLevelEncoder.java */
    final class AnonymousClass1 implements Comparator<C1149f> {
        AnonymousClass1() {
        }

        @Override // java.util.Comparator
        public final /* bridge */ /* synthetic */ int compare(C1149f c1149f, C1149f c1149f2) {
            return c1149f.f1933d - c1149f2.f1933d;
        }

        /* JADX INFO: renamed from: a */
        private static int m1545a(C1149f c1149f, C1149f c1149f2) {
            return c1149f.f1933d - c1149f2.f1933d;
        }
    }

    /* JADX INFO: renamed from: a */
    private Collection<C1149f> m1541a(Iterable<C1149f> iterable, int i) {
        LinkedList linkedList = new LinkedList();
        Iterator<C1149f> it2 = iterable.iterator();
        while (it2.hasNext()) {
            m1544a(it2.next(), i, linkedList);
        }
        return m1540a(linkedList);
    }

    /* JADX INFO: renamed from: a */
    final void m1544a(C1149f c1149f, int i, Collection<C1149f> collection) {
        char c = (char) (this.f1926i[i] & 255);
        boolean z = f1925j[c1149f.f1931b][c] > 0;
        C1149f c1149fM1554b = null;
        for (int i2 = 0; i2 <= 4; i2++) {
            int i3 = f1925j[i2][c];
            if (i3 > 0) {
                if (c1149fM1554b == null) {
                    c1149fM1554b = c1149f.m1554b(i);
                }
                if (!z || i2 == c1149f.f1931b || i2 == 2) {
                    collection.add(c1149fM1554b.m1551a(i2, i3));
                }
                if (!z && f1924h[c1149f.f1931b][i2] >= 0) {
                    collection.add(c1149fM1554b.m1555b(i2, i3));
                }
            }
        }
        if (c1149f.f1932c > 0 || f1925j[c1149f.f1931b][c] == 0) {
            collection.add(c1149f.m1550a(i));
        }
    }

    /* JADX INFO: renamed from: a */
    static Collection<C1149f> m1542a(Iterable<C1149f> iterable, int i, int i2) {
        LinkedList linkedList = new LinkedList();
        for (C1149f c1149f : iterable) {
            C1149f c1149fM1554b = c1149f.m1554b(i);
            linkedList.add(c1149fM1554b.m1551a(4, i2));
            if (c1149f.f1931b != 4) {
                linkedList.add(c1149fM1554b.m1555b(4, i2));
            }
            if (i2 == 3 || i2 == 4) {
                linkedList.add(c1149fM1554b.m1551a(2, 16 - i2).m1551a(2, 1));
            }
            if (c1149f.f1932c > 0) {
                linkedList.add(c1149f.m1550a(i).m1550a(i + 1));
            }
        }
        return m1540a(linkedList);
    }

    /* JADX INFO: renamed from: a */
    private static void m1543a(C1149f c1149f, int i, int i2, Collection<C1149f> collection) {
        C1149f c1149fM1554b = c1149f.m1554b(i);
        collection.add(c1149fM1554b.m1551a(4, i2));
        if (c1149f.f1931b != 4) {
            collection.add(c1149fM1554b.m1555b(4, i2));
        }
        if (i2 == 3 || i2 == 4) {
            collection.add(c1149fM1554b.m1551a(2, 16 - i2).m1551a(2, 1));
        }
        if (c1149f.f1932c > 0) {
            collection.add(c1149f.m1550a(i).m1550a(i + 1));
        }
    }

    /* JADX INFO: renamed from: a */
    static Collection<C1149f> m1540a(Iterable<C1149f> iterable) {
        LinkedList linkedList = new LinkedList();
        for (C1149f c1149f : iterable) {
            boolean z = true;
            Iterator it2 = linkedList.iterator();
            while (it2.hasNext()) {
                C1149f c1149f2 = (C1149f) it2.next();
                if (c1149f2.m1553a(c1149f)) {
                    z = false;
                    break;
                }
                if (c1149f.m1553a(c1149f2)) {
                    it2.remove();
                }
            }
            if (z) {
                linkedList.add(c1149f);
            }
        }
        return linkedList;
    }
}

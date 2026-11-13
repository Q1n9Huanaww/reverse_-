package com.google.p028a.p047g.p048a.p049a;

import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.C1363r;
import com.google.p028a.C1365t;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1233e;
import com.google.p028a.p035c.C1190a;
import com.google.p028a.p035c.p036a.C1191a;
import com.google.p028a.p047g.p048a.AbstractC1247a;
import com.google.p028a.p047g.p048a.C1271b;
import com.google.p028a.p047g.p048a.C1272c;
import com.google.p028a.p047g.p048a.C1275f;
import com.google.p028a.p047g.p048a.p049a.p050a.AbstractC1258j;
import com.google.p028a.p047g.p048a.p049a.p050a.C1249a;
import com.google.p028a.p047g.p048a.p049a.p050a.C1250b;
import com.google.p028a.p047g.p048a.p049a.p050a.C1251c;
import com.google.p028a.p047g.p048a.p049a.p050a.C1252d;
import com.google.p028a.p047g.p048a.p049a.p050a.C1253e;
import com.google.p028a.p047g.p048a.p049a.p050a.C1255g;
import com.google.p028a.p047g.p048a.p049a.p050a.C1259k;
import com.google.p028a.p047g.p048a.p049a.p050a.C1267s;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.a.a.d */
/* JADX INFO: compiled from: RSSExpandedReader.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1270d extends AbstractC1247a {

    /* JADX INFO: renamed from: l */
    private static final int f2410l = 0;

    /* JADX INFO: renamed from: m */
    private static final int f2411m = 1;

    /* JADX INFO: renamed from: n */
    private static final int f2412n = 2;

    /* JADX INFO: renamed from: o */
    private static final int f2413o = 3;

    /* JADX INFO: renamed from: p */
    private static final int f2414p = 4;

    /* JADX INFO: renamed from: q */
    private static final int f2415q = 5;

    /* JADX INFO: renamed from: s */
    private static final int f2417s = 11;

    /* JADX INFO: renamed from: t */
    private final List<C1268b> f2418t = new ArrayList(11);

    /* JADX INFO: renamed from: u */
    private final List<C1269c> f2419u = new ArrayList();

    /* JADX INFO: renamed from: v */
    private final int[] f2420v = new int[2];

    /* JADX INFO: renamed from: w */
    private boolean f2421w;

    /* JADX INFO: renamed from: g */
    private static final int[] f2405g = {7, 5, 4, 3, 1};

    /* JADX INFO: renamed from: h */
    private static final int[] f2406h = {4, 20, 52, 104, UiMessage.CommandToUi.Command_Type.SET_RADIO_GROUP_VALUE};

    /* JADX INFO: renamed from: i */
    private static final int[] f2407i = {0, 348, 1388, 2948, 3988};

    /* JADX INFO: renamed from: j */
    private static final int[][] f2408j = {new int[]{1, 8, 4, 1}, new int[]{3, 6, 4, 1}, new int[]{3, 4, 6, 1}, new int[]{3, 2, 8, 1}, new int[]{2, 6, 5, 1}, new int[]{2, 2, 9, 1}};

    /* JADX INFO: renamed from: k */
    private static final int[][] f2409k = {new int[]{1, 3, 9, 27, 81, 32, 96, 77}, new int[]{20, 60, 180, 118, 143, 7, 21, 63}, new int[]{189, 145, 13, 39, 117, 140, UiMessage.CommandToUi.Command_Type.SET_WEB_VIEW_VALUE, UiMessage.CommandToUi.Command_Type.SET_CHECK_BOX_VALUE}, new int[]{193, 157, 49, 147, 19, 57, 171, 91}, new int[]{62, 186, 136, 197, 169, 85, 44, 132}, new int[]{185, 133, 188, 142, 4, 12, 36, 108}, new int[]{113, 128, 173, 97, 80, 29, 87, 50}, new int[]{150, 28, 84, 41, 123, 158, 52, 156}, new int[]{46, 138, UiMessage.CommandToUi.Command_Type.SET_BOTTON_VALUE, 187, 139, UiMessage.CommandToUi.Command_Type.SET_IMAGE_VIEW_VALUE, 196, 166}, new int[]{76, 17, 51, 153, 37, 111, 122, 155}, new int[]{43, C0769a.f1097aC, 176, 106, 107, 110, 119, 146}, new int[]{16, 48, 144, 10, 30, 90, 59, 177}, new int[]{109, 116, 137, 200, 178, 112, 125, 164}, new int[]{70, UiMessage.CommandToUi.Command_Type.SET_LINE_VALUE, UiMessage.CommandToUi.Command_Type.SET_TAB_VALUE, UiMessage.CommandToUi.Command_Type.SET_EDIT_TEXT_VALUE, 184, 130, 179, 115}, new int[]{134, 191, 151, 31, 93, 68, UiMessage.CommandToUi.Command_Type.SET_RADIO_GROUP_VALUE, 190}, new int[]{148, 22, 66, 198, 172, 94, 71, 2}, new int[]{6, 18, 54, 162, 64, 192, 154, 40}, new int[]{120, 149, 25, 75, 14, 42, 126, 167}, new int[]{79, 26, 78, 23, 69, UiMessage.CommandToUi.Command_Type.SET_SPINNER_VALUE, 199, 175}, new int[]{103, 98, 83, 38, 114, 131, 182, 124}, new int[]{161, 61, 183, 127, 170, 88, 53, 159}, new int[]{55, 165, 73, 8, 24, 72, 5, 15}, new int[]{45, 135, 194, 160, 58, 174, 100, 89}};

    /* JADX INFO: renamed from: r */
    private static final int[][] f2416r = {new int[]{0, 0}, new int[]{0, 1, 1}, new int[]{0, 2, 1, 3}, new int[]{0, 4, 1, 3, 2}, new int[]{0, 4, 1, 3, 3, 5}, new int[]{0, 4, 1, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 2, 3, 3}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 4}, new int[]{0, 0, 1, 1, 2, 2, 3, 4, 5, 5}, new int[]{0, 0, 1, 1, 2, 3, 3, 4, 4, 5, 5}};

    @Override // com.google.p028a.p047g.AbstractC1294r
    /* JADX INFO: renamed from: a */
    public final C1363r mo2038a(int i, C1190a c1190a, Map<EnumC1233e, ?> map) throws C1358m, C1303h {
        this.f2418t.clear();
        this.f2421w = false;
        try {
            return m2129b(m2121a(i, c1190a));
        } catch (C1358m unused) {
            this.f2418t.clear();
            this.f2421w = true;
            return m2129b(m2121a(i, c1190a));
        }
    }

    @Override // com.google.p028a.p047g.AbstractC1294r, com.google.p028a.InterfaceC1361p
    /* JADX INFO: renamed from: a */
    public final void mo1491a() {
        this.f2418t.clear();
        this.f2419u.clear();
    }

    /* JADX INFO: renamed from: a */
    private List<C1268b> m2121a(int i, C1190a c1190a) throws C1358m {
        boolean zM2116a;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6 = false;
        while (!z6) {
            try {
                this.f2418t.add(m2118a(c1190a, this.f2418t, i));
            } catch (C1358m e) {
                if (this.f2418t.isEmpty()) {
                    throw e;
                }
                z6 = true;
            }
        }
        if (m2133c()) {
            return this.f2418t;
        }
        boolean z7 = !this.f2419u.isEmpty();
        int i2 = 0;
        boolean zM2116a2 = false;
        while (true) {
            if (i2 >= this.f2419u.size()) {
                zM2116a = false;
                break;
            }
            C1269c c1269c = this.f2419u.get(i2);
            if (c1269c.f2403b > i) {
                zM2116a = c1269c.m2116a(this.f2418t);
                break;
            }
            zM2116a2 = c1269c.m2116a(this.f2418t);
            i2++;
        }
        if (!zM2116a && !zM2116a2) {
            List<C1268b> list = this.f2418t;
            Iterator<T> it2 = this.f2419u.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    z = false;
                    break;
                }
                C1269c c1269c2 = (C1269c) it2.next();
                Iterator<T> it3 = list.iterator();
                while (true) {
                    if (!it3.hasNext()) {
                        z4 = true;
                        break;
                    }
                    C1268b c1268b = (C1268b) it3.next();
                    Iterator<C1268b> it4 = c1269c2.f2402a.iterator();
                    while (true) {
                        if (!it4.hasNext()) {
                            z5 = false;
                            break;
                        }
                        if (c1268b.equals(it4.next())) {
                            z5 = true;
                            break;
                        }
                    }
                    if (!z5) {
                        z4 = false;
                        break;
                    }
                }
                if (z4) {
                    z = true;
                    break;
                }
            }
            if (!z) {
                this.f2419u.add(i2, new C1269c(this.f2418t, i));
                List<C1268b> list2 = this.f2418t;
                Iterator<C1269c> it5 = this.f2419u.iterator();
                while (it5.hasNext()) {
                    C1269c next = it5.next();
                    if (next.f2402a.size() != list2.size()) {
                        Iterator<C1268b> it6 = next.f2402a.iterator();
                        while (true) {
                            if (!it6.hasNext()) {
                                z2 = true;
                                break;
                            }
                            C1268b next2 = it6.next();
                            Iterator<C1268b> it7 = list2.iterator();
                            while (true) {
                                if (!it7.hasNext()) {
                                    z3 = false;
                                    break;
                                }
                                if (next2.equals(it7.next())) {
                                    z3 = true;
                                    break;
                                }
                            }
                            if (!z3) {
                                z2 = false;
                                break;
                            }
                        }
                        if (z2) {
                            it5.remove();
                        }
                    }
                }
            }
        }
        if (z7) {
            List<C1268b> listM2123a = m2123a(false);
            if (listM2123a != null) {
                return listM2123a;
            }
            List<C1268b> listM2123a2 = m2123a(true);
            if (listM2123a2 != null) {
                return listM2123a2;
            }
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private List<C1268b> m2123a(boolean z) {
        List<C1268b> listM2122a;
        if (this.f2419u.size() > 25) {
            this.f2419u.clear();
            return null;
        }
        this.f2418t.clear();
        if (z) {
            Collections.reverse(this.f2419u);
        }
        try {
            listM2122a = m2122a(new ArrayList(), 0);
        } catch (C1358m unused) {
            listM2122a = null;
        }
        if (z) {
            Collections.reverse(this.f2419u);
        }
        return listM2122a;
    }

    /* JADX INFO: renamed from: a */
    private List<C1268b> m2122a(List<C1269c> list, int i) throws C1358m {
        boolean z;
        while (i < this.f2419u.size()) {
            C1269c c1269c = this.f2419u.get(i);
            this.f2418t.clear();
            Iterator<C1269c> it2 = list.iterator();
            while (it2.hasNext()) {
                this.f2418t.addAll(it2.next().f2402a);
            }
            this.f2418t.addAll(c1269c.f2402a);
            List<C1268b> list2 = this.f2418t;
            boolean z2 = false;
            for (int[] iArr : f2416r) {
                if (list2.size() <= iArr.length) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= list2.size()) {
                            z = true;
                            break;
                        }
                        if (list2.get(i2).f2400c.f2424a != iArr[i2]) {
                            z = false;
                            break;
                        }
                        i2++;
                    }
                    if (z) {
                        z2 = true;
                        break;
                    }
                }
            }
            if (z2) {
                if (m2133c()) {
                    return this.f2418t;
                }
                ArrayList arrayList = new ArrayList(list);
                arrayList.add(c1269c);
                try {
                    return m2122a(arrayList, i + 1);
                } catch (C1358m unused) {
                    continue;
                }
            }
            i++;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2128a(List<C1268b> list) {
        boolean z;
        for (int[] iArr : f2416r) {
            if (list.size() <= iArr.length) {
                int i = 0;
                while (true) {
                    if (i >= list.size()) {
                        z = true;
                        break;
                    }
                    if (list.get(i).f2400c.f2424a != iArr[i]) {
                        z = false;
                        break;
                    }
                    i++;
                }
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: a */
    private void m2124a(int i) {
        boolean zM2116a;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        int i2 = 0;
        boolean zM2116a2 = false;
        while (true) {
            if (i2 >= this.f2419u.size()) {
                zM2116a = false;
                break;
            }
            C1269c c1269c = this.f2419u.get(i2);
            if (c1269c.f2403b > i) {
                zM2116a = c1269c.m2116a(this.f2418t);
                break;
            } else {
                zM2116a2 = c1269c.m2116a(this.f2418t);
                i2++;
            }
        }
        if (zM2116a || zM2116a2) {
            return;
        }
        List<C1268b> list = this.f2418t;
        Iterator<T> it2 = this.f2419u.iterator();
        while (true) {
            if (!it2.hasNext()) {
                z = false;
                break;
            }
            C1269c c1269c2 = (C1269c) it2.next();
            Iterator<T> it3 = list.iterator();
            while (true) {
                if (!it3.hasNext()) {
                    z4 = true;
                    break;
                }
                C1268b c1268b = (C1268b) it3.next();
                Iterator<C1268b> it4 = c1269c2.f2402a.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        z5 = false;
                        break;
                    } else if (c1268b.equals(it4.next())) {
                        z5 = true;
                        break;
                    }
                }
                if (!z5) {
                    z4 = false;
                    break;
                }
            }
            if (z4) {
                z = true;
                break;
            }
        }
        if (z) {
            return;
        }
        this.f2419u.add(i2, new C1269c(this.f2418t, i));
        List<C1268b> list2 = this.f2418t;
        Iterator<C1269c> it5 = this.f2419u.iterator();
        while (it5.hasNext()) {
            C1269c next = it5.next();
            if (next.f2402a.size() != list2.size()) {
                Iterator<C1268b> it6 = next.f2402a.iterator();
                while (true) {
                    if (!it6.hasNext()) {
                        z2 = true;
                        break;
                    }
                    C1268b next2 = it6.next();
                    Iterator<C1268b> it7 = list2.iterator();
                    while (true) {
                        if (!it7.hasNext()) {
                            z3 = false;
                            break;
                        } else if (next2.equals(it7.next())) {
                            z3 = true;
                            break;
                        }
                    }
                    if (!z3) {
                        z2 = false;
                        break;
                    }
                }
                if (z2) {
                    it5.remove();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2125a(List<C1268b> list, List<C1269c> list2) {
        boolean z;
        boolean z2;
        Iterator<C1269c> it2 = list2.iterator();
        while (it2.hasNext()) {
            C1269c next = it2.next();
            if (next.f2402a.size() != list.size()) {
                Iterator<C1268b> it3 = next.f2402a.iterator();
                do {
                    z = false;
                    z2 = true;
                    if (!it3.hasNext()) {
                        z = true;
                        break;
                    }
                    C1268b next2 = it3.next();
                    Iterator<C1268b> it4 = list.iterator();
                    do {
                        if (!it4.hasNext()) {
                            z2 = false;
                            break;
                        }
                    } while (!next2.equals(it4.next()));
                } while (z2);
                if (z) {
                    it2.remove();
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2127a(Iterable<C1268b> iterable, Iterable<C1269c> iterable2) {
        boolean z;
        boolean z2;
        Iterator<C1269c> it2 = iterable2.iterator();
        do {
            z = false;
            if (!it2.hasNext()) {
                return false;
            }
            C1269c next = it2.next();
            Iterator<C1268b> it3 = iterable.iterator();
            do {
                if (!it3.hasNext()) {
                    z = true;
                    break;
                }
                C1268b next2 = it3.next();
                Iterator<C1268b> it4 = next.f2402a.iterator();
                while (true) {
                    if (!it4.hasNext()) {
                        z2 = false;
                        break;
                    }
                    if (next2.equals(it4.next())) {
                        z2 = true;
                        break;
                    }
                }
            } while (z2);
        } while (!z);
        return true;
    }

    /* JADX INFO: renamed from: b */
    private List<C1269c> m2130b() {
        return this.f2419u;
    }

    /* JADX INFO: renamed from: c */
    private boolean m2133c() {
        C1268b c1268b = this.f2418t.get(0);
        C1271b c1271b = c1268b.f2398a;
        C1271b c1271b2 = c1268b.f2399b;
        if (c1271b2 == null) {
            return false;
        }
        int i = c1271b2.f2423b;
        int i2 = 2;
        for (int i3 = 1; i3 < this.f2418t.size(); i3++) {
            C1268b c1268b2 = this.f2418t.get(i3);
            i += c1268b2.f2398a.f2423b;
            i2++;
            C1271b c1271b3 = c1268b2.f2399b;
            if (c1271b3 != null) {
                i += c1271b3.f2423b;
                i2++;
            }
        }
        return ((i2 + (-4)) * UiMessage.CommandToUi.Command_Type.SET_EDIT_INPUT_TYPE_VALUE) + (i % UiMessage.CommandToUi.Command_Type.SET_EDIT_INPUT_TYPE_VALUE) == c1271b.f2422a;
    }

    /* JADX INFO: renamed from: a */
    private static int m2117a(C1190a c1190a, int i) {
        if (c1190a.m1755a(i)) {
            return c1190a.m1760c(c1190a.m1762d(i));
        }
        return c1190a.m1762d(c1190a.m1760c(i));
    }

    /* JADX INFO: renamed from: a */
    private C1268b m2118a(C1190a c1190a, List<C1268b> list, int i) throws C1358m {
        int i2;
        C1271b c1271bM2119a;
        int i3 = 2;
        boolean z = list.size() % 2 == 0;
        if (this.f2421w) {
            z = !z;
        }
        int iM1762d = -1;
        boolean z2 = true;
        while (true) {
            int[] iArr = this.f2350a;
            iArr[0] = 0;
            iArr[1] = 0;
            iArr[i3] = 0;
            iArr[3] = 0;
            int i4 = c1190a.f2065b;
            if (iM1762d < 0) {
                i2 = list.isEmpty() ? 0 : list.get(list.size() - 1).f2400c.f2425b[1];
            } else {
                i2 = iM1762d;
            }
            boolean z3 = list.size() % i3 != 0;
            if (this.f2421w) {
                z3 = !z3;
            }
            boolean z4 = false;
            while (i2 < i4) {
                z4 = !c1190a.m1755a(i2);
                if (!z4) {
                    break;
                }
                i2++;
            }
            int i5 = i2;
            int i6 = 0;
            while (true) {
                if (i2 < i4) {
                    if (c1190a.m1755a(i2) != z4) {
                        iArr[i6] = iArr[i6] + 1;
                    } else {
                        if (i6 == 3) {
                            if (z3) {
                                m2132b(iArr);
                            }
                            if (m2041a(iArr)) {
                                break;
                            }
                            if (z3) {
                                m2132b(iArr);
                            }
                            i5 += iArr[0] + iArr[1];
                            iArr[0] = iArr[2];
                            iArr[1] = iArr[3];
                            iArr[2] = 0;
                            iArr[3] = 0;
                            i6--;
                        } else {
                            i6++;
                        }
                        iArr[i6] = 1;
                        z4 = !z4;
                    }
                    i2++;
                } else {
                    throw C1358m.m2611a();
                }
            }
            this.f2420v[0] = i5;
            this.f2420v[1] = i2;
            C1272c c1272cM2120a = m2120a(c1190a, i, z);
            if (c1272cM2120a == null) {
                int i7 = this.f2420v[0];
                if (c1190a.m1755a(i7)) {
                    iM1762d = c1190a.m1760c(c1190a.m1762d(i7));
                } else {
                    iM1762d = c1190a.m1762d(c1190a.m1760c(i7));
                }
            } else {
                z2 = false;
            }
            if (!z2) {
                C1271b c1271bM2119a2 = m2119a(c1190a, c1272cM2120a, z, true);
                if (!list.isEmpty()) {
                    if (list.get(list.size() - 1).f2399b == null) {
                        throw C1358m.m2611a();
                    }
                }
                try {
                    c1271bM2119a = m2119a(c1190a, c1272cM2120a, z, false);
                } catch (C1358m unused) {
                    c1271bM2119a = null;
                }
                return new C1268b(c1271bM2119a2, c1271bM2119a, c1272cM2120a);
            }
            i3 = 2;
        }
    }

    /* JADX INFO: renamed from: b */
    private static void m2132b(int[] iArr) {
        int length = iArr.length;
        for (int i = 0; i < length / 2; i++) {
            int i2 = iArr[i];
            int i3 = (length - i) - 1;
            iArr[i] = iArr[i3];
            iArr[i3] = i2;
        }
    }

    /* JADX INFO: renamed from: a */
    private C1272c m2120a(C1190a c1190a, int i, boolean z) {
        int i2;
        int iM1762d;
        int i3;
        if (z) {
            int i4 = this.f2420v[0] - 1;
            while (i4 >= 0 && !c1190a.m1755a(i4)) {
                i4--;
            }
            i2 = i4 + 1;
            i3 = this.f2420v[0] - i2;
            iM1762d = this.f2420v[1];
        } else {
            i2 = this.f2420v[0];
            iM1762d = c1190a.m1762d(this.f2420v[1] + 1);
            i3 = iM1762d - this.f2420v[1];
        }
        int i5 = i2;
        int i6 = iM1762d;
        int[] iArr = this.f2350a;
        System.arraycopy(iArr, 0, iArr, 1, iArr.length - 1);
        iArr[0] = i3;
        try {
            return new C1272c(m2039a(iArr, f2408j), new int[]{i5, i6}, i5, i6, i);
        } catch (C1358m unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private static C1363r m2129b(List<C1268b> list) throws C1358m, C1303h {
        AbstractC1258j c1249a;
        int size = (list.size() << 1) - 1;
        if (list.get(list.size() - 1).f2399b == null) {
            size--;
        }
        C1190a c1190a = new C1190a(size * 12);
        int i = list.get(0).f2399b.f2422a;
        int i2 = 0;
        for (int i3 = 11; i3 >= 0; i3--) {
            if (((1 << i3) & i) != 0) {
                c1190a.m1758b(i2);
            }
            i2++;
        }
        for (int i4 = 1; i4 < list.size(); i4++) {
            C1268b c1268b = list.get(i4);
            int i5 = c1268b.f2398a.f2422a;
            int i6 = i2;
            for (int i7 = 11; i7 >= 0; i7--) {
                if (((1 << i7) & i5) != 0) {
                    c1190a.m1758b(i6);
                }
                i6++;
            }
            if (c1268b.f2399b != null) {
                int i8 = c1268b.f2399b.f2422a;
                for (int i9 = 11; i9 >= 0; i9--) {
                    if (((1 << i9) & i8) != 0) {
                        c1190a.m1758b(i6);
                    }
                    i6++;
                }
            }
            i2 = i6;
        }
        if (c1190a.m1755a(1)) {
            c1249a = new C1255g(c1190a);
        } else if (!c1190a.m1755a(2)) {
            c1249a = new C1259k(c1190a);
        } else {
            switch (C1267s.m2089a(c1190a, 1, 4)) {
                case 4:
                    c1249a = new C1249a(c1190a);
                    break;
                case 5:
                    c1249a = new C1250b(c1190a);
                    break;
                default:
                    switch (C1267s.m2089a(c1190a, 1, 5)) {
                        case 12:
                            c1249a = new C1251c(c1190a);
                            break;
                        case 13:
                            c1249a = new C1252d(c1190a);
                            break;
                        default:
                            switch (C1267s.m2089a(c1190a, 1, 7)) {
                                case 56:
                                    c1249a = new C1253e(c1190a, "310", "11");
                                    break;
                                case 57:
                                    c1249a = new C1253e(c1190a, "320", "11");
                                    break;
                                case 58:
                                    c1249a = new C1253e(c1190a, "310", "13");
                                    break;
                                case 59:
                                    c1249a = new C1253e(c1190a, "320", "13");
                                    break;
                                case 60:
                                    c1249a = new C1253e(c1190a, "310", "15");
                                    break;
                                case 61:
                                    c1249a = new C1253e(c1190a, "320", "15");
                                    break;
                                case 62:
                                    c1249a = new C1253e(c1190a, "310", "17");
                                    break;
                                case 63:
                                    c1249a = new C1253e(c1190a, "320", "17");
                                    break;
                                default:
                                    throw new IllegalStateException("unknown decoder: ".concat(String.valueOf(c1190a)));
                            }
                            break;
                    }
                    break;
            }
        }
        String strMo2053a = c1249a.mo2053a();
        C1365t[] c1365tArr = list.get(0).f2400c.f2426c;
        C1365t[] c1365tArr2 = list.get(list.size() - 1).f2400c.f2426c;
        return new C1363r(strMo2053a, null, new C1365t[]{c1365tArr[0], c1365tArr[1], c1365tArr2[0], c1365tArr2[1]}, EnumC1138a.RSS_EXPANDED);
    }

    /* JADX INFO: renamed from: b */
    private void m2131b(C1190a c1190a, List<C1268b> list, int i) throws C1358m {
        int[] iArr = this.f2350a;
        iArr[0] = 0;
        iArr[1] = 0;
        iArr[2] = 0;
        iArr[3] = 0;
        int i2 = c1190a.f2065b;
        if (i < 0) {
            i = list.isEmpty() ? 0 : list.get(list.size() - 1).f2400c.f2425b[1];
        }
        boolean z = list.size() % 2 != 0;
        if (this.f2421w) {
            z = !z;
        }
        boolean z2 = false;
        while (i < i2) {
            z2 = !c1190a.m1755a(i);
            if (!z2) {
                break;
            } else {
                i++;
            }
        }
        int i3 = i;
        int i4 = 0;
        while (i < i2) {
            if (c1190a.m1755a(i) != z2) {
                iArr[i4] = iArr[i4] + 1;
            } else {
                if (i4 == 3) {
                    if (z) {
                        m2132b(iArr);
                    }
                    if (m2041a(iArr)) {
                        this.f2420v[0] = i3;
                        this.f2420v[1] = i;
                        return;
                    }
                    if (z) {
                        m2132b(iArr);
                    }
                    i3 += iArr[0] + iArr[1];
                    iArr[0] = iArr[2];
                    iArr[1] = iArr[3];
                    iArr[2] = 0;
                    iArr[3] = 0;
                    i4--;
                } else {
                    i4++;
                }
                iArr[i4] = 1;
                z2 = !z2;
            }
            i++;
        }
        throw C1358m.m2611a();
    }

    /* JADX INFO: renamed from: a */
    private C1271b m2119a(C1190a c1190a, C1272c c1272c, boolean z, boolean z2) throws C1358m {
        boolean z3;
        boolean z4;
        boolean z5;
        boolean z6;
        int[] iArr = this.f2351b;
        for (int i = 0; i < iArr.length; i++) {
            iArr[i] = 0;
        }
        if (z2) {
            m2195b(c1190a, c1272c.f2425b[0], iArr);
        } else {
            m2193a(c1190a, c1272c.f2425b[1], iArr);
            int i2 = 0;
            for (int length = iArr.length - 1; i2 < length; length--) {
                int i3 = iArr[i2];
                iArr[i2] = iArr[length];
                iArr[length] = i3;
                i2++;
            }
        }
        float fM1766a = C1191a.m1766a(iArr) / 17.0f;
        float f = (c1272c.f2425b[1] - c1272c.f2425b[0]) / 15.0f;
        if (Math.abs(fM1766a - f) / f > 0.3f) {
            throw C1358m.m2611a();
        }
        int[] iArr2 = this.f2354e;
        int[] iArr3 = this.f2355f;
        float[] fArr = this.f2352c;
        float[] fArr2 = this.f2353d;
        for (int i4 = 0; i4 < iArr.length; i4++) {
            float f2 = (iArr[i4] * 1.0f) / fM1766a;
            int i5 = (int) (0.5f + f2);
            if (i5 <= 0) {
                if (f2 < 0.3f) {
                    throw C1358m.m2611a();
                }
                i5 = 1;
            } else if (i5 > 8) {
                if (f2 > 8.7f) {
                    throw C1358m.m2611a();
                }
                i5 = 8;
            }
            int i6 = i4 / 2;
            if ((i4 & 1) == 0) {
                iArr2[i6] = i5;
                fArr[i6] = f2 - i5;
            } else {
                iArr3[i6] = i5;
                fArr2[i6] = f2 - i5;
            }
        }
        int iM1766a = C1191a.m1766a(this.f2354e);
        int iM1766a2 = C1191a.m1766a(this.f2355f);
        if (iM1766a > 13) {
            z3 = false;
            z4 = true;
        } else {
            z3 = iM1766a < 4;
            z4 = false;
        }
        if (iM1766a2 > 13) {
            z5 = false;
            z6 = true;
        } else {
            z5 = iM1766a2 < 4;
            z6 = false;
        }
        int i7 = (iM1766a + iM1766a2) - 17;
        boolean z7 = (iM1766a & 1) == 1;
        boolean z8 = (iM1766a2 & 1) == 0;
        if (i7 == 1) {
            if (!z7) {
                if (!z8) {
                    throw C1358m.m2611a();
                }
                z6 = true;
            } else {
                if (z8) {
                    throw C1358m.m2611a();
                }
                z4 = true;
            }
        } else if (i7 != -1) {
            if (i7 != 0) {
                throw C1358m.m2611a();
            }
            if (z7) {
                if (!z8) {
                    throw C1358m.m2611a();
                }
                if (iM1766a < iM1766a2) {
                    z3 = true;
                    z6 = true;
                } else {
                    z4 = true;
                    z5 = true;
                }
            } else if (z8) {
                throw C1358m.m2611a();
            }
        } else if (!z7) {
            if (!z8) {
                throw C1358m.m2611a();
            }
            z5 = true;
        } else {
            if (z8) {
                throw C1358m.m2611a();
            }
            z3 = true;
        }
        if (z3) {
            if (z4) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2354e, this.f2352c);
        }
        if (z4) {
            m2043b(this.f2354e, this.f2352c);
        }
        if (z5) {
            if (z6) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2355f, this.f2352c);
        }
        if (z6) {
            m2043b(this.f2355f, this.f2353d);
        }
        int i8 = (((c1272c.f2424a * 4) + (z ? 0 : 2)) + (!z2 ? 1 : 0)) - 1;
        int i9 = 0;
        int i10 = 0;
        for (int length2 = iArr2.length - 1; length2 >= 0; length2--) {
            if (m2126a(c1272c, z, z2)) {
                i9 += iArr2[length2] * f2409k[i8][length2 * 2];
            }
            i10 += iArr2[length2];
        }
        int i11 = 0;
        for (int length3 = iArr3.length - 1; length3 >= 0; length3--) {
            if (m2126a(c1272c, z, z2)) {
                i11 += iArr3[length3] * f2409k[i8][(length3 * 2) + 1];
            }
        }
        int i12 = i9 + i11;
        if ((i10 & 1) != 0 || i10 > 13 || i10 < 4) {
            throw C1358m.m2611a();
        }
        int i13 = (13 - i10) / 2;
        int i14 = f2405g[i13];
        return new C1271b((C1275f.m2152a(iArr2, i14, true) * f2406h[i13]) + C1275f.m2152a(iArr3, 9 - i14, false) + f2407i[i13], i12);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m2126a(C1272c c1272c, boolean z, boolean z2) {
        return (c1272c.f2424a == 0 && z && z2) ? false : true;
    }

    /* JADX INFO: renamed from: d */
    private void m2134d() throws C1358m {
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        int iM1766a = C1191a.m1766a(this.f2354e);
        int iM1766a2 = C1191a.m1766a(this.f2355f);
        if (iM1766a > 13) {
            z = false;
            z2 = true;
        } else {
            z = iM1766a < 4;
            z2 = false;
        }
        if (iM1766a2 > 13) {
            z3 = false;
            z4 = true;
        } else {
            z3 = iM1766a2 < 4;
            z4 = false;
        }
        int i = (iM1766a + iM1766a2) - 17;
        boolean z5 = (iM1766a & 1) == 1;
        boolean z6 = (iM1766a2 & 1) == 0;
        if (i == 1) {
            if (z5) {
                if (z6) {
                    throw C1358m.m2611a();
                }
                z2 = true;
            } else {
                if (!z6) {
                    throw C1358m.m2611a();
                }
                z4 = true;
            }
        } else if (i == -1) {
            if (z5) {
                if (z6) {
                    throw C1358m.m2611a();
                }
                z = true;
            } else {
                if (!z6) {
                    throw C1358m.m2611a();
                }
                z3 = true;
            }
        } else {
            if (i != 0) {
                throw C1358m.m2611a();
            }
            if (z5) {
                if (!z6) {
                    throw C1358m.m2611a();
                }
                if (iM1766a < iM1766a2) {
                    z4 = true;
                    z = true;
                } else {
                    z3 = true;
                    z2 = true;
                }
            } else if (z6) {
                throw C1358m.m2611a();
            }
        }
        if (z) {
            if (z2) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2354e, this.f2352c);
        }
        if (z2) {
            m2043b(this.f2354e, this.f2352c);
        }
        if (z3) {
            if (z4) {
                throw C1358m.m2611a();
            }
            m2040a(this.f2355f, this.f2352c);
        }
        if (z4) {
            m2043b(this.f2355f, this.f2353d);
        }
    }
}

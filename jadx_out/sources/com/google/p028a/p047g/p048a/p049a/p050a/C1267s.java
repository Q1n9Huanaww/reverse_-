package com.google.p028a.p047g.p048a.p049a.p050a;

import android.support.v7.widget.helper.ItemTouchHelper;
import com.google.p028a.C1303h;
import com.google.p028a.C1358m;
import com.google.p028a.p035c.C1190a;
import mobi.oneway.export.C1898a;
import org.apache.commons.p092io.FilenameUtils;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: renamed from: com.google.a.g.a.a.a.s */
/* JADX INFO: compiled from: GeneralAppIdDecoder.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1267s {

    /* JADX INFO: renamed from: a */
    private final C1190a f2395a;

    /* JADX INFO: renamed from: b */
    private final C1261m f2396b = new C1261m();

    /* JADX INFO: renamed from: c */
    private final StringBuilder f2397c = new StringBuilder();

    C1267s(C1190a c1190a) {
        this.f2395a = c1190a;
    }

    /* JADX INFO: renamed from: a */
    final String m2105a(StringBuilder sb, int i) throws C1358m, C1303h {
        String str = null;
        while (true) {
            C1263o c1263oM2104a = m2104a(i, str);
            String strM2087a = C1266r.m2087a(c1263oM2104a.f2383a);
            if (strM2087a != null) {
                sb.append(strM2087a);
            }
            String strValueOf = c1263oM2104a.f2385c ? String.valueOf(c1263oM2104a.f2384b) : null;
            if (i == c1263oM2104a.f2389d) {
                return sb.toString();
            }
            i = c1263oM2104a.f2389d;
            str = strValueOf;
        }
    }

    /* JADX INFO: renamed from: a */
    private boolean m2091a(int i) {
        if (i + 7 > this.f2395a.f2065b) {
            return i + 4 <= this.f2395a.f2065b;
        }
        int i2 = i;
        while (true) {
            int i3 = i + 3;
            if (i2 < i3) {
                if (this.f2395a.m1755a(i2)) {
                    return true;
                }
                i2++;
            } else {
                return this.f2395a.m1755a(i3);
            }
        }
    }

    /* JADX INFO: renamed from: b */
    private C1264p m2093b(int i) throws C1303h {
        int i2 = i + 7;
        if (i2 > this.f2395a.f2065b) {
            int iM2103a = m2103a(i, 4);
            if (iM2103a == 0) {
                return new C1264p(this.f2395a.f2065b, 10, 10);
            }
            return new C1264p(this.f2395a.f2065b, iM2103a - 1, 10);
        }
        int iM2103a2 = m2103a(i, 7) - 8;
        return new C1264p(i2, iM2103a2 / 11, iM2103a2 % 11);
    }

    /* JADX INFO: renamed from: a */
    final int m2103a(int i, int i2) {
        return m2089a(this.f2395a, i, i2);
    }

    /* JADX INFO: renamed from: a */
    public static int m2089a(C1190a c1190a, int i, int i2) {
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (c1190a.m1755a(i + i4)) {
                i3 |= 1 << ((i2 - i4) - 1);
            }
        }
        return i3;
    }

    /* JADX INFO: renamed from: a */
    final C1263o m2104a(int i, String str) throws C1303h {
        C1260l c1260lM2092b;
        boolean z;
        C1263o c1263o;
        char c;
        C1262n c1262n;
        int iM2103a;
        int iM2103a2;
        this.f2397c.setLength(0);
        if (str != null) {
            this.f2397c.append(str);
        }
        this.f2396b.f2375a = i;
        do {
            int i2 = this.f2396b.f2375a;
            if (this.f2396b.f2376b == C1261m.a.f2378b) {
                while (true) {
                    int i3 = this.f2396b.f2375a;
                    if (!(i3 + 5 <= this.f2395a.f2065b && (((iM2103a = m2103a(i3, 5)) >= 5 && iM2103a < 16) || (i3 + 6 <= this.f2395a.f2065b && (iM2103a2 = m2103a(i3, 6)) >= 16 && iM2103a2 < 63)))) {
                        if (m2101h(this.f2396b.f2375a)) {
                            this.f2396b.m2072a(3);
                            this.f2396b.f2376b = C1261m.a.f2377a;
                        } else if (m2100g(this.f2396b.f2375a)) {
                            if (this.f2396b.f2375a + 5 < this.f2395a.f2065b) {
                                this.f2396b.m2072a(5);
                            } else {
                                this.f2396b.f2375a = this.f2395a.f2065b;
                            }
                            this.f2396b.f2376b = C1261m.a.f2379c;
                        }
                        c1260lM2092b = new C1260l();
                    } else {
                        int i4 = this.f2396b.f2375a;
                        int iM2103a3 = m2103a(i4, 5);
                        if (iM2103a3 == 15) {
                            c1262n = new C1262n(i4 + 5, '$');
                        } else if (iM2103a3 >= 5 && iM2103a3 < 15) {
                            c1262n = new C1262n(i4 + 5, (char) ((iM2103a3 + 48) - 5));
                        } else {
                            int iM2103a4 = m2103a(i4, 6);
                            if (iM2103a4 >= 32 && iM2103a4 < 58) {
                                c1262n = new C1262n(i4 + 6, (char) (iM2103a4 + 33));
                            } else {
                                switch (iM2103a4) {
                                    case 58:
                                        c = '*';
                                        break;
                                    case 59:
                                        c = ',';
                                        break;
                                    case 60:
                                        c = '-';
                                        break;
                                    case 61:
                                        c = FilenameUtils.EXTENSION_SEPARATOR;
                                        break;
                                    case 62:
                                        c = IOUtils.DIR_SEPARATOR_UNIX;
                                        break;
                                    default:
                                        throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(iM2103a4)));
                                }
                                c1262n = new C1262n(i4 + 6, c);
                            }
                        }
                        this.f2396b.f2375a = c1262n.f2389d;
                        if (c1262n.m2075a()) {
                            c1260lM2092b = new C1260l(new C1263o(this.f2396b.f2375a, this.f2397c.toString()), true);
                        } else {
                            this.f2397c.append(c1262n.f2382a);
                        }
                    }
                }
                z = c1260lM2092b.f2374b;
            } else {
                if (this.f2396b.f2376b == C1261m.a.f2379c) {
                    c1260lM2092b = m2094c();
                    z = c1260lM2092b.f2374b;
                } else {
                    c1260lM2092b = m2092b();
                    z = c1260lM2092b.f2374b;
                }
            }
            if ((i2 != this.f2396b.f2375a) || z) {
            }
            c1263o = c1260lM2092b.f2373a;
            if (c1263o == null && c1263o.f2385c) {
                return new C1263o(this.f2396b.f2375a, this.f2397c.toString(), c1263o.f2384b);
            }
            return new C1263o(this.f2396b.f2375a, this.f2397c.toString());
        } while (!z);
        c1263o = c1260lM2092b.f2373a;
        if (c1263o == null) {
        }
        return new C1263o(this.f2396b.f2375a, this.f2397c.toString());
    }

    /* JADX INFO: renamed from: a */
    private C1263o m2090a() throws C1303h {
        C1260l c1260lM2092b;
        boolean z;
        char c;
        C1262n c1262n;
        int iM2103a;
        int iM2103a2;
        do {
            int i = this.f2396b.f2375a;
            if (this.f2396b.f2376b == C1261m.a.f2378b) {
                while (true) {
                    int i2 = this.f2396b.f2375a;
                    if (!(i2 + 5 <= this.f2395a.f2065b && (((iM2103a = m2103a(i2, 5)) >= 5 && iM2103a < 16) || (i2 + 6 <= this.f2395a.f2065b && (iM2103a2 = m2103a(i2, 6)) >= 16 && iM2103a2 < 63)))) {
                        if (m2101h(this.f2396b.f2375a)) {
                            this.f2396b.m2072a(3);
                            this.f2396b.f2376b = C1261m.a.f2377a;
                        } else if (m2100g(this.f2396b.f2375a)) {
                            if (this.f2396b.f2375a + 5 < this.f2395a.f2065b) {
                                this.f2396b.m2072a(5);
                            } else {
                                this.f2396b.f2375a = this.f2395a.f2065b;
                            }
                            this.f2396b.f2376b = C1261m.a.f2379c;
                        }
                        c1260lM2092b = new C1260l();
                    } else {
                        int i3 = this.f2396b.f2375a;
                        int iM2103a3 = m2103a(i3, 5);
                        if (iM2103a3 == 15) {
                            c1262n = new C1262n(i3 + 5, '$');
                        } else if (iM2103a3 >= 5 && iM2103a3 < 15) {
                            c1262n = new C1262n(i3 + 5, (char) ((iM2103a3 + 48) - 5));
                        } else {
                            int iM2103a4 = m2103a(i3, 6);
                            if (iM2103a4 >= 32 && iM2103a4 < 58) {
                                c1262n = new C1262n(i3 + 6, (char) (iM2103a4 + 33));
                            } else {
                                switch (iM2103a4) {
                                    case 58:
                                        c = '*';
                                        break;
                                    case 59:
                                        c = ',';
                                        break;
                                    case 60:
                                        c = '-';
                                        break;
                                    case 61:
                                        c = FilenameUtils.EXTENSION_SEPARATOR;
                                        break;
                                    case 62:
                                        c = IOUtils.DIR_SEPARATOR_UNIX;
                                        break;
                                    default:
                                        throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(iM2103a4)));
                                }
                                c1262n = new C1262n(i3 + 6, c);
                            }
                        }
                        this.f2396b.f2375a = c1262n.f2389d;
                        if (c1262n.m2075a()) {
                            c1260lM2092b = new C1260l(new C1263o(this.f2396b.f2375a, this.f2397c.toString()), true);
                        } else {
                            this.f2397c.append(c1262n.f2382a);
                        }
                    }
                }
                z = c1260lM2092b.f2374b;
            } else {
                if (this.f2396b.f2376b == C1261m.a.f2379c) {
                    c1260lM2092b = m2094c();
                    z = c1260lM2092b.f2374b;
                } else {
                    c1260lM2092b = m2092b();
                    z = c1260lM2092b.f2374b;
                }
            }
            if ((i != this.f2396b.f2375a) || z) {
            }
            return c1260lM2092b.f2373a;
        } while (!z);
        return c1260lM2092b.f2373a;
    }

    /* JADX INFO: renamed from: b */
    private C1260l m2092b() throws C1303h {
        boolean zM1755a;
        C1264p c1264p;
        C1263o c1263o;
        while (true) {
            int i = this.f2396b.f2375a;
            boolean z = false;
            if (i + 7 <= this.f2395a.f2065b) {
                int i2 = i;
                while (true) {
                    int i3 = i + 3;
                    if (i2 < i3) {
                        if (this.f2395a.m1755a(i2)) {
                            zM1755a = true;
                            break;
                        }
                        i2++;
                    } else {
                        zM1755a = this.f2395a.m1755a(i3);
                        break;
                    }
                }
            } else {
                if (i + 4 <= this.f2395a.f2065b) {
                    zM1755a = true;
                    break;
                }
                zM1755a = false;
            }
            if (zM1755a) {
                int i4 = this.f2396b.f2375a;
                int i5 = i4 + 7;
                if (i5 > this.f2395a.f2065b) {
                    int iM2103a = m2103a(i4, 4);
                    if (iM2103a == 0) {
                        c1264p = new C1264p(this.f2395a.f2065b, 10, 10);
                    } else {
                        c1264p = new C1264p(this.f2395a.f2065b, iM2103a - 1, 10);
                    }
                } else {
                    int iM2103a2 = m2103a(i4, 7) - 8;
                    c1264p = new C1264p(i5, iM2103a2 / 11, iM2103a2 % 11);
                }
                this.f2396b.f2375a = c1264p.f2389d;
                if (c1264p.f2387a == 10) {
                    if (c1264p.m2084a()) {
                        c1263o = new C1263o(this.f2396b.f2375a, this.f2397c.toString());
                    } else {
                        c1263o = new C1263o(this.f2396b.f2375a, this.f2397c.toString(), c1264p.f2388b);
                    }
                    return new C1260l(c1263o, true);
                }
                this.f2397c.append(c1264p.f2387a);
                if (c1264p.m2084a()) {
                    return new C1260l(new C1263o(this.f2396b.f2375a, this.f2397c.toString()), true);
                }
                this.f2397c.append(c1264p.f2388b);
            } else {
                int i6 = this.f2396b.f2375a;
                if (i6 + 1 <= this.f2395a.f2065b) {
                    int i7 = 0;
                    while (true) {
                        if (i7 < 4) {
                            int i8 = i7 + i6;
                            if (i8 < this.f2395a.f2065b) {
                                if (this.f2395a.m1755a(i8)) {
                                    break;
                                }
                                i7++;
                            }
                        }
                        z = true;
                        break;
                    }
                }
                if (z) {
                    this.f2396b.f2376b = C1261m.a.f2378b;
                    this.f2396b.m2072a(4);
                }
                return new C1260l();
            }
        }
    }

    /* JADX INFO: renamed from: c */
    private C1260l m2094c() throws C1303h {
        char c;
        C1262n c1262n;
        int iM2103a;
        int iM2103a2;
        int iM2103a3;
        while (true) {
            int i = this.f2396b.f2375a;
            if (!(i + 5 <= this.f2395a.f2065b && (((iM2103a = m2103a(i, 5)) >= 5 && iM2103a < 16) || (i + 7 <= this.f2395a.f2065b && (((iM2103a2 = m2103a(i, 7)) >= 64 && iM2103a2 < 116) || (i + 8 <= this.f2395a.f2065b && (iM2103a3 = m2103a(i, 8)) >= 232 && iM2103a3 < 253)))))) {
                if (m2101h(this.f2396b.f2375a)) {
                    this.f2396b.m2072a(3);
                    this.f2396b.f2376b = C1261m.a.f2377a;
                } else if (m2100g(this.f2396b.f2375a)) {
                    if (this.f2396b.f2375a + 5 < this.f2395a.f2065b) {
                        this.f2396b.m2072a(5);
                    } else {
                        this.f2396b.f2375a = this.f2395a.f2065b;
                    }
                    this.f2396b.f2376b = C1261m.a.f2378b;
                }
                return new C1260l();
            }
            int i2 = this.f2396b.f2375a;
            int iM2103a4 = m2103a(i2, 5);
            if (iM2103a4 == 15) {
                c1262n = new C1262n(i2 + 5, '$');
            } else if (iM2103a4 >= 5 && iM2103a4 < 15) {
                c1262n = new C1262n(i2 + 5, (char) ((iM2103a4 + 48) - 5));
            } else {
                int iM2103a5 = m2103a(i2, 7);
                if (iM2103a5 >= 64 && iM2103a5 < 90) {
                    c1262n = new C1262n(i2 + 7, (char) (iM2103a5 + 1));
                } else if (iM2103a5 >= 90 && iM2103a5 < 116) {
                    c1262n = new C1262n(i2 + 7, (char) (iM2103a5 + 7));
                } else {
                    switch (m2103a(i2, 8)) {
                        case 232:
                            c = '!';
                            break;
                        case 233:
                            c = '\"';
                            break;
                        case 234:
                            c = '%';
                            break;
                        case 235:
                            c = '&';
                            break;
                        case 236:
                            c = '\'';
                            break;
                        case 237:
                            c = '(';
                            break;
                        case 238:
                            c = ')';
                            break;
                        case 239:
                            c = '*';
                            break;
                        case 240:
                            c = '+';
                            break;
                        case 241:
                            c = ',';
                            break;
                        case 242:
                            c = '-';
                            break;
                        case 243:
                            c = FilenameUtils.EXTENSION_SEPARATOR;
                            break;
                        case 244:
                            c = IOUtils.DIR_SEPARATOR_UNIX;
                            break;
                        case 245:
                            c = ':';
                            break;
                        case 246:
                            c = ';';
                            break;
                        case C1898a.f4816e /* 247 */:
                            c = '<';
                            break;
                        case 248:
                            c = '=';
                            break;
                        case 249:
                            c = '>';
                            break;
                        case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                            c = '?';
                            break;
                        case 251:
                            c = '_';
                            break;
                        case 252:
                            c = ' ';
                            break;
                        default:
                            throw C1303h.m2217a();
                    }
                    c1262n = new C1262n(i2 + 8, c);
                }
            }
            this.f2396b.f2375a = c1262n.f2389d;
            if (c1262n.m2075a()) {
                return new C1260l(new C1263o(this.f2396b.f2375a, this.f2397c.toString()), true);
            }
            this.f2397c.append(c1262n.f2382a);
        }
    }

    /* JADX INFO: renamed from: d */
    private C1260l m2096d() {
        char c;
        C1262n c1262n;
        int iM2103a;
        int iM2103a2;
        while (true) {
            int i = this.f2396b.f2375a;
            if (!(i + 5 <= this.f2395a.f2065b && (((iM2103a = m2103a(i, 5)) >= 5 && iM2103a < 16) || (i + 6 <= this.f2395a.f2065b && (iM2103a2 = m2103a(i, 6)) >= 16 && iM2103a2 < 63)))) {
                if (m2101h(this.f2396b.f2375a)) {
                    this.f2396b.m2072a(3);
                    this.f2396b.f2376b = C1261m.a.f2377a;
                } else if (m2100g(this.f2396b.f2375a)) {
                    if (this.f2396b.f2375a + 5 < this.f2395a.f2065b) {
                        this.f2396b.m2072a(5);
                    } else {
                        this.f2396b.f2375a = this.f2395a.f2065b;
                    }
                    this.f2396b.f2376b = C1261m.a.f2379c;
                }
                return new C1260l();
            }
            int i2 = this.f2396b.f2375a;
            int iM2103a3 = m2103a(i2, 5);
            if (iM2103a3 == 15) {
                c1262n = new C1262n(i2 + 5, '$');
            } else if (iM2103a3 >= 5 && iM2103a3 < 15) {
                c1262n = new C1262n(i2 + 5, (char) ((iM2103a3 + 48) - 5));
            } else {
                int iM2103a4 = m2103a(i2, 6);
                if (iM2103a4 >= 32 && iM2103a4 < 58) {
                    c1262n = new C1262n(i2 + 6, (char) (iM2103a4 + 33));
                } else {
                    switch (iM2103a4) {
                        case 58:
                            c = '*';
                            break;
                        case 59:
                            c = ',';
                            break;
                        case 60:
                            c = '-';
                            break;
                        case 61:
                            c = FilenameUtils.EXTENSION_SEPARATOR;
                            break;
                        case 62:
                            c = IOUtils.DIR_SEPARATOR_UNIX;
                            break;
                        default:
                            throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(iM2103a4)));
                    }
                    c1262n = new C1262n(i2 + 6, c);
                }
            }
            this.f2396b.f2375a = c1262n.f2389d;
            if (c1262n.m2075a()) {
                return new C1260l(new C1263o(this.f2396b.f2375a, this.f2397c.toString()), true);
            }
            this.f2397c.append(c1262n.f2382a);
        }
    }

    /* JADX INFO: renamed from: c */
    private boolean m2095c(int i) {
        int iM2103a;
        if (i + 5 > this.f2395a.f2065b) {
            return false;
        }
        int iM2103a2 = m2103a(i, 5);
        if (iM2103a2 >= 5 && iM2103a2 < 16) {
            return true;
        }
        if (i + 7 > this.f2395a.f2065b) {
            return false;
        }
        int iM2103a3 = m2103a(i, 7);
        if (iM2103a3 < 64 || iM2103a3 >= 116) {
            return i + 8 <= this.f2395a.f2065b && (iM2103a = m2103a(i, 8)) >= 232 && iM2103a < 253;
        }
        return true;
    }

    /* JADX INFO: renamed from: d */
    private C1262n m2097d(int i) throws C1303h {
        char c;
        int iM2103a = m2103a(i, 5);
        if (iM2103a == 15) {
            return new C1262n(i + 5, '$');
        }
        if (iM2103a >= 5 && iM2103a < 15) {
            return new C1262n(i + 5, (char) ((iM2103a + 48) - 5));
        }
        int iM2103a2 = m2103a(i, 7);
        if (iM2103a2 >= 64 && iM2103a2 < 90) {
            return new C1262n(i + 7, (char) (iM2103a2 + 1));
        }
        if (iM2103a2 >= 90 && iM2103a2 < 116) {
            return new C1262n(i + 7, (char) (iM2103a2 + 7));
        }
        switch (m2103a(i, 8)) {
            case 232:
                c = '!';
                break;
            case 233:
                c = '\"';
                break;
            case 234:
                c = '%';
                break;
            case 235:
                c = '&';
                break;
            case 236:
                c = '\'';
                break;
            case 237:
                c = '(';
                break;
            case 238:
                c = ')';
                break;
            case 239:
                c = '*';
                break;
            case 240:
                c = '+';
                break;
            case 241:
                c = ',';
                break;
            case 242:
                c = '-';
                break;
            case 243:
                c = FilenameUtils.EXTENSION_SEPARATOR;
                break;
            case 244:
                c = IOUtils.DIR_SEPARATOR_UNIX;
                break;
            case 245:
                c = ':';
                break;
            case 246:
                c = ';';
                break;
            case C1898a.f4816e /* 247 */:
                c = '<';
                break;
            case 248:
                c = '=';
                break;
            case 249:
                c = '>';
                break;
            case ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION /* 250 */:
                c = '?';
                break;
            case 251:
                c = '_';
                break;
            case 252:
                c = ' ';
                break;
            default:
                throw C1303h.m2217a();
        }
        return new C1262n(i + 8, c);
    }

    /* JADX INFO: renamed from: e */
    private boolean m2098e(int i) {
        int iM2103a;
        if (i + 5 > this.f2395a.f2065b) {
            return false;
        }
        int iM2103a2 = m2103a(i, 5);
        if (iM2103a2 < 5 || iM2103a2 >= 16) {
            return i + 6 <= this.f2395a.f2065b && (iM2103a = m2103a(i, 6)) >= 16 && iM2103a < 63;
        }
        return true;
    }

    /* JADX INFO: renamed from: f */
    private C1262n m2099f(int i) {
        char c;
        int iM2103a = m2103a(i, 5);
        if (iM2103a == 15) {
            return new C1262n(i + 5, '$');
        }
        if (iM2103a >= 5 && iM2103a < 15) {
            return new C1262n(i + 5, (char) ((iM2103a + 48) - 5));
        }
        int iM2103a2 = m2103a(i, 6);
        if (iM2103a2 >= 32 && iM2103a2 < 58) {
            return new C1262n(i + 6, (char) (iM2103a2 + 33));
        }
        switch (iM2103a2) {
            case 58:
                c = '*';
                break;
            case 59:
                c = ',';
                break;
            case 60:
                c = '-';
                break;
            case 61:
                c = FilenameUtils.EXTENSION_SEPARATOR;
                break;
            case 62:
                c = IOUtils.DIR_SEPARATOR_UNIX;
                break;
            default:
                throw new IllegalStateException("Decoding invalid alphanumeric value: ".concat(String.valueOf(iM2103a2)));
        }
        return new C1262n(i + 6, c);
    }

    /* JADX INFO: renamed from: g */
    private boolean m2100g(int i) {
        int i2;
        if (i + 1 > this.f2395a.f2065b) {
            return false;
        }
        for (int i3 = 0; i3 < 5 && (i2 = i3 + i) < this.f2395a.f2065b; i3++) {
            if (i3 == 2) {
                if (!this.f2395a.m1755a(i + 2)) {
                    return false;
                }
            } else if (this.f2395a.m1755a(i2)) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: renamed from: h */
    private boolean m2101h(int i) {
        int i2 = i + 3;
        if (i2 > this.f2395a.f2065b) {
            return false;
        }
        while (i < i2) {
            if (this.f2395a.m1755a(i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* JADX INFO: renamed from: i */
    private boolean m2102i(int i) {
        int i2;
        if (i + 1 > this.f2395a.f2065b) {
            return false;
        }
        for (int i3 = 0; i3 < 4 && (i2 = i3 + i) < this.f2395a.f2065b; i3++) {
            if (this.f2395a.m1755a(i2)) {
                return false;
            }
        }
        return true;
    }
}

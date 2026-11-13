package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.AbstractC1762az;
import com.umeng.commonsdk.proguard.AbstractC1773bj;
import com.umeng.commonsdk.proguard.AbstractC1774bk;
import com.umeng.commonsdk.proguard.C1743ag;
import com.umeng.commonsdk.proguard.C1749am;
import com.umeng.commonsdk.proguard.C1750an;
import com.umeng.commonsdk.proguard.C1751ao;
import com.umeng.commonsdk.proguard.C1752ap;
import com.umeng.commonsdk.proguard.C1754ar;
import com.umeng.commonsdk.proguard.C1756at;
import com.umeng.commonsdk.proguard.C1757au;
import com.umeng.commonsdk.proguard.C1758av;
import com.umeng.commonsdk.proguard.C1759aw;
import com.umeng.commonsdk.proguard.C1764ba;
import com.umeng.commonsdk.proguard.C1766bc;
import com.umeng.commonsdk.proguard.C1768be;
import com.umeng.commonsdk.proguard.C1769bf;
import com.umeng.commonsdk.proguard.C1770bg;
import com.umeng.commonsdk.proguard.C1775bl;
import com.umeng.commonsdk.proguard.InterfaceC1737aa;
import com.umeng.commonsdk.proguard.InterfaceC1744ah;
import com.umeng.commonsdk.proguard.InterfaceC1771bh;
import com.umeng.commonsdk.proguard.InterfaceC1772bi;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c */
/* JADX INFO: compiled from: IdTracking.java */
/* JADX INFO: loaded from: classes.dex */
public class C1854c implements InterfaceC1737aa<C1854c, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d */
    public static final Map<e, C1749am> f4676d;

    /* JADX INFO: renamed from: e */
    private static final long f4677e = -5764118265293965743L;

    /* JADX INFO: renamed from: f */
    private static final C1768be f4678f = new C1768be("IdTracking");

    /* JADX INFO: renamed from: g */
    private static final C1757au f4679g = new C1757au("snapshots", C1770bg.f4123k, 1);

    /* JADX INFO: renamed from: h */
    private static final C1757au f4680h = new C1757au("journals", C1770bg.f4125m, 2);

    /* JADX INFO: renamed from: i */
    private static final C1757au f4681i = new C1757au("checksum", (byte) 11, 3);

    /* JADX INFO: renamed from: j */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4682j = new HashMap();

    /* JADX INFO: renamed from: a */
    public Map<String, C1853b> f4683a;

    /* JADX INFO: renamed from: b */
    public List<C1852a> f4684b;

    /* JADX INFO: renamed from: c */
    public String f4685c;

    /* JADX INFO: renamed from: k */
    private e[] f4686k;

    static {
        f4682j.put(AbstractC1773bj.class, new b());
        f4682j.put(AbstractC1774bk.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.SNAPSHOTS, new C1749am("snapshots", (byte) 1, new C1752ap(C1770bg.f4123k, new C1750an((byte) 11), new C1754ar((byte) 12, C1853b.class))));
        enumMap.put(e.JOURNALS, new C1749am("journals", (byte) 2, new C1751ao(C1770bg.f4125m, new C1754ar((byte) 12, C1852a.class))));
        enumMap.put(e.CHECKSUM, new C1749am("checksum", (byte) 2, new C1750an((byte) 11)));
        f4676d = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1854c.class, f4676d);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c$e */
    /* JADX INFO: compiled from: IdTracking.java */
    public enum e implements InterfaceC1744ah {
        SNAPSHOTS(1, "snapshots"),
        JOURNALS(2, "journals"),
        CHECKSUM(3, "checksum");


        /* JADX INFO: renamed from: d */
        private static final Map<String, e> f4690d = new HashMap();

        /* JADX INFO: renamed from: e */
        private final short f4692e;

        /* JADX INFO: renamed from: f */
        private final String f4693f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4690d.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4620a(int i) {
            switch (i) {
                case 1:
                    return SNAPSHOTS;
                case 2:
                    return JOURNALS;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static e m4622b(int i) {
            e eVarM4620a = m4620a(i);
            if (eVarM4620a != null) {
                return eVarM4620a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4621a(String str) {
            return f4690d.get(str);
        }

        e(short s, String str) {
            this.f4692e = s;
            this.f4693f = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4692e;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4693f;
        }
    }

    public C1854c() {
        this.f4686k = new e[]{e.JOURNALS, e.CHECKSUM};
    }

    public C1854c(Map<String, C1853b> map) {
        this();
        this.f4683a = map;
    }

    public C1854c(C1854c c1854c) {
        this.f4686k = new e[]{e.JOURNALS, e.CHECKSUM};
        if (c1854c.m4604e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, C1853b> entry : c1854c.f4683a.entrySet()) {
                map.put(entry.getKey(), new C1853b(entry.getValue()));
            }
            this.f4683a = map;
        }
        if (c1854c.m4609j()) {
            ArrayList arrayList = new ArrayList();
            Iterator<C1852a> it2 = c1854c.f4684b.iterator();
            while (it2.hasNext()) {
                arrayList.add(new C1852a(it2.next()));
            }
            this.f4684b = arrayList;
        }
        if (c1854c.m4612m()) {
            this.f4685c = c1854c.f4685c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1854c deepCopy() {
        return new C1854c(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4683a = null;
        this.f4684b = null;
        this.f4685c = null;
    }

    /* JADX INFO: renamed from: b */
    public int m4599b() {
        if (this.f4683a == null) {
            return 0;
        }
        return this.f4683a.size();
    }

    /* JADX INFO: renamed from: a */
    public void m4597a(String str, C1853b c1853b) {
        if (this.f4683a == null) {
            this.f4683a = new HashMap();
        }
        this.f4683a.put(str, c1853b);
    }

    /* JADX INFO: renamed from: c */
    public Map<String, C1853b> m4601c() {
        return this.f4683a;
    }

    /* JADX INFO: renamed from: a */
    public C1854c m4595a(Map<String, C1853b> map) {
        this.f4683a = map;
        return this;
    }

    /* JADX INFO: renamed from: d */
    public void m4603d() {
        this.f4683a = null;
    }

    /* JADX INFO: renamed from: e */
    public boolean m4604e() {
        return this.f4683a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4598a(boolean z) {
        if (z) {
            return;
        }
        this.f4683a = null;
    }

    /* JADX INFO: renamed from: f */
    public int m4605f() {
        if (this.f4684b == null) {
            return 0;
        }
        return this.f4684b.size();
    }

    /* JADX INFO: renamed from: g */
    public Iterator<C1852a> m4606g() {
        if (this.f4684b == null) {
            return null;
        }
        return this.f4684b.iterator();
    }

    /* JADX INFO: renamed from: a */
    public void m4596a(C1852a c1852a) {
        if (this.f4684b == null) {
            this.f4684b = new ArrayList();
        }
        this.f4684b.add(c1852a);
    }

    /* JADX INFO: renamed from: h */
    public List<C1852a> m4607h() {
        return this.f4684b;
    }

    /* JADX INFO: renamed from: a */
    public C1854c m4594a(List<C1852a> list) {
        this.f4684b = list;
        return this;
    }

    /* JADX INFO: renamed from: i */
    public void m4608i() {
        this.f4684b = null;
    }

    /* JADX INFO: renamed from: j */
    public boolean m4609j() {
        return this.f4684b != null;
    }

    /* JADX INFO: renamed from: b */
    public void m4600b(boolean z) {
        if (z) {
            return;
        }
        this.f4684b = null;
    }

    /* JADX INFO: renamed from: k */
    public String m4610k() {
        return this.f4685c;
    }

    /* JADX INFO: renamed from: a */
    public C1854c m4593a(String str) {
        this.f4685c = str;
        return this;
    }

    /* JADX INFO: renamed from: l */
    public void m4611l() {
        this.f4685c = null;
    }

    /* JADX INFO: renamed from: m */
    public boolean m4612m() {
        return this.f4685c != null;
    }

    /* JADX INFO: renamed from: c */
    public void m4602c(boolean z) {
        if (z) {
            return;
        }
        this.f4685c = null;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4620a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4682j.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4682j.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdTracking(");
        sb.append("snapshots:");
        if (this.f4683a == null) {
            sb.append("null");
        } else {
            sb.append(this.f4683a);
        }
        if (m4609j()) {
            sb.append(", ");
            sb.append("journals:");
            if (this.f4684b == null) {
                sb.append("null");
            } else {
                sb.append(this.f4684b);
            }
        }
        if (m4612m()) {
            sb.append(", ");
            sb.append("checksum:");
            if (this.f4685c == null) {
                sb.append("null");
            } else {
                sb.append(this.f4685c);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: n */
    public void m4613n() throws C1743ag {
        if (this.f4683a == null) {
            throw new C1764ba("Required field 'snapshots' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4586a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4585a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c$b */
    /* JADX INFO: compiled from: IdTracking.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c$a */
    /* JADX INFO: compiled from: IdTracking.java */
    private static class a extends AbstractC1773bj<C1854c> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1854c c1854c) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    int i = 0;
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 13) {
                                C1759aw c1759awMo3880n = abstractC1762az.mo3880n();
                                c1854c.f4683a = new HashMap(c1759awMo3880n.f4089c * 2);
                                while (i < c1759awMo3880n.f4089c) {
                                    String strMo3892z = abstractC1762az.mo3892z();
                                    C1853b c1853b = new C1853b();
                                    c1853b.read(abstractC1762az);
                                    c1854c.f4683a.put(strMo3892z, c1853b);
                                    i++;
                                }
                                abstractC1762az.mo3881o();
                                c1854c.m4598a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 15) {
                                C1758av c1758avMo3882p = abstractC1762az.mo3882p();
                                c1854c.f4684b = new ArrayList(c1758avMo3882p.f4086b);
                                while (i < c1758avMo3882p.f4086b) {
                                    C1852a c1852a = new C1852a();
                                    c1852a.read(abstractC1762az);
                                    c1854c.f4684b.add(c1852a);
                                    i++;
                                }
                                abstractC1762az.mo3883q();
                                c1854c.m4600b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1854c.f4685c = abstractC1762az.mo3892z();
                                c1854c.m4602c(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        default:
                            C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            break;
                    }
                    abstractC1762az.mo3879m();
                } else {
                    abstractC1762az.mo3877k();
                    c1854c.m4613n();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1854c c1854c) throws C1743ag {
            c1854c.m4613n();
            abstractC1762az.mo3860a(C1854c.f4678f);
            if (c1854c.f4683a != null) {
                abstractC1762az.mo3855a(C1854c.f4679g);
                abstractC1762az.mo3857a(new C1759aw((byte) 11, (byte) 12, c1854c.f4683a.size()));
                for (Map.Entry<String, C1853b> entry : c1854c.f4683a.entrySet()) {
                    abstractC1762az.mo3861a(entry.getKey());
                    entry.getValue().write(abstractC1762az);
                }
                abstractC1762az.mo3871e();
                abstractC1762az.mo3867c();
            }
            if (c1854c.f4684b != null && c1854c.m4609j()) {
                abstractC1762az.mo3855a(C1854c.f4680h);
                abstractC1762az.mo3856a(new C1758av((byte) 12, c1854c.f4684b.size()));
                Iterator<C1852a> it2 = c1854c.f4684b.iterator();
                while (it2.hasNext()) {
                    it2.next().write(abstractC1762az);
                }
                abstractC1762az.mo3872f();
                abstractC1762az.mo3867c();
            }
            if (c1854c.f4685c != null && c1854c.m4612m()) {
                abstractC1762az.mo3855a(C1854c.f4681i);
                abstractC1762az.mo3861a(c1854c.f4685c);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c$d */
    /* JADX INFO: compiled from: IdTracking.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.c$c */
    /* JADX INFO: compiled from: IdTracking.java */
    private static class c extends AbstractC1774bk<C1854c> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1854c c1854c) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3853a(c1854c.f4683a.size());
            for (Map.Entry<String, C1853b> entry : c1854c.f4683a.entrySet()) {
                c1769bf.mo3861a(entry.getKey());
                entry.getValue().write(c1769bf);
            }
            BitSet bitSet = new BitSet();
            if (c1854c.m4609j()) {
                bitSet.set(0);
            }
            if (c1854c.m4612m()) {
                bitSet.set(1);
            }
            c1769bf.m3928a(bitSet, 2);
            if (c1854c.m4609j()) {
                c1769bf.mo3853a(c1854c.f4684b.size());
                Iterator<C1852a> it2 = c1854c.f4684b.iterator();
                while (it2.hasNext()) {
                    it2.next().write(c1769bf);
                }
            }
            if (c1854c.m4612m()) {
                c1769bf.mo3861a(c1854c.f4685c);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1854c c1854c) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            C1759aw c1759aw = new C1759aw((byte) 11, (byte) 12, c1769bf.mo3889w());
            c1854c.f4683a = new HashMap(c1759aw.f4089c * 2);
            for (int i = 0; i < c1759aw.f4089c; i++) {
                String strZ = c1769bf.mo3892z();
                C1853b c1853b = new C1853b();
                c1853b.read(c1769bf);
                c1854c.f4683a.put(strZ, c1853b);
            }
            c1854c.m4598a(true);
            BitSet bitSetM3929b = c1769bf.m3929b(2);
            if (bitSetM3929b.get(0)) {
                C1758av c1758av = new C1758av((byte) 12, c1769bf.mo3889w());
                c1854c.f4684b = new ArrayList(c1758av.f4086b);
                for (int i2 = 0; i2 < c1758av.f4086b; i2++) {
                    C1852a c1852a = new C1852a();
                    c1852a.read(c1769bf);
                    c1854c.f4684b.add(c1852a);
                }
                c1854c.m4600b(true);
            }
            if (bitSetM3929b.get(1)) {
                c1854c.f4685c = c1769bf.mo3892z();
                c1854c.m4602c(true);
            }
        }
    }
}

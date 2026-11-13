package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.AbstractC1762az;
import com.umeng.commonsdk.proguard.AbstractC1773bj;
import com.umeng.commonsdk.proguard.AbstractC1774bk;
import com.umeng.commonsdk.proguard.C1743ag;
import com.umeng.commonsdk.proguard.C1749am;
import com.umeng.commonsdk.proguard.C1750an;
import com.umeng.commonsdk.proguard.C1752ap;
import com.umeng.commonsdk.proguard.C1754ar;
import com.umeng.commonsdk.proguard.C1756at;
import com.umeng.commonsdk.proguard.C1757au;
import com.umeng.commonsdk.proguard.C1759aw;
import com.umeng.commonsdk.proguard.C1764ba;
import com.umeng.commonsdk.proguard.C1766bc;
import com.umeng.commonsdk.proguard.C1768be;
import com.umeng.commonsdk.proguard.C1769bf;
import com.umeng.commonsdk.proguard.C1770bg;
import com.umeng.commonsdk.proguard.C1775bl;
import com.umeng.commonsdk.proguard.C1801x;
import com.umeng.commonsdk.proguard.InterfaceC1737aa;
import com.umeng.commonsdk.proguard.InterfaceC1744ah;
import com.umeng.commonsdk.proguard.InterfaceC1771bh;
import com.umeng.commonsdk.proguard.InterfaceC1772bi;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d */
/* JADX INFO: compiled from: Imprint.java */
/* JADX INFO: loaded from: classes.dex */
public class C1855d implements InterfaceC1737aa<C1855d, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d */
    public static final Map<e, C1749am> f4694d;

    /* JADX INFO: renamed from: e */
    private static final long f4695e = 2846460275012375038L;

    /* JADX INFO: renamed from: f */
    private static final C1768be f4696f = new C1768be("Imprint");

    /* JADX INFO: renamed from: g */
    private static final C1757au f4697g = new C1757au("property", C1770bg.f4123k, 1);

    /* JADX INFO: renamed from: h */
    private static final C1757au f4698h = new C1757au("version", (byte) 8, 2);

    /* JADX INFO: renamed from: i */
    private static final C1757au f4699i = new C1757au("checksum", (byte) 11, 3);

    /* JADX INFO: renamed from: j */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4700j = new HashMap();

    /* JADX INFO: renamed from: k */
    private static final int f4701k = 0;

    /* JADX INFO: renamed from: a */
    public Map<String, C1856e> f4702a;

    /* JADX INFO: renamed from: b */
    public int f4703b;

    /* JADX INFO: renamed from: c */
    public String f4704c;

    /* JADX INFO: renamed from: l */
    private byte f4705l;

    static {
        f4700j.put(AbstractC1773bj.class, new b());
        f4700j.put(AbstractC1774bk.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.PROPERTY, new C1749am("property", (byte) 1, new C1752ap(C1770bg.f4123k, new C1750an((byte) 11), new C1754ar((byte) 12, C1856e.class))));
        enumMap.put(e.VERSION, new C1749am("version", (byte) 1, new C1750an((byte) 8)));
        enumMap.put(e.CHECKSUM, new C1749am("checksum", (byte) 1, new C1750an((byte) 11)));
        f4694d = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1855d.class, f4694d);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$e */
    /* JADX INFO: compiled from: Imprint.java */
    public enum e implements InterfaceC1744ah {
        PROPERTY(1, "property"),
        VERSION(2, "version"),
        CHECKSUM(3, "checksum");


        /* JADX INFO: renamed from: d */
        private static final Map<String, e> f4709d = new HashMap();

        /* JADX INFO: renamed from: e */
        private final short f4711e;

        /* JADX INFO: renamed from: f */
        private final String f4712f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4709d.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4655a(int i) {
            switch (i) {
                case 1:
                    return PROPERTY;
                case 2:
                    return VERSION;
                case 3:
                    return CHECKSUM;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static e m4657b(int i) {
            e eVarM4655a = m4655a(i);
            if (eVarM4655a != null) {
                return eVarM4655a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4656a(String str) {
            return f4709d.get(str);
        }

        e(short s, String str) {
            this.f4711e = s;
            this.f4712f = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4711e;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4712f;
        }
    }

    public C1855d() {
        this.f4705l = (byte) 0;
    }

    public C1855d(Map<String, C1856e> map, int i, String str) {
        this();
        this.f4702a = map;
        this.f4703b = i;
        m4637b(true);
        this.f4704c = str;
    }

    public C1855d(C1855d c1855d) {
        this.f4705l = (byte) 0;
        this.f4705l = c1855d.f4705l;
        if (c1855d.m4641e()) {
            HashMap map = new HashMap();
            for (Map.Entry<String, C1856e> entry : c1855d.f4702a.entrySet()) {
                map.put(entry.getKey(), new C1856e(entry.getValue()));
            }
            this.f4702a = map;
        }
        this.f4703b = c1855d.f4703b;
        if (c1855d.m4647k()) {
            this.f4704c = c1855d.f4704c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1855d deepCopy() {
        return new C1855d(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4702a = null;
        m4637b(false);
        this.f4703b = 0;
        this.f4704c = null;
    }

    /* JADX INFO: renamed from: b */
    public int m4635b() {
        if (this.f4702a == null) {
            return 0;
        }
        return this.f4702a.size();
    }

    /* JADX INFO: renamed from: a */
    public void m4633a(String str, C1856e c1856e) {
        if (this.f4702a == null) {
            this.f4702a = new HashMap();
        }
        this.f4702a.put(str, c1856e);
    }

    /* JADX INFO: renamed from: c */
    public Map<String, C1856e> m4638c() {
        return this.f4702a;
    }

    /* JADX INFO: renamed from: a */
    public C1855d m4632a(Map<String, C1856e> map) {
        this.f4702a = map;
        return this;
    }

    /* JADX INFO: renamed from: d */
    public void m4640d() {
        this.f4702a = null;
    }

    /* JADX INFO: renamed from: e */
    public boolean m4641e() {
        return this.f4702a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4634a(boolean z) {
        if (z) {
            return;
        }
        this.f4702a = null;
    }

    /* JADX INFO: renamed from: f */
    public int m4642f() {
        return this.f4703b;
    }

    /* JADX INFO: renamed from: a */
    public C1855d m4630a(int i) {
        this.f4703b = i;
        m4637b(true);
        return this;
    }

    /* JADX INFO: renamed from: g */
    public void m4643g() {
        this.f4705l = C1801x.m4199b(this.f4705l, 0);
    }

    /* JADX INFO: renamed from: h */
    public boolean m4644h() {
        return C1801x.m4195a(this.f4705l, 0);
    }

    /* JADX INFO: renamed from: b */
    public void m4637b(boolean z) {
        this.f4705l = C1801x.m4187a(this.f4705l, 0, z);
    }

    /* JADX INFO: renamed from: i */
    public String m4645i() {
        return this.f4704c;
    }

    /* JADX INFO: renamed from: a */
    public C1855d m4631a(String str) {
        this.f4704c = str;
        return this;
    }

    /* JADX INFO: renamed from: j */
    public void m4646j() {
        this.f4704c = null;
    }

    /* JADX INFO: renamed from: k */
    public boolean m4647k() {
        return this.f4704c != null;
    }

    /* JADX INFO: renamed from: c */
    public void m4639c(boolean z) {
        if (z) {
            return;
        }
        this.f4704c = null;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4655a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4700j.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4700j.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Imprint(");
        sb.append("property:");
        if (this.f4702a == null) {
            sb.append("null");
        } else {
            sb.append(this.f4702a);
        }
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f4703b);
        sb.append(", ");
        sb.append("checksum:");
        if (this.f4704c == null) {
            sb.append("null");
        } else {
            sb.append(this.f4704c);
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: l */
    public void m4648l() throws C1743ag {
        if (this.f4702a == null) {
            throw new C1764ba("Required field 'property' was not present! Struct: " + toString());
        }
        if (this.f4704c == null) {
            throw new C1764ba("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4624a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4623a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f4705l = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$b */
    /* JADX INFO: compiled from: Imprint.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$a */
    /* JADX INFO: compiled from: Imprint.java */
    private static class a extends AbstractC1773bj<C1855d> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1855d c1855d) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 13) {
                                C1759aw c1759awMo3880n = abstractC1762az.mo3880n();
                                c1855d.f4702a = new HashMap(c1759awMo3880n.f4089c * 2);
                                for (int i = 0; i < c1759awMo3880n.f4089c; i++) {
                                    String strMo3892z = abstractC1762az.mo3892z();
                                    C1856e c1856e = new C1856e();
                                    c1856e.read(abstractC1762az);
                                    c1855d.f4702a.put(strMo3892z, c1856e);
                                }
                                abstractC1762az.mo3881o();
                                c1855d.m4634a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1855d.f4703b = abstractC1762az.mo3889w();
                                c1855d.m4637b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1855d.f4704c = abstractC1762az.mo3892z();
                                c1855d.m4639c(true);
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
                    if (!c1855d.m4644h()) {
                        throw new C1764ba("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                    c1855d.m4648l();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1855d c1855d) throws C1743ag {
            c1855d.m4648l();
            abstractC1762az.mo3860a(C1855d.f4696f);
            if (c1855d.f4702a != null) {
                abstractC1762az.mo3855a(C1855d.f4697g);
                abstractC1762az.mo3857a(new C1759aw((byte) 11, (byte) 12, c1855d.f4702a.size()));
                for (Map.Entry<String, C1856e> entry : c1855d.f4702a.entrySet()) {
                    abstractC1762az.mo3861a(entry.getKey());
                    entry.getValue().write(abstractC1762az);
                }
                abstractC1762az.mo3871e();
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3855a(C1855d.f4698h);
            abstractC1762az.mo3853a(c1855d.f4703b);
            abstractC1762az.mo3867c();
            if (c1855d.f4704c != null) {
                abstractC1762az.mo3855a(C1855d.f4699i);
                abstractC1762az.mo3861a(c1855d.f4704c);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$d */
    /* JADX INFO: compiled from: Imprint.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.d$c */
    /* JADX INFO: compiled from: Imprint.java */
    private static class c extends AbstractC1774bk<C1855d> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1855d c1855d) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3853a(c1855d.f4702a.size());
            for (Map.Entry<String, C1856e> entry : c1855d.f4702a.entrySet()) {
                c1769bf.mo3861a(entry.getKey());
                entry.getValue().write(c1769bf);
            }
            c1769bf.mo3853a(c1855d.f4703b);
            c1769bf.mo3861a(c1855d.f4704c);
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1855d c1855d) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            C1759aw c1759aw = new C1759aw((byte) 11, (byte) 12, c1769bf.mo3889w());
            c1855d.f4702a = new HashMap(c1759aw.f4089c * 2);
            for (int i = 0; i < c1759aw.f4089c; i++) {
                String strZ = c1769bf.mo3892z();
                C1856e c1856e = new C1856e();
                c1856e.read(c1769bf);
                c1855d.f4702a.put(strZ, c1856e);
            }
            c1855d.m4634a(true);
            c1855d.f4703b = c1769bf.mo3889w();
            c1855d.m4637b(true);
            c1855d.f4704c = c1769bf.mo3892z();
            c1855d.m4639c(true);
        }
    }
}

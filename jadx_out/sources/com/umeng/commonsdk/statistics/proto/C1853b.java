package com.umeng.commonsdk.statistics.proto;

import com.umeng.commonsdk.proguard.AbstractC1762az;
import com.umeng.commonsdk.proguard.AbstractC1773bj;
import com.umeng.commonsdk.proguard.AbstractC1774bk;
import com.umeng.commonsdk.proguard.C1743ag;
import com.umeng.commonsdk.proguard.C1749am;
import com.umeng.commonsdk.proguard.C1750an;
import com.umeng.commonsdk.proguard.C1756at;
import com.umeng.commonsdk.proguard.C1757au;
import com.umeng.commonsdk.proguard.C1764ba;
import com.umeng.commonsdk.proguard.C1766bc;
import com.umeng.commonsdk.proguard.C1768be;
import com.umeng.commonsdk.proguard.C1769bf;
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

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b */
/* JADX INFO: compiled from: IdSnapshot.java */
/* JADX INFO: loaded from: classes.dex */
public class C1853b implements InterfaceC1737aa<C1853b, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d */
    public static final Map<e, C1749am> f4656d;

    /* JADX INFO: renamed from: e */
    private static final long f4657e = -6496538196005191531L;

    /* JADX INFO: renamed from: f */
    private static final C1768be f4658f = new C1768be("IdSnapshot");

    /* JADX INFO: renamed from: g */
    private static final C1757au f4659g = new C1757au("identity", (byte) 11, 1);

    /* JADX INFO: renamed from: h */
    private static final C1757au f4660h = new C1757au("ts", (byte) 10, 2);

    /* JADX INFO: renamed from: i */
    private static final C1757au f4661i = new C1757au("version", (byte) 8, 3);

    /* JADX INFO: renamed from: j */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4662j = new HashMap();

    /* JADX INFO: renamed from: k */
    private static final int f4663k = 0;

    /* JADX INFO: renamed from: l */
    private static final int f4664l = 1;

    /* JADX INFO: renamed from: a */
    public String f4665a;

    /* JADX INFO: renamed from: b */
    public long f4666b;

    /* JADX INFO: renamed from: c */
    public int f4667c;

    /* JADX INFO: renamed from: m */
    private byte f4668m;

    static {
        f4662j.put(AbstractC1773bj.class, new b());
        f4662j.put(AbstractC1774bk.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.IDENTITY, new C1749am("identity", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.TS, new C1749am("ts", (byte) 1, new C1750an((byte) 10)));
        enumMap.put(e.VERSION, new C1749am("version", (byte) 1, new C1750an((byte) 8)));
        f4656d = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1853b.class, f4656d);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$e */
    /* JADX INFO: compiled from: IdSnapshot.java */
    public enum e implements InterfaceC1744ah {
        IDENTITY(1, "identity"),
        TS(2, "ts"),
        VERSION(3, "version");


        /* JADX INFO: renamed from: d */
        private static final Map<String, e> f4672d = new HashMap();

        /* JADX INFO: renamed from: e */
        private final short f4674e;

        /* JADX INFO: renamed from: f */
        private final String f4675f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4672d.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4582a(int i) {
            switch (i) {
                case 1:
                    return IDENTITY;
                case 2:
                    return TS;
                case 3:
                    return VERSION;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static e m4584b(int i) {
            e eVarM4582a = m4582a(i);
            if (eVarM4582a != null) {
                return eVarM4582a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4583a(String str) {
            return f4672d.get(str);
        }

        e(short s, String str) {
            this.f4674e = s;
            this.f4675f = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4674e;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4675f;
        }
    }

    public C1853b() {
        this.f4668m = (byte) 0;
    }

    public C1853b(String str, long j, int i) {
        this();
        this.f4665a = str;
        this.f4666b = j;
        m4565b(true);
        this.f4667c = i;
        m4567c(true);
    }

    public C1853b(C1853b c1853b) {
        this.f4668m = (byte) 0;
        this.f4668m = c1853b.f4668m;
        if (c1853b.m4568d()) {
            this.f4665a = c1853b.f4665a;
        }
        this.f4666b = c1853b.f4666b;
        this.f4667c = c1853b.f4667c;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1853b deepCopy() {
        return new C1853b(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4665a = null;
        m4565b(false);
        this.f4666b = 0L;
        m4567c(false);
        this.f4667c = 0;
    }

    /* JADX INFO: renamed from: b */
    public String m4564b() {
        return this.f4665a;
    }

    /* JADX INFO: renamed from: a */
    public C1853b m4561a(String str) {
        this.f4665a = str;
        return this;
    }

    /* JADX INFO: renamed from: c */
    public void m4566c() {
        this.f4665a = null;
    }

    /* JADX INFO: renamed from: d */
    public boolean m4568d() {
        return this.f4665a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4562a(boolean z) {
        if (z) {
            return;
        }
        this.f4665a = null;
    }

    /* JADX INFO: renamed from: e */
    public long m4569e() {
        return this.f4666b;
    }

    /* JADX INFO: renamed from: a */
    public C1853b m4560a(long j) {
        this.f4666b = j;
        m4565b(true);
        return this;
    }

    /* JADX INFO: renamed from: f */
    public void m4570f() {
        this.f4668m = C1801x.m4199b(this.f4668m, 0);
    }

    /* JADX INFO: renamed from: g */
    public boolean m4571g() {
        return C1801x.m4195a(this.f4668m, 0);
    }

    /* JADX INFO: renamed from: b */
    public void m4565b(boolean z) {
        this.f4668m = C1801x.m4187a(this.f4668m, 0, z);
    }

    /* JADX INFO: renamed from: h */
    public int m4572h() {
        return this.f4667c;
    }

    /* JADX INFO: renamed from: a */
    public C1853b m4559a(int i) {
        this.f4667c = i;
        m4567c(true);
        return this;
    }

    /* JADX INFO: renamed from: i */
    public void m4573i() {
        this.f4668m = C1801x.m4199b(this.f4668m, 1);
    }

    /* JADX INFO: renamed from: j */
    public boolean m4574j() {
        return C1801x.m4195a(this.f4668m, 1);
    }

    /* JADX INFO: renamed from: c */
    public void m4567c(boolean z) {
        this.f4668m = C1801x.m4187a(this.f4668m, 1, z);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4582a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4662j.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4662j.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdSnapshot(");
        sb.append("identity:");
        if (this.f4665a == null) {
            sb.append("null");
        } else {
            sb.append(this.f4665a);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f4666b);
        sb.append(", ");
        sb.append("version:");
        sb.append(this.f4667c);
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: k */
    public void m4575k() throws C1743ag {
        if (this.f4665a == null) {
            throw new C1764ba("Required field 'identity' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4553a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4552a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f4668m = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$b */
    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$a */
    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class a extends AbstractC1773bj<C1853b> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1853b c1853b) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1853b.f4665a = abstractC1762az.mo3892z();
                                c1853b.m4562a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 10) {
                                c1853b.f4666b = abstractC1762az.mo3890x();
                                c1853b.m4565b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1853b.f4667c = abstractC1762az.mo3889w();
                                c1853b.m4567c(true);
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
                    if (!c1853b.m4571g()) {
                        throw new C1764ba("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    if (!c1853b.m4574j()) {
                        throw new C1764ba("Required field 'version' was not found in serialized data! Struct: " + toString());
                    }
                    c1853b.m4575k();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1853b c1853b) throws C1743ag {
            c1853b.m4575k();
            abstractC1762az.mo3860a(C1853b.f4658f);
            if (c1853b.f4665a != null) {
                abstractC1762az.mo3855a(C1853b.f4659g);
                abstractC1762az.mo3861a(c1853b.f4665a);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3855a(C1853b.f4660h);
            abstractC1762az.mo3854a(c1853b.f4666b);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3855a(C1853b.f4661i);
            abstractC1762az.mo3853a(c1853b.f4667c);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$d */
    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.b$c */
    /* JADX INFO: compiled from: IdSnapshot.java */
    private static class c extends AbstractC1774bk<C1853b> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1853b c1853b) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3861a(c1853b.f4665a);
            c1769bf.mo3854a(c1853b.f4666b);
            c1769bf.mo3853a(c1853b.f4667c);
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1853b c1853b) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1853b.f4665a = c1769bf.mo3892z();
            c1853b.m4562a(true);
            c1853b.f4666b = c1769bf.mo3890x();
            c1853b.m4565b(true);
            c1853b.f4667c = c1769bf.mo3889w();
            c1853b.m4567c(true);
        }
    }
}

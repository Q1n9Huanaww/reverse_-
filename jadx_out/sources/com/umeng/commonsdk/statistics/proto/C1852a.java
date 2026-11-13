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
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a */
/* JADX INFO: compiled from: IdJournal.java */
/* JADX INFO: loaded from: classes.dex */
public class C1852a implements InterfaceC1737aa<C1852a, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: e */
    public static final Map<e, C1749am> f4633e;

    /* JADX INFO: renamed from: f */
    private static final long f4634f = 9132678615281394583L;

    /* JADX INFO: renamed from: g */
    private static final C1768be f4635g = new C1768be("IdJournal");

    /* JADX INFO: renamed from: h */
    private static final C1757au f4636h = new C1757au("domain", (byte) 11, 1);

    /* JADX INFO: renamed from: i */
    private static final C1757au f4637i = new C1757au("old_id", (byte) 11, 2);

    /* JADX INFO: renamed from: j */
    private static final C1757au f4638j = new C1757au("new_id", (byte) 11, 3);

    /* JADX INFO: renamed from: k */
    private static final C1757au f4639k = new C1757au("ts", (byte) 10, 4);

    /* JADX INFO: renamed from: l */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4640l = new HashMap();

    /* JADX INFO: renamed from: m */
    private static final int f4641m = 0;

    /* JADX INFO: renamed from: a */
    public String f4642a;

    /* JADX INFO: renamed from: b */
    public String f4643b;

    /* JADX INFO: renamed from: c */
    public String f4644c;

    /* JADX INFO: renamed from: d */
    public long f4645d;

    /* JADX INFO: renamed from: n */
    private byte f4646n;

    /* JADX INFO: renamed from: o */
    private e[] f4647o;

    static {
        f4640l.put(AbstractC1773bj.class, new b());
        f4640l.put(AbstractC1774bk.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.DOMAIN, new C1749am("domain", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.OLD_ID, new C1749am("old_id", (byte) 2, new C1750an((byte) 11)));
        enumMap.put(e.NEW_ID, new C1749am("new_id", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.TS, new C1749am("ts", (byte) 1, new C1750an((byte) 10)));
        f4633e = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1852a.class, f4633e);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$e */
    /* JADX INFO: compiled from: IdJournal.java */
    public enum e implements InterfaceC1744ah {
        DOMAIN(1, "domain"),
        OLD_ID(2, "old_id"),
        NEW_ID(3, "new_id"),
        TS(4, "ts");


        /* JADX INFO: renamed from: e */
        private static final Map<String, e> f4652e = new HashMap();

        /* JADX INFO: renamed from: f */
        private final short f4654f;

        /* JADX INFO: renamed from: g */
        private final String f4655g;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4652e.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4549a(int i) {
            switch (i) {
                case 1:
                    return DOMAIN;
                case 2:
                    return OLD_ID;
                case 3:
                    return NEW_ID;
                case 4:
                    return TS;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static e m4551b(int i) {
            e eVarM4549a = m4549a(i);
            if (eVarM4549a != null) {
                return eVarM4549a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4550a(String str) {
            return f4652e.get(str);
        }

        e(short s, String str) {
            this.f4654f = s;
            this.f4655g = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4654f;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4655g;
        }
    }

    public C1852a() {
        this.f4646n = (byte) 0;
        this.f4647o = new e[]{e.OLD_ID};
    }

    public C1852a(String str, String str2, long j) {
        this();
        this.f4642a = str;
        this.f4644c = str2;
        this.f4645d = j;
        m4531d(true);
    }

    public C1852a(C1852a c1852a) {
        this.f4646n = (byte) 0;
        this.f4647o = new e[]{e.OLD_ID};
        this.f4646n = c1852a.f4646n;
        if (c1852a.m4532d()) {
            this.f4642a = c1852a.f4642a;
        }
        if (c1852a.m4535g()) {
            this.f4643b = c1852a.f4643b;
        }
        if (c1852a.m4538j()) {
            this.f4644c = c1852a.f4644c;
        }
        this.f4645d = c1852a.f4645d;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1852a deepCopy() {
        return new C1852a(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4642a = null;
        this.f4643b = null;
        this.f4644c = null;
        m4531d(false);
        this.f4645d = 0L;
    }

    /* JADX INFO: renamed from: b */
    public String m4526b() {
        return this.f4642a;
    }

    /* JADX INFO: renamed from: a */
    public C1852a m4523a(String str) {
        this.f4642a = str;
        return this;
    }

    /* JADX INFO: renamed from: c */
    public void m4529c() {
        this.f4642a = null;
    }

    /* JADX INFO: renamed from: d */
    public boolean m4532d() {
        return this.f4642a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4524a(boolean z) {
        if (z) {
            return;
        }
        this.f4642a = null;
    }

    /* JADX INFO: renamed from: e */
    public String m4533e() {
        return this.f4643b;
    }

    /* JADX INFO: renamed from: b */
    public C1852a m4525b(String str) {
        this.f4643b = str;
        return this;
    }

    /* JADX INFO: renamed from: f */
    public void m4534f() {
        this.f4643b = null;
    }

    /* JADX INFO: renamed from: g */
    public boolean m4535g() {
        return this.f4643b != null;
    }

    /* JADX INFO: renamed from: b */
    public void m4527b(boolean z) {
        if (z) {
            return;
        }
        this.f4643b = null;
    }

    /* JADX INFO: renamed from: h */
    public String m4536h() {
        return this.f4644c;
    }

    /* JADX INFO: renamed from: c */
    public C1852a m4528c(String str) {
        this.f4644c = str;
        return this;
    }

    /* JADX INFO: renamed from: i */
    public void m4537i() {
        this.f4644c = null;
    }

    /* JADX INFO: renamed from: j */
    public boolean m4538j() {
        return this.f4644c != null;
    }

    /* JADX INFO: renamed from: c */
    public void m4530c(boolean z) {
        if (z) {
            return;
        }
        this.f4644c = null;
    }

    /* JADX INFO: renamed from: k */
    public long m4539k() {
        return this.f4645d;
    }

    /* JADX INFO: renamed from: a */
    public C1852a m4522a(long j) {
        this.f4645d = j;
        m4531d(true);
        return this;
    }

    /* JADX INFO: renamed from: l */
    public void m4540l() {
        this.f4646n = C1801x.m4199b(this.f4646n, 0);
    }

    /* JADX INFO: renamed from: m */
    public boolean m4541m() {
        return C1801x.m4195a(this.f4646n, 0);
    }

    /* JADX INFO: renamed from: d */
    public void m4531d(boolean z) {
        this.f4646n = C1801x.m4187a(this.f4646n, 0, z);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4549a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4640l.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4640l.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IdJournal(");
        sb.append("domain:");
        if (this.f4642a == null) {
            sb.append("null");
        } else {
            sb.append(this.f4642a);
        }
        if (m4535g()) {
            sb.append(", ");
            sb.append("old_id:");
            if (this.f4643b == null) {
                sb.append("null");
            } else {
                sb.append(this.f4643b);
            }
        }
        sb.append(", ");
        sb.append("new_id:");
        if (this.f4644c == null) {
            sb.append("null");
        } else {
            sb.append(this.f4644c);
        }
        sb.append(", ");
        sb.append("ts:");
        sb.append(this.f4645d);
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: n */
    public void m4542n() throws C1743ag {
        if (this.f4642a == null) {
            throw new C1764ba("Required field 'domain' was not present! Struct: " + toString());
        }
        if (this.f4644c == null) {
            throw new C1764ba("Required field 'new_id' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4514a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4513a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f4646n = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$b */
    /* JADX INFO: compiled from: IdJournal.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$a */
    /* JADX INFO: compiled from: IdJournal.java */
    private static class a extends AbstractC1773bj<C1852a> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1852a c1852a) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1852a.f4642a = abstractC1762az.mo3892z();
                                c1852a.m4524a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1852a.f4643b = abstractC1762az.mo3892z();
                                c1852a.m4527b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1852a.f4644c = abstractC1762az.mo3892z();
                                c1852a.m4530c(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 4:
                            if (c1757auMo3878l.f4083b == 10) {
                                c1852a.f4645d = abstractC1762az.mo3890x();
                                c1852a.m4531d(true);
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
                    if (!c1852a.m4541m()) {
                        throw new C1764ba("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    c1852a.m4542n();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1852a c1852a) throws C1743ag {
            c1852a.m4542n();
            abstractC1762az.mo3860a(C1852a.f4635g);
            if (c1852a.f4642a != null) {
                abstractC1762az.mo3855a(C1852a.f4636h);
                abstractC1762az.mo3861a(c1852a.f4642a);
                abstractC1762az.mo3867c();
            }
            if (c1852a.f4643b != null && c1852a.m4535g()) {
                abstractC1762az.mo3855a(C1852a.f4637i);
                abstractC1762az.mo3861a(c1852a.f4643b);
                abstractC1762az.mo3867c();
            }
            if (c1852a.f4644c != null) {
                abstractC1762az.mo3855a(C1852a.f4638j);
                abstractC1762az.mo3861a(c1852a.f4644c);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3855a(C1852a.f4639k);
            abstractC1762az.mo3854a(c1852a.f4645d);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$d */
    /* JADX INFO: compiled from: IdJournal.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.a$c */
    /* JADX INFO: compiled from: IdJournal.java */
    private static class c extends AbstractC1774bk<C1852a> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1852a c1852a) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3861a(c1852a.f4642a);
            c1769bf.mo3861a(c1852a.f4644c);
            c1769bf.mo3854a(c1852a.f4645d);
            BitSet bitSet = new BitSet();
            if (c1852a.m4535g()) {
                bitSet.set(0);
            }
            c1769bf.m3928a(bitSet, 1);
            if (c1852a.m4535g()) {
                c1769bf.mo3861a(c1852a.f4643b);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1852a c1852a) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1852a.f4642a = c1769bf.mo3892z();
            c1852a.m4524a(true);
            c1852a.f4644c = c1769bf.mo3892z();
            c1852a.m4530c(true);
            c1852a.f4645d = c1769bf.mo3890x();
            c1852a.m4531d(true);
            if (c1769bf.m3929b(1).get(0)) {
                c1852a.f4643b = c1769bf.mo3892z();
                c1852a.m4527b(true);
            }
        }
    }
}

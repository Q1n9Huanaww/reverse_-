package com.umeng.commonsdk.statistics.proto;

import com.p024ds.daisi.net.URL.URLConstant;
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

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e */
/* JADX INFO: compiled from: ImprintValue.java */
/* JADX INFO: loaded from: classes.dex */
public class C1856e implements InterfaceC1737aa<C1856e, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: d */
    public static final Map<e, C1749am> f4713d;

    /* JADX INFO: renamed from: e */
    private static final long f4714e = 7501688097813630241L;

    /* JADX INFO: renamed from: f */
    private static final C1768be f4715f = new C1768be("ImprintValue");

    /* JADX INFO: renamed from: g */
    private static final C1757au f4716g = new C1757au("value", (byte) 11, 1);

    /* JADX INFO: renamed from: h */
    private static final C1757au f4717h = new C1757au("ts", (byte) 10, 2);

    /* JADX INFO: renamed from: i */
    private static final C1757au f4718i = new C1757au(URLConstant.MSG_REQUEST_GUID, (byte) 11, 3);

    /* JADX INFO: renamed from: j */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4719j = new HashMap();

    /* JADX INFO: renamed from: k */
    private static final int f4720k = 0;

    /* JADX INFO: renamed from: a */
    public String f4721a;

    /* JADX INFO: renamed from: b */
    public long f4722b;

    /* JADX INFO: renamed from: c */
    public String f4723c;

    /* JADX INFO: renamed from: l */
    private byte f4724l;

    /* JADX INFO: renamed from: m */
    private e[] f4725m;

    static {
        f4719j.put(AbstractC1773bj.class, new b());
        f4719j.put(AbstractC1774bk.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.VALUE, new C1749am("value", (byte) 2, new C1750an((byte) 11)));
        enumMap.put(e.TS, new C1749am("ts", (byte) 1, new C1750an((byte) 10)));
        enumMap.put(e.GUID, new C1749am(URLConstant.MSG_REQUEST_GUID, (byte) 1, new C1750an((byte) 11)));
        f4713d = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1856e.class, f4713d);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$e */
    /* JADX INFO: compiled from: ImprintValue.java */
    public enum e implements InterfaceC1744ah {
        VALUE(1, "value"),
        TS(2, "ts"),
        GUID(3, URLConstant.MSG_REQUEST_GUID);


        /* JADX INFO: renamed from: d */
        private static final Map<String, e> f4729d = new HashMap();

        /* JADX INFO: renamed from: e */
        private final short f4731e;

        /* JADX INFO: renamed from: f */
        private final String f4732f;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4729d.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4688a(int i) {
            switch (i) {
                case 1:
                    return VALUE;
                case 2:
                    return TS;
                case 3:
                    return GUID;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static e m4690b(int i) {
            e eVarM4688a = m4688a(i);
            if (eVarM4688a != null) {
                return eVarM4688a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4689a(String str) {
            return f4729d.get(str);
        }

        e(short s, String str) {
            this.f4731e = s;
            this.f4732f = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4731e;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4732f;
        }
    }

    public C1856e() {
        this.f4724l = (byte) 0;
        this.f4725m = new e[]{e.VALUE};
    }

    public C1856e(long j, String str) {
        this();
        this.f4722b = j;
        m4671b(true);
        this.f4723c = str;
    }

    public C1856e(C1856e c1856e) {
        this.f4724l = (byte) 0;
        this.f4725m = new e[]{e.VALUE};
        this.f4724l = c1856e.f4724l;
        if (c1856e.m4674d()) {
            this.f4721a = c1856e.f4721a;
        }
        this.f4722b = c1856e.f4722b;
        if (c1856e.m4680j()) {
            this.f4723c = c1856e.f4723c;
        }
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1856e deepCopy() {
        return new C1856e(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4721a = null;
        m4671b(false);
        this.f4722b = 0L;
        this.f4723c = null;
    }

    /* JADX INFO: renamed from: b */
    public String m4670b() {
        return this.f4721a;
    }

    /* JADX INFO: renamed from: a */
    public C1856e m4667a(String str) {
        this.f4721a = str;
        return this;
    }

    /* JADX INFO: renamed from: c */
    public void m4672c() {
        this.f4721a = null;
    }

    /* JADX INFO: renamed from: d */
    public boolean m4674d() {
        return this.f4721a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4668a(boolean z) {
        if (z) {
            return;
        }
        this.f4721a = null;
    }

    /* JADX INFO: renamed from: e */
    public long m4675e() {
        return this.f4722b;
    }

    /* JADX INFO: renamed from: a */
    public C1856e m4666a(long j) {
        this.f4722b = j;
        m4671b(true);
        return this;
    }

    /* JADX INFO: renamed from: f */
    public void m4676f() {
        this.f4724l = C1801x.m4199b(this.f4724l, 0);
    }

    /* JADX INFO: renamed from: g */
    public boolean m4677g() {
        return C1801x.m4195a(this.f4724l, 0);
    }

    /* JADX INFO: renamed from: b */
    public void m4671b(boolean z) {
        this.f4724l = C1801x.m4187a(this.f4724l, 0, z);
    }

    /* JADX INFO: renamed from: h */
    public String m4678h() {
        return this.f4723c;
    }

    /* JADX INFO: renamed from: b */
    public C1856e m4669b(String str) {
        this.f4723c = str;
        return this;
    }

    /* JADX INFO: renamed from: i */
    public void m4679i() {
        this.f4723c = null;
    }

    /* JADX INFO: renamed from: j */
    public boolean m4680j() {
        return this.f4723c != null;
    }

    /* JADX INFO: renamed from: c */
    public void m4673c(boolean z) {
        if (z) {
            return;
        }
        this.f4723c = null;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4688a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4719j.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4719j.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        boolean z;
        StringBuilder sb = new StringBuilder("ImprintValue(");
        if (m4674d()) {
            sb.append("value:");
            if (this.f4721a == null) {
                sb.append("null");
            } else {
                sb.append(this.f4721a);
            }
            z = false;
        } else {
            z = true;
        }
        if (!z) {
            sb.append(", ");
        }
        sb.append("ts:");
        sb.append(this.f4722b);
        sb.append(", ");
        sb.append("guid:");
        if (this.f4723c == null) {
            sb.append("null");
        } else {
            sb.append(this.f4723c);
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: k */
    public void m4681k() throws C1743ag {
        if (this.f4723c == null) {
            throw new C1764ba("Required field 'guid' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4659a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4658a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f4724l = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$b */
    /* JADX INFO: compiled from: ImprintValue.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$a */
    /* JADX INFO: compiled from: ImprintValue.java */
    private static class a extends AbstractC1773bj<C1856e> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1856e c1856e) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1856e.f4721a = abstractC1762az.mo3892z();
                                c1856e.m4668a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 10) {
                                c1856e.f4722b = abstractC1762az.mo3890x();
                                c1856e.m4671b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1856e.f4723c = abstractC1762az.mo3892z();
                                c1856e.m4673c(true);
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
                    if (!c1856e.m4677g()) {
                        throw new C1764ba("Required field 'ts' was not found in serialized data! Struct: " + toString());
                    }
                    c1856e.m4681k();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1856e c1856e) throws C1743ag {
            c1856e.m4681k();
            abstractC1762az.mo3860a(C1856e.f4715f);
            if (c1856e.f4721a != null && c1856e.m4674d()) {
                abstractC1762az.mo3855a(C1856e.f4716g);
                abstractC1762az.mo3861a(c1856e.f4721a);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3855a(C1856e.f4717h);
            abstractC1762az.mo3854a(c1856e.f4722b);
            abstractC1762az.mo3867c();
            if (c1856e.f4723c != null) {
                abstractC1762az.mo3855a(C1856e.f4718i);
                abstractC1762az.mo3861a(c1856e.f4723c);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$d */
    /* JADX INFO: compiled from: ImprintValue.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.e$c */
    /* JADX INFO: compiled from: ImprintValue.java */
    private static class c extends AbstractC1774bk<C1856e> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1856e c1856e) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3854a(c1856e.f4722b);
            c1769bf.mo3861a(c1856e.f4723c);
            BitSet bitSet = new BitSet();
            if (c1856e.m4674d()) {
                bitSet.set(0);
            }
            c1769bf.m3928a(bitSet, 1);
            if (c1856e.m4674d()) {
                c1769bf.mo3861a(c1856e.f4721a);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1856e c1856e) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1856e.f4722b = c1769bf.mo3890x();
            c1856e.m4671b(true);
            c1856e.f4723c = c1769bf.mo3892z();
            c1856e.m4673c(true);
            if (c1769bf.m3929b(1).get(0)) {
                c1856e.f4721a = c1769bf.mo3892z();
                c1856e.m4668a(true);
            }
        }
    }
}

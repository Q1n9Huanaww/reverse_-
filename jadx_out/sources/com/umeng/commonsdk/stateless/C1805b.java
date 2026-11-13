package com.umeng.commonsdk.stateless;

import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.proguard.AbstractC1762az;
import com.umeng.commonsdk.proguard.AbstractC1773bj;
import com.umeng.commonsdk.proguard.AbstractC1774bk;
import com.umeng.commonsdk.proguard.C1738ab;
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
import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b */
/* JADX INFO: compiled from: UMSLEnvelope.java */
/* JADX INFO: loaded from: classes.dex */
public class C1805b implements InterfaceC1737aa<C1805b, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: A */
    private static final int f4406A = 2;

    /* JADX INFO: renamed from: B */
    private static final int f4407B = 3;

    /* JADX INFO: renamed from: k */
    public static final Map<e, C1749am> f4408k;

    /* JADX INFO: renamed from: l */
    private static final long f4409l = 420342210744516016L;

    /* JADX INFO: renamed from: m */
    private static final C1768be f4410m = new C1768be("UMSLEnvelope");

    /* JADX INFO: renamed from: n */
    private static final C1757au f4411n = new C1757au("version", (byte) 11, 1);

    /* JADX INFO: renamed from: o */
    private static final C1757au f4412o = new C1757au("address", (byte) 11, 2);

    /* JADX INFO: renamed from: p */
    private static final C1757au f4413p = new C1757au("signature", (byte) 11, 3);

    /* JADX INFO: renamed from: q */
    private static final C1757au f4414q = new C1757au("serial_num", (byte) 8, 4);

    /* JADX INFO: renamed from: r */
    private static final C1757au f4415r = new C1757au("ts_secs", (byte) 8, 5);

    /* JADX INFO: renamed from: s */
    private static final C1757au f4416s = new C1757au("length", (byte) 8, 6);

    /* JADX INFO: renamed from: t */
    private static final C1757au f4417t = new C1757au("entity", (byte) 11, 7);

    /* JADX INFO: renamed from: u */
    private static final C1757au f4418u = new C1757au(URLConstant.MSG_REQUEST_GUID, (byte) 11, 8);

    /* JADX INFO: renamed from: v */
    private static final C1757au f4419v = new C1757au("checksum", (byte) 11, 9);

    /* JADX INFO: renamed from: w */
    private static final C1757au f4420w = new C1757au("codex", (byte) 8, 10);

    /* JADX INFO: renamed from: x */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4421x = new HashMap();

    /* JADX INFO: renamed from: y */
    private static final int f4422y = 0;

    /* JADX INFO: renamed from: z */
    private static final int f4423z = 1;

    /* JADX INFO: renamed from: C */
    private byte f4424C;

    /* JADX INFO: renamed from: D */
    private e[] f4425D;

    /* JADX INFO: renamed from: a */
    public String f4426a;

    /* JADX INFO: renamed from: b */
    public String f4427b;

    /* JADX INFO: renamed from: c */
    public String f4428c;

    /* JADX INFO: renamed from: d */
    public int f4429d;

    /* JADX INFO: renamed from: e */
    public int f4430e;

    /* JADX INFO: renamed from: f */
    public int f4431f;

    /* JADX INFO: renamed from: g */
    public ByteBuffer f4432g;

    /* JADX INFO: renamed from: h */
    public String f4433h;

    /* JADX INFO: renamed from: i */
    public String f4434i;

    /* JADX INFO: renamed from: j */
    public int f4435j;

    static {
        f4421x.put(AbstractC1773bj.class, new b());
        f4421x.put(AbstractC1774bk.class, new d());
        EnumMap enumMap = new EnumMap(e.class);
        enumMap.put(e.VERSION, new C1749am("version", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.ADDRESS, new C1749am("address", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.SIGNATURE, new C1749am("signature", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.SERIAL_NUM, new C1749am("serial_num", (byte) 1, new C1750an((byte) 8)));
        enumMap.put(e.TS_SECS, new C1749am("ts_secs", (byte) 1, new C1750an((byte) 8)));
        enumMap.put(e.LENGTH, new C1749am("length", (byte) 1, new C1750an((byte) 8)));
        enumMap.put(e.ENTITY, new C1749am("entity", (byte) 1, new C1750an((byte) 11, true)));
        enumMap.put(e.GUID, new C1749am(URLConstant.MSG_REQUEST_GUID, (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.CHECKSUM, new C1749am("checksum", (byte) 1, new C1750an((byte) 11)));
        enumMap.put(e.CODEX, new C1749am("codex", (byte) 2, new C1750an((byte) 8)));
        f4408k = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1805b.class, f4408k);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b$e */
    /* JADX INFO: compiled from: UMSLEnvelope.java */
    public enum e implements InterfaceC1744ah {
        VERSION(1, "version"),
        ADDRESS(2, "address"),
        SIGNATURE(3, "signature"),
        SERIAL_NUM(4, "serial_num"),
        TS_SECS(5, "ts_secs"),
        LENGTH(6, "length"),
        ENTITY(7, "entity"),
        GUID(8, URLConstant.MSG_REQUEST_GUID),
        CHECKSUM(9, "checksum"),
        CODEX(10, "codex");


        /* JADX INFO: renamed from: k */
        private static final Map<String, e> f4446k = new HashMap();

        /* JADX INFO: renamed from: l */
        private final short f4448l;

        /* JADX INFO: renamed from: m */
        private final String f4449m;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4446k.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4285a(int i) {
            switch (i) {
                case 1:
                    return VERSION;
                case 2:
                    return ADDRESS;
                case 3:
                    return SIGNATURE;
                case 4:
                    return SERIAL_NUM;
                case 5:
                    return TS_SECS;
                case 6:
                    return LENGTH;
                case 7:
                    return ENTITY;
                case 8:
                    return GUID;
                case 9:
                    return CHECKSUM;
                case 10:
                    return CODEX;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static e m4287b(int i) {
            e eVarM4285a = m4285a(i);
            if (eVarM4285a != null) {
                return eVarM4285a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4286a(String str) {
            return f4446k.get(str);
        }

        e(short s, String str) {
            this.f4448l = s;
            this.f4449m = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4448l;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4449m;
        }
    }

    public C1805b() {
        this.f4424C = (byte) 0;
        this.f4425D = new e[]{e.CODEX};
    }

    public C1805b(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f4426a = str;
        this.f4427b = str2;
        this.f4428c = str3;
        this.f4429d = i;
        m4247d(true);
        this.f4430e = i2;
        m4252e(true);
        this.f4431f = i3;
        m4254f(true);
        this.f4432g = byteBuffer;
        this.f4433h = str4;
        this.f4434i = str5;
    }

    public C1805b(C1805b c1805b) {
        this.f4424C = (byte) 0;
        this.f4425D = new e[]{e.CODEX};
        this.f4424C = c1805b.f4424C;
        if (c1805b.m4248d()) {
            this.f4426a = c1805b.f4426a;
        }
        if (c1805b.m4256g()) {
            this.f4427b = c1805b.f4427b;
        }
        if (c1805b.m4262j()) {
            this.f4428c = c1805b.f4428c;
        }
        this.f4429d = c1805b.f4429d;
        this.f4430e = c1805b.f4430e;
        this.f4431f = c1805b.f4431f;
        if (c1805b.m4275w()) {
            this.f4432g = C1738ab.m3787d(c1805b.f4432g);
        }
        if (c1805b.m4278z()) {
            this.f4433h = c1805b.f4433h;
        }
        if (c1805b.m4226C()) {
            this.f4434i = c1805b.f4434i;
        }
        this.f4435j = c1805b.f4435j;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1805b deepCopy() {
        return new C1805b(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4426a = null;
        this.f4427b = null;
        this.f4428c = null;
        m4247d(false);
        this.f4429d = 0;
        m4252e(false);
        this.f4430e = 0;
        m4254f(false);
        this.f4431f = 0;
        this.f4432g = null;
        this.f4433h = null;
        this.f4434i = null;
        m4261j(false);
        this.f4435j = 0;
    }

    /* JADX INFO: renamed from: b */
    public String m4239b() {
        return this.f4426a;
    }

    /* JADX INFO: renamed from: a */
    public C1805b m4233a(String str) {
        this.f4426a = str;
        return this;
    }

    /* JADX INFO: renamed from: c */
    public void m4243c() {
        this.f4426a = null;
    }

    /* JADX INFO: renamed from: d */
    public boolean m4248d() {
        return this.f4426a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4236a(boolean z) {
        if (z) {
            return;
        }
        this.f4426a = null;
    }

    /* JADX INFO: renamed from: e */
    public String m4251e() {
        return this.f4427b;
    }

    /* JADX INFO: renamed from: b */
    public C1805b m4238b(String str) {
        this.f4427b = str;
        return this;
    }

    /* JADX INFO: renamed from: f */
    public void m4253f() {
        this.f4427b = null;
    }

    /* JADX INFO: renamed from: g */
    public boolean m4256g() {
        return this.f4427b != null;
    }

    /* JADX INFO: renamed from: b */
    public void m4240b(boolean z) {
        if (z) {
            return;
        }
        this.f4427b = null;
    }

    /* JADX INFO: renamed from: h */
    public String m4257h() {
        return this.f4428c;
    }

    /* JADX INFO: renamed from: c */
    public C1805b m4242c(String str) {
        this.f4428c = str;
        return this;
    }

    /* JADX INFO: renamed from: i */
    public void m4259i() {
        this.f4428c = null;
    }

    /* JADX INFO: renamed from: j */
    public boolean m4262j() {
        return this.f4428c != null;
    }

    /* JADX INFO: renamed from: c */
    public void m4244c(boolean z) {
        if (z) {
            return;
        }
        this.f4428c = null;
    }

    /* JADX INFO: renamed from: k */
    public int m4263k() {
        return this.f4429d;
    }

    /* JADX INFO: renamed from: a */
    public C1805b m4232a(int i) {
        this.f4429d = i;
        m4247d(true);
        return this;
    }

    /* JADX INFO: renamed from: l */
    public void m4264l() {
        this.f4424C = C1801x.m4199b(this.f4424C, 0);
    }

    /* JADX INFO: renamed from: m */
    public boolean m4265m() {
        return C1801x.m4195a(this.f4424C, 0);
    }

    /* JADX INFO: renamed from: d */
    public void m4247d(boolean z) {
        this.f4424C = C1801x.m4187a(this.f4424C, 0, z);
    }

    /* JADX INFO: renamed from: n */
    public int m4266n() {
        return this.f4430e;
    }

    /* JADX INFO: renamed from: b */
    public C1805b m4237b(int i) {
        this.f4430e = i;
        m4252e(true);
        return this;
    }

    /* JADX INFO: renamed from: o */
    public void m4267o() {
        this.f4424C = C1801x.m4199b(this.f4424C, 1);
    }

    /* JADX INFO: renamed from: p */
    public boolean m4268p() {
        return C1801x.m4195a(this.f4424C, 1);
    }

    /* JADX INFO: renamed from: e */
    public void m4252e(boolean z) {
        this.f4424C = C1801x.m4187a(this.f4424C, 1, z);
    }

    /* JADX INFO: renamed from: q */
    public int m4269q() {
        return this.f4431f;
    }

    /* JADX INFO: renamed from: c */
    public C1805b m4241c(int i) {
        this.f4431f = i;
        m4254f(true);
        return this;
    }

    /* JADX INFO: renamed from: r */
    public void m4270r() {
        this.f4424C = C1801x.m4199b(this.f4424C, 2);
    }

    /* JADX INFO: renamed from: s */
    public boolean m4271s() {
        return C1801x.m4195a(this.f4424C, 2);
    }

    /* JADX INFO: renamed from: f */
    public void m4254f(boolean z) {
        this.f4424C = C1801x.m4187a(this.f4424C, 2, z);
    }

    /* JADX INFO: renamed from: t */
    public byte[] m4272t() {
        m4234a(C1738ab.m3786c(this.f4432g));
        if (this.f4432g == null) {
            return null;
        }
        return this.f4432g.array();
    }

    /* JADX INFO: renamed from: u */
    public ByteBuffer m4273u() {
        return this.f4432g;
    }

    /* JADX INFO: renamed from: a */
    public C1805b m4235a(byte[] bArr) {
        m4234a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1805b m4234a(ByteBuffer byteBuffer) {
        this.f4432g = byteBuffer;
        return this;
    }

    /* JADX INFO: renamed from: v */
    public void m4274v() {
        this.f4432g = null;
    }

    /* JADX INFO: renamed from: w */
    public boolean m4275w() {
        return this.f4432g != null;
    }

    /* JADX INFO: renamed from: g */
    public void m4255g(boolean z) {
        if (z) {
            return;
        }
        this.f4432g = null;
    }

    /* JADX INFO: renamed from: x */
    public String m4276x() {
        return this.f4433h;
    }

    /* JADX INFO: renamed from: d */
    public C1805b m4246d(String str) {
        this.f4433h = str;
        return this;
    }

    /* JADX INFO: renamed from: y */
    public void m4277y() {
        this.f4433h = null;
    }

    /* JADX INFO: renamed from: z */
    public boolean m4278z() {
        return this.f4433h != null;
    }

    /* JADX INFO: renamed from: h */
    public void m4258h(boolean z) {
        if (z) {
            return;
        }
        this.f4433h = null;
    }

    /* JADX INFO: renamed from: A */
    public String m4224A() {
        return this.f4434i;
    }

    /* JADX INFO: renamed from: e */
    public C1805b m4250e(String str) {
        this.f4434i = str;
        return this;
    }

    /* JADX INFO: renamed from: B */
    public void m4225B() {
        this.f4434i = null;
    }

    /* JADX INFO: renamed from: C */
    public boolean m4226C() {
        return this.f4434i != null;
    }

    /* JADX INFO: renamed from: i */
    public void m4260i(boolean z) {
        if (z) {
            return;
        }
        this.f4434i = null;
    }

    /* JADX INFO: renamed from: D */
    public int m4227D() {
        return this.f4435j;
    }

    /* JADX INFO: renamed from: d */
    public C1805b m4245d(int i) {
        this.f4435j = i;
        m4261j(true);
        return this;
    }

    /* JADX INFO: renamed from: E */
    public void m4228E() {
        this.f4424C = C1801x.m4199b(this.f4424C, 3);
    }

    /* JADX INFO: renamed from: F */
    public boolean m4229F() {
        return C1801x.m4195a(this.f4424C, 3);
    }

    /* JADX INFO: renamed from: j */
    public void m4261j(boolean z) {
        this.f4424C = C1801x.m4187a(this.f4424C, 3, z);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4285a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4421x.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4421x.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMSLEnvelope(");
        sb.append("version:");
        if (this.f4426a == null) {
            sb.append("null");
        } else {
            sb.append(this.f4426a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.f4427b == null) {
            sb.append("null");
        } else {
            sb.append(this.f4427b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.f4428c == null) {
            sb.append("null");
        } else {
            sb.append(this.f4428c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.f4429d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f4430e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f4431f);
        sb.append(", ");
        sb.append("entity:");
        if (this.f4432g == null) {
            sb.append("null");
        } else {
            C1738ab.m3782a(this.f4432g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.f4433h == null) {
            sb.append("null");
        } else {
            sb.append(this.f4433h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.f4434i == null) {
            sb.append("null");
        } else {
            sb.append(this.f4434i);
        }
        if (m4229F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.f4435j);
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: G */
    public void m4230G() throws C1743ag {
        if (this.f4426a == null) {
            throw new C1764ba("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.f4427b == null) {
            throw new C1764ba("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.f4428c == null) {
            throw new C1764ba("Required field 'signature' was not present! Struct: " + toString());
        }
        if (this.f4432g == null) {
            throw new C1764ba("Required field 'entity' was not present! Struct: " + toString());
        }
        if (this.f4433h == null) {
            throw new C1764ba("Required field 'guid' was not present! Struct: " + toString());
        }
        if (this.f4434i == null) {
            throw new C1764ba("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4223a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4222a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f4424C = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b$b */
    /* JADX INFO: compiled from: UMSLEnvelope.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b$a */
    /* JADX INFO: compiled from: UMSLEnvelope.java */
    private static class a extends AbstractC1773bj<C1805b> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1805b c1805b) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1805b.f4426a = abstractC1762az.mo3892z();
                                c1805b.m4236a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1805b.f4427b = abstractC1762az.mo3892z();
                                c1805b.m4240b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1805b.f4428c = abstractC1762az.mo3892z();
                                c1805b.m4244c(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 4:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1805b.f4429d = abstractC1762az.mo3889w();
                                c1805b.m4247d(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 5:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1805b.f4430e = abstractC1762az.mo3889w();
                                c1805b.m4252e(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 6:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1805b.f4431f = abstractC1762az.mo3889w();
                                c1805b.m4254f(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 7:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1805b.f4432g = abstractC1762az.mo3849A();
                                c1805b.m4255g(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 8:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1805b.f4433h = abstractC1762az.mo3892z();
                                c1805b.m4258h(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 9:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1805b.f4434i = abstractC1762az.mo3892z();
                                c1805b.m4260i(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 10:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1805b.f4435j = abstractC1762az.mo3889w();
                                c1805b.m4261j(true);
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
                    if (!c1805b.m4265m()) {
                        throw new C1764ba("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!c1805b.m4268p()) {
                        throw new C1764ba("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (!c1805b.m4271s()) {
                        throw new C1764ba("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                    c1805b.m4230G();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1805b c1805b) throws C1743ag {
            c1805b.m4230G();
            abstractC1762az.mo3860a(C1805b.f4410m);
            if (c1805b.f4426a != null) {
                abstractC1762az.mo3855a(C1805b.f4411n);
                abstractC1762az.mo3861a(c1805b.f4426a);
                abstractC1762az.mo3867c();
            }
            if (c1805b.f4427b != null) {
                abstractC1762az.mo3855a(C1805b.f4412o);
                abstractC1762az.mo3861a(c1805b.f4427b);
                abstractC1762az.mo3867c();
            }
            if (c1805b.f4428c != null) {
                abstractC1762az.mo3855a(C1805b.f4413p);
                abstractC1762az.mo3861a(c1805b.f4428c);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3855a(C1805b.f4414q);
            abstractC1762az.mo3853a(c1805b.f4429d);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3855a(C1805b.f4415r);
            abstractC1762az.mo3853a(c1805b.f4430e);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3855a(C1805b.f4416s);
            abstractC1762az.mo3853a(c1805b.f4431f);
            abstractC1762az.mo3867c();
            if (c1805b.f4432g != null) {
                abstractC1762az.mo3855a(C1805b.f4417t);
                abstractC1762az.mo3862a(c1805b.f4432g);
                abstractC1762az.mo3867c();
            }
            if (c1805b.f4433h != null) {
                abstractC1762az.mo3855a(C1805b.f4418u);
                abstractC1762az.mo3861a(c1805b.f4433h);
                abstractC1762az.mo3867c();
            }
            if (c1805b.f4434i != null) {
                abstractC1762az.mo3855a(C1805b.f4419v);
                abstractC1762az.mo3861a(c1805b.f4434i);
                abstractC1762az.mo3867c();
            }
            if (c1805b.m4229F()) {
                abstractC1762az.mo3855a(C1805b.f4420w);
                abstractC1762az.mo3853a(c1805b.f4435j);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b$d */
    /* JADX INFO: compiled from: UMSLEnvelope.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.stateless.b$c */
    /* JADX INFO: compiled from: UMSLEnvelope.java */
    private static class c extends AbstractC1774bk<C1805b> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1805b c1805b) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3861a(c1805b.f4426a);
            c1769bf.mo3861a(c1805b.f4427b);
            c1769bf.mo3861a(c1805b.f4428c);
            c1769bf.mo3853a(c1805b.f4429d);
            c1769bf.mo3853a(c1805b.f4430e);
            c1769bf.mo3853a(c1805b.f4431f);
            c1769bf.mo3862a(c1805b.f4432g);
            c1769bf.mo3861a(c1805b.f4433h);
            c1769bf.mo3861a(c1805b.f4434i);
            BitSet bitSet = new BitSet();
            if (c1805b.m4229F()) {
                bitSet.set(0);
            }
            c1769bf.m3928a(bitSet, 1);
            if (c1805b.m4229F()) {
                c1769bf.mo3853a(c1805b.f4435j);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1805b c1805b) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1805b.f4426a = c1769bf.mo3892z();
            c1805b.m4236a(true);
            c1805b.f4427b = c1769bf.mo3892z();
            c1805b.m4240b(true);
            c1805b.f4428c = c1769bf.mo3892z();
            c1805b.m4244c(true);
            c1805b.f4429d = c1769bf.mo3889w();
            c1805b.m4247d(true);
            c1805b.f4430e = c1769bf.mo3889w();
            c1805b.m4252e(true);
            c1805b.f4431f = c1769bf.mo3889w();
            c1805b.m4254f(true);
            c1805b.f4432g = c1769bf.mo3849A();
            c1805b.m4255g(true);
            c1805b.f4433h = c1769bf.mo3892z();
            c1805b.m4258h(true);
            c1805b.f4434i = c1769bf.mo3892z();
            c1805b.m4260i(true);
            if (c1769bf.m3929b(1).get(0)) {
                c1805b.f4435j = c1769bf.mo3889w();
                c1805b.m4261j(true);
            }
        }
    }
}

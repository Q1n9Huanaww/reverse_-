package com.umeng.commonsdk.proguard;

import com.p024ds.daisi.net.URL.URLConstant;
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

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.w */
/* JADX INFO: compiled from: UMEnvelope.java */
/* JADX INFO: loaded from: classes.dex */
public class C1800w implements InterfaceC1737aa<C1800w, e>, Serializable, Cloneable {

    /* JADX INFO: renamed from: A */
    private static final int f4336A = 2;

    /* JADX INFO: renamed from: B */
    private static final int f4337B = 3;

    /* JADX INFO: renamed from: k */
    public static final Map<e, C1749am> f4338k;

    /* JADX INFO: renamed from: l */
    private static final long f4339l = 420342210744516016L;

    /* JADX INFO: renamed from: m */
    private static final C1768be f4340m = new C1768be("UMEnvelope");

    /* JADX INFO: renamed from: n */
    private static final C1757au f4341n = new C1757au("version", (byte) 11, 1);

    /* JADX INFO: renamed from: o */
    private static final C1757au f4342o = new C1757au("address", (byte) 11, 2);

    /* JADX INFO: renamed from: p */
    private static final C1757au f4343p = new C1757au("signature", (byte) 11, 3);

    /* JADX INFO: renamed from: q */
    private static final C1757au f4344q = new C1757au("serial_num", (byte) 8, 4);

    /* JADX INFO: renamed from: r */
    private static final C1757au f4345r = new C1757au("ts_secs", (byte) 8, 5);

    /* JADX INFO: renamed from: s */
    private static final C1757au f4346s = new C1757au("length", (byte) 8, 6);

    /* JADX INFO: renamed from: t */
    private static final C1757au f4347t = new C1757au("entity", (byte) 11, 7);

    /* JADX INFO: renamed from: u */
    private static final C1757au f4348u = new C1757au(URLConstant.MSG_REQUEST_GUID, (byte) 11, 8);

    /* JADX INFO: renamed from: v */
    private static final C1757au f4349v = new C1757au("checksum", (byte) 11, 9);

    /* JADX INFO: renamed from: w */
    private static final C1757au f4350w = new C1757au("codex", (byte) 8, 10);

    /* JADX INFO: renamed from: x */
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> f4351x = new HashMap();

    /* JADX INFO: renamed from: y */
    private static final int f4352y = 0;

    /* JADX INFO: renamed from: z */
    private static final int f4353z = 1;

    /* JADX INFO: renamed from: C */
    private byte f4354C;

    /* JADX INFO: renamed from: D */
    private e[] f4355D;

    /* JADX INFO: renamed from: a */
    public String f4356a;

    /* JADX INFO: renamed from: b */
    public String f4357b;

    /* JADX INFO: renamed from: c */
    public String f4358c;

    /* JADX INFO: renamed from: d */
    public int f4359d;

    /* JADX INFO: renamed from: e */
    public int f4360e;

    /* JADX INFO: renamed from: f */
    public int f4361f;

    /* JADX INFO: renamed from: g */
    public ByteBuffer f4362g;

    /* JADX INFO: renamed from: h */
    public String f4363h;

    /* JADX INFO: renamed from: i */
    public String f4364i;

    /* JADX INFO: renamed from: j */
    public int f4365j;

    static {
        f4351x.put(AbstractC1773bj.class, new b());
        f4351x.put(AbstractC1774bk.class, new d());
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
        f4338k = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(C1800w.class, f4338k);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.w$e */
    /* JADX INFO: compiled from: UMEnvelope.java */
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
        private static final Map<String, e> f4376k = new HashMap();

        /* JADX INFO: renamed from: l */
        private final short f4378l;

        /* JADX INFO: renamed from: m */
        private final String f4379m;

        static {
            for (e eVar : EnumSet.allOf(e.class)) {
                f4376k.put(eVar.mo3807b(), eVar);
            }
        }

        /* JADX INFO: renamed from: a */
        public static e m4184a(int i) {
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
        public static e m4186b(int i) {
            e eVarM4184a = m4184a(i);
            if (eVarM4184a != null) {
                return eVarM4184a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static e m4185a(String str) {
            return f4376k.get(str);
        }

        e(short s, String str) {
            this.f4378l = s;
            this.f4379m = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4378l;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4379m;
        }
    }

    public C1800w() {
        this.f4354C = (byte) 0;
        this.f4355D = new e[]{e.CODEX};
    }

    public C1800w(String str, String str2, String str3, int i, int i2, int i3, ByteBuffer byteBuffer, String str4, String str5) {
        this();
        this.f4356a = str;
        this.f4357b = str2;
        this.f4358c = str3;
        this.f4359d = i;
        m4146d(true);
        this.f4360e = i2;
        m4151e(true);
        this.f4361f = i3;
        m4153f(true);
        this.f4362g = byteBuffer;
        this.f4363h = str4;
        this.f4364i = str5;
    }

    public C1800w(C1800w c1800w) {
        this.f4354C = (byte) 0;
        this.f4355D = new e[]{e.CODEX};
        this.f4354C = c1800w.f4354C;
        if (c1800w.m4147d()) {
            this.f4356a = c1800w.f4356a;
        }
        if (c1800w.m4155g()) {
            this.f4357b = c1800w.f4357b;
        }
        if (c1800w.m4161j()) {
            this.f4358c = c1800w.f4358c;
        }
        this.f4359d = c1800w.f4359d;
        this.f4360e = c1800w.f4360e;
        this.f4361f = c1800w.f4361f;
        if (c1800w.m4174w()) {
            this.f4362g = C1738ab.m3787d(c1800w.f4362g);
        }
        if (c1800w.m4177z()) {
            this.f4363h = c1800w.f4363h;
        }
        if (c1800w.m4125C()) {
            this.f4364i = c1800w.f4364i;
        }
        this.f4365j = c1800w.f4365j;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public C1800w deepCopy() {
        return new C1800w(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        this.f4356a = null;
        this.f4357b = null;
        this.f4358c = null;
        m4146d(false);
        this.f4359d = 0;
        m4151e(false);
        this.f4360e = 0;
        m4153f(false);
        this.f4361f = 0;
        this.f4362g = null;
        this.f4363h = null;
        this.f4364i = null;
        m4160j(false);
        this.f4365j = 0;
    }

    /* JADX INFO: renamed from: b */
    public String m4138b() {
        return this.f4356a;
    }

    /* JADX INFO: renamed from: a */
    public C1800w m4132a(String str) {
        this.f4356a = str;
        return this;
    }

    /* JADX INFO: renamed from: c */
    public void m4142c() {
        this.f4356a = null;
    }

    /* JADX INFO: renamed from: d */
    public boolean m4147d() {
        return this.f4356a != null;
    }

    /* JADX INFO: renamed from: a */
    public void m4135a(boolean z) {
        if (z) {
            return;
        }
        this.f4356a = null;
    }

    /* JADX INFO: renamed from: e */
    public String m4150e() {
        return this.f4357b;
    }

    /* JADX INFO: renamed from: b */
    public C1800w m4137b(String str) {
        this.f4357b = str;
        return this;
    }

    /* JADX INFO: renamed from: f */
    public void m4152f() {
        this.f4357b = null;
    }

    /* JADX INFO: renamed from: g */
    public boolean m4155g() {
        return this.f4357b != null;
    }

    /* JADX INFO: renamed from: b */
    public void m4139b(boolean z) {
        if (z) {
            return;
        }
        this.f4357b = null;
    }

    /* JADX INFO: renamed from: h */
    public String m4156h() {
        return this.f4358c;
    }

    /* JADX INFO: renamed from: c */
    public C1800w m4141c(String str) {
        this.f4358c = str;
        return this;
    }

    /* JADX INFO: renamed from: i */
    public void m4158i() {
        this.f4358c = null;
    }

    /* JADX INFO: renamed from: j */
    public boolean m4161j() {
        return this.f4358c != null;
    }

    /* JADX INFO: renamed from: c */
    public void m4143c(boolean z) {
        if (z) {
            return;
        }
        this.f4358c = null;
    }

    /* JADX INFO: renamed from: k */
    public int m4162k() {
        return this.f4359d;
    }

    /* JADX INFO: renamed from: a */
    public C1800w m4131a(int i) {
        this.f4359d = i;
        m4146d(true);
        return this;
    }

    /* JADX INFO: renamed from: l */
    public void m4163l() {
        this.f4354C = C1801x.m4199b(this.f4354C, 0);
    }

    /* JADX INFO: renamed from: m */
    public boolean m4164m() {
        return C1801x.m4195a(this.f4354C, 0);
    }

    /* JADX INFO: renamed from: d */
    public void m4146d(boolean z) {
        this.f4354C = C1801x.m4187a(this.f4354C, 0, z);
    }

    /* JADX INFO: renamed from: n */
    public int m4165n() {
        return this.f4360e;
    }

    /* JADX INFO: renamed from: b */
    public C1800w m4136b(int i) {
        this.f4360e = i;
        m4151e(true);
        return this;
    }

    /* JADX INFO: renamed from: o */
    public void m4166o() {
        this.f4354C = C1801x.m4199b(this.f4354C, 1);
    }

    /* JADX INFO: renamed from: p */
    public boolean m4167p() {
        return C1801x.m4195a(this.f4354C, 1);
    }

    /* JADX INFO: renamed from: e */
    public void m4151e(boolean z) {
        this.f4354C = C1801x.m4187a(this.f4354C, 1, z);
    }

    /* JADX INFO: renamed from: q */
    public int m4168q() {
        return this.f4361f;
    }

    /* JADX INFO: renamed from: c */
    public C1800w m4140c(int i) {
        this.f4361f = i;
        m4153f(true);
        return this;
    }

    /* JADX INFO: renamed from: r */
    public void m4169r() {
        this.f4354C = C1801x.m4199b(this.f4354C, 2);
    }

    /* JADX INFO: renamed from: s */
    public boolean m4170s() {
        return C1801x.m4195a(this.f4354C, 2);
    }

    /* JADX INFO: renamed from: f */
    public void m4153f(boolean z) {
        this.f4354C = C1801x.m4187a(this.f4354C, 2, z);
    }

    /* JADX INFO: renamed from: t */
    public byte[] m4171t() {
        m4133a(C1738ab.m3786c(this.f4362g));
        if (this.f4362g == null) {
            return null;
        }
        return this.f4362g.array();
    }

    /* JADX INFO: renamed from: u */
    public ByteBuffer m4172u() {
        return this.f4362g;
    }

    /* JADX INFO: renamed from: a */
    public C1800w m4134a(byte[] bArr) {
        m4133a(bArr == null ? (ByteBuffer) null : ByteBuffer.wrap(bArr));
        return this;
    }

    /* JADX INFO: renamed from: a */
    public C1800w m4133a(ByteBuffer byteBuffer) {
        this.f4362g = byteBuffer;
        return this;
    }

    /* JADX INFO: renamed from: v */
    public void m4173v() {
        this.f4362g = null;
    }

    /* JADX INFO: renamed from: w */
    public boolean m4174w() {
        return this.f4362g != null;
    }

    /* JADX INFO: renamed from: g */
    public void m4154g(boolean z) {
        if (z) {
            return;
        }
        this.f4362g = null;
    }

    /* JADX INFO: renamed from: x */
    public String m4175x() {
        return this.f4363h;
    }

    /* JADX INFO: renamed from: d */
    public C1800w m4145d(String str) {
        this.f4363h = str;
        return this;
    }

    /* JADX INFO: renamed from: y */
    public void m4176y() {
        this.f4363h = null;
    }

    /* JADX INFO: renamed from: z */
    public boolean m4177z() {
        return this.f4363h != null;
    }

    /* JADX INFO: renamed from: h */
    public void m4157h(boolean z) {
        if (z) {
            return;
        }
        this.f4363h = null;
    }

    /* JADX INFO: renamed from: A */
    public String m4123A() {
        return this.f4364i;
    }

    /* JADX INFO: renamed from: e */
    public C1800w m4149e(String str) {
        this.f4364i = str;
        return this;
    }

    /* JADX INFO: renamed from: B */
    public void m4124B() {
        this.f4364i = null;
    }

    /* JADX INFO: renamed from: C */
    public boolean m4125C() {
        return this.f4364i != null;
    }

    /* JADX INFO: renamed from: i */
    public void m4159i(boolean z) {
        if (z) {
            return;
        }
        this.f4364i = null;
    }

    /* JADX INFO: renamed from: D */
    public int m4126D() {
        return this.f4365j;
    }

    /* JADX INFO: renamed from: d */
    public C1800w m4144d(int i) {
        this.f4365j = i;
        m4160j(true);
        return this;
    }

    /* JADX INFO: renamed from: E */
    public void m4127E() {
        this.f4354C = C1801x.m4199b(this.f4354C, 3);
    }

    /* JADX INFO: renamed from: F */
    public boolean m4128F() {
        return C1801x.m4195a(this.f4354C, 3);
    }

    /* JADX INFO: renamed from: j */
    public void m4160j(boolean z) {
        this.f4354C = C1801x.m4187a(this.f4354C, 3, z);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    /* JADX INFO: renamed from: e, reason: merged with bridge method [inline-methods] */
    public e fieldForId(int i) {
        return e.m4184a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        f4351x.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        f4351x.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("UMEnvelope(");
        sb.append("version:");
        if (this.f4356a == null) {
            sb.append("null");
        } else {
            sb.append(this.f4356a);
        }
        sb.append(", ");
        sb.append("address:");
        if (this.f4357b == null) {
            sb.append("null");
        } else {
            sb.append(this.f4357b);
        }
        sb.append(", ");
        sb.append("signature:");
        if (this.f4358c == null) {
            sb.append("null");
        } else {
            sb.append(this.f4358c);
        }
        sb.append(", ");
        sb.append("serial_num:");
        sb.append(this.f4359d);
        sb.append(", ");
        sb.append("ts_secs:");
        sb.append(this.f4360e);
        sb.append(", ");
        sb.append("length:");
        sb.append(this.f4361f);
        sb.append(", ");
        sb.append("entity:");
        if (this.f4362g == null) {
            sb.append("null");
        } else {
            C1738ab.m3782a(this.f4362g, sb);
        }
        sb.append(", ");
        sb.append("guid:");
        if (this.f4363h == null) {
            sb.append("null");
        } else {
            sb.append(this.f4363h);
        }
        sb.append(", ");
        sb.append("checksum:");
        if (this.f4364i == null) {
            sb.append("null");
        } else {
            sb.append(this.f4364i);
        }
        if (m4128F()) {
            sb.append(", ");
            sb.append("codex:");
            sb.append(this.f4365j);
        }
        sb.append(")");
        return sb.toString();
    }

    /* JADX INFO: renamed from: G */
    public void m4129G() throws C1743ag {
        if (this.f4356a == null) {
            throw new C1764ba("Required field 'version' was not present! Struct: " + toString());
        }
        if (this.f4357b == null) {
            throw new C1764ba("Required field 'address' was not present! Struct: " + toString());
        }
        if (this.f4358c == null) {
            throw new C1764ba("Required field 'signature' was not present! Struct: " + toString());
        }
        if (this.f4362g == null) {
            throw new C1764ba("Required field 'entity' was not present! Struct: " + toString());
        }
        if (this.f4363h == null) {
            throw new C1764ba("Required field 'guid' was not present! Struct: " + toString());
        }
        if (this.f4364i == null) {
            throw new C1764ba("Required field 'checksum' was not present! Struct: " + toString());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4122a(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4121a(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.f4354C = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e2) {
            throw new IOException(e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.w$b */
    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class b implements InterfaceC1772bi {
        private b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public a mo3837b() {
            return new a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.w$a */
    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class a extends AbstractC1773bj<C1800w> {
        private a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1800w c1800w) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1800w.f4356a = abstractC1762az.mo3892z();
                                c1800w.m4135a(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1800w.f4357b = abstractC1762az.mo3892z();
                                c1800w.m4139b(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1800w.f4358c = abstractC1762az.mo3892z();
                                c1800w.m4143c(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 4:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1800w.f4359d = abstractC1762az.mo3889w();
                                c1800w.m4146d(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 5:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1800w.f4360e = abstractC1762az.mo3889w();
                                c1800w.m4151e(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 6:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1800w.f4361f = abstractC1762az.mo3889w();
                                c1800w.m4153f(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 7:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1800w.f4362g = abstractC1762az.mo3849A();
                                c1800w.m4154g(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 8:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1800w.f4363h = abstractC1762az.mo3892z();
                                c1800w.m4157h(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 9:
                            if (c1757auMo3878l.f4083b == 11) {
                                c1800w.f4364i = abstractC1762az.mo3892z();
                                c1800w.m4159i(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 10:
                            if (c1757auMo3878l.f4083b == 8) {
                                c1800w.f4365j = abstractC1762az.mo3889w();
                                c1800w.m4160j(true);
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
                    if (!c1800w.m4164m()) {
                        throw new C1764ba("Required field 'serial_num' was not found in serialized data! Struct: " + toString());
                    }
                    if (!c1800w.m4167p()) {
                        throw new C1764ba("Required field 'ts_secs' was not found in serialized data! Struct: " + toString());
                    }
                    if (!c1800w.m4170s()) {
                        throw new C1764ba("Required field 'length' was not found in serialized data! Struct: " + toString());
                    }
                    c1800w.m4129G();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1800w c1800w) throws C1743ag {
            c1800w.m4129G();
            abstractC1762az.mo3860a(C1800w.f4340m);
            if (c1800w.f4356a != null) {
                abstractC1762az.mo3855a(C1800w.f4341n);
                abstractC1762az.mo3861a(c1800w.f4356a);
                abstractC1762az.mo3867c();
            }
            if (c1800w.f4357b != null) {
                abstractC1762az.mo3855a(C1800w.f4342o);
                abstractC1762az.mo3861a(c1800w.f4357b);
                abstractC1762az.mo3867c();
            }
            if (c1800w.f4358c != null) {
                abstractC1762az.mo3855a(C1800w.f4343p);
                abstractC1762az.mo3861a(c1800w.f4358c);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3855a(C1800w.f4344q);
            abstractC1762az.mo3853a(c1800w.f4359d);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3855a(C1800w.f4345r);
            abstractC1762az.mo3853a(c1800w.f4360e);
            abstractC1762az.mo3867c();
            abstractC1762az.mo3855a(C1800w.f4346s);
            abstractC1762az.mo3853a(c1800w.f4361f);
            abstractC1762az.mo3867c();
            if (c1800w.f4362g != null) {
                abstractC1762az.mo3855a(C1800w.f4347t);
                abstractC1762az.mo3862a(c1800w.f4362g);
                abstractC1762az.mo3867c();
            }
            if (c1800w.f4363h != null) {
                abstractC1762az.mo3855a(C1800w.f4348u);
                abstractC1762az.mo3861a(c1800w.f4363h);
                abstractC1762az.mo3867c();
            }
            if (c1800w.f4364i != null) {
                abstractC1762az.mo3855a(C1800w.f4349v);
                abstractC1762az.mo3861a(c1800w.f4364i);
                abstractC1762az.mo3867c();
            }
            if (c1800w.m4128F()) {
                abstractC1762az.mo3855a(C1800w.f4350w);
                abstractC1762az.mo3853a(c1800w.f4365j);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.w$d */
    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class d implements InterfaceC1772bi {
        private d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public c mo3837b() {
            return new c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.w$c */
    /* JADX INFO: compiled from: UMEnvelope.java */
    private static class c extends AbstractC1774bk<C1800w> {
        private c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, C1800w c1800w) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3861a(c1800w.f4356a);
            c1769bf.mo3861a(c1800w.f4357b);
            c1769bf.mo3861a(c1800w.f4358c);
            c1769bf.mo3853a(c1800w.f4359d);
            c1769bf.mo3853a(c1800w.f4360e);
            c1769bf.mo3853a(c1800w.f4361f);
            c1769bf.mo3862a(c1800w.f4362g);
            c1769bf.mo3861a(c1800w.f4363h);
            c1769bf.mo3861a(c1800w.f4364i);
            BitSet bitSet = new BitSet();
            if (c1800w.m4128F()) {
                bitSet.set(0);
            }
            c1769bf.m3928a(bitSet, 1);
            if (c1800w.m4128F()) {
                c1769bf.mo3853a(c1800w.f4365j);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, C1800w c1800w) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1800w.f4356a = c1769bf.mo3892z();
            c1800w.m4135a(true);
            c1800w.f4357b = c1769bf.mo3892z();
            c1800w.m4139b(true);
            c1800w.f4358c = c1769bf.mo3892z();
            c1800w.m4143c(true);
            c1800w.f4359d = c1769bf.mo3889w();
            c1800w.m4146d(true);
            c1800w.f4360e = c1769bf.mo3889w();
            c1800w.m4151e(true);
            c1800w.f4361f = c1769bf.mo3889w();
            c1800w.m4153f(true);
            c1800w.f4362g = c1769bf.mo3849A();
            c1800w.m4154g(true);
            c1800w.f4363h = c1769bf.mo3892z();
            c1800w.m4157h(true);
            c1800w.f4364i = c1769bf.mo3892z();
            c1800w.m4159i(true);
            if (c1769bf.m3929b(1).get(0)) {
                c1800w.f4365j = c1769bf.mo3889w();
                c1800w.m4160j(true);
            }
        }
    }
}

package com.umeng.commonsdk.statistics.proto;

import android.support.v4.app.NotificationCompat;
import com.umeng.commonsdk.proguard.AbstractC1762az;
import com.umeng.commonsdk.proguard.AbstractC1773bj;
import com.umeng.commonsdk.proguard.AbstractC1774bk;
import com.umeng.commonsdk.proguard.C1743ag;
import com.umeng.commonsdk.proguard.C1749am;
import com.umeng.commonsdk.proguard.C1750an;
import com.umeng.commonsdk.proguard.C1754ar;
import com.umeng.commonsdk.proguard.C1756at;
import com.umeng.commonsdk.proguard.C1757au;
import com.umeng.commonsdk.proguard.C1764ba;
import com.umeng.commonsdk.proguard.C1766bc;
import com.umeng.commonsdk.proguard.C1768be;
import com.umeng.commonsdk.proguard.C1769bf;
import com.umeng.commonsdk.proguard.C1775bl;
import com.umeng.commonsdk.proguard.C1799v;
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

/* JADX INFO: loaded from: classes.dex */
public class Response implements InterfaceC1737aa<Response, EnumC1851e>, Serializable, Cloneable {
    private static final int __RESP_CODE_ISSET_ID = 0;
    public static final Map<EnumC1851e, C1749am> metaDataMap;
    private static final long serialVersionUID = -4549277923241195391L;
    private byte __isset_bitfield;
    public C1855d imprint;
    public String msg;
    private EnumC1851e[] optionals;
    public int resp_code;
    private static final C1768be STRUCT_DESC = new C1768be("Response");
    private static final C1757au RESP_CODE_FIELD_DESC = new C1757au("resp_code", (byte) 8, 1);
    private static final C1757au MSG_FIELD_DESC = new C1757au(NotificationCompat.CATEGORY_MESSAGE, (byte) 11, 2);
    private static final C1757au IMPRINT_FIELD_DESC = new C1757au(C1799v.f4278Q, (byte) 12, 3);
    private static final Map<Class<? extends InterfaceC1771bh>, InterfaceC1772bi> schemes = new HashMap();

    static {
        schemes.put(AbstractC1773bj.class, new C1848b());
        schemes.put(AbstractC1774bk.class, new C1850d());
        EnumMap enumMap = new EnumMap(EnumC1851e.class);
        enumMap.put(EnumC1851e.RESP_CODE, new C1749am("resp_code", (byte) 1, new C1750an((byte) 8)));
        enumMap.put(EnumC1851e.MSG, new C1749am(NotificationCompat.CATEGORY_MESSAGE, (byte) 2, new C1750an((byte) 11)));
        enumMap.put(EnumC1851e.IMPRINT, new C1749am(C1799v.f4278Q, (byte) 2, new C1754ar((byte) 12, C1855d.class)));
        metaDataMap = Collections.unmodifiableMap(enumMap);
        C1749am.m3842a(Response.class, metaDataMap);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.Response$e */
    public enum EnumC1851e implements InterfaceC1744ah {
        RESP_CODE(1, "resp_code"),
        MSG(2, NotificationCompat.CATEGORY_MESSAGE),
        IMPRINT(3, C1799v.f4278Q);


        /* JADX INFO: renamed from: d */
        private static final Map<String, EnumC1851e> f4629d = new HashMap();

        /* JADX INFO: renamed from: e */
        private final short f4631e;

        /* JADX INFO: renamed from: f */
        private final String f4632f;

        static {
            for (EnumC1851e enumC1851e : EnumSet.allOf(EnumC1851e.class)) {
                f4629d.put(enumC1851e.mo3807b(), enumC1851e);
            }
        }

        /* JADX INFO: renamed from: a */
        public static EnumC1851e m4510a(int i) {
            switch (i) {
                case 1:
                    return RESP_CODE;
                case 2:
                    return MSG;
                case 3:
                    return IMPRINT;
                default:
                    return null;
            }
        }

        /* JADX INFO: renamed from: b */
        public static EnumC1851e m4512b(int i) {
            EnumC1851e enumC1851eM4510a = m4510a(i);
            if (enumC1851eM4510a != null) {
                return enumC1851eM4510a;
            }
            throw new IllegalArgumentException("Field " + i + " doesn't exist!");
        }

        /* JADX INFO: renamed from: a */
        public static EnumC1851e m4511a(String str) {
            return f4629d.get(str);
        }

        EnumC1851e(short s, String str) {
            this.f4631e = s;
            this.f4632f = str;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: a */
        public short mo3806a() {
            return this.f4631e;
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1744ah
        /* JADX INFO: renamed from: b */
        public String mo3807b() {
            return this.f4632f;
        }
    }

    public Response() {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new EnumC1851e[]{EnumC1851e.MSG, EnumC1851e.IMPRINT};
    }

    public Response(int i) {
        this();
        this.resp_code = i;
        setResp_codeIsSet(true);
    }

    public Response(Response response) {
        this.__isset_bitfield = (byte) 0;
        this.optionals = new EnumC1851e[]{EnumC1851e.MSG, EnumC1851e.IMPRINT};
        this.__isset_bitfield = response.__isset_bitfield;
        this.resp_code = response.resp_code;
        if (response.isSetMsg()) {
            this.msg = response.msg;
        }
        if (response.isSetImprint()) {
            this.imprint = new C1855d(response.imprint);
        }
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public Response deepCopy() {
        return new Response(this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void clear() {
        setResp_codeIsSet(false);
        this.resp_code = 0;
        this.msg = null;
        this.imprint = null;
    }

    public int getResp_code() {
        return this.resp_code;
    }

    public Response setResp_code(int i) {
        this.resp_code = i;
        setResp_codeIsSet(true);
        return this;
    }

    public void unsetResp_code() {
        this.__isset_bitfield = C1801x.m4199b(this.__isset_bitfield, 0);
    }

    public boolean isSetResp_code() {
        return C1801x.m4195a(this.__isset_bitfield, 0);
    }

    public void setResp_codeIsSet(boolean z) {
        this.__isset_bitfield = C1801x.m4187a(this.__isset_bitfield, 0, z);
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String str) {
        this.msg = str;
        return this;
    }

    public void unsetMsg() {
        this.msg = null;
    }

    public boolean isSetMsg() {
        return this.msg != null;
    }

    public void setMsgIsSet(boolean z) {
        if (z) {
            return;
        }
        this.msg = null;
    }

    public C1855d getImprint() {
        return this.imprint;
    }

    public Response setImprint(C1855d c1855d) {
        this.imprint = c1855d;
        return this;
    }

    public void unsetImprint() {
        this.imprint = null;
    }

    public boolean isSetImprint() {
        return this.imprint != null;
    }

    public void setImprintIsSet(boolean z) {
        if (z) {
            return;
        }
        this.imprint = null;
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public EnumC1851e fieldForId(int i) {
        return EnumC1851e.m4510a(i);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void read(AbstractC1762az abstractC1762az) throws C1743ag {
        schemes.get(abstractC1762az.mo3918D()).mo3837b().mo3834b(abstractC1762az, this);
    }

    @Override // com.umeng.commonsdk.proguard.InterfaceC1737aa
    public void write(AbstractC1762az abstractC1762az) throws C1743ag {
        schemes.get(abstractC1762az.mo3918D()).mo3837b().mo3832a(abstractC1762az, this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("Response(");
        sb.append("resp_code:");
        sb.append(this.resp_code);
        if (isSetMsg()) {
            sb.append(", ");
            sb.append("msg:");
            if (this.msg == null) {
                sb.append("null");
            } else {
                sb.append(this.msg);
            }
        }
        if (isSetImprint()) {
            sb.append(", ");
            sb.append("imprint:");
            if (this.imprint == null) {
                sb.append("null");
            } else {
                sb.append(this.imprint);
            }
        }
        sb.append(")");
        return sb.toString();
    }

    public void validate() throws C1743ag {
        if (this.imprint != null) {
            this.imprint.m4648l();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        try {
            write(new C1756at(new C1775bl(objectOutputStream)));
        } catch (C1743ag e) {
            throw new IOException(e.getMessage());
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        try {
            this.__isset_bitfield = (byte) 0;
            read(new C1756at(new C1775bl(objectInputStream)));
        } catch (C1743ag e) {
            throw new IOException(e.getMessage());
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.Response$b */
    private static class C1848b implements InterfaceC1772bi {
        private C1848b() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C1847a mo3837b() {
            return new C1847a();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.Response$a */
    private static class C1847a extends AbstractC1773bj<Response> {
        private C1847a() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, Response response) throws C1743ag {
            abstractC1762az.mo3876j();
            while (true) {
                C1757au c1757auMo3878l = abstractC1762az.mo3878l();
                if (c1757auMo3878l.f4083b != 0) {
                    switch (c1757auMo3878l.f4084c) {
                        case 1:
                            if (c1757auMo3878l.f4083b == 8) {
                                response.resp_code = abstractC1762az.mo3889w();
                                response.setResp_codeIsSet(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 2:
                            if (c1757auMo3878l.f4083b == 11) {
                                response.msg = abstractC1762az.mo3892z();
                                response.setMsgIsSet(true);
                            } else {
                                C1766bc.m3924a(abstractC1762az, c1757auMo3878l.f4083b);
                            }
                            break;
                        case 3:
                            if (c1757auMo3878l.f4083b == 12) {
                                response.imprint = new C1855d();
                                response.imprint.read(abstractC1762az);
                                response.setImprintIsSet(true);
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
                    if (!response.isSetResp_code()) {
                        throw new C1764ba("Required field 'resp_code' was not found in serialized data! Struct: " + toString());
                    }
                    response.validate();
                    return;
                }
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, Response response) throws C1743ag {
            response.validate();
            abstractC1762az.mo3860a(Response.STRUCT_DESC);
            abstractC1762az.mo3855a(Response.RESP_CODE_FIELD_DESC);
            abstractC1762az.mo3853a(response.resp_code);
            abstractC1762az.mo3867c();
            if (response.msg != null && response.isSetMsg()) {
                abstractC1762az.mo3855a(Response.MSG_FIELD_DESC);
                abstractC1762az.mo3861a(response.msg);
                abstractC1762az.mo3867c();
            }
            if (response.imprint != null && response.isSetImprint()) {
                abstractC1762az.mo3855a(Response.IMPRINT_FIELD_DESC);
                response.imprint.write(abstractC1762az);
                abstractC1762az.mo3867c();
            }
            abstractC1762az.mo3869d();
            abstractC1762az.mo3866b();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.Response$d */
    private static class C1850d implements InterfaceC1772bi {
        private C1850d() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1772bi
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public C1849c mo3837b() {
            return new C1849c();
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.proto.Response$c */
    private static class C1849c extends AbstractC1774bk<Response> {
        private C1849c() {
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void mo3832a(AbstractC1762az abstractC1762az, Response response) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            c1769bf.mo3853a(response.resp_code);
            BitSet bitSet = new BitSet();
            if (response.isSetMsg()) {
                bitSet.set(0);
            }
            if (response.isSetImprint()) {
                bitSet.set(1);
            }
            c1769bf.m3928a(bitSet, 2);
            if (response.isSetMsg()) {
                c1769bf.mo3861a(response.msg);
            }
            if (response.isSetImprint()) {
                response.imprint.write(c1769bf);
            }
        }

        @Override // com.umeng.commonsdk.proguard.InterfaceC1771bh
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        public void mo3834b(AbstractC1762az abstractC1762az, Response response) throws C1743ag {
            C1769bf c1769bf = (C1769bf) abstractC1762az;
            response.resp_code = c1769bf.mo3889w();
            response.setResp_codeIsSet(true);
            BitSet bitSetM3929b = c1769bf.m3929b(2);
            if (bitSetM3929b.get(0)) {
                response.msg = c1769bf.mo3892z();
                response.setMsgIsSet(true);
            }
            if (bitSetM3929b.get(1)) {
                response.imprint = new C1855d();
                response.imprint.read(c1769bf);
                response.setImprintIsSet(true);
            }
        }
    }
}

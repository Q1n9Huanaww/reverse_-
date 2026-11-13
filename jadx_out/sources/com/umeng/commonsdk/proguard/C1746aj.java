package com.umeng.commonsdk.proguard;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.aj */
/* JADX INFO: compiled from: TSerializer.java */
/* JADX INFO: loaded from: classes.dex */
public class C1746aj {

    /* JADX INFO: renamed from: a */
    private final ByteArrayOutputStream f4014a;

    /* JADX INFO: renamed from: b */
    private final C1775bl f4015b;

    /* JADX INFO: renamed from: c */
    private AbstractC1762az f4016c;

    public C1746aj() {
        this(new C1756at.a());
    }

    public C1746aj(InterfaceC1765bb interfaceC1765bb) {
        this.f4014a = new ByteArrayOutputStream();
        this.f4015b = new C1775bl(this.f4014a);
        this.f4016c = interfaceC1765bb.mo3893a(this.f4015b);
    }

    /* JADX INFO: renamed from: a */
    public byte[] m3809a(InterfaceC1737aa interfaceC1737aa) throws C1743ag {
        this.f4014a.reset();
        interfaceC1737aa.write(this.f4016c);
        return this.f4014a.toByteArray();
    }

    /* JADX INFO: renamed from: a */
    public String m3808a(InterfaceC1737aa interfaceC1737aa, String str) throws C1743ag {
        try {
            return new String(m3809a(interfaceC1737aa), str);
        } catch (UnsupportedEncodingException unused) {
            throw new C1743ag("JVM DOES NOT SUPPORT ENCODING: " + str);
        }
    }

    /* JADX INFO: renamed from: b */
    public String m3810b(InterfaceC1737aa interfaceC1737aa) throws C1743ag {
        return new String(m3809a(interfaceC1737aa));
    }
}

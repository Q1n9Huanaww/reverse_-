package com.p004b.p005a.p006a.p011e;

import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.p004b.p005a.AbstractC0601au;
import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0582ab;
import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0595ao;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.C0600at;
import com.p004b.p005a.EnumC0592al;
import com.p004b.p005a.p006a.AbstractC0484a;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p005a.p006a.p008b.C0501g;
import com.p004b.p005a.p006a.p009c.C0512i;
import com.p004b.p005a.p006a.p009c.C0513j;
import com.p004b.p005a.p006a.p009c.C0515l;
import com.p004b.p005a.p006a.p009c.InterfaceC0506c;
import com.p004b.p015b.C0639i;
import com.p004b.p015b.C0644n;
import com.p004b.p015b.InterfaceC0653w;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/* JADX INFO: renamed from: com.b.a.a.e.h */
/* JADX INFO: compiled from: Http2Codec.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0545h implements InterfaceC0506c {

    /* JADX INFO: renamed from: b */
    private static final C0639i f321b = C0639i.m575a("connection");

    /* JADX INFO: renamed from: c */
    private static final C0639i f322c = C0639i.m575a("host");

    /* JADX INFO: renamed from: d */
    private static final C0639i f323d = C0639i.m575a("keep-alive");

    /* JADX INFO: renamed from: e */
    private static final C0639i f324e = C0639i.m575a("proxy-connection");

    /* JADX INFO: renamed from: f */
    private static final C0639i f325f = C0639i.m575a("transfer-encoding");

    /* JADX INFO: renamed from: g */
    private static final C0639i f326g = C0639i.m575a("te");

    /* JADX INFO: renamed from: h */
    private static final C0639i f327h = C0639i.m575a("encoding");

    /* JADX INFO: renamed from: i */
    private static final C0639i f328i = C0639i.m575a("upgrade");

    /* JADX INFO: renamed from: j */
    private static final List<C0639i> f329j = C0503c.m113a(f321b, f322c, f323d, f324e, f326g, f325f, f327h, f328i, C0540c.f290c, C0540c.f291d, C0540c.f292e, C0540c.f293f);

    /* JADX INFO: renamed from: k */
    private static final List<C0639i> f330k = C0503c.m113a(f321b, f322c, f323d, f324e, f326g, f325f, f327h, f328i);

    /* JADX INFO: renamed from: a */
    final C0501g f331a;

    /* JADX INFO: renamed from: l */
    private final C0589ai f332l;

    /* JADX INFO: renamed from: m */
    private final C0547j f333m;

    /* JADX INFO: renamed from: n */
    private C0527ab f334n;

    public C0545h(C0589ai c0589ai, C0501g c0501g, C0547j c0547j) {
        this.f332l = c0589ai;
        this.f331a = c0501g;
        this.f333m = c0547j;
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final InterfaceC0653w mo130a(C0595ao c0595ao, long j) {
        return this.f334n.m187e();
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final void mo132a(C0595ao c0595ao) {
        if (this.f334n != null) {
            return;
        }
        boolean z = c0595ao.m402d() != null;
        C0581aa c0581aaM401c = c0595ao.m401c();
        ArrayList arrayList = new ArrayList(c0581aaM401c.m321a() + 4);
        arrayList.add(new C0540c(C0540c.f290c, c0595ao.m400b()));
        arrayList.add(new C0540c(C0540c.f291d, C0513j.m154a(c0595ao.m398a())));
        String strM399a = c0595ao.m399a(HttpHeaders.HOST);
        if (strM399a != null) {
            arrayList.add(new C0540c(C0540c.f293f, strM399a));
        }
        arrayList.add(new C0540c(C0540c.f292e, c0595ao.m398a().m345b()));
        int iM321a = c0581aaM401c.m321a();
        for (int i = 0; i < iM321a; i++) {
            C0639i c0639iM575a = C0639i.m575a(c0581aaM401c.m322a(i).toLowerCase(Locale.US));
            if (!f329j.contains(c0639iM575a)) {
                arrayList.add(new C0540c(c0639iM575a, c0581aaM401c.m325b(i)));
            }
        }
        this.f334n = this.f333m.m259a(arrayList, z);
        this.f334n.f239f.mo595a(this.f332l.m373b(), TimeUnit.MILLISECONDS);
        this.f334n.f240g.mo595a(this.f332l.m374c(), TimeUnit.MILLISECONDS);
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final void mo131a() {
        this.f333m.f352p.m208b();
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: b */
    public final void mo133b() {
        this.f334n.m187e().close();
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final C0600at mo128a(boolean z) throws ProtocolException {
        List<C0540c> listM184c = this.f334n.m184c();
        C0582ab c0582ab = new C0582ab();
        int size = listM184c.size();
        C0582ab c0582ab2 = c0582ab;
        C0515l c0515lM160a = null;
        for (int i = 0; i < size; i++) {
            C0540c c0540c = listM184c.get(i);
            if (c0540c == null) {
                if (c0515lM160a != null && c0515lM160a.f206b == 100) {
                    c0582ab2 = new C0582ab();
                    c0515lM160a = null;
                }
            } else {
                C0639i c0639i = c0540c.f294g;
                String strMo581a = c0540c.f295h.mo581a();
                if (c0639i.equals(C0540c.f289b)) {
                    c0515lM160a = C0515l.m160a("HTTP/1.1 " + strMo581a);
                } else if (!f330k.contains(c0639i)) {
                    AbstractC0484a.f81a.mo39a(c0582ab2, c0639i.mo581a(), strMo581a);
                }
            }
        }
        if (c0515lM160a == null) {
            throw new ProtocolException("Expected ':status' header not present");
        }
        C0600at c0600atM432a = new C0600at().m433a(EnumC0592al.HTTP_2).m430a(c0515lM160a.f206b).m438a(c0515lM160a.f207c).m432a(c0582ab2.m327a());
        if (z && AbstractC0484a.f81a.mo35a(c0600atM432a) == 100) {
            return null;
        }
        return c0600atM432a;
    }

    @Override // com.p004b.p005a.p006a.p009c.InterfaceC0506c
    /* JADX INFO: renamed from: a */
    public final AbstractC0601au mo129a(C0599as c0599as) {
        return new C0512i(c0599as.m422d(), C0644n.m606a(new C0546i(this, this.f334n.m186d())));
    }
}

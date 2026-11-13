package com.p004b.p005a.p006a.p009c;

import com.p004b.p005a.AbstractC0601au;
import com.p004b.p005a.C0581aa;
import com.p004b.p005a.C0588ah;
import com.p004b.p015b.InterfaceC0638h;

/* JADX INFO: renamed from: com.b.a.a.c.i */
/* JADX INFO: compiled from: RealResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0512i extends AbstractC0601au {

    /* JADX INFO: renamed from: a */
    private final C0581aa f198a;

    /* JADX INFO: renamed from: b */
    private final InterfaceC0638h f199b;

    public C0512i(C0581aa c0581aa, InterfaceC0638h interfaceC0638h) {
        this.f198a = c0581aa;
        this.f199b = interfaceC0638h;
    }

    @Override // com.p004b.p005a.AbstractC0601au
    /* JADX INFO: renamed from: a */
    public final C0588ah mo151a() {
        String strM323a = this.f198a.m323a("Content-Type");
        if (strM323a != null) {
            return C0588ah.m367a(strM323a);
        }
        return null;
    }

    @Override // com.p004b.p005a.AbstractC0601au
    /* JADX INFO: renamed from: b */
    public final long mo152b() {
        return C0509f.m138a(this.f198a);
    }

    @Override // com.p004b.p005a.AbstractC0601au
    /* JADX INFO: renamed from: c */
    public final InterfaceC0638h mo153c() {
        return this.f199b;
    }
}

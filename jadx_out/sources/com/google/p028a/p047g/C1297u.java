package com.google.p028a.p047g;

import com.google.p028a.C1368w;
import com.google.p028a.EnumC1138a;
import com.google.p028a.EnumC1245g;
import com.google.p028a.InterfaceC1367v;
import com.google.p028a.p035c.C1194b;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.util.Map;

/* JADX INFO: renamed from: com.google.a.g.u */
/* JADX INFO: compiled from: UPCAWriter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1297u implements InterfaceC1367v {

    /* JADX INFO: renamed from: a */
    private final C1286j f2521a = new C1286j();

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1516a(String str, EnumC1138a enumC1138a, int i, int i2) throws C1368w {
        return mo1517a(str, enumC1138a, i, i2, null);
    }

    @Override // com.google.p028a.InterfaceC1367v
    /* JADX INFO: renamed from: a */
    public final C1194b mo1517a(String str, EnumC1138a enumC1138a, int i, int i2, Map<EnumC1245g, ?> map) throws C1368w {
        if (enumC1138a != EnumC1138a.UPC_A) {
            throw new IllegalArgumentException("Can only encode UPC-A, but got ".concat(String.valueOf(enumC1138a)));
        }
        return this.f2521a.mo1517a(AccountManager.TYPE_LOGIN.concat(String.valueOf(str)), EnumC1138a.EAN_13, i, i2, map);
    }
}

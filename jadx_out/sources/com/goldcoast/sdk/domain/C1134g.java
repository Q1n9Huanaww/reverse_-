package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.InterfaceC0611h;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.g */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class C1134g implements InterfaceC0611h {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ List f1850a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ ConditionVariable f1851b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ EntryPoint f1852c;

    C1134g(EntryPoint entryPoint, List list, ConditionVariable conditionVariable) {
        this.f1852c = entryPoint;
        this.f1850a = list;
        this.f1851b = conditionVariable;
    }

    @Override // com.p004b.p005a.InterfaceC0611h
    /* JADX INFO: renamed from: a */
    public final void mo462a(C0599as c0599as) {
        String strM444d = c0599as.m423e().m444d();
        if (!TextUtils.isEmpty(strM444d)) {
            try {
                EntryPoint.m1447a(this.f1852c, strM444d, this.f1850a);
            } catch (Exception e) {
                int i = 3;
                String str = String.format("Exception:\n %s\n", e.getMessage());
                if (this.f1852c.f1832r == 4) {
                    str = String.format("Exception:\n %s\n$$$ failed", e.getMessage());
                    i = 1;
                }
                this.f1852c.m1460b(str, i);
            }
        }
        this.f1851b.open();
    }

    @Override // com.p004b.p005a.InterfaceC0611h
    /* JADX INFO: renamed from: a */
    public final void mo463a(IOException iOException) {
        new HashMap().put("stack", this.f1852c.getStackString(iOException));
        if (this.f1852c.f1832r != 4) {
            this.f1852c.m1460b(this.f1852c.getStackString(iOException), 3);
        } else {
            this.f1852c.m1460b(this.f1852c.getStackString(iOException) + "\n$$$ failed\n", 1);
        }
        this.f1851b.open();
    }
}

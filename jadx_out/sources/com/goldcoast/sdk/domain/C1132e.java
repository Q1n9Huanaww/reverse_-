package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.InterfaceC0611h;
import java.io.IOException;
import java.util.HashMap;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.e */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class C1132e implements InterfaceC0611h {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ConditionVariable f1846a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ boolean[] f1847b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ EntryPoint f1848c;

    C1132e(EntryPoint entryPoint, ConditionVariable conditionVariable, boolean[] zArr) {
        this.f1848c = entryPoint;
        this.f1846a = conditionVariable;
        this.f1847b = zArr;
    }

    @Override // com.p004b.p005a.InterfaceC0611h
    /* JADX INFO: renamed from: a */
    public final void mo462a(C0599as c0599as) {
        String strM444d = c0599as.m423e().m444d();
        if (!TextUtils.isEmpty(strM444d)) {
            try {
                EntryPoint.m1445a(this.f1848c, strM444d);
            } catch (Exception e) {
                int i = 3;
                String str = String.format("Exception:\n %s\n", e.getMessage());
                if (this.f1848c.f1832r == 4) {
                    str = String.format("Exception:\n %s\n$$$ failed", e.getMessage());
                    i = 1;
                }
                this.f1848c.m1460b(str, i);
            }
        }
        this.f1846a.open();
    }

    @Override // com.p004b.p005a.InterfaceC0611h
    /* JADX INFO: renamed from: a */
    public final void mo463a(IOException iOException) {
        new HashMap().put("stack", this.f1848c.getStackString(iOException));
        this.f1847b[0] = true;
        if (this.f1848c.f1832r != 4) {
            this.f1848c.m1460b(this.f1848c.getStackString(iOException), 3);
        } else {
            this.f1848c.m1460b(this.f1848c.getStackString(iOException) + "\n$$$ failed\n", 1);
        }
        this.f1846a.open();
    }
}

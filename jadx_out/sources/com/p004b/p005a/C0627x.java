package com.p004b.p005a;

import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0636f;
import com.p004b.p015b.InterfaceC0637g;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.x */
/* JADX INFO: compiled from: FormBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0627x extends AbstractC0597aq {

    /* JADX INFO: renamed from: a */
    private static final C0588ah f790a = C0588ah.m367a("application/x-www-form-urlencoded");

    /* JADX INFO: renamed from: b */
    private final List<String> f791b;

    /* JADX INFO: renamed from: c */
    private final List<String> f792c;

    C0627x(List<String> list, List<String> list2) {
        this.f791b = C0503c.m112a(list);
        this.f792c = C0503c.m112a(list2);
    }

    @Override // com.p004b.p005a.AbstractC0597aq
    /* JADX INFO: renamed from: a */
    public final C0588ah mo415a() {
        return f790a;
    }

    @Override // com.p004b.p005a.AbstractC0597aq
    /* JADX INFO: renamed from: b */
    public final long mo417b() {
        return m502a(null, true);
    }

    @Override // com.p004b.p005a.AbstractC0597aq
    /* JADX INFO: renamed from: a */
    public final void mo416a(InterfaceC0637g interfaceC0637g) {
        m502a(interfaceC0637g, false);
    }

    /* JADX INFO: renamed from: a */
    private long m502a(InterfaceC0637g interfaceC0637g, boolean z) {
        C0636f c0636fMo546c;
        if (z) {
            c0636fMo546c = new C0636f();
        } else {
            c0636fMo546c = interfaceC0637g.mo546c();
        }
        int size = this.f791b.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                c0636fMo546c.mo562h(38);
            }
            c0636fMo546c.mo542b(this.f791b.get(i));
            c0636fMo546c.mo562h(61);
            c0636fMo546c.mo542b(this.f792c.get(i));
        }
        if (!z) {
            return 0L;
        }
        long jM540b = c0636fMo546c.m540b();
        c0636fMo546c.m572o();
        return jM540b;
    }
}

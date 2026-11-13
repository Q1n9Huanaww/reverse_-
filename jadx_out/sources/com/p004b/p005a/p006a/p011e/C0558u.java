package com.p004b.p005a.p006a.p011e;

import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import com.p004b.p005a.p006a.C0503c;
import com.p004b.p015b.C0639i;
import com.p004b.p015b.InterfaceC0638h;
import java.io.IOException;
import java.util.List;

/* JADX INFO: renamed from: com.b.a.a.e.u */
/* JADX INFO: compiled from: Http2Connection.java */
/* JADX INFO: loaded from: classes.dex */
final class C0558u extends AbstractRunnableC0494b implements InterfaceC0526aa {

    /* JADX INFO: renamed from: a */
    final C0562y f392a;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ C0547j f393c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0558u(C0547j c0547j, C0562y c0562y) {
        super("OkHttp %s", c0547j.f341e);
        this.f393c = c0547j;
        this.f392a = c0562y;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [com.b.a.a.e.b] */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v4, types: [com.b.a.a.e.j] */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() throws Throwable {
        EnumC0539b enumC0539b = EnumC0539b.INTERNAL_ERROR;
        EnumC0539b enumC0539b2 = EnumC0539b.INTERNAL_ERROR;
        try {
            try {
                this.f392a.m280a(this);
                while (this.f392a.m281a(false, this)) {
                }
                EnumC0539b enumC0539b3 = EnumC0539b.NO_ERROR;
                try {
                    try {
                        enumC0539b = EnumC0539b.CANCEL;
                        enumC0539b2 = this.f393c;
                    } catch (IOException unused) {
                        enumC0539b3 = EnumC0539b.PROTOCOL_ERROR;
                        enumC0539b = EnumC0539b.PROTOCOL_ERROR;
                        enumC0539b2 = this.f393c;
                    }
                    enumC0539b2.m266a(enumC0539b3, enumC0539b);
                } catch (Throwable th) {
                    EnumC0539b enumC0539b4 = enumC0539b3;
                    th = th;
                    enumC0539b = enumC0539b4;
                    try {
                        this.f393c.m266a(enumC0539b, enumC0539b2);
                    } catch (IOException unused2) {
                    }
                    C0503c.m116a(this.f392a);
                    throw th;
                }
            } catch (IOException unused3) {
            }
        } catch (IOException unused4) {
        }
        C0503c.m116a(this.f392a);
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo174a(boolean z, int i, InterfaceC0638h interfaceC0638h, int i2) throws IOException {
        if (C0547j.m256d(i)) {
            this.f393c.m262a(i, interfaceC0638h, i2, z);
            return;
        }
        C0527ab c0527abM258a = this.f393c.m258a(i);
        if (c0527abM258a == null) {
            this.f393c.m261a(i, EnumC0539b.PROTOCOL_ERROR);
            interfaceC0638h.mo557f(i2);
        } else {
            c0527abM258a.m179a(interfaceC0638h, i2);
            if (z) {
                c0527abM258a.m188f();
            }
        }
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo175a(boolean z, int i, List<C0540c> list) {
        if (C0547j.m256d(i)) {
            this.f393c.m264a(i, list, z);
            return;
        }
        synchronized (this.f393c) {
            if (this.f393c.f344h) {
                return;
            }
            C0527ab c0527abM258a = this.f393c.m258a(i);
            if (c0527abM258a == null) {
                if (i <= this.f393c.f342f) {
                    return;
                }
                if (i % 2 == this.f393c.f343g % 2) {
                    return;
                }
                C0527ab c0527ab = new C0527ab(i, this.f393c, false, z, list);
                this.f393c.f342f = i;
                this.f393c.f340d.put(Integer.valueOf(i), c0527ab);
                C0547j.f336a.execute(new C0559v(this, "OkHttp %s stream %d", new Object[]{this.f393c.f341e, Integer.valueOf(i)}, c0527ab));
                return;
            }
            c0527abM258a.m180a(list);
            if (z) {
                c0527abM258a.m188f();
            }
        }
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo169a(int i, EnumC0539b enumC0539b) {
        if (C0547j.m256d(i)) {
            this.f393c.m271c(i, enumC0539b);
            return;
        }
        C0527ab c0527abM267b = this.f393c.m267b(i);
        if (c0527abM267b != null) {
            c0527abM267b.m185c(enumC0539b);
        }
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo172a(C0537al c0537al) {
        int i;
        C0527ab[] c0527abArr;
        long j;
        synchronized (this.f393c) {
            int iM230d = this.f393c.f349m.m230d();
            C0537al c0537al2 = this.f393c.f349m;
            for (int i2 = 0; i2 < 10; i2++) {
                if (c0537al.m225a(i2)) {
                    c0537al2.m224a(i2, c0537al.m227b(i2));
                }
            }
            C0547j.f336a.execute(new C0561x(this, "OkHttp %s ACK Settings", new Object[]{this.f393c.f341e}, c0537al));
            int iM230d2 = this.f393c.f349m.m230d();
            c0527abArr = null;
            if (iM230d2 == -1 || iM230d2 == iM230d) {
                j = 0;
            } else {
                j = iM230d2 - iM230d;
                if (!this.f393c.f350n) {
                    C0547j c0547j = this.f393c;
                    c0547j.f347k += j;
                    if (j > 0) {
                        c0547j.notifyAll();
                    }
                    this.f393c.f350n = true;
                }
                if (!this.f393c.f340d.isEmpty()) {
                    c0527abArr = (C0527ab[]) this.f393c.f340d.values().toArray(new C0527ab[this.f393c.f340d.size()]);
                }
            }
            C0547j.f336a.execute(new C0560w(this, "OkHttp %s settings", this.f393c.f341e));
        }
        if (c0527abArr == null || j == 0) {
            return;
        }
        for (C0527ab c0527ab : c0527abArr) {
            synchronized (c0527ab) {
                c0527ab.m177a(j);
            }
        }
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo173a(boolean z, int i, int i2) {
        if (z) {
            C0534ai c0534aiM270c = this.f393c.m270c(i);
            if (c0534aiM270c != null) {
                c0534aiM270c.m217b();
                return;
            }
            return;
        }
        C0547j c0547j = this.f393c;
        C0547j.f336a.execute(new C0550m(c0547j, "OkHttp %s ping %08x%08x", new Object[]{c0547j.f341e, Integer.valueOf(i), Integer.valueOf(i2)}, i, i2));
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo170a(int i, C0639i c0639i) {
        C0527ab[] c0527abArr;
        c0639i.mo590g();
        synchronized (this.f393c) {
            c0527abArr = (C0527ab[]) this.f393c.f340d.values().toArray(new C0527ab[this.f393c.f340d.size()]);
            this.f393c.f344h = true;
        }
        for (C0527ab c0527ab : c0527abArr) {
            if (c0527ab.f236c > i && c0527ab.m183b()) {
                c0527ab.m185c(EnumC0539b.REFUSED_STREAM);
                this.f393c.m267b(c0527ab.f236c);
            }
        }
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo168a(int i, long j) {
        if (i == 0) {
            synchronized (this.f393c) {
                this.f393c.f347k += j;
                this.f393c.notifyAll();
            }
            return;
        }
        C0527ab c0527abM258a = this.f393c.m258a(i);
        if (c0527abM258a != null) {
            synchronized (c0527abM258a) {
                c0527abM258a.m177a(j);
            }
        }
    }

    @Override // com.p004b.p005a.p006a.p011e.InterfaceC0526aa
    /* JADX INFO: renamed from: a */
    public final void mo171a(int i, List<C0540c> list) {
        this.f393c.m263a(i, list);
    }
}

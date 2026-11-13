package com.p004b.p005a;

import android.support.v4.app.NotificationCompat;
import com.p004b.p005a.p006a.AbstractRunnableC0494b;
import com.p004b.p005a.p006a.p013g.C0573h;
import java.io.IOException;

/* JADX INFO: renamed from: com.b.a.an */
/* JADX INFO: compiled from: RealCall.java */
/* JADX INFO: loaded from: classes.dex */
final class C0594an extends AbstractRunnableC0494b {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C0593am f546a;

    /* JADX INFO: renamed from: c */
    private final InterfaceC0611h f547c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    C0594an(C0593am c0593am, InterfaceC0611h interfaceC0611h) {
        super("OkHttp %s", c0593am.m395b());
        this.f546a = c0593am;
        this.f547c = interfaceC0611h;
    }

    /* JADX INFO: renamed from: a */
    final String m397a() {
        return this.f546a.f543c.f548a.f453b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v10, types: [com.b.a.u] */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v14, types: [com.b.a.u] */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v2 */
    /* JADX WARN: Type inference failed for: r0v4 */
    @Override // com.p004b.p005a.p006a.AbstractRunnableC0494b
    /* JADX INFO: renamed from: b */
    public final void mo62b() {
        IOException e;
        ?? r0;
        ?? r1;
        ?? r2 = 1;
        try {
            try {
                C0599as c0599asM396c = this.f546a.m396c();
                try {
                    if (this.f546a.f542b.m159a()) {
                        this.f547c.mo463a(new IOException("Canceled"));
                    } else {
                        this.f547c.mo462a(c0599asM396c);
                    }
                    r2 = this.f546a.f541a.f485c;
                    r1 = r2;
                } catch (IOException e2) {
                    e = e2;
                    r0 = r2;
                    if (r0 != 0) {
                        C0573h c0573hM307b = C0573h.m307b();
                        StringBuilder sb = new StringBuilder("Callback failure for ");
                        C0593am c0593am = this.f546a;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(c0593am.f542b.m159a() ? "canceled " : "");
                        sb2.append(c0593am.f544d ? "web socket" : NotificationCompat.CATEGORY_CALL);
                        sb2.append(" to ");
                        sb2.append(c0593am.m395b());
                        sb.append(sb2.toString());
                        c0573hM307b.mo287a(4, sb.toString(), e);
                    } else {
                        this.f547c.mo463a(e);
                    }
                    r1 = this.f546a.f541a.f485c;
                }
            } catch (Throwable th) {
                this.f546a.f541a.f485c.m500b(this);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            r0 = 0;
        }
        r1.m500b(this);
    }
}

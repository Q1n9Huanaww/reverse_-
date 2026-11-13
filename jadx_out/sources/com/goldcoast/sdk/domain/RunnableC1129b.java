package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import com.goldcoast.sdk.p027c.C1125g;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.b */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC1129b implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ ConditionVariable f1842a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ EntryPoint f1843b;

    RunnableC1129b(EntryPoint entryPoint, ConditionVariable conditionVariable) {
        this.f1843b = entryPoint;
        this.f1842a = conditionVariable;
    }

    /* JADX WARN: Code duplicated, block: B:28:0x005d  */
    @Override // java.lang.Runnable
    public final void run() {
        EntryPoint.m1472h(this.f1843b);
        String str = "timeout";
        for (int i = 0; i < EntryPoint.f1818j; i++) {
            if (this.f1843b.getStatus()) {
                str = "ok";
                break;
            }
            if (!this.f1843b.f1826D) {
                str = "failed";
                break;
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                ThrowableExtension.printStackTrace(e);
            }
            C1125g.m1428a();
            C1125g.m1430a("timer: loop");
        }
        switch (str) {
            case "ok":
                this.f1843b.m1460b("$$$ success", 0);
                break;
            case "failed":
                C1125g.m1428a();
                C1125g.m1430a("timer: $$$ failed");
                break;
            default:
                C1125g.m1428a();
                C1125g.m1430a("timer: $$$ timeout");
                break;
        }
        this.f1842a.open();
    }
}

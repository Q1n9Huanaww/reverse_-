package com.goldcoast.sdk.domain;

import android.annotation.TargetApi;
import android.util.Pair;
import com.goldcoast.sdk.p025a.C1113d;
import com.goldcoast.sdk.p027c.C1125g;
import com.p001a.p002a.p003a.C0446a;
import com.p001a.p002a.p003a.C0447b;
import java.io.File;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.j */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC1137j implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1113d f1859a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ EntryPoint f1860b;

    RunnableC1137j(EntryPoint entryPoint, C1113d c1113d) {
        this.f1860b = entryPoint;
        this.f1859a = c1113d;
    }

    @Override // java.lang.Runnable
    @TargetApi(5)
    public final void run() {
        this.f1860b.f1826D = true;
        if (this.f1860b.f1828F != null) {
            this.f1860b.f1828F = null;
        }
        EntryPoint.m1468f(this.f1860b);
        C1125g.m1428a();
        C1125g.m1431b("######\n" + String.format("In <<<<<< %d  >>>>>> \n", Integer.valueOf(this.f1860b.f1833s)));
        this.f1860b.m1460b(String.format("正在计算第  <%d>  个$$$方案 请等待...", Integer.valueOf(this.f1860b.f1833s)), 2);
        this.f1860b.m1460b(String.format("key:%s\norder:%d name: %s", this.f1859a.m1378e(), Integer.valueOf(this.f1859a.m1377d()), this.f1859a.m1372a()), 2);
        StringBuilder sb = new StringBuilder();
        this.f1859a.m1375b(this.f1859a.m1374b().replace("\n", ""));
        try {
            C0446a c0446aM12a = C0447b.m12a("sh", String.format("%s %d %s\n", EntryPoint.m1442a(this.f1859a.m1372a(), this.f1859a.m1376c()), 2, this.f1859a.m1374b()));
            C1125g.m1428a();
            C1125g.m1430a(c0446aM12a.m11a());
            Thread.sleep(6000L);
        } catch (Exception e) {
            this.f1860b.m1460b(String.format("A9DDDF2A4F7D94594EC2EA98407A410E1 exception: %s", e.getMessage()), 2);
        }
        File file = new File(EntryPoint.extractDir.getAbsolutePath() + File.separator + this.f1859a.m1372a());
        if (file.exists() && file.delete()) {
            C1125g.m1428a();
            C1125g.m1430a("delete file");
        }
        this.f1860b.f1828F = new Pair("no", sb.toString());
        this.f1860b.f1826D = false;
        EntryPoint.m1472h(this.f1860b);
    }
}

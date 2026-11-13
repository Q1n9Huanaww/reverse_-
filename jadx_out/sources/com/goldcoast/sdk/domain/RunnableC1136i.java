package com.goldcoast.sdk.domain;

import android.text.TextUtils;
import com.goldcoast.sdk.p027c.C1126h;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import org.json.JSONArray;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.i */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class RunnableC1136i implements Runnable {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ EntryPoint f1858a;

    RunnableC1136i(EntryPoint entryPoint) {
        this.f1858a = entryPoint;
    }

    @Override // java.lang.Runnable
    public final void run() {
        C1126h.m1432a();
        String strM1433a = C1126h.m1433a("refer");
        if (TextUtils.isEmpty(strM1433a)) {
            return;
        }
        try {
            JSONArray jSONArray = new JSONArray(strM1433a);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    this.f1858a.m1450a(jSONArray.get(i).toString(), i, 2);
                }
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

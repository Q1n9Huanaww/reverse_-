package com.goldcoast.sdk.domain;

import android.os.ConditionVariable;
import android.text.TextUtils;
import com.goldcoast.sdk.p027c.C1119a;
import com.goldcoast.sdk.p027c.C1125g;
import com.p004b.p005a.C0599as;
import com.p004b.p005a.InterfaceC0611h;
import java.io.IOException;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.h */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class C1135h implements InterfaceC0611h {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ int f1853a;

    /* JADX INFO: renamed from: b */
    final /* synthetic */ String f1854b;

    /* JADX INFO: renamed from: c */
    final /* synthetic */ ConditionVariable f1855c;

    /* JADX INFO: renamed from: d */
    final /* synthetic */ int f1856d;

    /* JADX INFO: renamed from: e */
    final /* synthetic */ EntryPoint f1857e;

    C1135h(EntryPoint entryPoint, int i, String str, ConditionVariable conditionVariable, int i2) {
        this.f1857e = entryPoint;
        this.f1853a = i;
        this.f1854b = str;
        this.f1855c = conditionVariable;
        this.f1856d = i2;
    }

    @Override // com.p004b.p005a.InterfaceC0611h
    /* JADX INFO: renamed from: a */
    public final void mo462a(C0599as c0599as) {
        String strM1405b;
        String strM444d = c0599as.m423e().m444d();
        HashMap map = new HashMap();
        C1125g.m1428a();
        C1125g.m1431b("######\n" + String.format("<feedBackP.Enc>:%s\n", strM444d));
        if (TextUtils.isEmpty(strM444d) && this.f1853a == 1) {
            map.put("message", "response is null");
            EntryPoint.m1449a(this.f1854b);
            this.f1855c.open();
            return;
        }
        try {
            strM1405b = C1119a.m1405b(new JSONObject(strM444d).optString("body", ""));
        } catch (JSONException e) {
            map.put("message", this.f1857e.getStackString(e));
            strM1405b = null;
        }
        if (TextUtils.isEmpty(strM1405b) && c0599as.m420b() != 200 && this.f1853a == 1) {
            EntryPoint.m1449a(this.f1854b);
            this.f1855c.open();
            return;
        }
        try {
            if (new JSONObject(strM1405b).optString("code", "").equals("200")) {
                if (this.f1853a == 2) {
                    EntryPoint.m1444a(this.f1856d);
                }
            } else if (this.f1853a == 1) {
                EntryPoint.m1449a(this.f1854b);
                map.put("message", "code not equals 200");
            }
        } catch (Exception e2) {
            map.put("stack", this.f1857e.getStackString(e2));
        }
        this.f1855c.open();
    }

    @Override // com.p004b.p005a.InterfaceC0611h
    /* JADX INFO: renamed from: a */
    public final void mo463a(IOException iOException) {
        new HashMap();
        if (this.f1853a == 1) {
            EntryPoint.m1449a(this.f1854b);
        }
        this.f1857e.m1460b(this.f1857e.getStackString(iOException), 3);
        this.f1855c.open();
    }
}

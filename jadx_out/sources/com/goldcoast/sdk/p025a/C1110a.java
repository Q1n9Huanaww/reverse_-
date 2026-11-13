package com.goldcoast.sdk.p025a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.goldcoast.sdk.a.a */
/* JADX INFO: compiled from: CPUInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1110a extends AbstractC1112c<C1110a> {

    /* JADX INFO: renamed from: a */
    private String f1713a = AccountManager.TYPE_LOGIN;

    /* JADX INFO: renamed from: b */
    private String f1714b;

    /* JADX INFO: renamed from: c */
    private String f1715c;

    /* JADX INFO: renamed from: d */
    private String f1716d;

    /* JADX INFO: renamed from: e */
    private String f1717e;

    /* JADX INFO: renamed from: f */
    private String f1718f;

    /* JADX INFO: renamed from: g */
    private String f1719g;

    /* JADX INFO: renamed from: h */
    private String f1720h;

    /* JADX INFO: renamed from: i */
    private String f1721i;

    /* JADX INFO: renamed from: j */
    private String f1722j;

    /* JADX INFO: renamed from: k */
    private String f1723k;

    public C1110a() {
    }

    public C1110a(Map map) {
        m1358a(map);
    }

    /* JADX INFO: renamed from: a */
    private C1110a m1358a(Map<String, String> map) {
        Iterator<String> it2 = map.keySet().iterator();
        if (it2 == null || !it2.hasNext()) {
            return null;
        }
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.replace(" ", "").toLowerCase().contains("processorcnt")) {
                this.f1713a = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("modelname")) {
                this.f1714b = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("features")) {
                this.f1715c = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("cpuimplementer")) {
                this.f1716d = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("cpuarchitecture")) {
                this.f1717e = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("cpuvariant")) {
                this.f1718f = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("cpupart")) {
                this.f1719g = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("cpurevision")) {
                this.f1720h = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("hardware")) {
                this.f1721i = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("revision")) {
                this.f1722j = map.get(next);
            } else if (next.replace(" ", "").toLowerCase().contains("serial")) {
                this.f1723k = map.get(next);
            }
        }
        try {
            this.f1713a = String.valueOf(Integer.valueOf(this.f1713a).intValue() + 1);
        } catch (NumberFormatException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return this;
    }

    /* JADX INFO: renamed from: a */
    public final JSONObject m1359a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("processorcnt", this.f1713a == null ? "" : this.f1713a);
            jSONObject.put("modelname", this.f1714b == null ? "" : this.f1714b);
            jSONObject.put("features", this.f1715c == null ? "" : this.f1715c);
            jSONObject.put("cpuimplementer", this.f1716d == null ? "" : this.f1716d);
            jSONObject.put("cpuarchitecture", this.f1717e == null ? "" : this.f1717e);
            jSONObject.put("cpuvariant", this.f1718f == null ? "" : this.f1718f);
            jSONObject.put("cpupart", this.f1719g == null ? "" : this.f1719g);
            jSONObject.put("cpurevision", this.f1720h == null ? "" : this.f1720h);
            jSONObject.put("hardware", this.f1721i == null ? "" : this.f1721i);
            jSONObject.put("revision", this.f1722j == null ? "" : this.f1722j);
            jSONObject.put("serial", this.f1723k == null ? "" : this.f1723k);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }
}

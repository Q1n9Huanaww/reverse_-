package com.goldcoast.sdk.p025a;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.goldcoast.sdk.a.e */
/* JADX INFO: compiled from: Phone.java */
/* JADX INFO: loaded from: classes.dex */
public final class C1114e extends AbstractC1112c<C1114e> {

    /* JADX INFO: renamed from: a */
    private String f1740a;

    /* JADX INFO: renamed from: b */
    private String f1741b;

    /* JADX INFO: renamed from: c */
    private String f1742c;

    /* JADX INFO: renamed from: d */
    private String f1743d;

    /* JADX INFO: renamed from: e */
    private String f1744e;

    /* JADX INFO: renamed from: f */
    private String f1745f;

    /* JADX INFO: renamed from: g */
    private String f1746g;

    /* JADX INFO: renamed from: h */
    private String f1747h;

    /* JADX INFO: renamed from: i */
    private String f1748i;

    /* JADX INFO: renamed from: j */
    private String f1749j;

    /* JADX INFO: renamed from: k */
    private String f1750k;

    /* JADX INFO: renamed from: l */
    private String f1751l;

    /* JADX INFO: renamed from: m */
    private String f1752m;

    /* JADX INFO: renamed from: n */
    private String f1753n;

    /* JADX INFO: renamed from: o */
    private String f1754o;

    /* JADX INFO: renamed from: p */
    private String f1755p;

    /* JADX INFO: renamed from: q */
    private String f1756q;

    /* JADX INFO: renamed from: r */
    private String f1757r;

    /* JADX INFO: renamed from: s */
    private String f1758s;

    /* JADX INFO: renamed from: t */
    private String f1759t;

    /* JADX INFO: renamed from: u */
    private String f1760u;

    /* JADX INFO: renamed from: v */
    private String f1761v;

    /* JADX INFO: renamed from: w */
    private String f1762w;

    /* JADX INFO: renamed from: x */
    private String f1763x;

    /* JADX INFO: renamed from: y */
    private String f1764y;

    public C1114e() {
    }

    public C1114e(Map<String, String> map) {
        Iterator<String> it2 = map.keySet().iterator();
        if (it2 == null || !it2.hasNext()) {
            return;
        }
        while (it2.hasNext()) {
            String next = it2.next();
            if (next.equals("ro.product.brand")) {
                this.f1740a = map.get(next);
            } else if (next.equals("ro.product.name")) {
                this.f1741b = map.get(next);
            } else if (next.equals("ro.product.model")) {
                this.f1742c = map.get(next);
            } else if (next.equals("ro.build.fingerprint")) {
                this.f1743d = map.get(next);
            } else if (next.equals("ro.build.version.sdk")) {
                this.f1744e = map.get(next);
            } else if (next.equals("ro.build.version.release")) {
                this.f1745f = map.get(next);
            } else if (next.equals("ro.build.date")) {
                this.f1746g = map.get(next);
            } else if (next.equals("ro.build.date.utc")) {
                this.f1747h = map.get(next);
            } else if (next.equals("ro.boot.cpuid")) {
                this.f1748i = map.get(next);
            } else if (next.equals("ro.btconfig.vendor")) {
                this.f1749j = map.get(next);
            } else if (next.equals("persist.sys.timezone")) {
                this.f1750k = map.get(next);
            } else if (next.equals("persist.sys.country")) {
                this.f1751l = map.get(next);
            } else if (next.equals("persist.sys.language")) {
                this.f1752m = map.get(next);
            } else if (next.equals("persist.sys.dalvik.vm.lib")) {
                this.f1753n = map.get(next);
            } else if (next.equals("ro.build.description")) {
                this.f1754o = map.get(next);
            } else if (next.equals("ro.runtime.firstboot")) {
                this.f1755p = map.get(next);
            } else if (next.equals("ro.serialno")) {
                this.f1756q = map.get(next);
            } else if (next.equals("ro.hardware")) {
                this.f1757r = map.get(next);
            } else if (next.equals("ro.product.board")) {
                this.f1758s = map.get(next);
            } else if (next.equals("ro.product.locale.language")) {
                this.f1759t = map.get(next);
            } else if (next.equals("ro.product.locale.region")) {
                this.f1760u = map.get(next);
            } else if (next.equals("ro.product.cpu.abi")) {
                this.f1761v = map.get(next);
            } else if (next.equals("ro.board.platform")) {
                this.f1762w = map.get(next);
            } else if (next.equals("ro.build.selinux")) {
                this.f1763x = map.get(next);
            } else if (next.equals("ro.build.selinux.enforce")) {
                this.f1764y = map.get(next);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public final JSONObject m1380a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ro.product.brand", this.f1740a);
            jSONObject.put("ro.product.name", this.f1741b);
            jSONObject.put("ro.product.model", this.f1742c);
            jSONObject.put("ro.build.fingerprint", this.f1743d);
            jSONObject.put("ro.build.version.sdk", this.f1744e);
            jSONObject.put("ro.build.version.release", this.f1745f);
            jSONObject.put("ro.build.date", this.f1746g);
            jSONObject.put("ro.build.date.utc", this.f1747h);
            jSONObject.put("ro.boot.cpuid", this.f1748i);
            jSONObject.put("ro.btconfig.vendor", this.f1749j);
            jSONObject.put("persist.sys.timezone", this.f1750k);
            jSONObject.put("persist.sys.country", this.f1751l);
            jSONObject.put("persist.sys.language", this.f1752m);
            jSONObject.put("persist.sys.dalvik.vm.lib", this.f1753n);
            jSONObject.put("ro.build.description", this.f1754o);
            jSONObject.put("ro.runtime.firstboot", this.f1755p);
            jSONObject.put("ro.serialno", this.f1756q);
            jSONObject.put("ro.hardware", this.f1757r);
            jSONObject.put("ro.product.board", this.f1758s);
            jSONObject.put("ro.product.locale.language", this.f1759t);
            jSONObject.put("ro.product.locale.region", this.f1760u);
            jSONObject.put("ro.product.cpu.abi", this.f1761v);
            jSONObject.put("ro.board.platform", this.f1762w);
            jSONObject.put("ro.build.selinux", this.f1763x);
            jSONObject.put("ro.build.selinux.enforce", this.f1764y);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    public final String m1381b() {
        return this.f1747h;
    }
}

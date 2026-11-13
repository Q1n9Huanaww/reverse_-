package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.os.Build;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.c */
/* JADX INFO: compiled from: UMInternalDataProtocol.java */
/* JADX INFO: loaded from: classes.dex */
public class C1780c implements UMLogDataProtocol {

    /* JADX INFO: renamed from: b */
    private static final String f4139b = "info";

    /* JADX INFO: renamed from: c */
    private static final String f4140c = "stat";

    /* JADX INFO: renamed from: a */
    private Context f4141a;

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return null;
    }

    public C1780c(Context context) {
        if (context != null) {
            this.f4141a = context.getApplicationContext();
        }
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) throws Throwable {
        C1817e.m4397a("walle", "[internal] workEvent");
        switch (i) {
            case C1736a.f3999e /* 32769 */:
                try {
                    C1817e.m4397a("walle", "[internal] workEvent send envelope");
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put(C1799v.f4305aq, C1736a.f3998d);
                    JSONObject jSONObjectBuildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.f4141a, jSONObject, C1781d.m3957d(this.f4141a));
                    if (jSONObjectBuildEnvelopeWithExtHeader != null && !jSONObjectBuildEnvelopeWithExtHeader.has(C1708b.f3730ao)) {
                        C1817e.m4397a("walle", "[internal] workEvent send envelope back, result is ok");
                        C1787j.m4002f(this.f4141a);
                        C1795r.m4076d(this.f4141a);
                        break;
                    }
                } catch (Exception e) {
                    C1782e.m3972a(this.f4141a, e);
                }
                break;
            case C1736a.f4000f /* 32770 */:
                try {
                    C1817e.m4397a("walle", "[internal] workEvent cache location, event is " + obj.toString());
                    SharedPreferences sharedPreferences = this.f4141a.getSharedPreferences(C1785h.f4156a, 0);
                    if (sharedPreferences != null) {
                        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
                        editorEdit.putString(C1785h.f4157b, obj.toString());
                        editorEdit.commit();
                        break;
                    }
                } catch (Exception e2) {
                    C1782e.m3972a(this.f4141a, e2);
                    return;
                }
                break;
            case C1736a.f4001g /* 32771 */:
                try {
                    C1817e.m4397a("walle", "[internal] workEvent cache battery, event is " + obj.toString());
                    C1796s.m4090b(this.f4141a, obj.toString());
                } catch (Exception e3) {
                    C1782e.m3972a(this.f4141a, e3);
                    return;
                }
                break;
            case C1736a.f4002h /* 32772 */:
                try {
                    C1817e.m4397a("walle", "[internal] workEvent cache station, event is " + obj.toString());
                    C1796s.m4088a(this.f4141a, obj.toString());
                } catch (Exception e4) {
                    C1782e.m3972a(this.f4141a, e4);
                    return;
                }
                break;
            case C1736a.f4003i /* 32773 */:
                C1791n.m4037a(this.f4141a, (C1787j.b) obj);
                break;
            case C1736a.f4004j /* 32774 */:
                try {
                    m3948a(this.f4141a);
                } catch (Exception e5) {
                    if (e5 != null) {
                        C1817e.m4418e("UM_INTERNAL_CACHE_WIFICHANGE_KEY:" + e5.getMessage());
                    }
                    C1782e.m3972a(this.f4141a, e5);
                    return;
                }
                break;
            case C1736a.f4005k /* 32775 */:
                C1791n.m4039a(this.f4141a, (String) obj);
                break;
            case C1736a.f4006l /* 32776 */:
                SharedPreferences sharedPreferences2 = this.f4141a.getApplicationContext().getSharedPreferences(f4139b, 0);
                if (sharedPreferences2 != null) {
                    sharedPreferences2.edit().putString(f4140c, (String) obj).commit();
                }
                break;
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m3948a(Context context) throws Throwable {
        WifiInfo wifiInfoM3993c;
        if (context == null || (wifiInfoM3993c = C1787j.m3993c(context)) == null) {
            return;
        }
        C1787j.c cVar = new C1787j.c();
        cVar.f4170a = wifiInfoM3993c.describeContents();
        cVar.f4171b = wifiInfoM3993c.getBSSID();
        cVar.f4172c = wifiInfoM3993c.getSSID();
        if (Build.VERSION.SDK_INT >= 21) {
            cVar.f4173d = wifiInfoM3993c.getFrequency();
        } else {
            cVar.f4173d = -1;
        }
        boolean z = false;
        if (wifiInfoM3993c.getHiddenSSID()) {
            cVar.f4174e = 1;
        } else {
            cVar.f4174e = 0;
        }
        cVar.f4175f = wifiInfoM3993c.getIpAddress();
        cVar.f4176g = wifiInfoM3993c.getLinkSpeed();
        cVar.f4177h = DeviceConfig.getMac(context);
        cVar.f4178i = wifiInfoM3993c.getNetworkId();
        cVar.f4179j = wifiInfoM3993c.getRssi();
        cVar.f4180k = C1787j.m4003g(context);
        cVar.f4181l = System.currentTimeMillis();
        if (wifiInfoM3993c != null) {
            try {
                JSONArray jSONArrayM4040b = C1791n.m4040b(context);
                if (jSONArrayM4040b != null && jSONArrayM4040b.length() > 0) {
                    for (int i = 0; i < jSONArrayM4040b.length(); i++) {
                        String strOptString = jSONArrayM4040b.optJSONObject(i).optString("ssid", null);
                        if (strOptString != null && strOptString.equals(cVar.f4172c)) {
                            z = true;
                            break;
                        }
                    }
                }
                if (z) {
                    return;
                }
                C1791n.m4038a(context, cVar);
            } catch (Exception e) {
                if (e != null) {
                    C1817e.m4418e("wifiChange:" + e.getMessage());
                }
            }
        }
    }
}

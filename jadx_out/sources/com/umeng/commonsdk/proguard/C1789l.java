package com.umeng.commonsdk.proguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.statistics.common.C1817e;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.l */
/* JADX INFO: compiled from: BatteryUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class C1789l {

    /* JADX INFO: renamed from: a */
    private static final String f4189a = "BatteryUtils";

    /* JADX INFO: renamed from: b */
    private static boolean f4190b;

    /* JADX INFO: renamed from: c */
    private static Context f4191c;

    /* JADX INFO: renamed from: d */
    private BroadcastReceiver f4192d;

    private C1789l() {
        this.f4192d = new BroadcastReceiver() { // from class: com.umeng.commonsdk.proguard.l.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                try {
                    if (intent.getAction().equals("android.intent.action.BATTERY_CHANGED")) {
                        JSONObject jSONObject = new JSONObject();
                        try {
                            jSONObject.put("le", intent.getIntExtra("level", 0));
                        } catch (Exception unused) {
                        }
                        try {
                            jSONObject.put("vol", intent.getIntExtra("voltage", 0));
                        } catch (Exception unused2) {
                        }
                        try {
                            jSONObject.put("temp", intent.getIntExtra("temperature", 0));
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception unused3) {
                        }
                        int i = 2;
                        int i2 = -1;
                        switch (intent.getIntExtra("status", 0)) {
                            case 2:
                                i2 = 1;
                                break;
                            case 4:
                                i2 = 0;
                                break;
                            case 5:
                                i2 = 2;
                                break;
                        }
                        try {
                            jSONObject.put("st", i2);
                        } catch (Exception unused4) {
                        }
                        switch (intent.getIntExtra("plugged", 0)) {
                            case 1:
                                i = 1;
                                break;
                            case 2:
                                break;
                            default:
                                i = 0;
                                break;
                        }
                        try {
                            jSONObject.put("ct", i);
                            jSONObject.put("ts", System.currentTimeMillis());
                        } catch (Exception unused5) {
                        }
                        C1817e.m4397a(C1789l.f4189a, jSONObject.toString());
                        UMWorkDispatch.sendEvent(context, C1736a.f4001g, C1763b.m3919a(C1789l.f4191c).m3920a(), jSONObject.toString());
                        C1789l.this.m4033c();
                    }
                } catch (Throwable th) {
                    C1782e.m3972a(C1789l.f4191c, th);
                }
            }
        };
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.proguard.l$a */
    /* JADX INFO: compiled from: BatteryUtils.java */
    private static class a {

        /* JADX INFO: renamed from: a */
        private static final C1789l f4194a = new C1789l();

        private a() {
        }
    }

    /* JADX INFO: renamed from: a */
    public static C1789l m4029a(Context context) {
        if (f4191c == null && context != null) {
            f4191c = context.getApplicationContext();
        }
        return a.f4194a;
    }

    /* JADX INFO: renamed from: a */
    public synchronized boolean m4031a() {
        return f4190b;
    }

    /* JADX INFO: renamed from: b */
    public synchronized void m4032b() {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("android.intent.action.BATTERY_CHANGED");
            f4191c.registerReceiver(this.f4192d, intentFilter);
            f4190b = true;
        } catch (Throwable th) {
            C1782e.m3972a(f4191c, th);
        }
    }

    /* JADX INFO: renamed from: c */
    public synchronized void m4033c() {
        try {
            f4191c.unregisterReceiver(this.f4192d);
            f4190b = false;
        } catch (Throwable th) {
            C1782e.m3972a(f4191c, th);
        }
    }
}

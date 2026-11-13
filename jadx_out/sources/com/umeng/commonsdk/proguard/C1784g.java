package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.umeng.commonsdk.amap.UMAmapConfig;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: renamed from: com.umeng.commonsdk.proguard.g */
/* JADX INFO: compiled from: UMSysLocation.java */
/* JADX INFO: loaded from: classes.dex */
public class C1784g {

    /* JADX INFO: renamed from: a */
    private static final String f4144a = "UMSysLocation";

    /* JADX INFO: renamed from: c */
    private static final int f4145c = 10000;

    /* JADX INFO: renamed from: b */
    private LocationManager f4146b;

    /* JADX INFO: renamed from: e */
    private Context f4148e;

    /* JADX INFO: renamed from: g */
    private AbstractC1786i f4150g;

    /* JADX INFO: renamed from: d */
    private boolean f4147d = false;

    /* JADX INFO: renamed from: f */
    private LocationListener f4149f = new LocationListener() { // from class: com.umeng.commonsdk.proguard.g.1
        @Override // android.location.LocationListener
        public void onProviderDisabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onProviderEnabled(String str) {
        }

        @Override // android.location.LocationListener
        public void onStatusChanged(String str, int i, Bundle bundle) {
        }

        @Override // android.location.LocationListener
        public void onLocationChanged(Location location) throws Throwable {
            C1817e.m4397a(C1784g.f4144a, "onLocationChanged");
            try {
                C1784g.this.f4147d = false;
                if (C1784g.this.f4150g != null) {
                    C1784g.this.f4150g.mo3984a(location);
                }
            } catch (Throwable th) {
                C1782e.m3972a(C1784g.this.f4148e, th);
            }
        }
    };

    private C1784g() {
    }

    public C1784g(Context context) throws Throwable {
        if (context == null) {
            MLog.m4337e("Context参数不能为null");
        } else {
            this.f4148e = context.getApplicationContext();
            this.f4146b = (LocationManager) context.getApplicationContext().getSystemService(RequestParameters.SUBRESOURCE_LOCATION);
        }
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m3981a(AbstractC1786i abstractC1786i) {
        C1817e.m4397a(f4144a, "getSystemLocation");
        if (abstractC1786i != null && this.f4148e != null) {
            this.f4150g = abstractC1786i;
            if (!UMUtils.checkPermission(this.f4148e, "android.permission.ACCESS_COARSE_LOCATION") || !UMUtils.checkPermission(this.f4148e, "android.permission.ACCESS_FINE_LOCATION")) {
                if (this.f4150g != null) {
                    this.f4150g.mo3984a(null);
                }
                return;
            }
            try {
                this.f4147d = true;
                if (this.f4146b != null && this.f4146b.isProviderEnabled("network")) {
                    C1817e.m4397a(f4144a, "NETWORK_PROVIDER");
                    this.f4146b.requestLocationUpdates("network", 1000L, 0.0f, this.f4149f);
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() { // from class: com.umeng.commonsdk.proguard.g.2
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            try {
                                C1817e.m4397a(C1784g.f4144a, "NETWORK_PROVIDER runing");
                                if (timer != null) {
                                    timer.cancel();
                                }
                                if (C1784g.this.f4147d) {
                                    if (C1784g.this.f4146b != null && C1784g.this.f4149f != null) {
                                        try {
                                            C1784g.this.f4146b.removeUpdates(C1784g.this.f4149f);
                                        } catch (Throwable unused) {
                                        }
                                    }
                                    if (C1784g.this.f4150g != null) {
                                        C1784g.this.f4150g.mo3984a(null);
                                    }
                                }
                            } catch (Throwable th) {
                                C1782e.m3972a(C1784g.this.f4148e, th);
                            }
                        }
                    }, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                } else if (this.f4146b.isProviderEnabled("gps")) {
                    C1817e.m4397a(f4144a, "GPS_PROVIDER");
                    this.f4146b.requestLocationUpdates("gps", 1000L, 0.0f, this.f4149f);
                    final Timer timer2 = new Timer();
                    timer2.schedule(new TimerTask() { // from class: com.umeng.commonsdk.proguard.g.3
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            try {
                                C1817e.m4397a(C1784g.f4144a, "GPS_PROVIDER runing");
                                if (timer2 != null) {
                                    timer2.cancel();
                                }
                                if (C1784g.this.f4147d) {
                                    if (C1784g.this.f4146b != null && C1784g.this.f4149f != null) {
                                        try {
                                            C1784g.this.f4146b.removeUpdates(C1784g.this.f4149f);
                                        } catch (Throwable unused) {
                                        }
                                    }
                                    if (C1784g.this.f4150g != null) {
                                        C1784g.this.f4150g.mo3984a(null);
                                    }
                                }
                            } catch (Throwable th) {
                                C1782e.m3972a(C1784g.this.f4148e, th);
                            }
                        }
                    }, UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                }
            } catch (Throwable th) {
                C1817e.m4397a(f4144a, "e is " + th);
                if (abstractC1786i != null) {
                    try {
                        abstractC1786i.mo3984a(null);
                    } catch (Throwable th2) {
                        C1782e.m3972a(this.f4148e, th2);
                    }
                }
                C1782e.m3972a(this.f4148e, th);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m3980a() {
        C1817e.m4397a(f4144a, "destroy");
        try {
            if (UMUtils.checkPermission(this.f4148e, "android.permission.ACCESS_COARSE_LOCATION") && UMUtils.checkPermission(this.f4148e, "android.permission.ACCESS_FINE_LOCATION")) {
                if (this.f4146b != null) {
                    if (this.f4149f != null) {
                        try {
                            this.f4146b.removeUpdates(this.f4149f);
                        } catch (Throwable unused) {
                        }
                    }
                    this.f4146b = null;
                }
            }
        } catch (Throwable th) {
            C1782e.m3972a(this.f4148e, th);
        }
    }
}

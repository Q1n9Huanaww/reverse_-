package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import java.io.File;
import java.io.IOException;

/* JADX INFO: renamed from: com.umeng.commonsdk.stateless.d */
/* JADX INFO: compiled from: UMSLNetWorkSender.java */
/* JADX INFO: loaded from: classes.dex */
public class C1807d {

    /* JADX INFO: renamed from: a */
    public static final int f4464a = 273;

    /* JADX INFO: renamed from: b */
    private static Context f4465b = null;

    /* JADX INFO: renamed from: c */
    private static HandlerThread f4466c = null;

    /* JADX INFO: renamed from: d */
    private static Handler f4467d = null;

    /* JADX INFO: renamed from: f */
    private static final int f4469f = 512;

    /* JADX INFO: renamed from: g */
    private static IntentFilter f4470g;

    /* JADX INFO: renamed from: h */
    private static boolean f4471h;

    /* JADX INFO: renamed from: e */
    private static Object f4468e = new Object();

    /* JADX INFO: renamed from: i */
    private static BroadcastReceiver f4472i = new BroadcastReceiver() { // from class: com.umeng.commonsdk.stateless.d.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context == null || intent == null) {
                return;
            }
            try {
                if (intent.getAction() != null && intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                    Context unused = C1807d.f4465b = context.getApplicationContext();
                    if (C1807d.f4465b != null && (connectivityManager = (ConnectivityManager) C1807d.f4465b.getSystemService("connectivity")) != null) {
                        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                        if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                            boolean unused2 = C1807d.f4471h = true;
                            C1817e.m4397a("walle", "[stateless] net reveiver ok --->>>");
                            C1807d.m4301b(273);
                        } else {
                            C1817e.m4397a("walle", "[stateless] net reveiver disconnected --->>>");
                            boolean unused3 = C1807d.f4471h = false;
                        }
                    }
                }
            } catch (Throwable th) {
                C1782e.m3972a(context, th);
            }
        }
    };

    public C1807d(Context context) {
        synchronized (f4468e) {
            if (context != null) {
                try {
                    f4465b = context.getApplicationContext();
                    if (f4465b != null && f4466c == null) {
                        f4466c = new HandlerThread("SL-NetWorkSender");
                        f4466c.start();
                        if (f4467d == null) {
                            f4467d = new Handler(f4466c.getLooper()) { // from class: com.umeng.commonsdk.stateless.d.2
                                @Override // android.os.Handler
                                public void handleMessage(Message message) throws IOException {
                                    int i = message.what;
                                    if (i == 273) {
                                        C1807d.m4304e();
                                    } else {
                                        if (i != 512) {
                                            return;
                                        }
                                        C1807d.m4305f();
                                    }
                                }
                            };
                        }
                        if (DeviceConfig.checkPermission(f4465b, "android.permission.ACCESS_NETWORK_STATE")) {
                            C1817e.m4397a("walle", "[stateless] begin register receiver");
                            if (f4470g == null) {
                                f4470g = new IntentFilter();
                                f4470g.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                if (f4472i != null) {
                                    C1817e.m4397a("walle", "[stateless] register receiver ok");
                                    f4465b.registerReceiver(f4472i, f4470g);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    C1782e.m3972a(context, th);
                }
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4298a(int i) {
        if (!f4471h || f4467d == null) {
            return;
        }
        Message messageObtainMessage = f4467d.obtainMessage();
        messageObtainMessage.what = i;
        f4467d.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: renamed from: b */
    public static void m4301b(int i) {
        try {
            if (!f4471h || f4467d == null || f4467d.hasMessages(i)) {
                return;
            }
            C1817e.m4397a("walle", "[stateless] sendMsgOnce !!!!");
            Message messageObtainMessage = f4467d.obtainMessage();
            messageObtainMessage.what = i;
            f4467d.sendMessage(messageObtainMessage);
        } catch (Throwable th) {
            C1782e.m3972a(f4465b, th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: e */
    public static void m4304e() throws IOException {
        if (!f4471h || f4465b == null) {
            return;
        }
        try {
            File fileM4309a = C1809f.m4309a(f4465b);
            if (fileM4309a == null || fileM4309a.getParentFile() == null || TextUtils.isEmpty(fileM4309a.getParentFile().getName())) {
                return;
            }
            C1808e c1808e = new C1808e(f4465b);
            if (c1808e != null) {
                String str = new String(Base64.decode(fileM4309a.getParentFile().getName(), 0));
                C1817e.m4397a("walle", "[stateless] handleProcessNext, pathUrl is " + str);
                byte[] bArrM4313a = null;
                try {
                    bArrM4313a = C1809f.m4313a(fileM4309a.getAbsolutePath());
                } catch (Exception unused) {
                }
                if (c1808e.m4307a(bArrM4313a, str)) {
                    C1817e.m4397a("walle", "[stateless] Send envelope file success, delete it.");
                    File file = new File(fileM4309a.getAbsolutePath());
                    if (!file.delete()) {
                        C1817e.m4397a("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                        file.delete();
                    }
                } else {
                    C1817e.m4397a("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                    return;
                }
            }
            m4301b(273);
        } catch (Throwable th) {
            C1782e.m3972a(f4465b, th);
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m4297a() {
        m4301b(512);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f */
    public static void m4305f() {
        if (f4470g != null) {
            if (f4472i != null) {
                if (f4465b != null) {
                    f4465b.unregisterReceiver(f4472i);
                }
                f4472i = null;
            }
            f4470g = null;
        }
        if (f4466c != null) {
            f4466c.quit();
            if (f4466c != null) {
                f4466c = null;
            }
            if (f4467d != null) {
                f4467d = null;
            }
        }
    }
}

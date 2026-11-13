package com.umeng.commonsdk.framework;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1787j;
import com.umeng.commonsdk.proguard.C1798u;
import com.umeng.commonsdk.statistics.C1818d;
import com.umeng.commonsdk.statistics.common.C1817e;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import java.io.File;

/* JADX INFO: renamed from: com.umeng.commonsdk.framework.d */
/* JADX INFO: compiled from: UMNetWorkSender.java */
/* JADX INFO: loaded from: classes.dex */
class C1734d {

    /* JADX INFO: renamed from: a */
    private static HandlerThread f3970a = null;

    /* JADX INFO: renamed from: b */
    private static Handler f3971b = null;

    /* JADX INFO: renamed from: c */
    private static Handler f3972c = null;

    /* JADX INFO: renamed from: d */
    private static final int f3973d = 273;

    /* JADX INFO: renamed from: e */
    private static final int f3974e = 512;

    /* JADX INFO: renamed from: f */
    private static final int f3975f = 769;

    /* JADX INFO: renamed from: g */
    private static a f3976g;

    /* JADX INFO: renamed from: h */
    private static ConnectivityManager f3977h;

    /* JADX INFO: renamed from: i */
    private static NetworkInfo f3978i;

    /* JADX INFO: renamed from: j */
    private static IntentFilter f3979j;

    /* JADX INFO: renamed from: k */
    private static StatTracer f3980k;

    /* JADX INFO: renamed from: l */
    private static ImLatent f3981l;

    /* JADX INFO: renamed from: m */
    private static boolean f3982m;

    /* JADX INFO: renamed from: n */
    private static BroadcastReceiver f3983n = new BroadcastReceiver() { // from class: com.umeng.commonsdk.framework.d.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(final Context context, Intent intent) {
            new Thread(new Runnable() { // from class: com.umeng.commonsdk.framework.d.1.1
                @Override // java.lang.Runnable
                public void run() {
                    C1787j.m3997d(context);
                }
            }).start();
            if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                final Context contextM3733a = C1733c.m3733a();
                ConnectivityManager unused = C1734d.f3977h = (ConnectivityManager) contextM3733a.getSystemService("connectivity");
                try {
                    NetworkInfo unused2 = C1734d.f3978i = C1734d.f3977h.getActiveNetworkInfo();
                    if (C1734d.f3978i != null && C1734d.f3978i.isAvailable()) {
                        C1817e.m4406c("--->>> network isAvailable, check if there are any files to send.");
                        boolean unused3 = C1734d.f3982m = true;
                        C1734d.m3748c(273);
                        if (C1734d.f3978i.getType() == 1) {
                            C1817e.m4406c("--->>> wifi connection available, send uop packet now.");
                            new Thread(new Runnable() { // from class: com.umeng.commonsdk.framework.d.1.2
                                @Override // java.lang.Runnable
                                public void run() {
                                    try {
                                        C1798u.m4106a(contextM3733a);
                                    } catch (Throwable th) {
                                        C1782e.m3972a(contextM3733a, th);
                                    }
                                }
                            }).start();
                        }
                    } else {
                        C1817e.m4406c("--->>> network disconnected.");
                        boolean unused4 = C1734d.f3982m = false;
                    }
                } catch (Throwable th) {
                    C1782e.m3972a(contextM3733a, th);
                }
            }
        }
    };

    public C1734d(Context context, Handler handler) throws Throwable {
        f3972c = handler;
        if (f3970a == null) {
            f3970a = new HandlerThread("NetWorkSender");
            f3970a.start();
            if (f3976g == null) {
                f3976g = new a(C1732b.m3728h(context));
                f3976g.startWatching();
                C1817e.m4400b("--->>> FileMonitor has already started!");
            }
            Context contextM3733a = C1733c.m3733a();
            if (DeviceConfig.checkPermission(contextM3733a, "android.permission.ACCESS_NETWORK_STATE") && f3979j == null) {
                f3979j = new IntentFilter();
                f3979j.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                if (f3983n != null) {
                    contextM3733a.registerReceiver(f3983n, f3979j);
                }
            }
            if (f3980k == null) {
                f3980k = StatTracer.getInstance(context);
                f3981l = ImLatent.getService(context, f3980k);
            }
            if (f3971b == null) {
                f3971b = new Handler(f3970a.getLooper()) { // from class: com.umeng.commonsdk.framework.d.2
                    @Override // android.os.Handler
                    public void handleMessage(Message message) throws Throwable {
                        int i = message.what;
                        if (i == 273) {
                            C1817e.m4400b("--->>> handleMessage: recv MSG_PROCESS_NEXT msg.");
                            C1734d.m3755j();
                        } else {
                            if (i != 512) {
                                return;
                            }
                            C1734d.m3754i();
                        }
                    }
                };
            }
        }
    }

    /* JADX INFO: renamed from: h */
    private static void m3753h() {
        if (f3970a != null) {
            f3970a = null;
        }
        if (f3971b != null) {
            f3971b = null;
        }
        if (f3972c != null) {
            f3972c = null;
        }
        if (f3981l != null) {
            f3981l = null;
        }
        if (f3980k != null) {
            f3980k = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: i */
    public static void m3754i() throws Throwable {
        if (f3976g != null) {
            f3976g.stopWatching();
            f3976g = null;
        }
        if (f3979j != null) {
            if (f3983n != null) {
                C1733c.m3733a().unregisterReceiver(f3983n);
                f3983n = null;
            }
            f3979j = null;
        }
        C1817e.m4400b("--->>> handleQuit: Quit sender thread.");
        if (f3970a != null) {
            f3970a.quit();
            m3753h();
        }
    }

    /* JADX INFO: renamed from: a */
    public static void m3740a() {
        m3748c(512);
    }

    /* JADX INFO: renamed from: b */
    private static void m3746b(int i) {
        if (!f3982m || f3971b == null || f3971b.hasMessages(i)) {
            return;
        }
        Message messageObtainMessage = f3971b.obtainMessage();
        messageObtainMessage.what = i;
        f3971b.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public static void m3748c(int i) {
        if (!f3982m || f3971b == null) {
            return;
        }
        Message messageObtainMessage = f3971b.obtainMessage();
        messageObtainMessage.what = i;
        f3971b.sendMessage(messageObtainMessage);
    }

    /* JADX INFO: renamed from: a */
    private static void m3743a(int i, long j) {
        if (!f3982m || f3971b == null) {
            return;
        }
        Message messageObtainMessage = f3971b.obtainMessage();
        messageObtainMessage.what = i;
        f3971b.sendMessageDelayed(messageObtainMessage, j);
    }

    /* JADX INFO: renamed from: b */
    public static void m3745b() {
        m3746b(273);
    }

    /* JADX INFO: renamed from: a */
    private static void m3742a(int i, int i2) {
        if (!f3982m || f3972c == null) {
            return;
        }
        f3972c.removeMessages(i);
        Message messageObtainMessage = f3972c.obtainMessage();
        messageObtainMessage.what = i;
        f3972c.sendMessageDelayed(messageObtainMessage, i2);
    }

    /* JADX INFO: renamed from: c */
    public static void m3747c() {
        m3742a(f3975f, 3000);
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.framework.d$a */
    /* JADX INFO: compiled from: UMNetWorkSender.java */
    static class a extends FileObserver {
        public a(String str) {
            super(str);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) throws Throwable {
            if ((i & 8) != 8) {
                return;
            }
            C1817e.m4400b("--->>> envelope file created >>> " + str);
            C1734d.m3748c(273);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: j */
    public static void m3755j() throws Throwable {
        C1817e.m4400b("--->>> handleProcessNext: Enter...");
        if (f3982m) {
            Context contextM3733a = C1733c.m3733a();
            try {
                if (C1732b.m3722c(contextM3733a) > 0) {
                    C1817e.m4400b("--->>> The envelope file exists.");
                    if (C1732b.m3722c(contextM3733a) > 100) {
                        C1817e.m4400b("--->>> Number of envelope files is greater than 100, remove old files first.");
                        C1732b.m3724d(contextM3733a);
                    }
                    File fileM3725e = C1732b.m3725e(contextM3733a);
                    if (fileM3725e != null) {
                        C1817e.m4400b("--->>> Ready to send envelope file [" + fileM3725e.getPath() + "].");
                        C1818d c1818d = new C1818d(contextM3733a);
                        if (f3981l != null && f3981l.isLatentActivite()) {
                            f3981l.latentDeactivite();
                            long delayTime = f3981l.getDelayTime();
                            if (delayTime > 0) {
                                C1817e.m4406c("start lacency policy, wait [" + delayTime + "] milliseconds .");
                                try {
                                    Thread.sleep(delayTime * 1000);
                                } catch (Throwable th) {
                                    C1782e.m3972a(contextM3733a, th);
                                }
                            }
                        }
                        if (c1818d.m4431a(fileM3725e)) {
                            C1817e.m4400b("--->>> Send envelope file success, delete it.");
                            if (!C1732b.m3718a(fileM3725e)) {
                                C1817e.m4400b("--->>> Failed to delete already processed file. We try again after delete failed.");
                                C1732b.m3718a(fileM3725e);
                            }
                            m3748c(273);
                            return;
                        }
                        C1817e.m4400b("--->>> Send envelope file failed, abandon and wait next trigger!");
                        return;
                    }
                }
                C1817e.m4400b("--->>> The envelope file not exists, start auto process for module cache data.");
                m3747c();
            } catch (Throwable th2) {
                C1782e.m3972a(contextM3733a, th2);
            }
        }
    }
}

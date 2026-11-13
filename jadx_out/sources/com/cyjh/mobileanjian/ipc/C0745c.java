package com.cyjh.mobileanjian.ipc;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;
import com.cyjh.mobileanjian.ipc.stuff.IntentActions;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.sdk.MqRunner;
import com.goldcoast.sdk.domain.AnalyseResult;
import com.goldcoast.sdk.domain.EntryPoint;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.amap.UMAmapConfig;
import java.io.File;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.c */
/* JADX INFO: compiled from: RootManager.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0745c {

    /* JADX INFO: renamed from: a */
    public static final int f986a = 1;

    /* JADX INFO: renamed from: b */
    public static final int f987b = 2;

    /* JADX INFO: renamed from: g */
    private static C0745c f988g = null;

    /* JADX INFO: renamed from: j */
    private static final String f989j = "3rd.need.or.not.m";

    /* JADX INFO: renamed from: i */
    private int f995i;

    /* JADX INFO: renamed from: d */
    boolean f991d = false;

    /* JADX INFO: renamed from: h */
    private boolean f994h = false;

    /* JADX INFO: renamed from: e */
    boolean f992e = false;

    /* JADX INFO: renamed from: f */
    boolean f993f = false;

    /* JADX INFO: renamed from: c */
    Handler f990c = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mobileanjian.ipc.c.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (C0830a.f1529i != null) {
                C0830a.f1529i.onRootProgress((String) message.obj, message.what);
            }
        }
    };

    /* JADX INFO: renamed from: a */
    private static /* synthetic */ boolean m718a(C0745c c0745c) {
        c0745c.f991d = false;
        return false;
    }

    /* JADX INFO: renamed from: b */
    private static /* synthetic */ boolean m721b(C0745c c0745c) {
        c0745c.f993f = true;
        return true;
    }

    /* JADX INFO: renamed from: c */
    private static /* synthetic */ boolean m723c(C0745c c0745c) {
        c0745c.f992e = true;
        return true;
    }

    private C0745c() {
    }

    /* JADX INFO: renamed from: b */
    private static synchronized C0745c m719b() {
        if (f988g == null) {
            f988g = new C0745c();
        }
        return f988g;
    }

    /* JADX INFO: renamed from: a */
    private void m714a(int i) {
        this.f995i = i;
    }

    /* JADX INFO: renamed from: c */
    private boolean m722c() {
        return this.f993f && this.f992e;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x00d0  */
    /* JADX INFO: renamed from: d */
    private void m725d() {
        if (this.f994h) {
            return;
        }
        this.f994h = true;
        if (RootUtil.isRoot()) {
            C0746d.m728a().m742a(MqRunner.getInstance());
        } else {
            new StringBuilder("check EntryPoint ").append(EntryPoint.instance());
            if (EntryPoint.instance() != null && C0830a.f1529i != null) {
                if (this.f991d) {
                    this.f990c.obtainMessage(1, "3rd root is going on now." + EntryPoint.class.getSimpleName()).sendToTarget();
                } else {
                    File file = new File(C0830a.f1527g.getFilesDir(), f989j);
                    if (file.exists()) {
                        this.f990c.obtainMessage(5, "failed because mark file").sendToTarget();
                    } else {
                        NetworkInfo activeNetworkInfo = ((ConnectivityManager) C0830a.f1527g.getSystemService("connectivity")).getActiveNetworkInfo();
                        if (!(activeNetworkInfo != null && activeNetworkInfo.isConnected())) {
                            this.f990c.obtainMessage(5, "failed because mark file").sendToTarget();
                        } else if (this.f993f) {
                            if (this.f992e) {
                                this.f990c.post(new AnonymousClass2());
                            } else {
                                this.f990c.obtainMessage(5, "result").sendToTarget();
                            }
                        } else {
                            this.f991d = true;
                            this.f990c.obtainMessage(1, "Now start 3rd root using " + EntryPoint.class.getSimpleName()).sendToTarget();
                            new AnonymousClass3("third_root_request", file).start();
                        }
                    }
                }
            }
        }
        this.f994h = false;
    }

    /* JADX INFO: renamed from: e */
    private void m726e() {
        if (this.f991d) {
            this.f990c.obtainMessage(1, "3rd root is going on now." + EntryPoint.class.getSimpleName()).sendToTarget();
            return;
        }
        File file = new File(C0830a.f1527g.getFilesDir(), f989j);
        if (!file.exists()) {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) C0830a.f1527g.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                if (this.f993f) {
                    if (this.f992e) {
                        this.f990c.post(new AnonymousClass2());
                        return;
                    } else {
                        this.f990c.obtainMessage(5, "result").sendToTarget();
                        return;
                    }
                }
                this.f991d = true;
                this.f990c.obtainMessage(1, "Now start 3rd root using " + EntryPoint.class.getSimpleName()).sendToTarget();
                new AnonymousClass3("third_root_request", file).start();
                return;
            }
        }
        this.f990c.obtainMessage(5, "failed because mark file").sendToTarget();
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.c$2, reason: invalid class name */
    /* JADX INFO: compiled from: RootManager.java */
    final class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            ExToast.makeText(C0830a.f1527g.getApplicationContext(), C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_script_engine_failed_start), ExToast.LENGTH_LONG).show();
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.c$3, reason: invalid class name */
    /* JADX INFO: compiled from: RootManager.java */
    final class AnonymousClass3 extends Thread {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ File f998a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass3(String str, File file) {
            super(str);
            this.f998a = file;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            try {
                if (!this.f998a.exists()) {
                    this.f998a.createNewFile();
                }
                EntryPoint.instance().analyse(new AnalyseResult() { // from class: com.cyjh.mobileanjian.ipc.c.3.1
                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onProgress(String str) {
                    }

                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onSuccess(String str) {
                        if (AnonymousClass3.this.f998a.exists()) {
                            AnonymousClass3.this.f998a.delete();
                        }
                        C0745c.this.f991d = false;
                        C0745c.this.f993f = true;
                        C0745c.this.f992e = true;
                        EntryPoint.instance().exec(new String[]{"chmod 777 /dev/input/*", C0830a.m1168a().getAbsolutePath()});
                        C0745c.this.f990c.sendMessageDelayed(C0745c.this.f990c.obtainMessage(3, str), UMAmapConfig.AMAP_CACHE_WRITE_TIME);
                    }

                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onFailed(String str) {
                        C0745c.this.f991d = false;
                        if (!C0745c.this.f993f) {
                            C0745c.this.f990c.obtainMessage(3, str).sendToTarget();
                        }
                        C0745c.this.f993f = true;
                        C0745c.m713a();
                    }

                    @Override // com.goldcoast.sdk.domain.AnalyseResult
                    public final void onException(String str) {
                        C0745c.this.f991d = false;
                        if (!C0745c.this.f993f) {
                            C0745c.this.f990c.obtainMessage(3, str).sendToTarget();
                        }
                        C0745c.this.f993f = true;
                        C0745c.m713a();
                    }
                });
            } catch (Exception unused) {
                C0745c.this.f991d = false;
                C0745c.this.f993f = true;
                C0745c.this.f990c.obtainMessage(3, "Exception occurs when startThirdRoot()").sendToTarget();
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private static boolean m717a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /* JADX INFO: renamed from: a */
    private void m715a(String str) {
        this.f990c.obtainMessage(2, str).sendToTarget();
        this.f990c.removeMessages(3);
    }

    /* JADX INFO: renamed from: b */
    private void m720b(String str) {
        this.f990c.obtainMessage(3, str).sendToTarget();
    }

    /* JADX INFO: renamed from: a */
    private static void m716a(boolean z) {
        Intent intent = new Intent(IntentActions.TRY_ACTIVE_RUNNER);
        intent.putExtra(IntentActions.EXTRA_ACTIVITE_TYPE, "2");
        intent.putExtra(IntentActions.EXTRA_ACTIVE_RESULT, z ? "1" : AccountManager.TYPE_LOGIN);
        LocalBroadcastManager.getInstance(C0830a.f1527g).sendBroadcast(intent);
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m713a() {
        Intent intent = new Intent(IntentActions.TRY_ACTIVE_RUNNER);
        intent.putExtra(IntentActions.EXTRA_ACTIVITE_TYPE, "2");
        intent.putExtra(IntentActions.EXTRA_ACTIVE_RESULT, AccountManager.TYPE_LOGIN);
        LocalBroadcastManager.getInstance(C0830a.f1527g).sendBroadcast(intent);
    }
}

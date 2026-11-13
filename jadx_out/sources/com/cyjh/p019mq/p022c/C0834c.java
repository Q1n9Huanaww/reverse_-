package com.cyjh.p019mq.p022c;

import android.content.Context;
import android.net.LocalServerSocket;
import android.net.LocalSocket;
import android.os.HandlerThread;
import com.cyjh.mobileanjian.ipc.interfaces.AppQuitListener;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.share.proto.C0770b;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mq.c.c */
/* JADX INFO: compiled from: IpcServer.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0834c implements AppQuitListener {

    /* JADX INFO: renamed from: b */
    private static final int f1569b = 10092;

    /* JADX INFO: renamed from: f */
    private Context f1574f;

    /* JADX INFO: renamed from: c */
    private ServerSocket f1571c = null;

    /* JADX INFO: renamed from: d */
    private LocalServerSocket f1572d = null;

    /* JADX INFO: renamed from: e */
    private C0833b f1573e = null;

    /* JADX INFO: renamed from: g */
    private boolean f1575g = false;

    /* JADX INFO: renamed from: a */
    public List<EngineStateObserver> f1570a = new ArrayList();

    public C0834c(Context context) {
        this.f1574f = null;
        this.f1574f = context;
    }

    /* JADX INFO: renamed from: a */
    private void m1237a(EngineStateObserver engineStateObserver) {
        this.f1570a.add(engineStateObserver);
    }

    /* JADX INFO: renamed from: b */
    private void m1240b(EngineStateObserver engineStateObserver) {
        this.f1570a.remove(engineStateObserver);
    }

    /* JADX WARN: Type inference failed for: r5v1, types: [com.cyjh.mq.c.c$1] */
    /* JADX INFO: renamed from: a */
    public final void m1244a(String str, int i) {
        while (this.f1571c == null) {
            try {
                this.f1571c = new ServerSocket(i);
            } catch (IOException e) {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e2) {
                    ThrowableExtension.printStackTrace(e2);
                }
                ThrowableExtension.printStackTrace(e);
            }
        }
        try {
            new HandlerThread("server socket thread") { // from class: com.cyjh.mq.c.c.1
                @Override // android.os.HandlerThread
                protected final void onLooperPrepared() {
                    super.onLooperPrepared();
                    while (!C0834c.this.f1575g) {
                        try {
                            Socket socketAccept = C0834c.this.f1571c.accept();
                            if (C0834c.this.f1573e != null && C0834c.this.f1573e.f1545b) {
                                socketAccept.close();
                            } else {
                                C0834c.this.f1573e = new C0833b(C0834c.this.f1574f, socketAccept);
                                C0834c.this.f1573e.f1550g = C0834c.this.f1570a;
                                C0834c.this.f1573e.m1235b();
                            }
                        } catch (IOException e3) {
                            ThrowableExtension.printStackTrace(e3);
                            return;
                        }
                    }
                    C0834c.this.f1571c.close();
                }
            }.start();
            this.f1572d = new LocalServerSocket(str);
            while (!this.f1575g) {
                LocalSocket localSocketAccept = this.f1572d.accept();
                if (this.f1573e != null && this.f1573e.f1545b) {
                    this.f1573e.mo1189a();
                } else {
                    this.f1573e = new C0833b(this.f1574f, localSocketAccept);
                    this.f1573e.f1550g = this.f1570a;
                    this.f1573e.m1235b();
                }
            }
            this.f1572d.close();
        } catch (IOException e3) {
            ThrowableExtension.printStackTrace(e3);
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.AppQuitListener
    public final void onAppQuit() {
        if (this.f1575g) {
            return;
        }
        this.f1575g = true;
        try {
            if (this.f1573e != null) {
                this.f1573e.m1234a(C0770b.m800a(65535));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

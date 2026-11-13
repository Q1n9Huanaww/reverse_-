package com.cyjh.p019mq.sdk;

import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mobileanjian.ipc.p017a.C0743b;
import com.cyjh.mobileanjian.ipc.p017a.C0743b.a;
import com.cyjh.mobileanjian.ipc.p017a.C0743b.c;
import com.cyjh.p019mq.sdk.entity.Script4Run;
import com.cyjh.p019mq.sdk.inf.IRunner;
import com.cyjh.p019mq.sdk.inf.InterfaceC0846a;
import com.cyjh.p019mq.sdk.inf.InterfaceC0847b;
import com.cyjh.p019mq.sdk.inf.OnElfCallback;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.ByteString;

/* JADX INFO: loaded from: classes.dex */
public class MqRunnerLite implements InterfaceC0846a, InterfaceC0847b {

    /* JADX INFO: renamed from: a */
    public OnScriptListener f1631a;

    /* JADX INFO: renamed from: b */
    public OnElfCallback f1632b;

    /* JADX INFO: renamed from: c */
    public OnRequestCallback f1633c;

    /* JADX INFO: renamed from: d */
    public boolean f1634d;

    /* JADX INFO: renamed from: e */
    public OnSpecialMqCmdCallback f1635e;

    /* JADX INFO: renamed from: f */
    private Script4Run f1636f;

    /* JADX INFO: renamed from: g */
    private C0743b f1637g;

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: a */
    public final void mo1305a(ByteString byteString) {
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: a */
    public final void mo1306a(String str) {
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: b */
    public final void mo1308b(ByteString byteString) {
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: c */
    public final void mo1309c(ByteString byteString) {
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void takeShot(int i, int i2, OnScreenShotCallback onScreenShotCallback) {
    }

    /* synthetic */ MqRunnerLite(byte b) {
        this();
    }

    private MqRunnerLite() {
        this.f1632b = null;
        this.f1633c = null;
        this.f1634d = true;
        this.f1637g = new C0743b();
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void initHost(String str) {
        C0743b c0743b = this.f1637g;
        try {
            if (!c0743b.f959c) {
                c0743b.f960d = c0743b.new c("ScriptRunnerLite_Request");
                c0743b.f960d.start();
            }
            c0743b.f963g.put(c0743b.new a(1, str, "", "", "", 0, 0, ""));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void initElf(String str, String str2, String str3, OnElfCallback onElfCallback) {
        this.f1632b = onElfCallback;
        C0743b c0743b = this.f1637g;
        try {
            c0743b.f963g.put(c0743b.new a(2, "", str, str2, str3, 0, 0, ""));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void setHeartBeat(int i, int i2) {
        C0743b c0743b = this.f1637g;
        try {
            c0743b.f963g.put(c0743b.new a(3, "", "", "", "", i, i2, ""));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void setOnSpecialMqCmdCallback(OnSpecialMqCmdCallback onSpecialMqCmdCallback) {
        this.f1635e = onSpecialMqCmdCallback;
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void setRegCode(String str) {
        C0743b c0743b = this.f1637g;
        try {
            c0743b.f963g.put(c0743b.new a(4, "", "", "", "", 0, 0, str));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0847b
    /* JADX INFO: renamed from: a */
    public final boolean mo1307a() {
        return this.f1634d;
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0847b
    /* JADX INFO: renamed from: d */
    public final OnScriptListener mo1310d() {
        return this.f1631a;
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0847b
    /* JADX INFO: renamed from: e */
    public final OnSpecialMqCmdCallback mo1311e() {
        return this.f1635e;
    }

    /* JADX INFO: renamed from: com.cyjh.mq.sdk.MqRunnerLite$a */
    private static class C0844a {

        /* JADX INFO: renamed from: a */
        private static final MqRunnerLite f1638a = new MqRunnerLite(0);

        private C0844a() {
        }
    }

    public static synchronized MqRunnerLite getInstance() {
        return C0844a.f1638a;
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public IRunner setScript(Script4Run script4Run) {
        this.f1636f = script4Run;
        if (this.f1637g != null) {
            C0743b c0743b = this.f1637g;
            if (script4Run != null) {
                c0743b.f961e = script4Run;
            }
        }
        return this;
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public IRunner setOnScriptListener(OnScriptListener onScriptListener) {
        this.f1631a = onScriptListener;
        if (this.f1637g != null) {
            C0743b c0743b = this.f1637g;
            if (onScriptListener != null) {
                c0743b.f962f = onScriptListener;
            }
        }
        return this;
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void start() {
        if (this.f1637g != null) {
            C0743b c0743b = this.f1637g;
            if (c0743b.f957a) {
                if (c0743b.f962f != null) {
                    c0743b.f962f.onScriptIsRunning();
                    return;
                }
                return;
            }
            new C0743b.b(c0743b, (byte) 0).start();
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void pause() {
        if (this.f1637g != null) {
            this.f1637g.m707e().Pause();
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void resume() {
        if (this.f1637g != null) {
            this.f1637g.m707e().Resume();
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void stop() {
        if (this.f1637g != null) {
            C0743b c0743b = this.f1637g;
            new StringBuilder("stop: ").append(c0743b.getClass().getSimpleName());
            c0743b.m707e().Stop();
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public boolean isScriptStarted() {
        if (this.f1637g == null) {
            return false;
        }
        return this.f1637g.m710h();
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void notifyRotationStatus() {
        this.f1637g.m709g();
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: b */
    public final OnRequestCallback mo1302b() {
        return this.f1633c;
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: c */
    public final OnElfCallback mo1303c() {
        return this.f1632b;
    }

    public void setImageCrop(boolean z, int i, int i2, int i3, int i4) {
        this.f1637g.m708f().SetImageCrop(z, i, i2, i3, i4);
    }
}

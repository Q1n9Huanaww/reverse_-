package com.cyjh.mobileanjian.ipc.p017a;

import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.WindowManager;
import android.widget.Toast;
import com.cyjh.event.C0721c;
import com.cyjh.event.Injector;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.log.NativeLog;
import com.cyjh.mqm.MQLanguageStub;
import com.cyjh.mqm.MQLanguageStub.MQAuxiliary;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.sdk.entity.Script4Run;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.a.b */
/* JADX INFO: compiled from: ScriptRunnerLite.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0743b implements InterfaceC0742a {

    /* JADX INFO: renamed from: h */
    private static final int f953h = 1;

    /* JADX INFO: renamed from: i */
    private static final int f954i = 2;

    /* JADX INFO: renamed from: j */
    private static final int f955j = 3;

    /* JADX INFO: renamed from: k */
    private static final int f956k = 25;

    /* JADX INFO: renamed from: d */
    public c f960d;

    /* JADX INFO: renamed from: e */
    public Script4Run f961e;

    /* JADX INFO: renamed from: f */
    public OnScriptListener f962f;

    /* JADX INFO: renamed from: l */
    private MQLanguageStub f964l;

    /* JADX INFO: renamed from: a */
    public volatile boolean f957a = false;

    /* JADX INFO: renamed from: b */
    boolean f958b = false;

    /* JADX INFO: renamed from: c */
    public boolean f959c = false;

    /* JADX INFO: renamed from: g */
    public ArrayBlockingQueue<a> f963g = new ArrayBlockingQueue<>(4);

    /* JADX INFO: renamed from: m */
    private Handler f965m = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mobileanjian.ipc.a.b.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            switch (message.what) {
                case 1:
                    if (C0743b.this.f962f != null) {
                        C0743b.this.f962f.onStartScript();
                    }
                    break;
                case 2:
                    if (C0743b.this.f962f != null) {
                        C0743b.this.f962f.onStopScript(message.arg1, (String) message.obj);
                    }
                    break;
                case 3:
                    Toast.makeText(C0830a.f1527g.getApplicationContext(), (String) message.obj, 1).show();
                    break;
            }
        }
    };

    /* JADX INFO: renamed from: n */
    private MQLanguageStub.MQAuxiliary f966n = null;

    /* JADX INFO: renamed from: e */
    private static /* synthetic */ boolean m705e(C0743b c0743b) {
        c0743b.f958b = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.a.b$b */
    /* JADX INFO: compiled from: ScriptRunnerLite.java */
    public class b extends Thread {
        private b() {
        }

        public /* synthetic */ b(C0743b c0743b, byte b) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            if (C0743b.this.f961e == null) {
                return;
            }
            Looper.prepare();
            C0743b.m693a(C0743b.this);
            Looper.myLooper().quit();
            Looper.loop();
        }
    }

    @Override // com.cyjh.mobileanjian.ipc.p017a.InterfaceC0742a
    /* JADX INFO: renamed from: a */
    public final void mo688a() {
        if (this.f957a) {
            if (this.f962f != null) {
                this.f962f.onScriptIsRunning();
                return;
            }
            return;
        }
        new b(this, (byte) 0).start();
    }

    @Override // com.cyjh.mobileanjian.ipc.p017a.InterfaceC0742a
    /* JADX INFO: renamed from: b */
    public final void mo689b() {
        m707e().Resume();
    }

    @Override // com.cyjh.mobileanjian.ipc.p017a.InterfaceC0742a
    /* JADX INFO: renamed from: c */
    public final void mo690c() {
        m707e().Pause();
    }

    @Override // com.cyjh.mobileanjian.ipc.p017a.InterfaceC0742a
    /* JADX INFO: renamed from: d */
    public final void mo691d() {
        new StringBuilder("stop: ").append(getClass().getSimpleName());
        m707e().Stop();
    }

    /* JADX INFO: renamed from: e */
    public final MQLanguageStub m707e() {
        if (this.f964l == null) {
            this.f964l = new MQLanguageStub();
            new StringBuilder("Application: ").append(C0830a.f1527g);
            File file = new File(Environment.getExternalStorageDirectory(), C0830a.f1527g.getPackageName());
            if (!file.exists()) {
                file.mkdir();
            }
            this.f964l.SetLocalDir(Environment.getExternalStorageDirectory().getAbsolutePath(), file.getAbsolutePath(), new File(C0830a.f1527g.getApplicationContext().getFilesDir().getParent(), "lib").getAbsolutePath());
            this.f964l.SetWriteLog2File(true);
        }
        return this.f964l;
    }

    /* JADX INFO: renamed from: f */
    public final MQLanguageStub.MQAuxiliary m708f() {
        if (this.f966n == null) {
            MQLanguageStub mQLanguageStubM707e = m707e();
            mQLanguageStubM707e.getClass();
            this.f966n = mQLanguageStubM707e.new MQAuxiliary();
        }
        return this.f966n;
    }

    /* JADX INFO: renamed from: a */
    private void m698a(boolean z, int i, int i2, int i3, int i4) {
        m708f().SetImageCrop(z, i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: a */
    private void m695a(Script4Run script4Run) {
        if (script4Run != null) {
            this.f961e = script4Run;
        }
    }

    /* JADX INFO: renamed from: i */
    private void m706i() {
        if (this.f961e == null) {
            return;
        }
        this.f957a = true;
        this.f965m.obtainMessage(1).sendToTarget();
        m703c(C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_on_start_script));
        m709g();
        int iRun = m707e().Run(this.f961e.getLcPath(), this.f961e.getAtcPath(), this.f961e.getConfigPath(), this.f961e.getAppId(), this.f961e.getUserName(), this.f961e.getTrialTime(), this.f961e.getEncryptKey());
        if (iRun == 102) {
            m703c(String.format(C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_run_failed), Integer.valueOf(iRun)));
        }
        if (this.f965m != null) {
            Message messageObtainMessage = this.f965m.obtainMessage();
            messageObtainMessage.what = 2;
            messageObtainMessage.arg1 = iRun;
            messageObtainMessage.obj = NativeLog.getExtraLog();
            this.f965m.sendMessage(messageObtainMessage);
        }
        m703c(C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_on_stop_script));
        C0721c.m656c();
        Injector.release();
        NativeLog.reset();
        this.f957a = false;
    }

    /* JADX INFO: renamed from: a */
    private void m696a(String str) {
        try {
            if (!this.f959c) {
                this.f960d = new c("ScriptRunnerLite_Request");
                this.f960d.start();
            }
            this.f963g.put(new a(1, str, "", "", "", 0, 0, ""));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m697a(String str, String str2, String str3) {
        try {
            this.f963g.put(new a(2, "", str, str2, str3, 0, 0, ""));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m692a(int i, int i2) {
        try {
            this.f963g.put(new a(3, "", "", "", "", i, i2, ""));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m701b(String str) {
        try {
            this.f963g.put(new a(4, "", "", "", "", 0, 0, str));
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m694a(OnScriptListener onScriptListener) {
        if (onScriptListener != null) {
            this.f962f = onScriptListener;
        }
    }

    /* JADX INFO: renamed from: c */
    private void m703c(String str) {
        this.f965m.obtainMessage(3, str).sendToTarget();
    }

    /* JADX INFO: renamed from: h */
    public final synchronized boolean m710h() {
        return this.f957a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.a.b$c */
    /* JADX INFO: compiled from: ScriptRunnerLite.java */
    public class c extends Thread {
        public c(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() {
            Looper.prepare();
            C0743b.this.f959c = true;
            while (C0830a.f1527g != null) {
                try {
                    a aVarTake = C0743b.this.f963g.take();
                    new StringBuilder("Take Request:\n").append(aVarTake);
                    switch (aVarTake.f974g) {
                        case 0:
                            C0743b.m693a(C0743b.this);
                            break;
                        case 1:
                            C0743b.this.m707e();
                            String str = aVarTake.f975h;
                            C0743b.this.f958b = true;
                            break;
                        case 2:
                            C0743b.this.m707e();
                            MQLanguageStub.InitElf(aVarTake.f976i, aVarTake.f977j, aVarTake.f978k);
                            break;
                        case 3:
                            C0743b.this.m707e();
                            MQLanguageStub.SetHeartBeatTime(aVarTake.f979l, aVarTake.f980m);
                            break;
                        case 4:
                            C0743b.this.m707e();
                            MQLanguageStub.SetRegCode(aVarTake.f981n);
                            break;
                    }
                } catch (InterruptedException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            Looper.myLooper().quit();
            Looper.loop();
            C0743b.this.f959c = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.a.b$a */
    /* JADX INFO: compiled from: ScriptRunnerLite.java */
    public class a {

        /* JADX INFO: renamed from: a */
        public static final int f968a = 0;

        /* JADX INFO: renamed from: b */
        public static final int f969b = 1;

        /* JADX INFO: renamed from: c */
        public static final int f970c = 2;

        /* JADX INFO: renamed from: d */
        public static final int f971d = 3;

        /* JADX INFO: renamed from: e */
        public static final int f972e = 4;

        /* JADX INFO: renamed from: f */
        public static final int f973f = 16;

        /* JADX INFO: renamed from: g */
        public int f974g;

        /* JADX INFO: renamed from: h */
        public String f975h;

        /* JADX INFO: renamed from: i */
        public String f976i;

        /* JADX INFO: renamed from: j */
        public String f977j;

        /* JADX INFO: renamed from: k */
        public String f978k;

        /* JADX INFO: renamed from: l */
        public int f979l;

        /* JADX INFO: renamed from: m */
        public int f980m;

        /* JADX INFO: renamed from: n */
        public String f981n;

        public final String toString() {
            return "";
        }

        public a(int i, String str, String str2, String str3, String str4, int i2, int i3, String str5) {
            this.f974g = i;
            this.f975h = str;
            this.f976i = str2;
            this.f977j = str3;
            this.f978k = str4;
            this.f979l = i2;
            this.f980m = i3;
            this.f981n = str5;
        }
    }

    /* JADX INFO: renamed from: g */
    public final void m709g() {
        m708f().SetScreenRotation(((WindowManager) C0830a.f1527g.getSystemService("window")).getDefaultDisplay().getRotation());
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m693a(C0743b c0743b) {
        if (c0743b.f961e != null) {
            c0743b.f957a = true;
            c0743b.f965m.obtainMessage(1).sendToTarget();
            c0743b.m703c(C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_on_start_script));
            c0743b.m709g();
            int iRun = c0743b.m707e().Run(c0743b.f961e.getLcPath(), c0743b.f961e.getAtcPath(), c0743b.f961e.getConfigPath(), c0743b.f961e.getAppId(), c0743b.f961e.getUserName(), c0743b.f961e.getTrialTime(), c0743b.f961e.getEncryptKey());
            if (iRun == 102) {
                c0743b.m703c(String.format(C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_run_failed), Integer.valueOf(iRun)));
            }
            if (c0743b.f965m != null) {
                Message messageObtainMessage = c0743b.f965m.obtainMessage();
                messageObtainMessage.what = 2;
                messageObtainMessage.arg1 = iRun;
                messageObtainMessage.obj = NativeLog.getExtraLog();
                c0743b.f965m.sendMessage(messageObtainMessage);
            }
            c0743b.m703c(C0830a.f1527g.getApplicationContext().getString(C0852R.string.toast_on_stop_script));
            C0721c.m656c();
            Injector.release();
            NativeLog.reset();
            c0743b.f957a = false;
        }
    }
}

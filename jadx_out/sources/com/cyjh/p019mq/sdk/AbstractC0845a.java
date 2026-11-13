package com.cyjh.p019mq.sdk;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;
import com.cyjh.mobileanjian.ipc.C0746d;
import com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver;
import com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScriptListener;
import com.cyjh.mobileanjian.ipc.share.proto.C0769a;
import com.cyjh.mobileanjian.ipc.share.proto.C0770b;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.p022c.C0833b;
import com.cyjh.p019mq.p023d.C0839d;
import com.cyjh.p019mq.sdk.entity.Script4Run;
import com.cyjh.p019mq.sdk.inf.IRunner;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.protobuf.ByteString;
import java.io.File;
import org.apache.commons.p092io.FileUtils;
import org.apache.commons.p092io.FilenameUtils;

/* JADX INFO: renamed from: com.cyjh.mq.sdk.a */
/* JADX INFO: compiled from: GeneralMqRunner.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0845a implements EngineStateObserver, OnRootApplyCallback, IRunner {

    /* JADX INFO: renamed from: a */
    protected static final int f1639a = 256;

    /* JADX INFO: renamed from: c */
    protected Script4Run f1641c;

    /* JADX INFO: renamed from: g */
    private OnScriptListener f1645g;

    /* JADX INFO: renamed from: b */
    protected C0833b f1640b = null;

    /* JADX INFO: renamed from: e */
    protected boolean f1643e = false;

    /* JADX INFO: renamed from: h */
    private boolean f1646h = false;

    /* JADX INFO: renamed from: f */
    protected boolean f1644f = false;

    /* JADX INFO: renamed from: d */
    protected Handler f1642d = new Handler(Looper.getMainLooper()) { // from class: com.cyjh.mq.sdk.a.1
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 256 || C0830a.f1529i == null) {
                return;
            }
            if (message.what == 4) {
                Toast.makeText(C0830a.f1527g.getApplicationContext(), C0830a.f1527g.getString(C0852R.string.toast_failed_got_root, new Object[]{C0839d.m1288a(C0830a.f1527g.getApplicationContext())}), 1).show();
            }
            C0830a.f1529i.onRootProgress((String) message.obj, message.what);
        }
    };

    /* JADX INFO: renamed from: com.cyjh.mq.sdk.a$a */
    /* JADX INFO: compiled from: GeneralMqRunner.java */
    private class a extends AsyncTask<Script4Run, Void, Script4Run> {
        private a() {
        }

        /* synthetic */ a(AbstractC0845a abstractC0845a, byte b) {
            this();
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ Script4Run doInBackground(Script4Run[] script4RunArr) {
            return m1316a(script4RunArr);
        }

        @Override // android.os.AsyncTask
        protected final /* synthetic */ void onPostExecute(Script4Run script4Run) {
            Script4Run script4Run2 = script4Run;
            if (script4Run2 != null) {
                AbstractC0845a.this.m1315a(script4Run2.toMessage(7));
                AbstractC0845a.this.m1315a(script4Run2.toMessage(1));
            }
        }

        /* JADX INFO: renamed from: a */
        private static Script4Run m1316a(Script4Run... script4RunArr) throws Throwable {
            Script4Run script4Run = script4RunArr[0];
            try {
                File file = new File(C0830a.f1527g.getApplicationContext().getFilesDir(), "script");
                if (!file.exists()) {
                    file.mkdirs();
                    file.setReadable(true, false);
                    file.setExecutable(true, false);
                } else {
                    FileUtils.deleteDirectory(file);
                }
                File[] fileArr = {new File(script4Run.getLcPath()), new File(script4Run.getAtcPath()), new File(script4Run.getConfigPath())};
                File file2 = new File(file, FilenameUtils.getName(script4Run.getLcPath()));
                File file3 = new File(file, FilenameUtils.getName(script4Run.getAtcPath()));
                File file4 = new File(file, FilenameUtils.getName(script4Run.getConfigPath()));
                File[] fileArr2 = new File[3];
                fileArr2[0] = file2;
                fileArr2[1] = file3;
                fileArr2[2] = file4;
                for (int i = 0; i < 3; i++) {
                    if (fileArr[i].exists()) {
                        FileUtils.copyFile(fileArr[i], fileArr2[i]);
                        fileArr2[i].setReadable(true, false);
                    } else {
                        fileArr2[i] = fileArr[i];
                    }
                }
                Script4Run script4RunM5181clone = script4Run.m5181clone();
                script4RunM5181clone.setLcPath(file2.getAbsolutePath()).setAtcPath(file3.getAbsolutePath()).setConfigPath(file4.getAbsolutePath());
                return script4RunM5181clone;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return null;
            }
        }

        /* JADX INFO: renamed from: a */
        private void m1317a(Script4Run script4Run) {
            if (script4Run != null) {
                AbstractC0845a.this.m1315a(script4Run.toMessage(7));
                AbstractC0845a.this.m1315a(script4Run.toMessage(1));
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1313a(Script4Run script4Run) {
        new a(this, (byte) 0).execute(script4Run);
    }

    /* JADX INFO: renamed from: a */
    protected final void m1315a(Ipc.IpcMessage ipcMessage) {
        if (this.f1643e) {
            this.f1640b.m1234a(ipcMessage);
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public IRunner setScript(Script4Run script4Run) {
        this.f1641c = script4Run;
        m1315a(script4Run.toMessage(7));
        return this;
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public IRunner setOnScriptListener(OnScriptListener onScriptListener) {
        this.f1645g = onScriptListener;
        if (this.f1640b != null) {
            this.f1640b.f1555l = this.f1645g;
        }
        return this;
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void pause() {
        m1315a(C0770b.m800a(2));
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void resume() {
        m1315a(C0770b.m800a(9));
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void stop() {
        Log.d("JAVA_RUNNER", "GeneralMqRunner stop");
        m1315a(C0770b.m800a(3));
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public boolean isScriptStarted() {
        if (this.f1640b == null) {
            return false;
        }
        return this.f1640b.f1548e;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onConnected(C0833b c0833b) {
        this.f1642d.removeMessages(256);
        this.f1643e = true;
        this.f1640b = c0833b;
        this.f1646h = false;
        setOnScriptListener(this.f1645g);
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: a */
    public final void mo1305a(ByteString byteString) {
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(64).setFileData(byteString).build());
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: b */
    public final void mo1308b(ByteString byteString) {
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(65).setFileData(byteString).build());
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: c */
    public final void mo1309c(ByteString byteString) {
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(C0769a.f1102aH).setFileData(byteString).build());
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: a */
    public final void mo1306a(String str) {
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(C0769a.f1103aI).addArg2(str).build());
    }

    /* JADX INFO: renamed from: a */
    protected final void m1314a() {
        if (this.f1646h) {
            return;
        }
        this.f1646h = true;
        File fileM1168a = C0830a.m1168a();
        C0746d.m728a().m743a("setenforce 0");
        C0746d.m728a().m743a("chmod 777 /dev/input/*");
        C0746d.m728a().m743a(fileM1168a.getAbsolutePath());
        this.f1642d.sendEmptyMessageDelayed(256, 6000L);
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback
    public void onObtained() {
        m1314a();
        this.f1644f = false;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.OnRootApplyCallback
    public void onRefused() {
        if (RootUtil.isRoot()) {
            this.f1642d.obtainMessage(4, "User refused root.").sendToTarget();
        }
        this.f1644f = false;
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void notifyRotationStatus() {
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(15).addArg1(((WindowManager) C0830a.f1527g.getSystemService("window")).getDefaultDisplay().getRotation()).build());
    }
}

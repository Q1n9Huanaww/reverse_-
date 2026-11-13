package com.cyjh.mobileanjian.ipc.uip;

import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mqm.MQUipStub;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: loaded from: classes.dex */
public class UisScriptRunner {

    /* JADX INFO: renamed from: a */
    private static UisScriptRunner f1371a;

    /* JADX INFO: renamed from: b */
    private static boolean f1372b;

    /* JADX INFO: renamed from: c */
    private MQUipStub f1373c = new MQUipStub();

    private UisScriptRunner() {
    }

    public static UisScriptRunner getInstance() {
        if (f1371a == null) {
            f1371a = new UisScriptRunner();
        }
        return f1371a;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [com.cyjh.mobileanjian.ipc.uip.UisScriptRunner$1] */
    public void startLoop(final String str) {
        if (f1372b) {
            stopLoop();
            while (f1372b) {
                try {
                    Thread.sleep(50L);
                } catch (InterruptedException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
        new Thread() { // from class: com.cyjh.mobileanjian.ipc.uip.UisScriptRunner.1
            @Override // java.lang.Thread, java.lang.Runnable
            public final void run() {
                super.run();
                boolean unused = UisScriptRunner.f1372b = true;
                UisScriptRunner.this.f1373c.StartLoop(str, 0L);
                boolean unused2 = UisScriptRunner.f1372b = false;
            }
        }.start();
    }

    public void stopLoop() {
        if (f1372b) {
            this.f1373c.StopLoop();
            UipEventStub.hasEvent(UiMessage.UipToCommand.newBuilder().setCommand(UiMessage.UipToCommand.Command_Type.EVENT).setIsSuccess(true).setEvent(UiMessage.ControlEvent.newBuilder().setControlId("stop_id").setType(UiMessage.ControlEvent.Event_Type.ON_CLOSE_EXIT).build()).build().toByteString());
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m1027a() {
        try {
            Thread.sleep(50L);
        } catch (InterruptedException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

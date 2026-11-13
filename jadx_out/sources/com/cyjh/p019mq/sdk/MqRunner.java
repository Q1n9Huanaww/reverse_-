package com.cyjh.p019mq.sdk;

import com.cyjh.mobileanjian.ipc.C0744b;
import com.cyjh.mobileanjian.ipc.interfaces.OnRequestCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;
import com.cyjh.mobileanjian.ipc.share.proto.C0770b;
import com.cyjh.mobileanjian.ipc.share.proto.Ipc;
import com.cyjh.mqm.MQLanguageStub;
import com.cyjh.p019mq.p022c.C0833b;
import com.cyjh.p019mq.sdk.inf.InterfaceC0846a;
import com.cyjh.p019mq.sdk.inf.OnElfCallback;

/* JADX INFO: loaded from: classes.dex */
public class MqRunner extends AbstractC0845a implements InterfaceC0846a {

    /* JADX INFO: renamed from: k */
    private OnElfCallback f1623k;

    /* JADX INFO: renamed from: m */
    private int f1625m;

    /* JADX INFO: renamed from: n */
    private int f1626n;

    /* JADX INFO: renamed from: o */
    private int f1627o;

    /* JADX INFO: renamed from: p */
    private int f1628p;

    /* JADX INFO: renamed from: q */
    private OnScreenShotCallback f1629q;

    /* JADX INFO: renamed from: g */
    private String f1619g = "";

    /* JADX INFO: renamed from: h */
    private String f1620h = "";

    /* JADX INFO: renamed from: i */
    private String f1621i = "";

    /* JADX INFO: renamed from: j */
    private String f1622j = "";

    /* JADX INFO: renamed from: l */
    private boolean f1624l = false;

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: b */
    public final OnRequestCallback mo1302b() {
        return null;
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0848c
    /* JADX INFO: renamed from: c */
    public final OnElfCallback mo1303c() {
        return null;
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onExit() {
    }

    /* JADX INFO: renamed from: com.cyjh.mq.sdk.MqRunner$a */
    private static class C0842a {

        /* JADX INFO: renamed from: a */
        private static final MqRunner f1630a = new MqRunner();

        private C0842a() {
        }
    }

    public static synchronized MqRunner getInstance() {
        return C0842a.f1630a;
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void initHost(String str) {
        MQLanguageStub.InitHost(str);
        if (this.f1643e) {
            m1315a(Ipc.IpcMessage.newBuilder().setCmd(83).addArg2(str).build());
        } else {
            this.f1619g = str;
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void initElf(String str, String str2, String str3, OnElfCallback onElfCallback) {
        this.f1620h = str;
        this.f1621i = str2;
        this.f1622j = str3;
        this.f1623k = onElfCallback;
        MQLanguageStub.InitElf(str, str2, str3);
        if (this.f1643e) {
            this.f1640b.f1558o = onElfCallback;
            m1315a(Ipc.IpcMessage.newBuilder().setCmd(80).addArg2(str).addArg2(str2).addArg2(str3).build());
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void setHeartBeat(int i, int i2) {
        this.f1625m = i;
        this.f1626n = i2;
        if (this.f1643e) {
            m1315a(Ipc.IpcMessage.newBuilder().setCmd(84).addArg1(i).addArg1(i2).build());
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void setOnSpecialMqCmdCallback(OnSpecialMqCmdCallback onSpecialMqCmdCallback) {
        if (this.f1643e) {
            this.f1640b.f1560q = onSpecialMqCmdCallback;
        }
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void setRegCode(String str) {
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(82).addArg2(str).build());
    }

    @Override // com.cyjh.p019mq.sdk.AbstractC0845a, com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onConnected(C0833b c0833b) {
        super.onConnected(c0833b);
        c0833b.f1558o = this.f1623k;
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(80).addArg2(this.f1620h).addArg2(this.f1621i).addArg2(this.f1622j).build());
        m1315a(Ipc.IpcMessage.newBuilder().setCmd(83).addArg2(this.f1619g).build());
        setHeartBeat(this.f1625m, this.f1626n);
    }

    @Override // com.cyjh.mobileanjian.ipc.interfaces.EngineStateObserver
    public void onCrash(C0744b c0744b) {
        this.f1643e = false;
        m1314a();
    }

    @Override // com.cyjh.p019mq.sdk.inf.IRunner
    public void start() {
        notifyRotationStatus();
        m1315a(C0770b.m800a(1));
    }

    @Override // com.cyjh.p019mq.sdk.inf.InterfaceC0846a
    public void takeShot(int i, int i2, OnScreenShotCallback onScreenShotCallback) {
        if (this.f1640b == null) {
            this.f1627o = i;
            this.f1628p = i2;
            this.f1629q = onScreenShotCallback;
        } else {
            this.f1640b.f1547d = true;
            this.f1640b.f1552i = onScreenShotCallback;
            this.f1640b.m1234a(Ipc.IpcMessage.newBuilder().setCmd(24).addArg1(i).build());
        }
    }
}

package com.cyjh.p019mq.sdk.inf;

import com.cyjh.mobileanjian.ipc.interfaces.OnScreenShotCallback;
import com.cyjh.mobileanjian.ipc.interfaces.OnSpecialMqCmdCallback;

/* JADX INFO: renamed from: com.cyjh.mq.sdk.inf.a */
/* JADX INFO: compiled from: CustomRunner.java */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0846a {
    void initElf(String str, String str2, String str3, OnElfCallback onElfCallback);

    void initHost(String str);

    void setHeartBeat(int i, int i2);

    void setOnSpecialMqCmdCallback(OnSpecialMqCmdCallback onSpecialMqCmdCallback);

    void setRegCode(String str);

    void takeShot(int i, int i2, OnScreenShotCallback onScreenShotCallback);
}

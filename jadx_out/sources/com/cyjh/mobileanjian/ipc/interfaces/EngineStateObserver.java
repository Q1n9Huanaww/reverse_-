package com.cyjh.mobileanjian.ipc.interfaces;

import com.cyjh.mobileanjian.ipc.C0744b;
import com.cyjh.p019mq.p022c.C0833b;

/* JADX INFO: loaded from: classes.dex */
public interface EngineStateObserver {
    void onConnected(C0833b c0833b);

    void onCrash(C0744b c0744b);

    void onExit();
}

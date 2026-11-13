package com.p024ds.daisi.activity;

import com.p024ds.daisi.entity.ElfinFloatViewEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
final /* synthetic */ class ElfinPayActivity$$Lambda$1 implements Runnable {
    static final Runnable $instance = new ElfinPayActivity$$Lambda$1();

    private ElfinPayActivity$$Lambda$1() {
    }

    @Override // java.lang.Runnable
    public void run() {
        EventBus.getDefault().post(new ElfinFloatViewEvent());
    }
}

package com.p024ds.daisi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.p024ds.daisi.entity.AdEvent;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class InstallAndRemoveAppSuccessReceive extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.PACKAGE_ADDED")) {
            EventBus.getDefault().post(new AdEvent(101, intent.getDataString()));
        }
        if (intent.getAction().equals("android.intent.action.PACKAGE_REMOVED")) {
            EventBus.getDefault().post(new AdEvent(102, intent.getDataString()));
        }
    }
}

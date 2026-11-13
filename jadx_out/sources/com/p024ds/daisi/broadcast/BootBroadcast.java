package com.p024ds.daisi.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.services.TimerService;

/* JADX INFO: loaded from: classes.dex */
public class BootBroadcast extends BroadcastReceiver {
    private static final String ACTION_BOOT = "android.intent.action.BOOT_COMPLETED";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.e("zzz", "BootBroadcast:开机启动广播" + action);
        if (ACTION_BOOT.equals(action)) {
            boolean z = ((AppContext) context.getApplicationContext()).sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_OPEN_RUN_SCRIPT, false);
            Log.e("zzz", "BootBroadcast:开机启动广播" + z);
            if (z) {
                AppContext.sIsBooted = true;
                context.startService(new Intent(context, (Class<?>) TimerService.class));
            }
        }
    }
}

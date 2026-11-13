package com.iflytek.voiceads.download;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import java.util.Map;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.o */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
class C1555o extends BroadcastReceiver {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ C1541d f3295a;

    C1555o(C1541d c1541d) {
        this.f3295a = c1541d;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.PACKAGE_ADDED".equals(intent.getAction())) {
            C1593g.m3188a(SDKConstants.TAG, "app add" + intent.getDataString());
            for (Map.Entry entry : this.f3295a.f3238d.entrySet()) {
                if (((C1541d.b) entry.getValue()).f3258h == 1) {
                    C1593g.m3188a(SDKConstants.TAG, ((C1541d.b) entry.getValue()).f3253c + "//" + intent.getDataString());
                    if (intent.getDataString().contains(((C1541d.b) entry.getValue()).f3253c) || "noPackage".equals(((C1541d.b) entry.getValue()).f3253c)) {
                        ((C1541d.b) entry.getValue()).f3258h = 2;
                        this.f3295a.m2983a(((C1541d.b) entry.getValue()).f3257g);
                    }
                    this.f3295a.f3238d.remove(((C1541d.b) entry.getValue()).f3252b);
                    C1593g.m3188a(SDKConstants.TAG, "安装完成监控");
                }
            }
            this.f3295a.m2991c();
        }
    }
}

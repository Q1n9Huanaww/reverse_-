package com.p024ds.daisi.pay;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.fragment.OptionFragment;
import com.p024ds.daisi.log.CommonLog;

/* JADX INFO: loaded from: classes.dex */
public class HandlerCheckAppResult implements PayConstants {
    private AppContext appContext;
    private CommonLog commonLog = new CommonLog();

    public HandlerCheckAppResult(AppContext appContext) {
        this.appContext = appContext;
    }

    public void onCheckAppResult(int i, int i2) {
        this.commonLog.m1348e("ICheckAppResult>>>iResult>>>" + i);
        if (i != 4) {
            switch (i) {
                case 0:
                    this.appContext.STATE_APP = 0;
                    break;
                case 1:
                    this.appContext.STATE_APP = 1;
                    break;
                case 2:
                    this.appContext.STATE_APP = 2;
                    break;
            }
        } else {
            this.appContext.STATE_APP = 4;
        }
        String str = i2 + "";
        this.commonLog.m1348e("ICheckAppResult>>>使用时间>>>" + str);
        sendDuTimeBroadcast(str);
    }

    private void sendDuTimeBroadcast(String str) {
        Intent intent = new Intent(OptionFragment.ACTION_DUETIME);
        intent.putExtra(OptionFragment.INTENT_EXTRA_DUETIME, str);
        LocalBroadcastManager.getInstance(this.appContext).sendBroadcast(intent);
    }
}

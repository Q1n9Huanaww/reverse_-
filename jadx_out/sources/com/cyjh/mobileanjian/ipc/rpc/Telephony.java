package com.cyjh.mobileanjian.ipc.rpc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

/* JADX INFO: loaded from: classes.dex */
public class Telephony {

    /* JADX INFO: renamed from: a */
    private static Context f1045a;

    public static void init(Context context) {
        f1045a = context;
    }

    public static void dialNumber(String str, String str2) {
        Intent intent;
        if (str2.equals("1")) {
            intent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str));
        } else {
            intent = new Intent("android.intent.action.DIAL", Uri.parse("tel:" + str));
        }
        intent.addFlags(268435456);
        f1045a.startActivity(intent);
    }

    public static void sendSMS(String str, String str2) {
        SmsManager smsManager = SmsManager.getDefault();
        if (smsManager != null) {
            smsManager.sendTextMessage(str, null, str2, null, null);
        }
    }
}

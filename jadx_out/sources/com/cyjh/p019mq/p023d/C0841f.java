package com.cyjh.p019mq.p023d;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.telephony.TelephonyManager;
import com.p024ds.daisi.net.URL.URLConstant;

/* JADX INFO: renamed from: com.cyjh.mq.d.f */
/* JADX INFO: compiled from: TelephonyUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0841f {
    /* JADX INFO: renamed from: b */
    private static String m1301b(@NonNull Context context) {
        String deviceId = ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getDeviceId();
        return (deviceId == null || deviceId.equals("")) ? m1300a(context) : deviceId;
    }

    /* JADX INFO: renamed from: a */
    public static String m1300a(@NonNull Context context) {
        WifiInfo connectionInfo = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo();
        return connectionInfo == null ? "" : connectionInfo.getMacAddress();
    }
}

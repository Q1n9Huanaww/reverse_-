package com.p024ds.daisi.net.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/* JADX INFO: loaded from: classes.dex */
public class NetworkStatusReceiver extends BroadcastReceiver {
    private NetworkStatusReceiverListener networkrListener;

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = activeNetworkInfo != null && activeNetworkInfo.isAvailable();
        if (this.networkrListener != null) {
            this.networkrListener.onReceive(z);
        }
    }

    public void setNetworkrListener(NetworkStatusReceiverListener networkStatusReceiverListener) {
        this.networkrListener = networkStatusReceiverListener;
    }
}

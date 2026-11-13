package com.goldcoast.sdk.domain;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;

/* JADX INFO: renamed from: com.goldcoast.sdk.domain.c */
/* JADX INFO: compiled from: EntryPoint.java */
/* JADX INFO: loaded from: classes.dex */
final class C1130c extends BroadcastReceiver {

    /* JADX INFO: renamed from: a */
    final /* synthetic */ EntryPoint f1844a;

    C1130c(EntryPoint entryPoint) {
        this.f1844a = entryPoint;
    }

    @Override // android.content.BroadcastReceiver
    public final void onReceive(Context context, Intent intent) {
        NetworkInfo activeNetworkInfo = this.f1844a.f1836v.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return;
        }
        this.f1844a.m1469f();
    }
}

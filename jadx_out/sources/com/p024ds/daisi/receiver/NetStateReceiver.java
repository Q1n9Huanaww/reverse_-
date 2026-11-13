package com.p024ds.daisi.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.cyjh.share.util.SlLog;
import com.p024ds.daisi.util.NetworkUtils;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class NetStateReceiver extends BroadcastReceiver {
    private static final String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private static final String TAG = "NetStateReceiver";
    private static boolean isNetAvailable;
    private static BroadcastReceiver mBroadcastReceiver;
    private static ArrayList<NetChangeObserver> mNetChangeObservers = new ArrayList<>();
    private static int mNetType;

    public interface NetChangeObserver {
        void onNetConnected(int i);

        void onNetDisConnect();
    }

    private static BroadcastReceiver getReceiver() {
        if (mBroadcastReceiver == null) {
            synchronized (NetStateReceiver.class) {
                if (mBroadcastReceiver == null) {
                    mBroadcastReceiver = new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)) {
            if (!NetworkUtils.isAvailable(context)) {
                SlLog.m1333e(getClass().getName(), "<--- network disconnected --->");
                isNetAvailable = false;
            } else {
                SlLog.m1333e(getClass().getName(), "<--- network connected --->");
                isNetAvailable = true;
                mNetType = NetworkUtils.getNetWorkType(context);
            }
            notifyObserver();
        }
    }

    public static void registerNetworkStateReceiver(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ANDROID_NET_CHANGE_ACTION);
        context.getApplicationContext().registerReceiver(getReceiver(), intentFilter);
    }

    public static void checkNetworkState(Context context) {
        Intent intent = new Intent();
        intent.setAction(ANDROID_NET_CHANGE_ACTION);
        context.sendBroadcast(intent);
    }

    public static void unRegisterNetworkStateReceiver(Context context) {
        if (mBroadcastReceiver != null) {
            try {
                context.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    public static int getAPNType() {
        return mNetType;
    }

    private void notifyObserver() {
        if (mNetChangeObservers.isEmpty()) {
            return;
        }
        int size = mNetChangeObservers.size();
        for (int i = 0; i < size; i++) {
            NetChangeObserver netChangeObserver = mNetChangeObservers.get(i);
            if (netChangeObserver != null) {
                if (isNetworkAvailable()) {
                    netChangeObserver.onNetConnected(mNetType);
                } else {
                    netChangeObserver.onNetDisConnect();
                }
            }
        }
    }

    public static void registerObserver(NetChangeObserver netChangeObserver) {
        if (mNetChangeObservers == null) {
            mNetChangeObservers = new ArrayList<>();
        }
        mNetChangeObservers.add(netChangeObserver);
    }

    public static void removeRegisterObserver(NetChangeObserver netChangeObserver) {
        if (mNetChangeObservers == null || !mNetChangeObservers.contains(netChangeObserver)) {
            return;
        }
        mNetChangeObservers.remove(netChangeObserver);
    }
}

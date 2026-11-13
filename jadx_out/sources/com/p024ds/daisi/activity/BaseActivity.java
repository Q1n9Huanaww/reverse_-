package com.p024ds.daisi.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.cyjh.share.manager.ActivitysManager;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.mvp.views.BaseView;
import com.p024ds.daisi.net.network.NetworkStatusReceiver;
import com.p024ds.daisi.net.network.NetworkStatusReceiverListener;
import com.p024ds.daisi.util.ToastUtils;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class BaseActivity extends FragmentActivity implements APPConstant, BaseView {
    protected AppContext appContext;
    protected CommonLog commonLog = new CommonLog();
    private NetworkStatusReceiver mNetworkReceiver;
    private NetworkStatusReceiverListener networkStatusListener;
    private ProgressDialog progressDialog;

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void hideProgress() {
    }

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void hideProgressDialog() {
    }

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void showProgress() {
    }

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void showProgressDialog(Context context, int i) {
    }

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.appContext = (AppContext) getApplicationContext();
        requestWindowFeature(1);
        ActivitysManager.getActivitysManager().addActivity(this);
        this.networkStatusListener = new NetworkStatusReceiverListener() { // from class: com.ds.daisi.activity.BaseActivity.1
            @Override // com.p024ds.daisi.net.network.NetworkStatusReceiverListener
            public void onReceive(boolean z) {
                if (z) {
                    return;
                }
                ToastUtils.showToastLong(BaseActivity.this, R.string.network_off);
            }
        };
        this.mNetworkReceiver = new NetworkStatusReceiver();
        this.mNetworkReceiver.setNetworkrListener(this.networkStatusListener);
        registerReceiver(this.mNetworkReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.mNetworkReceiver);
        ActivitysManager.getActivitysManager().finishActivity(this);
    }

    protected void showProgressDialog(Context context, int i, int i2) {
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setTitle(context.getString(i));
        this.progressDialog.setProgressStyle(0);
        this.progressDialog.setMessage(context.getString(i2));
        this.progressDialog.setCanceledOnTouchOutside(false);
        this.progressDialog.show();
    }

    protected void dismissProgressDialog() {
        this.progressDialog.dismiss();
    }
}

package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.p024ds.daisi.fragment.SplashFragment;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class SplashActivity extends BaseActivity {
    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_splash);
        if (!isTaskRoot()) {
            finish();
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.setPrimaryNavigationFragment(null);
        fragmentTransactionBeginTransaction.replace(R.id.id_splash_fragment, SplashFragment.newInstance(false)).commitAllowingStateLoss();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
    }
}

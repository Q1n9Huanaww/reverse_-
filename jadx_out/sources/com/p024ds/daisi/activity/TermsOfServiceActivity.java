package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.view.View;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.LicenseFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class TermsOfServiceActivity extends BaseActivity {
    private TitleView mTitleView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_terms_service);
        initView();
        replaceFragment();
    }

    private void initView() {
        this.mTitleView = (TitleView) findViewById(R.id.id_title);
        this.mTitleView.setTitleText(getString(R.string.terms_of_service_title));
        this.mTitleView.setVisibilityRightImage(4);
        this.mTitleView.setleftImage(R.drawable.ic_back);
        this.mTitleView.setOnLeftImageViewListener(new OnLeftImageViewOnClicker());
    }

    private void replaceFragment() {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fl_terms_service, LicenseFragment.newInstance()).commit();
    }

    private class OnLeftImageViewOnClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            TermsOfServiceActivity.this.finish();
        }
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}

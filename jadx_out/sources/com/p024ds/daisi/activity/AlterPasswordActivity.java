package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.AlterPasswordFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class AlterPasswordActivity extends BaseActivity {
    private FragmentManager fragmentManager;
    private TitleView mTitleView;
    private String userAccount;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_alter_password);
        this.userAccount = getIntent().getStringExtra(AlterPasswordActivity.class.getCanonicalName());
        initView();
        replaceFragment();
    }

    private void initView() {
        this.mTitleView = (TitleView) findViewById(R.id.id_title);
        this.mTitleView.setTitleText(getString(R.string.change_password_title));
        this.mTitleView.setleftImage(R.drawable.ic_back);
        this.mTitleView.setVisibilityRightImage(4);
        this.mTitleView.setOnLeftImageViewListener(new OnLeftImageViewClicker());
    }

    private void replaceFragment() {
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentManager.beginTransaction().replace(R.id.id_fl_reset_password, AlterPasswordFragment.newInstance(this.userAccount)).commit();
    }

    private class OnLeftImageViewClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            AlterPasswordActivity.this.finish();
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

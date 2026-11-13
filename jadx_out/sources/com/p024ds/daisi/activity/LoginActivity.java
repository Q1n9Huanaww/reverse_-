package com.p024ds.daisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.LoginFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class LoginActivity extends BaseActivity {
    private TitleView mTitleView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_login);
        int intExtra = getIntent().getIntExtra(LoginActivity.class.getCanonicalName(), -1);
        initView();
        replaceFragment(intExtra);
    }

    private void initView() {
        this.mTitleView = (TitleView) findViewById(R.id.id_title);
        this.mTitleView.setleftImage(R.drawable.ic_back);
        this.mTitleView.setTitleText(getString(R.string.login_title));
        this.mTitleView.setVisibilityTvRight(0);
        this.mTitleView.setRightTvText(getString(R.string.register));
        this.mTitleView.setVisibilityRightImage(8);
        this.mTitleView.setOnLeftImageViewListener(new OnLeftImageViewOnClicker());
        this.mTitleView.setOnRightTextViewListener(new OnRightTextViewOnClicker());
    }

    private void replaceFragment(int i) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fl_login, LoginFragment.newInstance(i)).commit();
    }

    private class OnLeftImageViewOnClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            LoginActivity.this.finish();
        }
    }

    private class OnRightTextViewOnClicker implements TitleView.OnRightTextViewListener {
        private OnRightTextViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnRightTextViewListener
        public void onClicker(View view) {
            Intent intent = new Intent(LoginActivity.this, (Class<?>) RegisterActivity.class);
            intent.putExtra(RegisterActivity.class.getCanonicalName(), "login");
            LoginActivity.this.startActivity(intent);
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

package com.p024ds.daisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.RegisterOneFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class RegisterActivity extends BaseActivity {
    public static final String REGISTER_BY_LOGIN = "login";
    public static final String REGISTER_BY_MAIN = "main";
    private FragmentManager fragmentManager;
    private TitleView mTitle;
    public String registerJumpType;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register);
        this.registerJumpType = getIntent().getStringExtra(RegisterActivity.class.getCanonicalName());
        initView();
        replaceFragment();
    }

    private void initView() {
        this.mTitle = (TitleView) findViewById(R.id.id_title);
        this.mTitle.setleftImage(R.drawable.ic_back);
        this.mTitle.setOnLeftImageViewListener(new OnLeftImageViewListener());
        this.mTitle.setVisibilityRightImage(8);
        this.mTitle.setRightTvText(getString(R.string.login_title));
        this.mTitle.setTitleText(getString(R.string.register_title));
        this.mTitle.setVisibilityTvRight(0);
        this.mTitle.setOnRightTextViewListener(new OnRightTextViewOnClicker());
    }

    private void replaceFragment() {
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentManager.beginTransaction().replace(R.id.id_fl_register, RegisterOneFragment.newInstance()).commit();
    }

    private class OnLeftImageViewListener implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            if (RegisterActivity.this.fragmentManager.getBackStackEntryCount() > 0) {
                Logger.m3442e("fragment退出管理:" + RegisterActivity.this.fragmentManager.getBackStackEntryCount(), new Object[0]);
                RegisterActivity.this.fragmentManager.popBackStack();
                return;
            }
            RegisterActivity.this.finish();
        }
    }

    private class OnRightTextViewOnClicker implements TitleView.OnRightTextViewListener {
        private OnRightTextViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnRightTextViewListener
        public void onClicker(View view) {
            Intent intent = new Intent(RegisterActivity.this, (Class<?>) LoginActivity.class);
            intent.putExtra(LoginActivity.class.getCanonicalName(), 3);
            RegisterActivity.this.startActivity(intent);
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

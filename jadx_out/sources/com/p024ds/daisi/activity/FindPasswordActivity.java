package com.p024ds.daisi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.FindPasswordOneFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class FindPasswordActivity extends BaseActivity {
    public static final int END_BIT = 18;
    public static final int START_BIT = 6;
    private FragmentManager fragmentManager;
    private TitleView mTitle;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_find_password);
        initView();
        replaceFragment();
    }

    private void initView() {
        this.mTitle = (TitleView) findViewById(R.id.id_title);
        this.mTitle.setleftImage(R.drawable.ic_back);
        this.mTitle.setOnLeftImageViewListener(new OnLeftImageViewListener());
        this.mTitle.setVisibilityRightImage(8);
        this.mTitle.setRightTvText(getString(R.string.login_title));
        this.mTitle.setTitleText(getString(R.string.find_password));
        this.mTitle.setVisibilityTvRight(0);
        this.mTitle.setOnRightTextViewListener(new OnRightTextViewOnClicker());
    }

    private void replaceFragment() {
        this.fragmentManager = getSupportFragmentManager();
        this.fragmentManager.beginTransaction().replace(R.id.fl_find_password, FindPasswordOneFragment.newInstance()).commit();
    }

    private class OnLeftImageViewListener implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            if (FindPasswordActivity.this.fragmentManager.getBackStackEntryCount() > 0) {
                Logger.m3442e("fragment退出管理:" + FindPasswordActivity.this.fragmentManager.getBackStackEntryCount(), new Object[0]);
                FindPasswordActivity.this.fragmentManager.popBackStack();
                return;
            }
            FindPasswordActivity.this.finish();
        }
    }

    private class OnRightTextViewOnClicker implements TitleView.OnRightTextViewListener {
        private OnRightTextViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnRightTextViewListener
        public void onClicker(View view) {
            FindPasswordActivity.this.startActivity(new Intent(FindPasswordActivity.this, (Class<?>) LoginActivity.class));
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
        MobclickAgent.onResume(this);
    }
}

package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.view.View;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.UnBindRegisterCodeFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class UnbindRegistrationCodeActivity extends BaseActivity {
    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register_code);
        initView();
        replaceFragment(getIntent().getStringExtra(UnbindRegistrationCodeActivity.class.getCanonicalName()));
    }

    private void initView() {
        TitleView titleView = (TitleView) findViewById(R.id.id_title);
        titleView.setTitleText(getString(R.string.register_code_unbind));
        titleView.setVisibilityRightImage(4);
        titleView.setleftImage(R.drawable.ic_back);
        titleView.setOnLeftImageViewListener(new OnLeftImageViewOnClicker());
    }

    private void replaceFragment(String str) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fl_register_code, UnBindRegisterCodeFragment.newInstance(str)).commit();
    }

    private class OnLeftImageViewOnClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            UnbindRegistrationCodeActivity.this.finish();
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

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}

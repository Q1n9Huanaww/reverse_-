package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.view.View;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.RegisterCodeActivationFragment;
import com.p024ds.daisi.util.IntentUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class BindRegisterCodeActivity extends BaseActivity {
    private String mRegCode;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_register_code);
        initView();
        this.mRegCode = getIntent().getStringExtra(BindRegisterCodeActivity.class.getCanonicalName());
        replaceFragment(this.mRegCode);
    }

    private void initView() {
        TitleView titleView = (TitleView) findViewById(R.id.id_title);
        titleView.setTitleText(getString(R.string.activation_code));
        titleView.setVisibilityRightImage(4);
        titleView.setleftImage(R.drawable.ic_back);
        titleView.setRightTvText(R.string.register_code_unbind);
        titleView.setVisibilityTvRight(0);
        titleView.setOnRightTextViewListener(new TitleView.OnRightTextViewListener() { // from class: com.ds.daisi.activity.BindRegisterCodeActivity.1
            @Override // com.ds.daisi.customview.TitleView.OnRightTextViewListener
            public void onClicker(View view) {
                IntentUtils.toUnbindRegistrationCodeActivity(BindRegisterCodeActivity.this, BindRegisterCodeActivity.this.mRegCode);
            }
        });
        titleView.setOnLeftImageViewListener(new OnLeftImageViewOnClicker());
    }

    private void replaceFragment(String str) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fl_register_code, RegisterCodeActivationFragment.newInstance(str)).commit();
    }

    private class OnLeftImageViewOnClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            BindRegisterCodeActivity.this.finish();
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

package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.cyjh.feedback.lib.fragment.FeedBackInfoFragment;
import com.cyjh.mobileanjian.ipc.utils.RootUtil;
import com.p024ds.daisi.customview.TitleView;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class FeedbackActivity extends BaseActivity {
    TitleView mTitleView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_feedback);
        initTitle();
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.frameLayout_feedback, FeedBackInfoFragment.newInstance(getIntent().getStringExtra(FeedbackActivity.class.getCanonicalName()), RootUtil.isRoot()));
        fragmentTransactionBeginTransaction.commit();
    }

    private void initTitle() {
        this.mTitleView = (TitleView) findViewById(R.id.titleview_feedback);
        this.mTitleView.setTitleText(R.string.user_feedback_info);
        this.mTitleView.setVisibilityLeftImage(0);
        this.mTitleView.setleftImage(R.drawable.ic_back);
        this.mTitleView.setOnLeftImageViewListener(new TitleView.OnLeftImageViewListener() { // from class: com.ds.daisi.activity.FeedbackActivity.1
            @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
            public void onClick(View view) {
                FeedbackActivity.this.onBackPressed();
            }
        });
        this.mTitleView.setVisibilityRightImage(4);
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

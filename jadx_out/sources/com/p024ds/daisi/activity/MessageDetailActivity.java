package com.p024ds.daisi.activity;

import android.os.Bundle;
import android.view.View;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.fragment.MsgDetailFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class MessageDetailActivity extends BaseActivity {
    private TitleView titleView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_msg_detail);
        initTitleView();
        replaceFragment(MsgDetailFragment.newInstance(getIntent().getLongExtra(MessageDetailActivity.class.getCanonicalName(), 0L)));
    }

    private void replaceFragment(MsgDetailFragment msgDetailFragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.id_fl_fragment, msgDetailFragment).commit();
    }

    private void initTitleView() {
        this.titleView = (TitleView) findViewById(R.id.id_msg_detail_head);
        this.titleView.setleftImage(R.drawable.ic_back);
        this.titleView.setTitleText(R.string.msg_notify);
        this.titleView.setVisibilityRightImage(4);
        this.titleView.setOnLeftImageViewListener(new OnLeftImageOnClickerListener());
    }

    private class OnLeftImageOnClickerListener implements TitleView.OnLeftImageViewListener {
        private OnLeftImageOnClickerListener() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            MessageDetailActivity.this.finish();
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

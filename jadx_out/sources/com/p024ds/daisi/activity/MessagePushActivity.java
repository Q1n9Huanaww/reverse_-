package com.p024ds.daisi.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.cyjh.share.bean.NotifyMsgBean;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.customview.TitleView;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.fragment.MsgPushFragment;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class MessagePushActivity extends BaseActivity {
    private ArrayList<NotifyMsgBean> datas;
    private SharedPreferences mSharePres;
    private TitleView titleView;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_msg_push);
        this.datas = getIntent().getParcelableArrayListExtra(Constants.MSG_DATA);
        EventBus.getDefault().post(new MsgItem(1001));
        initTitleView();
        replaceFragment();
    }

    private void replaceFragment() {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.id_fl_fragment, MsgPushFragment.newInstance(this.datas));
        fragmentTransactionBeginTransaction.commit();
    }

    private void initTitleView() {
        this.titleView = (TitleView) findViewById(R.id.id_msg_push_head);
        this.titleView.setleftImage(R.drawable.ic_back);
        this.titleView.setOnLeftImageViewListener(new OnLeftImageOnClicker());
        this.titleView.setTitleText(R.string.msg_notify);
        this.titleView.setRightTvText(R.string.clear_msg);
        this.titleView.setVisibilityRightImage(8);
        this.titleView.setVisibilityTvRight(0);
        this.titleView.setOnRightTextViewListener(new OnRightTextViewOnClicker());
        this.mSharePres = getSharedPreferences(Constants.SMALL_RED_DOT, 0);
        this.mSharePres.edit().putBoolean(Constants.RED_DOT_FLAG, false).apply();
    }

    private class OnLeftImageOnClicker implements TitleView.OnLeftImageViewListener {
        private OnLeftImageOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnLeftImageViewListener
        public void onClick(View view) {
            MessagePushActivity.this.finish();
        }
    }

    private class OnRightTextViewOnClicker implements TitleView.OnRightTextViewListener {
        private OnRightTextViewOnClicker() {
        }

        @Override // com.ds.daisi.customview.TitleView.OnRightTextViewListener
        public void onClicker(View view) {
            EventBus.getDefault().post(new MsgItem(1002));
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

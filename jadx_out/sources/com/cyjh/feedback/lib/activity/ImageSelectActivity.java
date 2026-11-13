package com.cyjh.feedback.lib.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cyjh.feedback.lib.fragment.ImageSelectFragment;
import com.cyjh.feedback.libraryfeedbackinfo.C0738R;
import com.cyjh.share.manager.ActivitysManager;
import com.umeng.analytics.MobclickAgent;

/* JADX INFO: loaded from: classes.dex */
public class ImageSelectActivity extends FragmentActivity implements View.OnClickListener {
    private ImageView mImgLeftBack;
    private TextView mTvTitle;

    @Override // android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        setContentView(C0738R.layout.activity_img_select);
        ActivitysManager.getActivitysManager().addActivity(this);
        initView();
        this.mTvTitle.setText(getString(C0738R.string.img_select_title));
        this.mTvTitle.setVisibility(0);
        this.mImgLeftBack.setOnClickListener(this);
        replaceFragment(ImageSelectFragment.newInstance(), "ImageSelectFragment");
    }

    private void initView() {
        this.mImgLeftBack = (ImageView) findViewById(C0738R.id.id_img_left_back_arrow);
        this.mTvTitle = (TextView) findViewById(C0738R.id.id_tv_title);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == C0738R.id.id_img_left_back_arrow) {
            onBackPressed();
        }
    }

    public void replaceFragment(Fragment fragment, String str) {
        FragmentTransaction fragmentTransactionBeginTransaction = getSupportFragmentManager().beginTransaction();
        if (str == null) {
            fragmentTransactionBeginTransaction.replace(C0738R.id.id_fl_replace_fragment, fragment);
        } else {
            fragmentTransactionBeginTransaction.replace(C0738R.id.id_fl_replace_fragment, fragment, str);
        }
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override // android.support.v4.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.e("elfin", "ImageSelectActivity-- onDestroy");
        ActivitysManager.getActivitysManager().finishActivity(this);
    }
}

package com.p024ds.daisi.activity.guide;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.cyjh.share.bean.response.PhoneConfig;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.util.FileUtil;
import com.cyjh.share.util.ToastUtils;
import com.p024ds.daisi.activity.BaseActivity;
import com.p024ds.daisi.util.IntentUtils;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class RecognitionGuideActivity extends BaseActivity {
    public static final String PHONE_CONFIG = "PhoneConfig";
    private static final String TAG = "RecognitionGuideActivity";
    private ImageView mIvBg;
    private String mLinkUrl;

    @Override // com.p024ds.daisi.activity.BaseActivity, android.support.v4.app.FragmentActivity, android.support.v4.app.SupportActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_recognition_guide);
        initView();
        initData();
    }

    private void initView() {
        this.mIvBg = (ImageView) findViewById(R.id.iv_content_bg);
    }

    private void initData() {
        PhoneConfig phoneConfig = (PhoneConfig) getIntent().getParcelableExtra(PHONE_CONFIG);
        if (phoneConfig != null) {
            String imageUrl = phoneConfig.getImageUrl();
            this.mLinkUrl = phoneConfig.getLinkUrl();
            Bitmap localBitmap = FileUtil.getLocalBitmap(imageUrl);
            if (localBitmap != null) {
                this.mIvBg.setImageBitmap(localBitmap);
            }
            this.mIvBg.setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.activity.guide.RecognitionGuideActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    RecognitionGuideActivity.this.jumpOperate();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpOperate() {
        if (TextUtils.isEmpty(this.mLinkUrl)) {
            ToastUtils.showToastShort(this, "跳转链接为空！");
            return;
        }
        IntentUtils.toCallNoTitleBarWebViewActivity(this, VariableAndConstantsManager.getInstance().toRecognitionGuideWebViewPrams(this.mLinkUrl, VariableAndConstantsManager.getInstance().getBaseRequestParams(this)));
        finish();
    }

    public static void actionStart(Context context, PhoneConfig phoneConfig) {
        Intent intent = new Intent(context, (Class<?>) RecognitionGuideActivity.class);
        intent.putExtra(PHONE_CONFIG, phoneConfig);
        context.startActivity(intent);
    }
}

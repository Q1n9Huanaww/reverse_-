package com.p024ds.daisi.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.GsonExUtil;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.SweepCodeActivity;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.entity.RegCodeInfoResponse;
import com.p024ds.daisi.entity.RegCodeStatusInfo;
import com.p024ds.daisi.mvp.presenters.news.UnbindRegisterCodePresenter;
import com.p024ds.daisi.mvp.views.UnBindRegCodeView;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class UnBindRegisterCodeFragment extends BaseFragement implements View.OnClickListener, UnBindRegCodeView {
    public static final int REQUEST_CODE = 1002;
    private static final int STR_LENGTH_SUFFIX = 2;
    private String bindRegCode;
    private boolean isPreventRepeatedClick = false;
    private Button mBtnUnbindByButton;
    private Button mBtnUnbindBySweep;
    private EditText mEdRegisterCodeInput;
    private MyHandler mHandler;
    private TextView mTvBuckleTime;
    private UnbindRegisterCodePresenter mUnbindRegisterCodePresenter;
    private String unbindRegCode;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_regcode_unbind;
    }

    public static UnBindRegisterCodeFragment newInstance(String str) {
        UnBindRegisterCodeFragment unBindRegisterCodeFragment = new UnBindRegisterCodeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(UnBindRegisterCodeFragment.class.getCanonicalName(), str);
        unBindRegisterCodeFragment.setArguments(bundle);
        return unBindRegisterCodeFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            this.bindRegCode = getArguments().getString(UnBindRegisterCodeFragment.class.getCanonicalName());
        }
        initView(view);
        initAfterView();
    }

    private void initAfterView() {
        if (!TextUtils.isEmpty(this.bindRegCode)) {
            this.mEdRegisterCodeInput.setText(this.bindRegCode);
        }
        String strValueOf = String.valueOf(ParamsWrap.getParamsWrap().unbindDeductHours);
        String str = String.format(getString(R.string.unbind_app_buckle_time), strValueOf);
        SpannableString spannableString = new SpannableString(str);
        int iIndexOf = str.indexOf(strValueOf);
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF4948")), iIndexOf, strValueOf.length() + iIndexOf + 2, 17);
        this.mTvBuckleTime.setText(spannableString);
        this.mBtnUnbindByButton.setOnClickListener(this);
        this.mBtnUnbindBySweep.setOnClickListener(this);
    }

    private void initView(View view) {
        this.mEdRegisterCodeInput = (EditText) view.findViewById(R.id.register_number);
        this.mTvBuckleTime = (TextView) view.findViewById(R.id.tv_buckle_time);
        this.mBtnUnbindByButton = (Button) view.findViewById(R.id.btn_unbind);
        this.mBtnUnbindBySweep = (Button) view.findViewById(R.id.btn_sweep_unbind);
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(UnBindRegisterCodeFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(UnBindRegisterCodeFragment.class.getCanonicalName());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_unbind /* 2131755416 */:
                this.unbindRegCode = this.mEdRegisterCodeInput.getText().toString().trim();
                if (TextUtils.isEmpty(this.unbindRegCode)) {
                    ToastUtils.showToastLong(getActivity(), R.string.input_register_number);
                    break;
                } else if (!NetworkUtils.isAvailable(view.getContext())) {
                    ToastUtils.showToastLong(AppContext.getInstance(), AppContext.getInstance().getResources().getString(R.string.network_off));
                    break;
                } else if (!this.isPreventRepeatedClick) {
                    this.isPreventRepeatedClick = true;
                    unbindCodeRequest();
                    if (this.mHandler == null) {
                        this.mHandler = new MyHandler();
                    }
                    this.mHandler.removeCallbacksAndMessages(null);
                    this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.fragment.UnBindRegisterCodeFragment.1
                        @Override // java.lang.Runnable
                        public void run() {
                            UnBindRegisterCodeFragment.this.isPreventRepeatedClick = false;
                        }
                    }, 3000L);
                    break;
                }
                break;
            case R.id.btn_sweep_unbind /* 2131755417 */:
                startActivityForResult(IntentUtils.toSweepCodeActivity(getActivity()), 1002);
                break;
        }
    }

    private void unbindCodeRequest() {
        if (this.mUnbindRegisterCodePresenter == null) {
            this.mUnbindRegisterCodePresenter = new UnbindRegisterCodePresenter(this);
        }
        this.mUnbindRegisterCodePresenter.unbindRegCode(this.unbindRegCode, getActivity());
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
        if (this.mUnbindRegisterCodePresenter != null) {
            this.mUnbindRegisterCodePresenter.cancel();
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1002 && i2 == -1 && intent != null) {
            this.unbindRegCode = intent.getStringExtra(SweepCodeActivity.SWEEP_RESULT_BACK);
            unbindCodeRequest();
        }
    }

    @Override // com.p024ds.daisi.mvp.views.UnBindRegCodeView
    public void onUnbindSuc(RegCodeInfoResponse regCodeInfoResponse) {
        String string = regCodeInfoResponse.Message;
        if (regCodeInfoResponse.Code == 200) {
            string = getString(R.string.unbind_reg_code_suc);
        }
        if (TextUtils.isEmpty(string)) {
            string = getString(R.string.status_code_text) + regCodeInfoResponse.Code;
        }
        ToastUtils.showToastLong(getActivity(), string);
        if (regCodeInfoResponse.Code == 200 && !TextUtils.isEmpty(this.bindRegCode) && TextUtils.equals(this.bindRegCode, this.unbindRegCode)) {
            EventBus.getDefault().post(new MsgItem.BindRegCodeBus(-1L));
            RegCodeStatusInfo regCodeStatusInfo = new RegCodeStatusInfo();
            regCodeStatusInfo.status = 2;
            regCodeStatusInfo.regCode = this.unbindRegCode;
            String strClass2Data = GsonExUtil.class2Data(regCodeStatusInfo);
            AppUtils.writeFileContent(getActivity(), InterfaceRelatedConstants.REG_CODE_STATUS_FILE_SAVE + MyConfig.getAppid(), strClass2Data);
        }
    }

    @Override // com.p024ds.daisi.mvp.views.UnBindRegCodeView
    public void onUnbindFailure(String str) {
        ToastUtils.showToastLong(getActivity(), str);
    }

    private static class MyHandler extends Handler {
        private MyHandler() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }
}

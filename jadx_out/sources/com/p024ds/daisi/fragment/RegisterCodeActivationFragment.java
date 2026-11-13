package com.p024ds.daisi.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.feedback.lib.executor.ThreadPoolProxyFactory;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.share.bean.response.BaseResponseInfo;
import com.cyjh.share.bean.response.BuyRegCodeConfigInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.cyjh.share.util.ToastUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.SweepCodeActivity;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.dialog.TipsDialog;
import com.p024ds.daisi.entity.BindRegCodeResponse;
import com.p024ds.daisi.entity.MsgItem;
import com.p024ds.daisi.entity.ParamsWrap;
import com.p024ds.daisi.entity.RegCodeInfoResponse;
import com.p024ds.daisi.entity.RegCodeStatusInfo;
import com.p024ds.daisi.manager.DialogManager;
import com.p024ds.daisi.mvp.presenters.news.BindRegisterCodePresenter;
import com.p024ds.daisi.mvp.presenters.news.QueryRegCodeInfoPresenter;
import com.p024ds.daisi.mvp.views.BindRegCodeView;
import com.p024ds.daisi.mvp.views.QueryRegCodeView;
import com.p024ds.daisi.util.IntentUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes.dex */
public class RegisterCodeActivationFragment extends BaseFragement implements View.OnClickListener, BindRegCodeView, QueryRegCodeView {
    public static final int ACTIVATION_REGISTRATION_CODE = 273;
    public static final int REQUEST_CODE = 1003;
    private static final String TAG = "RegisterCodeActivationFragment";
    private boolean isPreventRepeatedClick = false;
    private BindRegisterCodePresenter mBindRegisterCodePresenter;
    private EditText mEdRegisterCodeInput;
    private MyHandler mHandler;
    private LinearLayout mLLRegCodeBuy;
    private View mLineDay;
    private View mLineMonth;
    private View mLineWeek;
    private QueryRegCodeInfoPresenter mQueryRegCodeInfoPresenter;
    private TextView mTvDay;
    private TextView mTvMonth;
    private TextView mTvPermanentCard;
    private TextView mTvTipsNoRegCode;
    private TextView mTvWeek;
    private String oldRegCode;
    private String regCode;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_regcode_activation;
    }

    public static RegisterCodeActivationFragment newInstance(String str) {
        RegisterCodeActivationFragment registerCodeActivationFragment = new RegisterCodeActivationFragment();
        Bundle bundle = new Bundle();
        bundle.putString(RegisterCodeActivationFragment.class.getCanonicalName(), str);
        registerCodeActivationFragment.setArguments(bundle);
        return registerCodeActivationFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (getArguments() != null) {
            this.oldRegCode = getArguments().getString(RegisterCodeActivationFragment.class.getCanonicalName());
        }
        initView(view);
        initAfterView();
    }

    private void initAfterView() {
        if (ParamsWrap.getParamsWrap().mBuyRegCodeConfigInfos != null && ParamsWrap.getParamsWrap().mBuyRegCodeConfigInfos.size() > 0) {
            TextView[] textViewArr = {this.mTvDay, this.mTvWeek, this.mTvMonth, this.mTvPermanentCard};
            View[] viewArr = {this.mLineDay, this.mLineWeek, this.mLineMonth};
            this.mLLRegCodeBuy.setVisibility(0);
            this.mTvTipsNoRegCode.setVisibility(8);
            for (int i = 0; i < ParamsWrap.getParamsWrap().mBuyRegCodeConfigInfos.size(); i++) {
                final BuyRegCodeConfigInfo buyRegCodeConfigInfo = ParamsWrap.getParamsWrap().mBuyRegCodeConfigInfos.get(i);
                TextView textView = textViewArr[i];
                textView.setVisibility(0);
                textView.setText(buyRegCodeConfigInfo.ButtonName);
                if (viewArr.length < ParamsWrap.getParamsWrap().mBuyRegCodeConfigInfos.size() - 1) {
                    viewArr[i].setVisibility(0);
                }
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.fragment.RegisterCodeActivationFragment.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        try {
                            Intent intent = new Intent();
                            intent.setAction("android.intent.action.VIEW");
                            intent.setData(Uri.parse(buyRegCodeConfigInfo.BuyLinkUrl));
                            if (!RegisterCodeActivationFragment.this.hasPreferredApplication(RegisterCodeActivationFragment.this.getActivity(), intent)) {
                                intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
                            }
                            RegisterCodeActivationFragment.this.startActivity(intent);
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                            ToastUtils.showToastLong(RegisterCodeActivationFragment.this.getActivity(), RegisterCodeActivationFragment.this.getString(R.string.invalid_url_address));
                        }
                    }
                });
            }
        } else {
            this.mTvTipsNoRegCode.setVisibility(0);
        }
        this.mHandler = new MyHandler();
    }

    private void initView(View view) {
        this.mEdRegisterCodeInput = (EditText) view.findViewById(R.id.register_number);
        this.mTvTipsNoRegCode = (TextView) view.findViewById(R.id.tv_empty_msg);
        this.mLLRegCodeBuy = (LinearLayout) view.findViewById(R.id.ll_registration_buy);
        this.mTvDay = (TextView) view.findViewById(R.id.tv_day);
        this.mTvWeek = (TextView) view.findViewById(R.id.tv_week);
        this.mTvMonth = (TextView) view.findViewById(R.id.tv_month);
        this.mLineDay = view.findViewById(R.id.line_day);
        this.mLineWeek = view.findViewById(R.id.line_week);
        this.mLineMonth = view.findViewById(R.id.line_month);
        this.mTvPermanentCard = (TextView) view.findViewById(R.id.tv_permanent_card);
        ((Button) view.findViewById(R.id.btn_activation)).setOnClickListener(this);
        if (!TextUtils.isEmpty(this.oldRegCode)) {
            this.mEdRegisterCodeInput.setText(this.oldRegCode);
        }
        view.findViewById(R.id.btn_sweep_activation).setOnClickListener(this);
    }

    @Override // com.p024ds.daisi.fragment.BaseFragement, android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(RegisterCodeActivationFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(RegisterCodeActivationFragment.class.getCanonicalName());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_activation /* 2131755405 */:
                this.regCode = this.mEdRegisterCodeInput.getText().toString().trim();
                if (TextUtils.isEmpty(this.regCode)) {
                    com.p024ds.daisi.util.ToastUtils.showToastLong(getActivity(), R.string.input_register_number);
                    break;
                } else if (!NetworkUtils.isAvailable(getActivity())) {
                    com.p024ds.daisi.util.ToastUtils.showToastLong(AppContext.getInstance(), AppContext.getInstance().getString(R.string.network_off));
                    break;
                } else if (!this.isPreventRepeatedClick) {
                    this.isPreventRepeatedClick = true;
                    queryRegCodeInfo();
                    this.mHandler.removeCallbacksAndMessages(null);
                    this.mHandler.postDelayed(new Runnable() { // from class: com.ds.daisi.fragment.RegisterCodeActivationFragment.2
                        @Override // java.lang.Runnable
                        public void run() {
                            RegisterCodeActivationFragment.this.isPreventRepeatedClick = false;
                        }
                    }, 3000L);
                    break;
                }
                break;
            case R.id.btn_sweep_activation /* 2131755406 */:
                startActivityForResult(IntentUtils.toSweepCodeActivity(getActivity()), 1003);
                break;
        }
    }

    private void queryRegCodeInfo() {
        if (this.mQueryRegCodeInfoPresenter == null) {
            this.mQueryRegCodeInfoPresenter = new QueryRegCodeInfoPresenter(this);
        }
        this.mQueryRegCodeInfoPresenter.queryRegCodeInfo(this.regCode, this.oldRegCode, getActivity());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindRegCodeToApp(int i) {
        if (this.mBindRegisterCodePresenter == null) {
            this.mBindRegisterCodePresenter = new BindRegisterCodePresenter(this);
        }
        this.mBindRegisterCodePresenter.bindRegCode(this.regCode, getActivity(), i, this.oldRegCode);
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (this.mBindRegisterCodePresenter != null) {
            this.mBindRegisterCodePresenter.onCancel();
        }
        if (this.mQueryRegCodeInfoPresenter != null) {
            this.mQueryRegCodeInfoPresenter.onCancel();
        }
        if (this.mHandler != null) {
            this.mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override // com.p024ds.daisi.mvp.views.BindRegCodeView
    public void onBindSuc(BindRegCodeResponse bindRegCodeResponse) {
        if (200 == bindRegCodeResponse.Code) {
            this.mEdRegisterCodeInput.setText(this.regCode);
            Log.e("zzz", "RegisterCodeActivationFragment:onBindSuccg成功");
            BindRegCodeResponse.RegCodeTimeInfo regCodeTimeInfo = bindRegCodeResponse.Data;
            long j = regCodeTimeInfo.ExpireTime;
            long j2 = regCodeTimeInfo.ServerTimestamp;
            long j3 = (j - j2) / 60;
            AppContext.getInstance().ExpireTime = j;
            SlLog.m1334i(TAG, "onBindSuc --> expireTime=" + j + ",serverTimestamp=" + j2 + ",dueTime=" + j3);
            ToastUtils.showToastLong(getActivity(), getString(R.string.activation_code_suc));
            ThreadPoolProxyFactory.getNormalThreadPool().executeRunnable(new Runnable() { // from class: com.ds.daisi.fragment.RegisterCodeActivationFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    RegCodeStatusInfo regCodeStatusInfo = new RegCodeStatusInfo();
                    regCodeStatusInfo.status = 1;
                    regCodeStatusInfo.regCode = RegisterCodeActivationFragment.this.regCode;
                    String strClass2Data = GsonExUtil.class2Data(regCodeStatusInfo);
                    AppUtils.writeFileContent(RegisterCodeActivationFragment.this.getActivity(), InterfaceRelatedConstants.REG_CODE_STATUS_FILE_SAVE + MyConfig.getAppid(), strClass2Data);
                }
            });
            AppContext.getInstance().regCode = this.regCode;
            this.oldRegCode = AppContext.getInstance().regCode;
            EventBus.getDefault().post(new MsgItem.BindRegCodeBus(j3));
        } else {
            onBindFailure(new BaseResponseInfo(bindRegCodeResponse.Code, bindRegCodeResponse.Message));
            if (bindRegCodeResponse.Code == 1401 && TextUtils.equals(this.regCode, this.oldRegCode)) {
                Log.e("zzz", "RegisterCodeActivationFragment:onBindSuc注册码过期，清空注册码");
                AppContext.getInstance().regCode = "";
                MqRunner.getInstance().setRegCode(AppContext.getInstance().regCode);
                AppUtils.deleteFileContent(InterfaceRelatedConstants.REG_CODE_STATUS_FILE_SAVE + MyConfig.getAppid(), AppContext.getInstance());
            }
        }
        this.isPreventRepeatedClick = false;
    }

    @Override // com.p024ds.daisi.mvp.views.BindRegCodeView
    public void onBindFailure(BaseResponseInfo baseResponseInfo) {
        AppContext.getInstance().ExpireTime = 0L;
        int i = baseResponseInfo.Code;
        if (1100 == i || 1200 == i || 1201 == i || 1202 == i || 1300 == i) {
            if (!TipsDialog.sIsShow) {
                final TipsDialog tipsDialog = new TipsDialog(getContext(), false, baseResponseInfo.Message);
                tipsDialog.setClickListener(new View.OnClickListener() { // from class: com.ds.daisi.fragment.RegisterCodeActivationFragment.4
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        tipsDialog.dismiss();
                    }
                });
                tipsDialog.show();
            } else {
                EventBus.getDefault().post(new MsgItem.UpdateMessageToDialog(baseResponseInfo.Message));
            }
        } else {
            ToastUtils.showToastLong(getActivity(), baseResponseInfo.Message);
        }
        this.isPreventRepeatedClick = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean hasPreferredApplication(Context context, Intent intent) {
        return !Constants.ANDROID.equals(context.getPackageManager().resolveActivity(intent, 65536).activityInfo.packageName);
    }

    @Override // com.p024ds.daisi.mvp.views.QueryRegCodeView
    public void onQuerySuc(RegCodeInfoResponse regCodeInfoResponse) {
        if (regCodeInfoResponse.Code == 200) {
            if (regCodeInfoResponse.Data.IsSuperposeConfirm == 1) {
                final AlertDialog alertDialogShowMessageDialog = DialogManager.getInstance().showMessageDialog(getActivity(), R.layout.dialog_overlay_regcode, R.style.Theme_Dialog);
                alertDialogShowMessageDialog.setCancelable(false);
                alertDialogShowMessageDialog.show();
                TextView textView = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_no_superimposed);
                TextView textView2 = (TextView) alertDialogShowMessageDialog.findViewById(R.id.id_tv_superimposed);
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.fragment.RegisterCodeActivationFragment.5
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        alertDialogShowMessageDialog.dismiss();
                        RegisterCodeActivationFragment.this.mHandler.obtainMessage(273, 0).sendToTarget();
                    }
                });
                textView2.setOnClickListener(new View.OnClickListener() { // from class: com.ds.daisi.fragment.RegisterCodeActivationFragment.6
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        alertDialogShowMessageDialog.dismiss();
                        RegisterCodeActivationFragment.this.mHandler.obtainMessage(273, 1).sendToTarget();
                    }
                });
                return;
            }
            this.mHandler.obtainMessage(273, 0).sendToTarget();
            return;
        }
        String strValueOf = regCodeInfoResponse.Message;
        if (TextUtils.isEmpty(strValueOf)) {
            strValueOf = String.valueOf(regCodeInfoResponse.Code);
        }
        com.p024ds.daisi.util.ToastUtils.showToastLong(getActivity(), strValueOf);
    }

    @Override // com.p024ds.daisi.mvp.views.QueryRegCodeView
    public void onQueryFailure(String str) {
        com.p024ds.daisi.util.ToastUtils.showToastLong(getActivity(), str);
    }

    private static class MyHandler extends Handler {
        private WeakReference<RegisterCodeActivationFragment> mWeakReference;

        private MyHandler(RegisterCodeActivationFragment registerCodeActivationFragment) {
            this.mWeakReference = new WeakReference<>(registerCodeActivationFragment);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            RegisterCodeActivationFragment registerCodeActivationFragment = this.mWeakReference.get();
            if (registerCodeActivationFragment != null && message.what == 273) {
                registerCodeActivationFragment.bindRegCodeToApp(((Integer) message.obj).intValue());
            }
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1003 && i2 == -1 && intent != null) {
            this.regCode = intent.getStringExtra(SweepCodeActivity.SWEEP_RESULT_BACK);
            if (!TextUtils.isEmpty(this.regCode)) {
                queryRegCodeInfo();
            } else {
                com.p024ds.daisi.util.ToastUtils.showToastLong(getActivity(), getString(R.string.regcode_sweep_result_null));
            }
        }
    }
}

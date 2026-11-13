package com.p024ds.daisi.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.entity.AccountUser;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.mvp.presenters.AccountPresenter;
import com.p024ds.daisi.mvp.viewimpls.AccountViewImpl;
import com.p024ds.daisi.timer.CountTimer;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public class FindPasswordTwoFragment extends AccountViewImpl implements View.OnClickListener {
    private static final String AREA_CODE_PHONE = "+86";
    private static final int INTERVAL_TIME = 1000;
    private static final String STAR_STAR = "****";
    private static final int TOTAL_TIME = 60000;
    private AccountPresenter accountPresenter;
    private AccountResult accountResult;
    private AccountUser accountUser;
    private String bindPhone;
    private boolean isClickerVerifyCode = false;
    private Button mBtnNextStep;
    private Button mBtnSendCode;
    private EditText mEtVerityCode;
    private TextView mTvGetPhone;
    private SubmitTimeCounter submitTimeCounter;
    private String userAccount;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_find_password_two;
    }

    public static FindPasswordTwoFragment newInstance(String str, String str2) {
        FindPasswordTwoFragment findPasswordTwoFragment = new FindPasswordTwoFragment();
        Bundle bundle = new Bundle();
        bundle.putString(FindPasswordTwoFragment.class.getCanonicalName(), str);
        bundle.putString(FindPasswordTwoFragment.class.getSimpleName(), str2);
        findPasswordTwoFragment.setArguments(bundle);
        return findPasswordTwoFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.bindPhone = getArguments().getString(FindPasswordTwoFragment.class.getCanonicalName(), "");
            this.userAccount = getArguments().getString(FindPasswordTwoFragment.class.getSimpleName(), "");
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    private void initView(View view) {
        this.mBtnNextStep = (Button) view.findViewById(R.id.btn_step_two);
        this.mBtnSendCode = (Button) view.findViewById(R.id.id_send_codes);
        this.mTvGetPhone = (TextView) view.findViewById(R.id.id_phone_message);
        this.mEtVerityCode = (EditText) view.findViewById(R.id.id_input_codes);
        String str = this.bindPhone.substring(0, 3) + STAR_STAR + this.bindPhone.substring(7, 11);
        Logger.m3442e("带星号的手机号：" + str, new Object[0]);
        this.mTvGetPhone.setText(getTextPartColor(getString(R.string.verify_message, str), AREA_CODE_PHONE, str));
    }

    private void initData() {
        this.accountUser = new AccountUser();
        this.accountPresenter = new AccountPresenter(this);
        this.submitTimeCounter = new SubmitTimeCounter(60000L, 1000L);
    }

    private void initEvent() {
        this.mBtnSendCode.setOnClickListener(this);
        this.mBtnNextStep.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_send_codes /* 2131755389 */:
                if (!this.isClickerVerifyCode) {
                    this.accountUser.phone = this.userAccount;
                    this.accountUser.bindphone = this.bindPhone;
                    this.accountUser.type = AccountManager.TYPE_FIND_PASSWORD_CODE;
                    this.isClickerVerifyCode = true;
                    this.submitTimeCounter.start();
                    Logger.m3442e("accounter:" + this.accountUser.toString(), new Object[0]);
                    this.accountPresenter.phoneCodes(getActivity(), this.accountUser);
                }
                break;
            case R.id.btn_step_two /* 2131755390 */:
                String strTrim = this.mEtVerityCode.getText().toString().trim();
                if (TextUtils.isEmpty(strTrim)) {
                    ToastUtils.showToastLong(getActivity(), R.string.not_input_codes);
                } else if (this.accountResult != null && !TextUtils.isEmpty(this.accountResult.VerificationCode)) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    long time = 0;
                    Date date = new Date(System.currentTimeMillis());
                    Logger.m3442e("当前时间：" + date, new Object[0]);
                    long time2 = date.getTime() / 1000;
                    try {
                        time = simpleDateFormat.parse(this.accountResult.ExpireDate).getTime() / 1000;
                    } catch (ParseException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                    if (!this.accountResult.VerificationCode.equals(strTrim)) {
                        ToastUtils.showToastLong(getActivity(), R.string.error_codes);
                    } else if (time > time2) {
                        Logger.m3442e("accounter:" + this.accountUser.toString(), new Object[0]);
                        this.submitTimeCounter.cancel();
                        this.mBtnSendCode.setText(getString(R.string.send_codes));
                        this.mBtnSendCode.setBackgroundResource(R.drawable.bg_blue_box);
                        replaceFragment(this.accountUser);
                    } else {
                        ToastUtils.showToastLong(getActivity(), R.string.pin_timeout);
                    }
                } else {
                    ToastUtils.showToastLong(getActivity(), R.string.clicker_send_codes);
                }
                break;
        }
    }

    public SpannableStringBuilder getTextPartColor(String str, String str2, String str3) {
        int iIndexOf = str.indexOf(str2);
        int length = str2.length() + iIndexOf;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#3F7FC6")), iIndexOf, length, 34);
        if (TextUtils.isEmpty(str3)) {
            return spannableStringBuilder;
        }
        int iIndexOf2 = str.indexOf(str3);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.parseColor("#3F7FC6")), iIndexOf2, str3.length() + iIndexOf2, 34);
        return spannableStringBuilder;
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void codesResult(AccountResult accountResult) {
        if (accountResult.Code.equals(String.valueOf(1))) {
            this.accountResult = accountResult;
        } else {
            ToastUtils.showToastLong(getActivity(), R.string.get_codes_fail);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onDestroy() {
        this.submitTimeCounter.cancel();
        super.onDestroy();
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(FindPasswordTwoFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(FindPasswordTwoFragment.class.getCanonicalName());
    }

    private void replaceFragment(AccountUser accountUser) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.hide(this);
        fragmentTransactionBeginTransaction.add(R.id.fl_find_password, FindPasswordThreeFragment.newInstance(accountUser));
        fragmentTransactionBeginTransaction.addToBackStack(RegisterOneFragment.class.getCanonicalName());
        fragmentTransactionBeginTransaction.commit();
    }

    class SubmitTimeCounter extends CountTimer {
        public SubmitTimeCounter(long j, long j2) {
            super(j, j2);
        }

        @Override // com.p024ds.daisi.timer.CountTimer
        public void onTick(long j) {
            if (FindPasswordTwoFragment.this.getActivity() != null) {
                FindPasswordTwoFragment.this.mBtnSendCode.setBackgroundResource(R.drawable.bg_press_gray);
                FindPasswordTwoFragment.this.mBtnSendCode.setText((j / 1000) + "秒重新获取");
            }
        }

        @Override // com.p024ds.daisi.timer.CountTimer
        public void onFinish() {
            FindPasswordTwoFragment.this.mBtnSendCode.setBackgroundResource(R.drawable.bg_blue_box);
            if (FindPasswordTwoFragment.this.isAdded()) {
                FindPasswordTwoFragment.this.mBtnSendCode.setText(FindPasswordTwoFragment.this.getString(R.string.send_codes));
            }
            FindPasswordTwoFragment.this.isClickerVerifyCode = false;
        }
    }
}

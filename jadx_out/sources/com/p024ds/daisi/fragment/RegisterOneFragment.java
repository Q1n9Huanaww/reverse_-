package com.p024ds.daisi.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.activity.TermsOfServiceActivity;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.entity.AccountUser;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.mvp.presenters.AccountPresenter;
import com.p024ds.daisi.mvp.viewimpls.AccountViewImpl;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.MD5Util;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class RegisterOneFragment extends AccountViewImpl implements View.OnClickListener {
    private static final String DUPLICATED_ACCOUNT = "1";
    private static final String licenseText = "点击下一步，同时同意<font color= '#3E81C7'><b>《服务协议》</b></font>";
    private AccountPresenter accountPresenter;
    private AccountUser accountUser;
    private Button mBtnNextStep;
    private EditText mEdUserAccount;
    private EditText mEdUserPassword;
    private TextView mTvLicense;
    private String md5AfterPassword;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_register_one;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    public static RegisterOneFragment newInstance() {
        return new RegisterOneFragment();
    }

    private void initView(View view) {
        this.mEdUserAccount = (EditText) view.findViewById(R.id.id_user_account);
        this.mEdUserPassword = (EditText) view.findViewById(R.id.id_user_password);
        this.mBtnNextStep = (Button) view.findViewById(R.id.id_step_next);
        this.mTvLicense = (TextView) view.findViewById(R.id.id_tv_license);
        this.mTvLicense.setText(Html.fromHtml(licenseText));
    }

    private void initData() {
        this.accountUser = new AccountUser();
        this.accountPresenter = new AccountPresenter(this);
    }

    private void initEvent() {
        this.mTvLicense.setOnClickListener(this);
        this.mBtnNextStep.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_step_next /* 2131755422 */:
                String string = this.mEdUserAccount.getText().toString();
                String string2 = this.mEdUserPassword.getText().toString();
                if (TextUtils.isEmpty(string) || string.length() < 6 || string.length() > 18 || string.contains(" ") || !AppDeviceUtils.isValidAccountOrPassword(string)) {
                    ToastUtils.showToastLong(getActivity(), R.string.again_input_account);
                } else if (TextUtils.isEmpty(string2) || string2.length() < 6 || string2.length() > 18 || string2.contains(" ") || !AppDeviceUtils.isValidAccountOrPassword(string2)) {
                    ToastUtils.showToastLong(getActivity(), R.string.again_input_password);
                } else {
                    Logger.m3442e("加密前的密码：" + string2, new Object[0]);
                    this.accountUser.phone = string;
                    this.accountUser.type = AccountManager.TYPE_CHECKED_ACCOUNT_DUPLICATED;
                    this.md5AfterPassword = MD5Util.MD5(string2);
                    Logger.m3442e("注册界面：" + this.accountUser.toString(), new Object[0]);
                    this.accountPresenter.checkedAccountDuplicated(this.accountUser, getActivity());
                }
                break;
            case R.id.id_tv_license /* 2131755423 */:
                startActivity(new Intent(getActivity(), (Class<?>) TermsOfServiceActivity.class));
                break;
        }
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void checkedAccountDuplicatedResult(AccountResult accountResult) {
        super.checkedAccountDuplicatedResult(accountResult);
        if (TextUtils.isEmpty(accountResult.Code)) {
            return;
        }
        if (accountResult.Code.equals(String.valueOf("1"))) {
            ToastUtils.showToastLong(getActivity(), accountResult.Msg);
            return;
        }
        this.accountUser.password = this.md5AfterPassword;
        replaceFragment(this.accountUser);
    }

    private void replaceFragment(AccountUser accountUser) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.id_fl_register, RegisterTwoFragment.newInstance(accountUser));
        fragmentTransactionBeginTransaction.addToBackStack(RegisterOneFragment.class.getCanonicalName());
        fragmentTransactionBeginTransaction.commit();
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(RegisterOneFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(RegisterOneFragment.class.getCanonicalName());
    }
}

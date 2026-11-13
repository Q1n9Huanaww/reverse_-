package com.p024ds.daisi.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.entity.AccountUser;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.mvp.presenters.AccountPresenter;
import com.p024ds.daisi.mvp.viewimpls.AccountViewImpl;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class FindPasswordOneFragment extends AccountViewImpl implements View.OnClickListener {
    private AccountPresenter accountPresenter;
    private AccountUser accountUser;
    private Button mBtnNextStep;
    private EditText mEtUserAccount;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_find_password_one;
    }

    public static FindPasswordOneFragment newInstance() {
        return new FindPasswordOneFragment();
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    private void initView(View view) {
        this.mEtUserAccount = (EditText) view.findViewById(R.id.et_user_account);
        this.mBtnNextStep = (Button) view.findViewById(R.id.btn_step_one);
    }

    private void initData() {
        this.accountUser = new AccountUser();
        this.accountPresenter = new AccountPresenter(this);
    }

    private void initEvent() {
        this.mBtnNextStep.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_step_one) {
            return;
        }
        String strTrim = this.mEtUserAccount.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim) || !AppDeviceUtils.isValidAccountOrPassword(strTrim) || strTrim.contains(" ") || strTrim.length() < 6 || strTrim.length() > 18) {
            ToastUtils.showToastLong(getActivity(), R.string.again_input_account);
            return;
        }
        this.accountUser.phone = strTrim;
        this.accountUser.type = AccountManager.TYPE_GET_PHONE;
        this.accountPresenter.getPhoneByAccount(this.accountUser, getActivity());
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(FindPasswordOneFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(FindPasswordOneFragment.class.getCanonicalName());
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void getBindPhoneResult(AccountResult accountResult) {
        super.getBindPhoneResult(accountResult);
        if (accountResult.Code.equals(String.valueOf(1))) {
            this.accountUser.bindphone = accountResult.Msg;
            replaceFragment(this.accountUser);
            return;
        }
        ToastUtils.showToastLong(getActivity(), R.string.again_account_error);
    }

    private void replaceFragment(AccountUser accountUser) {
        FragmentTransaction fragmentTransactionBeginTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.replace(R.id.fl_find_password, FindPasswordTwoFragment.newInstance(accountUser.bindphone, accountUser.phone));
        fragmentTransactionBeginTransaction.addToBackStack(RegisterOneFragment.class.getCanonicalName());
        fragmentTransactionBeginTransaction.commit();
    }
}

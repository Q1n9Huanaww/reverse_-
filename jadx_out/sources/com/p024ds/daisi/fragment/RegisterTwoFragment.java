package com.p024ds.daisi.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.activity.RegisterActivity;
import com.p024ds.daisi.constant.Constants;
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
public class RegisterTwoFragment extends AccountViewImpl implements View.OnClickListener {
    public static final int PHONE_NUMBER_BITS = 11;
    private AccountPresenter accountPresenter;
    private AccountUser accountUser;
    private Button mBtnComplete;
    private EditText mEtBindPhone;
    private SharedPreferences mSharePre;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_register_two;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.accountUser = (AccountUser) getArguments().getParcelable(RegisterTwoFragment.class.getCanonicalName());
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
        this.mEtBindPhone = (EditText) view.findViewById(R.id.id_bind_phone_number);
        this.mBtnComplete = (Button) view.findViewById(R.id.id_btn_complete);
    }

    private void initData() {
        this.mSharePre = getActivity().getSharedPreferences(Constants.USER_MESSAGE, 0);
        this.accountPresenter = new AccountPresenter(this);
    }

    private void initEvent() {
        this.mBtnComplete.setOnClickListener(this);
    }

    public static RegisterTwoFragment newInstance(AccountUser accountUser) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(RegisterTwoFragment.class.getCanonicalName(), accountUser);
        RegisterTwoFragment registerTwoFragment = new RegisterTwoFragment();
        registerTwoFragment.setArguments(bundle);
        return registerTwoFragment;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.id_btn_complete) {
            return;
        }
        String strTrim = this.mEtBindPhone.getText().toString().trim();
        if (TextUtils.isEmpty(strTrim) || strTrim.length() != 11 || !AppDeviceUtils.isValidMobile(strTrim)) {
            ToastUtils.showToastLong(getActivity(), R.string.phone_wrong_format);
            return;
        }
        this.accountUser.bindphone = strTrim;
        this.accountUser.type = "1";
        this.accountUser.codetype = "1";
        Logger.m3442e("注册用户的信息：" + this.accountUser.toString(), new Object[0]);
        this.accountPresenter.register(getActivity(), this.accountUser);
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void loginResult(AccountResult accountResult) {
        if (accountResult.Code.equals(String.valueOf(1))) {
            Logger.m3442e("注册成功后的立即登录:" + accountResult.toString(), new Object[0]);
            SharedPreferences.Editor editorEdit = this.mSharePre.edit();
            editorEdit.putString(Constants.ACCOUNT, this.accountUser.phone);
            editorEdit.putBoolean(Constants.IS_LOGIN, true);
            editorEdit.apply();
            if (((RegisterActivity) getActivity()).registerJumpType.equals("login")) {
                startActivity(new Intent(getActivity(), (Class<?>) ElfinPayActivity.class));
            }
            getActivity().finish();
        }
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void registerResult(AccountResult accountResult) {
        if (accountResult.Code.equals(String.valueOf(1))) {
            ToastUtils.showToastShort(getActivity(), accountResult.Msg);
            this.accountUser.type = AccountManager.TYPE_LOGIN;
            this.accountUser.packagename = getActivity().getPackageName();
            this.accountUser.usertype = "1";
            Logger.m3442e("accountUser" + this.accountUser.toString(), new Object[0]);
            this.accountPresenter.login(getActivity(), this.accountUser);
            return;
        }
        if (accountResult.Code.equals(String.valueOf(-1))) {
            ToastUtils.showToastShort(getActivity(), accountResult.Msg);
        } else {
            ToastUtils.showToastShort(getActivity(), R.string.fail_register);
        }
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void alterAndFindPasswordResult(AccountResult accountResult) {
        super.alterAndFindPasswordResult(accountResult);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(RegisterTwoFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(RegisterTwoFragment.class.getCanonicalName());
    }
}

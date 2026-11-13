package com.p024ds.daisi.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.constant.Constants;
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
public class AlterPasswordFragment extends AccountViewImpl implements View.OnClickListener {
    private AccountPresenter accountPresenter;
    private AccountUser accountUser;
    private Button mBtnComplete;
    private EditText mEtNewPassword;
    private EditText mEtOldPassword;
    private SharedPreferences mSharePre;
    private String userAccount;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_alter_password;
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(AlterPasswordFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(AlterPasswordFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.userAccount = getArguments().getString(Constants.ACCOUNT);
        }
    }

    public static AlterPasswordFragment newInstance(String str) {
        AlterPasswordFragment alterPasswordFragment = new AlterPasswordFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.ACCOUNT, str);
        alterPasswordFragment.setArguments(bundle);
        return alterPasswordFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    private void initData() {
        this.mSharePre = getActivity().getSharedPreferences(Constants.USER_MESSAGE, 0);
        this.accountUser = new AccountUser();
        this.accountPresenter = new AccountPresenter(this);
    }

    private void initView(View view) {
        this.mBtnComplete = (Button) view.findViewById(R.id.btn_complete_operate);
        this.mEtNewPassword = (EditText) view.findViewById(R.id.et_new_password);
        this.mEtOldPassword = (EditText) view.findViewById(R.id.et_old_password);
    }

    private void initEvent() {
        this.mBtnComplete.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_complete_operate) {
            return;
        }
        String string = this.mEtOldPassword.getText().toString();
        String string2 = this.mEtNewPassword.getText().toString();
        if (TextUtils.isEmpty(string) || !AppDeviceUtils.isValidAccountOrPassword(string) || string.contains(" ") || string.length() < 6 || string.length() > 18) {
            ToastUtils.showToastLong(getActivity(), R.string.again_input_old_password);
            return;
        }
        if (TextUtils.isEmpty(string2) || !AppDeviceUtils.isValidAccountOrPassword(string2) || string2.contains(" ") || string2.length() < 6 || string2.length() > 18) {
            ToastUtils.showToastLong(getActivity(), R.string.again_input_new_password);
            return;
        }
        this.accountUser.phone = this.userAccount;
        this.accountUser.password = MD5Util.MD5(string);
        this.accountUser.newpassword = MD5Util.MD5(string2);
        this.accountUser.type = AccountManager.TYPE_ALTER_PASSWORD;
        Logger.m3442e("修改密码：" + this.accountUser.toString(), new Object[0]);
        this.accountPresenter.alterPassword(getActivity(), this.accountUser);
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void alterAndFindPasswordResult(AccountResult accountResult) {
        super.alterAndFindPasswordResult(accountResult);
        Logger.m3442e(accountResult.toString(), new Object[0]);
        if (accountResult.Code.equals(String.valueOf(1))) {
            ToastUtils.showToastShort(getActivity(), accountResult.Msg);
            this.accountUser.type = AccountManager.TYPE_LOGIN;
            this.accountUser.packagename = getActivity().getPackageName();
            this.accountUser.usertype = "1";
            this.accountUser.password = this.accountUser.newpassword;
            this.accountUser.newpassword = null;
            Logger.m3442e("accountUser" + this.accountUser.toString(), new Object[0]);
            this.accountPresenter.login(getActivity(), this.accountUser);
            return;
        }
        if (accountResult.Msg.equals(getString(R.string.verity_password_fail))) {
            ToastUtils.showToastShort(getActivity(), R.string.again_input_old_password);
        }
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void loginResult(AccountResult accountResult) {
        super.loginResult(accountResult);
        if (accountResult.Code.equals(String.valueOf(1))) {
            Logger.m3442e("注册成功后的立即登录:" + accountResult.toString(), new Object[0]);
            SharedPreferences.Editor editorEdit = this.mSharePre.edit();
            editorEdit.putString(Constants.ACCOUNT, this.accountUser.phone);
            editorEdit.putBoolean(Constants.IS_LOGIN, true);
            editorEdit.apply();
            getActivity().finish();
        }
    }
}

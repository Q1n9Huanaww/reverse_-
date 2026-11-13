package com.p024ds.daisi.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.activity.FindPasswordActivity;
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
public class LoginFragment extends AccountViewImpl implements View.OnClickListener {
    public static final int LOGIN_BY_FLOAT = 2;
    public static final int LOGIN_BY_MAIN = 4;
    public static final int LOGIN_BY_PAY = 1;
    public static final int LOGIN_BY_REGISTER = 3;
    public static final String USER_NORMAL = "1";
    public static final String USER_STUDIO = "2";
    private String account;
    private AccountPresenter accountPresenter;
    private AccountUser accountUser;
    private Button mBtnLogin;
    private CheckBox mCbStudio;
    private EditText mEtUserAccount;
    private EditText mEtUserPassword;
    private ImageView mImgClearEdit;
    private SharedPreferences mSharePre;
    private TextView mTvForgetPassword;
    private int typeLogin;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_login;
    }

    public static LoginFragment newInstance(int i) {
        LoginFragment loginFragment = new LoginFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LoginFragment.class.getCanonicalName(), i);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.typeLogin = getArguments().getInt(LoginFragment.class.getCanonicalName(), -1);
        }
        Logger.m3442e("onCreate" + this.typeLogin, new Object[0]);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(LoginFragment.class.getCanonicalName());
        this.account = this.mSharePre.getString(Constants.ACCOUNT, "");
        if (this.mEtUserAccount != null) {
            this.mEtUserAccount.setText(this.account);
        }
        Logger.m3442e("onResume" + this.mEtUserAccount, new Object[0]);
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(LoginFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
        initData();
        initEvent();
    }

    private void initView(View view) {
        this.mTvForgetPassword = (TextView) view.findViewById(R.id.id_forget_password);
        this.mBtnLogin = (Button) view.findViewById(R.id.id_login);
        this.mEtUserAccount = (EditText) view.findViewById(R.id.id_phone_number);
        this.mEtUserPassword = (EditText) view.findViewById(R.id.id_input_password);
        this.mImgClearEdit = (ImageView) view.findViewById(R.id.id_img_clear_edittext);
        this.mCbStudio = (CheckBox) view.findViewById(R.id.id_cb_studio);
    }

    private void initData() {
        this.accountPresenter = new AccountPresenter(this);
        this.accountUser = new AccountUser();
        this.mSharePre = getActivity().getSharedPreferences(Constants.USER_MESSAGE, 0);
        this.account = this.mSharePre.getString(Constants.ACCOUNT, "");
        if (this.mSharePre.getBoolean(Constants.IS_STUDIO, false)) {
            this.mCbStudio.setChecked(true);
        }
        Logger.m3442e("account：" + this.account, new Object[0]);
        this.mEtUserAccount.setText(this.account);
        if (TextUtils.isEmpty(this.account)) {
            return;
        }
        this.mEtUserAccount.setFocusable(false);
    }

    private void initEvent() {
        this.mTvForgetPassword.setOnClickListener(this);
        this.mBtnLogin.setOnClickListener(this);
        this.mEtUserAccount.setOnClickListener(this);
        this.mImgClearEdit.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.id_phone_number /* 2131755391 */:
                this.mEtUserAccount.setFocusable(true);
                this.mEtUserAccount.setFocusableInTouchMode(true);
                this.mEtUserAccount.setOnClickListener(null);
                this.mEtUserAccount.requestFocus();
                break;
            case R.id.id_img_clear_edittext /* 2131755392 */:
                this.mEtUserAccount.setFocusable(true);
                this.mEtUserAccount.setFocusableInTouchMode(true);
                this.mEtUserAccount.setOnClickListener(null);
                this.mEtUserAccount.requestFocus();
                this.mEtUserAccount.setText("");
                break;
            case R.id.id_login /* 2131755394 */:
                String string = this.mEtUserAccount.getText().toString();
                String string2 = this.mEtUserPassword.getText().toString();
                if (TextUtils.isEmpty(string) || !AppDeviceUtils.isValidAccountOrPassword(string) || string.contains(" ") || string.length() < 6 || string.length() > 18) {
                    ToastUtils.showToastLong(getActivity(), R.string.again_input_account);
                } else if (TextUtils.isEmpty(string2) || !AppDeviceUtils.isValidAccountOrPassword(string2) || string2.contains(" ") || string2.length() < 6 || string2.length() > 18) {
                    ToastUtils.showToastLong(getActivity(), R.string.again_input_password);
                } else {
                    this.accountUser.phone = string;
                    this.accountUser.password = MD5Util.MD5(string2);
                    this.accountUser.packagename = getActivity().getPackageName();
                    this.accountUser.type = AccountManager.TYPE_LOGIN;
                    if (this.mCbStudio.isChecked()) {
                        this.accountUser.usertype = "2";
                        this.mSharePre.edit().putBoolean(Constants.IS_STUDIO, true).commit();
                    } else {
                        this.accountUser.usertype = "1";
                    }
                    this.accountPresenter.login(getActivity(), this.accountUser);
                }
                break;
            case R.id.id_forget_password /* 2131755395 */:
                startActivity(new Intent(getActivity(), (Class<?>) FindPasswordActivity.class));
                break;
        }
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void loginResult(AccountResult accountResult) {
        super.loginResult(accountResult);
        if (accountResult.Code.equals(String.valueOf(1))) {
            ToastUtils.showToastShort(getActivity(), accountResult.Msg);
            SharedPreferences.Editor editorEdit = this.mSharePre.edit();
            editorEdit.putString(Constants.ACCOUNT, this.accountUser.phone);
            editorEdit.putBoolean(Constants.IS_LOGIN, true);
            editorEdit.apply();
            switch (this.typeLogin) {
                case 2:
                    getActivity().finish();
                    break;
                case 3:
                    getActivity().startActivity(new Intent(getActivity(), (Class<?>) ElfinPayActivity.class));
                    break;
                case 4:
                    getActivity().finish();
                    break;
            }
        }
        if (accountResult.Code.equals(String.valueOf(-1))) {
            ToastUtils.showToastShort(getActivity(), accountResult.Msg);
        }
    }
}

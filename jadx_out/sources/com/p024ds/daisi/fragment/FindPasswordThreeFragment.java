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
import com.p024ds.daisi.mvp.presenters.AccountPresenter;
import com.p024ds.daisi.mvp.viewimpls.AccountViewImpl;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.MD5Util;
import com.p024ds.daisi.util.ToastUtils;
import com.umeng.analytics.MobclickAgent;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class FindPasswordThreeFragment extends AccountViewImpl implements View.OnClickListener {
    private AccountPresenter mAccountPresenter;
    private AccountUser mAccountUser;
    private Button mBtnComplete;
    private EditText mEditPassword;
    private SharedPreferences mSharePre;

    @Override // com.p024ds.daisi.fragment.BaseFragement
    public int getFragmentLayoutId() {
        return R.layout.fragment_find_password_three;
    }

    public static FindPasswordThreeFragment newInstance(AccountUser accountUser) {
        FindPasswordThreeFragment findPasswordThreeFragment = new FindPasswordThreeFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FindPasswordThreeFragment.class.getCanonicalName(), accountUser);
        findPasswordThreeFragment.setArguments(bundle);
        return findPasswordThreeFragment;
    }

    @Override // android.support.v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (getArguments() != null) {
            this.mAccountUser = (AccountUser) getArguments().getParcelable(FindPasswordThreeFragment.class.getCanonicalName());
            Logger.m3442e("找回密码：" + this.mAccountUser.toString(), new Object[0]);
        }
    }

    @Override // android.support.v4.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        initView(view);
    }

    private void initView(View view) {
        this.mAccountPresenter = new AccountPresenter(this);
        this.mSharePre = getActivity().getSharedPreferences(Constants.USER_MESSAGE, 0);
        this.mEditPassword = (EditText) view.findViewById(R.id.id_input_password);
        this.mBtnComplete = (Button) view.findViewById(R.id.id_step_complete);
        this.mBtnComplete.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.id_step_complete) {
            return;
        }
        String string = this.mEditPassword.getText().toString();
        if (TextUtils.isEmpty(string) || !AppDeviceUtils.isValidAccountOrPassword(string) || string.contains(" ") || string.length() < 6 || string.length() > 18) {
            ToastUtils.showToastLong(getActivity(), R.string.again_input_password);
            return;
        }
        this.mAccountUser.type = "2";
        this.mAccountUser.bindphone = null;
        this.mAccountUser.password = MD5Util.MD5(string);
        this.mAccountUser.codetype = "2";
        Logger.m3442e("找回密码:" + this.mAccountUser.toString(), new Object[0]);
        this.mAccountPresenter.findPassword(getActivity(), this.mAccountUser);
    }

    @Override // com.p024ds.daisi.mvp.viewimpls.AccountViewImpl, com.p024ds.daisi.mvp.views.AccountView
    public void alterAndFindPasswordResult(AccountResult accountResult) {
        Logger.m3442e("找回密码：" + accountResult.toString() + "   ===" + this.mAccountUser.phone, new Object[0]);
        if (accountResult.Code.equals(String.valueOf(1))) {
            this.mSharePre.edit().putString(Constants.ACCOUNT, this.mAccountUser.phone).commit();
            ToastUtils.showToastShort(getActivity(), R.string.find_password_successful);
            getActivity().finish();
            return;
        }
        ToastUtils.showToastShort(getActivity(), R.string.find_password_fail);
    }

    @Override // android.support.v4.app.Fragment
    public void onResume() throws Throwable {
        super.onResume();
        MobclickAgent.onPageStart(FindPasswordThreeFragment.class.getCanonicalName());
    }

    @Override // android.support.v4.app.Fragment
    public void onPause() throws Throwable {
        super.onPause();
        MobclickAgent.onPageEnd(FindPasswordThreeFragment.class.getCanonicalName());
    }
}

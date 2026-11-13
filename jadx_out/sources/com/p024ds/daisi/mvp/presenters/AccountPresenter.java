package com.p024ds.daisi.mvp.presenters;

import android.app.Activity;
import android.content.Context;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.entity.AccountUser;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.views.AccountView;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class AccountPresenter implements BasePresenter {
    private AccountManager accountManager = new AccountManager();
    private AccountView accountView;

    public AccountPresenter(AccountView accountView) {
        this.accountView = accountView;
    }

    public void phoneCodes(Context context, AccountUser accountUser) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.null_hint_message);
        }
        this.accountManager.phoneCodes(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.1
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.codesResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }

    public void register(Context context, AccountUser accountUser) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.register);
        }
        this.accountManager.register(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.2
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.registerResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }

    public void login(Context context, AccountUser accountUser) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.login_title);
        }
        this.accountManager.login(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.3
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.loginResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }

    public void alterPassword(Context context, AccountUser accountUser) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.change_password_title);
        }
        this.accountManager.alterPassword(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.4
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.alterAndFindPasswordResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }

    public void findPassword(Context context, AccountUser accountUser) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.find_password);
        }
        this.accountManager.findPassword(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.5
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.alterAndFindPasswordResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }

    public void getPhoneByAccount(AccountUser accountUser, Context context) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.null_hint_message);
        }
        this.accountManager.getPhoneByAccount(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.6
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.getBindPhoneResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }

    public void checkedAccountDuplicated(AccountUser accountUser, Context context) {
        if (!((Activity) context).isFinishing()) {
            this.accountView.showProgressDialog(context, R.string.account_verify);
        }
        this.accountManager.checkedAccountDuplicated(accountUser, new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.AccountPresenter.7
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                AccountPresenter.this.accountView.hideProgressDialog();
                AccountPresenter.this.accountView.checkedAccountDuplicatedResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                AccountPresenter.this.accountView.hideProgressDialog();
            }
        });
    }
}

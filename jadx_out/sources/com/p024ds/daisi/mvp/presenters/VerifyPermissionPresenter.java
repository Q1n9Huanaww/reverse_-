package com.p024ds.daisi.mvp.presenters;

import android.app.Activity;
import android.content.Context;
import com.p024ds.daisi.entity.AccountResult;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.managers.VerifyPermissionManager;
import com.p024ds.daisi.mvp.views.VerifyPermissionView;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class VerifyPermissionPresenter {
    private VerifyPermissionManager verifyPermissionManager = new VerifyPermissionManager();
    private VerifyPermissionView verifyPermissionView;

    public VerifyPermissionPresenter(VerifyPermissionView verifyPermissionView) {
        this.verifyPermissionView = verifyPermissionView;
    }

    public void checkedUserFreeze(final Context context) {
        if (!((Activity) context).isFinishing()) {
            this.verifyPermissionView.showProgressDialog(context, R.string.account_verify);
        }
        this.verifyPermissionManager.checkedUserFreeze(new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.VerifyPermissionPresenter.1
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                VerifyPermissionPresenter.this.verifyPermissionView.hideProgressDialog();
                VerifyPermissionPresenter.this.verifyPermissionView.accountCheckedResult(accountResult);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
                if (((Activity) context).isFinishing()) {
                    return;
                }
                VerifyPermissionPresenter.this.verifyPermissionView.hideProgressDialog();
            }
        }, context);
    }

    public void getScriptTryCount(Context context) {
        this.verifyPermissionManager.getScriptTryCount(new Callback<AccountResult>() { // from class: com.ds.daisi.mvp.presenters.VerifyPermissionPresenter.2
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str) {
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(AccountResult accountResult) {
                VerifyPermissionPresenter.this.verifyPermissionView.getTryCountResult(accountResult);
            }
        }, context);
    }
}

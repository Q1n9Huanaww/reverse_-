package com.p024ds.daisi.mvp.presenters;

import android.content.Context;
import com.p024ds.daisi.entity.TokenBean;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.managers.VerifyTokenManager;
import com.p024ds.daisi.mvp.views.CheckTokenView;

/* JADX INFO: loaded from: classes.dex */
public class VerifyTokenPresenter implements BasePresenter {
    private CheckTokenView checkTokenView;
    private VerifyTokenManager verifyTokenManager = new VerifyTokenManager();

    public VerifyTokenPresenter(CheckTokenView checkTokenView) {
        this.checkTokenView = checkTokenView;
    }

    public void generateToken(Context context, String str) {
        this.verifyTokenManager.generateToken(context, str, new Callback<TokenBean>() { // from class: com.ds.daisi.mvp.presenters.VerifyTokenPresenter.1
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str2) {
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(TokenBean tokenBean) {
                VerifyTokenPresenter.this.checkTokenView.generatorToken(tokenBean);
            }
        });
    }

    public void checkToken(Context context, String str, String str2) {
        this.verifyTokenManager.checkToken(context, str, str2, new Callback<TokenBean>() { // from class: com.ds.daisi.mvp.presenters.VerifyTokenPresenter.2
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(TokenBean tokenBean) {
                VerifyTokenPresenter.this.checkTokenView.checkToken(tokenBean);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str3) {
                VerifyTokenPresenter.this.checkTokenView.checkTokenFail(str3);
            }
        });
    }

    public void cancelReqAboutToken() {
        this.verifyTokenManager.cancelTag();
    }
}

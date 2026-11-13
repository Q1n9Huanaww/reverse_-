package com.p024ds.daisi.mvp.views;

import com.p024ds.daisi.entity.AccountResult;

/* JADX INFO: loaded from: classes.dex */
public interface AccountView extends BaseView {
    void alterAndFindPasswordResult(AccountResult accountResult);

    void checkedAccountDuplicatedResult(AccountResult accountResult);

    void codesResult(AccountResult accountResult);

    void getBindPhoneResult(AccountResult accountResult);

    void loginResult(AccountResult accountResult);

    void registerResult(AccountResult accountResult);
}

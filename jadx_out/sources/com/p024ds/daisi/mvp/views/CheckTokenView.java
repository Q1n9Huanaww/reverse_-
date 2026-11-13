package com.p024ds.daisi.mvp.views;

import com.p024ds.daisi.entity.TokenBean;

/* JADX INFO: loaded from: classes.dex */
public interface CheckTokenView {
    void checkToken(TokenBean tokenBean);

    void checkTokenFail(String str);

    void generatorToken(TokenBean tokenBean);
}

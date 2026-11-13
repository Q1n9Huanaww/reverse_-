package com.p024ds.daisi.mvp.views;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public interface BaseView {
    void hideProgress();

    void hideProgressDialog();

    void showProgress();

    void showProgressDialog(Context context, int i);
}

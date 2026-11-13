package com.p024ds.daisi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.log.CommonLog;
import com.p024ds.daisi.manager.DialogManager;
import com.p024ds.daisi.mvp.views.BaseView;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseFragement extends Fragment implements APPConstant, BaseView {
    protected AppContext appContext;
    protected CommonLog commonLog = new CommonLog();

    public abstract int getFragmentLayoutId();

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void hideProgress() {
    }

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void showProgress() {
    }

    @Override // android.support.v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(getFragmentLayoutId(), viewGroup, false);
    }

    @Override // android.support.v4.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        setUserVisibleHint(true);
    }

    @Override // android.support.v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.appContext = (AppContext) getActivity().getApplicationContext();
    }

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void showProgressDialog(Context context, int i) {
        DialogManager.getInstance().showProgressDialog(context, i);
    }

    @Override // com.p024ds.daisi.mvp.views.BaseView
    public void hideProgressDialog() {
        DialogManager.getInstance().dismissProgressDialog();
    }
}

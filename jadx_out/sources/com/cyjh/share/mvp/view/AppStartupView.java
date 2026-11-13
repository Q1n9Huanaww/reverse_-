package com.cyjh.share.mvp.view;

import com.cyjh.share.bean.response.AppStartupResponse;
import com.cyjh.share.mvp.base.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface AppStartupView extends BaseView {
    void startupFail(String str);

    void startupSuc(AppStartupResponse appStartupResponse);
}

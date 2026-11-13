package com.cyjh.share.mvp.view;

import com.cyjh.share.bean.response.TemplateResponse;
import com.cyjh.share.mvp.base.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface TemplateVerifyView extends BaseView {
    void templateVerifyFailure(String str);

    void templateVerifySuccess(TemplateResponse templateResponse);
}

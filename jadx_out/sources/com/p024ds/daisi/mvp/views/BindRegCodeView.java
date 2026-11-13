package com.p024ds.daisi.mvp.views;

import com.cyjh.share.bean.response.BaseResponseInfo;
import com.cyjh.share.mvp.base.BaseView;
import com.p024ds.daisi.entity.BindRegCodeResponse;

/* JADX INFO: loaded from: classes.dex */
public interface BindRegCodeView extends BaseView {
    void onBindFailure(BaseResponseInfo baseResponseInfo);

    void onBindSuc(BindRegCodeResponse bindRegCodeResponse);
}

package com.p024ds.daisi.mvp.views;

import com.cyjh.share.mvp.base.BaseView;
import com.p024ds.daisi.entity.RegCodeInfoResponse;

/* JADX INFO: loaded from: classes.dex */
public interface QueryRegCodeView extends BaseView {
    void onQueryFailure(String str);

    void onQuerySuc(RegCodeInfoResponse regCodeInfoResponse);
}

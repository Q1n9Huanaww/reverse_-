package com.p024ds.daisi.mvp.views;

import com.cyjh.share.mvp.base.BaseView;
import com.p024ds.daisi.entity.RegCodeInfoResponse;

/* JADX INFO: loaded from: classes.dex */
public interface UnBindRegCodeView extends BaseView {
    void onUnbindFailure(String str);

    void onUnbindSuc(RegCodeInfoResponse regCodeInfoResponse);
}

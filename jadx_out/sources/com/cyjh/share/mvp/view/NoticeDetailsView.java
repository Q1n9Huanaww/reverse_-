package com.cyjh.share.mvp.view;

import com.cyjh.share.bean.response.NoticeBean;
import com.cyjh.share.mvp.base.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface NoticeDetailsView extends BaseView {
    void onFail();

    void onSuc(NoticeBean noticeBean);
}

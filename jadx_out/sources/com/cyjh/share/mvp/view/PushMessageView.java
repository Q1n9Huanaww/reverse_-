package com.cyjh.share.mvp.view;

import com.cyjh.share.bean.NotifyMsgBean;
import com.cyjh.share.mvp.base.BaseView;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface PushMessageView extends BaseView {
    void onSuccessfulAcquireNews(List<NotifyMsgBean> list);
}

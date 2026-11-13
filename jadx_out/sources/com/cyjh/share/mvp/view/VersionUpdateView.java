package com.cyjh.share.mvp.view;

import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.mvp.base.BaseView;

/* JADX INFO: loaded from: classes.dex */
public interface VersionUpdateView extends BaseView {
    void onUpdateEmpty();

    void onUpdateFailure(String str);

    void onUpdateSuccessful(VersionUpdateInfo versionUpdateInfo);
}

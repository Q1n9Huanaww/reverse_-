package com.cyjh.share.mvp.manager;

import android.content.Context;
import com.cyjh.share.bean.request.UpdateRequestInfo;
import com.cyjh.share.bean.response.VersionUpdateInfo;
import com.cyjh.share.mvp.base.BaseManager;
import com.cyjh.share.mvp.presenter.AppVersionUpdatePresenter;
import com.cyjh.share.mvp.view.VersionUpdateView;

/* JADX INFO: loaded from: classes.dex */
public class VersionUpdateManager implements BaseManager, VersionUpdateView {
    public static final int HAS_VERSION_NEW_VALUE = 2;
    public static final int PASS_OPERA_VALUE = 3;
    private static VersionUpdateManager sInstance;
    private OnUpdateVersionBack mDismissBack;
    private AppVersionUpdatePresenter mUpdatePresenter;

    public interface OnUpdateVersionBack {
        void onUpdateHas(VersionUpdateInfo versionUpdateInfo);
    }

    private VersionUpdateManager() {
    }

    public static VersionUpdateManager builder() {
        if (sInstance == null) {
            sInstance = new VersionUpdateManager();
        }
        return sInstance;
    }

    public void updateVersion(boolean z, Context context) {
        this.mUpdatePresenter = new AppVersionUpdatePresenter(context, this, z);
        this.mUpdatePresenter.updateVersion(context);
    }

    public void updateVersion(boolean z, Context context, UpdateRequestInfo updateRequestInfo) {
        this.mUpdatePresenter = new AppVersionUpdatePresenter(context, this, z, updateRequestInfo);
        this.mUpdatePresenter.updateVersion(context);
    }

    @Override // com.cyjh.share.mvp.view.VersionUpdateView
    public void onUpdateSuccessful(VersionUpdateInfo versionUpdateInfo) {
        if (this.mDismissBack != null) {
            this.mDismissBack.onUpdateHas(versionUpdateInfo);
        }
    }

    @Override // com.cyjh.share.mvp.view.VersionUpdateView
    public void onUpdateFailure(String str) {
        this.mDismissBack.onUpdateHas(null);
    }

    @Override // com.cyjh.share.mvp.view.VersionUpdateView
    public void onUpdateEmpty() {
        this.mDismissBack.onUpdateHas(null);
    }

    public VersionUpdateManager setDismissBack(OnUpdateVersionBack onUpdateVersionBack) {
        this.mDismissBack = onUpdateVersionBack;
        return this;
    }

    public void onCancelUpdate() {
        if (this.mUpdatePresenter != null) {
            this.mUpdatePresenter.onCancel();
        }
    }
}

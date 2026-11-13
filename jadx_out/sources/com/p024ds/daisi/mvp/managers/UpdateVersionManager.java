package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.entity.UpdateInfo;
import com.p024ds.daisi.mvp.presenters.UpdateVersionPresenter;
import com.p024ds.daisi.mvp.views.UpdateVersionView;

/* JADX INFO: loaded from: classes.dex */
public class UpdateVersionManager implements BaseManager, UpdateVersionView {
    private static UpdateVersionManager sInstance;
    private OnUpdateVersionBack mDismissBack;
    private UpdateVersionPresenter mUpdateVersionPresenter;
    private boolean isPop = false;
    private boolean isDismiss = false;

    public interface OnUpdateVersionBack {
        void onCall();
    }

    private UpdateVersionManager() {
        if (this.mUpdateVersionPresenter == null) {
            this.mUpdateVersionPresenter = new UpdateVersionPresenter(AppContext.getInstance(), this);
        }
    }

    public static UpdateVersionManager builder() {
        if (sInstance == null) {
            sInstance = new UpdateVersionManager();
        }
        return sInstance;
    }

    public void updateVersion(boolean z, Context context) {
        this.mUpdateVersionPresenter.updateVersion(z, context);
    }

    @Override // com.p024ds.daisi.mvp.views.UpdateVersionView
    public void onUpdateSuccessful(UpdateInfo updateInfo) {
        updateInfo.isFloat = this.isPop;
        updateInfo.isDismissing = this.isDismiss;
        if (this.mDismissBack != null) {
            this.mDismissBack.onCall();
        }
        this.isPop = false;
        this.isDismiss = false;
    }

    public UpdateVersionManager setPop(boolean z) {
        this.isPop = z;
        return this;
    }

    public UpdateVersionManager setDismiss(boolean z) {
        this.isDismiss = z;
        return this;
    }

    public UpdateVersionManager setDismissBack(OnUpdateVersionBack onUpdateVersionBack) {
        this.mDismissBack = onUpdateVersionBack;
        return this;
    }
}

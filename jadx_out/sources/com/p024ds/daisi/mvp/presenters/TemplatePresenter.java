package com.p024ds.daisi.mvp.presenters;

import android.content.Context;
import com.orhanobut.logger.Logger;
import com.p024ds.daisi.entity.TemplateStatus;
import com.p024ds.daisi.mvp.managers.BaseManager;
import com.p024ds.daisi.mvp.managers.Callback;
import com.p024ds.daisi.mvp.managers.TemplateManager;
import com.p024ds.daisi.mvp.views.TemplateView;
import com.p024ds.daisi.net.URL.URLConstant;

/* JADX INFO: loaded from: classes.dex */
public class TemplatePresenter implements BaseManager {
    private TemplateManager templateManager = new TemplateManager();
    private TemplateView templateView;

    public TemplatePresenter(TemplateView templateView) {
        this.templateView = templateView;
    }

    public void singleApkOrTemplateChecked(String str, Context context, final String str2) {
        this.templateManager.singleApkOrTemplateChecked(str, new Callback<TemplateStatus>() { // from class: com.ds.daisi.mvp.presenters.TemplatePresenter.1
            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void finish(TemplateStatus templateStatus) {
                TemplatePresenter.this.templateView.apkCheckedResult(templateStatus);
            }

            @Override // com.p024ds.daisi.mvp.managers.Callback
            public void error(String str3) {
                String str4;
                Logger.m3442e("request error type:" + str3, new Object[0]);
                if (str2.equals(URLConstant.MONOMER_INSTALLATION_PACKAGE)) {
                    str4 = String.valueOf(11) + str3;
                } else {
                    str4 = String.valueOf(10) + str3;
                }
                TemplatePresenter.this.templateView.errorMsg(str4);
            }
        }, context, str2);
    }
}

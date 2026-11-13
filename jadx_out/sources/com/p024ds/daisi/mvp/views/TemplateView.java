package com.p024ds.daisi.mvp.views;

import com.p024ds.daisi.entity.TemplateStatus;

/* JADX INFO: loaded from: classes.dex */
public interface TemplateView extends BaseView {
    void apkCheckedResult(TemplateStatus templateStatus);

    void errorMsg(String str);
}

package com.p024ds.daisi.mvp.presenters.news;

import android.content.Context;
import com.cyjh.share.bean.request.BindRegCodeRequestInfo;
import com.cyjh.share.bean.response.BaseResponseInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.entity.BindRegCodeResponse;
import com.p024ds.daisi.mvp.views.BindRegCodeView;
import com.warnship.autoplay.R;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class BindRegisterCodePresenter extends AbstractHttpPresenter {
    private static final String TAG = "BindRegisterCodePresenter";
    private BindRegCodeView mBindRegCodeView;

    public BindRegisterCodePresenter(BindRegCodeView bindRegCodeView) {
        this.mBindRegCodeView = bindRegCodeView;
    }

    public void bindRegCode(String str, Context context, int i, String str2) {
        try {
            BindRegCodeRequestInfo bindRegCodeRequestInfo = new BindRegCodeRequestInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            bindRegCodeRequestInfo.RegCode = str;
            bindRegCodeRequestInfo.OldRegCode = str2;
            bindRegCodeRequestInfo.IsSuperpose = i;
            Map<String, String> mapPrames = VariableAndConstantsManager.getInstance().toMapPrames(bindRegCodeRequestInfo);
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.SCRIPT_OPERA_NAME, InterfaceRelatedConstants.BIND_REGISTER_CODE_NAME).build().toString(), mapPrames);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            String str = (String) obj;
            SlLog.m1334i(TAG, "uiDataSuccess --> json=" + str);
            BindRegCodeResponse bindRegCodeResponse = (BindRegCodeResponse) GsonExUtil.parsData(str, BindRegCodeResponse.class);
            if (bindRegCodeResponse == null) {
                this.mBindRegCodeView.onBindFailure(new BaseResponseInfo(-1, AppContext.getInstance().getResources().getString(R.string.json_data_null)));
            } else {
                this.mBindRegCodeView.onBindSuc(bindRegCodeResponse);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            this.mBindRegCodeView.onBindFailure(new BaseResponseInfo(-1, e.getMessage()));
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        this.mBindRegCodeView.onBindFailure(new BaseResponseInfo(-1, str));
    }

    public void onCancel() {
        this.f1688mA.stopRequest(getClass().getCanonicalName());
    }
}

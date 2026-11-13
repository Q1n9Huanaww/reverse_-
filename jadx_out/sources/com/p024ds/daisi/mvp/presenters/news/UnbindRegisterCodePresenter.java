package com.p024ds.daisi.mvp.presenters.news;

import android.content.Context;
import com.cyjh.share.bean.request.UnbindRegCodeRequestInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.GsonExUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.entity.RegCodeInfoResponse;
import com.p024ds.daisi.mvp.views.UnBindRegCodeView;
import com.warnship.autoplay.R;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class UnbindRegisterCodePresenter extends AbstractHttpPresenter {
    private UnBindRegCodeView mView;

    public UnbindRegisterCodePresenter(UnBindRegCodeView unBindRegCodeView) {
        this.mView = unBindRegCodeView;
    }

    public void unbindRegCode(String str, Context context) {
        try {
            UnbindRegCodeRequestInfo unbindRegCodeRequestInfo = new UnbindRegCodeRequestInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            unbindRegCodeRequestInfo.RegCode = str;
            Map<String, String> mapPrames = VariableAndConstantsManager.getInstance().toMapPrames(unbindRegCodeRequestInfo);
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.SCRIPT_OPERA_NAME, InterfaceRelatedConstants.UNBIND_REGISTER_CODE_NAME).build().toString(), mapPrames);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            RegCodeInfoResponse regCodeInfoResponse = (RegCodeInfoResponse) GsonExUtil.parsData((String) obj, RegCodeInfoResponse.class);
            if (regCodeInfoResponse != null) {
                this.mView.onUnbindSuc(regCodeInfoResponse);
            } else {
                this.mView.onUnbindFailure(AppContext.getInstance().getResources().getString(R.string.reg_code_parse_exception));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            this.mView.onUnbindFailure("解绑注册码解析异常:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        this.mView.onUnbindFailure(str);
    }

    public void cancel() {
        this.f1688mA.stopRequest(getClass().getCanonicalName());
    }
}

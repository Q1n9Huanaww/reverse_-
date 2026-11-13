package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.util.Log;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.response.AliCloudServerResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.GsonExUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class GetTokenAliCloudPresenter extends AbstractHttpPresenter {
    private long currentTimestamp;

    public void load(Context context) {
        try {
            BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(context);
            this.currentTimestamp = VariableAndConstantsManager.sCurrentTimestamp;
            this.f1688mA.sendPostRequest(context, GetTokenAliCloudPresenter.class.getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.ALI_CLOUD_NAME, InterfaceRelatedConstants.ALI_CLOUD_TOKNE).build().toString(), VariableAndConstantsManager.getInstance().toMapPrames(baseRequestParams));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "GetTokenAliCloudPresenter--load--" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            JSONObject jSONObject = new JSONObject((String) obj);
            JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
            if (jSONObject.getInt("Code") == 200 && VariableAndConstantsManager.sCurrentTimestamp == this.currentTimestamp) {
                VariableAndConstantsManager.getInstance().mAliCloudServerResponse = (AliCloudServerResponse) GsonExUtil.parsData(jSONObject2.toString(), AliCloudServerResponse.class);
            }
        } catch (Exception e) {
            Log.e("zzz", "GetTokenAliCloudPresenter--uiDataSuccess exception-" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        Log.e("zzz", "GetTokenAliCloudPresenter--onErrorResponse--" + str);
    }

    public void onCancel() {
        this.f1688mA.stopRequest(GetTokenAliCloudPresenter.class.getCanonicalName());
    }
}

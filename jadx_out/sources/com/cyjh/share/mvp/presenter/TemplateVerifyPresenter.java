package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.cyjh.share.C0854R;
import com.cyjh.share.bean.response.TemplateResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.mvp.view.TemplateVerifyView;
import com.cyjh.share.util.GsonExUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class TemplateVerifyPresenter extends AbstractHttpPresenter {
    private long clientTimestamp;
    private TemplateVerifyView mView;

    public TemplateVerifyPresenter(TemplateVerifyView templateVerifyView) {
        this.mView = templateVerifyView;
    }

    public void loadAd(Context context) {
        try {
            Map<String, String> mapPrames = VariableAndConstantsManager.getInstance().toMapPrames(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            this.clientTimestamp = VariableAndConstantsManager.sCurrentTimestamp;
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.TEMPLATE_VERIFY_NAME).build().toString(), mapPrames);
        } catch (Exception e) {
            Log.e("zzz", "TemplateVerifyPresenter--loadAd:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
                int i = jSONObject.getInt("Code");
                String string = jSONObject.getString(Constants.DATABASE_TABLE_MSG);
                if (i == 200) {
                    TemplateResponse templateResponse = (TemplateResponse) GsonExUtil.parsData(jSONObject2.toString(), TemplateResponse.class);
                    if (templateResponse != null && this.clientTimestamp == templateResponse.ClientTimestamp) {
                        this.mView.templateVerifySuccess(templateResponse);
                    } else {
                        this.mView.templateVerifyFailure(VariableAndConstantsManager.getInstance().getContext().getResources().getString(C0854R.string.timestamp_diff_except));
                    }
                } else {
                    this.mView.templateVerifyFailure(string);
                }
            } else {
                this.mView.templateVerifyFailure(VariableAndConstantsManager.getInstance().getContext().getResources().getString(C0854R.string.json_data_null));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            this.mView.templateVerifyFailure(e.getMessage());
            Log.e("zzz", "com.cyjh.share.TemplateVerifyPresenter--uiDataSuccess:Exception--" + e.getMessage());
        }
    }

    public void onCancel() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        this.mView.templateVerifyFailure(str);
    }
}

package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.cyjh.share.C0854R;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.response.AppStartupResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.mvp.view.AppStartupView;
import com.cyjh.share.util.GsonExUtil;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppStartupPresenter extends AbstractHttpPresenter {
    private long clientTimestamp;
    private AppStartupView mView;

    public AppStartupPresenter(AppStartupView appStartupView) {
        this.mView = appStartupView;
    }

    public void loadAd(Context context) {
        try {
            BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(context);
            this.clientTimestamp = VariableAndConstantsManager.sCurrentTimestamp;
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.APP_START_NAME).build().toString(), VariableAndConstantsManager.getInstance().toMapPrames(baseRequestParams));
        } catch (Exception e) {
            Log.e("zzz", "AppStartupPresenter--loadAd:" + e.getMessage());
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
                    AppStartupResponse appStartupResponse = (AppStartupResponse) GsonExUtil.parsData(jSONObject2.toString(), AppStartupResponse.class);
                    if (appStartupResponse != null && this.clientTimestamp == appStartupResponse.ClientTimestamp) {
                        this.mView.startupSuc(appStartupResponse);
                    } else {
                        this.mView.startupFail(VariableAndConstantsManager.getInstance().getContext().getResources().getString(C0854R.string.timestamp_diff_except));
                    }
                } else {
                    this.mView.startupFail(string);
                }
            } else {
                this.mView.startupFail(VariableAndConstantsManager.getInstance().getContext().getResources().getString(C0854R.string.json_data_null));
            }
        } catch (Exception e) {
            this.mView.startupFail(e.getMessage());
        }
    }

    public void onCancel() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        Log.e("zzz", "com.cyjh.share.AppStartupPresenter--onErrorResponse:" + str);
        this.mView.startupFail(str);
    }
}

package com.cyjh.share.mvp.presenter;

import android.content.Context;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.request.DomainNameRequestParamsInfo;
import com.cyjh.share.bean.response.DomainResult;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppDomainRequestPresenter extends AbstractHttpPresenter {
    private static final String TAG = "AppDomainRequestPresenter";
    private static AppDomainRequestPresenter sStatisticsPresenter;
    private OnResultCallback mOnResultCallback;

    public interface OnResultCallback {
        void onFailure(int i, String str);

        void onSuccess(DomainResult domainResult);
    }

    private AppDomainRequestPresenter() {
    }

    public static AppDomainRequestPresenter getInstance() {
        if (sStatisticsPresenter == null) {
            synchronized (AppDomainRequestPresenter.class) {
                if (sStatisticsPresenter == null) {
                    sStatisticsPresenter = new AppDomainRequestPresenter();
                }
            }
        }
        return sStatisticsPresenter;
    }

    public void getDomainNameFirst(Context context, OnResultCallback onResultCallback) {
        try {
            this.mOnResultCallback = onResultCallback;
            BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(context);
            CommonUtil.writeDataToFile(CommonUtil.getBaseParamsFile(context), baseRequestParams.toString(), true);
            DomainNameRequestParamsInfo domainNameRequestParamsInfo = new DomainNameRequestParamsInfo(baseRequestParams);
            SlLog.m1334i(TAG, "getDomainNameFirst --> domainNameRequestParams=" + domainNameRequestParamsInfo.toString());
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), InterfaceRelatedConstants.GET_DOMAIN_NAME_FIRST_AVAILABLE_URL, VariableAndConstantsManager.getInstance().toMapPrames(domainNameRequestParamsInfo));
        } catch (Exception e) {
            SlLog.m1334i(TAG, "getDomainNameFirst --> exception=" + e.getMessage());
        }
    }

    public void getDomainNameSecond(Context context, OnResultCallback onResultCallback) {
        try {
            this.mOnResultCallback = onResultCallback;
            DomainNameRequestParamsInfo domainNameRequestParamsInfo = new DomainNameRequestParamsInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            SlLog.m1334i(TAG, "getDomainNameSecond --> domainNameRequestParams=" + domainNameRequestParamsInfo.toString());
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), InterfaceRelatedConstants.GET_DOMAIN_NAME_SECOND_AVAILABLE_URL, VariableAndConstantsManager.getInstance().toMapPrames(domainNameRequestParamsInfo));
        } catch (Exception e) {
            SlLog.m1334i(TAG, "getDomainNameSecond --> exception=" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        SlLog.m1334i(TAG, "uiDataSuccess --> object=" + obj);
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject((String) obj);
                int i = jSONObject.getInt("Code");
                String string = jSONObject.getString(Constants.DATABASE_TABLE_MSG);
                if (i == 200) {
                    DomainResult domainResult = (DomainResult) GsonExUtil.parsData(jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA).toString(), DomainResult.class);
                    if (this.mOnResultCallback != null) {
                        this.mOnResultCallback.onSuccess(domainResult);
                    }
                } else {
                    onRequestFailureOperate(i, string);
                }
                return;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                SlLog.m1334i(TAG, "uiDataSuccess --> exception=" + e.getMessage());
                onRequestFailureOperate(-1, e.getMessage());
                return;
            }
        }
        onRequestFailureOperate(-1, "后台返回数据为空！");
    }

    private void onRequestFailureOperate(int i, String str) {
        SlLog.m1334i(TAG, "onRequestFailureOperate --> code=" + i + ",message=" + str);
        if (this.mOnResultCallback != null) {
            this.mOnResultCallback.onFailure(i, str);
        }
    }

    public void onCancel() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
        }
    }

    public void cancelAllRequest() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
            this.f1688mA.stopRequest(getClass().getSimpleName());
            this.f1688mA.stopRequest(getClass().getName());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        SlLog.m1334i(TAG, "onErrorResponse --> errorMsg=" + str);
        onRequestFailureOperate(-1, str);
    }

    public AppDomainRequestPresenter setCallback(OnResultCallback onResultCallback) {
        this.mOnResultCallback = onResultCallback;
        return this;
    }
}

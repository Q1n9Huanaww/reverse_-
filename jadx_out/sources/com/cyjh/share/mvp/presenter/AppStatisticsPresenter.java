package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.util.Log;
import com.cyjh.share.C0854R;
import com.cyjh.share.bean.request.ScriptStartRunParams;
import com.cyjh.share.bean.request.ScriptStopRunParams;
import com.cyjh.share.bean.request.StatisticsRequestParamsInfo;
import com.cyjh.share.bean.response.ScriptStartRunResponse;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.helper.PhoneConfigHelper;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.ErrorUtil;
import com.cyjh.share.util.GsonExUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AppStatisticsPresenter extends AbstractHttpPresenter {
    private static AppStatisticsPresenter sStatisticsPresenter;
    private OnCallback mCallbackSucc;
    private OnClickScriptRunCallback mClickScriptRunCallback;

    public interface OnCallback {
        void onCallbackSuc(Object obj);
    }

    public interface OnClickScriptRunCallback {
        void onCallbackFail();

        void onCallbackSuc(Object obj);
    }

    private AppStatisticsPresenter() {
    }

    public static AppStatisticsPresenter getStatisticsPresenter() {
        if (sStatisticsPresenter == null) {
            synchronized (AppStatisticsPresenter.class) {
                if (sStatisticsPresenter == null) {
                    sStatisticsPresenter = new AppStatisticsPresenter();
                }
            }
        }
        return sStatisticsPresenter;
    }

    public void loadAd(Context context, int i, int i2, long j) {
        try {
            StatisticsRequestParamsInfo statisticsRequestParamsInfo = new StatisticsRequestParamsInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            statisticsRequestParamsInfo.Action = i2;
            statisticsRequestParamsInfo.BussinessId = i;
            statisticsRequestParamsInfo.RelationId = j;
            statisticsRequestParamsInfo.CloudPhoneType = PhoneConfigHelper.get().getCloudPhoneType();
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--loadAd:");
            Map<String, String> mapPrames = VariableAndConstantsManager.getInstance().toMapPrames(statisticsRequestParamsInfo);
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--parasMap:" + mapPrames);
            String string = MyConfig.getBuilderPay(InterfaceRelatedConstants.APP_STATISTICS_NAME).build().toString();
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--loadAd:" + string);
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), string, mapPrames);
        } catch (Exception e) {
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--loadAd:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--uiDataSuccess:" + obj);
        try {
            if (this.mCallbackSucc != null) {
                this.mCallbackSucc.onCallbackSuc(obj);
            }
            if (this.mClickScriptRunCallback != null) {
                ScriptStartRunResponse scriptStartRunResponse = (ScriptStartRunResponse) GsonExUtil.parsData((String) obj, ScriptStartRunResponse.class);
                if (ScriptStartRunResponse.sClientTime != 0 && ScriptStartRunResponse.sClientTime != scriptStartRunResponse.Data.ClientTimestamp) {
                    scriptStartRunResponse.Code = 602;
                    scriptStartRunResponse.Message = VariableAndConstantsManager.getInstance().getContext().getResources().getString(C0854R.string.check_sign_fail);
                    obj = GsonExUtil.class2Data(scriptStartRunResponse);
                    ScriptStartRunResponse.sClientTime = 0L;
                }
                this.mClickScriptRunCallback.onCallbackSuc(obj);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--uiDataSuccess:" + e.getMessage());
            callbackScriptRunFail();
        }
    }

    private void callbackScriptRunFail() {
        if (this.mClickScriptRunCallback != null) {
            this.mClickScriptRunCallback.onCallbackFail();
        }
    }

    public void onCancel() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
        }
    }

    public void cancelAllStatistics() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
            this.f1688mA.stopRequest(getClass().getSimpleName());
            this.f1688mA.stopRequest(getClass().getName());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        callbackScriptRunFail();
    }

    public AppStatisticsPresenter setCallback(OnCallback onCallback) {
        this.mCallbackSucc = onCallback;
        return this;
    }

    public void cleanCallback() {
        this.mCallbackSucc = null;
        this.mClickScriptRunCallback = null;
    }

    public void loadScriptStartRunStatistics(Context context, String str, String str2) {
        try {
            ScriptStartRunParams scriptStartRunParams = new ScriptStartRunParams(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            scriptStartRunParams.RegCode = str;
            ScriptStartRunResponse.sClientTime = scriptStartRunParams.ClientTimestamp;
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--loadScriptStartRunStatistics:" + scriptStartRunParams.toString());
            Map<String, String> mapPrames = VariableAndConstantsManager.getInstance().toMapPrames(scriptStartRunParams);
            if (ErrorUtil.debugMode) {
                String str3 = mapPrames.get(URLConstant.TOKEN_PARAMS_DATA);
                String str4 = mapPrames.get("R");
                ErrorUtil.writeProcessedStringToFile(ErrorUtil.getErrorFile(context), "loadScriptStartRunStatistics --> data=" + str3 + ",r=" + str4, null, true);
            }
            String string = MyConfig.getBuilderPay(InterfaceRelatedConstants.SCRIPT_OPERA_NAME, str2).build().toString();
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--loadScriptStartRunStatistics:" + string);
            this.f1688mA.sendPostRequest(context, getClass().getSimpleName(), string, mapPrames);
        } catch (Exception e) {
            Log.e("zzz", "com.cyjh.share.AppStatisticsPresenter--loadScriptStartRunStatistics:" + e.getMessage());
        }
    }

    public void loadScriptStopRunStatistics(Context context, String str) {
        try {
            ScriptStopRunParams scriptStopRunParams = new ScriptStopRunParams(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            scriptStopRunParams.RunGuid = str;
            this.f1688mA.sendPostRequest(context, getClass().getName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.SCRIPT_OPERA_NAME, InterfaceRelatedConstants.SCRIPT_STOP_RUN_NAME).build().toString(), VariableAndConstantsManager.getInstance().toMapPrames(scriptStopRunParams));
        } catch (Exception e) {
            Log.e("zzz", "AppStatisticsPresenter--loadScriptStopRunStatistics:" + e.getMessage());
            callbackScriptRunFail();
        }
    }

    public AppStatisticsPresenter setClickScriptRunCallback(OnClickScriptRunCallback onClickScriptRunCallback) {
        this.mClickScriptRunCallback = onClickScriptRunCallback;
        return this;
    }
}

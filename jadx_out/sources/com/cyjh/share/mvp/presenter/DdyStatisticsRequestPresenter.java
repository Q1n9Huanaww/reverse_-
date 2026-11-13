package com.cyjh.share.mvp.presenter;

import android.content.Context;
import com.cyjh.share.bean.request.DdyStatisticsData;
import com.cyjh.share.bean.request.DdyStatisticsRequestParamsInfo;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SlLog;
import java.net.URLEncoder;

/* JADX INFO: loaded from: classes.dex */
public class DdyStatisticsRequestPresenter extends AbstractHttpPresenter {
    private static final String TAG = "DdyStatisticsRequestPresenter";
    private static DdyStatisticsRequestPresenter sStatisticsPresenter;
    private OnResultCallback mOnResultCallback;

    public interface OnResultCallback {
        void onFailure(int i, String str);

        void onSuccess();
    }

    private DdyStatisticsRequestPresenter() {
    }

    public static DdyStatisticsRequestPresenter getInstance() {
        if (sStatisticsPresenter == null) {
            synchronized (DdyStatisticsRequestPresenter.class) {
                if (sStatisticsPresenter == null) {
                    sStatisticsPresenter = new DdyStatisticsRequestPresenter();
                }
            }
        }
        return sStatisticsPresenter;
    }

    public void report(Context context) {
        try {
            DdyStatisticsRequestParamsInfo ddyStatisticsRequestParamsInfo = new DdyStatisticsRequestParamsInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            ddyStatisticsRequestParamsInfo.IsDDYun = 1;
            ddyStatisticsRequestParamsInfo.IsChargeStatistics = 1;
            String strClass2Data = GsonExUtil.class2Data(new DdyStatisticsData(DdyStatisticsData.DAILY_STATISTICS, ddyStatisticsRequestParamsInfo));
            String str = InterfaceRelatedConstants.DDY_REPORT_URL + URLEncoder.encode(strClass2Data, "UTF-8");
            SlLog.m1334i(TAG, "report --> jsonResultStr=" + strClass2Data + "url=" + str);
            this.f1688mA.sendGetRequest(context, getClass().getCanonicalName(), str);
        } catch (Exception e) {
            SlLog.m1334i(TAG, "report --> ex=" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        SlLog.m1334i(TAG, "uiDataSuccess --> object=" + obj);
        if (this.mOnResultCallback != null) {
            this.mOnResultCallback.onSuccess();
        }
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

    public DdyStatisticsRequestPresenter setCallback(OnResultCallback onResultCallback) {
        this.mOnResultCallback = onResultCallback;
        return this;
    }
}

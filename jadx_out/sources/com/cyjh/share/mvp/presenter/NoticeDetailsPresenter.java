package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.util.Log;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.request.NoticeDetailsRequestParams;
import com.cyjh.share.bean.response.NoticeBean;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.mvp.view.NoticeDetailsView;
import com.cyjh.share.util.GsonExUtil;
import com.p024ds.daisi.net.URL.URLConstant;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NoticeDetailsPresenter extends AbstractHttpPresenter {
    private long clientTimestamp;
    private NoticeDetailsView mView;

    public NoticeDetailsPresenter(NoticeDetailsView noticeDetailsView) {
        this.mView = noticeDetailsView;
    }

    public void loadAd(Context context, long j) {
        try {
            BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(context);
            this.clientTimestamp = VariableAndConstantsManager.sCurrentTimestamp;
            NoticeDetailsRequestParams noticeDetailsRequestParams = new NoticeDetailsRequestParams(baseRequestParams);
            noticeDetailsRequestParams.NoticeId = j;
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.NOTICE_INTERFACE_NAME, "Detail").build().toString(), VariableAndConstantsManager.getInstance().toMapPrames(noticeDetailsRequestParams));
        } catch (Exception e) {
            Log.e("zzz", "com.cyjh.share.NoticeDetailsPresenter--loadAd:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        NoticeBean noticeBean;
        try {
            JSONObject jSONObject = new JSONObject((String) obj);
            JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
            if (jSONObject.getInt("Code") == 200 && (noticeBean = (NoticeBean) GsonExUtil.parsData(jSONObject2.toString(), NoticeBean.class)) != null && noticeBean.ClientTimestamp == this.clientTimestamp) {
                this.mView.onSuc(noticeBean);
            } else {
                this.mView.onFail();
            }
        } catch (Exception e) {
            Log.e("zzz", "com.cyjh.share.NoticeDetailsPresenter--uiDataSuccess:" + e.getMessage());
            this.mView.onFail();
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        Log.e("zzz", "com.cyjh.share.NoticeDetailsPresenter--onErrorResponse:" + str);
        this.mView.onFail();
    }

    public void onCancel() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
        }
    }
}

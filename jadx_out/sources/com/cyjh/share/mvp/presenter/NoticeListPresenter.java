package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.util.Log;
import com.cyjh.share.bean.NotifyMsgBean;
import com.cyjh.share.bean.request.BaseRequestValueInfo;
import com.cyjh.share.bean.response.NoticeBean;
import com.cyjh.share.bean.response.NoticeListBean;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.mvp.view.PushMessageView;
import com.cyjh.share.util.AppUtils;
import com.cyjh.share.util.GsonExUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.net.URL.URLConstant;
import java.util.ArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class NoticeListPresenter extends AbstractHttpPresenter {
    private long clientTimestamp;
    private PushMessageView mView;

    public NoticeListPresenter(PushMessageView pushMessageView) {
        this.mView = pushMessageView;
    }

    public void loadAd(Context context) {
        try {
            BaseRequestValueInfo baseRequestParams = VariableAndConstantsManager.getInstance().getBaseRequestParams(context);
            Log.e("zzz", "com.cyjh.share.NoticeListPresenter--loadAd:");
            VariableAndConstantsManager.getInstance().toMapPrames(baseRequestParams);
            this.clientTimestamp = VariableAndConstantsManager.sCurrentTimestamp;
            Log.e("zzz", "com.cyjh.share.NoticeListPresenter--loadAd:" + MyConfig.getBuilderPay(InterfaceRelatedConstants.NOTICE_INTERFACE_NAME, "List").build().toString());
        } catch (Exception e) {
            Log.e("zzz", "com.cyjh.share.NoticeListPresenter--loadAd:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        NoticeListBean noticeListBean;
        try {
            String str = (String) obj;
            Log.e("zzz", "com.cyjh.share.NoticeListPresenter--uiDataSuccess1:" + str);
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObject2 = jSONObject.getJSONObject(URLConstant.TOKEN_PARAMS_DATA);
            if (jSONObject.getInt("Code") != 200 || (noticeListBean = (NoticeListBean) GsonExUtil.parsData(jSONObject2.toString(), NoticeListBean.class)) == null || noticeListBean.NoticeList == null || noticeListBean.NoticeList.size() <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (NoticeBean noticeBean : noticeListBean.NoticeList) {
                NotifyMsgBean notifyMsgBean = new NotifyMsgBean();
                notifyMsgBean.f1679ID = String.valueOf(noticeBean.NoticeId);
                notifyMsgBean.NoticeTitle = noticeBean.NoticeTitle;
                notifyMsgBean.NoticeContent = noticeBean.NoticeContent;
                notifyMsgBean.NoticeTime = AppUtils.timeStamp2Date(noticeBean.NoticeTime, "yyyy-MM-dd HH:mm:ss");
                notifyMsgBean.DescriptionType = String.valueOf(noticeBean.DescriptionType);
                notifyMsgBean.NoticeDescription = noticeBean.NoticeDescription;
                boolean z = true;
                if (noticeBean.IsShowNoticeBar != 1) {
                    z = false;
                }
                notifyMsgBean.IsSendMessage = z;
                arrayList.add(notifyMsgBean);
            }
            this.mView.onSuccessfulAcquireNews(arrayList);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "com.cyjh.share.NoticeListPresenter--uiDataSuccess2:" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        Log.e("zzz", "com.cyjh.share.NoticeListPresenter--onErrorResponse:" + str);
    }

    public void onCancel() {
        if (this.f1688mA != null) {
            this.f1688mA.stopRequest(getClass().getCanonicalName());
        }
    }
}

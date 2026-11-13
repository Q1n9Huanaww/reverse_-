package com.cyjh.share.mvp.presenter;

import android.content.Context;
import android.util.Log;
import com.cyjh.share.bean.request.FeedbackRequestInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.mvp.view.FeedbackView;

/* JADX INFO: loaded from: classes.dex */
public class FeedbackCommitDataPresenter extends AbstractHttpPresenter {
    private FeedbackView mView;

    public FeedbackCommitDataPresenter(FeedbackView feedbackView) {
        this.mView = feedbackView;
    }

    public void commitFeedback(Context context, FeedbackRequestInfo feedbackRequestInfo) {
        try {
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.FEEDBACK_COMMIT_NAME, InterfaceRelatedConstants.FEEDBACK_COMMIT_PATH).build().toString(), VariableAndConstantsManager.getInstance().toMapPrames(feedbackRequestInfo));
        } catch (Exception e) {
            Log.e("zzz", "FeedbackCommitDataPresenter --异常：" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            this.mView.onSuc((String) obj);
        } catch (Exception e) {
            Log.e("zzz", "FeedbackCommitDataPresenter--uiDataSuccess:" + e.getMessage());
            this.mView.onFail(e.getMessage());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        Log.e("zzz", "FeedbackCommitDataPresenter--onErrorResponse:" + str);
        this.mView.onFail(str);
    }

    public void onCancel() {
        this.f1688mA.stopRequest(getClass().getCanonicalName());
    }
}

package com.p024ds.daisi.mvp.presenters.news;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.cyjh.share.bean.request.QueryRegCodeRequestInfo;
import com.cyjh.share.config.MyConfig;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.mvp.base.AbstractHttpPresenter;
import com.cyjh.share.util.GsonExUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.entity.RegCodeInfoResponse;
import com.p024ds.daisi.mvp.views.QueryRegCodeView;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class QueryRegCodeInfoPresenter extends AbstractHttpPresenter {
    private QueryRegCodeView mQueryRegCodeView;

    public QueryRegCodeInfoPresenter(QueryRegCodeView queryRegCodeView) {
        this.mQueryRegCodeView = queryRegCodeView;
    }

    public void queryRegCodeInfo(String str, String str2, Context context) {
        try {
            QueryRegCodeRequestInfo queryRegCodeRequestInfo = new QueryRegCodeRequestInfo(VariableAndConstantsManager.getInstance().getBaseRequestParams(context));
            queryRegCodeRequestInfo.RegCode = str;
            queryRegCodeRequestInfo.OldRegCode = str2;
            this.f1688mA.sendPostRequest(context, getClass().getCanonicalName(), MyConfig.getBuilderPay(InterfaceRelatedConstants.SCRIPT_OPERA_NAME, InterfaceRelatedConstants.QUERY_REG_CODE_INFO_NAME).build().toString(), VariableAndConstantsManager.getInstance().toMapPrames(queryRegCodeRequestInfo));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            Log.e("zzz", "QueryRegCodeInfoPresenter--queryRegCodeInfo--" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataSuccess(Object obj) {
        try {
            String str = (String) obj;
            if (!TextUtils.isEmpty(str)) {
                this.mQueryRegCodeView.onQuerySuc((RegCodeInfoResponse) GsonExUtil.parsData(str, RegCodeInfoResponse.class));
            } else {
                this.mQueryRegCodeView.onQueryFailure(AppContext.getInstance().getResources().getString(R.string.json_data_null));
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            this.mQueryRegCodeView.onQueryFailure("json解析抛异常：" + e.getMessage());
        }
    }

    @Override // com.cyjh.share.mvp.base.AbstractHttpPresenter
    protected void onErrorResponse(String str) {
        this.mQueryRegCodeView.onQueryFailure(str);
    }

    public void onCancel() {
        this.f1688mA.stopRequest(getClass().getCanonicalName());
    }
}

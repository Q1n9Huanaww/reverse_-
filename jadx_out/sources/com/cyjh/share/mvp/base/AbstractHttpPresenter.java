package com.cyjh.share.mvp.base;

import android.content.res.Resources;
import android.text.TextUtils;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.cyjh.share.C0854R;
import com.cyjh.share.bean.SignaClass;
import com.cyjh.share.bean.response.BaseInfo;
import com.cyjh.share.manager.VariableAndConstantsManager;
import com.cyjh.share.net.inf.IAnalysisJson;
import com.cyjh.share.net.inf.IUIDataListener;
import com.cyjh.share.net.volley.ActivityHttpHelper;
import com.cyjh.share.util.GsonExUtil;
import com.cyjh.share.util.SignUtil;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractHttpPresenter implements IAnalysisJson, IUIDataListener {

    /* JADX INFO: renamed from: mA */
    protected ActivityHttpHelper f1688mA = new ActivityHttpHelper(this, this);

    protected abstract void onErrorResponse(String str);

    protected AbstractHttpPresenter() {
    }

    @Override // com.cyjh.share.net.inf.IAnalysisJson
    public Object getData(String str) {
        try {
            BaseInfo baseInfo = (BaseInfo) GsonExUtil.parsData(str, BaseInfo.class);
            SignaClass signaClass = (SignaClass) GsonExUtil.parsData(SignUtil.decode(baseInfo.Data, baseInfo.f1681R), SignaClass.class);
            if (signaClass == null || !SignUtil.rsaVerify(signaClass.SignContent, signaClass.Signature)) {
                return null;
            }
            return signaClass.SignContent;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    @Override // com.cyjh.share.net.inf.IUIDataListener
    public void uiDataError(VolleyError volleyError) {
        String string;
        Resources resources = VariableAndConstantsManager.getInstance().getContext().getResources();
        if (resources != null) {
            if (volleyError instanceof ServerError) {
                string = resources.getString(C0854R.string.network_server_error);
            } else if (volleyError instanceof NoConnectionError) {
                string = resources.getString(C0854R.string.network_no_connection_error);
            } else if (volleyError instanceof NetworkError) {
                string = resources.getString(C0854R.string.network_network_error);
            } else if (volleyError instanceof ParseError) {
                string = resources.getString(C0854R.string.network_parse_error);
            } else if (volleyError instanceof AuthFailureError) {
                string = resources.getString(C0854R.string.network_auth_failure_error);
            } else {
                string = volleyError instanceof TimeoutError ? resources.getString(C0854R.string.network_timeout_error) : null;
            }
            if (volleyError.networkResponse != null) {
                if (!TextUtils.isEmpty(volleyError.networkResponse.statusCode + "")) {
                    string = volleyError.networkResponse.statusCode + "\t\t" + string;
                }
            }
            if (string == null) {
                string = resources.getString(C0854R.string.network_again_error);
            }
            onErrorResponse(string);
        }
    }
}

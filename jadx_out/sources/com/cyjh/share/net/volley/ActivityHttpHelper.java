package com.cyjh.share.net.volley;

import android.content.Context;
import com.android.volley.VolleyError;
import com.cyjh.share.net.inf.IAnalysisJson;
import com.cyjh.share.net.inf.IUIDataListener;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ActivityHttpHelper extends BaseVollerHelper {
    private IAnalysisJson mAnalysisJson;
    private IUIDataListener mDataListener;

    public ActivityHttpHelper(IUIDataListener iUIDataListener) {
        this.mDataListener = iUIDataListener;
    }

    public ActivityHttpHelper(IUIDataListener iUIDataListener, IAnalysisJson iAnalysisJson) {
        this.mDataListener = iUIDataListener;
        this.mAnalysisJson = iAnalysisJson;
    }

    @Override // com.android.volley.Response.Listener
    public void onResponse(Object obj) {
        if (this.mDataListener != null) {
            this.mDataListener.uiDataSuccess(obj);
        }
    }

    @Override // com.android.volley.Response.ErrorListener
    public void onErrorResponse(VolleyError volleyError) {
        if (this.mDataListener != null) {
            this.mDataListener.uiDataError(volleyError);
        }
    }

    public void sendGetRequest(Object obj, String str) {
        sendGetRequest(null, obj, str);
    }

    public void sendGetRequest(Context context, Object obj, String str) {
        super.sendGetRequest(context, obj, str, this.mAnalysisJson);
    }

    public void sendGetRequest(Context context, String str) {
        sendGetRequest(context, this, str);
    }

    public void sendPostRequest(Object obj, String str, Map<String, String> map) {
        sendPostRequest(null, obj, str, map);
    }

    public void sendPostRequest(Context context, Object obj, String str, Map<String, String> map) {
        super.sendPostRequest(context, obj, str, map, this.mAnalysisJson);
    }

    public void sendPostRequest(Context context, String str, Map<String, String> map) {
        sendPostRequest(context, this, str, map);
    }

    public void stopRequest() {
        stopRequest(this);
    }

    public void stopRequest(Object obj) {
        VollerHttpManager.getInstance().stopRequest(obj);
    }
}

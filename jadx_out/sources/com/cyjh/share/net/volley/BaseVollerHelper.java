package com.cyjh.share.net.volley;

import android.content.Context;
import com.android.volley.Response;
import com.cyjh.share.net.inf.IAnalysisJson;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseVollerHelper implements Response.Listener<Object>, Response.ErrorListener {
    public VollerRequest getRequestForGet(String str, IAnalysisJson iAnalysisJson) {
        return new VollerRequest(str, this, this, iAnalysisJson);
    }

    public VollerRequest getRequestForGPost(String str, Map<String, String> map, IAnalysisJson iAnalysisJson) {
        return new VollerRequest(str, map, this, this, iAnalysisJson);
    }

    public void sendGetRequest(Context context, Object obj, String str, IAnalysisJson iAnalysisJson) {
        VollerHttpManager.getInstance().addRequest(context, obj, getRequestForGet(str, iAnalysisJson));
    }

    public void sendPostRequest(Context context, Object obj, String str, Map<String, String> map, IAnalysisJson iAnalysisJson) {
        VollerHttpManager.getInstance().addRequest(context, obj, getRequestForGPost(str, map, iAnalysisJson));
    }
}

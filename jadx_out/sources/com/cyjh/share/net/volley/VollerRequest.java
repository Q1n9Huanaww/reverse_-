package com.cyjh.share.net.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.cyjh.share.net.inf.IAnalysisJson;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class VollerRequest extends Request<Object> {
    private static final int TIME_OUT = 50000;
    private IAnalysisJson mAnalysisJson;
    private Response.Listener<Object> mListener;
    private Map<String, String> map;

    public VollerRequest(int i, String str, Map<String, String> map, Response.Listener<Object> listener, Response.ErrorListener errorListener) {
        super(i, str, errorListener);
        this.mListener = listener;
        this.map = map;
        setRetryPolicy(new DefaultRetryPolicy(TIME_OUT, 0, 1.0f));
        setShouldCache(false);
    }

    public VollerRequest(String str, Response.Listener<Object> listener, Response.ErrorListener errorListener, IAnalysisJson iAnalysisJson) {
        this(0, str, (Map<String, String>) null, listener, errorListener);
        this.mAnalysisJson = iAnalysisJson;
    }

    public VollerRequest(String str, Map<String, String> map, Response.Listener<Object> listener, Response.ErrorListener errorListener, IAnalysisJson iAnalysisJson) {
        this(1, str, map, listener, errorListener);
        this.mAnalysisJson = iAnalysisJson;
    }

    @Override // com.android.volley.Request
    protected Map<String, String> getParams() throws AuthFailureError {
        return this.map;
    }

    @Override // com.android.volley.Request
    public Request.Priority getPriority() {
        return Request.Priority.HIGH;
    }

    @Override // com.android.volley.Request
    protected Response<Object> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String str = new String(networkResponse.data, "UTF-8");
            if (this.mAnalysisJson != null) {
                return Response.success(this.mAnalysisJson.getData(str), HttpHeaderParser.parseCacheHeaders(networkResponse));
            }
            return null;
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override // com.android.volley.Request
    protected void deliverResponse(Object obj) {
        if (this.mListener != null) {
            this.mListener.onResponse(obj);
        }
    }
}

package com.p024ds.daisi.net.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class VolleyManager {
    private static final int DEFAULT_REQUEST_TIMEOUT = 3000;
    private static final String TAG = VolleyManager.class.getCanonicalName();
    private static JsonObjectRequest mJsonRequest;
    private static StringRequest mStringRequest;

    public static void requestStringGet(String str, String str2, VolleyStrRequestManager volleyStrRequestManager) {
        VolleyRequestQueue.getRequestQueue().cancelAll(str2);
        mStringRequest = new StringRequest(0, str, volleyStrRequestManager.mVolleySuccessListener, volleyStrRequestManager.mVolleyErrorListener);
        mStringRequest.setTag(str2);
        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1.0f));
        mStringRequest.setShouldCache(false);
        VolleyRequestQueue.getRequestQueue().add(mStringRequest);
    }

    public static void cancelAllReq() {
        VolleyRequestQueue.getRequestQueue().cancelAll(TAG);
    }

    public static void requestStringPost(String str, String str2, final Map<String, String> map, VolleyStrRequestManager volleyStrRequestManager) {
        VolleyRequestQueue.getRequestQueue().cancelAll(str2);
        mStringRequest = new StringRequest(1, str, volleyStrRequestManager.mVolleySuccessListener, volleyStrRequestManager.mVolleyErrorListener) { // from class: com.ds.daisi.net.volley.VolleyManager.1
            @Override // com.android.volley.Request
            protected Map<String, String> getParams() throws AuthFailureError {
                if (map != null) {
                    return map;
                }
                return super.getParams();
            }
        };
        mStringRequest.setTag(str2);
        mStringRequest.setShouldCache(false);
        VolleyRequestQueue.getRequestQueue().add(mStringRequest);
    }

    public static void requestJsonObjectPost(String str, String str2, JSONObject jSONObject, VolleyJsonRequestManager volleyJsonRequestManager) {
        mJsonRequest = new JsonObjectRequest(1, str, jSONObject, volleyJsonRequestManager.mJsonSuccessListener, volleyJsonRequestManager.mVolleyErrorListener);
        mJsonRequest.setTag(str2);
        mJsonRequest.setRetryPolicy(new DefaultRetryPolicy(3000, 1, 1.0f));
        mJsonRequest.setShouldCache(false);
        VolleyRequestQueue.getRequestQueue().add(mJsonRequest);
    }
}

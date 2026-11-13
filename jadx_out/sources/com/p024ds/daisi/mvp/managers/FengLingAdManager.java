package com.p024ds.daisi.mvp.managers;

import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.webkit.WebView;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cyjh.share.bean.response.FengLingAdInfo;
import com.cyjh.share.bean.response.FengLingAdInfo.Imgs;
import com.cyjh.share.bean.response.FengLingAdInfo.WebTracks;
import com.cyjh.share.util.CommonUtil;
import com.cyjh.share.util.CommonUtils;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import com.p024ds.daisi.net.URL.URLConstant;
import com.p024ds.daisi.util.AppDeviceUtils;
import com.p024ds.daisi.util.GPSUtils;
import com.p024ds.daisi.util.NetworkUtils;
import com.umeng.commonsdk.proguard.C1799v;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class FengLingAdManager implements BaseManager {
    public static final int BANNER_AD = 1;
    public static final String CLICK_DOWN_X = "__CLICK_DOWN_X__";
    public static final String CLICK_DOWN_Y = "_CLICK_DOWN_Y__";
    public static final String CLICK_UP_X = "__CLICK_UP_X__";
    public static final String CLICK_UP_Y = "__CLICK_UP_Y__";
    public static final String EVENT_TIME = "__EVENT_TIME__";
    public static String PUBLIC_INTERNET_IP = "0.0.0.0";
    public static final int SPLASH_AD = 2;
    private static final String TAG = "FengLingAdManager";
    private Callback<FengLingAdInfo> callback;
    private Context context;
    private int type;
    private String url;
    private String webViewUserAgent;

    private String getOrientation() {
        return "1";
    }

    private String isSupportDeeplink() {
        return "false";
    }

    private String isUseHttps() {
        return "true";
    }

    public void getFengLingAdInfo(String str, int i, Callback<FengLingAdInfo> callback, Context context, String str2) {
        this.type = i;
        this.callback = callback;
        this.context = context;
        this.url = str2;
        this.webViewUserAgent = CommonUtils.getWebViewUserAgent(context);
        getFengLingAdInfoOperate(PUBLIC_INTERNET_IP);
    }

    public void reportLogInfo(Context context, String str, final Callback<FengLingAdInfo> callback) {
        this.callback = callback;
        Log.d(TAG, "reportLogInfo params -> " + str);
        Volley.newRequestQueue(context).add(new StringRequest(0, str, new Response.Listener<String>() { // from class: com.ds.daisi.mvp.managers.FengLingAdManager.1
            @Override // com.android.volley.Response.Listener
            public void onResponse(String str2) {
                Log.d(FengLingAdManager.TAG, "onResponse -> " + str2.toString());
                try {
                    if ("OK".equals(str2)) {
                        callback.finish(new FengLingAdInfo());
                    } else {
                        callback.error("请求数据失败！");
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    callback.error("解析数据失败！");
                }
            }
        }, new Response.ErrorListener() { // from class: com.ds.daisi.mvp.managers.FengLingAdManager.2
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(FengLingAdManager.TAG, volleyError.getMessage(), volleyError);
                callback.error(volleyError.getMessage());
            }
        }) { // from class: com.ds.daisi.mvp.managers.FengLingAdManager.3
            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() {
                HashMap map = new HashMap();
                map.put("Charset", "UTF-8");
                map.put("Content-Type", "application/x-javascript");
                map.put(HttpRequest.HEADER_ACCEPT_ENCODING, "gzip,deflate");
                map.put("User-Agent", FengLingAdManager.this.webViewUserAgent);
                return map;
            }
        });
    }

    public Map<String, Object> getFengLingMapParams(Context context, int i, String str) {
        String str2;
        int i2;
        int i3;
        if (i == 2) {
            str2 = Constants.FENG_LING_AD_SPLASH_ID;
            i2 = 640;
            i3 = 960;
        } else {
            str2 = Constants.FENG_LING_AD_BANNER_ID;
            i2 = 600;
            i3 = 300;
        }
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        map2.put("id", Constants.FENG_LING_AD_APP_ID);
        HashMap map3 = new HashMap();
        map3.put("adPid", str2);
        HashMap map4 = new HashMap();
        map4.put("width", Integer.valueOf(i2));
        map4.put("height", Integer.valueOf(i3));
        map3.put("banner", map4);
        map3.put("usingHttps", isUseHttps());
        map3.put("isSupportDeeplink", isSupportDeeplink());
        HashMap map5 = new HashMap();
        map5.put("imei", AppDeviceUtils.getDeviceId(context));
        map5.put("androidId", AppDeviceUtils.getAndroidId(context));
        map5.put("ip", str);
        map5.put("nt", String.valueOf(NetworkUtils.getNetWorkTypeIntValue(context)));
        map5.put("platform", AccountManager.TYPE_GET_PHONE);
        map5.put("lan", "zh_CN");
        map5.put("rslt", getDeviceDisplalyParam(context));
        map5.put("osv", AppDeviceUtils.getDeviceVersionName(context));
        map5.put(C1799v.f4272K, AppDeviceUtils.getOperateValue(context));
        map5.put("sendtime", String.valueOf(System.currentTimeMillis()));
        map5.put(C1799v.f4269H, "480");
        map5.put("mac", AppDeviceUtils.getDeviceMac(context));
        map5.put("appVer", AppDeviceUtils.getAppNumber(context));
        map5.put("cc", "CN");
        map5.put("orientation", getOrientation());
        map5.put("imsi", getImsi(context));
        map5.put("brand", getBrand());
        map5.put("dm", getDM());
        map5.put("ua", this.webViewUserAgent);
        HashMap map6 = new HashMap();
        String[] lngAndLatJudgeOperate = GPSUtils.getInstance(context).getLngAndLatJudgeOperate(null);
        map6.put("lon", lngAndLatJudgeOperate[0]);
        map6.put("lat", lngAndLatJudgeOperate[1]);
        map5.put("geo", map6);
        map.put("site", map2);
        map.put("imp", map3);
        map.put("device", map5);
        return map;
    }

    private String getDeviceDisplalyParam(Context context) {
        return AppDeviceUtils.getResolution(context).x + Marker.ANY_MARKER + AppDeviceUtils.getResolution(context).y;
    }

    private String getWebViewUserAgent(Context context) {
        try {
            return new WebView(context).getSettings().getUserAgentString();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    private String getImsi(Context context) {
        return ActivityCompat.checkSelfPermission(context, "android.permission.READ_PHONE_STATE") != 0 ? "" : ((TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT)).getSubscriberId();
    }

    private String getBrand() {
        try {
            return Build.BRAND;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    private String getDM() {
        try {
            return Build.MODEL;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return "";
        }
    }

    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    private void getFengLingAdInfoOperate(String str) {
        RequestQueue requestQueueNewRequestQueue = Volley.newRequestQueue(this.context);
        JSONObject jSONObject = new JSONObject(getFengLingMapParams(this.context, this.type, str));
        Log.d(TAG, "getFengLingAdInfo params -> " + jSONObject.toString());
        requestQueueNewRequestQueue.add(new JsonObjectRequest(1, this.url, jSONObject, new Response.Listener<JSONObject>() { // from class: com.ds.daisi.mvp.managers.FengLingAdManager.4
            @Override // com.android.volley.Response.Listener
            public void onResponse(JSONObject jSONObject2) {
                Log.d(FengLingAdManager.TAG, "onResponse -> " + jSONObject2.toString());
                try {
                    if (jSONObject2.getInt("code") != 0) {
                        FengLingAdManager.this.callback.error("请求数据失败！");
                        return;
                    }
                    JSONArray jSONArray = jSONObject2.getJSONArray("ads");
                    FengLingAdInfo fengLingAdInfo = new FengLingAdInfo();
                    if (jSONArray.length() <= 0) {
                        FengLingAdManager.this.callback.error("无有效广告数据！");
                        return;
                    }
                    JSONObject jSONObject3 = (JSONObject) jSONArray.get(0);
                    fengLingAdInfo.setCreativeType(jSONObject3.getInt("creativeType"));
                    fengLingAdInfo.setDrawType(jSONObject3.getInt("drawType"));
                    fengLingAdInfo.setActionName(jSONObject3.getInt("actionName"));
                    fengLingAdInfo.setWidth(jSONObject3.getInt("width"));
                    fengLingAdInfo.setHeight(jSONObject3.getInt("height"));
                    fengLingAdInfo.setTitle(jSONObject3.getString("title"));
                    fengLingAdInfo.setRating(jSONObject3.getInt("rating"));
                    JSONObject jSONObject4 = (JSONObject) jSONObject3.getJSONArray("imgs").get(0);
                    fengLingAdInfo.getClass();
                    FengLingAdInfo.Imgs imgs = fengLingAdInfo.new Imgs();
                    imgs.setHeight(jSONObject4.getInt("height"));
                    imgs.setWidth(jSONObject4.getInt("width"));
                    imgs.setSrc(jSONObject4.getString("src"));
                    fengLingAdInfo.setImgs(imgs);
                    fengLingAdInfo.setAdPlatformIcon(jSONObject3.getString("adPlatformIcon"));
                    fengLingAdInfo.setAdId(jSONObject3.getInt("adId"));
                    fengLingAdInfo.setClickUrl(jSONObject3.getString("clickUrl"));
                    JSONObject jSONObject5 = jSONObject3.getJSONObject("webTracks");
                    fengLingAdInfo.getClass();
                    FengLingAdInfo.WebTracks webTracks = fengLingAdInfo.new WebTracks();
                    webTracks.setType(jSONObject5.getInt("type"));
                    JSONArray jSONArray2 = jSONObject5.getJSONArray("imptrackUrls");
                    if (jSONArray2.length() > 0) {
                        webTracks.setImptrackUrls(CommonUtil.parseJsonArray(jSONArray2));
                    }
                    JSONArray jSONArray3 = jSONObject5.getJSONArray("ctrackUrls");
                    if (jSONArray3.length() > 0) {
                        webTracks.setCtrackUrls(CommonUtil.parseJsonArray(jSONArray3));
                    }
                    JSONArray jSONArray4 = jSONObject5.getJSONArray("dstrackUrls");
                    if (jSONArray4.length() > 0) {
                        webTracks.setDstrackUrls(CommonUtil.parseJsonArray(jSONArray4));
                    }
                    JSONArray jSONArray5 = jSONObject5.getJSONArray("istrackUrls");
                    if (jSONArray5.length() > 0) {
                        webTracks.setIstrackUrls(CommonUtil.parseJsonArray(jSONArray5));
                    }
                    fengLingAdInfo.setWebTracks(webTracks);
                    fengLingAdInfo.setAdPlatformId(jSONObject3.getInt("adPlatformId"));
                    FengLingAdManager.this.callback.finish(fengLingAdInfo);
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                    FengLingAdManager.this.callback.error("解析数据失败！");
                }
            }
        }, new Response.ErrorListener() { // from class: com.ds.daisi.mvp.managers.FengLingAdManager.5
            @Override // com.android.volley.Response.ErrorListener
            public void onErrorResponse(VolleyError volleyError) {
                Log.e(FengLingAdManager.TAG, volleyError.getMessage(), volleyError);
                FengLingAdManager.this.callback.error(volleyError.getMessage());
            }
        }) { // from class: com.ds.daisi.mvp.managers.FengLingAdManager.6
            @Override // com.android.volley.Request
            public Map<String, String> getHeaders() {
                HashMap map = new HashMap();
                map.put(HttpRequest.HEADER_ACCEPT, "application/json");
                map.put("Content-Type", "application/json; charset=UTF-8");
                map.put("User-Agent", FengLingAdManager.this.webViewUserAgent);
                return map;
            }
        });
    }
}

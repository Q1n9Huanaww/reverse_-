package com.p024ds.daisi.log.engine;

import android.content.Context;
import android.util.Log;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.p024ds.daisi.net.URL.URLConstant;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseLog {
    protected static final String BASE_URL = "http://api.mobileanjian.com/api";
    protected Context mContext;
    protected String urlApi;

    protected abstract String generateLog();

    protected abstract void initUrl();

    public BaseLog(Context context) {
        this.mContext = context;
    }

    public void commit() {
        initUrl();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        String strGenerateLog = generateLog();
        Log.e("zzz", "BaseLopg--jsonLog--" + strGenerateLog);
        requestParams.addQueryStringParameter(URLConstant.TOKEN_PARAMS_DATA, strGenerateLog);
        httpUtils.send(HttpRequest.HttpMethod.GET, this.urlApi, requestParams, null);
    }
}

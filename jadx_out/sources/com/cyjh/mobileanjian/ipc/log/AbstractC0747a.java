package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.client.HttpRequest;
import com.p024ds.daisi.net.URL.URLConstant;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.log.a */
/* JADX INFO: compiled from: BaseLog.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC0747a {

    /* JADX INFO: renamed from: a */
    protected static final String f1023a = "http://api.mobileanjian.com/api";

    /* JADX INFO: renamed from: b */
    protected Context f1024b;

    /* JADX INFO: renamed from: c */
    protected String f1025c;

    /* JADX INFO: renamed from: a */
    protected abstract void mo744a();

    /* JADX INFO: renamed from: b */
    protected abstract String mo745b();

    public AbstractC0747a(Context context) {
        this.f1024b = context;
    }

    public void commit() {
        mo744a();
        HttpUtils httpUtils = new HttpUtils();
        RequestParams requestParams = new RequestParams();
        requestParams.addQueryStringParameter(URLConstant.TOKEN_PARAMS_DATA, mo745b());
        httpUtils.send(HttpRequest.HttpMethod.GET, this.f1025c, requestParams, null);
    }
}

package com.androidquery.callback;

import java.net.Proxy;
import org.apache.http.HttpRequest;
import org.apache.http.impl.client.DefaultHttpClient;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProxyHandle {
    public abstract void applyProxy(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpRequest httpRequest, DefaultHttpClient defaultHttpClient);

    public abstract Proxy makeProxy(AbstractAjaxCallback<?, ?> abstractAjaxCallback);
}

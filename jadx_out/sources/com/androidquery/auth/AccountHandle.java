package com.androidquery.auth;

import android.content.Context;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import java.net.HttpURLConnection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import org.apache.http.HttpRequest;

/* JADX INFO: loaded from: classes.dex */
public abstract class AccountHandle {
    private LinkedHashSet<AbstractAjaxCallback<?, ?>> callbacks;

    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpURLConnection httpURLConnection) {
    }

    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpRequest httpRequest) {
    }

    protected abstract void auth();

    public abstract boolean authenticated();

    public abstract boolean expired(AbstractAjaxCallback<?, ?> abstractAjaxCallback, AjaxStatus ajaxStatus);

    public String getCacheUrl(String str) {
        return str;
    }

    public String getNetworkUrl(String str) {
        return str;
    }

    public abstract boolean reauth(AbstractAjaxCallback<?, ?> abstractAjaxCallback);

    public void unauth() {
    }

    public synchronized void auth(AbstractAjaxCallback<?, ?> abstractAjaxCallback) {
        if (this.callbacks == null) {
            this.callbacks = new LinkedHashSet<>();
            this.callbacks.add(abstractAjaxCallback);
            auth();
        } else {
            this.callbacks.add(abstractAjaxCallback);
        }
    }

    protected synchronized void success(Context context) {
        if (this.callbacks != null) {
            Iterator<AbstractAjaxCallback<?, ?>> it2 = this.callbacks.iterator();
            while (it2.hasNext()) {
                it2.next().async(context);
            }
            this.callbacks = null;
        }
    }

    protected synchronized void failure(Context context, int i, String str) {
        if (this.callbacks != null) {
            Iterator<AbstractAjaxCallback<?, ?>> it2 = this.callbacks.iterator();
            while (it2.hasNext()) {
                it2.next().failure(i, str);
            }
            this.callbacks = null;
        }
    }
}

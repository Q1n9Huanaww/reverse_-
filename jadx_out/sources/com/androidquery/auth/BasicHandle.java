package com.androidquery.auth;

import android.net.Uri;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.androidquery.callback.AbstractAjaxCallback;
import com.androidquery.callback.AjaxStatus;
import com.androidquery.util.AQUtility;
import java.net.HttpURLConnection;
import org.apache.http.HttpRequest;

/* JADX INFO: loaded from: classes.dex */
public class BasicHandle extends AccountHandle {
    private String password;
    private String username;

    @Override // com.androidquery.auth.AccountHandle
    protected void auth() {
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean authenticated() {
        return true;
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean expired(AbstractAjaxCallback<?, ?> abstractAjaxCallback, AjaxStatus ajaxStatus) {
        return false;
    }

    @Override // com.androidquery.auth.AccountHandle
    public boolean reauth(AbstractAjaxCallback<?, ?> abstractAjaxCallback) {
        return false;
    }

    public BasicHandle(String str, String str2) {
        this.username = str;
        this.password = str2;
    }

    @Override // com.androidquery.auth.AccountHandle
    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpRequest httpRequest) {
        byte[] bytes = (String.valueOf(this.username) + ":" + this.password).getBytes();
        StringBuilder sb = new StringBuilder("Basic ");
        sb.append(new String(AQUtility.encode64(bytes, 0, bytes.length)));
        String string = sb.toString();
        httpRequest.addHeader(HttpHeaders.HOST, Uri.parse(abstractAjaxCallback.getUrl()).getHost());
        httpRequest.addHeader("Authorization", string);
    }

    @Override // com.androidquery.auth.AccountHandle
    public void applyToken(AbstractAjaxCallback<?, ?> abstractAjaxCallback, HttpURLConnection httpURLConnection) {
        byte[] bytes = (String.valueOf(this.username) + ":" + this.password).getBytes();
        StringBuilder sb = new StringBuilder("Basic ");
        sb.append(new String(AQUtility.encode64(bytes, 0, bytes.length)));
        String string = sb.toString();
        httpURLConnection.setRequestProperty(HttpHeaders.HOST, Uri.parse(abstractAjaxCallback.getUrl()).getHost());
        httpURLConnection.setRequestProperty("Authorization", string);
    }
}

package com.p024ds.daisi.net.xutils;

import android.content.Context;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.util.PreferencesCookieStore;
import com.p024ds.daisi.constant.Constants;
import java.net.URISyntaxException;
import org.apache.http.client.utils.URIUtils;

/* JADX INFO: loaded from: classes.dex */
public class XUtilsHttpClient {
    private static final int DEFAULT_CONNECTION_TIMEOUT = 10000;
    private static final int DEFAULT_SOCKET_TIMEOUT = 10000;
    public static final String PROTOCOL_HTTP = "http";
    private static HttpUtils httpUtils;

    public static synchronized HttpUtils getInstence(Context context) {
        if (httpUtils == null) {
            httpUtils = new HttpUtils(Constants.REQUEST_CODE_APP_INSTALL);
            httpUtils.configSoTimeout(Constants.REQUEST_CODE_APP_INSTALL);
            httpUtils.configResponseTextCharset("UTF-8");
            PreferencesCookieStore preferencesCookieStore = new PreferencesCookieStore(context);
            preferencesCookieStore.clear();
            httpUtils.configCookieStore(preferencesCookieStore);
        }
        return httpUtils;
    }

    public static String getURL(String str, String str2, int i, String str3) {
        try {
            return URIUtils.createURI(str, str2, i, str3, null, null).toString();
        } catch (URISyntaxException e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        } catch (Exception e2) {
            ThrowableExtension.printStackTrace(e2);
            return null;
        }
    }
}

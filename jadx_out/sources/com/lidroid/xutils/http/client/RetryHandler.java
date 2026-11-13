package com.lidroid.xutils.http.client;

import android.os.SystemClock;
import com.lidroid.xutils.util.LogUtils;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes.dex */
public class RetryHandler implements HttpRequestRetryHandler {
    private static final int RETRY_SLEEP_INTERVAL = 500;
    private final int maxRetries;
    private static HashSet<Class<?>> exceptionWhiteList = new HashSet<>();
    private static HashSet<Class<?>> exceptionBlackList = new HashSet<>();

    static {
        exceptionWhiteList.add(NoHttpResponseException.class);
        exceptionWhiteList.add(UnknownHostException.class);
        exceptionWhiteList.add(SocketException.class);
        exceptionBlackList.add(InterruptedIOException.class);
        exceptionBlackList.add(SSLHandshakeException.class);
    }

    public RetryHandler(int i) {
        this.maxRetries = i;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x006f  */
    @Override // org.apache.http.client.HttpRequestRetryHandler
    public boolean retryRequest(IOException iOException, int i, HttpContext httpContext) {
        boolean zEquals;
        if (iOException == null || httpContext == null) {
            return false;
        }
        Object attribute = httpContext.getAttribute("http.request_sent");
        if (attribute != null) {
            ((Boolean) attribute).booleanValue();
        }
        boolean z = true;
        if (i <= this.maxRetries && !exceptionBlackList.contains(iOException.getClass())) {
            exceptionWhiteList.contains(iOException.getClass());
        } else {
            z = false;
        }
        if (z) {
            try {
                Object attribute2 = httpContext.getAttribute("http.request");
                if (attribute2 != null) {
                    if (attribute2 instanceof HttpRequestBase) {
                        zEquals = "GET".equals(((HttpRequestBase) attribute2).getMethod());
                    } else if (attribute2 instanceof RequestWrapper) {
                        zEquals = "GET".equals(((RequestWrapper) attribute2).getMethod());
                    } else {
                        zEquals = z;
                    }
                } else {
                    LogUtils.m3420e("retry error, curr request is null");
                    zEquals = false;
                }
            } catch (Throwable th) {
                LogUtils.m3421e("retry error", th);
            }
        } else {
            zEquals = z;
        }
        if (zEquals) {
            SystemClock.sleep(500L);
        }
        return zEquals;
    }
}

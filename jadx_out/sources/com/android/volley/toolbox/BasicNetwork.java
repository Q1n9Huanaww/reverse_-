package com.android.volley.toolbox;

import android.os.SystemClock;
import com.android.volley.AuthFailureError;
import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RetryPolicy;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.cookie.DateUtils;

/* JADX INFO: loaded from: classes.dex */
public class BasicNetwork implements Network {
    protected static final boolean DEBUG = VolleyLog.DEBUG;
    private static int DEFAULT_POOL_SIZE = 4096;
    private static int SLOW_REQUEST_THRESHOLD_MS = 3000;
    protected final HttpStack mHttpStack;
    protected final ByteArrayPool mPool;

    public BasicNetwork(HttpStack httpStack) {
        this(httpStack, new ByteArrayPool(DEFAULT_POOL_SIZE));
    }

    public BasicNetwork(HttpStack httpStack, ByteArrayPool byteArrayPool) {
        this.mHttpStack = httpStack;
        this.mPool = byteArrayPool;
    }

    /* JADX WARN: Code duplicated, block: B:46:0x00e0  */
    /* JADX WARN: Code duplicated, block: B:48:0x00ff  */
    /* JADX WARN: Code duplicated, block: B:50:0x0112  */
    /* JADX WARN: Code duplicated, block: B:79:0x012f A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:80:0x0129 A[SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v2, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r17v6 */
    /* JADX WARN: Type inference failed for: r17v7 */
    /* JADX WARN: Type inference failed for: r17v8 */
    /* JADX WARN: Type inference failed for: r1v29, types: [java.util.Map] */
    /* JADX WARN: Type inference failed for: r23v0, types: [com.android.volley.toolbox.BasicNetwork] */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14, types: [long] */
    @Override // com.android.volley.Network
    public NetworkResponse performRequest(Request<?> request) throws Throwable {
        HttpResponse httpResponsePerformRequest;
        byte[] bArr;
        IOException iOException;
        ?? r17;
        int statusCode;
        NetworkResponse networkResponse;
        byte[] bArrEntityToBytes;
        ?? ElapsedRealtime;
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        while (true) {
            Map mapEmptyMap = Collections.emptyMap();
            try {
                try {
                    HashMap map = new HashMap();
                    addCacheHeaders(map, request.getCacheEntry());
                    httpResponsePerformRequest = this.mHttpStack.performRequest(request, map);
                    try {
                        StatusLine statusLine = httpResponsePerformRequest.getStatusLine();
                        int statusCode2 = statusLine.getStatusCode();
                        Map<String, String> mapConvertHeaders = convertHeaders(httpResponsePerformRequest.getAllHeaders());
                        if (statusCode2 == 304) {
                            Cache.Entry cacheEntry = request.getCacheEntry();
                            if (cacheEntry == null) {
                                return new NetworkResponse(UiMessage.CommandToUi.Command_Type.SET_TEXT_VALUE, null, mapConvertHeaders, true, SystemClock.elapsedRealtime() - jElapsedRealtime);
                            }
                            cacheEntry.responseHeaders.putAll(mapConvertHeaders);
                            return new NetworkResponse(UiMessage.CommandToUi.Command_Type.SET_TEXT_VALUE, cacheEntry.data, cacheEntry.responseHeaders, true, SystemClock.elapsedRealtime() - jElapsedRealtime);
                        }
                        try {
                            if (httpResponsePerformRequest.getEntity() != null) {
                                try {
                                    bArrEntityToBytes = entityToBytes(httpResponsePerformRequest.getEntity());
                                } catch (IOException e) {
                                    iOException = e;
                                    bArr = null;
                                    r17 = mapConvertHeaders;
                                    if (httpResponsePerformRequest != null) {
                                        statusCode = httpResponsePerformRequest.getStatusLine().getStatusCode();
                                        VolleyLog.m16e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
                                        if (bArr != null) {
                                            networkResponse = new NetworkResponse(statusCode, bArr, r17, false, SystemClock.elapsedRealtime() - jElapsedRealtime);
                                            if (statusCode != 401) {
                                            }
                                            attemptRetryOnException("auth", request, new AuthFailureError(networkResponse));
                                        } else {
                                            throw new NetworkError((NetworkResponse) null);
                                        }
                                    } else {
                                        throw new NoConnectionError(iOException);
                                    }
                                }
                            } else {
                                bArrEntityToBytes = new byte[0];
                            }
                            byte[] bArr2 = bArrEntityToBytes;
                            try {
                                ElapsedRealtime = SystemClock.elapsedRealtime() - jElapsedRealtime;
                                logSlowRequests(ElapsedRealtime, request, bArr2, statusLine);
                                try {
                                    if (statusCode2 >= 200 && statusCode2 <= 299) {
                                        return new NetworkResponse(statusCode2, bArr2, mapConvertHeaders, false, SystemClock.elapsedRealtime() - jElapsedRealtime);
                                    }
                                    throw new IOException();
                                } catch (IOException e2) {
                                    e = e2;
                                    iOException = e;
                                    r17 = ElapsedRealtime;
                                    bArr = bArr2;
                                    if (httpResponsePerformRequest != null) {
                                        statusCode = httpResponsePerformRequest.getStatusLine().getStatusCode();
                                        VolleyLog.m16e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
                                        if (bArr != null) {
                                            networkResponse = new NetworkResponse(statusCode, bArr, r17, false, SystemClock.elapsedRealtime() - jElapsedRealtime);
                                            if (statusCode != 401) {
                                            }
                                            attemptRetryOnException("auth", request, new AuthFailureError(networkResponse));
                                        } else {
                                            throw new NetworkError((NetworkResponse) null);
                                        }
                                    } else {
                                        throw new NoConnectionError(iOException);
                                    }
                                }
                            } catch (IOException e3) {
                                e = e3;
                                ElapsedRealtime = mapConvertHeaders;
                            }
                        } catch (IOException e4) {
                            iOException = e4;
                            r17 = mapConvertHeaders;
                            bArr = null;
                        }
                    } catch (IOException e5) {
                        e = e5;
                        bArr = null;
                        iOException = e;
                        r17 = mapEmptyMap;
                        if (httpResponsePerformRequest != null) {
                            statusCode = httpResponsePerformRequest.getStatusLine().getStatusCode();
                            VolleyLog.m16e("Unexpected response code %d for %s", Integer.valueOf(statusCode), request.getUrl());
                            if (bArr != null) {
                                networkResponse = new NetworkResponse(statusCode, bArr, r17, false, SystemClock.elapsedRealtime() - jElapsedRealtime);
                                if (statusCode != 401 || statusCode == 403) {
                                    attemptRetryOnException("auth", request, new AuthFailureError(networkResponse));
                                } else {
                                    throw new ServerError(networkResponse);
                                }
                            } else {
                                throw new NetworkError((NetworkResponse) null);
                            }
                        } else {
                            throw new NoConnectionError(iOException);
                        }
                    }
                } catch (IOException e6) {
                    e = e6;
                    httpResponsePerformRequest = null;
                    bArr = null;
                }
            } catch (MalformedURLException e7) {
                throw new RuntimeException("Bad URL " + request.getUrl(), e7);
            } catch (SocketTimeoutException unused) {
                attemptRetryOnException("socket", request, new TimeoutError());
            } catch (ConnectTimeoutException unused2) {
                attemptRetryOnException("connection", request, new TimeoutError());
            }
        }
    }

    private void logSlowRequests(long j, Request<?> request, byte[] bArr, StatusLine statusLine) {
        if (DEBUG || j > SLOW_REQUEST_THRESHOLD_MS) {
            Object[] objArr = new Object[5];
            objArr[0] = request;
            objArr[1] = Long.valueOf(j);
            objArr[2] = bArr != null ? Integer.valueOf(bArr.length) : "null";
            objArr[3] = Integer.valueOf(statusLine.getStatusCode());
            objArr[4] = Integer.valueOf(request.getRetryPolicy().getCurrentRetryCount());
            VolleyLog.m15d("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", objArr);
        }
    }

    private static void attemptRetryOnException(String str, Request<?> request, VolleyError volleyError) throws VolleyError {
        RetryPolicy retryPolicy = request.getRetryPolicy();
        int timeoutMs = request.getTimeoutMs();
        try {
            retryPolicy.retry(volleyError);
            request.addMarker(String.format("%s-retry [timeout=%s]", str, Integer.valueOf(timeoutMs)));
        } catch (VolleyError e) {
            request.addMarker(String.format("%s-timeout-giveup [timeout=%s]", str, Integer.valueOf(timeoutMs)));
            throw e;
        }
    }

    private void addCacheHeaders(Map<String, String> map, Cache.Entry entry) {
        if (entry == null) {
            return;
        }
        if (entry.etag != null) {
            map.put("If-None-Match", entry.etag);
        }
        if (entry.lastModified > 0) {
            map.put("If-Modified-Since", DateUtils.formatDate(new Date(entry.lastModified)));
        }
    }

    protected void logError(String str, String str2, long j) {
        VolleyLog.m18v("HTTP ERROR(%s) %d ms to fetch %s", str, Long.valueOf(SystemClock.elapsedRealtime() - j), str2);
    }

    private byte[] entityToBytes(HttpEntity httpEntity) throws Throwable {
        PoolingByteArrayOutputStream poolingByteArrayOutputStream = new PoolingByteArrayOutputStream(this.mPool, (int) httpEntity.getContentLength());
        byte[] bArr = null;
        try {
            InputStream content = httpEntity.getContent();
            if (content == null) {
                throw new ServerError();
            }
            byte[] buf = this.mPool.getBuf(1024);
            while (true) {
                try {
                    int i = content.read(buf);
                    if (i == -1) {
                        break;
                    }
                    poolingByteArrayOutputStream.write(buf, 0, i);
                } catch (Throwable th) {
                    th = th;
                    bArr = buf;
                    try {
                        httpEntity.consumeContent();
                    } catch (IOException unused) {
                        VolleyLog.m18v("Error occured when calling consumingContent", new Object[0]);
                    }
                    this.mPool.returnBuf(bArr);
                    poolingByteArrayOutputStream.close();
                    throw th;
                }
            }
            byte[] byteArray = poolingByteArrayOutputStream.toByteArray();
            try {
                httpEntity.consumeContent();
            } catch (IOException unused2) {
                VolleyLog.m18v("Error occured when calling consumingContent", new Object[0]);
            }
            this.mPool.returnBuf(buf);
            poolingByteArrayOutputStream.close();
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    protected static Map<String, String> convertHeaders(Header[] headerArr) {
        TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
        for (int i = 0; i < headerArr.length; i++) {
            treeMap.put(headerArr[i].getName(), headerArr[i].getValue());
        }
        return treeMap;
    }
}

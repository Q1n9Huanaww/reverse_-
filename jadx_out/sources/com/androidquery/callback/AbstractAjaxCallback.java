package com.androidquery.callback;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.util.Xml;
import android.view.View;
import com.androidquery.auth.AccountHandle;
import com.androidquery.auth.GoogleHandle;
import com.androidquery.util.AQUtility;
import com.androidquery.util.Common;
import com.androidquery.util.Constants;
import com.androidquery.util.PredefinedBAOS;
import com.androidquery.util.Progress;
import com.androidquery.util.XmlDom;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.github.kevinsawicki.http.HttpRequest;
import com.umeng.analytics.pro.C1708b;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.java_websocket.WebSocketImpl;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractAjaxCallback<T, K> implements Runnable {
    private static String AGENT = null;
    private static final Class<?>[] DEFAULT_SIG = {String.class, Object.class, AjaxStatus.class};
    private static boolean GZIP = true;
    private static int NETWORK_POOL = 4;
    private static int NET_TIMEOUT = 30000;
    private static boolean REUSE_CLIENT = true;
    private static boolean SIMULATE_ERROR = false;
    private static final String boundary = "*****";
    private static DefaultHttpClient client = null;
    private static ExecutorService fetchExe = null;
    private static int lastStatus = 200;
    private static final String lineEnd = "\r\n";
    private static ProxyHandle proxyHandle = null;
    private static SocketFactory ssf = null;

    /* JADX INFO: renamed from: st */
    private static Transformer f55st = null;
    private static final String twoHyphens = "--";
    private boolean abort;
    private WeakReference<Activity> act;

    /* JADX INFO: renamed from: ah */
    protected AccountHandle f56ah;
    private boolean blocked;
    private File cacheDir;
    private String callback;
    private boolean completed;
    protected Map<String, String> cookies;
    private long expire;
    protected boolean fileCache;
    private Object handler;
    protected Map<String, String> headers;
    protected boolean memCache;
    private String networkUrl;
    protected Map<String, Object> params;
    private WeakReference<Object> progress;
    private HttpHost proxy;
    private boolean reauth;
    private boolean refresh;
    private HttpUriRequest request;
    protected T result;
    protected AjaxStatus status;
    private File targetFile;
    private Transformer transformer;
    private Class<T> type;
    private String url;
    private Reference<Object> whandler;
    private int policy = 0;
    private int timeout = 0;
    private boolean redirect = true;
    private String encoding = "UTF-8";
    private int method = 4;
    private boolean uiCallback = true;
    private int retry = 0;

    /* JADX WARN: Multi-variable type inference failed */
    private K self() {
        return this;
    }

    public void callback(String str, T t, AjaxStatus ajaxStatus) {
    }

    protected T datastoreGet(String str) {
        return null;
    }

    protected T memGet(String str) {
        return null;
    }

    protected void memPut(String str, T t) {
    }

    protected void skip(String str, T t, AjaxStatus ajaxStatus) {
    }

    private void clear() {
        this.whandler = null;
        this.handler = null;
        this.progress = null;
        this.request = null;
        this.transformer = null;
        this.f56ah = null;
        this.act = null;
    }

    public static void setTimeout(int i) {
        NET_TIMEOUT = i;
    }

    public static void setAgent(String str) {
        AGENT = str;
    }

    public static void setGZip(boolean z) {
        GZIP = z;
    }

    public static void setSimulateError(boolean z) {
        SIMULATE_ERROR = z;
    }

    public static void setTransformer(Transformer transformer) {
        f55st = transformer;
    }

    public Class<T> getType() {
        return this.type;
    }

    public K weakHandler(Object obj, String str) {
        this.whandler = new WeakReference(obj);
        this.callback = str;
        this.handler = null;
        return self();
    }

    public K handler(Object obj, String str) {
        this.handler = obj;
        this.callback = str;
        this.whandler = null;
        return self();
    }

    public K url(String str) {
        this.url = str;
        return self();
    }

    public K networkUrl(String str) {
        this.networkUrl = str;
        return self();
    }

    public K type(Class<T> cls) {
        this.type = cls;
        return self();
    }

    public K method(int i) {
        this.method = i;
        return self();
    }

    public K timeout(int i) {
        this.timeout = i;
        return self();
    }

    public K redirect(boolean z) {
        this.redirect = z;
        return self();
    }

    public K retry(int i) {
        this.retry = i;
        return self();
    }

    public K transformer(Transformer transformer) {
        this.transformer = transformer;
        return self();
    }

    public K fileCache(boolean z) {
        this.fileCache = z;
        return self();
    }

    public K memCache(boolean z) {
        this.memCache = z;
        return self();
    }

    public K policy(int i) {
        this.policy = i;
        return self();
    }

    public K refresh(boolean z) {
        this.refresh = z;
        return self();
    }

    public K uiCallback(boolean z) {
        this.uiCallback = z;
        return self();
    }

    public K expire(long j) {
        this.expire = j;
        return self();
    }

    public K header(String str, String str2) {
        if (this.headers == null) {
            this.headers = new HashMap();
        }
        this.headers.put(str, str2);
        return self();
    }

    public K headers(Map<String, String> map) {
        this.headers = map;
        return self();
    }

    public K cookie(String str, String str2) {
        if (this.cookies == null) {
            this.cookies = new HashMap();
        }
        this.cookies.put(str, str2);
        return self();
    }

    public K cookies(Map<String, String> map) {
        this.cookies = map;
        return self();
    }

    public K encoding(String str) {
        this.encoding = str;
        return self();
    }

    public K proxy(String str, int i) {
        this.proxy = new HttpHost(str, i);
        return self();
    }

    public K proxy(String str, int i, String str2, String str3) {
        proxy(str, i);
        String strMakeAuthHeader = makeAuthHeader(str2, str3);
        AQUtility.debug("proxy auth", strMakeAuthHeader);
        return header(HttpRequest.HEADER_PROXY_AUTHORIZATION, strMakeAuthHeader);
    }

    private static String makeAuthHeader(String str, String str2) {
        byte[] bytes = (String.valueOf(str) + ":" + str2).getBytes();
        return "Basic " + new String(AQUtility.encode64(bytes, 0, bytes.length));
    }

    public K targetFile(File file) {
        this.targetFile = file;
        return self();
    }

    public K param(String str, Object obj) {
        if (this.params == null) {
            this.params = new HashMap();
        }
        this.params.put(str, obj);
        return self();
    }

    public K params(Map<String, ?> map) {
        this.params = map;
        return self();
    }

    public K progress(View view) {
        return progress((Object) view);
    }

    public K progress(Dialog dialog) {
        return progress((Object) dialog);
    }

    public K progress(Object obj) {
        if (obj != null) {
            this.progress = new WeakReference<>(obj);
        }
        return self();
    }

    void callback() {
        showProgress(false);
        this.completed = true;
        if (isActive()) {
            if (this.callback != null) {
                AQUtility.invokeHandler(getHandler(), this.callback, true, true, new Class[]{String.class, this.type, AjaxStatus.class}, DEFAULT_SIG, this.url, this.result, this.status);
            } else {
                try {
                    callback(this.url, this.result, this.status);
                } catch (Exception e) {
                    AQUtility.report(e);
                }
            }
        } else {
            skip(this.url, this.result, this.status);
        }
        filePut();
        if (!this.blocked) {
            this.status.close();
        }
        wake();
        AQUtility.debugNotify();
    }

    private void wake() {
        if (this.blocked) {
            synchronized (this) {
                try {
                    notifyAll();
                } catch (Exception unused) {
                }
            }
        }
    }

    public void block() {
        if (AQUtility.isUIThread()) {
            throw new IllegalStateException("Cannot block UI thread.");
        }
        if (this.completed) {
            return;
        }
        try {
            synchronized (this) {
                try {
                    this.blocked = true;
                    wait(NET_TIMEOUT + 5000);
                } catch (Throwable th) {
                    throw th;
                }
            }
        } catch (Exception unused) {
        }
    }

    protected T fileGet(String str, File file, AjaxStatus ajaxStatus) {
        byte[] bytes;
        try {
            if (isStreamingContent()) {
                ajaxStatus.file(file);
                bytes = null;
            } else {
                bytes = AQUtility.toBytes(new FileInputStream(file));
            }
            return transform(str, bytes, ajaxStatus);
        } catch (Exception e) {
            AQUtility.debug((Throwable) e);
            return null;
        }
    }

    protected void showProgress(final boolean z) {
        final Object obj = this.progress == null ? null : this.progress.get();
        if (obj != null) {
            if (AQUtility.isUIThread()) {
                Common.showProgress(obj, this.url, z);
            } else {
                AQUtility.post(new Runnable() { // from class: com.androidquery.callback.AbstractAjaxCallback.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Common.showProgress(obj, AbstractAjaxCallback.this.url, z);
                    }
                });
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.io.File] */
    /* JADX WARN: Type inference failed for: r9v12, types: [T, java.io.Closeable, java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r9v13, types: [T, org.xmlpull.v1.XmlPullParser] */
    protected T transform(String str, byte[] bArr, AjaxStatus ajaxStatus) {
        String str2;
        if (this.type == null) {
            return null;
        }
        ?? r0 = (T) ajaxStatus.getFile();
        if (bArr != 0) {
            if (this.type.equals(Bitmap.class)) {
                return (T) BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
            }
            if (this.type.equals(JSONObject.class)) {
                try {
                    str2 = new String(bArr, this.encoding);
                    try {
                        return (T) ((JSONObject) new JSONTokener(str2).nextValue());
                    } catch (Exception e) {
                        e = e;
                        AQUtility.debug((Throwable) e);
                        AQUtility.debug(str2);
                        return null;
                    }
                } catch (Exception e2) {
                    e = e2;
                    str2 = null;
                }
            } else {
                if (this.type.equals(JSONArray.class)) {
                    try {
                        return (T) ((JSONArray) new JSONTokener(new String(bArr, this.encoding)).nextValue());
                    } catch (Exception e3) {
                        AQUtility.debug((Throwable) e3);
                        return null;
                    }
                }
                if (this.type.equals(String.class)) {
                    if (ajaxStatus.getSource() == 1) {
                        AQUtility.debug("network");
                        return (T) correctEncoding(bArr, this.encoding, ajaxStatus);
                    }
                    AQUtility.debug("file");
                    try {
                        return (T) new String(bArr, this.encoding);
                    } catch (Exception e4) {
                        AQUtility.debug((Throwable) e4);
                        return null;
                    }
                }
                if (this.type.equals(byte[].class)) {
                    return bArr;
                }
                if (this.transformer != null) {
                    return (T) this.transformer.transform(str, this.type, this.encoding, bArr, ajaxStatus);
                }
                if (f55st != null) {
                    return (T) f55st.transform(str, this.type, this.encoding, bArr, ajaxStatus);
                }
            }
        } else if (r0 != 0) {
            if (this.type.equals(File.class)) {
                return r0;
            }
            if (this.type.equals(XmlDom.class)) {
                try {
                    FileInputStream fileInputStream = new FileInputStream((File) r0);
                    T t = (T) new XmlDom(fileInputStream);
                    ajaxStatus.closeLater(fileInputStream);
                    return t;
                } catch (Exception e5) {
                    AQUtility.report(e5);
                    return null;
                }
            }
            if (this.type.equals(XmlPullParser.class)) {
                ?? r9 = (T) Xml.newPullParser();
                try {
                    FileInputStream fileInputStream2 = new FileInputStream((File) r0);
                    r9.setInput(fileInputStream2, this.encoding);
                    ajaxStatus.closeLater(fileInputStream2);
                    return r9;
                } catch (Exception e6) {
                    AQUtility.report(e6);
                    return null;
                }
            }
            if (this.type.equals(InputStream.class)) {
                try {
                    ?? r10 = (T) new FileInputStream((File) r0);
                    ajaxStatus.closeLater(r10);
                    return r10;
                } catch (Exception e7) {
                    AQUtility.report(e7);
                    return null;
                }
            }
        }
        return null;
    }

    private String getCharset(String str) {
        Matcher matcher = Pattern.compile("<meta [^>]*http-equiv[^>]*\"Content-Type\"[^>]*>", 2).matcher(str);
        if (matcher.find()) {
            return parseCharset(matcher.group());
        }
        return null;
    }

    private String parseCharset(String str) {
        int iIndexOf;
        if (str == null || (iIndexOf = str.indexOf(HttpRequest.PARAM_CHARSET)) == -1) {
            return null;
        }
        int iIndexOf2 = str.indexOf(";", iIndexOf);
        if (iIndexOf2 == -1) {
            iIndexOf2 = str.length();
        }
        return str.substring(iIndexOf + 7, iIndexOf2).replaceAll("[^\\w-]", "");
    }

    private String correctEncoding(byte[] bArr, String str, AjaxStatus ajaxStatus) {
        String str2;
        try {
            if (!"utf-8".equalsIgnoreCase(str)) {
                return new String(bArr, str);
            }
            String charset = parseCharset(ajaxStatus.getHeader("Content-Type"));
            AQUtility.debug("parsing header", charset);
            if (charset != null) {
                return new String(bArr, charset);
            }
            str2 = new String(bArr, "utf-8");
            try {
                String charset2 = getCharset(str2);
                AQUtility.debug("parsing needed", charset2);
                if (charset2 == null || "utf-8".equalsIgnoreCase(charset2)) {
                    return str2;
                }
                AQUtility.debug("correction needed", charset2);
                String str3 = new String(bArr, charset2);
                try {
                    ajaxStatus.data(str3.getBytes("utf-8"));
                    return str3;
                } catch (Exception e) {
                    e = e;
                    str2 = str3;
                }
            } catch (Exception e2) {
                e = e2;
            }
            AQUtility.report(e);
            return str2;
        } catch (Exception e3) {
            e = e3;
            str2 = null;
        }
    }

    protected void filePut(String str, T t, File file, byte[] bArr) {
        if (file == null || bArr == null) {
            return;
        }
        AQUtility.storeAsync(file, bArr, 0L);
    }

    protected File accessFile(File file, String str) {
        if (this.expire < 0) {
            return null;
        }
        File existedCacheByUrl = AQUtility.getExistedCacheByUrl(file, str);
        if (existedCacheByUrl == null || this.expire == 0 || System.currentTimeMillis() - existedCacheByUrl.lastModified() <= this.expire) {
            return existedCacheByUrl;
        }
        return null;
    }

    public void async(Activity activity) {
        if (activity.isFinishing()) {
            AQUtility.warn("Warning", "Possible memory leak. Calling ajax with a terminated activity.");
        }
        if (this.type == null) {
            AQUtility.warn("Warning", "type() is not called with response type.");
        } else {
            this.act = new WeakReference<>(activity);
            async((Context) activity);
        }
    }

    public void async(Context context) {
        if (this.status == null) {
            this.status = new AjaxStatus();
            this.status.redirect(this.url).refresh(this.refresh);
        } else if (this.status.getDone()) {
            this.status.reset();
            this.result = null;
        }
        showProgress(true);
        if (this.f56ah != null && !this.f56ah.authenticated()) {
            AQUtility.debug("auth needed", this.url);
            this.f56ah.auth(this);
        } else {
            work(context);
        }
    }

    private boolean isActive() {
        if (this.act == null) {
            return true;
        }
        Activity activity = this.act.get();
        return (activity == null || activity.isFinishing()) ? false : true;
    }

    public void failure(int i, String str) {
        if (this.status != null) {
            this.status.code(i).message(str).done();
            if (this.uiCallback) {
                AQUtility.post(this);
            } else {
                afterWork();
            }
        }
    }

    private void work(Context context) {
        T tMemGet = memGet(this.url);
        if (tMemGet != null) {
            this.result = tMemGet;
            this.status.source(4).done();
            callback();
        } else {
            this.cacheDir = AQUtility.getCacheDir(context, this.policy);
            execute(this);
        }
    }

    protected boolean cacheAvailable(Context context) {
        return this.fileCache && AQUtility.getExistedCacheByUrl(AQUtility.getCacheDir(context, this.policy), this.url) != null;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (!this.status.getDone()) {
            try {
                backgroundWork();
            } catch (Throwable th) {
                AQUtility.debug(th);
                this.status.code(AjaxStatus.NETWORK_ERROR).done();
            }
            if (this.status.getReauth()) {
                return;
            }
            if (this.uiCallback) {
                AQUtility.post(this);
                return;
            } else {
                afterWork();
                return;
            }
        }
        afterWork();
    }

    private void backgroundWork() throws Throwable {
        if (!this.refresh && this.fileCache) {
            fileWork();
        }
        if (this.result == null) {
            datastoreWork();
        }
        if (this.result == null) {
            networkWork();
        }
    }

    private String getCacheUrl() {
        if (this.f56ah != null) {
            return this.f56ah.getCacheUrl(this.url);
        }
        return this.url;
    }

    private String getNetworkUrl(String str) {
        if (this.networkUrl != null) {
            str = this.networkUrl;
        }
        return this.f56ah != null ? this.f56ah.getNetworkUrl(str) : str;
    }

    private void fileWork() {
        File fileAccessFile = accessFile(this.cacheDir, getCacheUrl());
        if (fileAccessFile != null) {
            this.status.source(3);
            this.result = fileGet(this.url, fileAccessFile, this.status);
            if (this.result != null) {
                this.status.time(new Date(fileAccessFile.lastModified())).done();
            }
        }
    }

    private void datastoreWork() {
        this.result = datastoreGet(this.url);
        if (this.result != null) {
            this.status.source(2).done();
        }
    }

    private void networkWork() throws Throwable {
        if (this.url == null) {
            this.status.code(AjaxStatus.NETWORK_ERROR).done();
            return;
        }
        byte[] data = null;
        try {
            network(this.retry + 1);
            if (this.f56ah != null && this.f56ah.expired(this, this.status) && !this.reauth) {
                AQUtility.debug("reauth needed", this.status.getMessage());
                this.reauth = true;
                if (this.f56ah.reauth(this)) {
                    network();
                } else {
                    this.status.reauth(true);
                    return;
                }
            }
            data = this.status.getData();
        } catch (IOException e) {
            AQUtility.debug("IOException");
            String message = e.getMessage();
            if (message != null && message.contains("No authentication challenges found")) {
                this.status.code(UiMessage.CommandToUi.Command_Type.GET_ENABLED_VALUE).message(message);
            } else {
                this.status.code(AjaxStatus.NETWORK_ERROR).message("network error");
            }
        } catch (Exception e2) {
            AQUtility.debug((Throwable) e2);
            this.status.code(AjaxStatus.NETWORK_ERROR).message("network error");
        }
        try {
            this.result = transform(this.url, data, this.status);
        } catch (Exception e3) {
            AQUtility.debug((Throwable) e3);
        }
        if (this.result == null && data != null) {
            this.status.code(AjaxStatus.TRANSFORM_ERROR).message("transform error");
        }
        lastStatus = this.status.getCode();
        this.status.done();
    }

    protected File getCacheFile() {
        return AQUtility.getCacheFile(this.cacheDir, getCacheUrl());
    }

    protected boolean isStreamingContent() {
        return File.class.equals(this.type) || XmlPullParser.class.equals(this.type) || InputStream.class.equals(this.type) || XmlDom.class.equals(this.type);
    }

    private File getPreFile() {
        File cacheFile;
        if (!isStreamingContent()) {
            cacheFile = null;
        } else if (this.targetFile != null) {
            cacheFile = this.targetFile;
        } else if (this.fileCache) {
            cacheFile = getCacheFile();
        } else {
            File tempDir = AQUtility.getTempDir();
            if (tempDir == null) {
                tempDir = this.cacheDir;
            }
            cacheFile = AQUtility.getCacheFile(tempDir, this.url);
        }
        if (cacheFile != null && !cacheFile.exists()) {
            try {
                cacheFile.getParentFile().mkdirs();
                cacheFile.createNewFile();
            } catch (Exception e) {
                AQUtility.report(e);
                return null;
            }
        }
        return cacheFile;
    }

    private void filePut() {
        if (this.result != null && this.fileCache) {
            byte[] data = this.status.getData();
            if (data != null) {
                try {
                    if (this.status.getSource() == 1) {
                        File cacheFile = getCacheFile();
                        if (!this.status.getInvalid()) {
                            filePut(this.url, this.result, cacheFile, data);
                        } else if (cacheFile.exists()) {
                            cacheFile.delete();
                        }
                    }
                } catch (Exception e) {
                    AQUtility.debug((Throwable) e);
                }
            }
            this.status.data(null);
            return;
        }
        if (this.status.getCode() == -103) {
            File cacheFile2 = getCacheFile();
            if (cacheFile2.exists()) {
                cacheFile2.delete();
                AQUtility.debug("invalidated cache due to transform error");
            }
        }
    }

    private static String extractUrl(Uri uri) {
        String str = String.valueOf(uri.getScheme()) + "://" + uri.getAuthority() + uri.getPath();
        String fragment = uri.getFragment();
        if (fragment == null) {
            return str;
        }
        return String.valueOf(str) + "#" + fragment;
    }

    private static Map<String, Object> extractParams(Uri uri) {
        HashMap map = new HashMap();
        for (String str : uri.getQuery().split("&")) {
            String[] strArrSplit = str.split("=");
            if (strArrSplit.length >= 2) {
                map.put(strArrSplit[0], strArrSplit[1]);
            } else if (strArrSplit.length == 1) {
                map.put(strArrSplit[0], "");
            }
        }
        return map;
    }

    private void network(int i) throws Throwable {
        if (i <= 1) {
            network();
            return;
        }
        for (int i2 = 0; i2 < i; i2++) {
            try {
                network();
                return;
            } catch (IOException e) {
                if (i2 == i - 1) {
                    throw e;
                }
            }
        }
    }

    private void network() throws Throwable {
        String str = this.url;
        Map<String, Object> map = this.params;
        if (map == null && str.length() > 2000) {
            Uri uri = Uri.parse(str);
            String strExtractUrl = extractUrl(uri);
            map = extractParams(uri);
            str = strExtractUrl;
        }
        String networkUrl = getNetworkUrl(str);
        if (2 == this.method) {
            httpDelete(networkUrl, this.status);
            return;
        }
        if (3 == this.method) {
            httpPut(networkUrl, map, this.status);
            return;
        }
        if (1 == this.method && map == null) {
            map = new HashMap<>();
        }
        if (map == null) {
            httpGet(networkUrl, this.status);
        } else if (isMultiPart(map)) {
            httpMulti(networkUrl, map, this.status);
        } else {
            httpPost(networkUrl, map, this.status);
        }
    }

    private void afterWork() {
        if (this.url != null && this.memCache) {
            memPut(this.url, this.result);
        }
        callback();
        clear();
    }

    public static void execute(Runnable runnable) {
        if (fetchExe == null) {
            fetchExe = Executors.newFixedThreadPool(NETWORK_POOL);
        }
        fetchExe.execute(runnable);
    }

    public static int getActiveCount() {
        if (fetchExe instanceof ThreadPoolExecutor) {
            return ((ThreadPoolExecutor) fetchExe).getActiveCount();
        }
        return 0;
    }

    public static void setNetworkLimit(int i) {
        NETWORK_POOL = Math.max(1, Math.min(25, i));
        fetchExe = null;
        AQUtility.debug("setting network limit", Integer.valueOf(NETWORK_POOL));
    }

    public static void cancel() {
        if (fetchExe != null) {
            fetchExe.shutdownNow();
            fetchExe = null;
        }
        BitmapAjaxCallback.clearTasks();
    }

    private static String patchUrl(String str) {
        return str.replaceAll(" ", "%20").replaceAll("\\|", "%7C");
    }

    private void httpGet(String str, AjaxStatus ajaxStatus) throws Throwable {
        AQUtility.debug("get", str);
        String strPatchUrl = patchUrl(str);
        httpDo(new HttpGet(strPatchUrl), strPatchUrl, ajaxStatus);
    }

    private void httpDelete(String str, AjaxStatus ajaxStatus) throws Throwable {
        AQUtility.debug("get", str);
        String strPatchUrl = patchUrl(str);
        httpDo(new HttpDelete(strPatchUrl), strPatchUrl, ajaxStatus);
    }

    private void httpPost(String str, Map<String, Object> map, AjaxStatus ajaxStatus) throws Throwable {
        AQUtility.debug("post", str);
        httpEntity(str, new HttpPost(str), map, ajaxStatus);
    }

    private void httpPut(String str, Map<String, Object> map, AjaxStatus ajaxStatus) throws Throwable {
        AQUtility.debug("put", str);
        httpEntity(str, new HttpPut(str), map, ajaxStatus);
    }

    private void httpEntity(String str, HttpEntityEnclosingRequestBase httpEntityEnclosingRequestBase, Map<String, Object> map, AjaxStatus ajaxStatus) throws Throwable {
        HttpEntity urlEncodedFormEntity;
        httpEntityEnclosingRequestBase.getParams().setBooleanParameter("http.protocol.expect-continue", false);
        Object obj = map.get(Constants.POST_ENTITY);
        if (obj instanceof HttpEntity) {
            urlEncodedFormEntity = (HttpEntity) obj;
        } else {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Object value = entry.getValue();
                if (value != null) {
                    arrayList.add(new BasicNameValuePair(entry.getKey(), value.toString()));
                }
            }
            urlEncodedFormEntity = new UrlEncodedFormEntity(arrayList, "UTF-8");
        }
        if (this.headers != null && !this.headers.containsKey("Content-Type")) {
            this.headers.put("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
        }
        httpEntityEnclosingRequestBase.setEntity(urlEncodedFormEntity);
        httpDo(httpEntityEnclosingRequestBase, str, ajaxStatus);
    }

    public static void setSSF(SocketFactory socketFactory) {
        ssf = socketFactory;
        client = null;
    }

    public static void setReuseHttpClient(boolean z) {
        REUSE_CLIENT = z;
        client = null;
    }

    private static DefaultHttpClient getClient() {
        if (client == null || !REUSE_CLIENT) {
            AQUtility.debug("creating http client");
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, NET_TIMEOUT);
            HttpConnectionParams.setSoTimeout(basicHttpParams, NET_TIMEOUT);
            ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(25));
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", ssf == null ? SSLSocketFactory.getSocketFactory() : ssf, WebSocketImpl.DEFAULT_WSS_PORT));
            client = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
        }
        return client;
    }

    private HttpResponse execute(HttpUriRequest httpUriRequest, DefaultHttpClient defaultHttpClient, HttpContext httpContext) throws IOException {
        HttpHost httpHost;
        if (httpUriRequest.getURI().getAuthority().contains("_")) {
            URL url = httpUriRequest.getURI().toURL();
            if (url.getPort() == -1) {
                httpHost = new HttpHost(url.getHost(), 80, url.getProtocol());
            } else {
                httpHost = new HttpHost(url.getHost(), url.getPort(), url.getProtocol());
            }
            return defaultHttpClient.execute(httpHost, httpUriRequest, httpContext);
        }
        return defaultHttpClient.execute(httpUriRequest, httpContext);
    }

    public static void setProxyHandle(ProxyHandle proxyHandle2) {
        proxyHandle = proxyHandle2;
    }

    private void httpDo(HttpUriRequest httpUriRequest, String str, AjaxStatus ajaxStatus) throws Throwable {
        HttpResponse httpResponseExecute;
        Throwable th;
        InputStream content;
        Exception exc;
        String str2;
        String str3;
        byte[] byteArray;
        File preFile;
        Throwable th2;
        OutputStream outputStream;
        OutputStream predefinedBAOS;
        File file;
        InputStream gZIPInputStream;
        DefaultHttpClient client2 = getClient();
        if (proxyHandle != null) {
            proxyHandle.applyProxy(this, httpUriRequest, client2);
        }
        if (AGENT != null) {
            httpUriRequest.addHeader("User-Agent", AGENT);
        } else if (AGENT == null && GZIP) {
            httpUriRequest.addHeader("User-Agent", HttpRequest.ENCODING_GZIP);
        }
        if (this.headers != null) {
            for (String str4 : this.headers.keySet()) {
                httpUriRequest.addHeader(str4, this.headers.get(str4));
            }
        }
        if (GZIP && (this.headers == null || !this.headers.containsKey(HttpRequest.HEADER_ACCEPT_ENCODING))) {
            httpUriRequest.addHeader(HttpRequest.HEADER_ACCEPT_ENCODING, HttpRequest.ENCODING_GZIP);
        }
        if (this.f56ah != null) {
            this.f56ah.applyToken((AbstractAjaxCallback<?, ?>) this, (org.apache.http.HttpRequest) httpUriRequest);
        }
        String strMakeCookie = makeCookie();
        if (strMakeCookie != null) {
            httpUriRequest.addHeader("Cookie", strMakeCookie);
        }
        HttpParams params = httpUriRequest.getParams();
        if (this.proxy != null) {
            params.setParameter("http.route.default-proxy", this.proxy);
        }
        if (this.timeout > 0) {
            params.setParameter("http.connection.timeout", Integer.valueOf(this.timeout));
            params.setParameter("http.socket.timeout", Integer.valueOf(this.timeout));
        }
        if (!this.redirect) {
            params.setBooleanParameter("http.protocol.handle-redirects", false);
        }
        BasicHttpContext basicHttpContext = new BasicHttpContext();
        basicHttpContext.setAttribute("http.cookie-store", new BasicCookieStore());
        this.request = httpUriRequest;
        if (this.abort) {
            throw new IOException("Aborted");
        }
        if (SIMULATE_ERROR) {
            throw new IOException("Simulated Error");
        }
        InputStream inputStream = null;
        try {
            httpResponseExecute = execute(httpUriRequest, client2, basicHttpContext);
        } catch (HttpHostConnectException e) {
            if (this.proxy != null) {
                AQUtility.debug("proxy failed, retrying without proxy");
                params.setParameter("http.route.default-proxy", null);
                httpResponseExecute = execute(httpUriRequest, client2, basicHttpContext);
            } else {
                throw e;
            }
        }
        HttpResponse httpResponse = httpResponseExecute;
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        String reasonPhrase = httpResponse.getStatusLine().getReasonPhrase();
        HttpEntity entity = httpResponse.getEntity();
        if (statusCode < 200 || statusCode >= 300) {
            if (entity != null) {
                try {
                    content = entity.getContent();
                    try {
                        try {
                            str2 = new String(toData(getEncoding(entity), content), "UTF-8");
                            try {
                                AQUtility.debug(C1708b.f3698J, str2);
                            } catch (Exception e2) {
                                exc = e2;
                                AQUtility.debug((Throwable) exc);
                            }
                        } catch (Exception e3) {
                            exc = e3;
                            str2 = null;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        AQUtility.close(content);
                        throw th;
                    }
                } catch (Exception e4) {
                    exc = e4;
                    str2 = null;
                    content = null;
                } catch (Throwable th4) {
                    th = th4;
                    content = null;
                    AQUtility.close(content);
                    throw th;
                }
            } else {
                str2 = null;
                content = null;
            }
            AQUtility.close(content);
            str3 = str;
            byteArray = null;
            preFile = null;
        } else {
            String str5 = String.valueOf(((HttpHost) basicHttpContext.getAttribute("http.target_host")).toURI()) + ((HttpUriRequest) basicHttpContext.getAttribute("http.request")).getURI();
            int iMax = Math.max(32, Math.min(65536, (int) entity.getContentLength()));
            try {
                preFile = getPreFile();
                if (preFile == null) {
                    try {
                        predefinedBAOS = new PredefinedBAOS(iMax);
                        file = null;
                    } catch (Throwable th5) {
                        th2 = th5;
                        outputStream = null;
                        AQUtility.close(inputStream);
                        AQUtility.close(outputStream);
                        throw th2;
                    }
                } else {
                    File fileMakeTempFile = makeTempFile(preFile);
                    file = fileMakeTempFile;
                    predefinedBAOS = new BufferedOutputStream(new FileOutputStream(fileMakeTempFile));
                }
                try {
                    InputStream content2 = entity.getContent();
                    try {
                        if (HttpRequest.ENCODING_GZIP.equalsIgnoreCase(getEncoding(entity))) {
                            try {
                                gZIPInputStream = new GZIPInputStream(content2);
                            } catch (Throwable th6) {
                                th2 = th6;
                                inputStream = content2;
                                outputStream = predefinedBAOS;
                                AQUtility.close(inputStream);
                                AQUtility.close(outputStream);
                                throw th2;
                            }
                        } else {
                            gZIPInputStream = content2;
                        }
                        try {
                            inputStream = gZIPInputStream;
                            outputStream = predefinedBAOS;
                            try {
                                copy(gZIPInputStream, predefinedBAOS, (int) entity.getContentLength(), file, preFile);
                                if (preFile == null) {
                                    byteArray = ((PredefinedBAOS) outputStream).toByteArray();
                                } else if (!preFile.exists() || preFile.length() == 0) {
                                    byteArray = null;
                                    preFile = null;
                                } else {
                                    byteArray = null;
                                }
                                AQUtility.close(inputStream);
                                AQUtility.close(outputStream);
                                str3 = str5;
                                str2 = null;
                            } catch (Throwable th7) {
                                th = th7;
                                th2 = th;
                                AQUtility.close(inputStream);
                                AQUtility.close(outputStream);
                                throw th2;
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            inputStream = gZIPInputStream;
                            outputStream = predefinedBAOS;
                        }
                    } catch (Throwable th9) {
                        outputStream = predefinedBAOS;
                        th2 = th9;
                        inputStream = content2;
                    }
                } catch (Throwable th10) {
                    outputStream = predefinedBAOS;
                    th2 = th10;
                    inputStream = null;
                }
            } catch (Throwable th11) {
                th2 = th11;
                inputStream = null;
                outputStream = null;
            }
        }
        AQUtility.debug("response", Integer.valueOf(statusCode));
        if (byteArray != null) {
            AQUtility.debug(Integer.valueOf(byteArray.length), str);
        }
        ajaxStatus.code(statusCode).message(reasonPhrase).error(str2).redirect(str3).time(new Date()).data(byteArray).file(preFile).client(client2).context(basicHttpContext).headers(httpResponse.getAllHeaders());
    }

    private String getEncoding(HttpEntity httpEntity) {
        Header contentEncoding;
        if (httpEntity == null || (contentEncoding = httpEntity.getContentEncoding()) == null) {
            return null;
        }
        return contentEncoding.getValue();
    }

    private void copy(InputStream inputStream, OutputStream outputStream, int i, File file, File file2) throws IOException {
        if (file2 == null) {
            copy(inputStream, outputStream, i);
            return;
        }
        try {
            copy(inputStream, outputStream, i);
            inputStream.close();
            outputStream.close();
            file.renameTo(file2);
        } catch (IOException e) {
            AQUtility.debug("copy failed, deleting files");
            file.delete();
            file2.delete();
            AQUtility.close(inputStream);
            AQUtility.close(outputStream);
            throw e;
        }
    }

    private File makeTempFile(File file) throws IOException {
        File file2 = new File(String.valueOf(file.getAbsolutePath()) + ".tmp");
        file2.createNewFile();
        return file2;
    }

    private void copy(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        Object obj = this.progress != null ? this.progress.get() : null;
        AQUtility.copy(inputStream, outputStream, i, obj != null ? new Progress(obj) : null);
    }

    public K auth(Activity activity, String str, String str2) {
        if (Build.VERSION.SDK_INT >= 5 && str.startsWith("g.")) {
            this.f56ah = new GoogleHandle(activity, str, str2);
        }
        return self();
    }

    public K auth(AccountHandle accountHandle) {
        this.f56ah = accountHandle;
        return self();
    }

    public String getUrl() {
        return this.url;
    }

    public Object getHandler() {
        if (this.handler != null) {
            return this.handler;
        }
        if (this.whandler == null) {
            return null;
        }
        return this.whandler.get();
    }

    public String getCallback() {
        return this.callback;
    }

    protected static int getLastStatus() {
        return lastStatus;
    }

    public T getResult() {
        return this.result;
    }

    public AjaxStatus getStatus() {
        return this.status;
    }

    public String getEncoding() {
        return this.encoding;
    }

    public void abort() {
        this.abort = true;
        if (this.request == null || this.request.isAborted()) {
            return;
        }
        this.request.abort();
    }

    private static boolean isMultiPart(Map<String, Object> map) {
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = entry.getValue();
            AQUtility.debug(entry.getKey(), value);
            if ((value instanceof File) || (value instanceof byte[]) || (value instanceof InputStream)) {
                return true;
            }
        }
        return false;
    }

    private void httpMulti(String str, Map<String, Object> map, AjaxStatus ajaxStatus) throws IOException {
        Proxy proxyMakeProxy;
        HttpURLConnection httpURLConnection;
        String str2;
        byte[] data;
        AQUtility.debug("multipart", str);
        URL url = new URL(str);
        if (this.proxy != null) {
            AQUtility.debug("proxy", this.proxy);
            proxyMakeProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(this.proxy.getHostName(), this.proxy.getPort()));
        } else {
            proxyMakeProxy = proxyHandle != null ? proxyHandle.makeProxy(this) : null;
        }
        if (proxyMakeProxy == null) {
            httpURLConnection = (HttpURLConnection) url.openConnection();
        } else {
            httpURLConnection = (HttpURLConnection) url.openConnection(proxyMakeProxy);
        }
        httpURLConnection.setInstanceFollowRedirects(false);
        httpURLConnection.setConnectTimeout(NET_TIMEOUT * 4);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;charset=utf-8;boundary=*****");
        if (this.headers != null) {
            for (String str3 : this.headers.keySet()) {
                httpURLConnection.setRequestProperty(str3, this.headers.get(str3));
            }
        }
        String strMakeCookie = makeCookie();
        if (strMakeCookie != null) {
            httpURLConnection.setRequestProperty("Cookie", strMakeCookie);
        }
        if (this.f56ah != null) {
            this.f56ah.applyToken((AbstractAjaxCallback<?, ?>) this, httpURLConnection);
        }
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            writeObject(dataOutputStream, entry.getKey(), entry.getValue());
        }
        dataOutputStream.writeBytes("--*****--\r\n");
        dataOutputStream.flush();
        dataOutputStream.close();
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        String responseMessage = httpURLConnection.getResponseMessage();
        String contentEncoding = httpURLConnection.getContentEncoding();
        if (responseCode < 200 || responseCode >= 300) {
            str2 = new String(toData(contentEncoding, httpURLConnection.getErrorStream()), "UTF-8");
            AQUtility.debug(C1708b.f3698J, str2);
            data = null;
        } else {
            data = toData(contentEncoding, httpURLConnection.getInputStream());
            str2 = null;
        }
        AQUtility.debug("response", Integer.valueOf(responseCode));
        if (data != null) {
            AQUtility.debug(Integer.valueOf(data.length), str);
        }
        ajaxStatus.code(responseCode).message(responseMessage).redirect(str).time(new Date()).data(data).error(str2).client(null);
    }

    private byte[] toData(String str, InputStream inputStream) throws IOException {
        return AQUtility.toBytes(HttpRequest.ENCODING_GZIP.equalsIgnoreCase(str) ? new GZIPInputStream(inputStream) : inputStream);
    }

    private static void writeObject(DataOutputStream dataOutputStream, String str, Object obj) throws IOException {
        if (obj == null) {
            return;
        }
        if (obj instanceof File) {
            File file = (File) obj;
            writeData(dataOutputStream, str, file.getName(), new FileInputStream(file));
        } else if (obj instanceof byte[]) {
            writeData(dataOutputStream, str, str, new ByteArrayInputStream((byte[]) obj));
        } else if (obj instanceof InputStream) {
            writeData(dataOutputStream, str, str, (InputStream) obj);
        } else {
            writeField(dataOutputStream, str, obj.toString());
        }
    }

    private static void writeData(DataOutputStream dataOutputStream, String str, String str2, InputStream inputStream) throws IOException {
        dataOutputStream.writeBytes("--*****\r\n");
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + str + "\"; filename=\"" + str2 + "\"\r\n");
        dataOutputStream.writeBytes("Content-Type: application/octet-stream");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.writeBytes("Content-Transfer-Encoding: binary");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.writeBytes("\r\n");
        AQUtility.copy(inputStream, dataOutputStream);
        dataOutputStream.writeBytes("\r\n");
    }

    private static void writeField(DataOutputStream dataOutputStream, String str, String str2) throws IOException {
        dataOutputStream.writeBytes("--*****\r\n");
        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + str + "\"");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.writeBytes("\r\n");
        dataOutputStream.write(str2.getBytes("UTF-8"));
        dataOutputStream.writeBytes("\r\n");
    }

    private String makeCookie() {
        if (this.cookies == null || this.cookies.size() == 0) {
            return null;
        }
        Iterator<String> it2 = this.cookies.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        while (it2.hasNext()) {
            String next = it2.next();
            String str = this.cookies.get(next);
            sb.append(next);
            sb.append("=");
            sb.append(str);
            if (it2.hasNext()) {
                sb.append("; ");
            }
        }
        return sb.toString();
    }
}

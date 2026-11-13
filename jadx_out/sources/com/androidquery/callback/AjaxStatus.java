package com.androidquery.callback;

import com.androidquery.util.AQUtility;
import java.io.Closeable;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HttpContext;

/* JADX INFO: loaded from: classes.dex */
public class AjaxStatus {
    public static final int AUTH_ERROR = -102;
    public static final int DATASTORE = 2;
    public static final int DEVICE = 5;
    public static final int FILE = 3;
    public static final int MEMORY = 4;
    public static final int NETWORK = 1;
    public static final int NETWORK_ERROR = -101;
    public static final int TRANSFORM_ERROR = -103;
    private DefaultHttpClient client;
    private Closeable close;
    private int code;
    private HttpContext context;
    private byte[] data;
    private boolean done;
    private long duration;
    private String error;
    private File file;
    private Header[] headers;
    private boolean invalid;
    private String message;
    private boolean reauth;
    private String redirect;
    private boolean refresh;
    private int source;
    private long start;
    private Date time;

    public AjaxStatus() {
        this.code = 200;
        this.message = "OK";
        this.time = new Date();
        this.source = 1;
        this.start = System.currentTimeMillis();
    }

    public AjaxStatus(int i, String str) {
        this.code = 200;
        this.message = "OK";
        this.time = new Date();
        this.source = 1;
        this.start = System.currentTimeMillis();
        this.code = i;
        this.message = str;
    }

    protected AjaxStatus source(int i) {
        this.source = i;
        return this;
    }

    public AjaxStatus code(int i) {
        this.code = i;
        return this;
    }

    protected AjaxStatus error(String str) {
        this.error = str;
        return this;
    }

    public AjaxStatus message(String str) {
        this.message = str;
        return this;
    }

    protected AjaxStatus redirect(String str) {
        this.redirect = str;
        return this;
    }

    protected AjaxStatus context(HttpContext httpContext) {
        this.context = httpContext;
        return this;
    }

    protected AjaxStatus time(Date date) {
        this.time = date;
        return this;
    }

    protected AjaxStatus refresh(boolean z) {
        this.refresh = z;
        return this;
    }

    protected AjaxStatus reauth(boolean z) {
        this.reauth = z;
        return this;
    }

    protected AjaxStatus client(DefaultHttpClient defaultHttpClient) {
        this.client = defaultHttpClient;
        return this;
    }

    protected AjaxStatus headers(Header[] headerArr) {
        this.headers = headerArr;
        return this;
    }

    public AjaxStatus done() {
        this.duration = System.currentTimeMillis() - this.start;
        this.done = true;
        this.reauth = false;
        return this;
    }

    protected AjaxStatus reset() {
        this.duration = System.currentTimeMillis() - this.start;
        this.done = false;
        close();
        return this;
    }

    protected void closeLater(Closeable closeable) {
        this.close = closeable;
    }

    public void close() {
        AQUtility.close(this.close);
        this.close = null;
    }

    protected AjaxStatus data(byte[] bArr) {
        this.data = bArr;
        return this;
    }

    protected AjaxStatus file(File file) {
        this.file = file;
        return this;
    }

    public AjaxStatus invalidate() {
        this.invalid = true;
        return this;
    }

    protected boolean getDone() {
        return this.done;
    }

    protected boolean getReauth() {
        return this.reauth;
    }

    protected boolean getInvalid() {
        return this.invalid;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getRedirect() {
        return this.redirect;
    }

    protected byte[] getData() {
        return this.data;
    }

    protected File getFile() {
        return this.file;
    }

    public Date getTime() {
        return this.time;
    }

    public boolean getRefresh() {
        return this.refresh;
    }

    public DefaultHttpClient getClient() {
        return this.client;
    }

    public long getDuration() {
        return this.duration;
    }

    public int getSource() {
        return this.source;
    }

    public String getError() {
        return this.error;
    }

    public boolean expired(long j) {
        return System.currentTimeMillis() - this.time.getTime() > j && getSource() != 1;
    }

    public List<Cookie> getCookies() {
        if (this.context == null) {
            return Collections.emptyList();
        }
        CookieStore cookieStore = (CookieStore) this.context.getAttribute("http.cookie-store");
        if (cookieStore == null) {
            return Collections.emptyList();
        }
        return cookieStore.getCookies();
    }

    public List<Header> getHeaders() {
        return this.headers == null ? Collections.emptyList() : Arrays.asList(this.headers);
    }

    public String getHeader(String str) {
        if (this.headers == null) {
            return null;
        }
        for (int i = 0; i < this.headers.length; i++) {
            if (str.equalsIgnoreCase(this.headers[i].getName())) {
                return this.headers[i].getValue();
            }
        }
        return null;
    }
}

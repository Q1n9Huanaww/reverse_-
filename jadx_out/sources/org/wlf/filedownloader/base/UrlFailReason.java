package org.wlf.filedownloader.base;

/* JADX INFO: loaded from: classes2.dex */
public abstract class UrlFailReason extends FailReason {
    private String mUrl;

    public UrlFailReason(String url, String type) {
        super(type);
        init(url);
    }

    public UrlFailReason(String url, String detailMessage, String type) {
        super(detailMessage, type);
        init(url);
    }

    public UrlFailReason(String url, String detailMessage, Throwable throwable, String type) {
        super(detailMessage, throwable, type);
        init(url);
    }

    public UrlFailReason(String url, Throwable throwable, String type) {
        super(throwable, type);
        init(url);
    }

    public UrlFailReason(String url, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        init(url);
    }

    public UrlFailReason(String url, Throwable throwable) {
        super(throwable);
        init(url);
    }

    private void init(String url) {
        this.mUrl = url;
    }

    protected final void setUrl(String url) {
        this.mUrl = url;
    }

    public String getUrl() {
        return this.mUrl;
    }
}

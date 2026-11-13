package org.wlf.filedownloader.base;

/* JADX INFO: loaded from: classes2.dex */
public class BaseDownloadConfigBuilder {
    public static final int DEFAULT_CONNECT_TIMEOUT = 15000;
    public static final int DEFAULT_RETRY_DOWNLOAD_TIMES = 0;
    public static final int MAX_CONNECT_TIMEOUT = 120000;
    public static final int MAX_RETRY_DOWNLOAD_TIMES = 10;
    public static final int MIN_CONNECT_TIMEOUT = 5000;
    protected int mRetryDownloadTimes = 0;
    protected int mConnectTimeout = DEFAULT_CONNECT_TIMEOUT;

    public BaseDownloadConfigBuilder configRetryDownloadTimes(int retryDownloadTimes) {
        if (retryDownloadTimes >= 0 && retryDownloadTimes <= 10) {
            this.mRetryDownloadTimes = retryDownloadTimes;
        } else if (retryDownloadTimes > 10) {
            this.mRetryDownloadTimes = 10;
        } else if (retryDownloadTimes < 0) {
            this.mRetryDownloadTimes = 0;
        } else {
            Log.m5160i(getClass().getSimpleName(), "configRetryDownloadTimes 配置下载失败重试次数失败，retryDownloadTimes：" + retryDownloadTimes);
        }
        return this;
    }

    public BaseDownloadConfigBuilder configConnectTimeout(int connectTimeout) {
        if (connectTimeout >= 5000 && connectTimeout <= 120000) {
            this.mConnectTimeout = connectTimeout;
        } else if (connectTimeout > 120000) {
            this.mConnectTimeout = MAX_CONNECT_TIMEOUT;
        } else if (connectTimeout < 5000) {
            this.mConnectTimeout = 5000;
        } else {
            Log.m5160i(getClass().getSimpleName(), "configConnectTimeout 配置连接超时时间失败，connectTimeout：" + connectTimeout);
        }
        return this;
    }
}

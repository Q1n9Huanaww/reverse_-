package org.wlf.filedownloader;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.MapUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class DownloadConfiguration {
    public static final String DEFAULT_REQUEST_METHOD = "GET";
    private InnerBuilder mBuilder;
    private static final String TAG = FileDownloadConfiguration.class.getSimpleName();
    private static final String NULL_KEY_FOR_URL = DownloadConfiguration.class + "_temp_key_for_null";

    public static class Builder extends InnerBuilder {
        public Builder() {
            super();
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public /* bridge */ /* synthetic */ InnerBuilder addHeaders(Map map) {
            return addHeaders((Map<String, String>) map);
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public /* bridge */ /* synthetic */ DownloadConfiguration build() {
            return super.build();
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public Builder addHeader(String key, String value) {
            super.addHeader(key, value);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public Builder replaceHeader(String key, String value) {
            super.replaceHeader(key, value);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public Builder addHeaders(Map<String, String> headers) {
            super.addHeaders(headers);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder, org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public Builder configRetryDownloadTimes(int retryDownloadTimes) {
            super.configRetryDownloadTimes(retryDownloadTimes);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder, org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public Builder configConnectTimeout(int connectTimeout) {
            super.configConnectTimeout(connectTimeout);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public Builder configRequestMethod(String requestMethod) {
            super.configRequestMethod(requestMethod);
            return this;
        }
    }

    public static class MultiBuilder extends InnerBuilder {
        public MultiBuilder() {
            super();
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public /* bridge */ /* synthetic */ InnerBuilder addHeaders(Map map) {
            return addHeaders((Map<String, String>) map);
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public /* bridge */ /* synthetic */ InnerBuilder addHeadersWithUrl(String str, Map map) {
            return addHeadersWithUrl(str, (Map<String, String>) map);
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public /* bridge */ /* synthetic */ DownloadConfiguration build() {
            return super.build();
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder addHeader(String key, String value) {
            super.addHeader(key, value);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder replaceHeader(String key, String value) {
            super.replaceHeader(key, value);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder addHeaders(Map<String, String> headers) {
            super.addHeaders(headers);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder, org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public MultiBuilder configRetryDownloadTimes(int retryDownloadTimes) {
            super.configRetryDownloadTimes(retryDownloadTimes);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder, org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public MultiBuilder configConnectTimeout(int connectTimeout) {
            super.configConnectTimeout(connectTimeout);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder configRequestMethod(String requestMethod) {
            super.configRequestMethod(requestMethod);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder addHeaderWithUrl(String url, String key, String value) {
            super.addHeaderWithUrl(url, key, value);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder replaceHeaderWithUrl(String url, String key, String value) {
            super.replaceHeaderWithUrl(url, key, value);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder addHeadersWithUrl(String url, Map<String, String> headers) {
            super.addHeadersWithUrl(url, headers);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder configRetryDownloadTimesWithUrl(String url, int retryDownloadTimes) {
            super.configRetryDownloadTimesWithUrl(url, retryDownloadTimes);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder configConnectTimeoutWithUrl(String url, int connectTimeout) {
            super.configConnectTimeoutWithUrl(url, connectTimeout);
            return this;
        }

        @Override // org.wlf.filedownloader.DownloadConfiguration.InnerBuilder
        public MultiBuilder configRequestMethodWithUrl(String url, String requestMethod) {
            super.configRequestMethodWithUrl(url, requestMethod);
            return this;
        }
    }

    private static class InnerBuilder extends BaseDownloadConfigBuilder {
        private Map<String, Integer> mConnectTimeout;
        private Map<String, String> mRequestMethod;
        private Map<String, Integer> mRetryDownloadTimes;
        private Map<String, Map<String, String>> mUrlHeaders;

        private InnerBuilder() {
            this.mUrlHeaders = new HashMap();
            this.mRetryDownloadTimes = new HashMap();
            this.mConnectTimeout = new HashMap();
            this.mRequestMethod = new HashMap();
        }

        protected InnerBuilder addHeader(String key, String value) {
            String url = DownloadConfiguration.NULL_KEY_FOR_URL;
            addOrReplaceWithUrl(url, key, value, false);
            return this;
        }

        protected InnerBuilder addHeaderWithUrl(String url, String key, String value) {
            addOrReplaceWithUrl(url, key, value, false);
            return this;
        }

        protected InnerBuilder replaceHeader(String key, String value) {
            String url = DownloadConfiguration.NULL_KEY_FOR_URL;
            addOrReplaceWithUrl(url, key, value, true);
            return this;
        }

        protected InnerBuilder replaceHeaderWithUrl(String url, String key, String value) {
            addOrReplaceWithUrl(url, key, value, true);
            return this;
        }

        private void addOrReplaceWithUrl(String url, String key, String value, boolean replace) {
            if (UrlUtil.isUrl(url) && !TextUtils.isEmpty(key)) {
                Map<String, String> existHeaders = this.mUrlHeaders.get(url);
                if (existHeaders == null) {
                    existHeaders = new HashMap<>();
                    this.mUrlHeaders.put(url, existHeaders);
                }
                if (replace) {
                    if (existHeaders.containsKey(key)) {
                        existHeaders.remove(key);
                        existHeaders.put(key, value);
                        return;
                    }
                    return;
                }
                if (!existHeaders.containsKey(key)) {
                    existHeaders.put(key, value);
                }
            }
        }

        protected InnerBuilder addHeaders(Map<String, String> headers) {
            String url = DownloadConfiguration.NULL_KEY_FOR_URL;
            addHeadersWithUrl(url, headers);
            return this;
        }

        protected InnerBuilder addHeadersWithUrl(String url, Map<String, String> headers) {
            if (UrlUtil.isUrl(url) && !MapUtil.isEmpty(headers)) {
                Map<String, String> existHeaders = this.mUrlHeaders.get(url);
                if (existHeaders == null) {
                    existHeaders = new HashMap<>();
                    this.mUrlHeaders.put(url, existHeaders);
                }
                existHeaders.putAll(headers);
            }
            return this;
        }

        @Override // org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public InnerBuilder configRetryDownloadTimes(int retryDownloadTimes) {
            String url = DownloadConfiguration.NULL_KEY_FOR_URL;
            configRetryDownloadTimesWithUrl(url, retryDownloadTimes);
            return this;
        }

        @Override // org.wlf.filedownloader.base.BaseDownloadConfigBuilder
        public InnerBuilder configConnectTimeout(int connectTimeout) {
            String url = DownloadConfiguration.NULL_KEY_FOR_URL;
            configConnectTimeoutWithUrl(url, connectTimeout);
            return this;
        }

        protected InnerBuilder configRetryDownloadTimesWithUrl(String url, int retryDownloadTimes) {
            if (!UrlUtil.isUrl(url)) {
                Log.m5160i(DownloadConfiguration.TAG, "configRetryDownloadTimes 配置下载失败重试次数失败，retryDownloadTimes：" + retryDownloadTimes);
            } else if (retryDownloadTimes >= 0 && retryDownloadTimes <= 10) {
                this.mRetryDownloadTimes.put(url, Integer.valueOf(retryDownloadTimes));
            } else if (retryDownloadTimes > 10) {
                this.mRetryDownloadTimes.put(url, 10);
            } else if (retryDownloadTimes >= 0) {
                Log.m5160i(DownloadConfiguration.TAG, "configRetryDownloadTimes 配置下载失败重试次数失败，retryDownloadTimes：" + retryDownloadTimes);
            } else {
                this.mRetryDownloadTimes.put(url, 0);
            }
            return this;
        }

        protected InnerBuilder configConnectTimeoutWithUrl(String url, int connectTimeout) {
            if (!UrlUtil.isUrl(url)) {
                Log.m5160i(DownloadConfiguration.TAG, "configConnectTimeout 配置连接超时时间失败，connectTimeout：" + connectTimeout);
            } else if (connectTimeout >= 5000 && connectTimeout <= 120000) {
                this.mConnectTimeout.put(url, Integer.valueOf(connectTimeout));
            } else if (connectTimeout > 120000) {
                this.mConnectTimeout.put(url, Integer.valueOf(BaseDownloadConfigBuilder.MAX_CONNECT_TIMEOUT));
            } else if (connectTimeout >= 5000) {
                Log.m5160i(DownloadConfiguration.TAG, "configConnectTimeout 配置连接超时时间失败，connectTimeout：" + connectTimeout);
            } else {
                this.mConnectTimeout.put(url, 5000);
            }
            return this;
        }

        protected InnerBuilder configRequestMethod(String requestMethod) {
            String url = DownloadConfiguration.NULL_KEY_FOR_URL;
            configRequestMethodWithUrl(url, requestMethod);
            return this;
        }

        protected InnerBuilder configRequestMethodWithUrl(String url, String requestMethod) {
            if (TextUtils.isEmpty(requestMethod)) {
                Log.m5160i(DownloadConfiguration.TAG, "configRequestMethodWithUrl 配置请求方法失败，requestMethod：" + requestMethod);
            } else {
                this.mRequestMethod.put(url, requestMethod);
            }
            return this;
        }

        public DownloadConfiguration build() {
            return new DownloadConfiguration(this);
        }
    }

    private DownloadConfiguration(InnerBuilder builder) {
        this.mBuilder = builder;
    }

    void initNullKeyForUrl(String url) {
        initNullKeyForUrlInternal(url, false);
    }

    private void initNullKeyForUrlInternal(String url, boolean replaceExistWithNullValue) {
        if (UrlUtil.isUrl(url) && this.mBuilder != null) {
            if (this.mBuilder.mUrlHeaders != null) {
                Map<String, String> nullHeaders = getHeaders(NULL_KEY_FOR_URL);
                Map<String, String> existUrlHeaders = getHeaders(url);
                HashMap map = new HashMap();
                if (!MapUtil.isEmpty(nullHeaders)) {
                    if (replaceExistWithNullValue) {
                        this.mBuilder.mUrlHeaders.remove(url);
                        map.putAll(nullHeaders);
                    } else if (!MapUtil.isEmpty(existUrlHeaders)) {
                        this.mBuilder.mUrlHeaders.remove(url);
                        map.putAll(nullHeaders);
                        map.putAll(existUrlHeaders);
                    } else {
                        map.putAll(nullHeaders);
                    }
                    Log.m5158e("wlf", "初始化headers：" + map.size());
                    this.mBuilder.mUrlHeaders.put(url, map);
                }
            }
            if (this.mBuilder.mRetryDownloadTimes != null) {
                int existUrlRetryDownloadTimes = getRetryDownloadTimes(url);
                int retryDownloadTimes = getRetryDownloadTimes(NULL_KEY_FOR_URL);
                if (!replaceExistWithNullValue) {
                    if (!this.mBuilder.mRetryDownloadTimes.containsKey(url)) {
                        this.mBuilder.mRetryDownloadTimes.put(url, Integer.valueOf(retryDownloadTimes));
                    }
                } else if (existUrlRetryDownloadTimes != 0) {
                    this.mBuilder.mRetryDownloadTimes.remove(url);
                    this.mBuilder.mRetryDownloadTimes.put(url, Integer.valueOf(retryDownloadTimes));
                } else if (!this.mBuilder.mRetryDownloadTimes.containsKey(url)) {
                    this.mBuilder.mRetryDownloadTimes.put(url, Integer.valueOf(retryDownloadTimes));
                }
            }
            if (this.mBuilder.mConnectTimeout != null) {
                int existUrlConnectTimeout = getConnectTimeout(url);
                int connectTimeout = getConnectTimeout(NULL_KEY_FOR_URL);
                if (!replaceExistWithNullValue) {
                    if (!this.mBuilder.mConnectTimeout.containsKey(url)) {
                        this.mBuilder.mConnectTimeout.put(url, Integer.valueOf(connectTimeout));
                    }
                } else if (existUrlConnectTimeout != 15000) {
                    this.mBuilder.mConnectTimeout.remove(url);
                    this.mBuilder.mConnectTimeout.put(url, Integer.valueOf(connectTimeout));
                } else if (!this.mBuilder.mConnectTimeout.containsKey(url)) {
                    this.mBuilder.mConnectTimeout.put(url, Integer.valueOf(connectTimeout));
                }
            }
            if (this.mBuilder.mRequestMethod != null) {
                String existUrlRequestMethod = getRequestMethod(url);
                String requestMethod = getRequestMethod(NULL_KEY_FOR_URL);
                if (!replaceExistWithNullValue) {
                    if (!this.mBuilder.mRequestMethod.containsKey(url)) {
                        this.mBuilder.mRequestMethod.put(url, requestMethod);
                    }
                } else if (!"GET".equalsIgnoreCase(existUrlRequestMethod)) {
                    this.mBuilder.mRequestMethod.remove(url);
                    this.mBuilder.mRequestMethod.put(url, requestMethod);
                } else if (!this.mBuilder.mRequestMethod.containsKey(url)) {
                    this.mBuilder.mRequestMethod.put(url, requestMethod);
                }
            }
        }
    }

    void initNullKeyForUrls(List<String> urls) {
        if (!CollectionUtil.isEmpty(urls) && this.mBuilder != null) {
            Log.m5158e("wlf", "初始化urls：" + urls.size());
            for (String url : urls) {
                if (UrlUtil.isUrl(url)) {
                    initNullKeyForUrlInternal(url, false);
                }
            }
        }
    }

    public Map<String, String> getHeaders(String url) {
        if (UrlUtil.isUrl(url) && this.mBuilder != null && this.mBuilder.mUrlHeaders != null) {
            return (Map) this.mBuilder.mUrlHeaders.get(url);
        }
        return null;
    }

    public int getRetryDownloadTimes(String url) {
        Integer retryDownloadTimes;
        if (!UrlUtil.isUrl(url) || this.mBuilder == null || this.mBuilder.mRetryDownloadTimes == null || (retryDownloadTimes = (Integer) this.mBuilder.mRetryDownloadTimes.get(url)) == null) {
            return 0;
        }
        return retryDownloadTimes.intValue();
    }

    public int getConnectTimeout(String url) {
        Integer connectTimeout;
        return (!UrlUtil.isUrl(url) || this.mBuilder == null || this.mBuilder.mConnectTimeout == null || (connectTimeout = (Integer) this.mBuilder.mConnectTimeout.get(url)) == null) ? BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT : connectTimeout.intValue();
    }

    public String getRequestMethod(String url) {
        if (UrlUtil.isUrl(url) && this.mBuilder != null && this.mBuilder.mRequestMethod != null) {
            String requestMethod = (String) this.mBuilder.mRequestMethod.get(url);
            if (TextUtils.isEmpty(requestMethod)) {
                return "GET";
            }
            return requestMethod;
        }
        return "GET";
    }
}

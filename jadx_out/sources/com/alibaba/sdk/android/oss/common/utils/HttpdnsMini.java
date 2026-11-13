package com.alibaba.sdk.android.oss.common.utils;

import com.alibaba.sdk.android.oss.common.OSSLog;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.constant.Constants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class HttpdnsMini {
    private static final String ACCOUNT_ID = "181345";
    private static final int EMPTY_RESULT_HOST_TTL = 30;
    private static final int MAX_HOLD_HOST_NUM = 100;
    private static final int MAX_THREAD_NUM = 5;
    private static final int RESOLVE_TIMEOUT_IN_SEC = 10;
    private static final String SERVER_IP = "203.107.1.1";
    private static final String TAG = "HttpDnsMini";
    private static HttpdnsMini instance;
    private ConcurrentMap<String, HostObject> hostManager = new ConcurrentHashMap();
    private ExecutorService pool = Executors.newFixedThreadPool(5);

    class HostObject {
        private String hostName;

        /* JADX INFO: renamed from: ip */
        private String f48ip;
        private long queryTime;
        private long ttl;

        HostObject() {
        }

        public String toString() {
            return "[hostName=" + getHostName() + ", ip=" + this.f48ip + ", ttl=" + getTtl() + ", queryTime=" + this.queryTime + "]";
        }

        public boolean isExpired() {
            return getQueryTime() + this.ttl < System.currentTimeMillis() / 1000;
        }

        public boolean isStillAvailable() {
            return (getQueryTime() + this.ttl) + 600 > System.currentTimeMillis() / 1000;
        }

        public String getIp() {
            return this.f48ip;
        }

        public void setIp(String str) {
            this.f48ip = str;
        }

        public void setHostName(String str) {
            this.hostName = str;
        }

        public String getHostName() {
            return this.hostName;
        }

        public long getTtl() {
            return this.ttl;
        }

        public void setTtl(long j) {
            this.ttl = j;
        }

        public long getQueryTime() {
            return this.queryTime;
        }

        public void setQueryTime(long j) {
            this.queryTime = j;
        }
    }

    class QueryHostTask implements Callable<String> {
        private boolean hasRetryed = false;
        private String hostName;

        public QueryHostTask(String str) {
            this.hostName = str;
        }

        /* JADX WARN: Code duplicated, block: B:51:0x015f  */
        /* JADX WARN: Code duplicated, block: B:53:0x0167 A[RETURN] */
        /* JADX WARN: Code duplicated, block: B:67:0x016b A[EXC_TOP_SPLITTER, SYNTHETIC] */
        @Override // java.util.concurrent.Callable
        public String call() throws Throwable {
            Throwable th;
            InputStream inputStream;
            Exception e;
            String str = "http://" + HttpdnsMini.SERVER_IP + InternalZipConstants.ZIP_FILE_SEPARATOR + HttpdnsMini.ACCOUNT_ID + "/d?host=" + this.hostName;
            OSSLog.logDebug("[httpdnsmini] - buildUrl: " + str);
            try {
                try {
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                    httpURLConnection.setConnectTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                    httpURLConnection.setReadTimeout(Constants.REQUEST_CODE_APP_INSTALL);
                    if (httpURLConnection.getResponseCode() != 200) {
                        OSSLog.logError("[httpdnsmini] - responseCodeNot 200, but: " + httpURLConnection.getResponseCode());
                        inputStream = null;
                    } else {
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            try {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                                StringBuilder sb = new StringBuilder();
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    if (line == null) {
                                        break;
                                    }
                                    sb.append(line);
                                }
                                JSONObject jSONObject = new JSONObject(sb.toString());
                                String string = jSONObject.getString("host");
                                long j = jSONObject.getLong("ttl");
                                JSONArray jSONArray = jSONObject.getJSONArray("ips");
                                OSSLog.logDebug("[httpdnsmini] - ips:" + jSONArray.toString());
                                if (string != null && jSONArray != null && jSONArray.length() > 0) {
                                    if (j == 0) {
                                        j = 30;
                                    }
                                    HostObject hostObject = HttpdnsMini.this.new HostObject();
                                    String string2 = jSONArray == null ? null : jSONArray.getString(0);
                                    hostObject.setHostName(string);
                                    hostObject.setTtl(j);
                                    hostObject.setIp(string2);
                                    hostObject.setQueryTime(System.currentTimeMillis() / 1000);
                                    OSSLog.logDebug("[httpdnsmini] - resolve result:" + hostObject.toString());
                                    if (HttpdnsMini.this.hostManager.size() < 100) {
                                        HttpdnsMini.this.hostManager.put(this.hostName, hostObject);
                                    }
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (IOException e2) {
                                            ThrowableExtension.printStackTrace(e2);
                                        }
                                    }
                                    return string2;
                                }
                            } catch (Exception e3) {
                                e = e3;
                                if (OSSLog.isEnableLog()) {
                                    ThrowableExtension.printStackTrace(e);
                                    OSSLog.logThrowable2Local(e);
                                }
                                if (inputStream != null) {
                                    inputStream.close();
                                }
                                if (!this.hasRetryed) {
                                    return null;
                                }
                                this.hasRetryed = true;
                                return call();
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (IOException e4) {
                                    ThrowableExtension.printStackTrace(e4);
                                }
                            }
                            throw th;
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e5) {
                    ThrowableExtension.printStackTrace(e5);
                }
            } catch (Exception e6) {
                e = e6;
                inputStream = null;
            } catch (Throwable th3) {
                th = th3;
                inputStream = null;
                if (inputStream != null) {
                    inputStream.close();
                }
                throw th;
            }
            if (!this.hasRetryed) {
                return null;
            }
            this.hasRetryed = true;
            return call();
        }
    }

    private HttpdnsMini() {
    }

    public static HttpdnsMini getInstance() {
        if (instance == null) {
            synchronized (HttpdnsMini.class) {
                if (instance == null) {
                    instance = new HttpdnsMini();
                }
            }
        }
        return instance;
    }

    public String getIpByHostAsync(String str) {
        HostObject hostObject = this.hostManager.get(str);
        if (hostObject == null || hostObject.isExpired()) {
            OSSLog.logDebug("[httpdnsmini] - refresh host: " + str);
            this.pool.submit(new QueryHostTask(str));
        }
        if (hostObject == null || !hostObject.isStillAvailable()) {
            return null;
        }
        return hostObject.getIp();
    }
}

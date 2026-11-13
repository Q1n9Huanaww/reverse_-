package org.wlf.filedownloader.file_download;

import android.text.TextUtils;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.github.kevinsawicki.http.HttpRequest;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.json.JSONObject;
import org.wlf.filedownloader.base.BaseDownloadConfigBuilder;
import org.wlf.filedownloader.base.Log;
import org.wlf.filedownloader.util.CollectionUtil;
import org.wlf.filedownloader.util.MapUtil;
import org.wlf.filedownloader.util.UrlUtil;

/* JADX INFO: loaded from: classes2.dex */
public class HttpConnectionHelper {
    private static final String TAG = HttpConnectionHelper.class.getSimpleName();

    public static HttpURLConnection createDetectConnection(String url, int connectTimeout, String charset, String requestMethod, Map<String, String> headers) throws Exception {
        RequestParam requestParam = new RequestParam(url, connectTimeout, charset);
        requestParam.setRequestMethod(requestMethod);
        requestParam.setHeaders(headers);
        return createHttpUrlConnection(requestParam);
    }

    public static HttpURLConnection createDownloadFileConnection(RequestParam requestParam) throws Exception {
        return createHttpUrlConnection(requestParam);
    }

    private static void initTrustSSL(HttpsURLConnection conn) {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: org.wlf.filedownloader.file_download.HttpConnectionHelper.1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() { // from class: org.wlf.filedownloader.file_download.HttpConnectionHelper.2
                @Override // javax.net.ssl.HostnameVerifier
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static HttpURLConnection createHttpUrlConnection(RequestParam requestParam) throws Exception {
        HttpURLConnection conn;
        if (requestParam == null) {
            return null;
        }
        Log.m5156d(TAG, "headBuffer，createHttpUrlConnection，发送的请求参数：" + requestParam.toString());
        String encodedUrl = UrlUtil.getASCIIEncodedUrl(requestParam.mUrl, requestParam.mCharset);
        if (TextUtils.isEmpty(encodedUrl)) {
            throw new IllegalAccessException("URL Illegal !");
        }
        URL url = new URL(encodedUrl);
        if (encodedUrl.toLowerCase().startsWith("https")) {
            HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
            initTrustSSL(httpsConn);
            conn = httpsConn;
        } else {
            conn = (HttpURLConnection) url.openConnection();
        }
        conn.setConnectTimeout(requestParam.mConnectTimeout);
        conn.setReadTimeout(requestParam.mConnectTimeout);
        conn.setRequestMethod(requestParam.mRequestMethod);
        conn.setInstanceFollowRedirects(false);
        if (!MapUtil.isEmpty(requestParam.mHeaders)) {
            Set<String> keys = requestParam.mHeaders.keySet();
            Log.m5160i(TAG, "自定义头信息大小：" + keys.size());
            for (String key : keys) {
                if (!TextUtils.isEmpty(key)) {
                    String value = (String) requestParam.mHeaders.get(key);
                    conn.setRequestProperty(key, value);
                    Log.m5160i(TAG, "添加自定义头信息，url：" + url + "，key：" + key + "，value：" + value);
                }
            }
        }
        conn.setRequestProperty(HttpRequest.HEADER_ACCEPT_ENCODING, "identity");
        if (!TextUtils.isEmpty(requestParam.mCharset)) {
            conn.setRequestProperty("Charset", requestParam.mCharset);
        }
        if (requestParam.mRangeStartPos > 0) {
            if (requestParam.mRangeEndPos > 0 && requestParam.mRangeEndPos > requestParam.mRangeStartPos) {
                conn.setRequestProperty(HttpHeaders.RANGE, "bytes=" + requestParam.mRangeStartPos + "-" + requestParam.mRangeEndPos);
            } else {
                conn.setRequestProperty(HttpHeaders.RANGE, "bytes=" + requestParam.mRangeStartPos + "-");
            }
            if (!TextUtils.isEmpty(requestParam.mETag)) {
                conn.setRequestProperty("If-Range", requestParam.mETag);
            } else if (!TextUtils.isEmpty(requestParam.mLastModified)) {
                conn.setRequestProperty("If-Range", requestParam.mLastModified);
            }
        }
        conn.connect();
        return conn;
    }

    public static class RequestParam {
        private String mCharset;
        private int mConnectTimeout;
        private String mETag;
        private Map<String, String> mHeaders;
        private String mLastModified;
        private long mRangeEndPos;
        private long mRangeStartPos;
        private String mRequestMethod;
        private String mUrl;

        public RequestParam(String url, int connectTimeout, String charset) {
            this.mConnectTimeout = BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT;
            this.mRangeStartPos = -1L;
            this.mRangeEndPos = -1L;
            this.mRequestMethod = "GET";
            this.mUrl = url;
            this.mConnectTimeout = connectTimeout;
            this.mCharset = charset;
        }

        public RequestParam(String url, int connectTimeout, String charset, long rangeStartPos, long rangeEndPos, String ETag, String lastModified) {
            this.mConnectTimeout = BaseDownloadConfigBuilder.DEFAULT_CONNECT_TIMEOUT;
            this.mRangeStartPos = -1L;
            this.mRangeEndPos = -1L;
            this.mRequestMethod = "GET";
            this.mUrl = url;
            this.mConnectTimeout = connectTimeout;
            this.mCharset = charset;
            this.mRangeStartPos = rangeStartPos;
            this.mRangeEndPos = rangeEndPos;
            this.mETag = ETag;
            this.mLastModified = lastModified;
        }

        public void setUrl(String url) {
            this.mUrl = url;
        }

        public void setRequestMethod(String requestMethod) {
            if (!TextUtils.isEmpty(requestMethod)) {
                this.mRequestMethod = requestMethod;
            }
        }

        public void setHeaders(Map<String, String> headers) {
            if (headers != null) {
                this.mHeaders = headers;
            }
        }

        public String toString() {
            return "RequestParam{mUrl='" + this.mUrl + "', mConnectTimeout=" + this.mConnectTimeout + ", mCharset='" + this.mCharset + "', mRangeStartPos=" + this.mRangeStartPos + ", mRangeEndPos=" + this.mRangeEndPos + ", mETag='" + this.mETag + "', mLastModified='" + this.mLastModified + "', mRequestMethod='" + this.mRequestMethod + "', mHeaders=" + this.mHeaders + '}';
        }
    }

    public static long getFileSizeFromResponseHeader(Map<String, List<String>> responseHeaderMap) {
        if (MapUtil.isEmpty(responseHeaderMap)) {
            return -1L;
        }
        long fileSize = getFileSizeFromCommonServerResponseHeader(responseHeaderMap);
        if (fileSize <= 0) {
            return getFileSizeFromPhpServerResponseHeader(responseHeaderMap);
        }
        return fileSize;
    }

    private static long getFileSizeFromCommonServerResponseHeader(Map<String, List<String>> responseHeaderMap) {
        if (MapUtil.isEmpty(responseHeaderMap)) {
            return -1L;
        }
        List<String> contentLengths = responseHeaderMap.get("Content-Length");
        if (CollectionUtil.isEmpty(contentLengths)) {
            return -1L;
        }
        String contentLengthStr = contentLengths.get(0);
        if (TextUtils.isEmpty(contentLengthStr)) {
            return -1L;
        }
        try {
            long fileSize = Long.parseLong(contentLengthStr);
            return fileSize;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    private static long getFileSizeFromPhpServerResponseHeader(Map<String, List<String>> responseHeaderMap) {
        if (MapUtil.isEmpty(responseHeaderMap)) {
            return -1L;
        }
        List<String> contentLengths = responseHeaderMap.get("Accept-Length");
        if (CollectionUtil.isEmpty(contentLengths)) {
            return -1L;
        }
        String contentLengthStr = contentLengths.get(0);
        if (TextUtils.isEmpty(contentLengthStr)) {
            return -1L;
        }
        try {
            long fileSize = Long.parseLong(contentLengthStr);
            return fileSize;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return -1L;
        }
    }

    public static String getFileNameFromResponseHeader(Map<String, List<String>> responseHeaderMap) {
        if (MapUtil.isEmpty(responseHeaderMap)) {
            return null;
        }
        String fileName = getFileNameFromCommonServerResponseHeader(responseHeaderMap);
        if (TextUtils.isEmpty(fileName)) {
            return getFileNameFromPhpServerResponseHeader(responseHeaderMap);
        }
        return fileName;
    }

    private static String getFileNameFromCommonServerResponseHeader(Map<String, List<String>> responseHeaderMap) {
        if (MapUtil.isEmpty(responseHeaderMap)) {
        }
        return null;
    }

    private static String getFileNameFromPhpServerResponseHeader(Map<String, List<String>> responseHeaderMap) {
        int start;
        if (MapUtil.isEmpty(responseHeaderMap)) {
            return null;
        }
        List<String> contentDispositions = responseHeaderMap.get("Content-Disposition");
        if (CollectionUtil.isEmpty(contentDispositions)) {
            return null;
        }
        for (String contentDisposition : contentDispositions) {
            if (!TextUtils.isEmpty(contentDisposition) && contentDisposition.contains("filename=") && (start = contentDisposition.lastIndexOf("=")) != -1) {
                return contentDisposition.substring(start + 1, contentDisposition.length());
            }
        }
        return null;
    }

    public static String getLastModifiedFromResponseHeader(Map<String, List<String>> responseHeaderMap) {
        if (MapUtil.isEmpty(responseHeaderMap)) {
            return null;
        }
        List<String> contentLengths = responseHeaderMap.get("Last-Modified");
        if (CollectionUtil.isEmpty(contentLengths)) {
            return null;
        }
        return contentLengths.get(0);
    }

    public static String getStringHeaders(Map<String, List<String>> headers) {
        try {
            if (MapUtil.isEmpty(headers)) {
                return null;
            }
            Map<String, ArrayList<String>> copyMap = getWritableMap(headers);
            JSONObject jsonObject = new JSONObject(copyMap);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Map<String, ArrayList<String>> getWritableMap(Map<String, ? extends List<String>> readOnlyMap) {
        Map<String, ArrayList<String>> readAndWriteMap = new TreeMap<>();
        Set<String> keys = readOnlyMap.keySet();
        for (String key : keys) {
            if (key != null) {
                ArrayList<String> readAndWriteList = new ArrayList<>();
                List<String> readOnlyValues = readOnlyMap.get(key);
                for (String value : readOnlyValues) {
                    readAndWriteList.add(value);
                }
                readAndWriteMap.put(key, readAndWriteList);
            }
        }
        return readAndWriteMap;
    }
}

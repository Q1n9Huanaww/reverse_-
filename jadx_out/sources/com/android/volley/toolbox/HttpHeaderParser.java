package com.android.volley.toolbox;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.github.kevinsawicki.http.HttpRequest;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

/* JADX INFO: loaded from: classes.dex */
public class HttpHeaderParser {
    /* JADX WARN: Code duplicated, block: B:30:0x0081  */
    public static Cache.Entry parseCacheHeaders(NetworkResponse networkResponse) {
        long j;
        boolean z;
        long j2;
        boolean z2;
        long j3;
        long j4;
        long jCurrentTimeMillis = System.currentTimeMillis();
        Map<String, String> map = networkResponse.headers;
        String str = map.get("Date");
        long dateAsEpoch = str != null ? parseDateAsEpoch(str) : 0L;
        String str2 = map.get("Cache-Control");
        if (str2 != null) {
            j = 0;
            z = false;
            j2 = 0;
            for (String str3 : str2.split(",")) {
                String strTrim = str3.trim();
                if (strTrim.equals("no-cache") || strTrim.equals("no-store")) {
                    z = true;
                } else if (strTrim.startsWith("max-age=")) {
                    try {
                        j = Long.parseLong(strTrim.substring(8));
                    } catch (Exception unused) {
                    }
                } else if (strTrim.startsWith("stale-while-revalidate=")) {
                    j2 = Long.parseLong(strTrim.substring(23));
                } else if (strTrim.equals("must-revalidate") || strTrim.equals("proxy-revalidate")) {
                    z = true;
                }
            }
            z2 = true;
        } else {
            j = 0;
            z = false;
            j2 = 0;
            z2 = false;
        }
        String str4 = map.get("Expires");
        long dateAsEpoch2 = str4 != null ? parseDateAsEpoch(str4) : 0L;
        String str5 = map.get("Last-Modified");
        long dateAsEpoch3 = str5 != null ? parseDateAsEpoch(str5) : 0L;
        String str6 = map.get("ETag");
        if (z2) {
            long j5 = jCurrentTimeMillis + (j * 1000);
            j3 = z ? j5 : j5 + (j2 * 1000);
            j4 = j5;
        } else if (dateAsEpoch <= 0 || dateAsEpoch2 < dateAsEpoch) {
            j3 = 0;
            j4 = 0;
        } else {
            j4 = jCurrentTimeMillis + (dateAsEpoch2 - dateAsEpoch);
            j3 = j4;
        }
        Cache.Entry entry = new Cache.Entry();
        entry.data = networkResponse.data;
        entry.etag = str6;
        entry.softTtl = j4;
        entry.ttl = j3;
        entry.serverDate = dateAsEpoch;
        entry.lastModified = dateAsEpoch3;
        entry.responseHeaders = map;
        return entry;
    }

    public static long parseDateAsEpoch(String str) {
        try {
            return DateUtils.parseDate(str).getTime();
        } catch (DateParseException unused) {
            return 0L;
        }
    }

    public static String parseCharset(Map<String, String> map, String str) {
        String str2 = map.get("Content-Type");
        if (str2 != null) {
            String[] strArrSplit = str2.split(";");
            for (int i = 1; i < strArrSplit.length; i++) {
                String[] strArrSplit2 = strArrSplit[i].trim().split("=");
                if (strArrSplit2.length == 2 && strArrSplit2[0].equals(HttpRequest.PARAM_CHARSET)) {
                    return strArrSplit2[1];
                }
            }
        }
        return str;
    }

    public static String parseCharset(Map<String, String> map) {
        return parseCharset(map, "ISO-8859-1");
    }
}

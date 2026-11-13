package com.p004b.p005a.p006a.p011e;

import com.alibaba.sdk.android.oss.common.RequestParameters;
import com.p004b.p015b.C0639i;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.b.a.a.e.d */
/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes.dex */
final class C0541d {

    /* JADX INFO: renamed from: a */
    static final C0540c[] f297a = {new C0540c(C0540c.f293f, ""), new C0540c(C0540c.f290c, "GET"), new C0540c(C0540c.f290c, "POST"), new C0540c(C0540c.f291d, InternalZipConstants.ZIP_FILE_SEPARATOR), new C0540c(C0540c.f291d, "/index.html"), new C0540c(C0540c.f292e, "http"), new C0540c(C0540c.f292e, "https"), new C0540c(C0540c.f289b, "200"), new C0540c(C0540c.f289b, "204"), new C0540c(C0540c.f289b, "206"), new C0540c(C0540c.f289b, "304"), new C0540c(C0540c.f289b, "400"), new C0540c(C0540c.f289b, "404"), new C0540c(C0540c.f289b, "500"), new C0540c("accept-charset", ""), new C0540c("accept-encoding", "gzip, deflate"), new C0540c("accept-language", ""), new C0540c("accept-ranges", ""), new C0540c("accept", ""), new C0540c("access-control-allow-origin", ""), new C0540c("age", ""), new C0540c("allow", ""), new C0540c("authorization", ""), new C0540c("cache-control", ""), new C0540c("content-disposition", ""), new C0540c("content-encoding", ""), new C0540c("content-language", ""), new C0540c("content-length", ""), new C0540c("content-location", ""), new C0540c("content-range", ""), new C0540c("content-type", ""), new C0540c("cookie", ""), new C0540c("date", ""), new C0540c("etag", ""), new C0540c("expect", ""), new C0540c("expires", ""), new C0540c("from", ""), new C0540c("host", ""), new C0540c("if-match", ""), new C0540c("if-modified-since", ""), new C0540c("if-none-match", ""), new C0540c("if-range", ""), new C0540c("if-unmodified-since", ""), new C0540c("last-modified", ""), new C0540c("link", ""), new C0540c(RequestParameters.SUBRESOURCE_LOCATION, ""), new C0540c("max-forwards", ""), new C0540c("proxy-authenticate", ""), new C0540c("proxy-authorization", ""), new C0540c("range", ""), new C0540c(RequestParameters.SUBRESOURCE_REFERER, ""), new C0540c("refresh", ""), new C0540c("retry-after", ""), new C0540c("server", ""), new C0540c("set-cookie", ""), new C0540c("strict-transport-security", ""), new C0540c("transfer-encoding", ""), new C0540c("user-agent", ""), new C0540c("vary", ""), new C0540c("via", ""), new C0540c("www-authenticate", "")};

    /* JADX INFO: renamed from: b */
    static final Map<C0639i, Integer> f298b;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f297a.length);
        for (int i = 0; i < f297a.length; i++) {
            if (!linkedHashMap.containsKey(f297a[i].f294g)) {
                linkedHashMap.put(f297a[i].f294g, Integer.valueOf(i));
            }
        }
        f298b = Collections.unmodifiableMap(linkedHashMap);
    }

    /* JADX INFO: renamed from: a */
    static C0639i m232a(C0639i c0639i) throws IOException {
        int iMo590g = c0639i.mo590g();
        for (int i = 0; i < iMo590g; i++) {
            byte bMo579a = c0639i.mo579a(i);
            if (bMo579a >= 65 && bMo579a <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + c0639i.mo581a());
            }
        }
        return c0639i;
    }
}

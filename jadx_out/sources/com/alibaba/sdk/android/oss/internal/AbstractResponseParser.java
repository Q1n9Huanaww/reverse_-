package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;
import okhttp3.Headers;
import okhttp3.Response;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractResponseParser<T extends OSSResult> implements ResponseParser {
    public boolean needCloseResponse() {
        return true;
    }

    abstract T parseData(Response response, T t) throws Exception;

    @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
    public T parse(Response response) throws IOException {
        try {
            try {
                T t = (T) ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).newInstance();
                if (t != null) {
                    t.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    t.setStatusCode(response.code());
                    t.setResponseHeader(parseResponseHeader(response));
                    t = (T) parseData(response, t);
                }
                if (needCloseResponse()) {
                    safeCloseResponse(response);
                }
                return t;
            } catch (Exception e) {
                IOException iOException = new IOException(e.getMessage(), e);
                ThrowableExtension.printStackTrace(e);
                OSSLog.logThrowable2Local(e);
                throw iOException;
            }
        } catch (Throwable th) {
            if (needCloseResponse()) {
                safeCloseResponse(response);
            }
            throw th;
        }
    }

    public static void safeCloseResponse(Response response) {
        try {
            response.body().close();
        } catch (Exception unused) {
        }
    }

    private Map<String, String> parseResponseHeader(Response response) {
        HashMap map = new HashMap();
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            map.put(headers.name(i), headers.value(i));
        }
        return map;
    }
}

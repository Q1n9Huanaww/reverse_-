package okhttp366.internal.http;

import com.android.volley.toolbox.HttpClientStack;
import com.github.kevinsawicki.http.HttpRequest;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpMethod {
    public static boolean invalidatesCache(String method) {
        return method.equals("POST") || method.equals(HttpClientStack.HttpPatch.METHOD_NAME) || method.equals(HttpRequest.METHOD_PUT) || method.equals(HttpRequest.METHOD_DELETE) || method.equals("MOVE");
    }

    public static boolean requiresRequestBody(String method) {
        return method.equals("POST") || method.equals(HttpRequest.METHOD_PUT) || method.equals(HttpClientStack.HttpPatch.METHOD_NAME) || method.equals("PROPPATCH") || method.equals("REPORT");
    }

    public static boolean permitsRequestBody(String method) {
        return (method.equals("GET") || method.equals("HEAD")) ? false : true;
    }

    public static boolean redirectsWithBody(String method) {
        return method.equals("PROPFIND");
    }

    public static boolean redirectsToGet(String method) {
        return !method.equals("PROPFIND");
    }

    private HttpMethod() {
    }
}

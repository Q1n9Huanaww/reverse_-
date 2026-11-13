package retrofit266;

import java.util.Objects;
import javax.annotation.Nullable;
import okhttp366.Headers;
import okhttp366.Protocol;
import okhttp366.Request;
import okhttp366.ResponseBody;

/* JADX INFO: loaded from: classes2.dex */
public final class Response<T> {

    @Nullable
    private final T body;

    @Nullable
    private final ResponseBody errorBody;
    private final okhttp366.Response rawResponse;

    public static <T> Response<T> success(@Nullable T body) {
        return success(body, new okhttp366.Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    public static <T> Response<T> success(int code, @Nullable T body) {
        if (code < 200 || code >= 300) {
            throw new IllegalArgumentException("code < 200 or >= 300: " + code);
        }
        return success(body, new okhttp366.Response.Builder().code(code).message("Response.success()").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    public static <T> Response<T> success(@Nullable T body, Headers headers) {
        Objects.requireNonNull(headers, "headers == null");
        return success(body, new okhttp366.Response.Builder().code(200).message("OK").protocol(Protocol.HTTP_1_1).headers(headers).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    public static <T> Response<T> success(@Nullable T body, okhttp366.Response rawResponse) {
        Objects.requireNonNull(rawResponse, "rawResponse == null");
        if (!rawResponse.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse must be successful response");
        }
        return new Response<>(rawResponse, body, null);
    }

    public static <T> Response<T> error(int code, ResponseBody body) {
        Objects.requireNonNull(body, "body == null");
        if (code < 400) {
            throw new IllegalArgumentException("code < 400: " + code);
        }
        return error(body, new okhttp366.Response.Builder().body(new OkHttpCall.NoContentResponseBody(body.contentType(), body.contentLength())).code(code).message("Response.error()").protocol(Protocol.HTTP_1_1).request(new Request.Builder().url("http://localhost/").build()).build());
    }

    public static <T> Response<T> error(ResponseBody body, okhttp366.Response rawResponse) {
        Objects.requireNonNull(body, "body == null");
        Objects.requireNonNull(rawResponse, "rawResponse == null");
        if (rawResponse.isSuccessful()) {
            throw new IllegalArgumentException("rawResponse should not be successful response");
        }
        return new Response<>(rawResponse, null, body);
    }

    private Response(okhttp366.Response rawResponse, @Nullable T body, @Nullable ResponseBody errorBody) {
        this.rawResponse = rawResponse;
        this.body = body;
        this.errorBody = errorBody;
    }

    public okhttp366.Response raw() {
        return this.rawResponse;
    }

    public int code() {
        return this.rawResponse.code();
    }

    public String message() {
        return this.rawResponse.message();
    }

    public Headers headers() {
        return this.rawResponse.headers();
    }

    public boolean isSuccessful() {
        return this.rawResponse.isSuccessful();
    }

    @Nullable
    public T body() {
        return this.body;
    }

    @Nullable
    public ResponseBody errorBody() {
        return this.errorBody;
    }

    public String toString() {
        return this.rawResponse.toString();
    }
}

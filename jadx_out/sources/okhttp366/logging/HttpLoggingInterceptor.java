package okhttp366.logging;

import com.github.kevinsawicki.http.HttpRequest;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import okhttp366.Connection;
import okhttp366.Headers;
import okhttp366.Interceptor;
import okhttp366.MediaType;
import okhttp366.Request;
import okhttp366.RequestBody;
import okhttp366.Response;
import okhttp366.ResponseBody;
import okhttp366.internal.http.HttpHeaders;
import okhttp366.internal.platform.Platform;
import okio66.Buffer;
import okio66.BufferedSource;
import okio66.GzipSource;

/* JADX INFO: loaded from: classes2.dex */
public final class HttpLoggingInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private volatile Set<String> headersToRedact;
    private volatile Level level;
    private final Logger logger;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface Logger {
        public static final Logger DEFAULT = new Logger() { // from class: okhttp366.logging.HttpLoggingInterceptor.Logger.1
            @Override // okhttp366.logging.HttpLoggingInterceptor.Logger
            public void log(String message) {
                Platform.get().log(4, message, null);
            }
        };

        void log(String str);
    }

    public HttpLoggingInterceptor() {
        this(Logger.DEFAULT);
    }

    public HttpLoggingInterceptor(Logger logger) {
        this.headersToRedact = Collections.emptySet();
        this.level = Level.NONE;
        this.logger = logger;
    }

    public void redactHeader(String name) {
        Set<String> newHeadersToRedact = new TreeSet<>((Comparator<? super String>) String.CASE_INSENSITIVE_ORDER);
        newHeadersToRedact.addAll(this.headersToRedact);
        newHeadersToRedact.add(name);
        this.headersToRedact = newHeadersToRedact;
    }

    public HttpLoggingInterceptor setLevel(Level level) {
        if (level == null) {
            throw new NullPointerException("level == null. Use Level.NONE instead.");
        }
        this.level = level;
        return this;
    }

    public Level getLevel() {
        return this.level;
    }

    @Override // okhttp366.Interceptor
    public Response intercept(Interceptor.Chain chain) throws Exception {
        String requestStartMessage;
        char c;
        String str;
        Level level = this.level;
        Request request = chain.request();
        if (level == Level.NONE) {
            return chain.proceed(request);
        }
        boolean logBody = level == Level.BODY;
        boolean logHeaders = logBody || level == Level.HEADERS;
        RequestBody requestBody = request.body();
        boolean hasRequestBody = requestBody != null;
        Connection connection = chain.connection();
        String str2 = "";
        String requestStartMessage2 = "--> " + request.method() + ' ' + request.url() + (connection != null ? " " + connection.protocol() : "");
        if (!logHeaders && hasRequestBody) {
            requestStartMessage = requestStartMessage2 + " (" + requestBody.contentLength() + "-byte body)";
        } else {
            requestStartMessage = requestStartMessage2;
        }
        this.logger.log(requestStartMessage);
        if (!logHeaders) {
            level = level;
            str2 = str2;
        } else {
            if (hasRequestBody) {
                if (requestBody.contentType() != null) {
                    this.logger.log("Content-Type: " + requestBody.contentType());
                }
                if (requestBody.contentLength() != -1) {
                    this.logger.log("Content-Length: " + requestBody.contentLength());
                }
            }
            Headers headers = request.headers();
            int i = 0;
            int count = headers.size();
            while (i < count) {
                String name = headers.name(i);
                Connection connection2 = connection;
                if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
                    logHeader(headers, i);
                }
                i++;
                connection = connection2;
            }
            if (!logBody || !hasRequestBody) {
                this.logger.log("--> END " + request.method());
            } else if (bodyHasUnknownEncoding(request.headers())) {
                this.logger.log("--> END " + request.method() + " (encoded body omitted)");
                str2 = str2;
            } else {
                Buffer buffer = new Buffer();
                requestBody.writeTo(buffer);
                Charset charset = UTF8;
                MediaType contentType = requestBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                str2 = str2;
                this.logger.log(str2);
                if (isPlaintext(buffer)) {
                    this.logger.log(buffer.readString(charset));
                    this.logger.log("--> END " + request.method() + " (" + requestBody.contentLength() + "-byte body)");
                } else {
                    this.logger.log("--> END " + request.method() + " (binary " + requestBody.contentLength() + "-byte body omitted)");
                }
            }
        }
        long startNs = System.nanoTime();
        try {
            Response response = chain.proceed(request);
            long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);
            ResponseBody responseBody = response.body();
            long contentLength = responseBody.contentLength();
            String bodySize = contentLength != -1 ? contentLength + "-byte" : "unknown-length";
            Logger logger = this.logger;
            StringBuilder sbAppend = new StringBuilder().append("<-- ").append(response.code());
            if (response.message().isEmpty()) {
                str = str2;
                c = ' ';
            } else {
                c = ' ';
                str = ' ' + response.message();
            }
            logger.log(sbAppend.append(str).append(c).append(response.request().url()).append(" (").append(tookMs).append("ms").append(!logHeaders ? ", " + bodySize + " body" : str2).append(')').toString());
            if (logHeaders) {
                Headers headers2 = response.headers();
                int count2 = headers2.size();
                for (int i2 = 0; i2 < count2; i2++) {
                    logHeader(headers2, i2);
                }
                if (!logBody || !HttpHeaders.hasBody(response)) {
                    this.logger.log("<-- END HTTP");
                } else if (bodyHasUnknownEncoding(response.headers())) {
                    this.logger.log("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource source = responseBody.source();
                    source.request(Long.MAX_VALUE);
                    Buffer buffer2 = source.buffer();
                    Long gzippedLength = null;
                    if (HttpRequest.ENCODING_GZIP.equalsIgnoreCase(headers2.get("Content-Encoding"))) {
                        gzippedLength = Long.valueOf(buffer2.size());
                        GzipSource gzippedResponseBody = null;
                        try {
                            try {
                                gzippedResponseBody = new GzipSource(buffer2.clone());
                                buffer2 = new Buffer();
                                buffer2.writeAll(gzippedResponseBody);
                                gzippedResponseBody.close();
                            } catch (Throwable th) {
                                th = th;
                                if (gzippedResponseBody != null) {
                                    gzippedResponseBody.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    Charset charset2 = UTF8;
                    MediaType contentType2 = responseBody.contentType();
                    if (contentType2 != null) {
                        charset2 = contentType2.charset(UTF8);
                    }
                    if (!isPlaintext(buffer2)) {
                        this.logger.log(str2);
                        this.logger.log("<-- END HTTP (binary " + buffer2.size() + "-byte body omitted)");
                        return response;
                    }
                    if (contentLength != 0) {
                        this.logger.log(str2);
                        this.logger.log(buffer2.clone().readString(charset2));
                    }
                    if (gzippedLength == null) {
                        this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte body)");
                    } else {
                        this.logger.log("<-- END HTTP (" + buffer2.size() + "-byte, " + gzippedLength + "-gzipped-byte body)");
                    }
                }
            }
            return response;
        } catch (Exception e) {
            this.logger.log("<-- HTTP FAILED: " + e);
            throw e;
        }
    }

    private void logHeader(Headers headers, int i) {
        String value = this.headersToRedact.contains(headers.name(i)) ? "██" : headers.value(i);
        this.logger.log(headers.name(i) + ": " + value);
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64L;
            buffer.copyTo(prefix, 0L, byteCount);
            for (int i = 0; i < 16 && !prefix.exhausted(); i++) {
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false;
        }
    }

    private static boolean bodyHasUnknownEncoding(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return (contentEncoding == null || contentEncoding.equalsIgnoreCase("identity") || contentEncoding.equalsIgnoreCase(HttpRequest.ENCODING_GZIP)) ? false : true;
    }
}

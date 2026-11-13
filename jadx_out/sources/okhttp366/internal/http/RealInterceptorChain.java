package okhttp366.internal.http;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp366.Call;
import okhttp366.Connection;
import okhttp366.Interceptor;
import okhttp366.Request;
import okhttp366.Response;
import okhttp366.internal.Util;
import okhttp366.internal.connection.Exchange;
import okhttp366.internal.connection.Transmitter;

/* JADX INFO: loaded from: classes2.dex */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final Call call;
    private int calls;
    private final int connectTimeout;

    @Nullable
    private final Exchange exchange;
    private final int index;
    private final List<Interceptor> interceptors;
    private final int readTimeout;
    private final Request request;
    private final Transmitter transmitter;
    private final int writeTimeout;

    public RealInterceptorChain(List<Interceptor> interceptors, Transmitter transmitter, @Nullable Exchange exchange, int index, Request request, Call call, int connectTimeout, int readTimeout, int writeTimeout) {
        this.interceptors = interceptors;
        this.transmitter = transmitter;
        this.exchange = exchange;
        this.index = index;
        this.request = request;
        this.call = call;
        this.connectTimeout = connectTimeout;
        this.readTimeout = readTimeout;
        this.writeTimeout = writeTimeout;
    }

    @Override // okhttp366.Interceptor.Chain
    @Nullable
    public Connection connection() {
        if (this.exchange != null) {
            return this.exchange.connection();
        }
        return null;
    }

    @Override // okhttp366.Interceptor.Chain
    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    @Override // okhttp366.Interceptor.Chain
    public Interceptor.Chain withConnectTimeout(int timeout, TimeUnit unit) {
        int millis = Util.checkDuration("timeout", timeout, unit);
        return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, millis, this.readTimeout, this.writeTimeout);
    }

    @Override // okhttp366.Interceptor.Chain
    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    @Override // okhttp366.Interceptor.Chain
    public Interceptor.Chain withReadTimeout(int timeout, TimeUnit unit) {
        int millis = Util.checkDuration("timeout", timeout, unit);
        return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, millis, this.writeTimeout);
    }

    @Override // okhttp366.Interceptor.Chain
    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    @Override // okhttp366.Interceptor.Chain
    public Interceptor.Chain withWriteTimeout(int timeout, TimeUnit unit) {
        int millis = Util.checkDuration("timeout", timeout, unit);
        return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, this.readTimeout, millis);
    }

    public Transmitter transmitter() {
        return this.transmitter;
    }

    public Exchange exchange() {
        if (this.exchange == null) {
            throw new IllegalStateException();
        }
        return this.exchange;
    }

    @Override // okhttp366.Interceptor.Chain
    public Call call() {
        return this.call;
    }

    @Override // okhttp366.Interceptor.Chain
    public Request request() {
        return this.request;
    }

    @Override // okhttp366.Interceptor.Chain
    public Response proceed(Request request) throws IOException {
        return proceed(request, this.transmitter, this.exchange);
    }

    public Response proceed(Request request, Transmitter transmitter, @Nullable Exchange exchange) throws IOException {
        if (this.index < this.interceptors.size()) {
            this.calls++;
            if (this.exchange != null && !this.exchange.connection().supportsUrl(request.url())) {
                throw new IllegalStateException("network interceptor " + this.interceptors.get(this.index - 1) + " must retain the same host and port");
            }
            if (this.exchange != null && this.calls > 1) {
                throw new IllegalStateException("network interceptor " + this.interceptors.get(this.index - 1) + " must call proceed() exactly once");
            }
            RealInterceptorChain next = new RealInterceptorChain(this.interceptors, transmitter, exchange, this.index + 1, request, this.call, this.connectTimeout, this.readTimeout, this.writeTimeout);
            Interceptor interceptor = this.interceptors.get(this.index);
            Response response = interceptor.intercept(next);
            if (exchange != null && this.index + 1 < this.interceptors.size() && next.calls != 1) {
                throw new IllegalStateException("network interceptor " + interceptor + " must call proceed() exactly once");
            }
            if (response == null) {
                throw new NullPointerException("interceptor " + interceptor + " returned null");
            }
            if (response.body() == null) {
                throw new IllegalStateException("interceptor " + interceptor + " returned a response with no body");
            }
            return response;
        }
        throw new AssertionError();
    }
}

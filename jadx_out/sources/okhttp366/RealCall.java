package okhttp366;

import android.support.v4.app.NotificationCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import okhttp366.internal.NamedRunnable;
import okhttp366.internal.Util;
import okhttp366.internal.cache.CacheInterceptor;
import okhttp366.internal.connection.ConnectInterceptor;
import okhttp366.internal.connection.Transmitter;
import okhttp366.internal.http.BridgeInterceptor;
import okhttp366.internal.http.CallServerInterceptor;
import okhttp366.internal.http.RealInterceptorChain;
import okhttp366.internal.http.RetryAndFollowUpInterceptor;
import okhttp366.internal.platform.Platform;
import okio66.Timeout;

/* JADX INFO: loaded from: classes2.dex */
final class RealCall implements Call {
    final OkHttpClient client;
    private boolean executed;
    final boolean forWebSocket;
    final Request originalRequest;
    private Transmitter transmitter;

    private RealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
        this.client = client;
        this.originalRequest = originalRequest;
        this.forWebSocket = forWebSocket;
    }

    static RealCall newRealCall(OkHttpClient client, Request originalRequest, boolean forWebSocket) {
        RealCall call = new RealCall(client, originalRequest, forWebSocket);
        call.transmitter = new Transmitter(client, call);
        return call;
    }

    @Override // okhttp366.Call
    public Request request() {
        return this.originalRequest;
    }

    @Override // okhttp366.Call
    public Response execute() throws IOException {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.transmitter.timeoutEnter();
        this.transmitter.callStart();
        try {
            this.client.dispatcher().executed(this);
            return getResponseWithInterceptorChain();
        } finally {
            this.client.dispatcher().finished(this);
        }
    }

    @Override // okhttp366.Call
    public void enqueue(Callback responseCallback) {
        synchronized (this) {
            if (this.executed) {
                throw new IllegalStateException("Already Executed");
            }
            this.executed = true;
        }
        this.transmitter.callStart();
        this.client.dispatcher().enqueue(new AsyncCall(responseCallback));
    }

    @Override // okhttp366.Call
    public void cancel() {
        this.transmitter.cancel();
    }

    @Override // okhttp366.Call
    public Timeout timeout() {
        return this.transmitter.timeout();
    }

    @Override // okhttp366.Call
    public synchronized boolean isExecuted() {
        return this.executed;
    }

    @Override // okhttp366.Call
    public boolean isCanceled() {
        return this.transmitter.isCanceled();
    }

    @Override // okhttp366.Call
    public RealCall clone() {
        return newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    final class AsyncCall extends NamedRunnable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private volatile AtomicInteger callsPerHost;
        private final Callback responseCallback;

        AsyncCall(Callback responseCallback) {
            super("OkHttp %s", RealCall.this.redactedUrl());
            this.callsPerHost = new AtomicInteger(0);
            this.responseCallback = responseCallback;
        }

        AtomicInteger callsPerHost() {
            return this.callsPerHost;
        }

        void reuseCallsPerHostFrom(AsyncCall other) {
            this.callsPerHost = other.callsPerHost;
        }

        String host() {
            return RealCall.this.originalRequest.url().host();
        }

        Request request() {
            return RealCall.this.originalRequest;
        }

        RealCall get() {
            return RealCall.this;
        }

        void executeOn(ExecutorService executorService) {
            if (Thread.holdsLock(RealCall.this.client.dispatcher())) {
                throw new AssertionError();
            }
            boolean success = false;
            try {
                executorService.execute(this);
                success = true;
            } catch (RejectedExecutionException e) {
                InterruptedIOException ioException = new InterruptedIOException("executor rejected");
                ioException.initCause(e);
                RealCall.this.transmitter.noMoreExchanges(ioException);
                this.responseCallback.onFailure(RealCall.this, ioException);
            } finally {
                if (!success) {
                    RealCall.this.client.dispatcher().finished(this);
                }
            }
        }

        @Override // okhttp366.internal.NamedRunnable
        protected void execute() {
            boolean signalledCallback = false;
            RealCall.this.transmitter.timeoutEnter();
            try {
                try {
                    try {
                        Response response = RealCall.this.getResponseWithInterceptorChain();
                        signalledCallback = true;
                        this.responseCallback.onResponse(RealCall.this, response);
                    } catch (Throwable t) {
                        RealCall.this.cancel();
                        if (!signalledCallback) {
                            IOException canceledException = new IOException("canceled due to " + t);
                            canceledException.addSuppressed(t);
                            this.responseCallback.onFailure(RealCall.this, canceledException);
                        }
                        throw t;
                    }
                } catch (IOException e) {
                    if (signalledCallback) {
                        Platform.get().log(4, "Callback failure for " + RealCall.this.toLoggableString(), e);
                    } else {
                        this.responseCallback.onFailure(RealCall.this, e);
                    }
                }
                RealCall.this.client.dispatcher().finished(this);
            } catch (Throwable t2) {
                RealCall.this.client.dispatcher().finished(this);
                throw t2;
            }
        }
    }

    String toLoggableString() {
        return (isCanceled() ? "canceled " : "") + (this.forWebSocket ? "web socket" : NotificationCompat.CATEGORY_CALL) + " to " + redactedUrl();
    }

    String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    Response getResponseWithInterceptorChain() throws IOException {
        List<Interceptor> interceptors = new ArrayList<>();
        interceptors.addAll(this.client.interceptors());
        interceptors.add(new RetryAndFollowUpInterceptor(this.client));
        interceptors.add(new BridgeInterceptor(this.client.cookieJar()));
        interceptors.add(new CacheInterceptor(this.client.internalCache()));
        interceptors.add(new ConnectInterceptor(this.client));
        if (!this.forWebSocket) {
            interceptors.addAll(this.client.networkInterceptors());
        }
        interceptors.add(new CallServerInterceptor(this.forWebSocket));
        Interceptor.Chain chain = new RealInterceptorChain(interceptors, this.transmitter, null, 0, this.originalRequest, this, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis());
        try {
            try {
                Response response = chain.proceed(this.originalRequest);
                if (this.transmitter.isCanceled()) {
                    Util.closeQuietly(response);
                    throw new IOException("Canceled");
                }
                if (0 == 0) {
                    this.transmitter.noMoreExchanges(null);
                }
                return response;
            } catch (IOException e) {
                throw this.transmitter.noMoreExchanges(e);
            }
        } catch (Throwable th) {
            if (0 == 0) {
                this.transmitter.noMoreExchanges(null);
            }
            throw th;
        }
    }
}

package okhttp366.internal.connection;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp366.Address;
import okhttp366.Call;
import okhttp366.CertificatePinner;
import okhttp366.Connection;
import okhttp366.EventListener;
import okhttp366.HttpUrl;
import okhttp366.Interceptor;
import okhttp366.OkHttpClient;
import okhttp366.Request;
import okhttp366.internal.Internal;
import okhttp366.internal.Util;
import okhttp366.internal.http.ExchangeCodec;
import okhttp366.internal.platform.Platform;
import okio66.AsyncTimeout;
import okio66.Timeout;

/* JADX INFO: loaded from: classes2.dex */
public final class Transmitter {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Call call;

    @Nullable
    private Object callStackTrace;
    private boolean canceled;
    private final OkHttpClient client;
    public RealConnection connection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;

    @Nullable
    private Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private boolean exchangeRequestDone;
    private boolean exchangeResponseDone;
    private boolean noMoreExchanges;
    private Request request;
    private final AsyncTimeout timeout = new AsyncTimeout() { // from class: okhttp366.internal.connection.Transmitter.1
        @Override // okio66.AsyncTimeout
        protected void timedOut() {
            Transmitter.this.cancel();
        }
    };
    private boolean timeoutEarlyExit;

    public Transmitter(OkHttpClient client, Call call) {
        this.client = client;
        this.connectionPool = Internal.instance.realConnectionPool(client.connectionPool());
        this.call = call;
        this.eventListener = client.eventListenerFactory().create(call);
        this.timeout.timeout(client.callTimeoutMillis(), TimeUnit.MILLISECONDS);
    }

    public Timeout timeout() {
        return this.timeout;
    }

    public void timeoutEnter() {
        this.timeout.enter();
    }

    public void timeoutEarlyExit() {
        if (this.timeoutEarlyExit) {
            throw new IllegalStateException();
        }
        this.timeoutEarlyExit = true;
        this.timeout.exit();
    }

    @Nullable
    private IOException timeoutExit(@Nullable IOException cause) {
        if (this.timeoutEarlyExit || !this.timeout.exit()) {
            return cause;
        }
        InterruptedIOException e = new InterruptedIOException("timeout");
        if (cause != null) {
            e.initCause(cause);
        }
        return e;
    }

    public void callStart() {
        this.callStackTrace = Platform.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this.call);
    }

    public void prepareToConnect(Request request) {
        if (this.request != null) {
            if (Util.sameConnection(this.request.url(), request.url()) && this.exchangeFinder.hasRouteToTry()) {
                return;
            }
            if (this.exchange != null) {
                throw new IllegalStateException();
            }
            if (this.exchangeFinder != null) {
                maybeReleaseConnection(null, true);
                this.exchangeFinder = null;
            }
        }
        this.request = request;
        this.exchangeFinder = new ExchangeFinder(this, this.connectionPool, createAddress(request.url()), this.call, this.eventListener);
    }

    private Address createAddress(HttpUrl url) {
        SSLSocketFactory sslSocketFactory = null;
        HostnameVerifier hostnameVerifier = null;
        CertificatePinner certificatePinner = null;
        if (url.isHttps()) {
            sslSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        }
        return new Address(url.host(), url.port(), this.client.dns(), this.client.socketFactory(), sslSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    Exchange newExchange(Interceptor.Chain chain, boolean doExtensiveHealthChecks) {
        synchronized (this.connectionPool) {
            if (this.noMoreExchanges) {
                throw new IllegalStateException("released");
            }
            if (this.exchange != null) {
                throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()");
            }
        }
        ExchangeCodec codec = this.exchangeFinder.find(this.client, chain, doExtensiveHealthChecks);
        Exchange result = new Exchange(this, this.call, this.eventListener, this.exchangeFinder, codec);
        synchronized (this.connectionPool) {
            this.exchange = result;
            this.exchangeRequestDone = false;
            this.exchangeResponseDone = false;
        }
        return result;
    }

    void acquireConnectionNoEvents(RealConnection connection) {
        if (!Thread.holdsLock(this.connectionPool)) {
            throw new AssertionError();
        }
        if (this.connection != null) {
            throw new IllegalStateException();
        }
        this.connection = connection;
        connection.transmitters.add(new TransmitterReference(this, this.callStackTrace));
    }

    @Nullable
    Socket releaseConnectionNoEvents() {
        if (!Thread.holdsLock(this.connectionPool)) {
            throw new AssertionError();
        }
        int index = -1;
        int size = this.connection.transmitters.size();
        for (int i = 0; i < size; i++) {
            Reference<Transmitter> reference = this.connection.transmitters.get(i);
            if (reference.get() == this) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            throw new IllegalStateException();
        }
        RealConnection released = this.connection;
        released.transmitters.remove(index);
        this.connection = null;
        if (released.transmitters.isEmpty()) {
            released.idleAtNanos = System.nanoTime();
            if (this.connectionPool.connectionBecameIdle(released)) {
                return released.socket();
            }
        }
        return null;
    }

    public void exchangeDoneDueToException() {
        synchronized (this.connectionPool) {
            if (this.noMoreExchanges) {
                throw new IllegalStateException();
            }
            this.exchange = null;
        }
    }

    @Nullable
    IOException exchangeMessageDone(Exchange exchange, boolean requestDone, boolean responseDone, @Nullable IOException e) {
        boolean exchangeDone = false;
        synchronized (this.connectionPool) {
            if (exchange != this.exchange) {
                return e;
            }
            boolean changed = false;
            if (requestDone) {
                changed = this.exchangeRequestDone ? false : true;
                this.exchangeRequestDone = true;
            }
            if (responseDone) {
                if (!this.exchangeResponseDone) {
                    changed = true;
                }
                this.exchangeResponseDone = true;
            }
            if (this.exchangeRequestDone && this.exchangeResponseDone && changed) {
                exchangeDone = true;
                this.exchange.connection().successCount++;
                this.exchange = null;
            }
            if (exchangeDone) {
                return maybeReleaseConnection(e, false);
            }
            return e;
        }
    }

    @Nullable
    public IOException noMoreExchanges(@Nullable IOException e) {
        synchronized (this.connectionPool) {
            this.noMoreExchanges = true;
        }
        return maybeReleaseConnection(e, false);
    }

    @Nullable
    private IOException maybeReleaseConnection(@Nullable IOException e, boolean force) {
        Connection releasedConnection;
        Socket socket;
        boolean callEnd;
        synchronized (this.connectionPool) {
            if (force) {
                if (this.exchange != null) {
                    throw new IllegalStateException("cannot release connection while it is in use");
                }
            }
            releasedConnection = this.connection;
            if (this.connection != null && this.exchange == null && (force || this.noMoreExchanges)) {
                socket = releaseConnectionNoEvents();
            } else {
                socket = null;
            }
            if (this.connection != null) {
                releasedConnection = null;
            }
            callEnd = this.noMoreExchanges && this.exchange == null;
        }
        Util.closeQuietly(socket);
        if (releasedConnection != null) {
            this.eventListener.connectionReleased(this.call, releasedConnection);
        }
        if (callEnd) {
            boolean callFailed = e != null;
            e = timeoutExit(e);
            if (callFailed) {
                this.eventListener.callFailed(this.call, e);
            } else {
                this.eventListener.callEnd(this.call);
            }
        }
        return e;
    }

    public boolean canRetry() {
        return this.exchangeFinder.hasStreamFailure() && this.exchangeFinder.hasRouteToTry();
    }

    public boolean hasExchange() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.exchange != null;
        }
        return z;
    }

    public void cancel() {
        Exchange exchangeToCancel;
        RealConnection connectionToCancel;
        synchronized (this.connectionPool) {
            this.canceled = true;
            exchangeToCancel = this.exchange;
            if (this.exchangeFinder != null && this.exchangeFinder.connectingConnection() != null) {
                connectionToCancel = this.exchangeFinder.connectingConnection();
            } else {
                connectionToCancel = this.connection;
            }
        }
        if (exchangeToCancel != null) {
            exchangeToCancel.cancel();
        } else if (connectionToCancel != null) {
            connectionToCancel.cancel();
        }
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.canceled;
        }
        return z;
    }

    static final class TransmitterReference extends WeakReference<Transmitter> {
        final Object callStackTrace;

        TransmitterReference(Transmitter referent, Object callStackTrace) {
            super(referent);
            this.callStackTrace = callStackTrace;
        }
    }
}

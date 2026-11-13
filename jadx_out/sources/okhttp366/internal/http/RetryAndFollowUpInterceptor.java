package okhttp366.internal.http;

import android.support.v7.widget.ActivityChooserView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import javax.annotation.Nullable;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import okhttp366.HttpUrl;
import okhttp366.Interceptor;
import okhttp366.OkHttpClient;
import okhttp366.Request;
import okhttp366.RequestBody;
import okhttp366.Response;
import okhttp366.Route;
import okhttp366.internal.Internal;
import okhttp366.internal.Util;
import okhttp366.internal.connection.Exchange;
import okhttp366.internal.connection.RouteException;
import okhttp366.internal.connection.Transmitter;
import okhttp366.internal.http2.ConnectionShutdownException;

/* JADX INFO: loaded from: classes2.dex */
public final class RetryAndFollowUpInterceptor implements Interceptor {
    private static final int MAX_FOLLOW_UPS = 20;
    private final OkHttpClient client;

    public RetryAndFollowUpInterceptor(OkHttpClient client) {
        this.client = client;
    }

    @Override // okhttp366.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        Request request = chain.request();
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Transmitter transmitter = realChain.transmitter();
        int followUpCount = 0;
        Response priorResponse = null;
        while (true) {
            transmitter.prepareToConnect(request);
            if (transmitter.isCanceled()) {
                throw new IOException("Canceled");
            }
            try {
                try {
                    Response response = realChain.proceed(request, transmitter, null);
                    if (1 == 0) {
                        transmitter.exchangeDoneDueToException();
                    }
                    if (priorResponse != null) {
                        response = response.newBuilder().priorResponse(priorResponse.newBuilder().body(null).build()).build();
                    }
                    Exchange exchange = Internal.instance.exchange(response);
                    Route route = exchange != null ? exchange.connection().route() : null;
                    Request followUp = followUpRequest(response, route);
                    if (followUp == null) {
                        if (exchange != null && exchange.isDuplex()) {
                            transmitter.timeoutEarlyExit();
                        }
                        return response;
                    }
                    RequestBody followUpBody = followUp.body();
                    if (followUpBody != null && followUpBody.isOneShot()) {
                        return response;
                    }
                    Util.closeQuietly(response.body());
                    if (transmitter.hasExchange()) {
                        exchange.detachWithViolence();
                    }
                    followUpCount++;
                    if (followUpCount > 20) {
                        throw new ProtocolException("Too many follow-up requests: " + followUpCount);
                    }
                    request = followUp;
                    priorResponse = response;
                } catch (IOException e) {
                    boolean requestSendStarted = e instanceof ConnectionShutdownException ? false : true;
                    if (!recover(e, transmitter, requestSendStarted, request)) {
                        throw e;
                    }
                    if (0 == 0) {
                        transmitter.exchangeDoneDueToException();
                    }
                } catch (RouteException e2) {
                    if (!recover(e2.getLastConnectException(), transmitter, false, request)) {
                        throw e2.getFirstConnectException();
                    }
                    if (0 == 0) {
                        transmitter.exchangeDoneDueToException();
                    }
                }
            } catch (Throwable th) {
                if (0 == 0) {
                    transmitter.exchangeDoneDueToException();
                }
                throw th;
            }
        }
    }

    private boolean recover(IOException e, Transmitter transmitter, boolean requestSendStarted, Request userRequest) {
        if (this.client.retryOnConnectionFailure()) {
            return !(requestSendStarted && requestIsOneShot(e, userRequest)) && isRecoverable(e, requestSendStarted) && transmitter.canRetry();
        }
        return false;
    }

    private boolean requestIsOneShot(IOException e, Request userRequest) {
        RequestBody requestBody = userRequest.body();
        return (requestBody != null && requestBody.isOneShot()) || (e instanceof FileNotFoundException);
    }

    private boolean isRecoverable(IOException e, boolean requestSendStarted) {
        if (e instanceof ProtocolException) {
            return false;
        }
        if (e instanceof InterruptedIOException) {
            return (e instanceof SocketTimeoutException) && !requestSendStarted;
        }
        return (((e instanceof SSLHandshakeException) && (e.getCause() instanceof CertificateException)) || (e instanceof SSLPeerUnverifiedException)) ? false : true;
    }

    private Request followUpRequest(Response userResponse, @Nullable Route route) throws IOException {
        String location;
        HttpUrl url;
        Proxy selectedProxy;
        if (userResponse == null) {
            throw new IllegalStateException();
        }
        int responseCode = userResponse.code();
        String method = userResponse.request().method();
        switch (responseCode) {
            case 300:
            case SET_ENABLED_VALUE:
            case SET_VISIBLE_VALUE:
            case SET_TITLE_TEXT_VALUE:
                break;
            case 307:
            case 308:
                if (!method.equals("GET") && !method.equals("HEAD")) {
                    return null;
                }
                break;
            case GET_ENABLED_VALUE:
                return this.client.authenticator().authenticate(route, userResponse);
            case GET_TEXTCOLOR_VALUE:
                if (route != null) {
                    selectedProxy = route.proxy();
                } else {
                    selectedProxy = this.client.proxy();
                }
                if (selectedProxy.type() != Proxy.Type.HTTP) {
                    throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
                }
                return this.client.proxyAuthenticator().authenticate(route, userResponse);
            case GET_VALUE_VALUE:
                if (!this.client.retryOnConnectionFailure()) {
                    return null;
                }
                RequestBody requestBody = userResponse.request().body();
                if (requestBody != null && requestBody.isOneShot()) {
                    return null;
                }
                if ((userResponse.priorResponse() == null || userResponse.priorResponse().code() != 408) && retryAfter(userResponse, 0) <= 0) {
                    return userResponse.request();
                }
                return null;
            case SET_PADDING_VALUE:
                if ((userResponse.priorResponse() == null || userResponse.priorResponse().code() != 503) && retryAfter(userResponse, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED) == 0) {
                    return userResponse.request();
                }
                return null;
            default:
                return null;
        }
        if (!this.client.followRedirects() || (location = userResponse.header("Location")) == null || (url = userResponse.request().url().resolve(location)) == null) {
            return null;
        }
        boolean sameScheme = url.scheme().equals(userResponse.request().url().scheme());
        if (!sameScheme && !this.client.followSslRedirects()) {
            return null;
        }
        Request.Builder requestBuilder = userResponse.request().newBuilder();
        if (HttpMethod.permitsRequestBody(method)) {
            boolean maintainBody = HttpMethod.redirectsWithBody(method);
            if (HttpMethod.redirectsToGet(method)) {
                requestBuilder.method("GET", null);
            } else {
                requestBuilder.method(method, maintainBody ? userResponse.request().body() : null);
            }
            if (!maintainBody) {
                requestBuilder.removeHeader("Transfer-Encoding");
                requestBuilder.removeHeader("Content-Length");
                requestBuilder.removeHeader("Content-Type");
            }
        }
        if (!Util.sameConnection(userResponse.request().url(), url)) {
            requestBuilder.removeHeader("Authorization");
        }
        return requestBuilder.url(url).build();
    }

    private int retryAfter(Response userResponse, int defaultDelay) {
        String header = userResponse.header("Retry-After");
        if (header == null) {
            return defaultDelay;
        }
        if (header.matches("\\d+")) {
            return Integer.valueOf(header).intValue();
        }
        return ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
    }
}

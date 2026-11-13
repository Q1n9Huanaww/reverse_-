package okhttp366.internal.connection;

import java.io.IOException;
import okhttp366.Interceptor;
import okhttp366.OkHttpClient;
import okhttp366.Request;
import okhttp366.Response;
import okhttp366.internal.http.RealInterceptorChain;

/* JADX INFO: loaded from: classes2.dex */
public final class ConnectInterceptor implements Interceptor {
    public final OkHttpClient client;

    public ConnectInterceptor(OkHttpClient client) {
        this.client = client;
    }

    @Override // okhttp366.Interceptor
    public Response intercept(Interceptor.Chain chain) throws IOException {
        RealInterceptorChain realChain = (RealInterceptorChain) chain;
        Request request = realChain.request();
        Transmitter transmitter = realChain.transmitter();
        boolean doExtensiveHealthChecks = !request.method().equals("GET");
        Exchange exchange = transmitter.newExchange(chain, doExtensiveHealthChecks);
        return realChain.proceed(request, transmitter, exchange);
    }
}

package okhttp366.internal;

import javax.annotation.Nullable;
import javax.net.ssl.SSLSocket;
import okhttp366.Address;
import okhttp366.Call;
import okhttp366.ConnectionPool;
import okhttp366.ConnectionSpec;
import okhttp366.Headers;
import okhttp366.OkHttpClient;
import okhttp366.Request;
import okhttp366.Response;
import okhttp366.internal.connection.Exchange;
import okhttp366.internal.connection.RealConnectionPool;

/* JADX INFO: loaded from: classes2.dex */
public abstract class Internal {
    public static Internal instance;

    public abstract void addLenient(Headers.Builder builder, String str);

    public abstract void addLenient(Headers.Builder builder, String str, String str2);

    public abstract void apply(ConnectionSpec connectionSpec, SSLSocket sSLSocket, boolean z);

    public abstract int code(Response.Builder builder);

    public abstract boolean equalsNonHost(Address address, Address address2);

    @Nullable
    public abstract Exchange exchange(Response response);

    public abstract void initExchange(Response.Builder builder, Exchange exchange);

    public abstract Call newWebSocketCall(OkHttpClient okHttpClient, Request request);

    public abstract RealConnectionPool realConnectionPool(ConnectionPool connectionPool);

    public static void initializeInstanceForTests() {
        new OkHttpClient();
    }
}

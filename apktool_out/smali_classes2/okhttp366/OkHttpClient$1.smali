.class Lokhttp366/OkHttpClient$1;
.super Lokhttp366/internal/Internal;
.source "OkHttpClient.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/OkHttpClient;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 131
    invoke-direct {p0}, Lokhttp366/internal/Internal;-><init>()V

    return-void
.end method


# virtual methods
.method public addLenient(Lokhttp366/Headers$Builder;Ljava/lang/String;)V
    .locals 0
    .param p1, "builder"    # Lokhttp366/Headers$Builder;
    .param p2, "line"    # Ljava/lang/String;

    .line 133
    invoke-virtual {p1, p2}, Lokhttp366/Headers$Builder;->addLenient(Ljava/lang/String;)Lokhttp366/Headers$Builder;

    .line 134
    return-void
.end method

.method public addLenient(Lokhttp366/Headers$Builder;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0
    .param p1, "builder"    # Lokhttp366/Headers$Builder;
    .param p2, "name"    # Ljava/lang/String;
    .param p3, "value"    # Ljava/lang/String;

    .line 137
    invoke-virtual {p1, p2, p3}, Lokhttp366/Headers$Builder;->addLenient(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Headers$Builder;

    .line 138
    return-void
.end method

.method public apply(Lokhttp366/ConnectionSpec;Ljavax/net/ssl/SSLSocket;Z)V
    .locals 0
    .param p1, "tlsConfiguration"    # Lokhttp366/ConnectionSpec;
    .param p2, "sslSocket"    # Ljavax/net/ssl/SSLSocket;
    .param p3, "isFallback"    # Z

    .line 154
    invoke-virtual {p1, p2, p3}, Lokhttp366/ConnectionSpec;->apply(Ljavax/net/ssl/SSLSocket;Z)V

    .line 155
    return-void
.end method

.method public code(Lokhttp366/Response$Builder;)I
    .locals 1
    .param p1, "responseBuilder"    # Lokhttp366/Response$Builder;

    .line 149
    iget v0, p1, Lokhttp366/Response$Builder;->code:I

    return v0
.end method

.method public equalsNonHost(Lokhttp366/Address;Lokhttp366/Address;)Z
    .locals 1
    .param p1, "a"    # Lokhttp366/Address;
    .param p2, "b"    # Lokhttp366/Address;

    .line 145
    invoke-virtual {p1, p2}, Lokhttp366/Address;->equalsNonHost(Lokhttp366/Address;)Z

    move-result v0

    return v0
.end method

.method public exchange(Lokhttp366/Response;)Lokhttp366/internal/connection/Exchange;
    .locals 1
    .param p1, "response"    # Lokhttp366/Response;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 167
    iget-object v0, p1, Lokhttp366/Response;->exchange:Lokhttp366/internal/connection/Exchange;

    return-object v0
.end method

.method public initExchange(Lokhttp366/Response$Builder;Lokhttp366/internal/connection/Exchange;)V
    .locals 0
    .param p1, "responseBuilder"    # Lokhttp366/Response$Builder;
    .param p2, "exchange"    # Lokhttp366/internal/connection/Exchange;

    .line 163
    invoke-virtual {p1, p2}, Lokhttp366/Response$Builder;->initExchange(Lokhttp366/internal/connection/Exchange;)V

    .line 164
    return-void
.end method

.method public newWebSocketCall(Lokhttp366/OkHttpClient;Lokhttp366/Request;)Lokhttp366/Call;
    .locals 1
    .param p1, "client"    # Lokhttp366/OkHttpClient;
    .param p2, "originalRequest"    # Lokhttp366/Request;

    .line 158
    const/4 v0, 0x1

    invoke-static {p1, p2, v0}, Lokhttp366/RealCall;->newRealCall(Lokhttp366/OkHttpClient;Lokhttp366/Request;Z)Lokhttp366/RealCall;

    move-result-object v0

    return-object v0
.end method

.method public realConnectionPool(Lokhttp366/ConnectionPool;)Lokhttp366/internal/connection/RealConnectionPool;
    .locals 1
    .param p1, "connectionPool"    # Lokhttp366/ConnectionPool;

    .line 141
    iget-object v0, p1, Lokhttp366/ConnectionPool;->delegate:Lokhttp366/internal/connection/RealConnectionPool;

    return-object v0
.end method

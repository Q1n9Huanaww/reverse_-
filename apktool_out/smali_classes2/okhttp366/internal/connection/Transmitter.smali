.class public final Lokhttp366/internal/connection/Transmitter;
.super Ljava/lang/Object;
.source "Transmitter.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lokhttp366/internal/connection/Transmitter$TransmitterReference;
    }
.end annotation


# static fields
.field static final synthetic $assertionsDisabled:Z


# instance fields
.field private final call:Lokhttp366/Call;

.field private callStackTrace:Ljava/lang/Object;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private canceled:Z

.field private final client:Lokhttp366/OkHttpClient;

.field public connection:Lokhttp366/internal/connection/RealConnection;

.field private final connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

.field private final eventListener:Lokhttp366/EventListener;

.field private exchange:Lokhttp366/internal/connection/Exchange;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

.field private exchangeRequestDone:Z

.field private exchangeResponseDone:Z

.field private noMoreExchanges:Z

.field private request:Lokhttp366/Request;

.field private final timeout:Lokio66/AsyncTimeout;

.field private timeoutEarlyExit:Z


# direct methods
.method static constructor <clinit>()V
    .locals 0

    .line 54
    return-void
.end method

.method public constructor <init>(Lokhttp366/OkHttpClient;Lokhttp366/Call;)V
    .locals 4
    .param p1, "client"    # Lokhttp366/OkHttpClient;
    .param p2, "call"    # Lokhttp366/Call;

    .line 79
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 59
    new-instance v0, Lokhttp366/internal/connection/Transmitter$1;

    invoke-direct {v0, p0}, Lokhttp366/internal/connection/Transmitter$1;-><init>(Lokhttp366/internal/connection/Transmitter;)V

    iput-object v0, p0, Lokhttp366/internal/connection/Transmitter;->timeout:Lokio66/AsyncTimeout;

    .line 80
    iput-object p1, p0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    .line 81
    sget-object v0, Lokhttp366/internal/Internal;->instance:Lokhttp366/internal/Internal;

    invoke-virtual {p1}, Lokhttp366/OkHttpClient;->connectionPool()Lokhttp366/ConnectionPool;

    move-result-object v1

    invoke-virtual {v0, v1}, Lokhttp366/internal/Internal;->realConnectionPool(Lokhttp366/ConnectionPool;)Lokhttp366/internal/connection/RealConnectionPool;

    move-result-object v0

    iput-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    .line 82
    iput-object p2, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    .line 83
    invoke-virtual {p1}, Lokhttp366/OkHttpClient;->eventListenerFactory()Lokhttp366/EventListener$Factory;

    move-result-object v0

    invoke-interface {v0, p2}, Lokhttp366/EventListener$Factory;->create(Lokhttp366/Call;)Lokhttp366/EventListener;

    move-result-object v0

    iput-object v0, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    .line 84
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->timeout:Lokio66/AsyncTimeout;

    invoke-virtual {p1}, Lokhttp366/OkHttpClient;->callTimeoutMillis()I

    move-result v1

    int-to-long v1, v1

    sget-object v3, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v0, v1, v2, v3}, Lokio66/AsyncTimeout;->timeout(JLjava/util/concurrent/TimeUnit;)Lokio66/Timeout;

    .line 85
    return-void
.end method

.method private createAddress(Lokhttp366/HttpUrl;)Lokhttp366/Address;
    .locals 18
    .param p1, "url"    # Lokhttp366/HttpUrl;

    .line 143
    move-object/from16 v0, p0

    const/4 v1, 0x0

    .line 144
    .local v1, "sslSocketFactory":Ljavax/net/ssl/SSLSocketFactory;
    const/4 v2, 0x0

    .line 145
    .local v2, "hostnameVerifier":Ljavax/net/ssl/HostnameVerifier;
    const/4 v3, 0x0

    .line 146
    .local v3, "certificatePinner":Lokhttp366/CertificatePinner;
    invoke-virtual/range {p1 .. p1}, Lokhttp366/HttpUrl;->isHttps()Z

    move-result v4

    if-eqz v4, :cond_0

    .line 147
    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->sslSocketFactory()Ljavax/net/ssl/SSLSocketFactory;

    move-result-object v1

    .line 148
    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->hostnameVerifier()Ljavax/net/ssl/HostnameVerifier;

    move-result-object v2

    .line 149
    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->certificatePinner()Lokhttp366/CertificatePinner;

    move-result-object v3

    .line 152
    :cond_0
    new-instance v17, Lokhttp366/Address;

    invoke-virtual/range {p1 .. p1}, Lokhttp366/HttpUrl;->host()Ljava/lang/String;

    move-result-object v5

    invoke-virtual/range {p1 .. p1}, Lokhttp366/HttpUrl;->port()I

    move-result v6

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->dns()Lokhttp366/Dns;

    move-result-object v7

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->socketFactory()Ljavax/net/SocketFactory;

    move-result-object v8

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    .line 153
    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->proxyAuthenticator()Lokhttp366/Authenticator;

    move-result-object v12

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    .line 154
    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->proxy()Ljava/net/Proxy;

    move-result-object v13

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->protocols()Ljava/util/List;

    move-result-object v14

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->connectionSpecs()Ljava/util/List;

    move-result-object v15

    iget-object v4, v0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v4}, Lokhttp366/OkHttpClient;->proxySelector()Ljava/net/ProxySelector;

    move-result-object v16

    move-object/from16 v4, v17

    move-object v9, v1

    move-object v10, v2

    move-object v11, v3

    invoke-direct/range {v4 .. v16}, Lokhttp366/Address;-><init>(Ljava/lang/String;ILokhttp366/Dns;Ljavax/net/SocketFactory;Ljavax/net/ssl/SSLSocketFactory;Ljavax/net/ssl/HostnameVerifier;Lokhttp366/CertificatePinner;Lokhttp366/Authenticator;Ljava/net/Proxy;Ljava/util/List;Ljava/util/List;Ljava/net/ProxySelector;)V

    .line 152
    return-object v17
.end method

.method private maybeReleaseConnection(Ljava/io/IOException;Z)Ljava/io/IOException;
    .locals 7
    .param p1, "e"    # Ljava/io/IOException;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "force"    # Z
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 283
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 284
    if-eqz p2, :cond_1

    :try_start_0
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-nez v1, :cond_0

    goto :goto_0

    .line 285
    :cond_0
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "cannot release connection while it is in use"

    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .end local p1    # "e":Ljava/io/IOException;
    .end local p2    # "force":Z
    throw v1

    .line 287
    .restart local p1    # "e":Ljava/io/IOException;
    .restart local p2    # "force":Z
    :cond_1
    :goto_0
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    .line 288
    .local v1, "releasedConnection":Lokhttp366/Connection;
    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    if-eqz v2, :cond_3

    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-nez v2, :cond_3

    if-nez p2, :cond_2

    iget-boolean v2, p0, Lokhttp366/internal/connection/Transmitter;->noMoreExchanges:Z

    if-eqz v2, :cond_3

    .line 289
    :cond_2
    invoke-virtual {p0}, Lokhttp366/internal/connection/Transmitter;->releaseConnectionNoEvents()Ljava/net/Socket;

    move-result-object v2

    goto :goto_1

    .line 290
    :cond_3
    const/4 v2, 0x0

    :goto_1
    nop

    .line 291
    .local v2, "socket":Ljava/net/Socket;
    iget-object v3, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    if-eqz v3, :cond_4

    const/4 v1, 0x0

    .line 292
    :cond_4
    iget-boolean v3, p0, Lokhttp366/internal/connection/Transmitter;->noMoreExchanges:Z

    const/4 v4, 0x1

    const/4 v5, 0x0

    if-eqz v3, :cond_5

    iget-object v3, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-nez v3, :cond_5

    const/4 v3, 0x1

    goto :goto_2

    :cond_5
    const/4 v3, 0x0

    .line 293
    .local v3, "callEnd":Z
    :goto_2
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 294
    invoke-static {v2}, Lokhttp366/internal/Util;->closeQuietly(Ljava/net/Socket;)V

    .line 296
    if-eqz v1, :cond_6

    .line 297
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    iget-object v6, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    invoke-virtual {v0, v6, v1}, Lokhttp366/EventListener;->connectionReleased(Lokhttp366/Call;Lokhttp366/Connection;)V

    .line 300
    :cond_6
    if-eqz v3, :cond_9

    .line 301
    if-eqz p1, :cond_7

    goto :goto_3

    :cond_7
    const/4 v4, 0x0

    :goto_3
    move v0, v4

    .line 302
    .local v0, "callFailed":Z
    invoke-direct {p0, p1}, Lokhttp366/internal/connection/Transmitter;->timeoutExit(Ljava/io/IOException;)Ljava/io/IOException;

    move-result-object p1

    .line 303
    if-eqz v0, :cond_8

    .line 304
    iget-object v4, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    iget-object v5, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    invoke-virtual {v4, v5, p1}, Lokhttp366/EventListener;->callFailed(Lokhttp366/Call;Ljava/io/IOException;)V

    goto :goto_4

    .line 306
    :cond_8
    iget-object v4, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    iget-object v5, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    invoke-virtual {v4, v5}, Lokhttp366/EventListener;->callEnd(Lokhttp366/Call;)V

    .line 309
    .end local v0    # "callFailed":Z
    :cond_9
    :goto_4
    return-object p1

    .line 293
    .end local v1    # "releasedConnection":Lokhttp366/Connection;
    .end local v2    # "socket":Ljava/net/Socket;
    .end local v3    # "callEnd":Z
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method private timeoutExit(Ljava/io/IOException;)Ljava/io/IOException;
    .locals 2
    .param p1, "cause"    # Ljava/io/IOException;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 106
    iget-boolean v0, p0, Lokhttp366/internal/connection/Transmitter;->timeoutEarlyExit:Z

    if-eqz v0, :cond_0

    return-object p1

    .line 107
    :cond_0
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->timeout:Lokio66/AsyncTimeout;

    invoke-virtual {v0}, Lokio66/AsyncTimeout;->exit()Z

    move-result v0

    if-nez v0, :cond_1

    return-object p1

    .line 109
    :cond_1
    new-instance v0, Ljava/io/InterruptedIOException;

    const-string v1, "timeout"

    invoke-direct {v0, v1}, Ljava/io/InterruptedIOException;-><init>(Ljava/lang/String;)V

    .line 110
    .local v0, "e":Ljava/io/InterruptedIOException;
    if-eqz p1, :cond_2

    invoke-virtual {v0, p1}, Ljava/io/InterruptedIOException;->initCause(Ljava/lang/Throwable;)Ljava/lang/Throwable;

    .line 112
    :cond_2
    return-object v0
.end method


# virtual methods
.method acquireConnectionNoEvents(Lokhttp366/internal/connection/RealConnection;)V
    .locals 3
    .param p1, "connection"    # Lokhttp366/internal/connection/RealConnection;

    .line 181
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    invoke-static {v0}, Ljava/lang/Thread;->holdsLock(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 183
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    if-nez v0, :cond_0

    .line 184
    iput-object p1, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    .line 185
    iget-object v0, p1, Lokhttp366/internal/connection/RealConnection;->transmitters:Ljava/util/List;

    new-instance v1, Lokhttp366/internal/connection/Transmitter$TransmitterReference;

    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->callStackTrace:Ljava/lang/Object;

    invoke-direct {v1, p0, v2}, Lokhttp366/internal/connection/Transmitter$TransmitterReference;-><init>(Lokhttp366/internal/connection/Transmitter;Ljava/lang/Object;)V

    invoke-interface {v0, v1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 186
    return-void

    .line 183
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-direct {v0}, Ljava/lang/IllegalStateException;-><init>()V

    throw v0

    .line 181
    :cond_1
    new-instance v0, Ljava/lang/AssertionError;

    invoke-direct {v0}, Ljava/lang/AssertionError;-><init>()V

    throw v0
.end method

.method public callStart()V
    .locals 2

    .line 116
    invoke-static {}, Lokhttp366/internal/platform/Platform;->get()Lokhttp366/internal/platform/Platform;

    move-result-object v0

    const-string v1, "response.body().close()"

    invoke-virtual {v0, v1}, Lokhttp366/internal/platform/Platform;->getStackTraceForCloseable(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    iput-object v0, p0, Lokhttp366/internal/connection/Transmitter;->callStackTrace:Ljava/lang/Object;

    .line 117
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    invoke-virtual {v0, v1}, Lokhttp366/EventListener;->callStart(Lokhttp366/Call;)V

    .line 118
    return-void
.end method

.method public canRetry()Z
    .locals 1

    .line 313
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    invoke-virtual {v0}, Lokhttp366/internal/connection/ExchangeFinder;->hasStreamFailure()Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    invoke-virtual {v0}, Lokhttp366/internal/connection/ExchangeFinder;->hasRouteToTry()Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public cancel()V
    .locals 3

    .line 334
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 335
    const/4 v1, 0x1

    :try_start_0
    iput-boolean v1, p0, Lokhttp366/internal/connection/Transmitter;->canceled:Z

    .line 336
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    .line 337
    .local v1, "exchangeToCancel":Lokhttp366/internal/connection/Exchange;
    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    if-eqz v2, :cond_0

    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    invoke-virtual {v2}, Lokhttp366/internal/connection/ExchangeFinder;->connectingConnection()Lokhttp366/internal/connection/RealConnection;

    move-result-object v2

    if-eqz v2, :cond_0

    .line 338
    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    invoke-virtual {v2}, Lokhttp366/internal/connection/ExchangeFinder;->connectingConnection()Lokhttp366/internal/connection/RealConnection;

    move-result-object v2

    goto :goto_0

    .line 339
    :cond_0
    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    :goto_0
    nop

    .line 340
    .local v2, "connectionToCancel":Lokhttp366/internal/connection/RealConnection;
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 341
    if-eqz v1, :cond_1

    .line 342
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->cancel()V

    goto :goto_1

    .line 343
    :cond_1
    if-eqz v2, :cond_2

    .line 344
    invoke-virtual {v2}, Lokhttp366/internal/connection/RealConnection;->cancel()V

    .line 346
    :cond_2
    :goto_1
    return-void

    .line 340
    .end local v1    # "exchangeToCancel":Lokhttp366/internal/connection/Exchange;
    .end local v2    # "connectionToCancel":Lokhttp366/internal/connection/RealConnection;
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method public exchangeDoneDueToException()V
    .locals 2

    .line 221
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 222
    :try_start_0
    iget-boolean v1, p0, Lokhttp366/internal/connection/Transmitter;->noMoreExchanges:Z

    if-nez v1, :cond_0

    .line 223
    const/4 v1, 0x0

    iput-object v1, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    .line 224
    monitor-exit v0

    .line 225
    return-void

    .line 222
    :cond_0
    new-instance v1, Ljava/lang/IllegalStateException;

    invoke-direct {v1}, Ljava/lang/IllegalStateException;-><init>()V

    throw v1

    .line 224
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method exchangeMessageDone(Lokhttp366/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;
    .locals 6
    .param p1, "exchange"    # Lokhttp366/internal/connection/Exchange;
    .param p2, "requestDone"    # Z
    .param p3, "responseDone"    # Z
    .param p4, "e"    # Ljava/io/IOException;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 237
    const/4 v0, 0x0

    .line 238
    .local v0, "exchangeDone":Z
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v1

    .line 239
    :try_start_0
    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-eq p1, v2, :cond_0

    .line 240
    monitor-exit v1

    return-object p4

    .line 242
    :cond_0
    const/4 v2, 0x0

    .line 243
    .local v2, "changed":Z
    const/4 v3, 0x1

    if-eqz p2, :cond_2

    .line 244
    iget-boolean v4, p0, Lokhttp366/internal/connection/Transmitter;->exchangeRequestDone:Z

    if-nez v4, :cond_1

    const/4 v2, 0x1

    .line 245
    :cond_1
    iput-boolean v3, p0, Lokhttp366/internal/connection/Transmitter;->exchangeRequestDone:Z

    .line 247
    :cond_2
    if-eqz p3, :cond_4

    .line 248
    iget-boolean v4, p0, Lokhttp366/internal/connection/Transmitter;->exchangeResponseDone:Z

    if-nez v4, :cond_3

    const/4 v2, 0x1

    .line 249
    :cond_3
    iput-boolean v3, p0, Lokhttp366/internal/connection/Transmitter;->exchangeResponseDone:Z

    .line 251
    :cond_4
    iget-boolean v4, p0, Lokhttp366/internal/connection/Transmitter;->exchangeRequestDone:Z

    if-eqz v4, :cond_5

    iget-boolean v4, p0, Lokhttp366/internal/connection/Transmitter;->exchangeResponseDone:Z

    if-eqz v4, :cond_5

    if-eqz v2, :cond_5

    .line 252
    const/4 v0, 0x1

    .line 253
    iget-object v4, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    invoke-virtual {v4}, Lokhttp366/internal/connection/Exchange;->connection()Lokhttp366/internal/connection/RealConnection;

    move-result-object v4

    iget v5, v4, Lokhttp366/internal/connection/RealConnection;->successCount:I

    add-int/2addr v5, v3

    iput v5, v4, Lokhttp366/internal/connection/RealConnection;->successCount:I

    .line 254
    const/4 v3, 0x0

    iput-object v3, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    .line 256
    .end local v2    # "changed":Z
    :cond_5
    monitor-exit v1
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 257
    if-eqz v0, :cond_6

    .line 258
    const/4 v1, 0x0

    invoke-direct {p0, p4, v1}, Lokhttp366/internal/connection/Transmitter;->maybeReleaseConnection(Ljava/io/IOException;Z)Ljava/io/IOException;

    move-result-object p4

    .line 260
    :cond_6
    return-object p4

    .line 256
    :catchall_0
    move-exception v2

    :try_start_1
    monitor-exit v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v2
.end method

.method public hasExchange()Z
    .locals 2

    .line 317
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 318
    :try_start_0
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-eqz v1, :cond_0

    const/4 v1, 0x1

    goto :goto_0

    :cond_0
    const/4 v1, 0x0

    :goto_0
    monitor-exit v0

    return v1

    .line 319
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method public isCanceled()Z
    .locals 2

    .line 349
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 350
    :try_start_0
    iget-boolean v1, p0, Lokhttp366/internal/connection/Transmitter;->canceled:Z

    monitor-exit v0

    return v1

    .line 351
    :catchall_0
    move-exception v1

    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    throw v1
.end method

.method newExchange(Lokhttp366/Interceptor$Chain;Z)Lokhttp366/internal/connection/Exchange;
    .locals 8
    .param p1, "chain"    # Lokhttp366/Interceptor$Chain;
    .param p2, "doExtensiveHealthChecks"    # Z

    .line 159
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 160
    :try_start_0
    iget-boolean v1, p0, Lokhttp366/internal/connection/Transmitter;->noMoreExchanges:Z

    if-nez v1, :cond_1

    .line 163
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-nez v1, :cond_0

    .line 167
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 169
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->client:Lokhttp366/OkHttpClient;

    invoke-virtual {v0, v1, p1, p2}, Lokhttp366/internal/connection/ExchangeFinder;->find(Lokhttp366/OkHttpClient;Lokhttp366/Interceptor$Chain;Z)Lokhttp366/internal/http/ExchangeCodec;

    move-result-object v1

    .line 170
    .local v1, "codec":Lokhttp366/internal/http/ExchangeCodec;
    new-instance v0, Lokhttp366/internal/connection/Exchange;

    iget-object v4, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    iget-object v5, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    iget-object v6, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    move-object v2, v0

    move-object v3, p0

    move-object v7, v1

    invoke-direct/range {v2 .. v7}, Lokhttp366/internal/connection/Exchange;-><init>(Lokhttp366/internal/connection/Transmitter;Lokhttp366/Call;Lokhttp366/EventListener;Lokhttp366/internal/connection/ExchangeFinder;Lokhttp366/internal/http/ExchangeCodec;)V

    .line 172
    .local v2, "result":Lokhttp366/internal/connection/Exchange;
    iget-object v3, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v3

    .line 173
    :try_start_1
    iput-object v2, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    .line 174
    const/4 v0, 0x0

    iput-boolean v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeRequestDone:Z

    .line 175
    iput-boolean v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeResponseDone:Z

    .line 176
    monitor-exit v3

    return-object v2

    .line 177
    :catchall_0
    move-exception v0

    monitor-exit v3
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v0

    .line 164
    .end local v1    # "codec":Lokhttp366/internal/http/ExchangeCodec;
    .end local v2    # "result":Lokhttp366/internal/connection/Exchange;
    :cond_0
    :try_start_2
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "cannot make a new request because the previous response is still open: please call response.close()"

    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .end local p1    # "chain":Lokhttp366/Interceptor$Chain;
    .end local p2    # "doExtensiveHealthChecks":Z
    throw v1

    .line 161
    .restart local p1    # "chain":Lokhttp366/Interceptor$Chain;
    .restart local p2    # "doExtensiveHealthChecks":Z
    :cond_1
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "released"

    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .end local p1    # "chain":Lokhttp366/Interceptor$Chain;
    .end local p2    # "doExtensiveHealthChecks":Z
    throw v1

    .line 167
    .restart local p1    # "chain":Lokhttp366/Interceptor$Chain;
    .restart local p2    # "doExtensiveHealthChecks":Z
    :catchall_1
    move-exception v1

    monitor-exit v0
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_1

    throw v1
.end method

.method public noMoreExchanges(Ljava/io/IOException;)Ljava/io/IOException;
    .locals 2
    .param p1, "e"    # Ljava/io/IOException;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 264
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    monitor-enter v0

    .line 265
    const/4 v1, 0x1

    :try_start_0
    iput-boolean v1, p0, Lokhttp366/internal/connection/Transmitter;->noMoreExchanges:Z

    .line 266
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 267
    const/4 v0, 0x0

    invoke-direct {p0, p1, v0}, Lokhttp366/internal/connection/Transmitter;->maybeReleaseConnection(Ljava/io/IOException;Z)Ljava/io/IOException;

    move-result-object v0

    return-object v0

    .line 266
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

.method public prepareToConnect(Lokhttp366/Request;)V
    .locals 7
    .param p1, "request"    # Lokhttp366/Request;

    .line 125
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->request:Lokhttp366/Request;

    if-eqz v0, :cond_2

    .line 126
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->request:Lokhttp366/Request;

    invoke-virtual {v0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v0

    invoke-virtual {p1}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v1

    invoke-static {v0, v1}, Lokhttp366/internal/Util;->sameConnection(Lokhttp366/HttpUrl;Lokhttp366/HttpUrl;)Z

    move-result v0

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    invoke-virtual {v0}, Lokhttp366/internal/connection/ExchangeFinder;->hasRouteToTry()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 127
    return-void

    .line 129
    :cond_0
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchange:Lokhttp366/internal/connection/Exchange;

    if-nez v0, :cond_1

    .line 131
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    if-eqz v0, :cond_2

    .line 132
    const/4 v0, 0x1

    const/4 v1, 0x0

    invoke-direct {p0, v1, v0}, Lokhttp366/internal/connection/Transmitter;->maybeReleaseConnection(Ljava/io/IOException;Z)Ljava/io/IOException;

    .line 133
    iput-object v1, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    goto :goto_0

    .line 129
    :cond_1
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-direct {v0}, Ljava/lang/IllegalStateException;-><init>()V

    throw v0

    .line 137
    :cond_2
    :goto_0
    iput-object p1, p0, Lokhttp366/internal/connection/Transmitter;->request:Lokhttp366/Request;

    .line 138
    new-instance v0, Lokhttp366/internal/connection/ExchangeFinder;

    iget-object v3, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    invoke-virtual {p1}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v1

    invoke-direct {p0, v1}, Lokhttp366/internal/connection/Transmitter;->createAddress(Lokhttp366/HttpUrl;)Lokhttp366/Address;

    move-result-object v4

    iget-object v5, p0, Lokhttp366/internal/connection/Transmitter;->call:Lokhttp366/Call;

    iget-object v6, p0, Lokhttp366/internal/connection/Transmitter;->eventListener:Lokhttp366/EventListener;

    move-object v1, v0

    move-object v2, p0

    invoke-direct/range {v1 .. v6}, Lokhttp366/internal/connection/ExchangeFinder;-><init>(Lokhttp366/internal/connection/Transmitter;Lokhttp366/internal/connection/RealConnectionPool;Lokhttp366/Address;Lokhttp366/Call;Lokhttp366/EventListener;)V

    iput-object v0, p0, Lokhttp366/internal/connection/Transmitter;->exchangeFinder:Lokhttp366/internal/connection/ExchangeFinder;

    .line 140
    return-void
.end method

.method releaseConnectionNoEvents()Ljava/net/Socket;
    .locals 5
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 193
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    invoke-static {v0}, Ljava/lang/Thread;->holdsLock(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_4

    .line 195
    const/4 v0, -0x1

    .line 196
    .local v0, "index":I
    const/4 v1, 0x0

    .local v1, "i":I
    iget-object v2, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    iget-object v2, v2, Lokhttp366/internal/connection/RealConnection;->transmitters:Ljava/util/List;

    invoke-interface {v2}, Ljava/util/List;->size()I

    move-result v2

    .local v2, "size":I
    :goto_0
    if-ge v1, v2, :cond_1

    .line 197
    iget-object v3, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    iget-object v3, v3, Lokhttp366/internal/connection/RealConnection;->transmitters:Ljava/util/List;

    invoke-interface {v3, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/ref/Reference;

    .line 198
    .local v3, "reference":Ljava/lang/ref/Reference;, "Ljava/lang/ref/Reference<Lokhttp366/internal/connection/Transmitter;>;"
    invoke-virtual {v3}, Ljava/lang/ref/Reference;->get()Ljava/lang/Object;

    move-result-object v4

    if-ne v4, p0, :cond_0

    .line 199
    move v0, v1

    .line 200
    goto :goto_1

    .line 196
    .end local v3    # "reference":Ljava/lang/ref/Reference;, "Ljava/lang/ref/Reference<Lokhttp366/internal/connection/Transmitter;>;"
    :cond_0
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 204
    .end local v1    # "i":I
    .end local v2    # "size":I
    :cond_1
    :goto_1
    const/4 v1, -0x1

    if-eq v0, v1, :cond_3

    .line 206
    iget-object v1, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    .line 207
    .local v1, "released":Lokhttp366/internal/connection/RealConnection;
    iget-object v2, v1, Lokhttp366/internal/connection/RealConnection;->transmitters:Ljava/util/List;

    invoke-interface {v2, v0}, Ljava/util/List;->remove(I)Ljava/lang/Object;

    .line 208
    const/4 v2, 0x0

    iput-object v2, p0, Lokhttp366/internal/connection/Transmitter;->connection:Lokhttp366/internal/connection/RealConnection;

    .line 210
    iget-object v3, v1, Lokhttp366/internal/connection/RealConnection;->transmitters:Ljava/util/List;

    invoke-interface {v3}, Ljava/util/List;->isEmpty()Z

    move-result v3

    if-eqz v3, :cond_2

    .line 211
    invoke-static {}, Ljava/lang/System;->nanoTime()J

    move-result-wide v3

    iput-wide v3, v1, Lokhttp366/internal/connection/RealConnection;->idleAtNanos:J

    .line 212
    iget-object v3, p0, Lokhttp366/internal/connection/Transmitter;->connectionPool:Lokhttp366/internal/connection/RealConnectionPool;

    invoke-virtual {v3, v1}, Lokhttp366/internal/connection/RealConnectionPool;->connectionBecameIdle(Lokhttp366/internal/connection/RealConnection;)Z

    move-result v3

    if-eqz v3, :cond_2

    .line 213
    invoke-virtual {v1}, Lokhttp366/internal/connection/RealConnection;->socket()Ljava/net/Socket;

    move-result-object v2

    return-object v2

    .line 217
    :cond_2
    return-object v2

    .line 204
    .end local v1    # "released":Lokhttp366/internal/connection/RealConnection;
    :cond_3
    new-instance v1, Ljava/lang/IllegalStateException;

    invoke-direct {v1}, Ljava/lang/IllegalStateException;-><init>()V

    throw v1

    .line 193
    .end local v0    # "index":I
    :cond_4
    new-instance v0, Ljava/lang/AssertionError;

    invoke-direct {v0}, Ljava/lang/AssertionError;-><init>()V

    throw v0
.end method

.method public timeout()Lokio66/Timeout;
    .locals 1

    .line 88
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->timeout:Lokio66/AsyncTimeout;

    return-object v0
.end method

.method public timeoutEarlyExit()V
    .locals 1

    .line 100
    iget-boolean v0, p0, Lokhttp366/internal/connection/Transmitter;->timeoutEarlyExit:Z

    if-nez v0, :cond_0

    .line 101
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokhttp366/internal/connection/Transmitter;->timeoutEarlyExit:Z

    .line 102
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->timeout:Lokio66/AsyncTimeout;

    invoke-virtual {v0}, Lokio66/AsyncTimeout;->exit()Z

    .line 103
    return-void

    .line 100
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    invoke-direct {v0}, Ljava/lang/IllegalStateException;-><init>()V

    throw v0
.end method

.method public timeoutEnter()V
    .locals 1

    .line 92
    iget-object v0, p0, Lokhttp366/internal/connection/Transmitter;->timeout:Lokio66/AsyncTimeout;

    invoke-virtual {v0}, Lokio66/AsyncTimeout;->enter()V

    .line 93
    return-void
.end method

.class public final Lokhttp366/internal/connection/ConnectInterceptor;
.super Ljava/lang/Object;
.source "ConnectInterceptor.java"

# interfaces
.implements Lokhttp366/Interceptor;


# instance fields
.field public final client:Lokhttp366/OkHttpClient;


# direct methods
.method public constructor <init>(Lokhttp366/OkHttpClient;)V
    .locals 0
    .param p1, "client"    # Lokhttp366/OkHttpClient;

    .line 30
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 31
    iput-object p1, p0, Lokhttp366/internal/connection/ConnectInterceptor;->client:Lokhttp366/OkHttpClient;

    .line 32
    return-void
.end method


# virtual methods
.method public intercept(Lokhttp366/Interceptor$Chain;)Lokhttp366/Response;
    .locals 6
    .param p1, "chain"    # Lokhttp366/Interceptor$Chain;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 35
    move-object v0, p1

    check-cast v0, Lokhttp366/internal/http/RealInterceptorChain;

    .line 36
    .local v0, "realChain":Lokhttp366/internal/http/RealInterceptorChain;
    invoke-virtual {v0}, Lokhttp366/internal/http/RealInterceptorChain;->request()Lokhttp366/Request;

    move-result-object v1

    .line 37
    .local v1, "request":Lokhttp366/Request;
    invoke-virtual {v0}, Lokhttp366/internal/http/RealInterceptorChain;->transmitter()Lokhttp366/internal/connection/Transmitter;

    move-result-object v2

    .line 40
    .local v2, "transmitter":Lokhttp366/internal/connection/Transmitter;
    invoke-virtual {v1}, Lokhttp366/Request;->method()Ljava/lang/String;

    move-result-object v3

    const-string v4, "GET"

    invoke-virtual {v3, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    xor-int/lit8 v3, v3, 0x1

    .line 41
    .local v3, "doExtensiveHealthChecks":Z
    invoke-virtual {v2, p1, v3}, Lokhttp366/internal/connection/Transmitter;->newExchange(Lokhttp366/Interceptor$Chain;Z)Lokhttp366/internal/connection/Exchange;

    move-result-object v4

    .line 43
    .local v4, "exchange":Lokhttp366/internal/connection/Exchange;
    invoke-virtual {v0, v1, v2, v4}, Lokhttp366/internal/http/RealInterceptorChain;->proceed(Lokhttp366/Request;Lokhttp366/internal/connection/Transmitter;Lokhttp366/internal/connection/Exchange;)Lokhttp366/Response;

    move-result-object v5

    return-object v5
.end method

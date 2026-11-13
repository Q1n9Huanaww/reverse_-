.class public abstract Lokhttp366/internal/Internal;
.super Ljava/lang/Object;
.source "Internal.java"


# static fields
.field public static instance:Lokhttp366/internal/Internal;


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 35
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static initializeInstanceForTests()V
    .locals 1

    .line 39
    new-instance v0, Lokhttp366/OkHttpClient;

    invoke-direct {v0}, Lokhttp366/OkHttpClient;-><init>()V

    .line 40
    return-void
.end method


# virtual methods
.method public abstract addLenient(Lokhttp366/Headers$Builder;Ljava/lang/String;)V
.end method

.method public abstract addLenient(Lokhttp366/Headers$Builder;Ljava/lang/String;Ljava/lang/String;)V
.end method

.method public abstract apply(Lokhttp366/ConnectionSpec;Ljavax/net/ssl/SSLSocket;Z)V
.end method

.method public abstract code(Lokhttp366/Response$Builder;)I
.end method

.method public abstract equalsNonHost(Lokhttp366/Address;Lokhttp366/Address;)Z
.end method

.method public abstract exchange(Lokhttp366/Response;)Lokhttp366/internal/connection/Exchange;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end method

.method public abstract initExchange(Lokhttp366/Response$Builder;Lokhttp366/internal/connection/Exchange;)V
.end method

.method public abstract newWebSocketCall(Lokhttp366/OkHttpClient;Lokhttp366/Request;)Lokhttp366/Call;
.end method

.method public abstract realConnectionPool(Lokhttp366/ConnectionPool;)Lokhttp366/internal/connection/RealConnectionPool;
.end method

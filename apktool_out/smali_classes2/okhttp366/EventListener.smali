.class public abstract Lokhttp366/EventListener;
.super Ljava/lang/Object;
.source "EventListener.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lokhttp366/EventListener$Factory;
    }
.end annotation


# static fields
.field public static final NONE:Lokhttp366/EventListener;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 53
    new-instance v0, Lokhttp366/EventListener$1;

    invoke-direct {v0}, Lokhttp366/EventListener$1;-><init>()V

    sput-object v0, Lokhttp366/EventListener;->NONE:Lokhttp366/EventListener;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static factory(Lokhttp366/EventListener;)Lokhttp366/EventListener$Factory;
    .locals 1
    .param p0, "listener"    # Lokhttp366/EventListener;

    .line 57
    new-instance v0, Lokhttp366/EventListener$$ExternalSyntheticLambda0;

    invoke-direct {v0, p0}, Lokhttp366/EventListener$$ExternalSyntheticLambda0;-><init>(Lokhttp366/EventListener;)V

    return-object v0
.end method

.method static synthetic lambda$factory$0(Lokhttp366/EventListener;Lokhttp366/Call;)Lokhttp366/EventListener;
    .locals 0
    .param p0, "listener"    # Lokhttp366/EventListener;
    .param p1, "call"    # Lokhttp366/Call;

    .line 57
    return-object p0
.end method


# virtual methods
.method public callEnd(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 284
    return-void
.end method

.method public callFailed(Lokhttp366/Call;Ljava/io/IOException;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "ioe"    # Ljava/io/IOException;

    .line 292
    return-void
.end method

.method public callStart(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 69
    return-void
.end method

.method public connectEnd(Lokhttp366/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;Lokhttp366/Protocol;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "inetSocketAddress"    # Ljava/net/InetSocketAddress;
    .param p3, "proxy"    # Ljava/net/Proxy;
    .param p4, "protocol"    # Lokhttp366/Protocol;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param

    .line 135
    return-void
.end method

.method public connectFailed(Lokhttp366/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;Lokhttp366/Protocol;Ljava/io/IOException;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "inetSocketAddress"    # Ljava/net/InetSocketAddress;
    .param p3, "proxy"    # Ljava/net/Proxy;
    .param p4, "protocol"    # Lokhttp366/Protocol;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p5, "ioe"    # Ljava/io/IOException;

    .line 147
    return-void
.end method

.method public connectStart(Lokhttp366/Call;Ljava/net/InetSocketAddress;Ljava/net/Proxy;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "inetSocketAddress"    # Ljava/net/InetSocketAddress;
    .param p3, "proxy"    # Ljava/net/Proxy;

    .line 101
    return-void
.end method

.method public connectionAcquired(Lokhttp366/Call;Lokhttp366/Connection;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "connection"    # Lokhttp366/Connection;

    .line 156
    return-void
.end method

.method public connectionReleased(Lokhttp366/Call;Lokhttp366/Connection;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "connection"    # Lokhttp366/Connection;

    .line 167
    return-void
.end method

.method public dnsEnd(Lokhttp366/Call;Ljava/lang/String;Ljava/util/List;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "domainName"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/Call;",
            "Ljava/lang/String;",
            "Ljava/util/List<",
            "Ljava/net/InetAddress;",
            ">;)V"
        }
    .end annotation

    .line 89
    .local p3, "inetAddressList":Ljava/util/List;, "Ljava/util/List<Ljava/net/InetAddress;>;"
    return-void
.end method

.method public dnsStart(Lokhttp366/Call;Ljava/lang/String;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "domainName"    # Ljava/lang/String;

    .line 81
    return-void
.end method

.method public requestBodyEnd(Lokhttp366/Call;J)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "byteCount"    # J

    .line 211
    return-void
.end method

.method public requestBodyStart(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 203
    return-void
.end method

.method public requestFailed(Lokhttp366/Call;Ljava/io/IOException;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "ioe"    # Ljava/io/IOException;

    .line 220
    return-void
.end method

.method public requestHeadersEnd(Lokhttp366/Call;Lokhttp366/Request;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "request"    # Lokhttp366/Request;

    .line 190
    return-void
.end method

.method public requestHeadersStart(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 179
    return-void
.end method

.method public responseBodyEnd(Lokhttp366/Call;J)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "byteCount"    # J

    .line 266
    return-void
.end method

.method public responseBodyStart(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 255
    return-void
.end method

.method public responseFailed(Lokhttp366/Call;Ljava/io/IOException;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "ioe"    # Ljava/io/IOException;

    .line 275
    return-void
.end method

.method public responseHeadersEnd(Lokhttp366/Call;Lokhttp366/Response;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "response"    # Lokhttp366/Response;

    .line 243
    return-void
.end method

.method public responseHeadersStart(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 232
    return-void
.end method

.method public secureConnectEnd(Lokhttp366/Call;Lokhttp366/Handshake;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "handshake"    # Lokhttp366/Handshake;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param

    .line 124
    return-void
.end method

.method public secureConnectStart(Lokhttp366/Call;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;

    .line 116
    return-void
.end method

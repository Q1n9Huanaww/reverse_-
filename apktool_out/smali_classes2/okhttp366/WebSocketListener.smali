.class public abstract Lokhttp366/WebSocketListener;
.super Ljava/lang/Object;
.source "WebSocketListener.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 21
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClosed(Lokhttp366/WebSocket;ILjava/lang/String;)V
    .locals 0
    .param p1, "webSocket"    # Lokhttp366/WebSocket;
    .param p2, "code"    # I
    .param p3, "reason"    # Ljava/lang/String;

    .line 49
    return-void
.end method

.method public onClosing(Lokhttp366/WebSocket;ILjava/lang/String;)V
    .locals 0
    .param p1, "webSocket"    # Lokhttp366/WebSocket;
    .param p2, "code"    # I
    .param p3, "reason"    # Ljava/lang/String;

    .line 42
    return-void
.end method

.method public onFailure(Lokhttp366/WebSocket;Ljava/lang/Throwable;Lokhttp366/Response;)V
    .locals 0
    .param p1, "webSocket"    # Lokhttp366/WebSocket;
    .param p2, "t"    # Ljava/lang/Throwable;
    .param p3, "response"    # Lokhttp366/Response;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param

    .line 57
    return-void
.end method

.method public onMessage(Lokhttp366/WebSocket;Ljava/lang/String;)V
    .locals 0
    .param p1, "webSocket"    # Lokhttp366/WebSocket;
    .param p2, "text"    # Ljava/lang/String;

    .line 31
    return-void
.end method

.method public onMessage(Lokhttp366/WebSocket;Lokio66/ByteString;)V
    .locals 0
    .param p1, "webSocket"    # Lokhttp366/WebSocket;
    .param p2, "bytes"    # Lokio66/ByteString;

    .line 35
    return-void
.end method

.method public onOpen(Lokhttp366/WebSocket;Lokhttp366/Response;)V
    .locals 0
    .param p1, "webSocket"    # Lokhttp366/WebSocket;
    .param p2, "response"    # Lokhttp366/Response;

    .line 27
    return-void
.end method

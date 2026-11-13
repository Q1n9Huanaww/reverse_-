.class public final Lokhttp366/internal/http/CallServerInterceptor;
.super Ljava/lang/Object;
.source "CallServerInterceptor.java"

# interfaces
.implements Lokhttp366/Interceptor;


# instance fields
.field private final forWebSocket:Z


# direct methods
.method public constructor <init>(Z)V
    .locals 0
    .param p1, "forWebSocket"    # Z

    .line 32
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 33
    iput-boolean p1, p0, Lokhttp366/internal/http/CallServerInterceptor;->forWebSocket:Z

    .line 34
    return-void
.end method


# virtual methods
.method public intercept(Lokhttp366/Interceptor$Chain;)Lokhttp366/Response;
    .locals 14
    .param p1, "chain"    # Lokhttp366/Interceptor$Chain;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 37
    move-object v0, p1

    check-cast v0, Lokhttp366/internal/http/RealInterceptorChain;

    .line 38
    .local v0, "realChain":Lokhttp366/internal/http/RealInterceptorChain;
    invoke-virtual {v0}, Lokhttp366/internal/http/RealInterceptorChain;->exchange()Lokhttp366/internal/connection/Exchange;

    move-result-object v1

    .line 39
    .local v1, "exchange":Lokhttp366/internal/connection/Exchange;
    invoke-virtual {v0}, Lokhttp366/internal/http/RealInterceptorChain;->request()Lokhttp366/Request;

    move-result-object v2

    .line 41
    .local v2, "request":Lokhttp366/Request;
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v3

    .line 43
    .local v3, "sentRequestMillis":J
    invoke-virtual {v1, v2}, Lokhttp366/internal/connection/Exchange;->writeRequestHeaders(Lokhttp366/Request;)V

    .line 45
    const/4 v5, 0x0

    .line 46
    .local v5, "responseHeadersStarted":Z
    const/4 v6, 0x0

    .line 47
    .local v6, "responseBuilder":Lokhttp366/Response$Builder;
    invoke-virtual {v2}, Lokhttp366/Request;->method()Ljava/lang/String;

    move-result-object v7

    invoke-static {v7}, Lokhttp366/internal/http/HttpMethod;->permitsRequestBody(Ljava/lang/String;)Z

    move-result v7

    const/4 v8, 0x0

    if-eqz v7, :cond_3

    invoke-virtual {v2}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v7

    if-eqz v7, :cond_3

    .line 51
    const-string v7, "Expect"

    invoke-virtual {v2, v7}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v7

    const-string v9, "100-continue"

    invoke-virtual {v9, v7}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v7

    const/4 v9, 0x1

    if-eqz v7, :cond_0

    .line 52
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->flushRequest()V

    .line 53
    const/4 v5, 0x1

    .line 54
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->responseHeadersStart()V

    .line 55
    invoke-virtual {v1, v9}, Lokhttp366/internal/connection/Exchange;->readResponseHeaders(Z)Lokhttp366/Response$Builder;

    move-result-object v6

    .line 58
    :cond_0
    if-nez v6, :cond_2

    .line 59
    invoke-virtual {v2}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v7

    invoke-virtual {v7}, Lokhttp366/RequestBody;->isDuplex()Z

    move-result v7

    if-eqz v7, :cond_1

    .line 61
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->flushRequest()V

    .line 62
    nop

    .line 63
    invoke-virtual {v1, v2, v9}, Lokhttp366/internal/connection/Exchange;->createRequestBody(Lokhttp366/Request;Z)Lokio66/Sink;

    move-result-object v7

    .line 62
    invoke-static {v7}, Lokio66/Okio;->buffer(Lokio66/Sink;)Lokio66/BufferedSink;

    move-result-object v7

    .line 64
    .local v7, "bufferedRequestBody":Lokio66/BufferedSink;
    invoke-virtual {v2}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v9

    invoke-virtual {v9, v7}, Lokhttp366/RequestBody;->writeTo(Lokio66/BufferedSink;)V

    .line 65
    .end local v7    # "bufferedRequestBody":Lokio66/BufferedSink;
    goto :goto_0

    .line 67
    :cond_1
    nop

    .line 68
    invoke-virtual {v1, v2, v8}, Lokhttp366/internal/connection/Exchange;->createRequestBody(Lokhttp366/Request;Z)Lokio66/Sink;

    move-result-object v7

    .line 67
    invoke-static {v7}, Lokio66/Okio;->buffer(Lokio66/Sink;)Lokio66/BufferedSink;

    move-result-object v7

    .line 69
    .restart local v7    # "bufferedRequestBody":Lokio66/BufferedSink;
    invoke-virtual {v2}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v9

    invoke-virtual {v9, v7}, Lokhttp366/RequestBody;->writeTo(Lokio66/BufferedSink;)V

    .line 70
    invoke-interface {v7}, Lokio66/BufferedSink;->close()V

    .line 71
    .end local v7    # "bufferedRequestBody":Lokio66/BufferedSink;
    goto :goto_0

    .line 73
    :cond_2
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->noRequestBody()V

    .line 74
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->connection()Lokhttp366/internal/connection/RealConnection;

    move-result-object v7

    invoke-virtual {v7}, Lokhttp366/internal/connection/RealConnection;->isMultiplexed()Z

    move-result v7

    if-nez v7, :cond_4

    .line 78
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->noNewExchangesOnConnection()V

    goto :goto_0

    .line 82
    :cond_3
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->noRequestBody()V

    .line 85
    :cond_4
    :goto_0
    invoke-virtual {v2}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v7

    if-eqz v7, :cond_5

    invoke-virtual {v2}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v7

    invoke-virtual {v7}, Lokhttp366/RequestBody;->isDuplex()Z

    move-result v7

    if-nez v7, :cond_6

    .line 86
    :cond_5
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->finishRequest()V

    .line 89
    :cond_6
    if-nez v5, :cond_7

    .line 90
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->responseHeadersStart()V

    .line 93
    :cond_7
    if-nez v6, :cond_8

    .line 94
    invoke-virtual {v1, v8}, Lokhttp366/internal/connection/Exchange;->readResponseHeaders(Z)Lokhttp366/Response$Builder;

    move-result-object v6

    .line 97
    :cond_8
    nop

    .line 98
    invoke-virtual {v6, v2}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v7

    .line 99
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->connection()Lokhttp366/internal/connection/RealConnection;

    move-result-object v9

    invoke-virtual {v9}, Lokhttp366/internal/connection/RealConnection;->handshake()Lokhttp366/Handshake;

    move-result-object v9

    invoke-virtual {v7, v9}, Lokhttp366/Response$Builder;->handshake(Lokhttp366/Handshake;)Lokhttp366/Response$Builder;

    move-result-object v7

    .line 100
    invoke-virtual {v7, v3, v4}, Lokhttp366/Response$Builder;->sentRequestAtMillis(J)Lokhttp366/Response$Builder;

    move-result-object v7

    .line 101
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v9

    invoke-virtual {v7, v9, v10}, Lokhttp366/Response$Builder;->receivedResponseAtMillis(J)Lokhttp366/Response$Builder;

    move-result-object v7

    .line 102
    invoke-virtual {v7}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v7

    .line 104
    .local v7, "response":Lokhttp366/Response;
    invoke-virtual {v7}, Lokhttp366/Response;->code()I

    move-result v9

    .line 105
    .local v9, "code":I
    const/16 v10, 0x64

    if-ne v9, v10, :cond_9

    .line 108
    invoke-virtual {v1, v8}, Lokhttp366/internal/connection/Exchange;->readResponseHeaders(Z)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 109
    invoke-virtual {v8, v2}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 110
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->connection()Lokhttp366/internal/connection/RealConnection;

    move-result-object v10

    invoke-virtual {v10}, Lokhttp366/internal/connection/RealConnection;->handshake()Lokhttp366/Handshake;

    move-result-object v10

    invoke-virtual {v8, v10}, Lokhttp366/Response$Builder;->handshake(Lokhttp366/Handshake;)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 111
    invoke-virtual {v8, v3, v4}, Lokhttp366/Response$Builder;->sentRequestAtMillis(J)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 112
    invoke-static {}, Ljava/lang/System;->currentTimeMillis()J

    move-result-wide v10

    invoke-virtual {v8, v10, v11}, Lokhttp366/Response$Builder;->receivedResponseAtMillis(J)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 113
    invoke-virtual {v8}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v7

    .line 115
    invoke-virtual {v7}, Lokhttp366/Response;->code()I

    move-result v9

    .line 118
    :cond_9
    invoke-virtual {v1, v7}, Lokhttp366/internal/connection/Exchange;->responseHeadersEnd(Lokhttp366/Response;)V

    .line 120
    iget-boolean v8, p0, Lokhttp366/internal/http/CallServerInterceptor;->forWebSocket:Z

    if-eqz v8, :cond_a

    const/16 v8, 0x65

    if-ne v9, v8, :cond_a

    .line 122
    invoke-virtual {v7}, Lokhttp366/Response;->newBuilder()Lokhttp366/Response$Builder;

    move-result-object v8

    sget-object v10, Lokhttp366/internal/Util;->EMPTY_RESPONSE:Lokhttp366/ResponseBody;

    .line 123
    invoke-virtual {v8, v10}, Lokhttp366/Response$Builder;->body(Lokhttp366/ResponseBody;)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 124
    invoke-virtual {v8}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v7

    goto :goto_1

    .line 126
    :cond_a
    invoke-virtual {v7}, Lokhttp366/Response;->newBuilder()Lokhttp366/Response$Builder;

    move-result-object v8

    .line 127
    invoke-virtual {v1, v7}, Lokhttp366/internal/connection/Exchange;->openResponseBody(Lokhttp366/Response;)Lokhttp366/ResponseBody;

    move-result-object v10

    invoke-virtual {v8, v10}, Lokhttp366/Response$Builder;->body(Lokhttp366/ResponseBody;)Lokhttp366/Response$Builder;

    move-result-object v8

    .line 128
    invoke-virtual {v8}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v7

    .line 131
    :goto_1
    invoke-virtual {v7}, Lokhttp366/Response;->request()Lokhttp366/Request;

    move-result-object v8

    const-string v10, "Connection"

    invoke-virtual {v8, v10}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    const-string v11, "close"

    invoke-virtual {v11, v8}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v8

    if-nez v8, :cond_b

    .line 132
    invoke-virtual {v7, v10}, Lokhttp366/Response;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v11, v8}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v8

    if-eqz v8, :cond_c

    .line 133
    :cond_b
    invoke-virtual {v1}, Lokhttp366/internal/connection/Exchange;->noNewExchangesOnConnection()V

    .line 136
    :cond_c
    const/16 v8, 0xcc

    if-eq v9, v8, :cond_d

    const/16 v8, 0xcd

    if-ne v9, v8, :cond_e

    :cond_d
    invoke-virtual {v7}, Lokhttp366/Response;->body()Lokhttp366/ResponseBody;

    move-result-object v8

    invoke-virtual {v8}, Lokhttp366/ResponseBody;->contentLength()J

    move-result-wide v10

    const-wide/16 v12, 0x0

    cmp-long v8, v10, v12

    if-gtz v8, :cond_f

    .line 141
    :cond_e
    return-object v7

    .line 137
    :cond_f
    new-instance v8, Ljava/net/ProtocolException;

    new-instance v10, Ljava/lang/StringBuilder;

    invoke-direct {v10}, Ljava/lang/StringBuilder;-><init>()V

    const-string v11, "HTTP "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10, v9}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v10

    const-string v11, " had non-zero Content-Length: "

    invoke-virtual {v10, v11}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v10

    .line 138
    invoke-virtual {v7}, Lokhttp366/Response;->body()Lokhttp366/ResponseBody;

    move-result-object v11

    invoke-virtual {v11}, Lokhttp366/ResponseBody;->contentLength()J

    move-result-wide v11

    invoke-virtual {v10, v11, v12}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v10

    invoke-virtual {v10}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v10

    invoke-direct {v8, v10}, Ljava/net/ProtocolException;-><init>(Ljava/lang/String;)V

    throw v8
.end method

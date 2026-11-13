.class public final Lokhttp366/internal/http2/Http2ExchangeCodec;
.super Ljava/lang/Object;
.source "Http2ExchangeCodec.java"

# interfaces
.implements Lokhttp366/internal/http/ExchangeCodec;


# static fields
.field private static final CONNECTION:Ljava/lang/String; = "connection"

.field private static final ENCODING:Ljava/lang/String; = "encoding"

.field private static final HOST:Ljava/lang/String; = "host"

.field private static final HTTP_2_SKIPPED_REQUEST_HEADERS:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final HTTP_2_SKIPPED_RESPONSE_HEADERS:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field

.field private static final KEEP_ALIVE:Ljava/lang/String; = "keep-alive"

.field private static final PROXY_CONNECTION:Ljava/lang/String; = "proxy-connection"

.field private static final TE:Ljava/lang/String; = "te"

.field private static final TRANSFER_ENCODING:Ljava/lang/String; = "transfer-encoding"

.field private static final UPGRADE:Ljava/lang/String; = "upgrade"


# instance fields
.field private volatile canceled:Z

.field private final chain:Lokhttp366/Interceptor$Chain;

.field private final connection:Lokhttp366/internal/http2/Http2Connection;

.field private final protocol:Lokhttp366/Protocol;

.field private final realConnection:Lokhttp366/internal/connection/RealConnection;

.field private volatile stream:Lokhttp366/internal/http2/Http2Stream;


# direct methods
.method static constructor <clinit>()V
    .locals 12

    .line 63
    const-string v0, "connection"

    const-string v1, "host"

    const-string v2, "keep-alive"

    const-string v3, "proxy-connection"

    const-string v4, "te"

    const-string v5, "transfer-encoding"

    const-string v6, "encoding"

    const-string v7, "upgrade"

    const-string v8, ":method"

    const-string v9, ":path"

    const-string v10, ":scheme"

    const-string v11, ":authority"

    filled-new-array/range {v0 .. v11}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lokhttp366/internal/Util;->immutableList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lokhttp366/internal/http2/Http2ExchangeCodec;->HTTP_2_SKIPPED_REQUEST_HEADERS:Ljava/util/List;

    .line 76
    const-string v1, "connection"

    const-string v2, "host"

    const-string v3, "keep-alive"

    const-string v4, "proxy-connection"

    const-string v5, "te"

    const-string v6, "transfer-encoding"

    const-string v7, "encoding"

    const-string v8, "upgrade"

    filled-new-array/range {v1 .. v8}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lokhttp366/internal/Util;->immutableList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    sput-object v0, Lokhttp366/internal/http2/Http2ExchangeCodec;->HTTP_2_SKIPPED_RESPONSE_HEADERS:Ljava/util/List;

    return-void
.end method

.method public constructor <init>(Lokhttp366/OkHttpClient;Lokhttp366/internal/connection/RealConnection;Lokhttp366/Interceptor$Chain;Lokhttp366/internal/http2/Http2Connection;)V
    .locals 2
    .param p1, "client"    # Lokhttp366/OkHttpClient;
    .param p2, "realConnection"    # Lokhttp366/internal/connection/RealConnection;
    .param p3, "chain"    # Lokhttp366/Interceptor$Chain;
    .param p4, "connection"    # Lokhttp366/internal/http2/Http2Connection;

    .line 94
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 95
    iput-object p2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    .line 96
    iput-object p3, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->chain:Lokhttp366/Interceptor$Chain;

    .line 97
    iput-object p4, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->connection:Lokhttp366/internal/http2/Http2Connection;

    .line 98
    invoke-virtual {p1}, Lokhttp366/OkHttpClient;->protocols()Ljava/util/List;

    move-result-object v0

    sget-object v1, Lokhttp366/Protocol;->H2_PRIOR_KNOWLEDGE:Lokhttp366/Protocol;

    invoke-interface {v0, v1}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 99
    sget-object v0, Lokhttp366/Protocol;->H2_PRIOR_KNOWLEDGE:Lokhttp366/Protocol;

    goto :goto_0

    .line 100
    :cond_0
    sget-object v0, Lokhttp366/Protocol;->HTTP_2:Lokhttp366/Protocol;

    :goto_0
    iput-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->protocol:Lokhttp366/Protocol;

    .line 101
    return-void
.end method

.method public static http2HeadersList(Lokhttp366/Request;)Ljava/util/List;
    .locals 8
    .param p0, "request"    # Lokhttp366/Request;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/Request;",
            ")",
            "Ljava/util/List<",
            "Lokhttp366/internal/http2/Header;",
            ">;"
        }
    .end annotation

    .line 145
    invoke-virtual {p0}, Lokhttp366/Request;->headers()Lokhttp366/Headers;

    move-result-object v0

    .line 146
    .local v0, "headers":Lokhttp366/Headers;
    new-instance v1, Ljava/util/ArrayList;

    invoke-virtual {v0}, Lokhttp366/Headers;->size()I

    move-result v2

    add-int/lit8 v2, v2, 0x4

    invoke-direct {v1, v2}, Ljava/util/ArrayList;-><init>(I)V

    .line 147
    .local v1, "result":Ljava/util/List;, "Ljava/util/List<Lokhttp366/internal/http2/Header;>;"
    new-instance v2, Lokhttp366/internal/http2/Header;

    sget-object v3, Lokhttp366/internal/http2/Header;->TARGET_METHOD:Lokio66/ByteString;

    invoke-virtual {p0}, Lokhttp366/Request;->method()Ljava/lang/String;

    move-result-object v4

    invoke-direct {v2, v3, v4}, Lokhttp366/internal/http2/Header;-><init>(Lokio66/ByteString;Ljava/lang/String;)V

    invoke-interface {v1, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 148
    new-instance v2, Lokhttp366/internal/http2/Header;

    sget-object v3, Lokhttp366/internal/http2/Header;->TARGET_PATH:Lokio66/ByteString;

    invoke-virtual {p0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v4

    invoke-static {v4}, Lokhttp366/internal/http/RequestLine;->requestPath(Lokhttp366/HttpUrl;)Ljava/lang/String;

    move-result-object v4

    invoke-direct {v2, v3, v4}, Lokhttp366/internal/http2/Header;-><init>(Lokio66/ByteString;Ljava/lang/String;)V

    invoke-interface {v1, v2}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 149
    const-string v2, "Host"

    invoke-virtual {p0, v2}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    .line 150
    .local v2, "host":Ljava/lang/String;
    if-eqz v2, :cond_0

    .line 151
    new-instance v3, Lokhttp366/internal/http2/Header;

    sget-object v4, Lokhttp366/internal/http2/Header;->TARGET_AUTHORITY:Lokio66/ByteString;

    invoke-direct {v3, v4, v2}, Lokhttp366/internal/http2/Header;-><init>(Lokio66/ByteString;Ljava/lang/String;)V

    invoke-interface {v1, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 153
    :cond_0
    new-instance v3, Lokhttp366/internal/http2/Header;

    sget-object v4, Lokhttp366/internal/http2/Header;->TARGET_SCHEME:Lokio66/ByteString;

    invoke-virtual {p0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v5

    invoke-virtual {v5}, Lokhttp366/HttpUrl;->scheme()Ljava/lang/String;

    move-result-object v5

    invoke-direct {v3, v4, v5}, Lokhttp366/internal/http2/Header;-><init>(Lokio66/ByteString;Ljava/lang/String;)V

    invoke-interface {v1, v3}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 155
    const/4 v3, 0x0

    .local v3, "i":I
    invoke-virtual {v0}, Lokhttp366/Headers;->size()I

    move-result v4

    .local v4, "size":I
    :goto_0
    if-ge v3, v4, :cond_3

    .line 157
    invoke-virtual {v0, v3}, Lokhttp366/Headers;->name(I)Ljava/lang/String;

    move-result-object v5

    sget-object v6, Ljava/util/Locale;->US:Ljava/util/Locale;

    invoke-virtual {v5, v6}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v5

    .line 158
    .local v5, "name":Ljava/lang/String;
    sget-object v6, Lokhttp366/internal/http2/Http2ExchangeCodec;->HTTP_2_SKIPPED_REQUEST_HEADERS:Ljava/util/List;

    invoke-interface {v6, v5}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_1

    .line 159
    const-string v6, "te"

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_2

    invoke-virtual {v0, v3}, Lokhttp366/Headers;->value(I)Ljava/lang/String;

    move-result-object v6

    const-string v7, "trailers"

    invoke-virtual {v6, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_2

    .line 160
    :cond_1
    new-instance v6, Lokhttp366/internal/http2/Header;

    invoke-virtual {v0, v3}, Lokhttp366/Headers;->value(I)Ljava/lang/String;

    move-result-object v7

    invoke-direct {v6, v5, v7}, Lokhttp366/internal/http2/Header;-><init>(Ljava/lang/String;Ljava/lang/String;)V

    invoke-interface {v1, v6}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 155
    .end local v5    # "name":Ljava/lang/String;
    :cond_2
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    .line 163
    .end local v3    # "i":I
    .end local v4    # "size":I
    :cond_3
    return-object v1
.end method

.method public static readHttp2HeadersList(Lokhttp366/Headers;Lokhttp366/Protocol;)Lokhttp366/Response$Builder;
    .locals 8
    .param p0, "headerBlock"    # Lokhttp366/Headers;
    .param p1, "protocol"    # Lokhttp366/Protocol;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 169
    const/4 v0, 0x0

    .line 170
    .local v0, "statusLine":Lokhttp366/internal/http/StatusLine;
    new-instance v1, Lokhttp366/Headers$Builder;

    invoke-direct {v1}, Lokhttp366/Headers$Builder;-><init>()V

    .line 171
    .local v1, "headersBuilder":Lokhttp366/Headers$Builder;
    const/4 v2, 0x0

    .local v2, "i":I
    invoke-virtual {p0}, Lokhttp366/Headers;->size()I

    move-result v3

    .local v3, "size":I
    :goto_0
    if-ge v2, v3, :cond_2

    .line 172
    invoke-virtual {p0, v2}, Lokhttp366/Headers;->name(I)Ljava/lang/String;

    move-result-object v4

    .line 173
    .local v4, "name":Ljava/lang/String;
    invoke-virtual {p0, v2}, Lokhttp366/Headers;->value(I)Ljava/lang/String;

    move-result-object v5

    .line 174
    .local v5, "value":Ljava/lang/String;
    const-string v6, ":status"

    invoke-virtual {v4, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v6

    if-eqz v6, :cond_0

    .line 175
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v7, "HTTP/1.1 "

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v6

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v6}, Lokhttp366/internal/http/StatusLine;->parse(Ljava/lang/String;)Lokhttp366/internal/http/StatusLine;

    move-result-object v0

    goto :goto_1

    .line 176
    :cond_0
    sget-object v6, Lokhttp366/internal/http2/Http2ExchangeCodec;->HTTP_2_SKIPPED_RESPONSE_HEADERS:Ljava/util/List;

    invoke-interface {v6, v4}, Ljava/util/List;->contains(Ljava/lang/Object;)Z

    move-result v6

    if-nez v6, :cond_1

    .line 177
    sget-object v6, Lokhttp366/internal/Internal;->instance:Lokhttp366/internal/Internal;

    invoke-virtual {v6, v1, v4, v5}, Lokhttp366/internal/Internal;->addLenient(Lokhttp366/Headers$Builder;Ljava/lang/String;Ljava/lang/String;)V

    .line 171
    .end local v4    # "name":Ljava/lang/String;
    .end local v5    # "value":Ljava/lang/String;
    :cond_1
    :goto_1
    add-int/lit8 v2, v2, 0x1

    goto :goto_0

    .line 180
    .end local v2    # "i":I
    .end local v3    # "size":I
    :cond_2
    if-eqz v0, :cond_3

    .line 182
    new-instance v2, Lokhttp366/Response$Builder;

    invoke-direct {v2}, Lokhttp366/Response$Builder;-><init>()V

    .line 183
    invoke-virtual {v2, p1}, Lokhttp366/Response$Builder;->protocol(Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v2

    iget v3, v0, Lokhttp366/internal/http/StatusLine;->code:I

    .line 184
    invoke-virtual {v2, v3}, Lokhttp366/Response$Builder;->code(I)Lokhttp366/Response$Builder;

    move-result-object v2

    iget-object v3, v0, Lokhttp366/internal/http/StatusLine;->message:Ljava/lang/String;

    .line 185
    invoke-virtual {v2, v3}, Lokhttp366/Response$Builder;->message(Ljava/lang/String;)Lokhttp366/Response$Builder;

    move-result-object v2

    .line 186
    invoke-virtual {v1}, Lokhttp366/Headers$Builder;->build()Lokhttp366/Headers;

    move-result-object v3

    invoke-virtual {v2, v3}, Lokhttp366/Response$Builder;->headers(Lokhttp366/Headers;)Lokhttp366/Response$Builder;

    move-result-object v2

    .line 182
    return-object v2

    .line 180
    :cond_3
    new-instance v2, Ljava/net/ProtocolException;

    const-string v3, "Expected \':status\' header not present"

    invoke-direct {v2, v3}, Ljava/net/ProtocolException;-><init>(Ljava/lang/String;)V

    throw v2
.end method


# virtual methods
.method public cancel()V
    .locals 2

    .line 202
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->canceled:Z

    .line 203
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    sget-object v1, Lokhttp366/internal/http2/ErrorCode;->CANCEL:Lokhttp366/internal/http2/ErrorCode;

    invoke-virtual {v0, v1}, Lokhttp366/internal/http2/Http2Stream;->closeLater(Lokhttp366/internal/http2/ErrorCode;)V

    .line 204
    :cond_0
    return-void
.end method

.method public connection()Lokhttp366/internal/connection/RealConnection;
    .locals 1

    .line 104
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    return-object v0
.end method

.method public createRequestBody(Lokhttp366/Request;J)Lokio66/Sink;
    .locals 1
    .param p1, "request"    # Lokhttp366/Request;
    .param p2, "contentLength"    # J

    .line 108
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v0}, Lokhttp366/internal/http2/Http2Stream;->getSink()Lokio66/Sink;

    move-result-object v0

    return-object v0
.end method

.method public finishRequest()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 132
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v0}, Lokhttp366/internal/http2/Http2Stream;->getSink()Lokio66/Sink;

    move-result-object v0

    invoke-interface {v0}, Lokio66/Sink;->close()V

    .line 133
    return-void
.end method

.method public flushRequest()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 128
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->connection:Lokhttp366/internal/http2/Http2Connection;

    invoke-virtual {v0}, Lokhttp366/internal/http2/Http2Connection;->flush()V

    .line 129
    return-void
.end method

.method public openResponseBodySource(Lokhttp366/Response;)Lokio66/Source;
    .locals 1
    .param p1, "response"    # Lokhttp366/Response;

    .line 194
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v0}, Lokhttp366/internal/http2/Http2Stream;->getSource()Lokio66/Source;

    move-result-object v0

    return-object v0
.end method

.method public readResponseHeaders(Z)Lokhttp366/Response$Builder;
    .locals 4
    .param p1, "expectContinue"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 136
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v0}, Lokhttp366/internal/http2/Http2Stream;->takeHeaders()Lokhttp366/Headers;

    move-result-object v0

    .line 137
    .local v0, "headers":Lokhttp366/Headers;
    iget-object v1, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->protocol:Lokhttp366/Protocol;

    invoke-static {v0, v1}, Lokhttp366/internal/http2/Http2ExchangeCodec;->readHttp2HeadersList(Lokhttp366/Headers;Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v1

    .line 138
    .local v1, "responseBuilder":Lokhttp366/Response$Builder;
    if-eqz p1, :cond_0

    sget-object v2, Lokhttp366/internal/Internal;->instance:Lokhttp366/internal/Internal;

    invoke-virtual {v2, v1}, Lokhttp366/internal/Internal;->code(Lokhttp366/Response$Builder;)I

    move-result v2

    const/16 v3, 0x64

    if-ne v2, v3, :cond_0

    .line 139
    const/4 v2, 0x0

    return-object v2

    .line 141
    :cond_0
    return-object v1
.end method

.method public reportedContentLength(Lokhttp366/Response;)J
    .locals 2
    .param p1, "response"    # Lokhttp366/Response;

    .line 190
    invoke-static {p1}, Lokhttp366/internal/http/HttpHeaders;->contentLength(Lokhttp366/Response;)J

    move-result-wide v0

    return-wide v0
.end method

.method public trailers()Lokhttp366/Headers;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 198
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v0}, Lokhttp366/internal/http2/Http2Stream;->trailers()Lokhttp366/Headers;

    move-result-object v0

    return-object v0
.end method

.method public writeRequestHeaders(Lokhttp366/Request;)V
    .locals 6
    .param p1, "request"    # Lokhttp366/Request;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 112
    iget-object v0, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    if-eqz v0, :cond_0

    return-void

    .line 114
    :cond_0
    invoke-virtual {p1}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v0

    if-eqz v0, :cond_1

    const/4 v0, 0x1

    goto :goto_0

    :cond_1
    const/4 v0, 0x0

    .line 115
    .local v0, "hasRequestBody":Z
    :goto_0
    invoke-static {p1}, Lokhttp366/internal/http2/Http2ExchangeCodec;->http2HeadersList(Lokhttp366/Request;)Ljava/util/List;

    move-result-object v1

    .line 116
    .local v1, "requestHeaders":Ljava/util/List;, "Ljava/util/List<Lokhttp366/internal/http2/Header;>;"
    iget-object v2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->connection:Lokhttp366/internal/http2/Http2Connection;

    invoke-virtual {v2, v1, v0}, Lokhttp366/internal/http2/Http2Connection;->newStream(Ljava/util/List;Z)Lokhttp366/internal/http2/Http2Stream;

    move-result-object v2

    iput-object v2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    .line 119
    iget-boolean v2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->canceled:Z

    if-nez v2, :cond_2

    .line 123
    iget-object v2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v2}, Lokhttp366/internal/http2/Http2Stream;->readTimeout()Lokio66/Timeout;

    move-result-object v2

    iget-object v3, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->chain:Lokhttp366/Interceptor$Chain;

    invoke-interface {v3}, Lokhttp366/Interceptor$Chain;->readTimeoutMillis()I

    move-result v3

    int-to-long v3, v3

    sget-object v5, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v2, v3, v4, v5}, Lokio66/Timeout;->timeout(JLjava/util/concurrent/TimeUnit;)Lokio66/Timeout;

    .line 124
    iget-object v2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    invoke-virtual {v2}, Lokhttp366/internal/http2/Http2Stream;->writeTimeout()Lokio66/Timeout;

    move-result-object v2

    iget-object v3, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->chain:Lokhttp366/Interceptor$Chain;

    invoke-interface {v3}, Lokhttp366/Interceptor$Chain;->writeTimeoutMillis()I

    move-result v3

    int-to-long v3, v3

    sget-object v5, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-virtual {v2, v3, v4, v5}, Lokio66/Timeout;->timeout(JLjava/util/concurrent/TimeUnit;)Lokio66/Timeout;

    .line 125
    return-void

    .line 120
    :cond_2
    iget-object v2, p0, Lokhttp366/internal/http2/Http2ExchangeCodec;->stream:Lokhttp366/internal/http2/Http2Stream;

    sget-object v3, Lokhttp366/internal/http2/ErrorCode;->CANCEL:Lokhttp366/internal/http2/ErrorCode;

    invoke-virtual {v2, v3}, Lokhttp366/internal/http2/Http2Stream;->closeLater(Lokhttp366/internal/http2/ErrorCode;)V

    .line 121
    new-instance v2, Ljava/io/IOException;

    const-string v3, "Canceled"

    invoke-direct {v2, v3}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    throw v2
.end method

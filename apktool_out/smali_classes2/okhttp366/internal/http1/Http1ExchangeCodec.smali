.class public final Lokhttp366/internal/http1/Http1ExchangeCodec;
.super Ljava/lang/Object;
.source "Http1ExchangeCodec.java"

# interfaces
.implements Lokhttp366/internal/http/ExchangeCodec;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lokhttp366/internal/http1/Http1ExchangeCodec$UnknownLengthSource;,
        Lokhttp366/internal/http1/Http1ExchangeCodec$ChunkedSource;,
        Lokhttp366/internal/http1/Http1ExchangeCodec$FixedLengthSource;,
        Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;,
        Lokhttp366/internal/http1/Http1ExchangeCodec$ChunkedSink;,
        Lokhttp366/internal/http1/Http1ExchangeCodec$KnownLengthSink;
    }
.end annotation


# static fields
.field private static final HEADER_LIMIT:I = 0x40000

.field private static final STATE_CLOSED:I = 0x6

.field private static final STATE_IDLE:I = 0x0

.field private static final STATE_OPEN_REQUEST_BODY:I = 0x1

.field private static final STATE_OPEN_RESPONSE_BODY:I = 0x4

.field private static final STATE_READING_RESPONSE_BODY:I = 0x5

.field private static final STATE_READ_RESPONSE_HEADERS:I = 0x3

.field private static final STATE_WRITING_REQUEST_BODY:I = 0x2


# instance fields
.field private final client:Lokhttp366/OkHttpClient;

.field private headerLimit:J

.field private final realConnection:Lokhttp366/internal/connection/RealConnection;

.field private final sink:Lokio66/BufferedSink;

.field private final source:Lokio66/BufferedSource;

.field private state:I

.field private trailers:Lokhttp366/Headers;


# direct methods
.method public constructor <init>(Lokhttp366/OkHttpClient;Lokhttp366/internal/connection/RealConnection;Lokio66/BufferedSource;Lokio66/BufferedSink;)V
    .locals 2
    .param p1, "client"    # Lokhttp366/OkHttpClient;
    .param p2, "realConnection"    # Lokhttp366/internal/connection/RealConnection;
    .param p3, "source"    # Lokio66/BufferedSource;
    .param p4, "sink"    # Lokio66/BufferedSink;

    .line 94
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 84
    const/4 v0, 0x0

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 85
    const-wide/32 v0, 0x40000

    iput-wide v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->headerLimit:J

    .line 95
    iput-object p1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->client:Lokhttp366/OkHttpClient;

    .line 96
    iput-object p2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    .line 97
    iput-object p3, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->source:Lokio66/BufferedSource;

    .line 98
    iput-object p4, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    .line 99
    return-void
.end method

.method static synthetic access$1000(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokhttp366/OkHttpClient;
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;

    .line 66
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->client:Lokhttp366/OkHttpClient;

    return-object v0
.end method

.method static synthetic access$200(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokio66/BufferedSink;
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;

    .line 66
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    return-object v0
.end method

.method static synthetic access$300(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokio66/ForwardingTimeout;)V
    .locals 0
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;
    .param p1, "x1"    # Lokio66/ForwardingTimeout;

    .line 66
    invoke-direct {p0, p1}, Lokhttp366/internal/http1/Http1ExchangeCodec;->detachTimeout(Lokio66/ForwardingTimeout;)V

    return-void
.end method

.method static synthetic access$400(Lokhttp366/internal/http1/Http1ExchangeCodec;)I
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;

    .line 66
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    return v0
.end method

.method static synthetic access$402(Lokhttp366/internal/http1/Http1ExchangeCodec;I)I
    .locals 0
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;
    .param p1, "x1"    # I

    .line 66
    iput p1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    return p1
.end method

.method static synthetic access$500(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokio66/BufferedSource;
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;

    .line 66
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->source:Lokio66/BufferedSource;

    return-object v0
.end method

.method static synthetic access$600(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokhttp366/internal/connection/RealConnection;
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;

    .line 66
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    return-object v0
.end method

.method static synthetic access$800(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokhttp366/Headers;
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;

    .line 66
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->trailers:Lokhttp366/Headers;

    return-object v0
.end method

.method static synthetic access$802(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokhttp366/Headers;)Lokhttp366/Headers;
    .locals 0
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;
    .param p1, "x1"    # Lokhttp366/Headers;

    .line 66
    iput-object p1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->trailers:Lokhttp366/Headers;

    return-object p1
.end method

.method static synthetic access$900(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokhttp366/Headers;
    .locals 1
    .param p0, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 66
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->readHeaders()Lokhttp366/Headers;

    move-result-object v0

    return-object v0
.end method

.method private detachTimeout(Lokio66/ForwardingTimeout;)V
    .locals 2
    .param p1, "timeout"    # Lokio66/ForwardingTimeout;

    .line 294
    invoke-virtual {p1}, Lokio66/ForwardingTimeout;->delegate()Lokio66/Timeout;

    move-result-object v0

    .line 295
    .local v0, "oldDelegate":Lokio66/Timeout;
    sget-object v1, Lokio66/Timeout;->NONE:Lokio66/Timeout;

    invoke-virtual {p1, v1}, Lokio66/ForwardingTimeout;->setDelegate(Lokio66/Timeout;)Lokio66/ForwardingTimeout;

    .line 296
    invoke-virtual {v0}, Lokio66/Timeout;->clearDeadline()Lokio66/Timeout;

    .line 297
    invoke-virtual {v0}, Lokio66/Timeout;->clearTimeout()Lokio66/Timeout;

    .line 298
    return-void
.end method

.method private newChunkedSink()Lokio66/Sink;
    .locals 3

    .line 258
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 259
    const/4 v0, 0x2

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 260
    new-instance v0, Lokhttp366/internal/http1/Http1ExchangeCodec$ChunkedSink;

    invoke-direct {v0, p0}, Lokhttp366/internal/http1/Http1ExchangeCodec$ChunkedSink;-><init>(Lokhttp366/internal/http1/Http1ExchangeCodec;)V

    return-object v0

    .line 258
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private newChunkedSource(Lokhttp366/HttpUrl;)Lokio66/Source;
    .locals 3
    .param p1, "url"    # Lokhttp366/HttpUrl;

    .line 276
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    .line 277
    const/4 v0, 0x5

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 278
    new-instance v0, Lokhttp366/internal/http1/Http1ExchangeCodec$ChunkedSource;

    invoke-direct {v0, p0, p1}, Lokhttp366/internal/http1/Http1ExchangeCodec$ChunkedSource;-><init>(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokhttp366/HttpUrl;)V

    return-object v0

    .line 276
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private newFixedLengthSource(J)Lokio66/Source;
    .locals 3
    .param p1, "length"    # J

    .line 270
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    .line 271
    const/4 v0, 0x5

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 272
    new-instance v0, Lokhttp366/internal/http1/Http1ExchangeCodec$FixedLengthSource;

    invoke-direct {v0, p0, p1, p2}, Lokhttp366/internal/http1/Http1ExchangeCodec$FixedLengthSource;-><init>(Lokhttp366/internal/http1/Http1ExchangeCodec;J)V

    return-object v0

    .line 270
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private newKnownLengthSink()Lokio66/Sink;
    .locals 3

    .line 264
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x1

    if-ne v0, v1, :cond_0

    .line 265
    const/4 v0, 0x2

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 266
    new-instance v0, Lokhttp366/internal/http1/Http1ExchangeCodec$KnownLengthSink;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lokhttp366/internal/http1/Http1ExchangeCodec$KnownLengthSink;-><init>(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokhttp366/internal/http1/Http1ExchangeCodec$1;)V

    return-object v0

    .line 264
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private newUnknownLengthSource()Lokio66/Source;
    .locals 3

    .line 282
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x4

    if-ne v0, v1, :cond_0

    .line 283
    const/4 v0, 0x5

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 284
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    invoke-virtual {v0}, Lokhttp366/internal/connection/RealConnection;->noNewExchanges()V

    .line 285
    new-instance v0, Lokhttp366/internal/http1/Http1ExchangeCodec$UnknownLengthSource;

    const/4 v1, 0x0

    invoke-direct {v0, p0, v1}, Lokhttp366/internal/http1/Http1ExchangeCodec$UnknownLengthSource;-><init>(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokhttp366/internal/http1/Http1ExchangeCodec$1;)V

    return-object v0

    .line 282
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method private readHeaderLine()Ljava/lang/String;
    .locals 5
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 242
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->source:Lokio66/BufferedSource;

    iget-wide v1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->headerLimit:J

    invoke-interface {v0, v1, v2}, Lokio66/BufferedSource;->readUtf8LineStrict(J)Ljava/lang/String;

    move-result-object v0

    .line 243
    .local v0, "line":Ljava/lang/String;
    iget-wide v1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->headerLimit:J

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v3

    int-to-long v3, v3

    sub-long/2addr v1, v3

    iput-wide v1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->headerLimit:J

    .line 244
    return-object v0
.end method

.method private readHeaders()Lokhttp366/Headers;
    .locals 3
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 249
    new-instance v0, Lokhttp366/Headers$Builder;

    invoke-direct {v0}, Lokhttp366/Headers$Builder;-><init>()V

    .line 251
    .local v0, "headers":Lokhttp366/Headers$Builder;
    :goto_0
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->readHeaderLine()Ljava/lang/String;

    move-result-object v1

    move-object v2, v1

    .local v2, "line":Ljava/lang/String;
    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    if-eqz v1, :cond_0

    .line 252
    sget-object v1, Lokhttp366/internal/Internal;->instance:Lokhttp366/internal/Internal;

    invoke-virtual {v1, v0, v2}, Lokhttp366/internal/Internal;->addLenient(Lokhttp366/Headers$Builder;Ljava/lang/String;)V

    goto :goto_0

    .line 254
    .end local v2    # "line":Ljava/lang/String;
    :cond_0
    invoke-virtual {v0}, Lokhttp366/Headers$Builder;->build()Lokhttp366/Headers;

    move-result-object v1

    return-object v1
.end method


# virtual methods
.method public cancel()V
    .locals 1

    .line 125
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    invoke-virtual {v0}, Lokhttp366/internal/connection/RealConnection;->cancel()V

    .line 126
    :cond_0
    return-void
.end method

.method public connection()Lokhttp366/internal/connection/RealConnection;
    .locals 1

    .line 102
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    return-object v0
.end method

.method public createRequestBody(Lokhttp366/Request;J)Lokio66/Sink;
    .locals 3
    .param p1, "request"    # Lokhttp366/Request;
    .param p2, "contentLength"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 106
    invoke-virtual {p1}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {p1}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp366/RequestBody;->isDuplex()Z

    move-result v0

    if-nez v0, :cond_0

    goto :goto_0

    .line 107
    :cond_0
    new-instance v0, Ljava/net/ProtocolException;

    const-string v1, "Duplex connections are not supported for HTTP/1"

    invoke-direct {v0, v1}, Ljava/net/ProtocolException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 110
    :cond_1
    :goto_0
    const-string v0, "Transfer-Encoding"

    invoke-virtual {p1, v0}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "chunked"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 112
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newChunkedSink()Lokio66/Sink;

    move-result-object v0

    return-object v0

    .line 115
    :cond_2
    const-wide/16 v0, -0x1

    cmp-long v2, p2, v0

    if-eqz v2, :cond_3

    .line 117
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newKnownLengthSink()Lokio66/Sink;

    move-result-object v0

    return-object v0

    .line 120
    :cond_3
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot stream a request body without chunked encoding or a known content length!"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public finishRequest()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 190
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->flush()V

    .line 191
    return-void
.end method

.method public flushRequest()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 186
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->flush()V

    .line 187
    return-void
.end method

.method public isClosed()Z
    .locals 2

    .line 182
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x6

    if-ne v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public openResponseBodySource(Lokhttp366/Response;)Lokio66/Source;
    .locals 5
    .param p1, "response"    # Lokhttp366/Response;

    .line 157
    invoke-static {p1}, Lokhttp366/internal/http/HttpHeaders;->hasBody(Lokhttp366/Response;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 158
    const-wide/16 v0, 0x0

    invoke-direct {p0, v0, v1}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newFixedLengthSource(J)Lokio66/Source;

    move-result-object v0

    return-object v0

    .line 161
    :cond_0
    const-string v0, "Transfer-Encoding"

    invoke-virtual {p1, v0}, Lokhttp366/Response;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "chunked"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 162
    invoke-virtual {p1}, Lokhttp366/Response;->request()Lokhttp366/Request;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v0

    invoke-direct {p0, v0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newChunkedSource(Lokhttp366/HttpUrl;)Lokio66/Source;

    move-result-object v0

    return-object v0

    .line 165
    :cond_1
    invoke-static {p1}, Lokhttp366/internal/http/HttpHeaders;->contentLength(Lokhttp366/Response;)J

    move-result-wide v0

    .line 166
    .local v0, "contentLength":J
    const-wide/16 v2, -0x1

    cmp-long v4, v0, v2

    if-eqz v4, :cond_2

    .line 167
    invoke-direct {p0, v0, v1}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newFixedLengthSource(J)Lokio66/Source;

    move-result-object v2

    return-object v2

    .line 170
    :cond_2
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newUnknownLengthSource()Lokio66/Source;

    move-result-object v2

    return-object v2
.end method

.method public readResponseHeaders(Z)Lokhttp366/Response$Builder;
    .locals 5
    .param p1, "expectContinue"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 208
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x1

    const/4 v2, 0x3

    if-eq v0, v1, :cond_1

    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    if-ne v0, v2, :cond_0

    goto :goto_0

    .line 209
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 213
    :cond_1
    :goto_0
    :try_start_0
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->readHeaderLine()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Lokhttp366/internal/http/StatusLine;->parse(Ljava/lang/String;)Lokhttp366/internal/http/StatusLine;

    move-result-object v0

    .line 215
    .local v0, "statusLine":Lokhttp366/internal/http/StatusLine;
    new-instance v1, Lokhttp366/Response$Builder;

    invoke-direct {v1}, Lokhttp366/Response$Builder;-><init>()V

    iget-object v3, v0, Lokhttp366/internal/http/StatusLine;->protocol:Lokhttp366/Protocol;

    .line 216
    invoke-virtual {v1, v3}, Lokhttp366/Response$Builder;->protocol(Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v1

    iget v3, v0, Lokhttp366/internal/http/StatusLine;->code:I

    .line 217
    invoke-virtual {v1, v3}, Lokhttp366/Response$Builder;->code(I)Lokhttp366/Response$Builder;

    move-result-object v1

    iget-object v3, v0, Lokhttp366/internal/http/StatusLine;->message:Ljava/lang/String;

    .line 218
    invoke-virtual {v1, v3}, Lokhttp366/Response$Builder;->message(Ljava/lang/String;)Lokhttp366/Response$Builder;

    move-result-object v1

    .line 219
    invoke-direct {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->readHeaders()Lokhttp366/Headers;

    move-result-object v3

    invoke-virtual {v1, v3}, Lokhttp366/Response$Builder;->headers(Lokhttp366/Headers;)Lokhttp366/Response$Builder;

    move-result-object v1

    .line 221
    .local v1, "responseBuilder":Lokhttp366/Response$Builder;
    const/16 v3, 0x64

    if-eqz p1, :cond_2

    iget v4, v0, Lokhttp366/internal/http/StatusLine;->code:I

    if-ne v4, v3, :cond_2

    .line 222
    const/4 v2, 0x0

    return-object v2

    .line 223
    :cond_2
    iget v4, v0, Lokhttp366/internal/http/StatusLine;->code:I

    if-ne v4, v3, :cond_3

    .line 224
    iput v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 225
    return-object v1

    .line 228
    :cond_3
    const/4 v2, 0x4

    iput v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I
    :try_end_0
    .catch Ljava/io/EOFException; {:try_start_0 .. :try_end_0} :catch_0

    .line 229
    return-object v1

    .line 230
    .end local v0    # "statusLine":Lokhttp366/internal/http/StatusLine;
    .end local v1    # "responseBuilder":Lokhttp366/Response$Builder;
    :catch_0
    move-exception v0

    .line 232
    .local v0, "e":Ljava/io/EOFException;
    const-string v1, "unknown"

    .line 233
    .local v1, "address":Ljava/lang/String;
    iget-object v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    if-eqz v2, :cond_4

    .line 234
    iget-object v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    invoke-virtual {v2}, Lokhttp366/internal/connection/RealConnection;->route()Lokhttp366/Route;

    move-result-object v2

    invoke-virtual {v2}, Lokhttp366/Route;->address()Lokhttp366/Address;

    move-result-object v2

    invoke-virtual {v2}, Lokhttp366/Address;->url()Lokhttp366/HttpUrl;

    move-result-object v2

    invoke-virtual {v2}, Lokhttp366/HttpUrl;->redact()Ljava/lang/String;

    move-result-object v1

    .line 236
    :cond_4
    new-instance v2, Ljava/io/IOException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "unexpected end of stream on "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3, v0}, Ljava/io/IOException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v2
.end method

.method public reportedContentLength(Lokhttp366/Response;)J
    .locals 2
    .param p1, "response"    # Lokhttp366/Response;

    .line 145
    invoke-static {p1}, Lokhttp366/internal/http/HttpHeaders;->hasBody(Lokhttp366/Response;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 146
    const-wide/16 v0, 0x0

    return-wide v0

    .line 149
    :cond_0
    const-string v0, "Transfer-Encoding"

    invoke-virtual {p1, v0}, Lokhttp366/Response;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "chunked"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_1

    .line 150
    const-wide/16 v0, -0x1

    return-wide v0

    .line 153
    :cond_1
    invoke-static {p1}, Lokhttp366/internal/http/HttpHeaders;->contentLength(Lokhttp366/Response;)J

    move-result-wide v0

    return-wide v0
.end method

.method public skipConnectBody(Lokhttp366/Response;)V
    .locals 5
    .param p1, "response"    # Lokhttp366/Response;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 305
    invoke-static {p1}, Lokhttp366/internal/http/HttpHeaders;->contentLength(Lokhttp366/Response;)J

    move-result-wide v0

    .line 306
    .local v0, "contentLength":J
    const-wide/16 v2, -0x1

    cmp-long v4, v0, v2

    if-nez v4, :cond_0

    return-void

    .line 307
    :cond_0
    invoke-direct {p0, v0, v1}, Lokhttp366/internal/http1/Http1ExchangeCodec;->newFixedLengthSource(J)Lokio66/Source;

    move-result-object v2

    .line 308
    .local v2, "body":Lokio66/Source;
    const v3, 0x7fffffff

    sget-object v4, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    invoke-static {v2, v3, v4}, Lokhttp366/internal/Util;->skipAll(Lokio66/Source;ILjava/util/concurrent/TimeUnit;)Z

    .line 309
    invoke-interface {v2}, Lokio66/Source;->close()V

    .line 310
    return-void
.end method

.method public trailers()Lokhttp366/Headers;
    .locals 2

    .line 174
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    const/4 v1, 0x6

    if-ne v0, v1, :cond_1

    .line 177
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->trailers:Lokhttp366/Headers;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->trailers:Lokhttp366/Headers;

    goto :goto_0

    :cond_0
    sget-object v0, Lokhttp366/internal/Util;->EMPTY_HEADERS:Lokhttp366/Headers;

    :goto_0
    return-object v0

    .line 175
    :cond_1
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "too early; can\'t read the trailers yet"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public writeRequest(Lokhttp366/Headers;Ljava/lang/String;)V
    .locals 5
    .param p1, "headers"    # Lokhttp366/Headers;
    .param p2, "requestLine"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 195
    iget v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    if-nez v0, :cond_1

    .line 196
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    invoke-interface {v0, p2}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    move-result-object v0

    const-string v1, "\r\n"

    invoke-interface {v0, v1}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    .line 197
    const/4 v0, 0x0

    .local v0, "i":I
    invoke-virtual {p1}, Lokhttp366/Headers;->size()I

    move-result v2

    .local v2, "size":I
    :goto_0
    if-ge v0, v2, :cond_0

    .line 198
    iget-object v3, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    invoke-virtual {p1, v0}, Lokhttp366/Headers;->name(I)Ljava/lang/String;

    move-result-object v4

    invoke-interface {v3, v4}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    move-result-object v3

    .line 199
    const-string v4, ": "

    invoke-interface {v3, v4}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    move-result-object v3

    .line 200
    invoke-virtual {p1, v0}, Lokhttp366/Headers;->value(I)Ljava/lang/String;

    move-result-object v4

    invoke-interface {v3, v4}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    move-result-object v3

    .line 201
    invoke-interface {v3, v1}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    .line 197
    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    .line 203
    .end local v0    # "i":I
    .end local v2    # "size":I
    :cond_0
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->sink:Lokio66/BufferedSink;

    invoke-interface {v0, v1}, Lokio66/BufferedSink;->writeUtf8(Ljava/lang/String;)Lokio66/BufferedSink;

    .line 204
    const/4 v0, 0x1

    iput v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    .line 205
    return-void

    .line 195
    :cond_1
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->state:I

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public writeRequestHeaders(Lokhttp366/Request;)V
    .locals 2
    .param p1, "request"    # Lokhttp366/Request;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 139
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec;->realConnection:Lokhttp366/internal/connection/RealConnection;

    .line 140
    invoke-virtual {v0}, Lokhttp366/internal/connection/RealConnection;->route()Lokhttp366/Route;

    move-result-object v0

    invoke-virtual {v0}, Lokhttp366/Route;->proxy()Ljava/net/Proxy;

    move-result-object v0

    invoke-virtual {v0}, Ljava/net/Proxy;->type()Ljava/net/Proxy$Type;

    move-result-object v0

    .line 139
    invoke-static {p1, v0}, Lokhttp366/internal/http/RequestLine;->get(Lokhttp366/Request;Ljava/net/Proxy$Type;)Ljava/lang/String;

    move-result-object v0

    .line 141
    .local v0, "requestLine":Ljava/lang/String;
    invoke-virtual {p1}, Lokhttp366/Request;->headers()Lokhttp366/Headers;

    move-result-object v1

    invoke-virtual {p0, v1, v0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->writeRequest(Lokhttp366/Headers;Ljava/lang/String;)V

    .line 142
    return-void
.end method

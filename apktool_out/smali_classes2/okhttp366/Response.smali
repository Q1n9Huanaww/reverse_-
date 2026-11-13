.class public final Lokhttp366/Response;
.super Ljava/lang/Object;
.source "Response.java"

# interfaces
.implements Ljava/io/Closeable;


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lokhttp366/Response$Builder;
    }
.end annotation


# instance fields
.field final body:Lokhttp366/ResponseBody;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private volatile cacheControl:Lokhttp366/CacheControl;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final cacheResponse:Lokhttp366/Response;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final code:I

.field final exchange:Lokhttp366/internal/connection/Exchange;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final handshake:Lokhttp366/Handshake;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final headers:Lokhttp366/Headers;

.field final message:Ljava/lang/String;

.field final networkResponse:Lokhttp366/Response;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final priorResponse:Lokhttp366/Response;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final protocol:Lokhttp366/Protocol;

.field final receivedResponseAtMillis:J

.field final request:Lokhttp366/Request;

.field final sentRequestAtMillis:J


# direct methods
.method constructor <init>(Lokhttp366/Response$Builder;)V
    .locals 2
    .param p1, "builder"    # Lokhttp366/Response$Builder;

    .line 61
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 62
    iget-object v0, p1, Lokhttp366/Response$Builder;->request:Lokhttp366/Request;

    iput-object v0, p0, Lokhttp366/Response;->request:Lokhttp366/Request;

    .line 63
    iget-object v0, p1, Lokhttp366/Response$Builder;->protocol:Lokhttp366/Protocol;

    iput-object v0, p0, Lokhttp366/Response;->protocol:Lokhttp366/Protocol;

    .line 64
    iget v0, p1, Lokhttp366/Response$Builder;->code:I

    iput v0, p0, Lokhttp366/Response;->code:I

    .line 65
    iget-object v0, p1, Lokhttp366/Response$Builder;->message:Ljava/lang/String;

    iput-object v0, p0, Lokhttp366/Response;->message:Ljava/lang/String;

    .line 66
    iget-object v0, p1, Lokhttp366/Response$Builder;->handshake:Lokhttp366/Handshake;

    iput-object v0, p0, Lokhttp366/Response;->handshake:Lokhttp366/Handshake;

    .line 67
    iget-object v0, p1, Lokhttp366/Response$Builder;->headers:Lokhttp366/Headers$Builder;

    invoke-virtual {v0}, Lokhttp366/Headers$Builder;->build()Lokhttp366/Headers;

    move-result-object v0

    iput-object v0, p0, Lokhttp366/Response;->headers:Lokhttp366/Headers;

    .line 68
    iget-object v0, p1, Lokhttp366/Response$Builder;->body:Lokhttp366/ResponseBody;

    iput-object v0, p0, Lokhttp366/Response;->body:Lokhttp366/ResponseBody;

    .line 69
    iget-object v0, p1, Lokhttp366/Response$Builder;->networkResponse:Lokhttp366/Response;

    iput-object v0, p0, Lokhttp366/Response;->networkResponse:Lokhttp366/Response;

    .line 70
    iget-object v0, p1, Lokhttp366/Response$Builder;->cacheResponse:Lokhttp366/Response;

    iput-object v0, p0, Lokhttp366/Response;->cacheResponse:Lokhttp366/Response;

    .line 71
    iget-object v0, p1, Lokhttp366/Response$Builder;->priorResponse:Lokhttp366/Response;

    iput-object v0, p0, Lokhttp366/Response;->priorResponse:Lokhttp366/Response;

    .line 72
    iget-wide v0, p1, Lokhttp366/Response$Builder;->sentRequestAtMillis:J

    iput-wide v0, p0, Lokhttp366/Response;->sentRequestAtMillis:J

    .line 73
    iget-wide v0, p1, Lokhttp366/Response$Builder;->receivedResponseAtMillis:J

    iput-wide v0, p0, Lokhttp366/Response;->receivedResponseAtMillis:J

    .line 74
    iget-object v0, p1, Lokhttp366/Response$Builder;->exchange:Lokhttp366/internal/connection/Exchange;

    iput-object v0, p0, Lokhttp366/Response;->exchange:Lokhttp366/internal/connection/Exchange;

    .line 75
    return-void
.end method


# virtual methods
.method public body()Lokhttp366/ResponseBody;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 179
    iget-object v0, p0, Lokhttp366/Response;->body:Lokhttp366/ResponseBody;

    return-object v0
.end method

.method public cacheControl()Lokhttp366/CacheControl;
    .locals 2

    .line 257
    iget-object v0, p0, Lokhttp366/Response;->cacheControl:Lokhttp366/CacheControl;

    .line 258
    .local v0, "result":Lokhttp366/CacheControl;
    if-eqz v0, :cond_0

    move-object v1, v0

    goto :goto_0

    :cond_0
    iget-object v1, p0, Lokhttp366/Response;->headers:Lokhttp366/Headers;

    invoke-static {v1}, Lokhttp366/CacheControl;->parse(Lokhttp366/Headers;)Lokhttp366/CacheControl;

    move-result-object v1

    iput-object v1, p0, Lokhttp366/Response;->cacheControl:Lokhttp366/CacheControl;

    :goto_0
    return-object v1
.end method

.method public cacheResponse()Lokhttp366/Response;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 216
    iget-object v0, p0, Lokhttp366/Response;->cacheResponse:Lokhttp366/Response;

    return-object v0
.end method

.method public challenges()Ljava/util/List;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "Lokhttp366/Challenge;",
            ">;"
        }
    .end annotation

    .line 242
    iget v0, p0, Lokhttp366/Response;->code:I

    const/16 v1, 0x191

    if-ne v0, v1, :cond_0

    .line 243
    const-string v0, "WWW-Authenticate"

    .local v0, "responseField":Ljava/lang/String;
    goto :goto_0

    .line 244
    .end local v0    # "responseField":Ljava/lang/String;
    :cond_0
    iget v0, p0, Lokhttp366/Response;->code:I

    const/16 v1, 0x197

    if-ne v0, v1, :cond_1

    .line 245
    const-string v0, "Proxy-Authenticate"

    .line 249
    .restart local v0    # "responseField":Ljava/lang/String;
    :goto_0
    invoke-virtual {p0}, Lokhttp366/Response;->headers()Lokhttp366/Headers;

    move-result-object v1

    invoke-static {v1, v0}, Lokhttp366/internal/http/HttpHeaders;->parseChallenges(Lokhttp366/Headers;Ljava/lang/String;)Ljava/util/List;

    move-result-object v1

    return-object v1

    .line 247
    .end local v0    # "responseField":Ljava/lang/String;
    :cond_1
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

.method public close()V
    .locals 2

    .line 287
    iget-object v0, p0, Lokhttp366/Response;->body:Lokhttp366/ResponseBody;

    if-eqz v0, :cond_0

    .line 290
    iget-object v0, p0, Lokhttp366/Response;->body:Lokhttp366/ResponseBody;

    invoke-virtual {v0}, Lokhttp366/ResponseBody;->close()V

    .line 291
    return-void

    .line 288
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "response is not eligible for a body and must not be closed"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public code()I
    .locals 1

    .line 101
    iget v0, p0, Lokhttp366/Response;->code:I

    return v0
.end method

.method public handshake()Lokhttp366/Handshake;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 122
    iget-object v0, p0, Lokhttp366/Response;->handshake:Lokhttp366/Handshake;

    return-object v0
.end method

.method public header(Ljava/lang/String;)Ljava/lang/String;
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 130
    const/4 v0, 0x0

    invoke-virtual {p0, p1, v0}, Lokhttp366/Response;->header(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public header(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 2
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "defaultValue"    # Ljava/lang/String;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 134
    iget-object v0, p0, Lokhttp366/Response;->headers:Lokhttp366/Headers;

    invoke-virtual {v0, p1}, Lokhttp366/Headers;->get(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 135
    .local v0, "result":Ljava/lang/String;
    if-eqz v0, :cond_0

    move-object v1, v0

    goto :goto_0

    :cond_0
    move-object v1, p2

    :goto_0
    return-object v1
.end method

.method public headers(Ljava/lang/String;)Ljava/util/List;
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Ljava/util/List<",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation

    .line 126
    iget-object v0, p0, Lokhttp366/Response;->headers:Lokhttp366/Headers;

    invoke-virtual {v0, p1}, Lokhttp366/Headers;->values(Ljava/lang/String;)Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

.method public headers()Lokhttp366/Headers;
    .locals 1

    .line 139
    iget-object v0, p0, Lokhttp366/Response;->headers:Lokhttp366/Headers;

    return-object v0
.end method

.method public isRedirect()Z
    .locals 1

    .line 188
    iget v0, p0, Lokhttp366/Response;->code:I

    packed-switch v0, :pswitch_data_0

    .line 197
    :pswitch_0
    const/4 v0, 0x0

    return v0

    .line 195
    :pswitch_1
    const/4 v0, 0x1

    return v0

    nop

    :pswitch_data_0
    .packed-switch 0x12c
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_1
        :pswitch_0
        :pswitch_0
        :pswitch_0
        :pswitch_1
        :pswitch_1
    .end packed-switch
.end method

.method public isSuccessful()Z
    .locals 2

    .line 109
    iget v0, p0, Lokhttp366/Response;->code:I

    const/16 v1, 0xc8

    if-lt v0, v1, :cond_0

    iget v0, p0, Lokhttp366/Response;->code:I

    const/16 v1, 0x12c

    if-ge v0, v1, :cond_0

    const/4 v0, 0x1

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return v0
.end method

.method public message()Ljava/lang/String;
    .locals 1

    .line 114
    iget-object v0, p0, Lokhttp366/Response;->message:Ljava/lang/String;

    return-object v0
.end method

.method public networkResponse()Lokhttp366/Response;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 207
    iget-object v0, p0, Lokhttp366/Response;->networkResponse:Lokhttp366/Response;

    return-object v0
.end method

.method public newBuilder()Lokhttp366/Response$Builder;
    .locals 1

    .line 183
    new-instance v0, Lokhttp366/Response$Builder;

    invoke-direct {v0, p0}, Lokhttp366/Response$Builder;-><init>(Lokhttp366/Response;)V

    return-object v0
.end method

.method public peekBody(J)Lokhttp366/ResponseBody;
    .locals 5
    .param p1, "byteCount"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 163
    iget-object v0, p0, Lokhttp366/Response;->body:Lokhttp366/ResponseBody;

    invoke-virtual {v0}, Lokhttp366/ResponseBody;->source()Lokio66/BufferedSource;

    move-result-object v0

    invoke-interface {v0}, Lokio66/BufferedSource;->peek()Lokio66/BufferedSource;

    move-result-object v0

    .line 164
    .local v0, "peeked":Lokio66/BufferedSource;
    new-instance v1, Lokio66/Buffer;

    invoke-direct {v1}, Lokio66/Buffer;-><init>()V

    .line 165
    .local v1, "buffer":Lokio66/Buffer;
    invoke-interface {v0, p1, p2}, Lokio66/BufferedSource;->request(J)Z

    .line 166
    invoke-interface {v0}, Lokio66/BufferedSource;->getBuffer()Lokio66/Buffer;

    move-result-object v2

    invoke-virtual {v2}, Lokio66/Buffer;->size()J

    move-result-wide v2

    invoke-static {p1, p2, v2, v3}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v2

    invoke-virtual {v1, v0, v2, v3}, Lokio66/Buffer;->write(Lokio66/Source;J)Lokio66/BufferedSink;

    .line 167
    iget-object v2, p0, Lokhttp366/Response;->body:Lokhttp366/ResponseBody;

    invoke-virtual {v2}, Lokhttp366/ResponseBody;->contentType()Lokhttp366/MediaType;

    move-result-object v2

    invoke-virtual {v1}, Lokio66/Buffer;->size()J

    move-result-wide v3

    invoke-static {v2, v3, v4, v1}, Lokhttp366/ResponseBody;->create(Lokhttp366/MediaType;JLokio66/BufferedSource;)Lokhttp366/ResponseBody;

    move-result-object v2

    return-object v2
.end method

.method public priorResponse()Lokhttp366/Response;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 226
    iget-object v0, p0, Lokhttp366/Response;->priorResponse:Lokhttp366/Response;

    return-object v0
.end method

.method public protocol()Lokhttp366/Protocol;
    .locals 1

    .line 96
    iget-object v0, p0, Lokhttp366/Response;->protocol:Lokhttp366/Protocol;

    return-object v0
.end method

.method public receivedResponseAtMillis()J
    .locals 2

    .line 276
    iget-wide v0, p0, Lokhttp366/Response;->receivedResponseAtMillis:J

    return-wide v0
.end method

.method public request()Lokhttp366/Request;
    .locals 1

    .line 89
    iget-object v0, p0, Lokhttp366/Response;->request:Lokhttp366/Request;

    return-object v0
.end method

.method public sentRequestAtMillis()J
    .locals 2

    .line 267
    iget-wide v0, p0, Lokhttp366/Response;->sentRequestAtMillis:J

    return-wide v0
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    .line 294
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "Response{protocol="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lokhttp366/Response;->protocol:Lokhttp366/Protocol;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, ", code="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget v1, p0, Lokhttp366/Response;->code:I

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, ", message="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lokhttp366/Response;->message:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, ", url="

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lokhttp366/Response;->request:Lokhttp366/Request;

    .line 301
    invoke-virtual {v1}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    const/16 v1, 0x7d

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 294
    return-object v0
.end method

.method public trailers()Lokhttp366/Headers;
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 147
    iget-object v0, p0, Lokhttp366/Response;->exchange:Lokhttp366/internal/connection/Exchange;

    if-eqz v0, :cond_0

    .line 148
    iget-object v0, p0, Lokhttp366/Response;->exchange:Lokhttp366/internal/connection/Exchange;

    invoke-virtual {v0}, Lokhttp366/internal/connection/Exchange;->trailers()Lokhttp366/Headers;

    move-result-object v0

    return-object v0

    .line 147
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "trailers not available"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

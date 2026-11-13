.class abstract Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;
.super Ljava/lang/Object;
.source "Http1ExchangeCodec.java"

# interfaces
.implements Lokio66/Source;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/internal/http1/Http1ExchangeCodec;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x402
    name = "AbstractSource"
.end annotation


# instance fields
.field protected closed:Z

.field final synthetic this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

.field protected final timeout:Lokio66/ForwardingTimeout;


# direct methods
.method private constructor <init>(Lokhttp366/internal/http1/Http1ExchangeCodec;)V
    .locals 1

    .line 379
    iput-object p1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 380
    new-instance p1, Lokio66/ForwardingTimeout;

    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$500(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokio66/BufferedSource;

    move-result-object v0

    invoke-interface {v0}, Lokio66/BufferedSource;->timeout()Lokio66/Timeout;

    move-result-object v0

    invoke-direct {p1, v0}, Lokio66/ForwardingTimeout;-><init>(Lokio66/Timeout;)V

    iput-object p1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->timeout:Lokio66/ForwardingTimeout;

    return-void
.end method

.method synthetic constructor <init>(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokhttp366/internal/http1/Http1ExchangeCodec$1;)V
    .locals 0
    .param p1, "x0"    # Lokhttp366/internal/http1/Http1ExchangeCodec;
    .param p2, "x1"    # Lokhttp366/internal/http1/Http1ExchangeCodec$1;

    .line 379
    invoke-direct {p0, p1}, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;-><init>(Lokhttp366/internal/http1/Http1ExchangeCodec;)V

    return-void
.end method


# virtual methods
.method public read(Lokio66/Buffer;J)J
    .locals 2
    .param p1, "sink"    # Lokio66/Buffer;
    .param p2, "byteCount"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 389
    :try_start_0
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$500(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokio66/BufferedSource;

    move-result-object v0

    invoke-interface {v0, p1, p2, p3}, Lokio66/BufferedSource;->read(Lokio66/Buffer;J)J

    move-result-wide v0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-wide v0

    .line 390
    :catch_0
    move-exception v0

    .line 391
    .local v0, "e":Ljava/io/IOException;
    iget-object v1, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v1}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$600(Lokhttp366/internal/http1/Http1ExchangeCodec;)Lokhttp366/internal/connection/RealConnection;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp366/internal/connection/RealConnection;->noNewExchanges()V

    .line 392
    invoke-virtual {p0}, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->responseBodyComplete()V

    .line 393
    throw v0
.end method

.method final responseBodyComplete()V
    .locals 3

    .line 402
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$400(Lokhttp366/internal/http1/Http1ExchangeCodec;)I

    move-result v0

    const/4 v1, 0x6

    if-ne v0, v1, :cond_0

    return-void

    .line 403
    :cond_0
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v0}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$400(Lokhttp366/internal/http1/Http1ExchangeCodec;)I

    move-result v0

    const/4 v2, 0x5

    if-ne v0, v2, :cond_1

    .line 405
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    iget-object v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->timeout:Lokio66/ForwardingTimeout;

    invoke-static {v0, v2}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$300(Lokhttp366/internal/http1/Http1ExchangeCodec;Lokio66/ForwardingTimeout;)V

    .line 407
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v0, v1}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$402(Lokhttp366/internal/http1/Http1ExchangeCodec;I)I

    .line 408
    return-void

    .line 403
    :cond_1
    new-instance v0, Ljava/lang/IllegalStateException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "state: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->this$0:Lokhttp366/internal/http1/Http1ExchangeCodec;

    invoke-static {v2}, Lokhttp366/internal/http1/Http1ExchangeCodec;->access$400(Lokhttp366/internal/http1/Http1ExchangeCodec;)I

    move-result v2

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public timeout()Lokio66/Timeout;
    .locals 1

    .line 384
    iget-object v0, p0, Lokhttp366/internal/http1/Http1ExchangeCodec$AbstractSource;->timeout:Lokio66/ForwardingTimeout;

    return-object v0
.end method

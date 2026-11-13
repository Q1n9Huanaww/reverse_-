.class final Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;
.super Lokhttp366/ResponseBody;
.source "OkHttpCall.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/OkHttpCall;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "ExceptionCatchingResponseBody"
.end annotation


# instance fields
.field private final delegate:Lokhttp366/ResponseBody;

.field private final delegateSource:Lokio66/BufferedSource;

.field thrownException:Ljava/io/IOException;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field


# direct methods
.method constructor <init>(Lokhttp366/ResponseBody;)V
    .locals 2
    .param p1, "delegate"    # Lokhttp366/ResponseBody;

    .line 309
    invoke-direct {p0}, Lokhttp366/ResponseBody;-><init>()V

    .line 310
    iput-object p1, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->delegate:Lokhttp366/ResponseBody;

    .line 311
    new-instance v0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody$1;

    .line 313
    invoke-virtual {p1}, Lokhttp366/ResponseBody;->source()Lokio66/BufferedSource;

    move-result-object v1

    invoke-direct {v0, p0, v1}, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody$1;-><init>(Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;Lokio66/Source;)V

    .line 312
    invoke-static {v0}, Lokio66/Okio;->buffer(Lokio66/Source;)Lokio66/BufferedSource;

    move-result-object v0

    iput-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->delegateSource:Lokio66/BufferedSource;

    .line 324
    return-void
.end method


# virtual methods
.method public close()V
    .locals 1

    .line 343
    iget-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->delegate:Lokhttp366/ResponseBody;

    invoke-virtual {v0}, Lokhttp366/ResponseBody;->close()V

    .line 344
    return-void
.end method

.method public contentLength()J
    .locals 2

    .line 333
    iget-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->delegate:Lokhttp366/ResponseBody;

    invoke-virtual {v0}, Lokhttp366/ResponseBody;->contentLength()J

    move-result-wide v0

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1

    .line 328
    iget-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->delegate:Lokhttp366/ResponseBody;

    invoke-virtual {v0}, Lokhttp366/ResponseBody;->contentType()Lokhttp366/MediaType;

    move-result-object v0

    return-object v0
.end method

.method public source()Lokio66/BufferedSource;
    .locals 1

    .line 338
    iget-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->delegateSource:Lokio66/BufferedSource;

    return-object v0
.end method

.method throwIfCaught()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 347
    iget-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->thrownException:Ljava/io/IOException;

    if-nez v0, :cond_0

    .line 350
    return-void

    .line 348
    :cond_0
    iget-object v0, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->thrownException:Ljava/io/IOException;

    throw v0
.end method

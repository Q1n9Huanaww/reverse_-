.class final Lretrofit266/OkHttpCall$NoContentResponseBody;
.super Lokhttp366/ResponseBody;
.source "OkHttpCall.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/OkHttpCall;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "NoContentResponseBody"
.end annotation


# instance fields
.field private final contentLength:J

.field private final contentType:Lokhttp366/MediaType;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field


# direct methods
.method constructor <init>(Lokhttp366/MediaType;J)V
    .locals 0
    .param p1, "contentType"    # Lokhttp366/MediaType;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "contentLength"    # J

    .line 283
    invoke-direct {p0}, Lokhttp366/ResponseBody;-><init>()V

    .line 284
    iput-object p1, p0, Lretrofit266/OkHttpCall$NoContentResponseBody;->contentType:Lokhttp366/MediaType;

    .line 285
    iput-wide p2, p0, Lretrofit266/OkHttpCall$NoContentResponseBody;->contentLength:J

    .line 286
    return-void
.end method


# virtual methods
.method public contentLength()J
    .locals 2

    .line 295
    iget-wide v0, p0, Lretrofit266/OkHttpCall$NoContentResponseBody;->contentLength:J

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1

    .line 290
    iget-object v0, p0, Lretrofit266/OkHttpCall$NoContentResponseBody;->contentType:Lokhttp366/MediaType;

    return-object v0
.end method

.method public source()Lokio66/BufferedSource;
    .locals 2

    .line 300
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Cannot read raw response body of a converted body."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

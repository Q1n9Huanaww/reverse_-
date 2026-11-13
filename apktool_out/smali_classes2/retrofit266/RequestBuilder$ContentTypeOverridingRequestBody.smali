.class Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;
.super Lokhttp366/RequestBody;
.source "RequestBuilder.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/RequestBuilder;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0xa
    name = "ContentTypeOverridingRequestBody"
.end annotation


# instance fields
.field private final contentType:Lokhttp366/MediaType;

.field private final delegate:Lokhttp366/RequestBody;


# direct methods
.method constructor <init>(Lokhttp366/RequestBody;Lokhttp366/MediaType;)V
    .locals 0
    .param p1, "delegate"    # Lokhttp366/RequestBody;
    .param p2, "contentType"    # Lokhttp366/MediaType;

    .line 277
    invoke-direct {p0}, Lokhttp366/RequestBody;-><init>()V

    .line 278
    iput-object p1, p0, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;->delegate:Lokhttp366/RequestBody;

    .line 279
    iput-object p2, p0, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;->contentType:Lokhttp366/MediaType;

    .line 280
    return-void
.end method


# virtual methods
.method public contentLength()J
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 289
    iget-object v0, p0, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;->delegate:Lokhttp366/RequestBody;

    invoke-virtual {v0}, Lokhttp366/RequestBody;->contentLength()J

    move-result-wide v0

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1

    .line 284
    iget-object v0, p0, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;->contentType:Lokhttp366/MediaType;

    return-object v0
.end method

.method public writeTo(Lokio66/BufferedSink;)V
    .locals 1
    .param p1, "sink"    # Lokio66/BufferedSink;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 294
    iget-object v0, p0, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;->delegate:Lokhttp366/RequestBody;

    invoke-virtual {v0, p1}, Lokhttp366/RequestBody;->writeTo(Lokio66/BufferedSink;)V

    .line 295
    return-void
.end method

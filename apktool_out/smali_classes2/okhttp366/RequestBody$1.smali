.class Lokhttp366/RequestBody$1;
.super Lokhttp366/RequestBody;
.source "RequestBody.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp366/RequestBody;->create(Lokhttp366/MediaType;Lokio66/ByteString;)Lokhttp366/RequestBody;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$content:Lokio66/ByteString;

.field final synthetic val$contentType:Lokhttp366/MediaType;


# direct methods
.method constructor <init>(Lokhttp366/MediaType;Lokio66/ByteString;)V
    .locals 0

    .line 116
    iput-object p1, p0, Lokhttp366/RequestBody$1;->val$contentType:Lokhttp366/MediaType;

    iput-object p2, p0, Lokhttp366/RequestBody$1;->val$content:Lokio66/ByteString;

    invoke-direct {p0}, Lokhttp366/RequestBody;-><init>()V

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

    .line 122
    iget-object v0, p0, Lokhttp366/RequestBody$1;->val$content:Lokio66/ByteString;

    invoke-virtual {v0}, Lokio66/ByteString;->size()I

    move-result v0

    int-to-long v0, v0

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 118
    iget-object v0, p0, Lokhttp366/RequestBody$1;->val$contentType:Lokhttp366/MediaType;

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

    .line 126
    iget-object v0, p0, Lokhttp366/RequestBody$1;->val$content:Lokio66/ByteString;

    invoke-interface {p1, v0}, Lokio66/BufferedSink;->write(Lokio66/ByteString;)Lokio66/BufferedSink;

    .line 127
    return-void
.end method

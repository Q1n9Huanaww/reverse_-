.class Lokhttp366/RequestBody$2;
.super Lokhttp366/RequestBody;
.source "RequestBody.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp366/RequestBody;->create(Lokhttp366/MediaType;[BII)Lokhttp366/RequestBody;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$byteCount:I

.field final synthetic val$content:[B

.field final synthetic val$contentType:Lokhttp366/MediaType;

.field final synthetic val$offset:I


# direct methods
.method constructor <init>(Lokhttp366/MediaType;I[BI)V
    .locals 0

    .line 141
    iput-object p1, p0, Lokhttp366/RequestBody$2;->val$contentType:Lokhttp366/MediaType;

    iput p2, p0, Lokhttp366/RequestBody$2;->val$byteCount:I

    iput-object p3, p0, Lokhttp366/RequestBody$2;->val$content:[B

    iput p4, p0, Lokhttp366/RequestBody$2;->val$offset:I

    invoke-direct {p0}, Lokhttp366/RequestBody;-><init>()V

    return-void
.end method


# virtual methods
.method public contentLength()J
    .locals 2

    .line 147
    iget v0, p0, Lokhttp366/RequestBody$2;->val$byteCount:I

    int-to-long v0, v0

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 143
    iget-object v0, p0, Lokhttp366/RequestBody$2;->val$contentType:Lokhttp366/MediaType;

    return-object v0
.end method

.method public writeTo(Lokio66/BufferedSink;)V
    .locals 3
    .param p1, "sink"    # Lokio66/BufferedSink;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 151
    iget-object v0, p0, Lokhttp366/RequestBody$2;->val$content:[B

    iget v1, p0, Lokhttp366/RequestBody$2;->val$offset:I

    iget v2, p0, Lokhttp366/RequestBody$2;->val$byteCount:I

    invoke-interface {p1, v0, v1, v2}, Lokio66/BufferedSink;->write([BII)Lokio66/BufferedSink;

    .line 152
    return-void
.end method

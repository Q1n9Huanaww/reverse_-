.class Lokhttp366/RequestBody$3;
.super Lokhttp366/RequestBody;
.source "RequestBody.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp366/RequestBody;->create(Lokhttp366/MediaType;Ljava/io/File;)Lokhttp366/RequestBody;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$contentType:Lokhttp366/MediaType;

.field final synthetic val$file:Ljava/io/File;


# direct methods
.method constructor <init>(Lokhttp366/MediaType;Ljava/io/File;)V
    .locals 0

    .line 160
    iput-object p1, p0, Lokhttp366/RequestBody$3;->val$contentType:Lokhttp366/MediaType;

    iput-object p2, p0, Lokhttp366/RequestBody$3;->val$file:Ljava/io/File;

    invoke-direct {p0}, Lokhttp366/RequestBody;-><init>()V

    return-void
.end method


# virtual methods
.method public contentLength()J
    .locals 2

    .line 166
    iget-object v0, p0, Lokhttp366/RequestBody$3;->val$file:Ljava/io/File;

    invoke-virtual {v0}, Ljava/io/File;->length()J

    move-result-wide v0

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 162
    iget-object v0, p0, Lokhttp366/RequestBody$3;->val$contentType:Lokhttp366/MediaType;

    return-object v0
.end method

.method public writeTo(Lokio66/BufferedSink;)V
    .locals 4
    .param p1, "sink"    # Lokio66/BufferedSink;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 170
    iget-object v0, p0, Lokhttp366/RequestBody$3;->val$file:Ljava/io/File;

    invoke-static {v0}, Lokio66/Okio;->source(Ljava/io/File;)Lokio66/Source;

    move-result-object v0

    .line 171
    .local v0, "source":Lokio66/Source;
    :try_start_0
    invoke-interface {p1, v0}, Lokio66/BufferedSink;->writeAll(Lokio66/Source;)J
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 172
    if-eqz v0, :cond_0

    invoke-interface {v0}, Lokio66/Source;->close()V

    .line 173
    .end local v0    # "source":Lokio66/Source;
    :cond_0
    return-void

    .line 170
    .restart local v0    # "source":Lokio66/Source;
    :catchall_0
    move-exception v1

    .end local v0    # "source":Lokio66/Source;
    .end local p1    # "sink":Lokio66/BufferedSink;
    :try_start_1
    throw v1
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 172
    .restart local v0    # "source":Lokio66/Source;
    .restart local p1    # "sink":Lokio66/BufferedSink;
    :catchall_1
    move-exception v2

    if-eqz v0, :cond_1

    :try_start_2
    invoke-interface {v0}, Lokio66/Source;->close()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    goto :goto_0

    :catchall_2
    move-exception v3

    invoke-virtual {v1, v3}, Ljava/lang/Throwable;->addSuppressed(Ljava/lang/Throwable;)V

    :cond_1
    :goto_0
    throw v2
.end method

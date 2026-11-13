.class Lokhttp366/internal/cache/CacheInterceptor$1;
.super Ljava/lang/Object;
.source "CacheInterceptor.java"

# interfaces
.implements Lokio66/Source;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp366/internal/cache/CacheInterceptor;->cacheWritingResponse(Lokhttp366/internal/cache/CacheRequest;Lokhttp366/Response;)Lokhttp366/Response;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field cacheRequestClosed:Z

.field final synthetic this$0:Lokhttp366/internal/cache/CacheInterceptor;

.field final synthetic val$cacheBody:Lokio66/BufferedSink;

.field final synthetic val$cacheRequest:Lokhttp366/internal/cache/CacheRequest;

.field final synthetic val$source:Lokio66/BufferedSource;


# direct methods
.method constructor <init>(Lokhttp366/internal/cache/CacheInterceptor;Lokio66/BufferedSource;Lokhttp366/internal/cache/CacheRequest;Lokio66/BufferedSink;)V
    .locals 0
    .param p1, "this$0"    # Lokhttp366/internal/cache/CacheInterceptor;

    .line 169
    iput-object p1, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->this$0:Lokhttp366/internal/cache/CacheInterceptor;

    iput-object p2, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$source:Lokio66/BufferedSource;

    iput-object p3, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheRequest:Lokhttp366/internal/cache/CacheRequest;

    iput-object p4, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheBody:Lokio66/BufferedSink;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public close()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 202
    iget-boolean v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->cacheRequestClosed:Z

    if-nez v0, :cond_0

    sget-object v0, Ljava/util/concurrent/TimeUnit;->MILLISECONDS:Ljava/util/concurrent/TimeUnit;

    .line 203
    const/16 v1, 0x64

    invoke-static {p0, v1, v0}, Lokhttp366/internal/Util;->discard(Lokio66/Source;ILjava/util/concurrent/TimeUnit;)Z

    move-result v0

    if-nez v0, :cond_0

    .line 204
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->cacheRequestClosed:Z

    .line 205
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheRequest:Lokhttp366/internal/cache/CacheRequest;

    invoke-interface {v0}, Lokhttp366/internal/cache/CacheRequest;->abort()V

    .line 207
    :cond_0
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$source:Lokio66/BufferedSource;

    invoke-interface {v0}, Lokio66/BufferedSource;->close()V

    .line 208
    return-void
.end method

.method public read(Lokio66/Buffer;J)J
    .locals 9
    .param p1, "sink"    # Lokio66/Buffer;
    .param p2, "byteCount"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 175
    const/4 v0, 0x1

    :try_start_0
    iget-object v1, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$source:Lokio66/BufferedSource;

    invoke-interface {v1, p1, p2, p3}, Lokio66/BufferedSource;->read(Lokio66/Buffer;J)J

    move-result-wide v1
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 182
    .local v1, "bytesRead":J
    nop

    .line 184
    const-wide/16 v3, -0x1

    cmp-long v5, v1, v3

    if-nez v5, :cond_1

    .line 185
    iget-boolean v5, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->cacheRequestClosed:Z

    if-nez v5, :cond_0

    .line 186
    iput-boolean v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->cacheRequestClosed:Z

    .line 187
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheBody:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->close()V

    .line 189
    :cond_0
    return-wide v3

    .line 192
    :cond_1
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheBody:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->buffer()Lokio66/Buffer;

    move-result-object v4

    invoke-virtual {p1}, Lokio66/Buffer;->size()J

    move-result-wide v5

    sub-long/2addr v5, v1

    move-object v3, p1

    move-wide v7, v1

    invoke-virtual/range {v3 .. v8}, Lokio66/Buffer;->copyTo(Lokio66/Buffer;JJ)Lokio66/Buffer;

    .line 193
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheBody:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->emitCompleteSegments()Lokio66/BufferedSink;

    .line 194
    return-wide v1

    .line 176
    .end local v1    # "bytesRead":J
    :catch_0
    move-exception v1

    .line 177
    .local v1, "e":Ljava/io/IOException;
    iget-boolean v2, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->cacheRequestClosed:Z

    if-nez v2, :cond_2

    .line 178
    iput-boolean v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->cacheRequestClosed:Z

    .line 179
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$cacheRequest:Lokhttp366/internal/cache/CacheRequest;

    invoke-interface {v0}, Lokhttp366/internal/cache/CacheRequest;->abort()V

    .line 181
    :cond_2
    throw v1
.end method

.method public timeout()Lokio66/Timeout;
    .locals 1

    .line 198
    iget-object v0, p0, Lokhttp366/internal/cache/CacheInterceptor$1;->val$source:Lokio66/BufferedSource;

    invoke-interface {v0}, Lokio66/BufferedSource;->timeout()Lokio66/Timeout;

    move-result-object v0

    return-object v0
.end method

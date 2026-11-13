.class Lokhttp366/Cache$CacheRequestImpl$1;
.super Lokio66/ForwardingSink;
.source "Cache.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp366/Cache$CacheRequestImpl;-><init>(Lokhttp366/Cache;Lokhttp366/internal/cache/DiskLruCache$Editor;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$1:Lokhttp366/Cache$CacheRequestImpl;

.field final synthetic val$editor:Lokhttp366/internal/cache/DiskLruCache$Editor;

.field final synthetic val$this$0:Lokhttp366/Cache;


# direct methods
.method constructor <init>(Lokhttp366/Cache$CacheRequestImpl;Lokio66/Sink;Lokhttp366/Cache;Lokhttp366/internal/cache/DiskLruCache$Editor;)V
    .locals 0
    .param p1, "this$1"    # Lokhttp366/Cache$CacheRequestImpl;
    .param p2, "x0"    # Lokio66/Sink;

    .line 440
    iput-object p1, p0, Lokhttp366/Cache$CacheRequestImpl$1;->this$1:Lokhttp366/Cache$CacheRequestImpl;

    iput-object p3, p0, Lokhttp366/Cache$CacheRequestImpl$1;->val$this$0:Lokhttp366/Cache;

    iput-object p4, p0, Lokhttp366/Cache$CacheRequestImpl$1;->val$editor:Lokhttp366/internal/cache/DiskLruCache$Editor;

    invoke-direct {p0, p2}, Lokio66/ForwardingSink;-><init>(Lokio66/Sink;)V

    return-void
.end method


# virtual methods
.method public close()V
    .locals 4
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 442
    iget-object v0, p0, Lokhttp366/Cache$CacheRequestImpl$1;->this$1:Lokhttp366/Cache$CacheRequestImpl;

    iget-object v0, v0, Lokhttp366/Cache$CacheRequestImpl;->this$0:Lokhttp366/Cache;

    monitor-enter v0

    .line 443
    :try_start_0
    iget-object v1, p0, Lokhttp366/Cache$CacheRequestImpl$1;->this$1:Lokhttp366/Cache$CacheRequestImpl;

    iget-boolean v1, v1, Lokhttp366/Cache$CacheRequestImpl;->done:Z

    if-eqz v1, :cond_0

    .line 444
    monitor-exit v0

    return-void

    .line 446
    :cond_0
    iget-object v1, p0, Lokhttp366/Cache$CacheRequestImpl$1;->this$1:Lokhttp366/Cache$CacheRequestImpl;

    const/4 v2, 0x1

    iput-boolean v2, v1, Lokhttp366/Cache$CacheRequestImpl;->done:Z

    .line 447
    iget-object v1, p0, Lokhttp366/Cache$CacheRequestImpl$1;->this$1:Lokhttp366/Cache$CacheRequestImpl;

    iget-object v1, v1, Lokhttp366/Cache$CacheRequestImpl;->this$0:Lokhttp366/Cache;

    iget v3, v1, Lokhttp366/Cache;->writeSuccessCount:I

    add-int/2addr v3, v2

    iput v3, v1, Lokhttp366/Cache;->writeSuccessCount:I

    .line 448
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 449
    invoke-super {p0}, Lokio66/ForwardingSink;->close()V

    .line 450
    iget-object v0, p0, Lokhttp366/Cache$CacheRequestImpl$1;->val$editor:Lokhttp366/internal/cache/DiskLruCache$Editor;

    invoke-virtual {v0}, Lokhttp366/internal/cache/DiskLruCache$Editor;->commit()V

    .line 451
    return-void

    .line 448
    :catchall_0
    move-exception v1

    :try_start_1
    monitor-exit v0
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    throw v1
.end method

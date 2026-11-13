.class Lokhttp366/Cache$1;
.super Ljava/lang/Object;
.source "Cache.java"

# interfaces
.implements Lokhttp366/internal/cache/InternalCache;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/Cache;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lokhttp366/Cache;


# direct methods
.method constructor <init>(Lokhttp366/Cache;)V
    .locals 0
    .param p1, "this$0"    # Lokhttp366/Cache;

    .line 143
    iput-object p1, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public get(Lokhttp366/Request;)Lokhttp366/Response;
    .locals 1
    .param p1, "request"    # Lokhttp366/Request;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 145
    iget-object v0, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-virtual {v0, p1}, Lokhttp366/Cache;->get(Lokhttp366/Request;)Lokhttp366/Response;

    move-result-object v0

    return-object v0
.end method

.method public put(Lokhttp366/Response;)Lokhttp366/internal/cache/CacheRequest;
    .locals 1
    .param p1, "response"    # Lokhttp366/Response;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 149
    iget-object v0, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-virtual {v0, p1}, Lokhttp366/Cache;->put(Lokhttp366/Response;)Lokhttp366/internal/cache/CacheRequest;

    move-result-object v0

    return-object v0
.end method

.method public remove(Lokhttp366/Request;)V
    .locals 1
    .param p1, "request"    # Lokhttp366/Request;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 153
    iget-object v0, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-virtual {v0, p1}, Lokhttp366/Cache;->remove(Lokhttp366/Request;)V

    .line 154
    return-void
.end method

.method public trackConditionalCacheHit()V
    .locals 1

    .line 161
    iget-object v0, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-virtual {v0}, Lokhttp366/Cache;->trackConditionalCacheHit()V

    .line 162
    return-void
.end method

.method public trackResponse(Lokhttp366/internal/cache/CacheStrategy;)V
    .locals 1
    .param p1, "cacheStrategy"    # Lokhttp366/internal/cache/CacheStrategy;

    .line 165
    iget-object v0, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-virtual {v0, p1}, Lokhttp366/Cache;->trackResponse(Lokhttp366/internal/cache/CacheStrategy;)V

    .line 166
    return-void
.end method

.method public update(Lokhttp366/Response;Lokhttp366/Response;)V
    .locals 1
    .param p1, "cached"    # Lokhttp366/Response;
    .param p2, "network"    # Lokhttp366/Response;

    .line 157
    iget-object v0, p0, Lokhttp366/Cache$1;->this$0:Lokhttp366/Cache;

    invoke-virtual {v0, p1, p2}, Lokhttp366/Cache;->update(Lokhttp366/Response;Lokhttp366/Response;)V

    .line 158
    return-void
.end method

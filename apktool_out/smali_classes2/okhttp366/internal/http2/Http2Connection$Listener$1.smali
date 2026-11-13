.class Lokhttp366/internal/http2/Http2Connection$Listener$1;
.super Lokhttp366/internal/http2/Http2Connection$Listener;
.source "Http2Connection.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/internal/http2/Http2Connection$Listener;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 992
    invoke-direct {p0}, Lokhttp366/internal/http2/Http2Connection$Listener;-><init>()V

    return-void
.end method


# virtual methods
.method public onStream(Lokhttp366/internal/http2/Http2Stream;)V
    .locals 2
    .param p1, "stream"    # Lokhttp366/internal/http2/Http2Stream;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 994
    sget-object v0, Lokhttp366/internal/http2/ErrorCode;->REFUSED_STREAM:Lokhttp366/internal/http2/ErrorCode;

    const/4 v1, 0x0

    invoke-virtual {p1, v0, v1}, Lokhttp366/internal/http2/Http2Stream;->close(Lokhttp366/internal/http2/ErrorCode;Ljava/io/IOException;)V

    .line 995
    return-void
.end method

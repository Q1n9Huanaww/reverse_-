.class public final Lokhttp366/internal/http/RealResponseBody;
.super Lokhttp366/ResponseBody;
.source "RealResponseBody.java"


# instance fields
.field private final contentLength:J

.field private final contentTypeString:Ljava/lang/String;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final source:Lokio66/BufferedSource;


# direct methods
.method public constructor <init>(Ljava/lang/String;JLokio66/BufferedSource;)V
    .locals 0
    .param p1, "contentTypeString"    # Ljava/lang/String;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "contentLength"    # J
    .param p4, "source"    # Lokio66/BufferedSource;

    .line 33
    invoke-direct {p0}, Lokhttp366/ResponseBody;-><init>()V

    .line 34
    iput-object p1, p0, Lokhttp366/internal/http/RealResponseBody;->contentTypeString:Ljava/lang/String;

    .line 35
    iput-wide p2, p0, Lokhttp366/internal/http/RealResponseBody;->contentLength:J

    .line 36
    iput-object p4, p0, Lokhttp366/internal/http/RealResponseBody;->source:Lokio66/BufferedSource;

    .line 37
    return-void
.end method


# virtual methods
.method public contentLength()J
    .locals 2

    .line 44
    iget-wide v0, p0, Lokhttp366/internal/http/RealResponseBody;->contentLength:J

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1

    .line 40
    iget-object v0, p0, Lokhttp366/internal/http/RealResponseBody;->contentTypeString:Ljava/lang/String;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokhttp366/internal/http/RealResponseBody;->contentTypeString:Ljava/lang/String;

    invoke-static {v0}, Lokhttp366/MediaType;->parse(Ljava/lang/String;)Lokhttp366/MediaType;

    move-result-object v0

    goto :goto_0

    :cond_0
    const/4 v0, 0x0

    :goto_0
    return-object v0
.end method

.method public source()Lokio66/BufferedSource;
    .locals 1

    .line 48
    iget-object v0, p0, Lokhttp366/internal/http/RealResponseBody;->source:Lokio66/BufferedSource;

    return-object v0
.end method

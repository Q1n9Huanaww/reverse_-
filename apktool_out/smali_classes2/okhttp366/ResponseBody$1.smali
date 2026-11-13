.class Lokhttp366/ResponseBody$1;
.super Lokhttp366/ResponseBody;
.source "ResponseBody.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lokhttp366/ResponseBody;->create(Lokhttp366/MediaType;JLokio66/BufferedSource;)Lokhttp366/ResponseBody;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$content:Lokio66/BufferedSource;

.field final synthetic val$contentLength:J

.field final synthetic val$contentType:Lokhttp366/MediaType;


# direct methods
.method constructor <init>(Lokhttp366/MediaType;JLokio66/BufferedSource;)V
    .locals 0

    .line 228
    iput-object p1, p0, Lokhttp366/ResponseBody$1;->val$contentType:Lokhttp366/MediaType;

    iput-wide p2, p0, Lokhttp366/ResponseBody$1;->val$contentLength:J

    iput-object p4, p0, Lokhttp366/ResponseBody$1;->val$content:Lokio66/BufferedSource;

    invoke-direct {p0}, Lokhttp366/ResponseBody;-><init>()V

    return-void
.end method


# virtual methods
.method public contentLength()J
    .locals 2

    .line 234
    iget-wide v0, p0, Lokhttp366/ResponseBody$1;->val$contentLength:J

    return-wide v0
.end method

.method public contentType()Lokhttp366/MediaType;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 230
    iget-object v0, p0, Lokhttp366/ResponseBody$1;->val$contentType:Lokhttp366/MediaType;

    return-object v0
.end method

.method public source()Lokio66/BufferedSource;
    .locals 1

    .line 238
    iget-object v0, p0, Lokhttp366/ResponseBody$1;->val$content:Lokio66/BufferedSource;

    return-object v0
.end method

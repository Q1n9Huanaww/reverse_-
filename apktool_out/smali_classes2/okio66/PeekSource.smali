.class final Lokio66/PeekSource;
.super Ljava/lang/Object;
.source "PeekSource.java"

# interfaces
.implements Lokio66/Source;


# instance fields
.field private final buffer:Lokio66/Buffer;

.field private closed:Z

.field private expectedPos:I

.field private expectedSegment:Lokio66/Segment;

.field private pos:J

.field private final upstream:Lokio66/BufferedSource;


# direct methods
.method constructor <init>(Lokio66/BufferedSource;)V
    .locals 1
    .param p1, "upstream"    # Lokio66/BufferedSource;

    .line 39
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 40
    iput-object p1, p0, Lokio66/PeekSource;->upstream:Lokio66/BufferedSource;

    .line 41
    invoke-interface {p1}, Lokio66/BufferedSource;->buffer()Lokio66/Buffer;

    move-result-object v0

    iput-object v0, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    .line 42
    iget-object v0, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-object v0, v0, Lokio66/Buffer;->head:Lokio66/Segment;

    iput-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    .line 43
    iget-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    if-eqz v0, :cond_0

    iget-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    iget v0, v0, Lokio66/Segment;->pos:I

    goto :goto_0

    :cond_0
    const/4 v0, -0x1

    :goto_0
    iput v0, p0, Lokio66/PeekSource;->expectedPos:I

    .line 44
    return-void
.end method


# virtual methods
.method public close()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 78
    const/4 v0, 0x1

    iput-boolean v0, p0, Lokio66/PeekSource;->closed:Z

    .line 79
    return-void
.end method

.method public read(Lokio66/Buffer;J)J
    .locals 8
    .param p1, "sink"    # Lokio66/Buffer;
    .param p2, "byteCount"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 47
    iget-boolean v0, p0, Lokio66/PeekSource;->closed:Z

    if-nez v0, :cond_4

    .line 51
    iget-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    if-eqz v0, :cond_1

    iget-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    iget-object v1, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-object v1, v1, Lokio66/Buffer;->head:Lokio66/Segment;

    if-ne v0, v1, :cond_0

    iget v0, p0, Lokio66/PeekSource;->expectedPos:I

    iget-object v1, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-object v1, v1, Lokio66/Buffer;->head:Lokio66/Segment;

    iget v1, v1, Lokio66/Segment;->pos:I

    if-ne v0, v1, :cond_0

    goto :goto_0

    .line 53
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Peek source is invalid because upstream source was used"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 56
    :cond_1
    :goto_0
    iget-object v0, p0, Lokio66/PeekSource;->upstream:Lokio66/BufferedSource;

    iget-wide v1, p0, Lokio66/PeekSource;->pos:J

    add-long/2addr v1, p2

    invoke-interface {v0, v1, v2}, Lokio66/BufferedSource;->request(J)Z

    .line 57
    iget-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    if-nez v0, :cond_2

    iget-object v0, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-object v0, v0, Lokio66/Buffer;->head:Lokio66/Segment;

    if-eqz v0, :cond_2

    .line 61
    iget-object v0, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-object v0, v0, Lokio66/Buffer;->head:Lokio66/Segment;

    iput-object v0, p0, Lokio66/PeekSource;->expectedSegment:Lokio66/Segment;

    .line 62
    iget-object v0, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-object v0, v0, Lokio66/Buffer;->head:Lokio66/Segment;

    iget v0, v0, Lokio66/Segment;->pos:I

    iput v0, p0, Lokio66/PeekSource;->expectedPos:I

    .line 65
    :cond_2
    iget-object v0, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-wide v0, v0, Lokio66/Buffer;->size:J

    iget-wide v2, p0, Lokio66/PeekSource;->pos:J

    sub-long/2addr v0, v2

    invoke-static {p2, p3, v0, v1}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v0

    .line 66
    .local v0, "toCopy":J
    const-wide/16 v2, 0x0

    cmp-long v4, v0, v2

    if-gtz v4, :cond_3

    const-wide/16 v2, -0x1

    return-wide v2

    .line 68
    :cond_3
    iget-object v2, p0, Lokio66/PeekSource;->buffer:Lokio66/Buffer;

    iget-wide v4, p0, Lokio66/PeekSource;->pos:J

    move-object v3, p1

    move-wide v6, v0

    invoke-virtual/range {v2 .. v7}, Lokio66/Buffer;->copyTo(Lokio66/Buffer;JJ)Lokio66/Buffer;

    .line 69
    iget-wide v2, p0, Lokio66/PeekSource;->pos:J

    add-long/2addr v2, v0

    iput-wide v2, p0, Lokio66/PeekSource;->pos:J

    .line 70
    return-wide v0

    .line 47
    .end local v0    # "toCopy":J
    :cond_4
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "closed"

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public timeout()Lokio66/Timeout;
    .locals 1

    .line 74
    iget-object v0, p0, Lokio66/PeekSource;->upstream:Lokio66/BufferedSource;

    invoke-interface {v0}, Lokio66/BufferedSource;->timeout()Lokio66/Timeout;

    move-result-object v0

    return-object v0
.end method

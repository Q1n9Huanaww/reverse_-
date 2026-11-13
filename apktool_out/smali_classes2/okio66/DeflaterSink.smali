.class public final Lokio66/DeflaterSink;
.super Ljava/lang/Object;
.source "DeflaterSink.java"

# interfaces
.implements Lokio66/Sink;


# instance fields
.field private closed:Z

.field private final deflater:Ljava/util/zip/Deflater;

.field private final sink:Lokio66/BufferedSink;


# direct methods
.method constructor <init>(Lokio66/BufferedSink;Ljava/util/zip/Deflater;)V
    .locals 2
    .param p1, "sink"    # Lokio66/BufferedSink;
    .param p2, "deflater"    # Ljava/util/zip/Deflater;

    .line 52
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 53
    if-eqz p1, :cond_1

    .line 54
    if-eqz p2, :cond_0

    .line 55
    iput-object p1, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    .line 56
    iput-object p2, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    .line 57
    return-void

    .line 54
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "inflater == null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 53
    :cond_1
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "source == null"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public constructor <init>(Lokio66/Sink;Ljava/util/zip/Deflater;)V
    .locals 1
    .param p1, "sink"    # Lokio66/Sink;
    .param p2, "deflater"    # Ljava/util/zip/Deflater;

    .line 44
    invoke-static {p1}, Lokio66/Okio;->buffer(Lokio66/Sink;)Lokio66/BufferedSink;

    move-result-object v0

    invoke-direct {p0, v0, p2}, Lokio66/DeflaterSink;-><init>(Lokio66/BufferedSink;Ljava/util/zip/Deflater;)V

    .line 45
    return-void
.end method

.method private deflate(Z)V
    .locals 7
    .param p1, "syncFlush"    # Z
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 84
    iget-object v0, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->buffer()Lokio66/Buffer;

    move-result-object v0

    .line 86
    .local v0, "buffer":Lokio66/Buffer;
    :goto_0
    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Lokio66/Buffer;->writableSegment(I)Lokio66/Segment;

    move-result-object v1

    .line 92
    .local v1, "s":Lokio66/Segment;
    if-eqz p1, :cond_0

    .line 93
    iget-object v2, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    iget-object v3, v1, Lokio66/Segment;->data:[B

    iget v4, v1, Lokio66/Segment;->limit:I

    iget v5, v1, Lokio66/Segment;->limit:I

    rsub-int v5, v5, 0x2000

    const/4 v6, 0x2

    invoke-virtual {v2, v3, v4, v5, v6}, Ljava/util/zip/Deflater;->deflate([BIII)I

    move-result v2

    goto :goto_1

    .line 94
    :cond_0
    iget-object v2, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    iget-object v3, v1, Lokio66/Segment;->data:[B

    iget v4, v1, Lokio66/Segment;->limit:I

    iget v5, v1, Lokio66/Segment;->limit:I

    rsub-int v5, v5, 0x2000

    invoke-virtual {v2, v3, v4, v5}, Ljava/util/zip/Deflater;->deflate([BII)I

    move-result v2

    :goto_1
    nop

    .line 96
    .local v2, "deflated":I
    if-lez v2, :cond_1

    .line 97
    iget v3, v1, Lokio66/Segment;->limit:I

    add-int/2addr v3, v2

    iput v3, v1, Lokio66/Segment;->limit:I

    .line 98
    iget-wide v3, v0, Lokio66/Buffer;->size:J

    int-to-long v5, v2

    add-long/2addr v3, v5

    iput-wide v3, v0, Lokio66/Buffer;->size:J

    .line 99
    iget-object v3, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    invoke-interface {v3}, Lokio66/BufferedSink;->emitCompleteSegments()Lokio66/BufferedSink;

    goto :goto_2

    .line 100
    :cond_1
    iget-object v3, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    invoke-virtual {v3}, Ljava/util/zip/Deflater;->needsInput()Z

    move-result v3

    if-eqz v3, :cond_3

    .line 101
    iget v3, v1, Lokio66/Segment;->pos:I

    iget v4, v1, Lokio66/Segment;->limit:I

    if-ne v3, v4, :cond_2

    .line 103
    invoke-virtual {v1}, Lokio66/Segment;->pop()Lokio66/Segment;

    move-result-object v3

    iput-object v3, v0, Lokio66/Buffer;->head:Lokio66/Segment;

    .line 104
    invoke-static {v1}, Lokio66/SegmentPool;->recycle(Lokio66/Segment;)V

    .line 106
    :cond_2
    return-void

    .line 108
    .end local v1    # "s":Lokio66/Segment;
    .end local v2    # "deflated":I
    :cond_3
    :goto_2
    goto :goto_0
.end method


# virtual methods
.method public close()V
    .locals 2
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 122
    iget-boolean v0, p0, Lokio66/DeflaterSink;->closed:Z

    if-eqz v0, :cond_0

    return-void

    .line 126
    :cond_0
    const/4 v0, 0x0

    .line 128
    .local v0, "thrown":Ljava/lang/Throwable;
    :try_start_0
    invoke-virtual {p0}, Lokio66/DeflaterSink;->finishDeflate()V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 131
    goto :goto_0

    .line 129
    :catchall_0
    move-exception v1

    .line 130
    .local v1, "e":Ljava/lang/Throwable;
    move-object v0, v1

    .line 134
    .end local v1    # "e":Ljava/lang/Throwable;
    :goto_0
    :try_start_1
    iget-object v1, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    invoke-virtual {v1}, Ljava/util/zip/Deflater;->end()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 137
    goto :goto_1

    .line 135
    :catchall_1
    move-exception v1

    .line 136
    .restart local v1    # "e":Ljava/lang/Throwable;
    if-nez v0, :cond_1

    move-object v0, v1

    .line 140
    .end local v1    # "e":Ljava/lang/Throwable;
    :cond_1
    :goto_1
    :try_start_2
    iget-object v1, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    invoke-interface {v1}, Lokio66/BufferedSink;->close()V
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_2

    .line 143
    goto :goto_2

    .line 141
    :catchall_2
    move-exception v1

    .line 142
    .restart local v1    # "e":Ljava/lang/Throwable;
    if-nez v0, :cond_2

    move-object v0, v1

    .line 144
    .end local v1    # "e":Ljava/lang/Throwable;
    :cond_2
    :goto_2
    const/4 v1, 0x1

    iput-boolean v1, p0, Lokio66/DeflaterSink;->closed:Z

    .line 146
    if-eqz v0, :cond_3

    invoke-static {v0}, Lokio66/Util;->sneakyRethrow(Ljava/lang/Throwable;)V

    .line 147
    :cond_3
    return-void
.end method

.method finishDeflate()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 117
    iget-object v0, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    invoke-virtual {v0}, Ljava/util/zip/Deflater;->finish()V

    .line 118
    const/4 v0, 0x0

    invoke-direct {p0, v0}, Lokio66/DeflaterSink;->deflate(Z)V

    .line 119
    return-void
.end method

.method public flush()V
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 112
    const/4 v0, 0x1

    invoke-direct {p0, v0}, Lokio66/DeflaterSink;->deflate(Z)V

    .line 113
    iget-object v0, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->flush()V

    .line 114
    return-void
.end method

.method public timeout()Lokio66/Timeout;
    .locals 1

    .line 150
    iget-object v0, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    invoke-interface {v0}, Lokio66/BufferedSink;->timeout()Lokio66/Timeout;

    move-result-object v0

    return-object v0
.end method

.method public toString()Ljava/lang/String;
    .locals 2

    .line 154
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "DeflaterSink("

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    iget-object v1, p0, Lokio66/DeflaterSink;->sink:Lokio66/BufferedSink;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, ")"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public write(Lokio66/Buffer;J)V
    .locals 7
    .param p1, "source"    # Lokio66/Buffer;
    .param p2, "byteCount"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 60
    iget-wide v0, p1, Lokio66/Buffer;->size:J

    const-wide/16 v2, 0x0

    move-wide v4, p2

    invoke-static/range {v0 .. v5}, Lokio66/Util;->checkOffsetAndCount(JJJ)V

    .line 61
    :goto_0
    const-wide/16 v0, 0x0

    cmp-long v2, p2, v0

    if-lez v2, :cond_1

    .line 63
    iget-object v0, p1, Lokio66/Buffer;->head:Lokio66/Segment;

    .line 64
    .local v0, "head":Lokio66/Segment;
    iget v1, v0, Lokio66/Segment;->limit:I

    iget v2, v0, Lokio66/Segment;->pos:I

    sub-int/2addr v1, v2

    int-to-long v1, v1

    invoke-static {p2, p3, v1, v2}, Ljava/lang/Math;->min(JJ)J

    move-result-wide v1

    long-to-int v2, v1

    .line 65
    .local v2, "toDeflate":I
    iget-object v1, p0, Lokio66/DeflaterSink;->deflater:Ljava/util/zip/Deflater;

    iget-object v3, v0, Lokio66/Segment;->data:[B

    iget v4, v0, Lokio66/Segment;->pos:I

    invoke-virtual {v1, v3, v4, v2}, Ljava/util/zip/Deflater;->setInput([BII)V

    .line 68
    const/4 v1, 0x0

    invoke-direct {p0, v1}, Lokio66/DeflaterSink;->deflate(Z)V

    .line 71
    iget-wide v3, p1, Lokio66/Buffer;->size:J

    int-to-long v5, v2

    sub-long/2addr v3, v5

    iput-wide v3, p1, Lokio66/Buffer;->size:J

    .line 72
    iget v1, v0, Lokio66/Segment;->pos:I

    add-int/2addr v1, v2

    iput v1, v0, Lokio66/Segment;->pos:I

    .line 73
    iget v1, v0, Lokio66/Segment;->pos:I

    iget v3, v0, Lokio66/Segment;->limit:I

    if-ne v1, v3, :cond_0

    .line 74
    invoke-virtual {v0}, Lokio66/Segment;->pop()Lokio66/Segment;

    move-result-object v1

    iput-object v1, p1, Lokio66/Buffer;->head:Lokio66/Segment;

    .line 75
    invoke-static {v0}, Lokio66/SegmentPool;->recycle(Lokio66/Segment;)V

    .line 78
    :cond_0
    int-to-long v3, v2

    sub-long/2addr p2, v3

    .line 79
    .end local v0    # "head":Lokio66/Segment;
    .end local v2    # "toDeflate":I
    goto :goto_0

    .line 80
    :cond_1
    return-void
.end method

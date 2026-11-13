.class public final Lokio66/Pipe;
.super Ljava/lang/Object;
.source "Pipe.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lokio66/Pipe$PipeSource;,
        Lokio66/Pipe$PipeSink;
    }
.end annotation


# instance fields
.field final buffer:Lokio66/Buffer;

.field private foldedSink:Lokio66/Sink;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final maxBufferSize:J

.field private final sink:Lokio66/Sink;

.field sinkClosed:Z

.field private final source:Lokio66/Source;

.field sourceClosed:Z


# direct methods
.method public constructor <init>(J)V
    .locals 3
    .param p1, "maxBufferSize"    # J

    .line 46
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 39
    new-instance v0, Lokio66/Buffer;

    invoke-direct {v0}, Lokio66/Buffer;-><init>()V

    iput-object v0, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    .line 42
    new-instance v0, Lokio66/Pipe$PipeSink;

    invoke-direct {v0, p0}, Lokio66/Pipe$PipeSink;-><init>(Lokio66/Pipe;)V

    iput-object v0, p0, Lokio66/Pipe;->sink:Lokio66/Sink;

    .line 43
    new-instance v0, Lokio66/Pipe$PipeSource;

    invoke-direct {v0, p0}, Lokio66/Pipe$PipeSource;-><init>(Lokio66/Pipe;)V

    iput-object v0, p0, Lokio66/Pipe;->source:Lokio66/Source;

    .line 47
    const-wide/16 v0, 0x1

    cmp-long v2, p1, v0

    if-ltz v2, :cond_0

    .line 50
    iput-wide p1, p0, Lokio66/Pipe;->maxBufferSize:J

    .line 51
    return-void

    .line 48
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "maxBufferSize < 1: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1, p2}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method static synthetic access$000(Lokio66/Pipe;)Lokio66/Sink;
    .locals 1
    .param p0, "x0"    # Lokio66/Pipe;

    .line 37
    iget-object v0, p0, Lokio66/Pipe;->foldedSink:Lokio66/Sink;

    return-object v0
.end method


# virtual methods
.method public fold(Lokio66/Sink;)V
    .locals 6
    .param p1, "sink"    # Lokio66/Sink;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 72
    nop

    :goto_0
    iget-object v0, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    monitor-enter v0

    .line 73
    :try_start_0
    iget-object v1, p0, Lokio66/Pipe;->foldedSink:Lokio66/Sink;

    if-nez v1, :cond_3

    .line 75
    iget-object v1, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    invoke-virtual {v1}, Lokio66/Buffer;->exhausted()Z

    move-result v1

    const/4 v2, 0x1

    if-eqz v1, :cond_0

    .line 76
    iput-boolean v2, p0, Lokio66/Pipe;->sourceClosed:Z

    .line 77
    iput-object p1, p0, Lokio66/Pipe;->foldedSink:Lokio66/Sink;

    .line 78
    monitor-exit v0

    return-void

    .line 81
    :cond_0
    new-instance v1, Lokio66/Buffer;

    invoke-direct {v1}, Lokio66/Buffer;-><init>()V

    .line 82
    .local v1, "sinkBuffer":Lokio66/Buffer;
    iget-object v3, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    iget-object v4, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    iget-wide v4, v4, Lokio66/Buffer;->size:J

    invoke-virtual {v1, v3, v4, v5}, Lokio66/Buffer;->write(Lokio66/Buffer;J)V

    .line 83
    iget-object v3, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    invoke-virtual {v3}, Ljava/lang/Object;->notifyAll()V

    .line 84
    monitor-exit v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_3

    .line 86
    const/4 v0, 0x0

    .line 88
    .local v0, "success":Z
    :try_start_1
    iget-wide v3, v1, Lokio66/Buffer;->size:J

    invoke-interface {p1, v1, v3, v4}, Lokio66/Sink;->write(Lokio66/Buffer;J)V

    .line 89
    invoke-interface {p1}, Lokio66/Sink;->flush()V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_1

    .line 90
    const/4 v0, 0x1

    .line 92
    if-nez v0, :cond_1

    .line 93
    iget-object v3, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    monitor-enter v3

    .line 94
    :try_start_2
    iput-boolean v2, p0, Lokio66/Pipe;->sourceClosed:Z

    .line 95
    iget-object v2, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    invoke-virtual {v2}, Ljava/lang/Object;->notifyAll()V

    .line 96
    monitor-exit v3

    goto :goto_1

    :catchall_0
    move-exception v2

    monitor-exit v3
    :try_end_2
    .catchall {:try_start_2 .. :try_end_2} :catchall_0

    throw v2

    .line 99
    .end local v0    # "success":Z
    .end local v1    # "sinkBuffer":Lokio66/Buffer;
    :cond_1
    :goto_1
    goto :goto_0

    .line 92
    .restart local v0    # "success":Z
    .restart local v1    # "sinkBuffer":Lokio66/Buffer;
    :catchall_1
    move-exception v3

    if-nez v0, :cond_2

    .line 93
    iget-object v4, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    monitor-enter v4

    .line 94
    :try_start_3
    iput-boolean v2, p0, Lokio66/Pipe;->sourceClosed:Z

    .line 95
    iget-object v2, p0, Lokio66/Pipe;->buffer:Lokio66/Buffer;

    invoke-virtual {v2}, Ljava/lang/Object;->notifyAll()V

    .line 96
    monitor-exit v4

    goto :goto_2

    :catchall_2
    move-exception v2

    monitor-exit v4
    :try_end_3
    .catchall {:try_start_3 .. :try_end_3} :catchall_2

    throw v2

    :cond_2
    :goto_2
    throw v3

    .line 73
    .end local v0    # "success":Z
    .end local v1    # "sinkBuffer":Lokio66/Buffer;
    :cond_3
    :try_start_4
    new-instance v1, Ljava/lang/IllegalStateException;

    const-string v2, "sink already folded"

    invoke-direct {v1, v2}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    .end local p1    # "sink":Lokio66/Sink;
    throw v1

    .line 84
    .restart local p1    # "sink":Lokio66/Sink;
    :catchall_3
    move-exception v1

    monitor-exit v0
    :try_end_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_3

    throw v1
.end method

.method public final sink()Lokio66/Sink;
    .locals 1

    .line 58
    iget-object v0, p0, Lokio66/Pipe;->sink:Lokio66/Sink;

    return-object v0
.end method

.method public final source()Lokio66/Source;
    .locals 1

    .line 54
    iget-object v0, p0, Lokio66/Pipe;->source:Lokio66/Source;

    return-object v0
.end method

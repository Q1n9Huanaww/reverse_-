.class Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody$1;
.super Lokio66/ForwardingSource;
.source "OkHttpCall.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;-><init>(Lokhttp366/ResponseBody;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;


# direct methods
.method constructor <init>(Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;Lokio66/Source;)V
    .locals 0
    .param p1, "this$0"    # Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;
    .param p2, "arg0"    # Lokio66/Source;

    .line 313
    iput-object p1, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody$1;->this$0:Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;

    invoke-direct {p0, p2}, Lokio66/ForwardingSource;-><init>(Lokio66/Source;)V

    return-void
.end method


# virtual methods
.method public read(Lokio66/Buffer;J)J
    .locals 2
    .param p1, "sink"    # Lokio66/Buffer;
    .param p2, "byteCount"    # J
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 317
    :try_start_0
    invoke-super {p0, p1, p2, p3}, Lokio66/ForwardingSource;->read(Lokio66/Buffer;J)J

    move-result-wide v0
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    return-wide v0

    .line 318
    :catch_0
    move-exception v0

    .line 319
    .local v0, "e":Ljava/io/IOException;
    iget-object v1, p0, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody$1;->this$0:Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;

    iput-object v0, v1, Lretrofit266/OkHttpCall$ExceptionCatchingResponseBody;->thrownException:Ljava/io/IOException;

    .line 320
    throw v0
.end method

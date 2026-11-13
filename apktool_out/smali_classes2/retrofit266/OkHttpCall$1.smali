.class Lretrofit266/OkHttpCall$1;
.super Ljava/lang/Object;
.source "OkHttpCall.java"

# interfaces
.implements Lokhttp366/Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lretrofit266/OkHttpCall;->enqueue(Lretrofit266/Callback;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic this$0:Lretrofit266/OkHttpCall;

.field final synthetic val$callback:Lretrofit266/Callback;


# direct methods
.method constructor <init>(Lretrofit266/OkHttpCall;Lretrofit266/Callback;)V
    .locals 0
    .param p1, "this$0"    # Lretrofit266/OkHttpCall;

    .line 151
    .local p0, "this":Lretrofit266/OkHttpCall$1;, "Lretrofit266/OkHttpCall$1;"
    iput-object p1, p0, Lretrofit266/OkHttpCall$1;->this$0:Lretrofit266/OkHttpCall;

    iput-object p2, p0, Lretrofit266/OkHttpCall$1;->val$callback:Lretrofit266/Callback;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method private callFailure(Ljava/lang/Throwable;)V
    .locals 2
    .param p1, "e"    # Ljava/lang/Throwable;

    .line 178
    .local p0, "this":Lretrofit266/OkHttpCall$1;, "Lretrofit266/OkHttpCall$1;"
    :try_start_0
    iget-object v0, p0, Lretrofit266/OkHttpCall$1;->val$callback:Lretrofit266/Callback;

    iget-object v1, p0, Lretrofit266/OkHttpCall$1;->this$0:Lretrofit266/OkHttpCall;

    invoke-interface {v0, v1, p1}, Lretrofit266/Callback;->onFailure(Lretrofit266/Call;Ljava/lang/Throwable;)V
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 182
    goto :goto_0

    .line 179
    :catchall_0
    move-exception v0

    .line 180
    .local v0, "t":Ljava/lang/Throwable;
    invoke-static {v0}, Lretrofit266/Utils;->throwIfFatal(Ljava/lang/Throwable;)V

    .line 181
    invoke-virtual {v0}, Ljava/lang/Throwable;->printStackTrace()V

    .line 183
    .end local v0    # "t":Ljava/lang/Throwable;
    :goto_0
    return-void
.end method


# virtual methods
.method public onFailure(Lokhttp366/Call;Ljava/io/IOException;)V
    .locals 0
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "e"    # Ljava/io/IOException;

    .line 173
    .local p0, "this":Lretrofit266/OkHttpCall$1;, "Lretrofit266/OkHttpCall$1;"
    invoke-direct {p0, p2}, Lretrofit266/OkHttpCall$1;->callFailure(Ljava/lang/Throwable;)V

    .line 174
    return-void
.end method

.method public onResponse(Lokhttp366/Call;Lokhttp366/Response;)V
    .locals 3
    .param p1, "call"    # Lokhttp366/Call;
    .param p2, "rawResponse"    # Lokhttp366/Response;

    .line 156
    .local p0, "this":Lretrofit266/OkHttpCall$1;, "Lretrofit266/OkHttpCall$1;"
    :try_start_0
    iget-object v0, p0, Lretrofit266/OkHttpCall$1;->this$0:Lretrofit266/OkHttpCall;

    invoke-virtual {v0, p2}, Lretrofit266/OkHttpCall;->parseResponse(Lokhttp366/Response;)Lretrofit266/Response;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    .line 161
    .local v0, "response":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    nop

    .line 164
    :try_start_1
    iget-object v1, p0, Lretrofit266/OkHttpCall$1;->val$callback:Lretrofit266/Callback;

    iget-object v2, p0, Lretrofit266/OkHttpCall$1;->this$0:Lretrofit266/OkHttpCall;

    invoke-interface {v1, v2, v0}, Lretrofit266/Callback;->onResponse(Lretrofit266/Call;Lretrofit266/Response;)V
    :try_end_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 168
    goto :goto_0

    .line 165
    :catchall_0
    move-exception v1

    .line 166
    .local v1, "t":Ljava/lang/Throwable;
    invoke-static {v1}, Lretrofit266/Utils;->throwIfFatal(Ljava/lang/Throwable;)V

    .line 167
    invoke-virtual {v1}, Ljava/lang/Throwable;->printStackTrace()V

    .line 169
    .end local v1    # "t":Ljava/lang/Throwable;
    :goto_0
    return-void

    .line 157
    .end local v0    # "response":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    :catchall_1
    move-exception v0

    .line 158
    .local v0, "e":Ljava/lang/Throwable;
    invoke-static {v0}, Lretrofit266/Utils;->throwIfFatal(Ljava/lang/Throwable;)V

    .line 159
    invoke-direct {p0, v0}, Lretrofit266/OkHttpCall$1;->callFailure(Ljava/lang/Throwable;)V

    .line 160
    return-void
.end method

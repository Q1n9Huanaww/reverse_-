.class final Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;
.super Ljava/lang/Object;
.source "DefaultCallAdapterFactory.java"

# interfaces
.implements Lretrofit266/Call;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/DefaultCallAdapterFactory;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "ExecutorCallbackCall"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lretrofit266/Call<",
        "TT;>;"
    }
.end annotation


# instance fields
.field final callbackExecutor:Ljava/util/concurrent/Executor;

.field final delegate:Lretrofit266/Call;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit266/Call<",
            "TT;>;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Ljava/util/concurrent/Executor;Lretrofit266/Call;)V
    .locals 0
    .param p1, "callbackExecutor"    # Ljava/util/concurrent/Executor;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/concurrent/Executor;",
            "Lretrofit266/Call<",
            "TT;>;)V"
        }
    .end annotation

    .line 69
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    .local p2, "delegate":Lretrofit266/Call;, "Lretrofit266/Call<TT;>;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 70
    iput-object p1, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->callbackExecutor:Ljava/util/concurrent/Executor;

    .line 71
    iput-object p2, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    .line 72
    return-void
.end method


# virtual methods
.method public cancel()V
    .locals 1

    .line 113
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->cancel()V

    .line 114
    return-void
.end method

.method public bridge synthetic clone()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/lang/CloneNotSupportedException;
        }
    .end annotation

    .line 65
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    invoke-virtual {p0}, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->clone()Lretrofit266/Call;

    move-result-object v0

    return-object v0
.end method

.method public clone()Lretrofit266/Call;
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lretrofit266/Call<",
            "TT;>;"
        }
    .end annotation

    .line 124
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    new-instance v0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    iget-object v1, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->callbackExecutor:Ljava/util/concurrent/Executor;

    iget-object v2, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v2}, Lretrofit266/Call;->clone()Lretrofit266/Call;

    move-result-object v2

    invoke-direct {v0, v1, v2}, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;-><init>(Ljava/util/concurrent/Executor;Lretrofit266/Call;)V

    return-object v0
.end method

.method public enqueue(Lretrofit266/Callback;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Callback<",
            "TT;>;)V"
        }
    .end annotation

    .line 76
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    .local p1, "callback":Lretrofit266/Callback;, "Lretrofit266/Callback<TT;>;"
    const-string v0, "callback == null"

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 78
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    new-instance v1, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;

    invoke-direct {v1, p0, p1}, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;-><init>(Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;Lretrofit266/Callback;)V

    invoke-interface {v0, v1}, Lretrofit266/Call;->enqueue(Lretrofit266/Callback;)V

    .line 99
    return-void
.end method

.method public execute()Lretrofit266/Response;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 108
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->execute()Lretrofit266/Response;

    move-result-object v0

    return-object v0
.end method

.method public isCanceled()Z
    .locals 1

    .line 118
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->isCanceled()Z

    move-result v0

    return v0
.end method

.method public isExecuted()Z
    .locals 1

    .line 103
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->isExecuted()Z

    move-result v0

    return v0
.end method

.method public request()Lokhttp366/Request;
    .locals 1

    .line 129
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->request()Lokhttp366/Request;

    move-result-object v0

    return-object v0
.end method

.method public timeout()Lokio66/Timeout;
    .locals 1

    .line 134
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->timeout()Lokio66/Timeout;

    move-result-object v0

    return-object v0
.end method

.class Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;
.super Ljava/lang/Object;
.source "DefaultCallAdapterFactory.java"

# interfaces
.implements Lretrofit266/Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->enqueue(Lretrofit266/Callback;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lretrofit266/Callback<",
        "TT;>;"
    }
.end annotation


# instance fields
.field final synthetic this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

.field final synthetic val$callback:Lretrofit266/Callback;


# direct methods
.method constructor <init>(Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;Lretrofit266/Callback;)V
    .locals 0
    .param p1, "this$0"    # Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    .line 79
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;"
    iput-object p1, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    iput-object p2, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->val$callback:Lretrofit266/Callback;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method synthetic lambda$onFailure$1$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1(Lretrofit266/Callback;Ljava/lang/Throwable;)V
    .locals 1
    .param p1, "callback"    # Lretrofit266/Callback;
    .param p2, "t"    # Ljava/lang/Throwable;

    .line 96
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    invoke-interface {p1, v0, p2}, Lretrofit266/Callback;->onFailure(Lretrofit266/Call;Ljava/lang/Throwable;)V

    return-void
.end method

.method synthetic lambda$onResponse$0$retrofit2-DefaultCallAdapterFactory$ExecutorCallbackCall$1(Lretrofit266/Callback;Lretrofit266/Response;)V
    .locals 3
    .param p1, "callback"    # Lretrofit266/Callback;
    .param p2, "response"    # Lretrofit266/Response;

    .line 84
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    iget-object v0, v0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->delegate:Lretrofit266/Call;

    invoke-interface {v0}, Lretrofit266/Call;->isCanceled()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 87
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    new-instance v1, Ljava/io/IOException;

    const-string v2, "Canceled"

    invoke-direct {v1, v2}, Ljava/io/IOException;-><init>(Ljava/lang/String;)V

    invoke-interface {p1, v0, v1}, Lretrofit266/Callback;->onFailure(Lretrofit266/Call;Ljava/lang/Throwable;)V

    goto :goto_0

    .line 89
    :cond_0
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    invoke-interface {p1, v0, p2}, Lretrofit266/Callback;->onResponse(Lretrofit266/Call;Lretrofit266/Response;)V

    .line 91
    :goto_0
    return-void
.end method

.method public onFailure(Lretrofit266/Call;Ljava/lang/Throwable;)V
    .locals 3
    .param p2, "t"    # Ljava/lang/Throwable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TT;>;",
            "Ljava/lang/Throwable;",
            ")V"
        }
    .end annotation

    .line 96
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    iget-object v0, v0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->callbackExecutor:Ljava/util/concurrent/Executor;

    iget-object v1, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->val$callback:Lretrofit266/Callback;

    new-instance v2, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda1;

    invoke-direct {v2, p0, v1, p2}, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda1;-><init>(Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;Lretrofit266/Callback;Ljava/lang/Throwable;)V

    invoke-interface {v0, v2}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 97
    return-void
.end method

.method public onResponse(Lretrofit266/Call;Lretrofit266/Response;)V
    .locals 3
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TT;>;",
            "Lretrofit266/Response<",
            "TT;>;)V"
        }
    .end annotation

    .line 82
    .local p0, "this":Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;, "Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TT;>;"
    .local p2, "response":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->this$0:Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;

    iget-object v0, v0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall;->callbackExecutor:Ljava/util/concurrent/Executor;

    iget-object v1, p0, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;->val$callback:Lretrofit266/Callback;

    new-instance v2, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda0;

    invoke-direct {v2, p0, v1, p2}, Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1$$ExternalSyntheticLambda0;-><init>(Lretrofit266/DefaultCallAdapterFactory$ExecutorCallbackCall$1;Lretrofit266/Callback;Lretrofit266/Response;)V

    invoke-interface {v0, v2}, Ljava/util/concurrent/Executor;->execute(Ljava/lang/Runnable;)V

    .line 92
    return-void
.end method

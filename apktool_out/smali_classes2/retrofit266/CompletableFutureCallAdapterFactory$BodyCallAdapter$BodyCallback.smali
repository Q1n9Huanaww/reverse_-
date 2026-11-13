.class Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;
.super Ljava/lang/Object;
.source "CompletableFutureCallAdapterFactory.java"

# interfaces
.implements Lretrofit266/Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x2
    name = "BodyCallback"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lretrofit266/Callback<",
        "TR;>;"
    }
.end annotation


# instance fields
.field private final future:Ljava/util/concurrent/CompletableFuture;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/concurrent/CompletableFuture<",
            "TR;>;"
        }
    .end annotation
.end field

.field final synthetic this$0:Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;


# direct methods
.method public constructor <init>(Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;Ljava/util/concurrent/CompletableFuture;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/concurrent/CompletableFuture<",
            "TR;>;)V"
        }
    .end annotation

    .line 80
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>.BodyCallback;"
    .local p2, "future":Ljava/util/concurrent/CompletableFuture;, "Ljava/util/concurrent/CompletableFuture<TR;>;"
    iput-object p1, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;->this$0:Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 81
    iput-object p2, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;->future:Ljava/util/concurrent/CompletableFuture;

    .line 82
    return-void
.end method


# virtual methods
.method public onFailure(Lretrofit266/Call;Ljava/lang/Throwable;)V
    .locals 1
    .param p2, "t"    # Ljava/lang/Throwable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TR;>;",
            "Ljava/lang/Throwable;",
            ")V"
        }
    .end annotation

    .line 95
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>.BodyCallback;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TR;>;"
    iget-object v0, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;->future:Ljava/util/concurrent/CompletableFuture;

    invoke-virtual {v0, p2}, Ljava/util/concurrent/CompletableFuture;->completeExceptionally(Ljava/lang/Throwable;)Z

    .line 96
    return-void
.end method

.method public onResponse(Lretrofit266/Call;Lretrofit266/Response;)V
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TR;>;",
            "Lretrofit266/Response<",
            "TR;>;)V"
        }
    .end annotation

    .line 86
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>.BodyCallback;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TR;>;"
    .local p2, "response":Lretrofit266/Response;, "Lretrofit266/Response<TR;>;"
    invoke-virtual {p2}, Lretrofit266/Response;->isSuccessful()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 87
    iget-object v0, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;->future:Ljava/util/concurrent/CompletableFuture;

    invoke-virtual {p2}, Lretrofit266/Response;->body()Ljava/lang/Object;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/util/concurrent/CompletableFuture;->complete(Ljava/lang/Object;)Z

    goto :goto_0

    .line 89
    :cond_0
    iget-object v0, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;->future:Ljava/util/concurrent/CompletableFuture;

    new-instance v1, Lretrofit266/HttpException;

    invoke-direct {v1, p2}, Lretrofit266/HttpException;-><init>(Lretrofit266/Response;)V

    invoke-virtual {v0, v1}, Ljava/util/concurrent/CompletableFuture;->completeExceptionally(Ljava/lang/Throwable;)Z

    .line 91
    :goto_0
    return-void
.end method

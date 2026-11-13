.class final Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;
.super Ljava/lang/Object;
.source "CompletableFutureCallAdapterFactory.java"

# interfaces
.implements Lretrofit266/CallAdapter;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/CompletableFutureCallAdapterFactory;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x1a
    name = "ResponseCallAdapter"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter$ResponseCallback;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<R:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lretrofit266/CallAdapter<",
        "TR;",
        "Ljava/util/concurrent/CompletableFuture<",
        "Lretrofit266/Response<",
        "TR;>;>;>;"
    }
.end annotation


# instance fields
.field private final responseType:Ljava/lang/reflect/Type;


# direct methods
.method constructor <init>(Ljava/lang/reflect/Type;)V
    .locals 0
    .param p1, "responseType"    # Ljava/lang/reflect/Type;

    .line 105
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter<TR;>;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 106
    iput-object p1, p0, Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;->responseType:Ljava/lang/reflect/Type;

    .line 107
    return-void
.end method


# virtual methods
.method public bridge synthetic adapt(Lretrofit266/Call;)Ljava/lang/Object;
    .locals 0

    .line 100
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter<TR;>;"
    invoke-virtual {p0, p1}, Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;->adapt(Lretrofit266/Call;)Ljava/util/concurrent/CompletableFuture;

    move-result-object p1

    return-object p1
.end method

.method public adapt(Lretrofit266/Call;)Ljava/util/concurrent/CompletableFuture;
    .locals 2
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TR;>;)",
            "Ljava/util/concurrent/CompletableFuture<",
            "Lretrofit266/Response<",
            "TR;>;>;"
        }
    .end annotation

    .line 116
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter<TR;>;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TR;>;"
    new-instance v0, Lretrofit266/CompletableFutureCallAdapterFactory$CallCancelCompletableFuture;

    invoke-direct {v0, p1}, Lretrofit266/CompletableFutureCallAdapterFactory$CallCancelCompletableFuture;-><init>(Lretrofit266/Call;)V

    .line 117
    .local v0, "future":Ljava/util/concurrent/CompletableFuture;, "Ljava/util/concurrent/CompletableFuture<Lretrofit266/Response<TR;>;>;"
    new-instance v1, Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter$ResponseCallback;

    invoke-direct {v1, p0, v0}, Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter$ResponseCallback;-><init>(Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;Ljava/util/concurrent/CompletableFuture;)V

    invoke-interface {p1, v1}, Lretrofit266/Call;->enqueue(Lretrofit266/Callback;)V

    .line 118
    return-object v0
.end method

.method public responseType()Ljava/lang/reflect/Type;
    .locals 1

    .line 111
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter<TR;>;"
    iget-object v0, p0, Lretrofit266/CompletableFutureCallAdapterFactory$ResponseCallAdapter;->responseType:Ljava/lang/reflect/Type;

    return-object v0
.end method

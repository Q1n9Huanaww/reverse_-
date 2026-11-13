.class final Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;
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
    name = "BodyCallAdapter"
.end annotation

.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;
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
        "TR;>;>;"
    }
.end annotation


# instance fields
.field private final responseType:Ljava/lang/reflect/Type;


# direct methods
.method constructor <init>(Ljava/lang/reflect/Type;)V
    .locals 0
    .param p1, "responseType"    # Ljava/lang/reflect/Type;

    .line 60
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 61
    iput-object p1, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;->responseType:Ljava/lang/reflect/Type;

    .line 62
    return-void
.end method


# virtual methods
.method public bridge synthetic adapt(Lretrofit266/Call;)Ljava/lang/Object;
    .locals 0

    .line 56
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>;"
    invoke-virtual {p0, p1}, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;->adapt(Lretrofit266/Call;)Ljava/util/concurrent/CompletableFuture;

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
            "TR;>;"
        }
    .end annotation

    .line 71
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TR;>;"
    new-instance v0, Lretrofit266/CompletableFutureCallAdapterFactory$CallCancelCompletableFuture;

    invoke-direct {v0, p1}, Lretrofit266/CompletableFutureCallAdapterFactory$CallCancelCompletableFuture;-><init>(Lretrofit266/Call;)V

    .line 72
    .local v0, "future":Ljava/util/concurrent/CompletableFuture;, "Ljava/util/concurrent/CompletableFuture<TR;>;"
    new-instance v1, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;

    invoke-direct {v1, p0, v0}, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter$BodyCallback;-><init>(Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;Ljava/util/concurrent/CompletableFuture;)V

    invoke-interface {p1, v1}, Lretrofit266/Call;->enqueue(Lretrofit266/Callback;)V

    .line 73
    return-object v0
.end method

.method public responseType()Ljava/lang/reflect/Type;
    .locals 1

    .line 66
    .local p0, "this":Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;, "Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter<TR;>;"
    iget-object v0, p0, Lretrofit266/CompletableFutureCallAdapterFactory$BodyCallAdapter;->responseType:Ljava/lang/reflect/Type;

    return-object v0
.end method

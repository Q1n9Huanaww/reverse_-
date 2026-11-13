.class final Lretrofit266/HttpServiceMethod$SuspendForBody;
.super Lretrofit266/HttpServiceMethod;
.source "HttpServiceMethod.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/HttpServiceMethod;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "SuspendForBody"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<ResponseT:",
        "Ljava/lang/Object;",
        ">",
        "Lretrofit266/HttpServiceMethod<",
        "TResponseT;",
        "Ljava/lang/Object;",
        ">;"
    }
.end annotation


# instance fields
.field private final callAdapter:Lretrofit266/CallAdapter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit266/CallAdapter<",
            "TResponseT;",
            "Lretrofit266/Call<",
            "TResponseT;>;>;"
        }
    .end annotation
.end field

.field private final isNullable:Z

.field private final isUnit:Z


# direct methods
.method constructor <init>(Lretrofit266/RequestFactory;Lokhttp366/Call$Factory;Lretrofit266/Converter;Lretrofit266/CallAdapter;ZZ)V
    .locals 0
    .param p1, "requestFactory"    # Lretrofit266/RequestFactory;
    .param p2, "callFactory"    # Lokhttp366/Call$Factory;
    .param p5, "isNullable"    # Z
    .param p6, "isUnit"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/RequestFactory;",
            "Lokhttp366/Call$Factory;",
            "Lretrofit266/Converter<",
            "Lokhttp366/ResponseBody;",
            "TResponseT;>;",
            "Lretrofit266/CallAdapter<",
            "TResponseT;",
            "Lretrofit266/Call<",
            "TResponseT;>;>;ZZ)V"
        }
    .end annotation

    .line 224
    .local p0, "this":Lretrofit266/HttpServiceMethod$SuspendForBody;, "Lretrofit266/HttpServiceMethod$SuspendForBody<TResponseT;>;"
    .local p3, "responseConverter":Lretrofit266/Converter;, "Lretrofit266/Converter<Lokhttp366/ResponseBody;TResponseT;>;"
    .local p4, "callAdapter":Lretrofit266/CallAdapter;, "Lretrofit266/CallAdapter<TResponseT;Lretrofit266/Call<TResponseT;>;>;"
    invoke-direct {p0, p1, p2, p3}, Lretrofit266/HttpServiceMethod;-><init>(Lretrofit266/RequestFactory;Lokhttp366/Call$Factory;Lretrofit266/Converter;)V

    .line 225
    iput-object p4, p0, Lretrofit266/HttpServiceMethod$SuspendForBody;->callAdapter:Lretrofit266/CallAdapter;

    .line 226
    iput-boolean p5, p0, Lretrofit266/HttpServiceMethod$SuspendForBody;->isNullable:Z

    .line 227
    iput-boolean p6, p0, Lretrofit266/HttpServiceMethod$SuspendForBody;->isUnit:Z

    .line 228
    return-void
.end method


# virtual methods
.method protected adapt(Lretrofit266/Call;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 3
    .param p2, "args"    # [Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TResponseT;>;[",
            "Ljava/lang/Object;",
            ")",
            "Ljava/lang/Object;"
        }
    .end annotation

    .line 232
    .local p0, "this":Lretrofit266/HttpServiceMethod$SuspendForBody;, "Lretrofit266/HttpServiceMethod$SuspendForBody<TResponseT;>;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TResponseT;>;"
    iget-object v0, p0, Lretrofit266/HttpServiceMethod$SuspendForBody;->callAdapter:Lretrofit266/CallAdapter;

    invoke-interface {v0, p1}, Lretrofit266/CallAdapter;->adapt(Lretrofit266/Call;)Ljava/lang/Object;

    move-result-object v0

    move-object p1, v0

    check-cast p1, Lretrofit266/Call;

    .line 235
    array-length v0, p2

    add-int/lit8 v0, v0, -0x1

    aget-object v0, p2, v0

    check-cast v0, Lkotlin/coroutines/Continuation;

    .line 246
    .local v0, "continuation":Lkotlin/coroutines/Continuation;, "Lkotlin/coroutines/Continuation<TResponseT;>;"
    :try_start_0
    iget-boolean v1, p0, Lretrofit266/HttpServiceMethod$SuspendForBody;->isUnit:Z

    if-eqz v1, :cond_0

    .line 248
    invoke-static {p1, v0}, Lretrofit266/KotlinExtensions;->awaitUnit(Lretrofit266/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    return-object v1

    .line 249
    :cond_0
    iget-boolean v1, p0, Lretrofit266/HttpServiceMethod$SuspendForBody;->isNullable:Z

    if-eqz v1, :cond_1

    .line 250
    invoke-static {p1, v0}, Lretrofit266/KotlinExtensions;->awaitNullable(Lretrofit266/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1

    return-object v1

    .line 252
    :cond_1
    invoke-static {p1, v0}, Lretrofit266/KotlinExtensions;->await(Lretrofit266/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/VirtualMachineError; {:try_start_0 .. :try_end_0} :catch_2
    .catch Ljava/lang/ThreadDeath; {:try_start_0 .. :try_end_0} :catch_1
    .catch Ljava/lang/LinkageError; {:try_start_0 .. :try_end_0} :catch_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    return-object v1

    .line 257
    :catchall_0
    move-exception v1

    .line 258
    .local v1, "e":Ljava/lang/Throwable;
    invoke-static {v1, v0}, Lretrofit266/KotlinExtensions;->suspendAndThrow(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    return-object v2

    .line 254
    .end local v1    # "e":Ljava/lang/Throwable;
    :catch_0
    move-exception v1

    goto :goto_0

    :catch_1
    move-exception v1

    goto :goto_0

    :catch_2
    move-exception v1

    .line 256
    .local v1, "e":Ljava/lang/Error;
    :goto_0
    throw v1
.end method

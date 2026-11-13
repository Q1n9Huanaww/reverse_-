.class final Lretrofit266/HttpServiceMethod$SuspendForResponse;
.super Lretrofit266/HttpServiceMethod;
.source "HttpServiceMethod.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/HttpServiceMethod;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "SuspendForResponse"
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


# direct methods
.method constructor <init>(Lretrofit266/RequestFactory;Lokhttp366/Call$Factory;Lretrofit266/Converter;Lretrofit266/CallAdapter;)V
    .locals 0
    .param p1, "requestFactory"    # Lretrofit266/RequestFactory;
    .param p2, "callFactory"    # Lokhttp366/Call$Factory;
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
            "TResponseT;>;>;)V"
        }
    .end annotation

    .line 191
    .local p0, "this":Lretrofit266/HttpServiceMethod$SuspendForResponse;, "Lretrofit266/HttpServiceMethod$SuspendForResponse<TResponseT;>;"
    .local p3, "responseConverter":Lretrofit266/Converter;, "Lretrofit266/Converter<Lokhttp366/ResponseBody;TResponseT;>;"
    .local p4, "callAdapter":Lretrofit266/CallAdapter;, "Lretrofit266/CallAdapter<TResponseT;Lretrofit266/Call<TResponseT;>;>;"
    invoke-direct {p0, p1, p2, p3}, Lretrofit266/HttpServiceMethod;-><init>(Lretrofit266/RequestFactory;Lokhttp366/Call$Factory;Lretrofit266/Converter;)V

    .line 192
    iput-object p4, p0, Lretrofit266/HttpServiceMethod$SuspendForResponse;->callAdapter:Lretrofit266/CallAdapter;

    .line 193
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

    .line 197
    .local p0, "this":Lretrofit266/HttpServiceMethod$SuspendForResponse;, "Lretrofit266/HttpServiceMethod$SuspendForResponse<TResponseT;>;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TResponseT;>;"
    iget-object v0, p0, Lretrofit266/HttpServiceMethod$SuspendForResponse;->callAdapter:Lretrofit266/CallAdapter;

    invoke-interface {v0, p1}, Lretrofit266/CallAdapter;->adapt(Lretrofit266/Call;)Ljava/lang/Object;

    move-result-object v0

    move-object p1, v0

    check-cast p1, Lretrofit266/Call;

    .line 200
    array-length v0, p2

    add-int/lit8 v0, v0, -0x1

    aget-object v0, p2, v0

    check-cast v0, Lkotlin/coroutines/Continuation;

    .line 205
    .local v0, "continuation":Lkotlin/coroutines/Continuation;, "Lkotlin/coroutines/Continuation<Lretrofit266/Response<TResponseT;>;>;"
    :try_start_0
    invoke-static {p1, v0}, Lretrofit266/KotlinExtensions;->awaitResponse(Lretrofit266/Call;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v1
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    return-object v1

    .line 206
    :catch_0
    move-exception v1

    .line 207
    .local v1, "e":Ljava/lang/Exception;
    invoke-static {v1, v0}, Lretrofit266/KotlinExtensions;->suspendAndThrow(Ljava/lang/Throwable;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;

    move-result-object v2

    return-object v2
.end method

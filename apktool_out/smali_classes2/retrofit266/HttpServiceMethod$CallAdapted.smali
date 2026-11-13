.class final Lretrofit266/HttpServiceMethod$CallAdapted;
.super Lretrofit266/HttpServiceMethod;
.source "HttpServiceMethod.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/HttpServiceMethod;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "CallAdapted"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<ResponseT:",
        "Ljava/lang/Object;",
        "ReturnT:",
        "Ljava/lang/Object;",
        ">",
        "Lretrofit266/HttpServiceMethod<",
        "TResponseT;TReturnT;>;"
    }
.end annotation


# instance fields
.field private final callAdapter:Lretrofit266/CallAdapter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit266/CallAdapter<",
            "TResponseT;TReturnT;>;"
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
            "TResponseT;TReturnT;>;)V"
        }
    .end annotation

    .line 173
    .local p0, "this":Lretrofit266/HttpServiceMethod$CallAdapted;, "Lretrofit266/HttpServiceMethod$CallAdapted<TResponseT;TReturnT;>;"
    .local p3, "responseConverter":Lretrofit266/Converter;, "Lretrofit266/Converter<Lokhttp366/ResponseBody;TResponseT;>;"
    .local p4, "callAdapter":Lretrofit266/CallAdapter;, "Lretrofit266/CallAdapter<TResponseT;TReturnT;>;"
    invoke-direct {p0, p1, p2, p3}, Lretrofit266/HttpServiceMethod;-><init>(Lretrofit266/RequestFactory;Lokhttp366/Call$Factory;Lretrofit266/Converter;)V

    .line 174
    iput-object p4, p0, Lretrofit266/HttpServiceMethod$CallAdapted;->callAdapter:Lretrofit266/CallAdapter;

    .line 175
    return-void
.end method


# virtual methods
.method protected adapt(Lretrofit266/Call;[Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1
    .param p2, "args"    # [Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "TResponseT;>;[",
            "Ljava/lang/Object;",
            ")TReturnT;"
        }
    .end annotation

    .line 179
    .local p0, "this":Lretrofit266/HttpServiceMethod$CallAdapted;, "Lretrofit266/HttpServiceMethod$CallAdapted<TResponseT;TReturnT;>;"
    .local p1, "call":Lretrofit266/Call;, "Lretrofit266/Call<TResponseT;>;"
    iget-object v0, p0, Lretrofit266/HttpServiceMethod$CallAdapted;->callAdapter:Lretrofit266/CallAdapter;

    invoke-interface {v0, p1}, Lretrofit266/CallAdapter;->adapt(Lretrofit266/Call;)Ljava/lang/Object;

    move-result-object v0

    return-object v0
.end method

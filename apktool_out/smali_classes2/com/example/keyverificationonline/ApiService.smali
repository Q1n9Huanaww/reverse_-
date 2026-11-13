.class public interface abstract Lcom/example/keyverificationonline/ApiService;
.super Ljava/lang/Object;
.source "ApiService.java"


# virtual methods
.method public abstract checkExpire(Ljava/lang/String;)Lretrofit266/Call;
    .param p1    # Ljava/lang/String;
        .annotation runtime Lretrofit266/http/Url;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Lretrofit266/Call<",
            "Lokhttp366/ResponseBody;",
            ">;"
        }
    .end annotation

    .annotation runtime Lretrofit266/http/GET;
    .end annotation
.end method

.method public abstract getInfo(Ljava/lang/String;)Lretrofit266/Call;
    .param p1    # Ljava/lang/String;
        .annotation runtime Lretrofit266/http/Url;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Lretrofit266/Call<",
            "Lokhttp366/ResponseBody;",
            ">;"
        }
    .end annotation

    .annotation runtime Lretrofit266/http/GET;
    .end annotation
.end method

.method public abstract userLogin(Ljava/lang/String;)Lretrofit266/Call;
    .param p1    # Ljava/lang/String;
        .annotation runtime Lretrofit266/http/Url;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            ")",
            "Lretrofit266/Call<",
            "Lokhttp366/ResponseBody;",
            ">;"
        }
    .end annotation

    .annotation runtime Lretrofit266/http/GET;
    .end annotation
.end method

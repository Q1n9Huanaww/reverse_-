.class public interface abstract Lokhttp366/Callback;
.super Ljava/lang/Object;
.source "Callback.java"


# virtual methods
.method public abstract onFailure(Lokhttp366/Call;Ljava/io/IOException;)V
.end method

.method public abstract onResponse(Lokhttp366/Call;Lokhttp366/Response;)V
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation
.end method

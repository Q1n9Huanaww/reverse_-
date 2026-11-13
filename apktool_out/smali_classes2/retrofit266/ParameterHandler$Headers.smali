.class final Lretrofit266/ParameterHandler$Headers;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Headers"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lretrofit266/ParameterHandler<",
        "Lokhttp366/Headers;",
        ">;"
    }
.end annotation


# instance fields
.field private final method:Ljava/lang/reflect/Method;

.field private final p:I


# direct methods
.method constructor <init>(Ljava/lang/reflect/Method;I)V
    .locals 0
    .param p1, "method"    # Ljava/lang/reflect/Method;
    .param p2, "p"    # I

    .line 252
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 253
    iput-object p1, p0, Lretrofit266/ParameterHandler$Headers;->method:Ljava/lang/reflect/Method;

    .line 254
    iput p2, p0, Lretrofit266/ParameterHandler$Headers;->p:I

    .line 255
    return-void
.end method


# virtual methods
.method bridge synthetic apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
    .locals 0
    .param p2    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 248
    check-cast p2, Lokhttp366/Headers;

    invoke-virtual {p0, p1, p2}, Lretrofit266/ParameterHandler$Headers;->apply(Lretrofit266/RequestBuilder;Lokhttp366/Headers;)V

    return-void
.end method

.method apply(Lretrofit266/RequestBuilder;Lokhttp366/Headers;)V
    .locals 4
    .param p1, "builder"    # Lretrofit266/RequestBuilder;
    .param p2, "headers"    # Lokhttp366/Headers;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param

    .line 259
    if-eqz p2, :cond_0

    .line 262
    invoke-virtual {p1, p2}, Lretrofit266/RequestBuilder;->addHeaders(Lokhttp366/Headers;)V

    .line 263
    return-void

    .line 260
    :cond_0
    iget-object v0, p0, Lretrofit266/ParameterHandler$Headers;->method:Ljava/lang/reflect/Method;

    iget v1, p0, Lretrofit266/ParameterHandler$Headers;->p:I

    const/4 v2, 0x0

    new-array v2, v2, [Ljava/lang/Object;

    const-string v3, "Headers parameter must not be null."

    invoke-static {v0, v1, v3, v2}, Lretrofit266/Utils;->parameterError(Ljava/lang/reflect/Method;ILjava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v0

    throw v0
.end method

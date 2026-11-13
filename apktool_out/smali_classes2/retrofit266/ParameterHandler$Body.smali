.class final Lretrofit266/ParameterHandler$Body;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Body"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lretrofit266/ParameterHandler<",
        "TT;>;"
    }
.end annotation


# instance fields
.field private final converter:Lretrofit266/Converter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit266/Converter<",
            "TT;",
            "Lokhttp366/RequestBody;",
            ">;"
        }
    .end annotation
.end field

.field private final method:Ljava/lang/reflect/Method;

.field private final p:I


# direct methods
.method constructor <init>(Ljava/lang/reflect/Method;ILretrofit266/Converter;)V
    .locals 0
    .param p1, "method"    # Ljava/lang/reflect/Method;
    .param p2, "p"    # I
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Method;",
            "I",
            "Lretrofit266/Converter<",
            "TT;",
            "Lokhttp366/RequestBody;",
            ">;)V"
        }
    .end annotation

    .line 425
    .local p0, "this":Lretrofit266/ParameterHandler$Body;, "Lretrofit266/ParameterHandler$Body<TT;>;"
    .local p3, "converter":Lretrofit266/Converter;, "Lretrofit266/Converter<TT;Lokhttp366/RequestBody;>;"
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 426
    iput-object p1, p0, Lretrofit266/ParameterHandler$Body;->method:Ljava/lang/reflect/Method;

    .line 427
    iput p2, p0, Lretrofit266/ParameterHandler$Body;->p:I

    .line 428
    iput-object p3, p0, Lretrofit266/ParameterHandler$Body;->converter:Lretrofit266/Converter;

    .line 429
    return-void
.end method


# virtual methods
.method apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
    .locals 6
    .param p1, "builder"    # Lretrofit266/RequestBuilder;
    .param p2    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/RequestBuilder;",
            "TT;)V"
        }
    .end annotation

    .line 433
    .local p0, "this":Lretrofit266/ParameterHandler$Body;, "Lretrofit266/ParameterHandler$Body<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    const/4 v0, 0x0

    if-eqz p2, :cond_0

    .line 438
    :try_start_0
    iget-object v1, p0, Lretrofit266/ParameterHandler$Body;->converter:Lretrofit266/Converter;

    invoke-interface {v1, p2}, Lretrofit266/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lokhttp366/RequestBody;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    move-object v0, v1

    .line 441
    .local v0, "body":Lokhttp366/RequestBody;
    nop

    .line 442
    invoke-virtual {p1, v0}, Lretrofit266/RequestBuilder;->setBody(Lokhttp366/RequestBody;)V

    .line 443
    return-void

    .line 439
    .end local v0    # "body":Lokhttp366/RequestBody;
    :catch_0
    move-exception v1

    .line 440
    .local v1, "e":Ljava/io/IOException;
    iget-object v2, p0, Lretrofit266/ParameterHandler$Body;->method:Ljava/lang/reflect/Method;

    iget v3, p0, Lretrofit266/ParameterHandler$Body;->p:I

    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "Unable to convert "

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v4

    const-string v5, " to RequestBody"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    new-array v0, v0, [Ljava/lang/Object;

    invoke-static {v2, v1, v3, v4, v0}, Lretrofit266/Utils;->parameterError(Ljava/lang/reflect/Method;Ljava/lang/Throwable;ILjava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v0

    throw v0

    .line 434
    .end local v1    # "e":Ljava/io/IOException;
    :cond_0
    iget-object v1, p0, Lretrofit266/ParameterHandler$Body;->method:Ljava/lang/reflect/Method;

    iget v2, p0, Lretrofit266/ParameterHandler$Body;->p:I

    const-string v3, "Body parameter value must not be null."

    new-array v0, v0, [Ljava/lang/Object;

    invoke-static {v1, v2, v3, v0}, Lretrofit266/Utils;->parameterError(Ljava/lang/reflect/Method;ILjava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v0

    throw v0
.end method

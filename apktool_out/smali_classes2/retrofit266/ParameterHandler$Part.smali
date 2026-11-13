.class final Lretrofit266/ParameterHandler$Part;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Part"
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

.field private final headers:Lokhttp366/Headers;

.field private final method:Ljava/lang/reflect/Method;

.field private final p:I


# direct methods
.method constructor <init>(Ljava/lang/reflect/Method;ILokhttp366/Headers;Lretrofit266/Converter;)V
    .locals 0
    .param p1, "method"    # Ljava/lang/reflect/Method;
    .param p2, "p"    # I
    .param p3, "headers"    # Lokhttp366/Headers;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Method;",
            "I",
            "Lokhttp366/Headers;",
            "Lretrofit266/Converter<",
            "TT;",
            "Lokhttp366/RequestBody;",
            ">;)V"
        }
    .end annotation

    .line 343
    .local p0, "this":Lretrofit266/ParameterHandler$Part;, "Lretrofit266/ParameterHandler$Part<TT;>;"
    .local p4, "converter":Lretrofit266/Converter;, "Lretrofit266/Converter<TT;Lokhttp366/RequestBody;>;"
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 344
    iput-object p1, p0, Lretrofit266/ParameterHandler$Part;->method:Ljava/lang/reflect/Method;

    .line 345
    iput p2, p0, Lretrofit266/ParameterHandler$Part;->p:I

    .line 346
    iput-object p3, p0, Lretrofit266/ParameterHandler$Part;->headers:Lokhttp366/Headers;

    .line 347
    iput-object p4, p0, Lretrofit266/ParameterHandler$Part;->converter:Lretrofit266/Converter;

    .line 348
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

    .line 352
    .local p0, "this":Lretrofit266/ParameterHandler$Part;, "Lretrofit266/ParameterHandler$Part<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    if-nez p2, :cond_0

    return-void

    .line 356
    :cond_0
    :try_start_0
    iget-object v0, p0, Lretrofit266/ParameterHandler$Part;->converter:Lretrofit266/Converter;

    invoke-interface {v0, p2}, Lretrofit266/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Lokhttp366/RequestBody;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 359
    .local v0, "body":Lokhttp366/RequestBody;
    nop

    .line 360
    iget-object v1, p0, Lretrofit266/ParameterHandler$Part;->headers:Lokhttp366/Headers;

    invoke-virtual {p1, v1, v0}, Lretrofit266/RequestBuilder;->addPart(Lokhttp366/Headers;Lokhttp366/RequestBody;)V

    .line 361
    return-void

    .line 357
    .end local v0    # "body":Lokhttp366/RequestBody;
    :catch_0
    move-exception v0

    .line 358
    .local v0, "e":Ljava/io/IOException;
    iget-object v1, p0, Lretrofit266/ParameterHandler$Part;->method:Ljava/lang/reflect/Method;

    iget v2, p0, Lretrofit266/ParameterHandler$Part;->p:I

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Unable to convert "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, " to RequestBody"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    const/4 v4, 0x1

    new-array v4, v4, [Ljava/lang/Object;

    const/4 v5, 0x0

    aput-object v0, v4, v5

    invoke-static {v1, v2, v3, v4}, Lretrofit266/Utils;->parameterError(Ljava/lang/reflect/Method;ILjava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v1

    throw v1
.end method

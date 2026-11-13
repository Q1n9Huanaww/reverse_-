.class final Lretrofit266/ParameterHandler$Path;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Path"
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
.field private final encoded:Z

.field private final method:Ljava/lang/reflect/Method;

.field private final name:Ljava/lang/String;

.field private final p:I

.field private final valueConverter:Lretrofit266/Converter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit266/Converter<",
            "TT;",
            "Ljava/lang/String;",
            ">;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Ljava/lang/reflect/Method;ILjava/lang/String;Lretrofit266/Converter;Z)V
    .locals 1
    .param p1, "method"    # Ljava/lang/reflect/Method;
    .param p2, "p"    # I
    .param p3, "name"    # Ljava/lang/String;
    .param p5, "encoded"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Method;",
            "I",
            "Ljava/lang/String;",
            "Lretrofit266/Converter<",
            "TT;",
            "Ljava/lang/String;",
            ">;Z)V"
        }
    .end annotation

    .line 104
    .local p0, "this":Lretrofit266/ParameterHandler$Path;, "Lretrofit266/ParameterHandler$Path<TT;>;"
    .local p4, "valueConverter":Lretrofit266/Converter;, "Lretrofit266/Converter<TT;Ljava/lang/String;>;"
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 105
    iput-object p1, p0, Lretrofit266/ParameterHandler$Path;->method:Ljava/lang/reflect/Method;

    .line 106
    iput p2, p0, Lretrofit266/ParameterHandler$Path;->p:I

    .line 107
    const-string v0, "name == null"

    invoke-static {p3, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    iput-object v0, p0, Lretrofit266/ParameterHandler$Path;->name:Ljava/lang/String;

    .line 108
    iput-object p4, p0, Lretrofit266/ParameterHandler$Path;->valueConverter:Lretrofit266/Converter;

    .line 109
    iput-boolean p5, p0, Lretrofit266/ParameterHandler$Path;->encoded:Z

    .line 110
    return-void
.end method


# virtual methods
.method apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
    .locals 4
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

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 114
    .local p0, "this":Lretrofit266/ParameterHandler$Path;, "Lretrofit266/ParameterHandler$Path<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    if-eqz p2, :cond_0

    .line 118
    iget-object v0, p0, Lretrofit266/ParameterHandler$Path;->name:Ljava/lang/String;

    iget-object v1, p0, Lretrofit266/ParameterHandler$Path;->valueConverter:Lretrofit266/Converter;

    invoke-interface {v1, p2}, Lretrofit266/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Ljava/lang/String;

    iget-boolean v2, p0, Lretrofit266/ParameterHandler$Path;->encoded:Z

    invoke-virtual {p1, v0, v1, v2}, Lretrofit266/RequestBuilder;->addPathParam(Ljava/lang/String;Ljava/lang/String;Z)V

    .line 119
    return-void

    .line 115
    :cond_0
    iget-object v0, p0, Lretrofit266/ParameterHandler$Path;->method:Ljava/lang/reflect/Method;

    iget v1, p0, Lretrofit266/ParameterHandler$Path;->p:I

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Path parameter \""

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    iget-object v3, p0, Lretrofit266/ParameterHandler$Path;->name:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "\" value must not be null."

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    const/4 v3, 0x0

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {v0, v1, v2, v3}, Lretrofit266/Utils;->parameterError(Ljava/lang/reflect/Method;ILjava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v0

    throw v0
.end method

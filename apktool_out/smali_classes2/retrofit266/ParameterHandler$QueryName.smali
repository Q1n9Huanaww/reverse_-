.class final Lretrofit266/ParameterHandler$QueryName;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "QueryName"
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

.field private final nameConverter:Lretrofit266/Converter;
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
.method constructor <init>(Lretrofit266/Converter;Z)V
    .locals 0
    .param p2, "encoded"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Converter<",
            "TT;",
            "Ljava/lang/String;",
            ">;Z)V"
        }
    .end annotation

    .line 148
    .local p0, "this":Lretrofit266/ParameterHandler$QueryName;, "Lretrofit266/ParameterHandler$QueryName<TT;>;"
    .local p1, "nameConverter":Lretrofit266/Converter;, "Lretrofit266/Converter<TT;Ljava/lang/String;>;"
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 149
    iput-object p1, p0, Lretrofit266/ParameterHandler$QueryName;->nameConverter:Lretrofit266/Converter;

    .line 150
    iput-boolean p2, p0, Lretrofit266/ParameterHandler$QueryName;->encoded:Z

    .line 151
    return-void
.end method


# virtual methods
.method apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
    .locals 3
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

    .line 155
    .local p0, "this":Lretrofit266/ParameterHandler$QueryName;, "Lretrofit266/ParameterHandler$QueryName<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    if-nez p2, :cond_0

    return-void

    .line 156
    :cond_0
    iget-object v0, p0, Lretrofit266/ParameterHandler$QueryName;->nameConverter:Lretrofit266/Converter;

    invoke-interface {v0, p2}, Lretrofit266/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    const/4 v1, 0x0

    iget-boolean v2, p0, Lretrofit266/ParameterHandler$QueryName;->encoded:Z

    invoke-virtual {p1, v0, v1, v2}, Lretrofit266/RequestBuilder;->addQueryParam(Ljava/lang/String;Ljava/lang/String;Z)V

    .line 157
    return-void
.end method

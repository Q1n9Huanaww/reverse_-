.class final Lretrofit266/ParameterHandler$Header;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Header"
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
.field private final allowUnsafeNonAsciiValues:Z

.field private final name:Ljava/lang/String;

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
.method constructor <init>(Ljava/lang/String;Lretrofit266/Converter;Z)V
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p3, "allowUnsafeNonAsciiValues"    # Z
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/String;",
            "Lretrofit266/Converter<",
            "TT;",
            "Ljava/lang/String;",
            ">;Z)V"
        }
    .end annotation

    .line 80
    .local p0, "this":Lretrofit266/ParameterHandler$Header;, "Lretrofit266/ParameterHandler$Header<TT;>;"
    .local p2, "valueConverter":Lretrofit266/Converter;, "Lretrofit266/Converter<TT;Ljava/lang/String;>;"
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 81
    const-string v0, "name == null"

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    iput-object v0, p0, Lretrofit266/ParameterHandler$Header;->name:Ljava/lang/String;

    .line 82
    iput-object p2, p0, Lretrofit266/ParameterHandler$Header;->valueConverter:Lretrofit266/Converter;

    .line 83
    iput-boolean p3, p0, Lretrofit266/ParameterHandler$Header;->allowUnsafeNonAsciiValues:Z

    .line 84
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

    .line 88
    .local p0, "this":Lretrofit266/ParameterHandler$Header;, "Lretrofit266/ParameterHandler$Header<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    if-nez p2, :cond_0

    return-void

    .line 90
    :cond_0
    iget-object v0, p0, Lretrofit266/ParameterHandler$Header;->valueConverter:Lretrofit266/Converter;

    invoke-interface {v0, p2}, Lretrofit266/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Ljava/lang/String;

    .line 91
    .local v0, "headerValue":Ljava/lang/String;
    if-nez v0, :cond_1

    return-void

    .line 93
    :cond_1
    iget-object v1, p0, Lretrofit266/ParameterHandler$Header;->name:Ljava/lang/String;

    iget-boolean v2, p0, Lretrofit266/ParameterHandler$Header;->allowUnsafeNonAsciiValues:Z

    invoke-virtual {p1, v1, v0, v2}, Lretrofit266/RequestBuilder;->addHeader(Ljava/lang/String;Ljava/lang/String;Z)V

    .line 94
    return-void
.end method

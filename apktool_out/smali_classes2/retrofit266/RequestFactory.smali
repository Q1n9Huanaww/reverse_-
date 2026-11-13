.class final Lretrofit266/RequestFactory;
.super Ljava/lang/Object;
.source "RequestFactory.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/RequestFactory$Builder;
    }
.end annotation


# instance fields
.field private final baseUrl:Lokhttp366/HttpUrl;

.field private final contentType:Lokhttp366/MediaType;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final hasBody:Z

.field private final headers:Lokhttp366/Headers;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field final httpMethod:Ljava/lang/String;

.field private final isFormEncoded:Z

.field final isKotlinSuspendFunction:Z

.field private final isMultipart:Z

.field private final method:Ljava/lang/reflect/Method;

.field private final parameterHandlers:[Lretrofit266/ParameterHandler;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "[",
            "Lretrofit266/ParameterHandler<",
            "*>;"
        }
    .end annotation
.end field

.field private final relativeUrl:Ljava/lang/String;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final service:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class<",
            "*>;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lretrofit266/RequestFactory$Builder;)V
    .locals 1
    .param p1, "builder"    # Lretrofit266/RequestFactory$Builder;

    .line 83
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 84
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->service:Ljava/lang/Class;

    iput-object v0, p0, Lretrofit266/RequestFactory;->service:Ljava/lang/Class;

    .line 85
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->method:Ljava/lang/reflect/Method;

    iput-object v0, p0, Lretrofit266/RequestFactory;->method:Ljava/lang/reflect/Method;

    .line 86
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->retrofit:Lretrofit266/Retrofit;

    iget-object v0, v0, Lretrofit266/Retrofit;->baseUrl:Lokhttp366/HttpUrl;

    iput-object v0, p0, Lretrofit266/RequestFactory;->baseUrl:Lokhttp366/HttpUrl;

    .line 87
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->httpMethod:Ljava/lang/String;

    iput-object v0, p0, Lretrofit266/RequestFactory;->httpMethod:Ljava/lang/String;

    .line 88
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->relativeUrl:Ljava/lang/String;

    iput-object v0, p0, Lretrofit266/RequestFactory;->relativeUrl:Ljava/lang/String;

    .line 89
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->headers:Lokhttp366/Headers;

    iput-object v0, p0, Lretrofit266/RequestFactory;->headers:Lokhttp366/Headers;

    .line 90
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->contentType:Lokhttp366/MediaType;

    iput-object v0, p0, Lretrofit266/RequestFactory;->contentType:Lokhttp366/MediaType;

    .line 91
    iget-boolean v0, p1, Lretrofit266/RequestFactory$Builder;->hasBody:Z

    iput-boolean v0, p0, Lretrofit266/RequestFactory;->hasBody:Z

    .line 92
    iget-boolean v0, p1, Lretrofit266/RequestFactory$Builder;->isFormEncoded:Z

    iput-boolean v0, p0, Lretrofit266/RequestFactory;->isFormEncoded:Z

    .line 93
    iget-boolean v0, p1, Lretrofit266/RequestFactory$Builder;->isMultipart:Z

    iput-boolean v0, p0, Lretrofit266/RequestFactory;->isMultipart:Z

    .line 94
    iget-object v0, p1, Lretrofit266/RequestFactory$Builder;->parameterHandlers:[Lretrofit266/ParameterHandler;

    iput-object v0, p0, Lretrofit266/RequestFactory;->parameterHandlers:[Lretrofit266/ParameterHandler;

    .line 95
    iget-boolean v0, p1, Lretrofit266/RequestFactory$Builder;->isKotlinSuspendFunction:Z

    iput-boolean v0, p0, Lretrofit266/RequestFactory;->isKotlinSuspendFunction:Z

    .line 96
    return-void
.end method

.method static parseAnnotations(Lretrofit266/Retrofit;Ljava/lang/Class;Ljava/lang/reflect/Method;)Lretrofit266/RequestFactory;
    .locals 1
    .param p0, "retrofit"    # Lretrofit266/Retrofit;
    .param p2, "method"    # Ljava/lang/reflect/Method;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Retrofit;",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/reflect/Method;",
            ")",
            "Lretrofit266/RequestFactory;"
        }
    .end annotation

    .line 67
    .local p1, "service":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    new-instance v0, Lretrofit266/RequestFactory$Builder;

    invoke-direct {v0, p0, p1, p2}, Lretrofit266/RequestFactory$Builder;-><init>(Lretrofit266/Retrofit;Ljava/lang/Class;Ljava/lang/reflect/Method;)V

    invoke-virtual {v0}, Lretrofit266/RequestFactory$Builder;->build()Lretrofit266/RequestFactory;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method create(Ljava/lang/Object;[Ljava/lang/Object;)Lokhttp366/Request;
    .locals 12
    .param p1, "instance"    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "args"    # [Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 100
    iget-object v0, p0, Lretrofit266/RequestFactory;->parameterHandlers:[Lretrofit266/ParameterHandler;

    .line 102
    .local v0, "handlers":[Lretrofit266/ParameterHandler;, "[Lretrofit266/ParameterHandler<Ljava/lang/Object;>;"
    array-length v1, p2

    .line 103
    .local v1, "argumentCount":I
    array-length v2, v0

    if-ne v1, v2, :cond_2

    .line 112
    new-instance v2, Lretrofit266/RequestBuilder;

    iget-object v4, p0, Lretrofit266/RequestFactory;->httpMethod:Ljava/lang/String;

    iget-object v5, p0, Lretrofit266/RequestFactory;->baseUrl:Lokhttp366/HttpUrl;

    iget-object v6, p0, Lretrofit266/RequestFactory;->relativeUrl:Ljava/lang/String;

    iget-object v7, p0, Lretrofit266/RequestFactory;->headers:Lokhttp366/Headers;

    iget-object v8, p0, Lretrofit266/RequestFactory;->contentType:Lokhttp366/MediaType;

    iget-boolean v9, p0, Lretrofit266/RequestFactory;->hasBody:Z

    iget-boolean v10, p0, Lretrofit266/RequestFactory;->isFormEncoded:Z

    iget-boolean v11, p0, Lretrofit266/RequestFactory;->isMultipart:Z

    move-object v3, v2

    invoke-direct/range {v3 .. v11}, Lretrofit266/RequestBuilder;-><init>(Ljava/lang/String;Lokhttp366/HttpUrl;Ljava/lang/String;Lokhttp366/Headers;Lokhttp366/MediaType;ZZZ)V

    .line 123
    .local v2, "requestBuilder":Lretrofit266/RequestBuilder;
    iget-boolean v3, p0, Lretrofit266/RequestFactory;->isKotlinSuspendFunction:Z

    if-eqz v3, :cond_0

    .line 125
    add-int/lit8 v1, v1, -0x1

    .line 128
    :cond_0
    new-instance v3, Ljava/util/ArrayList;

    invoke-direct {v3, v1}, Ljava/util/ArrayList;-><init>(I)V

    .line 129
    .local v3, "argumentList":Ljava/util/List;, "Ljava/util/List<Ljava/lang/Object;>;"
    const/4 v4, 0x0

    .local v4, "p":I
    :goto_0
    if-ge v4, v1, :cond_1

    .line 130
    aget-object v5, p2, v4

    invoke-interface {v3, v5}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 131
    aget-object v5, v0, v4

    aget-object v6, p2, v4

    invoke-virtual {v5, v2, v6}, Lretrofit266/ParameterHandler;->apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V

    .line 129
    add-int/lit8 v4, v4, 0x1

    goto :goto_0

    .line 134
    .end local v4    # "p":I
    :cond_1
    nop

    .line 135
    invoke-virtual {v2}, Lretrofit266/RequestBuilder;->get()Lokhttp366/Request$Builder;

    move-result-object v4

    const-class v5, Lretrofit266/Invocation;

    new-instance v6, Lretrofit266/Invocation;

    iget-object v7, p0, Lretrofit266/RequestFactory;->service:Ljava/lang/Class;

    iget-object v8, p0, Lretrofit266/RequestFactory;->method:Ljava/lang/reflect/Method;

    invoke-direct {v6, v7, p1, v8, v3}, Lretrofit266/Invocation;-><init>(Ljava/lang/Class;Ljava/lang/Object;Ljava/lang/reflect/Method;Ljava/util/List;)V

    .line 136
    invoke-virtual {v4, v5, v6}, Lokhttp366/Request$Builder;->tag(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp366/Request$Builder;

    move-result-object v4

    .line 137
    invoke-virtual {v4}, Lokhttp366/Request$Builder;->build()Lokhttp366/Request;

    move-result-object v4

    .line 134
    return-object v4

    .line 104
    .end local v2    # "requestBuilder":Lretrofit266/RequestBuilder;
    .end local v3    # "argumentList":Ljava/util/List;, "Ljava/util/List<Ljava/lang/Object;>;"
    :cond_2
    new-instance v2, Ljava/lang/IllegalArgumentException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Argument count ("

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, v1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ") doesn\'t match expected count ("

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    array-length v4, v0

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ")"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v2
.end method

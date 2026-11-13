.class abstract Lretrofit266/ServiceMethod;
.super Ljava/lang/Object;
.source "ServiceMethod.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 24
    .local p0, "this":Lretrofit266/ServiceMethod;, "Lretrofit266/ServiceMethod<TT;>;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static parseAnnotations(Lretrofit266/Retrofit;Ljava/lang/Class;Ljava/lang/reflect/Method;)Lretrofit266/ServiceMethod;
    .locals 4
    .param p0, "retrofit"    # Lretrofit266/Retrofit;
    .param p2, "method"    # Ljava/lang/reflect/Method;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lretrofit266/Retrofit;",
            "Ljava/lang/Class<",
            "*>;",
            "Ljava/lang/reflect/Method;",
            ")",
            "Lretrofit266/ServiceMethod<",
            "TT;>;"
        }
    .end annotation

    .line 26
    .local p1, "service":Ljava/lang/Class;, "Ljava/lang/Class<*>;"
    invoke-static {p0, p1, p2}, Lretrofit266/RequestFactory;->parseAnnotations(Lretrofit266/Retrofit;Ljava/lang/Class;Ljava/lang/reflect/Method;)Lretrofit266/RequestFactory;

    move-result-object v0

    .line 28
    .local v0, "requestFactory":Lretrofit266/RequestFactory;
    invoke-virtual {p2}, Ljava/lang/reflect/Method;->getGenericReturnType()Ljava/lang/reflect/Type;

    move-result-object v1

    .line 29
    .local v1, "returnType":Ljava/lang/reflect/Type;
    invoke-static {v1}, Lretrofit266/Utils;->hasUnresolvableType(Ljava/lang/reflect/Type;)Z

    move-result v2

    const/4 v3, 0x0

    if-nez v2, :cond_1

    .line 35
    sget-object v2, Ljava/lang/Void;->TYPE:Ljava/lang/Class;

    if-eq v1, v2, :cond_0

    .line 39
    invoke-static {p0, p2, v0}, Lretrofit266/HttpServiceMethod;->parseAnnotations(Lretrofit266/Retrofit;Ljava/lang/reflect/Method;Lretrofit266/RequestFactory;)Lretrofit266/HttpServiceMethod;

    move-result-object v2

    return-object v2

    .line 36
    :cond_0
    const-string v2, "Service methods cannot return void."

    new-array v3, v3, [Ljava/lang/Object;

    invoke-static {p2, v2, v3}, Lretrofit266/Utils;->methodError(Ljava/lang/reflect/Method;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v2

    throw v2

    .line 30
    :cond_1
    const/4 v2, 0x1

    new-array v2, v2, [Ljava/lang/Object;

    aput-object v1, v2, v3

    const-string v3, "Method return type must not include a type variable or wildcard: %s"

    invoke-static {p2, v3, v2}, Lretrofit266/Utils;->methodError(Ljava/lang/reflect/Method;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/RuntimeException;

    move-result-object v2

    throw v2
.end method


# virtual methods
.method abstract invoke(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Object;",
            "[",
            "Ljava/lang/Object;",
            ")TT;"
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end method

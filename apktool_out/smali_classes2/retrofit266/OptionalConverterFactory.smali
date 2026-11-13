.class public final Lretrofit266/OptionalConverterFactory;
.super Lretrofit266/Converter$Factory;
.source "OptionalConverterFactory.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/OptionalConverterFactory$OptionalConverter;
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 44
    invoke-direct {p0}, Lretrofit266/Converter$Factory;-><init>()V

    return-void
.end method

.method public static create()Lretrofit266/OptionalConverterFactory;
    .locals 1

    .line 41
    new-instance v0, Lretrofit266/OptionalConverterFactory;

    invoke-direct {v0}, Lretrofit266/OptionalConverterFactory;-><init>()V

    return-object v0
.end method


# virtual methods
.method public responseBodyConverter(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;Lretrofit266/Retrofit;)Lretrofit266/Converter;
    .locals 3
    .param p1, "type"    # Ljava/lang/reflect/Type;
    .param p2, "annotations"    # [Ljava/lang/annotation/Annotation;
    .param p3, "retrofit"    # Lretrofit266/Retrofit;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Type;",
            "[",
            "Ljava/lang/annotation/Annotation;",
            "Lretrofit266/Retrofit;",
            ")",
            "Lretrofit266/Converter<",
            "Lokhttp366/ResponseBody;",
            "*>;"
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 49
    invoke-static {p1}, Lretrofit266/OptionalConverterFactory;->getRawType(Ljava/lang/reflect/Type;)Ljava/lang/Class;

    move-result-object v0

    const-class v1, Ljava/util/Optional;

    if-eq v0, v1, :cond_0

    .line 50
    const/4 v0, 0x0

    return-object v0

    .line 53
    :cond_0
    const/4 v0, 0x0

    move-object v1, p1

    check-cast v1, Ljava/lang/reflect/ParameterizedType;

    invoke-static {v0, v1}, Lretrofit266/OptionalConverterFactory;->getParameterUpperBound(ILjava/lang/reflect/ParameterizedType;)Ljava/lang/reflect/Type;

    move-result-object v0

    .line 54
    .local v0, "innerType":Ljava/lang/reflect/Type;
    nop

    .line 55
    invoke-virtual {p3, v0, p2}, Lretrofit266/Retrofit;->responseBodyConverter(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;)Lretrofit266/Converter;

    move-result-object v1

    .line 56
    .local v1, "delegate":Lretrofit266/Converter;, "Lretrofit266/Converter<Lokhttp366/ResponseBody;Ljava/lang/Object;>;"
    new-instance v2, Lretrofit266/OptionalConverterFactory$OptionalConverter;

    invoke-direct {v2, v1}, Lretrofit266/OptionalConverterFactory$OptionalConverter;-><init>(Lretrofit266/Converter;)V

    return-object v2
.end method

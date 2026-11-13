.class final Lretrofit266/BuiltInConverters;
.super Lretrofit266/Converter$Factory;
.source "BuiltInConverters.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/BuiltInConverters$ToStringConverter;,
        Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;,
        Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;,
        Lretrofit266/BuiltInConverters$RequestBodyConverter;,
        Lretrofit266/BuiltInConverters$UnitResponseBodyConverter;,
        Lretrofit266/BuiltInConverters$VoidResponseBodyConverter;
    }
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 27
    invoke-direct {p0}, Lretrofit266/Converter$Factory;-><init>()V

    return-void
.end method


# virtual methods
.method public requestBodyConverter(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;[Ljava/lang/annotation/Annotation;Lretrofit266/Retrofit;)Lretrofit266/Converter;
    .locals 2
    .param p1, "type"    # Ljava/lang/reflect/Type;
    .param p2, "parameterAnnotations"    # [Ljava/lang/annotation/Annotation;
    .param p3, "methodAnnotations"    # [Ljava/lang/annotation/Annotation;
    .param p4, "retrofit"    # Lretrofit266/Retrofit;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/reflect/Type;",
            "[",
            "Ljava/lang/annotation/Annotation;",
            "[",
            "Ljava/lang/annotation/Annotation;",
            "Lretrofit266/Retrofit;",
            ")",
            "Lretrofit266/Converter<",
            "*",
            "Lokhttp366/RequestBody;",
            ">;"
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 52
    const-class v0, Lokhttp366/RequestBody;

    invoke-static {p1}, Lretrofit266/Utils;->getRawType(Ljava/lang/reflect/Type;)Ljava/lang/Class;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/Class;->isAssignableFrom(Ljava/lang/Class;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 53
    sget-object v0, Lretrofit266/BuiltInConverters$RequestBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$RequestBodyConverter;

    return-object v0

    .line 55
    :cond_0
    const/4 v0, 0x0

    return-object v0
.end method

.method public responseBodyConverter(Ljava/lang/reflect/Type;[Ljava/lang/annotation/Annotation;Lretrofit266/Retrofit;)Lretrofit266/Converter;
    .locals 1
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

    .line 32
    const-class v0, Lokhttp366/ResponseBody;

    if-ne p1, v0, :cond_1

    .line 33
    const-class v0, Lretrofit266/http/Streaming;

    invoke-static {p2, v0}, Lretrofit266/Utils;->isAnnotationPresent([Ljava/lang/annotation/Annotation;Ljava/lang/Class;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 34
    sget-object v0, Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;

    goto :goto_0

    .line 35
    :cond_0
    sget-object v0, Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;

    .line 33
    :goto_0
    return-object v0

    .line 37
    :cond_1
    const-class v0, Ljava/lang/Void;

    if-ne p1, v0, :cond_2

    .line 38
    sget-object v0, Lretrofit266/BuiltInConverters$VoidResponseBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$VoidResponseBodyConverter;

    return-object v0

    .line 40
    :cond_2
    invoke-static {p1}, Lretrofit266/Utils;->isUnit(Ljava/lang/reflect/Type;)Z

    move-result v0

    if-eqz v0, :cond_3

    .line 41
    sget-object v0, Lretrofit266/BuiltInConverters$UnitResponseBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$UnitResponseBodyConverter;

    return-object v0

    .line 43
    :cond_3
    const/4 v0, 0x0

    return-object v0
.end method

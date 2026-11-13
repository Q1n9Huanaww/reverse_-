.class final Lretrofit266/OptionalConverterFactory$OptionalConverter;
.super Ljava/lang/Object;
.source "OptionalConverterFactory.java"

# interfaces
.implements Lretrofit266/Converter;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/OptionalConverterFactory;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "OptionalConverter"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;",
        "Lretrofit266/Converter<",
        "Lokhttp366/ResponseBody;",
        "Ljava/util/Optional<",
        "TT;>;>;"
    }
.end annotation


# instance fields
.field private final delegate:Lretrofit266/Converter;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Lretrofit266/Converter<",
            "Lokhttp366/ResponseBody;",
            "TT;>;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Lretrofit266/Converter;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Converter<",
            "Lokhttp366/ResponseBody;",
            "TT;>;)V"
        }
    .end annotation

    .line 63
    .local p0, "this":Lretrofit266/OptionalConverterFactory$OptionalConverter;, "Lretrofit266/OptionalConverterFactory$OptionalConverter<TT;>;"
    .local p1, "delegate":Lretrofit266/Converter;, "Lretrofit266/Converter<Lokhttp366/ResponseBody;TT;>;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 64
    iput-object p1, p0, Lretrofit266/OptionalConverterFactory$OptionalConverter;->delegate:Lretrofit266/Converter;

    .line 65
    return-void
.end method


# virtual methods
.method public bridge synthetic convert(Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 59
    .local p0, "this":Lretrofit266/OptionalConverterFactory$OptionalConverter;, "Lretrofit266/OptionalConverterFactory$OptionalConverter<TT;>;"
    check-cast p1, Lokhttp366/ResponseBody;

    invoke-virtual {p0, p1}, Lretrofit266/OptionalConverterFactory$OptionalConverter;->convert(Lokhttp366/ResponseBody;)Ljava/util/Optional;

    move-result-object p1

    return-object p1
.end method

.method public convert(Lokhttp366/ResponseBody;)Ljava/util/Optional;
    .locals 1
    .param p1, "value"    # Lokhttp366/ResponseBody;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/ResponseBody;",
            ")",
            "Ljava/util/Optional<",
            "TT;>;"
        }
    .end annotation

    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 69
    .local p0, "this":Lretrofit266/OptionalConverterFactory$OptionalConverter;, "Lretrofit266/OptionalConverterFactory$OptionalConverter<TT;>;"
    iget-object v0, p0, Lretrofit266/OptionalConverterFactory$OptionalConverter;->delegate:Lretrofit266/Converter;

    invoke-interface {v0, p1}, Lretrofit266/Converter;->convert(Ljava/lang/Object;)Ljava/lang/Object;

    move-result-object v0

    invoke-static {v0}, Ljava/util/Optional;->ofNullable(Ljava/lang/Object;)Ljava/util/Optional;

    move-result-object v0

    return-object v0
.end method

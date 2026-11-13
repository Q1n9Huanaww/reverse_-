.class final Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;
.super Ljava/lang/Object;
.source "BuiltInConverters.java"

# interfaces
.implements Lretrofit266/Converter;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/BuiltInConverters;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "StreamingResponseBodyConverter"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lretrofit266/Converter<",
        "Lokhttp366/ResponseBody;",
        "Lokhttp366/ResponseBody;",
        ">;"
    }
.end annotation


# static fields
.field static final INSTANCE:Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 89
    new-instance v0, Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;

    invoke-direct {v0}, Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;-><init>()V

    sput-object v0, Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    .line 87
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

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

    .line 87
    check-cast p1, Lokhttp366/ResponseBody;

    invoke-virtual {p0, p1}, Lretrofit266/BuiltInConverters$StreamingResponseBodyConverter;->convert(Lokhttp366/ResponseBody;)Lokhttp366/ResponseBody;

    move-result-object p1

    return-object p1
.end method

.method public convert(Lokhttp366/ResponseBody;)Lokhttp366/ResponseBody;
    .locals 0
    .param p1, "value"    # Lokhttp366/ResponseBody;

    .line 93
    return-object p1
.end method

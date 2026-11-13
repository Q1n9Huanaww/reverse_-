.class final Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;
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
    name = "BufferingResponseBodyConverter"
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
.field static final INSTANCE:Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 99
    new-instance v0, Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;

    invoke-direct {v0}, Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;-><init>()V

    sput-object v0, Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;->INSTANCE:Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;

    return-void
.end method

.method constructor <init>()V
    .locals 0

    .line 97
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

    .line 97
    check-cast p1, Lokhttp366/ResponseBody;

    invoke-virtual {p0, p1}, Lretrofit266/BuiltInConverters$BufferingResponseBodyConverter;->convert(Lokhttp366/ResponseBody;)Lokhttp366/ResponseBody;

    move-result-object p1

    return-object p1
.end method

.method public convert(Lokhttp366/ResponseBody;)Lokhttp366/ResponseBody;
    .locals 1
    .param p1, "value"    # Lokhttp366/ResponseBody;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 105
    :try_start_0
    invoke-static {p1}, Lretrofit266/Utils;->buffer(Lokhttp366/ResponseBody;)Lokhttp366/ResponseBody;

    move-result-object v0
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 107
    invoke-virtual {p1}, Lokhttp366/ResponseBody;->close()V

    .line 105
    return-object v0

    .line 107
    :catchall_0
    move-exception v0

    invoke-virtual {p1}, Lokhttp366/ResponseBody;->close()V

    throw v0
.end method

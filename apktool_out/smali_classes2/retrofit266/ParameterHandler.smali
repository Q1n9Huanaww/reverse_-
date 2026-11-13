.class abstract Lretrofit266/ParameterHandler;
.super Ljava/lang/Object;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/ParameterHandler$Tag;,
        Lretrofit266/ParameterHandler$Body;,
        Lretrofit266/ParameterHandler$PartMap;,
        Lretrofit266/ParameterHandler$RawPart;,
        Lretrofit266/ParameterHandler$Part;,
        Lretrofit266/ParameterHandler$FieldMap;,
        Lretrofit266/ParameterHandler$Field;,
        Lretrofit266/ParameterHandler$Headers;,
        Lretrofit266/ParameterHandler$HeaderMap;,
        Lretrofit266/ParameterHandler$QueryMap;,
        Lretrofit266/ParameterHandler$QueryName;,
        Lretrofit266/ParameterHandler$Query;,
        Lretrofit266/ParameterHandler$Path;,
        Lretrofit266/ParameterHandler$Header;,
        Lretrofit266/ParameterHandler$RelativeUrl;
    }
.end annotation

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

    .line 27
    .local p0, "this":Lretrofit266/ParameterHandler;, "Lretrofit266/ParameterHandler<TT;>;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method abstract apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
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
.end method

.method final array()Lretrofit266/ParameterHandler;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lretrofit266/ParameterHandler<",
            "Ljava/lang/Object;",
            ">;"
        }
    .end annotation

    .line 44
    .local p0, "this":Lretrofit266/ParameterHandler;, "Lretrofit266/ParameterHandler<TT;>;"
    new-instance v0, Lretrofit266/ParameterHandler$2;

    invoke-direct {v0, p0}, Lretrofit266/ParameterHandler$2;-><init>(Lretrofit266/ParameterHandler;)V

    return-object v0
.end method

.method final iterable()Lretrofit266/ParameterHandler;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Lretrofit266/ParameterHandler<",
            "Ljava/lang/Iterable<",
            "TT;>;>;"
        }
    .end annotation

    .line 31
    .local p0, "this":Lretrofit266/ParameterHandler;, "Lretrofit266/ParameterHandler<TT;>;"
    new-instance v0, Lretrofit266/ParameterHandler$1;

    invoke-direct {v0, p0}, Lretrofit266/ParameterHandler$1;-><init>(Lretrofit266/ParameterHandler;)V

    return-object v0
.end method

.class final Lretrofit266/ParameterHandler$RawPart;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "RawPart"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Lretrofit266/ParameterHandler<",
        "Lokhttp366/MultipartBody$Part;",
        ">;"
    }
.end annotation


# static fields
.field static final INSTANCE:Lretrofit266/ParameterHandler$RawPart;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 365
    new-instance v0, Lretrofit266/ParameterHandler$RawPart;

    invoke-direct {v0}, Lretrofit266/ParameterHandler$RawPart;-><init>()V

    sput-object v0, Lretrofit266/ParameterHandler$RawPart;->INSTANCE:Lretrofit266/ParameterHandler$RawPart;

    return-void
.end method

.method private constructor <init>()V
    .locals 0

    .line 367
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    return-void
.end method


# virtual methods
.method bridge synthetic apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
    .locals 0
    .param p2    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 364
    check-cast p2, Lokhttp366/MultipartBody$Part;

    invoke-virtual {p0, p1, p2}, Lretrofit266/ParameterHandler$RawPart;->apply(Lretrofit266/RequestBuilder;Lokhttp366/MultipartBody$Part;)V

    return-void
.end method

.method apply(Lretrofit266/RequestBuilder;Lokhttp366/MultipartBody$Part;)V
    .locals 0
    .param p1, "builder"    # Lretrofit266/RequestBuilder;
    .param p2, "value"    # Lokhttp366/MultipartBody$Part;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param

    .line 371
    if-eqz p2, :cond_0

    .line 372
    invoke-virtual {p1, p2}, Lretrofit266/RequestBuilder;->addPart(Lokhttp366/MultipartBody$Part;)V

    .line 374
    :cond_0
    return-void
.end method

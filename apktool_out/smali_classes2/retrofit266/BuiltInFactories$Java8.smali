.class final Lretrofit266/BuiltInFactories$Java8;
.super Lretrofit266/BuiltInFactories;
.source "BuiltInFactories.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/BuiltInFactories;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Java8"
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 38
    invoke-direct {p0}, Lretrofit266/BuiltInFactories;-><init>()V

    return-void
.end method


# virtual methods
.method createDefaultCallAdapterFactories(Ljava/util/concurrent/Executor;)Ljava/util/List;
    .locals 3
    .param p1, "callbackExecutor"    # Ljava/util/concurrent/Executor;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/concurrent/Executor;",
            ")",
            "Ljava/util/List<",
            "+",
            "Lretrofit266/CallAdapter$Factory;",
            ">;"
        }
    .end annotation

    .line 42
    const/4 v0, 0x2

    new-array v0, v0, [Lretrofit266/CallAdapter$Factory;

    new-instance v1, Lretrofit266/CompletableFutureCallAdapterFactory;

    invoke-direct {v1}, Lretrofit266/CompletableFutureCallAdapterFactory;-><init>()V

    const/4 v2, 0x0

    aput-object v1, v0, v2

    new-instance v1, Lretrofit266/DefaultCallAdapterFactory;

    invoke-direct {v1, p1}, Lretrofit266/DefaultCallAdapterFactory;-><init>(Ljava/util/concurrent/Executor;)V

    const/4 v2, 0x1

    aput-object v1, v0, v2

    invoke-static {v0}, Ljava/util/Arrays;->asList([Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

.method createDefaultConverterFactories()Ljava/util/List;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()",
            "Ljava/util/List<",
            "+",
            "Lretrofit266/Converter$Factory;",
            ">;"
        }
    .end annotation

    .line 49
    new-instance v0, Lretrofit266/OptionalConverterFactory;

    invoke-direct {v0}, Lretrofit266/OptionalConverterFactory;-><init>()V

    invoke-static {v0}, Ljava/util/Collections;->singletonList(Ljava/lang/Object;)Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

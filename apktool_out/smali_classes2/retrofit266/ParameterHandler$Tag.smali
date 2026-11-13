.class final Lretrofit266/ParameterHandler$Tag;
.super Lretrofit266/ParameterHandler;
.source "ParameterHandler.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lretrofit266/ParameterHandler;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x18
    name = "Tag"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Lretrofit266/ParameterHandler<",
        "TT;>;"
    }
.end annotation


# instance fields
.field final cls:Ljava/lang/Class;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/lang/Class<",
            "TT;>;"
        }
    .end annotation
.end field


# direct methods
.method constructor <init>(Ljava/lang/Class;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/lang/Class<",
            "TT;>;)V"
        }
    .end annotation

    .line 449
    .local p0, "this":Lretrofit266/ParameterHandler$Tag;, "Lretrofit266/ParameterHandler$Tag<TT;>;"
    .local p1, "cls":Ljava/lang/Class;, "Ljava/lang/Class<TT;>;"
    invoke-direct {p0}, Lretrofit266/ParameterHandler;-><init>()V

    .line 450
    iput-object p1, p0, Lretrofit266/ParameterHandler$Tag;->cls:Ljava/lang/Class;

    .line 451
    return-void
.end method


# virtual methods
.method apply(Lretrofit266/RequestBuilder;Ljava/lang/Object;)V
    .locals 1
    .param p1, "builder"    # Lretrofit266/RequestBuilder;
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

    .line 455
    .local p0, "this":Lretrofit266/ParameterHandler$Tag;, "Lretrofit266/ParameterHandler$Tag<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    iget-object v0, p0, Lretrofit266/ParameterHandler$Tag;->cls:Ljava/lang/Class;

    invoke-virtual {p1, v0, p2}, Lretrofit266/RequestBuilder;->addTag(Ljava/lang/Class;Ljava/lang/Object;)V

    .line 456
    return-void
.end method

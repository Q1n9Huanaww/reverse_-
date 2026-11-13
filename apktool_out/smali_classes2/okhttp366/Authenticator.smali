.class public interface abstract Lokhttp366/Authenticator;
.super Ljava/lang/Object;
.source "Authenticator.java"


# static fields
.field public static final NONE:Lokhttp366/Authenticator;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 100
    new-instance v0, Lokhttp366/Authenticator$$ExternalSyntheticLambda0;

    invoke-direct {v0}, Lokhttp366/Authenticator$$ExternalSyntheticLambda0;-><init>()V

    sput-object v0, Lokhttp366/Authenticator;->NONE:Lokhttp366/Authenticator;

    return-void
.end method


# virtual methods
.method public abstract authenticate(Lokhttp366/Route;Lokhttp366/Response;)Lokhttp366/Request;
    .param p1    # Lokhttp366/Route;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end method

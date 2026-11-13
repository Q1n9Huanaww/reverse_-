.class Lokhttp366/CookieJar$1;
.super Ljava/lang/Object;
.source "CookieJar.java"

# interfaces
.implements Lokhttp366/CookieJar;


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/CookieJar;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# direct methods
.method constructor <init>()V
    .locals 0

    .line 36
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public loadForRequest(Lokhttp366/HttpUrl;)Ljava/util/List;
    .locals 1
    .param p1, "url"    # Lokhttp366/HttpUrl;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/HttpUrl;",
            ")",
            "Ljava/util/List<",
            "Lokhttp366/Cookie;",
            ">;"
        }
    .end annotation

    .line 41
    invoke-static {}, Ljava/util/Collections;->emptyList()Ljava/util/List;

    move-result-object v0

    return-object v0
.end method

.method public saveFromResponse(Lokhttp366/HttpUrl;Ljava/util/List;)V
    .locals 0
    .param p1, "url"    # Lokhttp366/HttpUrl;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/HttpUrl;",
            "Ljava/util/List<",
            "Lokhttp366/Cookie;",
            ">;)V"
        }
    .end annotation

    .line 38
    .local p2, "cookies":Ljava/util/List;, "Ljava/util/List<Lokhttp366/Cookie;>;"
    return-void
.end method

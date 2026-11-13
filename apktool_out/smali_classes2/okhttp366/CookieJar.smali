.class public interface abstract Lokhttp366/CookieJar;
.super Ljava/lang/Object;
.source "CookieJar.java"


# static fields
.field public static final NO_COOKIES:Lokhttp366/CookieJar;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 36
    new-instance v0, Lokhttp366/CookieJar$1;

    invoke-direct {v0}, Lokhttp366/CookieJar$1;-><init>()V

    sput-object v0, Lokhttp366/CookieJar;->NO_COOKIES:Lokhttp366/CookieJar;

    return-void
.end method


# virtual methods
.method public abstract loadForRequest(Lokhttp366/HttpUrl;)Ljava/util/List;
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
.end method

.method public abstract saveFromResponse(Lokhttp366/HttpUrl;Ljava/util/List;)V
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/HttpUrl;",
            "Ljava/util/List<",
            "Lokhttp366/Cookie;",
            ">;)V"
        }
    .end annotation
.end method

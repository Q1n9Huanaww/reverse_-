.class public Lcom/example/keyverificationonline/Config;
.super Ljava/lang/Object;
.source "Config.java"


# static fields
.field public static APP_VERSION:Ljava/lang/String;

.field public static BASE_URL:Ljava/lang/String;

.field public static PASSWORD:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 5
    const-string v0, "http://110.40.45.59:82/HttpApiGb.ashx"

    sput-object v0, Lcom/example/keyverificationonline/Config;->BASE_URL:Ljava/lang/String;

    .line 6
    const-string v0, "0"

    sput-object v0, Lcom/example/keyverificationonline/Config;->PASSWORD:Ljava/lang/String;

    .line 7
    const-string v0, "4"

    sput-object v0, Lcom/example/keyverificationonline/Config;->APP_VERSION:Ljava/lang/String;

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 3
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.class public final enum Lokhttp366/logging/HttpLoggingInterceptor$Level;
.super Ljava/lang/Enum;
.source "HttpLoggingInterceptor.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/logging/HttpLoggingInterceptor;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x4019
    name = "Level"
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Enum<",
        "Lokhttp366/logging/HttpLoggingInterceptor$Level;",
        ">;"
    }
.end annotation


# static fields
.field private static final synthetic $VALUES:[Lokhttp366/logging/HttpLoggingInterceptor$Level;

.field public static final enum BASIC:Lokhttp366/logging/HttpLoggingInterceptor$Level;

.field public static final enum BODY:Lokhttp366/logging/HttpLoggingInterceptor$Level;

.field public static final enum HEADERS:Lokhttp366/logging/HttpLoggingInterceptor$Level;

.field public static final enum NONE:Lokhttp366/logging/HttpLoggingInterceptor$Level;


# direct methods
.method static constructor <clinit>()V
    .locals 6

    .line 54
    new-instance v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;

    const-string v1, "NONE"

    const/4 v2, 0x0

    invoke-direct {v0, v1, v2}, Lokhttp366/logging/HttpLoggingInterceptor$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;->NONE:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    .line 65
    new-instance v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;

    const-string v1, "BASIC"

    const/4 v3, 0x1

    invoke-direct {v0, v1, v3}, Lokhttp366/logging/HttpLoggingInterceptor$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;->BASIC:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    .line 83
    new-instance v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;

    const-string v1, "HEADERS"

    const/4 v4, 0x2

    invoke-direct {v0, v1, v4}, Lokhttp366/logging/HttpLoggingInterceptor$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;->HEADERS:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    .line 105
    new-instance v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;

    const-string v1, "BODY"

    const/4 v5, 0x3

    invoke-direct {v0, v1, v5}, Lokhttp366/logging/HttpLoggingInterceptor$Level;-><init>(Ljava/lang/String;I)V

    sput-object v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;->BODY:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    .line 52
    const/4 v0, 0x4

    new-array v0, v0, [Lokhttp366/logging/HttpLoggingInterceptor$Level;

    sget-object v1, Lokhttp366/logging/HttpLoggingInterceptor$Level;->NONE:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    aput-object v1, v0, v2

    sget-object v1, Lokhttp366/logging/HttpLoggingInterceptor$Level;->BASIC:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    aput-object v1, v0, v3

    sget-object v1, Lokhttp366/logging/HttpLoggingInterceptor$Level;->HEADERS:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    aput-object v1, v0, v4

    sget-object v1, Lokhttp366/logging/HttpLoggingInterceptor$Level;->BODY:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    aput-object v1, v0, v5

    sput-object v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;->$VALUES:[Lokhttp366/logging/HttpLoggingInterceptor$Level;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;I)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 52
    invoke-direct {p0, p1, p2}, Ljava/lang/Enum;-><init>(Ljava/lang/String;I)V

    return-void
.end method

.method public static valueOf(Ljava/lang/String;)Lokhttp366/logging/HttpLoggingInterceptor$Level;
    .locals 1
    .param p0, "name"    # Ljava/lang/String;

    .line 52
    const-class v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;

    invoke-static {v0, p0}, Ljava/lang/Enum;->valueOf(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Enum;

    move-result-object v0

    check-cast v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;

    return-object v0
.end method

.method public static values()[Lokhttp366/logging/HttpLoggingInterceptor$Level;
    .locals 1

    .line 52
    sget-object v0, Lokhttp366/logging/HttpLoggingInterceptor$Level;->$VALUES:[Lokhttp366/logging/HttpLoggingInterceptor$Level;

    invoke-virtual {v0}, [Lokhttp366/logging/HttpLoggingInterceptor$Level;->clone()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, [Lokhttp366/logging/HttpLoggingInterceptor$Level;

    return-object v0
.end method

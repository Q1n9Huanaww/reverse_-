.class public Lcom/example/keyverificationonline/DialogUtil;
.super Ljava/lang/Object;
.source "DialogUtil.java"


# static fields
.field private static accountId:Ljava/lang/String;

.field private static apiService:Lcom/example/keyverificationonline/ApiService;

.field private static appVersion:Ljava/lang/String;

.field private static channelId:Ljava/lang/String;

.field private static checkHandler:Landroid/os/Handler;

.field private static checkInterval:J

.field private static checkRunnable:Ljava/lang/Runnable;

.field public static conf:Ljava/util/Properties;

.field private static deviceID:Ljava/lang/String;

.field private static dialog:Landroid/app/AlertDialog;

.field private static dialogView:Landroid/view/View;

.field private static downloadState:Z

.field private static expireDate:Ljava/lang/String;

.field private static httpSign:Ljava/lang/String;

.field private static identifierCode:Ljava/lang/String;

.field public static localConfig:Ljava/lang/String;

.field private static noticeMsg:Ljava/lang/String;

.field private static randomID:I

.field private static sp:Landroid/content/SharedPreferences;


# direct methods
.method static constructor <clinit>()V
    .locals 4

    .line 51
    const/4 v0, 0x0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    .line 52
    const-string v1, ""

    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->accountId:Ljava/lang/String;

    .line 53
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->expireDate:Ljava/lang/String;

    .line 54
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->channelId:Ljava/lang/String;

    .line 55
    new-instance v2, Ljava/util/Random;

    invoke-direct {v2}, Ljava/util/Random;-><init>()V

    const v3, 0xf423f

    invoke-virtual {v2, v3}, Ljava/util/Random;->nextInt(I)I

    move-result v2

    add-int/lit8 v2, v2, 0x1

    sput v2, Lcom/example/keyverificationonline/DialogUtil;->randomID:I

    .line 60
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->noticeMsg:Ljava/lang/String;

    .line 61
    new-instance v2, Landroid/os/Handler;

    invoke-direct {v2}, Landroid/os/Handler;-><init>()V

    sput-object v2, Lcom/example/keyverificationonline/DialogUtil;->checkHandler:Landroid/os/Handler;

    .line 62
    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkRunnable:Ljava/lang/Runnable;

    .line 65
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->localConfig:Ljava/lang/String;

    .line 69
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->identifierCode:Ljava/lang/String;

    .line 70
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->httpSign:Ljava/lang/String;

    .line 71
    sput-object v1, Lcom/example/keyverificationonline/DialogUtil;->appVersion:Ljava/lang/String;

    .line 72
    const-wide/32 v0, 0x927c0

    sput-wide v0, Lcom/example/keyverificationonline/DialogUtil;->checkInterval:J

    .line 74
    const/4 v0, 0x0

    sput-boolean v0, Lcom/example/keyverificationonline/DialogUtil;->downloadState:Z

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .line 48
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic access$002(Ljava/lang/String;)Ljava/lang/String;
    .locals 0
    .param p0, "x0"    # Ljava/lang/String;

    .line 48
    sput-object p0, Lcom/example/keyverificationonline/DialogUtil;->noticeMsg:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$100(Ljava/lang/String;Landroid/content/Context;)V
    .locals 0
    .param p0, "x0"    # Ljava/lang/String;
    .param p1, "x1"    # Landroid/content/Context;

    .line 48
    invoke-static {p0, p1}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    return-void
.end method

.method static synthetic access$1000()J
    .locals 2

    .line 48
    sget-wide v0, Lcom/example/keyverificationonline/DialogUtil;->checkInterval:J

    return-wide v0
.end method

.method static synthetic access$1100()Landroid/os/Handler;
    .locals 1

    .line 48
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkHandler:Landroid/os/Handler;

    return-object v0
.end method

.method static synthetic access$1200()V
    .locals 0

    .line 48
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->stopCheck()V

    return-void
.end method

.method static synthetic access$1300(Landroid/content/Context;Ljava/lang/String;)V
    .locals 0
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .line 48
    invoke-static {p0, p1}, Lcom/example/keyverificationonline/DialogUtil;->installApk(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$200(Ljava/lang/String;)Z
    .locals 1
    .param p0, "x0"    # Ljava/lang/String;

    .line 48
    invoke-static {p0}, Lcom/example/keyverificationonline/DialogUtil;->checkSystemDate(Ljava/lang/String;)Z

    move-result v0

    return v0
.end method

.method static synthetic access$300()Landroid/content/SharedPreferences;
    .locals 1

    .line 48
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->sp:Landroid/content/SharedPreferences;

    return-object v0
.end method

.method static synthetic access$402(Ljava/lang/String;)Ljava/lang/String;
    .locals 0
    .param p0, "x0"    # Ljava/lang/String;

    .line 48
    sput-object p0, Lcom/example/keyverificationonline/DialogUtil;->accountId:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$500()Ljava/lang/String;
    .locals 1

    .line 48
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->expireDate:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic access$502(Ljava/lang/String;)Ljava/lang/String;
    .locals 0
    .param p0, "x0"    # Ljava/lang/String;

    .line 48
    sput-object p0, Lcom/example/keyverificationonline/DialogUtil;->expireDate:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$602(Ljava/lang/String;)Ljava/lang/String;
    .locals 0
    .param p0, "x0"    # Ljava/lang/String;

    .line 48
    sput-object p0, Lcom/example/keyverificationonline/DialogUtil;->channelId:Ljava/lang/String;

    return-object p0
.end method

.method static synthetic access$700()Landroid/app/AlertDialog;
    .locals 1

    .line 48
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->dialog:Landroid/app/AlertDialog;

    return-object v0
.end method

.method static synthetic access$800(Landroid/content/Context;Ljava/lang/String;)V
    .locals 0
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .line 48
    invoke-static {p0, p1}, Lcom/example/keyverificationonline/DialogUtil;->startCheck(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method static synthetic access$900(Landroid/content/Context;Ljava/lang/String;)V
    .locals 0
    .param p0, "x0"    # Landroid/content/Context;
    .param p1, "x1"    # Ljava/lang/String;

    .line 48
    invoke-static {p0, p1}, Lcom/example/keyverificationonline/DialogUtil;->checkExpireDate(Landroid/content/Context;Ljava/lang/String;)V

    return-void
.end method

.method private static checkExpireDate(Landroid/content/Context;Ljava/lang/String;)V
    .locals 4
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "activeCode"    # Ljava/lang/String;

    .line 480
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->accountId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget v2, Lcom/example/keyverificationonline/DialogUtil;->randomID:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/DialogUtil;->identifierCode:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->channelId:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 481
    .local v0, "data":Ljava/lang/String;
    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->apiService:Lcom/example/keyverificationonline/ApiService;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v3, Lcom/example/keyverificationonline/Config;->BASE_URL:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "?action=CheckTime&data="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v2}, Lcom/example/keyverificationonline/ApiService;->checkExpire(Ljava/lang/String;)Lretrofit266/Call;

    move-result-object v1

    .line 482
    .local v1, "call":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    new-instance v2, Lcom/example/keyverificationonline/DialogUtil$6;

    invoke-direct {v2, p0}, Lcom/example/keyverificationonline/DialogUtil$6;-><init>(Landroid/content/Context;)V

    invoke-interface {v1, v2}, Lretrofit266/Call;->enqueue(Lretrofit266/Callback;)V

    .line 553
    return-void
.end method

.method private static checkSystemDate(Ljava/lang/String;)Z
    .locals 8
    .param p0, "currentDateString"    # Ljava/lang/String;

    .line 611
    new-instance v0, Ljava/text/SimpleDateFormat;

    const-string v1, "yyyy-MM-dd HH:mm:ss"

    invoke-direct {v0, v1}, Ljava/text/SimpleDateFormat;-><init>(Ljava/lang/String;)V

    .line 612
    .local v0, "sdf":Ljava/text/SimpleDateFormat;
    invoke-static {}, Ljava/util/Calendar;->getInstance()Ljava/util/Calendar;

    move-result-object v1

    invoke-virtual {v1}, Ljava/util/Calendar;->getTimeInMillis()J

    move-result-wide v1

    .line 614
    .local v1, "local":J
    :try_start_0
    invoke-virtual {v0, p0}, Ljava/text/SimpleDateFormat;->parse(Ljava/lang/String;)Ljava/util/Date;

    move-result-object v3

    invoke-virtual {v3}, Ljava/util/Date;->getTime()J

    move-result-wide v3

    sub-long/2addr v3, v1

    invoke-static {v3, v4}, Ljava/lang/Math;->abs(J)J

    move-result-wide v3
    :try_end_0
    .catch Ljava/text/ParseException; {:try_start_0 .. :try_end_0} :catch_0

    const-wide/32 v5, 0x1d4c0

    cmp-long v7, v3, v5

    if-ltz v7, :cond_0

    const/4 v3, 0x1

    goto :goto_0

    :cond_0
    const/4 v3, 0x0

    :goto_0
    return v3

    .line 615
    :catch_0
    move-exception v3

    .line 616
    .local v3, "e":Ljava/text/ParseException;
    new-instance v4, Ljava/lang/RuntimeException;

    invoke-direct {v4, v3}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v4
.end method

.method private static crashReport(Ljava/lang/String;)V
    .locals 1
    .param p0, "message"    # Ljava/lang/String;

    .line 640
    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v0

    .line 644
    .local v0, "version":Ljava/lang/String;
    return-void
.end method

.method private static fullscreenDialog(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Landroid/content/DialogInterface$OnClickListener;)V
    .locals 3
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "title"    # Ljava/lang/String;
    .param p2, "message"    # Ljava/lang/String;
    .param p3, "listener"    # Landroid/content/DialogInterface$OnClickListener;

    .line 625
    new-instance v0, Landroid/app/AlertDialog$Builder;

    sget v1, Lcom/example/keyverificationonline/R$style;->FULLSCREEN:I

    invoke-direct {v0, p0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;I)V

    .line 626
    invoke-virtual {v0, p1}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 627
    invoke-virtual {v0, p2}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 628
    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setCancelable(Z)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    new-instance v1, Lcom/example/keyverificationonline/DialogUtil$7;

    invoke-direct {v1, p3}, Lcom/example/keyverificationonline/DialogUtil$7;-><init>(Landroid/content/DialogInterface$OnClickListener;)V

    .line 629
    const-string v2, "\u9000\u51fa"

    invoke-virtual {v0, v2, v1}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 635
    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    .line 636
    .local v0, "dialog":Landroid/app/AlertDialog;
    invoke-virtual {v0}, Landroid/app/AlertDialog;->show()V

    .line 637
    return-void
.end method

.method private static installApk(Landroid/content/Context;Ljava/lang/String;)V
    .locals 4
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "filePath"    # Ljava/lang/String;

    .line 556
    new-instance v0, Landroid/content/Intent;

    const-string v1, "android.intent.action.VIEW"

    invoke-direct {v0, v1}, Landroid/content/Intent;-><init>(Ljava/lang/String;)V

    .line 557
    .local v0, "intent":Landroid/content/Intent;
    const/high16 v1, 0x10000000

    invoke-virtual {v0, v1}, Landroid/content/Intent;->setFlags(I)Landroid/content/Intent;

    .line 558
    const/4 v1, 0x0

    .line 559
    .local v1, "apkUri":Landroid/net/Uri;
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x1d

    if-lt v2, v3, :cond_0

    const-string v2, "content://"

    invoke-virtual {p1, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_0

    .line 560
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    goto :goto_0

    .line 561
    :cond_0
    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x18

    if-lt v2, v3, :cond_1

    .line 563
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    goto :goto_0

    .line 564
    :cond_1
    const-string v2, "file://"

    invoke-virtual {p1, v2}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v2

    if-eqz v2, :cond_2

    .line 566
    invoke-static {p1}, Landroid/net/Uri;->parse(Ljava/lang/String;)Landroid/net/Uri;

    move-result-object v1

    .line 568
    :cond_2
    :goto_0
    const-string v2, "application/vnd.android.package-archive"

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->setDataAndType(Landroid/net/Uri;Ljava/lang/String;)Landroid/content/Intent;

    .line 569
    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Landroid/content/Intent;->addFlags(I)Landroid/content/Intent;

    .line 571
    :try_start_0
    invoke-virtual {p0, v0}, Landroid/content/Context;->startActivity(Landroid/content/Intent;)V
    :try_end_0
    .catch Landroid/content/ActivityNotFoundException; {:try_start_0 .. :try_end_0} :catch_0

    .line 575
    goto :goto_1

    .line 572
    :catch_0
    move-exception v2

    .line 573
    .local v2, "e":Landroid/content/ActivityNotFoundException;
    invoke-virtual {v2}, Landroid/content/ActivityNotFoundException;->printStackTrace()V

    .line 576
    .end local v2    # "e":Landroid/content/ActivityNotFoundException;
    :goto_1
    return-void
.end method

.method static synthetic lambda$updateDialog$3(Landroid/content/Context;Landroid/widget/LinearLayout;Landroid/widget/ProgressBar;Landroid/widget/TextView;Ljava/lang/String;Landroid/view/View;)V
    .locals 3
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "progressLayout"    # Landroid/widget/LinearLayout;
    .param p2, "progressBar"    # Landroid/widget/ProgressBar;
    .param p3, "progressTextView"    # Landroid/widget/TextView;
    .param p4, "updateUrl"    # Ljava/lang/String;
    .param p5, "v"    # Landroid/view/View;

    .line 377
    const/16 v0, 0x17

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    const/4 v2, 0x0

    if-gt v0, v1, :cond_0

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x1d

    if-gt v0, v1, :cond_0

    .line 379
    const-string v0, "android.permission.WRITE_EXTERNAL_STORAGE"

    invoke-static {p0, v0}, Landroid/support/v4/content/ContextCompat;->checkSelfPermission(Landroid/content/Context;Ljava/lang/String;)I

    move-result v1

    if-eqz v1, :cond_0

    .line 380
    const-string v1, "\u8bf7\u6388\u4e88\u5b58\u50a8\u6743\u9650\uff01"

    invoke-static {v1, p0}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 381
    move-object v1, p0

    check-cast v1, Landroid/app/Activity;

    filled-new-array {v0, v0}, [Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0, v2}, Landroid/support/v4/app/ActivityCompat;->requestPermissions(Landroid/app/Activity;[Ljava/lang/String;I)V

    .line 382
    return-void

    .line 386
    :cond_0
    invoke-virtual {p1, v2}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 387
    new-instance v0, Lcom/example/keyverificationonline/DownloadFileTask;

    new-instance v1, Lcom/example/keyverificationonline/DialogUtil$3;

    invoke-direct {v1, p2, p3, p0}, Lcom/example/keyverificationonline/DialogUtil$3;-><init>(Landroid/widget/ProgressBar;Landroid/widget/TextView;Landroid/content/Context;)V

    invoke-direct {v0, p0, v1}, Lcom/example/keyverificationonline/DownloadFileTask;-><init>(Landroid/content/Context;Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;)V

    filled-new-array {p4}, [Ljava/lang/String;

    move-result-object v1

    .line 399
    invoke-virtual {v0, v1}, Lcom/example/keyverificationonline/DownloadFileTask;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    .line 400
    return-void
.end method

.method static synthetic lambda$verificationDialog$0(Landroid/content/Context;Landroid/view/View;)V
    .locals 1
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "v"    # Landroid/view/View;

    .line 295
    const-string v0, "test"

    .line 296
    .local v0, "trialCode":Ljava/lang/String;
    invoke-static {v0, p0}, Lcom/example/keyverificationonline/DialogUtil;->requestLogin(Ljava/lang/String;Landroid/content/Context;)V

    .line 297
    return-void
.end method

.method static synthetic lambda$verificationDialog$1(Landroid/content/Context;Landroid/view/View;)V
    .locals 1
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "v"    # Landroid/view/View;

    .line 300
    const-string v0, "\u9000\u51fa\u8f6f\u4ef6"

    invoke-static {v0, p0}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 301
    const/4 v0, 0x0

    invoke-static {v0}, Ljava/lang/System;->exit(I)V

    .line 302
    return-void
.end method

.method static synthetic lambda$verificationDialog$2(Landroid/widget/EditText;Landroid/content/Context;Landroid/view/View;)V
    .locals 2
    .param p0, "codeEditText"    # Landroid/widget/EditText;
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "v"    # Landroid/view/View;

    .line 305
    invoke-virtual {p0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v0

    .line 306
    .local v0, "activeCode":Ljava/lang/String;
    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    .line 307
    const-string v1, "\u6ce8\u518c\u7801\u4e0d\u80fd\u4e3a\u7a7a\uff01"

    invoke-static {v1, p1}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 308
    return-void

    .line 310
    :cond_0
    const-string v1, " "

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-eqz v1, :cond_1

    .line 311
    const-string v1, "\u6ce8\u518c\u7801\u4e0d\u5b58\u5728\uff01"

    invoke-static {v1, p1}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 312
    return-void

    .line 314
    :cond_1
    invoke-static {v0, p1}, Lcom/example/keyverificationonline/DialogUtil;->requestLogin(Ljava/lang/String;Landroid/content/Context;)V

    .line 315
    return-void
.end method

.method public static notice(Landroid/content/Context;)V
    .locals 4
    .param p0, "context"    # Landroid/content/Context;

    .line 146
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->identifierCode:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->httpSign:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 147
    .local v0, "data":Ljava/lang/String;
    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->apiService:Lcom/example/keyverificationonline/ApiService;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v3, Lcom/example/keyverificationonline/Config;->BASE_URL:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "?action=GetInfo&data="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v2}, Lcom/example/keyverificationonline/ApiService;->userLogin(Ljava/lang/String;)Lretrofit266/Call;

    move-result-object v1

    .line 148
    .local v1, "call":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    new-instance v2, Lcom/example/keyverificationonline/DialogUtil$2;

    invoke-direct {v2, p0}, Lcom/example/keyverificationonline/DialogUtil$2;-><init>(Landroid/content/Context;)V

    invoke-interface {v1, v2}, Lretrofit266/Call;->enqueue(Lretrofit266/Callback;)V

    .line 193
    return-void
.end method

.method private static requestLogin(Ljava/lang/String;Landroid/content/Context;)V
    .locals 4
    .param p0, "activeCode"    # Ljava/lang/String;
    .param p1, "context"    # Landroid/content/Context;

    .line 404
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "\n"

    const-string v2, ""

    invoke-virtual {p0, v1, v2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v3, " "

    invoke-virtual {v1, v3, v2}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/Config;->PASSWORD:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/DialogUtil;->identifierCode:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v2, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget v2, Lcom/example/keyverificationonline/DialogUtil;->randomID:I

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->httpSign:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 405
    .local v0, "data":Ljava/lang/String;
    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->apiService:Lcom/example/keyverificationonline/ApiService;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v3, Lcom/example/keyverificationonline/Config;->BASE_URL:Ljava/lang/String;

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "?action=UserLogin&data="

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-interface {v1, v2}, Lcom/example/keyverificationonline/ApiService;->userLogin(Ljava/lang/String;)Lretrofit266/Call;

    move-result-object v1

    .line 406
    .local v1, "call":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    new-instance v2, Lcom/example/keyverificationonline/DialogUtil$4;

    invoke-direct {v2, p1, p0}, Lcom/example/keyverificationonline/DialogUtil$4;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    invoke-interface {v1, v2}, Lretrofit266/Call;->enqueue(Lretrofit266/Callback;)V

    .line 456
    return-void
.end method

.method public static show(Landroid/content/Context;)V
    .locals 6
    .param p0, "context"    # Landroid/content/Context;

    .line 81
    :try_start_0
    new-instance v0, Ljava/io/InputStreamReader;

    .line 82
    invoke-virtual {p0}, Landroid/content/Context;->getResources()Landroid/content/res/Resources;

    move-result-object v1

    invoke-virtual {v1}, Landroid/content/res/Resources;->getAssets()Landroid/content/res/AssetManager;

    move-result-object v1

    const-string v2, "Jq"

    invoke-virtual {v1, v2}, Landroid/content/res/AssetManager;->open(Ljava/lang/String;)Ljava/io/InputStream;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/io/InputStreamReader;-><init>(Ljava/io/InputStream;)V

    .line 84
    .local v0, "reader":Ljava/io/InputStreamReader;
    new-instance v1, Ljava/io/BufferedReader;

    invoke-direct {v1, v0}, Ljava/io/BufferedReader;-><init>(Ljava/io/Reader;)V

    .line 85
    .local v1, "bufferedReader":Ljava/io/BufferedReader;
    const-string v2, ""

    .line 86
    .local v2, "line":Ljava/lang/String;
    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    .line 87
    .local v3, "result":Ljava/lang/StringBuilder;
    :goto_0
    invoke-virtual {v1}, Ljava/io/BufferedReader;->readLine()Ljava/lang/String;

    move-result-object v4

    move-object v2, v4

    if-eqz v4, :cond_0

    .line 88
    invoke-virtual {v3, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    goto :goto_0

    .line 90
    :cond_0
    invoke-virtual {v1}, Ljava/io/BufferedReader;->close()V

    .line 91
    invoke-virtual {v0}, Ljava/io/InputStreamReader;->close()V

    .line 92
    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    sput-object v4, Lcom/example/keyverificationonline/DialogUtil;->localConfig:Ljava/lang/String;
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    .line 96
    .end local v0    # "reader":Ljava/io/InputStreamReader;
    .end local v1    # "bufferedReader":Ljava/io/BufferedReader;
    .end local v2    # "line":Ljava/lang/String;
    .end local v3    # "result":Ljava/lang/StringBuilder;
    goto :goto_1

    .line 93
    :catch_0
    move-exception v0

    .line 94
    .local v0, "e":Ljava/lang/Exception;
    invoke-virtual {v0}, Ljava/lang/Exception;->printStackTrace()V

    .line 95
    const-string v1, "\u521d\u59cb\u5316\u5931\u8d25\uff01"

    invoke-static {v1, p0}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 98
    .end local v0    # "e":Ljava/lang/Exception;
    :goto_1
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    const/4 v1, 0x0

    if-nez v0, :cond_2

    .line 99
    new-instance v0, Ljava/util/Properties;

    invoke-direct {v0}, Ljava/util/Properties;-><init>()V

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    .line 101
    :try_start_1
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    new-instance v2, Ljava/io/ByteArrayInputStream;

    sget-object v3, Lcom/example/keyverificationonline/DialogUtil;->localConfig:Ljava/lang/String;

    .line 102
    invoke-virtual {v3}, Ljava/lang/String;->getBytes()[B

    move-result-object v3

    invoke-static {v3, v1}, Landroid/util/Base64;->decode([BI)[B

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/io/ByteArrayInputStream;-><init>([B)V

    .line 101
    invoke-virtual {v0, v2}, Ljava/util/Properties;->load(Ljava/io/InputStream;)V

    .line 104
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    const-string v2, "Code"

    invoke-virtual {v0, v2}, Ljava/util/Properties;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->identifierCode:Ljava/lang/String;

    .line 105
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    const-string v2, "Sign"

    invoke-virtual {v0, v2}, Ljava/util/Properties;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->httpSign:Ljava/lang/String;

    .line 106
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    const-string v2, "Ver"

    invoke-virtual {v0, v2}, Ljava/util/Properties;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->appVersion:Ljava/lang/String;

    .line 107
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->conf:Ljava/util/Properties;

    const-string v2, "Time"

    invoke-virtual {v0, v2}, Ljava/util/Properties;->getProperty(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 108
    .local v0, "checkIntervalString":Ljava/lang/String;
    if-eqz v0, :cond_1

    invoke-virtual {v0}, Ljava/lang/String;->isEmpty()Z

    move-result v2

    if-nez v2, :cond_1

    .line 109
    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v2

    int-to-long v2, v2

    const-wide/16 v4, 0x3e8

    mul-long v2, v2, v4

    sput-wide v2, Lcom/example/keyverificationonline/DialogUtil;->checkInterval:J
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_1

    .line 111
    :cond_1
    nop

    .line 114
    .end local v0    # "checkIntervalString":Ljava/lang/String;
    goto :goto_2

    .line 112
    :catch_1
    move-exception v0

    .line 113
    .local v0, "e":Ljava/io/IOException;
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "\u521d\u59cb\u5316\u5931\u8d25\uff1a"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v0}, Ljava/io/IOException;->getMessage()Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-static {v2, p0}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 117
    .end local v0    # "e":Ljava/io/IOException;
    :cond_2
    :goto_2
    const-string v0, "config"

    invoke-virtual {p0, v0, v1}, Landroid/content/Context;->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;

    move-result-object v0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->sp:Landroid/content/SharedPreferences;

    .line 118
    invoke-static {p0}, Lcom/example/keyverificationonline/DeviceUtil;->getDeviceID(Landroid/content/Context;)Ljava/lang/String;

    move-result-object v0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    .line 119
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->deviceID:Ljava/lang/String;

    if-nez v0, :cond_3

    .line 120
    const-string v0, "\u673a\u5668\u7801\u83b7\u53d6\u5931\u8d25\uff01"

    invoke-static {v0, p0}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 121
    invoke-static {v1}, Ljava/lang/System;->exit(I)V

    .line 127
    :cond_3
    new-instance v0, Lokhttp366/logging/HttpLoggingInterceptor;

    new-instance v1, Lcom/example/keyverificationonline/DialogUtil$1;

    invoke-direct {v1}, Lcom/example/keyverificationonline/DialogUtil$1;-><init>()V

    invoke-direct {v0, v1}, Lokhttp366/logging/HttpLoggingInterceptor;-><init>(Lokhttp366/logging/HttpLoggingInterceptor$Logger;)V

    .line 133
    .local v0, "httpLoggingInterceptor":Lokhttp366/logging/HttpLoggingInterceptor;
    sget-object v1, Lokhttp366/logging/HttpLoggingInterceptor$Level;->BODY:Lokhttp366/logging/HttpLoggingInterceptor$Level;

    invoke-virtual {v0, v1}, Lokhttp366/logging/HttpLoggingInterceptor;->setLevel(Lokhttp366/logging/HttpLoggingInterceptor$Level;)Lokhttp366/logging/HttpLoggingInterceptor;

    .line 134
    new-instance v1, Lokhttp366/OkHttpClient$Builder;

    invoke-direct {v1}, Lokhttp366/OkHttpClient$Builder;-><init>()V

    .line 135
    invoke-virtual {v1, v0}, Lokhttp366/OkHttpClient$Builder;->addInterceptor(Lokhttp366/Interceptor;)Lokhttp366/OkHttpClient$Builder;

    move-result-object v1

    .line 136
    invoke-virtual {v1}, Lokhttp366/OkHttpClient$Builder;->build()Lokhttp366/OkHttpClient;

    move-result-object v1

    .line 137
    .local v1, "client":Lokhttp366/OkHttpClient;
    new-instance v2, Lretrofit266/Retrofit$Builder;

    invoke-direct {v2}, Lretrofit266/Retrofit$Builder;-><init>()V

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    sget-object v4, Lcom/example/keyverificationonline/Config;->BASE_URL:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, "/"

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    .line 138
    invoke-virtual {v2, v3}, Lretrofit266/Retrofit$Builder;->baseUrl(Ljava/lang/String;)Lretrofit266/Retrofit$Builder;

    move-result-object v2

    .line 139
    invoke-virtual {v2, v1}, Lretrofit266/Retrofit$Builder;->client(Lokhttp366/OkHttpClient;)Lretrofit266/Retrofit$Builder;

    move-result-object v2

    .line 140
    invoke-virtual {v2}, Lretrofit266/Retrofit$Builder;->build()Lretrofit266/Retrofit;

    move-result-object v2

    .line 141
    .local v2, "retrofit":Lretrofit266/Retrofit;
    const-class v3, Lcom/example/keyverificationonline/ApiService;

    invoke-virtual {v2, v3}, Lretrofit266/Retrofit;->create(Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/example/keyverificationonline/ApiService;

    sput-object v3, Lcom/example/keyverificationonline/DialogUtil;->apiService:Lcom/example/keyverificationonline/ApiService;

    .line 142
    invoke-static {p0}, Lcom/example/keyverificationonline/DialogUtil;->notice(Landroid/content/Context;)V

    .line 143
    return-void
.end method

.method private static showToast(Ljava/lang/String;Landroid/content/Context;)V
    .locals 1
    .param p0, "msg"    # Ljava/lang/String;
    .param p1, "context"    # Landroid/content/Context;

    .line 621
    const/4 v0, 0x0

    invoke-static {p1, p0, v0}, Landroid/widget/Toast;->makeText(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;

    move-result-object v0

    invoke-virtual {v0}, Landroid/widget/Toast;->show()V

    .line 622
    return-void
.end method

.method private static startCheck(Landroid/content/Context;Ljava/lang/String;)V
    .locals 4
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "activeCode"    # Ljava/lang/String;

    .line 459
    new-instance v0, Lcom/example/keyverificationonline/DialogUtil$5;

    invoke-direct {v0, p0, p1}, Lcom/example/keyverificationonline/DialogUtil$5;-><init>(Landroid/content/Context;Ljava/lang/String;)V

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkRunnable:Ljava/lang/Runnable;

    .line 469
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkHandler:Landroid/os/Handler;

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->checkRunnable:Ljava/lang/Runnable;

    sget-wide v2, Lcom/example/keyverificationonline/DialogUtil;->checkInterval:J

    invoke-virtual {v0, v1, v2, v3}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 470
    return-void
.end method

.method private static stopCheck()V
    .locals 2

    .line 473
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkRunnable:Ljava/lang/Runnable;

    if-eqz v0, :cond_0

    .line 474
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkHandler:Landroid/os/Handler;

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->checkRunnable:Ljava/lang/Runnable;

    invoke-virtual {v0, v1}, Landroid/os/Handler;->removeCallbacks(Ljava/lang/Runnable;)V

    .line 475
    const/4 v0, 0x0

    sput-object v0, Lcom/example/keyverificationonline/DialogUtil;->checkRunnable:Ljava/lang/Runnable;

    .line 477
    :cond_0
    return-void
.end method

.method public static updateDialog(Landroid/content/Context;ZLjava/lang/String;Ljava/lang/String;)V
    .locals 18
    .param p0, "context"    # Landroid/content/Context;
    .param p1, "isFocusUpdate"    # Z
    .param p2, "updateUrl"    # Ljava/lang/String;
    .param p3, "cloudAppVersion"    # Ljava/lang/String;

    .line 320
    move-object/from16 v6, p0

    move-object/from16 v7, p3

    new-instance v0, Ljava/io/File;

    sget-object v1, Landroid/os/Environment;->DIRECTORY_DOWNLOADS:Ljava/lang/String;

    invoke-static {v1}, Landroid/os/Environment;->getExternalStoragePublicDirectory(Ljava/lang/String;)Ljava/io/File;

    move-result-object v1

    const-string v2, "app_update.apk"

    invoke-direct {v0, v1, v2}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    move-object v8, v0

    .line 321
    .local v8, "file":Ljava/io/File;
    invoke-virtual {v8}, Ljava/io/File;->exists()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 322
    invoke-virtual {v8}, Ljava/io/File;->delete()Z

    .line 324
    :cond_0
    if-eqz p2, :cond_4

    invoke-virtual/range {p2 .. p2}, Ljava/lang/String;->isEmpty()Z

    move-result v0

    if-eqz v0, :cond_1

    move-object/from16 v16, v8

    goto/16 :goto_0

    .line 328
    :cond_1
    sget-object v0, Lcom/example/keyverificationonline/DialogUtil;->appVersion:Ljava/lang/String;

    invoke-virtual {v0, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    .line 329
    return-void

    .line 332
    :cond_2
    new-instance v0, Landroid/widget/LinearLayout;

    invoke-direct {v0, v6}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    move-object v9, v0

    .line 333
    .local v9, "layout":Landroid/widget/LinearLayout;
    const/4 v0, 0x1

    invoke-virtual {v9, v0}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 334
    const/16 v0, 0x32

    const/16 v1, 0x28

    invoke-virtual {v9, v0, v1, v0, v1}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 337
    new-instance v0, Landroid/widget/TextView;

    invoke-direct {v0, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    move-object v10, v0

    .line 338
    .local v10, "messageTextView":Landroid/widget/TextView;
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    const-string v1, "\u5f53\u524d\u7248\u672c "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->appVersion:Ljava/lang/String;

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v1, "\uff0c\u65b0\u7248\u672c "

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v10, v0}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 339
    const/high16 v0, 0x41800000    # 16.0f

    invoke-virtual {v10, v0}, Landroid/widget/TextView;->setTextSize(F)V

    .line 340
    const/16 v0, 0x1e

    const/4 v1, 0x0

    invoke-virtual {v10, v1, v1, v1, v0}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 341
    invoke-virtual {v9, v10}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 344
    new-instance v0, Landroid/widget/LinearLayout;

    invoke-direct {v0, v6}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    move-object v11, v0

    .line 345
    .local v11, "progressLayout":Landroid/widget/LinearLayout;
    invoke-virtual {v11, v1}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 346
    const/16 v0, 0x10

    invoke-virtual {v11, v0, v0, v0, v0}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 349
    new-instance v0, Landroid/widget/ProgressBar;

    const v2, 0x1010078

    const/4 v3, 0x0

    invoke-direct {v0, v6, v3, v2}, Landroid/widget/ProgressBar;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    move-object v12, v0

    .line 350
    .local v12, "progressBar":Landroid/widget/ProgressBar;
    const/16 v0, 0x64

    invoke-virtual {v12, v0}, Landroid/widget/ProgressBar;->setMax(I)V

    .line 351
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    const/high16 v2, 0x3f800000    # 1.0f

    const/4 v4, -0x2

    invoke-direct {v0, v1, v4, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    move-object v13, v0

    .line 352
    .local v13, "progressBarParams":Landroid/widget/LinearLayout$LayoutParams;
    invoke-virtual {v12, v13}, Landroid/widget/ProgressBar;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 353
    invoke-virtual {v11, v12}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 356
    new-instance v0, Landroid/widget/TextView;

    invoke-direct {v0, v6}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    move-object v14, v0

    .line 357
    .local v14, "progressTextView":Landroid/widget/TextView;
    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v0, v4, v4}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v14, v0}, Landroid/widget/TextView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 358
    const/16 v0, 0x8

    invoke-virtual {v14, v0, v1, v1, v1}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 359
    const-string v2, "0%"

    invoke-virtual {v14, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 360
    invoke-virtual {v11, v14}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 362
    invoke-virtual {v11, v0}, Landroid/widget/LinearLayout;->setVisibility(I)V

    .line 363
    invoke-virtual {v9, v11}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 366
    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-direct {v0, v6}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 367
    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setCancelable(Z)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 368
    const-string v1, "\u63d0\u793a"

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setTitle(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 369
    invoke-virtual {v0, v9}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    move-result-object v0

    .line 370
    const-string v1, "\u66f4\u65b0"

    invoke-virtual {v0, v1, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    move-result-object v15

    .line 371
    .local v15, "builder":Landroid/app/AlertDialog$Builder;
    if-nez p1, :cond_3

    .line 372
    const-string v0, "\u6682\u4e0d\u66f4\u65b0"

    invoke-virtual {v15, v0, v3}, Landroid/app/AlertDialog$Builder;->setNegativeButton(Ljava/lang/CharSequence;Landroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    .line 374
    :cond_3
    invoke-virtual {v15}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    move-result-object v5

    .line 375
    .local v5, "updateDialog":Landroid/app/AlertDialog;
    const/4 v0, -0x1

    invoke-virtual {v5, v0}, Landroid/app/AlertDialog;->getButton(I)Landroid/widget/Button;

    move-result-object v4

    new-instance v3, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda3;

    move-object v0, v3

    move-object/from16 v1, p0

    move-object v2, v11

    move-object v7, v3

    move-object v3, v12

    move-object/from16 v16, v8

    move-object v8, v4

    .end local v8    # "file":Ljava/io/File;
    .local v16, "file":Ljava/io/File;
    move-object v4, v14

    move-object/from16 v17, v5

    .end local v5    # "updateDialog":Landroid/app/AlertDialog;
    .local v17, "updateDialog":Landroid/app/AlertDialog;
    move-object/from16 v5, p2

    invoke-direct/range {v0 .. v5}, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda3;-><init>(Landroid/content/Context;Landroid/widget/LinearLayout;Landroid/widget/ProgressBar;Landroid/widget/TextView;Ljava/lang/String;)V

    invoke-virtual {v8, v7}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 401
    return-void

    .line 324
    .end local v9    # "layout":Landroid/widget/LinearLayout;
    .end local v10    # "messageTextView":Landroid/widget/TextView;
    .end local v11    # "progressLayout":Landroid/widget/LinearLayout;
    .end local v12    # "progressBar":Landroid/widget/ProgressBar;
    .end local v13    # "progressBarParams":Landroid/widget/LinearLayout$LayoutParams;
    .end local v14    # "progressTextView":Landroid/widget/TextView;
    .end local v15    # "builder":Landroid/app/AlertDialog$Builder;
    .end local v16    # "file":Ljava/io/File;
    .end local v17    # "updateDialog":Landroid/app/AlertDialog;
    .restart local v8    # "file":Ljava/io/File;
    :cond_4
    move-object/from16 v16, v8

    .line 325
    .end local v8    # "file":Ljava/io/File;
    .restart local v16    # "file":Ljava/io/File;
    :goto_0
    const-string v0, "\u66f4\u65b0\u94fe\u63a5\u65e0\u6548"

    invoke-static {v0, v6}, Lcom/example/keyverificationonline/DialogUtil;->showToast(Ljava/lang/String;Landroid/content/Context;)V

    .line 326
    return-void
.end method

.method public static verificationDialog(Landroid/content/Context;)V
    .locals 18
    .param p0, "context"    # Landroid/content/Context;

    .line 200
    move-object/from16 v0, p0

    sget-object v1, Lcom/example/keyverificationonline/DialogUtil;->sp:Landroid/content/SharedPreferences;

    const-string v2, "activeCode"

    const-string v3, ""

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v1

    .line 201
    .local v1, "code":Ljava/lang/String;
    new-instance v2, Landroid/widget/LinearLayout;

    invoke-direct {v2, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 202
    .local v2, "rootContainer":Landroid/widget/LinearLayout;
    const/4 v3, 0x1

    invoke-virtual {v2, v3}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 203
    new-instance v4, Landroid/widget/LinearLayout$LayoutParams;

    const/4 v5, -0x1

    const/4 v6, -0x2

    invoke-direct {v4, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v2, v4}, Landroid/widget/LinearLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 207
    const/4 v4, 0x0

    const/16 v7, 0x32

    invoke-virtual {v2, v4, v7, v4, v4}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 210
    new-instance v8, Landroid/widget/LinearLayout;

    invoke-direct {v8, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 211
    .local v8, "codeContainer":Landroid/widget/LinearLayout;
    invoke-virtual {v8, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 212
    new-instance v9, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v9, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v8, v9}, Landroid/widget/LinearLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 216
    invoke-virtual {v8, v7, v4, v7, v7}, Landroid/widget/LinearLayout;->setPadding(IIII)V

    .line 218
    new-instance v9, Landroid/widget/EditText;

    invoke-direct {v9, v0}, Landroid/widget/EditText;-><init>(Landroid/content/Context;)V

    .line 219
    .local v9, "codeEditText":Landroid/widget/EditText;
    new-instance v10, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v10, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v9, v10}, Landroid/widget/EditText;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 223
    invoke-virtual {v9, v3}, Landroid/widget/EditText;->setMaxLines(I)V

    .line 224
    invoke-virtual {v9, v3}, Landroid/widget/EditText;->setSingleLine(Z)V

    .line 225
    invoke-virtual {v9}, Landroid/widget/EditText;->getLayoutParams()Landroid/view/ViewGroup$LayoutParams;

    move-result-object v10

    check-cast v10, Landroid/widget/LinearLayout$LayoutParams;

    .line 226
    .local v10, "codeEditTextParams":Landroid/widget/LinearLayout$LayoutParams;
    const/high16 v11, 0x3f800000    # 1.0f

    iput v11, v10, Landroid/widget/LinearLayout$LayoutParams;->weight:F

    .line 227
    invoke-virtual {v9, v10}, Landroid/widget/EditText;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 229
    new-instance v12, Landroid/widget/Button;

    invoke-direct {v12, v0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    .line 230
    .local v12, "activeBtn":Landroid/widget/Button;
    const-string v13, "\u6fc0\u6d3b"

    invoke-virtual {v12, v13}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 231
    new-instance v13, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v13, v6, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v12, v13}, Landroid/widget/Button;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 235
    invoke-virtual {v8, v9}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 236
    invoke-virtual {v8, v12}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 238
    new-instance v13, Landroid/widget/TextView;

    invoke-direct {v13, v0}, Landroid/widget/TextView;-><init>(Landroid/content/Context;)V

    .line 239
    .local v13, "noticeMsgText":Landroid/widget/TextView;
    sget-object v14, Lcom/example/keyverificationonline/DialogUtil;->noticeMsg:Ljava/lang/String;

    invoke-virtual {v13, v14}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 240
    invoke-virtual {v13, v7, v4, v7, v7}, Landroid/widget/TextView;->setPadding(IIII)V

    .line 241
    invoke-virtual {v2, v8}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 242
    invoke-virtual {v2, v13}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 243
    invoke-virtual {v9, v1}, Landroid/widget/EditText;->setText(Ljava/lang/CharSequence;)V

    .line 246
    new-instance v7, Landroid/widget/LinearLayout;

    invoke-direct {v7, v0}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    .line 247
    .local v7, "buttonContainer":Landroid/widget/LinearLayout;
    invoke-virtual {v7, v4}, Landroid/widget/LinearLayout;->setOrientation(I)V

    .line 248
    new-instance v14, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v14, v5, v6}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    invoke-virtual {v7, v14}, Landroid/widget/LinearLayout;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 253
    new-instance v14, Landroid/view/View;

    invoke-direct {v14, v0}, Landroid/view/View;-><init>(Landroid/content/Context;)V

    .line 254
    .local v14, "verticalLine":Landroid/view/View;
    new-instance v15, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v15, v3, v5}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    move-object v3, v15

    .line 258
    .local v3, "lineParams":Landroid/widget/LinearLayout$LayoutParams;
    invoke-virtual {v14, v3}, Landroid/view/View;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 259
    const v5, -0x777778

    invoke-virtual {v14, v5}, Landroid/view/View;->setBackgroundColor(I)V

    .line 261
    new-instance v5, Landroid/widget/Button;

    invoke-direct {v5, v0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    .line 262
    .local v5, "neutralButton":Landroid/widget/Button;
    const-string v15, "\u70b9\u6211\u8bd5\u7528"

    invoke-virtual {v5, v15}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 263
    new-instance v15, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v15, v4, v6, v11}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v5, v15}, Landroid/widget/Button;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 268
    const-string v15, "#ea702e"

    invoke-static {v15}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v15

    invoke-virtual {v5, v15}, Landroid/widget/Button;->setBackgroundColor(I)V

    .line 269
    const-string v15, "#FFFFFF"

    invoke-static {v15}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v4

    invoke-virtual {v5, v4}, Landroid/widget/Button;->setTextColor(I)V

    .line 271
    new-instance v4, Landroid/widget/Button;

    invoke-direct {v4, v0}, Landroid/widget/Button;-><init>(Landroid/content/Context;)V

    .line 272
    .local v4, "positiveButton":Landroid/widget/Button;
    const-string v6, "\u9000\u51fa"

    invoke-virtual {v4, v6}, Landroid/widget/Button;->setText(Ljava/lang/CharSequence;)V

    .line 273
    new-instance v6, Landroid/widget/LinearLayout$LayoutParams;

    move-object/from16 v17, v1

    move-object/from16 v16, v3

    const/4 v1, -0x2

    const/4 v3, 0x0

    .end local v1    # "code":Ljava/lang/String;
    .end local v3    # "lineParams":Landroid/widget/LinearLayout$LayoutParams;
    .local v16, "lineParams":Landroid/widget/LinearLayout$LayoutParams;
    .local v17, "code":Ljava/lang/String;
    invoke-direct {v6, v3, v1, v11}, Landroid/widget/LinearLayout$LayoutParams;-><init>(IIF)V

    invoke-virtual {v4, v6}, Landroid/widget/Button;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    .line 278
    const-string v1, "#4b77bf"

    invoke-static {v1}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    invoke-virtual {v4, v1}, Landroid/widget/Button;->setBackgroundColor(I)V

    .line 279
    invoke-static {v15}, Landroid/graphics/Color;->parseColor(Ljava/lang/String;)I

    move-result v1

    invoke-virtual {v4, v1}, Landroid/widget/Button;->setTextColor(I)V

    .line 281
    invoke-virtual {v7, v5}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 282
    invoke-virtual {v7, v14}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 283
    invoke-virtual {v7, v4}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 284
    invoke-virtual {v2, v7}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    .line 286
    sput-object v2, Lcom/example/keyverificationonline/DialogUtil;->dialogView:Landroid/view/View;

    .line 288
    new-instance v1, Landroid/app/AlertDialog$Builder;

    invoke-direct {v1, v0}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    .line 289
    const-string v3, "\u5361\u5bc6\u6fc0\u6d3b"

    invoke-virtual {v1, v3}, Landroid/app/AlertDialog$Builder;->setMessage(Ljava/lang/CharSequence;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    .line 290
    const/4 v3, 0x0

    invoke-virtual {v1, v3}, Landroid/app/AlertDialog$Builder;->setCancelable(Z)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    sget-object v3, Lcom/example/keyverificationonline/DialogUtil;->dialogView:Landroid/view/View;

    .line 291
    invoke-virtual {v1, v3}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    move-result-object v1

    .line 292
    .local v1, "builder":Landroid/app/AlertDialog$Builder;
    invoke-virtual {v1}, Landroid/app/AlertDialog$Builder;->show()Landroid/app/AlertDialog;

    move-result-object v3

    sput-object v3, Lcom/example/keyverificationonline/DialogUtil;->dialog:Landroid/app/AlertDialog;

    .line 294
    new-instance v3, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda0;

    invoke-direct {v3, v0}, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda0;-><init>(Landroid/content/Context;)V

    invoke-virtual {v5, v3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 299
    new-instance v3, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda1;

    invoke-direct {v3, v0}, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda1;-><init>(Landroid/content/Context;)V

    invoke-virtual {v4, v3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 304
    new-instance v3, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda2;

    invoke-direct {v3, v9, v0}, Lcom/example/keyverificationonline/DialogUtil$$ExternalSyntheticLambda2;-><init>(Landroid/widget/EditText;Landroid/content/Context;)V

    invoke-virtual {v12, v3}, Landroid/widget/Button;->setOnClickListener(Landroid/view/View$OnClickListener;)V

    .line 316
    return-void
.end method

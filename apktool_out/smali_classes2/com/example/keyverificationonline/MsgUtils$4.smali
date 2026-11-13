.class Lcom/example/keyverificationonline/MsgUtils$4;
.super Ljava/util/TimerTask;
.source "MsgUtils.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/MsgUtils;->exit(Landroid/content/Context;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .locals 0

    .line 160
    iput-object p1, p0, Lcom/example/keyverificationonline/MsgUtils$4;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/util/TimerTask;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 1

    .line 164
    nop

    :goto_0
    iget-object v0, p0, Lcom/example/keyverificationonline/MsgUtils$4;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/example/keyverificationonline/MsgUtils;->exit(Landroid/content/Context;)V

    goto :goto_0
.end method

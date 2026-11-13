.class Lcom/example/keyverificationonline/DialogUtil$5;
.super Ljava/lang/Object;
.source "DialogUtil.java"

# interfaces
.implements Ljava/lang/Runnable;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/DialogUtil;->startCheck(Landroid/content/Context;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$activeCode:Ljava/lang/String;

.field final synthetic val$context:Landroid/content/Context;


# direct methods
.method constructor <init>(Landroid/content/Context;Ljava/lang/String;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 459
    iput-object p1, p0, Lcom/example/keyverificationonline/DialogUtil$5;->val$context:Landroid/content/Context;

    iput-object p2, p0, Lcom/example/keyverificationonline/DialogUtil$5;->val$activeCode:Ljava/lang/String;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    .line 465
    iget-object v0, p0, Lcom/example/keyverificationonline/DialogUtil$5;->val$context:Landroid/content/Context;

    iget-object v1, p0, Lcom/example/keyverificationonline/DialogUtil$5;->val$activeCode:Ljava/lang/String;

    invoke-static {v0, v1}, Lcom/example/keyverificationonline/DialogUtil;->access$900(Landroid/content/Context;Ljava/lang/String;)V

    .line 466
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$1100()Landroid/os/Handler;

    move-result-object v0

    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$1000()J

    move-result-wide v1

    invoke-virtual {v0, p0, v1, v2}, Landroid/os/Handler;->postDelayed(Ljava/lang/Runnable;J)Z

    .line 467
    return-void
.end method

.class Lcom/example/keyverificationonline/MsgUtils$1;
.super Landroid/os/Handler;
.source "MsgUtils.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/MsgUtils;->alert(Landroid/content/Context;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$str:Ljava/lang/String;


# direct methods
.method constructor <init>(Landroid/os/Looper;Landroid/content/Context;Ljava/lang/String;)V
    .locals 0
    .param p1, "arg0"    # Landroid/os/Looper;

    .line 53
    iput-object p2, p0, Lcom/example/keyverificationonline/MsgUtils$1;->val$context:Landroid/content/Context;

    iput-object p3, p0, Lcom/example/keyverificationonline/MsgUtils$1;->val$str:Ljava/lang/String;

    invoke-direct {p0, p1}, Landroid/os/Handler;-><init>(Landroid/os/Looper;)V

    return-void
.end method


# virtual methods
.method public handleMessage(Landroid/os/Message;)V
    .locals 6
    .param p1, "msg"    # Landroid/os/Message;

    .line 56
    iget v0, p1, Landroid/os/Message;->what:I

    if-nez v0, :cond_1

    .line 57
    invoke-static {}, Lcom/example/keyverificationonline/MsgUtils;->access$000()Landroid/widget/TextView;

    move-result-object v0

    if-eqz v0, :cond_1

    .line 58
    invoke-static {}, Lcom/example/keyverificationonline/MsgUtils;->access$100()I

    move-result v0

    if-lez v0, :cond_0

    .line 59
    invoke-static {}, Lcom/example/keyverificationonline/MsgUtils;->access$110()I

    move-result v0

    .line 60
    .local v0, "i1":I
    iget-object v1, p0, Lcom/example/keyverificationonline/MsgUtils$1;->val$context:Landroid/content/Context;

    const/4 v2, 0x2

    new-array v2, v2, [Ljava/lang/Object;

    iget-object v3, p0, Lcom/example/keyverificationonline/MsgUtils$1;->val$str:Ljava/lang/String;

    const/4 v4, 0x0

    aput-object v3, v2, v4

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    const/4 v5, 0x1

    aput-object v3, v2, v5

    const-string v3, "%s(%d\u79d2\u540e\u5c06\u9000\u51fa\u7a0b\u5e8f)"

    invoke-static {v3, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-static {v1, v2}, Lcom/example/keyverificationonline/MsgUtils;->show(Landroid/content/Context;Ljava/lang/String;)V

    .line 61
    invoke-static {}, Lcom/example/keyverificationonline/MsgUtils;->access$000()Landroid/widget/TextView;

    move-result-object v1

    new-array v2, v5, [Ljava/lang/Object;

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    aput-object v3, v2, v4

    const-string v3, "\u63d0\u793a(%d\u79d2\u540e\u5c06\u9000\u51fa\u7a0b\u5e8f)"

    invoke-static {v3, v2}, Ljava/lang/String;->format(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 62
    invoke-static {}, Lcom/example/keyverificationonline/MsgUtils;->access$200()Landroid/os/Handler;

    move-result-object v1

    const-wide/16 v2, 0x3e8

    invoke-virtual {v1, v4, v2, v3}, Landroid/os/Handler;->sendEmptyMessageDelayed(IJ)Z

    .line 63
    .end local v0    # "i1":I
    goto :goto_0

    .line 64
    :cond_0
    invoke-static {}, Lcom/example/keyverificationonline/MsgUtils;->access$300()I

    move-result v0

    invoke-static {v0}, Lcom/example/keyverificationonline/MsgUtils;->access$102(I)I

    .line 65
    iget-object v0, p0, Lcom/example/keyverificationonline/MsgUtils$1;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/example/keyverificationonline/MsgUtils;->exit(Landroid/content/Context;)V

    .line 69
    :cond_1
    :goto_0
    return-void
.end method

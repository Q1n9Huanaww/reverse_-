.class Lcom/example/keyverificationonline/DialogUtil$3;
.super Ljava/lang/Object;
.source "DialogUtil.java"

# interfaces
.implements Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/DialogUtil;->updateDialog(Landroid/content/Context;ZLjava/lang/String;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation


# instance fields
.field final synthetic val$context:Landroid/content/Context;

.field final synthetic val$progressBar:Landroid/widget/ProgressBar;

.field final synthetic val$progressTextView:Landroid/widget/TextView;


# direct methods
.method constructor <init>(Landroid/widget/ProgressBar;Landroid/widget/TextView;Landroid/content/Context;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 387
    iput-object p1, p0, Lcom/example/keyverificationonline/DialogUtil$3;->val$progressBar:Landroid/widget/ProgressBar;

    iput-object p2, p0, Lcom/example/keyverificationonline/DialogUtil$3;->val$progressTextView:Landroid/widget/TextView;

    iput-object p3, p0, Lcom/example/keyverificationonline/DialogUtil$3;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onDownloadFinished(Ljava/lang/String;)V
    .locals 1
    .param p1, "filePath"    # Ljava/lang/String;

    .line 397
    iget-object v0, p0, Lcom/example/keyverificationonline/DialogUtil$3;->val$context:Landroid/content/Context;

    invoke-static {v0, p1}, Lcom/example/keyverificationonline/DialogUtil;->access$1300(Landroid/content/Context;Ljava/lang/String;)V

    .line 398
    return-void
.end method

.method public onProgress(I)V
    .locals 3
    .param p1, "progress"    # I

    .line 390
    iget-object v0, p0, Lcom/example/keyverificationonline/DialogUtil$3;->val$progressBar:Landroid/widget/ProgressBar;

    invoke-virtual {v0, p1}, Landroid/widget/ProgressBar;->setProgress(I)V

    .line 391
    iget-object v0, p0, Lcom/example/keyverificationonline/DialogUtil$3;->val$progressTextView:Landroid/widget/TextView;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, "%"

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    .line 393
    return-void
.end method

.class Lcom/example/keyverificationonline/MsgUtils$2;
.super Ljava/lang/Object;
.source "MsgUtils.java"

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


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


# direct methods
.method constructor <init>(Landroid/content/Context;)V
    .locals 0
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()V"
        }
    .end annotation

    .line 97
    iput-object p1, p0, Lcom/example/keyverificationonline/MsgUtils$2;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 1
    .param p1, "dialogInterface"    # Landroid/content/DialogInterface;
    .param p2, "i"    # I

    .line 100
    iget-object v0, p0, Lcom/example/keyverificationonline/MsgUtils$2;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/example/keyverificationonline/MsgUtils;->exit(Landroid/content/Context;)V

    .line 101
    return-void
.end method

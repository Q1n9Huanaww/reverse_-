.class public Lcom/example/keyverificationonline/MainActivity;
.super Landroid/app/Activity;
.source "MainActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 6
    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 1
    .param p1, "savedInstanceState"    # Landroid/os/Bundle;

    .line 10
    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    .line 11
    sget v0, Lcom/example/keyverificationonline/R$layout;->activity_main:I

    invoke-virtual {p0, v0}, Lcom/example/keyverificationonline/MainActivity;->setContentView(I)V

    .line 12
    invoke-static {p0}, Lcom/example/keyverificationonline/DialogUtil;->show(Landroid/content/Context;)V

    .line 13
    return-void
.end method

.class public Lcom/ds/daisi/activity/SplashActivity;
.super Lcom/ds/daisi/activity/BaseActivity;
.source "SplashActivity.java"


# direct methods
.method public constructor <init>()V
    .locals 0

    .line 14
    invoke-direct {p0}, Lcom/ds/daisi/activity/BaseActivity;-><init>()V

    return-void
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 2

    .line 17
    invoke-super {p0, p1}, Lcom/ds/daisi/activity/BaseActivity;->onCreate(Landroid/os/Bundle;)V

    const p1, 0x7f04002f

    .line 18
    invoke-virtual {p0, p1}, Lcom/ds/daisi/activity/SplashActivity;->setContentView(I)V

    .line 19
    invoke-virtual {p0}, Lcom/ds/daisi/activity/SplashActivity;->isTaskRoot()Z

    move-result p1

    if-nez p1, :cond_0

    .line 20
    invoke-virtual {p0}, Lcom/ds/daisi/activity/SplashActivity;->finish()V

    return-void

    .line 23
    :cond_0
    const/4 p1, 0x0

    .line 24
    invoke-virtual {p0}, Lcom/ds/daisi/activity/SplashActivity;->getSupportFragmentManager()Landroid/support/v4/app/FragmentManager;

    move-result-object v0

    invoke-virtual {v0}, Landroid/support/v4/app/FragmentManager;->beginTransaction()Landroid/support/v4/app/FragmentTransaction;

    move-result-object v0

    const/4 p0, 0x0

    invoke-virtual {v0, p0}, Landroid/support/v4/app/FragmentTransaction;->setPrimaryNavigationFragment(Landroid/support/v4/app/Fragment;)Landroid/support/v4/app/FragmentTransaction;

    const v1, 0x7f1000c8

    .line 25
    invoke-static {p1}, Lcom/ds/daisi/fragment/SplashFragment;->newInstance(Z)Lcom/ds/daisi/fragment/SplashFragment;

    move-result-object p1

    invoke-virtual {v0, v1, p1}, Landroid/support/v4/app/FragmentTransaction;->replace(ILandroid/support/v4/app/Fragment;)Landroid/support/v4/app/FragmentTransaction;

    move-result-object p1

    .line 26
    invoke-virtual {p1}, Landroid/support/v4/app/FragmentTransaction;->commitAllowingStateLoss()I

    return-void
.end method

.method protected onPause()V
    .locals 0

    .line 37
    invoke-super {p0}, Lcom/ds/daisi/activity/BaseActivity;->onPause()V

    return-void
.end method

.method protected onResume()V
    .locals 0

    .line 31
    invoke-super {p0}, Lcom/ds/daisi/activity/BaseActivity;->onResume()V

    return-void
.end method

.class Lcom/example/keyverificationonline/DialogUtil$6;
.super Ljava/lang/Object;
.source "DialogUtil.java"

# interfaces
.implements Lretrofit266/Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/DialogUtil;->checkExpireDate(Landroid/content/Context;Ljava/lang/String;)V
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x0
    name = null
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Ljava/lang/Object;",
        "Lretrofit266/Callback<",
        "Lokhttp366/ResponseBody;",
        ">;"
    }
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

    .line 482
    iput-object p1, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onFailure(Lretrofit266/Call;Ljava/lang/Throwable;)V
    .locals 2
    .param p2, "p1"    # Ljava/lang/Throwable;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "Lokhttp366/ResponseBody;",
            ">;",
            "Ljava/lang/Throwable;",
            ")V"
        }
    .end annotation

    .line 550
    .local p1, "p0":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    iget-object v0, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    const-string v1, "\u8fc7\u671f\u6821\u9a8c\u51fa\u9519"

    invoke-static {v0, v1}, Lcom/example/keyverificationonline/MsgUtils;->alert(Landroid/content/Context;Ljava/lang/String;)V

    .line 551
    return-void
.end method

.method public onResponse(Lretrofit266/Call;Lretrofit266/Response;)V
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lretrofit266/Call<",
            "Lokhttp366/ResponseBody;",
            ">;",
            "Lretrofit266/Response<",
            "Lokhttp366/ResponseBody;",
            ">;)V"
        }
    .end annotation

    .line 485
    .local p1, "p0":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    .local p2, "response":Lretrofit266/Response;, "Lretrofit266/Response<Lokhttp366/ResponseBody;>;"
    const/4 v0, 0x0

    .line 486
    .local v0, "pass":Z
    const-string v1, ""

    .line 487
    .local v1, "errorMessage":Ljava/lang/String;
    const/4 v2, 0x0

    .line 489
    .local v2, "result":Ljava/lang/String;
    :try_start_0
    invoke-virtual {p2}, Lretrofit266/Response;->body()Ljava/lang/Object;

    move-result-object v3
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    const-string v4, ""

    if-eqz v3, :cond_0

    :try_start_1
    invoke-virtual {p2}, Lretrofit266/Response;->body()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lokhttp366/ResponseBody;

    invoke-virtual {v3}, Lokhttp366/ResponseBody;->string()Ljava/lang/String;

    move-result-object v3
    :try_end_1
    .catch Ljava/io/IOException; {:try_start_1 .. :try_end_1} :catch_0

    goto :goto_0

    :cond_0
    move-object v3, v4

    :goto_0
    move-object v2, v3

    .line 492
    nop

    .line 493
    invoke-virtual {v2}, Ljava/lang/String;->hashCode()I

    move-result v3

    packed-switch v3, :pswitch_data_0

    :cond_1
    goto :goto_1

    :pswitch_0
    const-string v3, "7"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x6

    goto :goto_2

    :pswitch_1
    const-string v3, "6"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x5

    goto :goto_2

    :pswitch_2
    const-string v3, "5"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x4

    goto :goto_2

    :pswitch_3
    const-string v3, "4"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x3

    goto :goto_2

    :pswitch_4
    const-string v3, "3"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x2

    goto :goto_2

    :pswitch_5
    const-string v3, "2"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x1

    goto :goto_2

    :pswitch_6
    const-string v3, "1"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_1

    const/4 v3, 0x0

    goto :goto_2

    :goto_1
    const/4 v3, -0x1

    :goto_2
    const-string v5, "activeCode"

    packed-switch v3, :pswitch_data_1

    .line 521
    const/4 v0, 0x1

    goto :goto_3

    .line 518
    :pswitch_7
    const-string v3, "\u5e10\u53f7\u70b9\u6570\u4e0d\u8db3"

    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    invoke-static {v3, v4}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 519
    goto :goto_3

    .line 515
    :pswitch_8
    const-string v3, "\u8f6f\u4ef6\u7ef4\u62a4\u4e2d"

    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    invoke-static {v3, v4}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 516
    goto :goto_3

    .line 512
    :pswitch_9
    const-string v3, "\u8f6f\u4ef6\u505c\u6b62\u8fd0\u8425"

    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    invoke-static {v3, v4}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 513
    goto :goto_3

    .line 508
    :pswitch_a
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$300()Landroid/content/SharedPreferences;

    move-result-object v3

    invoke-interface {v3}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v3

    invoke-interface {v3, v5, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v3

    invoke-interface {v3}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 509
    const-string v1, "\u5e10\u53f7\u5df2\u5728\u522b\u5904\u767b\u5f55"

    .line 510
    goto :goto_3

    .line 504
    :pswitch_b
    const-string v3, "\u5185\u90e8\u9519\u8bef"

    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    invoke-static {v3, v4}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 505
    goto :goto_3

    .line 500
    :pswitch_c
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$300()Landroid/content/SharedPreferences;

    move-result-object v3

    invoke-interface {v3}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v3

    invoke-interface {v3, v5, v4}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    move-result-object v3

    invoke-interface {v3}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 501
    const-string v1, "\u5e10\u53f7\u8fc7\u671f"

    .line 502
    goto :goto_3

    .line 496
    :pswitch_d
    const-string v1, "\u5e10\u53f7\u88ab\u9501\u5b9a"

    .line 497
    nop

    .line 524
    :goto_3
    if-nez v0, :cond_3

    .line 525
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$1200()V

    .line 526
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$700()Landroid/app/AlertDialog;

    move-result-object v3

    invoke-virtual {v3}, Landroid/app/AlertDialog;->show()V

    .line 527
    invoke-virtual {v1}, Ljava/lang/String;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_2

    goto :goto_4

    .line 535
    :cond_2
    const-string v1, "\u672a\u77e5\u9519\u8bef"

    .line 537
    :goto_4
    iget-object v3, p0, Lcom/example/keyverificationonline/DialogUtil$6;->val$context:Landroid/content/Context;

    invoke-static {v3, v1}, Lcom/example/keyverificationonline/MsgUtils;->alert(Landroid/content/Context;Ljava/lang/String;)V

    .line 539
    :cond_3
    return-void

    .line 490
    :catch_0
    move-exception v3

    .line 491
    .local v3, "e":Ljava/io/IOException;
    new-instance v4, Ljava/lang/RuntimeException;

    invoke-direct {v4, v3}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v4

    nop

    :pswitch_data_0
    .packed-switch 0x31
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch

    :pswitch_data_1
    .packed-switch 0x0
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
    .end packed-switch
.end method

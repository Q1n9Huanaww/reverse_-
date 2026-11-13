.class Lcom/example/keyverificationonline/DialogUtil$4;
.super Ljava/lang/Object;
.source "DialogUtil.java"

# interfaces
.implements Lretrofit266/Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/DialogUtil;->requestLogin(Ljava/lang/String;Landroid/content/Context;)V
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

    .line 406
    iput-object p1, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$context:Landroid/content/Context;

    iput-object p2, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$activeCode:Ljava/lang/String;

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

    .line 453
    .local p1, "p0":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    const-string v0, "\u8bf7\u6c42\u5931\u8d25\uff01"

    iget-object v1, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$context:Landroid/content/Context;

    invoke-static {v0, v1}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 454
    return-void
.end method

.method public onResponse(Lretrofit266/Call;Lretrofit266/Response;)V
    .locals 7
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

    .line 409
    .local p1, "p0":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    .local p2, "response":Lretrofit266/Response;, "Lretrofit266/Response<Lokhttp366/ResponseBody;>;"
    const/4 v0, 0x0

    .line 411
    .local v0, "result":Ljava/lang/String;
    :try_start_0
    invoke-virtual {p2}, Lretrofit266/Response;->body()Ljava/lang/Object;

    move-result-object v1

    if-eqz v1, :cond_0

    invoke-virtual {p2}, Lretrofit266/Response;->body()Ljava/lang/Object;

    move-result-object v1

    check-cast v1, Lokhttp366/ResponseBody;

    invoke-virtual {v1}, Lokhttp366/ResponseBody;->string()Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    :cond_0
    const-string v1, ""
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    move-object v0, v1

    .line 414
    nop

    .line 415
    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    return-void

    .line 416
    :cond_1
    const-string v1, "\\|"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    .line 417
    .local v1, "values":[Ljava/lang/String;
    array-length v2, v1

    if-nez v2, :cond_2

    return-void

    .line 418
    :cond_2
    const/4 v2, 0x0

    aget-object v2, v1, v2

    .line 420
    .local v2, "code":Ljava/lang/String;
    const-string v3, "1"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_5

    .line 421
    const/4 v3, 0x4

    aget-object v3, v1, v3

    .line 422
    .local v3, "currentDate":Ljava/lang/String;
    invoke-static {v3}, Lcom/example/keyverificationonline/DialogUtil;->access$200(Ljava/lang/String;)Z

    move-result v4

    if-eqz v4, :cond_3

    .line 430
    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$context:Landroid/content/Context;

    const-string v5, "\u65f6\u95f4\u6821\u9a8c\u5931\u8d25\uff01\u8bf7\u68c0\u67e5\u8bbe\u5907\u65f6\u95f4\u662f\u5426\u51c6\u786e"

    invoke-static {v4, v5}, Lcom/example/keyverificationonline/MsgUtils;->alert(Landroid/content/Context;Ljava/lang/String;)V

    .line 432
    :cond_3
    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$activeCode:Ljava/lang/String;

    invoke-virtual {v4}, Ljava/lang/String;->toLowerCase()Ljava/lang/String;

    move-result-object v4

    const-string v5, "test"

    invoke-virtual {v4, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-nez v4, :cond_4

    .line 433
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$300()Landroid/content/SharedPreferences;

    move-result-object v4

    invoke-interface {v4}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v4

    .line 434
    .local v4, "editor":Landroid/content/SharedPreferences$Editor;
    const-string v5, "activeCode"

    iget-object v6, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$activeCode:Ljava/lang/String;

    invoke-interface {v4, v5, v6}, Landroid/content/SharedPreferences$Editor;->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;

    .line 435
    invoke-interface {v4}, Landroid/content/SharedPreferences$Editor;->apply()V

    .line 437
    .end local v4    # "editor":Landroid/content/SharedPreferences$Editor;
    :cond_4
    const/4 v4, 0x2

    aget-object v4, v1, v4

    invoke-static {v4}, Lcom/example/keyverificationonline/DialogUtil;->access$402(Ljava/lang/String;)Ljava/lang/String;

    .line 438
    const/4 v4, 0x3

    aget-object v4, v1, v4

    invoke-static {v4}, Lcom/example/keyverificationonline/DialogUtil;->access$502(Ljava/lang/String;)Ljava/lang/String;

    .line 439
    const/4 v4, 0x7

    aget-object v4, v1, v4

    invoke-static {v4}, Lcom/example/keyverificationonline/DialogUtil;->access$602(Ljava/lang/String;)Ljava/lang/String;

    .line 440
    new-instance v4, Ljava/lang/StringBuilder;

    invoke-direct {v4}, Ljava/lang/StringBuilder;-><init>()V

    const-string v5, "\u5230\u671f\u65f6\u95f4\uff1a"

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$500()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v4}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v4

    iget-object v5, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$context:Landroid/content/Context;

    invoke-static {v4, v5}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 441
    invoke-static {}, Lcom/example/keyverificationonline/DialogUtil;->access$700()Landroid/app/AlertDialog;

    move-result-object v4

    invoke-virtual {v4}, Landroid/app/AlertDialog;->dismiss()V

    .line 442
    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$context:Landroid/content/Context;

    iget-object v5, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$activeCode:Ljava/lang/String;

    invoke-static {v4, v5}, Lcom/example/keyverificationonline/DialogUtil;->access$800(Landroid/content/Context;Ljava/lang/String;)V

    .line 445
    .end local v3    # "currentDate":Ljava/lang/String;
    :cond_5
    const-string v3, "2"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_6

    .line 446
    const/4 v3, 0x1

    aget-object v3, v1, v3

    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$4;->val$context:Landroid/content/Context;

    invoke-static {v3, v4}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 449
    :cond_6
    return-void

    .line 412
    .end local v1    # "values":[Ljava/lang/String;
    .end local v2    # "code":Ljava/lang/String;
    :catch_0
    move-exception v1

    .line 413
    .local v1, "e":Ljava/io/IOException;
    new-instance v2, Ljava/lang/RuntimeException;

    invoke-direct {v2, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v2
.end method

.class Lcom/example/keyverificationonline/DialogUtil$2;
.super Ljava/lang/Object;
.source "DialogUtil.java"

# interfaces
.implements Lretrofit266/Callback;


# annotations
.annotation system Ldalvik/annotation/EnclosingMethod;
    value = Lcom/example/keyverificationonline/DialogUtil;->notice(Landroid/content/Context;)V
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

    .line 148
    iput-object p1, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

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

    .line 189
    .local p1, "p0":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    const-string v0, "\u516c\u544a\u83b7\u53d6\u51fa\u9519\uff01"

    iget-object v1, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v0, v1}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 190
    iget-object v0, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v0}, Lcom/example/keyverificationonline/DialogUtil;->verificationDialog(Landroid/content/Context;)V

    .line 191
    return-void
.end method

.method public onResponse(Lretrofit266/Call;Lretrofit266/Response;)V
    .locals 8
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

    .line 151
    .local p1, "p0":Lretrofit266/Call;, "Lretrofit266/Call<Lokhttp366/ResponseBody;>;"
    .local p2, "response":Lretrofit266/Response;, "Lretrofit266/Response<Lokhttp366/ResponseBody;>;"
    const/4 v0, 0x0

    .line 153
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

    .line 156
    nop

    .line 157
    const-string v1, "|"

    invoke-virtual {v0, v1}, Ljava/lang/String;->contains(Ljava/lang/CharSequence;)Z

    move-result v1

    if-nez v1, :cond_1

    .line 158
    iget-object v1, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v1}, Lcom/example/keyverificationonline/DialogUtil;->verificationDialog(Landroid/content/Context;)V

    .line 159
    return-void

    .line 161
    :cond_1
    const-string v1, "\\|"

    invoke-virtual {v0, v1}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v1

    .line 162
    .local v1, "values":[Ljava/lang/String;
    array-length v2, v1

    if-nez v2, :cond_2

    .line 163
    iget-object v2, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v2}, Lcom/example/keyverificationonline/DialogUtil;->verificationDialog(Landroid/content/Context;)V

    .line 164
    return-void

    .line 166
    :cond_2
    const/4 v2, 0x0

    aget-object v2, v1, v2

    .line 167
    .local v2, "code":Ljava/lang/String;
    const-string v3, "1"

    invoke-virtual {v2, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v4

    if-eqz v4, :cond_4

    .line 168
    const/4 v4, 0x5

    aget-object v4, v1, v4

    invoke-static {v4}, Lcom/example/keyverificationonline/DialogUtil;->access$002(Ljava/lang/String;)Ljava/lang/String;

    .line 169
    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v4}, Lcom/example/keyverificationonline/DialogUtil;->verificationDialog(Landroid/content/Context;)V

    .line 170
    const/4 v4, 0x0

    .line 171
    .local v4, "isFocusUpdate":Z
    const/4 v5, 0x3

    aget-object v5, v1, v5

    .line 172
    .local v5, "updateState":Ljava/lang/String;
    const-string v6, "0"

    invoke-virtual {v5, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    .line 173
    const/4 v4, 0x0

    .line 175
    invoke-virtual {v5, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v3

    if-eqz v3, :cond_3

    .line 176
    const/4 v4, 0x1

    .line 178
    :cond_3
    const/4 v3, 0x4

    aget-object v3, v1, v3

    .line 179
    .local v3, "updateUrl":Ljava/lang/String;
    const/4 v6, 0x1

    aget-object v6, v1, v6

    .line 180
    .local v6, "cloudAppVersion":Ljava/lang/String;
    iget-object v7, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v7, v4, v3, v6}, Lcom/example/keyverificationonline/DialogUtil;->updateDialog(Landroid/content/Context;ZLjava/lang/String;Ljava/lang/String;)V

    .line 181
    .end local v3    # "updateUrl":Ljava/lang/String;
    .end local v4    # "isFocusUpdate":Z
    .end local v5    # "updateState":Ljava/lang/String;
    .end local v6    # "cloudAppVersion":Ljava/lang/String;
    goto :goto_1

    .line 182
    :cond_4
    const-string v3, "\u516c\u544a\u83b7\u53d6\u5931\u8d25\uff01"

    iget-object v4, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v3, v4}, Lcom/example/keyverificationonline/DialogUtil;->access$100(Ljava/lang/String;Landroid/content/Context;)V

    .line 183
    iget-object v3, p0, Lcom/example/keyverificationonline/DialogUtil$2;->val$context:Landroid/content/Context;

    invoke-static {v3}, Lcom/example/keyverificationonline/DialogUtil;->verificationDialog(Landroid/content/Context;)V

    .line 185
    :goto_1
    return-void

    .line 154
    .end local v1    # "values":[Ljava/lang/String;
    .end local v2    # "code":Ljava/lang/String;
    :catch_0
    move-exception v1

    .line 155
    .local v1, "e":Ljava/io/IOException;
    new-instance v2, Ljava/lang/RuntimeException;

    invoke-direct {v2, v1}, Ljava/lang/RuntimeException;-><init>(Ljava/lang/Throwable;)V

    throw v2
.end method

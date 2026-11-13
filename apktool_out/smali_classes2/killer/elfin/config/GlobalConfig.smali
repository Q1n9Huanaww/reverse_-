.class public Lkiller/elfin/config/GlobalConfig;
.super Ljava/lang/Object;
.source "GlobalConfig.java"


# static fields
.field private static cloudAppId:Ljava/lang/String;

.field private static cloudHost:Ljava/lang/String;

.field private static runScriptUpdate:Z

.field private static scriptUpdateUrl:Ljava/lang/String;

.field private static scriptVersion:Ljava/lang/Long;

.field private static stopScriptUpdate:Z


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .prologue
    const/4 v0, 0x0

    .line 17
    sput-boolean v0, Lkiller/elfin/config/GlobalConfig;->stopScriptUpdate:Z

    .line 18
    sput-boolean v0, Lkiller/elfin/config/GlobalConfig;->runScriptUpdate:Z

    return-void
.end method

.method public constructor <init>()V
    .locals 0

    .prologue
    .line 11
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static getCloudAppId()Ljava/lang/String;
    .locals 4

    .prologue
    .line 63
    invoke-static {}, Lkiller/elfin/Free;->getSharedPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 64
    .local v1, "sharedPreferences":Landroid/content/SharedPreferences;
    if-eqz v1, :cond_0

    .line 65
    const-string v2, "cloudAppId"

    const-string v3, ""

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 66
    .local v0, "cacheApId":Ljava/lang/String;
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    invoke-virtual {v0}, Ljava/lang/String;->trim()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/String;->length()I

    move-result v2

    if-lez v2, :cond_0

    .line 70
    .end local v0    # "cacheApId":Ljava/lang/String;
    :goto_0
    return-object v0

    :cond_0
    sget-object v0, Lkiller/elfin/config/GlobalConfig;->cloudAppId:Ljava/lang/String;

    goto :goto_0
.end method

.method public static getCloudHost()Ljava/lang/String;
    .locals 1

    .prologue
    .line 86
    sget-object v0, Lkiller/elfin/config/GlobalConfig;->cloudHost:Ljava/lang/String;

    return-object v0
.end method

.method public static getDeviceName()Ljava/lang/String;
    .locals 5

    .prologue
    .line 74
    invoke-static {}, Lkiller/elfin/Free;->getSharedPreferences()Landroid/content/SharedPreferences;

    move-result-object v1

    .line 75
    .local v1, "sharedPreferences":Landroid/content/SharedPreferences;
    if-eqz v1, :cond_0

    .line 76
    const-string v2, "cloudDeviceName"

    const/4 v3, 0x0

    invoke-interface {v1, v2, v3}, Landroid/content/SharedPreferences;->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 77
    .local v0, "cloudDeviceName":Ljava/lang/String;
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_0

    .line 81
    .end local v0    # "cloudDeviceName":Ljava/lang/String;
    :goto_0
    return-object v0

    :cond_0
    sget-object v2, Landroid/os/Build;->MODEL:Ljava/lang/String;

    invoke-static {v2}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_1

    move-object v0, v2

    goto :goto_0

    :cond_1
    sget-object v2, Landroid/os/Build;->MANUFACTURER:Ljava/lang/String;

    sget-object v3, Landroid/os/Build;->PRODUCT:Ljava/lang/String;

    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    invoke-virtual {v0, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    const-string v4, " "

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v2

    if-nez v2, :cond_2

    goto :goto_0

    :cond_2
    const-string v0, "\u672a\u547d\u540d"

    goto :goto_0
.end method

.method public static getScriptUpdateUrl()Ljava/lang/String;
    .locals 1

    .prologue
    .line 59
    sget-object v0, Lkiller/elfin/config/GlobalConfig;->scriptUpdateUrl:Ljava/lang/String;

    return-object v0
.end method

.method public static getScriptVersion()J
    .locals 4

    .prologue
    .line 51
    invoke-static {}, Lkiller/elfin/Free;->getSharedPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 52
    .local v0, "sharedPreferences":Landroid/content/SharedPreferences;
    if-eqz v0, :cond_0

    .line 53
    const-string v1, "scriptVersion"

    sget-object v2, Lkiller/elfin/config/GlobalConfig;->scriptVersion:Ljava/lang/Long;

    invoke-virtual {v2}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    invoke-interface {v0, v1, v2, v3}, Landroid/content/SharedPreferences;->getLong(Ljava/lang/String;J)J

    move-result-wide v2

    .line 55
    :goto_0
    return-wide v2

    :cond_0
    sget-object v1, Lkiller/elfin/config/GlobalConfig;->scriptVersion:Ljava/lang/Long;

    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v2

    goto :goto_0
.end method

.method public static initConfig()V
    .locals 9

    .prologue
    .line 22
    :try_start_0
    invoke-static {}, Lkiller/elfin/Free;->getContext()Landroid/content/Context;

    move-result-object v3

    if-nez v3, :cond_1

    .line 41
    .local v0, "context":Ljava/lang/String;
    :cond_0
    :goto_0
    return-void

    .line 25
    .end local v0    # "context":Ljava/lang/String;
    :cond_1
    invoke-static {}, Lkiller/elfin/Free;->getContext()Landroid/content/Context;

    move-result-object v3

    const-string v4, "script.json"

    invoke-static {v3, v4}, Lkiller/core/util/AssetsUtil;->readFileString(Landroid/content/Context;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    .line 26
    .restart local v0    # "context":Ljava/lang/String;
    invoke-static {v0}, Landroid/text/TextUtils;->isEmpty(Ljava/lang/CharSequence;)Z

    move-result v3

    if-nez v3, :cond_0

    .line 29
    const-string v3, "Base64"

    const-string v4, "\u6267\u884cBase64\u89e3\u5bc6"

    invoke-static {v3, v4}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v4

    const/16 v5, 0x32

    if-ge v4, v5, :cond_2

    move-object v6, v0

    goto :goto_1

    :cond_2
    const/4 v6, 0x0

    invoke-virtual {v0, v6, v5}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v6

    :goto_1
    new-instance v7, Ljava/lang/StringBuilder;

    invoke-direct {v7}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "\u539f\u59cb\u6570\u636e: "

    invoke-virtual {v7, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7, v6}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v7}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v3, v6}, Landroid/util/Log;->d(Ljava/lang/String;Ljava/lang/String;)I

    invoke-static {v0}, Lokio/ByteString;->decodeBase64(Ljava/lang/String;)Lokio/ByteString;

    move-result-object v7

    if-eqz v7, :cond_4

    invoke-virtual {v7}, Lokio/ByteString;->utf8()Ljava/lang/String;

    move-result-object v8

    move-object v0, v8

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v7

    const/16 v8, 0x64

    if-ge v7, v8, :cond_3

    move-object v7, v0

    goto :goto_2

    :cond_3
    const/4 v6, 0x0

    invoke-virtual {v0, v6, v8}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object v7

    :goto_2
    new-instance v6, Ljava/lang/StringBuilder;

    invoke-direct {v6}, Ljava/lang/StringBuilder;-><init>()V

    const-string v8, "\u89e3\u5bc6\u7ed3\u679c: "

    invoke-virtual {v6, v8}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6, v7}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v6}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-static {v3, v6}, Landroid/util/Log;->i(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_3

    :cond_4
    const-string v4, "Base64\u89e3\u7801\u5931\u8d25\uff0c\u4f7f\u7528\u539f\u59cb\u6570\u636e\u7ee7\u7eed"

    invoke-static {v3, v4}, Landroid/util/Log;->w(Ljava/lang/String;Ljava/lang/String;)I

    :goto_3
    invoke-static {v0}, Lcom/alibaba/fastjson/JSON;->parseObject(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONObject;

    move-result-object v2

    .line 30
    .local v2, "jsonObject":Lcom/alibaba/fastjson/JSONObject;
    if-eqz v2, :cond_0

    .line 31
    const-string v3, "cloudAppId"

    invoke-virtual {v2, v3}, Lcom/alibaba/fastjson/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    sput-object v3, Lkiller/elfin/config/GlobalConfig;->cloudAppId:Ljava/lang/String;

    .line 32
    const-string v3, "cloudHost"

    invoke-virtual {v2, v3}, Lcom/alibaba/fastjson/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    sput-object v3, Lkiller/elfin/config/GlobalConfig;->cloudHost:Ljava/lang/String;

    .line 33
    const-string v3, "scriptVersion"

    invoke-virtual {v2, v3}, Lcom/alibaba/fastjson/JSONObject;->getLong(Ljava/lang/String;)Ljava/lang/Long;

    move-result-object v3

    sput-object v3, Lkiller/elfin/config/GlobalConfig;->scriptVersion:Ljava/lang/Long;

    .line 34
    const-string v3, "scriptUpdateUrl"

    invoke-virtual {v2, v3}, Lcom/alibaba/fastjson/JSONObject;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    sput-object v3, Lkiller/elfin/config/GlobalConfig;->scriptUpdateUrl:Ljava/lang/String;

    .line 35
    const-string v3, "stopScriptUpdate"

    invoke-virtual {v2, v3}, Lcom/alibaba/fastjson/JSONObject;->getBoolean(Ljava/lang/String;)Ljava/lang/Boolean;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v3

    sput-boolean v3, Lkiller/elfin/config/GlobalConfig;->stopScriptUpdate:Z

    .line 36
    const-string v3, "runScriptUpdate"

    invoke-virtual {v2, v3}, Lcom/alibaba/fastjson/JSONObject;->getBoolean(Ljava/lang/String;)Ljava/lang/Boolean;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v3

    sput-boolean v3, Lkiller/elfin/config/GlobalConfig;->runScriptUpdate:Z
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    goto/16 :goto_0

    .line 38
    .end local v2    # "jsonObject":Lcom/alibaba/fastjson/JSONObject;
    :catch_0
    move-exception v1

    .line 39
    .local v1, "e":Ljava/lang/Exception;
    const-string v3, "GlobalConfig"

    const-string v4, "initConfig"

    invoke-static {v3, v4, v1}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto/16 :goto_0
.end method

.method public static isRunScriptUpdate()Z
    .locals 1

    .prologue
    .line 90
    sget-boolean v0, Lkiller/elfin/config/GlobalConfig;->runScriptUpdate:Z

    return v0
.end method

.method public static isStopScriptUpdate()Z
    .locals 1

    .prologue
    .line 94
    sget-boolean v0, Lkiller/elfin/config/GlobalConfig;->stopScriptUpdate:Z

    return v0
.end method

.method public static setScriptVersion(Ljava/lang/Long;)V
    .locals 6
    .param p0, "version"    # Ljava/lang/Long;

    .prologue
    .line 44
    invoke-static {}, Lkiller/elfin/Free;->getSharedPreferences()Landroid/content/SharedPreferences;

    move-result-object v0

    .line 45
    .local v0, "sharedPreferences":Landroid/content/SharedPreferences;
    if-eqz v0, :cond_0

    .line 46
    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    const-string v2, "scriptVersion"

    invoke-virtual {p0}, Ljava/lang/Long;->longValue()J

    move-result-wide v4

    invoke-interface {v1, v2, v4, v5}, Landroid/content/SharedPreferences$Editor;->putLong(Ljava/lang/String;J)Landroid/content/SharedPreferences$Editor;

    move-result-object v1

    invoke-interface {v1}, Landroid/content/SharedPreferences$Editor;->commit()Z

    .line 48
    :cond_0
    return-void
.end method

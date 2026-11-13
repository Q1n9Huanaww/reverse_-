.class public Lcom/example/keyverificationonline/DownloadFileTask;
.super Landroid/os/AsyncTask;
.source "DownloadFileTask.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;
    }
.end annotation

.annotation system Ldalvik/annotation/Signature;
    value = {
        "Landroid/os/AsyncTask<",
        "Ljava/lang/String;",
        "Ljava/lang/Integer;",
        "Ljava/lang/String;",
        ">;"
    }
.end annotation


# instance fields
.field private context:Landroid/content/Context;

.field private listener:Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;


# direct methods
.method public constructor <init>(Landroid/content/Context;Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;)V
    .locals 0
    .param p1, "context"    # Landroid/content/Context;
    .param p2, "listener"    # Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;

    .line 24
    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    .line 25
    iput-object p2, p0, Lcom/example/keyverificationonline/DownloadFileTask;->listener:Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;

    .line 26
    iput-object p1, p0, Lcom/example/keyverificationonline/DownloadFileTask;->context:Landroid/content/Context;

    .line 27
    return-void
.end method


# virtual methods
.method protected bridge synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x1000
        }
        names = {
            null
        }
    .end annotation

    .line 19
    check-cast p1, [Ljava/lang/String;

    invoke-virtual {p0, p1}, Lcom/example/keyverificationonline/DownloadFileTask;->doInBackground([Ljava/lang/String;)Ljava/lang/String;

    move-result-object p1

    return-object p1
.end method

.method protected varargs doInBackground([Ljava/lang/String;)Ljava/lang/String;
    .locals 19
    .param p1, "urls"    # [Ljava/lang/String;

    .line 36
    move-object/from16 v1, p0

    const/4 v0, 0x0

    aget-object v2, p1, v0

    .line 37
    .local v2, "fileUrl":Ljava/lang/String;
    const-string v3, "app_update.apk"

    .line 38
    .local v3, "fileName":Ljava/lang/String;
    const/4 v4, 0x0

    .line 39
    .local v4, "fileUri":Landroid/net/Uri;
    const/4 v5, 0x0

    .line 40
    .local v5, "outputFile":Ljava/io/File;
    const/4 v6, 0x0

    .line 41
    .local v6, "output":Ljava/io/OutputStream;
    const/4 v7, 0x0

    .line 42
    .local v7, "input":Ljava/io/InputStream;
    const/4 v8, 0x0

    .line 45
    .local v8, "connection":Ljava/net/HttpURLConnection;
    :try_start_0
    new-instance v9, Ljava/net/URL;

    invoke-direct {v9, v2}, Ljava/net/URL;-><init>(Ljava/lang/String;)V

    .line 46
    .local v9, "url":Ljava/net/URL;
    invoke-virtual {v9}, Ljava/net/URL;->openConnection()Ljava/net/URLConnection;

    move-result-object v10

    check-cast v10, Ljava/net/HttpURLConnection;

    move-object v8, v10

    .line 47
    invoke-virtual {v8}, Ljava/net/HttpURLConnection;->connect()V

    .line 50
    invoke-virtual {v8}, Ljava/net/HttpURLConnection;->getContentLength()I

    move-result v10

    .line 52
    .local v10, "fileLength":I
    sget v11, Landroid/os/Build$VERSION;->SDK_INT:I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_4
    .catchall {:try_start_0 .. :try_end_0} :catchall_1

    const/16 v12, 0x1d

    if-lt v11, v12, :cond_3

    .line 54
    :try_start_1
    new-instance v11, Landroid/content/ContentValues;

    invoke-direct {v11}, Landroid/content/ContentValues;-><init>()V

    .line 55
    .local v11, "values":Landroid/content/ContentValues;
    const-string v12, "_display_name"

    invoke-virtual {v11, v12, v3}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 56
    const-string v12, "mime_type"

    const-string v13, "application/vnd.android.package-archive"

    invoke-virtual {v11, v12, v13}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 57
    const-string v12, "relative_path"

    sget-object v13, Landroid/os/Environment;->DIRECTORY_DOWNLOADS:Ljava/lang/String;

    invoke-virtual {v11, v12, v13}, Landroid/content/ContentValues;->put(Ljava/lang/String;Ljava/lang/String;)V

    .line 59
    iget-object v12, v1, Lcom/example/keyverificationonline/DownloadFileTask;->context:Landroid/content/Context;

    invoke-virtual {v12}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v12

    sget-object v13, Landroid/provider/MediaStore$Downloads;->EXTERNAL_CONTENT_URI:Landroid/net/Uri;

    invoke-virtual {v12, v13, v11}, Landroid/content/ContentResolver;->insert(Landroid/net/Uri;Landroid/content/ContentValues;)Landroid/net/Uri;

    move-result-object v12

    move-object v4, v12

    .line 60
    if-nez v4, :cond_2

    .line 61
    const-string v12, "Failed to create file."
    :try_end_1
    .catch Ljava/lang/Exception; {:try_start_1 .. :try_end_1} :catch_1
    .catchall {:try_start_1 .. :try_end_1} :catchall_0

    .line 104
    if-eqz v7, :cond_0

    .line 105
    :try_start_2
    invoke-virtual {v7}, Ljava/io/InputStream;->close()V

    goto :goto_0

    .line 110
    :catch_0
    move-exception v0

    goto :goto_1

    .line 107
    :cond_0
    :goto_0
    if-eqz v8, :cond_1

    .line 108
    invoke-virtual {v8}, Ljava/net/HttpURLConnection;->disconnect()V
    :try_end_2
    .catch Ljava/lang/Exception; {:try_start_2 .. :try_end_2} :catch_0

    .line 111
    :cond_1
    nop

    .line 61
    :goto_1
    return-object v12

    .line 64
    :cond_2
    :try_start_3
    iget-object v12, v1, Lcom/example/keyverificationonline/DownloadFileTask;->context:Landroid/content/Context;

    invoke-virtual {v12}, Landroid/content/Context;->getContentResolver()Landroid/content/ContentResolver;

    move-result-object v12

    invoke-virtual {v12, v4}, Landroid/content/ContentResolver;->openOutputStream(Landroid/net/Uri;)Ljava/io/OutputStream;

    move-result-object v12
    :try_end_3
    .catch Ljava/lang/Exception; {:try_start_3 .. :try_end_3} :catch_1
    .catchall {:try_start_3 .. :try_end_3} :catchall_0

    move-object v6, v12

    .line 65
    .end local v11    # "values":Landroid/content/ContentValues;
    goto :goto_2

    .line 103
    .end local v9    # "url":Ljava/net/URL;
    .end local v10    # "fileLength":I
    :catchall_0
    move-exception v0

    move-object v14, v2

    move-object/from16 v16, v3

    move-object v2, v0

    goto/16 :goto_c

    .line 99
    :catch_1
    move-exception v0

    move-object v14, v2

    move-object/from16 v16, v3

    goto/16 :goto_9

    .line 69
    .restart local v9    # "url":Ljava/net/URL;
    .restart local v10    # "fileLength":I
    :cond_3
    :try_start_4
    sget-object v11, Landroid/os/Environment;->DIRECTORY_DOWNLOADS:Ljava/lang/String;

    invoke-static {v11}, Landroid/os/Environment;->getExternalStoragePublicDirectory(Ljava/lang/String;)Ljava/io/File;

    move-result-object v11

    .line 70
    .local v11, "downloadDir":Ljava/io/File;
    new-instance v12, Ljava/io/File;

    invoke-direct {v12, v11, v3}, Ljava/io/File;-><init>(Ljava/io/File;Ljava/lang/String;)V

    move-object v5, v12

    .line 71
    invoke-virtual {v5}, Ljava/io/File;->exists()Z

    move-result v12
    :try_end_4
    .catch Ljava/lang/Exception; {:try_start_4 .. :try_end_4} :catch_4
    .catchall {:try_start_4 .. :try_end_4} :catchall_1

    if-eqz v12, :cond_4

    .line 72
    :try_start_5
    invoke-virtual {v5}, Ljava/io/File;->delete()Z
    :try_end_5
    .catch Ljava/lang/Exception; {:try_start_5 .. :try_end_5} :catch_1
    .catchall {:try_start_5 .. :try_end_5} :catchall_0

    .line 75
    :cond_4
    :try_start_6
    new-instance v12, Ljava/io/FileOutputStream;

    invoke-direct {v12, v5}, Ljava/io/FileOutputStream;-><init>(Ljava/io/File;)V

    move-object v6, v12

    .line 76
    invoke-static {v5}, Landroid/net/Uri;->fromFile(Ljava/io/File;)Landroid/net/Uri;

    move-result-object v12

    move-object v4, v12

    .line 80
    .end local v11    # "downloadDir":Ljava/io/File;
    :goto_2
    new-instance v11, Ljava/io/BufferedInputStream;

    invoke-virtual {v9}, Ljava/net/URL;->openStream()Ljava/io/InputStream;

    move-result-object v12

    invoke-direct {v11, v12}, Ljava/io/BufferedInputStream;-><init>(Ljava/io/InputStream;)V

    move-object v7, v11

    .line 82
    const/16 v11, 0x1000

    new-array v11, v11, [B

    .line 83
    .local v11, "data":[B
    const-wide/16 v12, 0x0

    .line 85
    .local v12, "total":J
    :goto_3
    invoke-virtual {v7, v11}, Ljava/io/InputStream;->read([B)I

    move-result v14
    :try_end_6
    .catch Ljava/lang/Exception; {:try_start_6 .. :try_end_6} :catch_4
    .catchall {:try_start_6 .. :try_end_6} :catchall_1

    move v15, v14

    .local v15, "count":I
    const/4 v0, -0x1

    if-eq v14, v0, :cond_7

    .line 86
    move-object v14, v2

    move-object/from16 v16, v3

    .end local v2    # "fileUrl":Ljava/lang/String;
    .end local v3    # "fileName":Ljava/lang/String;
    .local v14, "fileUrl":Ljava/lang/String;
    .local v16, "fileName":Ljava/lang/String;
    int-to-long v2, v15

    add-long/2addr v12, v2

    .line 88
    if-lez v10, :cond_5

    .line 89
    const/4 v0, 0x1

    :try_start_7
    new-array v0, v0, [Ljava/lang/Integer;

    const-wide/16 v2, 0x64

    mul-long v2, v2, v12

    move-wide/from16 v17, v12

    .end local v12    # "total":J
    .local v17, "total":J
    int-to-long v12, v10

    div-long/2addr v2, v12

    long-to-int v3, v2

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    const/4 v3, 0x0

    aput-object v2, v0, v3

    invoke-virtual {v1, v0}, Lcom/example/keyverificationonline/DownloadFileTask;->publishProgress([Ljava/lang/Object;)V

    goto :goto_4

    .line 88
    .end local v17    # "total":J
    .restart local v12    # "total":J
    :cond_5
    move-wide/from16 v17, v12

    .line 91
    .end local v12    # "total":J
    .restart local v17    # "total":J
    :goto_4
    if-eqz v6, :cond_6

    .line 92
    const/4 v0, 0x0

    invoke-virtual {v6, v11, v0, v15}, Ljava/io/OutputStream;->write([BII)V

    goto :goto_5

    .line 91
    :cond_6
    const/4 v0, 0x0

    .line 85
    .end local v15    # "count":I
    :goto_5
    move-object v2, v14

    move-object/from16 v3, v16

    move-wide/from16 v12, v17

    goto :goto_3

    .line 95
    .end local v14    # "fileUrl":Ljava/lang/String;
    .end local v16    # "fileName":Ljava/lang/String;
    .end local v17    # "total":J
    .restart local v2    # "fileUrl":Ljava/lang/String;
    .restart local v3    # "fileName":Ljava/lang/String;
    .restart local v12    # "total":J
    .restart local v15    # "count":I
    :cond_7
    move-object v14, v2

    move-object/from16 v16, v3

    .end local v2    # "fileUrl":Ljava/lang/String;
    .end local v3    # "fileName":Ljava/lang/String;
    .restart local v14    # "fileUrl":Ljava/lang/String;
    .restart local v16    # "fileName":Ljava/lang/String;
    if-eqz v6, :cond_8

    .line 96
    invoke-virtual {v6}, Ljava/io/OutputStream;->flush()V

    .line 97
    invoke-virtual {v6}, Ljava/io/OutputStream;->close()V
    :try_end_7
    .catch Ljava/lang/Exception; {:try_start_7 .. :try_end_7} :catch_2
    .catchall {:try_start_7 .. :try_end_7} :catchall_2

    goto :goto_6

    .line 99
    .end local v9    # "url":Ljava/net/URL;
    .end local v10    # "fileLength":I
    .end local v11    # "data":[B
    .end local v12    # "total":J
    .end local v15    # "count":I
    :catch_2
    move-exception v0

    goto :goto_9

    .line 104
    :cond_8
    :goto_6
    nop

    .line 105
    :try_start_8
    invoke-virtual {v7}, Ljava/io/InputStream;->close()V

    .line 107
    if-eqz v8, :cond_9

    .line 108
    invoke-virtual {v8}, Ljava/net/HttpURLConnection;->disconnect()V
    :try_end_8
    .catch Ljava/lang/Exception; {:try_start_8 .. :try_end_8} :catch_3

    .line 111
    :cond_9
    goto :goto_7

    .line 110
    :catch_3
    move-exception v0

    .line 112
    nop

    .line 115
    :goto_7
    if-eqz v4, :cond_a

    invoke-virtual {v4}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_8

    :cond_a
    const/4 v0, 0x0

    :goto_8
    return-object v0

    .line 103
    .end local v14    # "fileUrl":Ljava/lang/String;
    .end local v16    # "fileName":Ljava/lang/String;
    .restart local v2    # "fileUrl":Ljava/lang/String;
    .restart local v3    # "fileName":Ljava/lang/String;
    :catchall_1
    move-exception v0

    move-object v14, v2

    move-object/from16 v16, v3

    move-object v2, v0

    .end local v2    # "fileUrl":Ljava/lang/String;
    .end local v3    # "fileName":Ljava/lang/String;
    .restart local v14    # "fileUrl":Ljava/lang/String;
    .restart local v16    # "fileName":Ljava/lang/String;
    goto :goto_c

    .line 99
    .end local v14    # "fileUrl":Ljava/lang/String;
    .end local v16    # "fileName":Ljava/lang/String;
    .restart local v2    # "fileUrl":Ljava/lang/String;
    .restart local v3    # "fileName":Ljava/lang/String;
    :catch_4
    move-exception v0

    move-object v14, v2

    move-object/from16 v16, v3

    .end local v2    # "fileUrl":Ljava/lang/String;
    .end local v3    # "fileName":Ljava/lang/String;
    .restart local v14    # "fileUrl":Ljava/lang/String;
    .restart local v16    # "fileName":Ljava/lang/String;
    :goto_9
    move-object v2, v0

    .line 100
    .local v2, "e":Ljava/lang/Exception;
    :try_start_9
    invoke-virtual {v2}, Ljava/lang/Exception;->printStackTrace()V

    .line 101
    invoke-virtual {v2}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object v3
    :try_end_9
    .catchall {:try_start_9 .. :try_end_9} :catchall_2

    .line 104
    if-eqz v7, :cond_b

    .line 105
    :try_start_a
    invoke-virtual {v7}, Ljava/io/InputStream;->close()V

    goto :goto_a

    .line 110
    :catch_5
    move-exception v0

    goto :goto_b

    .line 107
    :cond_b
    :goto_a
    if-eqz v8, :cond_c

    .line 108
    invoke-virtual {v8}, Ljava/net/HttpURLConnection;->disconnect()V
    :try_end_a
    .catch Ljava/lang/Exception; {:try_start_a .. :try_end_a} :catch_5

    .line 111
    :cond_c
    nop

    .line 101
    :goto_b
    return-object v3

    .line 103
    .end local v2    # "e":Ljava/lang/Exception;
    :catchall_2
    move-exception v0

    move-object v2, v0

    .line 104
    :goto_c
    if-eqz v7, :cond_d

    .line 105
    :try_start_b
    invoke-virtual {v7}, Ljava/io/InputStream;->close()V

    goto :goto_d

    .line 110
    :catch_6
    move-exception v0

    goto :goto_e

    .line 107
    :cond_d
    :goto_d
    if-eqz v8, :cond_e

    .line 108
    invoke-virtual {v8}, Ljava/net/HttpURLConnection;->disconnect()V
    :try_end_b
    .catch Ljava/lang/Exception; {:try_start_b .. :try_end_b} :catch_6

    .line 111
    :cond_e
    nop

    .line 112
    :goto_e
    throw v2
.end method

.method protected bridge synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x1000
        }
        names = {
            null
        }
    .end annotation

    .line 19
    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Lcom/example/keyverificationonline/DownloadFileTask;->onPostExecute(Ljava/lang/String;)V

    return-void
.end method

.method protected onPostExecute(Ljava/lang/String;)V
    .locals 1
    .param p1, "filePath"    # Ljava/lang/String;

    .line 127
    iget-object v0, p0, Lcom/example/keyverificationonline/DownloadFileTask;->listener:Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;

    invoke-interface {v0, p1}, Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;->onDownloadFinished(Ljava/lang/String;)V

    .line 128
    return-void
.end method

.method protected onPreExecute()V
    .locals 0

    .line 31
    invoke-super {p0}, Landroid/os/AsyncTask;->onPreExecute()V

    .line 32
    return-void
.end method

.method protected varargs onProgressUpdate([Ljava/lang/Integer;)V
    .locals 2
    .param p1, "progress"    # [Ljava/lang/Integer;

    .line 120
    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onProgressUpdate([Ljava/lang/Object;)V

    .line 122
    iget-object v0, p0, Lcom/example/keyverificationonline/DownloadFileTask;->listener:Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;

    const/4 v1, 0x0

    aget-object v1, p1, v1

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I

    move-result v1

    invoke-interface {v0, v1}, Lcom/example/keyverificationonline/DownloadFileTask$DownloadListener;->onProgress(I)V

    .line 123
    return-void
.end method

.method protected bridge synthetic onProgressUpdate([Ljava/lang/Object;)V
    .locals 0
    .annotation system Ldalvik/annotation/MethodParameters;
        accessFlags = {
            0x1000
        }
        names = {
            null
        }
    .end annotation

    .line 19
    check-cast p1, [Ljava/lang/Integer;

    invoke-virtual {p0, p1}, Lcom/example/keyverificationonline/DownloadFileTask;->onProgressUpdate([Ljava/lang/Integer;)V

    return-void
.end method

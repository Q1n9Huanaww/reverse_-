.class public final Lokhttp366/MultipartBody$Builder;
.super Ljava/lang/Object;
.source "MultipartBody.java"


# annotations
.annotation system Ldalvik/annotation/EnclosingClass;
    value = Lokhttp366/MultipartBody;
.end annotation

.annotation system Ldalvik/annotation/InnerClass;
    accessFlags = 0x19
    name = "Builder"
.end annotation


# instance fields
.field private final boundary:Lokio66/ByteString;

.field private final parts:Ljava/util/List;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Ljava/util/List<",
            "Lokhttp366/MultipartBody$Part;",
            ">;"
        }
    .end annotation
.end field

.field private type:Lokhttp366/MediaType;


# direct methods
.method public constructor <init>()V
    .locals 1

    .line 288
    invoke-static {}, Ljava/util/UUID;->randomUUID()Ljava/util/UUID;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/UUID;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-direct {p0, v0}, Lokhttp366/MultipartBody$Builder;-><init>(Ljava/lang/String;)V

    .line 289
    return-void
.end method

.method public constructor <init>(Ljava/lang/String;)V
    .locals 1
    .param p1, "boundary"    # Ljava/lang/String;

    .line 291
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 284
    sget-object v0, Lokhttp366/MultipartBody;->MIXED:Lokhttp366/MediaType;

    iput-object v0, p0, Lokhttp366/MultipartBody$Builder;->type:Lokhttp366/MediaType;

    .line 285
    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Lokhttp366/MultipartBody$Builder;->parts:Ljava/util/List;

    .line 292
    invoke-static {p1}, Lokio66/ByteString;->encodeUtf8(Ljava/lang/String;)Lokio66/ByteString;

    move-result-object v0

    iput-object v0, p0, Lokhttp366/MultipartBody$Builder;->boundary:Lokio66/ByteString;

    .line 293
    return-void
.end method


# virtual methods
.method public addFormDataPart(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/MultipartBody$Builder;
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;

    .line 322
    invoke-static {p1, p2}, Lokhttp366/MultipartBody$Part;->createFormData(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/MultipartBody$Part;

    move-result-object v0

    invoke-virtual {p0, v0}, Lokhttp366/MultipartBody$Builder;->addPart(Lokhttp366/MultipartBody$Part;)Lokhttp366/MultipartBody$Builder;

    move-result-object v0

    return-object v0
.end method

.method public addFormDataPart(Ljava/lang/String;Ljava/lang/String;Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Builder;
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "filename"    # Ljava/lang/String;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p3, "body"    # Lokhttp366/RequestBody;

    .line 327
    invoke-static {p1, p2, p3}, Lokhttp366/MultipartBody$Part;->createFormData(Ljava/lang/String;Ljava/lang/String;Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Part;

    move-result-object v0

    invoke-virtual {p0, v0}, Lokhttp366/MultipartBody$Builder;->addPart(Lokhttp366/MultipartBody$Part;)Lokhttp366/MultipartBody$Builder;

    move-result-object v0

    return-object v0
.end method

.method public addPart(Lokhttp366/Headers;Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Builder;
    .locals 1
    .param p1, "headers"    # Lokhttp366/Headers;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p2, "body"    # Lokhttp366/RequestBody;

    .line 317
    invoke-static {p1, p2}, Lokhttp366/MultipartBody$Part;->create(Lokhttp366/Headers;Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Part;

    move-result-object v0

    invoke-virtual {p0, v0}, Lokhttp366/MultipartBody$Builder;->addPart(Lokhttp366/MultipartBody$Part;)Lokhttp366/MultipartBody$Builder;

    move-result-object v0

    return-object v0
.end method

.method public addPart(Lokhttp366/MultipartBody$Part;)Lokhttp366/MultipartBody$Builder;
    .locals 2
    .param p1, "part"    # Lokhttp366/MultipartBody$Part;

    .line 332
    if-eqz p1, :cond_0

    .line 333
    iget-object v0, p0, Lokhttp366/MultipartBody$Builder;->parts:Ljava/util/List;

    invoke-interface {v0, p1}, Ljava/util/List;->add(Ljava/lang/Object;)Z

    .line 334
    return-object p0

    .line 332
    :cond_0
    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "part == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public addPart(Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Builder;
    .locals 1
    .param p1, "body"    # Lokhttp366/RequestBody;

    .line 312
    invoke-static {p1}, Lokhttp366/MultipartBody$Part;->create(Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Part;

    move-result-object v0

    invoke-virtual {p0, v0}, Lokhttp366/MultipartBody$Builder;->addPart(Lokhttp366/MultipartBody$Part;)Lokhttp366/MultipartBody$Builder;

    move-result-object v0

    return-object v0
.end method

.method public build()Lokhttp366/MultipartBody;
    .locals 4

    .line 339
    iget-object v0, p0, Lokhttp366/MultipartBody$Builder;->parts:Ljava/util/List;

    invoke-interface {v0}, Ljava/util/List;->isEmpty()Z

    move-result v0

    if-nez v0, :cond_0

    .line 342
    new-instance v0, Lokhttp366/MultipartBody;

    iget-object v1, p0, Lokhttp366/MultipartBody$Builder;->boundary:Lokio66/ByteString;

    iget-object v2, p0, Lokhttp366/MultipartBody$Builder;->type:Lokhttp366/MediaType;

    iget-object v3, p0, Lokhttp366/MultipartBody$Builder;->parts:Ljava/util/List;

    invoke-direct {v0, v1, v2, v3}, Lokhttp366/MultipartBody;-><init>(Lokio66/ByteString;Lokhttp366/MediaType;Ljava/util/List;)V

    return-object v0

    .line 340
    :cond_0
    new-instance v0, Ljava/lang/IllegalStateException;

    const-string v1, "Multipart body must have at least one part."

    invoke-direct {v0, v1}, Ljava/lang/IllegalStateException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public setType(Lokhttp366/MediaType;)Lokhttp366/MultipartBody$Builder;
    .locals 3
    .param p1, "type"    # Lokhttp366/MediaType;

    .line 300
    if-eqz p1, :cond_1

    .line 303
    invoke-virtual {p1}, Lokhttp366/MediaType;->type()Ljava/lang/String;

    move-result-object v0

    const-string v1, "multipart"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 306
    iput-object p1, p0, Lokhttp366/MultipartBody$Builder;->type:Lokhttp366/MediaType;

    .line 307
    return-object p0

    .line 304
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "multipart != "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 301
    :cond_1
    new-instance v0, Ljava/lang/NullPointerException;

    const-string v1, "type == null"

    invoke-direct {v0, v1}, Ljava/lang/NullPointerException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

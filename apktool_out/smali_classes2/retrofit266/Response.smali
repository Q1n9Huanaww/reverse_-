.class public final Lretrofit266/Response;
.super Ljava/lang/Object;
.source "Response.java"


# annotations
.annotation system Ldalvik/annotation/Signature;
    value = {
        "<T:",
        "Ljava/lang/Object;",
        ">",
        "Ljava/lang/Object;"
    }
.end annotation


# instance fields
.field private final body:Ljava/lang/Object;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "TT;"
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final errorBody:Lokhttp366/ResponseBody;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final rawResponse:Lokhttp366/Response;


# direct methods
.method private constructor <init>(Lokhttp366/Response;Ljava/lang/Object;Lokhttp366/ResponseBody;)V
    .locals 0
    .param p1, "rawResponse"    # Lokhttp366/Response;
    .param p2    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p3, "errorBody"    # Lokhttp366/ResponseBody;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Lokhttp366/Response;",
            "TT;",
            "Lokhttp366/ResponseBody;",
            ")V"
        }
    .end annotation

    .line 119
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    .local p2, "body":Ljava/lang/Object;, "TT;"
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 120
    iput-object p1, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    .line 121
    iput-object p2, p0, Lretrofit266/Response;->body:Ljava/lang/Object;

    .line 122
    iput-object p3, p0, Lretrofit266/Response;->errorBody:Lokhttp366/ResponseBody;

    .line 123
    return-void
.end method

.method public static error(ILokhttp366/ResponseBody;)Lretrofit266/Response;
    .locals 5
    .param p0, "code"    # I
    .param p1, "body"    # Lokhttp366/ResponseBody;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(I",
            "Lokhttp366/ResponseBody;",
            ")",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .line 91
    const-string v0, "body == null"

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 92
    const/16 v0, 0x190

    if-lt p0, v0, :cond_0

    .line 93
    new-instance v0, Lokhttp366/Response$Builder;

    invoke-direct {v0}, Lokhttp366/Response$Builder;-><init>()V

    new-instance v1, Lretrofit266/OkHttpCall$NoContentResponseBody;

    .line 96
    invoke-virtual {p1}, Lokhttp366/ResponseBody;->contentType()Lokhttp366/MediaType;

    move-result-object v2

    invoke-virtual {p1}, Lokhttp366/ResponseBody;->contentLength()J

    move-result-wide v3

    invoke-direct {v1, v2, v3, v4}, Lretrofit266/OkHttpCall$NoContentResponseBody;-><init>(Lokhttp366/MediaType;J)V

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->body(Lokhttp366/ResponseBody;)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 97
    invoke-virtual {v0, p0}, Lokhttp366/Response$Builder;->code(I)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 98
    const-string v1, "Response.error()"

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->message(Ljava/lang/String;)Lokhttp366/Response$Builder;

    move-result-object v0

    sget-object v1, Lokhttp366/Protocol;->HTTP_1_1:Lokhttp366/Protocol;

    .line 99
    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->protocol(Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v0

    new-instance v1, Lokhttp366/Request$Builder;

    invoke-direct {v1}, Lokhttp366/Request$Builder;-><init>()V

    .line 100
    const-string v2, "http://localhost/"

    invoke-virtual {v1, v2}, Lokhttp366/Request$Builder;->url(Ljava/lang/String;)Lokhttp366/Request$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp366/Request$Builder;->build()Lokhttp366/Request;

    move-result-object v1

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 101
    invoke-virtual {v0}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v0

    .line 93
    invoke-static {p1, v0}, Lretrofit266/Response;->error(Lokhttp366/ResponseBody;Lokhttp366/Response;)Lretrofit266/Response;

    move-result-object v0

    return-object v0

    .line 92
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "code < 400: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public static error(Lokhttp366/ResponseBody;Lokhttp366/Response;)Lretrofit266/Response;
    .locals 2
    .param p0, "body"    # Lokhttp366/ResponseBody;
    .param p1, "rawResponse"    # Lokhttp366/Response;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Lokhttp366/ResponseBody;",
            "Lokhttp366/Response;",
            ")",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .line 106
    const-string v0, "body == null"

    invoke-static {p0, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 107
    const-string v0, "rawResponse == null"

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 108
    invoke-virtual {p1}, Lokhttp366/Response;->isSuccessful()Z

    move-result v0

    if-nez v0, :cond_0

    .line 111
    new-instance v0, Lretrofit266/Response;

    const/4 v1, 0x0

    invoke-direct {v0, p1, v1, p0}, Lretrofit266/Response;-><init>(Lokhttp366/Response;Ljava/lang/Object;Lokhttp366/ResponseBody;)V

    return-object v0

    .line 109
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "rawResponse should not be successful response"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public static success(ILjava/lang/Object;)Lretrofit266/Response;
    .locals 3
    .param p0, "code"    # I
    .param p1    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(ITT;)",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .line 44
    .local p1, "body":Ljava/lang/Object;, "TT;"
    const/16 v0, 0xc8

    if-lt p0, v0, :cond_0

    const/16 v0, 0x12c

    if-ge p0, v0, :cond_0

    .line 47
    new-instance v0, Lokhttp366/Response$Builder;

    invoke-direct {v0}, Lokhttp366/Response$Builder;-><init>()V

    .line 50
    invoke-virtual {v0, p0}, Lokhttp366/Response$Builder;->code(I)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 51
    const-string v1, "Response.success()"

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->message(Ljava/lang/String;)Lokhttp366/Response$Builder;

    move-result-object v0

    sget-object v1, Lokhttp366/Protocol;->HTTP_1_1:Lokhttp366/Protocol;

    .line 52
    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->protocol(Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v0

    new-instance v1, Lokhttp366/Request$Builder;

    invoke-direct {v1}, Lokhttp366/Request$Builder;-><init>()V

    .line 53
    const-string v2, "http://localhost/"

    invoke-virtual {v1, v2}, Lokhttp366/Request$Builder;->url(Ljava/lang/String;)Lokhttp366/Request$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp366/Request$Builder;->build()Lokhttp366/Request;

    move-result-object v1

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 54
    invoke-virtual {v0}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v0

    .line 47
    invoke-static {p1, v0}, Lretrofit266/Response;->success(Ljava/lang/Object;Lokhttp366/Response;)Lretrofit266/Response;

    move-result-object v0

    return-object v0

    .line 45
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "code < 200 or >= 300: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1, p0}, Ljava/lang/StringBuilder;->append(I)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method

.method public static success(Ljava/lang/Object;)Lretrofit266/Response;
    .locals 3
    .param p0    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;)",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .line 29
    .local p0, "body":Ljava/lang/Object;, "TT;"
    new-instance v0, Lokhttp366/Response$Builder;

    invoke-direct {v0}, Lokhttp366/Response$Builder;-><init>()V

    .line 32
    const/16 v1, 0xc8

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->code(I)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 33
    const-string v1, "OK"

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->message(Ljava/lang/String;)Lokhttp366/Response$Builder;

    move-result-object v0

    sget-object v1, Lokhttp366/Protocol;->HTTP_1_1:Lokhttp366/Protocol;

    .line 34
    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->protocol(Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v0

    new-instance v1, Lokhttp366/Request$Builder;

    invoke-direct {v1}, Lokhttp366/Request$Builder;-><init>()V

    .line 35
    const-string v2, "http://localhost/"

    invoke-virtual {v1, v2}, Lokhttp366/Request$Builder;->url(Ljava/lang/String;)Lokhttp366/Request$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp366/Request$Builder;->build()Lokhttp366/Request;

    move-result-object v1

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 36
    invoke-virtual {v0}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v0

    .line 29
    invoke-static {p0, v0}, Lretrofit266/Response;->success(Ljava/lang/Object;Lokhttp366/Response;)Lretrofit266/Response;

    move-result-object v0

    return-object v0
.end method

.method public static success(Ljava/lang/Object;Lokhttp366/Headers;)Lretrofit266/Response;
    .locals 3
    .param p0    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p1, "headers"    # Lokhttp366/Headers;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;",
            "Lokhttp366/Headers;",
            ")",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .line 62
    .local p0, "body":Ljava/lang/Object;, "TT;"
    const-string v0, "headers == null"

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 63
    new-instance v0, Lokhttp366/Response$Builder;

    invoke-direct {v0}, Lokhttp366/Response$Builder;-><init>()V

    .line 66
    const/16 v1, 0xc8

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->code(I)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 67
    const-string v1, "OK"

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->message(Ljava/lang/String;)Lokhttp366/Response$Builder;

    move-result-object v0

    sget-object v1, Lokhttp366/Protocol;->HTTP_1_1:Lokhttp366/Protocol;

    .line 68
    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->protocol(Lokhttp366/Protocol;)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 69
    invoke-virtual {v0, p1}, Lokhttp366/Response$Builder;->headers(Lokhttp366/Headers;)Lokhttp366/Response$Builder;

    move-result-object v0

    new-instance v1, Lokhttp366/Request$Builder;

    invoke-direct {v1}, Lokhttp366/Request$Builder;-><init>()V

    .line 70
    const-string v2, "http://localhost/"

    invoke-virtual {v1, v2}, Lokhttp366/Request$Builder;->url(Ljava/lang/String;)Lokhttp366/Request$Builder;

    move-result-object v1

    invoke-virtual {v1}, Lokhttp366/Request$Builder;->build()Lokhttp366/Request;

    move-result-object v1

    invoke-virtual {v0, v1}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v0

    .line 71
    invoke-virtual {v0}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v0

    .line 63
    invoke-static {p0, v0}, Lretrofit266/Response;->success(Ljava/lang/Object;Lokhttp366/Response;)Lretrofit266/Response;

    move-result-object v0

    return-object v0
.end method

.method public static success(Ljava/lang/Object;Lokhttp366/Response;)Lretrofit266/Response;
    .locals 2
    .param p0    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p1, "rawResponse"    # Lokhttp366/Response;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(TT;",
            "Lokhttp366/Response;",
            ")",
            "Lretrofit266/Response<",
            "TT;>;"
        }
    .end annotation

    .line 79
    .local p0, "body":Ljava/lang/Object;, "TT;"
    const-string v0, "rawResponse == null"

    invoke-static {p1, v0}, Ljava/util/Objects;->requireNonNull(Ljava/lang/Object;Ljava/lang/String;)Ljava/lang/Object;

    .line 80
    invoke-virtual {p1}, Lokhttp366/Response;->isSuccessful()Z

    move-result v0

    if-eqz v0, :cond_0

    .line 83
    new-instance v0, Lretrofit266/Response;

    const/4 v1, 0x0

    invoke-direct {v0, p1, p0, v1}, Lretrofit266/Response;-><init>(Lokhttp366/Response;Ljava/lang/Object;Lokhttp366/ResponseBody;)V

    return-object v0

    .line 81
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    const-string v1, "rawResponse must be successful response"

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0
.end method


# virtual methods
.method public body()Ljava/lang/Object;
    .locals 1
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "()TT;"
        }
    .end annotation

    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 152
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->body:Ljava/lang/Object;

    return-object v0
.end method

.method public code()I
    .locals 1

    .line 132
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    invoke-virtual {v0}, Lokhttp366/Response;->code()I

    move-result v0

    return v0
.end method

.method public errorBody()Lokhttp366/ResponseBody;
    .locals 1
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation

    .line 157
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->errorBody:Lokhttp366/ResponseBody;

    return-object v0
.end method

.method public headers()Lokhttp366/Headers;
    .locals 1

    .line 142
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    invoke-virtual {v0}, Lokhttp366/Response;->headers()Lokhttp366/Headers;

    move-result-object v0

    return-object v0
.end method

.method public isSuccessful()Z
    .locals 1

    .line 147
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    invoke-virtual {v0}, Lokhttp366/Response;->isSuccessful()Z

    move-result v0

    return v0
.end method

.method public message()Ljava/lang/String;
    .locals 1

    .line 137
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    invoke-virtual {v0}, Lokhttp366/Response;->message()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method public raw()Lokhttp366/Response;
    .locals 1

    .line 127
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    return-object v0
.end method

.method public toString()Ljava/lang/String;
    .locals 1

    .line 162
    .local p0, "this":Lretrofit266/Response;, "Lretrofit266/Response<TT;>;"
    iget-object v0, p0, Lretrofit266/Response;->rawResponse:Lokhttp366/Response;

    invoke-virtual {v0}, Lokhttp366/Response;->toString()Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

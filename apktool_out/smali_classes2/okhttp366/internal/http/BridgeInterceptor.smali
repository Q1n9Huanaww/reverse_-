.class public final Lokhttp366/internal/http/BridgeInterceptor;
.super Ljava/lang/Object;
.source "BridgeInterceptor.java"

# interfaces
.implements Lokhttp366/Interceptor;


# instance fields
.field private final cookieJar:Lokhttp366/CookieJar;


# direct methods
.method public constructor <init>(Lokhttp366/CookieJar;)V
    .locals 0
    .param p1, "cookieJar"    # Lokhttp366/CookieJar;

    .line 43
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 44
    iput-object p1, p0, Lokhttp366/internal/http/BridgeInterceptor;->cookieJar:Lokhttp366/CookieJar;

    .line 45
    return-void
.end method

.method private cookieHeader(Ljava/util/List;)Ljava/lang/String;
    .locals 6
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "(",
            "Ljava/util/List<",
            "Lokhttp366/Cookie;",
            ">;)",
            "Ljava/lang/String;"
        }
    .end annotation

    .line 118
    .local p1, "cookies":Ljava/util/List;, "Ljava/util/List<Lokhttp366/Cookie;>;"
    new-instance v0, Ljava/lang/StringBuilder;

    invoke-direct {v0}, Ljava/lang/StringBuilder;-><init>()V

    .line 119
    .local v0, "cookieHeader":Ljava/lang/StringBuilder;
    const/4 v1, 0x0

    .local v1, "i":I
    invoke-interface {p1}, Ljava/util/List;->size()I

    move-result v2

    .local v2, "size":I
    :goto_0
    if-ge v1, v2, :cond_1

    .line 120
    if-lez v1, :cond_0

    .line 121
    const-string v3, "; "

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 123
    :cond_0
    invoke-interface {p1, v1}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lokhttp366/Cookie;

    .line 124
    .local v3, "cookie":Lokhttp366/Cookie;
    invoke-virtual {v3}, Lokhttp366/Cookie;->name()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v0, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v4

    const/16 v5, 0x3d

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v4

    invoke-virtual {v3}, Lokhttp366/Cookie;->value()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v4, v5}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    .line 119
    .end local v3    # "cookie":Lokhttp366/Cookie;
    add-int/lit8 v1, v1, 0x1

    goto :goto_0

    .line 126
    .end local v1    # "i":I
    .end local v2    # "size":I
    :cond_1
    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    return-object v1
.end method


# virtual methods
.method public intercept(Lokhttp366/Interceptor$Chain;)Lokhttp366/Response;
    .locals 14
    .param p1, "chain"    # Lokhttp366/Interceptor$Chain;
    .annotation system Ldalvik/annotation/Throws;
        value = {
            Ljava/io/IOException;
        }
    .end annotation

    .line 48
    invoke-interface {p1}, Lokhttp366/Interceptor$Chain;->request()Lokhttp366/Request;

    move-result-object v0

    .line 49
    .local v0, "userRequest":Lokhttp366/Request;
    invoke-virtual {v0}, Lokhttp366/Request;->newBuilder()Lokhttp366/Request$Builder;

    move-result-object v1

    .line 51
    .local v1, "requestBuilder":Lokhttp366/Request$Builder;
    invoke-virtual {v0}, Lokhttp366/Request;->body()Lokhttp366/RequestBody;

    move-result-object v2

    .line 52
    .local v2, "body":Lokhttp366/RequestBody;
    const-string v3, "Content-Type"

    const-wide/16 v4, -0x1

    const-string v6, "Content-Length"

    if-eqz v2, :cond_2

    .line 53
    invoke-virtual {v2}, Lokhttp366/RequestBody;->contentType()Lokhttp366/MediaType;

    move-result-object v7

    .line 54
    .local v7, "contentType":Lokhttp366/MediaType;
    if-eqz v7, :cond_0

    .line 55
    invoke-virtual {v7}, Lokhttp366/MediaType;->toString()Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v1, v3, v8}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 58
    :cond_0
    invoke-virtual {v2}, Lokhttp366/RequestBody;->contentLength()J

    move-result-wide v8

    .line 59
    .local v8, "contentLength":J
    const-string v10, "Transfer-Encoding"

    cmp-long v11, v8, v4

    if-eqz v11, :cond_1

    .line 60
    invoke-static {v8, v9}, Ljava/lang/Long;->toString(J)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v1, v6, v11}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 61
    invoke-virtual {v1, v10}, Lokhttp366/Request$Builder;->removeHeader(Ljava/lang/String;)Lokhttp366/Request$Builder;

    goto :goto_0

    .line 63
    :cond_1
    const-string v11, "chunked"

    invoke-virtual {v1, v10, v11}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 64
    invoke-virtual {v1, v6}, Lokhttp366/Request$Builder;->removeHeader(Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 68
    .end local v7    # "contentType":Lokhttp366/MediaType;
    .end local v8    # "contentLength":J
    :cond_2
    :goto_0
    const-string v7, "Host"

    invoke-virtual {v0, v7}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    if-nez v8, :cond_3

    .line 69
    invoke-virtual {v0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v8

    const/4 v9, 0x0

    invoke-static {v8, v9}, Lokhttp366/internal/Util;->hostHeader(Lokhttp366/HttpUrl;Z)Ljava/lang/String;

    move-result-object v8

    invoke-virtual {v1, v7, v8}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 72
    :cond_3
    const-string v7, "Connection"

    invoke-virtual {v0, v7}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v8

    if-nez v8, :cond_4

    .line 73
    const-string v8, "Keep-Alive"

    invoke-virtual {v1, v7, v8}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 78
    :cond_4
    const/4 v7, 0x0

    .line 79
    .local v7, "transparentGzip":Z
    const-string v8, "Accept-Encoding"

    invoke-virtual {v0, v8}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    const-string v10, "gzip"

    if-nez v9, :cond_5

    const-string v9, "Range"

    invoke-virtual {v0, v9}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v9

    if-nez v9, :cond_5

    .line 80
    const/4 v7, 0x1

    .line 81
    invoke-virtual {v1, v8, v10}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 84
    :cond_5
    iget-object v8, p0, Lokhttp366/internal/http/BridgeInterceptor;->cookieJar:Lokhttp366/CookieJar;

    invoke-virtual {v0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v9

    invoke-interface {v8, v9}, Lokhttp366/CookieJar;->loadForRequest(Lokhttp366/HttpUrl;)Ljava/util/List;

    move-result-object v8

    .line 85
    .local v8, "cookies":Ljava/util/List;, "Ljava/util/List<Lokhttp366/Cookie;>;"
    invoke-interface {v8}, Ljava/util/List;->isEmpty()Z

    move-result v9

    if-nez v9, :cond_6

    .line 86
    const-string v9, "Cookie"

    invoke-direct {p0, v8}, Lokhttp366/internal/http/BridgeInterceptor;->cookieHeader(Ljava/util/List;)Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v1, v9, v11}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 89
    :cond_6
    const-string v9, "User-Agent"

    invoke-virtual {v0, v9}, Lokhttp366/Request;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v11

    if-nez v11, :cond_7

    .line 90
    invoke-static {}, Lokhttp366/internal/Version;->userAgent()Ljava/lang/String;

    move-result-object v11

    invoke-virtual {v1, v9, v11}, Lokhttp366/Request$Builder;->header(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Request$Builder;

    .line 93
    :cond_7
    invoke-virtual {v1}, Lokhttp366/Request$Builder;->build()Lokhttp366/Request;

    move-result-object v9

    invoke-interface {p1, v9}, Lokhttp366/Interceptor$Chain;->proceed(Lokhttp366/Request;)Lokhttp366/Response;

    move-result-object v9

    .line 95
    .local v9, "networkResponse":Lokhttp366/Response;
    iget-object v11, p0, Lokhttp366/internal/http/BridgeInterceptor;->cookieJar:Lokhttp366/CookieJar;

    invoke-virtual {v0}, Lokhttp366/Request;->url()Lokhttp366/HttpUrl;

    move-result-object v12

    invoke-virtual {v9}, Lokhttp366/Response;->headers()Lokhttp366/Headers;

    move-result-object v13

    invoke-static {v11, v12, v13}, Lokhttp366/internal/http/HttpHeaders;->receiveHeaders(Lokhttp366/CookieJar;Lokhttp366/HttpUrl;Lokhttp366/Headers;)V

    .line 97
    invoke-virtual {v9}, Lokhttp366/Response;->newBuilder()Lokhttp366/Response$Builder;

    move-result-object v11

    .line 98
    invoke-virtual {v11, v0}, Lokhttp366/Response$Builder;->request(Lokhttp366/Request;)Lokhttp366/Response$Builder;

    move-result-object v11

    .line 100
    .local v11, "responseBuilder":Lokhttp366/Response$Builder;
    if-eqz v7, :cond_8

    .line 101
    const-string v12, "Content-Encoding"

    invoke-virtual {v9, v12}, Lokhttp366/Response;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v13

    invoke-virtual {v10, v13}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v10

    if-eqz v10, :cond_8

    .line 102
    invoke-static {v9}, Lokhttp366/internal/http/HttpHeaders;->hasBody(Lokhttp366/Response;)Z

    move-result v10

    if-eqz v10, :cond_8

    .line 103
    new-instance v10, Lokio66/GzipSource;

    invoke-virtual {v9}, Lokhttp366/Response;->body()Lokhttp366/ResponseBody;

    move-result-object v13

    invoke-virtual {v13}, Lokhttp366/ResponseBody;->source()Lokio66/BufferedSource;

    move-result-object v13

    invoke-direct {v10, v13}, Lokio66/GzipSource;-><init>(Lokio66/Source;)V

    .line 104
    .local v10, "responseBody":Lokio66/GzipSource;
    invoke-virtual {v9}, Lokhttp366/Response;->headers()Lokhttp366/Headers;

    move-result-object v13

    invoke-virtual {v13}, Lokhttp366/Headers;->newBuilder()Lokhttp366/Headers$Builder;

    move-result-object v13

    .line 105
    invoke-virtual {v13, v12}, Lokhttp366/Headers$Builder;->removeAll(Ljava/lang/String;)Lokhttp366/Headers$Builder;

    move-result-object v12

    .line 106
    invoke-virtual {v12, v6}, Lokhttp366/Headers$Builder;->removeAll(Ljava/lang/String;)Lokhttp366/Headers$Builder;

    move-result-object v6

    .line 107
    invoke-virtual {v6}, Lokhttp366/Headers$Builder;->build()Lokhttp366/Headers;

    move-result-object v6

    .line 108
    .local v6, "strippedHeaders":Lokhttp366/Headers;
    invoke-virtual {v11, v6}, Lokhttp366/Response$Builder;->headers(Lokhttp366/Headers;)Lokhttp366/Response$Builder;

    .line 109
    invoke-virtual {v9, v3}, Lokhttp366/Response;->header(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    .line 110
    .local v3, "contentType":Ljava/lang/String;
    new-instance v12, Lokhttp366/internal/http/RealResponseBody;

    invoke-static {v10}, Lokio66/Okio;->buffer(Lokio66/Source;)Lokio66/BufferedSource;

    move-result-object v13

    invoke-direct {v12, v3, v4, v5, v13}, Lokhttp366/internal/http/RealResponseBody;-><init>(Ljava/lang/String;JLokio66/BufferedSource;)V

    invoke-virtual {v11, v12}, Lokhttp366/Response$Builder;->body(Lokhttp366/ResponseBody;)Lokhttp366/Response$Builder;

    .line 113
    .end local v3    # "contentType":Ljava/lang/String;
    .end local v6    # "strippedHeaders":Lokhttp366/Headers;
    .end local v10    # "responseBody":Lokio66/GzipSource;
    :cond_8
    invoke-virtual {v11}, Lokhttp366/Response$Builder;->build()Lokhttp366/Response;

    move-result-object v3

    return-object v3
.end method

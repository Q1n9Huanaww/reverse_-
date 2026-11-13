.class final Lretrofit266/RequestBuilder;
.super Ljava/lang/Object;
.source "RequestBuilder.java"


# annotations
.annotation system Ldalvik/annotation/MemberClasses;
    value = {
        Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;
    }
.end annotation


# static fields
.field private static final HEX_DIGITS:[C

.field private static final PATH_SEGMENT_ALWAYS_ENCODE_SET:Ljava/lang/String; = " \"<>^`{}|\\?#"

.field private static final PATH_TRAVERSAL:Ljava/util/regex/Pattern;


# instance fields
.field private final baseUrl:Lokhttp366/HttpUrl;

.field private body:Lokhttp366/RequestBody;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private contentType:Lokhttp366/MediaType;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private formBuilder:Lokhttp366/FormBody$Builder;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final hasBody:Z

.field private final headersBuilder:Lokhttp366/Headers$Builder;

.field private final method:Ljava/lang/String;

.field private multipartBuilder:Lokhttp366/MultipartBody$Builder;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private relativeUrl:Ljava/lang/String;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field

.field private final requestBuilder:Lokhttp366/Request$Builder;

.field private urlBuilder:Lokhttp366/HttpUrl$Builder;
    .annotation runtime Ljavax/annotation/Nullable;
    .end annotation
.end field


# direct methods
.method static constructor <clinit>()V
    .locals 1

    .line 32
    const/16 v0, 0x10

    new-array v0, v0, [C

    fill-array-data v0, :array_0

    sput-object v0, Lretrofit266/RequestBuilder;->HEX_DIGITS:[C

    .line 50
    const-string v0, "(.*/)?(\\.|%2e|%2E){1,2}(/.*)?"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Lretrofit266/RequestBuilder;->PATH_TRAVERSAL:Ljava/util/regex/Pattern;

    return-void

    :array_0
    .array-data 2
        0x30s
        0x31s
        0x32s
        0x33s
        0x34s
        0x35s
        0x36s
        0x37s
        0x38s
        0x39s
        0x41s
        0x42s
        0x43s
        0x44s
        0x45s
        0x46s
    .end array-data
.end method

.method constructor <init>(Ljava/lang/String;Lokhttp366/HttpUrl;Ljava/lang/String;Lokhttp366/Headers;Lokhttp366/MediaType;ZZZ)V
    .locals 2
    .param p1, "method"    # Ljava/lang/String;
    .param p2, "baseUrl"    # Lokhttp366/HttpUrl;
    .param p3, "relativeUrl"    # Ljava/lang/String;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p4, "headers"    # Lokhttp366/Headers;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p5, "contentType"    # Lokhttp366/MediaType;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p6, "hasBody"    # Z
    .param p7, "isFormEncoded"    # Z
    .param p8, "isMultipart"    # Z

    .line 75
    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    .line 76
    iput-object p1, p0, Lretrofit266/RequestBuilder;->method:Ljava/lang/String;

    .line 77
    iput-object p2, p0, Lretrofit266/RequestBuilder;->baseUrl:Lokhttp366/HttpUrl;

    .line 78
    iput-object p3, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    .line 79
    new-instance v0, Lokhttp366/Request$Builder;

    invoke-direct {v0}, Lokhttp366/Request$Builder;-><init>()V

    iput-object v0, p0, Lretrofit266/RequestBuilder;->requestBuilder:Lokhttp366/Request$Builder;

    .line 80
    iput-object p5, p0, Lretrofit266/RequestBuilder;->contentType:Lokhttp366/MediaType;

    .line 81
    iput-boolean p6, p0, Lretrofit266/RequestBuilder;->hasBody:Z

    .line 83
    if-eqz p4, :cond_0

    .line 84
    invoke-virtual {p4}, Lokhttp366/Headers;->newBuilder()Lokhttp366/Headers$Builder;

    move-result-object v0

    iput-object v0, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    goto :goto_0

    .line 86
    :cond_0
    new-instance v0, Lokhttp366/Headers$Builder;

    invoke-direct {v0}, Lokhttp366/Headers$Builder;-><init>()V

    iput-object v0, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    .line 89
    :goto_0
    if-eqz p7, :cond_1

    .line 91
    new-instance v0, Lokhttp366/FormBody$Builder;

    invoke-direct {v0}, Lokhttp366/FormBody$Builder;-><init>()V

    iput-object v0, p0, Lretrofit266/RequestBuilder;->formBuilder:Lokhttp366/FormBody$Builder;

    goto :goto_1

    .line 92
    :cond_1
    if-eqz p8, :cond_2

    .line 94
    new-instance v0, Lokhttp366/MultipartBody$Builder;

    invoke-direct {v0}, Lokhttp366/MultipartBody$Builder;-><init>()V

    iput-object v0, p0, Lretrofit266/RequestBuilder;->multipartBuilder:Lokhttp366/MultipartBody$Builder;

    .line 95
    iget-object v0, p0, Lretrofit266/RequestBuilder;->multipartBuilder:Lokhttp366/MultipartBody$Builder;

    sget-object v1, Lokhttp366/MultipartBody;->FORM:Lokhttp366/MediaType;

    invoke-virtual {v0, v1}, Lokhttp366/MultipartBody$Builder;->setType(Lokhttp366/MediaType;)Lokhttp366/MultipartBody$Builder;

    .line 97
    :cond_2
    :goto_1
    return-void
.end method

.method private static canonicalizeForPath(Ljava/lang/String;Z)Ljava/lang/String;
    .locals 5
    .param p0, "input"    # Ljava/lang/String;
    .param p1, "alreadyEncoded"    # Z

    .line 137
    const/4 v0, 0x0

    .local v0, "i":I
    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result v1

    .local v1, "limit":I
    :goto_0
    if-ge v0, v1, :cond_2

    .line 138
    invoke-virtual {p0, v0}, Ljava/lang/String;->codePointAt(I)I

    move-result v2

    .line 139
    .local v2, "codePoint":I
    const/16 v3, 0x20

    if-lt v2, v3, :cond_1

    const/16 v3, 0x7f

    if-ge v2, v3, :cond_1

    .line 141
    const-string v3, " \"<>^`{}|\\?#"

    invoke-virtual {v3, v2}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    const/4 v4, -0x1

    if-ne v3, v4, :cond_1

    if-nez p1, :cond_0

    const/16 v3, 0x2f

    if-eq v2, v3, :cond_1

    const/16 v3, 0x25

    if-ne v2, v3, :cond_0

    goto :goto_1

    .line 137
    :cond_0
    invoke-static {v2}, Ljava/lang/Character;->charCount(I)I

    move-result v3

    add-int/2addr v0, v3

    goto :goto_0

    .line 144
    :cond_1
    :goto_1
    new-instance v3, Lokio66/Buffer;

    invoke-direct {v3}, Lokio66/Buffer;-><init>()V

    .line 145
    .local v3, "out":Lokio66/Buffer;
    const/4 v4, 0x0

    invoke-virtual {v3, p0, v4, v0}, Lokio66/Buffer;->writeUtf8(Ljava/lang/String;II)Lokio66/Buffer;

    .line 146
    invoke-static {v3, p0, v0, v1, p1}, Lretrofit266/RequestBuilder;->canonicalizeForPath(Lokio66/Buffer;Ljava/lang/String;IIZ)V

    .line 147
    invoke-virtual {v3}, Lokio66/Buffer;->readUtf8()Ljava/lang/String;

    move-result-object v4

    return-object v4

    .line 152
    .end local v0    # "i":I
    .end local v1    # "limit":I
    .end local v2    # "codePoint":I
    .end local v3    # "out":Lokio66/Buffer;
    :cond_2
    return-object p0
.end method

.method private static canonicalizeForPath(Lokio66/Buffer;Ljava/lang/String;IIZ)V
    .locals 7
    .param p0, "out"    # Lokio66/Buffer;
    .param p1, "input"    # Ljava/lang/String;
    .param p2, "pos"    # I
    .param p3, "limit"    # I
    .param p4, "alreadyEncoded"    # Z

    .line 157
    const/4 v0, 0x0

    .line 159
    .local v0, "utf8Buffer":Lokio66/Buffer;
    move v1, p2

    .local v1, "i":I
    :goto_0
    if-ge v1, p3, :cond_6

    .line 160
    invoke-virtual {p1, v1}, Ljava/lang/String;->codePointAt(I)I

    move-result v2

    .line 161
    .local v2, "codePoint":I
    if-eqz p4, :cond_1

    const/16 v3, 0x9

    if-eq v2, v3, :cond_0

    const/16 v3, 0xa

    if-eq v2, v3, :cond_0

    const/16 v3, 0xc

    if-eq v2, v3, :cond_0

    const/16 v3, 0xd

    if-ne v2, v3, :cond_1

    :cond_0
    goto :goto_3

    .line 164
    :cond_1
    const/16 v3, 0x20

    const/16 v4, 0x25

    if-lt v2, v3, :cond_3

    const/16 v3, 0x7f

    if-ge v2, v3, :cond_3

    .line 166
    const-string v3, " \"<>^`{}|\\?#"

    invoke-virtual {v3, v2}, Ljava/lang/String;->indexOf(I)I

    move-result v3

    const/4 v5, -0x1

    if-ne v3, v5, :cond_3

    if-nez p4, :cond_2

    const/16 v3, 0x2f

    if-eq v2, v3, :cond_3

    if-ne v2, v4, :cond_2

    goto :goto_1

    .line 181
    :cond_2
    invoke-virtual {p0, v2}, Lokio66/Buffer;->writeUtf8CodePoint(I)Lokio66/Buffer;

    goto :goto_3

    .line 169
    :cond_3
    :goto_1
    if-nez v0, :cond_4

    .line 170
    new-instance v3, Lokio66/Buffer;

    invoke-direct {v3}, Lokio66/Buffer;-><init>()V

    move-object v0, v3

    .line 172
    :cond_4
    invoke-virtual {v0, v2}, Lokio66/Buffer;->writeUtf8CodePoint(I)Lokio66/Buffer;

    .line 173
    :goto_2
    invoke-virtual {v0}, Lokio66/Buffer;->exhausted()Z

    move-result v3

    if-nez v3, :cond_5

    .line 174
    invoke-virtual {v0}, Lokio66/Buffer;->readByte()B

    move-result v3

    and-int/lit16 v3, v3, 0xff

    .line 175
    .local v3, "b":I
    invoke-virtual {p0, v4}, Lokio66/Buffer;->writeByte(I)Lokio66/Buffer;

    .line 176
    sget-object v5, Lretrofit266/RequestBuilder;->HEX_DIGITS:[C

    shr-int/lit8 v6, v3, 0x4

    and-int/lit8 v6, v6, 0xf

    aget-char v5, v5, v6

    invoke-virtual {p0, v5}, Lokio66/Buffer;->writeByte(I)Lokio66/Buffer;

    .line 177
    sget-object v5, Lretrofit266/RequestBuilder;->HEX_DIGITS:[C

    and-int/lit8 v6, v3, 0xf

    aget-char v5, v5, v6

    invoke-virtual {p0, v5}, Lokio66/Buffer;->writeByte(I)Lokio66/Buffer;

    .line 178
    .end local v3    # "b":I
    goto :goto_2

    .line 159
    :cond_5
    :goto_3
    invoke-static {v2}, Ljava/lang/Character;->charCount(I)I

    move-result v3

    add-int/2addr v1, v3

    goto :goto_0

    .line 184
    .end local v1    # "i":I
    .end local v2    # "codePoint":I
    :cond_6
    return-void
.end method


# virtual methods
.method addFormField(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 1
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
    .param p3, "encoded"    # Z

    .line 208
    if-eqz p3, :cond_0

    .line 209
    iget-object v0, p0, Lretrofit266/RequestBuilder;->formBuilder:Lokhttp366/FormBody$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/FormBody$Builder;->addEncoded(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/FormBody$Builder;

    goto :goto_0

    .line 211
    :cond_0
    iget-object v0, p0, Lretrofit266/RequestBuilder;->formBuilder:Lokhttp366/FormBody$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/FormBody$Builder;->add(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/FormBody$Builder;

    .line 213
    :goto_0
    return-void
.end method

.method addHeader(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 4
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
    .param p3, "allowUnsafeNonAsciiValues"    # Z

    .line 104
    const-string v0, "Content-Type"

    invoke-virtual {v0, p1}, Ljava/lang/String;->equalsIgnoreCase(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    .line 106
    :try_start_0
    invoke-static {p2}, Lokhttp366/MediaType;->get(Ljava/lang/String;)Lokhttp366/MediaType;

    move-result-object v0

    iput-object v0, p0, Lretrofit266/RequestBuilder;->contentType:Lokhttp366/MediaType;
    :try_end_0
    .catch Ljava/lang/IllegalArgumentException; {:try_start_0 .. :try_end_0} :catch_0

    .line 109
    goto :goto_0

    .line 107
    :catch_0
    move-exception v0

    .line 108
    .local v0, "e":Ljava/lang/IllegalArgumentException;
    new-instance v1, Ljava/lang/IllegalArgumentException;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "Malformed content type: "

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-direct {v1, v2, v0}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;Ljava/lang/Throwable;)V

    throw v1

    .line 110
    .end local v0    # "e":Ljava/lang/IllegalArgumentException;
    :cond_0
    if-eqz p3, :cond_1

    .line 111
    iget-object v0, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/Headers$Builder;->addUnsafeNonAscii(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Headers$Builder;

    goto :goto_0

    .line 113
    :cond_1
    iget-object v0, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/Headers$Builder;->add(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Headers$Builder;

    .line 115
    :goto_0
    return-void
.end method

.method addHeaders(Lokhttp366/Headers;)V
    .locals 1
    .param p1, "headers"    # Lokhttp366/Headers;

    .line 118
    iget-object v0, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    invoke-virtual {v0, p1}, Lokhttp366/Headers$Builder;->addAll(Lokhttp366/Headers;)Lokhttp366/Headers$Builder;

    .line 119
    return-void
.end method

.method addPart(Lokhttp366/Headers;Lokhttp366/RequestBody;)V
    .locals 1
    .param p1, "headers"    # Lokhttp366/Headers;
    .param p2, "body"    # Lokhttp366/RequestBody;

    .line 217
    iget-object v0, p0, Lretrofit266/RequestBuilder;->multipartBuilder:Lokhttp366/MultipartBody$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/MultipartBody$Builder;->addPart(Lokhttp366/Headers;Lokhttp366/RequestBody;)Lokhttp366/MultipartBody$Builder;

    .line 218
    return-void
.end method

.method addPart(Lokhttp366/MultipartBody$Part;)V
    .locals 1
    .param p1, "part"    # Lokhttp366/MultipartBody$Part;

    .line 222
    iget-object v0, p0, Lretrofit266/RequestBuilder;->multipartBuilder:Lokhttp366/MultipartBody$Builder;

    invoke-virtual {v0, p1}, Lokhttp366/MultipartBody$Builder;->addPart(Lokhttp366/MultipartBody$Part;)Lokhttp366/MultipartBody$Builder;

    .line 223
    return-void
.end method

.method addPathParam(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 5
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
    .param p3, "encoded"    # Z

    .line 122
    iget-object v0, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 126
    invoke-static {p2, p3}, Lretrofit266/RequestBuilder;->canonicalizeForPath(Ljava/lang/String;Z)Ljava/lang/String;

    move-result-object v0

    .line 127
    .local v0, "replacement":Ljava/lang/String;
    iget-object v1, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-direct {v2}, Ljava/lang/StringBuilder;-><init>()V

    const-string v3, "{"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2, p1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    const-string v3, "}"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    .line 128
    .local v1, "newRelativeUrl":Ljava/lang/String;
    sget-object v2, Lretrofit266/RequestBuilder;->PATH_TRAVERSAL:Ljava/util/regex/Pattern;

    invoke-virtual {v2, v1}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v2

    invoke-virtual {v2}, Ljava/util/regex/Matcher;->matches()Z

    move-result v2

    if-nez v2, :cond_0

    .line 132
    iput-object v1, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    .line 133
    return-void

    .line 129
    :cond_0
    new-instance v2, Ljava/lang/IllegalArgumentException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "@Path parameters shouldn\'t perform path traversal (\'.\' or \'..\'): "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3, p2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v2

    .line 124
    .end local v0    # "replacement":Ljava/lang/String;
    .end local v1    # "newRelativeUrl":Ljava/lang/String;
    :cond_1
    new-instance v0, Ljava/lang/AssertionError;

    invoke-direct {v0}, Ljava/lang/AssertionError;-><init>()V

    throw v0
.end method

.method addQueryParam(Ljava/lang/String;Ljava/lang/String;Z)V
    .locals 3
    .param p1, "name"    # Ljava/lang/String;
    .param p2, "value"    # Ljava/lang/String;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .param p3, "encoded"    # Z

    .line 187
    iget-object v0, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    if-eqz v0, :cond_1

    .line 189
    iget-object v0, p0, Lretrofit266/RequestBuilder;->baseUrl:Lokhttp366/HttpUrl;

    iget-object v1, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    invoke-virtual {v0, v1}, Lokhttp366/HttpUrl;->newBuilder(Ljava/lang/String;)Lokhttp366/HttpUrl$Builder;

    move-result-object v0

    iput-object v0, p0, Lretrofit266/RequestBuilder;->urlBuilder:Lokhttp366/HttpUrl$Builder;

    .line 190
    iget-object v0, p0, Lretrofit266/RequestBuilder;->urlBuilder:Lokhttp366/HttpUrl$Builder;

    if-eqz v0, :cond_0

    .line 194
    const/4 v0, 0x0

    iput-object v0, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    goto :goto_0

    .line 191
    :cond_0
    new-instance v0, Ljava/lang/IllegalArgumentException;

    new-instance v1, Ljava/lang/StringBuilder;

    invoke-direct {v1}, Ljava/lang/StringBuilder;-><init>()V

    const-string v2, "Malformed URL. Base: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lretrofit266/RequestBuilder;->baseUrl:Lokhttp366/HttpUrl;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v1

    const-string v2, ", Relative: "

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    iget-object v2, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    invoke-virtual {v1, v2}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v0

    .line 197
    :cond_1
    :goto_0
    if-eqz p3, :cond_2

    .line 199
    iget-object v0, p0, Lretrofit266/RequestBuilder;->urlBuilder:Lokhttp366/HttpUrl$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/HttpUrl$Builder;->addEncodedQueryParameter(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/HttpUrl$Builder;

    goto :goto_1

    .line 202
    :cond_2
    iget-object v0, p0, Lretrofit266/RequestBuilder;->urlBuilder:Lokhttp366/HttpUrl$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/HttpUrl$Builder;->addQueryParameter(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/HttpUrl$Builder;

    .line 204
    :goto_1
    return-void
.end method

.method addTag(Ljava/lang/Class;Ljava/lang/Object;)V
    .locals 1
    .param p2    # Ljava/lang/Object;
        .annotation runtime Ljavax/annotation/Nullable;
        .end annotation
    .end param
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "<T:",
            "Ljava/lang/Object;",
            ">(",
            "Ljava/lang/Class<",
            "TT;>;TT;)V"
        }
    .end annotation

    .line 230
    .local p1, "cls":Ljava/lang/Class;, "Ljava/lang/Class<TT;>;"
    .local p2, "value":Ljava/lang/Object;, "TT;"
    iget-object v0, p0, Lretrofit266/RequestBuilder;->requestBuilder:Lokhttp366/Request$Builder;

    invoke-virtual {v0, p1, p2}, Lokhttp366/Request$Builder;->tag(Ljava/lang/Class;Ljava/lang/Object;)Lokhttp366/Request$Builder;

    .line 231
    return-void
.end method

.method get()Lokhttp366/Request$Builder;
    .locals 7

    .line 235
    iget-object v0, p0, Lretrofit266/RequestBuilder;->urlBuilder:Lokhttp366/HttpUrl$Builder;

    .line 236
    .local v0, "urlBuilder":Lokhttp366/HttpUrl$Builder;
    if-eqz v0, :cond_0

    .line 237
    invoke-virtual {v0}, Lokhttp366/HttpUrl$Builder;->build()Lokhttp366/HttpUrl;

    move-result-object v1

    .local v1, "url":Lokhttp366/HttpUrl;
    goto :goto_0

    .line 241
    .end local v1    # "url":Lokhttp366/HttpUrl;
    :cond_0
    iget-object v1, p0, Lretrofit266/RequestBuilder;->baseUrl:Lokhttp366/HttpUrl;

    iget-object v2, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    invoke-virtual {v1, v2}, Lokhttp366/HttpUrl;->resolve(Ljava/lang/String;)Lokhttp366/HttpUrl;

    move-result-object v1

    .line 242
    .restart local v1    # "url":Lokhttp366/HttpUrl;
    if-eqz v1, :cond_6

    .line 248
    :goto_0
    iget-object v2, p0, Lretrofit266/RequestBuilder;->body:Lokhttp366/RequestBody;

    .line 249
    .local v2, "body":Lokhttp366/RequestBody;
    if-nez v2, :cond_3

    .line 251
    iget-object v3, p0, Lretrofit266/RequestBuilder;->formBuilder:Lokhttp366/FormBody$Builder;

    if-eqz v3, :cond_1

    .line 252
    iget-object v3, p0, Lretrofit266/RequestBuilder;->formBuilder:Lokhttp366/FormBody$Builder;

    invoke-virtual {v3}, Lokhttp366/FormBody$Builder;->build()Lokhttp366/FormBody;

    move-result-object v2

    goto :goto_1

    .line 253
    :cond_1
    iget-object v3, p0, Lretrofit266/RequestBuilder;->multipartBuilder:Lokhttp366/MultipartBody$Builder;

    if-eqz v3, :cond_2

    .line 254
    iget-object v3, p0, Lretrofit266/RequestBuilder;->multipartBuilder:Lokhttp366/MultipartBody$Builder;

    invoke-virtual {v3}, Lokhttp366/MultipartBody$Builder;->build()Lokhttp366/MultipartBody;

    move-result-object v2

    goto :goto_1

    .line 255
    :cond_2
    iget-boolean v3, p0, Lretrofit266/RequestBuilder;->hasBody:Z

    if-eqz v3, :cond_3

    .line 257
    const/4 v3, 0x0

    new-array v3, v3, [B

    const/4 v4, 0x0

    invoke-static {v4, v3}, Lokhttp366/RequestBody;->create(Lokhttp366/MediaType;[B)Lokhttp366/RequestBody;

    move-result-object v2

    .line 261
    :cond_3
    :goto_1
    iget-object v3, p0, Lretrofit266/RequestBuilder;->contentType:Lokhttp366/MediaType;

    .line 262
    .local v3, "contentType":Lokhttp366/MediaType;
    if-eqz v3, :cond_5

    .line 263
    if-eqz v2, :cond_4

    .line 264
    new-instance v4, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;

    invoke-direct {v4, v2, v3}, Lretrofit266/RequestBuilder$ContentTypeOverridingRequestBody;-><init>(Lokhttp366/RequestBody;Lokhttp366/MediaType;)V

    move-object v2, v4

    goto :goto_2

    .line 266
    :cond_4
    iget-object v4, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    const-string v5, "Content-Type"

    invoke-virtual {v3}, Lokhttp366/MediaType;->toString()Ljava/lang/String;

    move-result-object v6

    invoke-virtual {v4, v5, v6}, Lokhttp366/Headers$Builder;->add(Ljava/lang/String;Ljava/lang/String;)Lokhttp366/Headers$Builder;

    .line 270
    :cond_5
    :goto_2
    iget-object v4, p0, Lretrofit266/RequestBuilder;->requestBuilder:Lokhttp366/Request$Builder;

    invoke-virtual {v4, v1}, Lokhttp366/Request$Builder;->url(Lokhttp366/HttpUrl;)Lokhttp366/Request$Builder;

    move-result-object v4

    iget-object v5, p0, Lretrofit266/RequestBuilder;->headersBuilder:Lokhttp366/Headers$Builder;

    invoke-virtual {v5}, Lokhttp366/Headers$Builder;->build()Lokhttp366/Headers;

    move-result-object v5

    invoke-virtual {v4, v5}, Lokhttp366/Request$Builder;->headers(Lokhttp366/Headers;)Lokhttp366/Request$Builder;

    move-result-object v4

    iget-object v5, p0, Lretrofit266/RequestBuilder;->method:Ljava/lang/String;

    invoke-virtual {v4, v5, v2}, Lokhttp366/Request$Builder;->method(Ljava/lang/String;Lokhttp366/RequestBody;)Lokhttp366/Request$Builder;

    move-result-object v4

    return-object v4

    .line 243
    .end local v2    # "body":Lokhttp366/RequestBody;
    .end local v3    # "contentType":Lokhttp366/MediaType;
    :cond_6
    new-instance v2, Ljava/lang/IllegalArgumentException;

    new-instance v3, Ljava/lang/StringBuilder;

    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    const-string v4, "Malformed URL. Base: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, p0, Lretrofit266/RequestBuilder;->baseUrl:Lokhttp366/HttpUrl;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/Object;)Ljava/lang/StringBuilder;

    move-result-object v3

    const-string v4, ", Relative: "

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    iget-object v4, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/lang/IllegalArgumentException;-><init>(Ljava/lang/String;)V

    throw v2
.end method

.method setBody(Lokhttp366/RequestBody;)V
    .locals 0
    .param p1, "body"    # Lokhttp366/RequestBody;

    .line 226
    iput-object p1, p0, Lretrofit266/RequestBuilder;->body:Lokhttp366/RequestBody;

    .line 227
    return-void
.end method

.method setRelativeUrl(Ljava/lang/Object;)V
    .locals 1
    .param p1, "relativeUrl"    # Ljava/lang/Object;

    .line 100
    invoke-virtual {p1}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lretrofit266/RequestBuilder;->relativeUrl:Ljava/lang/String;

    .line 101
    return-void
.end method

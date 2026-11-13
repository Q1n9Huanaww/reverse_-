package okhttp366;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.annotation.Nullable;
import okhttp366.internal.Util;
import okio66.BufferedSink;
import okio66.ByteString;
import okio66.Okio;
import okio66.Source;

/* JADX INFO: loaded from: classes2.dex */
public abstract class RequestBody {
    @Nullable
    public abstract MediaType contentType();

    public abstract void writeTo(BufferedSink bufferedSink) throws IOException;

    public long contentLength() throws IOException {
        return -1L;
    }

    public boolean isDuplex() {
        return false;
    }

    public boolean isOneShot() {
        return false;
    }

    public static RequestBody create(@Nullable MediaType contentType, String content) {
        Charset charset = StandardCharsets.UTF_8;
        if (contentType != null && (charset = contentType.charset()) == null) {
            charset = StandardCharsets.UTF_8;
            contentType = MediaType.parse(contentType + "; charset=utf-8");
        }
        byte[] bytes = content.getBytes(charset);
        return create(contentType, bytes);
    }

    public static RequestBody create(@Nullable final MediaType contentType, final ByteString content) {
        return new RequestBody() { // from class: okhttp366.RequestBody.1
            @Override // okhttp366.RequestBody
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override // okhttp366.RequestBody
            public long contentLength() throws IOException {
                return content.size();
            }

            @Override // okhttp366.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content);
            }
        };
    }

    public static RequestBody create(@Nullable MediaType contentType, byte[] content) {
        return create(contentType, content, 0, content.length);
    }

    public static RequestBody create(@Nullable final MediaType contentType, final byte[] content, final int offset, final int byteCount) {
        if (content == null) {
            throw new NullPointerException("content == null");
        }
        Util.checkOffsetAndCount(content.length, offset, byteCount);
        return new RequestBody() { // from class: okhttp366.RequestBody.2
            @Override // okhttp366.RequestBody
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override // okhttp366.RequestBody
            public long contentLength() {
                return byteCount;
            }

            @Override // okhttp366.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                sink.write(content, offset, byteCount);
            }
        };
    }

    public static RequestBody create(@Nullable final MediaType contentType, final File file) {
        if (file == null) {
            throw new NullPointerException("file == null");
        }
        return new RequestBody() { // from class: okhttp366.RequestBody.3
            @Override // okhttp366.RequestBody
            @Nullable
            public MediaType contentType() {
                return contentType;
            }

            @Override // okhttp366.RequestBody
            public long contentLength() {
                return file.length();
            }

            @Override // okhttp366.RequestBody
            public void writeTo(BufferedSink sink) throws IOException {
                Source source = Okio.source(file);
                try {
                    sink.writeAll(source);
                    if (source != null) {
                        source.close();
                    }
                } catch (Throwable th) {
                    try {
                        throw th;
                    } catch (Throwable th2) {
                        if (source != null) {
                            try {
                                source.close();
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                            }
                        }
                        throw th2;
                    }
                }
            }
        };
    }
}

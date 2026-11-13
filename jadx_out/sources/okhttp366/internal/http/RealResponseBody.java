package okhttp366.internal.http;

import javax.annotation.Nullable;
import okhttp366.MediaType;
import okhttp366.ResponseBody;
import okio66.BufferedSource;

/* JADX INFO: loaded from: classes2.dex */
public final class RealResponseBody extends ResponseBody {
    private final long contentLength;

    @Nullable
    private final String contentTypeString;
    private final BufferedSource source;

    public RealResponseBody(@Nullable String contentTypeString, long contentLength, BufferedSource source) {
        this.contentTypeString = contentTypeString;
        this.contentLength = contentLength;
        this.source = source;
    }

    @Override // okhttp366.ResponseBody
    public MediaType contentType() {
        if (this.contentTypeString != null) {
            return MediaType.parse(this.contentTypeString);
        }
        return null;
    }

    @Override // okhttp366.ResponseBody
    public long contentLength() {
        return this.contentLength;
    }

    @Override // okhttp366.ResponseBody
    public BufferedSource source() {
        return this.source;
    }
}

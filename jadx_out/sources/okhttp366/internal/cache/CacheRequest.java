package okhttp366.internal.cache;

import java.io.IOException;
import okio66.Sink;

/* JADX INFO: loaded from: classes2.dex */
public interface CacheRequest {
    void abort();

    Sink body() throws IOException;
}

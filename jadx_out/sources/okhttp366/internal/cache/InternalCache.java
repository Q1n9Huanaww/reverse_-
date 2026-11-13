package okhttp366.internal.cache;

import java.io.IOException;
import javax.annotation.Nullable;
import okhttp366.Request;
import okhttp366.Response;

/* JADX INFO: loaded from: classes2.dex */
public interface InternalCache {
    @Nullable
    Response get(Request request) throws IOException;

    @Nullable
    CacheRequest put(Response response) throws IOException;

    void remove(Request request) throws IOException;

    void trackConditionalCacheHit();

    void trackResponse(CacheStrategy cacheStrategy);

    void update(Response response, Response response2);
}

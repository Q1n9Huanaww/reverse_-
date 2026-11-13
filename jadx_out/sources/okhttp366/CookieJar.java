package okhttp366;

import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface CookieJar {
    public static final CookieJar NO_COOKIES = new CookieJar() { // from class: okhttp366.CookieJar.1
        @Override // okhttp366.CookieJar
        public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
        }

        @Override // okhttp366.CookieJar
        public List<Cookie> loadForRequest(HttpUrl url) {
            return Collections.emptyList();
        }
    };

    List<Cookie> loadForRequest(HttpUrl httpUrl);

    void saveFromResponse(HttpUrl httpUrl, List<Cookie> list);
}

package okhttp366;

import java.io.IOException;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public interface Authenticator {
    public static final Authenticator NONE = new Authenticator() { // from class: okhttp366.Authenticator$$ExternalSyntheticLambda0
        @Override // okhttp366.Authenticator
        public final Request authenticate(Route route, Response response) {
            return Authenticator.CC.lambda$static$0(route, response);
        }
    };

    @Nullable
    Request authenticate(@Nullable Route route, Response response) throws IOException;

    /* JADX INFO: renamed from: okhttp366.Authenticator$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            Authenticator authenticator = Authenticator.NONE;
        }

        public static /* synthetic */ Request lambda$static$0(Route route, Response response) throws IOException {
            return null;
        }
    }
}

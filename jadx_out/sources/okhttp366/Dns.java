package okhttp366;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface Dns {
    public static final Dns SYSTEM = new Dns() { // from class: okhttp366.Dns$$ExternalSyntheticLambda0
        @Override // okhttp366.Dns
        public final List lookup(String str) {
            return Dns.CC.lambda$static$0(str);
        }
    };

    List<InetAddress> lookup(String str) throws UnknownHostException;

    /* JADX INFO: renamed from: okhttp366.Dns$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        static {
            Dns dns = Dns.SYSTEM;
        }

        public static /* synthetic */ List lambda$static$0(String hostname) throws UnknownHostException {
            if (hostname == null) {
                throw new UnknownHostException("hostname == null");
            }
            try {
                return Arrays.asList(InetAddress.getAllByName(hostname));
            } catch (NullPointerException e) {
                UnknownHostException unknownHostException = new UnknownHostException("Broken system behaviour for dns lookup of " + hostname);
                unknownHostException.initCause(e);
                throw unknownHostException;
            }
        }
    }
}

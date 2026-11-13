package okhttp366.internal.connection;

import java.util.LinkedHashSet;
import java.util.Set;
import okhttp366.Route;

/* JADX INFO: loaded from: classes2.dex */
final class RouteDatabase {
    private final Set<Route> failedRoutes = new LinkedHashSet();

    RouteDatabase() {
    }

    public synchronized void failed(Route failedRoute) {
        this.failedRoutes.add(failedRoute);
    }

    public synchronized void connected(Route route) {
        this.failedRoutes.remove(route);
    }

    public synchronized boolean shouldPostpone(Route route) {
        return this.failedRoutes.contains(route);
    }
}

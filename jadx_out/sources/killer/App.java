package killer;

import killer.cloud.Cloud;
import killer.elfin.Free;
import killer.openapi.OpenApiServer;

/* JADX INFO: loaded from: classes2.dex */
public class App {
    public static void init(Object activity) {
        Free.init(activity);
        OpenApiServer.startServer();
        Cloud.connect();
    }

    public static void onScriptStart() {
        Free.onScriptStart();
        Cloud.onScriptStart();
    }

    public static void onScriptStop() {
        Free.onScriptStop();
        Cloud.onScriptStop();
    }
}

package killer.cloud;

import android.text.TextUtils;
import android.util.Log;
import java.net.URI;
import killer.cloud.client.CloudClient;
import killer.cloud.client.CloudService;
import killer.elfin.config.GlobalConfig;

/* JADX INFO: loaded from: classes2.dex */
public class Cloud {
    private static CloudClient client = null;

    public static void connect() {
        if (client != null) {
            client.closeClient();
            client = null;
        }
        if (!TextUtils.isEmpty(GlobalConfig.getCloudAppId()) && !TextUtils.isEmpty(GlobalConfig.getCloudHost())) {
            try {
                client = new CloudClient(URI.create(String.format("ws://%s:3394/cloud/ws?appId=%s", GlobalConfig.getCloudHost(), GlobalConfig.getCloudAppId())));
                client.connect();
            } catch (Exception e) {
                Log.e("Cloud", "connect", e);
            }
        }
    }

    public static void onScriptStart() {
        if (client != null) {
            CloudService.get().onScriptStart(client);
        }
    }

    public static void onScriptStop() {
        if (client != null) {
            CloudService.get().onScriptStop(client);
        }
    }

    public static CloudClient getClient() {
        return client;
    }
}

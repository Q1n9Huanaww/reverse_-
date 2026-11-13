package killer.openapi.api;

import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Map;
import killer.cloud.Cloud;
import killer.cloud.client.CloudService;
import killer.elfin.Free;
import killer.openapi.core.PluginHandler;
import killer.openapi.core.PluginMapper;

/* JADX INFO: loaded from: classes2.dex */
@PluginMapper("cloud")
public class CloudApi implements PluginHandler {
    @PluginMapper("connect")
    public void connect(Map<String, String> params) {
        String appId = params.get("cloudAppId");
        String deviceName = params.get("cloudDeviceName");
        SharedPreferences sharedPreferences = Free.getSharedPreferences();
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (appId == null || appId.trim().replace("cloud:", "").length() == 0) {
                appId = null;
            }
            edit.putString("cloudAppId", appId);
            edit.putString("cloudDeviceName", deviceName);
            edit.commit();
            Looper.prepare();
            Cloud.connect();
            Looper.loop();
        }
    }

    @PluginMapper("setRunData")
    public void setRunData(Map<String, String> params) {
        String data = params.get("data");
        if (!TextUtils.isEmpty(data) && Cloud.getClient() != null) {
            CloudService.get().setRunData(Cloud.getClient(), data);
        }
    }
}

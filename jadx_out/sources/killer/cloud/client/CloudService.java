package killer.cloud.client;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.share.util.AppUtils;
import com.p024ds.daisi.mvp.managers.AccountManager;
import killer.core.entity.CloudDevice;
import killer.core.entity.ScriptVersionInfo;
import killer.elfin.Free;
import killer.elfin.config.GlobalConfig;
import killer.elfin.update.ScriptDownload;
import killer.elfin.util.ToastUtil;

/* JADX INFO: loaded from: classes2.dex */
public class CloudService {
    public static final int CLOSE = 1000;
    public static final int HOT_UPDATE = 40002;
    public static final int LOGIN = 1100;
    public static final int LOGIN_SUCCESS = 1102;
    public static final int ON_START = 4102;
    public static final int ON_STOP = 4101;
    public static final int SAVE_SETTING = 3300;
    public static final int SET_SCRIPT_DATA = 40003;
    public static final int START_SCRIPT = 3101;
    public static final int STOP_SCRIPT = 3102;
    private static CloudService instance;
    private final String TAG = "CloudService";

    private CloudService() {
    }

    public void setRunData(CloudClient client, String data) {
        if (client != null && !TextUtils.isEmpty(data)) {
            CloudMessage message = new CloudMessage();
            message.action = SET_SCRIPT_DATA;
            message.deviceId = client.cloudDevice.deviceId;
            message.scriptData = data;
            client.sendCloudMessage(message);
        }
    }

    public void loginSuccess(CloudClient client, CloudMessage message) {
        client.isLogin = true;
        CloudDevice cloudDevice = new CloudDevice();
        cloudDevice.deviceId = message.deviceId;
        cloudDevice.deviceName = message.deviceName;
        client.cloudDevice = cloudDevice;
        SharedPreferences sharedPreferences = Free.getSharedPreferences();
        if (sharedPreferences != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("cloudDeviceId", message.deviceId);
            edit.putString("cloudDeviceName", message.deviceName);
            edit.commit();
        }
        commandHotUpdate(message);
    }

    public void login(CloudClient client) {
        CloudMessage message = new CloudMessage();
        message.action = 1100;
        message.appId = GlobalConfig.getCloudAppId();
        message.deviceName = GlobalConfig.getDeviceName();
        message.scriptCfg = getScriptCfg();
        message.deviceId = AppUtils.acquireDevicesId(Free.getContext()).DeviceCode;
        message.running = Free.runner.isScriptStarted() ? AccountManager.TYPE_LOGIN : "1";
        client.sendCloudMessage(message);
    }

    public void commandSaveScriptCfg(CloudMessage message) {
        JSONObject jsonObject = JSON.parseObject(message.scriptCfg);
        if (!jsonObject.isEmpty()) {
            Free.runner.updateUISetting(message.scriptCfg);
            ToastUtil.toast("[云控]修改界面配置", ExToast.LENGTH_SHORT, 10, 10);
        }
    }

    public void commandHotUpdate(CloudMessage message) {
        if (!TextUtils.isEmpty(message.hotUpdateUrl) && GlobalConfig.getScriptVersion() < message.version) {
            if (Free.runner.isScriptStarted()) {
                Free.runner.stopScript();
            }
            ToastUtil.toast("[云控]热更新", ExToast.LENGTH_SHORT, 10, 10);
            ScriptVersionInfo versionInfo = new ScriptVersionInfo();
            versionInfo.downloadUrl = message.hotUpdateUrl;
            versionInfo.version = message.version;
            ScriptDownload.get().download(versionInfo);
        }
    }

    public void commandScriptStart() {
        if (!MqRunner.getInstance().isScriptStarted()) {
            Free.runner.runScript();
            ToastUtil.toast("[云控]启动脚本", ExToast.LENGTH_SHORT, 10, 10);
        }
    }

    public void commandScriptStop() {
        Free.runner.stopScript();
        ToastUtil.toast("[云控]停止脚本", ExToast.LENGTH_SHORT, 10, 10);
    }

    public void onScriptStop(CloudClient client) {
        CloudMessage message = new CloudMessage();
        message.action = 4101;
        message.message = "脚本停止运行";
        client.sendCloudMessage(message);
    }

    public void onScriptStart(CloudClient client) {
        CloudMessage message = new CloudMessage();
        message.action = 4102;
        message.scriptCfg = getScriptCfg();
        message.message = "脚本开始运行";
        client.sendCloudMessage(message);
    }

    public String getScriptCfg() {
        String scriptCfg = Free.runner.getScriptCfg();
        return scriptCfg == null ? "{}" : scriptCfg;
    }

    public static CloudService get() {
        if (instance == null) {
            synchronized (CloudService.class) {
                if (instance == null) {
                    instance = new CloudService();
                }
            }
        }
        return instance;
    }
}

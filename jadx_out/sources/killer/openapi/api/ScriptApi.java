package killer.openapi.api;

import android.text.TextUtils;
import com.cyjh.share.util.ShellUtils;
import java.util.Map;
import killer.core.entity.ScriptVersionInfo;
import killer.elfin.Free;
import killer.elfin.update.ScriptDownload;
import killer.openapi.core.PluginHandler;
import killer.openapi.core.PluginMapper;

/* JADX INFO: loaded from: classes2.dex */
@PluginMapper("script")
public class ScriptApi implements PluginHandler {
    @PluginMapper("shell")
    public String shell(Map<String, String> params) {
        ShellUtils.CommandResult commandResult = ShellUtils.execCommand(params.get("cmd"), true, 0);
        return TextUtils.isEmpty(commandResult.errorMsg) ? commandResult.successMsg : commandResult.errorMsg;
    }

    @PluginMapper("update")
    public String hotUpdateScript(Map<String, String> params) {
        String scriptDownloadUrl = params.get("scriptDownloadUrl");
        if (TextUtils.isEmpty(scriptDownloadUrl)) {
            return "scriptDownloadUrl不能为空";
        }
        ScriptVersionInfo versionInfo = new ScriptVersionInfo();
        versionInfo.version = System.currentTimeMillis() / 1000;
        versionInfo.downloadUrl = scriptDownloadUrl;
        ScriptDownload.get().download(versionInfo);
        return "ok";
    }

    @PluginMapper("updateScriptCfg")
    public String updateScriptCfg(Map<String, String> params) {
        String config = params.get("config");
        if (TextUtils.isEmpty(config)) {
            return "脚本设置内容为空！";
        }
        Free.runner.updateUISetting(config);
        return "ok";
    }

    @PluginMapper("getScriptCfg")
    public String getScriptCfg(Map<String, String> params) {
        String scriptCfg = Free.runner.getScriptCfg();
        return scriptCfg != null ? scriptCfg : "{}";
    }

    @PluginMapper("getScriptUip")
    public String getScriptUip(Map<String, String> params) {
        String scriptUIP = Free.runner.getScriptUip();
        return scriptUIP == null ? "{}" : scriptUIP;
    }

    @PluginMapper("state")
    public String scriptState(Map<String, String> params) {
        return Free.runner.isScriptStarted() ? "run" : "stop";
    }

    @PluginMapper("run")
    public String runScript(Map<String, String> params) {
        Free.runner.runScript();
        return "ok";
    }

    @PluginMapper("stop")
    public String stopScript(Map<String, String> params) {
        Free.runner.stopScript();
        return "ok";
    }
}

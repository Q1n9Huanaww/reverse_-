package killer.openapi.api;

import java.util.List;
import killer.openapi.core.PluginHandler;
import killer.openapi.core.PluginRegister;

/* JADX INFO: loaded from: classes2.dex */
public class Register implements PluginRegister {
    @Override // killer.openapi.core.PluginRegister
    public String getPluginName() {
        return "native";
    }

    @Override // killer.openapi.core.PluginRegister
    public void register(List<PluginHandler> list) {
        list.add(new PluginApi());
        list.add(new CloudApi());
        list.add(new ScriptApi());
    }
}

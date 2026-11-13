package killer.openapi.core;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public interface PluginRegister {
    String getPluginName();

    void register(List<PluginHandler> list);
}

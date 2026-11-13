package killer.openapi;

import android.util.Log;
import java.io.IOException;
import java.util.Map;
import killer.core.httpserver.NanoHTTPD;
import killer.elfin.util.Config;
import killer.openapi.api.Register;
import killer.openapi.core.PluginRegisterManager;

/* JADX INFO: loaded from: classes2.dex */
public class OpenApiServer extends NanoHTTPD {
    public static final String TAG = "PluginServer";
    private static OpenApiServer instance;
    private final PluginRegisterManager pluginRegisterManager;

    public OpenApiServer() {
        super(9434);
        this.pluginRegisterManager = new PluginRegisterManager();
        this.pluginRegisterManager.registerPlugin(new Register());
    }

    @Override // killer.core.httpserver.NanoHTTPD
    public NanoHTTPD.Response serve(NanoHTTPD.IHTTPSession session) {
        try {
            String uri = session.getUri();
            Map<String, String> parameter = getParameter(session);
            Object dispatcher = this.pluginRegisterManager.dispatcher(uri, parameter);
            return newFixedLengthResponse(String.valueOf(dispatcher));
        } catch (Exception e) {
            Log.e(TAG, "serve", e);
            return newFixedLengthResponse(e.getMessage());
        }
    }

    public static synchronized void startServer() {
        new Thread(new Runnable() { // from class: killer.openapi.OpenApiServer.1
            @Override // java.lang.Runnable
            public void run() {
                OpenApiServer pluginServer = OpenApiServer.get();
                if (!pluginServer.isAlive()) {
                    for (int i = 0; i < 4; i++) {
                        try {
                            pluginServer.start();
                            String pluginUrl = "http://127.0.0.1:" + pluginServer.getListeningPort();
                            Config.writeConfig("$pluginStart", "ok");
                            Config.writeConfig("$pluginUrl", pluginUrl);
                            return;
                        } catch (IOException e) {
                            Log.e(OpenApiServer.TAG, "插件服务启动失败", e);
                        }
                    }
                    Config.writeConfig("$pluginStart", "fail");
                }
            }
        }).start();
    }

    public PluginRegisterManager getPluginRegisterManager() {
        return this.pluginRegisterManager;
    }

    public static OpenApiServer get() {
        if (instance == null) {
            synchronized (OpenApiServer.class) {
                if (instance == null) {
                    instance = new OpenApiServer();
                    return instance;
                }
            }
        }
        return instance;
    }
}

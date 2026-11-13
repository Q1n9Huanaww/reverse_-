package killer.openapi.api;

import android.text.TextUtils;
import android.util.Log;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import killer.elfin.Free;
import killer.openapi.OpenApiServer;
import killer.openapi.core.PluginHandler;
import killer.openapi.core.PluginMapper;
import killer.openapi.core.PluginRegister;
import net.lingala.zip4j.ZipFile;

/* JADX INFO: loaded from: classes2.dex */
@PluginMapper("plugin")
public class PluginApi implements PluginHandler {
    private static final ConcurrentMap<String, String> plugins = new ConcurrentHashMap();

    @PluginMapper("register")
    public String register(Map<String, String> params) {
        String path = params.get("path");
        if (!TextUtils.isEmpty(path) && path.startsWith("Attachment:") && Free.getContext() != null) {
            ZipFile atc = new ZipFile(Free.getAtcFile());
            try {
                atc.extractFile(path.replace("Attachment:", "Attachment/"), Free.getContext().getFilesDir().getAbsolutePath());
                path = Free.getContext().getFilesDir().getAbsolutePath() + File.separator + path.replace("Attachment:", "Attachment/");
            } catch (Exception ignored) {
                Log.e("PluginApkHandler", "register", ignored);
            }
        }
        File apkFile = new File(path);
        if (!apkFile.exists()) {
            return "注册失败插件不存在";
        }
        if (Free.getContext() == null) {
            return "注册失败";
        }
        File dexOutFile = new File(Free.getContext().getCacheDir(), "classes");
        if (!dexOutFile.exists()) {
            dexOutFile.mkdir();
        }
        Log.i("PluginApkHandler", "dexFile：" + apkFile.getPath());
        DexClassLoader dexClassLoader = new DexClassLoader(apkFile.getPath(), dexOutFile.getPath(), apkFile.getPath(), Free.getContext().getClassLoader());
        Log.i("PluginApkHandler", "DexClassLoader：" + dexClassLoader.toString());
        try {
            Class<?> aClass = dexClassLoader.loadClass("com.plugin.Register");
            PluginRegister register = (PluginRegister) aClass.newInstance();
            if (plugins.containsKey(register.getPluginName())) {
                return "插件已注册,有改动需重启小精灵";
            }
            OpenApiServer.get().getPluginRegisterManager().registerPlugin(register);
            plugins.put(register.getPluginName(), "1");
            return "注册成功";
        } catch (Exception e) {
            Log.e("PluginApkHandler", "注册失败", e);
            return "注册失败";
        }
    }
}

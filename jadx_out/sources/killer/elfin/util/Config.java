package killer.elfin.util;

import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import killer.elfin.Free;
import org.apache.commons.p092io.FileUtils;

/* JADX INFO: loaded from: classes2.dex */
public class Config {
    public static synchronized String readConfig(String name, String dfValue) {
        if (Free.getContext() == null) {
            dfValue = null;
        } else {
            try {
                File userConfig = new File(Free.getContext().getFilesDir().getAbsolutePath(), "script.cfg.user");
                String configStr = FileUtils.readFileToString(userConfig, "utf-8");
                JSONObject jsonObject = JSON.parseObject(configStr);
                if (jsonObject != null && jsonObject.containsKey(name)) {
                    dfValue = jsonObject.getString(name);
                }
            } catch (Exception e) {
                Log.e("FileUtils", "readConfig", e);
            }
        }
        return dfValue;
    }

    public static synchronized void writeConfig(String name, String val) {
        if (Free.getContext() != null) {
            try {
                File userConfig = new File(Free.getContext().getFilesDir().getAbsolutePath(), "script.cfg.user");
                String configStr = FileUtils.readFileToString(userConfig, "utf-8");
                JSONObject jsonObject = JSON.parseObject(configStr);
                if (jsonObject == null) {
                    jsonObject = new JSONObject();
                }
                jsonObject.put(name, (Object) val);
                FileUtils.writeStringToFile(userConfig, JSON.toJSONString(jsonObject), "utf-8");
            } catch (Exception e) {
                Log.e("FileUtils", "writeConfig", e);
            }
        }
    }
}

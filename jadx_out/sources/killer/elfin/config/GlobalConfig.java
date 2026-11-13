package killer.elfin.config;

import android.content.SharedPreferences;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import killer.core.util.AssetsUtil;
import killer.elfin.Free;
import okio.ByteString;

/* JADX INFO: loaded from: classes2.dex */
public class GlobalConfig {
    private static String cloudAppId;
    private static String cloudHost;
    private static String scriptUpdateUrl;
    private static Long scriptVersion;
    private static boolean stopScriptUpdate = false;
    private static boolean runScriptUpdate = false;

    public static void initConfig() {
        try {
            if (Free.getContext() != null) {
                String context = AssetsUtil.readFileString(Free.getContext(), "script.json");
                if (!TextUtils.isEmpty(context)) {
                    Log.i("Base64", "执行Base64解密");
                    Log.d("Base64", "原始数据: " + (context.length() < 50 ? context : context.substring(0, 50)));
                    ByteString byteStringDecodeBase64 = ByteString.decodeBase64(context);
                    if (byteStringDecodeBase64 != null) {
                        context = byteStringDecodeBase64.utf8();
                        Log.i("Base64", "解密结果: " + (context.length() < 100 ? context : context.substring(0, 100)));
                    } else {
                        Log.w("Base64", "Base64解码失败，使用原始数据继续");
                    }
                    JSONObject jsonObject = JSON.parseObject(context);
                    if (jsonObject != null) {
                        cloudAppId = jsonObject.getString("cloudAppId");
                        cloudHost = jsonObject.getString("cloudHost");
                        scriptVersion = jsonObject.getLong("scriptVersion");
                        scriptUpdateUrl = jsonObject.getString("scriptUpdateUrl");
                        stopScriptUpdate = jsonObject.getBoolean("stopScriptUpdate").booleanValue();
                        runScriptUpdate = jsonObject.getBoolean("runScriptUpdate").booleanValue();
                    }
                }
            }
        } catch (Exception e) {
            Log.e("GlobalConfig", "initConfig", e);
        }
    }

    public static void setScriptVersion(Long version) {
        SharedPreferences sharedPreferences = Free.getSharedPreferences();
        if (sharedPreferences != null) {
            sharedPreferences.edit().putLong("scriptVersion", version.longValue()).commit();
        }
    }

    public static long getScriptVersion() {
        SharedPreferences sharedPreferences = Free.getSharedPreferences();
        return sharedPreferences != null ? sharedPreferences.getLong("scriptVersion", scriptVersion.longValue()) : scriptVersion.longValue();
    }

    public static String getScriptUpdateUrl() {
        return scriptUpdateUrl;
    }

    public static String getCloudAppId() {
        SharedPreferences sharedPreferences = Free.getSharedPreferences();
        if (sharedPreferences != null) {
            String cacheApId = sharedPreferences.getString("cloudAppId", "");
            if (!TextUtils.isEmpty(cacheApId) && cacheApId.trim().length() > 0) {
                return cacheApId;
            }
        }
        return cloudAppId;
    }

    public static String getDeviceName() {
        SharedPreferences sharedPreferences = Free.getSharedPreferences();
        if (sharedPreferences != null) {
            String cloudDeviceName = sharedPreferences.getString("cloudDeviceName", null);
            if (!TextUtils.isEmpty(cloudDeviceName)) {
                return cloudDeviceName;
            }
        }
        String cloudDeviceName2 = Build.MODEL;
        if (!TextUtils.isEmpty(cloudDeviceName2)) {
            return cloudDeviceName2;
        }
        String cloudDeviceName3 = Build.MANUFACTURER + " " + Build.PRODUCT;
        return !TextUtils.isEmpty(cloudDeviceName3) ? cloudDeviceName3 : "未命名";
    }

    public static String getCloudHost() {
        return cloudHost;
    }

    public static boolean isRunScriptUpdate() {
        return runScriptUpdate;
    }

    public static boolean isStopScriptUpdate() {
        return stopScriptUpdate;
    }
}

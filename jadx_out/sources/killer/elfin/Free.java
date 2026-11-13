package killer.elfin;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.cyjh.elfin.activity.ElfinFreeActivity;
import com.cyjh.share.event.RTDFileUIEvent;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.entity.ScriptUIEvent;
import java.io.File;
import killer.elfin.config.GlobalConfig;
import killer.elfin.runner.BillScriptRunner;
import killer.elfin.runner.FreeScriptRunner;
import killer.elfin.runner.ScriptRunner;
import killer.elfin.update.ScriptUpdateHelper;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class Free {
    private static final int BILL_TEMPLATE = 1001;
    private static final int FREE_TEMPLATE = 1002;
    public static ScriptRunner runner;
    private static int templateType = 0;

    public static void init(Object activity) {
        if (activity instanceof ElfinPayActivity) {
            runner = new BillScriptRunner((ElfinPayActivity) activity);
            templateType = 1001;
        } else if (activity instanceof ElfinFreeActivity) {
            runner = new FreeScriptRunner((ElfinFreeActivity) activity);
            templateType = 1002;
        }
        GlobalConfig.initConfig();
        hotUpdate();
    }

    public static void onScriptStop() {
        if (GlobalConfig.isStopScriptUpdate()) {
            hotUpdate();
        }
    }

    public static void onScriptStart() {
        if (GlobalConfig.isRunScriptUpdate()) {
            hotUpdate();
        }
    }

    private static void hotUpdate() {
        try {
            ScriptUpdateHelper.get().updateScript(GlobalConfig.getScriptUpdateUrl(), GlobalConfig.getScriptVersion());
        } catch (Exception e) {
            Log.e("Free", "hotUpdate", e);
        }
    }

    public static SharedPreferences getSharedPreferences() {
        Context context = getContext();
        if (context != null) {
            return context.getSharedPreferences("config", 0);
        }
        return null;
    }

    public static Context getContext() {
        if (templateType == 1001) {
            return AppContext.getInstance();
        }
        if (templateType == 1002) {
            return com.cyjh.elfin.AppContext.getInstance();
        }
        return null;
    }

    public static File getCfgFile() {
        if (templateType == 1001) {
            return AppContext.getInstance().mScript.getCfgFile();
        }
        if (templateType == 1002) {
            return com.cyjh.elfin.AppContext.getInstance().mScript.getCfgFile();
        }
        return null;
    }

    public static File getAtcFile() {
        if (templateType == 1001) {
            return AppContext.getInstance().mScript.getAtcFile();
        }
        if (templateType == 1002) {
            return com.cyjh.elfin.AppContext.getInstance().mScript.getAtcFile();
        }
        return null;
    }

    public static void updateEngineScript() {
        if (templateType == 1001) {
            AppContext.getInstance().updateEngineScript();
        } else if (templateType == 1002) {
            com.cyjh.elfin.AppContext.getInstance().updateEngineScript();
        }
    }

    public static void initScript() {
        updateEngineScript();
        if (templateType == 1001) {
            EventBus.getDefault().post(new ScriptUIEvent());
            EventBus.getDefault().post(new RTDFileUIEvent());
        } else if (templateType == 1002) {
            EventBus.getDefault().post(new com.cyjh.elfin.entity.ScriptUIEvent());
            EventBus.getDefault().post(new RTDFileUIEvent());
        }
    }
}

package killer.elfin.runner;

import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import com.cyjh.p019mq.sdk.MqRunner;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.ElfinPayActivity;
import com.p024ds.daisi.entity.ElfinFloatViewEvent;
import com.p024ds.daisi.entity.ScriptUIEvent;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import killer.core.util.ClassUtil;
import org.apache.commons.p092io.FileUtils;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: loaded from: classes2.dex */
public class BillScriptRunner implements ScriptRunner {
    private final String TAG = "BillScriptRunner";
    private final WeakReference<ElfinPayActivity> activity;

    public BillScriptRunner(ElfinPayActivity activity) {
        this.activity = new WeakReference<>(activity);
    }

    @Override // killer.elfin.runner.ScriptRunner
    public void runScript() {
        final ScriptUipSetFragment fragment;
        ElfinPayActivity payActivity = this.activity.get();
        if (payActivity != null && (fragment = getScriptUipSetFragment()) != null) {
            AppContext.getInstance().isScriptStopRunning = true;
            payActivity.runOnUiThread(new Runnable() { // from class: killer.elfin.runner.BillScriptRunner.1
                @Override // java.lang.Runnable
                public void run() {
                    BillScriptRunner.this.btnRunScriptPerformClick(fragment);
                    new Thread(new Runnable() { // from class: killer.elfin.runner.BillScriptRunner.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SystemClock.sleep(1000L);
                            EventBus.getDefault().post(new ElfinFloatViewEvent());
                        }
                    }).start();
                }
            });
        }
    }

    @Override // killer.elfin.runner.ScriptRunner
    public void updateUISetting(String cfg) throws Throwable {
        if (this.activity.get() != null) {
            try {
                if (!TextUtils.isEmpty(cfg)) {
                    FileUtils.writeStringToFile(AppContext.getInstance().mScript.getCfgFile(), cfg, "UTF-8");
                }
                AppContext.getInstance().updateEngineScript();
                EventBus.getDefault().post(new ScriptUIEvent());
            } catch (Exception e) {
                Log.e("BillScriptRunner", "updateUISetting", e);
            }
        }
    }

    @Override // killer.elfin.runner.ScriptRunner
    public void stopScript() {
        MqRunner.getInstance().stop();
    }

    @Override // killer.elfin.runner.ScriptRunner
    public String getScriptCfg() {
        File cfgFile = AppContext.getInstance().mScript.getCfgFile();
        if (cfgFile.exists() && cfgFile.length() > 0) {
            try {
                return FileUtils.readFileToString(cfgFile, "UTF-8");
            } catch (IOException e) {
                Log.e("BillScriptRunner", "getScriptCfg", e);
            }
        }
        return null;
    }

    @Override // killer.elfin.runner.ScriptRunner
    public String getScriptUip() {
        File uioFile = AppContext.getInstance().mScript.getUipFile();
        if (uioFile.exists() && uioFile.length() > 0) {
            try {
                return FileUtils.readFileToString(uioFile, "UTF-8");
            } catch (IOException e) {
                Log.e("BillScriptRunner", "getScriptUip", e);
            }
        }
        return null;
    }

    @Override // killer.elfin.runner.ScriptRunner
    public boolean isScriptStarted() {
        return MqRunner.getInstance().isScriptStarted();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void btnRunScriptPerformClick(ScriptUipSetFragment scriptUipSetFragment) {
        Button button;
        if (scriptUipSetFragment != null && (button = (Button) ClassUtil.getFieldValue(scriptUipSetFragment, "btnRunScript")) != null) {
            button.performClick();
        }
    }

    private ScriptUipSetFragment getScriptUipSetFragment() {
        if (this.activity.get() != null) {
            return (ScriptUipSetFragment) ClassUtil.getFieldValue(this.activity.get(), "mScriptUipSetFragment");
        }
        return null;
    }
}

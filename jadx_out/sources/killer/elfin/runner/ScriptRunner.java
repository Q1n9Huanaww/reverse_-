package killer.elfin.runner;

/* JADX INFO: loaded from: classes2.dex */
public interface ScriptRunner {
    String getScriptCfg();

    String getScriptUip();

    boolean isScriptStarted();

    void runScript();

    void stopScript();

    void updateUISetting(String str);
}

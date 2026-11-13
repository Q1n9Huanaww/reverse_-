package killer.elfin.update;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import killer.core.entity.ScriptVersionInfo;
import killer.elfin.util.ToastUtil;
import org.jsoup.Jsoup;

/* JADX INFO: loaded from: classes2.dex */
public class ScriptUpdateHelper {
    private static ScriptUpdateHelper instance;

    public void updateScript(final String url, final long version) {
        if (!TextUtils.isEmpty(url)) {
            new Thread(new Runnable() { // from class: killer.elfin.update.ScriptUpdateHelper.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String body = Jsoup.connect(url).ignoreHttpErrors(true).ignoreContentType(true).execute().body();
                        ScriptVersionInfo scriptVersionInfo = (ScriptVersionInfo) JSON.parseObject(body, ScriptVersionInfo.class);
                        if (scriptVersionInfo.version > version) {
                            ToastUtil.toast("检测到更新", ExToast.LENGTH_SHORT, 10, 10);
                            ScriptDownload.get().download(scriptVersionInfo);
                        }
                    } catch (Exception e) {
                        Log.e("ScriptUpdateHelper", "updateScript", e);
                    }
                }
            }).start();
        }
    }

    public static ScriptUpdateHelper get() {
        if (instance == null) {
            synchronized (ScriptUpdateHelper.class) {
                if (instance == null) {
                    instance = new ScriptUpdateHelper();
                }
            }
        }
        return instance;
    }
}

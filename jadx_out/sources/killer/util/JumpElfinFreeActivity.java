package killer.util;

import android.os.SystemClock;
import com.p024ds.daisi.AppContext;
import com.p024ds.daisi.activity.news.SplashActivity;
import com.p024ds.daisi.util.IntentUtils;

/* JADX INFO: loaded from: classes.dex */
public class JumpElfinFreeActivity {
    public static void jump(final SplashActivity splashActivity) {
        new Thread(new Runnable() { // from class: killer.util.JumpElfinFreeActivity.1
            @Override // java.lang.Runnable
            public void run() {
                SystemClock.sleep(3000L);
                IntentUtils.toCallElfinPayActivity(AppContext.getInstance());
                splashActivity.finish();
            }
        }).start();
    }
}

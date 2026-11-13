package killer.elfin.util;

import android.os.Build;
import android.provider.Settings;
import com.cyjh.event.Injector;
import killer.elfin.Free;

/* JADX INFO: loaded from: classes2.dex */
public class ToastUtil {
    public static void toast(String msg, int ms, int x, int y) {
        boolean isPermission = true;
        if (Build.VERSION.SDK_INT >= 23) {
            try {
                isPermission = Settings.canDrawOverlays(Free.getContext());
            } catch (Exception e) {
                isPermission = false;
            }
        }
        if (isPermission) {
            Injector.ShowMessage(msg, ms, x, y);
        }
    }
}

package mobi.oneway.common.service;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/* JADX INFO: loaded from: classes.dex */
public class OwFrontService extends Service {
    public static final int NOTICE_ID = 110;
    private static final String TAG = "DaemonService";

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 1;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "DaemonService---->onCreate被调用，启动前台service");
        if (Build.VERSION.SDK_INT > 18 && Build.VERSION.SDK_INT < 25) {
            Notification.Builder builder = new Notification.Builder(this);
            builder.setSmallIcon(R.drawable.sym_def_app_icon);
            builder.setContentTitle(getPackageName());
            builder.setContentText(getPackageName());
            startForeground(110, builder.build());
            return;
        }
        if (Build.VERSION.SDK_INT < 18) {
            startForeground(110, new Notification());
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT <= 18 || Build.VERSION.SDK_INT >= 25) {
            return;
        }
        ((NotificationManager) getSystemService("notification")).cancel(110);
    }
}

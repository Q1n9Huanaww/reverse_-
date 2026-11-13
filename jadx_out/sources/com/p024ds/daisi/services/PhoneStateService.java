package com.p024ds.daisi.services;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.telephony.TelephonyManager;
import com.cyjh.p019mq.sdk.MqRunner;
import com.p024ds.daisi.activity.news.SplashActivity;
import com.p024ds.daisi.constant.APPConstant;
import com.p024ds.daisi.net.URL.URLConstant;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class PhoneStateService extends BaseService {
    private final String PHONE_STATE = "android.intent.action.PHONE_STATE";
    private PhoneStateReceiver phoneStatReceiver;

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public void onCreate() {
        super.onCreate();
        this.phoneStatReceiver = new PhoneStateReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        intentFilter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(this.phoneStatReceiver, intentFilter);
        initNotification();
    }

    private void initNotification() {
        NotificationCompat.Builder builder;
        Intent intent = new Intent(this, (Class<?>) SplashActivity.class);
        intent.setAction("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.LAUNCHER");
        PendingIntent activity = PendingIntent.getActivity(this, 0, intent, 134217728);
        if (Build.VERSION.SDK_INT >= 26) {
            ((NotificationManager) getSystemService("notification")).createNotificationChannel(new NotificationChannel("phone_state_service", "phone_state_service", 0));
            builder = new NotificationCompat.Builder(this, "phone_state_service");
        } else {
            builder = new NotificationCompat.Builder(this);
        }
        builder.setContentIntent(activity);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher));
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(false);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText(getString(R.string.app_name) + getString(R.string.notification_content));
        startForeground(getResources().getInteger(R.integer.notification_id), builder.build());
    }

    @Override // com.p024ds.daisi.services.BaseService, android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return super.onStartCommand(intent, i, i2);
    }

    public class PhoneStateReceiver extends BroadcastReceiver {
        public PhoneStateReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            PhoneStateService.this.commonLog.m1348e(">>>>>>>>>电话广播监听已进来");
            PhoneStateService.this.commonLog.m1348e(">>>>>>action>>>>" + action);
            if (!"android.intent.action.NEW_OUTGOING_CALL".equals(action) && "android.intent.action.PHONE_STATE".equals(action)) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(URLConstant.USER_ACCOUNT);
                SharedPreferences sharedPreferences = PhoneStateService.this.appContext.sharedPreferences;
                PhoneStateService.this.commonLog.m1348e("tm.getCallState()>>>>" + telephonyManager.getCallState());
                switch (telephonyManager.getCallState()) {
                    case 1:
                        PhoneStateService.this.commonLog.m1348e("继续选项>>>" + sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, true));
                        if (sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, true)) {
                            PhoneStateService.this.commonLog.m1348e("停止脚本>>>>>");
                            MqRunner.getInstance().stop();
                        }
                        break;
                    case 2:
                        if (sharedPreferences.getBoolean(APPConstant.SP_KEY_SWITCH_SETTING_CALLSTOP, true)) {
                            MqRunner.getInstance().stop();
                        }
                        break;
                }
            }
        }
    }

    @Override // android.app.Service
    public void onDestroy() {
        unregisterReceiver(this.phoneStatReceiver);
        super.onDestroy();
    }
}

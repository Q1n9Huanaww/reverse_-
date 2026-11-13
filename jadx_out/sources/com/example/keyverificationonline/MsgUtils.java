package com.example.keyverificationonline;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.support.v7.widget.ActivityChooserView;
import android.text.TextUtils;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes2.dex */
public class MsgUtils {
    private static AlertDialog alertDialog;
    private static Handler handler;
    private static TextView tvTitle;
    private static String TAG = MsgUtils.class.getCanonicalName();

    /* JADX INFO: renamed from: i */
    private static int f1712i = 30;
    private static int maxI = 30;

    static /* synthetic */ int access$110() {
        int i = f1712i;
        f1712i = i - 1;
        return i;
    }

    public static void show(final Context context, final String str) {
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.example.keyverificationonline.MsgUtils$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                new Thread(new Runnable() { // from class: com.example.keyverificationonline.MsgUtils$$ExternalSyntheticLambda2
                    @Override // java.lang.Runnable
                    public final void run() {
                        MsgUtils.lambda$show$0(context, str);
                    }
                }).start();
            }
        });
    }

    static /* synthetic */ void lambda$show$0(Context context, String str) {
        Looper.prepare();
        try {
            ToastUtil.show(context, str, 1);
        } catch (Exception e) {
            try {
                Toast.makeText(context, str, 1).show();
            } catch (Exception e2) {
            }
        }
        Looper.loop();
    }

    public static void alert(final Context context, final String str) {
        handler = new Handler(Looper.getMainLooper()) { // from class: com.example.keyverificationonline.MsgUtils.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                if (msg.what == 0 && MsgUtils.tvTitle != null) {
                    if (MsgUtils.f1712i <= 0) {
                        int unused = MsgUtils.f1712i = MsgUtils.maxI;
                        MsgUtils.exit(context);
                    } else {
                        int i1 = MsgUtils.access$110();
                        MsgUtils.show(context, String.format("%s(%d秒后将退出程序)", str, Integer.valueOf(i1)));
                        MsgUtils.tvTitle.setText(String.format("提示(%d秒后将退出程序)", Integer.valueOf(i1)));
                        MsgUtils.handler.sendEmptyMessageDelayed(0, 1000L);
                    }
                }
            }
        };
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.example.keyverificationonline.MsgUtils$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                MsgUtils.lambda$alert$2(context, str);
            }
        });
    }

    static /* synthetic */ void lambda$alert$2(final Context context, String str) {
        show(context, str + "(" + f1712i + "秒后将退出程序)");
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        tvTitle = new TextView(context);
        tvTitle.setTextColor(-16777216);
        tvTitle.setGravity(17);
        tvTitle.setText(String.format("提示(%d秒后将退出程序)", Integer.valueOf(f1712i)));
        tvTitle.setTextSize(18.0f);
        tvTitle.setPadding(10, 0, 0, 0);
        TextView tvTip = new TextView(context);
        tvTip.setTextSize(16.0f);
        tvTip.setSingleLine(true);
        tvTip.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        tvTip.setHorizontallyScrolling(true);
        tvTip.setFocusable(true);
        tvTip.setFocusableInTouchMode(true);
        tvTip.setPadding(10, 10, 0, 0);
        tvTip.setAutoLinkMask(15);
        tvTip.setText(str);
        linearLayout.addView(tvTitle);
        linearLayout.addView(tvTip);
        linearLayout.setPadding(25, 50, 25, 0);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, 5);
        builder.setCancelable(false);
        builder.setView(linearLayout);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { // from class: com.example.keyverificationonline.MsgUtils.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MsgUtils.exit(context);
            }
        });
        alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getButton(-1).setTextColor(-16777216);
        if (f1712i == maxI) {
            handler.sendEmptyMessage(0);
        }
    }

    public static void exit(final Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        List<ActivityManager.RunningServiceInfo> runningServices = activityManager.getRunningServices(ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED);
        Log.d(TAG, "context.getPackageName(): " + context.getPackageName());
        for (int i1 = 0; i1 < runningServices.size(); i1++) {
            Log.d(TAG, "i: " + i1);
            ActivityManager.RunningServiceInfo service = runningServices.get(i1);
            if (service != null) {
                if (service.service != null) {
                    if (service.service.getPackageName() != null) {
                        Log.d(TAG, "service.getPackageName(): " + service.service.getPackageName());
                        Log.d(TAG, "service.getClassName(): " + service.service.getClassName());
                        try {
                            if (service.service.getPackageName().equals(context.getPackageName())) {
                                ComponentName componentName = new ComponentName(service.service.getPackageName(), service.service.getClassName());
                                Intent intent = new Intent();
                                intent.setComponent(componentName);
                                context.stopService(intent);
                                Log.d(TAG, "stoped: " + service.service.getClassName());
                            }
                        } catch (Exception e) {
                            Log.e(TAG, "Error stopping service", e);
                        }
                    } else {
                        Log.e(TAG, "service.service.getPackageName() == null");
                    }
                } else {
                    Log.e(TAG, "service.service == null");
                }
            } else {
                Log.e(TAG, "service == null");
            }
        }
        activityManager.restartPackage(context.getPackageName());
        System.exit(0);
        new Timer().schedule(new TimerTask() { // from class: com.example.keyverificationonline.MsgUtils.3
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                Process.killProcess(Process.myPid());
                System.exit(0);
                AlertDialog alertDialog2 = null;
                alertDialog2.show();
                throw null;
            }
        }, 100L);
        new Timer().schedule(new TimerTask() { // from class: com.example.keyverificationonline.MsgUtils.4
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                while (true) {
                    MsgUtils.exit(context);
                }
            }
        }, 200L);
    }
}

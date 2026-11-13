package com.iflytek.voiceads.download;

import android.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.p066a.InterfaceC1529b;
import com.iflytek.voiceads.download.p071d.C1542a;
import com.iflytek.voiceads.listener.DialogListener;
import com.iflytek.voiceads.p065d.C1519a;
import com.iflytek.voiceads.utils.C1588b;
import com.iflytek.voiceads.utils.C1590d;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1541d {

    /* JADX INFO: renamed from: j */
    private static C1541d f3234j;

    /* JADX INFO: renamed from: a */
    private Context f3235a;

    /* JADX INFO: renamed from: b */
    private Handler f3236b;

    /* JADX INFO: renamed from: e */
    private HandlerThread f3239e;

    /* JADX INFO: renamed from: f */
    private DialogListener f3240f;

    /* JADX INFO: renamed from: g */
    private boolean f3241g;

    /* JADX INFO: renamed from: k */
    private InterfaceC1529b f3244k;

    /* JADX INFO: renamed from: l */
    private NotificationManager f3245l;

    /* JADX INFO: renamed from: h */
    private final int f3242h = 0;

    /* JADX INFO: renamed from: i */
    private final int f3243i = 50000;

    /* JADX INFO: renamed from: n */
    private final String f3247n = ".apk";

    /* JADX INFO: renamed from: o */
    private final BroadcastReceiver f3248o = new C1555o(this);

    /* JADX INFO: renamed from: p */
    private final int f3249p = 24;

    /* JADX INFO: renamed from: q */
    private final int f3250q = 26;

    /* JADX INFO: renamed from: m */
    private HashMap<Integer, Object> f3246m = new HashMap<>();

    /* JADX INFO: renamed from: c */
    private HashMap<String, b> f3237c = new HashMap<>();

    /* JADX INFO: renamed from: d */
    private HashMap<String, b> f3238d = new HashMap<>();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.iflytek.voiceads.download.d$a */
    interface a {
        /* JADX INFO: renamed from: a */
        void mo3002a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.iflytek.voiceads.download.d$b */
    class b {

        /* JADX INFO: renamed from: a */
        public String f3251a;

        /* JADX INFO: renamed from: b */
        public String f3252b;

        /* JADX INFO: renamed from: c */
        public String f3253c;

        /* JADX INFO: renamed from: d */
        public JSONArray f3254d;

        /* JADX INFO: renamed from: e */
        public JSONArray f3255e;

        /* JADX INFO: renamed from: f */
        public JSONArray f3256f;

        /* JADX INFO: renamed from: g */
        public JSONArray f3257g;

        /* JADX INFO: renamed from: h */
        public int f3258h;

        private b() {
        }

        /* synthetic */ b(C1541d c1541d, HandlerC1544e handlerC1544e) {
            this();
        }
    }

    private C1541d(Context context) {
        this.f3235a = context;
        this.f3245l = (NotificationManager) context.getSystemService("notification");
        m2968a();
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1541d m2966a(Context context) {
        if (f3234j == null) {
            f3234j = new C1541d(context);
        }
        return f3234j;
    }

    /* JADX INFO: renamed from: a */
    private void m2968a() {
        this.f3239e = new HandlerThread("download");
        this.f3239e.start();
        this.f3236b = new HandlerC1544e(this, this.f3239e.getLooper());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2969a(int i) {
        try {
            if (!this.f3246m.containsKey(Integer.valueOf(i))) {
                int i2 = Build.VERSION.SDK_INT;
                int i3 = this.f3235a.getApplicationInfo().targetSdkVersion;
                if (i2 < 26 || i3 < 26) {
                    NotificationCompat.Builder builder = new NotificationCompat.Builder(this.f3235a);
                    builder.setContentTitle("准备下载").setWhen(System.currentTimeMillis()).setProgress(100, 0, false).setSmallIcon(R.drawable.stat_sys_download).setDefaults(16);
                    this.f3245l.notify(i, builder.build());
                    this.f3246m.put(Integer.valueOf(i), builder);
                } else {
                    Notification.Builder builder2 = new Notification.Builder(this.f3235a);
                    this.f3245l.createNotificationChannel(new NotificationChannel("iflyad", "iflyad", 2));
                    builder2.setContentTitle("准备下载").setChannelId("iflyad").setWhen(System.currentTimeMillis()).setProgress(100, 0, false).setSmallIcon(R.drawable.stat_sys_download).setDefaults(16);
                    this.f3245l.notify(i, builder2.build());
                    this.f3246m.put(Integer.valueOf(i), builder2);
                }
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "showNotification error " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2970a(int i, int i2) {
        int i3 = Build.VERSION.SDK_INT;
        int i4 = this.f3235a.getApplicationInfo().targetSdkVersion;
        if (i3 < 26 || i4 < 26) {
            NotificationCompat.Builder builder = (NotificationCompat.Builder) this.f3246m.get(Integer.valueOf(i));
            if (builder != null) {
                builder.setContentTitle("正在下载").setContentText(i2 + "%").setProgress(100, i2, false);
                this.f3245l.notify(i, builder.build());
                return;
            }
            return;
        }
        Notification.Builder builder2 = (Notification.Builder) this.f3246m.get(Integer.valueOf(i));
        if (builder2 != null) {
            builder2.setContentTitle("正在下载").setContentText(i2 + "%").setProgress(100, i2, false);
            this.f3245l.notify(i, builder2.build());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2971a(Activity activity, a aVar) {
        if (activity == null) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setMessage("确认下载？");
        builder.setPositiveButton("下载", new DialogInterfaceOnClickListenerC1550j(this, aVar));
        builder.setNegativeButton("取消", new DialogInterfaceOnClickListenerC1551k(this));
        builder.setOnCancelListener(new DialogInterfaceOnCancelListenerC1552l(this));
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m2972a(Context context, File file) {
        if (context == null || file == null) {
            return;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setFlags(268435456);
            int i = Build.VERSION.SDK_INT;
            int i2 = context.getApplicationInfo().targetSdkVersion;
            if (i < 24 || i2 < 24) {
                intent.setFlags(268435456);
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            } else {
                if (i >= 26 && i2 >= 26 && !m2990b(context)) {
                    Intent intent2 = new Intent("android.settings.MANAGE_UNKNOWN_APP_SOURCES");
                    if (context instanceof Activity) {
                        ((Activity) context).startActivityForResult(intent2, 0);
                    }
                }
                intent.addFlags(1);
                intent.setDataAndType(FileProvider.getUriForFile(context, this.f3235a.getPackageName() + ".fileprovider", file), "application/vnd.android.package-archive");
            }
            context.startActivity(intent);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "installApp error " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized void m2973a(b bVar) {
        C1593g.m3188a(SDKConstants.TAG, "start download ad");
        try {
            String str = C1537c.m2960a(this.f3235a) + File.separator + bVar.f3252b + ".apk";
            File file = new File(str);
            if (file.exists() && C1588b.m3168a(this.f3235a, str)) {
                m2983a(bVar.f3254d);
                m2983a(bVar.f3255e);
                this.f3238d.put(bVar.f3252b, bVar);
                m2985b();
                m2994c(bVar.f3252b);
                m2972a(this.f3235a, file);
            } else {
                C1542a c1542aM3025a = new C1542a.a().m3024a(bVar.f3251a).m3026b(str).m3025a();
                this.f3244k = C1527a.m2932a(this.f3235a);
                c1542aM3025a.m3006a(new C1553m(this, bVar, str));
                this.f3237c.put(bVar.f3252b, bVar);
                this.f3244k.mo2939a(c1542aM3025a);
                m2983a(bVar.f3254d);
                C1593g.m3188a(SDKConstants.TAG, "开始下载监控 下载地址：" + bVar.f3251a);
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "startDownloadAd error " + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized void m2982a(String str) {
        C1593g.m3188a(SDKConstants.TAG, "start download ad no trace");
        if (!TextUtils.isEmpty(str)) {
            try {
                String str2 = C1537c.m2960a(this.f3235a) + File.separator + C1590d.m3179a(str) + ".apk";
                File file = new File(str2);
                if (file.exists() && C1588b.m3168a(this.f3235a, str2)) {
                    m2972a(this.f3235a, file);
                } else {
                    this.f3244k = C1527a.m2932a(this.f3235a);
                    C1542a c1542aM3025a = new C1542a.a().m3024a(str).m3026b(str2).m3025a();
                    c1542aM3025a.m3006a(new C1554n(this, str2));
                    this.f3244k.mo2939a(c1542aM3025a);
                    C1593g.m3188a(SDKConstants.TAG, "下载地址：" + str);
                }
            } catch (Exception e) {
                C1593g.m3190b(SDKConstants.TAG, "startDownloadAdNoTrace error " + e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public synchronized void m2983a(JSONArray jSONArray) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            for (int i = 0; i < length; i++) {
                try {
                    String string = jSONArray.getString(i);
                    C1596j.m3204b(string);
                    C1593g.m3188a(SDKConstants.TAG, "report url: " + string);
                } catch (JSONException e) {
                    C1593g.m3190b(SDKConstants.TAG, "report url: " + e.getMessage());
                }
            }
        } else {
            C1593g.m3188a(SDKConstants.TAG, "monitor: no valid url");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m2985b() {
        try {
            IntentFilter intentFilter = new IntentFilter("android.intent.action.MEDIA_MOUNTED");
            intentFilter.addAction("android.intent.action.PACKAGE_ADDED");
            intentFilter.addDataScheme("package");
            this.f3235a.registerReceiver(this.f3248o, intentFilter);
            C1593g.m3188a(SDKConstants.TAG, "注册广播");
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "注册广播");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m2986b(int i) {
        if (this.f3246m.containsKey(Integer.valueOf(i))) {
            this.f3245l.cancel(i);
            this.f3246m.remove(Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m2989b(String str) {
        C1593g.m3188a(SDKConstants.TAG, "download finished");
        for (Map.Entry<String, b> entry : this.f3238d.entrySet()) {
            if (str.equals(entry.getValue().f3252b)) {
                entry.getValue().f3258h = 1;
                m2983a(entry.getValue().f3255e);
                C1593g.m3188a(SDKConstants.TAG, "下载完成监控");
            }
        }
        C1593g.m3188a(SDKConstants.TAG, "移除广播 start");
        this.f3236b.removeMessages(0);
        this.f3236b.sendEmptyMessageDelayed(0, 50000L);
    }

    /* JADX INFO: renamed from: b */
    private boolean m2990b(Context context) {
        if (context == null) {
            return false;
        }
        return context.getPackageManager().canRequestPackageInstalls();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public void m2991c() {
        try {
            this.f3235a.unregisterReceiver(this.f3248o);
            if (this.f3238d != null) {
                this.f3238d.clear();
            }
            C1593g.m3188a(SDKConstants.TAG, "注销安装广播");
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "注销安装广播 error");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: c */
    public void m2994c(String str) {
        C1593g.m3188a(SDKConstants.TAG, "start installation");
        for (Map.Entry<String, b> entry : this.f3238d.entrySet()) {
            if (str.equals(entry.getValue().f3252b)) {
                entry.getValue().f3258h = 1;
                m2983a(entry.getValue().f3256f);
                C1593g.m3188a(SDKConstants.TAG, "开始安装监控");
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:16:0x005f A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:7:0x0007, B:8:0x000c, B:10:0x0014, B:12:0x0017, B:13:0x001e, B:14:0x0055, B:16:0x005f, B:18:0x0063, B:20:0x0067, B:32:0x00a2, B:34:0x00a9, B:28:0x007e, B:31:0x0084, B:26:0x0076), top: B:36:0x0003, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:28:0x007e A[Catch: all -> 0x0072, Exception -> 0x0083, TRY_ENTER, TRY_LEAVE, TryCatch #2 {Exception -> 0x0083, blocks: (B:10:0x0014, B:12:0x0017, B:13:0x001e, B:28:0x007e), top: B:39:0x0014, outer: #0 }] */
    /* JADX WARN: Code duplicated, block: B:32:0x00a2 A[Catch: all -> 0x0072, TryCatch #0 {all -> 0x0072, blocks: (B:7:0x0007, B:8:0x000c, B:10:0x0014, B:12:0x0017, B:13:0x001e, B:14:0x0055, B:16:0x005f, B:18:0x0063, B:20:0x0067, B:32:0x00a2, B:34:0x00a9, B:28:0x007e, B:31:0x0084, B:26:0x0076), top: B:36:0x0003, inners: #1, #2 }] */
    /* JADX WARN: Code duplicated, block: B:34:0x00a9 A[Catch: all -> 0x0072, TRY_LEAVE, TryCatch #0 {all -> 0x0072, blocks: (B:7:0x0007, B:8:0x000c, B:10:0x0014, B:12:0x0017, B:13:0x001e, B:14:0x0055, B:16:0x005f, B:18:0x0063, B:20:0x0067, B:32:0x00a2, B:34:0x00a9, B:28:0x007e, B:31:0x0084, B:26:0x0076), top: B:36:0x0003, inners: #1, #2 }] */
    /* JADX INFO: renamed from: a */
    public synchronized void m2998a(Context context, C1519a c1519a, Object... objArr) {
        if (c1519a != null) {
            try {
                if (context != null) {
                    try {
                        Class.forName("com.iflytek.voiceads.download.DownloadService");
                        b bVar = new b(this, null);
                        if (objArr != null) {
                            try {
                                if (objArr.length > 0) {
                                    bVar.f3251a = (String) objArr[0];
                                } else {
                                    bVar.f3251a = c1519a.f3142L;
                                }
                                bVar.f3252b = C1590d.m3179a(bVar.f3251a);
                                bVar.f3253c = c1519a.f3135E;
                                bVar.f3254d = c1519a.f3140J.optJSONArray("download_start_urls");
                                bVar.f3255e = c1519a.f3140J.optJSONArray("download_complete_urls");
                                bVar.f3256f = c1519a.f3140J.optJSONArray("install_start_urls");
                                bVar.f3257g = c1519a.f3140J.optJSONArray("install_complete_urls");
                                bVar.f3258h = 0;
                                if (!this.f3237c.containsKey(bVar.f3252b)) {
                                    if (this.f3241g || !(context instanceof Activity)) {
                                        m2973a(bVar);
                                        if (this.f3240f != null) {
                                            this.f3240f.onConfirm();
                                            this.f3240f = null;
                                        }
                                    } else {
                                        Activity activity = (Activity) context;
                                        activity.runOnUiThread(new RunnableC1546f(this, activity, bVar));
                                    }
                                }
                            } catch (Exception e) {
                                C1593g.m3190b(SDKConstants.TAG, "parse downExpInfo" + e.getMessage());
                            }
                        } else {
                            bVar.f3251a = c1519a.f3142L;
                            bVar.f3252b = C1590d.m3179a(bVar.f3251a);
                            bVar.f3253c = c1519a.f3135E;
                            bVar.f3254d = c1519a.f3140J.optJSONArray("download_start_urls");
                            bVar.f3255e = c1519a.f3140J.optJSONArray("download_complete_urls");
                            bVar.f3256f = c1519a.f3140J.optJSONArray("install_start_urls");
                            bVar.f3257g = c1519a.f3140J.optJSONArray("install_complete_urls");
                            bVar.f3258h = 0;
                            if (!this.f3237c.containsKey(bVar.f3252b)) {
                                if (this.f3241g) {
                                    m2973a(bVar);
                                    if (this.f3240f != null) {
                                        this.f3240f.onConfirm();
                                        this.f3240f = null;
                                    }
                                } else {
                                    m2973a(bVar);
                                    if (this.f3240f != null) {
                                        this.f3240f.onConfirm();
                                        this.f3240f = null;
                                    }
                                }
                            }
                        }
                    } catch (ClassNotFoundException e2) {
                        C1593g.m3190b(SDKConstants.TAG, "service not found");
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m2999a(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            try {
                Class.forName("com.iflytek.voiceads.download.DownloadService");
                if (this.f3241g && (context instanceof Activity)) {
                    Activity activity = (Activity) context;
                    activity.runOnUiThread(new RunnableC1548h(this, activity, str));
                } else {
                    m2982a(str);
                    if (this.f3240f != null) {
                        this.f3240f.onConfirm();
                        this.f3240f = null;
                    }
                }
            } catch (ClassNotFoundException e) {
                C1593g.m3190b(SDKConstants.TAG, "service not found");
            }
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3000a(DialogListener dialogListener) {
        this.f3240f = dialogListener;
    }

    /* JADX INFO: renamed from: a */
    public void m3001a(boolean z) {
        this.f3241g = z;
    }
}

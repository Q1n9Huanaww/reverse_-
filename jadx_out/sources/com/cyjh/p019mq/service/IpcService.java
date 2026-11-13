package com.cyjh.p019mq.service;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.cyjh.event.Injector;
import com.cyjh.mobileanjian.ipc.C0746d;
import com.cyjh.mobileanjian.ipc.rpc.AndroidHelper;
import com.cyjh.mobileanjian.ipc.utils.C0821r;
import com.cyjh.mobileanjian.ipc.utils.FileUtils;
import com.cyjh.mobileanjian.ipc.utils.RomUtils;
import com.cyjh.mobileanjian.screencap.ScreenShoterV3;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.mqsdk.C0853a;
import com.cyjh.p019mq.p020a.C0830a;
import com.cyjh.p019mq.p022c.C0834c;
import com.cyjh.p019mq.sdk.MqRunner;
import com.cyjh.p019mq.sdk.MqRunnerLite;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: classes.dex */
public class IpcService extends Service {

    /* JADX INFO: renamed from: f */
    private static final String f1649f = "mqm_engine";

    /* JADX INFO: renamed from: g */
    private static final String f1650g = "ipc_server_port";

    /* JADX INFO: renamed from: h */
    private static final String f1651h = "/system/bin/app_process32";

    /* JADX INFO: renamed from: i */
    private static final String f1652i = "elfinject";

    /* JADX INFO: renamed from: j */
    private static final String f1653j = "model";

    /* JADX INFO: renamed from: k */
    private static final String f1654k = "code_cache";

    /* JADX INFO: renamed from: l */
    private static final String f1655l = "secondary-dexes";

    /* JADX INFO: renamed from: m */
    private static final String f1656m = "mycache";

    /* JADX INFO: renamed from: n */
    private static final String f1657n = "dalvik-cache";

    /* JADX INFO: renamed from: b */
    private int f1659b;

    /* JADX INFO: renamed from: e */
    private C0834c f1662e;

    /* JADX INFO: renamed from: a */
    private String f1658a = null;

    /* JADX INFO: renamed from: c */
    private String f1660c = "";

    /* JADX INFO: renamed from: d */
    private String f1661d = "";

    @Override // android.app.Service
    @Nullable
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        AndroidHelper.init(this);
        Injector.init(this);
        String absolutePath = getApplicationContext().getFilesDir().getAbsolutePath();
        new File(absolutePath).setExecutable(true, false);
        this.f1658a = getApplicationContext().getPackageName() + C0830a.f1523c;
        this.f1659b = m1331g();
        String str = getFilesDir().getAbsolutePath() + File.separator + C0830a.f1525e;
        String strMakeAbsolutePath = FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, "lib", C0830a.f1526f);
        String str2 = new File(f1651h).exists() ? f1651h : "app_process";
        File file = new File(FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, f1656m, f1657n));
        file.mkdirs();
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);
        file.getParentFile().setReadable(true, false);
        file.getParentFile().setWritable(true, false);
        file.getParentFile().setExecutable(true, false);
        this.f1660c = "export CLASSPATH=" + str + "\n" + String.format("exec %s %s %s %s %s %s &\n", str2, absolutePath, C0830a.f1524d, this.f1658a, strMakeAbsolutePath, Integer.valueOf(this.f1659b));
        if (RomUtils.isOppoR9S()) {
            this.f1660c = "export CLASSPATH=" + str + "\n" + String.format("exec %s -Xnodex2oat %s %s %s %s %s &\n", str2, absolutePath, C0830a.f1524d, this.f1658a, strMakeAbsolutePath, Integer.valueOf(this.f1659b));
        }
        this.f1661d = "export ANDROID_DATA=" + file.getParent() + "\n" + this.f1660c + "\necho Okay";
        new HandlerThreadC08501("ipcserver_thread").start();
        new C08512("startRootRequest").start();
        Log.i("VERSION", "build info: build on 2021-09-26 14:41:33");
    }

    /* JADX INFO: renamed from: a */
    private void m1320a() {
        String absolutePath = getApplicationContext().getFilesDir().getAbsolutePath();
        new File(absolutePath).setExecutable(true, false);
        this.f1658a = getApplicationContext().getPackageName() + C0830a.f1523c;
        this.f1659b = m1331g();
        String str = getFilesDir().getAbsolutePath() + File.separator + C0830a.f1525e;
        String strMakeAbsolutePath = FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, "lib", C0830a.f1526f);
        String str2 = new File(f1651h).exists() ? f1651h : "app_process";
        File file = new File(FileUtils.makeAbsolutePath(getApplicationInfo().dataDir, f1656m, f1657n));
        file.mkdirs();
        file.setReadable(true, false);
        file.setWritable(true, false);
        file.setExecutable(true, false);
        file.getParentFile().setReadable(true, false);
        file.getParentFile().setWritable(true, false);
        file.getParentFile().setExecutable(true, false);
        this.f1660c = "export CLASSPATH=" + str + "\n" + String.format("exec %s %s %s %s %s %s &\n", str2, absolutePath, C0830a.f1524d, this.f1658a, strMakeAbsolutePath, Integer.valueOf(this.f1659b));
        if (RomUtils.isOppoR9S()) {
            this.f1660c = "export CLASSPATH=" + str + "\n" + String.format("exec %s -Xnodex2oat %s %s %s %s %s &\n", str2, absolutePath, C0830a.f1524d, this.f1658a, strMakeAbsolutePath, Integer.valueOf(this.f1659b));
        }
        this.f1661d = "export ANDROID_DATA=" + file.getParent() + "\n" + this.f1660c + "\necho Okay";
    }

    @Override // android.app.Service
    public void onDestroy() {
        this.f1662e.onAppQuit();
        super.onDestroy();
        C0746d c0746dM728a = C0746d.m728a();
        if (c0746dM728a.f1005d) {
            c0746dM728a.m743a("exit");
            if (c0746dM728a.f1004c.isAlive()) {
                c0746dM728a.f1004c.interrupt();
            }
            try {
                c0746dM728a.f1006e.close();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        C0746d.f1001a = null;
    }

    @Override // android.app.Service, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        MqRunner.getInstance().notifyRotationStatus();
        MqRunnerLite.getInstance().notifyRotationStatus();
        ScreenShoterV3.getInstance().updateScreenSize();
    }

    /* JADX INFO: renamed from: b */
    private void m1322b() {
        File dir = getDir(f1653j, 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            for (String str : getResources().getAssets().list(f1653j)) {
                File file = new File(dir, str);
                FileUtils.copyAssetsFile(this, new File(f1653j, str).getPath(), file.getAbsolutePath());
                if (file.exists()) {
                    file.setReadable(true, false);
                }
            }
            File file2 = new File(getFilesDir(), C0830a.f1525e);
            FileUtils.copyAssetsFile(this, C0830a.f1525e, file2.getAbsolutePath());
            file2.setReadable(true, false);
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: c */
    private void m1324c() throws Throwable {
        String str = getFilesDir().getAbsolutePath() + File.separator + f1652i;
        if (C0821r.m1123b(this)) {
            FileUtils.copyRawFile(this, C0852R.raw.elfinject_x86, str);
        } else {
            FileUtils.copyRawFile(this, C0852R.raw.elfinject_arm, str);
        }
    }

    /* JADX INFO: renamed from: d */
    private void m1325d() {
        File externalFilesDir;
        File filesDir = getFilesDir();
        File file = new File(filesDir, C0830a.f1521a);
        FileUtils.writeStringToFile(file, this.f1661d);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        FileUtils.writeStringToFile(new File(filesDir, C0830a.f1522b), this.f1660c);
        C0830a.m1168a().setExecutable(true, false);
        if (Build.VERSION.SDK_INT < 23 || (externalFilesDir = getExternalFilesDir(null)) == null || !externalFilesDir.exists()) {
            return;
        }
        FileUtils.writeStringToFile(new File(externalFilesDir, C0830a.f1521a), this.f1661d);
    }

    /* JADX INFO: renamed from: com.cyjh.mq.service.IpcService$1 */
    final class HandlerThreadC08501 extends HandlerThread {
        HandlerThreadC08501(String str) {
            super(str);
        }

        @Override // android.os.HandlerThread
        protected final void onLooperPrepared() {
            super.onLooperPrepared();
            IpcService.this.f1662e = new C0834c(IpcService.this);
            C0834c c0834c = IpcService.this.f1662e;
            c0834c.f1570a.add(MqRunner.getInstance());
            IpcService.this.f1662e.m1244a(IpcService.this.f1658a, IpcService.this.f1659b);
        }
    }

    /* JADX INFO: renamed from: e */
    private void m1327e() {
        new HandlerThreadC08501("ipcserver_thread").start();
    }

    /* JADX INFO: renamed from: com.cyjh.mq.service.IpcService$2 */
    final class C08512 extends Thread {
        C08512(String str) {
            super(str);
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws Throwable {
            super.run();
            IpcService.m1326d(IpcService.this);
            IpcService.m1328e(IpcService.this);
            IpcService.m1330f(IpcService.this);
            C0746d.m728a().m742a(MqRunner.getInstance());
        }
    }

    /* JADX INFO: renamed from: f */
    private void m1329f() {
        new C08512("startRootRequest").start();
    }

    /* JADX INFO: renamed from: g */
    private static int m1331g() {
        int i = C0853a.f1675h;
        for (int i2 = 0; i2 < 9; i2++) {
            try {
                new ServerSocket(i).close();
                break;
            } catch (UnknownHostException e) {
                i++;
                ThrowableExtension.printStackTrace(e);
            } catch (IOException e2) {
                i++;
                ThrowableExtension.printStackTrace(e2);
            }
        }
        return i;
    }

    /* JADX INFO: renamed from: d */
    static /* synthetic */ void m1326d(IpcService ipcService) {
        File dir = ipcService.getDir(f1653j, 0);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            for (String str : ipcService.getResources().getAssets().list(f1653j)) {
                File file = new File(dir, str);
                FileUtils.copyAssetsFile(ipcService, new File(f1653j, str).getPath(), file.getAbsolutePath());
                if (file.exists()) {
                    file.setReadable(true, false);
                }
            }
            File file2 = new File(ipcService.getFilesDir(), C0830a.f1525e);
            FileUtils.copyAssetsFile(ipcService, C0830a.f1525e, file2.getAbsolutePath());
            file2.setReadable(true, false);
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: e */
    static /* synthetic */ void m1328e(IpcService ipcService) throws Throwable {
        String str = ipcService.getFilesDir().getAbsolutePath() + File.separator + f1652i;
        if (C0821r.m1123b(ipcService)) {
            FileUtils.copyRawFile(ipcService, C0852R.raw.elfinject_x86, str);
        } else {
            FileUtils.copyRawFile(ipcService, C0852R.raw.elfinject_arm, str);
        }
    }

    /* JADX INFO: renamed from: f */
    static /* synthetic */ void m1330f(IpcService ipcService) {
        File externalFilesDir;
        File filesDir = ipcService.getFilesDir();
        File file = new File(filesDir, C0830a.f1521a);
        FileUtils.writeStringToFile(file, ipcService.f1661d);
        file.setReadable(true, false);
        file.setExecutable(true, false);
        FileUtils.writeStringToFile(new File(filesDir, C0830a.f1522b), ipcService.f1660c);
        C0830a.m1168a().setExecutable(true, false);
        if (Build.VERSION.SDK_INT < 23 || (externalFilesDir = ipcService.getExternalFilesDir(null)) == null || !externalFilesDir.exists()) {
            return;
        }
        FileUtils.writeStringToFile(new File(externalFilesDir, C0830a.f1521a), ipcService.f1661d);
    }
}

package com.iflytek.collector.p060a.p061a;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.iflytek.collector.device.IDeviceInfo;
import com.p024ds.daisi.fragment.ScriptUipSetFragment;
import dalvik.system.DexClassLoader;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: renamed from: com.iflytek.collector.a.a.b */
/* JADX INFO: loaded from: classes.dex */
public class C1479b {

    /* JADX INFO: renamed from: a */
    private static volatile C1479b f3027a;

    /* JADX INFO: renamed from: b */
    private IDeviceInfo f3028b;

    /* JADX INFO: renamed from: c */
    private Context f3029c;

    /* JADX INFO: renamed from: d */
    private volatile String f3030d;

    /* JADX INFO: renamed from: e */
    private volatile boolean f3031e;

    /* JADX INFO: renamed from: f */
    private volatile String f3032f;

    private C1479b(Context context) {
        boolean z;
        try {
            this.f3029c = context.getApplicationContext();
            this.f3028b = (IDeviceInfo) new DexClassLoader(m2798c(), m2793g(), null, this.f3029c.getClassLoader()).loadClass("com.iflytek.collector.device.DeviceInfo").newInstance();
            new C1480c(this).start();
            z = false;
        } catch (Throwable unused) {
            this.f3028b = null;
            z = true;
        }
        m2791a(z);
    }

    /* JADX INFO: renamed from: a */
    public static C1479b m2789a(Context context) {
        if (f3027a == null) {
            synchronized (C1479b.class) {
                if (f3027a == null) {
                    f3027a = new C1479b(context);
                }
            }
        }
        return f3027a;
    }

    /* JADX INFO: renamed from: a */
    private static void m2790a(File file) {
        if (file.isDirectory()) {
            for (File file2 : file.listFiles()) {
                m2790a(file2);
            }
        }
        file.delete();
    }

    /* JADX INFO: renamed from: a */
    private void m2791a(boolean z) {
        try {
            if (this.f3031e) {
                return;
            }
            new Thread(new RunnableC1481d(this.f3029c, z)).start();
            this.f3031e = true;
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: renamed from: c */
    private void m2792c(String str) {
        File[] fileArrListFiles;
        File file = new File(str);
        if (!file.exists() || !file.isDirectory() || (fileArrListFiles = file.listFiles()) == null || fileArrListFiles.length <= 0) {
            return;
        }
        for (int i = 0; i < fileArrListFiles.length; i++) {
            if (fileArrListFiles[i] != null) {
                m2790a(fileArrListFiles[i]);
            }
        }
    }

    /* JADX INFO: renamed from: g */
    private String m2793g() {
        String str = this.f3029c.getFilesDir().getAbsolutePath() + File.separator + "dex/";
        File file = new File(str);
        if (!file.exists()) {
            file.mkdirs();
        }
        return str;
    }

    /* JADX INFO: renamed from: a */
    JSONObject m2794a() {
        try {
            if (this.f3028b != null) {
                return this.f3028b.getHeart(this.f3029c);
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2795a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3030d = str;
        SharedPreferences sharedPreferencesM2833b = C1488k.m2833b(this.f3029c);
        if (sharedPreferencesM2833b == null || str.equals(sharedPreferencesM2833b.getString("appid", ""))) {
            return;
        }
        SharedPreferences.Editor editorEdit = sharedPreferencesM2833b.edit();
        editorEdit.putString("appid", str);
        editorEdit.apply();
    }

    /* JADX INFO: renamed from: b */
    String m2796b() {
        try {
            return this.f3028b != null ? this.f3028b.getVersion() : ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG;
        } catch (Throwable unused) {
            return ScriptUipSetFragment.DISPLAY_TRY_TIME_FLAG;
        }
    }

    /* JADX INFO: renamed from: b */
    public void m2797b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.f3032f = str;
        SharedPreferences sharedPreferencesM2833b = C1488k.m2833b(this.f3029c);
        if (sharedPreferencesM2833b != null) {
            if (this.f3032f.equals(sharedPreferencesM2833b.getString("subid", ""))) {
                return;
            }
            SharedPreferences.Editor editorEdit = sharedPreferencesM2833b.edit();
            editorEdit.putString("subid", str);
            editorEdit.apply();
        }
    }

    /* JADX INFO: renamed from: c */
    String m2798c() {
        return (this.f3029c.getFilesDir().getAbsolutePath() + File.separator + "jar/") + "iflytek_device_info.zip";
    }

    /* JADX INFO: renamed from: d */
    void m2799d() {
        String absolutePath = this.f3029c.getFilesDir().getAbsolutePath();
        m2792c(absolutePath + File.separator + "jar/");
        m2792c(absolutePath + File.separator + "dex/");
    }

    /* JADX INFO: renamed from: e */
    public String m2800e() {
        return this.f3030d;
    }

    /* JADX INFO: renamed from: f */
    public String m2801f() {
        return this.f3032f;
    }
}

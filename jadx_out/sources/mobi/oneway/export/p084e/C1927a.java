package mobi.oneway.export.p084e;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import android.webkit.WebView;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.didi.virtualapk.PluginManager;
import com.didi.virtualapk.delegate.RemoteContentProvider;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import mobi.oneway.export.C1898a;
import mobi.oneway.export.C1903b;
import mobi.oneway.export.enums.PluginErrorType;
import mobi.oneway.export.p078a.C1899a;
import mobi.oneway.export.p078a.C1900b;
import mobi.oneway.export.p079b.C1904a;
import mobi.oneway.export.p083d.C1926f;
import mobi.oneway.export.p085f.C1941c;
import mobi.oneway.export.p085f.C1942d;
import mobi.oneway.export.p085f.C1944f;
import mobi.oneway.export.p085f.C1945g;
import mobi.oneway.export.p087g.C1952f;
import mobi.oneway.export.p087g.C1953g;
import mobi.oneway.export.p087g.C1954h;
import mobi.oneway.export.p087g.C1960n;
import mobi.oneway.export.p087g.C1962p;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: mobi.oneway.export.e.a */
/* JADX INFO: loaded from: classes.dex */
public class C1927a extends Thread {

    /* JADX INFO: renamed from: a */
    private String f4923a;

    /* JADX INFO: renamed from: b */
    private PluginManager f4924b;

    /* JADX INFO: renamed from: c */
    private C1945g f4925c;

    /* JADX INFO: renamed from: g */
    private Timer f4929g;

    /* JADX INFO: renamed from: h */
    private TimerTask f4930h;

    /* JADX INFO: renamed from: d */
    private boolean f4926d = false;

    /* JADX INFO: renamed from: f */
    private final long f4928f = 5000;

    /* JADX INFO: renamed from: e */
    private CountDownLatch f4927e = new CountDownLatch(1);

    public C1927a(String str) {
        this.f4923a = str;
        try {
            m4860c();
            this.f4924b = PluginManager.getInstance(C1900b.m4706a());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private String m4851a(String str) {
        return C1900b.m4706a().getDir("plugin", 0).getPath() + File.separator + str;
    }

    /* JADX INFO: renamed from: a */
    private String m4852a(JSONArray jSONArray, int i) {
        if (jSONArray != null) {
            try {
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        if (i == jSONObject.optInt("adp")) {
                            return jSONObject.getString("appId");
                        }
                    }
                }
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        return "";
    }

    /* JADX INFO: renamed from: a */
    private C1945g m4854a() throws Throwable {
        m4861d();
        if (this.f4925c == null && !this.f4926d) {
            this.f4926d = true;
            ClassLoader classLoaderM4721a = C1903b.m4721a();
            if (classLoaderM4721a != null) {
                try {
                    Class<?> clsLoadClass = classLoaderM4721a.loadClass(C1899a.f4829l);
                    this.f4925c = new C1945g();
                    this.f4925c.m4950a(clsLoadClass);
                    this.f4925c.m4948a(1);
                    this.f4925c.m4951a(classLoaderM4721a);
                    this.f4925c.m4954b(C1900b.m4710b());
                } catch (Exception e) {
                    C1929c.m4872a(PluginErrorType.shell_error_reflectClass, C1953g.m4999a((Throwable) e));
                }
            }
        }
        m4862e();
        return this.f4925c;
    }

    /* JADX INFO: renamed from: a */
    private C1945g m4855a(JSONObject jSONObject, JSONArray jSONArray) {
        return jSONObject.optInt("adp") == 1 ? m4854a() : m4858b(jSONObject, jSONArray);
    }

    /* JADX INFO: renamed from: a */
    private void m4856a(JSONArray jSONArray) {
        C1942d.m4935a(jSONArray.toString());
        if (jSONArray == null || jSONArray.length() <= 0) {
            return;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                C1942d.m4936a(new C1941c(jSONObjectOptJSONObject));
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4857a(JSONObject jSONObject) {
        C1904a c1904aM4727a = C1904a.m4727a();
        c1904aM4727a.m4736a(jSONObject.optBoolean("cacheAd"));
        c1904aM4727a.m4733a(jSONObject.optInt("reqExpireTime"));
    }

    /* JADX INFO: renamed from: b */
    private C1945g m4858b(JSONObject jSONObject, JSONArray jSONArray) {
        m4861d();
        int iOptInt = jSONObject.optInt("adp");
        String strOptString = jSONObject.optString("apkpkg");
        String strOptString2 = jSONObject.optString("verison");
        String strOptString3 = jSONObject.optString("adcl");
        try {
            this.f4924b.loadPlugin(new File(m4851a(iOptInt + ".apk")));
            ClassLoader classLoader = this.f4924b.getLoadedPlugin(strOptString).getClassLoader();
            Class<?> clsLoadClass = classLoader.loadClass(strOptString3);
            String strM4852a = m4852a(jSONArray, iOptInt);
            C1945g c1945g = new C1945g();
            c1945g.m4951a(classLoader);
            c1945g.m4948a(iOptInt);
            c1945g.m4950a(clsLoadClass);
            c1945g.m4952a(strOptString2);
            c1945g.m4949a(System.currentTimeMillis());
            c1945g.m4954b(strM4852a);
            return c1945g;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            m4862e();
            return null;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m4859b() throws Throwable {
        C1945g c1945gM4854a = m4854a();
        if (c1945gM4854a != null) {
            C1942d.m4937a(c1945gM4854a);
        }
        C1928b.m4863a().m4869c();
    }

    /* JADX INFO: renamed from: c */
    private void m4860c() {
        C1928b.m4863a().m4870c(new Runnable() { // from class: mobi.oneway.export.e.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (Build.VERSION.SDK_INT >= 28) {
                        try {
                            int iMyPid = Process.myPid();
                            Context contextM4706a = C1900b.m4706a();
                            String str = contextM4706a.getPackageName() + iMyPid;
                            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) contextM4706a.getSystemService("activity")).getRunningAppProcesses()) {
                                if (runningAppProcessInfo.pid == iMyPid) {
                                    str = runningAppProcessInfo.processName;
                                }
                            }
                            WebView.setDataDirectorySuffix(str);
                        } catch (Exception e) {
                            ThrowableExtension.printStackTrace(e);
                        }
                    }
                    new WebView(C1900b.m4706a());
                } catch (Exception e2) {
                    ThrowableExtension.printStackTrace(e2);
                } finally {
                    if (C1927a.this.f4927e != null) {
                        C1927a.this.f4927e.countDown();
                    }
                }
            }
        });
    }

    /* JADX INFO: renamed from: d */
    private void m4861d() {
        m4862e();
        this.f4930h = new TimerTask() { // from class: mobi.oneway.export.e.a.2
            @Override // java.util.TimerTask, java.lang.Runnable
            public void run() {
                C1928b.m4863a().m4869c();
            }
        };
        this.f4929g = new Timer();
        this.f4929g.schedule(this.f4930h, 5000L);
    }

    /* JADX INFO: renamed from: e */
    private void m4862e() {
        if (this.f4930h != null) {
            this.f4930h.cancel();
            this.f4930h = null;
        }
        if (this.f4929g != null) {
            this.f4929g.cancel();
            this.f4929g = null;
        }
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        super.run();
        try {
            if (this.f4927e != null) {
                this.f4927e.await(5000L, TimeUnit.MILLISECONDS);
            }
            JSONObject jSONObjectM4847m = new C1926f(C1899a.f4823f, C1899a.f4818a).m4823a("publishId", (Object) this.f4923a).m4823a("shellVersion", (Object) C1898a.f4817f).m4823a("dmd", (Object) Build.MODEL).m4823a("dmk", (Object) Build.BRAND).m4823a("ip", (Object) C1960n.m5055a(C1900b.m4706a())).m4823a("osv", (Object) Build.VERSION.RELEASE).m4823a("osl", Integer.valueOf(Build.VERSION.SDK_INT)).m4823a("ts", Long.valueOf(System.currentTimeMillis())).m4823a("osi", (Object) C1944f.m4946a()).m4823a(RemoteContentProvider.KEY_PKG, (Object) C1900b.m4706a().getPackageName()).m4823a("did", (Object) C1952f.m4985a()).m4823a("aid", (Object) C1952f.m4991e()).m4847m();
            boolean zOptBoolean = jSONObjectM4847m.optBoolean("upgrade");
            boolean zOptBoolean2 = jSONObjectM4847m.optBoolean("e");
            String strOptString = jSONObjectM4847m.optString("appToken");
            String strOptString2 = jSONObjectM4847m.optString("sc");
            JSONArray jSONArrayOptJSONArray = jSONObjectM4847m.optJSONArray("plugins");
            JSONArray jSONArrayOptJSONArray2 = jSONObjectM4847m.optJSONArray("appIdConfigs");
            JSONArray jSONArrayOptJSONArray3 = jSONObjectM4847m.optJSONArray("placements");
            C1900b.m4711b(strOptString);
            C1900b.m4709a(zOptBoolean2);
            C1900b.m4712c(strOptString2);
            if (zOptBoolean && jSONArrayOptJSONArray.length() != 0) {
                m4856a(jSONArrayOptJSONArray3);
                m4857a(jSONObjectM4847m);
                for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArrayOptJSONArray.getJSONObject(i);
                    String strOptString3 = jSONObject.optString("hash");
                    int iOptInt = jSONObject.optInt("adp");
                    String strOptString4 = jSONObject.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
                    File file = new File(m4851a(iOptInt == 1 ? C1899a.f4827j : iOptInt + ".apk"));
                    String upperCase = C1954h.m5021e(file) ? C1962p.m5058a(file).toUpperCase() : null;
                    if (upperCase == null || !upperCase.equalsIgnoreCase(strOptString3)) {
                        byte[] bArrM4846l = new C1926f(strOptString4).m4818a(ExToast.LENGTH_SHORT).m4846l();
                        if (C1962p.m5060a(bArrM4846l).toUpperCase().equalsIgnoreCase(strOptString3)) {
                            C1954h.m5006a(file, bArrM4846l);
                        }
                    }
                    C1945g c1945gM4855a = m4855a(jSONObject, jSONArrayOptJSONArray2);
                    if (c1945gM4855a != null) {
                        C1942d.m4937a(c1945gM4855a);
                    }
                }
                C1928b.m4863a().m4869c();
                return;
            }
            m4859b();
        } catch (IOException | JSONException | Exception e) {
            ThrowableExtension.printStackTrace(e);
            m4859b();
        }
    }
}

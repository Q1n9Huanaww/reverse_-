package com.goldcoast.sdk.domain;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.ConditionVariable;
import android.os.Process;
import android.support.v4.app.FrameMetricsAggregator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.cyjh.share.constants.InterfaceRelatedConstants;
import com.goldcoast.sdk.p025a.C1111b;
import com.goldcoast.sdk.p025a.C1113d;
import com.goldcoast.sdk.p025a.C1115f;
import com.goldcoast.sdk.p025a.C1116g;
import com.goldcoast.sdk.p026b.C1117a;
import com.goldcoast.sdk.p026b.C1118b;
import com.goldcoast.sdk.p027c.C1119a;
import com.goldcoast.sdk.p027c.C1121c;
import com.goldcoast.sdk.p027c.C1122d;
import com.goldcoast.sdk.p027c.C1123e;
import com.goldcoast.sdk.p027c.C1125g;
import com.goldcoast.sdk.p027c.C1126h;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.king.sdk.C1641i;
import com.king.sdk.IDFEE16B42C8B2890D8FF2860AF5562B1;
import com.king.sdk.KingListener;
import com.p004b.p005a.C0589ai;
import com.p004b.p005a.C0596ap;
import com.p004b.p005a.C0627x;
import com.p004b.p005a.C0628y;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.p024ds.daisi.net.URL.URLConstant;
import com.umeng.commonsdk.proguard.C1799v;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateEncodingException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import net.lingala.zip4j.util.InternalZipConstants;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class EntryPoint {
    public static final byte RS_LONG = 1;
    public static final byte RS_MIXED = 2;
    public static final byte RS_TEMP = 0;
    public static final byte S_120 = 3;
    public static final byte S_123 = 2;
    public static final byte S_43 = 1;
    public static final byte S_47 = 4;

    /* JADX INFO: renamed from: a */
    private static EntryPoint f1809a = null;

    /* JADX INFO: renamed from: b */
    private static byte f1810b = 1;

    /* JADX INFO: renamed from: c */
    private static String f1811c = null;

    /* JADX INFO: renamed from: d */
    private static String f1812d = null;

    /* JADX INFO: renamed from: e */
    private static String f1813e = null;
    public static File extractDir = null;

    /* JADX INFO: renamed from: f */
    private static String f1814f = null;

    /* JADX INFO: renamed from: g */
    private static C0589ai f1815g = new C0589ai();

    /* JADX INFO: renamed from: h */
    private static AnalyseResult f1816h = null;

    /* JADX INFO: renamed from: i */
    private static String f1817i = null;

    /* JADX INFO: renamed from: j */
    private static int f1818j = 60;

    /* JADX INFO: renamed from: k */
    private static int f1819k = -1;

    /* JADX INFO: renamed from: l */
    private static byte f1820l = 0;

    /* JADX INFO: renamed from: m */
    private static boolean f1821m = true;
    protected static Application mContext;

    /* JADX INFO: renamed from: n */
    private static final boolean f1822n;

    /* JADX INFO: renamed from: A */
    private C1113d f1823A;

    /* JADX INFO: renamed from: B */
    private C1113d f1824B;

    /* JADX INFO: renamed from: F */
    private Pair<String, String> f1828F;

    /* JADX INFO: renamed from: r */
    private int f1832r;

    /* JADX INFO: renamed from: s */
    private int f1833s;

    /* JADX INFO: renamed from: t */
    private int f1834t;

    /* JADX INFO: renamed from: u */
    private int f1835u;

    /* JADX INFO: renamed from: v */
    private ConnectivityManager f1836v;

    /* JADX INFO: renamed from: w */
    private JNIWrapper f1837w;

    /* JADX INFO: renamed from: x */
    private String f1838x;

    /* JADX INFO: renamed from: z */
    private C1121c f1840z;

    /* JADX INFO: renamed from: o */
    private final String[] f1829o = {"天", "干", "地", "支", "山"};

    /* JADX INFO: renamed from: p */
    private final KingListener f1830p = new BinderC1128a(this);

    /* JADX INFO: renamed from: q */
    private boolean f1831q = false;

    /* JADX INFO: renamed from: y */
    private boolean f1839y = false;

    /* JADX INFO: renamed from: C */
    private boolean f1825C = false;

    /* JADX INFO: renamed from: D */
    private boolean f1826D = false;

    /* JADX INFO: renamed from: E */
    private BroadcastReceiver f1827E = new C1130c(this);

    /* JADX INFO: renamed from: f */
    static /* synthetic */ int m1468f(EntryPoint entryPoint) {
        int i = entryPoint.f1833s;
        entryPoint.f1833s = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: h */
    static /* synthetic */ boolean m1472h(EntryPoint entryPoint) {
        entryPoint.f1825C = false;
        return false;
    }

    static {
        f1822n = Build.VERSION.SDK_INT >= 14;
        try {
            System.loadLibrary("goldcoast");
        } catch (Exception e) {
            f1821m = false;
            ThrowableExtension.printStackTrace(e);
        }
    }

    private EntryPoint() {
        C1126h.m1432a();
        C1126h.m1435a("client", f1817i);
        if (f1821m) {
            this.f1837w = new JNIWrapper();
        }
        extractDir = new File(mContext.getFilesDir().getAbsoluteFile() + File.separator + "ota" + File.separator + "elf");
        try {
            mContext.unregisterReceiver(this.f1827E);
        } catch (Exception unused) {
        }
        this.f1836v = (ConnectivityManager) mContext.getSystemService("connectivity");
        mContext.registerReceiver(this.f1827E, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        C1125g.m1429a(mContext);
        this.f1840z = C1121c.m1410a(mContext);
        initHost("http://www.newbyvideo.com:10001");
        C1118b.m1403a(f1811c);
    }

    public static void init(Application application, String str, AnalyseResult analyseResult, int i, byte b) {
        mContext = application;
        f1816h = analyseResult;
        C1126h.m1434a(mContext);
        f1817i = str;
        f1810b = b;
        if (i >= 120) {
            f1818j = i;
        }
    }

    public static EntryPoint instance() {
        if (f1809a == null) {
            f1809a = new EntryPoint();
        }
        return f1809a;
    }

    public void initHost(String str) {
        f1811c = str;
        f1812d = String.format("%s/api/v1/phones", f1811c);
        f1813e = String.format("%s/api/v1/phones/referer", f1811c);
        f1814f = String.format("%s/api/v1/where_is_the_way", f1811c);
    }

    public void braFlush() {
        if (extractDir == null || extractDir.listFiles() == null) {
            return;
        }
        try {
            for (File file : extractDir.listFiles()) {
                file.delete();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1456b() {
        File file = new File(extractDir.getParent());
        if (file.listFiles() != null) {
            try {
                for (File file2 : file.listFiles()) {
                    file2.delete();
                }
            } catch (Exception unused) {
            }
        }
        extractDir.mkdirs();
        this.f1839y = false;
    }

    /* JADX INFO: renamed from: c */
    private void m1461c() {
        File file = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + "dump");
        if (file.exists()) {
            file.delete();
        }
        this.f1831q = false;
        this.f1833s = 0;
    }

    /* JADX INFO: renamed from: a */
    private boolean m1452a(AnalyseResult analyseResult) {
        boolean z;
        NetworkInfo[] allNetworkInfo;
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService("connectivity");
        if (connectivityManager != null && (allNetworkInfo = connectivityManager.getAllNetworkInfo()) != null) {
            int i = 0;
            while (true) {
                if (i >= allNetworkInfo.length) {
                    z = false;
                    break;
                }
                if (allNetworkInfo[i].getState() == NetworkInfo.State.CONNECTED) {
                    z = true;
                    break;
                }
                i++;
            }
        } else {
            z = false;
            break;
        }
        if (!z) {
            m1460b("start failed: 当前网络连接异常，请检测网络连接是否可用.", 1);
            return false;
        }
        C1126h.m1432a();
        if (TextUtils.isEmpty(C1126h.m1433a("client")) || mContext == null) {
            m1460b("start failed: 请在Application中调用 EntryPoint.init([context],[token])", 1);
            return false;
        }
        if (analyseResult != null) {
            return true;
        }
        m1460b("start failed: 请初始化analyseResult参数", 1);
        return false;
    }

    public synchronized void analyse(AnalyseResult analyseResult) {
        f1819k = -1;
        f1816h = analyseResult;
        if (m1452a(f1816h)) {
            if (getStatus()) {
                m1467e();
                return;
            }
            if (f1819k == 0) {
                m1467e();
                return;
            }
            if (this.f1825C) {
                m1460b("busying now\n$$$ failed", 1);
                return;
            }
            m1456b();
            m1461c();
            m1469f();
            JSONObject jSONObjectM1465d = m1465d();
            for (int i = 0; i < this.f1829o.length; i++) {
                if (i != 3) {
                    this.f1832r = i;
                    if (this.f1831q) {
                        return;
                    }
                    braFlush();
                    if (this.f1825C) {
                        break;
                    }
                    new Thread(new RunnableC1131d(this)).start();
                    if (f1819k != 0) {
                        ConditionVariable conditionVariable = new ConditionVariable(false);
                        m1460b(String.format("正在云端  [%s]  字组查找方案 请等待...", this.f1829o[i]), 2);
                        jSONObjectM1465d.put("category", i);
                        jSONObjectM1465d.put("sdkV", "4.0.14");
                        jSONObjectM1465d.put("rtp", (int) f1810b);
                        C0627x c0627xM503a = new C0628y().m504a("body", C1119a.m1404a(jSONObjectM1465d.toString())).m503a();
                        C1125g.m1428a();
                        C1125g.m1431b("######\n" + String.format("<analyseQ.Enc>:%s\n", c0627xM503a.toString()));
                        conditionVariable.close();
                        f1815g.m372a(new C0596ap().m410a(f1812d).m409a(c0627xM503a).m406a()).mo394a(new C1132e(this, conditionVariable, new boolean[]{false}));
                        conditionVariable.block();
                    }
                }
            }
        }
    }

    public synchronized List<C1113d> manual(int i, AnalyseResult analyseResult) {
        if (!f1821m) {
            return null;
        }
        f1819k = -1;
        f1816h = null;
        f1816h = analyseResult;
        if (!m1452a(f1816h)) {
            return null;
        }
        if (f1819k == 0) {
            m1467e();
            return null;
        }
        if (this.f1825C) {
            m1460b("busying now\n$$$ failed", 1);
            return null;
        }
        m1456b();
        m1461c();
        m1469f();
        this.f1832r = i;
        JSONObject jSONObjectM1465d = m1465d();
        new Thread(new RunnableC1133f(this)).start();
        ConditionVariable conditionVariable = new ConditionVariable(false);
        m1460b(String.format("正在云端  [%s]  字组查找方案 请等待...", this.f1829o[i]), 2);
        jSONObjectM1465d.put("category", i);
        jSONObjectM1465d.put("sdkV", "4.0.14");
        jSONObjectM1465d.put("rtp", (int) f1810b);
        C0627x c0627xM503a = new C0628y().m504a("body", C1119a.m1404a(jSONObjectM1465d.toString())).m503a();
        C1125g.m1428a();
        C1125g.m1431b("######\n" + String.format("<analyseQ.Enc>:%s\n", c0627xM503a.toString()));
        conditionVariable.close();
        ArrayList arrayList = new ArrayList();
        f1815g.m372a(new C0596ap().m410a(f1812d).m409a(c0627xM503a).m406a()).mo394a(new C1134g(this, arrayList, conditionVariable));
        conditionVariable.block();
        return arrayList;
    }

    /* JADX INFO: renamed from: d */
    private JSONObject m1465d() throws JSONException {
        C1122d.m1416a(mContext);
        C1111b c1111bM1415a = C1122d.m1415a();
        JSONObject jSONObject = new JSONObject();
        C1115f c1115f = new C1115f(C1115f.m1383a());
        C1122d.m1416a(mContext);
        c1115f.m1384a(C1122d.m1418c().m1381b());
        c1111bM1415a.m1363a(c1115f);
        jSONObject.put("device", c1111bM1415a.m1360a());
        jSONObject.put("self", mContext.getPackageName());
        C1126h.m1432a();
        jSONObject.put("token", C1126h.m1433a("client"));
        jSONObject.put(C1799v.f4319j, mContext.getPackageName());
        jSONObject.put("signature", getCertificateFingerprintMD5());
        jSONObject.put("keyword", "");
        return jSONObject;
    }

    /* JADX INFO: renamed from: b */
    private static void m1459b(String str) throws IOException {
        String str2 = str.split("\\?")[0];
        String strSubstring = str2.substring(str2.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1, str2.length());
        C1117a.m1402a(str, mContext, str2);
        String str3 = extractDir.getAbsolutePath() + File.separator + strSubstring;
        m1441a(new FileInputStream(str3), extractDir.getAbsolutePath());
        new File(str3).delete();
    }

    /* JADX INFO: renamed from: c */
    private void m1463c(String str) throws IOException {
        if (this.f1839y) {
            return;
        }
        String str2 = str.split("\\?")[0];
        String strSubstring = str2.substring(str2.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1, str2.length());
        String str3 = extractDir.getAbsolutePath() + File.separator + strSubstring;
        C1117a.m1402a(str, mContext, strSubstring);
        m1441a(new FileInputStream(str3), extractDir.getParent());
        new File(str3).delete();
        String str4 = String.format("%s%slibsu.so", extractDir.getParent(), File.separator);
        File file = new File(mContext.getFilesDir().getAbsolutePath() + File.separator + "permmgr" + File.separator + "libsu.so");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(str4);
        C1123e.m1424a(file2, file);
        C1123e.m1421a(file);
        file2.delete();
        this.f1839y = true;
    }

    /* JADX INFO: renamed from: e */
    private void m1467e() {
        m1460b("$$$ success", 0);
        this.f1831q = true;
        braFlush();
    }

    public void getBra(C1113d c1113d) throws IOException {
        C1117a.m1402a(c1113d.m1379f(), mContext, c1113d.m1372a());
        if (TextUtils.isEmpty(c1113d.m1374b())) {
            m1460b("poc is null", 2);
        } else if (c1113d.m1372a().endsWith(".zip")) {
            String str = extractDir.getAbsolutePath() + File.separator + c1113d.m1372a();
            c1113d.m1373a(m1441a(new FileInputStream(str), extractDir.getAbsolutePath()));
            new File(str).delete();
        }
        this.f1823A = c1113d;
        if (this.f1840z.m1414c(c1113d.m1372a())) {
            m1460b("$$$ skip", 2);
            return;
        }
        if (this.f1832r == 4) {
            if (m1438a(c1113d) == 3000) {
                m1467e();
                return;
            } else {
                m1460b("$$$ failed", 1);
                braFlush();
                return;
            }
        }
        m1455b(c1113d);
    }

    @TargetApi(3)
    /* JADX INFO: renamed from: a */
    private void m1451a(String str, int i, String str2, int i2) {
        File file = new File(extractDir.getAbsolutePath(), str);
        C1116g c1116g = new C1116g();
        C1115f c1115f = new C1115f(C1115f.m1383a());
        C1122d.m1416a(mContext);
        c1115f.m1384a(C1122d.m1415a().m1365b().m1381b());
        c1116g.m1389a(c1115f);
        c1116g.m1390a(str);
        c1116g.m1387a(i);
        c1116g.m1392b(str2);
        c1116g.m1388a(System.currentTimeMillis());
        C1122d.m1416a(mContext);
        c1116g.m1393c(C1122d.m1417b());
        try {
            TelephonyManager telephonyManager = (TelephonyManager) mContext.getSystemService(URLConstant.USER_ACCOUNT);
            c1116g.m1394d(telephonyManager.getNetworkOperatorName());
            c1116g.m1395e(telephonyManager.getSimOperatorName());
        } catch (Exception unused) {
        }
        c1116g.m1396f(Locale.getDefault().getDisplayLanguage());
        c1116g.m1397g(Locale.getDefault().getDisplayCountry());
        C1126h.m1432a();
        c1116g.m1398h(C1126h.m1433a("client"));
        c1116g.m1400j(mContext.getPackageName());
        c1116g.m1401k(getCertificateFingerprintMD5());
        c1116g.m1391b(i2);
        c1116g.m1399i("4.0.14");
        file.delete();
        try {
            m1450a(C1119a.m1404a(c1116g.m1386a().toString()), -1, 1);
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m1450a(String str, int i, int i2) {
        C0627x c0627xM503a = new C0628y().m504a("body", str).m503a();
        StringBuilder sb = new StringBuilder();
        if (i2 == 2) {
            sb.append("######rePost\n");
        } else {
            sb.append("######\n");
        }
        sb.append(String.format("<feedbackQ.Enc>:%s\n", c0627xM503a.toString()));
        C1125g.m1428a();
        C1125g.m1431b(sb.toString());
        ConditionVariable conditionVariable = new ConditionVariable(false);
        conditionVariable.close();
        f1815g.m372a(new C0596ap().m410a(f1813e).m409a(c0627xM503a).m406a()).mo394a(new C1135h(this, i2, str, conditionVariable, i));
        conditionVariable.block();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: f */
    public void m1469f() {
        new Thread(new RunnableC1136i(this)).start();
    }

    /* JADX INFO: renamed from: a */
    private int m1438a(C1113d c1113d) {
        m1460b(String.format("key:%s\norder:%d name: %s", c1113d.m1378e(), Integer.valueOf(c1113d.m1377d()), c1113d.m1372a()), 2);
        try {
            if (this.f1837w == null) {
                return -1;
            }
            return this.f1837w.loadSO(String.format("%s/ota/elf/%s", mContext.getFilesDir(), c1113d.m1372a()));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        }
    }

    @TargetApi(5)
    /* JADX INFO: renamed from: b */
    private Pair<String, String> m1455b(C1113d c1113d) {
        if (f1819k == 0) {
            return new Pair<>("yes", "$$$ success");
        }
        ConditionVariable conditionVariable = new ConditionVariable(false);
        conditionVariable.close();
        C1121c c1121c = this.f1840z;
        String strM1372a = c1113d.m1372a();
        if (!c1121c.m1414c(strM1372a)) {
            String strM1411a = c1121c.m1411a();
            JSONArray jSONArray = null;
            if (TextUtils.isEmpty(strM1411a)) {
                jSONArray = new JSONArray();
            } else {
                try {
                    jSONArray = new JSONArray(strM1411a);
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
            try {
                jSONArray.put(strM1372a);
                c1121c.m1412a(jSONArray.toString());
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        new Thread(new RunnableC1137j(this, c1113d)).start();
        new Thread(new RunnableC1129b(this, conditionVariable)).start();
        conditionVariable.block();
        this.f1840z.m1413b(c1113d.m1372a());
        if (this.f1828F == null) {
            this.f1828F = new Pair<>("no", "not return any result");
        }
        return this.f1828F;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m1460b(String str, int i) {
        f1819k = i;
        if (i == 0) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (this.f1824B == null || !this.f1824B.m1372a().equals(this.f1823A.m1372a())) {
                this.f1824B = this.f1823A;
                this.f1840z.m1412a("");
                if (this.f1823A != null) {
                    m1451a(this.f1823A.m1372a(), 1, str, this.f1823A.m1376c());
                }
                for (int i2 = 0; i2 < 30; i2++) {
                    try {
                        if (getStatus()) {
                            break;
                        }
                        Thread.sleep(1000L);
                    } catch (InterruptedException e) {
                        ThrowableExtension.printStackTrace(e);
                    }
                }
                f1816h.onSuccess(str);
                return;
            }
            return;
        }
        if (i == 1) {
            f1816h.onFailed(str);
            braFlush();
            if (this.f1823A != null) {
                m1451a(this.f1823A.m1372a(), -1, str, this.f1823A.m1376c());
                return;
            }
            return;
        }
        if (i == 2) {
            f1816h.onProgress(str);
            return;
        }
        if (this.f1832r == 4 && this.f1834t - 1 == this.f1835u) {
            f1816h.onFailed(str);
            braFlush();
            if (this.f1823A != null) {
                m1451a(this.f1823A.m1372a(), -1, str, this.f1823A.m1376c());
                return;
            }
            return;
        }
        f1816h.onException(str);
    }

    /* JADX INFO: renamed from: a */
    private static String m1441a(InputStream inputStream, String str) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        String name = "";
        zipInputStream.available();
        String name2 = "";
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry == null) {
                break;
            }
            if (!name.startsWith("META-INF") && !nextEntry.isDirectory()) {
                if (nextEntry.getName().startsWith("_")) {
                    name2 = nextEntry.getName();
                }
                i++;
                name = nextEntry.getName();
                File file = new File(str, name);
                file.delete();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                m1448a(zipInputStream, fileOutputStream);
                fileOutputStream.close();
                C1123e.m1421a(file);
            }
        }
        zipInputStream.close();
        return i > 1 ? name2 : name;
    }

    /* JADX INFO: renamed from: a */
    private static void m1448a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return;
            } else {
                outputStream.write(bArr, 0, i);
            }
        }
    }

    public String getStackString(Throwable th) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        ThrowableExtension.printStackTrace(th, printWriter);
        printWriter.close();
        return stringWriter.toString();
    }

    public void cleanSkipFlag() {
        this.f1840z.m1412a("");
    }

    public boolean hasSkip() {
        return !TextUtils.isEmpty(this.f1840z.m1411a());
    }

    public boolean getStatus() {
        boolean zM1471g = m1471g();
        f1820l = (byte) 0;
        return zM1471g;
    }

    /* JADX INFO: renamed from: g */
    private static boolean m1471g() {
        try {
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress("darwin.rodo", LocalSocketAddress.Namespace.ABSTRACT));
            localSocket.close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public void execDarwin(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        File file = new File(mContext.getFilesDir(), "tmp.sh");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                ThrowableExtension.printStackTrace(e);
            }
        }
        C1123e.m1423a(strArr, file);
        C1123e.m1422a(file.getAbsolutePath(), FrameMetricsAggregator.EVERY_DURATION);
        m1454a(file);
    }

    /* JADX INFO: renamed from: a */
    private static boolean m1454a(File file) {
        try {
            LocalSocket localSocket = new LocalSocket();
            localSocket.connect(new LocalSocketAddress("darwin.rodo", LocalSocketAddress.Namespace.ABSTRACT));
            byte[] bytes = file.getAbsolutePath().getBytes();
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bytes.length + 4);
            byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate.putInt(bytes.length);
            byteBufferAllocate.put(bytes);
            localSocket.getOutputStream().write(byteBufferAllocate.array());
            byte[] bytes2 = String.valueOf(Process.myPid()).getBytes();
            Log.d("KingDaemon", "client pid=" + Process.myPid());
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bytes2.length + 4);
            byteBufferAllocate2.order(ByteOrder.LITTLE_ENDIAN);
            byteBufferAllocate2.putInt(bytes2.length);
            byteBufferAllocate2.put(bytes2);
            localSocket.getOutputStream().write(byteBufferAllocate2.array());
            localSocket.close();
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public boolean exec(String[] strArr) {
        if (!getStatus()) {
            Log.d("EntryPoint", "getStatus failed, Service not start");
            return false;
        }
        if (f1820l == 0) {
            execDarwin(strArr);
        } else if (f1820l == 1) {
            try {
                C1641i c1641iM3404a = C1641i.m3404a(mContext);
                int iMyPid = Process.myPid();
                String packageName = mContext.getPackageName();
                C1126h.m1432a();
                c1641iM3404a.m3407a(strArr, iMyPid, packageName, C1126h.m1433a("client"));
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        } else {
            Log.d("EntryPoint", "Service has been stoped");
            return false;
        }
        return true;
    }

    public String getCertificateFingerprintMD5() {
        PackageInfo packageInfo;
        CertificateFactory certificateFactory;
        X509Certificate x509Certificate;
        try {
            packageInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 64);
        } catch (PackageManager.NameNotFoundException e) {
            ThrowableExtension.printStackTrace(e);
            packageInfo = null;
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(packageInfo.signatures[0].toByteArray());
        try {
            certificateFactory = CertificateFactory.getInstance("X509");
        } catch (CertificateException e2) {
            ThrowableExtension.printStackTrace(e2);
            certificateFactory = null;
        }
        try {
            x509Certificate = (X509Certificate) certificateFactory.generateCertificate(byteArrayInputStream);
        } catch (CertificateException e3) {
            ThrowableExtension.printStackTrace(e3);
            x509Certificate = null;
        }
        try {
            return m1443a(MessageDigest.getInstance("MD5").digest(x509Certificate.getEncoded()));
        } catch (NoSuchAlgorithmException e4) {
            ThrowableExtension.printStackTrace(e4);
            return null;
        } catch (CertificateEncodingException e5) {
            ThrowableExtension.printStackTrace(e5);
            return null;
        }
    }

    /* JADX INFO: renamed from: a */
    private static String m1443a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            String hexString = Integer.toHexString(bArr[i]);
            int length = hexString.length();
            if (length == 1) {
                hexString = AccountManager.TYPE_LOGIN + hexString;
            }
            if (length > 2) {
                hexString = hexString.substring(length - 2, length);
            }
            sb.append(hexString.toUpperCase());
            if (i < bArr.length - 1) {
                sb.append(':');
            }
        }
        return sb.toString();
    }

    public boolean registerStatus(boolean z, KingListener kingListener) {
        if (!getStatus()) {
            return false;
        }
        if (kingListener == null) {
            kingListener = this.f1830p;
        }
        if (z) {
            try {
                C1641i.m3404a(mContext).m3405a((IDFEE16B42C8B2890D8FF2860AF5562B1) kingListener);
                return true;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
        } else {
            try {
                C1641i.m3404a(mContext).m3406a(kingListener);
                return true;
            } catch (Exception e2) {
                ThrowableExtension.printStackTrace(e2);
            }
        }
        return false;
    }

    public boolean sendmsg(String str, String str2) {
        try {
            return C1641i.m3404a(mContext).m3409a(str, str2);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    public boolean sendData(String str, int i, byte[] bArr) {
        try {
            return C1641i.m3404a(mContext).m3408a(str, i, bArr);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return false;
        }
    }

    /* JADX INFO: renamed from: c */
    static /* synthetic */ void m1462c(EntryPoint entryPoint) {
        if (TextUtils.isEmpty(entryPoint.f1840z.m1411a())) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("rf_md5", entryPoint.f1840z.m1411a());
            C1122d.m1416a(mContext);
            jSONObject.put(InterfaceRelatedConstants.ANDROID_ID_SDCARD_FILE, C1122d.m1417b());
            C1126h.m1432a();
            jSONObject.put("token", C1126h.m1433a("client"));
            jSONObject.put(C1799v.f4319j, mContext.getPackageName());
            jSONObject.put("signature", entryPoint.getCertificateFingerprintMD5());
            f1815g.m372a(new C0596ap().m410a(f1814f).m409a(new C0628y().m504a("body", C1119a.m1404a(jSONObject.toString())).m503a()).m406a()).mo393a();
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1445a(EntryPoint entryPoint, String str) throws JSONException, IOException {
        String strOptString = new JSONObject(str).optString("body", "");
        int i = 1;
        if (TextUtils.isEmpty(strOptString)) {
            if (entryPoint.f1832r == 4) {
                entryPoint.m1460b("response body is null\n$$$ failed", 1);
                return;
            } else {
                entryPoint.m1460b("response body is null", 2);
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("######\n");
        char c = 0;
        sb.append(String.format("<analyseP.Enc>:%s\n", str));
        C1125g.m1428a();
        C1125g.m1431b(sb.toString());
        JSONObject jSONObject = new JSONObject(C1119a.m1405b(strOptString));
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("bra");
        entryPoint.f1834t = jSONArrayOptJSONArray.length();
        String strOptString2 = jSONObject.optString("global", "");
        int iOptInt = jSONObject.optInt("category", 0);
        String strOptString3 = jSONObject.optString("token", "");
        if (!TextUtils.isEmpty(strOptString3)) {
            C1126h.m1432a();
            C1126h.m1435a("client", strOptString3);
        }
        entryPoint.m1463c(strOptString2);
        String strOptString4 = jSONObject.optString("poc", "");
        ArrayList arrayList = new ArrayList();
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            if (entryPoint.f1832r == 4) {
                entryPoint.m1460b("not found suitable su file\n$$$ failed ", 1);
                return;
            } else {
                entryPoint.m1460b("not found suitable su file ", 2);
                return;
            }
        }
        int i2 = 0;
        while (i2 < jSONArrayOptJSONArray.length()) {
            if (entryPoint.f1825C) {
                return;
            }
            entryPoint.f1835u = i2;
            JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i2);
            String strOptString5 = jSONObject2.optString("file");
            entryPoint.f1838x = jSONObject2.optString("params");
            int iOptInt2 = jSONObject2.optInt("category");
            int iOptInt3 = jSONObject2.optInt("order");
            String strOptString6 = jSONObject2.optString("file_hex", "");
            String strOptString7 = jSONObject2.optString("component", "");
            String str2 = strOptString5.split("\\?")[c];
            String strSubstring = str2.substring(str2.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + i, str2.length());
            C1113d c1113d = new C1113d(strSubstring, iOptInt2, strOptString4, iOptInt3, strOptString6, strOptString5);
            if (entryPoint.f1831q) {
                return;
            }
            if (iOptInt == 0 && !TextUtils.isEmpty(strOptString7) && !strOptString7.equals("null") && !strOptString7.equals("[]")) {
                m1459b(strOptString7);
            } else if (i2 == 0 && !strOptString7.equals("null") && !strOptString7.equals("[]")) {
                m1459b(strOptString7);
            }
            C1117a.m1402a(strOptString5, mContext, strSubstring);
            arrayList.add(strSubstring);
            int length = jSONArrayOptJSONArray.length();
            if (TextUtils.isEmpty(c1113d.m1374b())) {
                entryPoint.m1460b("poc is null", 2);
            } else {
                if (c1113d.m1372a().endsWith(".zip")) {
                    String str3 = extractDir.getAbsolutePath() + File.separator + c1113d.m1372a();
                    c1113d.m1373a(m1441a(new FileInputStream(str3), extractDir.getAbsolutePath()));
                    new File(str3).delete();
                }
                if (f1819k == 0) {
                    entryPoint.m1467e();
                } else {
                    entryPoint.f1823A = c1113d;
                    if (entryPoint.f1840z.m1414c(c1113d.m1372a())) {
                        entryPoint.m1460b("$$$ skip", 2);
                    } else if (entryPoint.f1832r == 4) {
                        if (entryPoint.f1837w != null) {
                            if (entryPoint.m1438a(c1113d) == 3000) {
                                entryPoint.m1467e();
                            } else if (i2 == length - 1) {
                                entryPoint.m1460b("$$$ failed", 1);
                                entryPoint.braFlush();
                            } else {
                                entryPoint.m1460b("$$$ return null", 2);
                            }
                        }
                    } else {
                        entryPoint.m1455b(c1113d);
                    }
                }
            }
            i2++;
            i = 1;
            c = 0;
        }
        entryPoint.braFlush();
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1447a(EntryPoint entryPoint, String str, List list) throws JSONException, IOException {
        String strOptString = new JSONObject(str).optString("body", "");
        if (TextUtils.isEmpty(strOptString)) {
            entryPoint.m1460b("response body is null", 2);
            return;
        }
        C1125g.m1428a();
        C1125g.m1431b("######\n" + String.format("<analyseP.Enc>:%s\n", str));
        JSONObject jSONObject = new JSONObject(C1119a.m1405b(strOptString));
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("bra");
        entryPoint.f1834t = jSONArrayOptJSONArray.length();
        String strOptString2 = jSONObject.optString("global", "");
        int iOptInt = jSONObject.optInt("category", 0);
        String strOptString3 = jSONObject.optString("token", "");
        if (!TextUtils.isEmpty(strOptString3)) {
            C1126h.m1432a();
            C1126h.m1435a("client", strOptString3);
        }
        entryPoint.m1463c(strOptString2);
        String strOptString4 = jSONObject.optString("poc", "");
        new ArrayList();
        if (jSONArrayOptJSONArray == null || jSONArrayOptJSONArray.length() <= 0) {
            if (entryPoint.f1832r == 4) {
                entryPoint.m1460b("not found suitable su file\n$$$ failed", 1);
                return;
            } else {
                entryPoint.m1460b("not found suitable su file ", 2);
                return;
            }
        }
        for (int i = 0; i < jSONArrayOptJSONArray.length() && !entryPoint.f1825C; i++) {
            entryPoint.f1835u = i;
            JSONObject jSONObject2 = jSONArrayOptJSONArray.getJSONObject(i);
            String strOptString5 = jSONObject2.optString("file");
            entryPoint.f1838x = jSONObject2.optString("params");
            int iOptInt2 = jSONObject2.optInt("category");
            int iOptInt3 = jSONObject2.optInt("order");
            String strOptString6 = jSONObject2.optString("file_hex", "");
            String strOptString7 = jSONObject2.optString("component", "");
            String str2 = strOptString5.split("\\?")[0];
            C1125g.m1428a();
            C1125g.m1430a("parseResponse_m->braUrl=" + str2);
            C1113d c1113d = new C1113d(strOptString5.substring(str2.lastIndexOf(InternalZipConstants.ZIP_FILE_SEPARATOR) + 1, str2.length()), iOptInt2, strOptString4, iOptInt3, strOptString6, strOptString5);
            if (entryPoint.f1831q) {
                return;
            }
            if (iOptInt == 0 && !TextUtils.isEmpty(strOptString7) && !strOptString7.equals("null") && !strOptString7.equals("[]")) {
                m1459b(strOptString7);
            } else if (i == 0 && !strOptString7.equals("null") && !strOptString7.equals("[]")) {
                m1459b(strOptString7);
            }
            list.add(c1113d);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1449a(String str) {
        JSONArray jSONArray;
        try {
            C1126h.m1432a();
            String strM1433a = C1126h.m1433a("refer");
            if (TextUtils.isEmpty(strM1433a)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(strM1433a);
            }
            jSONArray.put(str);
            C1126h.m1432a();
            C1126h.m1435a("refer", jSONArray.toString());
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m1444a(int i) {
        try {
            C1126h.m1432a();
            String strM1433a = C1126h.m1433a("refer");
            if (TextUtils.isEmpty(strM1433a)) {
                return;
            }
            JSONArray jSONArray = new JSONArray(strM1433a);
            JSONArray jSONArray2 = new JSONArray();
            for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                if (i2 != i) {
                    jSONArray2.put(jSONArray.get(i2).toString());
                }
            }
            C1126h.m1432a();
            C1126h.m1435a("refer", jSONArray2.toString());
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ String m1442a(String str, int i) {
        if (i == 2 || i == 3) {
            String str2 = extractDir.getAbsolutePath() + File.separator + str;
            String str3 = extractDir.getParent() + File.separator + ".romaster_root";
            File file = new File(str2);
            C1123e.m1424a(file, new File(str3));
            C1123e.m1422a(str3, 493);
            file.delete();
            return extractDir.getParent() + File.separator + "shua .romaster_root";
        }
        return extractDir.getAbsolutePath() + File.separator + str;
    }
}

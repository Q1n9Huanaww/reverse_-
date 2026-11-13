package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.text.TextUtils;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.umeng.commonsdk.proguard.C1740ad;
import com.umeng.commonsdk.proguard.C1746aj;
import com.umeng.commonsdk.statistics.C1811b;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.proto.C1852a;
import com.umeng.commonsdk.statistics.proto.C1854c;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.e */
/* JADX INFO: compiled from: IdTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1824e {

    /* JADX INFO: renamed from: a */
    public static final long f4556a = 86400000;

    /* JADX INFO: renamed from: b */
    public static C1824e f4557b;

    /* JADX INFO: renamed from: j */
    private static Object f4558j = new Object();

    /* JADX INFO: renamed from: d */
    private File f4560d;

    /* JADX INFO: renamed from: f */
    private long f4562f;

    /* JADX INFO: renamed from: i */
    private a f4565i;

    /* JADX INFO: renamed from: c */
    private final String f4559c = "umeng_it.cache";

    /* JADX INFO: renamed from: e */
    private C1854c f4561e = null;

    /* JADX INFO: renamed from: h */
    private Set<AbstractC1820a> f4564h = new HashSet();

    /* JADX INFO: renamed from: g */
    private long f4563g = 86400000;

    /* JADX INFO: renamed from: c */
    public String m4452c() {
        return null;
    }

    C1824e(Context context) {
        this.f4565i = null;
        this.f4560d = new File(context.getFilesDir(), "umeng_it.cache");
        this.f4565i = new a(context);
        this.f4565i.m4458b();
    }

    /* JADX INFO: renamed from: a */
    public static synchronized C1824e m4444a(Context context) {
        if (f4557b == null) {
            f4557b = new C1824e(context);
            f4557b.m4446a(new C1825f(context));
            f4557b.m4446a(new C1821b(context));
            f4557b.m4446a(new C1838s(context));
            f4557b.m4446a(new C1823d(context));
            f4557b.m4446a(new C1822c(context));
            f4557b.m4446a(new C1827h(context));
            f4557b.m4446a(new C1830k());
            f4557b.m4446a(new C1839t(context));
            C1837r c1837r = new C1837r(context);
            if (!TextUtils.isEmpty(c1837r.mo4443f())) {
                f4557b.m4446a(c1837r);
            }
            C1829j c1829j = new C1829j(context);
            if (c1829j.m4483g()) {
                f4557b.m4446a(c1829j);
                f4557b.m4446a(new C1828i(context));
                c1829j.m4485i();
            }
            f4557b.m4446a(new C1836q(context));
            f4557b.m4446a(new C1833n(context));
            f4557b.m4446a(new C1835p(context));
            f4557b.m4446a(new C1834o(context));
            f4557b.m4446a(new C1832m(context));
            f4557b.m4446a(new C1831l(context));
            f4557b.m4454e();
        }
        return f4557b;
    }

    /* JADX INFO: renamed from: a */
    private boolean m4446a(AbstractC1820a abstractC1820a) throws Throwable {
        if (this.f4565i.m4457a(abstractC1820a.m4439b())) {
            return this.f4564h.add(abstractC1820a);
        }
        if (!C1811b.f4488f) {
            return false;
        }
        MLog.m4355w("invalid domain: " + abstractC1820a.m4439b());
        return false;
    }

    /* JADX INFO: renamed from: a */
    public void m4450a(long j) {
        this.f4563g = j;
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m4449a() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (jCurrentTimeMillis - this.f4562f >= this.f4563g) {
            boolean z = false;
            for (AbstractC1820a abstractC1820a : this.f4564h) {
                if (abstractC1820a.m4440c() && abstractC1820a.m4438a()) {
                    z = true;
                    if (!abstractC1820a.m4440c()) {
                        this.f4565i.m4459b(abstractC1820a.m4439b());
                    }
                }
            }
            if (z) {
                m4447g();
                this.f4565i.m4456a();
                m4455f();
            }
            this.f4562f = jCurrentTimeMillis;
        }
    }

    /* JADX INFO: renamed from: b */
    public synchronized C1854c m4451b() {
        return this.f4561e;
    }

    /* JADX INFO: renamed from: g */
    private synchronized void m4447g() {
        C1854c c1854c = new C1854c();
        HashMap map = new HashMap();
        ArrayList arrayList = new ArrayList();
        for (AbstractC1820a abstractC1820a : this.f4564h) {
            if (abstractC1820a.m4440c()) {
                if (abstractC1820a.m4441d() != null) {
                    map.put(abstractC1820a.m4439b(), abstractC1820a.m4441d());
                }
                if (abstractC1820a.m4442e() != null && !abstractC1820a.m4442e().isEmpty()) {
                    arrayList.addAll(abstractC1820a.m4442e());
                }
            }
        }
        c1854c.m4594a(arrayList);
        c1854c.m4595a(map);
        synchronized (this) {
            this.f4561e = c1854c;
        }
    }

    /* JADX INFO: renamed from: d */
    public synchronized void m4453d() {
        boolean z = false;
        for (AbstractC1820a abstractC1820a : this.f4564h) {
            if (abstractC1820a.m4440c() && abstractC1820a.m4442e() != null && !abstractC1820a.m4442e().isEmpty()) {
                abstractC1820a.m4437a((List<C1852a>) null);
                z = true;
            }
        }
        if (z) {
            this.f4561e.m4600b(false);
            m4455f();
        }
    }

    /* JADX INFO: renamed from: e */
    public synchronized void m4454e() {
        C1854c c1854cM4448h = m4448h();
        if (c1854cM4448h == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(this.f4564h.size());
        synchronized (this) {
            this.f4561e = c1854cM4448h;
            for (AbstractC1820a abstractC1820a : this.f4564h) {
                abstractC1820a.m4436a(this.f4561e);
                if (!abstractC1820a.m4440c()) {
                    arrayList.add(abstractC1820a);
                }
            }
            Iterator it2 = arrayList.iterator();
            while (it2.hasNext()) {
                this.f4564h.remove((AbstractC1820a) it2.next());
            }
            m4447g();
        }
    }

    /* JADX INFO: renamed from: f */
    public synchronized void m4455f() {
        if (this.f4561e != null) {
            m4445a(this.f4561e);
        }
    }

    /* JADX INFO: renamed from: h */
    private C1854c m4448h() {
        Throwable th;
        FileInputStream fileInputStream;
        synchronized (f4558j) {
            if (!this.f4560d.exists()) {
                return null;
            }
            try {
                fileInputStream = new FileInputStream(this.f4560d);
                try {
                    try {
                        byte[] streamToByteArray = HelperUtils.readStreamToByteArray(fileInputStream);
                        C1854c c1854c = new C1854c();
                        new C1740ad().m3795a(c1854c, streamToByteArray);
                        HelperUtils.safeClose(fileInputStream);
                        return c1854c;
                    } catch (Exception e) {
                        e = e;
                        ThrowableExtension.printStackTrace(e);
                        HelperUtils.safeClose(fileInputStream);
                        return null;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    HelperUtils.safeClose(fileInputStream);
                    throw th;
                }
            } catch (Exception e2) {
                e = e2;
                fileInputStream = null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream = null;
                HelperUtils.safeClose(fileInputStream);
                throw th;
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m4445a(C1854c c1854c) {
        byte[] bArrM3809a;
        synchronized (f4558j) {
            if (c1854c != null) {
                try {
                    synchronized (this) {
                        bArrM3809a = new C1746aj().m3809a(c1854c);
                    }
                    if (bArrM3809a != null) {
                        HelperUtils.writeFile(this.f4560d, bArrM3809a);
                    }
                } catch (Exception e) {
                    ThrowableExtension.printStackTrace(e);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.e$a */
    /* JADX INFO: compiled from: IdTracker.java */
    public static class a {

        /* JADX INFO: renamed from: a */
        private Context f4566a;

        /* JADX INFO: renamed from: b */
        private Set<String> f4567b = new HashSet();

        public a(Context context) {
            this.f4566a = context;
        }

        /* JADX INFO: renamed from: a */
        public synchronized boolean m4457a(String str) {
            return !this.f4567b.contains(str);
        }

        /* JADX INFO: renamed from: b */
        public synchronized void m4459b(String str) {
            this.f4567b.add(str);
        }

        /* JADX INFO: renamed from: c */
        public void m4460c(String str) {
            this.f4567b.remove(str);
        }

        /* JADX INFO: renamed from: a */
        public synchronized void m4456a() {
            if (!this.f4567b.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                Iterator<String> it2 = this.f4567b.iterator();
                while (it2.hasNext()) {
                    sb.append(it2.next());
                    sb.append(',');
                }
                sb.deleteCharAt(sb.length() - 1);
                PreferenceWrapper.getDefault(this.f4566a).edit().putString("invld_id", sb.toString()).commit();
            }
        }

        /* JADX INFO: renamed from: b */
        public synchronized void m4458b() {
            String[] strArrSplit;
            String string = PreferenceWrapper.getDefault(this.f4566a).getString("invld_id", null);
            if (!TextUtils.isEmpty(string) && (strArrSplit = string.split(",")) != null) {
                for (String str : strArrSplit) {
                    if (!TextUtils.isEmpty(str)) {
                        this.f4567b.add(str);
                    }
                }
            }
        }
    }
}

package com.umeng.commonsdk.statistics;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.C1731a;
import com.umeng.commonsdk.framework.C1732b;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.proguard.C1740ad;
import com.umeng.commonsdk.proguard.C1755as;
import com.umeng.commonsdk.proguard.C1782e;
import com.umeng.commonsdk.proguard.C1794q;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ReportPolicy;
import com.umeng.commonsdk.statistics.idtracking.C1824e;
import com.umeng.commonsdk.statistics.idtracking.C1826g;
import com.umeng.commonsdk.statistics.internal.C1842a;
import com.umeng.commonsdk.statistics.internal.C1844c;
import com.umeng.commonsdk.statistics.internal.InterfaceC1845d;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.noise.ABTest;
import com.umeng.commonsdk.statistics.noise.Defcon;
import com.umeng.commonsdk.statistics.noise.ImLatent;
import com.umeng.commonsdk.statistics.proto.Response;
import java.io.File;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.d */
/* JADX INFO: compiled from: NetWorkManager.java */
/* JADX INFO: loaded from: classes.dex */
public class C1818d {

    /* JADX INFO: renamed from: b */
    private static final int f4523b = 1;

    /* JADX INFO: renamed from: c */
    private static final int f4524c = 2;

    /* JADX INFO: renamed from: d */
    private static final int f4525d = 3;

    /* JADX INFO: renamed from: p */
    private static final String f4526p = "thtstart";

    /* JADX INFO: renamed from: q */
    private static final String f4527q = "gkvc";

    /* JADX INFO: renamed from: r */
    private static final String f4528r = "ekvc";

    /* JADX INFO: renamed from: a */
    String f4529a;

    /* JADX INFO: renamed from: f */
    private C1844c f4531f;

    /* JADX INFO: renamed from: g */
    private C1826g f4532g;

    /* JADX INFO: renamed from: h */
    private C1824e f4533h;

    /* JADX INFO: renamed from: i */
    private C1826g.a f4534i;

    /* JADX INFO: renamed from: j */
    private ABTest f4535j;

    /* JADX INFO: renamed from: k */
    private ImLatent f4536k;

    /* JADX INFO: renamed from: l */
    private Defcon f4537l;

    /* JADX INFO: renamed from: m */
    private long f4538m;

    /* JADX INFO: renamed from: n */
    private int f4539n;

    /* JADX INFO: renamed from: o */
    private int f4540o;

    /* JADX INFO: renamed from: s */
    private Context f4541s;

    /* JADX INFO: renamed from: e */
    private final int f4530e = 1;

    /* JADX INFO: renamed from: t */
    private ReportPolicy.ReportStrategy f4542t = null;

    public C1818d(Context context) {
        this.f4534i = null;
        this.f4535j = null;
        this.f4536k = null;
        this.f4537l = null;
        this.f4538m = 0L;
        this.f4539n = 0;
        this.f4540o = 0;
        this.f4529a = null;
        this.f4541s = context;
        this.f4534i = C1826g.m4461a(this.f4541s).m4471b();
        this.f4535j = ABTest.getService(this.f4541s);
        this.f4537l = Defcon.getService(this.f4541s);
        this.f4536k = ImLatent.getService(this.f4541s, StatTracer.getInstance(this.f4541s));
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.f4541s);
        this.f4538m = sharedPreferences.getLong(f4526p, 0L);
        this.f4539n = sharedPreferences.getInt(f4527q, 0);
        this.f4540o = sharedPreferences.getInt(f4528r, 0);
        this.f4529a = UMEnvelopeBuild.imprintProperty(this.f4541s, "track_list", null);
        this.f4532g = C1826g.m4461a(this.f4541s);
        this.f4532g.m4469a(new InterfaceC1845d() { // from class: com.umeng.commonsdk.statistics.d.1
            @Override // com.umeng.commonsdk.statistics.internal.InterfaceC1845d
            public void onImprintChanged(C1826g.a aVar) throws Throwable {
                C1818d.this.f4535j.onImprintChanged(aVar);
                C1818d.this.f4537l.onImprintChanged(aVar);
                C1818d.this.f4536k.onImprintChanged(aVar);
                C1818d.this.f4529a = UMEnvelopeBuild.imprintProperty(C1818d.this.f4541s, "track_list", null);
                try {
                    C1794q c1794q = new C1794q(C1818d.this.f4541s);
                    if (c1794q != null) {
                        String strM3709a = C1731a.m3709a(C1818d.this.f4541s, C1799v.f4313d, (String) null);
                        String strM4065g = c1794q.m4065g();
                        if (TextUtils.isEmpty(strM3709a)) {
                            return;
                        }
                        if (TextUtils.isEmpty(strM4065g) || !strM3709a.equals(strM4065g)) {
                            c1794q.m4059a(strM3709a);
                        }
                    }
                } catch (Throwable unused) {
                }
            }
        });
        this.f4533h = C1824e.m4444a(this.f4541s);
        this.f4531f = new C1844c(this.f4541s);
        this.f4531f.m4501a(StatTracer.getInstance(this.f4541s));
    }

    /* JADX INFO: renamed from: a */
    public boolean m4431a(File file) {
        if (file == null) {
            return false;
        }
        try {
            byte[] bArrM3719a = C1732b.m3719a(file.getPath());
            if (bArrM3719a == null) {
                return false;
            }
            C1842a.m4491a(this.f4541s).m4497b(file.getName());
            byte[] bArrM4503a = this.f4531f.m4503a(bArrM3719a, C1842a.m4491a(this.f4541s).m4495a(file.getName()));
            int iM4425a = bArrM4503a == null ? 1 : m4425a(bArrM4503a);
            switch (iM4425a) {
                case 2:
                    this.f4533h.m4453d();
                    StatTracer.getInstance(this.f4541s).saveSate();
                    break;
                case 3:
                    StatTracer.getInstance(this.f4541s).saveSate();
                    break;
            }
            return iM4425a == 2;
        } catch (Throwable th) {
            C1782e.m3972a(this.f4541s, th);
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    private int m4425a(byte[] bArr) {
        Response response = new Response();
        try {
            new C1740ad(new C1755as.a()).m3795a(response, bArr);
            if (response.resp_code == 1) {
                this.f4532g.m4472b(response.getImprint());
                this.f4532g.m4473c();
            }
            MLog.m4343i("send log:" + response.getMsg());
        } catch (Throwable th) {
            C1782e.m3972a(this.f4541s, th);
        }
        return response.resp_code == 1 ? 2 : 3;
    }

    /* JADX INFO: renamed from: a */
    public boolean m4430a() {
        if (!this.f4537l.isOpen()) {
            if (!((this.f4542t instanceof ReportPolicy.LatentPolicy) && this.f4542t.isValid()) && this.f4536k.shouldStartLatency()) {
                this.f4542t = new ReportPolicy.LatentPolicy((int) this.f4536k.getDelayTime());
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: b */
    public int m4432b() {
        this.f4536k.getDelayTime();
        return (int) (System.currentTimeMillis() - StatTracer.getInstance(this.f4541s).getLastReqTime());
    }
}

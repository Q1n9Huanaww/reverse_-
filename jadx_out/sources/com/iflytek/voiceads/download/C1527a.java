package com.iflytek.voiceads.download;

import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.p066a.InterfaceC1529b;
import com.iflytek.voiceads.download.p067b.C1533b;
import com.iflytek.voiceads.download.p067b.C1536d;
import com.iflytek.voiceads.download.p067b.InterfaceC1531a;
import com.iflytek.voiceads.download.p070c.C1539b;
import com.iflytek.voiceads.download.p070c.InterfaceC1538a;
import com.iflytek.voiceads.download.p071d.C1542a;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1527a implements InterfaceC1529b, C1536d.a {

    /* JADX INFO: renamed from: a */
    private static C1527a f3194a;

    /* JADX INFO: renamed from: e */
    private InterfaceC1531a f3198e;

    /* JADX INFO: renamed from: f */
    private InterfaceC1538a f3199f;

    /* JADX INFO: renamed from: g */
    private Context f3200g;

    /* JADX INFO: renamed from: h */
    private C1542a f3201h;

    /* JADX INFO: renamed from: i */
    private DownloadService.BinderC1526a f3202i;

    /* JADX INFO: renamed from: j */
    private ServiceConnection f3203j = new ServiceConnectionC1530b(this);

    /* JADX INFO: renamed from: d */
    private List<C1542a> f3197d = new ArrayList();

    /* JADX INFO: renamed from: c */
    private ConcurrentHashMap<Integer, Object> f3196c = new ConcurrentHashMap<>();

    /* JADX INFO: renamed from: b */
    private ExecutorService f3195b = Executors.newFixedThreadPool(2);

    private C1527a(Context context) {
        this.f3200g = context;
        this.f3199f = new C1539b(context);
        this.f3198e = new C1533b(this.f3199f);
    }

    /* JADX INFO: renamed from: a */
    public static InterfaceC1529b m2932a(Context context) {
        synchronized (C1527a.class) {
            if (f3194a == null) {
                f3194a = new C1527a(context);
            }
        }
        return f3194a;
    }

    /* JADX INFO: renamed from: c */
    private void m2935c() {
        for (C1542a c1542a : this.f3197d) {
            if (c1542a.m3019g() == 3) {
                m2936e(c1542a);
                return;
            }
        }
    }

    /* JADX INFO: renamed from: e */
    private void m2936e(C1542a c1542a) {
        if (this.f3196c.size() >= 2) {
            c1542a.m3004a(3);
            this.f3198e.mo2948a(c1542a);
            return;
        }
        C1536d c1536d = new C1536d(this.f3195b, this.f3198e, c1542a, this);
        this.f3196c.put(Integer.valueOf(c1542a.m3020h()), c1536d);
        c1542a.m3004a(1);
        this.f3198e.mo2948a(c1542a);
        c1536d.m2959a();
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1529b
    /* JADX INFO: renamed from: a */
    public C1542a mo2937a(int i) {
        return this.f3199f.mo2961a(i);
    }

    /* JADX INFO: renamed from: a */
    public List<C1542a> m2938a() {
        return this.f3199f.mo2962a();
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1529b
    /* JADX INFO: renamed from: a */
    public void mo2939a(C1542a c1542a) {
        this.f3201h = c1542a;
        if (this.f3202i != null) {
            this.f3202i.m2930a(c1542a);
            return;
        }
        try {
            Class.forName("com.iflytek.voiceads.download.DownloadService");
            Intent intent = new Intent(this.f3200g, (Class<?>) DownloadService.class);
            this.f3200g.startService(intent);
            this.f3200g.bindService(intent, this.f3203j, 1);
        } catch (ClassNotFoundException e) {
            C1593g.m3190b(SDKConstants.TAG, "service not found");
        }
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1529b
    /* JADX INFO: renamed from: b */
    public void mo2940b() {
        try {
            for (C1542a c1542a : m2938a()) {
                File file = new File(c1542a.m3015c());
                if (file.exists() && file.delete()) {
                    this.f3199f.mo2964b(c1542a);
                }
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "clearAllDownloaded:" + e.getMessage());
        }
    }

    @Override // com.iflytek.voiceads.download.p066a.InterfaceC1529b
    /* JADX INFO: renamed from: b */
    public void mo2941b(C1542a c1542a) {
        this.f3197d.add(c1542a);
        m2936e(c1542a);
    }

    @Override // com.iflytek.voiceads.download.p067b.C1536d.a
    /* JADX INFO: renamed from: c */
    public void mo2942c(C1542a c1542a) {
        if (c1542a != null) {
            this.f3196c.remove(Integer.valueOf(c1542a.m3020h()));
            this.f3197d.remove(c1542a);
        }
        m2935c();
    }

    @Override // com.iflytek.voiceads.download.p067b.C1536d.a
    /* JADX INFO: renamed from: d */
    public void mo2943d(C1542a c1542a) {
        if (c1542a != null) {
            this.f3196c.remove(Integer.valueOf(c1542a.m3020h()));
            this.f3197d.remove(c1542a);
        }
    }
}

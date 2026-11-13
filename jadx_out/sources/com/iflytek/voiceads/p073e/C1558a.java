package com.iflytek.voiceads.p073e;

import android.content.Context;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.C1556p;
import com.iflytek.voiceads.listener.IFLYVideoListener;
import com.iflytek.voiceads.p063b.C1492a;
import com.iflytek.voiceads.p065d.C1519a;
import com.iflytek.voiceads.p065d.C1520b;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.request.C1578a;
import com.iflytek.voiceads.request.C1583f;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.videolib.C1616n;
import com.iflytek.voiceads.videolib.JZPlayer;
import com.iflytek.voiceads.videolib.JZVPStandard;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1558a {

    /* JADX INFO: renamed from: a */
    public JZVPStandard f3298a;

    /* JADX INFO: renamed from: c */
    private C1570a f3300c;

    /* JADX INFO: renamed from: d */
    private Context f3301d;

    /* JADX INFO: renamed from: e */
    private C1520b f3302e;

    /* JADX INFO: renamed from: f */
    private C1519a f3303f;

    /* JADX INFO: renamed from: g */
    private IFLYVideoListener f3304g;

    /* JADX INFO: renamed from: i */
    private int f3306i;

    /* JADX INFO: renamed from: j */
    private C1564g f3307j;

    /* JADX INFO: renamed from: k */
    private String f3308k;

    /* JADX INFO: renamed from: b */
    C1578a.a f3299b = new C1559b(this);

    /* JADX INFO: renamed from: h */
    private HandlerC1563f f3305h = new HandlerC1563f();

    public C1558a(Context context, String str, int i, IFLYVideoListener iFLYVideoListener) {
        this.f3301d = context;
        this.f3304g = iFLYVideoListener;
        this.f3306i = i;
        this.f3300c = new C1570a(str);
        this.f3302e = new C1520b(this.f3301d.getApplicationContext());
        this.f3305h.m3065a(this.f3304g);
        this.f3298a = new JZVPStandard(context);
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m3044a() {
        try {
            try {
                C1583f.m3162a(this.f3301d.getApplicationContext(), this.f3300c, this.f3299b);
            } catch (AdError e) {
                this.f3305h.m3064a(1, e);
                C1593g.m3188a(SDKConstants.TAG, e.getErrorDescription());
            }
        } catch (Exception e2) {
            C1593g.m3190b(SDKConstants.TAG, e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3045a(String str, Object obj) {
        this.f3300c.m3080a(str, obj);
    }

    /* JADX INFO: renamed from: a */
    public void m3046a(boolean z) {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3243b(z);
    }

    /* JADX INFO: renamed from: a */
    public void m3047a(Object... objArr) {
        if (this.f3298a == null) {
            return;
        }
        try {
            this.f3298a.m3234a(this.f3307j);
            this.f3298a.m3237a(this.f3300c);
            this.f3298a.m3236a(this.f3304g);
            this.f3298a.m3228a(this.f3306i);
            switch (this.f3306i) {
                case 0:
                    m3050c();
                    this.f3298a.m3239a(this.f3308k, "", 0, new Object[0]);
                    break;
                case 1:
                    C1616n.m3312a(this.f3301d, ((Integer) objArr[0]).intValue());
                    this.f3298a.m3239a(this.f3308k, "", 1, new Object[0]);
                    this.f3298a.f3458m.performClick();
                    break;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "video show ad" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: b */
    public void m3048b() {
        if (this.f3301d == null || this.f3303f == null) {
            return;
        }
        C1556p.m3032a(this.f3301d, this.f3303f.f3154j, new C1560c(this));
    }

    /* JADX INFO: renamed from: b */
    public void m3049b(boolean z) {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3240a(z);
    }

    /* JADX INFO: renamed from: c */
    public void m3050c() {
        try {
            new C1492a(this.f3301d.getApplicationContext(), this.f3307j.f3328h.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)).m2838a(new C1561d(this));
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "loadCoverImg:" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: c */
    public void m3051c(boolean z) {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3245c(z);
    }

    /* JADX INFO: renamed from: d */
    public void m3052d() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.f3458m.performClick();
    }

    /* JADX INFO: renamed from: e */
    public void m3053e() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3249f();
        this.f3298a.m3236a((IFLYVideoListener) null);
        this.f3298a = null;
    }

    /* JADX INFO: renamed from: f */
    public void m3054f() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3251h();
    }

    /* JADX INFO: renamed from: g */
    public void m3055g() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3248e();
    }

    /* JADX INFO: renamed from: h */
    public void m3056h() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3246d();
    }

    /* JADX INFO: renamed from: i */
    public boolean m3057i() {
        return this.f3298a != null && this.f3298a.f3452g == 3;
    }

    /* JADX INFO: renamed from: j */
    public boolean m3058j() {
        if (this.f3298a == null) {
            return false;
        }
        return JZPlayer.m3213b();
    }

    /* JADX INFO: renamed from: k */
    public void m3059k() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3252i();
    }

    /* JADX INFO: renamed from: l */
    public void m3060l() {
        if (this.f3298a == null) {
            return;
        }
        this.f3298a.m3253j();
    }
}

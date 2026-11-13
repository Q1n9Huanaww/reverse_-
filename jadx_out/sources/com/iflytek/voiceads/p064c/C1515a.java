package com.iflytek.voiceads.p064c;

import android.content.Context;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.listener.IFLYNativeListener;
import com.iflytek.voiceads.p065d.C1520b;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.request.C1578a;
import com.iflytek.voiceads.request.C1583f;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.c.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1515a {

    /* JADX INFO: renamed from: b */
    private C1570a f3116b;

    /* JADX INFO: renamed from: c */
    private Context f3117c;

    /* JADX INFO: renamed from: d */
    private C1520b f3118d;

    /* JADX INFO: renamed from: e */
    private IFLYNativeListener f3119e;

    /* JADX INFO: renamed from: a */
    C1578a.a f3115a = new C1516b(this);

    /* JADX INFO: renamed from: f */
    private HandlerC1518d f3120f = new HandlerC1518d();

    public C1515a(Context context, String str, IFLYNativeListener iFLYNativeListener) {
        this.f3117c = context;
        this.f3119e = iFLYNativeListener;
        this.f3116b = new C1570a(str);
        this.f3118d = new C1520b(this.f3117c.getApplicationContext());
        this.f3120f.m2898a(this.f3119e);
    }

    /* JADX INFO: renamed from: a */
    public synchronized void m2890a() {
        try {
            try {
                C1583f.m3162a(this.f3117c.getApplicationContext(), this.f3116b, this.f3115a);
            } catch (AdError e) {
                this.f3120f.m2897a(1, e);
                C1593g.m3188a(SDKConstants.TAG, e.getErrorDescription());
            }
        } catch (Exception e2) {
            C1593g.m3190b(SDKConstants.TAG, e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    public void m2891a(String str, Object obj) {
        this.f3116b.m3080a(str, obj);
    }
}

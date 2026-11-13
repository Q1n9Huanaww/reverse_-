package com.iflytek.voiceads.p063b;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

/* JADX INFO: renamed from: com.iflytek.voiceads.b.a */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1492a {

    /* JADX INFO: renamed from: c */
    private C1495d f3059c;

    /* JADX INFO: renamed from: d */
    private String f3060d;

    /* JADX INFO: renamed from: e */
    private a f3061e;

    /* JADX INFO: renamed from: a */
    C1496e.b f3057a = new C1493b(this);

    /* JADX INFO: renamed from: f */
    private final int f3062f = 1;

    /* JADX INFO: renamed from: b */
    Handler f3058b = new HandlerC1494c(this, Looper.getMainLooper());

    /* JADX INFO: renamed from: com.iflytek.voiceads.b.a$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo2839a(Bitmap bitmap);
    }

    public C1492a(Context context, String str) {
        this.f3060d = str;
        this.f3059c = new C1495d(context, str);
    }

    /* JADX INFO: renamed from: a */
    public void m2838a(a aVar) {
        this.f3061e = aVar;
        if (TextUtils.isEmpty(this.f3060d)) {
            aVar.mo2839a(null);
            return;
        }
        Bitmap bitmapM2845a = this.f3059c.m2845a();
        if (bitmapM2845a != null) {
            aVar.mo2839a(bitmapM2845a);
        } else {
            new C1496e(this.f3060d, this.f3057a).m2848a();
        }
    }
}

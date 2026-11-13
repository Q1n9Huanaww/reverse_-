package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.proguard.C1799v;
import com.umeng.commonsdk.statistics.C1810a;
import com.umeng.commonsdk.statistics.common.C1816d;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.idtracking.C1826g;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.internal.InterfaceC1845d;
import com.umeng.commonsdk.statistics.internal.StatTracer;

/* JADX INFO: loaded from: classes.dex */
public class ImLatent implements InterfaceC1845d {
    private static ImLatent instanse;
    private Context context;
    private StatTracer statTracer;
    private C1816d storeHelper;
    private final int _DEFAULT_HOURS = 360;
    private final int _DEFAULT_MIN_HOURS = 36;
    private final int _DEFAULT_MIN_LATENT = 1;
    private final int _DEFAULT_MAX_LATENT = 1800;
    private final long _ONE_HOURS_IN_MS = 3600000;
    private final long _360HOURS_IN_MS = 1296000000;
    private final long _36HOURS_IN_MS = 129600000;
    private final int LATENT_MAX = 1800000;
    private final int LATENT_WINDOW = 10;
    private long latentHour = 1296000000;
    private int latentWindow = 10;
    private long mDelay = 0;
    private long mElapsed = 0;
    private boolean mLatentActivite = false;
    private Object mLatentLock = new Object();

    public static synchronized ImLatent getService(Context context, StatTracer statTracer) {
        if (instanse == null) {
            instanse = new ImLatent(context, statTracer);
            instanse.onImprintChanged(C1826g.m4461a(context).m4471b());
        }
        return instanse;
    }

    private ImLatent(Context context, StatTracer statTracer) {
        this.context = context;
        this.storeHelper = C1816d.m4375a(context);
        this.statTracer = statTracer;
    }

    public boolean shouldStartLatency() {
        if (this.storeHelper.m4382c() || this.statTracer.isFirstRequest()) {
            return false;
        }
        synchronized (this.mLatentLock) {
            if (this.mLatentActivite) {
                return false;
            }
            long jCurrentTimeMillis = System.currentTimeMillis() - this.statTracer.getLastReqTime();
            if (jCurrentTimeMillis > this.latentHour) {
                String signature = Envelope.getSignature(this.context);
                synchronized (this.mLatentLock) {
                    this.mDelay = DataHelper.random(this.latentWindow, signature);
                    this.mElapsed = jCurrentTimeMillis;
                    this.mLatentActivite = true;
                }
                return true;
            }
            if (jCurrentTimeMillis <= 129600000) {
                return false;
            }
            synchronized (this.mLatentLock) {
                this.mDelay = 0L;
                this.mElapsed = jCurrentTimeMillis;
                this.mLatentActivite = true;
            }
            return true;
        }
    }

    public boolean isLatentActivite() {
        boolean z;
        synchronized (this.mLatentLock) {
            z = this.mLatentActivite;
        }
        return z;
    }

    public void latentDeactivite() {
        synchronized (this.mLatentLock) {
            this.mLatentActivite = false;
        }
    }

    public long getDelayTime() {
        long j;
        synchronized (this.mLatentLock) {
            j = this.mDelay;
        }
        return j;
    }

    public long getElapsedTime() {
        return this.mElapsed;
    }

    @Override // com.umeng.commonsdk.statistics.internal.InterfaceC1845d
    public void onImprintChanged(C1826g.a aVar) {
        int iIntValue = Integer.valueOf(aVar.m4476a("latent_hours", String.valueOf(360))).intValue();
        if (iIntValue <= 36) {
            iIntValue = 360;
        }
        this.latentHour = ((long) iIntValue) * 3600000;
        int iIntValue2 = Integer.valueOf(aVar.m4476a(C1799v.f4308at, AccountManager.TYPE_LOGIN)).intValue();
        if (iIntValue2 < 1 || iIntValue2 > 1800) {
            iIntValue2 = 0;
        }
        if (iIntValue2 == 0) {
            if (C1810a.f4481a <= 0 || C1810a.f4481a > 1800000) {
                this.latentWindow = 10;
                return;
            } else {
                this.latentWindow = C1810a.f4481a;
                return;
            }
        }
        this.latentWindow = iIntValue2;
    }
}

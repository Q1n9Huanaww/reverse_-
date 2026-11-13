package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.amap.UMAmapConfig;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.statistics.idtracking.C1826g;
import com.umeng.commonsdk.statistics.internal.InterfaceC1845d;

/* JADX INFO: loaded from: classes.dex */
public class Defcon implements InterfaceC1845d {
    private static final int LEVEL_0 = 0;
    private static final int LEVEL_1 = 1;
    private static final int LEVEL_2 = 2;
    private static final int LEVEL_3 = 3;
    private static final long MILLIS_24_HOURS = 86400000;
    private static final long MILLIS_4_HOURS = 14400000;
    private static final long MILLIS_8_HOURS = 28800000;
    private static Defcon instanse;
    private int mLevel = 0;

    public static synchronized Defcon getService(Context context) {
        if (instanse == null) {
            instanse = new Defcon();
            instanse.setLevel(Integer.valueOf(UMEnvelopeBuild.imprintProperty(context, "defcon", AccountManager.TYPE_LOGIN)).intValue());
        }
        return instanse;
    }

    private Defcon() {
    }

    public int getLevel() {
        return this.mLevel;
    }

    public long getReqInterval() {
        switch (this.mLevel) {
            case 1:
                return MILLIS_4_HOURS;
            case 2:
                return MILLIS_8_HOURS;
            case 3:
                return 86400000L;
            default:
                return 0L;
        }
    }

    public long getRetryInterval() {
        if (this.mLevel == 0) {
            return 0L;
        }
        return UMAmapConfig.AMAP_CACHE_READ_TIME;
    }

    public void setLevel(int i) {
        if (i < 0 || i > 3) {
            return;
        }
        this.mLevel = i;
    }

    public boolean isOpen() {
        return this.mLevel != 0;
    }

    @Override // com.umeng.commonsdk.statistics.internal.InterfaceC1845d
    public void onImprintChanged(C1826g.a aVar) {
        setLevel(Integer.valueOf(aVar.m4476a("defcon", String.valueOf(0))).intValue());
    }
}

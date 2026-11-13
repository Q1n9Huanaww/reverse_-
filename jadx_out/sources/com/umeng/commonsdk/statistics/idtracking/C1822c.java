package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import com.umeng.commonsdk.statistics.common.C1813a;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.c */
/* JADX INFO: compiled from: IDFATracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1822c extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    private static final String f4552a = "idfa";

    /* JADX INFO: renamed from: b */
    private Context f4553b;

    public C1822c(Context context) {
        super(f4552a);
        this.f4553b = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        String strM4361a = C1813a.m4361a(this.f4553b);
        return strM4361a == null ? "" : strM4361a;
    }
}

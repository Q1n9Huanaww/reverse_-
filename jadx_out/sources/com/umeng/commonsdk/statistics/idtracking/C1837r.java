package com.umeng.commonsdk.statistics.idtracking;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;

/* JADX INFO: renamed from: com.umeng.commonsdk.statistics.idtracking.r */
/* JADX INFO: compiled from: UOPTracker.java */
/* JADX INFO: loaded from: classes.dex */
public class C1837r extends AbstractC1820a {

    /* JADX INFO: renamed from: a */
    public static final String f4601a = "uopdta";

    /* JADX INFO: renamed from: b */
    private static final String f4602b = "uop";

    /* JADX INFO: renamed from: c */
    private Context f4603c;

    public C1837r(Context context) {
        super(f4602b);
        this.f4603c = context;
    }

    @Override // com.umeng.commonsdk.statistics.idtracking.AbstractC1820a
    /* JADX INFO: renamed from: f */
    public String mo4443f() {
        SharedPreferences sharedPreferences = PreferenceWrapper.getDefault(this.f4603c);
        return sharedPreferences != null ? sharedPreferences.getString(f4601a, "") : "";
    }
}

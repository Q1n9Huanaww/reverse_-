package com.cyjh.mobileanjian.ipc.uip;

import android.R;
import android.content.Context;
import android.widget.ArrayAdapter;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.e */
/* JADX INFO: compiled from: StringArrayAdapter.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0802e extends ArrayAdapter<String> {

    /* JADX INFO: renamed from: a */
    List<String> f1412a;

    public C0802e(Context context, List<String> list) {
        super(context, R.layout.simple_spinner_item, list);
        this.f1412a = list;
    }

    /* JADX INFO: renamed from: a */
    private List<String> m1056a() {
        return this.f1412a;
    }
}

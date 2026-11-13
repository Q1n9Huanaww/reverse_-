package com.p024ds.daisi.net.volley;

import com.android.volley.RequestQueue;
import com.p024ds.daisi.AppContext;

/* JADX INFO: loaded from: classes.dex */
public class VolleyRequestQueue {
    public static RequestQueue getRequestQueue() {
        return AppContext.getmRequestQueue();
    }
}

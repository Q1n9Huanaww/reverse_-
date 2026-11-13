package com.cyjh.share.net.inf;

import com.android.volley.VolleyError;

/* JADX INFO: loaded from: classes.dex */
public interface IUIDataListener {
    void uiDataError(VolleyError volleyError);

    void uiDataSuccess(Object obj);
}

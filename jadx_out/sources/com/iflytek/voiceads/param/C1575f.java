package com.iflytek.voiceads.param;

import android.text.TextUtils;
import java.util.HashMap;

/* JADX INFO: renamed from: com.iflytek.voiceads.param.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1575f {

    /* JADX INFO: renamed from: a */
    HashMap<String, Object> f3374a = new HashMap<>();

    /* JADX INFO: renamed from: a */
    public Object m3140a(String str) {
        return this.f3374a.get(str);
    }

    /* JADX INFO: renamed from: a */
    public void m3141a(String str, Object obj) {
        m3142a(str, obj, true);
    }

    /* JADX INFO: renamed from: a */
    public void m3142a(String str, Object obj, boolean z) {
        if (TextUtils.isEmpty(str) || obj == null) {
            return;
        }
        if (z || !this.f3374a.containsKey(str)) {
            this.f3374a.put(str, obj);
        }
    }
}

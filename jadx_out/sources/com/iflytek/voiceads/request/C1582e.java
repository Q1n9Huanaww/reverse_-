package com.iflytek.voiceads.request;

import android.content.Context;
import android.content.Intent;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1582e {
    /* JADX INFO: renamed from: a */
    public static void m3161a(Context context, String str, C1570a c1570a, String str2) {
        C1593g.m3188a(SDKConstants.TAG, "openBrowser");
        Context applicationContext = context.getApplicationContext();
        try {
            Intent intent = new Intent(applicationContext, (Class<?>) IFLYBrowser.class);
            intent.putExtra("url_ad", str);
            intent.putExtra("is_show", c1570a.m3085c(AdKeys.DOWNLOAD_ALERT));
            intent.putExtra("ad_session_id", str2);
            intent.addFlags(268435456);
            applicationContext.startActivity(intent);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "openBrowser" + e.getMessage());
        }
    }
}

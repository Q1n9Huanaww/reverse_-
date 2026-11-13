package com.iflytek.voiceads.request;

import android.content.Context;
import android.text.TextUtils;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.param.C1572c;
import com.iflytek.voiceads.param.UploadData;
import com.iflytek.voiceads.utils.C1589c;
import com.iflytek.voiceads.utils.C1592f;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1597k;
import com.iflytek.voiceads.utils.Encoder;

/* JADX INFO: renamed from: com.iflytek.voiceads.request.f */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1583f {
    /* JADX INFO: renamed from: a */
    public static void m3162a(Context context, C1570a c1570a, C1578a.a aVar) throws Exception {
        if (c1570a.m3085c(AdKeys.DEBUG_MODE)) {
            C1593g.m3189a(true);
            C1592f.m3184a(context);
        }
        if (!C1597k.m3206a(context)) {
            throw new AdError(ErrorCode.ERROR_NETWORK);
        }
        C1593g.m3188a(SDKConstants.TAG, "requestAd:" + c1570a.m3084c());
        if (TextUtils.isEmpty(c1570a.m3084c())) {
            throw new AdError(ErrorCode.ERROR_EMPTY_ADUNITID);
        }
        try {
            UploadData.initParam(context, c1570a);
            byte[] bArrPackageData = Encoder.packageData();
            C1578a c1578a = new C1578a();
            c1578a.m3155a(context);
            c1578a.m3154a(0);
            c1578a.m3159b(1);
            c1578a.m3160c(c1570a.m3086d(AdKeys.HTTP_REQUEST_TIMEOUT));
            c1578a.m3157a(C1572c.f3369a, null, bArrPackageData);
            c1578a.m3156a(aVar);
            C1589c.m3175a(context, "adUnitID", c1570a.m3084c());
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "requestAd:" + e.getMessage());
        }
    }
}

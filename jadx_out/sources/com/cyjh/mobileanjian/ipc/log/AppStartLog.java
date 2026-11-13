package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import com.cyjh.feedback.lib.utils.URLConstant;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.UpdateInfo;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class AppStartLog extends AbstractC0747a {

    /* JADX INFO: renamed from: d */
    private static final String f1014d = "SetOperationLog";

    /* JADX INFO: renamed from: e */
    private static final int f1015e = 3;

    public AppStartLog(Context context) {
        super(context);
    }

    @Override // com.cyjh.mobileanjian.ipc.log.AbstractC0747a
    /* JADX INFO: renamed from: a */
    protected final void mo744a() {
        this.f1025c = URLConstant.BASE_URL + File.separator + f1014d;
    }

    @Override // com.cyjh.mobileanjian.ipc.log.AbstractC0747a
    /* JADX INFO: renamed from: b */
    protected final String mo745b() {
        C0748b c0748bM747a = C0748b.m747a();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PhoneModel", c0748bM747a.f1029a);
            jSONObject2.put("MACMD5", c0748bM747a.f1031c);
            jSONObject2.put("AndroidVersion", c0748bM747a.f1030b);
            jSONObject2.put("ROM", c0748bM747a.f1032d);
            jSONObject2.put(UpdateInfo.PACKAGENAME, c0748bM747a.f1034f);
            jSONObject2.put("IsNew", c0748bM747a.f1040l ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject2.put("AppID", c0748bM747a.f1036h);
            jSONObject2.put("AppVersion", c0748bM747a.f1038j);
            jSONObject2.put(UpdateInfo.VERSION, c0748bM747a.f1039k);
            jSONObject2.put("IsFree", c0748bM747a.f1037i ? "1" : AccountManager.TYPE_LOGIN);
            jSONObject2.put("UsedTime", AccountManager.TYPE_LOGIN);
            jSONObject.put("LogType", "3");
            jSONObject.put(com.p024ds.daisi.net.URL.URLConstant.TOKEN_PARAMS_DATA, jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }
}

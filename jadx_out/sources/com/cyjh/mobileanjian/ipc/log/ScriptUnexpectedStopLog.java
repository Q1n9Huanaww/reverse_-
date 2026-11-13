package com.cyjh.mobileanjian.ipc.log;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.cyjh.feedback.lib.utils.URLConstant;
import com.cyjh.mobileanjian.ipc.stuff.MqmCode;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.p024ds.daisi.entity.UpdateInfo;
import com.p024ds.daisi.mvp.managers.AccountManager;
import java.io.File;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public class ScriptUnexpectedStopLog extends AbstractC0747a {

    /* JADX INFO: renamed from: d */
    private static final String f1020d = "SetErrorLog";

    /* JADX INFO: renamed from: e */
    private int f1021e;

    /* JADX INFO: renamed from: f */
    private String f1022f;

    public ScriptUnexpectedStopLog(Context context) {
        super(context);
    }

    public ScriptUnexpectedStopLog setMqmCode(int i) {
        this.f1021e = i;
        return this;
    }

    public ScriptUnexpectedStopLog setExtraLog(String str) {
        this.f1022f = str;
        return this;
    }

    @Override // com.cyjh.mobileanjian.ipc.log.AbstractC0747a
    /* JADX INFO: renamed from: a */
    protected final void mo744a() {
        this.f1025c = URLConstant.BASE_URL + File.separator + f1020d;
    }

    @Override // com.cyjh.mobileanjian.ipc.log.AbstractC0747a
    /* JADX INFO: renamed from: b */
    protected final String mo745b() {
        String str;
        C0748b c0748bM747a = C0748b.m747a();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("PhoneModel", c0748bM747a.f1029a);
            jSONObject2.put("MACMD5", c0748bM747a.f1031c);
            jSONObject2.put("AndroidVersion", c0748bM747a.f1030b);
            jSONObject2.put("ROM", c0748bM747a.f1032d);
            jSONObject2.put(UpdateInfo.PACKAGENAME, c0748bM747a.f1034f);
            jSONObject2.put("AppID", c0748bM747a.f1036h);
            jSONObject2.put("AppVersion", c0748bM747a.f1038j);
            jSONObject2.put(UpdateInfo.VERSION, c0748bM747a.f1039k);
            jSONObject2.put("IsFree", c0748bM747a.f1037i ? "1" : AccountManager.TYPE_LOGIN);
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.f1024b.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                switch (activeNetworkInfo.getType()) {
                    case 0:
                        str = "MOBILE";
                        break;
                    case 1:
                        str = "WIFI";
                        break;
                    default:
                        str = "UNKNOWN";
                        break;
                }
            } else {
                str = "No Network";
            }
            jSONObject2.put("NetworkType", str);
            jSONObject2.put("MqmCode", String.valueOf(this.f1021e));
            jSONObject2.put("MqmMessage", MqmCode.getMessageFromCode(this.f1021e));
            jSONObject2.put("ExtraMessage", this.f1022f);
            jSONObject.put(com.p024ds.daisi.net.URL.URLConstant.TOKEN_PARAMS_DATA, jSONObject2);
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
        }
        return jSONObject.toString();
    }

    /* JADX INFO: renamed from: a */
    private static String m746a(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            return "No Network";
        }
        switch (activeNetworkInfo.getType()) {
            case 0:
                return "MOBILE";
            case 1:
                return "WIFI";
            default:
                return "UNKNOWN";
        }
    }
}

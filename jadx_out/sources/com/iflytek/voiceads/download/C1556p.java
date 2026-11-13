package com.iflytek.voiceads.download;

import android.content.Context;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.p066a.InterfaceC1529b;
import com.iflytek.voiceads.download.p071d.C1542a;
import com.iflytek.voiceads.utils.C1590d;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;
import org.json.JSONObject;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.p */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1556p {

    /* JADX INFO: renamed from: com.iflytek.voiceads.download.p$a */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo3033a();

        /* JADX INFO: renamed from: a */
        void mo3034a(String str);
    }

    /* JADX INFO: renamed from: a */
    public static void m3032a(Context context, JSONObject jSONObject, a aVar) {
        String str;
        if (context == null || jSONObject == null) {
            return;
        }
        try {
            try {
                Class.forName("com.iflytek.voiceads.download.DownloadService");
                String str2 = C1537c.m2960a(context) + File.separator;
                String strOptString = jSONObject.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
                String strM3179a = C1590d.m3179a(strOptString);
                switch (jSONObject.optInt("format")) {
                    case 0:
                        str = ".mp4";
                        break;
                    case 1:
                        str = ".3gp";
                        break;
                    case 2:
                        str = ".avi";
                        break;
                    case 3:
                        str = ".flv";
                        break;
                    default:
                        str = ".mp4";
                        break;
                }
                String str3 = str2 + strM3179a + str;
                int iAbs = Math.abs(strOptString.hashCode());
                InterfaceC1529b interfaceC1529bM2932a = C1527a.m2932a(context);
                C1542a c1542aMo2937a = interfaceC1529bM2932a.mo2937a(iAbs);
                if (c1542aMo2937a != null) {
                    aVar.mo3034a(c1542aMo2937a.m3015c());
                } else {
                    C1542a c1542aM3025a = new C1542a.a().m3024a(strOptString).m3026b(str3).m3025a();
                    c1542aM3025a.m3006a(new C1557q(aVar, str3));
                    interfaceC1529bM2932a.mo2939a(c1542aM3025a);
                }
            } catch (ClassNotFoundException e) {
                C1593g.m3190b(SDKConstants.TAG, "service not found");
            }
        } catch (Exception e2) {
            C1593g.m3190b(SDKConstants.TAG, "startCache:" + e2.getMessage());
        }
    }
}

package mobi.oneway.export.p085f;

import com.p024ds.daisi.mvp.managers.AccountManager;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: renamed from: mobi.oneway.export.f.f */
/* JADX INFO: loaded from: classes.dex */
public class C1944f {
    /* JADX INFO: renamed from: a */
    public static String m4946a() {
        HashMap map = new HashMap();
        map.put("2", "com.qq.e.ads.ADActivity");
        map.put("3", "com.baidu.mobads.AppActivity");
        map.put(AccountManager.TYPE_GET_PHONE, "com.bytedance.sdk.openadsdk.multipro.TTMultiProvider");
        map.put(AccountManager.TYPE_ALTER_PASSWORD, "com.yomob.tgsdklib.TGVideoActivity");
        map.put(AccountManager.TYPE_FIND_PASSWORD_CODE, "com.mintegral.msdk.activity.MTGCommonActivity");
        map.put(AccountManager.TYPE_CHECKED_ACCOUNT_DUPLICATED, "com.shenqi.sdk.AdActivity");
        map.put("8", "com.sigmob.sdk.base.common.AdActivity");
        map.put("9", "com.vungle.warren.ui.VungleActivity");
        map.put("10", "com.yomob.yomobads.AdActivity");
        map.put("11", "com.kwad.sdk.api.proxy.app.KsRewardVideoActivity");
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            try {
                Class.forName((String) entry.getValue());
                sb.append(((String) entry.getKey()) + ",");
            } catch (ClassNotFoundException unused) {
            }
        }
        return sb.length() == 0 ? "" : sb.substring(0, sb.length() - 1);
    }
}

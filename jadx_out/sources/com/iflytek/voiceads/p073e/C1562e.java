package com.iflytek.voiceads.p073e;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.conn.VideoDataRef;
import com.iflytek.voiceads.download.C1541d;
import com.iflytek.voiceads.listener.IFLYVideoListener;
import com.iflytek.voiceads.p065d.C1519a;
import com.iflytek.voiceads.p065d.C1520b;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.param.C1572c;
import com.iflytek.voiceads.request.C1582e;
import com.iflytek.voiceads.utils.C1588b;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;
import org.json.JSONArray;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: com.iflytek.voiceads.e.e */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1562e extends VideoDataRef {

    /* JADX INFO: renamed from: a */
    private Context f3312a;

    /* JADX INFO: renamed from: b */
    private C1519a f3313b;

    /* JADX INFO: renamed from: c */
    private C1520b f3314c;

    /* JADX INFO: renamed from: d */
    private String f3315d;

    /* JADX INFO: renamed from: e */
    private C1570a f3316e;

    /* JADX INFO: renamed from: f */
    private IFLYVideoListener f3317f;

    /* JADX INFO: renamed from: g */
    private boolean f3318g = false;

    /* JADX INFO: renamed from: h */
    private boolean f3319h = false;

    public C1562e(Context context, C1520b c1520b, C1570a c1570a, IFLYVideoListener iFLYVideoListener) {
        this.f3312a = context;
        this.f3314c = c1520b;
        this.f3313b = c1520b.f3176f;
        this.f3315d = c1520b.f3173c;
        this.f3316e = c1570a;
        this.f3317f = iFLYVideoListener;
    }

    /* JADX INFO: renamed from: a */
    private void m3061a() {
        String str = this.f3313b.f3142L;
        if (TextUtils.isEmpty(this.f3313b.f3141K)) {
            m3062a(str);
            return;
        }
        String str2 = this.f3313b.f3141K;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        if (!C1588b.m3169a(str2) || !C1588b.m3166a(this.f3312a.getApplicationContext(), intent)) {
            m3062a(str);
            C1596j.m3204b(C1572c.f3370b + "type=NotInstall&sid=" + this.f3315d);
            return;
        }
        try {
            intent.addFlags(32768);
            intent.addFlags(268435456);
            this.f3312a.startActivity(intent);
            C1596j.m3204b(C1572c.f3370b + "type=DeepLink&sid=" + this.f3315d);
        } catch (Exception e) {
            C1596j.m3204b(C1572c.f3370b + "type=DeepFail&sid=" + this.f3315d);
            C1593g.m3190b(SDKConstants.TAG, "video deep" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m3062a(String str) {
        if (!URLUtil.isValidUrl(str) || str.equals("about:blank")) {
            C1593g.m3188a(SDKConstants.TAG, "Invalid click url: " + str);
        }
        switch (getActionType()) {
            case 3:
                m3063b("");
                break;
            default:
                C1582e.m3161a(this.f3312a, str, this.f3316e, this.f3315d);
                C1596j.m3204b(C1572c.f3370b + "type=H5Open&sid=" + this.f3315d);
                break;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m3063b(String str) {
        C1541d c1541dM2966a = C1541d.m2966a(this.f3312a.getApplicationContext());
        c1541dM2966a.m3000a(this.f3317f);
        c1541dM2966a.m3001a(this.f3316e.m3085c(AdKeys.DOWNLOAD_ALERT));
        if (TextUtils.isEmpty(str)) {
            c1541dM2966a.m2998a(this.f3312a, this.f3313b, new Object[0]);
            C1593g.m3188a(SDKConstants.TAG, "startVideoDownload");
        } else {
            c1541dM2966a.m2998a(this.f3312a, this.f3313b, str);
            C1593g.m3188a(SDKConstants.TAG, "startVideoDownload with url");
        }
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public void downloadApp() {
        if (TextUtils.isEmpty(this.f3313b.f3164t)) {
            return;
        }
        m3063b(this.f3313b.f3164t);
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public int getActionType() {
        return this.f3313b.f3143M;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getAdSourceMark() {
        return this.f3313b.f3144N;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getBrand() {
        return this.f3313b.f3136F;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getContent() {
        return this.f3313b.f3159o;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getCtatext() {
        return this.f3313b.f3160p;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getDesc() {
        return this.f3313b.f3157m;
    }

    @Override // com.iflytek.voiceads.conn.VideoDataRef
    public int getDuration() {
        if (this.f3313b.f3154j == null || !this.f3313b.f3154j.has("duration")) {
            return 0;
        }
        return this.f3313b.f3154j.optInt("duration");
    }

    @Override // com.iflytek.voiceads.conn.VideoDataRef
    public int getHeight() {
        if (this.f3313b.f3154j == null || !this.f3313b.f3154j.has("height")) {
            return 0;
        }
        return this.f3313b.f3154j.optInt("height");
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getIconUrl() {
        return (this.f3313b.f3158n == null || !this.f3313b.f3158n.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) ? "" : this.f3313b.f3158n.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getImgUrl() {
        return (this.f3313b.f3150f == null || !this.f3313b.f3150f.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) ? "" : this.f3313b.f3150f.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getRequestID() {
        return this.f3314c.f3172b;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public int getTemplateID() {
        return this.f3313b.f3145a;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getTitle() {
        return this.f3313b.f3156l;
    }

    @Override // com.iflytek.voiceads.conn.VideoDataRef
    public int getWidth() {
        if (this.f3313b.f3154j == null || !this.f3313b.f3154j.has("width")) {
            return 0;
        }
        return this.f3313b.f3154j.optInt("width");
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public boolean isExposured() {
        return this.f3318g;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public boolean onClick(View view) {
        try {
            m3061a();
            if (this.f3319h) {
                return true;
            }
            if (this.f3318g) {
                C1596j.m3203a(this.f3313b.f3140J.optJSONArray("click_urls"), this.f3312a, 2);
                this.f3319h = true;
                return true;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "video clk urls" + e.getMessage());
        }
        return false;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public boolean onExposure(View view) {
        if (this.f3318g) {
            C1593g.m3188a(SDKConstants.TAG, "已曝光");
            return true;
        }
        boolean zM3165a = C1588b.m3165a(this.f3312a);
        boolean zM3170b = C1588b.m3170b(this.f3312a);
        boolean z = view.getVisibility() == 0;
        boolean zIsShown = view.isShown();
        boolean zM3167a = C1588b.m3167a(this.f3312a, view);
        C1593g.m3188a(SDKConstants.TAG, "曝光失败-: B:" + zM3165a + " L:" + zM3170b + " V:" + z + " S:" + zIsShown + " I:" + zM3167a);
        if (zM3165a || zM3170b || !z || !zIsShown || !zM3167a) {
            return false;
        }
        try {
            JSONArray jSONArrayM3202a = C1596j.m3202a(this.f3316e.m3087e(AdKeys.AUCTION_PRICE), this.f3313b.f3140J.optJSONArray("impress_urls"));
            if (jSONArrayM3202a == null) {
                C1593g.m3188a(SDKConstants.TAG, "impArray null");
                return false;
            }
            this.f3318g = true;
            C1593g.m3188a(SDKConstants.TAG, "曝光成功");
            C1596j.m3203a(jSONArrayM3202a, this.f3312a, 1);
            return true;
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "video imp urls" + e.getMessage());
            return false;
        }
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public void showIntroduce() {
        if (TextUtils.isEmpty(this.f3313b.f3165u)) {
            return;
        }
        C1582e.m3161a(this.f3312a, this.f3313b.f3165u, this.f3316e, this.f3315d);
    }
}

package com.iflytek.voiceads.p064c;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.webkit.URLUtil;
import com.iflytek.voiceads.bean.AdAudio;
import com.iflytek.voiceads.bean.AudioMonitor;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.conn.NativeDataRef;
import com.iflytek.voiceads.download.C1541d;
import com.iflytek.voiceads.listener.IFLYNativeListener;
import com.iflytek.voiceads.p065d.C1519a;
import com.iflytek.voiceads.p065d.C1520b;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.param.C1572c;
import com.iflytek.voiceads.request.C1582e;
import com.iflytek.voiceads.utils.C1588b;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;
import com.umeng.analytics.pro.C1708b;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.wlf.filedownloader.DownloadFileInfo;

/* JADX INFO: renamed from: com.iflytek.voiceads.c.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1517c extends NativeDataRef {

    /* JADX INFO: renamed from: a */
    private Context f3122a;

    /* JADX INFO: renamed from: b */
    private C1519a f3123b;

    /* JADX INFO: renamed from: c */
    private C1520b f3124c;

    /* JADX INFO: renamed from: d */
    private String f3125d;

    /* JADX INFO: renamed from: e */
    private C1570a f3126e;

    /* JADX INFO: renamed from: f */
    private IFLYNativeListener f3127f;

    /* JADX INFO: renamed from: g */
    private boolean f3128g = false;

    /* JADX INFO: renamed from: h */
    private boolean f3129h = false;

    public C1517c(Context context, C1520b c1520b, C1570a c1570a, IFLYNativeListener iFLYNativeListener) {
        this.f3122a = context;
        this.f3124c = c1520b;
        this.f3123b = c1520b.f3176f;
        this.f3125d = c1520b.f3173c;
        this.f3126e = c1570a;
        this.f3127f = iFLYNativeListener;
    }

    /* JADX INFO: renamed from: a */
    private void m2894a() {
        String str = this.f3123b.f3142L;
        if (TextUtils.isEmpty(this.f3123b.f3141K)) {
            m2895a(str);
            return;
        }
        String str2 = this.f3123b.f3141K;
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str2));
        if (!C1588b.m3169a(str2) || !C1588b.m3166a(this.f3122a.getApplicationContext(), intent)) {
            m2895a(str);
            C1596j.m3204b(C1572c.f3370b + "type=NotInstall&sid=" + this.f3125d);
            return;
        }
        try {
            intent.addFlags(32768);
            intent.addFlags(268435456);
            this.f3122a.startActivity(intent);
            C1596j.m3204b(C1572c.f3370b + "type=DeepLink&sid=" + this.f3125d);
        } catch (Exception e) {
            C1596j.m3204b(C1572c.f3370b + "type=DeepFail&sid=" + this.f3125d);
            C1593g.m3190b(SDKConstants.TAG, "native deep" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private void m2895a(String str) {
        if (!URLUtil.isValidUrl(str) || str.equals("about:blank")) {
            C1593g.m3188a(SDKConstants.TAG, "Invalid click url: " + str);
        }
        switch (getActionType()) {
            case 3:
                m2896b("");
                break;
            default:
                C1582e.m3161a(this.f3122a, str, this.f3126e, this.f3125d);
                C1596j.m3204b(C1572c.f3370b + "type=H5Open&sid=" + this.f3125d);
                break;
        }
    }

    /* JADX INFO: renamed from: b */
    private void m2896b(String str) {
        C1541d c1541dM2966a = C1541d.m2966a(this.f3122a.getApplicationContext());
        c1541dM2966a.m3000a(this.f3127f);
        c1541dM2966a.m3001a(this.f3126e.m3085c(AdKeys.DOWNLOAD_ALERT));
        if (TextUtils.isEmpty(str)) {
            c1541dM2966a.m2998a(this.f3122a, this.f3123b, new Object[0]);
            C1593g.m3188a(SDKConstants.TAG, "startNativeDownload");
        } else {
            c1541dM2966a.m2998a(this.f3122a, this.f3123b, str);
            C1593g.m3188a(SDKConstants.TAG, "startNativeDownload with url");
        }
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public void downloadApp() {
        if (TextUtils.isEmpty(this.f3123b.f3164t)) {
            return;
        }
        m2896b(this.f3123b.f3164t);
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public int getActionType() {
        return this.f3123b.f3143M;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public AdAudio getAdAudio() {
        if (this.f3123b.f3155k == null) {
            return null;
        }
        AdAudio adAudio = new AdAudio();
        adAudio.url = this.f3123b.f3155k.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
        if (this.f3123b.f3155k.has("duration")) {
            adAudio.duration = this.f3123b.f3155k.optInt("duration");
        }
        if (this.f3123b.f3155k.has("bitrate")) {
            adAudio.bitrate = this.f3123b.f3155k.optInt("bitrate");
        }
        if (this.f3123b.f3155k.has("format")) {
            adAudio.format = this.f3123b.f3155k.optInt("format");
        }
        if (!this.f3123b.f3155k.has(C1708b.f3748q)) {
            return adAudio;
        }
        adAudio.cacheDeadLine = this.f3123b.f3155k.optLong(C1708b.f3748q);
        return adAudio;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getAdSourceMark() {
        return this.f3123b.f3144N;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public String getAddress() {
        return this.f3123b.f3133C;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public String getAppName() {
        return this.f3123b.f3161q;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public double getAppSize() {
        return this.f3123b.f3167w;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public String getAppVer() {
        return this.f3123b.f3166v;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public AudioMonitor getAudioMonitor() {
        if (this.f3123b.f3155k == null || this.f3123b.f3140J == null) {
            return null;
        }
        AudioMonitor audioMonitor = new AudioMonitor();
        audioMonitor.start_urls = this.f3123b.f3140J.optJSONArray("start_urls");
        audioMonitor.first_quartile_urls = this.f3123b.f3140J.optJSONArray("first_quartile_urls");
        audioMonitor.mid_point_urls = this.f3123b.f3140J.optJSONArray("mid_point_urls");
        audioMonitor.third_quartile_urls = this.f3123b.f3140J.optJSONArray("third_quartile_urls");
        audioMonitor.complete_urls = this.f3123b.f3140J.optJSONArray("complete_urls");
        audioMonitor.pause_urls = this.f3123b.f3140J.optJSONArray("pause_urls");
        audioMonitor.resume_urls = this.f3123b.f3140J.optJSONArray("resume_urls");
        audioMonitor.skip_urls = this.f3123b.f3140J.optJSONArray("skip_urls");
        audioMonitor.mute_urls = this.f3123b.f3140J.optJSONArray("mute_urls");
        audioMonitor.unmute_urls = this.f3123b.f3140J.optJSONArray("unmute_urls");
        audioMonitor.replay_urls = this.f3123b.f3140J.optJSONArray("replay_urls");
        audioMonitor.close_urls = this.f3123b.f3140J.optJSONArray("close_linear_urls");
        return audioMonitor;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getBrand() {
        return this.f3123b.f3136F;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getContent() {
        return this.f3123b.f3159o;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getCtatext() {
        return this.f3123b.f3160p;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public double getCurrentPrice() {
        return this.f3123b.f3131A;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getDesc() {
        return this.f3123b.f3157m;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public ArrayList<String> getDisplayLabels() {
        if (this.f3123b.f3137G == null) {
            return null;
        }
        JSONArray jSONArray = this.f3123b.f3137G;
        ArrayList<String> arrayList = new ArrayList<>();
        int i = 0;
        while (i < jSONArray.length()) {
            i++;
            try {
                arrayList.add(jSONArray.getString(i));
            } catch (JSONException e) {
                C1593g.m3190b(SDKConstants.TAG, "get labels" + e.getMessage());
            }
        }
        return arrayList;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public int getDownloads() {
        return this.f3123b.f3162r;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getIconUrl() {
        return (this.f3123b.f3158n == null || !this.f3123b.f3158n.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) ? "" : this.f3123b.f3158n.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public List<String> getImgList() {
        ArrayList arrayList = new ArrayList();
        if (this.f3123b.f3151g != null && this.f3123b.f3151g.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) {
            arrayList.add(this.f3123b.f3151g.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL));
        }
        if (this.f3123b.f3152h != null && this.f3123b.f3152h.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) {
            arrayList.add(this.f3123b.f3152h.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL));
        }
        if (this.f3123b.f3153i != null && this.f3123b.f3153i.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) {
            arrayList.add(this.f3123b.f3153i.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL));
        }
        return arrayList;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getImgUrl() {
        return (this.f3123b.f3150f == null || !this.f3123b.f3150f.has(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL)) ? "" : this.f3123b.f3150f.optString(DownloadFileInfo.Table.COLUMN_NAME_OF_FIELD_URL);
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public int getLikes() {
        return this.f3123b.f3169y;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public double getOriginalPrice() {
        return this.f3123b.f3170z;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public String getPhone() {
        return this.f3123b.f3168x;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public String getRating() {
        return this.f3123b.f3163s;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getRequestID() {
        return this.f3124c.f3172b;
    }

    @Override // com.iflytek.voiceads.conn.NativeDataRef
    public String getSponsored() {
        return this.f3123b.f3132B;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public int getTemplateID() {
        return this.f3123b.f3145a;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public String getTitle() {
        return this.f3123b.f3156l;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public boolean isExposured() {
        return this.f3128g;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public boolean onClick(View view) {
        m2894a();
        if (this.f3129h) {
            return true;
        }
        try {
            if (this.f3128g) {
                C1596j.m3203a(this.f3123b.f3140J.optJSONArray("click_urls"), this.f3122a, 2);
                this.f3129h = true;
                return true;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "native clk urls" + e.getMessage());
        }
        return false;
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public boolean onExposure(View view) {
        if (this.f3128g) {
            C1593g.m3188a(SDKConstants.TAG, "已曝光");
            return true;
        }
        if (!this.f3126e.m3085c(AdKeys.LOCK_SCREEN_AD) && C1588b.m3170b(this.f3122a)) {
            C1593g.m3188a(SDKConstants.TAG, "曝光失败-L");
            return false;
        }
        boolean zM3165a = C1588b.m3165a(this.f3122a);
        boolean z = view.getVisibility() == 0;
        boolean zIsShown = view.isShown();
        boolean zM3167a = C1588b.m3167a(this.f3122a, view);
        C1593g.m3188a(SDKConstants.TAG, "曝光失败-: B:" + zM3165a + " V:" + z + " S:" + zIsShown + " I:" + zM3167a);
        if (zM3165a || !z || !zIsShown || !zM3167a) {
            return false;
        }
        try {
            JSONArray jSONArrayM3202a = C1596j.m3202a(this.f3126e.m3087e(AdKeys.AUCTION_PRICE), this.f3123b.f3140J.optJSONArray("impress_urls"));
            if (jSONArrayM3202a == null) {
                C1593g.m3188a(SDKConstants.TAG, "impArray null");
                return false;
            }
            this.f3128g = true;
            C1593g.m3188a(SDKConstants.TAG, "曝光成功");
            C1596j.m3203a(jSONArrayM3202a, this.f3122a, 1);
            return true;
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "native imp urls" + e.getMessage());
            return false;
        }
    }

    @Override // com.iflytek.voiceads.conn.BaseDataRef
    public void showIntroduce() {
        if (TextUtils.isEmpty(this.f3123b.f3165u)) {
            return;
        }
        C1582e.m3161a(this.f3122a, this.f3123b.f3165u, this.f3126e, this.f3125d);
    }
}

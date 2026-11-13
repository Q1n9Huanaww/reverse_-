package com.iflytek.voiceads.bridge;

import android.content.Context;
import android.os.CountDownTimer;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.C1537c;
import com.iflytek.voiceads.p062a.C1489a;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;

/* JADX INFO: renamed from: com.iflytek.voiceads.bridge.b */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1501b {

    /* JADX INFO: renamed from: a */
    private Context f3088a;

    /* JADX INFO: renamed from: b */
    private DSBridgeWebView f3089b;

    /* JADX INFO: renamed from: c */
    private C1489a f3090c;

    /* JADX INFO: renamed from: d */
    private a f3091d;

    /* JADX INFO: renamed from: e */
    private boolean f3092e;

    /* JADX INFO: renamed from: f */
    private String f3093f;

    /* JADX INFO: renamed from: com.iflytek.voiceads.bridge.b$a */
    class a extends CountDownTimer {

        /* JADX INFO: renamed from: a */
        final /* synthetic */ C1501b f3094a;

        @Override // android.os.CountDownTimer
        public void onFinish() {
            if (this.f3094a.f3092e || this.f3094a.f3089b == null) {
                return;
            }
            this.f3094a.f3089b.m2874a("voiceRecordEnd", (Object[]) null, (InterfaceC1513n) null);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }
    }

    public C1501b(Context context, DSBridgeWebView dSBridgeWebView) {
        this.f3088a = context;
        this.f3089b = dSBridgeWebView;
        this.f3093f = C1537c.m2960a(context) + File.separator + "voiceRecord.wav";
    }

    /* JADX INFO: renamed from: a */
    public void m2880a() {
        try {
            this.f3092e = true;
            if (this.f3091d != null) {
                this.f3091d = null;
            }
            if (this.f3090c != null) {
                this.f3090c.a();
                this.f3090c = null;
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "stopRecordExcept:" + e.getMessage());
        }
    }
}

package com.iflytek.voiceads.request;

import android.R;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.iflytek.voiceads.bridge.C1501b;
import com.iflytek.voiceads.bridge.DSBridgeWebView;
import com.iflytek.voiceads.bridge.InterfaceC1514o;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.C1541d;
import com.iflytek.voiceads.listener.DialogListener;
import com.iflytek.voiceads.utils.C1593g;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class IFLYBrowser extends Activity {

    /* JADX INFO: renamed from: a */
    InterfaceC1514o f3376a = new C1579b(this);

    /* JADX INFO: renamed from: b */
    WebChromeClient f3377b = new C1580c(this);

    /* JADX INFO: renamed from: c */
    DialogListener f3378c = new C1581d(this);

    /* JADX INFO: renamed from: d */
    private DSBridgeWebView f3379d;

    /* JADX INFO: renamed from: e */
    private LinearLayout f3380e;

    /* JADX INFO: renamed from: f */
    private ProgressBar f3381f;

    /* JADX INFO: renamed from: g */
    private C1501b f3382g;

    /* JADX INFO: renamed from: h */
    private String f3383h;

    /* JADX INFO: renamed from: com.iflytek.voiceads.request.IFLYBrowser$a */
    class C1577a implements DownloadListener {
        C1577a() {
        }

        @Override // android.webkit.DownloadListener
        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            C1593g.m3188a(SDKConstants.TAG, "self webView");
            C1541d c1541dM2966a = C1541d.m2966a(IFLYBrowser.this.getApplicationContext());
            c1541dM2966a.m3000a(IFLYBrowser.this.f3378c);
            c1541dM2966a.m3001a(IFLYBrowser.this.getIntent().getBooleanExtra("is_show", false));
            c1541dM2966a.m2999a(IFLYBrowser.this, str);
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        this.f3380e = new LinearLayout(getApplicationContext());
        this.f3380e.setOrientation(1);
        this.f3379d = new DSBridgeWebView(getApplicationContext());
        this.f3379d.getSettings().setDomStorageEnabled(true);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        this.f3381f = new ProgressBar(getApplicationContext(), null, R.attr.progressBarStyleHorizontal);
        this.f3381f.setMax(100);
        this.f3381f.setProgress(0);
        this.f3380e.addView(this.f3381f, -1, 16);
        this.f3380e.addView(this.f3379d, layoutParams);
        setContentView(this.f3380e);
        this.f3383h = getIntent().getStringExtra("ad_session_id");
        this.f3379d.m2870a(this.f3376a);
        this.f3379d.setWebChromeClient(this.f3377b);
        this.f3379d.setDownloadListener(new C1577a());
        this.f3382g = new C1501b(getApplicationContext(), this.f3379d);
        this.f3379d.m2872a(this.f3382g, (String) null);
        this.f3379d.loadUrl(getIntent().getStringExtra("url_ad"));
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        C1593g.m3188a(SDKConstants.TAG, "IFLYBrowser onDestroy");
        try {
            if (this.f3382g != null) {
                this.f3382g.m2880a();
            }
            if (this.f3379d != null) {
                this.f3380e.removeView(this.f3379d);
                this.f3379d.stopLoading();
                this.f3379d.getSettings().setJavaScriptEnabled(false);
                this.f3379d.clearHistory();
                this.f3379d.loadUrl("about:blank");
                this.f3379d.removeAllViews();
                this.f3379d.destroy();
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "IFLYBrowser onDestroy " + e.getMessage());
        }
        super.onDestroy();
    }

    @Override // android.app.Activity
    protected void onPause() {
        super.onPause();
        if (this.f3382g != null) {
            this.f3382g.m2880a();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
    }
}

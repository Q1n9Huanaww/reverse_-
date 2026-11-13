package com.iflytek.voiceads.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.iflytek.voiceads.config.AdError;
import com.iflytek.voiceads.config.AdKeys;
import com.iflytek.voiceads.config.ErrorCode;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.download.C1541d;
import com.iflytek.voiceads.listener.IFLYAdListener;
import com.iflytek.voiceads.listener.InternalListener;
import com.iflytek.voiceads.p065d.C1519a;
import com.iflytek.voiceads.p065d.C1520b;
import com.iflytek.voiceads.p074f.C1566a;
import com.iflytek.voiceads.p074f.C1567b;
import com.iflytek.voiceads.p074f.InterfaceC1568c;
import com.iflytek.voiceads.p074f.InterfaceC1569d;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.request.C1578a;
import com.iflytek.voiceads.request.C1582e;
import com.iflytek.voiceads.request.C1583f;
import com.iflytek.voiceads.utils.C1587a;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;
import org.json.JSONArray;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public abstract class AdView extends WebView implements HandlerC1625b.a {

    /* JADX INFO: renamed from: a */
    protected Context f3531a;

    /* JADX INFO: renamed from: b */
    protected RelativeLayout f3532b;

    /* JADX INFO: renamed from: c */
    protected C1567b f3533c;

    /* JADX INFO: renamed from: d */
    protected C1566a f3534d;

    /* JADX INFO: renamed from: e */
    protected C1570a f3535e;

    /* JADX INFO: renamed from: f */
    protected C1520b f3536f;

    /* JADX INFO: renamed from: g */
    protected C1519a f3537g;

    /* JADX INFO: renamed from: h */
    protected C1587a.a f3538h;

    /* JADX INFO: renamed from: i */
    protected C1587a.b f3539i;

    /* JADX INFO: renamed from: j */
    protected IFLYAdListener f3540j;

    /* JADX INFO: renamed from: k */
    protected InternalListener f3541k;

    /* JADX INFO: renamed from: l */
    protected HandlerC1625b f3542l;

    /* JADX INFO: renamed from: m */
    protected HandlerThread f3543m;

    /* JADX INFO: renamed from: n */
    protected HandlerC1624a f3544n;

    /* JADX INFO: renamed from: o */
    protected HandlerC1623a f3545o;

    /* JADX INFO: renamed from: p */
    protected int f3546p;

    /* JADX INFO: renamed from: q */
    C1578a.a f3547q;

    /* JADX INFO: renamed from: r */
    InterfaceC1569d f3548r;

    /* JADX INFO: renamed from: s */
    InterfaceC1568c f3549s;

    /* JADX INFO: renamed from: t */
    private int f3550t;

    /* JADX INFO: renamed from: u */
    private boolean f3551u;

    /* JADX INFO: renamed from: v */
    private final int f3552v;

    /* JADX INFO: renamed from: w */
    private final int f3553w;

    /* JADX INFO: renamed from: x */
    private final int f3554x;

    /* JADX INFO: renamed from: com.iflytek.voiceads.view.AdView$a */
    class HandlerC1623a extends Handler {
        private HandlerC1623a(Looper looper) {
            super(looper);
        }

        /* synthetic */ HandlerC1623a(AdView adView, Looper looper, C1626c c1626c) {
            this(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            switch (message.what) {
                case 1:
                    C1593g.m3188a(SDKConstants.TAG, "procMsgShow");
                    AdView.this.m3334a(0);
                    AdView.this.m3345d();
                    AdView.this.mo3352j();
                    ViewGroup viewGroup = (ViewGroup) AdView.this.f3532b.getParent();
                    if (viewGroup != null && viewGroup.getVisibility() == 0) {
                        if (AdView.this.f3535e.m3085c(AdKeys.BANNER_RECYCLE)) {
                            AdView.this.mo3350h();
                        }
                        AdView.this.f3540j.onAdExposure();
                    } else {
                        AdView.this.f3540j.onAdFailed(new AdError(ErrorCode.ERROR_INVALID_REQUEST));
                        C1593g.m3190b(SDKConstants.TAG, "Ad is invisible, invalid exposure!");
                    }
                    break;
                case 2:
                    C1593g.m3188a(SDKConstants.TAG, "procMsgTimeout:" + AdView.this.getProgress() + "% , " + AdView.this.getContentHeight());
                    if (AdView.this.getContentHeight() <= 0 && AdView.this.m3330q() != C1587a.b.success) {
                        if (AdView.this.f3550t >= 2) {
                            AdView.this.f3550t = 0;
                            AdView.this.f3542l.m3363a(5, ErrorCode.ERROR_PAGE_LOAD_TIMEOUT);
                        } else {
                            AdView.m3325c(AdView.this);
                            AdView.this.m3329p();
                            C1593g.m3188a(SDKConstants.TAG, "Loading ad timeout, reload again!");
                        }
                        break;
                    }
                    break;
                case 3:
                    try {
                        String strOptString = AdView.this.f3537g.f3149e.optString("adm");
                        if (TextUtils.isEmpty(strOptString)) {
                            AdView.this.f3542l.m3363a(5, ErrorCode.ERROR_INVALID_REQUEST);
                        } else {
                            try {
                                AdView.this.loadDataWithBaseURL(null, strOptString, "text/html", "utf-8", null);
                                AdView.this.mo3353k();
                            } catch (Exception e) {
                                AdView.this.f3542l.m3363a(5, ErrorCode.ERROR_PAGE_LOAD_ERROR);
                                AdView.this.f3541k.onAdDestroy();
                            }
                        }
                    } catch (Exception e2) {
                        AdView.this.f3542l.m3363a(5, ErrorCode.ERROR_PAGE_LOAD_ERROR);
                        C1593g.m3190b(SDKConstants.TAG, "html load:" + e2.getMessage());
                        return;
                    }
                    break;
            }
        }
    }

    protected AdView(Context context) {
        super(context);
        this.f3539i = C1587a.b.init;
        this.f3546p = 0;
        this.f3550t = 0;
        this.f3547q = new C1626c(this);
        this.f3551u = false;
        this.f3548r = new C1627d(this);
        this.f3549s = new C1628e(this);
        this.f3552v = 1;
        this.f3553w = 2;
        this.f3554x = 3;
    }

    protected AdView(Context context, RelativeLayout relativeLayout, String str, C1587a.a aVar, InternalListener internalListener) {
        super(context.getApplicationContext());
        this.f3539i = C1587a.b.init;
        this.f3546p = 0;
        this.f3550t = 0;
        this.f3547q = new C1626c(this);
        this.f3551u = false;
        this.f3548r = new C1627d(this);
        this.f3549s = new C1628e(this);
        this.f3552v = 1;
        this.f3553w = 2;
        this.f3554x = 3;
        this.f3531a = context.getApplicationContext();
        this.f3532b = relativeLayout;
        this.f3538h = aVar;
        this.f3541k = internalListener;
        this.f3545o = new HandlerC1623a(this, context.getMainLooper(), null);
        this.f3536f = new C1520b(this.f3531a.getApplicationContext());
        this.f3535e = new C1570a(str);
        m3327n();
    }

    /* JADX INFO: renamed from: a */
    private synchronized void m3321a(C1587a.b bVar) {
        this.f3539i = bVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m3324b(String str) throws AdError {
        this.f3536f.m2906a(str);
        this.f3537g = this.f3536f.f3176f;
        if (70200 != this.f3536f.f3171a) {
            this.f3542l.m3363a(5, this.f3536f.f3171a);
            return;
        }
        try {
            int iOptInt = this.f3537g.f3149e.optInt("width");
            int iOptInt2 = this.f3537g.f3149e.optInt("height");
            if (iOptInt <= 0 || iOptInt2 <= 0) {
                this.f3542l.m3363a(5, ErrorCode.ERROR_PAGE_LOAD_ERROR);
                return;
            }
            this.f3535e.m3079a(iOptInt);
            this.f3535e.m3083b(iOptInt2);
            this.f3542l.m3363a(2, this.f3536f.f3171a);
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "html parse2:" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: c */
    static /* synthetic */ int m3325c(AdView adView) {
        int i = adView.f3550t;
        adView.f3550t = i + 1;
        return i;
    }

    /* JADX INFO: renamed from: n */
    private void m3327n() {
        setScrollContainer(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        getSettings().setJavaScriptEnabled(true);
        this.f3542l = new HandlerC1625b(m3328o().getLooper(), this);
        this.f3544n = new HandlerC1624a();
        this.f3544n.m3360a(this.f3541k);
        setBackgroundColor(0);
        getSettings().setCacheMode(2);
        if (Build.VERSION.SDK_INT >= 16) {
            getSettings().setAllowUniversalAccessFromFileURLs(true);
        }
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        m3334a(8);
        this.f3533c = new C1567b(this.f3548r);
        setWebViewClient(this.f3533c);
        this.f3534d = new C1566a(this.f3549s);
        setWebChromeClient(this.f3534d);
    }

    /* JADX INFO: renamed from: o */
    private HandlerThread m3328o() {
        this.f3543m = new HandlerThread("" + this.f3538h);
        this.f3543m.start();
        return this.f3543m;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: p */
    public synchronized void m3329p() {
        try {
            m3321a(C1587a.b.start);
            Message message = new Message();
            message.obj = this.f3536f;
            message.what = 3;
            this.f3545o.sendMessage(message);
        } catch (Exception e) {
            this.f3542l.m3363a(5, ErrorCode.ERROR_PAGE_LOAD_ERROR);
            C1593g.m3190b(SDKConstants.TAG, "ShowAd:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: q */
    public synchronized C1587a.b m3330q() {
        return this.f3539i;
    }

    /* JADX INFO: renamed from: r */
    private void m3331r() {
        if (this.f3544n.hasMessages(4)) {
            C1593g.m3188a(SDKConstants.TAG, "移除关闭广告msg");
            this.f3544n.removeMessages(4);
            this.f3551u = true;
        }
    }

    /* JADX INFO: renamed from: s */
    private void m3332s() {
        if (this.f3551u) {
            C1593g.m3188a(SDKConstants.TAG, "添加关闭广告msg");
            this.f3544n.m3357a(4, ExToast.LENGTH_SHORT);
        }
    }

    @Override // com.iflytek.voiceads.view.HandlerC1625b.a
    /* JADX INFO: renamed from: a */
    public void mo3333a() {
        m3329p();
    }

    /* JADX INFO: renamed from: a */
    public void m3334a(int i) {
        if (this.f3532b == null) {
            return;
        }
        setVisibility(i);
        this.f3532b.setVisibility(i);
        for (int i2 = 0; i2 < this.f3532b.getChildCount(); i2++) {
            this.f3532b.getChildAt(i2).setVisibility(i);
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m3335a(int i, int i2) {
        C1593g.m3188a(SDKConstants.TAG, "AdView setLayoutParam()");
        RelativeLayout.LayoutParams layoutParamsM3340b = m3340b(i, i2);
        if (getParent() == null) {
            this.f3532b.removeAllViews();
            this.f3532b.addView(this, layoutParamsM3340b);
        }
    }

    @Override // com.iflytek.voiceads.view.HandlerC1625b.a
    /* JADX INFO: renamed from: a */
    public void mo3336a(Message message) {
        try {
            mo3346d(message);
        } catch (AdError e) {
            this.f3542l.m3363a(5, e.getErrorCode());
        } catch (Exception e2) {
            this.f3542l.m3363a(5, ErrorCode.ERROR_NETWORK);
            C1593g.m3190b(SDKConstants.TAG, "Request:" + e2.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    public synchronized void mo3337a(IFLYAdListener iFLYAdListener) {
        try {
            if (iFLYAdListener == null) {
                C1593g.m3190b(SDKConstants.TAG, "invalid IFLYAdListener!");
            } else if (C1587a.c.exit == this.f3542l.m3361a()) {
                C1593g.m3188a(SDKConstants.TAG, "ad is exited!");
            } else if (C1587a.c.init == this.f3542l.m3361a() || C1587a.c.end == this.f3542l.m3361a()) {
                if (this.f3535e.m3085c(AdKeys.DEBUG_MODE)) {
                    C1593g.m3189a(true);
                } else {
                    C1593g.m3189a(false);
                }
                this.f3540j = iFLYAdListener;
                this.f3544n.m3359a(iFLYAdListener);
                this.f3542l.m3362a(1);
            } else {
                C1593g.m3188a(SDKConstants.TAG, "ad is requesting, please retry a little later!");
            }
        } catch (Throwable th) {
            throw th;
        }
    }

    /* JADX INFO: renamed from: a */
    protected void m3338a(String str) {
        int i = this.f3537g.f3143M;
        C1593g.m3188a(SDKConstants.TAG, "clickAd: actionType=" + i);
        if (!URLUtil.isValidUrl(str) || str.equals("about:blank")) {
            C1593g.m3188a(SDKConstants.TAG, "Invalid click url: " + str);
            return;
        }
        switch (i) {
            case 3:
                C1541d c1541dM2966a = C1541d.m2966a(this.f3531a.getApplicationContext());
                c1541dM2966a.m3000a(this.f3540j);
                c1541dM2966a.m3001a(this.f3535e.m3085c(AdKeys.DOWNLOAD_ALERT));
                c1541dM2966a.m2998a(this.f3531a, this.f3536f.f3176f, str);
                C1593g.m3188a(SDKConstants.TAG, "AdView startDownload");
                break;
            default:
                C1582e.m3161a(this.f3531a, str, this.f3535e, this.f3536f.f3173c);
                break;
        }
        C1593g.m3188a(SDKConstants.TAG, "点击监控");
        try {
            if (this.f3537g.f3140J != null && this.f3537g.f3140J.has("click_urls")) {
                C1596j.m3203a(this.f3537g.f3140J.optJSONArray("click_urls"), this.f3531a, 2);
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "html click:" + e.getMessage());
        }
        this.f3540j.onAdClick();
    }

    /* JADX INFO: renamed from: a */
    public void m3339a(String str, Object obj) {
        this.f3535e.m3080a(str, obj);
    }

    /* JADX INFO: renamed from: b */
    protected RelativeLayout.LayoutParams m3340b(int i, int i2) {
        return new RelativeLayout.LayoutParams(i, i2);
    }

    @Override // com.iflytek.voiceads.view.HandlerC1625b.a
    /* JADX INFO: renamed from: b */
    public void mo3341b() {
        this.f3545o.sendEmptyMessage(1);
        this.f3542l.m3366a(C1587a.c.end);
        try {
            if (this.f3537g.f3140J != null && this.f3537g.f3140J.has("impress_urls")) {
                JSONArray jSONArrayM3202a = C1596j.m3202a(this.f3535e.m3087e(AdKeys.AUCTION_PRICE), this.f3537g.f3140J.optJSONArray("impress_urls"));
                if (jSONArrayM3202a != null) {
                    C1596j.m3203a(jSONArrayM3202a, this.f3531a, 1);
                } else {
                    C1593g.m3190b(SDKConstants.TAG, "impArray null");
                }
            }
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "Show:" + e.getMessage());
        }
        C1593g.m3188a(SDKConstants.TAG, "procMsgShow successfully!");
    }

    @Override // com.iflytek.voiceads.view.HandlerC1625b.a
    /* JADX INFO: renamed from: b */
    public void mo3342b(Message message) {
        this.f3545o.sendEmptyMessage(2);
        C1593g.m3188a(SDKConstants.TAG, "procMsgTimeout " + message.obj);
    }

    @Override // com.iflytek.voiceads.view.HandlerC1625b.a
    /* JADX INFO: renamed from: c */
    public void mo3343c() {
        this.f3540j.onAdClose();
        m3351i();
        this.f3531a = null;
    }

    @Override // com.iflytek.voiceads.view.HandlerC1625b.a
    /* JADX INFO: renamed from: c */
    public void mo3344c(Message message) {
        C1593g.m3188a(SDKConstants.TAG, "procMsgEnd:" + message.obj);
        int errorCode = 0;
        if (message.obj != null) {
            AdError adError = new AdError(((Integer) message.obj).intValue());
            errorCode = adError.getErrorCode();
            this.f3544n.m3358a(1, adError);
        }
        if (70403 == errorCode || !this.f3535e.m3085c(AdKeys.BANNER_RECYCLE)) {
            return;
        }
        mo3350h();
    }

    /* JADX INFO: renamed from: d */
    protected void m3345d() {
        if (C1587a.a.BANNER != this.f3538h) {
            requestFocus();
        }
        setFocusable(true);
        setFocusableInTouchMode(true);
    }

    /* JADX INFO: renamed from: d */
    protected synchronized void mo3346d(Message message) throws Exception {
        C1583f.m3162a(this.f3531a.getApplicationContext(), this.f3535e, this.f3547q);
    }

    /* JADX INFO: renamed from: e */
    protected int m3347e() {
        int iM3086d = this.f3535e.m3086d(AdKeys.BANNER_INTERVAL);
        if (iM3086d < 15 || iM3086d > 40) {
            return 30000;
        }
        return iM3086d * 1000;
    }

    /* JADX INFO: renamed from: f */
    protected boolean m3348f() {
        return this.f3535e.m3085c(AdKeys.BANNER_RECYCLE);
    }

    /* JADX INFO: renamed from: g */
    public synchronized void m3349g() {
        this.f3542l.m3363a(3, ErrorCode.SUCCESS);
    }

    /* JADX INFO: renamed from: h */
    protected synchronized void mo3350h() {
    }

    /* JADX INFO: renamed from: i */
    protected void m3351i() {
        this.f3542l.removeCallbacksAndMessages(null);
        this.f3543m.quit();
    }

    /* JADX INFO: renamed from: j */
    protected void mo3352j() {
    }

    /* JADX INFO: renamed from: k */
    protected abstract void mo3353k();

    /* JADX INFO: renamed from: l */
    public void m3354l() {
        mo3343c();
    }

    /* JADX INFO: renamed from: m */
    protected void m3355m() {
        if (m3330q() != C1587a.b.success) {
            int visibility = getVisibility();
            m3321a(C1587a.b.success);
            this.f3540j.onAdReceive();
            C1593g.m3188a(SDKConstants.TAG, "onAdPageFinished:" + visibility);
        }
    }

    @Override // android.webkit.WebView, android.view.View, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case 4:
                if (C1587a.a.INTERSTITIAL.equals(this.f3538h) && this.f3535e.m3085c(AdKeys.BACK_KEY_ENABLE)) {
                    this.f3541k.onAdDestroy();
                    this.f3540j.onAdClose();
                    return true;
                }
                break;
        }
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.webkit.WebView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 1:
                this.f3533c.m3068a(true);
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    @Override // android.webkit.WebView, android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        this.f3546p = i;
        C1593g.m3188a(SDKConstants.TAG, "ad view visibility=" + this.f3546p);
        if (this.f3546p == 8 || this.f3546p == 4) {
            m3331r();
        }
        if (this.f3546p == 0) {
            m3332s();
        }
    }
}

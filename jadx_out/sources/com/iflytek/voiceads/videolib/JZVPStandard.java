package com.iflytek.voiceads.videolib;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import com.iflytek.voiceads.utils.C1591e;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;
import com.iflytek.voiceads.utils.C1597k;
import java.util.Timer;
import java.util.TimerTask;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class JZVPStandard extends JZPlayer {

    /* JADX INFO: renamed from: U */
    public ProgressBar f3475U;

    /* JADX INFO: renamed from: V */
    public ImageView f3476V;

    /* JADX INFO: renamed from: W */
    public TextView f3477W;

    /* JADX INFO: renamed from: aa */
    public LinearLayout f3478aa;

    /* JADX INFO: renamed from: ab */
    public int f3479ab;

    /* JADX INFO: renamed from: ac */
    public int f3480ac;

    /* JADX INFO: renamed from: ad */
    public int f3481ad;

    /* JADX INFO: renamed from: ae */
    public int f3482ae;

    /* JADX INFO: renamed from: af */
    public int f3483af;

    /* JADX INFO: renamed from: ag */
    public int f3484ag;

    /* JADX INFO: renamed from: ah */
    public int f3485ah;

    /* JADX INFO: renamed from: ai */
    public int f3486ai;

    /* JADX INFO: renamed from: aj */
    public int f3487aj;

    /* JADX INFO: renamed from: ak */
    private Timer f3488ak;

    /* JADX INFO: renamed from: al */
    private C1602a f3489al;

    /* JADX INFO: renamed from: com.iflytek.voiceads.videolib.JZVPStandard$a */
    public class C1602a extends TimerTask {
        public C1602a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            JZVPStandard.this.m3283Y();
        }
    }

    public JZVPStandard(Context context) {
        super(context);
    }

    public JZVPStandard(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: L */
    public void mo3226L() {
        super.mo3226L();
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage("您当前正在使用移动网络，继续播放将消耗流量！");
        builder.setPositiveButton("继续", new DialogInterfaceOnClickListenerC1618p(this));
        builder.setNegativeButton("取消", new DialogInterfaceOnClickListenerC1619q(this));
        builder.setOnCancelListener(new DialogInterfaceOnCancelListenerC1620r(this));
        builder.create().show();
    }

    /* JADX INFO: renamed from: M */
    public void m3271M() {
        switch (this.f3452g) {
            case 1:
                m3273O();
                break;
            case 3:
                if (this.f3464s.getVisibility() != 0) {
                    m3274P();
                } else {
                    m3275Q();
                }
                break;
            case 5:
                if (this.f3464s.getVisibility() != 0) {
                    m3276R();
                } else {
                    m3277S();
                }
                break;
        }
    }

    /* JADX INFO: renamed from: N */
    public void m3272N() {
        m3284a(4, 0, 4, 0, 4);
        m3280V();
    }

    /* JADX INFO: renamed from: O */
    public void m3273O() {
        m3284a(4, 4, 0, 0, 4);
        m3280V();
    }

    /* JADX INFO: renamed from: P */
    public void m3274P() {
        m3284a(0, 0, 4, 4, 4);
        m3280V();
    }

    /* JADX INFO: renamed from: Q */
    public void m3275Q() {
        m3284a(4, 4, 4, 4, 4);
    }

    /* JADX INFO: renamed from: R */
    public void m3276R() {
        m3284a(0, 0, 4, 4, 4);
        m3280V();
    }

    /* JADX INFO: renamed from: S */
    public void m3277S() {
        m3284a(4, 4, 4, 4, 4);
    }

    /* JADX INFO: renamed from: T */
    public void m3278T() {
        m3284a(4, 0, 4, 0, 4);
        m3280V();
    }

    /* JADX INFO: renamed from: U */
    public void m3279U() {
        m3284a(4, 0, 4, 4, 0);
        m3280V();
    }

    /* JADX INFO: renamed from: V */
    public void m3280V() {
        if (this.f3452g == 3) {
            this.f3458m.setVisibility(0);
            this.f3458m.setImageResource(this.f3486ai);
            return;
        }
        if (this.f3452g == 7) {
            this.f3458m.setVisibility(4);
            return;
        }
        if (this.f3452g != 6) {
            this.f3458m.setImageResource(this.f3485ah);
        } else if (this.f3446S != 0) {
            this.f3458m.setVisibility(4);
        } else {
            this.f3458m.setVisibility(0);
            this.f3458m.setImageResource(this.f3487aj);
        }
    }

    /* JADX INFO: renamed from: W */
    public void m3281W() {
        m3282X();
        this.f3488ak = new Timer();
        this.f3489al = new C1602a();
        this.f3488ak.schedule(this.f3489al, 2000L);
    }

    /* JADX INFO: renamed from: X */
    public void m3282X() {
        if (this.f3488ak != null) {
            this.f3488ak.cancel();
        }
        if (this.f3489al != null) {
            this.f3489al.cancel();
        }
    }

    /* JADX INFO: renamed from: Y */
    public void m3283Y() {
        if (this.f3452g == 0 || this.f3452g == 7 || this.f3452g == 6 || getContext() == null || !(getContext() instanceof Activity)) {
            return;
        }
        ((Activity) getContext()).runOnUiThread(new RunnableC1621s(this));
    }

    /* JADX INFO: renamed from: a */
    public void m3284a(int i, int i2, int i3, int i4, int i5) {
        this.f3464s.setVisibility(i);
        this.f3458m.setVisibility(i2);
        this.f3475U.setVisibility(i3);
        this.f3476V.setVisibility(i4);
        this.f3478aa.setVisibility(i5);
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: a */
    public void mo3231a(int i, long j) {
        super.mo3231a(i, j);
        this.f3475U.setVisibility(0);
        this.f3458m.setVisibility(4);
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: a */
    public void mo3233a(Context context) {
        super.mo3233a(context);
        mo3242b(context);
        this.f3476V.setOnClickListener(this);
        this.f3477W.setOnClickListener(this);
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: a */
    public void mo3238a(C1603a c1603a, int i, Object... objArr) {
        super.mo3238a(c1603a, i, objArr);
        if (this.f3453h == 1) {
            this.f3459n.setImageResource(this.f3483af);
            m3285e(C1616n.m3309a(getContext(), 62.0f));
        } else if (this.f3453h == 0) {
            this.f3459n.setImageResource(this.f3484ag);
            m3285e(C1616n.m3309a(getContext(), 45.0f));
        }
        if (this.f3454i == 0) {
            this.f3460o.setImageResource(this.f3430C);
        } else if (this.f3454i == 1) {
            this.f3460o.setImageResource(this.f3429B);
        }
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: b */
    public void mo3242b(Context context) {
        super.mo3242b(context);
        this.f3479ab = C1591e.m3181a(context, "id", "ifly_ad_loading_progress");
        this.f3480ac = C1591e.m3181a(context, "id", "ifly_ad_thumb");
        this.f3481ad = C1591e.m3181a(context, "id", "ifly_ad_retry_btn");
        this.f3482ae = C1591e.m3181a(context, "id", "ifly_ad_layout_retry");
        this.f3483af = C1591e.m3181a(context, "drawable", "ifly_ad_jz_shrink");
        this.f3484ag = C1591e.m3181a(context, "drawable", "ifly_ad_jz_enlarge");
        this.f3485ah = C1591e.m3181a(context, "drawable", "ifly_ad_jz_click_play_selector");
        this.f3486ai = C1591e.m3181a(context, "drawable", "ifly_ad_jz_click_pause_selector");
        this.f3487aj = C1591e.m3181a(context, "drawable", "ifly_ad_jz_click_replay_selector");
        this.f3475U = (ProgressBar) findViewById(this.f3479ab);
        this.f3476V = (ImageView) findViewById(this.f3480ac);
        this.f3477W = (TextView) findViewById(this.f3481ad);
        this.f3478aa = (LinearLayout) findViewById(this.f3482ae);
    }

    /* JADX INFO: renamed from: e */
    public void m3285e(int i) {
        ViewGroup.LayoutParams layoutParams = this.f3458m.getLayoutParams();
        layoutParams.height = i;
        layoutParams.width = i;
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: g */
    public int mo3250g() {
        return C1591e.m3181a(getContext(), "layout", "ifly_ad_jz_layout_standard");
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: m */
    public void mo3256m() {
        super.mo3256m();
        m3272N();
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: n */
    public void mo3257n() {
        super.mo3257n();
        m3273O();
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        int id = view.getId();
        if (id == this.f3480ac) {
            if (this.f3450e == null || this.f3450e.f3493c.isEmpty() || this.f3450e.m3286a() == null) {
                C1593g.m3188a("JZVideoPlayer", "播放地址无效");
                return;
            }
            if (this.f3452g == 0) {
                if (this.f3450e.m3286a().toString().startsWith("file") || this.f3450e.m3286a().toString().startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) || C1597k.m3208b(getContext()) || !this.f3447T) {
                    m3254k();
                    return;
                } else {
                    mo3226L();
                    return;
                }
            }
            if (this.f3452g == 6) {
                m3254k();
                if (this.f3443P.f3355k) {
                    return;
                }
                C1596j.m3203a(this.f3442O.f3339s);
                this.f3443P.f3355k = true;
                return;
            }
            return;
        }
        if (id == this.f3471z) {
            if (this.f3446S != 0) {
                if (this.f3444Q != null) {
                    this.f3444Q.onAdClick();
                    return;
                }
                return;
            } else if (!this.f3456k) {
                m3281W();
                m3271M();
                return;
            } else {
                if (this.f3444Q != null) {
                    this.f3444Q.onAdClick();
                    return;
                }
                return;
            }
        }
        if (id == this.f3481ad) {
            if (!this.f3450e.m3286a().toString().startsWith("file") && !this.f3450e.m3286a().toString().startsWith(InternalZipConstants.ZIP_FILE_SEPARATOR) && !C1597k.m3208b(getContext()) && this.f3447T) {
                mo3226L();
                return;
            }
            m3265v();
            m3266w();
            TextureViewSurfaceTextureListenerC1605c.m3300a(this.f3450e);
            mo3257n();
        }
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        super.onStartTrackingTouch(seekBar);
        m3282X();
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer, android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        super.onStopTrackingTouch(seekBar);
        if (this.f3452g == 3) {
            m3283Y();
        } else if (this.f3446S == 0) {
            m3281W();
        }
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer, android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (view.getId() == this.f3469x) {
            switch (motionEvent.getAction()) {
                case 0:
                    m3282X();
                    break;
                case 1:
                    m3281W();
                    break;
            }
        }
        return super.onTouch(view, motionEvent);
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: p */
    public void mo3259p() {
        super.mo3259p();
        m3275Q();
        if (this.f3443P.f3345a) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3329i);
        this.f3443P.f3345a = true;
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: q */
    public void mo3260q() {
        super.mo3260q();
        m3276R();
        m3281W();
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: r */
    public void mo3261r() {
        super.mo3261r();
        m3279U();
        if (this.f3444Q != null) {
            this.f3444Q.onAdPlayError();
        }
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: s */
    public void mo3262s() {
        super.mo3262s();
        m3278T();
        m3282X();
        this.f3460o.setImageResource(this.f3430C);
        if (this.f3443P.f3349e) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3333m);
        this.f3443P.f3349e = true;
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: t */
    public void mo3263t() {
        super.mo3263t();
        m3282X();
    }

    @Override // com.iflytek.voiceads.videolib.JZPlayer
    /* JADX INFO: renamed from: u */
    public void mo3264u() {
        super.mo3264u();
        m3282X();
    }
}

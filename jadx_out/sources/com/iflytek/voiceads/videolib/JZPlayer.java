package com.iflytek.voiceads.videolib;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import com.iflytek.voiceads.listener.IFLYVideoListener;
import com.iflytek.voiceads.p073e.C1564g;
import com.iflytek.voiceads.p073e.C1565h;
import com.iflytek.voiceads.param.C1570a;
import com.iflytek.voiceads.utils.C1591e;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1596j;
import com.iflytek.voiceads.utils.C1597k;
import com.warnship.autoplay.R;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import mobi.oneway.export.p083d.InterfaceC1925e;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public abstract class JZPlayer extends FrameLayout implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {

    /* JADX INFO: renamed from: a */
    public static int f3425a = 1;

    /* JADX INFO: renamed from: b */
    public static int f3426b = 0;

    /* JADX INFO: renamed from: c */
    public static long f3427c = 0;

    /* JADX INFO: renamed from: A */
    public int f3428A;

    /* JADX INFO: renamed from: B */
    public int f3429B;

    /* JADX INFO: renamed from: C */
    public int f3430C;

    /* JADX INFO: renamed from: D */
    public int f3431D;

    /* JADX INFO: renamed from: E */
    public int f3432E;

    /* JADX INFO: renamed from: F */
    public int f3433F;

    /* JADX INFO: renamed from: G */
    public int f3434G;

    /* JADX INFO: renamed from: H */
    protected int f3435H;

    /* JADX INFO: renamed from: I */
    protected int f3436I;

    /* JADX INFO: renamed from: J */
    protected AudioManager f3437J;

    /* JADX INFO: renamed from: K */
    protected Handler f3438K;

    /* JADX INFO: renamed from: L */
    protected ScheduledExecutorService f3439L;

    /* JADX INFO: renamed from: M */
    protected C1601a f3440M;

    /* JADX INFO: renamed from: N */
    protected boolean f3441N;

    /* JADX INFO: renamed from: O */
    public C1564g f3442O;

    /* JADX INFO: renamed from: P */
    public C1565h f3443P;

    /* JADX INFO: renamed from: Q */
    public IFLYVideoListener f3444Q;

    /* JADX INFO: renamed from: R */
    public C1570a f3445R;

    /* JADX INFO: renamed from: S */
    public int f3446S;

    /* JADX INFO: renamed from: T */
    public boolean f3447T;

    /* JADX INFO: renamed from: U */
    private final int f3448U;

    /* JADX INFO: renamed from: d */
    public AudioManager.OnAudioFocusChangeListener f3449d;

    /* JADX INFO: renamed from: e */
    public C1603a f3450e;

    /* JADX INFO: renamed from: f */
    public Object[] f3451f;

    /* JADX INFO: renamed from: g */
    public int f3452g;

    /* JADX INFO: renamed from: h */
    public int f3453h;

    /* JADX INFO: renamed from: i */
    public int f3454i;

    /* JADX INFO: renamed from: j */
    public long f3455j;

    /* JADX INFO: renamed from: k */
    public boolean f3456k;

    /* JADX INFO: renamed from: l */
    public SeekBar f3457l;

    /* JADX INFO: renamed from: m */
    public ImageView f3458m;

    /* JADX INFO: renamed from: n */
    public ImageView f3459n;

    /* JADX INFO: renamed from: o */
    public ImageView f3460o;

    /* JADX INFO: renamed from: p */
    public TextView f3461p;

    /* JADX INFO: renamed from: q */
    public TextView f3462q;

    /* JADX INFO: renamed from: r */
    public ViewGroup f3463r;

    /* JADX INFO: renamed from: s */
    public ViewGroup f3464s;

    /* JADX INFO: renamed from: t */
    public int f3465t;

    /* JADX INFO: renamed from: u */
    public int f3466u;

    /* JADX INFO: renamed from: v */
    public int f3467v;

    /* JADX INFO: renamed from: w */
    public int f3468w;

    /* JADX INFO: renamed from: x */
    public int f3469x;

    /* JADX INFO: renamed from: y */
    public int f3470y;

    /* JADX INFO: renamed from: z */
    public int f3471z;

    /* JADX INFO: renamed from: com.iflytek.voiceads.videolib.JZPlayer$a */
    public class C1601a extends TimerTask {
        public C1601a() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            if (JZPlayer.this.f3452g == 3 || JZPlayer.this.f3452g == 5) {
                JZPlayer.this.f3438K.post(new RunnableC1615m(this));
            }
        }
    }

    public JZPlayer(Context context) {
        super(context);
        this.f3448U = R.drawable.abc_btn_check_to_on_mtrl_015;
        this.f3449d = new C1614l(this);
        this.f3451f = null;
        this.f3452g = -1;
        this.f3453h = -1;
        this.f3454i = 0;
        this.f3455j = 0L;
        this.f3456k = false;
        this.f3431D = 0;
        this.f3432E = 0;
        this.f3433F = -1;
        this.f3434G = 0;
        mo3233a(context);
    }

    public JZPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3448U = R.drawable.abc_btn_check_to_on_mtrl_015;
        this.f3449d = new C1614l(this);
        this.f3451f = null;
        this.f3452g = -1;
        this.f3453h = -1;
        this.f3454i = 0;
        this.f3455j = 0L;
        this.f3456k = false;
        this.f3431D = 0;
        this.f3432E = 0;
        this.f3433F = -1;
        this.f3434G = 0;
        mo3233a(context);
    }

    /* JADX INFO: renamed from: b */
    public static boolean m3213b() {
        if (System.currentTimeMillis() - f3427c < 300) {
            return false;
        }
        if (C1617o.m3315b() != null) {
            f3427c = System.currentTimeMillis();
            if (C1617o.m3313a().f3450e.m3288a(TextureViewSurfaceTextureListenerC1605c.m3302b())) {
                C1617o.m3313a().m3224J();
            } else {
                m3214c();
            }
            return true;
        }
        if (C1617o.m3313a() == null || C1617o.m3313a().f3453h != 1) {
            return false;
        }
        f3427c = System.currentTimeMillis();
        m3214c();
        return false;
    }

    /* JADX INFO: renamed from: c */
    public static void m3214c() {
        C1617o.m3313a().m3269z();
        TextureViewSurfaceTextureListenerC1605c.m3298a().m3307g();
        C1617o.m3318d();
    }

    /* JADX INFO: renamed from: A */
    public void m3215A() {
        C1593g.m3188a("JZVideoPlayer", "onVideoSizeChanged");
        if (TextureViewSurfaceTextureListenerC1605c.f3499a != null) {
            if (this.f3434G != 0) {
                TextureViewSurfaceTextureListenerC1605c.f3499a.setRotation(this.f3434G);
            }
            TextureViewSurfaceTextureListenerC1605c.f3499a.m3270a(TextureViewSurfaceTextureListenerC1605c.m3298a().f3505g, TextureViewSurfaceTextureListenerC1605c.m3298a().f3506h);
        }
    }

    /* JADX INFO: renamed from: B */
    public void m3216B() {
        C1593g.m3188a("JZVideoPlayer", "startProgressTimer");
        m3217C();
        this.f3439L = Executors.newScheduledThreadPool(1);
        this.f3440M = new C1601a();
        this.f3439L.scheduleAtFixedRate(this.f3440M, 0L, 300L, TimeUnit.MILLISECONDS);
    }

    /* JADX INFO: renamed from: C */
    public void m3217C() {
        C1593g.m3188a("JZVideoPlayer", "cancelProgressTimer");
        if (this.f3439L != null) {
            this.f3439L.shutdown();
        }
        if (this.f3440M != null) {
            this.f3440M.cancel();
        }
    }

    /* JADX INFO: renamed from: D */
    public void m3218D() {
        this.f3457l.setProgress(0);
        this.f3457l.setSecondaryProgress(0);
        this.f3461p.setText(C1616n.m3311a(0L));
        this.f3462q.setText(C1616n.m3311a(0L));
    }

    /* JADX INFO: renamed from: E */
    public long m3219E() {
        if (this.f3452g != 3 && this.f3452g != 5) {
            return 0L;
        }
        try {
            return TextureViewSurfaceTextureListenerC1605c.m3303c();
        } catch (Exception e) {
            C1593g.m3190b("JZVideoPlayer", "get position" + e.getMessage());
            return 0L;
        }
    }

    /* JADX INFO: renamed from: F */
    public long m3220F() {
        try {
            return TextureViewSurfaceTextureListenerC1605c.m3304d();
        } catch (Exception e) {
            C1593g.m3190b("JZVideoPlayer", "get dur" + e.getMessage());
            return 0L;
        }
    }

    /* JADX INFO: renamed from: G */
    public void m3221G() {
        try {
            if (this.f3442O.f3323c > this.f3442O.f3324d) {
                C1616n.m3312a(getContext(), 0);
            } else {
                C1616n.m3312a(getContext(), 1);
            }
            Activity activityM3310a = C1616n.m3310a(getContext());
            if (activityM3310a == null) {
                C1593g.m3188a("JZVideoPlayer", "startWindowFullscreen curActivity is null!");
                return;
            }
            ViewGroup viewGroup = (ViewGroup) activityM3310a.findViewById(android.R.id.content);
            View viewFindViewById = viewGroup.findViewById(R.drawable.abc_btn_check_to_on_mtrl_015);
            if (viewFindViewById != null) {
                viewGroup.removeView(viewFindViewById);
            }
            this.f3463r.removeView(TextureViewSurfaceTextureListenerC1605c.f3499a);
            JZPlayer jZPlayer = (JZPlayer) getClass().getConstructor(Context.class).newInstance(getContext());
            jZPlayer.setId(R.drawable.abc_btn_check_to_on_mtrl_015);
            viewGroup.addView(jZPlayer, new FrameLayout.LayoutParams(-1, -1));
            int i = Build.VERSION.SDK_INT;
            int i2 = getContext().getApplicationInfo().targetSdkVersion;
            if (i >= 19 && i2 >= 19) {
                jZPlayer.setSystemUiVisibility(2054);
            } else if (i < 16 || i2 < 16) {
                jZPlayer.setSystemUiVisibility(2);
            } else {
                jZPlayer.setSystemUiVisibility(6);
            }
            jZPlayer.m3234a(this.f3442O);
            jZPlayer.m3237a(this.f3445R);
            jZPlayer.m3228a(this.f3446S);
            jZPlayer.m3236a(this.f3444Q);
            jZPlayer.m3241b(this.f3454i);
            jZPlayer.m3235a(this.f3443P);
            jZPlayer.mo3238a(this.f3450e, 1, this.f3451f);
            jZPlayer.m3244c(this.f3452g);
            jZPlayer.m3266w();
            C1617o.m3316b(jZPlayer);
            mo3256m();
            jZPlayer.f3457l.setSecondaryProgress(this.f3457l.getSecondaryProgress());
            jZPlayer.m3216B();
            f3427c = System.currentTimeMillis();
        } catch (Exception e) {
            C1593g.m3190b("JZVideoPlayer", "window full" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: H */
    public boolean m3222H() {
        return m3223I() && this.f3450e.m3288a(TextureViewSurfaceTextureListenerC1605c.m3302b());
    }

    /* JADX INFO: renamed from: I */
    public boolean m3223I() {
        return C1617o.m3317c() != null && C1617o.m3317c() == this;
    }

    /* JADX INFO: renamed from: J */
    public void m3224J() {
        this.f3454i = C1617o.m3315b().f3454i;
        if (this.f3454i == 0) {
            this.f3460o.setImageResource(this.f3430C);
        } else if (this.f3454i == 1) {
            this.f3460o.setImageResource(this.f3429B);
        }
        this.f3452g = C1617o.m3315b().f3452g;
        m3269z();
        m3244c(this.f3452g);
        m3266w();
    }

    /* JADX INFO: renamed from: K */
    public void m3225K() {
    }

    /* JADX INFO: renamed from: L */
    public void mo3226L() {
    }

    /* JADX INFO: renamed from: a */
    public void m3227a() {
        if (System.currentTimeMillis() - f3427c > 300) {
            C1617o.m3318d();
            TextureViewSurfaceTextureListenerC1605c.m3298a().f3504f = -1;
            TextureViewSurfaceTextureListenerC1605c.m3298a().m3307g();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3228a(int i) {
        this.f3446S = i;
    }

    /* JADX INFO: renamed from: a */
    public void m3229a(int i, int i2) {
        C1593g.m3188a("JZVideoPlayer", "onError");
        if (i == 38 || i2 == -38 || i == -38 || i2 == 38 || i2 == -19) {
            return;
        }
        mo3261r();
        if (m3222H()) {
            TextureViewSurfaceTextureListenerC1605c.m3298a().m3307g();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3230a(int i, int i2, int i3) {
        switch (i) {
            case 0:
                mo3256m();
                break;
            case 1:
                mo3257n();
                break;
            case 2:
                mo3231a(i2, i3);
                break;
            case 3:
                mo3259p();
                break;
            case 5:
                mo3260q();
                break;
            case 6:
                mo3262s();
                break;
            case 7:
                mo3261r();
                break;
        }
    }

    /* JADX INFO: renamed from: a */
    public void mo3231a(int i, long j) {
        C1593g.m3188a("JZVideoPlayer", "onStatePreparingChangingUrl");
        this.f3452g = 2;
        this.f3455j = j;
        this.f3450e.f3492b = i;
        TextureViewSurfaceTextureListenerC1605c.m3300a(this.f3450e);
        TextureViewSurfaceTextureListenerC1605c.m3298a().m3308h();
    }

    /* JADX INFO: renamed from: a */
    public void m3232a(int i, long j, long j2) {
        if (!this.f3441N && i != 0) {
            this.f3457l.setProgress(i);
            if (21 < i && i < 29 && !this.f3443P.f3346b) {
                C1596j.m3203a(this.f3442O.f3330j);
                this.f3443P.f3346b = true;
            }
            if (46 < i && i < 54 && !this.f3443P.f3347c) {
                C1596j.m3203a(this.f3442O.f3331k);
                this.f3443P.f3347c = true;
            }
            if (71 < i && i < 79 && !this.f3443P.f3348d) {
                C1596j.m3203a(this.f3442O.f3332l);
                this.f3443P.f3348d = true;
            }
        }
        if (j != 0) {
            this.f3461p.setText(C1616n.m3311a(j));
        }
        this.f3462q.setText(C1616n.m3311a(j2));
    }

    /* JADX INFO: renamed from: a */
    public void mo3233a(Context context) {
        try {
            View.inflate(context, mo3250g(), this);
            mo3242b(context);
            this.f3458m = (ImageView) findViewById(this.f3465t);
            this.f3459n = (ImageView) findViewById(this.f3466u);
            this.f3460o = (ImageView) findViewById(this.f3467v);
            this.f3457l = (SeekBar) findViewById(this.f3469x);
            this.f3461p = (TextView) findViewById(this.f3468w);
            this.f3462q = (TextView) findViewById(this.f3470y);
            this.f3464s = (ViewGroup) findViewById(this.f3428A);
            this.f3463r = (ViewGroup) findViewById(this.f3471z);
            this.f3458m.setOnClickListener(this);
            this.f3459n.setOnClickListener(this);
            this.f3460o.setOnClickListener(this);
            this.f3457l.setOnSeekBarChangeListener(this);
            this.f3464s.setOnClickListener(this);
            this.f3463r.setOnClickListener(this);
            this.f3463r.setOnTouchListener(this);
            this.f3435H = getContext().getResources().getDisplayMetrics().widthPixels;
            this.f3436I = getContext().getResources().getDisplayMetrics().heightPixels;
            this.f3437J = (AudioManager) getContext().getSystemService("audio");
            this.f3438K = new Handler();
            this.f3443P = new C1565h();
            if (m3222H() && (context instanceof Activity)) {
                f3425a = ((Activity) context).getRequestedOrientation();
            }
        } catch (Exception e) {
            C1593g.m3190b("JZVideoPlayer", "jz init" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3234a(C1564g c1564g) {
        this.f3442O = c1564g;
    }

    /* JADX INFO: renamed from: a */
    public void m3235a(C1565h c1565h) {
        this.f3443P = c1565h;
    }

    /* JADX INFO: renamed from: a */
    public void m3236a(IFLYVideoListener iFLYVideoListener) {
        this.f3444Q = iFLYVideoListener;
    }

    /* JADX INFO: renamed from: a */
    public void m3237a(C1570a c1570a) {
        this.f3445R = c1570a;
    }

    /* JADX INFO: renamed from: a */
    public void mo3238a(C1603a c1603a, int i, Object... objArr) {
        if (this.f3450e == null || c1603a.m3286a() == null || !this.f3450e.m3288a(c1603a.m3286a())) {
            if (m3223I() && c1603a.m3288a(TextureViewSurfaceTextureListenerC1605c.m3302b())) {
                TextureViewSurfaceTextureListenerC1605c.m3298a().m3307g();
            }
            this.f3453h = i;
            this.f3450e = c1603a;
            this.f3451f = objArr;
            this.f3450e.f3497g = objArr;
            mo3256m();
        }
    }

    /* JADX INFO: renamed from: a */
    public void m3239a(String str, String str2, int i, Object... objArr) {
        mo3238a(new C1603a(str, str2), i, objArr);
    }

    /* JADX INFO: renamed from: a */
    public void m3240a(boolean z) {
        this.f3447T = z;
    }

    /* JADX INFO: renamed from: b */
    public void m3241b(int i) {
        this.f3454i = i;
    }

    /* JADX INFO: renamed from: b */
    public void mo3242b(Context context) {
        this.f3465t = C1591e.m3181a(context, "id", "ifly_ad_play_state_btn");
        this.f3466u = C1591e.m3181a(context, "id", "ifly_ad_fullscreen_btn");
        this.f3467v = C1591e.m3181a(context, "id", "ifly_ad_volume_btn");
        this.f3468w = C1591e.m3181a(context, "id", "ifly_ad_cur_time");
        this.f3469x = C1591e.m3181a(context, "id", "ifly_ad_bottom_seek_bar");
        this.f3470y = C1591e.m3181a(context, "id", "ifly_ad_total_time");
        this.f3471z = C1591e.m3181a(context, "id", "ifly_ad_surface_container");
        this.f3428A = C1591e.m3181a(context, "id", "ifly_ad_layout_bottom");
        this.f3429B = C1591e.m3181a(context, "drawable", "ifly_ad_jz_open_volume");
        this.f3430C = C1591e.m3181a(context, "drawable", "ifly_ad_jz_close_volume");
    }

    /* JADX INFO: renamed from: b */
    public void m3243b(boolean z) {
        TextureViewSurfaceTextureListenerC1605c.m3301a(z);
        if (z) {
            this.f3454i = 0;
            if (this.f3443P.f3353i) {
                return;
            }
            C1596j.m3203a(this.f3442O.f3337q);
            this.f3443P.f3353i = true;
            return;
        }
        this.f3454i = 1;
        if (this.f3443P.f3354j) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3338r);
        this.f3443P.f3354j = true;
    }

    /* JADX INFO: renamed from: c */
    public void m3244c(int i) {
        m3230a(i, 0, 0);
    }

    /* JADX INFO: renamed from: c */
    public void m3245c(boolean z) {
        this.f3456k = z;
    }

    /* JADX INFO: renamed from: d */
    public void m3246d() {
        JZPlayer jZPlayerM3317c = C1617o.m3317c();
        if (jZPlayerM3317c == null || jZPlayerM3317c.f3452g != 5) {
            return;
        }
        jZPlayerM3317c.mo3259p();
        TextureViewSurfaceTextureListenerC1605c.m3306f();
        if (this.f3443P.f3351g || this.f3442O == null) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3335o);
        this.f3443P.f3351g = true;
    }

    /* JADX INFO: renamed from: d */
    public void m3247d(int i) {
        if (i != 0) {
            this.f3457l.setSecondaryProgress(i);
        }
    }

    /* JADX INFO: renamed from: e */
    public void m3248e() {
        JZPlayer jZPlayerM3317c = C1617o.m3317c();
        if (jZPlayerM3317c == null || jZPlayerM3317c.f3452g == 6 || jZPlayerM3317c.f3452g == 0 || jZPlayerM3317c.f3452g == 7) {
            return;
        }
        jZPlayerM3317c.mo3260q();
        TextureViewSurfaceTextureListenerC1605c.m3305e();
        if (this.f3443P.f3350f || this.f3442O == null) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3334n);
        this.f3443P.f3350f = true;
    }

    /* JADX INFO: renamed from: f */
    public void m3249f() {
        JZPlayer jZPlayerM3317c = C1617o.m3317c();
        if (jZPlayerM3317c != null) {
            jZPlayerM3317c.m3225K();
            m3227a();
            if (this.f3443P.f3356l) {
                return;
            }
            C1596j.m3203a(this.f3442O.f3340t);
            this.f3443P.f3356l = true;
        }
    }

    /* JADX INFO: renamed from: g */
    public abstract int mo3250g();

    /* JADX INFO: renamed from: h */
    public void m3251h() {
        m3213b();
        if (this.f3443P.f3352h) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3336p);
        this.f3443P.f3352h = true;
    }

    /* JADX INFO: renamed from: i */
    public void m3252i() {
        if (this.f3443P.f3359o) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3343w);
        this.f3443P.f3359o = true;
    }

    /* JADX INFO: renamed from: j */
    public void m3253j() {
        if (this.f3443P.f3360p) {
            return;
        }
        C1596j.m3203a(this.f3442O.f3344x);
        this.f3443P.f3360p = true;
    }

    /* JADX INFO: renamed from: k */
    public void m3254k() {
        C1617o.m3318d();
        m3265v();
        m3266w();
        ((AudioManager) getContext().getSystemService("audio")).requestAudioFocus(this.f3449d, 3, 2);
        Activity activityM3310a = C1616n.m3310a(getContext());
        if (activityM3310a != null) {
            activityM3310a.getWindow().addFlags(128);
        }
        TextureViewSurfaceTextureListenerC1605c.m3300a(this.f3450e);
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3504f = this.f3433F;
        mo3257n();
        C1617o.m3314a(this);
    }

    /* JADX INFO: renamed from: l */
    public void m3255l() {
        m3258o();
        mo3259p();
        if (this.f3444Q != null) {
            this.f3444Q.onVideoStart();
        }
    }

    /* JADX INFO: renamed from: m */
    public void mo3256m() {
        C1593g.m3188a("JZVideoPlayer", "onStateNormal");
        this.f3452g = 0;
        m3217C();
    }

    /* JADX INFO: renamed from: n */
    public void mo3257n() {
        C1593g.m3188a("JZVideoPlayer", "onStatePreparing");
        this.f3452g = 1;
        m3218D();
    }

    /* JADX INFO: renamed from: o */
    public void m3258o() {
        C1593g.m3188a("JZVideoPlayer", "onStatePrepared");
        if (this.f3455j == 0) {
            TextureViewSurfaceTextureListenerC1605c.m3299a(0L);
        } else {
            TextureViewSurfaceTextureListenerC1605c.m3299a(this.f3455j);
            this.f3455j = 0L;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id != this.f3465t) {
            if (id == this.f3466u) {
                if (this.f3452g != 6) {
                    if (this.f3453h == 1) {
                        m3213b();
                        if (this.f3443P.f3358n) {
                            return;
                        }
                        C1596j.m3203a(this.f3442O.f3342v);
                        this.f3443P.f3358n = true;
                        return;
                    }
                    m3221G();
                    if (this.f3443P.f3357m) {
                        return;
                    }
                    C1596j.m3203a(this.f3442O.f3341u);
                    this.f3443P.f3357m = true;
                    return;
                }
                return;
            }
            if (id == this.f3467v) {
                if (this.f3454i == 0) {
                    TextureViewSurfaceTextureListenerC1605c.m3301a(false);
                    this.f3454i = 1;
                    this.f3460o.setImageResource(this.f3429B);
                    if (this.f3443P.f3354j) {
                        return;
                    }
                    C1596j.m3203a(this.f3442O.f3338r);
                    this.f3443P.f3354j = true;
                    return;
                }
                if (this.f3454i == 1) {
                    TextureViewSurfaceTextureListenerC1605c.m3301a(true);
                    this.f3454i = 0;
                    this.f3460o.setImageResource(this.f3430C);
                    if (this.f3443P.f3353i) {
                        return;
                    }
                    C1596j.m3203a(this.f3442O.f3337q);
                    this.f3443P.f3353i = true;
                    return;
                }
                return;
            }
            return;
        }
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
        if (this.f3452g == 3) {
            TextureViewSurfaceTextureListenerC1605c.m3305e();
            mo3260q();
            if (this.f3443P.f3350f) {
                return;
            }
            C1596j.m3203a(this.f3442O.f3334n);
            this.f3443P.f3350f = true;
            return;
        }
        if (this.f3452g == 5) {
            TextureViewSurfaceTextureListenerC1605c.m3306f();
            mo3259p();
            if (this.f3443P.f3351g) {
                return;
            }
            C1596j.m3203a(this.f3442O.f3335o);
            this.f3443P.f3351g = true;
            return;
        }
        if (this.f3452g == 6) {
            m3254k();
            if (!this.f3443P.f3355k) {
                C1596j.m3203a(this.f3442O.f3339s);
                this.f3443P.f3355k = true;
            }
            if (this.f3444Q != null) {
                this.f3444Q.onVideoReplay();
            }
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        if (this.f3453h == 1) {
            super.onMeasure(i, i2);
            return;
        }
        if (this.f3431D == 0 || this.f3432E == 0) {
            super.onMeasure(i, i2);
            return;
        }
        int size = View.MeasureSpec.getSize(i);
        int i3 = (int) ((size * this.f3432E) / this.f3431D);
        setMeasuredDimension(size, i3);
        getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec(size, InterfaceC1925e.f4896e), View.MeasureSpec.makeMeasureSpec(i3, InterfaceC1925e.f4896e));
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        m3217C();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(true);
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        m3216B();
        for (ViewParent parent = getParent(); parent != null; parent = parent.getParent()) {
            parent.requestDisallowInterceptTouchEvent(false);
        }
        if (this.f3452g == 3 || this.f3452g == 5) {
            TextureViewSurfaceTextureListenerC1605c.m3299a((((long) seekBar.getProgress()) * m3220F()) / 100);
        }
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    /* JADX INFO: renamed from: p */
    public void mo3259p() {
        C1593g.m3188a("JZVideoPlayer", "onStatePlaying");
        this.f3452g = 3;
        m3216B();
    }

    /* JADX INFO: renamed from: q */
    public void mo3260q() {
        C1593g.m3188a("JZVideoPlayer", "onStatePause");
        this.f3452g = 5;
        m3216B();
    }

    /* JADX INFO: renamed from: r */
    public void mo3261r() {
        C1593g.m3188a("JZVideoPlayer", "onStateError");
        this.f3452g = 7;
        m3217C();
    }

    /* JADX INFO: renamed from: s */
    public void mo3262s() {
        C1593g.m3188a("JZVideoPlayer", "onStateAutoComplete");
        this.f3452g = 6;
        m3217C();
        this.f3457l.setProgress(100);
        this.f3461p.setText(this.f3462q.getText());
    }

    /* JADX INFO: renamed from: t */
    public void mo3263t() {
        C1593g.m3188a("JZVideoPlayer", "onAutoCompletion");
        Runtime.getRuntime().gc();
        mo3262s();
        if (this.f3453h == 1) {
            m3213b();
        }
        TextureViewSurfaceTextureListenerC1605c.m3298a().m3307g();
        Activity activityM3310a = C1616n.m3310a(getContext());
        if (activityM3310a != null) {
            activityM3310a.getWindow().clearFlags(128);
        }
        if (this.f3444Q != null) {
            this.f3444Q.onVideoComplete();
        }
    }

    /* JADX INFO: renamed from: u */
    public void mo3264u() {
        C1593g.m3188a("JZVideoPlayer", "onCompletion");
        m3217C();
        mo3256m();
        this.f3463r.removeView(TextureViewSurfaceTextureListenerC1605c.f3499a);
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3505g = 0;
        TextureViewSurfaceTextureListenerC1605c.m3298a().f3506h = 0;
        ((AudioManager) getContext().getSystemService("audio")).abandonAudioFocus(this.f3449d);
        Activity activityM3310a = C1616n.m3310a(getContext());
        if (activityM3310a != null) {
            activityM3310a.getWindow().clearFlags(128);
        }
        m3268y();
        C1616n.m3312a(getContext(), f3425a);
        if (TextureViewSurfaceTextureListenerC1605c.f3502d != null) {
            TextureViewSurfaceTextureListenerC1605c.f3502d.release();
        }
        if (TextureViewSurfaceTextureListenerC1605c.f3501c != null) {
            TextureViewSurfaceTextureListenerC1605c.f3501c.release();
        }
        TextureViewSurfaceTextureListenerC1605c.f3499a = null;
        TextureViewSurfaceTextureListenerC1605c.f3501c = null;
    }

    /* JADX INFO: renamed from: v */
    public void m3265v() {
        C1593g.m3188a("JZVideoPlayer", "initTextureView");
        m3267x();
        TextureViewSurfaceTextureListenerC1605c.f3499a = new JZTextureView(getContext());
        TextureViewSurfaceTextureListenerC1605c.f3499a.setSurfaceTextureListener(TextureViewSurfaceTextureListenerC1605c.m3298a());
    }

    /* JADX INFO: renamed from: w */
    public void m3266w() {
        C1593g.m3188a("JZVideoPlayer", "addTextureView");
        this.f3463r.addView(TextureViewSurfaceTextureListenerC1605c.f3499a, new FrameLayout.LayoutParams(-1, -1, 17));
    }

    /* JADX INFO: renamed from: x */
    public void m3267x() {
        C1593g.m3188a("JZVideoPlayer", "removeTextureView");
        TextureViewSurfaceTextureListenerC1605c.f3501c = null;
        if (TextureViewSurfaceTextureListenerC1605c.f3499a == null || TextureViewSurfaceTextureListenerC1605c.f3499a.getParent() == null) {
            return;
        }
        ((ViewGroup) TextureViewSurfaceTextureListenerC1605c.f3499a.getParent()).removeView(TextureViewSurfaceTextureListenerC1605c.f3499a);
    }

    /* JADX INFO: renamed from: y */
    public void m3268y() {
        ViewGroup viewGroup;
        View viewFindViewById;
        C1593g.m3188a("JZVideoPlayer", "clearFullscreenLayout");
        Activity activityM3310a = C1616n.m3310a(getContext());
        if (activityM3310a == null || (viewFindViewById = (viewGroup = (ViewGroup) activityM3310a.findViewById(android.R.id.content)).findViewById(R.drawable.abc_btn_check_to_on_mtrl_015)) == null) {
            return;
        }
        viewGroup.removeView(viewFindViewById);
    }

    /* JADX INFO: renamed from: z */
    public void m3269z() {
        C1593g.m3188a("JZVideoPlayer", "clearFloatScreen");
        C1616n.m3312a(getContext(), f3425a);
        JZPlayer jZPlayerM3317c = C1617o.m3317c();
        jZPlayerM3317c.f3463r.removeView(TextureViewSurfaceTextureListenerC1605c.f3499a);
        Activity activityM3310a = C1616n.m3310a(getContext());
        if (activityM3310a != null) {
            ((ViewGroup) activityM3310a.findViewById(android.R.id.content)).removeView(jZPlayerM3317c);
        }
        C1617o.m3316b(null);
    }
}

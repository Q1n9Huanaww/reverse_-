package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.cyjh.mobileanjian.ipc.share.proto.UiMessage;
import com.cyjh.mqsdk.C0852R;
import com.cyjh.p019mq.sdk.MqRunner;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.j */
/* JADX INFO: compiled from: UiShowFloat.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0789j extends RelativeLayout {

    /* JADX INFO: renamed from: a */
    WindowManager f1282a;

    /* JADX INFO: renamed from: b */
    WindowManager.LayoutParams f1283b;

    /* JADX INFO: renamed from: c */
    RelativeLayout f1284c;

    /* JADX INFO: renamed from: d */
    boolean f1285d;

    /* JADX INFO: renamed from: e */
    boolean f1286e;

    /* JADX INFO: renamed from: f */
    int f1287f;

    /* JADX INFO: renamed from: g */
    float f1288g;

    /* JADX INFO: renamed from: h */
    int f1289h;

    /* JADX INFO: renamed from: i */
    String f1290i;

    /* JADX INFO: renamed from: j */
    private Context f1291j;

    /* JADX INFO: renamed from: k */
    private C0786g f1292k;

    /* JADX INFO: renamed from: l */
    private Handler f1293l;

    public C0789j(Context context, String str, int i, int i2, int i3, int i4) {
        super(context);
        this.f1285d = false;
        this.f1286e = false;
        this.f1287f = Color.parseColor("#FFFFFF");
        this.f1288g = 0.0f;
        this.f1289h = 100;
        this.f1290i = "";
        this.f1293l = new Handler();
        this.f1291j = context;
        this.f1292k = C0786g.m851a(context);
        this.f1282a = (WindowManager) this.f1291j.getSystemService("window");
        this.f1283b = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1283b.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1283b.type = 2005;
        } else {
            this.f1283b.type = 2002;
        }
        this.f1283b.format = 1;
        this.f1283b.flags = 1320;
        this.f1283b.systemUiVisibility = 2;
        this.f1283b.gravity = 51;
        this.f1283b.x = i;
        this.f1283b.y = i2;
        this.f1283b.alpha = 1.0f;
        this.f1291j.getResources().getDisplayMetrics();
        this.f1284c = (RelativeLayout) LayoutInflater.from(this.f1291j).inflate(C0852R.layout.ui_show_float, (ViewGroup) null);
        this.f1283b.width = i3;
        this.f1283b.height = i4;
        setTag(str);
    }

    /* JADX INFO: renamed from: a */
    private void m965a(int i, int i2) {
        this.f1282a = (WindowManager) this.f1291j.getSystemService("window");
        this.f1283b = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1283b.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1283b.type = 2005;
        } else {
            this.f1283b.type = 2002;
        }
        this.f1283b.format = 1;
        this.f1283b.flags = 1320;
        this.f1283b.systemUiVisibility = 2;
        this.f1283b.gravity = 51;
        this.f1283b.x = i;
        this.f1283b.y = i2;
        this.f1283b.alpha = 1.0f;
    }

    /* JADX INFO: renamed from: b */
    private void m969b(int i, int i2) {
        this.f1291j.getResources().getDisplayMetrics();
        this.f1284c = (RelativeLayout) LayoutInflater.from(this.f1291j).inflate(C0852R.layout.ui_show_float, (ViewGroup) null);
        this.f1283b.width = i;
        this.f1283b.height = i2;
    }

    /* JADX INFO: renamed from: c */
    private int m972c(int i) {
        return (int) (i * (this.f1291j.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* JADX INFO: renamed from: a */
    public final void m978a() {
        String str = (String) getTag();
        if (this.f1290i != null && this.f1290i.length() > 0) {
            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.f1290i);
            if (Build.VERSION.SDK_INT >= 16) {
                this.f1284c.setBackground(new BitmapDrawable(bitmapDecodeFile));
            }
        } else {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadii(new float[]{this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g});
            try {
                gradientDrawable.setColor(this.f1287f);
            } catch (Exception unused) {
            }
            if (Build.VERSION.SDK_INT >= 16) {
                this.f1284c.setBackground(gradientDrawable);
            }
        }
        this.f1285d = true;
        this.f1284c.setOnTouchListener(new AnonymousClass1(str));
        this.f1282a.addView(this.f1284c, this.f1283b);
        this.f1284c.getBackground().setAlpha((this.f1289h * 255) / 100);
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.j$1, reason: invalid class name */
    /* JADX INFO: compiled from: UiShowFloat.java */
    final class AnonymousClass1 implements View.OnTouchListener {

        /* JADX INFO: renamed from: a */
        int f1294a;

        /* JADX INFO: renamed from: b */
        int f1295b;

        /* JADX INFO: renamed from: c */
        int f1296c;

        /* JADX INFO: renamed from: d */
        int f1297d;

        /* JADX INFO: renamed from: e */
        long f1298e;

        /* JADX INFO: renamed from: f */
        long f1299f;

        /* JADX INFO: renamed from: g */
        Runnable f1300g = new Runnable() { // from class: com.cyjh.mobileanjian.ipc.ui.j.1.1
            @Override // java.lang.Runnable
            public final void run() {
                C0789j.m966a(AnonymousClass1.this.f1301h, UiMessage.ControlEvent.Event_Type.ON_TOUCH);
            }
        };

        /* JADX INFO: renamed from: h */
        final /* synthetic */ String f1301h;

        AnonymousClass1(String str) {
            this.f1301h = str;
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case 0:
                    this.f1294a = (int) motionEvent.getRawX();
                    this.f1295b = (int) motionEvent.getRawY();
                    this.f1296c = C0789j.this.f1283b.x;
                    this.f1297d = C0789j.this.f1283b.y;
                    this.f1298e = System.currentTimeMillis();
                    this.f1299f = this.f1298e;
                    C0789j.m966a(this.f1301h, UiMessage.ControlEvent.Event_Type.ON_TOUCH_DOWN);
                    new StringBuilder("ACTION_DOWN ").append(System.currentTimeMillis());
                    C0789j.this.f1293l.postDelayed(this.f1300g, 501L);
                    break;
                case 1:
                    if (this.f1300g != null) {
                        C0789j.this.f1293l.removeCallbacks(this.f1300g);
                    }
                    new StringBuilder("ACTION_UP ").append(System.currentTimeMillis());
                    if (Math.abs(motionEvent.getRawX() - this.f1294a) < 30.0f && Math.abs(motionEvent.getRawY() - this.f1295b) < 30.0f && System.currentTimeMillis() - this.f1298e < 500) {
                        C0789j.m966a(this.f1301h, UiMessage.ControlEvent.Event_Type.ON_CLICK);
                    } else {
                        C0789j.m966a(this.f1301h, UiMessage.ControlEvent.Event_Type.ON_TOUCH_UP);
                    }
                    break;
                case 2:
                    new StringBuilder("ACTION_MOVE ").append(System.currentTimeMillis());
                    if (C0789j.this.f1286e) {
                        int rawX = ((int) motionEvent.getRawX()) - this.f1294a;
                        int rawY = ((int) motionEvent.getRawY()) - this.f1295b;
                        C0789j.this.f1283b.x = this.f1296c + rawX;
                        C0789j.this.f1283b.y = this.f1297d + rawY;
                        C0789j.this.f1282a.updateViewLayout(C0789j.this.f1284c, C0789j.this.f1283b);
                    }
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if ((Math.abs(motionEvent.getRawX() - this.f1294a) != 5.0f || Math.abs(motionEvent.getRawY() - this.f1295b) != 5.0f) && jCurrentTimeMillis - this.f1299f > 50) {
                        C0789j.m966a(this.f1301h, UiMessage.ControlEvent.Event_Type.ON_TOUCH_MOVE);
                        this.f1299f = jCurrentTimeMillis;
                    }
                    break;
            }
            return true;
        }
    }

    /* JADX INFO: renamed from: b */
    public final void m981b() {
        this.f1285d = false;
        try {
            this.f1282a.removeView(this.f1284c);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: renamed from: d */
    public final boolean m984d() {
        return this.f1285d;
    }

    /* JADX INFO: renamed from: a */
    private void m967a(boolean z) {
        this.f1286e = z;
    }

    public final void setOpacity(int i) {
        this.f1284c.getBackground().setAlpha((i * 255) / 100);
        this.f1289h = i;
    }

    public final int getOpacity() {
        return this.f1289h;
    }

    /* JADX INFO: renamed from: a */
    public final void m979a(int i) {
        this.f1288g = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g});
        try {
            gradientDrawable.setColor(this.f1287f);
        } catch (Exception unused) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1284c.setBackground(gradientDrawable);
        }
        this.f1284c.getBackground().setAlpha((this.f1289h * 255) / 100);
    }

    /* JADX INFO: renamed from: b */
    public final void m982b(int i) {
        this.f1287f = i;
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadii(new float[]{this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g, this.f1288g});
        try {
            gradientDrawable.setColor(this.f1287f);
        } catch (Exception unused) {
        }
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1284c.setBackground(gradientDrawable);
        }
        this.f1284c.getBackground().setAlpha((this.f1289h * 255) / 100);
    }

    /* JADX INFO: renamed from: b */
    private void m970b(String str) {
        this.f1290i = str;
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(this.f1290i);
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1284c.setBackground(new BitmapDrawable(bitmapDecodeFile));
        }
        this.f1284c.getBackground().setAlpha((this.f1289h * 255) / 100);
    }

    /* JADX INFO: renamed from: a */
    public final void m980a(View view) {
        final String str = (String) view.getTag();
        if (!(view instanceof Button)) {
            view.setOnTouchListener(new View.OnTouchListener() { // from class: com.cyjh.mobileanjian.ipc.ui.j.2

                /* JADX INFO: renamed from: a */
                int f1304a;

                /* JADX INFO: renamed from: b */
                int f1305b;

                /* JADX INFO: renamed from: c */
                int f1306c;

                /* JADX INFO: renamed from: d */
                int f1307d;

                /* JADX INFO: renamed from: e */
                long f1308e;

                /* JADX INFO: renamed from: f */
                long f1309f;

                /* JADX INFO: renamed from: g */
                Runnable f1310g = new Runnable() { // from class: com.cyjh.mobileanjian.ipc.ui.j.2.1
                    @Override // java.lang.Runnable
                    public final void run() {
                        C0789j.m966a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH);
                    }
                };

                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view2, MotionEvent motionEvent) {
                    switch (motionEvent.getAction()) {
                        case 0:
                            this.f1304a = (int) motionEvent.getRawX();
                            this.f1305b = (int) motionEvent.getRawY();
                            this.f1306c = C0789j.this.f1283b.x;
                            this.f1307d = C0789j.this.f1283b.y;
                            this.f1308e = System.currentTimeMillis();
                            this.f1309f = this.f1308e;
                            C0789j.m966a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH_DOWN);
                            new StringBuilder("ACTION_DOWN ").append(System.currentTimeMillis());
                            C0789j.this.f1293l.postDelayed(this.f1310g, 501L);
                            break;
                        case 1:
                            C0789j.this.f1293l.removeCallbacks(this.f1310g);
                            new StringBuilder("ACTION_UP ").append(System.currentTimeMillis());
                            if (Math.abs(motionEvent.getRawX() - this.f1304a) < 30.0f && Math.abs(motionEvent.getRawY() - this.f1305b) < 30.0f && System.currentTimeMillis() - this.f1308e < 500) {
                                C0789j.m966a(str, UiMessage.ControlEvent.Event_Type.ON_CLICK);
                            } else {
                                C0789j.m966a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH_UP);
                            }
                            break;
                        case 2:
                            new StringBuilder("ACTION_MOVE ").append(System.currentTimeMillis());
                            if (C0789j.this.f1286e) {
                                int rawX = ((int) motionEvent.getRawX()) - this.f1304a;
                                int rawY = ((int) motionEvent.getRawY()) - this.f1305b;
                                C0789j.this.f1283b.x = this.f1306c + rawX;
                                C0789j.this.f1283b.y = this.f1307d + rawY;
                                C0789j.this.f1282a.updateViewLayout(C0789j.this.f1284c, C0789j.this.f1283b);
                            }
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            if (jCurrentTimeMillis - this.f1308e > 501) {
                                C0789j.m966a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH);
                            }
                            if ((Math.abs(motionEvent.getRawX() - this.f1304a) != 5.0f || Math.abs(motionEvent.getRawY() - this.f1305b) != 5.0f) && jCurrentTimeMillis - this.f1309f > 50) {
                                C0789j.m966a(str, UiMessage.ControlEvent.Event_Type.ON_TOUCH_MOVE);
                                this.f1309f = jCurrentTimeMillis;
                            }
                            break;
                    }
                    return true;
                }
            });
        }
        this.f1284c.addView(view);
    }

    /* JADX INFO: renamed from: a */
    public final View m977a(String str) {
        return this.f1284c.findViewWithTag(str);
    }

    /* JADX INFO: renamed from: f */
    private boolean m975f() {
        return this.f1286e;
    }

    /* JADX INFO: renamed from: b */
    private static void m971b(String str, UiMessage.ControlEvent.Event_Type event_Type) {
        MqRunner.getInstance().mo1309c(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(event_Type).build()).setIsSuccess(true).build().toByteString());
    }

    public final WindowManager.LayoutParams getParams() {
        return this.f1283b;
    }

    /* JADX INFO: renamed from: e */
    public final void m985e() {
        if (this.f1284c == null || !this.f1285d) {
            return;
        }
        this.f1282a.updateViewLayout(this.f1284c, this.f1283b);
    }

    /* JADX INFO: renamed from: g */
    private void m976g() {
        if (this.f1284c == null || !this.f1285d) {
            return;
        }
        this.f1282a.removeView(this.f1284c);
        this.f1282a.addView(this.f1284c, this.f1283b);
    }

    public final void setFloatLeft(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatLeft wLayoutParams[ ");
        sb.append(params.x);
        sb.append(",");
        sb.append(params.y);
        sb.append("]");
        params.x = i;
        if (this.f1285d) {
            this.f1282a.updateViewLayout(this.f1284c, params);
        }
    }

    public final void setFloatTop(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatTop wLayoutParams[ ");
        sb.append(params.x);
        sb.append(",");
        sb.append(params.y);
        sb.append("]");
        params.y = i;
        if (this.f1285d) {
            this.f1282a.updateViewLayout(this.f1284c, params);
        }
    }

    public final void setFloatWidth(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatWidth wLayoutParams[ ");
        sb.append(params.width);
        sb.append(",");
        sb.append(params.height);
        sb.append("]");
        this.f1283b.width = i;
        this.f1283b.height = params.height;
        if (this.f1285d) {
            this.f1282a.updateViewLayout(this.f1284c, this.f1283b);
        }
    }

    public final void setFloatHeight(int i) {
        WindowManager.LayoutParams params = getParams();
        StringBuilder sb = new StringBuilder("setFloatHeight wLayoutParams[ ");
        sb.append(params.width);
        sb.append(",");
        sb.append(params.height);
        sb.append("]");
        this.f1283b.width = params.width;
        this.f1283b.height = i;
        if (this.f1285d) {
            this.f1282a.updateViewLayout(this.f1284c, this.f1283b);
        }
    }

    /* JADX INFO: renamed from: c */
    public final void m983c() {
        if (this.f1285d) {
            m981b();
        }
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m966a(String str, UiMessage.ControlEvent.Event_Type event_Type) {
        MqRunner.getInstance().mo1309c(UiMessage.UiToCommand.newBuilder().setCommand(UiMessage.UiToCommand.Command_Type.EVENT).setEvent(UiMessage.ControlEvent.newBuilder().setControlId(str).setType(event_Type).build()).setIsSuccess(true).build().toByteString());
    }
}

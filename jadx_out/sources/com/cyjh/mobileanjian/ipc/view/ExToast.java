package com.cyjh.mobileanjian.ipc.view;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.widget.ActivityChooserView;
import android.view.WindowManager;
import android.widget.Toast;
import com.cyjh.mqsdk.C0852R;
import com.p024ds.daisi.constant.Constants;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: loaded from: classes.dex */
public class ExToast {
    public static final int LENGTH_LONG = 3500;
    public static final int LENGTH_SHORT = 2000;

    /* JADX INFO: renamed from: g */
    private static final int f1452g = 51;

    /* JADX INFO: renamed from: a */
    private WindowManager f1453a;

    /* JADX INFO: renamed from: d */
    private Toast f1456d;

    /* JADX INFO: renamed from: e */
    private boolean f1457e;

    /* JADX INFO: renamed from: f */
    private int f1458f;

    /* JADX INFO: renamed from: h */
    private int f1459h;

    /* JADX INFO: renamed from: k */
    private CharSequence f1462k;

    /* JADX INFO: renamed from: i */
    private int f1460i = -1;

    /* JADX INFO: renamed from: j */
    private int f1461j = -1;

    /* JADX INFO: renamed from: l */
    private int f1463l = LENGTH_SHORT;

    /* JADX INFO: renamed from: m */
    private C0823a f1464m = new C0823a(this, 0);

    /* JADX INFO: renamed from: c */
    private Timer f1455c = new Timer();

    /* JADX INFO: renamed from: b */
    private WindowManager.LayoutParams f1454b = new WindowManager.LayoutParams();

    public ExToast(Context context) {
        this.f1453a = (WindowManager) context.getSystemService("window");
        this.f1456d = Toast.makeText(context, "", 0);
        this.f1454b.height = -2;
        this.f1454b.width = -2;
        this.f1454b.format = -3;
        this.f1454b.windowAnimations = R.style.Animation.Toast;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1454b.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1454b.type = 2005;
        } else {
            this.f1454b.type = 2002;
        }
        this.f1454b.flags = 152;
        int identifier = Resources.getSystem().getIdentifier("config_toastDefaultGravity", "Integer", Constants.ANDROID);
        this.f1458f = identifier == 0 ? 81 : context.getResources().getInteger(identifier);
        int identifier2 = Resources.getSystem().getIdentifier("toast_y_offset", "dimen", Constants.ANDROID);
        this.f1459h = context.getResources().getDimensionPixelSize(identifier2 == 0 ? C0852R.dimen.toast_y_offset : identifier2);
        this.f1454b.setTitle("Toast");
    }

    public void setText(CharSequence charSequence) {
        this.f1462k = charSequence;
        this.f1456d.setText(charSequence);
    }

    public void setDuration(int i) {
        this.f1463l = i;
    }

    public static ExToast makeText(Context context, CharSequence charSequence, int i) {
        ExToast exToast = new ExToast(context);
        exToast.setText(charSequence);
        exToast.setDuration(i);
        return exToast;
    }

    public static ExToast makeText(Context context, int i, int i2) {
        return makeText(context, context.getString(i), i2);
    }

    /* JADX INFO: renamed from: a */
    private void m1130a(Context context) {
        this.f1454b = new WindowManager.LayoutParams();
        this.f1454b.height = -2;
        this.f1454b.width = -2;
        this.f1454b.format = -3;
        this.f1454b.windowAnimations = R.style.Animation.Toast;
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1454b.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1454b.type = 2005;
        } else {
            this.f1454b.type = 2002;
        }
        this.f1454b.flags = 152;
        int identifier = Resources.getSystem().getIdentifier("config_toastDefaultGravity", "Integer", Constants.ANDROID);
        this.f1458f = identifier == 0 ? 81 : context.getResources().getInteger(identifier);
        int identifier2 = Resources.getSystem().getIdentifier("toast_y_offset", "dimen", Constants.ANDROID);
        Resources resources = context.getResources();
        if (identifier2 == 0) {
            identifier2 = C0852R.dimen.toast_y_offset;
        }
        this.f1459h = resources.getDimensionPixelSize(identifier2);
        this.f1454b.setTitle("Toast");
    }

    /* JADX INFO: renamed from: a */
    private void m1129a(int i, CharSequence charSequence, int i2, int i3, boolean z) {
        this.f1454b.gravity = i;
        if (i == this.f1458f) {
            this.f1454b.x = 0;
            this.f1454b.y = this.f1459h;
        } else {
            this.f1454b.x = i2;
            this.f1454b.y = i3;
        }
        setText(charSequence);
        if (!this.f1457e) {
            this.f1453a.addView(this.f1456d.getView(), this.f1454b);
        } else if (z) {
            this.f1453a.updateViewLayout(this.f1456d.getView(), this.f1454b);
        }
    }

    /* JADX WARN: Code duplicated, block: B:18:0x001c  */
    public void show(CharSequence charSequence, int i, int i2, int i3) {
        boolean z;
        synchronized (this.f1456d) {
            byte b = 0;
            if (i < 0) {
                try {
                    if (this.f1460i < 0) {
                        z = true;
                    } else if ((i2 >= 0 && this.f1461j < 0) || (i == this.f1460i && i2 == this.f1461j)) {
                        z = true;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                z = i2 >= 0 ? false : false;
            }
            int i4 = (i < 0 || i2 < 0) ? this.f1458f : 51;
            this.f1460i = i;
            this.f1461j = i2;
            this.f1464m.cancel();
            m1129a(i4, charSequence, i, i2, !z);
            this.f1457e = true;
            this.f1464m = new C0823a(this, b);
            if (i3 < 0) {
                i3 = ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED;
            } else if (i3 >= 0 && i3 < 2000) {
                i3 = LENGTH_SHORT;
            }
            this.f1455c.schedule(this.f1464m, i3);
        }
    }

    public void show() {
        m1129a(this.f1458f, this.f1462k, 0, 0, true);
        this.f1464m = new C0823a(this, (byte) 0);
        this.f1455c.schedule(this.f1464m, 2000L);
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.view.ExToast$a */
    private class C0823a extends TimerTask {
        private C0823a() {
        }

        /* synthetic */ C0823a(ExToast exToast, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            ExToast.this.hide();
        }
    }

    public void hide() {
        synchronized (this.f1456d) {
            this.f1464m.cancel();
            if (this.f1456d.getView().getParent() != null) {
                this.f1453a.removeView(this.f1456d.getView());
            }
            this.f1457e = false;
        }
    }
}

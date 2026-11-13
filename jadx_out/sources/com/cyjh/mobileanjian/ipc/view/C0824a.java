package com.cyjh.mobileanjian.ipc.view;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Timer;
import java.util.TimerTask;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.view.a */
/* JADX INFO: compiled from: ExToast0.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0824a {

    /* JADX INFO: renamed from: a */
    public static final int f1466a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f1467b = 2;

    /* JADX INFO: renamed from: c */
    public static final int f1468c = 4;

    /* JADX INFO: renamed from: d */
    private Toast f1469d;

    /* JADX INFO: renamed from: e */
    private Context f1470e;

    /* JADX INFO: renamed from: i */
    private final int f1474i;

    /* JADX INFO: renamed from: j */
    private final int f1475j;

    /* JADX INFO: renamed from: k */
    private final int f1476k;

    /* JADX INFO: renamed from: l */
    private int f1477l;

    /* JADX INFO: renamed from: m */
    private int f1478m;

    /* JADX INFO: renamed from: n */
    private Object f1479n;

    /* JADX INFO: renamed from: o */
    private Method f1480o;

    /* JADX INFO: renamed from: p */
    private Method f1481p;

    /* JADX INFO: renamed from: q */
    private WindowManager f1482q;

    /* JADX INFO: renamed from: r */
    private WindowManager.LayoutParams f1483r;

    /* JADX INFO: renamed from: t */
    private View f1485t;

    /* JADX INFO: renamed from: f */
    private boolean f1471f = false;

    /* JADX INFO: renamed from: g */
    private int f1472g = -1;

    /* JADX INFO: renamed from: h */
    private int f1473h = 2;

    /* JADX INFO: renamed from: s */
    private WindowManager.LayoutParams f1484s = null;

    /* JADX INFO: renamed from: u */
    private Handler f1486u = new Handler();

    /* JADX INFO: renamed from: v */
    private Timer f1487v = new Timer("hideTimer");

    /* JADX INFO: renamed from: w */
    private a f1488w = new a(this, 0);

    /* JADX INFO: renamed from: x */
    private Runnable f1489x = new Runnable() { // from class: com.cyjh.mobileanjian.ipc.view.a.1
        @Override // java.lang.Runnable
        public final void run() {
            C0824a.this.m1146a();
        }
    };

    private C0824a(Context context) {
        this.f1470e = context;
        Toast toast = new Toast(context);
        this.f1474i = toast.getGravity();
        this.f1475j = toast.getXOffset();
        this.f1476k = toast.getYOffset();
        this.f1477l = this.f1475j;
        this.f1478m = this.f1476k;
    }

    /* JADX INFO: renamed from: a */
    private static C0824a m1132a(Context context, CharSequence charSequence, int i) {
        Toast toastMakeText = Toast.makeText(context, charSequence, i);
        C0824a c0824a = new C0824a(context);
        c0824a.f1469d = toastMakeText;
        c0824a.f1473h = i;
        return c0824a;
    }

    /* JADX INFO: renamed from: a */
    private static C0824a m1131a(Context context, int i, int i2) {
        Toast toastMakeText = Toast.makeText(context, i, i2);
        C0824a c0824a = new C0824a(context);
        c0824a.f1469d = toastMakeText;
        c0824a.f1473h = i2;
        return c0824a;
    }

    /* JADX INFO: renamed from: a */
    private void m1138a(CharSequence charSequence, int i, int i2) {
        byte b = 0;
        Toast.makeText(this.f1470e, charSequence, 0).show();
        boolean z = (i < 0 && this.f1477l < 0) || (i2 < 0 && this.f1478m < 0) || (i == this.f1477l && i2 == this.f1478m);
        boolean z2 = i < 0 || i2 < 0;
        this.f1477l = i;
        this.f1478m = i2;
        if (z) {
            m1137a(charSequence);
        } else {
            m1146a();
            this.f1469d = Toast.makeText(this.f1470e, charSequence, 0);
            if (z2) {
                this.f1469d.setGravity(this.f1474i, this.f1475j, this.f1476k);
            } else {
                this.f1469d.setGravity(51, i, i2);
            }
        }
        try {
            if (this.f1471f) {
                this.f1488w.cancel();
                this.f1488w = new a(this, b);
                this.f1487v.schedule(this.f1488w, this.f1473h * 1000);
                return;
            }
            try {
                Field declaredField = this.f1469d.getClass().getDeclaredField("mTN");
                declaredField.setAccessible(true);
                this.f1479n = declaredField.get(this.f1469d);
                this.f1480o = this.f1479n.getClass().getMethod("show", new Class[0]);
                this.f1481p = this.f1479n.getClass().getMethod("hide", new Class[0]);
                Field declaredField2 = this.f1479n.getClass().getDeclaredField("mParams");
                declaredField2.setAccessible(true);
                this.f1483r = (WindowManager.LayoutParams) declaredField2.get(this.f1479n);
                if (this.f1484s != null) {
                    this.f1483r.flags = this.f1484s.flags;
                    this.f1483r.width = this.f1484s.width;
                    this.f1483r.height = this.f1484s.height;
                    this.f1483r.gravity = this.f1484s.gravity;
                    this.f1483r.windowAnimations = this.f1484s.windowAnimations;
                }
                if (this.f1472g != -1) {
                    this.f1483r.windowAnimations = this.f1472g;
                }
                Field declaredField3 = this.f1479n.getClass().getDeclaredField("mNextView");
                declaredField3.setAccessible(true);
                declaredField3.set(this.f1479n, this.f1469d.getView());
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            this.f1480o.invoke(this.f1479n, new Object[0]);
            this.f1471f = true;
            if (this.f1473h > 0) {
                this.f1488w.cancel();
                this.f1488w = new a(this, b);
                this.f1487v.schedule(this.f1488w, this.f1473h * 1000);
            }
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IllegalStateException e3) {
            ThrowableExtension.printStackTrace(e3);
            m1146a();
            this.f1487v.cancel();
            this.f1487v = new Timer();
        } catch (InvocationTargetException e4) {
            ThrowableExtension.printStackTrace(e4);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1139b() {
        try {
            byte b = 0;
            if (this.f1471f) {
                this.f1488w.cancel();
                this.f1488w = new a(this, b);
                this.f1487v.schedule(this.f1488w, this.f1473h * 1000);
                return;
            }
            try {
                Field declaredField = this.f1469d.getClass().getDeclaredField("mTN");
                declaredField.setAccessible(true);
                this.f1479n = declaredField.get(this.f1469d);
                this.f1480o = this.f1479n.getClass().getMethod("show", new Class[0]);
                this.f1481p = this.f1479n.getClass().getMethod("hide", new Class[0]);
                Field declaredField2 = this.f1479n.getClass().getDeclaredField("mParams");
                declaredField2.setAccessible(true);
                this.f1483r = (WindowManager.LayoutParams) declaredField2.get(this.f1479n);
                if (this.f1484s != null) {
                    this.f1483r.flags = this.f1484s.flags;
                    this.f1483r.width = this.f1484s.width;
                    this.f1483r.height = this.f1484s.height;
                    this.f1483r.gravity = this.f1484s.gravity;
                    this.f1483r.windowAnimations = this.f1484s.windowAnimations;
                }
                if (this.f1472g != -1) {
                    this.f1483r.windowAnimations = this.f1472g;
                }
                Field declaredField3 = this.f1479n.getClass().getDeclaredField("mNextView");
                declaredField3.setAccessible(true);
                declaredField3.set(this.f1479n, this.f1469d.getView());
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
            }
            this.f1480o.invoke(this.f1479n, new Object[0]);
            this.f1471f = true;
            if (this.f1473h > 0) {
                this.f1488w.cancel();
                this.f1488w = new a(this, b);
                this.f1487v.schedule(this.f1488w, this.f1473h * 1000);
            }
        } catch (IllegalAccessException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (IllegalStateException e3) {
            ThrowableExtension.printStackTrace(e3);
            m1146a();
            this.f1487v.cancel();
            this.f1487v = new Timer();
        } catch (InvocationTargetException e4) {
            ThrowableExtension.printStackTrace(e4);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1134a(int i, int i2, int i3) {
        this.f1469d.setGravity(i, i2, i3);
    }

    /* JADX INFO: renamed from: a */
    private void m1135a(View view) {
        this.f1469d.setView(view);
    }

    /* JADX INFO: renamed from: c */
    private View m1142c() {
        return this.f1469d.getView();
    }

    /* JADX INFO: renamed from: a */
    private void m1133a(int i) {
        this.f1473h = i;
    }

    /* JADX INFO: renamed from: b */
    private void m1140b(int i) {
        m1137a(this.f1470e.getText(i));
    }

    /* JADX INFO: renamed from: a */
    private void m1137a(CharSequence charSequence) {
        this.f1469d.setText(charSequence);
    }

    /* JADX INFO: renamed from: d */
    private int m1144d() {
        return this.f1472g;
    }

    /* JADX INFO: renamed from: c */
    private void m1143c(int i) {
        this.f1472g = i;
    }

    /* JADX INFO: renamed from: a */
    public final void m1146a() {
        if (this.f1471f) {
            try {
                this.f1481p.invoke(this.f1479n, new Object[0]);
            } catch (IllegalAccessException e) {
                ThrowableExtension.printStackTrace(e);
            } catch (InvocationTargetException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
            this.f1471f = false;
        }
    }

    /* JADX INFO: renamed from: e */
    private void m1145e() {
        try {
            Field declaredField = this.f1469d.getClass().getDeclaredField("mTN");
            declaredField.setAccessible(true);
            this.f1479n = declaredField.get(this.f1469d);
            this.f1480o = this.f1479n.getClass().getMethod("show", new Class[0]);
            this.f1481p = this.f1479n.getClass().getMethod("hide", new Class[0]);
            Field declaredField2 = this.f1479n.getClass().getDeclaredField("mParams");
            declaredField2.setAccessible(true);
            this.f1483r = (WindowManager.LayoutParams) declaredField2.get(this.f1479n);
            if (this.f1484s != null) {
                this.f1483r.flags = this.f1484s.flags;
                this.f1483r.width = this.f1484s.width;
                this.f1483r.height = this.f1484s.height;
                this.f1483r.gravity = this.f1484s.gravity;
                this.f1483r.windowAnimations = this.f1484s.windowAnimations;
            }
            if (this.f1472g != -1) {
                this.f1483r.windowAnimations = this.f1472g;
            }
            Field declaredField3 = this.f1479n.getClass().getDeclaredField("mNextView");
            declaredField3.setAccessible(true);
            declaredField3.set(this.f1479n, this.f1469d.getView());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1136a(WindowManager.LayoutParams layoutParams) {
        try {
            Field declaredField = this.f1469d.getClass().getDeclaredField("mTN");
            declaredField.setAccessible(true);
            this.f1479n = declaredField.get(this.f1469d);
            Field declaredField2 = this.f1479n.getClass().getDeclaredField("mParams");
            declaredField2.setAccessible(true);
            declaredField2.set(this.f1479n, layoutParams);
        } catch (IllegalAccessException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchFieldException e2) {
            ThrowableExtension.printStackTrace(e2);
        }
    }

    /* JADX INFO: renamed from: b */
    private void m1141b(WindowManager.LayoutParams layoutParams) {
        this.f1484s = layoutParams;
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.view.a$a */
    /* JADX INFO: compiled from: ExToast0.java */
    private class a extends TimerTask {
        private a() {
        }

        /* synthetic */ a(C0824a c0824a, byte b) {
            this();
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public final void run() {
            C0824a.this.m1146a();
        }
    }
}

package com.cyjh.mobileanjian.ipc.p018ui;

import android.R;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.d */
/* JADX INFO: compiled from: SlidingTabStrip.java */
/* JADX INFO: loaded from: classes.dex */
final class C0783d extends LinearLayout {

    /* JADX INFO: renamed from: c */
    private static final int f1213c = 0;

    /* JADX INFO: renamed from: d */
    private static final byte f1214d = 38;

    /* JADX INFO: renamed from: e */
    private static final int f1215e = 3;

    /* JADX INFO: renamed from: f */
    private static final int f1216f = -13388315;

    /* JADX INFO: renamed from: a */
    SlidingTabLayout.InterfaceC0779c f1217a;

    /* JADX INFO: renamed from: b */
    final a f1218b;

    /* JADX INFO: renamed from: g */
    private final int f1219g;

    /* JADX INFO: renamed from: h */
    private final Paint f1220h;

    /* JADX INFO: renamed from: i */
    private final int f1221i;

    /* JADX INFO: renamed from: j */
    private final Paint f1222j;

    /* JADX INFO: renamed from: k */
    private int f1223k;

    /* JADX INFO: renamed from: l */
    private float f1224l;

    C0783d(Context context) {
        this(context, (byte) 0);
    }

    private C0783d(Context context, byte b) {
        super(context, null);
        setWillNotDraw(false);
        float f = getResources().getDisplayMetrics().density;
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.colorForeground, typedValue, true);
        int i = typedValue.data;
        int iArgb = Color.argb(38, Color.red(i), Color.green(i), Color.blue(i));
        this.f1218b = new a((byte) 0);
        this.f1218b.f1225a = new int[]{f1216f};
        this.f1219g = (int) (0.0f * f);
        this.f1220h = new Paint();
        this.f1220h.setColor(iArgb);
        this.f1221i = (int) (f * 3.0f);
        this.f1222j = new Paint();
    }

    /* JADX INFO: renamed from: a */
    private void m842a(SlidingTabLayout.InterfaceC0779c interfaceC0779c) {
        this.f1217a = interfaceC0779c;
        invalidate();
    }

    /* JADX INFO: renamed from: a */
    private void m843a(int... iArr) {
        this.f1217a = null;
        this.f1218b.f1225a = iArr;
        invalidate();
    }

    /* JADX INFO: renamed from: a */
    final void m844a(int i, float f) {
        this.f1223k = i;
        this.f1224l = f;
        invalidate();
    }

    @Override // android.widget.LinearLayout, android.view.View
    protected final void onDraw(Canvas canvas) {
        int height = getHeight();
        int childCount = getChildCount();
        SlidingTabLayout.InterfaceC0779c interfaceC0779c = this.f1217a != null ? this.f1217a : this.f1218b;
        if (childCount > 0) {
            View childAt = getChildAt(this.f1223k);
            int left = childAt.getLeft();
            int right = childAt.getRight();
            int iMo828a = interfaceC0779c.mo828a(this.f1223k);
            if (this.f1224l > 0.0f && this.f1223k < getChildCount() - 1) {
                int iMo828a2 = interfaceC0779c.mo828a(this.f1223k + 1);
                if (iMo828a != iMo828a2) {
                    float f = this.f1224l;
                    float f2 = 1.0f - f;
                    iMo828a = Color.rgb((int) ((Color.red(iMo828a2) * f) + (Color.red(iMo828a) * f2)), (int) ((Color.green(iMo828a2) * f) + (Color.green(iMo828a) * f2)), (int) ((Color.blue(iMo828a2) * f) + (Color.blue(iMo828a) * f2)));
                }
                View childAt2 = getChildAt(this.f1223k + 1);
                left = (int) ((this.f1224l * childAt2.getLeft()) + ((1.0f - this.f1224l) * left));
                right = (int) ((this.f1224l * childAt2.getRight()) + ((1.0f - this.f1224l) * right));
            }
            this.f1222j.setColor(iMo828a);
            canvas.drawRect(left, height - this.f1221i, right, height, this.f1222j);
        }
        canvas.drawRect(0.0f, height - this.f1219g, getWidth(), height, this.f1220h);
    }

    /* JADX INFO: renamed from: a */
    private static int m840a(int i) {
        return Color.argb(38, Color.red(i), Color.green(i), Color.blue(i));
    }

    /* JADX INFO: renamed from: a */
    private static int m841a(int i, int i2, float f) {
        float f2 = 1.0f - f;
        return Color.rgb((int) ((Color.red(i) * f) + (Color.red(i2) * f2)), (int) ((Color.green(i) * f) + (Color.green(i2) * f2)), (int) ((Color.blue(i) * f) + (Color.blue(i2) * f2)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.d$a */
    /* JADX INFO: compiled from: SlidingTabStrip.java */
    static class a implements SlidingTabLayout.InterfaceC0779c {

        /* JADX INFO: renamed from: a */
        int[] f1225a;

        private a() {
        }

        /* synthetic */ a(byte b) {
            this();
        }

        @Override // com.cyjh.mobileanjian.ipc.p018ui.SlidingTabLayout.InterfaceC0779c
        /* JADX INFO: renamed from: a */
        public final int mo828a(int i) {
            return this.f1225a[i % this.f1225a.length];
        }

        /* JADX INFO: renamed from: a */
        private void m845a(int... iArr) {
            this.f1225a = iArr;
        }
    }
}

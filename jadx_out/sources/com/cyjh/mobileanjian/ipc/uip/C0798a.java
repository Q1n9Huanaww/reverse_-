package com.cyjh.mobileanjian.ipc.uip;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.support.annotation.AnimatorRes;
import android.support.annotation.DrawableRes;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import com.cyjh.mqsdk.C0852R;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.a */
/* JADX INFO: compiled from: CircleIndicator.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0798a extends LinearLayout {

    /* JADX INFO: renamed from: a */
    private static final int f1376a = 8;

    /* JADX INFO: renamed from: b */
    private ViewPager f1377b;

    /* JADX INFO: renamed from: c */
    private int f1378c;

    /* JADX INFO: renamed from: d */
    private int f1379d;

    /* JADX INFO: renamed from: e */
    private int f1380e;

    /* JADX INFO: renamed from: f */
    private int f1381f;

    /* JADX INFO: renamed from: g */
    private int f1382g;

    /* JADX INFO: renamed from: h */
    private int f1383h;

    /* JADX INFO: renamed from: i */
    private int f1384i;

    /* JADX INFO: renamed from: j */
    private Animator f1385j;

    /* JADX INFO: renamed from: k */
    private Animator f1386k;

    /* JADX INFO: renamed from: l */
    private Animator f1387l;

    /* JADX INFO: renamed from: m */
    private Animator f1388m;

    /* JADX INFO: renamed from: n */
    private int f1389n;

    /* JADX INFO: renamed from: o */
    private final ViewPager.OnPageChangeListener f1390o;

    /* JADX INFO: renamed from: p */
    private DataSetObserver f1391p;

    public C0798a(Context context) {
        super(context);
        this.f1378c = -1;
        this.f1379d = -1;
        this.f1380e = -1;
        this.f1381f = C0852R.animator.no_animator;
        this.f1382g = 0;
        this.f1383h = C0852R.drawable.ci_blue_point;
        this.f1384i = C0852R.drawable.ci_gray_point;
        this.f1389n = -1;
        this.f1390o = new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.uip.a.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageSelected(int i) {
                View childAt;
                if (C0798a.this.f1377b.getAdapter() == null || C0798a.this.f1377b.getAdapter().getCount() <= 0) {
                    return;
                }
                if (C0798a.this.f1386k.isRunning()) {
                    C0798a.this.f1386k.end();
                    C0798a.this.f1386k.cancel();
                }
                if (C0798a.this.f1385j.isRunning()) {
                    C0798a.this.f1385j.end();
                    C0798a.this.f1385j.cancel();
                }
                if (C0798a.this.f1389n >= 0 && (childAt = C0798a.this.getChildAt(C0798a.this.f1389n)) != null) {
                    childAt.setBackgroundResource(C0798a.this.f1384i);
                    C0798a.this.f1386k.setTarget(childAt);
                    C0798a.this.f1386k.start();
                }
                View childAt2 = C0798a.this.getChildAt(i);
                if (childAt2 != null) {
                    childAt2.setBackgroundResource(C0798a.this.f1383h);
                    C0798a.this.f1385j.setTarget(childAt2);
                    C0798a.this.f1385j.start();
                }
                C0798a.this.f1389n = i;
            }
        };
        this.f1391p = new DataSetObserver() { // from class: com.cyjh.mobileanjian.ipc.uip.a.2
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                int count;
                super.onChanged();
                if (C0798a.this.f1377b == null || (count = C0798a.this.f1377b.getAdapter().getCount()) == C0798a.this.getChildCount()) {
                    return;
                }
                if (C0798a.this.f1389n < count) {
                    C0798a.this.f1389n = C0798a.this.f1377b.getCurrentItem();
                } else {
                    C0798a.this.f1389n = -1;
                }
                C0798a.this.m1031a();
            }
        };
        m1036a(context, (AttributeSet) null);
    }

    private C0798a(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f1378c = -1;
        this.f1379d = -1;
        this.f1380e = -1;
        this.f1381f = C0852R.animator.no_animator;
        this.f1382g = 0;
        this.f1383h = C0852R.drawable.ci_blue_point;
        this.f1384i = C0852R.drawable.ci_gray_point;
        this.f1389n = -1;
        this.f1390o = new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.uip.a.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageSelected(int i) {
                View childAt;
                if (C0798a.this.f1377b.getAdapter() == null || C0798a.this.f1377b.getAdapter().getCount() <= 0) {
                    return;
                }
                if (C0798a.this.f1386k.isRunning()) {
                    C0798a.this.f1386k.end();
                    C0798a.this.f1386k.cancel();
                }
                if (C0798a.this.f1385j.isRunning()) {
                    C0798a.this.f1385j.end();
                    C0798a.this.f1385j.cancel();
                }
                if (C0798a.this.f1389n >= 0 && (childAt = C0798a.this.getChildAt(C0798a.this.f1389n)) != null) {
                    childAt.setBackgroundResource(C0798a.this.f1384i);
                    C0798a.this.f1386k.setTarget(childAt);
                    C0798a.this.f1386k.start();
                }
                View childAt2 = C0798a.this.getChildAt(i);
                if (childAt2 != null) {
                    childAt2.setBackgroundResource(C0798a.this.f1383h);
                    C0798a.this.f1385j.setTarget(childAt2);
                    C0798a.this.f1385j.start();
                }
                C0798a.this.f1389n = i;
            }
        };
        this.f1391p = new DataSetObserver() { // from class: com.cyjh.mobileanjian.ipc.uip.a.2
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                int count;
                super.onChanged();
                if (C0798a.this.f1377b == null || (count = C0798a.this.f1377b.getAdapter().getCount()) == C0798a.this.getChildCount()) {
                    return;
                }
                if (C0798a.this.f1389n < count) {
                    C0798a.this.f1389n = C0798a.this.f1377b.getCurrentItem();
                } else {
                    C0798a.this.f1389n = -1;
                }
                C0798a.this.m1031a();
            }
        };
        m1036a(context, attributeSet);
    }

    private C0798a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1378c = -1;
        this.f1379d = -1;
        this.f1380e = -1;
        this.f1381f = C0852R.animator.no_animator;
        this.f1382g = 0;
        this.f1383h = C0852R.drawable.ci_blue_point;
        this.f1384i = C0852R.drawable.ci_gray_point;
        this.f1389n = -1;
        this.f1390o = new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.uip.a.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrollStateChanged(int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageSelected(int i2) {
                View childAt;
                if (C0798a.this.f1377b.getAdapter() == null || C0798a.this.f1377b.getAdapter().getCount() <= 0) {
                    return;
                }
                if (C0798a.this.f1386k.isRunning()) {
                    C0798a.this.f1386k.end();
                    C0798a.this.f1386k.cancel();
                }
                if (C0798a.this.f1385j.isRunning()) {
                    C0798a.this.f1385j.end();
                    C0798a.this.f1385j.cancel();
                }
                if (C0798a.this.f1389n >= 0 && (childAt = C0798a.this.getChildAt(C0798a.this.f1389n)) != null) {
                    childAt.setBackgroundResource(C0798a.this.f1384i);
                    C0798a.this.f1386k.setTarget(childAt);
                    C0798a.this.f1386k.start();
                }
                View childAt2 = C0798a.this.getChildAt(i2);
                if (childAt2 != null) {
                    childAt2.setBackgroundResource(C0798a.this.f1383h);
                    C0798a.this.f1385j.setTarget(childAt2);
                    C0798a.this.f1385j.start();
                }
                C0798a.this.f1389n = i2;
            }
        };
        this.f1391p = new DataSetObserver() { // from class: com.cyjh.mobileanjian.ipc.uip.a.2
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                int count;
                super.onChanged();
                if (C0798a.this.f1377b == null || (count = C0798a.this.f1377b.getAdapter().getCount()) == C0798a.this.getChildCount()) {
                    return;
                }
                if (C0798a.this.f1389n < count) {
                    C0798a.this.f1389n = C0798a.this.f1377b.getCurrentItem();
                } else {
                    C0798a.this.f1389n = -1;
                }
                C0798a.this.m1031a();
            }
        };
        m1036a(context, attributeSet);
    }

    @TargetApi(21)
    private C0798a(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.f1378c = -1;
        this.f1379d = -1;
        this.f1380e = -1;
        this.f1381f = C0852R.animator.no_animator;
        this.f1382g = 0;
        this.f1383h = C0852R.drawable.ci_blue_point;
        this.f1384i = C0852R.drawable.ci_gray_point;
        this.f1389n = -1;
        this.f1390o = new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.uip.a.1
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrollStateChanged(int i3) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrolled(int i3, float f, int i4) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageSelected(int i3) {
                View childAt;
                if (C0798a.this.f1377b.getAdapter() == null || C0798a.this.f1377b.getAdapter().getCount() <= 0) {
                    return;
                }
                if (C0798a.this.f1386k.isRunning()) {
                    C0798a.this.f1386k.end();
                    C0798a.this.f1386k.cancel();
                }
                if (C0798a.this.f1385j.isRunning()) {
                    C0798a.this.f1385j.end();
                    C0798a.this.f1385j.cancel();
                }
                if (C0798a.this.f1389n >= 0 && (childAt = C0798a.this.getChildAt(C0798a.this.f1389n)) != null) {
                    childAt.setBackgroundResource(C0798a.this.f1384i);
                    C0798a.this.f1386k.setTarget(childAt);
                    C0798a.this.f1386k.start();
                }
                View childAt2 = C0798a.this.getChildAt(i3);
                if (childAt2 != null) {
                    childAt2.setBackgroundResource(C0798a.this.f1383h);
                    C0798a.this.f1385j.setTarget(childAt2);
                    C0798a.this.f1385j.start();
                }
                C0798a.this.f1389n = i3;
            }
        };
        this.f1391p = new DataSetObserver() { // from class: com.cyjh.mobileanjian.ipc.uip.a.2
            @Override // android.database.DataSetObserver
            public final void onChanged() {
                int count;
                super.onChanged();
                if (C0798a.this.f1377b == null || (count = C0798a.this.f1377b.getAdapter().getCount()) == C0798a.this.getChildCount()) {
                    return;
                }
                if (C0798a.this.f1389n < count) {
                    C0798a.this.f1389n = C0798a.this.f1377b.getCurrentItem();
                } else {
                    C0798a.this.f1389n = -1;
                }
                C0798a.this.m1031a();
            }
        };
        m1036a(context, attributeSet);
    }

    /* JADX INFO: renamed from: b */
    private void m1039b(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0852R.styleable.CircleIndicator);
        this.f1379d = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0852R.styleable.CircleIndicator_ci_width, -1);
        this.f1380e = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0852R.styleable.CircleIndicator_ci_height, -1);
        this.f1378c = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0852R.styleable.CircleIndicator_ci_margin, -1);
        this.f1381f = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_animator, C0852R.animator.scale_with_alpha);
        this.f1382g = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_animator_reverse, 0);
        this.f1383h = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_drawable, C0852R.drawable.white_radius);
        this.f1384i = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_drawable_unselected, this.f1383h);
        setOrientation(typedArrayObtainStyledAttributes.getInt(C0852R.styleable.CircleIndicator_ci_orientation, -1) == 1 ? 1 : 0);
        int i = typedArrayObtainStyledAttributes.getInt(C0852R.styleable.CircleIndicator_ci_gravity, -1);
        if (i < 0) {
            i = 17;
        }
        setGravity(i);
        typedArrayObtainStyledAttributes.recycle();
    }

    /* JADX INFO: renamed from: a */
    private void m1032a(int i, int i2, int i3) {
        int i4 = C0852R.animator.scale_with_alpha;
        int i5 = C0852R.drawable.white_radius;
        int i6 = C0852R.drawable.white_radius;
        this.f1379d = i;
        this.f1380e = i2;
        this.f1378c = i3;
        this.f1381f = i4;
        this.f1382g = 0;
        this.f1383h = i5;
        this.f1384i = i6;
        m1035a(getContext());
    }

    /* JADX INFO: renamed from: a */
    private void m1033a(int i, int i2, int i3, @AnimatorRes int i4, @DrawableRes int i5, @DrawableRes int i6) {
        this.f1379d = i;
        this.f1380e = i2;
        this.f1378c = i3;
        this.f1381f = i4;
        this.f1382g = 0;
        this.f1383h = i5;
        this.f1384i = i6;
        m1035a(getContext());
    }

    /* JADX INFO: renamed from: a */
    private void m1035a(Context context) {
        this.f1379d = this.f1379d < 0 ? m1046a(8.0f) : this.f1379d;
        this.f1380e = this.f1380e < 0 ? m1046a(8.0f) : this.f1380e;
        this.f1378c = this.f1378c < 0 ? m1046a(8.0f) : this.f1378c;
        this.f1381f = this.f1381f == 0 ? C0852R.animator.scale_with_alpha : this.f1381f;
        this.f1385j = m1037b(context);
        this.f1387l = m1037b(context);
        this.f1387l.setDuration(0L);
        this.f1386k = m1040c(context);
        this.f1388m = m1040c(context);
        this.f1388m.setDuration(0L);
        this.f1383h = this.f1383h == 0 ? C0852R.drawable.white_radius : this.f1383h;
        this.f1384i = this.f1384i == 0 ? this.f1383h : this.f1384i;
    }

    /* JADX INFO: renamed from: b */
    private Animator m1037b(Context context) {
        return AnimatorInflater.loadAnimator(context, this.f1381f);
    }

    /* JADX INFO: renamed from: c */
    private Animator m1040c(Context context) {
        if (this.f1382g == 0) {
            Animator animatorLoadAnimator = AnimatorInflater.loadAnimator(context, this.f1381f);
            animatorLoadAnimator.setInterpolator(new a(this, (byte) 0));
            return animatorLoadAnimator;
        }
        return AnimatorInflater.loadAnimator(context, this.f1382g);
    }

    public final void setViewPager(ViewPager viewPager) {
        this.f1377b = viewPager;
        if (this.f1377b == null || this.f1377b.getAdapter() == null) {
            return;
        }
        this.f1389n = -1;
        m1031a();
        this.f1377b.removeOnPageChangeListener(this.f1390o);
        this.f1377b.addOnPageChangeListener(this.f1390o);
        this.f1390o.onPageSelected(this.f1377b.getCurrentItem());
    }

    public final DataSetObserver getDataSetObserver() {
        return this.f1391p;
    }

    @Deprecated
    public final void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        if (this.f1377b == null) {
            throw new NullPointerException("can not find Viewpager , setViewPager first");
        }
        this.f1377b.removeOnPageChangeListener(onPageChangeListener);
        this.f1377b.addOnPageChangeListener(onPageChangeListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: a */
    public void m1031a() {
        removeAllViews();
        int count = this.f1377b.getAdapter().getCount();
        if (count <= 0) {
            return;
        }
        int currentItem = this.f1377b.getCurrentItem();
        int orientation = getOrientation();
        for (int i = 0; i < count; i++) {
            if (currentItem == i) {
                m1034a(orientation, this.f1383h, this.f1387l);
            } else {
                m1034a(orientation, this.f1384i, this.f1388m);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m1034a(int i, @DrawableRes int i2, Animator animator) {
        if (animator.isRunning()) {
            animator.end();
            animator.cancel();
        }
        View view = new View(getContext());
        view.setBackgroundResource(i2);
        addView(view, this.f1379d, this.f1380e);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        if (i == 0) {
            layoutParams.leftMargin = this.f1378c;
            layoutParams.rightMargin = this.f1378c;
        } else {
            layoutParams.topMargin = this.f1378c;
            layoutParams.bottomMargin = this.f1378c;
        }
        view.setLayoutParams(layoutParams);
        animator.setTarget(view);
        animator.start();
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.a$a */
    /* JADX INFO: compiled from: CircleIndicator.java */
    private class a implements Interpolator {
        private a() {
        }

        /* synthetic */ a(C0798a c0798a, byte b) {
            this();
        }

        @Override // android.animation.TimeInterpolator
        public final float getInterpolation(float f) {
            return Math.abs(1.0f - f);
        }
    }

    /* JADX INFO: renamed from: a */
    public final int m1046a(float f) {
        return (int) ((f * getResources().getDisplayMetrics().density) + 0.5f);
    }

    /* JADX INFO: renamed from: a */
    private void m1036a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, C0852R.styleable.CircleIndicator);
            this.f1379d = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0852R.styleable.CircleIndicator_ci_width, -1);
            this.f1380e = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0852R.styleable.CircleIndicator_ci_height, -1);
            this.f1378c = typedArrayObtainStyledAttributes.getDimensionPixelSize(C0852R.styleable.CircleIndicator_ci_margin, -1);
            this.f1381f = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_animator, C0852R.animator.scale_with_alpha);
            this.f1382g = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_animator_reverse, 0);
            this.f1383h = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_drawable, C0852R.drawable.white_radius);
            this.f1384i = typedArrayObtainStyledAttributes.getResourceId(C0852R.styleable.CircleIndicator_ci_drawable_unselected, this.f1383h);
            setOrientation(typedArrayObtainStyledAttributes.getInt(C0852R.styleable.CircleIndicator_ci_orientation, -1) == 1 ? 1 : 0);
            int i = typedArrayObtainStyledAttributes.getInt(C0852R.styleable.CircleIndicator_ci_gravity, -1);
            if (i < 0) {
                i = 17;
            }
            setGravity(i);
            typedArrayObtainStyledAttributes.recycle();
        }
        m1035a(context);
    }
}

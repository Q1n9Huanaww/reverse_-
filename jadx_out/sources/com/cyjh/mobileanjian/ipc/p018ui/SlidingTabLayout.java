package com.cyjh.mobileanjian.ipc.p018ui;

import android.R;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.mqsdk.C0852R;

/* JADX INFO: loaded from: classes.dex */
public class SlidingTabLayout extends HorizontalScrollView {

    /* JADX INFO: renamed from: b */
    private static final int f1169b = 24;

    /* JADX INFO: renamed from: c */
    private static final int f1170c = 4;

    /* JADX INFO: renamed from: d */
    private static final int f1171d = 12;

    /* JADX INFO: renamed from: a */
    ViewPager f1172a;

    /* JADX INFO: renamed from: e */
    private int f1173e;

    /* JADX INFO: renamed from: f */
    private int f1174f;

    /* JADX INFO: renamed from: g */
    private int f1175g;

    /* JADX INFO: renamed from: h */
    private boolean f1176h;

    /* JADX INFO: renamed from: i */
    private SparseArray<String> f1177i;

    /* JADX INFO: renamed from: j */
    private ViewPager.OnPageChangeListener f1178j;

    /* JADX INFO: renamed from: k */
    private final C0783d f1179k;

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout$c */
    public interface InterfaceC0779c {
        /* JADX INFO: renamed from: a */
        int mo828a(int i);
    }

    public SlidingTabLayout(Context context) {
        this(context, null);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SlidingTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f1177i = new SparseArray<>();
        setHorizontalScrollBarEnabled(false);
        setFillViewport(true);
        this.f1173e = (int) (getResources().getDisplayMetrics().density * 24.0f);
        this.f1179k = new C0783d(context);
        addView(this.f1179k, -1, -2);
    }

    public void setCustomTabColorizer(InterfaceC0779c interfaceC0779c) {
        C0783d c0783d = this.f1179k;
        c0783d.f1217a = interfaceC0779c;
        c0783d.invalidate();
    }

    public void setDistributeEvenly(boolean z) {
        this.f1176h = z;
    }

    public void setSelectedIndicatorColors(int... iArr) {
        C0783d c0783d = this.f1179k;
        c0783d.f1217a = null;
        c0783d.f1218b.f1225a = iArr;
        c0783d.invalidate();
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.f1178j = onPageChangeListener;
    }

    /* JADX INFO: renamed from: a */
    private void m821a(int i, int i2) {
        this.f1174f = i;
        this.f1175g = i2;
    }

    public void setViewPager(ViewPager viewPager) {
        View viewInflate;
        TextView textView;
        this.f1179k.removeAllViews();
        this.f1172a = viewPager;
        if (viewPager != null) {
            byte b = 0;
            viewPager.setOnPageChangeListener(new C0777a(this, b));
            PagerAdapter adapter = this.f1172a.getAdapter();
            ViewOnClickListenerC0778b viewOnClickListenerC0778b = new ViewOnClickListenerC0778b(this, b);
            for (int i = 0; i < adapter.getCount(); i++) {
                if (this.f1174f != 0) {
                    viewInflate = LayoutInflater.from(getContext()).inflate(this.f1174f, (ViewGroup) this.f1179k, false);
                    textView = (TextView) viewInflate.findViewById(this.f1175g);
                } else {
                    viewInflate = null;
                    textView = null;
                }
                if (viewInflate == null) {
                    TextView textView2 = new TextView(getContext());
                    textView2.setGravity(17);
                    textView2.setTextSize(2, 12.0f);
                    textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                    TypedValue typedValue = new TypedValue();
                    getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
                    textView2.setBackgroundResource(typedValue.resourceId);
                    textView2.setAllCaps(true);
                    int i2 = (int) (getResources().getDisplayMetrics().density * 4.0f);
                    textView2.setPadding(i2, i2, i2, i2);
                    textView2.setSingleLine(true);
                    viewInflate = textView2;
                }
                if (textView == null && TextView.class.isInstance(viewInflate)) {
                    textView = (TextView) viewInflate;
                }
                if (this.f1176h) {
                    LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewInflate.getLayoutParams();
                    layoutParams.width = 0;
                    layoutParams.weight = 1.0f;
                }
                textView.setTextColor(getResources().getColorStateList(C0852R.color.selector_tab));
                textView.setTextSize(2, 14.0f);
                textView.setText(adapter.getPageTitle(i));
                textView.setAllCaps(false);
                viewInflate.setOnClickListener(viewOnClickListenerC0778b);
                String str = this.f1177i.get(i, null);
                if (str != null) {
                    viewInflate.setContentDescription(str);
                }
                this.f1179k.addView(viewInflate);
                if (i == this.f1172a.getCurrentItem()) {
                    viewInflate.setSelected(true);
                }
            }
        }
    }

    public ViewPager getViewPager() {
        return this.f1172a;
    }

    /* JADX INFO: renamed from: a */
    private void m823a(LinearLayout linearLayout, String str) {
        C0791l c0791l = (C0791l) this.f1172a.getAdapter();
        c0791l.f1343a.add(linearLayout);
        c0791l.f1344b.add(str);
        c0791l.notifyDataSetChanged();
        this.f1172a.setAdapter(c0791l);
        setViewPager(this.f1172a);
    }

    /* JADX INFO: renamed from: a */
    private TextView m818a(Context context) {
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setTextSize(2, 12.0f);
        textView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        TypedValue typedValue = new TypedValue();
        getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
        textView.setBackgroundResource(typedValue.resourceId);
        textView.setAllCaps(true);
        int i = (int) (getResources().getDisplayMetrics().density * 4.0f);
        textView.setPadding(i, i, i, i);
        textView.setSingleLine(true);
        return textView;
    }

    /* JADX INFO: renamed from: a */
    private void m820a() {
        View viewInflate;
        TextView textView;
        PagerAdapter adapter = this.f1172a.getAdapter();
        ViewOnClickListenerC0778b viewOnClickListenerC0778b = new ViewOnClickListenerC0778b(this, (byte) 0);
        for (int i = 0; i < adapter.getCount(); i++) {
            if (this.f1174f != 0) {
                viewInflate = LayoutInflater.from(getContext()).inflate(this.f1174f, (ViewGroup) this.f1179k, false);
                textView = (TextView) viewInflate.findViewById(this.f1175g);
            } else {
                viewInflate = null;
                textView = null;
            }
            if (viewInflate == null) {
                TextView textView2 = new TextView(getContext());
                textView2.setGravity(17);
                textView2.setTextSize(2, 12.0f);
                textView2.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
                TypedValue typedValue = new TypedValue();
                getContext().getTheme().resolveAttribute(R.attr.selectableItemBackground, typedValue, true);
                textView2.setBackgroundResource(typedValue.resourceId);
                textView2.setAllCaps(true);
                int i2 = (int) (getResources().getDisplayMetrics().density * 4.0f);
                textView2.setPadding(i2, i2, i2, i2);
                textView2.setSingleLine(true);
                viewInflate = textView2;
            }
            if (textView == null && TextView.class.isInstance(viewInflate)) {
                textView = (TextView) viewInflate;
            }
            if (this.f1176h) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) viewInflate.getLayoutParams();
                layoutParams.width = 0;
                layoutParams.weight = 1.0f;
            }
            textView.setTextColor(getResources().getColorStateList(C0852R.color.selector_tab));
            textView.setTextSize(2, 14.0f);
            textView.setText(adapter.getPageTitle(i));
            textView.setAllCaps(false);
            viewInflate.setOnClickListener(viewOnClickListenerC0778b);
            String str = this.f1177i.get(i, null);
            if (str != null) {
                viewInflate.setContentDescription(str);
            }
            this.f1179k.addView(viewInflate);
            if (i == this.f1172a.getCurrentItem()) {
                viewInflate.setSelected(true);
            }
        }
    }

    /* JADX INFO: renamed from: a */
    private void m822a(int i, String str) {
        this.f1177i.put(i, str);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.f1172a != null) {
            m826b(this.f1172a.getCurrentItem(), 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public void m826b(int i, int i2) {
        View childAt;
        int childCount = this.f1179k.getChildCount();
        if (childCount == 0 || i < 0 || i >= childCount || (childAt = this.f1179k.getChildAt(i)) == null) {
            return;
        }
        int left = childAt.getLeft() + i2;
        if (i > 0 || i2 > 0) {
            left -= this.f1173e;
        }
        scrollTo(left, 0);
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout$a */
    private class C0777a implements ViewPager.OnPageChangeListener {

        /* JADX INFO: renamed from: b */
        private int f1181b;

        private C0777a() {
        }

        /* synthetic */ C0777a(SlidingTabLayout slidingTabLayout, byte b) {
            this();
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public final void onPageScrolled(int i, float f, int i2) {
            int childCount = SlidingTabLayout.this.f1179k.getChildCount();
            if (childCount == 0 || i < 0 || i >= childCount) {
                return;
            }
            SlidingTabLayout.this.f1179k.m844a(i, f);
            View childAt = SlidingTabLayout.this.f1179k.getChildAt(i);
            SlidingTabLayout.this.m826b(i, childAt != null ? (int) (childAt.getWidth() * f) : 0);
            if (SlidingTabLayout.this.f1178j != null) {
                SlidingTabLayout.this.f1178j.onPageScrolled(i, f, i2);
            }
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public final void onPageScrollStateChanged(int i) {
            this.f1181b = i;
            if (SlidingTabLayout.this.f1178j != null) {
                SlidingTabLayout.this.f1178j.onPageScrollStateChanged(i);
            }
        }

        @Override // android.support.v4.view.ViewPager.OnPageChangeListener
        public final void onPageSelected(int i) {
            if (this.f1181b == 0) {
                SlidingTabLayout.this.f1179k.m844a(i, 0.0f);
                SlidingTabLayout.this.m826b(i, 0);
            }
            int i2 = 0;
            while (i2 < SlidingTabLayout.this.f1179k.getChildCount()) {
                SlidingTabLayout.this.f1179k.getChildAt(i2).setSelected(i == i2);
                i2++;
            }
            if (SlidingTabLayout.this.f1178j != null) {
                SlidingTabLayout.this.f1178j.onPageSelected(i);
            }
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.SlidingTabLayout$b */
    private class ViewOnClickListenerC0778b implements View.OnClickListener {
        private ViewOnClickListenerC0778b() {
        }

        /* synthetic */ ViewOnClickListenerC0778b(SlidingTabLayout slidingTabLayout, byte b) {
            this();
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            for (int i = 0; i < SlidingTabLayout.this.f1179k.getChildCount(); i++) {
                if (view == SlidingTabLayout.this.f1179k.getChildAt(i)) {
                    SlidingTabLayout.this.f1172a.setCurrentItem(i);
                    return;
                }
            }
        }
    }
}

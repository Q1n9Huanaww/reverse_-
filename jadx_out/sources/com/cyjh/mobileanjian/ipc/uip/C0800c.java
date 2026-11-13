package com.cyjh.mobileanjian.ipc.uip;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout;
import com.cyjh.mqsdk.C0852R;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.c */
/* JADX INFO: compiled from: HSettingLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0800c extends LinearLayout implements RadioGroup.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a */
    static final int[] f1400a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};

    /* JADX INFO: renamed from: h */
    private static final int f1401h = 32;

    /* JADX INFO: renamed from: b */
    RadioGroup f1402b;

    /* JADX INFO: renamed from: c */
    List<ScrollView> f1403c;

    /* JADX INFO: renamed from: d */
    int f1404d;

    /* JADX INFO: renamed from: e */
    LinearLayout f1405e;

    /* JADX INFO: renamed from: f */
    ScrollView f1406f;

    /* JADX INFO: renamed from: g */
    int f1407g;

    /* JADX INFO: renamed from: i */
    private FrameLayout f1408i;

    /* JADX INFO: renamed from: j */
    private int f1409j;

    public C0800c(Context context) {
        this(context, (byte) 0);
    }

    private C0800c(Context context, byte b) {
        this(context, (char) 0);
    }

    private C0800c(Context context, char c) {
        super(context, null, 0);
        this.f1407g = 0;
        this.f1404d = (int) TypedValue.applyDimension(1, 10.0f, context.getResources().getDisplayMetrics());
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        setOrientation(0);
        LinearLayout linearLayout = new LinearLayout(context);
        TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(0, -1);
        layoutParams.weight = 7.5f;
        linearLayout.setLayoutParams(layoutParams);
        linearLayout.setGravity(16);
        linearLayout.setOrientation(1);
        ScrollView scrollView = new ScrollView(context);
        this.f1402b = new RadioGroup(context);
        this.f1402b.setOrientation(1);
        this.f1402b.setPadding(this.f1404d, 0, 0, -this.f1404d);
        scrollView.addView(this.f1402b);
        linearLayout.addView(scrollView);
        addView(linearLayout);
        View view = new View(context);
        view.setLayoutParams(new LinearLayout.LayoutParams((int) TypedValue.applyDimension(1, 1.0f, context.getResources().getDisplayMetrics()), -1));
        view.setBackgroundColor(-16777216);
        addView(view);
        this.f1408i = new FrameLayout(context);
        TableLayout.LayoutParams layoutParams2 = new TableLayout.LayoutParams(0, -1);
        layoutParams2.weight = 2.5f;
        this.f1408i.setLayoutParams(layoutParams2);
        this.f1408i.setPadding(this.f1404d, 0, this.f1404d, 0);
        addView(this.f1408i);
        this.f1403c = new ArrayList(32);
        this.f1402b.setOnCheckedChangeListener(this);
    }

    public final List<ScrollView> getScrollViewList() {
        return this.f1403c;
    }

    /* JADX INFO: renamed from: a */
    private void m1052a(String str) {
        RadioButton radioButton = new RadioButton(getContext());
        radioButton.setId(f1400a[this.f1407g]);
        radioButton.setPadding(0, 0, 0, this.f1404d);
        radioButton.setButtonDrawable(new BitmapDrawable((Bitmap) null));
        radioButton.setTextColor(getResources().getColorStateList(C0852R.color.selector_tab));
        radioButton.setText(str);
        radioButton.setLines(1);
        radioButton.setEllipsize(TextUtils.TruncateAt.END);
        this.f1402b.addView(radioButton);
        this.f1406f = new ScrollView(getContext());
        this.f1406f.setDescendantFocusability(131072);
        this.f1406f.setFocusable(true);
        this.f1406f.setFocusableInTouchMode(true);
        this.f1406f.setOnTouchListener(new AnonymousClass1());
        this.f1406f.setTag(str);
        this.f1406f.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.f1405e = linearLayout;
        this.f1406f.addView(this.f1405e);
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.uip.c$1, reason: invalid class name */
    /* JADX INFO: compiled from: HSettingLayout.java */
    final class AnonymousClass1 implements View.OnTouchListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            view.requestFocusFromTouch();
            return false;
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1054a(View view) {
        this.f1405e.addView(view);
    }

    /* JADX INFO: renamed from: a */
    private void m1051a() {
        this.f1403c.add(this.f1406f);
        this.f1407g++;
    }

    /* JADX INFO: renamed from: b */
    private LinearLayout m1053b() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        return linearLayout;
    }

    /* JADX INFO: renamed from: a */
    public final void m1055a(List<ScrollView> list) {
        this.f1403c = list;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ScrollView scrollView = list.get(i);
            RadioButton radioButton = new RadioButton(getContext());
            radioButton.setId(f1400a[i]);
            radioButton.setPadding(0, 0, 0, this.f1404d);
            radioButton.setButtonDrawable(new BitmapDrawable((Bitmap) null));
            radioButton.setTextColor(getResources().getColorStateList(C0852R.color.selector_tab));
            radioButton.setText((String) scrollView.getTag());
            radioButton.setLines(1);
            radioButton.setEllipsize(TextUtils.TruncateAt.END);
            this.f1402b.addView(radioButton);
        }
    }

    public final int getCurrentItem() {
        return this.f1409j;
    }

    public final void setSelectTab(int i) {
        if (this.f1403c.size() == 0) {
            return;
        }
        if (i < 0 || i >= 32) {
            i = 0;
        }
        this.f1409j = i;
        this.f1402b.check(i);
        this.f1408i.removeAllViews();
        this.f1408i.addView(this.f1403c.get(i));
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public final void onCheckedChanged(RadioGroup radioGroup, int i) {
        setSelectTab(i);
    }
}

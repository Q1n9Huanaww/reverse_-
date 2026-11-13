package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.cyjh.mobileanjian.ipc.view.ExToast;
import com.cyjh.mqsdk.C0852R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.k */
/* JADX INFO: compiled from: UiShowLayout.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0790k {

    /* JADX INFO: renamed from: d */
    private static final int f1314d = 4096;

    /* JADX INFO: renamed from: e */
    private static final String f1315e = "first_row_tag";

    /* JADX INFO: renamed from: f */
    private static final String f1316f = "row_mark";

    /* JADX INFO: renamed from: v */
    private static final int f1317v = 256;

    /* JADX INFO: renamed from: x */
    private static int f1318x;

    /* JADX INFO: renamed from: a */
    public View.OnClickListener f1319a;

    /* JADX INFO: renamed from: b */
    public View.OnClickListener f1320b;

    /* JADX INFO: renamed from: g */
    private WindowManager f1322g;

    /* JADX INFO: renamed from: i */
    private ExToast f1324i;

    /* JADX INFO: renamed from: j */
    private Context f1325j;

    /* JADX INFO: renamed from: k */
    private LinearLayout f1326k;

    /* JADX INFO: renamed from: l */
    private String f1327l;

    /* JADX INFO: renamed from: q */
    private C0786g f1332q;

    /* JADX INFO: renamed from: r */
    private LinearLayout f1333r;

    /* JADX INFO: renamed from: s */
    private LinearLayout f1334s;

    /* JADX INFO: renamed from: t */
    private TextView f1335t;

    /* JADX INFO: renamed from: m */
    private Set<String> f1328m = new HashSet();

    /* JADX INFO: renamed from: n */
    private Set<String> f1329n = new HashSet();

    /* JADX INFO: renamed from: o */
    private HashMap<String, String> f1330o = new HashMap<>();

    /* JADX INFO: renamed from: p */
    private HashMap<String, String> f1331p = new HashMap<>();

    /* JADX INFO: renamed from: c */
    public boolean f1321c = false;

    /* JADX INFO: renamed from: u */
    private ArrayList<SlidingTabLayout> f1336u = new ArrayList<>();

    /* JADX INFO: renamed from: w */
    private Handler f1337w = new Handler() { // from class: com.cyjh.mobileanjian.ipc.ui.k.3
        @Override // android.os.Handler
        public final void handleMessage(Message message) {
            if (message.what != 256) {
                return;
            }
            C0790k.this.f1326k.invalidate();
        }
    };

    /* JADX INFO: renamed from: h */
    private WindowManager.LayoutParams f1323h = new WindowManager.LayoutParams();

    public C0790k(Context context, String str, int i, int i2) {
        this.f1327l = null;
        this.f1325j = context;
        this.f1332q = C0786g.m851a(context);
        this.f1322g = (WindowManager) this.f1325j.getSystemService("window");
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1323h.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1323h.type = 2005;
        } else {
            this.f1323h.type = 2002;
        }
        this.f1323h.format = 1;
        this.f1323h.gravity = 16;
        this.f1323h.x = 0;
        this.f1323h.y = 0;
        this.f1323h.alpha = 1.0f;
        this.f1323h.width = -1;
        this.f1323h.height = -1;
        DisplayMetrics displayMetrics = this.f1325j.getResources().getDisplayMetrics();
        if (m989c(40) + i >= displayMetrics.widthPixels) {
            i = -1;
        } else if (i < -2 || (i >= 0 && i < m989c(200))) {
            i = m989c(200);
        }
        if (m989c(40) + i2 >= displayMetrics.heightPixels) {
            i2 = -2;
        } else if (i2 < -2 || (i2 >= 0 && i2 < m989c(200))) {
            i2 = m989c(200);
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1325j);
        this.f1334s = (LinearLayout) layoutInflaterFrom.inflate(C0852R.layout.layout_ui_show, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) layoutInflaterFrom.inflate(C0852R.layout.layout_display, (ViewGroup) null);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(i, i2));
        this.f1334s.setGravity(17);
        this.f1334s.addView(linearLayout);
        this.f1335t = (TextView) this.f1334s.findViewById(C0852R.id.title);
        this.f1326k = (LinearLayout) this.f1334s.findViewById(C0852R.id.layout_user);
        this.f1334s.findViewById(C0852R.id.exit).setOnClickListener(new AnonymousClass1());
        this.f1334s.findViewById(C0852R.id.next).setOnClickListener(new AnonymousClass2());
        this.f1327l = str;
        m1000a(str);
        m1002a(f1316f, str, -1, -2);
    }

    /* JADX INFO: renamed from: a */
    public final void m997a(Typeface typeface, int i) {
        this.f1335t.setTypeface(typeface);
        this.f1335t.setTextSize(2, i);
    }

    /* JADX INFO: renamed from: a */
    public final void m1000a(String str) {
        this.f1335t.setText(str);
    }

    /* JADX INFO: renamed from: a */
    public final String m995a() {
        return this.f1335t.getText().toString();
    }

    /* JADX INFO: renamed from: a */
    public final void m996a(int i) {
        this.f1335t.setTextColor(i);
    }

    /* JADX INFO: renamed from: b */
    public final void m1006b(int i) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i);
        gradientDrawable.setCornerRadii(new float[]{m989c(4), m989c(4), m989c(4), m989c(4), 0.0f, 0.0f, 0.0f, 0.0f});
        this.f1335t.setBackground(gradientDrawable);
    }

    /* JADX INFO: renamed from: f */
    private String m992f() {
        return this.f1327l;
    }

    /* JADX INFO: renamed from: c */
    private void m991c(String str) {
        this.f1327l = str;
        m1000a(str);
    }

    /* JADX INFO: renamed from: b */
    public final View m1004b(String str) {
        View viewFindViewWithTag = this.f1326k.findViewWithTag(str);
        if (viewFindViewWithTag == null) {
            Iterator<SlidingTabLayout> it2 = this.f1336u.iterator();
            while (it2.hasNext()) {
                View viewFindViewWithTag2 = null;
                Iterator<LinearLayout> it3 = ((C0791l) it2.next().getViewPager().getAdapter()).f1343a.iterator();
                while (it3.hasNext() && (viewFindViewWithTag2 = it3.next().findViewWithTag(str)) == null) {
                }
                viewFindViewWithTag = viewFindViewWithTag2;
                if (viewFindViewWithTag != null) {
                    break;
                }
            }
        }
        return viewFindViewWithTag;
    }

    /* JADX INFO: renamed from: a */
    private void m987a(int i, int i2) {
        DisplayMetrics displayMetrics = this.f1325j.getResources().getDisplayMetrics();
        if (m989c(40) + i >= displayMetrics.widthPixels) {
            i = -1;
        } else if (i < -2 || (i >= 0 && i < m989c(200))) {
            i = m989c(200);
        }
        if (m989c(40) + i2 >= displayMetrics.heightPixels) {
            i2 = -2;
        } else if (i2 < -2 || (i2 >= 0 && i2 < m989c(200))) {
            i2 = m989c(200);
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.f1325j);
        this.f1334s = (LinearLayout) layoutInflaterFrom.inflate(C0852R.layout.layout_ui_show, (ViewGroup) null);
        LinearLayout linearLayout = (LinearLayout) layoutInflaterFrom.inflate(C0852R.layout.layout_display, (ViewGroup) null);
        linearLayout.setLayoutParams(new RelativeLayout.LayoutParams(i, i2));
        this.f1334s.setGravity(17);
        this.f1334s.addView(linearLayout);
        this.f1335t = (TextView) this.f1334s.findViewById(C0852R.id.title);
        this.f1326k = (LinearLayout) this.f1334s.findViewById(C0852R.id.layout_user);
        this.f1334s.findViewById(C0852R.id.exit).setOnClickListener(new AnonymousClass1());
        this.f1334s.findViewById(C0852R.id.next).setOnClickListener(new AnonymousClass2());
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.k$1, reason: invalid class name */
    /* JADX INFO: compiled from: UiShowLayout.java */
    final class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (C0790k.this.f1319a != null) {
                C0790k.this.f1319a.onClick(view);
            }
        }
    }

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.k$2, reason: invalid class name */
    /* JADX INFO: compiled from: UiShowLayout.java */
    final class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            if (C0790k.this.f1320b != null) {
                C0790k.this.f1320b.onClick(view);
            }
        }
    }

    /* JADX INFO: renamed from: g */
    private void m993g() {
        this.f1322g = (WindowManager) this.f1325j.getSystemService("window");
        this.f1323h = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1323h.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1323h.type = 2005;
        } else {
            this.f1323h.type = 2002;
        }
        this.f1323h.format = 1;
        this.f1323h.gravity = 16;
        this.f1323h.x = 0;
        this.f1323h.y = 0;
        this.f1323h.alpha = 1.0f;
        this.f1323h.width = -1;
        this.f1323h.height = -1;
    }

    /* JADX INFO: renamed from: b */
    public final void m1005b() {
        this.f1337w.sendEmptyMessageDelayed(256, 10L);
    }

    /* JADX INFO: renamed from: c */
    public final void m1008c() {
        this.f1321c = true;
        this.f1322g.addView(this.f1334s, this.f1323h);
    }

    /* JADX INFO: renamed from: d */
    public final boolean m1009d() {
        return this.f1321c;
    }

    /* JADX INFO: renamed from: e */
    public final void m1010e() {
        this.f1321c = false;
        this.f1322g.removeView(this.f1334s);
    }

    /* JADX INFO: renamed from: c */
    private int m989c(int i) {
        return (int) (i * (this.f1325j.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* JADX INFO: renamed from: a */
    public final void m999a(View view, String str) {
        new StringBuilder("row tag: ").append(this.f1330o.get(str));
        if (str.equals(this.f1327l)) {
            this.f1333r.addView(view);
        } else if (this.f1328m.contains(str)) {
            ((C0791l) ((SlidingTabLayout) this.f1326k.findViewWithTag(this.f1331p.get(str))).getViewPager().getAdapter()).m1015a(view, str, this.f1330o.get(str));
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m1001a(String str, int i) {
        this.f1329n.add(str);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f1325j).inflate(C0852R.layout.ui_show_sliding_layout, (ViewGroup) null);
        final SlidingTabLayout slidingTabLayout = (SlidingTabLayout) linearLayout.findViewById(C0852R.id.slidingTabLayout);
        slidingTabLayout.setTag(str);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(this.f1325j.getResources().getColor(C0852R.color.ui_show_blue));
        slidingTabLayout.setBackgroundResource(C0852R.drawable.bg_tab_host);
        slidingTabLayout.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.cyjh.mobileanjian.ipc.ui.k.4
            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrollStateChanged(int i2) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageScrolled(int i2, float f, int i3) {
            }

            @Override // android.support.v4.view.ViewPager.OnPageChangeListener
            public final void onPageSelected(int i2) {
                slidingTabLayout.setFocusable(true);
                slidingTabLayout.setFocusableInTouchMode(true);
                slidingTabLayout.getViewPager().requestFocus();
            }
        });
        if (i == -2) {
            i = m989c(200);
        }
        ViewPager viewPager = (ViewPager) linearLayout.findViewById(C0852R.id.viewPager);
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, i));
        viewPager.setAdapter(new C0791l(new ArrayList(), new ArrayList()));
        slidingTabLayout.setViewPager(viewPager);
        this.f1326k.addView(linearLayout);
        this.f1336u.add(slidingTabLayout);
    }

    /* JADX INFO: renamed from: h */
    private static String m994h() {
        StringBuilder sb = new StringBuilder("first_row_");
        int i = f1318x;
        f1318x = i + 1;
        sb.append(i);
        return sb.toString();
    }

    /* JADX INFO: renamed from: a */
    public final boolean m1003a(int i, String str, String str2, String str3) {
        if (!this.f1329n.contains(str2)) {
            return false;
        }
        StringBuilder sb = new StringBuilder("first_row_");
        int i2 = f1318x;
        f1318x = i2 + 1;
        sb.append(i2);
        String string = sb.toString();
        this.f1328m.add(str);
        this.f1330o.put(str, string);
        this.f1331p.put(str, str2);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout) this.f1326k.findViewWithTag(str2);
        LinearLayout linearLayout = (LinearLayout) this.f1332q.f1237a.inflate(C0852R.layout.ui_show_tab_layout, (ViewGroup) null);
        linearLayout.setId(i);
        linearLayout.setTag(str);
        linearLayout.setId(i);
        this.f1333r = this.f1332q.m872c(string, -1, -2);
        linearLayout.addView(this.f1333r);
        C0791l c0791l = (C0791l) slidingTabLayout.f1172a.getAdapter();
        c0791l.f1343a.add(linearLayout);
        c0791l.f1344b.add(str3);
        c0791l.notifyDataSetChanged();
        slidingTabLayout.f1172a.setAdapter(c0791l);
        slidingTabLayout.setViewPager(slidingTabLayout.f1172a);
        return true;
    }

    /* JADX INFO: renamed from: a */
    public final void m998a(View.OnClickListener onClickListener) {
        this.f1319a = onClickListener;
    }

    /* JADX INFO: renamed from: b */
    public final void m1007b(View.OnClickListener onClickListener) {
        this.f1320b = onClickListener;
    }

    /* JADX INFO: renamed from: a */
    public final void m1002a(String str, String str2, int i, int i2) {
        if (str2.equals(this.f1327l)) {
            this.f1333r = this.f1332q.m872c(f1316f, i, i2);
            this.f1326k.addView(this.f1333r);
            this.f1330o.put(str2, str);
        } else if (this.f1328m.contains(str2)) {
            for (LinearLayout linearLayout : ((C0791l) ((SlidingTabLayout) this.f1326k.findViewWithTag(this.f1331p.get(str2))).getViewPager().getAdapter()).f1343a) {
                if (linearLayout.getTag().equals(str2)) {
                    linearLayout.addView(C0786g.m851a(linearLayout.getContext()).m872c(str, i, i2));
                }
            }
            this.f1330o.put(str2, str);
        }
    }
}

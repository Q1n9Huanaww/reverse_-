package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cyjh.mqsdk.C0852R;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.b */
/* JADX INFO: compiled from: FloatAlertDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0781b {

    /* JADX INFO: renamed from: a */
    public static final int f1183a = 0;

    /* JADX INFO: renamed from: b */
    public static final int f1184b = 1;

    /* JADX INFO: renamed from: c */
    public static final int f1185c = 2;

    /* JADX INFO: renamed from: d */
    public static final int f1186d = 3;

    /* JADX INFO: renamed from: e */
    public static final int f1187e = 0;

    /* JADX INFO: renamed from: f */
    public static final int f1188f = 1;

    /* JADX INFO: renamed from: g */
    public static final int f1189g = 2;

    /* JADX INFO: renamed from: h */
    public static final int f1190h = 3;

    /* JADX INFO: renamed from: i */
    a f1191i;

    /* JADX INFO: renamed from: j */
    private WindowManager f1192j;

    /* JADX INFO: renamed from: k */
    private WindowManager.LayoutParams f1193k;

    /* JADX INFO: renamed from: l */
    private View f1194l;

    /* JADX INFO: renamed from: m */
    private int f1195m;

    /* JADX INFO: renamed from: n */
    private int f1196n;

    /* JADX INFO: renamed from: o */
    private LinearLayout f1197o;

    /* JADX INFO: renamed from: p */
    private LinearLayout f1198p;

    /* JADX INFO: renamed from: q */
    private Button f1199q;

    /* JADX INFO: renamed from: r */
    private Button f1200r;

    /* JADX INFO: renamed from: s */
    private Button f1201s;

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.b$a */
    /* JADX INFO: compiled from: FloatAlertDialog.java */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo648a(int i);
    }

    public C0781b(Context context, String str, final int i, a aVar) {
        this.f1191i = aVar;
        this.f1192j = (WindowManager) context.getSystemService("window");
        this.f1194l = LayoutInflater.from(context).inflate(C0852R.layout.dialog_alert, (ViewGroup) null);
        ((TextView) this.f1194l.findViewById(C0852R.id.prompt)).setText(str);
        this.f1193k = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1193k.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1193k.type = 2005;
        } else {
            this.f1193k.type = 2002;
        }
        this.f1193k.format = 1;
        this.f1193k.gravity = 16;
        this.f1193k.x = 0;
        this.f1193k.y = 0;
        this.f1193k.alpha = 1.0f;
        this.f1193k.width = -1;
        this.f1193k.height = -1;
        switch (i) {
            case 1:
                this.f1194l.findViewById(C0852R.id.single_ok_button).setVisibility(8);
                this.f1194l.findViewById(C0852R.id.layout_type3).setVisibility(8);
                this.f1199q = (Button) this.f1194l.findViewById(C0852R.id.positive_button);
                this.f1200r = (Button) this.f1194l.findViewById(C0852R.id.negative_button);
                break;
            case 2:
                this.f1194l.findViewById(C0852R.id.single_ok_button).setVisibility(8);
                this.f1194l.findViewById(C0852R.id.layout_type3).setVisibility(8);
                this.f1199q = (Button) this.f1194l.findViewById(C0852R.id.positive_button);
                this.f1200r = (Button) this.f1194l.findViewById(C0852R.id.negative_button);
                this.f1199q.setText(C0852R.string.dialog_yes_button);
                this.f1200r.setText(C0852R.string.dialog_no_button);
                break;
            case 3:
                this.f1194l.findViewById(C0852R.id.single_ok_button).setVisibility(8);
                this.f1194l.findViewById(C0852R.id.layout_type2).setVisibility(8);
                this.f1199q = (Button) this.f1194l.findViewById(C0852R.id.yes_button);
                this.f1200r = (Button) this.f1194l.findViewById(C0852R.id.no_button);
                this.f1201s = (Button) this.f1194l.findViewById(C0852R.id.cancel_button);
                break;
            default:
                this.f1194l.findViewById(C0852R.id.layout_type2).setVisibility(8);
                this.f1194l.findViewById(C0852R.id.layout_type3).setVisibility(8);
                this.f1199q = (Button) this.f1194l.findViewById(C0852R.id.single_ok_button);
                break;
        }
        if (this.f1199q != null) {
            this.f1199q.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.b.1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (C0781b.this.f1191i != null) {
                        C0781b.this.f1191i.mo648a((i == 0 || i == 1) ? 0 : 2);
                    }
                    C0781b.m829a(C0781b.this);
                }
            });
        }
        if (this.f1200r != null) {
            this.f1200r.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.b.2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (C0781b.this.f1191i != null) {
                        C0781b.this.f1191i.mo648a(i != 1 ? 3 : 1);
                    }
                    C0781b.m829a(C0781b.this);
                }
            });
        }
        if (this.f1201s != null) {
            this.f1201s.setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.b.3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    if (C0781b.this.f1191i != null) {
                        C0781b.this.f1191i.mo648a(1);
                    }
                    C0781b.m829a(C0781b.this);
                }
            });
        }
    }

    /* JADX INFO: renamed from: a */
    public final void m833a() {
        this.f1192j.addView(this.f1194l, this.f1193k);
    }

    /* JADX INFO: renamed from: b */
    private void m831b() {
        this.f1192j.removeView(this.f1194l);
    }

    /* JADX INFO: renamed from: c */
    private void m832c() {
        this.f1193k = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1193k.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1193k.type = 2005;
        } else {
            this.f1193k.type = 2002;
        }
        this.f1193k.format = 1;
        this.f1193k.gravity = 16;
        this.f1193k.x = 0;
        this.f1193k.y = 0;
        this.f1193k.alpha = 1.0f;
        this.f1193k.width = -1;
        this.f1193k.height = -1;
    }

    /* JADX INFO: renamed from: a */
    static /* synthetic */ void m829a(C0781b c0781b) {
        c0781b.f1192j.removeView(c0781b.f1194l);
    }
}

package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import com.cyjh.mqsdk.C0852R;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.c */
/* JADX INFO: compiled from: FloatInputDialog.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0782c {

    /* JADX INFO: renamed from: a */
    View f1207a;

    /* JADX INFO: renamed from: b */
    EditText f1208b;

    /* JADX INFO: renamed from: c */
    a f1209c;

    /* JADX INFO: renamed from: d */
    WindowManager f1210d;

    /* JADX INFO: renamed from: e */
    private WindowManager.LayoutParams f1211e;

    /* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.c$a */
    /* JADX INFO: compiled from: FloatInputDialog.java */
    public interface a {
        /* JADX INFO: renamed from: a */
        void mo649a(String str);
    }

    public C0782c(Context context, String str, a aVar) {
        this.f1209c = aVar;
        this.f1207a = LayoutInflater.from(context).inflate(C0852R.layout.dialog_input, (ViewGroup) null);
        ((TextView) this.f1207a.findViewById(C0852R.id.prompt)).setText(str);
        this.f1208b = (EditText) this.f1207a.findViewById(C0852R.id.et_input);
        this.f1207a.findViewById(C0852R.id.single_ok_button).setOnClickListener(new View.OnClickListener() { // from class: com.cyjh.mobileanjian.ipc.ui.c.1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                if (C0782c.this.f1209c != null) {
                    C0782c.this.f1209c.mo649a(C0782c.this.f1208b.getText().toString());
                }
                C0782c c0782c = C0782c.this;
                c0782c.f1210d.removeView(c0782c.f1207a);
            }
        });
        this.f1210d = (WindowManager) context.getSystemService("window");
        this.f1211e = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1211e.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1211e.type = 2005;
        } else {
            this.f1211e.type = 2002;
        }
        this.f1211e.format = 1;
        this.f1211e.gravity = 17;
        this.f1211e.x = 0;
        this.f1211e.y = 0;
        this.f1211e.alpha = 1.0f;
        this.f1211e.width = -1;
        this.f1211e.height = -1;
    }

    /* JADX INFO: renamed from: a */
    public final void m839a() {
        this.f1210d.addView(this.f1207a, this.f1211e);
    }

    /* JADX INFO: renamed from: b */
    private void m836b() {
        this.f1210d.removeView(this.f1207a);
    }

    /* JADX INFO: renamed from: c */
    private void m837c() {
        this.f1211e = new WindowManager.LayoutParams();
        if (Build.VERSION.SDK_INT >= 26) {
            this.f1211e.type = 2038;
        } else if (Build.VERSION.SDK_INT >= 19 && Build.VERSION.SDK_INT < 24) {
            this.f1211e.type = 2005;
        } else {
            this.f1211e.type = 2002;
        }
        this.f1211e.format = 1;
        this.f1211e.gravity = 17;
        this.f1211e.x = 0;
        this.f1211e.y = 0;
        this.f1211e.alpha = 1.0f;
        this.f1211e.width = -1;
        this.f1211e.height = -1;
    }
}

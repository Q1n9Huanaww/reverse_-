package com.cyjh.mobileanjian.ipc.p018ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.cyjh.mqsdk.C0852R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;
import java.util.List;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.ui.g */
/* JADX INFO: compiled from: UiFactory.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0786g {

    /* JADX INFO: renamed from: a */
    LayoutInflater f1237a;

    /* JADX INFO: renamed from: b */
    private Context f1238b;

    private C0786g(Context context) {
        this.f1238b = context;
        this.f1237a = LayoutInflater.from(this.f1238b);
    }

    /* JADX INFO: renamed from: a */
    private int m847a(int i) {
        return (int) (i * (this.f1238b.getResources().getDisplayMetrics().densityDpi / 160.0f));
    }

    /* JADX INFO: renamed from: a */
    private LinearLayout.LayoutParams m848a(int i, int i2) {
        return m855b(i, i2);
    }

    /* JADX INFO: renamed from: b */
    private LinearLayout.LayoutParams m855b(int i, int i2) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        if (i != -2 && i2 != -2) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        layoutParams.setMargins(0, 0, m847a(8), 0);
        return layoutParams;
    }

    /* JADX INFO: renamed from: a */
    private int m846a(float f) {
        return (int) (((double) (this.f1238b.getResources().getDisplayMetrics().density * f)) + 0.5d);
    }

    /* JADX INFO: renamed from: b */
    private int m853b(float f) {
        return (int) (((double) (f / this.f1238b.getResources().getDisplayMetrics().density)) + 0.5d);
    }

    /* JADX INFO: renamed from: a */
    public static C0786g m851a(Context context) {
        return new C0786g(context);
    }

    /* JADX INFO: renamed from: a */
    private C0790k m852a(String str) {
        return m868a(str, -1, -2);
    }

    /* JADX INFO: renamed from: a */
    public final C0790k m868a(String str, int i, int i2) {
        return new C0790k(this.f1238b, str, i, i2);
    }

    /* JADX INFO: renamed from: b */
    public final LinearLayout m870b(String str, int i, int i2) {
        LinearLayout linearLayout = new LinearLayout(this.f1238b);
        linearLayout.setTag(str);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(-16777216);
        if (i == -1) {
            i = 3000;
        }
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        return linearLayout;
    }

    /* JADX INFO: renamed from: c */
    public final LinearLayout m872c(String str, int i, int i2) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.f1238b).inflate(C0852R.layout.ui_show_row, (ViewGroup) null);
        linearLayout.setOrientation(0);
        linearLayout.setTag(str);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        return linearLayout;
    }

    /* JADX INFO: renamed from: a */
    private TextView m850a(String str, String str2) {
        return m866a(str, str2, -2, -2);
    }

    /* JADX INFO: renamed from: e */
    private TextView m857e(String str, String str2, int i, int i2) {
        TextView textView = (TextView) this.f1237a.inflate(C0852R.layout.ui_show_text_view, (ViewGroup) null);
        textView.setTag(str);
        textView.setText(str2);
        textView.setLayoutParams(m855b(i, i2));
        return textView;
    }

    /* JADX INFO: renamed from: f */
    private TextView m858f(String str, String str2, int i, int i2) {
        TextView textView = (TextView) this.f1237a.inflate(C0852R.layout.ui_float_text_view, (ViewGroup) null);
        textView.setTag(str);
        textView.setText(str2);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (i != -2 && i2 != -2) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        textView.setLayoutParams(layoutParams);
        return textView;
    }

    /* JADX INFO: renamed from: b */
    private Button m854b(String str, String str2) {
        return m869b(str, str2, -2, -2);
    }

    /* JADX INFO: renamed from: g */
    private Button m859g(String str, String str2, int i, int i2) {
        Button button = (Button) this.f1237a.inflate(C0852R.layout.ui_show_button, (ViewGroup) null);
        button.setTag(str);
        button.setText(str2);
        button.setAllCaps(false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.setMargins(0, 0, m847a(8), 0);
        button.setLayoutParams(layoutParams);
        button.setBackgroundResource(C0852R.drawable.bg_default_button);
        return button;
    }

    /* JADX INFO: renamed from: h */
    private Button m860h(String str, String str2, int i, int i2) {
        RelativeLayout.LayoutParams layoutParams;
        Button button = (Button) this.f1237a.inflate(C0852R.layout.ui_float_button, (ViewGroup) null);
        button.setTag(str);
        button.setText(str2);
        button.setAllCaps(false);
        if (i == 0 && i2 == 0) {
            layoutParams = new RelativeLayout.LayoutParams(-1, 100);
        } else {
            layoutParams = new RelativeLayout.LayoutParams(i, i2);
        }
        button.setLayoutParams(layoutParams);
        return button;
    }

    /* JADX INFO: renamed from: c */
    public final ImageView m871c(String str, String str2, int i, int i2) {
        Bitmap bitmapDecodeStream;
        ImageView imageView = (ImageView) this.f1237a.inflate(C0852R.layout.ui_show_image_view, (ViewGroup) null);
        imageView.setTag(str);
        try {
            bitmapDecodeStream = BitmapFactory.decodeStream(new FileInputStream(str2));
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            bitmapDecodeStream = null;
        }
        if (bitmapDecodeStream != null) {
            imageView.setImageBitmap(bitmapDecodeStream);
        }
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (i != -2 && i2 != -2) {
            layoutParams.width = i;
            layoutParams.height = i2;
        }
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }

    /* JADX INFO: renamed from: c */
    private EditText m856c(String str, String str2) {
        return m873d(str, str2, -2, -2);
    }

    /* JADX INFO: renamed from: d */
    public final EditText m873d(String str, String str2, int i, int i2) {
        EditText editText = (EditText) this.f1237a.inflate(C0852R.layout.ui_show_edit_text, (ViewGroup) null);
        editText.setTag(str);
        editText.setText(str2);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.setMargins(0, 0, m847a(8), 0);
        editText.setLayoutParams(layoutParams);
        editText.setBackgroundResource(C0852R.drawable.bg_edit_text);
        return editText;
    }

    /* JADX INFO: renamed from: a */
    public final CheckBox m862a(String str, String str2, int i, int i2, boolean z) {
        CheckBox checkBox = (CheckBox) LayoutInflater.from(this.f1238b).inflate(C0852R.layout.ui_show_checkbox, (ViewGroup) null);
        checkBox.setTag(str);
        checkBox.setText(str2);
        checkBox.setChecked(z);
        checkBox.setLayoutParams(m855b(i, i2));
        checkBox.setPadding(0, 0, 0, 0);
        return checkBox;
    }

    /* JADX INFO: renamed from: a */
    public final RadioGroup m864a(String str, List<String> list, int i, int i2, int i3) {
        RadioGroup radioGroup = new RadioGroup(this.f1238b);
        radioGroup.setTag(str);
        radioGroup.setLayoutParams(m855b(i2, i3));
        for (int i4 = 0; i4 < list.size(); i4++) {
            RadioButton radioButton = (RadioButton) this.f1237a.inflate(C0852R.layout.ui_show_radio_button, (ViewGroup) null);
            radioButton.setId(i4);
            radioButton.setText(list.get(i4));
            radioButton.setLayoutParams(m855b(-2, -2));
            radioButton.setPadding(0, m847a(5), 0, m847a(5));
            radioGroup.addView(radioButton);
            if (i4 >= 100) {
                break;
            }
        }
        radioGroup.clearCheck();
        radioGroup.check(i < radioGroup.getChildCount() ? i : 0);
        return radioGroup;
    }

    /* JADX INFO: renamed from: a */
    public final WebView m861a(String str, int i, int i2, String str2) {
        if (!str2.startsWith("http://") && !str2.startsWith("https://")) {
            str2 = "http://" + str2;
        }
        WebView webView = new WebView(this.f1238b);
        webView.setTag(str);
        webView.setLayoutParams(new LinearLayout.LayoutParams(i, i2));
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(str2);
        webView.setWebViewClient(new WebViewClient() { // from class: com.cyjh.mobileanjian.ipc.ui.g.1
            @Override // android.webkit.WebViewClient
            public final boolean shouldOverrideUrlLoading(WebView webView2, String str3) {
                webView2.loadUrl(str3);
                return true;
            }
        });
        return webView;
    }

    /* JADX INFO: renamed from: a */
    public final ImageView m863a(int i, String str, int i2, int i3, String str2) {
        ImageView imageView = new ImageView(this.f1238b);
        imageView.setId(i);
        imageView.setTag(str);
        imageView.setLayoutParams(m855b(i2, i3));
        imageView.setImageURI(Uri.fromFile(new File(str2)));
        return imageView;
    }

    /* JADX INFO: renamed from: a */
    public final Spinner m865a(String str, List<String> list, int i) {
        Spinner spinner = (Spinner) this.f1237a.inflate(C0852R.layout.ui_show_spinner, (ViewGroup) null);
        spinner.setTag(str);
        spinner.setLayoutParams(m855b(-2, -2));
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.f1238b, C0852R.layout.ui_show_spinner_dropdown_item, list);
        spinner.setAdapter((SpinnerAdapter) arrayAdapter);
        if (i >= arrayAdapter.getCount()) {
            i = 0;
        }
        spinner.setSelection(i);
        return spinner;
    }

    /* JADX INFO: renamed from: a */
    private LinearLayout m849a(int i, String str) {
        LinearLayout linearLayout = (LinearLayout) this.f1237a.inflate(C0852R.layout.ui_show_tab_layout, (ViewGroup) null);
        linearLayout.setId(i);
        linearLayout.setTag(str);
        return linearLayout;
    }

    /* JADX INFO: renamed from: a */
    public final C0789j m867a(String str, int i, int i2, int i3, int i4) {
        return new C0789j(this.f1238b, str, i, i2, i3, i4);
    }

    /* JADX INFO: renamed from: a */
    public final TextView m866a(String str, String str2, int i, int i2) {
        TextView textView = (TextView) this.f1237a.inflate(C0852R.layout.ui_show_text_view, (ViewGroup) null);
        textView.setTag(str);
        textView.setText(str2);
        textView.setLayoutParams(m855b(i, i2));
        return textView;
    }

    /* JADX INFO: renamed from: b */
    public final Button m869b(String str, String str2, int i, int i2) {
        Button button = (Button) this.f1237a.inflate(C0852R.layout.ui_show_button, (ViewGroup) null);
        button.setTag(str);
        button.setText(str2);
        button.setAllCaps(false);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i, i2);
        layoutParams.setMargins(0, 0, m847a(8), 0);
        button.setLayoutParams(layoutParams);
        button.setBackgroundResource(C0852R.drawable.bg_default_button);
        return button;
    }
}

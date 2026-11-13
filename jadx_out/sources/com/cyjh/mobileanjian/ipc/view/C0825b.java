package com.cyjh.mobileanjian.ipc.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import com.cyjh.mqsdk.C0852R;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: renamed from: com.cyjh.mobileanjian.ipc.view.b */
/* JADX INFO: compiled from: ShowTapPic.java */
/* JADX INFO: loaded from: classes.dex */
public final class C0825b {

    /* JADX INFO: renamed from: a */
    WindowManager f1492a;

    /* JADX INFO: renamed from: b */
    View f1493b;

    /* JADX INFO: renamed from: c */
    private Handler f1494c;

    /* JADX INFO: renamed from: d */
    private Context f1495d;

    /* JADX INFO: renamed from: e */
    private Handler.Callback f1496e = new Handler.Callback() { // from class: com.cyjh.mobileanjian.ipc.view.b.1
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            C0825b.this.f1492a.removeView(C0825b.this.f1493b);
            return false;
        }
    };

    public C0825b(Context context) {
        this.f1495d = context;
        this.f1492a = (WindowManager) context.getSystemService("window");
    }

    /* JADX INFO: renamed from: a */
    public final void m1149a(int i, int i2, String str) {
        Bitmap bitmapDecodeStream;
        try {
            if (new File(str).exists() && (bitmapDecodeStream = BitmapFactory.decodeStream(new FileInputStream(str))) != null) {
                int width = bitmapDecodeStream.getWidth();
                int height = bitmapDecodeStream.getHeight();
                WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                if (Build.VERSION.SDK_INT >= 26) {
                    layoutParams.type = 2038;
                } else {
                    layoutParams.type = 2002;
                }
                layoutParams.format = 1;
                layoutParams.gravity = 51;
                layoutParams.flags = 792;
                layoutParams.width = width;
                layoutParams.height = height;
                layoutParams.x = i - (width / 2);
                layoutParams.y = i2 - (height / 2);
                this.f1494c = new Handler(this.f1495d.getMainLooper(), this.f1496e);
                this.f1493b = LayoutInflater.from(this.f1495d).inflate(C0852R.layout.ui_show_image, (ViewGroup) null);
                ((ImageView) this.f1493b.findViewById(C0852R.id.tap_imageview)).setImageBitmap(bitmapDecodeStream);
                this.f1492a.addView(this.f1493b, layoutParams);
                this.f1494c.sendEmptyMessageDelayed(0, 1000L);
            }
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
        }
    }
}

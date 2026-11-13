package com.example.keyverificationonline;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes2.dex */
public class ToastUtil {
    private static WeakReference<Toast> nextToast;
    private static WeakReference<Toast> preToast;

    public static void show(Context context, String text, int duration) {
        Toast toast = Toast.makeText(context, text, duration);
        toast.getView().addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() { // from class: com.example.keyverificationonline.ToastUtil.1
            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewAttachedToWindow(View v) {
            }

            @Override // android.view.View.OnAttachStateChangeListener
            public void onViewDetachedFromWindow(View v) {
                if (ToastUtil.nextToast == null || ToastUtil.nextToast.get() == null) {
                    WeakReference unused = ToastUtil.preToast = null;
                } else {
                    ((Toast) ToastUtil.nextToast.get()).show();
                    WeakReference unused2 = ToastUtil.nextToast = null;
                }
            }
        });
        if (preToast == null || preToast.get() == null) {
            toast.show();
            preToast = new WeakReference<>(toast);
        } else {
            nextToast = new WeakReference<>(toast);
            preToast.get().cancel();
        }
    }
}

package com.p024ds.daisi.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class ToastUtils {
    public static void showToastShort(Context context, String str) {
        Toast.makeText(context, str, 0).show();
    }

    public static void showToastShort(Context context, int i) {
        Toast.makeText(context, i, 0).show();
    }

    public static void showToastLong(Context context, String str) {
        Toast.makeText(context, str, 1).show();
    }

    public static void showToastLong(Context context, int i) {
        Toast.makeText(context, i, 1).show();
    }

    public static void showTwoLinesText(Context context, String str, String str2) {
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.item_two_lines_toast, (ViewGroup) null, false);
        if (viewInflate == null) {
            return;
        }
        TextView textView = (TextView) viewInflate.findViewById(R.id.first_text);
        if (textView != null) {
            textView.setText(str);
        }
        TextView textView2 = (TextView) viewInflate.findViewById(R.id.second_text);
        if (textView2 != null) {
            textView2.setText(str2);
        }
        Toast toast = new Toast(context);
        toast.setGravity(17, 0, 600);
        toast.setDuration(1);
        toast.setView(viewInflate);
        toast.show();
    }
}

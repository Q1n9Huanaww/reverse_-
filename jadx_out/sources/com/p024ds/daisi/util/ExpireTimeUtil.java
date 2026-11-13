package com.p024ds.daisi.util;

import android.content.Context;
import android.content.res.Resources;
import com.warnship.autoplay.R;

/* JADX INFO: loaded from: classes.dex */
public class ExpireTimeUtil {
    public static final int MINUTE_OF_DAY = 1440;
    public static final int NUMBER_VALUE_60 = 60;

    public static String convertTimeFormat(Context context, long j) {
        Resources resources = context.getResources();
        resources.getString(R.string.optionfragment_duetime_day);
        if (j >= 1440) {
            return String.format(resources.getString(R.string.optionfragment_duetime_day), Long.valueOf(j / 1440), Long.valueOf((j % 1440) / 60), Long.valueOf(j % 60));
        }
        if (j > 60 && j < 1440) {
            return String.format(resources.getString(R.string.optionfragment_duetime_hour), Long.valueOf(j / 60), Long.valueOf(j % 60));
        }
        if (j > 60 || j <= 0) {
            return j <= 0 ? resources.getString(R.string.optionfragment_duetime_no) : "";
        }
        return String.format(resources.getString(R.string.optionfragment_duetime_minute), Long.valueOf(j));
    }
}

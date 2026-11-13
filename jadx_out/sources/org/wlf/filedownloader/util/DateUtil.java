package org.wlf.filedownloader.util;

import android.text.TextUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: loaded from: classes2.dex */
public class DateUtil {
    private static final Map<String, ThreadLocal<SimpleDateFormat>> DATE_FORMAT_MAP = new ConcurrentHashMap();

    private static SimpleDateFormat getSimpleDateFormat(String format) {
        ThreadLocal<SimpleDateFormat> threadLocal = DATE_FORMAT_MAP.get(format);
        if (threadLocal == null) {
            threadLocal = new ThreadLocal<>();
            DATE_FORMAT_MAP.put(format, threadLocal);
        }
        SimpleDateFormat sdf = threadLocal.get();
        if (sdf == null) {
            SimpleDateFormat sdf2 = new SimpleDateFormat(format, Locale.getDefault());
            threadLocal.set(sdf2);
            return sdf2;
        }
        return sdf;
    }

    public static Date string2Date_yyyy_MM_dd_HH_mm_ss(String strDate) {
        if (TextUtils.isEmpty(strDate)) {
            return null;
        }
        SimpleDateFormat sdf = getSimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String date2String_yyyy_MM_dd_HH_mm_ss(Date date) {
        return getSimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }
}

package com.iflytek.voiceads.dex;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.iflytek.voiceads.config.SDKLogger;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;

/* JADX INFO: renamed from: com.iflytek.voiceads.dex.c */
/* JADX INFO: loaded from: classes.dex */
public class C1523c {
    /* JADX INFO: renamed from: a */
    public static String m2920a(Context context, String str) {
        String strM2928c = m2928c(context, str);
        String strM2929d = m2929d(context, str);
        String str2 = context.getFilesDir().getAbsolutePath() + File.separator;
        if (m2925b(strM2928c, str) > m2925b(strM2929d, str)) {
            m2922a(context, str2 + strM2928c, strM2928c);
        } else {
            strM2928c = strM2929d;
        }
        m2923a(context, str2, str, strM2928c);
        return strM2928c;
    }

    /* JADX INFO: renamed from: a */
    private static String m2921a(String[] strArr, String str) {
        int iIntValue = 0;
        if (strArr != null) {
            ArrayList arrayList = new ArrayList();
            for (String str2 : strArr) {
                if (m2925b(str2, str) > 0) {
                    arrayList.add(Integer.valueOf(m2925b(str2, str)));
                }
            }
            if (arrayList.size() > 0) {
                Collections.sort(arrayList);
                iIntValue = ((Integer) arrayList.get(arrayList.size() - 1)).intValue();
            }
        }
        return "AdDex." + str + "." + iIntValue + ".dex";
    }

    /* JADX INFO: renamed from: a */
    private static void m2922a(Context context, String str, String str2) {
        SDKLogger.m2899d("copyDexFile");
        try {
            InputStream inputStreamOpen = context.getAssets().open(str2);
            FileOutputStream fileOutputStream = new FileOutputStream(new File(str));
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStreamOpen.read(bArr);
                if (i <= 0) {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    inputStreamOpen.close();
                    return;
                }
                fileOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            SDKLogger.m2901e("copyDexFile:" + e.getMessage());
        }
    }

    /* JADX INFO: renamed from: a */
    private static void m2923a(Context context, String str, String str2, String str3) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        SharedPreferences sharedPreferences = context.getSharedPreferences("FLY_AD_SHARED", 0);
        if (jCurrentTimeMillis - sharedPreferences.getLong("checkUpdateTime", 0L) < 86400000) {
            SDKLogger.m2899d("less than 24h");
            return;
        }
        sharedPreferences.edit().putLong("checkUpdateTime", jCurrentTimeMillis).apply();
        new Thread(new RunnableC1524d("https://api.voiceads.cn/hotUpdate/?ver=" + str2, str2, str3, str)).start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static int m2925b(String str, String str2) {
        if (Pattern.matches("^AdDex." + str2 + "(\\.[0-9]{1,})\\.dex$", str)) {
            return Integer.valueOf(str.split("\\.")[3]).intValue();
        }
        return -1;
    }

    /* JADX INFO: renamed from: b */
    public static String m2926b(Context context, String str) {
        for (File file : context.getFilesDir().listFiles()) {
            if (m2925b(file.getName(), str) != -1) {
                SDKLogger.m2899d("delete " + file.getName());
                file.delete();
            }
        }
        String strM2928c = m2928c(context, str);
        String str2 = context.getFilesDir().getAbsolutePath() + File.separator;
        m2922a(context, str2 + strM2928c, strM2928c);
        m2923a(context, str2, str, strM2928c);
        return strM2928c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: b */
    public static void m2927b(String str, String str2, String str3) {
        new Thread(new RunnableC1525e(str2, str3, str)).start();
    }

    /* JADX INFO: renamed from: c */
    private static String m2928c(Context context, String str) {
        String[] list;
        try {
            list = context.getAssets().list("");
        } catch (IOException e) {
            ThrowableExtension.printStackTrace(e);
            list = null;
        }
        return m2921a(list, str);
    }

    /* JADX INFO: renamed from: d */
    private static String m2929d(Context context, String str) {
        return m2921a(new File(context.getFilesDir().getAbsolutePath() + File.separator).list(), str);
    }
}

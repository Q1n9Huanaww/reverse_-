package com.iflytek.voiceads.download;

import android.content.Context;
import android.os.Environment;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import com.iflytek.voiceads.utils.C1598l;
import java.io.File;

/* JADX INFO: renamed from: com.iflytek.voiceads.download.c */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1537c {

    /* JADX INFO: renamed from: a */
    private static final String f3227a = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "IFlyAdDownload";

    /* JADX INFO: renamed from: a */
    public static String m2960a(Context context) {
        try {
            File file = C1598l.m3210a(context, "android.permission.WRITE_EXTERNAL_STORAGE") ? new File(f3227a) : new File(context.getFilesDir().getAbsolutePath() + File.separator + "IFlyAdDownload");
            if (!file.exists()) {
                file.mkdir();
            }
            long length = 0;
            for (File file2 : file.listFiles()) {
                length += file2.length();
            }
            if (length > 314572800) {
                C1527a.m2932a(context).mo2940b();
                for (File file3 : file.listFiles()) {
                    file3.delete();
                }
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "get pre path:" + e.getMessage());
            return "";
        }
    }
}

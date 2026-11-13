package com.iflytek.voiceads.p063b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import com.iflytek.voiceads.config.SDKConstants;
import com.iflytek.voiceads.utils.C1593g;
import java.io.File;
import java.io.FileOutputStream;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: renamed from: com.iflytek.voiceads.b.d */
/* JADX INFO: loaded from: assets/AdDex.4.0.1.dex */
public class C1495d {

    /* JADX INFO: renamed from: a */
    private String f3065a;

    /* JADX INFO: renamed from: b */
    private Context f3066b;

    public C1495d(Context context, String str) {
        this.f3066b = context;
        this.f3065a = str;
    }

    /* JADX INFO: renamed from: a */
    private String m2842a(String str) {
        String[] strArrSplit = str.split(InternalZipConstants.ZIP_FILE_SEPARATOR);
        return strArrSplit[strArrSplit.length - 1];
    }

    /* JADX INFO: renamed from: b */
    private String m2843b() {
        return m2844c() + File.separator + "ifly_image_cache";
    }

    /* JADX INFO: renamed from: c */
    private String m2844c() {
        File cacheDir = this.f3066b.getCacheDir();
        return (cacheDir == null || TextUtils.isEmpty(cacheDir.getPath())) ? "" : cacheDir.getPath();
    }

    /* JADX INFO: renamed from: a */
    public Bitmap m2845a() {
        String str = m2844c() + File.separator + "ifly_image_cache" + File.separator + m2842a(this.f3065a) + ".cache";
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(str);
        if (!file.exists()) {
            return null;
        }
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str);
        if (bitmapDecodeFile != null) {
            return bitmapDecodeFile;
        }
        file.delete();
        return null;
    }

    /* JADX INFO: renamed from: a */
    public void m2846a(Bitmap bitmap) {
        if (bitmap == null) {
            return;
        }
        String str = m2842a(this.f3065a) + ".cache";
        String strM2843b = m2843b();
        File file = new File(strM2843b);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(strM2843b + File.separator + str);
        try {
            file2.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file2);
            if (this.f3065a.contains("png")) {
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            } else if (this.f3065a.contains("jpg")) {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
            }
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            C1593g.m3190b(SDKConstants.TAG, "saveBitmap:" + e.getMessage());
        }
    }
}

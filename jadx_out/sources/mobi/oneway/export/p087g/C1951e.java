package mobi.oneway.export.p087g;

import android.text.TextUtils;
import java.util.zip.CRC32;

/* JADX INFO: renamed from: mobi.oneway.export.g.e */
/* JADX INFO: loaded from: classes.dex */
public class C1951e {
    /* JADX INFO: renamed from: a */
    public static Long m4984a(String str) {
        long value;
        if (TextUtils.isEmpty(str)) {
            value = 0;
        } else {
            CRC32 crc32 = new CRC32();
            crc32.update(str.getBytes());
            value = crc32.getValue();
        }
        return Long.valueOf(value);
    }
}

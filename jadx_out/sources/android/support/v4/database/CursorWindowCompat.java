package android.support.v4.database;

import android.database.CursorWindow;
import android.os.Build;

/* JADX INFO: loaded from: classes2.dex */
public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    public static CursorWindow create(String name, long windowSizeBytes) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new CursorWindow(name, windowSizeBytes);
        }
        return new CursorWindow(name);
    }
}

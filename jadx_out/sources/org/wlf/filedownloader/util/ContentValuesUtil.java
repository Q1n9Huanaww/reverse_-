package org.wlf.filedownloader.util;

import android.content.ContentValues;

/* JADX INFO: loaded from: classes2.dex */
public class ContentValuesUtil {
    public static boolean isEmpty(ContentValues values) {
        return values == null || values.size() == 0;
    }
}

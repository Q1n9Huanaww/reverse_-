package org.wlf.filedownloader.util;

import java.util.Collection;

/* JADX INFO: loaded from: classes2.dex */
public class CollectionUtil {
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty() || collection.size() == 0;
    }
}

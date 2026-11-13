package com.p024ds.daisi.util;

import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public class CollectionUtils {
    public static boolean isEmpty(Collection<? extends Object> collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection<? extends Object> collection) {
        return collection != null && collection.size() > 0;
    }
}

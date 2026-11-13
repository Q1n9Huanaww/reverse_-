package com.cyjh.share.util;

import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class GsonExUtil {
    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static Gson getGson() {
        return new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public static <T> List<T> json2Collection(String str, Class<T> cls) {
        return (List) getGson().fromJson(str, new TypeToken<List<T>>() { // from class: com.cyjh.share.util.GsonExUtil.1
        }.getType());
    }

    public static Object parsData(String str, Class cls) {
        try {
            return new Gson().fromJson(str, cls);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static Object StringToDate(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static <T> Object parsListData(String str, Class cls) {
        try {
            return new Gson().fromJson(str, new TypeToken<List<T>>() { // from class: com.cyjh.share.util.GsonExUtil.2
            }.getType());
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public static String class2Data(Object obj) {
        try {
            return new Gson().toJson(obj);
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }
}

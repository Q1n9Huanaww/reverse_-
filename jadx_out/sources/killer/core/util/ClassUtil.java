package killer.core.util;

import android.util.Log;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class ClassUtil {
    public static Object getFieldValue(Object object, String name) {
        if (object == null) {
            return null;
        }
        try {
            Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            Log.e("ClassUtil", "getFieldValue", e);
            return null;
        }
    }
}

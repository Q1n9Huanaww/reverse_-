package com.lidroid.xutils.p076db.table;

import android.text.TextUtils;
import com.lidroid.xutils.p076db.annotation.Check;
import com.lidroid.xutils.p076db.annotation.Column;
import com.lidroid.xutils.p076db.annotation.Finder;
import com.lidroid.xutils.p076db.annotation.Foreign;
import com.lidroid.xutils.p076db.annotation.InterfaceC1648Id;
import com.lidroid.xutils.p076db.annotation.NotNull;
import com.lidroid.xutils.p076db.annotation.Transient;
import com.lidroid.xutils.p076db.annotation.Unique;
import com.lidroid.xutils.p076db.converter.ColumnConverter;
import com.lidroid.xutils.p076db.converter.ColumnConverterFactory;
import com.lidroid.xutils.p076db.sqlite.FinderLazyLoader;
import com.lidroid.xutils.p076db.sqlite.ForeignLazyLoader;
import com.lidroid.xutils.util.LogUtils;
import com.umeng.commonsdk.proguard.C1799v;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ColumnUtils {
    private static final HashSet<String> DB_PRIMITIVE_TYPES;

    private ColumnUtils() {
    }

    static {
        HashSet<String> hashSet = new HashSet<>(14);
        DB_PRIMITIVE_TYPES = hashSet;
        hashSet.add(Integer.TYPE.getName());
        DB_PRIMITIVE_TYPES.add(Long.TYPE.getName());
        DB_PRIMITIVE_TYPES.add(Short.TYPE.getName());
        DB_PRIMITIVE_TYPES.add(Byte.TYPE.getName());
        DB_PRIMITIVE_TYPES.add(Float.TYPE.getName());
        DB_PRIMITIVE_TYPES.add(Double.TYPE.getName());
        DB_PRIMITIVE_TYPES.add(Integer.class.getName());
        DB_PRIMITIVE_TYPES.add(Long.class.getName());
        DB_PRIMITIVE_TYPES.add(Short.class.getName());
        DB_PRIMITIVE_TYPES.add(Byte.class.getName());
        DB_PRIMITIVE_TYPES.add(Float.class.getName());
        DB_PRIMITIVE_TYPES.add(Double.class.getName());
        DB_PRIMITIVE_TYPES.add(String.class.getName());
        DB_PRIMITIVE_TYPES.add(byte[].class.getName());
    }

    public static boolean isDbPrimitiveType(Class<?> cls) {
        return DB_PRIMITIVE_TYPES.contains(cls.getName());
    }

    public static Method getColumnGetMethod(Class<?> cls, Field field) throws NoSuchMethodException {
        Method booleanColumnGetMethod;
        while (true) {
            String name = field.getName();
            booleanColumnGetMethod = field.getType() == Boolean.TYPE ? getBooleanColumnGetMethod(cls, name) : null;
            if (booleanColumnGetMethod == null) {
                String str = "get" + name.substring(0, 1).toUpperCase() + name.substring(1);
                try {
                    booleanColumnGetMethod = cls.getDeclaredMethod(str, new Class[0]);
                } catch (NoSuchMethodException unused) {
                    LogUtils.m3418d(String.valueOf(str) + " not exist");
                }
            }
            if (booleanColumnGetMethod != null || Object.class.equals(cls.getSuperclass())) {
                break;
            }
            cls = cls.getSuperclass();
        }
        return booleanColumnGetMethod;
    }

    public static Method getColumnSetMethod(Class<?> cls, Field field) throws NoSuchMethodException {
        Method booleanColumnSetMethod;
        while (true) {
            String name = field.getName();
            booleanColumnSetMethod = field.getType() == Boolean.TYPE ? getBooleanColumnSetMethod(cls, field) : null;
            if (booleanColumnSetMethod == null) {
                String str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
                try {
                    booleanColumnSetMethod = cls.getDeclaredMethod(str, field.getType());
                } catch (NoSuchMethodException unused) {
                    LogUtils.m3418d(String.valueOf(str) + " not exist");
                }
            }
            if (booleanColumnSetMethod != null || Object.class.equals(cls.getSuperclass())) {
                break;
            }
            cls = cls.getSuperclass();
        }
        return booleanColumnSetMethod;
    }

    public static String getColumnNameByField(Field field) {
        Column column = (Column) field.getAnnotation(Column.class);
        if (column != null && !TextUtils.isEmpty(column.column())) {
            return column.column();
        }
        InterfaceC1648Id interfaceC1648Id = (InterfaceC1648Id) field.getAnnotation(InterfaceC1648Id.class);
        if (interfaceC1648Id != null && !TextUtils.isEmpty(interfaceC1648Id.column())) {
            return interfaceC1648Id.column();
        }
        Foreign foreign = (Foreign) field.getAnnotation(Foreign.class);
        if (foreign != null && !TextUtils.isEmpty(foreign.column())) {
            return foreign.column();
        }
        if (((Finder) field.getAnnotation(Finder.class)) != null) {
            return field.getName();
        }
        return field.getName();
    }

    public static String getForeignColumnNameByField(Field field) {
        Foreign foreign = (Foreign) field.getAnnotation(Foreign.class);
        if (foreign != null) {
            return foreign.foreign();
        }
        return field.getName();
    }

    public static String getColumnDefaultValue(Field field) {
        Column column = (Column) field.getAnnotation(Column.class);
        if (column == null || TextUtils.isEmpty(column.defaultValue())) {
            return null;
        }
        return column.defaultValue();
    }

    public static boolean isTransient(Field field) {
        return field.getAnnotation(Transient.class) != null;
    }

    public static boolean isForeign(Field field) {
        return field.getAnnotation(Foreign.class) != null;
    }

    public static boolean isFinder(Field field) {
        return field.getAnnotation(Finder.class) != null;
    }

    public static boolean isUnique(Field field) {
        return field.getAnnotation(Unique.class) != null;
    }

    public static boolean isNotNull(Field field) {
        return field.getAnnotation(NotNull.class) != null;
    }

    public static String getCheck(Field field) {
        Check check = (Check) field.getAnnotation(Check.class);
        if (check != null) {
            return check.value();
        }
        return null;
    }

    public static Class<?> getForeignEntityType(Foreign foreign) {
        Class<?> type = foreign.getColumnField().getType();
        return (type.equals(ForeignLazyLoader.class) || type.equals(List.class)) ? (Class) ((ParameterizedType) foreign.getColumnField().getGenericType()).getActualTypeArguments()[0] : type;
    }

    public static Class<?> getFinderTargetEntityType(Finder finder) {
        Class<?> type = finder.getColumnField().getType();
        return (type.equals(FinderLazyLoader.class) || type.equals(List.class)) ? (Class) ((ParameterizedType) finder.getColumnField().getGenericType()).getActualTypeArguments()[0] : type;
    }

    public static Object convert2DbColumnValueIfNeeded(Object obj) {
        ColumnConverter columnConverter;
        if (obj == null) {
            return obj;
        }
        Class<?> cls = obj.getClass();
        return (isDbPrimitiveType(cls) || (columnConverter = ColumnConverterFactory.getColumnConverter(cls)) == null) ? obj : columnConverter.fieldValue2ColumnValue(obj);
    }

    private static boolean isStartWithIs(String str) {
        return str != null && str.startsWith(C1799v.f4286Y);
    }

    private static Method getBooleanColumnGetMethod(Class<?> cls, String str) {
        String str2 = C1799v.f4286Y + str.substring(0, 1).toUpperCase() + str.substring(1);
        if (!isStartWithIs(str)) {
            str = str2;
        }
        try {
            return cls.getDeclaredMethod(str, new Class[0]);
        } catch (NoSuchMethodException unused) {
            LogUtils.m3418d(String.valueOf(str) + " not exist");
            return null;
        }
    }

    private static Method getBooleanColumnSetMethod(Class<?> cls, Field field) {
        String str;
        String name = field.getName();
        if (isStartWithIs(field.getName())) {
            str = "set" + name.substring(2, 3).toUpperCase() + name.substring(3);
        } else {
            str = "set" + name.substring(0, 1).toUpperCase() + name.substring(1);
        }
        try {
            return cls.getDeclaredMethod(str, field.getType());
        } catch (NoSuchMethodException unused) {
            LogUtils.m3418d(String.valueOf(str) + " not exist");
            return null;
        }
    }
}

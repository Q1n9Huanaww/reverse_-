package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.util.FieldInfo;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public abstract class FieldDeserializer {
    public final Class<?> clazz;
    protected long[] enumNameHashCodes;
    protected Enum[] enums;
    public final FieldInfo fieldInfo;

    public abstract void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map);

    public FieldDeserializer(Class<?> clazz, FieldInfo fieldInfo, int fastMatchToken) {
        this.clazz = clazz;
        this.fieldInfo = fieldInfo;
        if (fieldInfo != null) {
            Class<?> cls = fieldInfo.fieldClass;
            if (cls.isEnum()) {
                Enum[] enums = (Enum[]) cls.getEnumConstants();
                long[] enumNameHashCodes = new long[enums.length];
                this.enumNameHashCodes = new long[enums.length];
                for (int i = 0; i < enums.length; i++) {
                    String name = enums[i].name();
                    long hash = -3750763034362895579L;
                    for (int j = 0; j < name.length(); j++) {
                        char ch = name.charAt(j);
                        hash = (hash ^ ((long) ch)) * 1099511628211L;
                    }
                    enumNameHashCodes[i] = hash;
                    this.enumNameHashCodes[i] = hash;
                }
                Arrays.sort(this.enumNameHashCodes);
                this.enums = new Enum[enums.length];
                for (int i2 = 0; i2 < this.enumNameHashCodes.length; i2++) {
                    for (int j2 = 0; j2 < enumNameHashCodes.length; j2++) {
                        if (this.enumNameHashCodes[i2] == enumNameHashCodes[j2]) {
                            this.enums[i2] = enums[j2];
                            break;
                        }
                    }
                }
            }
        }
    }

    public Enum getEnumByHashCode(long hashCode) {
        int enumIndex;
        if (this.enums != null && (enumIndex = Arrays.binarySearch(this.enumNameHashCodes, hashCode)) >= 0) {
            return this.enums[enumIndex];
        }
        return null;
    }

    public void setValue(Object object, int value) throws IllegalAccessException {
        this.fieldInfo.field.setInt(object, value);
    }

    public void setValue(Object object, long value) throws IllegalAccessException {
        this.fieldInfo.field.setLong(object, value);
    }

    public void setValue(Object object, float value) throws IllegalAccessException {
        this.fieldInfo.field.setFloat(object, value);
    }

    public void setValue(Object object, double value) throws IllegalAccessException {
        this.fieldInfo.field.setDouble(object, value);
    }

    public void setValue(Object object, Object value) {
        if (value == null) {
            Class<?> fieldClass = this.fieldInfo.fieldClass;
            if (fieldClass.isPrimitive()) {
                return;
            }
        }
        Field field = this.fieldInfo.field;
        Method method = this.fieldInfo.method;
        try {
            if (this.fieldInfo.fieldAccess) {
                if (this.fieldInfo.getOnly) {
                    if (Map.class.isAssignableFrom(this.fieldInfo.fieldClass)) {
                        Map map = (Map) field.get(object);
                        if (map != null) {
                            map.putAll((Map) value);
                        }
                    } else {
                        Collection collection = (Collection) field.get(object);
                        if (collection != null) {
                            collection.addAll((Collection) value);
                        }
                    }
                } else {
                    field.set(object, value);
                }
            } else if (this.fieldInfo.getOnly) {
                if (Map.class.isAssignableFrom(this.fieldInfo.fieldClass)) {
                    Map map2 = (Map) method.invoke(object, new Object[0]);
                    if (map2 != null) {
                        map2.putAll((Map) value);
                    }
                } else {
                    Collection collection2 = (Collection) method.invoke(object, new Object[0]);
                    if (collection2 != null) {
                        collection2.addAll((Collection) value);
                    }
                }
            } else {
                method.invoke(object, value);
            }
        } catch (Exception e) {
            throw new JSONException("set property error, " + this.fieldInfo.name, e);
        }
    }
}

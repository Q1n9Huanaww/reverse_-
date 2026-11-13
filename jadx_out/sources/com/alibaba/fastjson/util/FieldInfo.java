package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.annotation.JSONField;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* JADX INFO: loaded from: classes2.dex */
public class FieldInfo implements Comparable<FieldInfo> {
    public final String[] alternateNames;
    public final Class<?> declaringClass;
    public final Field field;
    public final boolean fieldAccess;
    private final JSONField fieldAnnotation;
    public final Class<?> fieldClass;
    public final boolean fieldTransient;
    public final Type fieldType;
    public final String format;
    public final boolean getOnly;
    public final boolean isEnum;
    public final Method method;
    private final JSONField methodAnnotation;
    public final String name;
    public final long nameHashCode;
    private int ordinal;
    public final int serialzeFeatures;

    public FieldInfo(String name, Class<?> declaringClass, Class<?> fieldClass, Type fieldType, Field field, int ordinal, int serialzeFeatures) {
        this.ordinal = 0;
        ordinal = ordinal < 0 ? 0 : ordinal;
        this.name = name;
        this.declaringClass = declaringClass;
        this.fieldClass = fieldClass;
        this.fieldType = fieldType;
        this.method = null;
        this.field = field;
        this.ordinal = ordinal;
        this.serialzeFeatures = serialzeFeatures;
        this.isEnum = fieldClass.isEnum() && !JSONAware.class.isAssignableFrom(fieldClass);
        this.fieldAnnotation = null;
        this.methodAnnotation = null;
        if (field != null) {
            int modifiers = field.getModifiers();
            this.fieldAccess = (modifiers & 1) != 0 || this.method == null;
            this.fieldTransient = Modifier.isTransient(modifiers);
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        this.getOnly = false;
        long hashCode = -3750763034362895579L;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            hashCode = (hashCode ^ ((long) c)) * 1099511628211L;
        }
        this.nameHashCode = hashCode;
        this.format = null;
        this.alternateNames = new String[0];
    }

    public FieldInfo(String name, Method method, Field field, Class<?> clazz, Type type, int ordinal, int serialzeFeatures, JSONField methodAnnotation, JSONField fieldAnnotation, boolean fieldGenericSupport) {
        Class<?> fieldClass;
        Type fieldType;
        this.ordinal = 0;
        ordinal = ordinal < 0 ? 0 : ordinal;
        this.name = name;
        this.method = method;
        this.field = field;
        this.ordinal = ordinal;
        this.methodAnnotation = methodAnnotation;
        this.fieldAnnotation = fieldAnnotation;
        this.serialzeFeatures = serialzeFeatures;
        JSONField annotation = getAnnotation();
        String format = null;
        if (annotation != null) {
            format = annotation.format();
            format = format.trim().length() == 0 ? null : format;
            this.alternateNames = annotation.alternateNames();
        } else {
            this.alternateNames = new String[0];
        }
        this.format = format;
        if (field != null) {
            int modifiers = field.getModifiers();
            this.fieldAccess = method == null || ((modifiers & 1) != 0 && method.getReturnType() == field.getType());
            this.fieldTransient = (modifiers & 128) != 0;
        } else {
            this.fieldAccess = false;
            this.fieldTransient = false;
        }
        long hashCode = -3750763034362895579L;
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            hashCode = (hashCode ^ ((long) c)) * 1099511628211L;
        }
        this.nameHashCode = hashCode;
        if (method != null) {
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes.length == 1) {
                fieldClass = parameterTypes[0];
                fieldType = (fieldClass == Class.class || fieldClass == String.class || fieldClass.isPrimitive() || !fieldGenericSupport) ? fieldClass : method.getGenericParameterTypes()[0];
                this.getOnly = false;
            } else {
                fieldClass = method.getReturnType();
                fieldType = (fieldClass != Class.class && fieldGenericSupport) ? method.getGenericReturnType() : fieldClass;
                this.getOnly = true;
            }
            this.declaringClass = method.getDeclaringClass();
        } else {
            fieldClass = field.getType();
            fieldType = (fieldClass.isPrimitive() || fieldClass == String.class || fieldClass.isEnum() || !fieldGenericSupport) ? fieldClass : field.getGenericType();
            this.declaringClass = field.getDeclaringClass();
            this.getOnly = Modifier.isFinal(field.getModifiers());
        }
        if (clazz != null && fieldClass == Object.class && (fieldType instanceof TypeVariable)) {
            TypeVariable<?> tv = (TypeVariable) fieldType;
            Type genericFieldType = null;
            Type[] arguments = null;
            if (type instanceof ParameterizedType) {
                ParameterizedType ptype = (ParameterizedType) type;
                arguments = ptype.getActualTypeArguments();
            }
            for (Class<?> c2 = clazz; c2 != null && c2 != Object.class && c2 != this.declaringClass; c2 = c2.getSuperclass()) {
                Type superType = c2.getGenericSuperclass();
                if (superType instanceof ParameterizedType) {
                    ParameterizedType p_superType = (ParameterizedType) superType;
                    Type[] p_superType_args = p_superType.getActualTypeArguments();
                    TypeUtils.getArgument(p_superType_args, c2.getTypeParameters(), arguments);
                    arguments = p_superType_args;
                }
            }
            if (arguments != null) {
                TypeVariable<?>[] typeVariables = this.declaringClass.getTypeParameters();
                for (int j = 0; j < typeVariables.length; j++) {
                    if (tv.equals(typeVariables[j])) {
                        genericFieldType = arguments[j];
                        break;
                    }
                }
            }
            if (genericFieldType != null) {
                this.fieldClass = TypeUtils.getClass(genericFieldType);
                this.fieldType = genericFieldType;
                this.isEnum = fieldClass.isEnum() && !JSONAware.class.isAssignableFrom(fieldClass);
                return;
            }
        }
        Type genericFieldType2 = fieldType;
        if (!(fieldType instanceof Class)) {
            genericFieldType2 = getFieldType(clazz, type == null ? clazz : type, fieldType);
            if (genericFieldType2 != fieldType && ((genericFieldType2 instanceof ParameterizedType) || (genericFieldType2 instanceof Class))) {
                fieldClass = TypeUtils.getClass(genericFieldType2);
            }
        }
        this.fieldType = genericFieldType2;
        this.fieldClass = fieldClass;
        this.isEnum = (fieldClass.isArray() || !fieldClass.isEnum() || JSONAware.class.isAssignableFrom(fieldClass)) ? false : true;
    }

    public static Type getFieldType(Class<?> clazz, Type type, Type fieldType) {
        if (clazz == null || type == null) {
            return fieldType;
        }
        if (fieldType instanceof GenericArrayType) {
            GenericArrayType genericArrayType = (GenericArrayType) fieldType;
            Type componentType = genericArrayType.getGenericComponentType();
            Type componentTypeX = getFieldType(clazz, type, componentType);
            return componentType != componentTypeX ? Array.newInstance(TypeUtils.getClass(componentTypeX), 0).getClass() : fieldType;
        }
        if (!TypeUtils.isGenericParamType(type)) {
            return fieldType;
        }
        if (fieldType instanceof TypeVariable) {
            ParameterizedType paramType = (ParameterizedType) TypeUtils.getGenericParamType(type);
            Class<?> parameterizedClass = TypeUtils.getClass(paramType);
            TypeVariable<?> typeVar = (TypeVariable) fieldType;
            for (int i = 0; i < parameterizedClass.getTypeParameters().length; i++) {
                if (parameterizedClass.getTypeParameters()[i].getName().equals(typeVar.getName())) {
                    return paramType.getActualTypeArguments()[i];
                }
            }
        }
        if (fieldType instanceof ParameterizedType) {
            ParameterizedType parameterizedFieldType = (ParameterizedType) fieldType;
            Type[] arguments = parameterizedFieldType.getActualTypeArguments();
            boolean changed = false;
            TypeVariable<?>[] typeVariables = null;
            Type[] actualTypes = null;
            ParameterizedType paramType2 = null;
            if (type instanceof ParameterizedType) {
                paramType2 = (ParameterizedType) type;
                typeVariables = clazz.getTypeParameters();
            } else if (clazz.getGenericSuperclass() instanceof ParameterizedType) {
                paramType2 = (ParameterizedType) clazz.getGenericSuperclass();
                typeVariables = clazz.getSuperclass().getTypeParameters();
            }
            for (int i2 = 0; i2 < arguments.length && paramType2 != null; i2++) {
                Type feildTypeArguement = arguments[i2];
                if (feildTypeArguement instanceof TypeVariable) {
                    TypeVariable<?> typeVar2 = (TypeVariable) feildTypeArguement;
                    for (int j = 0; j < typeVariables.length; j++) {
                        if (typeVariables[j].getName().equals(typeVar2.getName())) {
                            if (actualTypes == null) {
                                actualTypes = paramType2.getActualTypeArguments();
                            }
                            arguments[i2] = actualTypes[j];
                            changed = true;
                        }
                    }
                }
            }
            if (changed) {
                return new ParameterizedTypeImpl(arguments, parameterizedFieldType.getOwnerType(), parameterizedFieldType.getRawType());
            }
        }
        return fieldType;
    }

    public String toString() {
        return this.name;
    }

    @Override // java.lang.Comparable
    public int compareTo(FieldInfo o) {
        if (this.ordinal < o.ordinal) {
            return -1;
        }
        if (this.ordinal > o.ordinal) {
            return 1;
        }
        return this.name.compareTo(o.name);
    }

    public boolean equals(FieldInfo o) {
        return o == this || compareTo(o) == 0;
    }

    public JSONField getAnnotation() {
        return this.fieldAnnotation != null ? this.fieldAnnotation : this.methodAnnotation;
    }

    public Object get(Object javaObject) throws IllegalAccessException, InvocationTargetException {
        if (this.fieldAccess) {
            return this.field.get(javaObject);
        }
        return this.method.invoke(javaObject, new Object[0]);
    }

    public void set(Object javaObject, Object value) throws IllegalAccessException, InvocationTargetException {
        if (this.method != null) {
            this.method.invoke(javaObject, value);
        } else {
            this.field.set(javaObject, value);
        }
    }
}

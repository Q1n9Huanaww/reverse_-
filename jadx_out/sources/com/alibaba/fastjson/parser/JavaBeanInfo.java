package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONCreator;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.umeng.commonsdk.proguard.C1799v;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class JavaBeanInfo {
    final Constructor<?> creatorConstructor;
    public final String[] creatorConstructorParameters;
    final Constructor<?> defaultConstructor;
    final int defaultConstructorParameterSize;
    final Method factoryMethod;
    final FieldInfo[] fields;
    final JSONType jsonType;
    boolean ordered = false;
    public final int parserFeatures;
    final FieldInfo[] sortedFields;
    final boolean supportBeanToArray;
    public final String typeKey;
    public final long typeKeyHashCode;
    public final String typeName;

    JavaBeanInfo(Class<?> clazz, Constructor<?> defaultConstructor, Constructor<?> creatorConstructor, Method factoryMethod, FieldInfo[] fields, FieldInfo[] sortedFields, JSONType jsonType, String[] creatorConstructorParameters) {
        int length;
        this.defaultConstructor = defaultConstructor;
        this.creatorConstructor = creatorConstructor;
        this.factoryMethod = factoryMethod;
        this.fields = fields;
        this.jsonType = jsonType;
        if (creatorConstructorParameters != null && creatorConstructorParameters.length == fields.length) {
            this.creatorConstructorParameters = null;
        } else {
            this.creatorConstructorParameters = creatorConstructorParameters;
        }
        int parserFeatures = 0;
        if (jsonType != null) {
            String typeName = jsonType.typeName();
            this.typeName = typeName.length() <= 0 ? clazz.getName() : typeName;
            String typeKey = jsonType.typeKey();
            this.typeKey = typeKey.length() <= 0 ? null : typeKey;
            for (Feature feature : jsonType.parseFeatures()) {
                parserFeatures |= feature.mask;
            }
        } else {
            this.typeName = clazz.getName();
            this.typeKey = null;
        }
        if (this.typeKey == null) {
            this.typeKeyHashCode = 0L;
        } else {
            this.typeKeyHashCode = TypeUtils.fnv_64_lower(this.typeKey);
        }
        this.parserFeatures = parserFeatures;
        boolean supportBeanToArray = false;
        if (jsonType != null) {
            for (Feature feature2 : jsonType.parseFeatures()) {
                if (feature2 == Feature.SupportArrayToBean) {
                    supportBeanToArray = true;
                }
            }
        }
        this.supportBeanToArray = supportBeanToArray;
        FieldInfo[] sortedFields2 = computeSortedFields(fields, sortedFields);
        this.sortedFields = Arrays.equals(fields, sortedFields2) ? fields : sortedFields2;
        if (defaultConstructor != null) {
            length = defaultConstructor.getParameterTypes().length;
        } else {
            length = factoryMethod != null ? factoryMethod.getParameterTypes().length : 0;
        }
        this.defaultConstructorParameterSize = length;
    }

    private FieldInfo[] computeSortedFields(FieldInfo[] fields, FieldInfo[] sortedFields) {
        if (this.jsonType == null) {
            return sortedFields;
        }
        String[] orders = this.jsonType.orders();
        if (orders != null && orders.length != 0) {
            boolean containsAll = true;
            for (String str : orders) {
                boolean got = false;
                for (FieldInfo fieldInfo : sortedFields) {
                    if (fieldInfo.name.equals(str)) {
                        got = true;
                        break;
                    }
                }
                if (!got) {
                    containsAll = false;
                    break;
                }
            }
            if (!containsAll) {
                return sortedFields;
            }
            if (orders.length == fields.length) {
                boolean orderMatch = true;
                for (int i = 0; i < orders.length; i++) {
                    if (!sortedFields[i].name.equals(orders[i])) {
                        orderMatch = false;
                        break;
                    }
                }
                if (orderMatch) {
                    return sortedFields;
                }
                FieldInfo[] newSortedFields = new FieldInfo[sortedFields.length];
                for (int i2 = 0; i2 < orders.length; i2++) {
                    for (int j = 0; j < sortedFields.length; j++) {
                        if (sortedFields[j].name.equals(orders[i2])) {
                            newSortedFields[i2] = sortedFields[j];
                            break;
                        }
                    }
                }
                this.ordered = true;
                return newSortedFields;
            }
            FieldInfo[] newSortedFields2 = new FieldInfo[sortedFields.length];
            for (int i3 = 0; i3 < orders.length; i3++) {
                for (int j2 = 0; j2 < sortedFields.length; j2++) {
                    if (sortedFields[j2].name.equals(orders[i3])) {
                        newSortedFields2[i3] = sortedFields[j2];
                        break;
                    }
                }
            }
            int fieldIndex = orders.length;
            for (int i4 = 0; i4 < sortedFields.length; i4++) {
                boolean contains = false;
                for (int j3 = 0; j3 < newSortedFields2.length && j3 < fieldIndex; j3++) {
                    if (newSortedFields2[i4].equals(sortedFields[j3])) {
                        contains = true;
                        break;
                    }
                }
                if (!contains) {
                    newSortedFields2[fieldIndex] = sortedFields[i4];
                    fieldIndex++;
                }
            }
            this.ordered = true;
        }
        return sortedFields;
    }

    static boolean addField(List<FieldInfo> fields, FieldInfo field, boolean fieldOnly) {
        if (!fieldOnly) {
            int size = fields.size();
            for (int i = 0; i < size; i++) {
                FieldInfo item = fields.get(i);
                if (item.name.equals(field.name) && (!item.getOnly || field.getOnly)) {
                    return false;
                }
            }
        }
        fields.add(field);
        return true;
    }

    /* JADX WARN: Code duplicated, block: B:197:0x0497 A[PHI: r10 r11
  0x0497: PHI (r10v9 'ordinal' int) = (r10v8 'ordinal' int), (r10v12 'ordinal' int) binds: [B:190:0x044c, B:194:0x0468] A[DONT_GENERATE, DONT_INLINE]
  0x0497: PHI (r11v9 'serialzeFeatures' int) = (r11v8 'serialzeFeatures' int), (r11v12 'serialzeFeatures' int) binds: [B:190:0x044c, B:194:0x0468] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:199:0x04a1  */
    /* JADX WARN: Code duplicated, block: B:201:0x04ae  */
    /* JADX WARN: Code duplicated, block: B:203:0x04b2  */
    /* JADX WARN: Code duplicated, block: B:210:0x050d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:211:0x050f  */
    /* JADX WARN: Code duplicated, block: B:213:0x051b  */
    /* JADX WARN: Code duplicated, block: B:215:0x0531  */
    /* JADX WARN: Code duplicated, block: B:216:0x0551  */
    /* JADX WARN: Code duplicated, block: B:217:0x0576  */
    /* JADX WARN: Code duplicated, block: B:219:0x057c  */
    /* JADX WARN: Code duplicated, block: B:220:0x0585  */
    /* JADX WARN: Code duplicated, block: B:222:0x058b  */
    /* JADX WARN: Code duplicated, block: B:223:0x0594  */
    /* JADX WARN: Code duplicated, block: B:228:0x05b5  */
    /* JADX WARN: Code duplicated, block: B:229:0x05b9 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:230:0x05bb  */
    /* JADX WARN: Code duplicated, block: B:231:0x05bd A[DONT_INVERT, PHI: r10 r11 r29
  0x05bd: PHI (r10v10 'ordinal' int) = (r10v9 'ordinal' int), (r10v9 'ordinal' int), (r10v11 'ordinal' int), (r10v11 'ordinal' int) binds: [B:209:0x050b, B:212:0x0519, B:229:0x05b9, B:230:0x05bb] A[DONT_GENERATE, DONT_INLINE]
  0x05bd: PHI (r11v10 'serialzeFeatures' int) = 
  (r11v9 'serialzeFeatures' int)
  (r11v9 'serialzeFeatures' int)
  (r11v11 'serialzeFeatures' int)
  (r11v11 'serialzeFeatures' int)
 binds: [B:209:0x050b, B:212:0x0519, B:229:0x05b9, B:230:0x05bb] A[DONT_GENERATE, DONT_INLINE]
  0x05bd: PHI (r29v8 'annotation' com.alibaba.fastjson.annotation.JSONField) = 
  (r29v7 'annotation' com.alibaba.fastjson.annotation.JSONField)
  (r29v7 'annotation' com.alibaba.fastjson.annotation.JSONField)
  (r29v7 'annotation' com.alibaba.fastjson.annotation.JSONField)
  (r29v9 'annotation' com.alibaba.fastjson.annotation.JSONField)
 binds: [B:209:0x050b, B:212:0x0519, B:229:0x05b9, B:230:0x05bb] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:232:0x05bf  */
    /* JADX WARN: Code duplicated, block: B:247:0x0631  */
    /* JADX WARN: Code duplicated, block: B:249:0x0639  */
    /* JADX WARN: Code duplicated, block: B:268:0x0685  */
    /* JADX WARN: Code duplicated, block: B:270:0x0689  */
    /* JADX WARN: Code duplicated, block: B:319:0x07d8  */
    /* JADX WARN: Code duplicated, block: B:339:0x0414 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:347:0x060e A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:354:0x0662 A[SYNTHETIC] */
    public static JavaBeanInfo build(Class<?> clazz, int classModifiers, Type type, boolean fieldOnly, boolean jsonTypeSupport, boolean jsonFieldSupport, boolean fieldGenericSupport, PropertyNamingStrategy propertyNamingStrategy) {
        Method[] methods;
        int ordinal;
        int serialzeFeatures;
        String propertyName;
        Class<?> returnType;
        char c3;
        String propertyName2;
        Field field;
        JSONField fieldAnnotation;
        JSONType jsonType = null;
        List<FieldInfo> fieldList = new ArrayList<>();
        Map<Class<?>, Field[]> classFieldCache = new HashMap<>();
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        boolean kotlin = TypeUtils.isKotlin(clazz);
        Constructor<?> defaultConstructor = null;
        if ((classModifiers & 1024) == 0 && (constructors.length == 1 || !kotlin)) {
            try {
                defaultConstructor = clazz.getDeclaredConstructor(new Class[0]);
            } catch (Exception e) {
            }
            if (defaultConstructor == null && clazz.isMemberClass() && (classModifiers & 8) == 0) {
                for (Constructor<?> constructor : constructors) {
                    Class<?>[] parameterTypes = constructor.getParameterTypes();
                    if (parameterTypes.length == 1 && parameterTypes[0].equals(clazz.getDeclaringClass())) {
                        defaultConstructor = constructor;
                        break;
                    }
                }
            }
        }
        Constructor<?> creatorConstructor = null;
        String[] creatorConstructorParameters = null;
        Method factoryMethod = null;
        if (fieldOnly) {
            methods = null;
        } else {
            List<Method> methodList = new ArrayList<>();
            for (Class<?> superclass = clazz; superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                Method[] declaredMethods = superclass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    int modifier = method.getModifiers();
                    if ((modifier & 8) != 0) {
                        if (!method.isAnnotationPresent(JSONCreator.class)) {
                            continue;
                        } else {
                            if (factoryMethod != null) {
                                throw new JSONException("multi-json creator");
                            }
                            factoryMethod = method;
                        }
                    } else if ((modifier & 2) == 0 && (modifier & 256) == 0 && (modifier & 4) == 0) {
                        methodList.add(method);
                    }
                }
            }
            methods = new Method[methodList.size()];
            methodList.toArray(methods);
        }
        Field[] declaredFields = clazz.getDeclaredFields();
        boolean isInterfaceOrAbstract = clazz.isInterface() || (classModifiers & 1024) != 0;
        if (defaultConstructor == null || isInterfaceOrAbstract) {
            creatorConstructor = null;
            for (Constructor<?> constructor2 : constructors) {
                if (((JSONCreator) constructor2.getAnnotation(JSONCreator.class)) != null) {
                    if (0 != 0) {
                        throw new JSONException("multi-json creator");
                    }
                    creatorConstructor = constructor2;
                    break;
                }
            }
            if (creatorConstructor != null) {
                TypeUtils.setAccessible(clazz, creatorConstructor, classModifiers);
                Class<?>[] parameterTypes2 = creatorConstructor.getParameterTypes();
                Type[] getGenericParameterTypes = fieldGenericSupport ? creatorConstructor.getGenericParameterTypes() : parameterTypes2;
                Annotation[][] paramAnnotationArrays = creatorConstructor.getParameterAnnotations();
                for (int i = 0; i < parameterTypes2.length; i++) {
                    Annotation[] paramAnnotations = paramAnnotationArrays[i];
                    JSONField fieldAnnotation2 = null;
                    for (Annotation paramAnnotation : paramAnnotations) {
                        if (paramAnnotation instanceof JSONField) {
                            fieldAnnotation2 = (JSONField) paramAnnotation;
                            break;
                        }
                    }
                    if (fieldAnnotation2 == null) {
                        throw new JSONException("illegal json creator");
                    }
                    Class<?> fieldClass = parameterTypes2[i];
                    Type fieldType = getGenericParameterTypes[i];
                    Field field2 = TypeUtils.getField(clazz, fieldAnnotation2.name(), declaredFields, classFieldCache);
                    if (field2 != null) {
                        TypeUtils.setAccessible(clazz, field2, classModifiers);
                    }
                    int ordinal2 = fieldAnnotation2.ordinal();
                    int serialzeFeatures2 = SerializerFeature.m14of(fieldAnnotation2.serialzeFeatures());
                    FieldInfo fieldInfo = new FieldInfo(fieldAnnotation2.name(), clazz, fieldClass, fieldType, field2, ordinal2, serialzeFeatures2);
                    addField(fieldList, fieldInfo, fieldOnly);
                }
                FieldInfo[] fields = new FieldInfo[fieldList.size()];
                fieldList.toArray(fields);
                FieldInfo[] sortedFields = new FieldInfo[fields.length];
                System.arraycopy(fields, 0, sortedFields, 0, fields.length);
                Arrays.sort(sortedFields);
                if (jsonTypeSupport) {
                }
                creatorConstructorParameters = new String[fields.length];
                for (int i2 = 0; i2 < fields.length; i2++) {
                    creatorConstructorParameters[i2] = fields[i2].name;
                }
            } else if (factoryMethod != null) {
                TypeUtils.setAccessible(clazz, factoryMethod, classModifiers);
                Class<?>[] parameterTypes3 = factoryMethod.getParameterTypes();
                if (parameterTypes3.length > 0) {
                    Type[] genericParameterTypes = fieldGenericSupport ? factoryMethod.getGenericParameterTypes() : parameterTypes3;
                    Annotation[][] paramAnnotationArrays2 = factoryMethod.getParameterAnnotations();
                    for (int i3 = 0; i3 < parameterTypes3.length; i3++) {
                        Annotation[] paramAnnotations2 = paramAnnotationArrays2[i3];
                        JSONField fieldAnnotation3 = null;
                        for (Annotation paramAnnotation2 : paramAnnotations2) {
                            if (paramAnnotation2 instanceof JSONField) {
                                fieldAnnotation3 = (JSONField) paramAnnotation2;
                                break;
                            }
                        }
                        if (fieldAnnotation3 == null) {
                            throw new JSONException("illegal json creator");
                        }
                        Class<?> fieldClass2 = parameterTypes3[i3];
                        Type fieldType2 = genericParameterTypes[i3];
                        Field field3 = TypeUtils.getField(clazz, fieldAnnotation3.name(), declaredFields, classFieldCache);
                        int ordinal3 = fieldAnnotation3.ordinal();
                        int serialzeFeatures3 = SerializerFeature.m14of(fieldAnnotation3.serialzeFeatures());
                        FieldInfo fieldInfo2 = new FieldInfo(fieldAnnotation3.name(), clazz, fieldClass2, fieldType2, field3, ordinal3, serialzeFeatures3);
                        addField(fieldList, fieldInfo2, fieldOnly);
                    }
                    FieldInfo[] fields2 = new FieldInfo[fieldList.size()];
                    fieldList.toArray(fields2);
                    FieldInfo[] sortedFields2 = new FieldInfo[fields2.length];
                    System.arraycopy(fields2, 0, sortedFields2, 0, fields2.length);
                    Arrays.sort(sortedFields2);
                    if (Arrays.equals(fields2, sortedFields2)) {
                        sortedFields2 = fields2;
                    }
                    if (0 == 0) {
                        jsonType = jsonTypeSupport ? (JSONType) clazz.getAnnotation(JSONType.class) : null;
                    }
                    return new JavaBeanInfo(clazz, null, null, factoryMethod, fields2, sortedFields2, jsonType, null);
                }
            } else if (!isInterfaceOrAbstract) {
                if (kotlin && constructors.length > 0) {
                    String[] parameters = TypeUtils.getKoltinConstructorParameters(clazz);
                    if (parameters != null) {
                        for (Constructor<?> constructor3 : constructors) {
                            Class<?>[] parameterTypes4 = constructor3.getParameterTypes();
                            if ((parameterTypes4.length <= 0 || !parameterTypes4[parameterTypes4.length - 1].getName().equals("kotlin.jvm.internal.DefaultConstructorMarker")) && (creatorConstructor == null || creatorConstructor.getParameterTypes().length < parameterTypes4.length)) {
                                creatorConstructor = constructor3;
                            }
                        }
                        creatorConstructor.setAccessible(true);
                        TypeUtils.setAccessible(clazz, creatorConstructor, classModifiers);
                        Class<?>[] parameterTypes5 = creatorConstructor.getParameterTypes();
                        Type[] getGenericParameterTypes2 = fieldGenericSupport ? creatorConstructor.getGenericParameterTypes() : parameterTypes5;
                        Annotation[][] paramAnnotationArrays3 = creatorConstructor.getParameterAnnotations();
                        for (int i4 = 0; i4 < parameterTypes5.length; i4++) {
                            String paramName = parameters[i4];
                            Annotation[] paramAnnotations3 = paramAnnotationArrays3[i4];
                            JSONField fieldAnnotation4 = null;
                            for (Annotation paramAnnotation3 : paramAnnotations3) {
                                if (paramAnnotation3 instanceof JSONField) {
                                    fieldAnnotation4 = (JSONField) paramAnnotation3;
                                    break;
                                }
                            }
                            Class<?> fieldClass3 = parameterTypes5[i4];
                            Type fieldType3 = getGenericParameterTypes2[i4];
                            Field field4 = TypeUtils.getField(clazz, paramName, declaredFields, classFieldCache);
                            if (field4 != null && fieldAnnotation4 == null) {
                                fieldAnnotation4 = (JSONField) field4.getAnnotation(JSONField.class);
                            }
                            if (fieldAnnotation4 != null) {
                                ordinal = fieldAnnotation4.ordinal();
                                serialzeFeatures = SerializerFeature.m14of(fieldAnnotation4.serialzeFeatures());
                                String nameAnnotated = fieldAnnotation4.name();
                                if (nameAnnotated.length() != 0) {
                                    paramName = nameAnnotated;
                                }
                            } else {
                                ordinal = 0;
                                serialzeFeatures = 0;
                            }
                            FieldInfo fieldInfo3 = new FieldInfo(paramName, clazz, fieldClass3, fieldType3, field4, ordinal, serialzeFeatures);
                            addField(fieldList, fieldInfo3, fieldOnly);
                        }
                        FieldInfo[] fields3 = new FieldInfo[fieldList.size()];
                        fieldList.toArray(fields3);
                        FieldInfo[] sortedFields3 = new FieldInfo[fields3.length];
                        System.arraycopy(fields3, 0, sortedFields3, 0, fields3.length);
                        Arrays.sort(sortedFields3);
                        creatorConstructorParameters = new String[fields3.length];
                        for (int i5 = 0; i5 < fields3.length; i5++) {
                            creatorConstructorParameters[i5] = fields3[i5].name;
                        }
                    } else {
                        throw new JSONException("default constructor not found. " + clazz);
                    }
                } else {
                    throw new JSONException("default constructor not found. " + clazz);
                }
            }
        }
        if (defaultConstructor != null) {
            TypeUtils.setAccessible(clazz, defaultConstructor, classModifiers);
        }
        if (!fieldOnly) {
            for (Method method2 : methods) {
                int ordinal4 = 0;
                int serialzeFeatures4 = 0;
                String methodName = method2.getName();
                if (methodName.length() >= 4 && (((returnType = method2.getReturnType()) == Void.TYPE || returnType == method2.getDeclaringClass()) && method2.getParameterTypes().length == 1)) {
                    JSONField annotation = jsonFieldSupport ? (JSONField) method2.getAnnotation(JSONField.class) : null;
                    if (annotation == null && jsonFieldSupport) {
                        annotation = TypeUtils.getSupperMethodAnnotation(clazz, method2);
                    }
                    if (annotation != null) {
                        if (annotation.deserialize()) {
                            ordinal4 = annotation.ordinal();
                            serialzeFeatures4 = SerializerFeature.m14of(annotation.serialzeFeatures());
                            if (annotation.name().length() != 0) {
                                String propertyName3 = annotation.name();
                                addField(fieldList, new FieldInfo(propertyName3, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                                TypeUtils.setAccessible(clazz, method2, classModifiers);
                            } else if (methodName.startsWith("set")) {
                                c3 = methodName.charAt(3);
                                if (Character.isUpperCase(c3)) {
                                    if (TypeUtils.compatibleWithJavaBean) {
                                        propertyName2 = TypeUtils.decapitalize(methodName.substring(3));
                                    } else {
                                        propertyName2 = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                                    }
                                } else if (c3 == '_') {
                                    propertyName2 = methodName.substring(4);
                                } else if (c3 == 'f') {
                                    propertyName2 = methodName.substring(3);
                                } else if (methodName.length() < 5) {
                                }
                                field = TypeUtils.getField(clazz, propertyName2, declaredFields, classFieldCache);
                                if (field == null) {
                                    String isFieldName = C1799v.f4286Y + Character.toUpperCase(propertyName2.charAt(0)) + propertyName2.substring(1);
                                    field = TypeUtils.getField(clazz, isFieldName, declaredFields, classFieldCache);
                                }
                                if (field == null) {
                                    if (jsonFieldSupport) {
                                        fieldAnnotation = (JSONField) field.getAnnotation(JSONField.class);
                                    } else {
                                        fieldAnnotation = null;
                                    }
                                    if (fieldAnnotation != null) {
                                        ordinal4 = fieldAnnotation.ordinal();
                                        serialzeFeatures4 = SerializerFeature.m14of(fieldAnnotation.serialzeFeatures());
                                        if (fieldAnnotation.name().length() != 0) {
                                            String propertyName4 = fieldAnnotation.name();
                                            addField(fieldList, new FieldInfo(propertyName4, method2, field, clazz, type, ordinal4, serialzeFeatures4, annotation, fieldAnnotation, fieldGenericSupport), fieldOnly);
                                        } else {
                                            if (annotation == null) {
                                                annotation = fieldAnnotation;
                                            }
                                            if (propertyNamingStrategy != null) {
                                                propertyName2 = propertyNamingStrategy.translate(propertyName2);
                                            }
                                            addField(fieldList, new FieldInfo(propertyName2, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                                            TypeUtils.setAccessible(clazz, method2, classModifiers);
                                        }
                                    } else {
                                        if (propertyNamingStrategy != null) {
                                            propertyName2 = propertyNamingStrategy.translate(propertyName2);
                                        }
                                        addField(fieldList, new FieldInfo(propertyName2, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                                        TypeUtils.setAccessible(clazz, method2, classModifiers);
                                    }
                                } else {
                                    if (propertyNamingStrategy != null) {
                                        propertyName2 = propertyNamingStrategy.translate(propertyName2);
                                    }
                                    addField(fieldList, new FieldInfo(propertyName2, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                                    TypeUtils.setAccessible(clazz, method2, classModifiers);
                                }
                            }
                        }
                    } else if (methodName.startsWith("set")) {
                        c3 = methodName.charAt(3);
                        if (Character.isUpperCase(c3)) {
                            if (TypeUtils.compatibleWithJavaBean) {
                                propertyName2 = TypeUtils.decapitalize(methodName.substring(3));
                            } else {
                                propertyName2 = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                            }
                        } else if (c3 == '_') {
                            propertyName2 = methodName.substring(4);
                        } else if (c3 == 'f') {
                            propertyName2 = methodName.substring(3);
                        } else if (methodName.length() < 5 && Character.isUpperCase(methodName.charAt(4))) {
                            propertyName2 = TypeUtils.decapitalize(methodName.substring(3));
                        }
                        field = TypeUtils.getField(clazz, propertyName2, declaredFields, classFieldCache);
                        if (field == null && method2.getParameterTypes()[0] == Boolean.TYPE) {
                            String isFieldName2 = C1799v.f4286Y + Character.toUpperCase(propertyName2.charAt(0)) + propertyName2.substring(1);
                            field = TypeUtils.getField(clazz, isFieldName2, declaredFields, classFieldCache);
                        }
                        if (field == null) {
                            if (jsonFieldSupport) {
                                fieldAnnotation = (JSONField) field.getAnnotation(JSONField.class);
                            } else {
                                fieldAnnotation = null;
                            }
                            if (fieldAnnotation != null) {
                                ordinal4 = fieldAnnotation.ordinal();
                                serialzeFeatures4 = SerializerFeature.m14of(fieldAnnotation.serialzeFeatures());
                                if (fieldAnnotation.name().length() != 0) {
                                    String propertyName5 = fieldAnnotation.name();
                                    addField(fieldList, new FieldInfo(propertyName5, method2, field, clazz, type, ordinal4, serialzeFeatures4, annotation, fieldAnnotation, fieldGenericSupport), fieldOnly);
                                } else {
                                    if (annotation == null) {
                                        annotation = fieldAnnotation;
                                    }
                                    if (propertyNamingStrategy != null) {
                                        propertyName2 = propertyNamingStrategy.translate(propertyName2);
                                    }
                                    addField(fieldList, new FieldInfo(propertyName2, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                                    TypeUtils.setAccessible(clazz, method2, classModifiers);
                                }
                            } else {
                                if (propertyNamingStrategy != null) {
                                    propertyName2 = propertyNamingStrategy.translate(propertyName2);
                                }
                                addField(fieldList, new FieldInfo(propertyName2, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                                TypeUtils.setAccessible(clazz, method2, classModifiers);
                            }
                        } else {
                            if (propertyNamingStrategy != null) {
                                propertyName2 = propertyNamingStrategy.translate(propertyName2);
                            }
                            addField(fieldList, new FieldInfo(propertyName2, method2, null, clazz, type, ordinal4, serialzeFeatures4, annotation, null, fieldGenericSupport), fieldOnly);
                            TypeUtils.setAccessible(clazz, method2, classModifiers);
                        }
                    }
                }
            }
        }
        List<Field> classfields = new ArrayList<>(declaredFields.length);
        for (Field f : declaredFields) {
            int modifiers = f.getModifiers();
            if ((modifiers & 8) == 0) {
                if ((modifiers & 16) != 0) {
                    Class<?> fieldType4 = f.getType();
                    boolean supportReadOnly = Map.class.isAssignableFrom(fieldType4) || Collection.class.isAssignableFrom(fieldType4);
                    if (supportReadOnly) {
                        if ((f.getModifiers() & 1) != 0) {
                            classfields.add(f);
                        }
                    }
                } else if ((f.getModifiers() & 1) != 0) {
                    classfields.add(f);
                }
            }
        }
        for (Class<?> c = clazz.getSuperclass(); c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field f2 : c.getDeclaredFields()) {
                int modifiers2 = f2.getModifiers();
                if ((modifiers2 & 8) == 0) {
                    if ((modifiers2 & 16) != 0) {
                        Class<?> fieldType5 = f2.getType();
                        boolean supportReadOnly2 = Map.class.isAssignableFrom(fieldType5) || Collection.class.isAssignableFrom(fieldType5);
                        if (supportReadOnly2) {
                            if ((modifiers2 & 1) != 0) {
                                classfields.add(f2);
                            }
                        }
                    } else if ((modifiers2 & 1) != 0) {
                        classfields.add(f2);
                    }
                }
            }
        }
        for (Field field5 : classfields) {
            String fieldName = field5.getName();
            boolean contains = false;
            int size = fieldList.size();
            for (int i6 = 0; i6 < size; i6++) {
                FieldInfo item = fieldList.get(i6);
                if (item.name.equals(fieldName)) {
                    contains = true;
                }
            }
            if (!contains) {
                int ordinal5 = 0;
                int serialzeFeatures5 = 0;
                String propertyName6 = fieldName;
                JSONField fieldAnnotation5 = jsonFieldSupport ? (JSONField) field5.getAnnotation(JSONField.class) : null;
                if (fieldAnnotation5 != null) {
                    ordinal5 = fieldAnnotation5.ordinal();
                    serialzeFeatures5 = SerializerFeature.m14of(fieldAnnotation5.serialzeFeatures());
                    if (fieldAnnotation5.name().length() != 0) {
                        propertyName6 = fieldAnnotation5.name();
                    }
                }
                if (propertyNamingStrategy != null) {
                    propertyName6 = propertyNamingStrategy.translate(propertyName6);
                }
                TypeUtils.setAccessible(clazz, field5, classModifiers);
                addField(fieldList, new FieldInfo(propertyName6, null, field5, clazz, type, ordinal5, serialzeFeatures5, null, fieldAnnotation5, fieldGenericSupport), fieldOnly);
            }
        }
        if (!fieldOnly) {
            for (Method method3 : methods) {
                String methodName2 = method3.getName();
                if (methodName2.length() >= 4 && methodName2.startsWith("get") && Character.isUpperCase(methodName2.charAt(3)) && method3.getParameterTypes().length == 0) {
                    Class<?> methodReturnType = method3.getReturnType();
                    if (Collection.class.isAssignableFrom(methodReturnType) || Map.class.isAssignableFrom(methodReturnType)) {
                        JSONField annotation2 = jsonFieldSupport ? (JSONField) method3.getAnnotation(JSONField.class) : null;
                        if (annotation2 != null) {
                            String annotationName = annotation2.name();
                            if (annotationName.length() > 0) {
                                propertyName = annotationName;
                            } else {
                                propertyName = Character.toLowerCase(methodName2.charAt(3)) + methodName2.substring(4);
                            }
                        } else {
                            propertyName = Character.toLowerCase(methodName2.charAt(3)) + methodName2.substring(4);
                        }
                        addField(fieldList, new FieldInfo(propertyName, method3, null, clazz, type, 0, 0, annotation2, null, fieldGenericSupport), fieldOnly);
                        TypeUtils.setAccessible(clazz, method3, classModifiers);
                    }
                }
            }
        }
        FieldInfo[] fields4 = new FieldInfo[fieldList.size()];
        fieldList.toArray(fields4);
        FieldInfo[] sortedFields4 = new FieldInfo[fields4.length];
        System.arraycopy(fields4, 0, sortedFields4, 0, fields4.length);
        Arrays.sort(sortedFields4);
        JSONType jsonType2 = jsonTypeSupport ? (JSONType) clazz.getAnnotation(JSONType.class) : null;
        return new JavaBeanInfo(clazz, defaultConstructor, creatorConstructor, factoryMethod, fields4, sortedFields4, jsonType2, creatorConstructorParameters);
    }
}

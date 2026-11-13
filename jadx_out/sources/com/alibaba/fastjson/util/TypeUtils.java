package com.alibaba.fastjson.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyjh.mobileanjian.ipc.utils.C0816m;
import com.cyjh.share.util.AppUtils;
import com.googlecode.tesseract.android.TessBaseAPI;
import com.p024ds.daisi.mvp.managers.AccountManager;
import com.umeng.commonsdk.proguard.C1799v;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.security.AccessControlException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes2.dex */
public class TypeUtils {
    private static volatile Map<Class, String[]> kotlinIgnores;
    private static volatile boolean kotlinIgnores_error;
    private static volatile boolean kotlin_class_klass_error;
    private static volatile boolean kotlin_error;
    private static volatile Constructor kotlin_kclass_constructor;
    private static volatile Method kotlin_kclass_getConstructors;
    private static volatile Method kotlin_kfunction_getParameters;
    private static volatile Method kotlin_kparameter_getName;
    private static volatile Class kotlin_metadata;
    private static volatile boolean kotlin_metadata_error;
    public static boolean compatibleWithJavaBean = false;
    private static boolean setAccessibleEnable = true;
    private static final ConcurrentMap<String, Class<?>> mappings = new ConcurrentHashMap(36, 0.75f, 1);

    static {
        mappings.put("byte", Byte.TYPE);
        mappings.put("short", Short.TYPE);
        mappings.put("int", Integer.TYPE);
        mappings.put("long", Long.TYPE);
        mappings.put("float", Float.TYPE);
        mappings.put("double", Double.TYPE);
        mappings.put("boolean", Boolean.TYPE);
        mappings.put("char", Character.TYPE);
        mappings.put("[byte", byte[].class);
        mappings.put("[short", short[].class);
        mappings.put("[int", int[].class);
        mappings.put("[long", long[].class);
        mappings.put("[float", float[].class);
        mappings.put("[double", double[].class);
        mappings.put("[boolean", boolean[].class);
        mappings.put("[char", char[].class);
        mappings.put("[B", byte[].class);
        mappings.put("[S", short[].class);
        mappings.put("[I", int[].class);
        mappings.put("[J", long[].class);
        mappings.put("[F", float[].class);
        mappings.put("[D", double[].class);
        mappings.put("[C", char[].class);
        mappings.put("[Z", boolean[].class);
        mappings.put("java.util.HashMap", HashMap.class);
        mappings.put("java.util.TreeMap", TreeMap.class);
        mappings.put("java.util.Date", Date.class);
        mappings.put("com.alibaba.fastjson.JSONObject", JSONObject.class);
        mappings.put("java.util.concurrent.ConcurrentHashMap", ConcurrentHashMap.class);
        mappings.put("java.text.SimpleDateFormat", SimpleDateFormat.class);
        mappings.put("java.lang.StackTraceElement", StackTraceElement.class);
        mappings.put("java.lang.RuntimeException", RuntimeException.class);
    }

    public static boolean isKotlin(Class clazz) {
        if (kotlin_metadata == null && !kotlin_metadata_error) {
            try {
                kotlin_metadata = Class.forName("kotlin.Metadata");
            } catch (Throwable th) {
                kotlin_metadata_error = true;
            }
        }
        if (kotlin_metadata == null) {
            return false;
        }
        return clazz.isAnnotationPresent(kotlin_metadata);
    }

    private static boolean isKotlinIgnore(Class clazz, String methodName) {
        String[] ignores;
        if (kotlinIgnores == null && !kotlinIgnores_error) {
            try {
                Map<Class, String[]> map = new HashMap<>();
                map.put(Class.forName("kotlin.ranges.CharRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.IntRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.LongRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.ClosedFloatRange"), new String[]{"getEndInclusive", "isEmpty"});
                map.put(Class.forName("kotlin.ranges.ClosedDoubleRange"), new String[]{"getEndInclusive", "isEmpty"});
                kotlinIgnores = map;
            } catch (Throwable th) {
                kotlinIgnores_error = true;
            }
        }
        if (kotlinIgnores == null || (ignores = kotlinIgnores.get(clazz)) == null) {
            return false;
        }
        return Arrays.binarySearch(ignores, methodName) >= 0;
    }

    public static String[] getKoltinConstructorParameters(Class clazz) {
        if (kotlin_kclass_constructor == null && !kotlin_class_klass_error) {
            try {
                Class<?> cls = Class.forName("kotlin.reflect.jvm.internal.KClassImpl");
                kotlin_kclass_constructor = cls.getConstructor(Class.class);
                kotlin_kclass_getConstructors = cls.getMethod("getConstructors", new Class[0]);
                kotlin_kfunction_getParameters = Class.forName("kotlin.reflect.KFunction").getMethod("getParameters", new Class[0]);
                kotlin_kparameter_getName = Class.forName("kotlin.reflect.KParameter").getMethod("getName", new Class[0]);
            } catch (Throwable th) {
                kotlin_class_klass_error = true;
            }
        }
        if (kotlin_kclass_constructor == null || kotlin_error) {
            return null;
        }
        Object constructor = null;
        try {
            Object kclassImpl = kotlin_kclass_constructor.newInstance(clazz);
            Iterable it2 = (Iterable) kotlin_kclass_getConstructors.invoke(kclassImpl, new Object[0]);
            Iterator iterator = it2.iterator();
            while (iterator.hasNext()) {
                Object item = iterator.next();
                List parameters = (List) kotlin_kfunction_getParameters.invoke(item, new Object[0]);
                if (constructor == null || parameters.size() != 0) {
                    constructor = item;
                }
                iterator.hasNext();
            }
            List parameters2 = (List) kotlin_kfunction_getParameters.invoke(constructor, new Object[0]);
            String[] names = new String[parameters2.size()];
            for (int i = 0; i < parameters2.size(); i++) {
                Object param = parameters2.get(i);
                names[i] = (String) kotlin_kparameter_getName.invoke(param, new Object[0]);
            }
            return names;
        } catch (Throwable th2) {
            kotlin_error = true;
            return null;
        }
    }

    public static final String castToString(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }

    public static final Byte castToByte(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Byte.valueOf(((Number) value).byteValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Byte.valueOf(Byte.parseByte(strVal));
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static final Character castToChar(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Character) {
            return (Character) value;
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0) {
                return null;
            }
            if (strVal.length() != 1) {
                throw new JSONException("can not cast to byte, value : " + value);
            }
            return Character.valueOf(strVal.charAt(0));
        }
        throw new JSONException("can not cast to byte, value : " + value);
    }

    public static final Short castToShort(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Short.valueOf(((Number) value).shortValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Short.valueOf(Short.parseShort(strVal));
        }
        throw new JSONException("can not cast to short, value : " + value);
    }

    public static final BigDecimal castToBigDecimal(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            return (BigDecimal) value;
        }
        if (value instanceof BigInteger) {
            return new BigDecimal((BigInteger) value);
        }
        String strVal = value.toString();
        if (strVal.length() == 0 || "null".equals(strVal)) {
            return null;
        }
        return new BigDecimal(strVal);
    }

    public static final BigInteger castToBigInteger(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigInteger) {
            return (BigInteger) value;
        }
        if ((value instanceof Float) || (value instanceof Double)) {
            return BigInteger.valueOf(((Number) value).longValue());
        }
        String strVal = value.toString();
        if (strVal.length() == 0 || "null".equals(strVal)) {
            return null;
        }
        return new BigInteger(strVal);
    }

    public static final Float castToFloat(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Float.valueOf(((Number) value).floatValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Float.valueOf(Float.parseFloat(strVal));
        }
        throw new JSONException("can not cast to float, value : " + value);
    }

    public static final Double castToDouble(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Number) {
            return Double.valueOf(((Number) value).doubleValue());
        }
        if (value instanceof String) {
            String strVal = value.toString();
            if (strVal.length() == 0 || "null".equals(strVal) || "NULL".equals(strVal)) {
                return null;
            }
            return Double.valueOf(Double.parseDouble(strVal));
        }
        throw new JSONException("can not cast to double, value : " + value);
    }

    public static final Date castToDate(Object value) {
        String format;
        if (value == null) {
            return null;
        }
        if (value instanceof Calendar) {
            return ((Calendar) value).getTime();
        }
        if (value instanceof Date) {
            return (Date) value;
        }
        long longValue = -1;
        if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            if (scale >= -100 && scale <= 100) {
                longValue = decimal.longValue();
            } else {
                longValue = decimal.longValueExact();
            }
        } else if (value instanceof Number) {
            longValue = ((Number) value).longValue();
        } else if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.indexOf(45) != -1) {
                if (strVal.length() == JSON.DEFFAULT_DATE_FORMAT.length()) {
                    format = JSON.DEFFAULT_DATE_FORMAT;
                } else if (strVal.length() == 10) {
                    format = AppUtils.TIME_FORMAT_DAY;
                } else if (strVal.length() == "yyyy-MM-dd HH:mm:ss".length()) {
                    format = "yyyy-MM-dd HH:mm:ss";
                } else if (strVal.length() == 29 && strVal.charAt(26) == ':' && strVal.charAt(28) == '0') {
                    format = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";
                } else {
                    format = "yyyy-MM-dd HH:mm:ss.SSS";
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat(format, JSON.defaultLocale);
                dateFormat.setTimeZone(JSON.defaultTimeZone);
                try {
                    return dateFormat.parse(strVal);
                } catch (ParseException e) {
                    throw new JSONException("can not cast to Date, value : " + strVal);
                }
            }
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            longValue = Long.parseLong(strVal);
        }
        if (longValue < 0) {
            throw new JSONException("can not cast to Date, value : " + value);
        }
        return new Date(longValue);
    }

    public static final Long castToLong(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            if (scale >= -100 && scale <= 100) {
                return Long.valueOf(decimal.longValue());
            }
            return Long.valueOf(decimal.longValueExact());
        }
        if (value instanceof Number) {
            return Long.valueOf(((Number) value).longValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            try {
                return Long.valueOf(Long.parseLong(strVal));
            } catch (NumberFormatException e) {
                JSONLexer dateParser = new JSONLexer(strVal);
                Calendar calendar = null;
                if (dateParser.scanISO8601DateIfMatch(false)) {
                    calendar = dateParser.calendar;
                }
                dateParser.close();
                if (calendar != null) {
                    return Long.valueOf(calendar.getTimeInMillis());
                }
            }
        }
        throw new JSONException("can not cast to long, value : " + value);
    }

    public static final Integer castToInt(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Integer) {
            return (Integer) value;
        }
        if (value instanceof BigDecimal) {
            BigDecimal decimal = (BigDecimal) value;
            int scale = decimal.scale();
            if (scale >= -100 && scale <= 100) {
                return Integer.valueOf(decimal.intValue());
            }
            return Integer.valueOf(decimal.intValueExact());
        }
        if (value instanceof Number) {
            return Integer.valueOf(((Number) value).intValue());
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            return Integer.valueOf(Integer.parseInt(strVal));
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final byte[] castToBytes(Object value) {
        if (value instanceof byte[]) {
            return (byte[]) value;
        }
        if (value instanceof String) {
            String string = (String) value;
            return JSONLexer.decodeFast(string, 0, string.length());
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final Boolean castToBoolean(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof Boolean) {
            return (Boolean) value;
        }
        if (value instanceof BigDecimal) {
            return Boolean.valueOf(((BigDecimal) value).intValueExact() == 1);
        }
        if (value instanceof Number) {
            return Boolean.valueOf(((Number) value).intValue() == 1);
        }
        if (value instanceof String) {
            String strVal = (String) value;
            if (strVal.length() == 0 || "null".equals(strVal)) {
                return null;
            }
            if ("true".equalsIgnoreCase(strVal) || "1".equals(strVal)) {
                return Boolean.TRUE;
            }
            if ("false".equalsIgnoreCase(strVal) || AccountManager.TYPE_LOGIN.equals(strVal)) {
                return Boolean.FALSE;
            }
        }
        throw new JSONException("can not cast to int, value : " + value);
    }

    public static final <T> T castToJavaBean(Object obj, Class<T> cls) {
        return (T) cast(obj, (Class) cls, ParserConfig.global);
    }

    public static final <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig) {
        return (T) cast(obj, cls, parserConfig, 0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(Object obj, Class<T> cls, ParserConfig parserConfig, int i) {
        Calendar calendar;
        if (obj == 0) {
            return null;
        }
        if (cls == null) {
            throw new IllegalArgumentException("clazz is null");
        }
        if (cls != obj.getClass()) {
            if (obj instanceof Map) {
                if (cls != Map.class) {
                    Map map = (Map) obj;
                    if (cls != Object.class || map.containsKey(JSON.DEFAULT_TYPE_KEY)) {
                        return (T) castToJavaBean((Map) obj, cls, parserConfig, i);
                    }
                    return obj;
                }
                return obj;
            }
            if (cls.isArray()) {
                if (obj instanceof Collection) {
                    Collection collection = (Collection) obj;
                    int i2 = 0;
                    T t = (T) Array.newInstance(cls.getComponentType(), collection.size());
                    Iterator it2 = collection.iterator();
                    while (it2.hasNext()) {
                        Array.set(t, i2, cast(it2.next(), (Class) cls.getComponentType(), parserConfig));
                        i2++;
                    }
                    return t;
                }
                if (cls == byte[].class) {
                    return (T) castToBytes(obj);
                }
            }
            if (!cls.isAssignableFrom(obj.getClass())) {
                if (cls == Boolean.TYPE || cls == Boolean.class) {
                    return (T) castToBoolean(obj);
                }
                if (cls == Byte.TYPE || cls == Byte.class) {
                    return (T) castToByte(obj);
                }
                if ((cls == Character.TYPE || cls == Character.class) && (obj instanceof String)) {
                    String str = (String) obj;
                    if (str.length() == 1) {
                        return (T) Character.valueOf(str.charAt(0));
                    }
                }
                if (cls == Short.TYPE || cls == Short.class) {
                    return (T) castToShort(obj);
                }
                if (cls == Integer.TYPE || cls == Integer.class) {
                    return (T) castToInt(obj);
                }
                if (cls == Long.TYPE || cls == Long.class) {
                    return (T) castToLong(obj);
                }
                if (cls == Float.TYPE || cls == Float.class) {
                    return (T) castToFloat(obj);
                }
                if (cls == Double.TYPE || cls == Double.class) {
                    return (T) castToDouble(obj);
                }
                if (cls == String.class) {
                    return (T) castToString(obj);
                }
                if (cls == BigDecimal.class) {
                    return (T) castToBigDecimal(obj);
                }
                if (cls == BigInteger.class) {
                    return (T) castToBigInteger(obj);
                }
                if (cls == Date.class) {
                    return (T) castToDate(obj);
                }
                if (cls.isEnum()) {
                    return (T) castToEnum(obj, cls, parserConfig);
                }
                if (Calendar.class.isAssignableFrom(cls)) {
                    Date dateCastToDate = castToDate(obj);
                    if (cls == Calendar.class) {
                        calendar = Calendar.getInstance(JSON.defaultTimeZone, JSON.defaultLocale);
                    } else {
                        try {
                            calendar = (Calendar) cls.newInstance();
                        } catch (Exception e) {
                            throw new JSONException("can not cast to : " + cls.getName(), e);
                        }
                    }
                    calendar.setTime(dateCastToDate);
                    return (T) calendar;
                }
                if (obj instanceof String) {
                    String str2 = (String) obj;
                    if (str2.length() == 0 || "null".equals(str2)) {
                        return null;
                    }
                    if (cls == Currency.class) {
                        return (T) Currency.getInstance(str2);
                    }
                }
                throw new JSONException("can not cast to : " + cls.getName());
            }
            return obj;
        }
        return obj;
    }

    public static final <T> T castToEnum(Object obj, Class<T> cls, ParserConfig parserConfig) {
        try {
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0) {
                    return null;
                }
                return (T) Enum.valueOf(cls, str);
            }
            if ((obj instanceof Integer) || (obj instanceof Long)) {
                int iIntValue = ((Number) obj).intValue();
                T[] enumConstants = cls.getEnumConstants();
                if (iIntValue < enumConstants.length) {
                    return enumConstants[iIntValue];
                }
            }
            throw new JSONException("can not cast to : " + cls.getName());
        } catch (Exception e) {
            throw new JSONException("can not cast to : " + cls.getName(), e);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static final <T> T cast(Object obj, Type type, ParserConfig parserConfig) {
        if (obj == 0) {
            return null;
        }
        if (type instanceof Class) {
            return (T) cast(obj, (Class) type, parserConfig, 0);
        }
        if (type instanceof ParameterizedType) {
            return (T) cast(obj, (ParameterizedType) type, parserConfig);
        }
        if (obj instanceof String) {
            String str = (String) obj;
            if (str.length() == 0 || "null".equals(str)) {
                return null;
            }
        }
        if (type instanceof TypeVariable) {
            return obj;
        }
        throw new JSONException("can not cast to : " + type);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r16v0, types: [T, java.util.HashMap, java.util.Map] */
    /* JADX WARN: Type inference failed for: r4v0, types: [T, java.util.ArrayList] */
    public static final <T> T cast(Object obj, ParameterizedType parameterizedType, ParserConfig parserConfig) {
        Object objCast;
        Collection hashSet;
        Object objCast2;
        if (obj != 0) {
            Type rawType = parameterizedType.getRawType();
            if (rawType == List.class || rawType == ArrayList.class) {
                Type type = parameterizedType.getActualTypeArguments()[0];
                if (obj instanceof List) {
                    List list = (List) obj;
                    int size = list.size();
                    ?? r4 = (T) new ArrayList(size);
                    for (int i = 0; i < size; i++) {
                        Object obj2 = list.get(i);
                        if (type instanceof Class) {
                            if (obj2 != null && obj2.getClass() == JSONObject.class) {
                                objCast = ((JSONObject) obj2).toJavaObject((Class) type, parserConfig, 0);
                            } else {
                                objCast = cast(obj2, (Class) type, parserConfig, 0);
                            }
                        } else {
                            objCast = cast(obj2, type, parserConfig);
                        }
                        r4.add(objCast);
                    }
                    return r4;
                }
            }
            if (rawType == Set.class || rawType == HashSet.class || rawType == TreeSet.class || rawType == List.class || rawType == ArrayList.class) {
                Type type2 = parameterizedType.getActualTypeArguments()[0];
                if (obj instanceof Iterable) {
                    if (rawType == Set.class || rawType == HashSet.class) {
                        hashSet = new HashSet();
                    } else if (rawType == TreeSet.class) {
                        hashSet = new TreeSet();
                    } else {
                        hashSet = new ArrayList();
                    }
                    for (T t : (Iterable) obj) {
                        if (type2 instanceof Class) {
                            if (t != null && t.getClass() == JSONObject.class) {
                                objCast2 = ((JSONObject) t).toJavaObject((Class) type2, parserConfig, 0);
                            } else {
                                objCast2 = cast(t, (Class) type2, parserConfig, 0);
                            }
                        } else {
                            objCast2 = cast(t, type2, parserConfig);
                        }
                        hashSet.add(objCast2);
                    }
                    return (T) hashSet;
                }
            }
            if (rawType == Map.class || rawType == HashMap.class) {
                Type type3 = parameterizedType.getActualTypeArguments()[0];
                Type type4 = parameterizedType.getActualTypeArguments()[1];
                if (obj instanceof Map) {
                    ?? r16 = (T) new HashMap();
                    for (Map.Entry entry : ((Map) obj).entrySet()) {
                        r16.put(cast(entry.getKey(), type3, parserConfig), cast(entry.getValue(), type4, parserConfig));
                    }
                    return r16;
                }
            }
            if (obj instanceof String) {
                String str = (String) obj;
                if (str.length() == 0 || "null".equals(str)) {
                    return null;
                }
            }
            if (parameterizedType.getActualTypeArguments().length == 1 && (parameterizedType.getActualTypeArguments()[0] instanceof WildcardType)) {
                return (T) cast(obj, rawType, parserConfig);
            }
            throw new JSONException("can not cast to : " + parameterizedType);
        }
        return obj;
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig) {
        return (T) castToJavaBean(map, cls, parserConfig, 0);
    }

    public static final <T> T castToJavaBean(Map<String, Object> map, Class<T> cls, ParserConfig parserConfig, int i) {
        JSONObject jSONObject;
        int iIntValue;
        try {
            if (cls == StackTraceElement.class) {
                String str = (String) map.get("className");
                String str2 = (String) map.get("methodName");
                String str3 = (String) map.get("fileName");
                Number number = (Number) map.get("lineNumber");
                if (number == null) {
                    iIntValue = 0;
                } else if (number instanceof BigDecimal) {
                    iIntValue = ((BigDecimal) number).intValueExact();
                } else {
                    iIntValue = number.intValue();
                }
                return (T) new StackTraceElement(str, str2, str3, iIntValue);
            }
            Object obj = map.get(JSON.DEFAULT_TYPE_KEY);
            if (obj instanceof String) {
                String str4 = (String) obj;
                if (parserConfig == null) {
                    parserConfig = ParserConfig.global;
                }
                Class<?> clsCheckAutoType = parserConfig.checkAutoType(str4, null, i);
                if (clsCheckAutoType == null) {
                    throw new ClassNotFoundException(str4 + " not found");
                }
                if (!clsCheckAutoType.equals(cls)) {
                    return (T) castToJavaBean(map, clsCheckAutoType, parserConfig, i);
                }
            }
            if (cls.isInterface()) {
                if (map instanceof JSONObject) {
                    jSONObject = (JSONObject) map;
                } else {
                    jSONObject = new JSONObject(map);
                }
                if (parserConfig == null) {
                    parserConfig = ParserConfig.getGlobalInstance();
                }
                return parserConfig.getDeserializer(cls) != null ? (T) JSON.parseObject(JSON.toJSONString(jSONObject), cls) : (T) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{cls}, jSONObject);
            }
            if (cls == String.class && (map instanceof JSONObject)) {
                return (T) map.toString();
            }
            if (parserConfig == null) {
                parserConfig = ParserConfig.global;
            }
            JavaBeanDeserializer javaBeanDeserializer = null;
            ObjectDeserializer deserializer = parserConfig.getDeserializer(cls);
            if (deserializer instanceof JavaBeanDeserializer) {
                javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
            }
            if (javaBeanDeserializer == null) {
                throw new JSONException("can not get javaBeanDeserializer");
            }
            return (T) javaBeanDeserializer.createInstance(map, parserConfig);
        } catch (Exception e) {
            throw new JSONException(e.getMessage(), e);
        }
    }

    public static Class<?> getClassFromMapping(String className) {
        return mappings.get(className);
    }

    public static Class<?> loadClass(String className, ClassLoader classLoader) {
        return loadClass(className, classLoader, false);
    }

    public static Class<?> loadClass(String className, ClassLoader classLoader, boolean cache) {
        if (className == null || className.length() == 0) {
            return null;
        }
        if (className.length() >= 256) {
            throw new JSONException("className too long. " + className);
        }
        Class<?> clazz = mappings.get(className);
        if (clazz == null) {
            if (className.charAt(0) == '[') {
                Class<?> componentType = loadClass(className.substring(1), classLoader, false);
                if (componentType == null) {
                    return null;
                }
                return Array.newInstance(componentType, 0).getClass();
            }
            if (className.startsWith("L") && className.endsWith(";")) {
                String newClassName = className.substring(1, className.length() - 1);
                return loadClass(newClassName, classLoader, false);
            }
            if (classLoader != null) {
                try {
                    clazz = classLoader.loadClass(className);
                    if (cache) {
                        mappings.put(className, clazz);
                        return clazz;
                    }
                    return clazz;
                } catch (Exception e) {
                }
            }
            try {
                ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
                if (contextClassLoader != null && contextClassLoader != classLoader) {
                    clazz = contextClassLoader.loadClass(className);
                    if (cache) {
                        mappings.put(className, clazz);
                        return clazz;
                    }
                    return clazz;
                }
            } catch (Exception e2) {
            }
            try {
                clazz = Class.forName(className);
                mappings.put(className, clazz);
                return clazz;
            } catch (Exception e3) {
                return clazz;
            }
        }
        return clazz;
    }

    public static List<FieldInfo> computeGetters(Class<?> clazz, int modifiers, boolean fieldOnly, JSONType jsonType, Map<String, String> aliasMap, boolean sorted, boolean jsonFieldSupport, boolean fieldGenericSupport, PropertyNamingStrategy propertyNamingStrategy) {
        String propertyName;
        String propertyName2;
        Map<String, FieldInfo> fieldInfoMap = new LinkedHashMap<>();
        Map<Class<?>, Field[]> classFieldCache = new HashMap<>();
        Field[] declaredFields = clazz.getDeclaredFields();
        if (!fieldOnly) {
            boolean kotlin = isKotlin(clazz);
            List<Method> methodList = new ArrayList<>();
            for (Class<?> superclass = clazz; superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                Method[] declaredMethods = superclass.getDeclaredMethods();
                for (Method method : declaredMethods) {
                    int modifier = method.getModifiers();
                    if ((modifier & 8) == 0 && (modifier & 2) == 0 && (modifier & 256) == 0 && (modifier & 4) == 0 && !method.getReturnType().equals(Void.TYPE) && method.getParameterTypes().length == 0 && method.getReturnType() != ClassLoader.class && method.getDeclaringClass() != Object.class) {
                        methodList.add(method);
                    }
                }
            }
            Constructor<?>[] declaredConstructors = null;
            Annotation[][] paramAnnotationArrays = (Annotation[][]) null;
            String[] paramNames = null;
            short[] paramNameMapping = null;
            for (Method method2 : methodList) {
                String methodName = method2.getName();
                int ordinal = 0;
                int serialzeFeatures = 0;
                if (!methodName.equals("getMetaClass") || !method2.getReturnType().getName().equals("groovy.lang.MetaClass")) {
                    JSONField annotation = jsonFieldSupport ? (JSONField) method2.getAnnotation(JSONField.class) : null;
                    if (annotation == null && jsonFieldSupport) {
                        annotation = getSupperMethodAnnotation(clazz, method2);
                    }
                    if (!kotlin || !isKotlinIgnore(clazz, methodName)) {
                        if (annotation == null && kotlin) {
                            if (declaredConstructors == null) {
                                declaredConstructors = clazz.getDeclaredConstructors();
                                if (declaredConstructors.length == 1) {
                                    paramAnnotationArrays = declaredConstructors[0].getParameterAnnotations();
                                    paramNames = getKoltinConstructorParameters(clazz);
                                    if (paramNames != null) {
                                        String[] paramNames_sorted = new String[paramNames.length];
                                        System.arraycopy(paramNames, 0, paramNames_sorted, 0, paramNames.length);
                                        Arrays.sort(paramNames_sorted);
                                        paramNameMapping = new short[paramNames.length];
                                        for (short p = 0; p < paramNames.length; p = (short) (p + 1)) {
                                            int index = Arrays.binarySearch(paramNames_sorted, paramNames[p]);
                                            paramNameMapping[index] = p;
                                        }
                                        paramNames = paramNames_sorted;
                                    }
                                }
                            }
                            if (paramNames != null && paramNameMapping != null && methodName.startsWith("get")) {
                                String propertyName3 = decapitalize(methodName.substring(3));
                                int p2 = Arrays.binarySearch(paramNames, propertyName3);
                                if (p2 < 0) {
                                    for (int i = 0; i < paramNames.length; i++) {
                                        if (propertyName3.equalsIgnoreCase(paramNames[i])) {
                                            p2 = i;
                                            break;
                                        }
                                    }
                                }
                                if (p2 >= 0) {
                                    short index2 = paramNameMapping[p2];
                                    Annotation[] paramAnnotations = paramAnnotationArrays[index2];
                                    if (paramAnnotations != null) {
                                        for (Annotation paramAnnotation : paramAnnotations) {
                                            if (paramAnnotation instanceof JSONField) {
                                                annotation = (JSONField) paramAnnotation;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        boolean fieldAnnotationExists = false;
                        if (annotation != null) {
                            if (annotation.serialize()) {
                                ordinal = annotation.ordinal();
                                serialzeFeatures = SerializerFeature.m14of(annotation.serialzeFeatures());
                                if (annotation.name().length() != 0) {
                                    String propertyName4 = annotation.name();
                                    if (aliasMap == null || (propertyName4 = aliasMap.get(propertyName4)) != null) {
                                        setAccessible(clazz, method2, modifiers);
                                        fieldInfoMap.put(propertyName4, new FieldInfo(propertyName4, method2, null, clazz, null, ordinal, serialzeFeatures, annotation, null, true));
                                    }
                                }
                            }
                        }
                        if (methodName.startsWith("get")) {
                            if (methodName.length() >= 4 && !methodName.equals("getClass")) {
                                char c3 = methodName.charAt(3);
                                if (Character.isUpperCase(c3)) {
                                    if (compatibleWithJavaBean) {
                                        propertyName2 = decapitalize(methodName.substring(3));
                                    } else {
                                        propertyName2 = Character.toLowerCase(methodName.charAt(3)) + methodName.substring(4);
                                    }
                                } else if (c3 == '_') {
                                    propertyName2 = methodName.substring(4);
                                } else if (c3 == 'f') {
                                    propertyName2 = methodName.substring(3);
                                } else if (methodName.length() >= 5 && Character.isUpperCase(methodName.charAt(4))) {
                                    propertyName2 = decapitalize(methodName.substring(3));
                                }
                                if (!isJSONTypeIgnore(clazz, jsonType, propertyName2)) {
                                    Field field = getField(clazz, propertyName2, declaredFields, classFieldCache);
                                    JSONField fieldAnnotation = null;
                                    if (field != null) {
                                        fieldAnnotation = jsonFieldSupport ? (JSONField) field.getAnnotation(JSONField.class) : null;
                                        if (fieldAnnotation != null) {
                                            if (fieldAnnotation.serialize()) {
                                                ordinal = fieldAnnotation.ordinal();
                                                serialzeFeatures = SerializerFeature.m14of(fieldAnnotation.serialzeFeatures());
                                                if (fieldAnnotation.name().length() != 0) {
                                                    propertyName2 = fieldAnnotation.name();
                                                    fieldAnnotationExists = true;
                                                    if (aliasMap == null || (propertyName2 = aliasMap.get(propertyName2)) != null) {
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    if (propertyNamingStrategy != null && !fieldAnnotationExists) {
                                        propertyName2 = propertyNamingStrategy.translate(propertyName2);
                                    }
                                    if (aliasMap == null || (propertyName2 = aliasMap.get(propertyName2)) != null) {
                                        setAccessible(clazz, method2, modifiers);
                                        fieldInfoMap.put(propertyName2, new FieldInfo(propertyName2, method2, field, clazz, null, ordinal, serialzeFeatures, annotation, fieldAnnotation, fieldGenericSupport));
                                    }
                                }
                            }
                        }
                        if (methodName.startsWith(C1799v.f4286Y) && methodName.length() >= 3) {
                            char c2 = methodName.charAt(2);
                            if (Character.isUpperCase(c2)) {
                                if (compatibleWithJavaBean) {
                                    propertyName = decapitalize(methodName.substring(2));
                                } else {
                                    propertyName = Character.toLowerCase(methodName.charAt(2)) + methodName.substring(3);
                                }
                            } else if (c2 == '_') {
                                propertyName = methodName.substring(3);
                            } else if (c2 == 'f') {
                                propertyName = methodName.substring(2);
                            }
                            if (!isJSONTypeIgnore(clazz, jsonType, propertyName)) {
                                Field field2 = getField(clazz, propertyName, declaredFields, classFieldCache);
                                if (field2 == null) {
                                    field2 = getField(clazz, methodName, declaredFields, classFieldCache);
                                }
                                JSONField fieldAnnotation2 = null;
                                if (field2 != null) {
                                    fieldAnnotation2 = jsonFieldSupport ? (JSONField) field2.getAnnotation(JSONField.class) : null;
                                    if (fieldAnnotation2 != null) {
                                        if (fieldAnnotation2.serialize()) {
                                            ordinal = fieldAnnotation2.ordinal();
                                            serialzeFeatures = SerializerFeature.m14of(fieldAnnotation2.serialzeFeatures());
                                            if (fieldAnnotation2.name().length() != 0) {
                                                propertyName = fieldAnnotation2.name();
                                                if (aliasMap == null || (propertyName = aliasMap.get(propertyName)) != null) {
                                                }
                                            }
                                        }
                                    }
                                }
                                if (propertyNamingStrategy != null) {
                                    propertyName = propertyNamingStrategy.translate(propertyName);
                                }
                                if (aliasMap == null || (propertyName = aliasMap.get(propertyName)) != null) {
                                    setAccessible(clazz, field2, modifiers);
                                    setAccessible(clazz, method2, modifiers);
                                    fieldInfoMap.put(propertyName, new FieldInfo(propertyName, method2, field2, clazz, null, ordinal, serialzeFeatures, annotation, fieldAnnotation2, fieldGenericSupport));
                                }
                            }
                        }
                    }
                }
            }
        }
        List<Field> classfields = new ArrayList<>(declaredFields.length);
        for (Field f : declaredFields) {
            if ((f.getModifiers() & 8) == 0 && !f.getName().equals("this$0") && (f.getModifiers() & 1) != 0) {
                classfields.add(f);
            }
        }
        for (Class<?> c = clazz.getSuperclass(); c != null && c != Object.class; c = c.getSuperclass()) {
            for (Field f2 : c.getDeclaredFields()) {
                if ((f2.getModifiers() & 8) == 0 && (f2.getModifiers() & 1) != 0) {
                    classfields.add(f2);
                }
            }
        }
        for (Field field3 : classfields) {
            JSONField fieldAnnotation3 = jsonFieldSupport ? (JSONField) field3.getAnnotation(JSONField.class) : null;
            int ordinal2 = 0;
            int serialzeFeatures2 = 0;
            String propertyName5 = field3.getName();
            if (fieldAnnotation3 != null) {
                if (fieldAnnotation3.serialize()) {
                    ordinal2 = fieldAnnotation3.ordinal();
                    serialzeFeatures2 = SerializerFeature.m14of(fieldAnnotation3.serialzeFeatures());
                    if (fieldAnnotation3.name().length() != 0) {
                        propertyName5 = fieldAnnotation3.name();
                    }
                }
            }
            if (aliasMap == null || (propertyName5 = aliasMap.get(propertyName5)) != null) {
                if (propertyNamingStrategy != null) {
                    propertyName5 = propertyNamingStrategy.translate(propertyName5);
                }
                if (!fieldInfoMap.containsKey(propertyName5)) {
                    setAccessible(clazz, field3, modifiers);
                    fieldInfoMap.put(propertyName5, new FieldInfo(propertyName5, null, field3, clazz, null, ordinal2, serialzeFeatures2, null, fieldAnnotation3, fieldGenericSupport));
                }
            }
        }
        List<FieldInfo> fieldInfoList = new ArrayList<>();
        boolean containsAll = false;
        String[] orders = null;
        if (jsonType != null) {
            orders = jsonType.orders();
            if (orders != null && orders.length == fieldInfoMap.size()) {
                containsAll = true;
                for (String item : orders) {
                    if (!fieldInfoMap.containsKey(item)) {
                        containsAll = false;
                        break;
                    }
                }
            } else {
                containsAll = false;
            }
        }
        if (containsAll) {
            for (String item2 : orders) {
                FieldInfo fieldInfo = fieldInfoMap.get(item2);
                fieldInfoList.add(fieldInfo);
            }
        } else {
            for (FieldInfo fieldInfo2 : fieldInfoMap.values()) {
                fieldInfoList.add(fieldInfo2);
            }
            if (sorted) {
                Collections.sort(fieldInfoList);
            }
        }
        return fieldInfoList;
    }

    public static JSONField getSupperMethodAnnotation(Class<?> clazz, Method method) {
        JSONField annotation;
        JSONField annotation2;
        for (Class<?> interfaceClass : clazz.getInterfaces()) {
            for (Method interfaceMethod : interfaceClass.getMethods()) {
                if (interfaceMethod.getName().equals(method.getName())) {
                    Class<?>[] interfaceParameterTypes = interfaceMethod.getParameterTypes();
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (interfaceParameterTypes.length == parameterTypes.length) {
                        boolean match = true;
                        for (int i = 0; i < interfaceParameterTypes.length; i++) {
                            if (!interfaceParameterTypes[i].equals(parameterTypes[i])) {
                                match = false;
                                break;
                            }
                        }
                        if (match && (annotation2 = (JSONField) interfaceMethod.getAnnotation(JSONField.class)) != null) {
                            return annotation2;
                        }
                    } else {
                        continue;
                    }
                }
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass != null && Modifier.isAbstract(superClass.getModifiers())) {
            Class<?>[] types = method.getParameterTypes();
            for (Method interfaceMethod2 : superClass.getMethods()) {
                Class<?>[] interfaceTypes = interfaceMethod2.getParameterTypes();
                if (interfaceTypes.length == types.length && interfaceMethod2.getName().equals(method.getName())) {
                    boolean match2 = true;
                    for (int i2 = 0; i2 < types.length; i2++) {
                        if (!interfaceTypes[i2].equals(types[i2])) {
                            match2 = false;
                            break;
                        }
                    }
                    if (match2 && (annotation = (JSONField) interfaceMethod2.getAnnotation(JSONField.class)) != null) {
                        return annotation;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isJSONTypeIgnore(Class<?> clazz, JSONType jsonType, String propertyName) {
        if (jsonType != null && jsonType.ignores() != null) {
            for (String item : jsonType.ignores()) {
                if (propertyName.equalsIgnoreCase(item)) {
                    return true;
                }
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        return (superClass == Object.class || superClass == null || !isJSONTypeIgnore(superClass, (JSONType) superClass.getAnnotation(JSONType.class), propertyName)) ? false : true;
    }

    public static boolean isGenericParamType(Type type) {
        if (type instanceof ParameterizedType) {
            return true;
        }
        if (!(type instanceof Class)) {
            return false;
        }
        Type superType = ((Class) type).getGenericSuperclass();
        return superType != Object.class && isGenericParamType(superType);
    }

    public static Type getGenericParamType(Type type) {
        if (!(type instanceof Class)) {
            return type;
        }
        return getGenericParamType(((Class) type).getGenericSuperclass());
    }

    public static Class<?> getClass(Type type) {
        if (type.getClass() == Class.class) {
            return (Class) type;
        }
        if (type instanceof ParameterizedType) {
            return getClass(((ParameterizedType) type).getRawType());
        }
        if (type instanceof TypeVariable) {
            return (Class) ((TypeVariable) type).getBounds()[0];
        }
        if (type instanceof WildcardType) {
            Type[] upperBounds = ((WildcardType) type).getUpperBounds();
            if (upperBounds.length == 1) {
                return getClass(upperBounds[0]);
            }
        }
        return Object.class;
    }

    public static String decapitalize(String name) {
        if (name != null && name.length() != 0) {
            if (name.length() <= 1 || !Character.isUpperCase(name.charAt(1)) || !Character.isUpperCase(name.charAt(0))) {
                char[] chars = name.toCharArray();
                chars[0] = Character.toLowerCase(chars[0]);
                return new String(chars);
            }
            return name;
        }
        return name;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean setAccessible(Class<?> clazz, Member member, int classMofifiers) {
        if (member == 0 || !setAccessibleEnable) {
            return false;
        }
        Class<?> supperClass = clazz.getSuperclass();
        if ((supperClass == null || supperClass == Object.class) && (member.getModifiers() & 1) != 0 && (classMofifiers & 1) != 0) {
            return false;
        }
        AccessibleObject obj = (AccessibleObject) member;
        try {
            obj.setAccessible(true);
            return true;
        } catch (AccessControlException e) {
            setAccessibleEnable = false;
            return false;
        }
    }

    public static Field getField(Class<?> clazz, String fieldName, Field[] declaredFields) {
        return getField(clazz, fieldName, declaredFields, null);
    }

    public static Field getField(Class<?> clazz, String fieldName, Field[] declaredFields, Map<Class<?>, Field[]> classFieldCache) {
        Field field = getField0(clazz, fieldName, declaredFields, classFieldCache);
        if (field == null) {
            field = getField0(clazz, "_" + fieldName, declaredFields, classFieldCache);
        }
        if (field == null) {
            field = getField0(clazz, "m_" + fieldName, declaredFields, classFieldCache);
        }
        if (field == null) {
            String mName = C0816m.f1430a + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            return getField0(clazz, mName, declaredFields, classFieldCache);
        }
        return field;
    }

    private static Field getField0(Class<?> clazz, String fieldName, Field[] declaredFields, Map<Class<?>, Field[]> classFieldCache) {
        char c0;
        char c1;
        for (Field item : declaredFields) {
            String itemName = item.getName();
            if (!fieldName.equals(itemName)) {
                if (fieldName.length() > 2 && (c0 = fieldName.charAt(0)) >= 'a' && c0 <= 'z' && (c1 = fieldName.charAt(1)) >= 'A' && c1 <= 'Z' && fieldName.equalsIgnoreCase(itemName)) {
                    return item;
                }
            } else {
                return item;
            }
        }
        Class<?> superClass = clazz.getSuperclass();
        if (superClass == null || superClass == Object.class) {
            return null;
        }
        Field[] superClassFields = classFieldCache != null ? classFieldCache.get(superClass) : null;
        if (superClassFields == null) {
            superClassFields = superClass.getDeclaredFields();
            if (classFieldCache != null) {
                classFieldCache.put(superClass, superClassFields);
            }
        }
        return getField(superClass, fieldName, superClassFields, classFieldCache);
    }

    public static Type getCollectionItemType(Type fieldType) {
        Type itemType = null;
        if (fieldType instanceof ParameterizedType) {
            Type actualTypeArgument = ((ParameterizedType) fieldType).getActualTypeArguments()[0];
            if (actualTypeArgument instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) actualTypeArgument;
                Type[] upperBounds = wildcardType.getUpperBounds();
                if (upperBounds.length == 1) {
                    actualTypeArgument = upperBounds[0];
                }
            }
            itemType = actualTypeArgument;
        } else if (fieldType instanceof Class) {
            Class<?> clazz = (Class) fieldType;
            if (!clazz.getName().startsWith("java.")) {
                Type superClass = clazz.getGenericSuperclass();
                itemType = getCollectionItemType(superClass);
            }
        }
        if (itemType == null) {
            return Object.class;
        }
        return itemType;
    }

    public static Object defaultValue(Class<?> fieldType) {
        if (fieldType == Byte.TYPE) {
            return (byte) 0;
        }
        if (fieldType == Short.TYPE) {
            return (short) 0;
        }
        if (fieldType == Integer.TYPE) {
            return 0;
        }
        if (fieldType == Long.TYPE) {
            return 0L;
        }
        if (fieldType == Float.TYPE) {
            return Float.valueOf(0.0f);
        }
        if (fieldType == Double.TYPE) {
            return Double.valueOf(0.0d);
        }
        if (fieldType == Boolean.TYPE) {
            return Boolean.FALSE;
        }
        if (fieldType == Character.TYPE) {
            return '0';
        }
        return null;
    }

    public static boolean getArgument(Type[] typeArgs, TypeVariable[] typeVariables, Type[] arguments) {
        if (arguments == null || typeVariables.length == 0) {
            return false;
        }
        boolean changed = false;
        for (int i = 0; i < typeArgs.length; i++) {
            Type typeArg = typeArgs[i];
            if (typeArg instanceof ParameterizedType) {
                ParameterizedType p_typeArg = (ParameterizedType) typeArg;
                Type[] p_typeArg_args = p_typeArg.getActualTypeArguments();
                boolean p_changed = getArgument(p_typeArg_args, typeVariables, arguments);
                if (p_changed) {
                    typeArgs[i] = new ParameterizedTypeImpl(p_typeArg_args, p_typeArg.getOwnerType(), p_typeArg.getRawType());
                    changed = true;
                }
            } else if (typeArg instanceof TypeVariable) {
                for (int j = 0; j < typeVariables.length; j++) {
                    if (typeArg.equals(typeVariables[j])) {
                        typeArgs[i] = arguments[j];
                        changed = true;
                    }
                }
            }
        }
        return changed;
    }

    public static double parseDouble(String str) {
        int len = str.length();
        if (len > 10) {
            return Double.parseDouble(str);
        }
        boolean negative = false;
        long longValue = 0;
        int scale = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == '-' && i == 0) {
                negative = true;
            } else if (ch == '.') {
                if (scale != 0) {
                    return Double.parseDouble(str);
                }
                scale = (len - i) - 1;
            } else if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                longValue = (10 * longValue) + ((long) digit);
            } else {
                return Double.parseDouble(str);
            }
        }
        if (negative) {
            longValue = -longValue;
        }
        switch (scale) {
            case 0:
                return longValue;
            case 1:
                return longValue / 10.0d;
            case 2:
                return longValue / 100.0d;
            case 3:
                return longValue / 1000.0d;
            case 4:
                return longValue / 10000.0d;
            case 5:
                return longValue / 100000.0d;
            case 6:
                return longValue / 1000000.0d;
            case 7:
                return longValue / 1.0E7d;
            case 8:
                return longValue / 1.0E8d;
            case 9:
                return longValue / 1.0E9d;
            default:
                return Double.parseDouble(str);
        }
    }

    public static float parseFloat(String str) {
        int len = str.length();
        if (len >= 10) {
            return Float.parseFloat(str);
        }
        boolean negative = false;
        long longValue = 0;
        int scale = 0;
        for (int i = 0; i < len; i++) {
            char ch = str.charAt(i);
            if (ch == '-' && i == 0) {
                negative = true;
            } else if (ch == '.') {
                if (scale != 0) {
                    return Float.parseFloat(str);
                }
                scale = (len - i) - 1;
            } else if (ch >= '0' && ch <= '9') {
                int digit = ch - '0';
                longValue = (10 * longValue) + ((long) digit);
            } else {
                return Float.parseFloat(str);
            }
        }
        if (negative) {
            longValue = -longValue;
        }
        switch (scale) {
            case 0:
                return longValue;
            case 1:
                return longValue / 10.0f;
            case 2:
                return longValue / 100.0f;
            case 3:
                return longValue / 1000.0f;
            case 4:
                return longValue / 10000.0f;
            case 5:
                return longValue / 100000.0f;
            case 6:
                return longValue / 1000000.0f;
            case 7:
                return longValue / 1.0E7f;
            case 8:
                return longValue / 1.0E8f;
            case 9:
                return longValue / 1.0E9f;
            default:
                return Float.parseFloat(str);
        }
    }

    public static long fnv_64_lower(String key) {
        if (key == null) {
            return 0L;
        }
        long hashCode = -3750763034362895579L;
        for (int i = 0; i < key.length(); i++) {
            char ch = key.charAt(i);
            if (ch != '_' && ch != '-') {
                if (ch >= 'A' && ch <= 'Z') {
                    ch = (char) (ch + ' ');
                }
                hashCode = (hashCode ^ ((long) ch)) * 1099511628211L;
            }
        }
        return hashCode;
    }

    public static void addMapping(String className, Class<?> clazz) {
        mappings.put(className, clazz);
    }

    public static Type checkPrimitiveArray(GenericArrayType genericArrayType) {
        Type clz = genericArrayType;
        Type genericComponentType = genericArrayType.getGenericComponentType();
        String prefix = "[";
        while (genericComponentType instanceof GenericArrayType) {
            genericComponentType = ((GenericArrayType) genericComponentType).getGenericComponentType();
            prefix = prefix + prefix;
        }
        if (genericComponentType instanceof Class) {
            Class<?> ck = (Class) genericComponentType;
            if (ck.isPrimitive()) {
                try {
                    if (ck == Boolean.TYPE) {
                        clz = Class.forName(prefix + "Z");
                    } else if (ck == Character.TYPE) {
                        clz = Class.forName(prefix + "C");
                    } else if (ck == Byte.TYPE) {
                        clz = Class.forName(prefix + "B");
                    } else if (ck == Short.TYPE) {
                        clz = Class.forName(prefix + "S");
                    } else if (ck == Integer.TYPE) {
                        clz = Class.forName(prefix + "I");
                    } else if (ck == Long.TYPE) {
                        clz = Class.forName(prefix + "J");
                    } else if (ck == Float.TYPE) {
                        clz = Class.forName(prefix + TessBaseAPI.f2988f);
                    } else if (ck == Double.TYPE) {
                        clz = Class.forName(prefix + "D");
                    }
                } catch (ClassNotFoundException e) {
                }
            }
        }
        return clz;
    }
}

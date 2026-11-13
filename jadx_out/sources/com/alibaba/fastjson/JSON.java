package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldTypeResolver;
import com.alibaba.fastjson.parser.deserializer.ParseProcess;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.JavaBeanSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.ObjectSerializer;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes.dex */
public abstract class JSON implements JSONStreamAware, JSONAware {
    public static int DEFAULT_GENERATE_FEATURE = 0;
    public static int DEFAULT_PARSER_FEATURE = 0;
    public static final String DEFAULT_TYPE_KEY = "@type";
    public static String DEFFAULT_DATE_FORMAT = null;
    public static final String VERSION = "1.1.72";
    public static TimeZone defaultTimeZone = TimeZone.getDefault();
    public static Locale defaultLocale = Locale.getDefault();

    static {
        int features = 0 | Feature.UseBigDecimal.mask;
        DEFAULT_PARSER_FEATURE = features | Feature.SortFeidFastMatch.mask | Feature.IgnoreNotMatch.mask;
        DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        int features2 = 0 | SerializerFeature.QuoteFieldNames.mask;
        DEFAULT_GENERATE_FEATURE = features2 | SerializerFeature.SkipTransientField.mask | SerializerFeature.WriteEnumUsingToString.mask | SerializerFeature.SortField.mask;
    }

    public static final Object parse(String text) {
        return parse(text, DEFAULT_PARSER_FEATURE);
    }

    public static final Object parse(String text, int features) {
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.global, features);
        Object value = parser.parse(null);
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static final Object parse(byte[] input, Feature... features) {
        try {
            return parseObject(new String(input, "UTF-8"), features);
        } catch (UnsupportedEncodingException e) {
            throw new JSONException("UTF-8 not support", e);
        }
    }

    public static final Object parse(String text, Feature... features) {
        int featureValues = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featureValues |= feature.mask;
        }
        return parse(text, featureValues);
    }

    public static final JSONObject parseObject(String text, Feature... features) {
        Object obj = parse(text, features);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        JSONObject jsonObject = (JSONObject) toJSON(obj);
        boolean autoTypeSupport = (DEFAULT_PARSER_FEATURE & Feature.SupportAutoType.mask) != 0;
        if (!autoTypeSupport) {
            for (Feature feature : features) {
                if (feature == Feature.SupportAutoType) {
                    autoTypeSupport = true;
                }
            }
        }
        if (autoTypeSupport) {
            jsonObject.put(DEFAULT_TYPE_KEY, (Object) obj.getClass().getName());
        }
        return jsonObject;
    }

    public static final JSONObject parseObject(String text) {
        Object obj = parse(text);
        if ((obj instanceof JSONObject) || obj == null) {
            return (JSONObject) obj;
        }
        JSONObject jsonObject = (JSONObject) toJSON(obj);
        boolean autoTypeSupport = (DEFAULT_PARSER_FEATURE & Feature.SupportAutoType.mask) != 0;
        if (autoTypeSupport) {
            jsonObject.put(DEFAULT_TYPE_KEY, (Object) obj.getClass().getName());
            return jsonObject;
        }
        return jsonObject;
    }

    public static <T> T parseObject(String str, Type type, ParserConfig parserConfig, Feature... featureArr) {
        return (T) parseObject(str, type, parserConfig, null, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, TypeReference<T> typeReference, Feature... featureArr) {
        return (T) parseObject(str, typeReference.type, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Class<T> cls, Feature... featureArr) {
        return (T) parseObject(str, cls, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Class<T> cls, ParseProcess parseProcess, Feature... featureArr) {
        return (T) parseObject(str, cls, ParserConfig.global, parseProcess, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, ParseProcess parseProcess, Feature... featureArr) {
        return (T) parseObject(str, type, ParserConfig.global, parseProcess, DEFAULT_PARSER_FEATURE, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, int i, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i |= feature.mask;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, ParserConfig.global, i);
        T t = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t);
        defaultJSONParser.close();
        return t;
    }

    public static final <T> T parseObject(String str, Type type, ParserConfig parserConfig, int i, Feature... featureArr) {
        return (T) parseObject(str, type, parserConfig, null, i, featureArr);
    }

    public static final <T> T parseObject(String str, Type type, ParserConfig parserConfig, ParseProcess parseProcess, int i, Feature... featureArr) {
        if (str == null) {
            return null;
        }
        for (Feature feature : featureArr) {
            i |= feature.mask;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(str, parserConfig, i);
        if (parseProcess instanceof ExtraTypeProvider) {
            defaultJSONParser.getExtraTypeProviders().add((ExtraTypeProvider) parseProcess);
        }
        if (parseProcess instanceof ExtraProcessor) {
            defaultJSONParser.getExtraProcessors().add((ExtraProcessor) parseProcess);
        }
        if (parseProcess instanceof FieldTypeResolver) {
            defaultJSONParser.fieldTypeResolver = (FieldTypeResolver) parseProcess;
        }
        T t = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t);
        defaultJSONParser.close();
        return t;
    }

    public static final <T> T parseObject(byte[] bArr, Type type, Feature... featureArr) {
        try {
            return (T) parseObject(new String(bArr, "UTF-8"), type, featureArr);
        } catch (UnsupportedEncodingException e) {
            throw new JSONException("UTF-8 not support");
        }
    }

    public static final <T> T parseObject(char[] cArr, int i, Type type, Feature... featureArr) {
        if (cArr == null || cArr.length == 0) {
            return null;
        }
        int i2 = DEFAULT_PARSER_FEATURE;
        for (Feature feature : featureArr) {
            i2 |= feature.mask;
        }
        DefaultJSONParser defaultJSONParser = new DefaultJSONParser(cArr, i, ParserConfig.global, i2);
        T t = (T) defaultJSONParser.parseObject(type);
        defaultJSONParser.handleResovleTask(t);
        defaultJSONParser.close();
        return t;
    }

    public static final <T> T parseObject(String str, Class<T> cls) {
        return (T) parseObject(str, (Class) cls, new Feature[0]);
    }

    public static final JSONArray parseArray(String text) {
        return parseArray(text, new Feature[0]);
    }

    public static final JSONArray parseArray(String text, Feature... features) {
        JSONArray array;
        if (text == null) {
            return null;
        }
        int featuresValue = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featuresValue |= feature.mask;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.global, featuresValue);
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 8) {
            lexer.nextToken();
            array = null;
        } else if (token == 20) {
            array = null;
        } else {
            array = new JSONArray();
            parser.parseArray(array, (Object) null);
            parser.handleResovleTask(array);
        }
        parser.close();
        return array;
    }

    public static final <T> List<T> parseArray(String text, Class<T> clazz) {
        List<T> list;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.global);
        JSONLexer lexer = parser.lexer;
        int token = lexer.token();
        if (token == 8) {
            lexer.nextToken();
            list = null;
        } else if (token == 20 && lexer.isBlankInput()) {
            list = null;
        } else {
            list = new ArrayList<>();
            parser.parseArray((Class<?>) clazz, (Collection) list);
            parser.handleResovleTask(list);
        }
        parser.close();
        return list;
    }

    public static final List<Object> parseArray(String text, Type[] types) {
        List<Object> list;
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, ParserConfig.global);
        Object[] objectArray = parser.parseArray(types);
        if (objectArray == null) {
            list = null;
        } else {
            list = Arrays.asList(objectArray);
        }
        parser.handleResovleTask(list);
        parser.close();
        return list;
    }

    public static Object parse(String text, ParserConfig config) {
        return parse(text, config, DEFAULT_PARSER_FEATURE);
    }

    public static Object parse(String text, ParserConfig config, Feature... features) {
        int featuresValue = DEFAULT_PARSER_FEATURE;
        for (Feature feature : features) {
            featuresValue |= feature.mask;
        }
        return parse(text, config, featuresValue);
    }

    public static Object parse(String text, ParserConfig config, int features) {
        if (text == null) {
            return null;
        }
        DefaultJSONParser parser = new DefaultJSONParser(text, config, features);
        Object value = parser.parse();
        parser.handleResovleTask(value);
        parser.close();
        return value;
    }

    public static final String toJSONString(Object object) {
        return toJSONString(object, SerializeConfig.globalInstance, null, null, DEFAULT_GENERATE_FEATURE, new SerializerFeature[0]);
    }

    public static final String toJSONString(Object object, SerializerFeature... features) {
        return toJSONString(object, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final String toJSONString(Object object, int defaultFeatures, SerializerFeature... features) {
        return toJSONString(object, SerializeConfig.globalInstance, null, null, defaultFeatures, features);
    }

    public static final String toJSONStringWithDateFormat(Object object, String dateFormat, SerializerFeature... features) {
        return toJSONString(object, SerializeConfig.globalInstance, null, dateFormat, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final String toJSONString(Object object, SerializeFilter filter, SerializerFeature... features) {
        return toJSONString(object, SerializeConfig.globalInstance, new SerializeFilter[]{filter}, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final String toJSONString(Object object, SerializeFilter[] filters, SerializerFeature... features) {
        return toJSONString(object, SerializeConfig.globalInstance, filters, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final byte[] toJSONBytes(Object object, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter((Writer) null, DEFAULT_GENERATE_FEATURE, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, SerializeConfig.globalInstance);
            serializer.write(object);
            return out.toBytes("UTF-8");
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, SerializeConfig config, SerializerFeature... features) {
        return toJSONString(object, config, null, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final String toJSONString(Object object, SerializeConfig config, SerializeFilter filter, SerializerFeature... features) {
        return toJSONString(object, config, new SerializeFilter[]{filter}, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final String toJSONString(Object object, SerializeConfig config, SerializeFilter[] filters, SerializerFeature... features) {
        return toJSONString(object, config, filters, null, DEFAULT_GENERATE_FEATURE, features);
    }

    public static final String toJSONStringZ(Object object, SerializeConfig mapping, SerializerFeature... features) {
        return toJSONString(object, SerializeConfig.globalInstance, null, null, 0, features);
    }

    public static final byte[] toJSONBytes(Object object, SerializeConfig config, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter((Writer) null, DEFAULT_GENERATE_FEATURE, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            serializer.write(object);
            return out.toBytes("UTF-8");
        } finally {
            out.close();
        }
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, int defaultFeatures, SerializerFeature... features) {
        return toJSONBytes(object, config, new SerializeFilter[0], defaultFeatures, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeFilter[] filters, SerializerFeature... features) {
        return toJSONBytes(object, SerializeConfig.globalInstance, filters, DEFAULT_GENERATE_FEATURE, features);
    }

    public static byte[] toJSONBytes(Object object, SerializeConfig config, SerializeFilter[] filters, int defaultFeatures, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    if (filter != null) {
                        if (filter instanceof PropertyPreFilter) {
                            serializer.getPropertyPreFilters().add((PropertyPreFilter) filter);
                        }
                        if (filter instanceof NameFilter) {
                            serializer.getNameFilters().add((NameFilter) filter);
                        }
                        if (filter instanceof ValueFilter) {
                            serializer.getValueFilters().add((ValueFilter) filter);
                        }
                        if (filter instanceof PropertyFilter) {
                            serializer.getPropertyFilters().add((PropertyFilter) filter);
                        }
                        if (filter instanceof BeforeFilter) {
                            serializer.getBeforeFilters().add((BeforeFilter) filter);
                        }
                        if (filter instanceof AfterFilter) {
                            serializer.getAfterFilters().add((AfterFilter) filter);
                        }
                    }
                }
            }
            serializer.write(object);
            return out.toBytes("UTF-8");
        } finally {
            out.close();
        }
    }

    public static final String toJSONString(Object object, boolean prettyFormat) {
        return !prettyFormat ? toJSONString(object) : toJSONString(object, SerializerFeature.PrettyFormat);
    }

    public static final void writeJSONStringTo(Object object, Writer writer, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(writer, DEFAULT_GENERATE_FEATURE, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, SerializeConfig.globalInstance);
            serializer.write(object);
        } finally {
            out.close();
        }
    }

    public String toString() {
        return toJSONString();
    }

    @Override // com.alibaba.fastjson.JSONAware
    public String toJSONString() {
        SerializeWriter out = new SerializeWriter((Writer) null, DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
        try {
            new JSONSerializer(out, SerializeConfig.globalInstance).write(this);
            return out.toString();
        } finally {
            out.close();
        }
    }

    @Override // com.alibaba.fastjson.JSONStreamAware
    public void writeJSONString(Appendable appendable) {
        SerializeWriter out = new SerializeWriter((Writer) null, DEFAULT_GENERATE_FEATURE, SerializerFeature.EMPTY);
        try {
            try {
                new JSONSerializer(out, SerializeConfig.globalInstance).write(this);
                appendable.append(out.toString());
                out.close();
            } catch (IOException e) {
                throw new JSONException(e.getMessage(), e);
            }
        } catch (Throwable th) {
            out.close();
            throw th;
        }
    }

    public static final Object toJSON(Object javaObject) {
        return toJSON(javaObject, SerializeConfig.globalInstance);
    }

    @Deprecated
    public static final Object toJSON(Object javaObject, ParserConfig mapping) {
        return toJSON(javaObject, SerializeConfig.globalInstance);
    }

    public static Object toJSON(Object javaObject, SerializeConfig config) {
        Map innerMap;
        if (javaObject == null) {
            return null;
        }
        if (javaObject instanceof JSON) {
            return (JSON) javaObject;
        }
        if (javaObject instanceof Map) {
            Map<Object, Object> map = (Map) javaObject;
            int size = map.size();
            if (map instanceof LinkedHashMap) {
                innerMap = new LinkedHashMap(size);
            } else if (map instanceof TreeMap) {
                innerMap = new TreeMap();
            } else {
                innerMap = new HashMap(size);
            }
            JSONObject json = new JSONObject((Map<String, Object>) innerMap);
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object key = entry.getKey();
                String jsonKey = TypeUtils.castToString(key);
                Object jsonValue = toJSON(entry.getValue());
                json.put(jsonKey, jsonValue);
            }
            return json;
        }
        if (javaObject instanceof Collection) {
            Collection<Object> collection = (Collection) javaObject;
            JSONArray array = new JSONArray(collection.size());
            for (Object item : collection) {
                Object jsonValue2 = toJSON(item);
                array.add(jsonValue2);
            }
            return array;
        }
        Class<?> clazz = javaObject.getClass();
        if (clazz.isEnum()) {
            return ((Enum) javaObject).name();
        }
        if (clazz.isArray()) {
            int len = Array.getLength(javaObject);
            JSONArray array2 = new JSONArray(len);
            for (int i = 0; i < len; i++) {
                Object item2 = Array.get(javaObject, i);
                Object jsonValue3 = toJSON(item2);
                array2.add(jsonValue3);
            }
            return array2;
        }
        if (!ParserConfig.isPrimitive(clazz)) {
            ObjectSerializer serializer = config.get(clazz);
            if (serializer instanceof JavaBeanSerializer) {
                JavaBeanSerializer javaBeanSerializer = (JavaBeanSerializer) serializer;
                JSONObject json2 = new JSONObject();
                try {
                    Map<String, Object> values = javaBeanSerializer.getFieldValuesMap(javaObject);
                    for (Map.Entry<String, Object> entry2 : values.entrySet()) {
                        json2.put(entry2.getKey(), toJSON(entry2.getValue()));
                    }
                    return json2;
                } catch (Exception e) {
                    throw new JSONException("toJSON error", e);
                }
            }
            return null;
        }
        return javaObject;
    }

    public static final <T> T toJavaObject(JSON json, Class<T> cls) {
        return (T) TypeUtils.cast((Object) json, (Class) cls, ParserConfig.global);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T toJavaObject(Class<T> cls) {
        return cls == Map.class ? this : (T) TypeUtils.cast(this, cls, ParserConfig.getGlobalInstance(), 0);
    }

    public static String toJSONString(Object object, SerializeConfig config, SerializeFilter[] filters, String dateFormat, int defaultFeatures, SerializerFeature... features) {
        SerializeWriter out = new SerializeWriter(null, defaultFeatures, features);
        try {
            JSONSerializer serializer = new JSONSerializer(out, config);
            for (SerializerFeature feature : features) {
                serializer.config(feature, true);
            }
            if (dateFormat != null && dateFormat.length() != 0) {
                serializer.setDateFormat(dateFormat);
                serializer.config(SerializerFeature.WriteDateUseDateFormat, true);
            }
            if (filters != null) {
                for (SerializeFilter filter : filters) {
                    if (filter != null) {
                        if (filter instanceof PropertyPreFilter) {
                            serializer.getPropertyPreFilters().add((PropertyPreFilter) filter);
                        }
                        if (filter instanceof NameFilter) {
                            serializer.getNameFilters().add((NameFilter) filter);
                        }
                        if (filter instanceof ValueFilter) {
                            serializer.getValueFilters().add((ValueFilter) filter);
                        }
                        if (filter instanceof PropertyFilter) {
                            serializer.getPropertyFilters().add((PropertyFilter) filter);
                        }
                        if (filter instanceof BeforeFilter) {
                            serializer.getBeforeFilters().add((BeforeFilter) filter);
                        }
                        if (filter instanceof AfterFilter) {
                            serializer.getAfterFilters().add((AfterFilter) filter);
                        }
                    }
                }
            }
            serializer.write(object);
            return out.toString();
        } finally {
            out.close();
        }
    }
}

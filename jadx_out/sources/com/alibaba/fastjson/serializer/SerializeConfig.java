package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.PropertyNamingStrategy;
import com.alibaba.fastjson.util.IdentityHashMap;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.AbstractSequentialList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Currency;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public class SerializeConfig {
    public static final SerializeConfig globalInstance = new SerializeConfig();
    public PropertyNamingStrategy propertyNamingStrategy;
    protected String typeKey = JSON.DEFAULT_TYPE_KEY;
    private final IdentityHashMap<ObjectSerializer> serializers = new IdentityHashMap<>(1024);

    public static final SerializeConfig getGlobalInstance() {
        return globalInstance;
    }

    public ObjectSerializer registerIfNotExists(Class<?> clazz) {
        return registerIfNotExists(clazz, clazz.getModifiers(), false, true, true, true);
    }

    public ObjectSerializer registerIfNotExists(Class<?> clazz, int classModifers, boolean fieldOnly, boolean jsonTypeSupport, boolean jsonFieldSupport, boolean fieldGenericSupport) {
        ObjectSerializer serializer = this.serializers.get(clazz);
        if (serializer == null) {
            ObjectSerializer serializer2 = new JavaBeanSerializer(clazz, classModifers, null, fieldOnly, jsonTypeSupport, jsonFieldSupport, fieldGenericSupport, this.propertyNamingStrategy);
            this.serializers.put(clazz, serializer2);
            return serializer2;
        }
        return serializer;
    }

    public SerializeConfig() {
        this.serializers.put(Boolean.class, BooleanCodec.instance);
        this.serializers.put(Character.class, MiscCodec.instance);
        this.serializers.put(Byte.class, IntegerCodec.instance);
        this.serializers.put(Short.class, IntegerCodec.instance);
        this.serializers.put(Integer.class, IntegerCodec.instance);
        this.serializers.put(Long.class, IntegerCodec.instance);
        this.serializers.put(Float.class, NumberCodec.instance);
        this.serializers.put(Double.class, NumberCodec.instance);
        this.serializers.put(Number.class, NumberCodec.instance);
        this.serializers.put(BigDecimal.class, BigDecimalCodec.instance);
        this.serializers.put(BigInteger.class, BigDecimalCodec.instance);
        this.serializers.put(String.class, StringCodec.instance);
        this.serializers.put(Object[].class, ArrayCodec.instance);
        this.serializers.put(Class.class, MiscCodec.instance);
        this.serializers.put(SimpleDateFormat.class, MiscCodec.instance);
        this.serializers.put(Locale.class, MiscCodec.instance);
        this.serializers.put(Currency.class, MiscCodec.instance);
        this.serializers.put(TimeZone.class, MiscCodec.instance);
        this.serializers.put(UUID.class, MiscCodec.instance);
        this.serializers.put(URI.class, MiscCodec.instance);
        this.serializers.put(URL.class, MiscCodec.instance);
        this.serializers.put(Pattern.class, MiscCodec.instance);
        this.serializers.put(Charset.class, MiscCodec.instance);
    }

    public ObjectSerializer get(Class<?> clazz) {
        Class<?> superClass;
        ObjectSerializer writer = this.serializers.get(clazz);
        if (writer == null) {
            if (Map.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap = this.serializers;
                writer = new MapSerializer();
                identityHashMap.put(clazz, writer);
            } else if (AbstractSequentialList.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap2 = this.serializers;
                writer = CollectionCodec.instance;
                identityHashMap2.put(clazz, writer);
            } else if (List.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap3 = this.serializers;
                writer = new ListSerializer();
                identityHashMap3.put(clazz, writer);
            } else if (Collection.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap4 = this.serializers;
                writer = CollectionCodec.instance;
                identityHashMap4.put(clazz, writer);
            } else if (Date.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap5 = this.serializers;
                writer = DateCodec.instance;
                identityHashMap5.put(clazz, writer);
            } else if (JSONAware.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap6 = this.serializers;
                writer = MiscCodec.instance;
                identityHashMap6.put(clazz, writer);
            } else if (JSONSerializable.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap7 = this.serializers;
                writer = MiscCodec.instance;
                identityHashMap7.put(clazz, writer);
            } else if (JSONStreamAware.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap8 = this.serializers;
                writer = MiscCodec.instance;
                identityHashMap8.put(clazz, writer);
            } else if (clazz.isEnum() || ((superClass = clazz.getSuperclass()) != null && superClass != Object.class && superClass.isEnum())) {
                IdentityHashMap<ObjectSerializer> identityHashMap9 = this.serializers;
                writer = new EnumSerializer();
                identityHashMap9.put(clazz, writer);
            } else if (clazz.isArray()) {
                Class<?> componentType = clazz.getComponentType();
                ObjectSerializer compObjectSerializer = get(componentType);
                IdentityHashMap<ObjectSerializer> identityHashMap10 = this.serializers;
                writer = new ArraySerializer(componentType, compObjectSerializer);
                identityHashMap10.put(clazz, writer);
            } else if (Throwable.class.isAssignableFrom(clazz)) {
                JavaBeanSerializer serializer = new JavaBeanSerializer(clazz, this.propertyNamingStrategy);
                serializer.features |= SerializerFeature.WriteClassName.mask;
                writer = serializer;
                this.serializers.put(clazz, serializer);
            } else if (TimeZone.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap11 = this.serializers;
                writer = MiscCodec.instance;
                identityHashMap11.put(clazz, writer);
            } else if (Charset.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap12 = this.serializers;
                writer = MiscCodec.instance;
                identityHashMap12.put(clazz, writer);
            } else if (Enumeration.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap13 = this.serializers;
                writer = MiscCodec.instance;
                identityHashMap13.put(clazz, writer);
            } else if (Calendar.class.isAssignableFrom(clazz)) {
                IdentityHashMap<ObjectSerializer> identityHashMap14 = this.serializers;
                writer = DateCodec.instance;
                identityHashMap14.put(clazz, writer);
            } else {
                boolean isCglibProxy = false;
                boolean isJavassistProxy = false;
                for (Class<?> item : clazz.getInterfaces()) {
                    if (item.getName().equals("net.sf.cglib.proxy.Factory") || item.getName().equals("org.springframework.cglib.proxy.Factory")) {
                        isCglibProxy = true;
                        break;
                    }
                    if (item.getName().equals("javassist.util.proxy.ProxyObject")) {
                        isJavassistProxy = true;
                        break;
                    }
                }
                if (isCglibProxy || isJavassistProxy) {
                    Class<?> superClazz = clazz.getSuperclass();
                    ObjectSerializer superWriter = get(superClazz);
                    this.serializers.put(clazz, superWriter);
                    return superWriter;
                }
                String className = clazz.getName();
                if (className.startsWith("android.net.Uri$")) {
                    writer = MiscCodec.instance;
                } else {
                    writer = new JavaBeanSerializer(clazz, this.propertyNamingStrategy);
                }
                this.serializers.put(clazz, writer);
            }
            if (writer == null) {
                writer = this.serializers.get(clazz);
            }
        }
        return writer;
    }

    public boolean put(Type key, ObjectSerializer value) {
        return this.serializers.put(key, value);
    }

    public String getTypeKey() {
        return this.typeKey;
    }

    public void setTypeKey(String typeKey) {
        this.typeKey = typeKey;
    }
}

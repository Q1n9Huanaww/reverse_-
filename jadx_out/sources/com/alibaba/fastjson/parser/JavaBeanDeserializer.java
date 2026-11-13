package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessable;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;
import com.umeng.analytics.pro.C1708b;
import com.umeng.commonsdk.proguard.C1799v;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: loaded from: classes.dex */
public class JavaBeanDeserializer implements ObjectDeserializer {
    private final Map<String, FieldDeserializer> alterNameFieldDeserializers;
    public final JavaBeanInfo beanInfo;
    protected final Class<?> clazz;
    private ConcurrentMap<String, Object> extraFieldDeserializers;
    private final FieldDeserializer[] fieldDeserializers;
    private transient long[] smartMatchHashArray;
    private transient int[] smartMatchHashArrayMapping;
    private final FieldDeserializer[] sortedFieldDeserializers;

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz, Type type) {
        this(config, clazz, type, JavaBeanInfo.build(clazz, clazz.getModifiers(), type, false, true, true, true, config.propertyNamingStrategy));
    }

    public JavaBeanDeserializer(ParserConfig config, Class<?> clazz, Type type, JavaBeanInfo beanInfo) {
        this.clazz = clazz;
        this.beanInfo = beanInfo;
        Map<String, FieldDeserializer> alterNameFieldDeserializers = null;
        this.sortedFieldDeserializers = new FieldDeserializer[beanInfo.sortedFields.length];
        int size = beanInfo.sortedFields.length;
        for (int i = 0; i < size; i++) {
            FieldInfo fieldInfo = beanInfo.sortedFields[i];
            FieldDeserializer fieldDeserializer = config.createFieldDeserializer(config, clazz, fieldInfo);
            this.sortedFieldDeserializers[i] = fieldDeserializer;
            for (String name : fieldInfo.alternateNames) {
                if (alterNameFieldDeserializers == null) {
                    alterNameFieldDeserializers = new HashMap<>();
                }
                alterNameFieldDeserializers.put(name, fieldDeserializer);
            }
        }
        this.alterNameFieldDeserializers = alterNameFieldDeserializers;
        this.fieldDeserializers = new FieldDeserializer[beanInfo.fields.length];
        int size2 = beanInfo.fields.length;
        for (int i2 = 0; i2 < size2; i2++) {
            this.fieldDeserializers[i2] = getFieldDeserializer(beanInfo.fields[i2].name);
        }
    }

    protected Object createInstance(DefaultJSONParser parser, Type type) {
        Object object;
        if ((type instanceof Class) && this.clazz.isInterface()) {
            Class<?> clazz = (Class) type;
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            boolean ordered = (parser.lexer.features & Feature.OrderedField.mask) != 0;
            JSONObject object2 = new JSONObject(ordered);
            return Proxy.newProxyInstance(loader, new Class[]{clazz}, object2);
        }
        if (this.beanInfo.defaultConstructor == null && this.beanInfo.factoryMethod == null) {
            return null;
        }
        if (this.beanInfo.factoryMethod != null && this.beanInfo.defaultConstructorParameterSize > 0) {
            return null;
        }
        try {
            Constructor<?> constructor = this.beanInfo.defaultConstructor;
            if (this.beanInfo.defaultConstructorParameterSize == 0) {
                if (constructor != null) {
                    object = constructor.newInstance(new Object[0]);
                } else {
                    object = this.beanInfo.factoryMethod.invoke(null, new Object[0]);
                }
            } else {
                object = constructor.newInstance(parser.contex.object);
            }
            if (parser != null && (parser.lexer.features & Feature.InitStringFieldAsEmpty.mask) != 0) {
                for (FieldInfo fieldInfo : this.beanInfo.fields) {
                    if (fieldInfo.fieldClass == String.class) {
                        fieldInfo.set(object, "");
                    }
                }
            }
            return object;
        } catch (Exception e) {
            throw new JSONException("create instance error, class " + this.clazz.getName(), e);
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null);
    }

    private <T> T deserialzeArrayMapping(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) {
        Enum enumValueOf;
        String strScanStringValue;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        T t = (T) createInstance(defaultJSONParser, type);
        int length = this.sortedFieldDeserializers.length;
        int i = 0;
        while (i < length) {
            char c = i == length + (-1) ? ']' : ',';
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            FieldInfo fieldInfo = fieldDeserializer.fieldInfo;
            Class<?> cls = fieldInfo.fieldClass;
            try {
                if (cls == Integer.TYPE) {
                    int iScanLongValue = (int) jSONLexer.scanLongValue();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setInt(t, iScanLongValue);
                    } else {
                        fieldDeserializer.setValue(t, new Integer(iScanLongValue));
                    }
                    if (jSONLexer.f44ch == ',') {
                        int i2 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i2;
                        jSONLexer.f44ch = i2 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i2);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.f44ch == ']') {
                        int i3 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i3;
                        jSONLexer.f44ch = i3 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i3);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls == String.class) {
                    if (jSONLexer.f44ch == '\"') {
                        strScanStringValue = jSONLexer.scanStringValue('\"');
                    } else if (jSONLexer.f44ch == 'n' && jSONLexer.text.startsWith("null", jSONLexer.f43bp)) {
                        jSONLexer.f43bp += 4;
                        jSONLexer.f44ch = jSONLexer.f43bp >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(jSONLexer.f43bp);
                        strScanStringValue = null;
                    } else {
                        throw new JSONException("not match string. feild : " + obj);
                    }
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.set(t, strScanStringValue);
                    } else {
                        fieldDeserializer.setValue(t, strScanStringValue);
                    }
                    if (jSONLexer.f44ch == ',') {
                        int i4 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i4;
                        jSONLexer.f44ch = i4 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i4);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.f44ch == ']') {
                        int i5 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i5;
                        jSONLexer.f44ch = i5 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i5);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls == Long.TYPE) {
                    long jScanLongValue = jSONLexer.scanLongValue();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setLong(t, jScanLongValue);
                    } else {
                        fieldDeserializer.setValue(t, new Long(jScanLongValue));
                    }
                    if (jSONLexer.f44ch == ',') {
                        int i6 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i6;
                        jSONLexer.f44ch = i6 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i6);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.f44ch == ']') {
                        int i7 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i7;
                        jSONLexer.f44ch = i7 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i7);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls == Boolean.TYPE) {
                    boolean zScanBoolean = jSONLexer.scanBoolean();
                    if (fieldInfo.fieldAccess) {
                        fieldInfo.field.setBoolean(t, zScanBoolean);
                    } else {
                        fieldDeserializer.setValue(t, Boolean.valueOf(zScanBoolean));
                    }
                    if (jSONLexer.f44ch == ',') {
                        int i8 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i8;
                        jSONLexer.f44ch = i8 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i8);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.f44ch == ']') {
                        int i9 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i9;
                        jSONLexer.f44ch = i9 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i9);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls.isEnum()) {
                    char c2 = jSONLexer.f44ch;
                    if (c2 == '\"') {
                        String strScanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
                        enumValueOf = strScanSymbol == null ? null : Enum.valueOf(cls, strScanSymbol);
                    } else if (c2 >= '0' && c2 <= '9') {
                        enumValueOf = ((EnumDeserializer) ((DefaultFieldDeserializer) fieldDeserializer).getFieldValueDeserilizer(defaultJSONParser.config)).ordinalEnums[(int) jSONLexer.scanLongValue()];
                    } else {
                        throw new JSONException("illegal enum." + jSONLexer.info());
                    }
                    fieldDeserializer.setValue(t, enumValueOf);
                    if (jSONLexer.f44ch == ',') {
                        int i10 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i10;
                        jSONLexer.f44ch = i10 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i10);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.f44ch == ']') {
                        int i11 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i11;
                        jSONLexer.f44ch = i11 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i11);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else if (cls == Date.class && jSONLexer.f44ch == '1') {
                    fieldDeserializer.setValue(t, new Date(jSONLexer.scanLongValue()));
                    if (jSONLexer.f44ch == ',') {
                        int i12 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i12;
                        jSONLexer.f44ch = i12 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i12);
                        jSONLexer.token = 16;
                    } else if (jSONLexer.f44ch == ']') {
                        int i13 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i13;
                        jSONLexer.f44ch = i13 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i13);
                        jSONLexer.token = 15;
                    } else {
                        jSONLexer.nextToken();
                    }
                } else {
                    if (jSONLexer.f44ch == '[') {
                        int i14 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i14;
                        jSONLexer.f44ch = i14 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i14);
                        jSONLexer.token = 14;
                    } else if (jSONLexer.f44ch == '{') {
                        int i15 = jSONLexer.f43bp + 1;
                        jSONLexer.f43bp = i15;
                        jSONLexer.f44ch = i15 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i15);
                        jSONLexer.token = 12;
                    } else {
                        jSONLexer.nextToken();
                    }
                    fieldDeserializer.parseField(defaultJSONParser, t, fieldInfo.fieldType, null);
                    if (c == ']') {
                        if (jSONLexer.token != 15) {
                            throw new JSONException("syntax error");
                        }
                    } else if (c == ',' && jSONLexer.token != 16) {
                        throw new JSONException("syntax error");
                    }
                }
                i++;
            } catch (IllegalAccessException e) {
                throw new JSONException("set " + fieldInfo.name + C1708b.f3698J, e);
            }
        }
        if (jSONLexer.f44ch == ',') {
            int i16 = jSONLexer.f43bp + 1;
            jSONLexer.f43bp = i16;
            jSONLexer.f44ch = i16 >= jSONLexer.len ? JSONLexer.EOI : jSONLexer.text.charAt(i16);
            jSONLexer.token = 16;
        } else {
            jSONLexer.nextToken();
        }
        return t;
    }

    /* JADX WARN: Code duplicated, block: B:393:0x07de A[Catch: all -> 0x00fe, TryCatch #3 {all -> 0x00fe, blocks: (B:18:0x0045, B:20:0x004c, B:26:0x0064, B:28:0x006c, B:32:0x007b, B:41:0x009a, B:49:0x00b4, B:51:0x00be, B:56:0x00d0, B:58:0x00e9, B:59:0x00f4, B:60:0x00fd, B:66:0x010b, B:68:0x0112, B:69:0x0117, B:99:0x01c8, B:101:0x01ce, B:400:0x0812, B:402:0x081c, B:403:0x0821, B:406:0x082f, B:408:0x083b, B:410:0x0845, B:411:0x084d, B:413:0x085a, B:414:0x0863, B:416:0x086b, B:418:0x0879, B:419:0x0881, B:421:0x0887, B:423:0x089b, B:427:0x08cb, B:429:0x08d3, B:434:0x08f0, B:435:0x0912, B:425:0x08a8, B:426:0x08ca, B:412:0x0852, B:300:0x0623, B:303:0x0631, B:304:0x063c, B:390:0x07cd, B:392:0x07d5, B:393:0x07de, B:395:0x07e6, B:397:0x07ed, B:398:0x080d, B:308:0x064a, B:317:0x066c, B:322:0x067d, B:327:0x068f, B:313:0x065a, B:314:0x0663, B:331:0x069b, B:326:0x0689, B:321:0x0678, B:312:0x0656, B:334:0x06a7, B:348:0x06f6, B:358:0x0726, B:368:0x0759, B:378:0x078c, B:372:0x0765, B:374:0x076b, B:376:0x0771, B:377:0x077c, B:362:0x0732, B:364:0x0738, B:366:0x073e, B:367:0x0749, B:352:0x0702, B:354:0x0708, B:356:0x070e, B:357:0x0719, B:338:0x06b3, B:340:0x06b9, B:342:0x06bf, B:347:0x06e9, B:380:0x0797, B:345:0x06ca, B:346:0x06e8, B:381:0x07a2, B:383:0x07b0, B:385:0x07b8, B:386:0x07bd, B:388:0x07c5, B:389:0x07cc), top: B:444:0x0041, inners: #0, #1, #4 }] */
    /* JADX WARN: Code duplicated, block: B:480:0x07cd A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:484:0x0644 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:485:0x07d5 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:63:0x0101  */
    private <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2) throws Throwable {
        HashMap map;
        T t;
        FieldDeserializer fieldDeserializer;
        if (type == JSON.class || type == JSONObject.class) {
            return (T) defaultJSONParser.parse();
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token;
        if (i == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        boolean z = jSONLexer.disableCircularReferenceDetect;
        ParseContext parseContext = defaultJSONParser.contex;
        if (obj2 != null && parseContext != null) {
            parseContext = parseContext.parent;
        }
        ParseContext context = null;
        try {
            if (i == 13) {
                jSONLexer.nextToken(16);
                if (obj2 == null) {
                    obj2 = createInstance(defaultJSONParser, type);
                }
                if (0 != 0) {
                    context.object = obj2;
                }
                defaultJSONParser.setContext(parseContext);
                return (T) obj2;
            }
            if (i == 14) {
                if (this.beanInfo.supportBeanToArray || (jSONLexer.features & Feature.SupportArrayToBean.mask) != 0) {
                    T t2 = (T) deserialzeArrayMapping(defaultJSONParser, type, obj, obj2);
                    if (0 != 0) {
                        context.object = obj2;
                    }
                    defaultJSONParser.setContext(parseContext);
                    return t2;
                }
            }
            if (i != 12 && i != 16) {
                if (jSONLexer.isBlankInput()) {
                    if (0 != 0) {
                        context.object = obj2;
                    }
                    defaultJSONParser.setContext(parseContext);
                    return null;
                }
                if (i != 4 || jSONLexer.stringVal().length() != 0) {
                    StringBuffer stringBufferAppend = new StringBuffer().append("syntax error, expect {, actual ").append(jSONLexer.info());
                    if (obj instanceof String) {
                        stringBufferAppend.append(", fieldName ").append(obj);
                    }
                    throw new JSONException(stringBufferAppend.toString());
                }
                jSONLexer.nextToken();
                if (0 != 0) {
                    context.object = obj2;
                }
                defaultJSONParser.setContext(parseContext);
                return null;
            }
            if (defaultJSONParser.resolveStatus == 2) {
                defaultJSONParser.resolveStatus = 0;
            }
            String str = this.beanInfo.typeKey;
            long j = 0;
            int i2 = 0;
            int length = this.sortedFieldDeserializers.length;
            HashMap map2 = null;
            while (true) {
                String strScanSymbol = null;
                FieldDeserializer fieldDeserializerByHash = null;
                FieldInfo fieldInfo = null;
                Class<?> cls = null;
                if (j != 0) {
                    try {
                        fieldDeserializerByHash = getFieldDeserializerByHash(j);
                        if (fieldDeserializerByHash != null) {
                            fieldInfo = fieldDeserializerByHash.fieldInfo;
                            cls = fieldInfo.fieldClass;
                        }
                        j = 0;
                    } catch (Throwable th) {
                        th = th;
                        if (context != null) {
                            context.object = obj2;
                        }
                        defaultJSONParser.setContext(parseContext);
                        throw th;
                    }
                }
                if (fieldDeserializerByHash == null) {
                    if (i2 < length) {
                        fieldDeserializerByHash = this.sortedFieldDeserializers[i2];
                        fieldInfo = fieldDeserializerByHash.fieldInfo;
                        cls = fieldInfo.fieldClass;
                        i2++;
                    } else {
                        i2++;
                    }
                }
                boolean z2 = false;
                boolean z3 = false;
                Object objValueOf = null;
                int iScanFieldInt = 0;
                long jScanFieldLong = 0;
                float fScanFieldFloat = 0.0f;
                double dScanFieldDouble = 0.0d;
                if (fieldDeserializerByHash != null) {
                    long j2 = fieldInfo.nameHashCode;
                    if (cls == Integer.TYPE || cls == Integer.class) {
                        iScanFieldInt = jSONLexer.scanFieldInt(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == Long.TYPE || cls == Long.class) {
                        jScanFieldLong = jSONLexer.scanFieldLong(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == String.class) {
                        objValueOf = jSONLexer.scanFieldString(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == Date.class) {
                        objValueOf = jSONLexer.scanFieldDate(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == Boolean.TYPE || cls == Boolean.class) {
                        objValueOf = Boolean.valueOf(jSONLexer.scanFieldBoolean(j2));
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == Float.TYPE || cls == Float.class) {
                        fScanFieldFloat = jSONLexer.scanFieldFloat(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == Double.TYPE || cls == Double.class) {
                        dScanFieldDouble = jSONLexer.scanFieldDouble(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (fieldInfo.isEnum && (defaultJSONParser.config.getDeserializer(cls) instanceof EnumDeserializer)) {
                        long jScanFieldSymbol = jSONLexer.scanFieldSymbol(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                            objValueOf = fieldDeserializerByHash.getEnumByHashCode(jScanFieldSymbol);
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == int[].class) {
                        objValueOf = jSONLexer.scanFieldIntArray(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == float[].class) {
                        objValueOf = jSONLexer.scanFieldFloatArray(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == double[].class) {
                        objValueOf = jSONLexer.scanFieldDoubleArray(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == float[][].class) {
                        objValueOf = jSONLexer.scanFieldFloatArray2(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (cls == double[][].class) {
                        objValueOf = jSONLexer.scanFieldDoubleArray2(j2);
                        if (jSONLexer.matchStat > 0) {
                            z2 = true;
                            z3 = true;
                        } else if (jSONLexer.matchStat == -2) {
                            j = jSONLexer.fieldHash;
                        }
                    } else if (jSONLexer.matchField(fieldInfo.nameHashCode)) {
                        z2 = true;
                    } else {
                        continue;
                    }
                }
                if (!z2) {
                    strScanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
                    if (strScanSymbol == null) {
                        int i3 = jSONLexer.token;
                        if (i3 == 13) {
                            jSONLexer.nextToken(16);
                            map = map2;
                            break;
                        }
                        if (i3 == 16) {
                            continue;
                        }
                    }
                    if ("$ref" == strScanSymbol && parseContext != null) {
                        jSONLexer.nextTokenWithChar(':');
                        int i4 = jSONLexer.token;
                        if (i4 != 4) {
                            throw new JSONException("illegal ref, " + JSONToken.name(i4));
                        }
                        String strStringVal = jSONLexer.stringVal();
                        if ("@".equals(strStringVal)) {
                            obj2 = parseContext.object;
                        } else if ("..".equals(strStringVal)) {
                            ParseContext parseContext2 = parseContext.parent;
                            if (parseContext2.object != null) {
                                obj2 = parseContext2.object;
                            } else {
                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(parseContext2, strStringVal));
                                defaultJSONParser.resolveStatus = 1;
                            }
                        } else if ("$".equals(strStringVal)) {
                            ParseContext parseContext3 = parseContext;
                            while (parseContext3.parent != null) {
                                parseContext3 = parseContext3.parent;
                            }
                            if (parseContext3.object != null) {
                                obj2 = parseContext3.object;
                            } else {
                                defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(parseContext3, strStringVal));
                                defaultJSONParser.resolveStatus = 1;
                            }
                        } else {
                            defaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(parseContext, strStringVal));
                            defaultJSONParser.resolveStatus = 1;
                        }
                        jSONLexer.nextToken(13);
                        if (jSONLexer.token != 13) {
                            throw new JSONException("illegal ref");
                        }
                        jSONLexer.nextToken(16);
                        defaultJSONParser.setContext(parseContext, obj2, obj);
                        if (context != null) {
                            context.object = obj2;
                        }
                        defaultJSONParser.setContext(parseContext);
                        return (T) obj2;
                    }
                    if ((str != null && str.equals(strScanSymbol)) || JSON.DEFAULT_TYPE_KEY == strScanSymbol) {
                        jSONLexer.nextTokenWithChar(':');
                        if (jSONLexer.token != 4) {
                            throw new JSONException("syntax error");
                        }
                        String strStringVal2 = jSONLexer.stringVal();
                        jSONLexer.nextToken(16);
                        if (!(type instanceof Class) || !strStringVal2.equals(((Class) type).getName())) {
                            ObjectDeserializer seeAlso = getSeeAlso(defaultJSONParser.config, this.beanInfo, strStringVal2);
                            Class<?> clsCheckAutoType = null;
                            if (seeAlso == null) {
                                clsCheckAutoType = defaultJSONParser.config.checkAutoType(strStringVal2, this.clazz, jSONLexer.features);
                                Class<?> cls2 = TypeUtils.getClass(type);
                                if (cls2 != null && (clsCheckAutoType == null || !cls2.isAssignableFrom(clsCheckAutoType))) {
                                    throw new JSONException("type not match");
                                }
                                seeAlso = defaultJSONParser.config.getDeserializer(clsCheckAutoType);
                            }
                            if (seeAlso instanceof JavaBeanDeserializer) {
                                JavaBeanDeserializer javaBeanDeserializer = (JavaBeanDeserializer) seeAlso;
                                t = (T) javaBeanDeserializer.deserialze(defaultJSONParser, clsCheckAutoType, obj, null);
                                if (str != null && (fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(str)) != null) {
                                    fieldDeserializer.setValue(t, strStringVal2);
                                }
                            } else {
                                t = (T) seeAlso.deserialze(defaultJSONParser, clsCheckAutoType, obj);
                            }
                            if (context != null) {
                                context.object = obj2;
                            }
                            defaultJSONParser.setContext(parseContext);
                            return t;
                        }
                        if (jSONLexer.token == 13) {
                            jSONLexer.nextToken();
                            map = map2;
                            break;
                        }
                    }
                }
                if (obj2 == null && map2 == null) {
                    obj2 = createInstance(defaultJSONParser, type);
                    map = obj2 == null ? new HashMap(this.fieldDeserializers.length) : map2;
                    if (!z) {
                        context = defaultJSONParser.setContext(parseContext, obj2, obj);
                    }
                } else {
                    map = map2;
                }
                if (z2) {
                    if (z3) {
                        if (obj2 == null) {
                            if (cls == Integer.TYPE || cls == Integer.class) {
                                objValueOf = Integer.valueOf(iScanFieldInt);
                            } else if (cls == Long.TYPE || cls == Long.class) {
                                objValueOf = Long.valueOf(jScanFieldLong);
                            } else if (cls == Float.TYPE || cls == Float.class) {
                                objValueOf = new Float(fScanFieldFloat);
                            } else if (cls == Double.TYPE || cls == Double.class) {
                                objValueOf = new Double(dScanFieldDouble);
                            }
                            map.put(fieldInfo.name, objValueOf);
                        } else if (objValueOf == null) {
                            try {
                                if (cls == Integer.TYPE || cls == Integer.class) {
                                    if (fieldInfo.fieldAccess && cls == Integer.TYPE) {
                                        fieldDeserializerByHash.setValue(obj2, iScanFieldInt);
                                    } else {
                                        fieldDeserializerByHash.setValue(obj2, Integer.valueOf(iScanFieldInt));
                                    }
                                } else if (cls == Long.TYPE || cls == Long.class) {
                                    if (fieldInfo.fieldAccess && cls == Long.TYPE) {
                                        fieldDeserializerByHash.setValue(obj2, jScanFieldLong);
                                    } else {
                                        fieldDeserializerByHash.setValue(obj2, Long.valueOf(jScanFieldLong));
                                    }
                                } else if (cls == Float.TYPE || cls == Float.class) {
                                    if (fieldInfo.fieldAccess && cls == Float.TYPE) {
                                        fieldDeserializerByHash.setValue(obj2, fScanFieldFloat);
                                    } else {
                                        fieldDeserializerByHash.setValue(obj2, new Float(fScanFieldFloat));
                                    }
                                } else if (cls != Double.TYPE && cls != Double.class) {
                                    fieldDeserializerByHash.setValue(obj2, objValueOf);
                                } else if (fieldInfo.fieldAccess && cls == Double.TYPE) {
                                    fieldDeserializerByHash.setValue(obj2, dScanFieldDouble);
                                } else {
                                    fieldDeserializerByHash.setValue(obj2, new Double(dScanFieldDouble));
                                }
                            } catch (IllegalAccessException e) {
                                throw new JSONException("set property error, " + fieldInfo.name, e);
                            }
                        } else {
                            fieldDeserializerByHash.setValue(obj2, objValueOf);
                        }
                        if (jSONLexer.matchStat != 4) {
                            break;
                        }
                        break;
                    }
                    fieldDeserializerByHash.parseField(defaultJSONParser, obj2, type, map);
                    if (jSONLexer.token != 16) {
                        map2 = map;
                    } else {
                        if (jSONLexer.token == 13) {
                            jSONLexer.nextToken(16);
                            break;
                        }
                        if (jSONLexer.token != 18 || jSONLexer.token == 1) {
                            throw new JSONException("syntax error, unexpect token " + JSONToken.name(jSONLexer.token));
                        }
                        map2 = map;
                    }
                } else if (parseField(defaultJSONParser, strScanSymbol, obj2, type, map)) {
                    if (jSONLexer.token == 17) {
                        throw new JSONException("syntax error, unexpect token ':'");
                    }
                    if (jSONLexer.token != 16) {
                        if (jSONLexer.token == 13) {
                            jSONLexer.nextToken(16);
                            break;
                        }
                        if (jSONLexer.token != 18) {
                        }
                        throw new JSONException("syntax error, unexpect token " + JSONToken.name(jSONLexer.token));
                    }
                    map2 = map;
                } else {
                    if (jSONLexer.token == 13) {
                        jSONLexer.nextToken();
                        break;
                    }
                    map2 = map;
                }
            }
            if (obj2 == null) {
                if (map == null) {
                    T t3 = (T) createInstance(defaultJSONParser, type);
                    if (context == null) {
                        context = defaultJSONParser.setContext(parseContext, t3, obj);
                    }
                    if (context != null) {
                        context.object = t3;
                    }
                    defaultJSONParser.setContext(parseContext);
                    return t3;
                }
                String[] strArr = this.beanInfo.creatorConstructorParameters;
                int length2 = strArr != null ? strArr.length : this.fieldDeserializers.length;
                Object[] objArr = new Object[length2];
                for (int i5 = 0; i5 < length2; i5++) {
                    FieldInfo fieldInfo2 = this.fieldDeserializers[i5].fieldInfo;
                    Object objRemove = strArr != null ? map.remove(fieldInfo2.name) : map.get(fieldInfo2.name);
                    if (objRemove == null) {
                        objRemove = TypeUtils.defaultValue(fieldInfo2.fieldClass);
                    }
                    objArr[i5] = objRemove;
                }
                if (this.beanInfo.creatorConstructor != null) {
                    try {
                        obj2 = this.beanInfo.creatorConstructor.newInstance(objArr);
                        if (strArr != null) {
                            for (Map.Entry<String, Object> entry : map.entrySet()) {
                                FieldDeserializer fieldDeserializer2 = getFieldDeserializer(entry.getKey());
                                if (fieldDeserializer2 != null) {
                                    fieldDeserializer2.setValue(obj2, entry.getValue());
                                }
                            }
                        }
                    } catch (Exception e2) {
                        throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e2);
                    }
                } else if (this.beanInfo.factoryMethod != null) {
                    try {
                        obj2 = this.beanInfo.factoryMethod.invoke(null, objArr);
                    } catch (Exception e3) {
                        throw new JSONException("create factory method error, " + this.beanInfo.factoryMethod.toString(), e3);
                    }
                }
            }
            if (context != null) {
                context.object = obj2;
            }
            defaultJSONParser.setContext(parseContext);
            return (T) obj2;
        } catch (Throwable th2) {
            th = th2;
            if (context != null) {
                context.object = obj2;
            }
            defaultJSONParser.setContext(parseContext);
            throw th;
        }
    }

    protected FieldDeserializer getFieldDeserializerByHash(long fieldHash) {
        for (int i = 0; i < this.sortedFieldDeserializers.length; i++) {
            FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
            if (fieldDeserializer.fieldInfo.nameHashCode == fieldHash) {
                return fieldDeserializer;
            }
        }
        return null;
    }

    protected FieldDeserializer getFieldDeserializer(String key) {
        if (key == null) {
            return null;
        }
        if (this.beanInfo.ordered) {
            for (int i = 0; i < this.sortedFieldDeserializers.length; i++) {
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[i];
                if (fieldDeserializer.fieldInfo.name.equalsIgnoreCase(key)) {
                    return fieldDeserializer;
                }
            }
            return null;
        }
        int low = 0;
        int high = this.sortedFieldDeserializers.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            String fieldName = this.sortedFieldDeserializers[mid].fieldInfo.name;
            int cmp = fieldName.compareTo(key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return this.sortedFieldDeserializers[mid];
            }
        }
        if (this.alterNameFieldDeserializers != null) {
            return this.alterNameFieldDeserializers.get(key);
        }
        if (this.smartMatchHashArray == null) {
            long[] hashArray = new long[this.sortedFieldDeserializers.length];
            for (int i2 = 0; i2 < this.sortedFieldDeserializers.length; i2++) {
                hashArray[i2] = TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i2].fieldInfo.name);
            }
            Arrays.sort(hashArray);
            this.smartMatchHashArray = hashArray;
        }
        long smartKeyHash = TypeUtils.fnv_64_lower(key);
        int pos = Arrays.binarySearch(this.smartMatchHashArray, smartKeyHash);
        if (pos >= 0) {
            if (this.smartMatchHashArrayMapping == null) {
                int[] mapping = new int[this.smartMatchHashArray.length];
                Arrays.fill(mapping, -1);
                for (int i3 = 0; i3 < this.sortedFieldDeserializers.length; i3++) {
                    int p = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i3].fieldInfo.name));
                    if (p >= 0) {
                        mapping[p] = i3;
                    }
                }
                this.smartMatchHashArrayMapping = mapping;
            }
            int deserIndex = this.smartMatchHashArrayMapping[pos];
            if (deserIndex != -1) {
                return this.sortedFieldDeserializers[deserIndex];
            }
        }
        return smartMatch(key);
    }

    private boolean parseField(DefaultJSONParser parser, String key, Object object, Type objectType, Map<String, Object> fieldValues) {
        JSONLexer lexer = parser.lexer;
        FieldDeserializer fieldDeserializer = getFieldDeserializer(key);
        if (fieldDeserializer == null) {
            fieldDeserializer = smartMatch(key);
        }
        int mask = Feature.SupportNonPublicField.mask;
        if (fieldDeserializer == null && ((parser.lexer.features & mask) != 0 || (this.beanInfo.parserFeatures & mask) != 0)) {
            if (this.extraFieldDeserializers == null) {
                ConcurrentHashMap extraFieldDeserializers = new ConcurrentHashMap(1, 0.75f, 1);
                for (Class<?> superclass = this.clazz; superclass != null && superclass != Object.class; superclass = superclass.getSuperclass()) {
                    Field[] fields = superclass.getDeclaredFields();
                    for (Field field : fields) {
                        String fieldName = field.getName();
                        if (getFieldDeserializer(fieldName) == null) {
                            int fieldModifiers = field.getModifiers();
                            if ((fieldModifiers & 16) == 0 && (fieldModifiers & 8) == 0) {
                                extraFieldDeserializers.put(fieldName, field);
                            }
                        }
                    }
                }
                this.extraFieldDeserializers = extraFieldDeserializers;
            }
            Object deserOrField = this.extraFieldDeserializers.get(key);
            if (deserOrField != null) {
                if (deserOrField instanceof FieldDeserializer) {
                    fieldDeserializer = (FieldDeserializer) deserOrField;
                } else {
                    Field field2 = (Field) deserOrField;
                    field2.setAccessible(true);
                    FieldInfo fieldInfo = new FieldInfo(key, field2.getDeclaringClass(), field2.getType(), field2.getGenericType(), field2, 0, 0);
                    fieldDeserializer = new DefaultFieldDeserializer(parser.config, this.clazz, fieldInfo);
                    this.extraFieldDeserializers.put(key, fieldDeserializer);
                }
            }
        }
        if (fieldDeserializer == null) {
            parseExtra(parser, object, key);
            return false;
        }
        lexer.nextTokenWithChar(':');
        fieldDeserializer.parseField(parser, object, objectType, fieldValues);
        return true;
    }

    private FieldDeserializer smartMatch(String key) {
        long smartKeyHash = TypeUtils.fnv_64_lower(key);
        if (this.smartMatchHashArray == null) {
            long[] hashArray = new long[this.sortedFieldDeserializers.length];
            for (int i = 0; i < this.sortedFieldDeserializers.length; i++) {
                hashArray[i] = TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i].fieldInfo.name);
            }
            Arrays.sort(hashArray);
            this.smartMatchHashArray = hashArray;
        }
        int pos = Arrays.binarySearch(this.smartMatchHashArray, smartKeyHash);
        boolean is = false;
        if (pos < 0 && (is = key.startsWith(C1799v.f4286Y))) {
            long smartKeyHash2 = TypeUtils.fnv_64_lower(key.substring(2));
            pos = Arrays.binarySearch(this.smartMatchHashArray, smartKeyHash2);
        }
        if (pos >= 0) {
            if (this.smartMatchHashArrayMapping == null) {
                int[] mapping = new int[this.smartMatchHashArray.length];
                Arrays.fill(mapping, -1);
                for (int i2 = 0; i2 < this.sortedFieldDeserializers.length; i2++) {
                    int p = Arrays.binarySearch(this.smartMatchHashArray, TypeUtils.fnv_64_lower(this.sortedFieldDeserializers[i2].fieldInfo.name));
                    if (p >= 0) {
                        mapping[p] = i2;
                    }
                }
                this.smartMatchHashArrayMapping = mapping;
            }
            int deserIndex = this.smartMatchHashArrayMapping[pos];
            if (deserIndex != -1) {
                FieldDeserializer fieldDeserializer = this.sortedFieldDeserializers[deserIndex];
                Class<?> cls = fieldDeserializer.fieldInfo.fieldClass;
                if (is && cls != Boolean.TYPE && cls != Boolean.class) {
                    return null;
                }
                return fieldDeserializer;
            }
        }
        return null;
    }

    void parseExtra(DefaultJSONParser parser, Object object, String key) {
        Object value;
        JSONLexer lexer = parser.lexer;
        if ((parser.lexer.features & Feature.IgnoreNotMatch.mask) == 0) {
            throw new JSONException("setter not found, class " + this.clazz.getName() + ", property " + key);
        }
        lexer.nextTokenWithChar(':');
        Type type = null;
        List<ExtraTypeProvider> extraTypeProviders = parser.extraTypeProviders;
        if (extraTypeProviders != null) {
            for (ExtraTypeProvider extraProvider : extraTypeProviders) {
                type = extraProvider.getExtraType(object, key);
            }
        }
        if (type == null) {
            value = parser.parse();
        } else {
            value = parser.parseObject(type);
        }
        if (object instanceof ExtraProcessable) {
            ExtraProcessable extraProcessable = (ExtraProcessable) object;
            extraProcessable.processExtra(key, value);
            return;
        }
        List<ExtraProcessor> extraProcessors = parser.extraProcessors;
        if (extraProcessors != null) {
            for (ExtraProcessor process : extraProcessors) {
                process.processExtra(object, key, value);
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:86:0x021c  */
    /* JADX WARN: Code duplicated, block: B:94:0x024a  */
    /* JADX WARN: Code duplicated, block: B:96:0x0252  */
    /* JADX WARN: Code duplicated, block: B:97:0x025f  */
    public Object createInstance(Map<String, Object> map, ParserConfig config) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        String format;
        Object objCast;
        float floatValue;
        double doubleValue;
        if (this.beanInfo.creatorConstructor == null) {
            Object object = createInstance((DefaultJSONParser) null, this.clazz);
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                FieldDeserializer fieldDeser = getFieldDeserializer(entry.getKey());
                if (fieldDeser != null) {
                    Object value = entry.getValue();
                    if (value == null) {
                        Class<?> fieldClass = fieldDeser.fieldInfo.fieldClass;
                        if (fieldClass == Integer.TYPE) {
                            value = 0;
                        } else if (fieldClass == Long.TYPE) {
                            value = 0L;
                        } else if (fieldClass == Short.TYPE) {
                            value = (short) 0;
                        } else if (fieldClass == Byte.TYPE) {
                            value = (byte) 0;
                        } else if (fieldClass == Float.TYPE) {
                            value = Float.valueOf(0.0f);
                        } else if (fieldClass == Double.TYPE) {
                            value = Double.valueOf(0.0d);
                        } else if (fieldClass == Character.TYPE) {
                            value = '0';
                        } else if (fieldClass == Boolean.TYPE) {
                            value = false;
                        }
                    }
                    Method method = fieldDeser.fieldInfo.method;
                    if (method != null) {
                        method.invoke(object, TypeUtils.cast(value, method.getGenericParameterTypes()[0], config));
                    } else {
                        Field field = fieldDeser.fieldInfo.field;
                        Type paramType = fieldDeser.fieldInfo.fieldType;
                        if (paramType == Boolean.TYPE) {
                            if (value == Boolean.FALSE) {
                                field.setBoolean(object, false);
                            } else if (value == Boolean.TRUE) {
                                field.setBoolean(object, true);
                            } else {
                                format = fieldDeser.fieldInfo.format;
                                if (format == null && paramType == Date.class && (value instanceof String)) {
                                    try {
                                        objCast = new SimpleDateFormat(format).parse((String) value);
                                    } catch (ParseException e) {
                                        objCast = null;
                                    }
                                } else if (paramType instanceof ParameterizedType) {
                                    objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                } else {
                                    objCast = TypeUtils.cast(value, paramType, config);
                                }
                                field.set(object, objCast);
                            }
                        } else if (paramType == Integer.TYPE) {
                            if (value instanceof Number) {
                                field.setInt(object, ((Number) value).intValue());
                            } else {
                                format = fieldDeser.fieldInfo.format;
                                if (format == null) {
                                    if (paramType instanceof ParameterizedType) {
                                        objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                    } else {
                                        objCast = TypeUtils.cast(value, paramType, config);
                                    }
                                } else if (paramType instanceof ParameterizedType) {
                                    objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                } else {
                                    objCast = TypeUtils.cast(value, paramType, config);
                                }
                                field.set(object, objCast);
                            }
                        } else if (paramType == Long.TYPE) {
                            if (value instanceof Number) {
                                field.setLong(object, ((Number) value).longValue());
                            } else {
                                format = fieldDeser.fieldInfo.format;
                                if (format == null) {
                                    if (paramType instanceof ParameterizedType) {
                                        objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                    } else {
                                        objCast = TypeUtils.cast(value, paramType, config);
                                    }
                                } else if (paramType instanceof ParameterizedType) {
                                    objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                } else {
                                    objCast = TypeUtils.cast(value, paramType, config);
                                }
                                field.set(object, objCast);
                            }
                        } else if (paramType == Float.TYPE) {
                            if (value instanceof Number) {
                                field.setFloat(object, ((Number) value).floatValue());
                            } else if (value instanceof String) {
                                String strVal = (String) value;
                                if (strVal.length() <= 10) {
                                    floatValue = TypeUtils.parseFloat(strVal);
                                } else {
                                    floatValue = Float.parseFloat(strVal);
                                }
                                field.setFloat(object, floatValue);
                            } else {
                                format = fieldDeser.fieldInfo.format;
                                if (format == null) {
                                    if (paramType instanceof ParameterizedType) {
                                        objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                    } else {
                                        objCast = TypeUtils.cast(value, paramType, config);
                                    }
                                } else if (paramType instanceof ParameterizedType) {
                                    objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                } else {
                                    objCast = TypeUtils.cast(value, paramType, config);
                                }
                                field.set(object, objCast);
                            }
                        } else if (paramType == Double.TYPE) {
                            if (value instanceof Number) {
                                field.setDouble(object, ((Number) value).doubleValue());
                            } else if (value instanceof String) {
                                String strVal2 = (String) value;
                                if (strVal2.length() <= 10) {
                                    doubleValue = TypeUtils.parseDouble(strVal2);
                                } else {
                                    doubleValue = Double.parseDouble(strVal2);
                                }
                                field.setDouble(object, doubleValue);
                            } else {
                                format = fieldDeser.fieldInfo.format;
                                if (format == null) {
                                    if (paramType instanceof ParameterizedType) {
                                        objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                    } else {
                                        objCast = TypeUtils.cast(value, paramType, config);
                                    }
                                } else if (paramType instanceof ParameterizedType) {
                                    objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                } else {
                                    objCast = TypeUtils.cast(value, paramType, config);
                                }
                                field.set(object, objCast);
                            }
                        } else if (value != null && paramType == value.getClass()) {
                            field.set(object, value);
                        } else {
                            format = fieldDeser.fieldInfo.format;
                            if (format == null) {
                                if (paramType instanceof ParameterizedType) {
                                    objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                                } else {
                                    objCast = TypeUtils.cast(value, paramType, config);
                                }
                            } else if (paramType instanceof ParameterizedType) {
                                objCast = TypeUtils.cast(value, (ParameterizedType) paramType, config);
                            } else {
                                objCast = TypeUtils.cast(value, paramType, config);
                            }
                            field.set(object, objCast);
                        }
                    }
                }
            }
            return object;
        }
        FieldInfo[] fieldInfoList = this.beanInfo.fields;
        int size = fieldInfoList.length;
        Object[] params = new Object[size];
        for (int i = 0; i < size; i++) {
            FieldInfo fieldInfo = fieldInfoList[i];
            Object param = map.get(fieldInfo.name);
            if (param == null) {
                param = TypeUtils.defaultValue(fieldInfo.fieldClass);
            }
            params[i] = param;
        }
        if (this.beanInfo.creatorConstructor == null) {
            return null;
        }
        try {
            return this.beanInfo.creatorConstructor.newInstance(params);
        } catch (Exception e2) {
            throw new JSONException("create instance error, " + this.beanInfo.creatorConstructor.toGenericString(), e2);
        }
    }

    protected JavaBeanDeserializer getSeeAlso(ParserConfig config, JavaBeanInfo beanInfo, String typeName) {
        if (beanInfo.jsonType == null) {
            return null;
        }
        for (Class<?> seeAlsoClass : beanInfo.jsonType.seeAlso()) {
            ObjectDeserializer seeAlsoDeser = config.getDeserializer(seeAlsoClass);
            if (seeAlsoDeser instanceof JavaBeanDeserializer) {
                JavaBeanDeserializer seeAlsoJavaBeanDeser = (JavaBeanDeserializer) seeAlsoDeser;
                JavaBeanInfo subBeanInfo = seeAlsoJavaBeanDeser.beanInfo;
                if (!subBeanInfo.typeName.equals(typeName)) {
                    JavaBeanDeserializer subSeeAlso = getSeeAlso(config, subBeanInfo, typeName);
                    if (subSeeAlso != null) {
                        return subSeeAlso;
                    }
                } else {
                    return seeAlsoJavaBeanDeser;
                }
            }
        }
        return null;
    }
}

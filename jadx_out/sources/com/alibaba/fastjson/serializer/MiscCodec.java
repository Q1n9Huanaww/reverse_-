package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONAware;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONStreamAware;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import com.iflytek.voiceads.config.AdKeys;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes.dex */
public final class MiscCodec implements ObjectSerializer, ObjectDeserializer {
    public static final MiscCodec instance = new MiscCodec();

    private MiscCodec() {
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws Throwable {
        SerializeWriter out = serializer.out;
        if (object == null) {
            if (fieldType == Character.TYPE || fieldType == Character.class) {
                serializer.write("");
                return;
            }
            if ((out.features & SerializerFeature.WriteNullListAsEmpty.mask) != 0) {
                Class<?> fieldClass = TypeUtils.getClass(fieldType);
                if (Enumeration.class.isAssignableFrom(fieldClass)) {
                    out.write("[]");
                    return;
                }
            }
            out.writeNull();
            return;
        }
        if (object instanceof Pattern) {
            Pattern p = (Pattern) object;
            serializer.write(p.pattern());
            return;
        }
        if (object instanceof TimeZone) {
            TimeZone timeZone = (TimeZone) object;
            serializer.write(timeZone.getID());
            return;
        }
        if (object instanceof Currency) {
            Currency currency = (Currency) object;
            serializer.write(currency.getCurrencyCode());
            return;
        }
        if (object instanceof Class) {
            Class<?> clazz = (Class) object;
            serializer.write(clazz.getName());
            return;
        }
        if (object instanceof Character) {
            Character value = (Character) object;
            char c = value.charValue();
            if (c == 0) {
                serializer.write("\u0000");
                return;
            } else {
                serializer.write(value.toString());
                return;
            }
        }
        if (object instanceof SimpleDateFormat) {
            String pattern = ((SimpleDateFormat) object).toPattern();
            if ((out.features & SerializerFeature.WriteClassName.mask) != 0 && object.getClass() != fieldType) {
                out.write(123);
                out.writeFieldName(JSON.DEFAULT_TYPE_KEY, false);
                serializer.write(object.getClass().getName());
                out.write(44);
                out.writeFieldName("val", false);
                out.writeString(pattern);
                out.write(125);
                return;
            }
            out.writeString(pattern);
            return;
        }
        if (object instanceof JSONStreamAware) {
            JSONStreamAware aware = (JSONStreamAware) object;
            aware.writeJSONString(serializer.out);
            return;
        }
        if (object instanceof JSONAware) {
            JSONAware aware2 = (JSONAware) object;
            out.write(aware2.toJSONString());
            return;
        }
        if (object instanceof JSONSerializable) {
            JSONSerializable jsonSerializable = (JSONSerializable) object;
            jsonSerializable.write(serializer, fieldName, fieldType);
            return;
        }
        if (object instanceof Enumeration) {
            Type elementType = null;
            if ((out.features & SerializerFeature.WriteClassName.mask) != 0 && (fieldType instanceof ParameterizedType)) {
                ParameterizedType param = (ParameterizedType) fieldType;
                elementType = param.getActualTypeArguments()[0];
            }
            Enumeration<?> e = (Enumeration) object;
            SerialContext context = serializer.context;
            serializer.setContext(context, object, fieldName, 0);
            try {
                out.write(91);
                int i = 0;
                while (e.hasMoreElements()) {
                    try {
                        Object item = e.nextElement();
                        i++;
                        if (i != 0) {
                            out.write(44);
                        }
                        if (item == null) {
                            out.writeNull();
                        } else {
                            Class<?> clazz2 = item.getClass();
                            ObjectSerializer itemSerializer = serializer.config.get(clazz2);
                            itemSerializer.write(serializer, item, Integer.valueOf(i - 1), elementType);
                        }
                    } catch (Throwable th) {
                        th = th;
                        serializer.context = context;
                        throw th;
                    }
                }
                out.write(93);
                serializer.context = context;
            } catch (Throwable th2) {
                th = th2;
            }
        } else {
            serializer.write(object.toString());
        }
    }

    /* JADX WARN: Type inference failed for: r5v7, types: [T, java.text.SimpleDateFormat] */
    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Object obj2;
        if (type == StackTraceElement.class) {
            return (T) parseStackTraceElement(defaultJSONParser);
        }
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
            defaultJSONParser.accept(16);
            if (jSONLexer.token() == 4) {
                if (!"val".equals(jSONLexer.stringVal())) {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                obj2 = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            obj2 = defaultJSONParser.parse();
        }
        if (obj2 == null) {
            return null;
        }
        if (obj2 instanceof String) {
            String str = (String) obj2;
            if (str.length() == 0) {
                return null;
            }
            if (type == UUID.class) {
                return (T) UUID.fromString(str);
            }
            if (type == Class.class) {
                return (T) TypeUtils.loadClass(str, defaultJSONParser.config.defaultClassLoader, false);
            }
            if (type == Locale.class) {
                String[] strArrSplit = str.split("_");
                if (strArrSplit.length == 1) {
                    return (T) new Locale(strArrSplit[0]);
                }
                if (strArrSplit.length == 2) {
                    return (T) new Locale(strArrSplit[0], strArrSplit[1]);
                }
                return (T) new Locale(strArrSplit[0], strArrSplit[1], strArrSplit[2]);
            }
            if (type == URI.class) {
                return (T) URI.create(str);
            }
            if (type == URL.class) {
                try {
                    return (T) new URL(str);
                } catch (MalformedURLException e) {
                    throw new JSONException("create url error", e);
                }
            }
            if (type == Pattern.class) {
                return (T) Pattern.compile(str);
            }
            if (type == Charset.class) {
                return (T) Charset.forName(str);
            }
            if (type == Currency.class) {
                return (T) Currency.getInstance(str);
            }
            if (type == SimpleDateFormat.class) {
                ?? r5 = (T) new SimpleDateFormat(str, defaultJSONParser.lexer.locale);
                r5.setTimeZone(defaultJSONParser.lexer.timeZone);
                return r5;
            }
            if (type == Character.TYPE || type == Character.class) {
                return (T) TypeUtils.castToChar(str);
            }
            if ((type instanceof Class) && "android.net.Uri".equals(((Class) type).getName())) {
                try {
                    return (T) Class.forName("android.net.Uri").getMethod("parse", String.class).invoke(null, str);
                } catch (Exception e2) {
                    throw new JSONException("parse android.net.Uri error.", e2);
                }
            }
            return (T) TimeZone.getTimeZone(str);
        }
        if (obj2 instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj2;
            if (type == Currency.class) {
                String string = jSONObject.getString(AdKeys.CURRENCY);
                if (string != null) {
                    return (T) Currency.getInstance(string);
                }
                String string2 = jSONObject.getString("currencyCode");
                if (string2 != null) {
                    return (T) Currency.getInstance(string2);
                }
            }
            if (type == Map.Entry.class) {
                return (T) jSONObject.entrySet().iterator().next();
            }
        }
        throw new JSONException("except string value");
    }

    protected <T> T parseStackTraceElement(DefaultJSONParser defaultJSONParser) {
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token() == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (jSONLexer.token() != 12 && jSONLexer.token() != 16) {
            throw new JSONException("syntax error: " + JSONToken.name(jSONLexer.token()));
        }
        String strStringVal = null;
        String strStringVal2 = null;
        String strStringVal3 = null;
        int iIntValue = 0;
        while (true) {
            String strScanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
            if (strScanSymbol == null) {
                if (jSONLexer.token() == 13) {
                    jSONLexer.nextToken(16);
                    break;
                }
                if (jSONLexer.token() == 16) {
                    continue;
                }
            }
            jSONLexer.nextTokenWithChar(':');
            if ("className".equals(strScanSymbol)) {
                if (jSONLexer.token() == 8) {
                    strStringVal = null;
                } else if (jSONLexer.token() == 4) {
                    strStringVal = jSONLexer.stringVal();
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("methodName".equals(strScanSymbol)) {
                if (jSONLexer.token() == 8) {
                    strStringVal2 = null;
                } else if (jSONLexer.token() == 4) {
                    strStringVal2 = jSONLexer.stringVal();
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("fileName".equals(strScanSymbol)) {
                if (jSONLexer.token() == 8) {
                    strStringVal3 = null;
                } else if (jSONLexer.token() == 4) {
                    strStringVal3 = jSONLexer.stringVal();
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("lineNumber".equals(strScanSymbol)) {
                if (jSONLexer.token() == 8) {
                    iIntValue = 0;
                } else if (jSONLexer.token() == 2) {
                    iIntValue = jSONLexer.intValue();
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("nativeMethod".equals(strScanSymbol)) {
                if (jSONLexer.token() == 8 || jSONLexer.token() == 6 || jSONLexer.token() == 7) {
                    jSONLexer.nextToken(16);
                } else {
                    throw new JSONException("syntax error");
                }
            } else if (strScanSymbol == JSON.DEFAULT_TYPE_KEY) {
                if (jSONLexer.token() == 4) {
                    String strStringVal4 = jSONLexer.stringVal();
                    if (!strStringVal4.equals("java.lang.StackTraceElement")) {
                        throw new JSONException("syntax error : " + strStringVal4);
                    }
                } else if (jSONLexer.token() != 8) {
                    throw new JSONException("syntax error");
                }
            } else {
                throw new JSONException("syntax error : " + strScanSymbol);
            }
            if (jSONLexer.token() == 13) {
                jSONLexer.nextToken(16);
                break;
            }
        }
        return (T) new StackTraceElement(strStringVal, strStringVal2, strStringVal3, iIntValue);
    }
}

package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public final class IntegerCodec implements ObjectSerializer, ObjectDeserializer {
    public static IntegerCodec instance = new IntegerCodec();

    private IntegerCodec() {
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        SerializeWriter out = serializer.out;
        Number value = (Number) object;
        if (value == null) {
            if ((out.features & SerializerFeature.WriteNullNumberAsZero.mask) != 0) {
                out.write(48);
                return;
            } else {
                out.writeNull();
                return;
            }
        }
        if (object instanceof Long) {
            out.writeLong(value.longValue());
        } else {
            out.writeInt(value.intValue());
        }
        if ((out.features & SerializerFeature.WriteClassName.mask) != 0) {
            Class<?> clazz = value.getClass();
            if (clazz == Byte.class) {
                out.write(66);
                return;
            }
            if (clazz == Short.class) {
                out.write(83);
                return;
            }
            if (clazz == Long.class) {
                long longValue = value.longValue();
                if (longValue <= 2147483647L && longValue >= -2147483648L && fieldType != Long.class) {
                    out.write(76);
                }
            }
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        T t;
        T t2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        if (i == 8) {
            jSONLexer.nextToken(16);
            return null;
        }
        if (i == 2) {
            if (type == Long.TYPE || type == Long.class) {
                t2 = (T) Long.valueOf(jSONLexer.longValue());
            } else {
                try {
                    t2 = (T) Integer.valueOf(jSONLexer.intValue());
                } catch (NumberFormatException e) {
                    throw new JSONException("int value overflow, field : " + obj, e);
                }
            }
            jSONLexer.nextToken(16);
            return t2;
        }
        if (i == 3) {
            BigDecimal bigDecimalDecimalValue = jSONLexer.decimalValue();
            jSONLexer.nextToken(16);
            if (type == Long.TYPE || type == Long.class) {
                return (T) Long.valueOf(bigDecimalDecimalValue.longValueExact());
            }
            return (T) Integer.valueOf(bigDecimalDecimalValue.intValueExact());
        }
        Object obj2 = defaultJSONParser.parse();
        try {
            if (type == Long.TYPE || type == Long.class) {
                t = (T) TypeUtils.castToLong(obj2);
            } else {
                t = (T) TypeUtils.castToInt(obj2);
            }
            return t;
        } catch (Exception e2) {
            throw new JSONException("cast error, field : " + obj + ", value " + obj2, e2);
        }
    }
}

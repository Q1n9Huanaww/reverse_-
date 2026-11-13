package com.alibaba.fastjson.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import java.io.IOException;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/* JADX INFO: loaded from: classes.dex */
public final class DateCodec implements ObjectSerializer, ObjectDeserializer {
    public static final DateCodec instance = new DateCodec();

    private DateCodec() {
    }

    @Override // com.alibaba.fastjson.serializer.ObjectSerializer
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType) throws IOException {
        Date date;
        char[] buf;
        SerializeWriter out = serializer.out;
        if (object == null) {
            out.writeNull();
            return;
        }
        if ((out.features & SerializerFeature.WriteClassName.mask) != 0 && object.getClass() != fieldType) {
            if (object.getClass() == Date.class) {
                out.write("new Date(");
                out.writeLong(((Date) object).getTime());
                out.write(41);
                return;
            }
            out.write(123);
            out.writeFieldName(JSON.DEFAULT_TYPE_KEY, false);
            serializer.write(object.getClass().getName());
            out.write(44);
            out.writeFieldName("val", false);
            out.writeLong(((Date) object).getTime());
            out.write(125);
            return;
        }
        if (object instanceof Calendar) {
            date = ((Calendar) object).getTime();
        } else {
            date = (Date) object;
        }
        if ((out.features & SerializerFeature.WriteDateUseDateFormat.mask) != 0) {
            DateFormat format = serializer.getDateFormat();
            if (format == null) {
                format = new SimpleDateFormat(JSON.DEFFAULT_DATE_FORMAT, serializer.locale);
                format.setTimeZone(serializer.timeZone);
            }
            String text = format.format(date);
            out.writeString(text);
            return;
        }
        long time = date.getTime();
        if ((out.features & SerializerFeature.UseISO8601DateFormat.mask) != 0) {
            if ((out.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                out.write(39);
            } else {
                out.write(34);
            }
            Calendar calendar = Calendar.getInstance(serializer.timeZone, serializer.locale);
            calendar.setTimeInMillis(time);
            int year = calendar.get(1);
            int month = calendar.get(2) + 1;
            int day = calendar.get(5);
            int hour = calendar.get(11);
            int minute = calendar.get(12);
            int second = calendar.get(13);
            int millis = calendar.get(14);
            if (millis != 0) {
                buf = "0000-00-00T00:00:00.000".toCharArray();
                SerializeWriter.getChars(millis, 23, buf);
                SerializeWriter.getChars(second, 19, buf);
                SerializeWriter.getChars(minute, 16, buf);
                SerializeWriter.getChars(hour, 13, buf);
                SerializeWriter.getChars(day, 10, buf);
                SerializeWriter.getChars(month, 7, buf);
                SerializeWriter.getChars(year, 4, buf);
            } else if (second == 0 && minute == 0 && hour == 0) {
                buf = "0000-00-00".toCharArray();
                SerializeWriter.getChars(day, 10, buf);
                SerializeWriter.getChars(month, 7, buf);
                SerializeWriter.getChars(year, 4, buf);
            } else {
                buf = "0000-00-00T00:00:00".toCharArray();
                SerializeWriter.getChars(second, 19, buf);
                SerializeWriter.getChars(minute, 16, buf);
                SerializeWriter.getChars(hour, 13, buf);
                SerializeWriter.getChars(day, 10, buf);
                SerializeWriter.getChars(month, 7, buf);
                SerializeWriter.getChars(year, 4, buf);
            }
            out.write(buf);
            if ((out.features & SerializerFeature.UseSingleQuotes.mask) != 0) {
                out.write(39);
                return;
            } else {
                out.write(34);
                return;
            }
        }
        out.writeLong(time);
    }

    @Override // com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        return (T) deserialze(defaultJSONParser, type, obj, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0, types: [T, java.util.Calendar] */
    /* JADX WARN: Type inference failed for: r8v2, types: [T, java.util.Calendar] */
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj, String str) {
        Object obj2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        if (i == 2) {
            Object objValueOf = Long.valueOf(jSONLexer.longValue());
            jSONLexer.nextToken(16);
            obj2 = objValueOf;
        } else if (i == 4) {
            String strStringVal = jSONLexer.stringVal();
            Object time = strStringVal;
            jSONLexer.nextToken(16);
            if ((jSONLexer.features & Feature.AllowISO8601DateFormat.mask) != 0) {
                JSONLexer jSONLexer2 = new JSONLexer(strStringVal);
                if (jSONLexer2.scanISO8601DateIfMatch(true)) {
                    ?? r8 = (T) jSONLexer2.calendar;
                    if (type == Calendar.class) {
                        jSONLexer2.close();
                        return r8;
                    }
                    time = r8.getTime();
                }
                jSONLexer2.close();
            }
            obj2 = time;
        } else if (i == 8) {
            jSONLexer.nextToken();
            obj2 = null;
        } else if (i == 12) {
            jSONLexer.nextToken();
            if (jSONLexer.token() == 4) {
                if (JSON.DEFAULT_TYPE_KEY.equals(jSONLexer.stringVal())) {
                    jSONLexer.nextToken();
                    defaultJSONParser.accept(17);
                    Type typeCheckAutoType = defaultJSONParser.config.checkAutoType(jSONLexer.stringVal(), null, jSONLexer.features);
                    if (typeCheckAutoType != null) {
                        type = typeCheckAutoType;
                    }
                    defaultJSONParser.accept(4);
                    defaultJSONParser.accept(16);
                }
                jSONLexer.nextTokenWithChar(':');
                int i2 = jSONLexer.token();
                if (i2 == 2) {
                    long jLongValue = jSONLexer.longValue();
                    jSONLexer.nextToken();
                    Object objValueOf2 = Long.valueOf(jLongValue);
                    defaultJSONParser.accept(13);
                    obj2 = objValueOf2;
                } else {
                    throw new JSONException("syntax error : " + JSONToken.name(i2));
                }
            } else {
                throw new JSONException("syntax error");
            }
        } else if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
            defaultJSONParser.accept(16);
            if (jSONLexer.token() == 4) {
                if (!"val".equals(jSONLexer.stringVal())) {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken();
                defaultJSONParser.accept(17);
                Object obj3 = defaultJSONParser.parse();
                defaultJSONParser.accept(13);
                obj2 = obj3;
            } else {
                throw new JSONException("syntax error");
            }
        } else {
            obj2 = defaultJSONParser.parse();
        }
        T t = (T) cast(defaultJSONParser, type, obj, obj2, str);
        if (type == Calendar.class && !(t instanceof Calendar)) {
            Date date = (Date) t;
            if (date == null) {
                return null;
            }
            ?? r9 = (T) Calendar.getInstance(jSONLexer.timeZone, jSONLexer.locale);
            r9.setTime(date);
            return r9;
        }
        return t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [T, java.util.Calendar] */
    protected <T> T cast(DefaultJSONParser defaultJSONParser, Type type, Object obj, Object obj2, String str) {
        DateFormat dateFormat;
        if (obj2 == 0) {
            return null;
        }
        if (!(obj2 instanceof Date)) {
            if (obj2 instanceof BigDecimal) {
                return (T) new Date(((BigDecimal) obj2).longValueExact());
            }
            if (obj2 instanceof Number) {
                return (T) new Date(((Number) obj2).longValue());
            }
            if (obj2 instanceof String) {
                String str2 = (String) obj2;
                if (str2.length() == 0) {
                    return null;
                }
                JSONLexer jSONLexer = new JSONLexer(str2);
                try {
                    if (jSONLexer.scanISO8601DateIfMatch(false)) {
                        ?? r0 = (T) jSONLexer.calendar;
                        if (type != Calendar.class) {
                            T t = (T) r0.getTime();
                            jSONLexer.close();
                            return t;
                        }
                        jSONLexer.close();
                        return r0;
                    }
                    jSONLexer.close();
                    if ("0000-00-00".equals(str2) || "0000-00-00T00:00:00".equalsIgnoreCase(str2) || "0001-01-01T00:00:00+08:00".equalsIgnoreCase(str2)) {
                        return null;
                    }
                    if (str != null) {
                        dateFormat = new SimpleDateFormat(str);
                    } else {
                        dateFormat = defaultJSONParser.getDateFormat();
                    }
                    try {
                        return (T) dateFormat.parse(str2);
                    } catch (ParseException e) {
                        return (T) new Date(Long.parseLong(str2));
                    }
                } catch (Throwable th) {
                    jSONLexer.close();
                    throw th;
                }
            }
            throw new JSONException("parse error");
        }
        return obj2;
    }
}

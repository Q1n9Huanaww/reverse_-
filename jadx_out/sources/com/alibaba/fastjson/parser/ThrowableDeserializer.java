package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class ThrowableDeserializer extends JavaBeanDeserializer {
    public ThrowableDeserializer(ParserConfig mapping, Class<?> clazz) {
        super(mapping, clazz, clazz);
    }

    @Override // com.alibaba.fastjson.parser.JavaBeanDeserializer, com.alibaba.fastjson.parser.deserializer.ObjectDeserializer
    public <T> T deserialze(DefaultJSONParser defaultJSONParser, Type type, Object obj) {
        Throwable th;
        Object obj2;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        if (jSONLexer.token == 8) {
            jSONLexer.nextToken();
            return null;
        }
        if (defaultJSONParser.resolveStatus == 2) {
            defaultJSONParser.resolveStatus = 0;
        } else if (jSONLexer.token != 12) {
            throw new JSONException("syntax error");
        }
        Throwable th2 = null;
        Class<?> clsLoadClass = null;
        if (type != null && (type instanceof Class)) {
            Class<?> cls = (Class) type;
            if (Throwable.class.isAssignableFrom(cls)) {
                clsLoadClass = cls;
            }
        }
        String strStringVal = null;
        StackTraceElement[] stackTraceElementArr = null;
        Map<String, Object> map = null;
        while (true) {
            String strScanSymbol = jSONLexer.scanSymbol(defaultJSONParser.symbolTable);
            if (strScanSymbol == null) {
                if (jSONLexer.token == 13) {
                    jSONLexer.nextToken(16);
                    break;
                }
                if (jSONLexer.token == 16) {
                    continue;
                }
            }
            jSONLexer.nextTokenWithChar(':');
            if (JSON.DEFAULT_TYPE_KEY.equals(strScanSymbol)) {
                if (jSONLexer.token == 4) {
                    clsLoadClass = TypeUtils.loadClass(jSONLexer.stringVal(), defaultJSONParser.config.defaultClassLoader, false);
                    jSONLexer.nextToken(16);
                } else {
                    throw new JSONException("syntax error");
                }
            } else if ("message".equals(strScanSymbol)) {
                if (jSONLexer.token == 8) {
                    strStringVal = null;
                } else if (jSONLexer.token == 4) {
                    strStringVal = jSONLexer.stringVal();
                } else {
                    throw new JSONException("syntax error");
                }
                jSONLexer.nextToken();
            } else if ("cause".equals(strScanSymbol)) {
                th2 = (Throwable) deserialze(defaultJSONParser, null, "cause");
            } else if ("stackTrace".equals(strScanSymbol)) {
                stackTraceElementArr = (StackTraceElement[]) defaultJSONParser.parseObject((Class) StackTraceElement[].class);
            } else {
                if (map == null) {
                    map = new HashMap<>();
                }
                map.put(strScanSymbol, defaultJSONParser.parse());
            }
            if (jSONLexer.token == 13) {
                jSONLexer.nextToken(16);
                break;
            }
        }
        if (clsLoadClass == null) {
            obj2 = (T) new Exception(strStringVal, th2);
        } else {
            Constructor<?> constructor = null;
            Constructor<?> constructor2 = null;
            Constructor<?> constructor3 = null;
            try {
                for (Constructor<?> constructor4 : clsLoadClass.getConstructors()) {
                    if (constructor4.getParameterTypes().length == 0) {
                        constructor = constructor4;
                    } else if (constructor4.getParameterTypes().length == 1 && constructor4.getParameterTypes()[0] == String.class) {
                        constructor2 = constructor4;
                    } else if (constructor4.getParameterTypes().length == 2 && constructor4.getParameterTypes()[0] == String.class && constructor4.getParameterTypes()[1] == Throwable.class) {
                        constructor3 = constructor4;
                    }
                }
                if (constructor3 != null) {
                    th = (Throwable) constructor3.newInstance(strStringVal, th2);
                } else if (constructor2 != null) {
                    th = (Throwable) constructor2.newInstance(strStringVal);
                } else if (constructor == null) {
                    th = null;
                } else {
                    th = (Throwable) constructor.newInstance(new Object[0]);
                }
                if (th == null) {
                    try {
                        obj2 = (T) new Exception(strStringVal, th2);
                    } catch (Exception e) {
                        e = e;
                        throw new JSONException("create instance error", e);
                    }
                } else {
                    obj2 = (T) th;
                }
            } catch (Exception e2) {
                e = e2;
            }
        }
        if (stackTraceElementArr != null) {
            ((Throwable) obj2).setStackTrace(stackTraceElementArr);
        }
        if (map == null) {
            return (T) obj2;
        }
        JavaBeanDeserializer javaBeanDeserializer = null;
        if (clsLoadClass != null) {
            if (clsLoadClass == this.clazz) {
                javaBeanDeserializer = this;
            } else {
                ObjectDeserializer deserializer = defaultJSONParser.config.getDeserializer(clsLoadClass);
                if (deserializer instanceof JavaBeanDeserializer) {
                    javaBeanDeserializer = (JavaBeanDeserializer) deserializer;
                }
            }
        }
        if (javaBeanDeserializer == null) {
            return (T) obj2;
        }
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();
            FieldDeserializer fieldDeserializer = javaBeanDeserializer.getFieldDeserializer(key);
            if (fieldDeserializer != null) {
                fieldDeserializer.setValue(obj2, value);
            }
        }
        return (T) obj2;
    }
}

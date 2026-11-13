package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.serializer.DateCodec;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class DefaultFieldDeserializer extends FieldDeserializer {
    protected ObjectDeserializer fieldValueDeserilizer;

    public DefaultFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        super(clazz, fieldInfo, 2);
    }

    public ObjectDeserializer getFieldValueDeserilizer(ParserConfig config) {
        if (this.fieldValueDeserilizer == null) {
            this.fieldValueDeserilizer = config.getDeserializer(this.fieldInfo.fieldClass, this.fieldInfo.fieldType);
        }
        return this.fieldValueDeserilizer;
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser parser, Object object, Type objectType, Map<String, Object> fieldValues) {
        Object value;
        Class<?> fieldClass;
        if (this.fieldValueDeserilizer == null) {
            this.fieldValueDeserilizer = parser.config.getDeserializer(this.fieldInfo.fieldClass, this.fieldInfo.fieldType);
        }
        Type fieldType = this.fieldInfo.fieldType;
        if (objectType instanceof ParameterizedType) {
            ParseContext objContext = parser.contex;
            if (objContext != null) {
                objContext.type = objectType;
            }
            fieldType = FieldInfo.getFieldType(this.clazz, objectType, fieldType);
            this.fieldValueDeserilizer = parser.config.getDeserializer(fieldType);
        }
        if ((fieldType instanceof ParameterizedType) && (objectType instanceof ParameterizedType)) {
            ParameterizedType fieldParamType = (ParameterizedType) fieldType;
            ParameterizedType objectParamType = (ParameterizedType) objectType;
            Type[] args = fieldParamType.getActualTypeArguments();
            Type rawType = objectParamType.getRawType();
            if (rawType instanceof Class) {
                Class<?> rawClass = (Class) rawType;
                boolean changed = TypeUtils.getArgument(args, rawClass.getTypeParameters(), objectParamType.getActualTypeArguments());
                if (changed) {
                    fieldType = new ParameterizedTypeImpl(args, fieldParamType.getOwnerType(), fieldParamType.getRawType());
                }
            }
        }
        String format = this.fieldInfo.format;
        if (format != null && (this.fieldValueDeserilizer instanceof DateCodec)) {
            value = ((DateCodec) this.fieldValueDeserilizer).deserialze(parser, fieldType, this.fieldInfo.name, format);
        } else {
            value = this.fieldValueDeserilizer.deserialze(parser, fieldType, this.fieldInfo.name);
        }
        if (parser.resolveStatus == 1) {
            DefaultJSONParser.ResolveTask task = parser.getLastResolveTask();
            task.fieldDeserializer = this;
            task.ownerContext = parser.contex;
            parser.resolveStatus = 0;
            return;
        }
        if (object == null) {
            fieldValues.put(this.fieldInfo.name, value);
            return;
        }
        if (value != null || ((fieldClass = this.fieldInfo.fieldClass) != Byte.TYPE && fieldClass != Short.TYPE && fieldClass != Float.TYPE && fieldClass != Double.TYPE)) {
            setValue(object, value);
        }
    }
}

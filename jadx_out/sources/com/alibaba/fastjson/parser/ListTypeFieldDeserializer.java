package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
class ListTypeFieldDeserializer extends FieldDeserializer {
    private final boolean array;
    private ObjectDeserializer deserializer;
    private final Type itemType;

    public ListTypeFieldDeserializer(ParserConfig mapping, Class<?> clazz, FieldInfo fieldInfo) {
        super(clazz, fieldInfo, 14);
        Type fieldType = fieldInfo.fieldType;
        Class<?> fieldClass = fieldInfo.fieldClass;
        if (fieldClass.isArray()) {
            this.itemType = fieldClass.getComponentType();
            this.array = true;
        } else {
            this.itemType = TypeUtils.getCollectionItemType(fieldType);
            this.array = false;
        }
    }

    @Override // com.alibaba.fastjson.parser.deserializer.FieldDeserializer
    public void parseField(DefaultJSONParser defaultJSONParser, Object obj, Type type, Map<String, Object> map) {
        List arrayList;
        Object obj2;
        Object array;
        JSONLexer jSONLexer = defaultJSONParser.lexer;
        int i = jSONLexer.token();
        if (i == 8 || (i == 4 && jSONLexer.stringVal().length() == 0)) {
            setValue(obj, (Object) null);
            defaultJSONParser.lexer.nextToken();
            return;
        }
        JSONArray jSONArray = null;
        if (this.array) {
            JSONArray jSONArray2 = new JSONArray();
            arrayList = jSONArray2;
            jSONArray2.setComponentType(this.itemType);
            jSONArray = jSONArray2;
        } else {
            arrayList = new ArrayList();
        }
        ParseContext parseContext = defaultJSONParser.contex;
        defaultJSONParser.setContext(parseContext, obj, this.fieldInfo.name);
        parseArray(defaultJSONParser, type, arrayList);
        defaultJSONParser.setContext(parseContext);
        if (this.array) {
            array = arrayList.toArray((Object[]) Array.newInstance((Class<?>) this.itemType, arrayList.size()));
            jSONArray.setRelatedArray(array);
        } else {
            obj2 = arrayList;
        }
        if (obj == null) {
            obj2 = array;
            map.put(this.fieldInfo.name, obj2);
        } else {
            obj2 = array;
            setValue(obj, obj2);
        }
    }

    final void parseArray(DefaultJSONParser parser, Type objectType, Collection array) {
        Type itemType = this.itemType;
        ObjectDeserializer itemTypeDeser = this.deserializer;
        if (objectType instanceof ParameterizedType) {
            if (itemType instanceof TypeVariable) {
                TypeVariable typeVar = (TypeVariable) itemType;
                ParameterizedType paramType = (ParameterizedType) objectType;
                Class<?> objectClass = null;
                if (paramType.getRawType() instanceof Class) {
                    objectClass = (Class) paramType.getRawType();
                }
                int paramIndex = -1;
                if (objectClass != null) {
                    int size = objectClass.getTypeParameters().length;
                    for (int i = 0; i < size; i++) {
                        if (objectClass.getTypeParameters()[i].getName().equals(typeVar.getName())) {
                            paramIndex = i;
                            break;
                        }
                    }
                }
                if (paramIndex != -1) {
                    itemType = paramType.getActualTypeArguments()[paramIndex];
                    if (!itemType.equals(this.itemType)) {
                        itemTypeDeser = parser.config.getDeserializer(itemType);
                    }
                }
            } else if (itemType instanceof ParameterizedType) {
                ParameterizedType parameterizedItemType = (ParameterizedType) itemType;
                Type[] itemActualTypeArgs = parameterizedItemType.getActualTypeArguments();
                if (itemActualTypeArgs.length == 1 && (itemActualTypeArgs[0] instanceof TypeVariable)) {
                    TypeVariable typeVar2 = (TypeVariable) itemActualTypeArgs[0];
                    ParameterizedType paramType2 = (ParameterizedType) objectType;
                    Class<?> objectClass2 = null;
                    if (paramType2.getRawType() instanceof Class) {
                        objectClass2 = (Class) paramType2.getRawType();
                    }
                    int paramIndex2 = -1;
                    if (objectClass2 != null) {
                        int size2 = objectClass2.getTypeParameters().length;
                        for (int i2 = 0; i2 < size2; i2++) {
                            if (objectClass2.getTypeParameters()[i2].getName().equals(typeVar2.getName())) {
                                paramIndex2 = i2;
                                break;
                            }
                        }
                    }
                    if (paramIndex2 != -1) {
                        itemActualTypeArgs[0] = paramType2.getActualTypeArguments()[paramIndex2];
                        itemType = new ParameterizedTypeImpl(itemActualTypeArgs, parameterizedItemType.getOwnerType(), parameterizedItemType.getRawType());
                    }
                }
            }
        } else if ((itemType instanceof TypeVariable) && (objectType instanceof Class)) {
            Class objectClass3 = (Class) objectType;
            TypeVariable typeVar3 = (TypeVariable) itemType;
            objectClass3.getTypeParameters();
            int size3 = objectClass3.getTypeParameters().length;
            for (int i3 = 0; i3 < size3; i3++) {
                TypeVariable item = objectClass3.getTypeParameters()[i3];
                if (item.getName().equals(typeVar3.getName())) {
                    Type[] bounds = item.getBounds();
                    if (bounds.length != 1) {
                        break;
                    }
                    itemType = bounds[0];
                    break;
                }
            }
        }
        JSONLexer lexer = parser.lexer;
        if (itemTypeDeser == null) {
            itemTypeDeser = parser.config.getDeserializer(itemType);
            this.deserializer = itemTypeDeser;
        }
        if (lexer.token != 14) {
            if (lexer.token == 12) {
                Object val = itemTypeDeser.deserialze(parser, itemType, 0);
                array.add(val);
                return;
            } else {
                String errorMessage = "exepct '[', but " + JSONToken.name(lexer.token);
                if (objectType != null) {
                    errorMessage = errorMessage + ", type : " + objectType;
                }
                throw new JSONException(errorMessage);
            }
        }
        int ch = lexer.f44ch;
        if (ch == 91) {
            int index = lexer.f43bp + 1;
            lexer.f43bp = index;
            lexer.f44ch = index >= lexer.len ? JSONLexer.EOI : lexer.text.charAt(index);
            lexer.token = 14;
        } else if (ch == 123) {
            int index2 = lexer.f43bp + 1;
            lexer.f43bp = index2;
            lexer.f44ch = index2 >= lexer.len ? JSONLexer.EOI : lexer.text.charAt(index2);
            lexer.token = 12;
        } else if (ch == 34) {
            lexer.scanString();
        } else if (ch == 93) {
            int index3 = lexer.f43bp + 1;
            lexer.f43bp = index3;
            lexer.f44ch = index3 >= lexer.len ? JSONLexer.EOI : lexer.text.charAt(index3);
            lexer.token = 15;
        } else {
            lexer.nextToken();
        }
        int i4 = 0;
        while (true) {
            if (lexer.token == 16) {
                lexer.nextToken();
            } else {
                if (lexer.token == 15) {
                    break;
                }
                Object val2 = itemTypeDeser.deserialze(parser, itemType, Integer.valueOf(i4));
                array.add(val2);
                if (parser.resolveStatus == 1) {
                    parser.checkListResolve(array);
                }
                if (lexer.token == 16) {
                    int ch2 = lexer.f44ch;
                    if (ch2 == 91) {
                        int index4 = lexer.f43bp + 1;
                        lexer.f43bp = index4;
                        lexer.f44ch = index4 >= lexer.len ? JSONLexer.EOI : lexer.text.charAt(index4);
                        lexer.token = 14;
                    } else if (ch2 == 123) {
                        int index5 = lexer.f43bp + 1;
                        lexer.f43bp = index5;
                        lexer.f44ch = index5 >= lexer.len ? JSONLexer.EOI : lexer.text.charAt(index5);
                        lexer.token = 12;
                    } else if (ch2 == 34) {
                        lexer.scanString();
                    } else {
                        lexer.nextToken();
                    }
                }
                i4++;
            }
        }
        if (lexer.f44ch == ',') {
            int index6 = lexer.f43bp + 1;
            lexer.f43bp = index6;
            lexer.f44ch = index6 >= lexer.len ? JSONLexer.EOI : lexer.text.charAt(index6);
            lexer.token = 16;
            return;
        }
        lexer.nextToken();
    }
}

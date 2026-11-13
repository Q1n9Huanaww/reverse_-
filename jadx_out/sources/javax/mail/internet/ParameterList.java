package javax.mail.internet;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.litepal.util.Const;
import org.slf4j.Marker;

/* JADX INFO: loaded from: classes.dex */
public class ParameterList {
    private static boolean applehack;
    private static boolean decodeParameters;
    private static boolean decodeParametersStrict;
    private static boolean encodeParameters;
    private static final char[] hex;
    private String lastName;
    private Map list;
    private Set multisegmentNames;
    private Map slist;

    static {
        try {
            String property = System.getProperty("mail.mime.encodeparameters");
            boolean z = false;
            encodeParameters = property != null && property.equalsIgnoreCase("true");
            String property2 = System.getProperty("mail.mime.decodeparameters");
            decodeParameters = property2 != null && property2.equalsIgnoreCase("true");
            String property3 = System.getProperty("mail.mime.decodeparameters.strict");
            decodeParametersStrict = property3 != null && property3.equalsIgnoreCase("true");
            String property4 = System.getProperty("mail.mime.applefilenames");
            if (property4 != null && property4.equalsIgnoreCase("true")) {
                z = true;
            }
            applehack = z;
        } catch (SecurityException unused) {
        }
        hex = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    }

    private static class Value {
        String charset;
        String encodedValue;
        String value;

        private Value() {
        }

        /* synthetic */ Value(Value value) {
            this();
        }
    }

    private static class MultiValue extends ArrayList {
        String value;

        private MultiValue() {
        }

        /* synthetic */ MultiValue(MultiValue multiValue) {
            this();
        }
    }

    private static class ParamEnum implements Enumeration {

        /* JADX INFO: renamed from: it */
        private Iterator f4747it;

        ParamEnum(Iterator it2) {
            this.f4747it = it2;
        }

        @Override // java.util.Enumeration
        public boolean hasMoreElements() {
            return this.f4747it.hasNext();
        }

        @Override // java.util.Enumeration
        public Object nextElement() {
            return this.f4747it.next();
        }
    }

    public ParameterList() {
        this.list = new LinkedHashMap();
        this.lastName = null;
        if (decodeParameters) {
            this.multisegmentNames = new HashSet();
            this.slist = new HashMap();
        }
    }

    public ParameterList(String str) throws ParseException {
        this();
        HeaderTokenizer headerTokenizer = new HeaderTokenizer(str, HeaderTokenizer.MIME);
        while (true) {
            HeaderTokenizer.Token next = headerTokenizer.next();
            int type = next.getType();
            if (type == -4) {
                break;
            }
            if (((char) type) == ';') {
                HeaderTokenizer.Token next2 = headerTokenizer.next();
                if (next2.getType() == -4) {
                    break;
                }
                if (next2.getType() != -1) {
                    throw new ParseException("Expected parameter name, got \"" + next2.getValue() + "\"");
                }
                String lowerCase = next2.getValue().toLowerCase(Locale.ENGLISH);
                HeaderTokenizer.Token next3 = headerTokenizer.next();
                if (((char) next3.getType()) != '=') {
                    throw new ParseException("Expected '=', got \"" + next3.getValue() + "\"");
                }
                HeaderTokenizer.Token next4 = headerTokenizer.next();
                int type2 = next4.getType();
                if (type2 != -1 && type2 != -2) {
                    throw new ParseException("Expected parameter value, got \"" + next4.getValue() + "\"");
                }
                String value = next4.getValue();
                this.lastName = lowerCase;
                if (decodeParameters) {
                    putEncodedName(lowerCase, value);
                } else {
                    this.list.put(lowerCase, value);
                }
            } else if (applehack && type == -1 && this.lastName != null && (this.lastName.equals(Const.TableSchema.COLUMN_NAME) || this.lastName.equals("filename"))) {
                this.list.put(this.lastName, String.valueOf((String) this.list.get(this.lastName)) + " " + next.getValue());
            } else {
                throw new ParseException("Expected ';', got \"" + next.getValue() + "\"");
            }
        }
        if (decodeParameters) {
            combineMultisegmentNames(false);
        }
    }

    private void putEncodedName(String str, String str2) throws ParseException {
        Object obj;
        int iIndexOf = str.indexOf(42);
        if (iIndexOf < 0) {
            this.list.put(str, str2);
            return;
        }
        if (iIndexOf == str.length() - 1) {
            this.list.put(str.substring(0, iIndexOf), decodeValue(str2));
            return;
        }
        String strSubstring = str.substring(0, iIndexOf);
        this.multisegmentNames.add(strSubstring);
        this.list.put(strSubstring, "");
        if (str.endsWith(Marker.ANY_MARKER)) {
            obj = str2;
            Value value = new Value(null);
            Value value2 = value;
            value2.encodedValue = str2;
            value2.value = str2;
            str = str.substring(0, str.length() - 1);
            obj = value;
        }
        obj = str2;
        this.slist.put(str, obj);
    }

    private void combineMultisegmentNames(boolean z) throws ParseException {
        String str;
        try {
            for (String str2 : this.multisegmentNames) {
                StringBuffer stringBuffer = new StringBuffer();
                MultiValue multiValue = new MultiValue(null);
                int i = 0;
                String str3 = null;
                while (true) {
                    String str4 = String.valueOf(str2) + Marker.ANY_MARKER + i;
                    Object obj = this.slist.get(str4);
                    if (obj == null) {
                        break;
                    }
                    multiValue.add(obj);
                    if (obj instanceof Value) {
                        try {
                            Value value = (Value) obj;
                            str = value.encodedValue;
                            if (i == 0) {
                                try {
                                    Value valueDecodeValue = decodeValue(str);
                                    String str5 = valueDecodeValue.charset;
                                    value.charset = str5;
                                    try {
                                        String str6 = valueDecodeValue.value;
                                        value.value = str6;
                                        str = str6;
                                        str3 = str5;
                                    } catch (UnsupportedEncodingException e) {
                                        e = e;
                                        str3 = str5;
                                        if (decodeParametersStrict) {
                                            throw new ParseException(e.toString());
                                        }
                                    } catch (NumberFormatException e2) {
                                        e = e2;
                                        str3 = str5;
                                        if (decodeParametersStrict) {
                                            throw new ParseException(e.toString());
                                        }
                                    } catch (StringIndexOutOfBoundsException e3) {
                                        e = e3;
                                        str3 = str5;
                                        if (decodeParametersStrict) {
                                            throw new ParseException(e.toString());
                                        }
                                    }
                                } catch (UnsupportedEncodingException e4) {
                                    e = e4;
                                } catch (NumberFormatException e5) {
                                    e = e5;
                                } catch (StringIndexOutOfBoundsException e6) {
                                    e = e6;
                                }
                            } else if (str3 == null) {
                                this.multisegmentNames.remove(str2);
                                break;
                            } else {
                                String strDecodeBytes = decodeBytes(str, str3);
                                value.value = strDecodeBytes;
                                str = strDecodeBytes;
                            }
                        } catch (UnsupportedEncodingException e7) {
                            e = e7;
                            str = null;
                        } catch (NumberFormatException e8) {
                            e = e8;
                            str = null;
                        } catch (StringIndexOutOfBoundsException e9) {
                            e = e9;
                            str = null;
                        }
                    } else {
                        str = (String) obj;
                    }
                    stringBuffer.append(str);
                    this.slist.remove(str4);
                    i++;
                }
                if (i == 0) {
                    this.list.remove(str2);
                } else {
                    multiValue.value = stringBuffer.toString();
                    this.list.put(str2, multiValue);
                }
            }
            if (this.slist.size() > 0) {
                for (Object obj2 : this.slist.values()) {
                    if (obj2 instanceof Value) {
                        Value value2 = (Value) obj2;
                        Value valueDecodeValue2 = decodeValue(value2.encodedValue);
                        value2.charset = valueDecodeValue2.charset;
                        value2.value = valueDecodeValue2.value;
                    }
                }
                this.list.putAll(this.slist);
            }
            this.multisegmentNames.clear();
            this.slist.clear();
        } catch (Throwable th) {
            if (z) {
                if (this.slist.size() > 0) {
                    for (Object obj3 : this.slist.values()) {
                        if (obj3 instanceof Value) {
                            Value value3 = (Value) obj3;
                            Value valueDecodeValue3 = decodeValue(value3.encodedValue);
                            value3.charset = valueDecodeValue3.charset;
                            value3.value = valueDecodeValue3.value;
                        }
                    }
                    this.list.putAll(this.slist);
                }
                this.multisegmentNames.clear();
                this.slist.clear();
            }
            throw th;
        }
    }

    public int size() {
        return this.list.size();
    }

    public String get(String str) {
        Object obj = this.list.get(str.trim().toLowerCase(Locale.ENGLISH));
        if (obj instanceof MultiValue) {
            return ((MultiValue) obj).value;
        }
        if (obj instanceof Value) {
            return ((Value) obj).value;
        }
        return (String) obj;
    }

    public void set(String str, String str2) {
        if (str == null && str2 != null && str2.equals("DONE")) {
            if (!decodeParameters || this.multisegmentNames.size() <= 0) {
                return;
            }
            try {
                combineMultisegmentNames(true);
                return;
            } catch (ParseException unused) {
                return;
            }
        }
        String lowerCase = str.trim().toLowerCase(Locale.ENGLISH);
        if (decodeParameters) {
            try {
                putEncodedName(lowerCase, str2);
                return;
            } catch (ParseException unused2) {
                this.list.put(lowerCase, str2);
                return;
            }
        }
        this.list.put(lowerCase, str2);
    }

    public void set(String str, String str2, String str3) {
        if (encodeParameters) {
            Value valueEncodeValue = encodeValue(str2, str3);
            if (valueEncodeValue != null) {
                this.list.put(str.trim().toLowerCase(Locale.ENGLISH), valueEncodeValue);
                return;
            } else {
                set(str, str2);
                return;
            }
        }
        set(str, str2);
    }

    public void remove(String str) {
        this.list.remove(str.trim().toLowerCase(Locale.ENGLISH));
    }

    public Enumeration getNames() {
        return new ParamEnum(this.list.keySet().iterator());
    }

    public String toString() {
        return toString(0);
    }

    public String toString(int i) {
        ToStringBuffer toStringBuffer = new ToStringBuffer(i);
        for (String str : this.list.keySet()) {
            Object obj = this.list.get(str);
            if (obj instanceof MultiValue) {
                MultiValue multiValue = (MultiValue) obj;
                String str2 = String.valueOf(str) + Marker.ANY_MARKER;
                for (int i2 = 0; i2 < multiValue.size(); i2++) {
                    Object obj2 = multiValue.get(i2);
                    if (obj2 instanceof Value) {
                        toStringBuffer.addNV(String.valueOf(str2) + i2 + Marker.ANY_MARKER, ((Value) obj2).encodedValue);
                    } else {
                        toStringBuffer.addNV(String.valueOf(str2) + i2, (String) obj2);
                    }
                }
            } else if (obj instanceof Value) {
                toStringBuffer.addNV(String.valueOf(str) + Marker.ANY_MARKER, ((Value) obj).encodedValue);
            } else {
                toStringBuffer.addNV(str, (String) obj);
            }
        }
        return toStringBuffer.toString();
    }

    private static class ToStringBuffer {

        /* JADX INFO: renamed from: sb */
        private StringBuffer f4748sb = new StringBuffer();
        private int used;

        public ToStringBuffer(int i) {
            this.used = i;
        }

        public void addNV(String str, String str2) {
            String strQuote = ParameterList.quote(str2);
            this.f4748sb.append("; ");
            this.used += 2;
            if (this.used + str.length() + strQuote.length() + 1 > 76) {
                this.f4748sb.append("\r\n\t");
                this.used = 8;
            }
            StringBuffer stringBuffer = this.f4748sb;
            stringBuffer.append(str);
            stringBuffer.append('=');
            this.used += str.length() + 1;
            if (this.used + strQuote.length() > 76) {
                String strFold = MimeUtility.fold(this.used, strQuote);
                this.f4748sb.append(strFold);
                int iLastIndexOf = strFold.lastIndexOf(10);
                if (iLastIndexOf >= 0) {
                    this.used += (strFold.length() - iLastIndexOf) - 1;
                    return;
                } else {
                    this.used += strFold.length();
                    return;
                }
            }
            this.f4748sb.append(strQuote);
            this.used += strQuote.length();
        }

        public String toString() {
            return this.f4748sb.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String quote(String str) {
        return MimeUtility.quote(str, HeaderTokenizer.MIME);
    }

    private static Value encodeValue(String str, String str2) {
        Value value = null;
        if (MimeUtility.checkAscii(str) == 1) {
            return null;
        }
        try {
            byte[] bytes = str.getBytes(MimeUtility.javaCharset(str2));
            StringBuffer stringBuffer = new StringBuffer(bytes.length + str2.length() + 2);
            stringBuffer.append(str2);
            stringBuffer.append("''");
            for (byte b : bytes) {
                char c = (char) (b & 255);
                if (c <= ' ' || c >= 127 || c == '*' || c == '\'' || c == '%' || HeaderTokenizer.MIME.indexOf(c) >= 0) {
                    stringBuffer.append('%');
                    stringBuffer.append(hex[c >> 4]);
                    stringBuffer.append(hex[c & 15]);
                } else {
                    stringBuffer.append(c);
                }
            }
            Value value2 = new Value(value);
            value2.charset = str2;
            value2.value = str;
            value2.encodedValue = stringBuffer.toString();
            return value2;
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    private static Value decodeValue(String str) throws ParseException {
        Value value = new Value(null);
        value.encodedValue = str;
        value.value = str;
        try {
            int iIndexOf = str.indexOf(39);
            if (iIndexOf <= 0) {
                if (!decodeParametersStrict) {
                    return value;
                }
                throw new ParseException("Missing charset in encoded value: " + str);
            }
            String strSubstring = str.substring(0, iIndexOf);
            int i = iIndexOf + 1;
            int iIndexOf2 = str.indexOf(39, i);
            if (iIndexOf2 < 0) {
                if (!decodeParametersStrict) {
                    return value;
                }
                throw new ParseException("Missing language in encoded value: " + str);
            }
            str.substring(i, iIndexOf2);
            String strSubstring2 = str.substring(iIndexOf2 + 1);
            value.charset = strSubstring;
            value.value = decodeBytes(strSubstring2, strSubstring);
            return value;
        } catch (UnsupportedEncodingException e) {
            if (decodeParametersStrict) {
                throw new ParseException(e.toString());
            }
        } catch (NumberFormatException e2) {
            if (decodeParametersStrict) {
                throw new ParseException(e2.toString());
            }
        } catch (StringIndexOutOfBoundsException e3) {
            if (decodeParametersStrict) {
                throw new ParseException(e3.toString());
            }
        }
    }

    private static String decodeBytes(String str, String str2) throws UnsupportedEncodingException {
        byte[] bArr = new byte[str.length()];
        int i = 0;
        int i2 = 0;
        while (i < str.length()) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '%') {
                cCharAt = (char) Integer.parseInt(str.substring(i + 1, i + 3), 16);
                i += 2;
            }
            bArr[i2] = (byte) cCharAt;
            i++;
            i2++;
        }
        return new String(bArr, 0, i2, MimeUtility.javaCharset(str2));
    }
}

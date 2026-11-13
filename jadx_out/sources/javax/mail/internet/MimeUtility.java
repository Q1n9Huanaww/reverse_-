package javax.mail.internet;

import com.lidroid.xutils.http.client.multipart.MIME;
import com.sun.mail.util.ASCIIUtility;
import com.sun.mail.util.BASE64DecoderStream;
import com.sun.mail.util.BASE64EncoderStream;
import com.sun.mail.util.BEncoderStream;
import com.sun.mail.util.LineInputStream;
import com.sun.mail.util.QDecoderStream;
import com.sun.mail.util.QEncoderStream;
import com.sun.mail.util.QPDecoderStream;
import com.sun.mail.util.QPEncoderStream;
import com.sun.mail.util.UUDecoderStream;
import com.sun.mail.util.UUEncoderStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class MimeUtility {
    public static final int ALL = -1;
    static final int ALL_ASCII = 1;
    static final int MOSTLY_ASCII = 2;
    static final int MOSTLY_NONASCII = 3;
    private static boolean decodeStrict = true;
    private static String defaultJavaCharset = null;
    private static String defaultMIMECharset = null;
    private static boolean encodeEolStrict = false;
    private static boolean foldEncodedWords = false;
    private static boolean foldText = true;
    private static Hashtable java2mime;
    private static Hashtable mime2java;

    static final boolean nonascii(int i) {
        if (i < 127) {
            return (i >= 32 || i == 13 || i == 10 || i == 9) ? false : true;
        }
        return true;
    }

    private MimeUtility() {
    }

    static {
        InputStream lineInputStream;
        Throwable th;
        try {
            String property = System.getProperty("mail.mime.decodetext.strict");
            boolean z = true;
            decodeStrict = property == null || !property.equalsIgnoreCase("false");
            String property2 = System.getProperty("mail.mime.encodeeol.strict");
            encodeEolStrict = property2 != null && property2.equalsIgnoreCase("true");
            String property3 = System.getProperty("mail.mime.foldencodedwords");
            foldEncodedWords = property3 != null && property3.equalsIgnoreCase("true");
            String property4 = System.getProperty("mail.mime.foldtext");
            if (property4 != null && property4.equalsIgnoreCase("false")) {
                z = false;
            }
            foldText = z;
        } catch (SecurityException unused) {
        }
        java2mime = new Hashtable(40);
        mime2java = new Hashtable(10);
        try {
            InputStream resourceAsStream = MimeUtility.class.getResourceAsStream("/META-INF/javamail.charset.map");
            if (resourceAsStream != null) {
                try {
                    lineInputStream = new LineInputStream(resourceAsStream);
                    try {
                        loadMappings((LineInputStream) lineInputStream, java2mime);
                        loadMappings((LineInputStream) lineInputStream, mime2java);
                        lineInputStream.close();
                    } catch (Throwable th2) {
                        th = th2;
                        try {
                            lineInputStream.close();
                        } catch (Exception unused2) {
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    lineInputStream = resourceAsStream;
                    th = th3;
                }
            }
        } catch (Exception unused3) {
        }
        if (java2mime.isEmpty()) {
            java2mime.put("8859_1", "ISO-8859-1");
            java2mime.put("iso8859_1", "ISO-8859-1");
            java2mime.put("iso8859-1", "ISO-8859-1");
            java2mime.put("8859_2", "ISO-8859-2");
            java2mime.put("iso8859_2", "ISO-8859-2");
            java2mime.put("iso8859-2", "ISO-8859-2");
            java2mime.put("8859_3", "ISO-8859-3");
            java2mime.put("iso8859_3", "ISO-8859-3");
            java2mime.put("iso8859-3", "ISO-8859-3");
            java2mime.put("8859_4", "ISO-8859-4");
            java2mime.put("iso8859_4", "ISO-8859-4");
            java2mime.put("iso8859-4", "ISO-8859-4");
            java2mime.put("8859_5", "ISO-8859-5");
            java2mime.put("iso8859_5", "ISO-8859-5");
            java2mime.put("iso8859-5", "ISO-8859-5");
            java2mime.put("8859_6", "ISO-8859-6");
            java2mime.put("iso8859_6", "ISO-8859-6");
            java2mime.put("iso8859-6", "ISO-8859-6");
            java2mime.put("8859_7", "ISO-8859-7");
            java2mime.put("iso8859_7", "ISO-8859-7");
            java2mime.put("iso8859-7", "ISO-8859-7");
            java2mime.put("8859_8", "ISO-8859-8");
            java2mime.put("iso8859_8", "ISO-8859-8");
            java2mime.put("iso8859-8", "ISO-8859-8");
            java2mime.put("8859_9", "ISO-8859-9");
            java2mime.put("iso8859_9", "ISO-8859-9");
            java2mime.put("iso8859-9", "ISO-8859-9");
            java2mime.put("sjis", "Shift_JIS");
            java2mime.put("jis", "ISO-2022-JP");
            java2mime.put("iso2022jp", "ISO-2022-JP");
            java2mime.put("euc_jp", "euc-jp");
            java2mime.put("koi8_r", "koi8-r");
            java2mime.put("euc_cn", "euc-cn");
            java2mime.put("euc_tw", "euc-tw");
            java2mime.put("euc_kr", "euc-kr");
        }
        if (mime2java.isEmpty()) {
            mime2java.put("iso-2022-cn", "ISO2022CN");
            mime2java.put("iso-2022-kr", "ISO2022KR");
            mime2java.put("utf-8", "UTF8");
            mime2java.put("utf8", "UTF8");
            mime2java.put("ja_jp.iso2022-7", "ISO2022JP");
            mime2java.put("ja_jp.eucjp", "EUCJIS");
            mime2java.put("euc-kr", "KSC5601");
            mime2java.put("euckr", "KSC5601");
            mime2java.put("us-ascii", "ISO-8859-1");
            mime2java.put("x-us-ascii", "ISO-8859-1");
        }
    }

    public static String getEncoding(DataSource dataSource) {
        String str;
        try {
            ContentType contentType = new ContentType(dataSource.getContentType());
            InputStream inputStream = dataSource.getInputStream();
            switch (checkAscii(inputStream, -1, !contentType.match("text/*"))) {
                case 1:
                    str = "7bit";
                    break;
                case 2:
                    str = "quoted-printable";
                    break;
                default:
                    str = "base64";
                    break;
            }
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
            return str;
        } catch (Exception unused2) {
            return "base64";
        }
    }

    public static String getEncoding(DataHandler dataHandler) {
        if (dataHandler.getName() != null) {
            return getEncoding(dataHandler.getDataSource());
        }
        try {
            if (new ContentType(dataHandler.getContentType()).match("text/*")) {
                AsciiOutputStream asciiOutputStream = new AsciiOutputStream(false, false);
                try {
                    dataHandler.writeTo(asciiOutputStream);
                } catch (IOException unused) {
                }
                switch (asciiOutputStream.getAscii()) {
                    case 1:
                        return "7bit";
                    case 2:
                        return "quoted-printable";
                    default:
                        return "base64";
                }
            }
            AsciiOutputStream asciiOutputStream2 = new AsciiOutputStream(true, encodeEolStrict);
            try {
                dataHandler.writeTo(asciiOutputStream2);
            } catch (IOException unused2) {
            }
            return asciiOutputStream2.getAscii() == 1 ? "7bit" : "base64";
        } catch (Exception unused3) {
            return "base64";
        }
    }

    public static InputStream decode(InputStream inputStream, String str) throws MessagingException {
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64DecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPDecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase("uuencode") || str.equalsIgnoreCase("x-uuencode") || str.equalsIgnoreCase("x-uue")) {
            return new UUDecoderStream(inputStream);
        }
        if (str.equalsIgnoreCase(MIME.ENC_BINARY) || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase(MIME.ENC_8BIT)) {
            return inputStream;
        }
        throw new MessagingException("Unknown encoding: " + str);
    }

    public static OutputStream encode(OutputStream outputStream, String str) throws MessagingException {
        if (str == null) {
            return outputStream;
        }
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64EncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPEncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("uuencode") || str.equalsIgnoreCase("x-uuencode") || str.equalsIgnoreCase("x-uue")) {
            return new UUEncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase(MIME.ENC_BINARY) || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase(MIME.ENC_8BIT)) {
            return outputStream;
        }
        throw new MessagingException("Unknown encoding: " + str);
    }

    public static OutputStream encode(OutputStream outputStream, String str, String str2) throws MessagingException {
        if (str == null) {
            return outputStream;
        }
        if (str.equalsIgnoreCase("base64")) {
            return new BASE64EncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("quoted-printable")) {
            return new QPEncoderStream(outputStream);
        }
        if (str.equalsIgnoreCase("uuencode") || str.equalsIgnoreCase("x-uuencode") || str.equalsIgnoreCase("x-uue")) {
            return new UUEncoderStream(outputStream, str2);
        }
        if (str.equalsIgnoreCase(MIME.ENC_BINARY) || str.equalsIgnoreCase("7bit") || str.equalsIgnoreCase(MIME.ENC_8BIT)) {
            return outputStream;
        }
        throw new MessagingException("Unknown encoding: " + str);
    }

    public static String encodeText(String str) throws UnsupportedEncodingException {
        return encodeText(str, null, null);
    }

    public static String encodeText(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, false);
    }

    public static String decodeText(String str) throws ParseException, UnsupportedEncodingException {
        if (str.indexOf("=?") == -1) {
            return str;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(str, " \t\n\r", true);
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        boolean zEndsWith = false;
        while (stringTokenizer.hasMoreTokens()) {
            String strNextToken = stringTokenizer.nextToken();
            char cCharAt = strNextToken.charAt(0);
            if (cCharAt == ' ' || cCharAt == '\t' || cCharAt == '\r' || cCharAt == '\n') {
                stringBuffer2.append(cCharAt);
            } else {
                try {
                    String strDecodeWord = decodeWord(strNextToken);
                    if (!zEndsWith && stringBuffer2.length() > 0) {
                        stringBuffer.append(stringBuffer2);
                    }
                    strNextToken = strDecodeWord;
                    zEndsWith = true;
                } catch (ParseException unused) {
                    if (!decodeStrict) {
                        String strDecodeInnerWords = decodeInnerWords(strNextToken);
                        if (strDecodeInnerWords != strNextToken) {
                            if ((!zEndsWith || !strNextToken.startsWith("=?")) && stringBuffer2.length() > 0) {
                                stringBuffer.append(stringBuffer2);
                            }
                            zEndsWith = strNextToken.endsWith("?=");
                            strNextToken = strDecodeInnerWords;
                        } else if (stringBuffer2.length() > 0) {
                            stringBuffer.append(stringBuffer2);
                        }
                    } else if (stringBuffer2.length() > 0) {
                        stringBuffer.append(stringBuffer2);
                    }
                    zEndsWith = false;
                }
                stringBuffer.append(strNextToken);
                stringBuffer2.setLength(0);
            }
        }
        stringBuffer.append(stringBuffer2);
        return stringBuffer.toString();
    }

    public static String encodeWord(String str) throws UnsupportedEncodingException {
        return encodeWord(str, null, null);
    }

    public static String encodeWord(String str, String str2, String str3) throws UnsupportedEncodingException {
        return encodeWord(str, str2, str3, true);
    }

    private static String encodeWord(String str, String str2, String str3, boolean z) throws UnsupportedEncodingException {
        String strJavaCharset;
        boolean z2;
        int iCheckAscii = checkAscii(str);
        if (iCheckAscii == 1) {
            return str;
        }
        if (str2 == null) {
            strJavaCharset = getDefaultJavaCharset();
            str2 = getDefaultMIMECharset();
        } else {
            strJavaCharset = javaCharset(str2);
        }
        if (str3 == null) {
            str3 = iCheckAscii != 3 ? "Q" : "B";
        }
        if (str3.equalsIgnoreCase("B")) {
            z2 = true;
        } else {
            if (!str3.equalsIgnoreCase("Q")) {
                throw new UnsupportedEncodingException("Unknown transfer encoding: " + str3);
            }
            z2 = false;
        }
        StringBuffer stringBuffer = new StringBuffer();
        doEncode(str, z2, strJavaCharset, 68 - str2.length(), "=?" + str2 + "?" + str3 + "?", true, z, stringBuffer);
        return stringBuffer.toString();
    }

    private static void doEncode(String str, boolean z, String str2, int i, String str3, boolean z2, boolean z3, StringBuffer stringBuffer) throws UnsupportedEncodingException {
        byte[] bytes;
        int iEncodedLength;
        OutputStream qEncoderStream;
        int length;
        int i2 = 0;
        String strSubstring = str;
        boolean z4 = z2;
        while (true) {
            bytes = strSubstring.getBytes(str2);
            if (z) {
                iEncodedLength = BEncoderStream.encodedLength(bytes);
            } else {
                iEncodedLength = QEncoderStream.encodedLength(bytes, z3);
            }
            if (iEncodedLength <= i || (length = strSubstring.length()) <= 1) {
                break;
            }
            int i3 = length / 2;
            doEncode(strSubstring.substring(i2, i3), z, str2, i, str3, z4, z3, stringBuffer);
            strSubstring = strSubstring.substring(i3, length);
            z4 = false;
            i2 = 0;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        if (z) {
            qEncoderStream = new BEncoderStream(byteArrayOutputStream);
        } else {
            qEncoderStream = new QEncoderStream(byteArrayOutputStream, z3);
        }
        try {
            qEncoderStream.write(bytes);
            qEncoderStream.close();
        } catch (IOException unused) {
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        if (!z4) {
            if (foldEncodedWords) {
                stringBuffer.append("\r\n ");
            } else {
                stringBuffer.append(" ");
            }
        }
        stringBuffer.append(str3);
        for (byte b : byteArray) {
            stringBuffer.append((char) b);
        }
        stringBuffer.append("?=");
    }

    public static String decodeWord(String str) throws ParseException, UnsupportedEncodingException {
        String str2;
        InputStream qDecoderStream;
        if (!str.startsWith("=?")) {
            throw new ParseException("encoded word does not start with \"=?\": " + str);
        }
        int iIndexOf = str.indexOf(63, 2);
        if (iIndexOf == -1) {
            throw new ParseException("encoded word does not include charset: " + str);
        }
        String strJavaCharset = javaCharset(str.substring(2, iIndexOf));
        int i = iIndexOf + 1;
        int iIndexOf2 = str.indexOf(63, i);
        if (iIndexOf2 == -1) {
            throw new ParseException("encoded word does not include encoding: " + str);
        }
        String strSubstring = str.substring(i, iIndexOf2);
        int i2 = iIndexOf2 + 1;
        int iIndexOf3 = str.indexOf("?=", i2);
        if (iIndexOf3 == -1) {
            throw new ParseException("encoded word does not end with \"?=\": " + str);
        }
        String strSubstring2 = str.substring(i2, iIndexOf3);
        try {
            if (strSubstring2.length() > 0) {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(ASCIIUtility.getBytes(strSubstring2));
                if (strSubstring.equalsIgnoreCase("B")) {
                    qDecoderStream = new BASE64DecoderStream(byteArrayInputStream);
                } else if (strSubstring.equalsIgnoreCase("Q")) {
                    qDecoderStream = new QDecoderStream(byteArrayInputStream);
                } else {
                    throw new UnsupportedEncodingException("unknown encoding: " + strSubstring);
                }
                int iAvailable = byteArrayInputStream.available();
                byte[] bArr = new byte[iAvailable];
                int i3 = qDecoderStream.read(bArr, 0, iAvailable);
                str2 = i3 <= 0 ? "" : new String(bArr, 0, i3, strJavaCharset);
            } else {
                str2 = "";
            }
            int i4 = iIndexOf3 + 2;
            if (i4 >= str.length()) {
                return str2;
            }
            String strSubstring3 = str.substring(i4);
            if (!decodeStrict) {
                strSubstring3 = decodeInnerWords(strSubstring3);
            }
            return String.valueOf(str2) + strSubstring3;
        } catch (UnsupportedEncodingException e) {
            throw e;
        } catch (IOException e2) {
            throw new ParseException(e2.toString());
        } catch (IllegalArgumentException unused) {
            throw new UnsupportedEncodingException(strJavaCharset);
        }
    }

    private static String decodeInnerWords(String str) throws ParseException, UnsupportedEncodingException {
        int iIndexOf;
        int iIndexOf2;
        StringBuffer stringBuffer = new StringBuffer();
        int i = 0;
        while (true) {
            int iIndexOf3 = str.indexOf("=?", i);
            if (iIndexOf3 < 0) {
                break;
            }
            stringBuffer.append(str.substring(i, iIndexOf3));
            int iIndexOf4 = str.indexOf(63, iIndexOf3 + 2);
            if (iIndexOf4 < 0 || (iIndexOf = str.indexOf(63, iIndexOf4 + 1)) < 0 || (iIndexOf2 = str.indexOf("?=", iIndexOf + 1)) < 0) {
                break;
            }
            i = iIndexOf2 + 2;
            String strSubstring = str.substring(iIndexOf3, i);
            try {
                strSubstring = decodeWord(strSubstring);
            } catch (ParseException unused) {
            }
            stringBuffer.append(strSubstring);
        }
        if (i == 0) {
            return str;
        }
        if (i < str.length()) {
            stringBuffer.append(str.substring(i));
        }
        return stringBuffer.toString();
    }

    public static String quote(String str, String str2) {
        int length = str.length();
        char c = 0;
        int i = 0;
        boolean z = false;
        while (i < length) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == '\\' || cCharAt == '\r' || cCharAt == '\n') {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append('\"');
                stringBuffer.append(str.substring(0, i));
                while (i < length) {
                    char cCharAt2 = str.charAt(i);
                    if ((cCharAt2 == '\"' || cCharAt2 == '\\' || cCharAt2 == '\r' || cCharAt2 == '\n') && (cCharAt2 != '\n' || c != '\r')) {
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    }
                    stringBuffer.append(cCharAt2);
                    i++;
                    c = cCharAt2;
                }
                stringBuffer.append('\"');
                return stringBuffer.toString();
            }
            if (cCharAt < ' ' || cCharAt >= 127 || str2.indexOf(cCharAt) >= 0) {
                z = true;
            }
            i++;
        }
        if (!z) {
            return str;
        }
        StringBuffer stringBuffer2 = new StringBuffer(length + 2);
        stringBuffer2.append('\"');
        stringBuffer2.append(str);
        stringBuffer2.append('\"');
        return stringBuffer2.toString();
    }

    public static String fold(int i, String str) {
        char cCharAt;
        if (!foldText) {
            return str;
        }
        int length = str.length() - 1;
        while (length >= 0 && ((cCharAt = str.charAt(length)) == ' ' || cCharAt == '\t' || cCharAt == '\r' || cCharAt == '\n')) {
            length--;
        }
        if (length != str.length() - 1) {
            str = str.substring(0, length + 1);
        }
        if (str.length() + i <= 76) {
            return str;
        }
        StringBuffer stringBuffer = new StringBuffer(str.length() + 4);
        String strSubstring = str;
        char cCharAt2 = 0;
        for (int i2 = i; strSubstring.length() + i2 > 76; i2 = 1) {
            char c = cCharAt2;
            int i3 = 0;
            int i4 = -1;
            while (i3 < strSubstring.length() && (i4 == -1 || i2 + i3 <= 76)) {
                char cCharAt3 = strSubstring.charAt(i3);
                if ((cCharAt3 == ' ' || cCharAt3 == '\t') && c != ' ' && c != '\t') {
                    i4 = i3;
                }
                i3++;
                c = cCharAt3;
            }
            if (i4 == -1) {
                stringBuffer.append(strSubstring);
                strSubstring = "";
                break;
            }
            stringBuffer.append(strSubstring.substring(0, i4));
            stringBuffer.append(IOUtils.LINE_SEPARATOR_WINDOWS);
            cCharAt2 = strSubstring.charAt(i4);
            stringBuffer.append(cCharAt2);
            strSubstring = strSubstring.substring(i4 + 1);
        }
        stringBuffer.append(strSubstring);
        return stringBuffer.toString();
    }

    public static String unfold(String str) {
        char cCharAt;
        if (!foldText) {
            return str;
        }
        StringBuffer stringBuffer = null;
        while (true) {
            int iIndexOfAny = indexOfAny(str, IOUtils.LINE_SEPARATOR_WINDOWS);
            if (iIndexOfAny < 0) {
                break;
            }
            int length = str.length();
            int i = iIndexOfAny + 1;
            if (i < length && str.charAt(i - 1) == '\r' && str.charAt(i) == '\n') {
                i++;
            }
            if (iIndexOfAny != 0) {
                int i2 = iIndexOfAny - 1;
                if (str.charAt(i2) == '\\') {
                    if (stringBuffer == null) {
                        stringBuffer = new StringBuffer(str.length());
                    }
                    stringBuffer.append(str.substring(0, i2));
                    stringBuffer.append(str.substring(iIndexOfAny, i));
                    str = str.substring(i);
                }
            }
            if (i < length && ((cCharAt = str.charAt(i)) == ' ' || cCharAt == '\t')) {
                int i3 = i + 1;
                while (i3 < length) {
                    char cCharAt2 = str.charAt(i3);
                    if (cCharAt2 != ' ' && cCharAt2 != '\t') {
                        break;
                    }
                    i3++;
                }
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length());
                }
                if (iIndexOfAny != 0) {
                    stringBuffer.append(str.substring(0, iIndexOfAny));
                    stringBuffer.append(' ');
                }
                str = str.substring(i3);
            } else {
                if (stringBuffer == null) {
                    stringBuffer = new StringBuffer(str.length());
                }
                stringBuffer.append(str.substring(0, i));
                str = str.substring(i);
            }
        }
        if (stringBuffer == null) {
            return str;
        }
        stringBuffer.append(str);
        return stringBuffer.toString();
    }

    private static int indexOfAny(String str, String str2) {
        return indexOfAny(str, str2, 0);
    }

    private static int indexOfAny(String str, String str2, int i) {
        try {
            int length = str.length();
            while (i < length) {
                if (str2.indexOf(str.charAt(i)) >= 0) {
                    return i;
                }
                i++;
            }
            return -1;
        } catch (StringIndexOutOfBoundsException unused) {
            return -1;
        }
    }

    public static String javaCharset(String str) {
        String str2;
        return (mime2java == null || str == null || (str2 = (String) mime2java.get(str.toLowerCase(Locale.ENGLISH))) == null) ? str : str2;
    }

    public static String mimeCharset(String str) {
        String str2;
        return (java2mime == null || str == null || (str2 = (String) java2mime.get(str.toLowerCase(Locale.ENGLISH))) == null) ? str : str2;
    }

    public static String getDefaultJavaCharset() {
        if (defaultJavaCharset == null) {
            String property = null;
            try {
                property = System.getProperty(EmailConstants.MAIL_MIME_CHARSET);
            } catch (SecurityException unused) {
            }
            if (property != null && property.length() > 0) {
                String strJavaCharset = javaCharset(property);
                defaultJavaCharset = strJavaCharset;
                return strJavaCharset;
            }
            try {
                defaultJavaCharset = System.getProperty("file.encoding", "8859_1");
            } catch (SecurityException unused2) {
                String encoding = new InputStreamReader(new InputStream() { // from class: javax.mail.internet.MimeUtility.1NullInputStream
                    @Override // java.io.InputStream
                    public int read() {
                        return 0;
                    }
                }).getEncoding();
                defaultJavaCharset = encoding;
                if (encoding == null) {
                    defaultJavaCharset = "8859_1";
                }
            }
        }
        return defaultJavaCharset;
    }

    static String getDefaultMIMECharset() {
        if (defaultMIMECharset == null) {
            try {
                defaultMIMECharset = System.getProperty(EmailConstants.MAIL_MIME_CHARSET);
            } catch (SecurityException unused) {
            }
        }
        if (defaultMIMECharset == null) {
            defaultMIMECharset = mimeCharset(getDefaultJavaCharset());
        }
        return defaultMIMECharset;
    }

    private static void loadMappings(LineInputStream lineInputStream, Hashtable hashtable) {
        while (true) {
            try {
                String line = lineInputStream.readLine();
                if (line == null) {
                    return;
                }
                if (line.startsWith("--") && line.endsWith("--")) {
                    return;
                }
                if (line.trim().length() != 0 && !line.startsWith("#")) {
                    StringTokenizer stringTokenizer = new StringTokenizer(line, " \t");
                    try {
                        String strNextToken = stringTokenizer.nextToken();
                        hashtable.put(strNextToken.toLowerCase(Locale.ENGLISH), stringTokenizer.nextToken());
                    } catch (NoSuchElementException unused) {
                    }
                }
            } catch (IOException unused2) {
                return;
            }
        }
    }

    static int checkAscii(String str) {
        int length = str.length();
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            if (nonascii(str.charAt(i3))) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == 0) {
            return 1;
        }
        return i2 > i ? 2 : 3;
    }

    static int checkAscii(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        for (byte b : bArr) {
            if (nonascii(b & 255)) {
                i++;
            } else {
                i2++;
            }
        }
        if (i == 0) {
            return 1;
        }
        return i2 > i ? 2 : 3;
    }

    static int checkAscii(InputStream inputStream, int i, boolean z) {
        int i2 = i;
        int i3 = 0;
        boolean z2 = encodeEolStrict && z;
        byte[] bArr = null;
        int i4 = -1;
        int iMin = 4096;
        if (i2 != 0) {
            iMin = i2 != -1 ? Math.min(i2, 4096) : 4096;
            bArr = new byte[iMin];
        }
        int i5 = 0;
        int i6 = 0;
        boolean z3 = false;
        boolean z4 = false;
        int i7 = 0;
        while (i2 != 0) {
            try {
                int i8 = inputStream.read(bArr, i3, iMin);
                if (i8 == i4) {
                    break;
                }
                int i9 = i7;
                boolean z5 = z3;
                int i10 = i6;
                int i11 = i5;
                int i12 = 0;
                while (i12 < i8) {
                    try {
                        int i13 = bArr[i12] & 255;
                        if (z2 && ((i3 == 13 && i13 != 10) || (i3 != 13 && i13 == 10))) {
                            z5 = true;
                        }
                        if (i13 == 13 || i13 == 10) {
                            i9 = 0;
                        } else {
                            int i14 = i9 + 1;
                            if (i14 > 998) {
                                i9 = i14;
                                z4 = true;
                            } else {
                                i9 = i14;
                            }
                        }
                        if (!nonascii(i13)) {
                            i11++;
                        } else {
                            if (z) {
                                return 3;
                            }
                            i10++;
                        }
                        i12++;
                        i3 = i13;
                        i4 = -1;
                    } catch (IOException unused) {
                        i5 = i11;
                        i6 = i10;
                        z3 = z5;
                    }
                }
                if (i2 != i4) {
                    i2 -= i8;
                }
                i5 = i11;
                i6 = i10;
                z3 = z5;
                i7 = i9;
                i3 = 0;
            } catch (IOException unused2) {
            }
        }
        if (i2 == 0 && z) {
            return 3;
        }
        if (i6 != 0) {
            return i5 > i6 ? 2 : 3;
        }
        if (z3) {
            return 3;
        }
        return z4 ? 2 : 1;
    }
}

package javax.mail.internet;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Locale;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.mail.Address;
import javax.mail.Session;
import org.apache.commons.mail.EmailConstants;
import org.apache.commons.p092io.IOUtils;

/* JADX INFO: loaded from: classes.dex */
public class InternetAddress extends Address implements Cloneable {
    private static final String rfc822phrase = HeaderTokenizer.RFC822.replace(' ', (char) 0).replace('\t', (char) 0);
    private static final long serialVersionUID = -7507595530758302903L;
    private static final String specialsNoDot = "()<>,;:\\\"[]@";
    private static final String specialsNoDotNoAt = "()<>,;:\\\"[]";
    protected String address;
    protected String encodedPersonal;
    protected String personal;

    @Override // javax.mail.Address
    public String getType() {
        return "rfc822";
    }

    public InternetAddress() {
    }

    public InternetAddress(String str) throws AddressException {
        InternetAddress[] internetAddressArr = parse(str, true);
        if (internetAddressArr.length != 1) {
            throw new AddressException("Illegal address", str);
        }
        this.address = internetAddressArr[0].address;
        this.personal = internetAddressArr[0].personal;
        this.encodedPersonal = internetAddressArr[0].encodedPersonal;
    }

    public InternetAddress(String str, boolean z) throws AddressException {
        this(str);
        if (z) {
            checkAddress(this.address, true, true);
        }
    }

    public InternetAddress(String str, String str2) throws UnsupportedEncodingException {
        this(str, str2, null);
    }

    public InternetAddress(String str, String str2, String str3) throws UnsupportedEncodingException {
        this.address = str;
        setPersonal(str2, str3);
    }

    public Object clone() {
        try {
            return (InternetAddress) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setPersonal(String str, String str2) throws UnsupportedEncodingException {
        this.personal = str;
        if (str != null) {
            this.encodedPersonal = MimeUtility.encodeWord(str, str2, null);
        } else {
            this.encodedPersonal = null;
        }
    }

    public void setPersonal(String str) throws UnsupportedEncodingException {
        this.personal = str;
        if (str != null) {
            this.encodedPersonal = MimeUtility.encodeWord(str);
        } else {
            this.encodedPersonal = null;
        }
    }

    public String getAddress() {
        return this.address;
    }

    public String getPersonal() {
        if (this.personal != null) {
            return this.personal;
        }
        if (this.encodedPersonal == null) {
            return null;
        }
        try {
            this.personal = MimeUtility.decodeText(this.encodedPersonal);
            return this.personal;
        } catch (Exception unused) {
            return this.encodedPersonal;
        }
    }

    @Override // javax.mail.Address
    public String toString() {
        if (this.encodedPersonal == null && this.personal != null) {
            try {
                this.encodedPersonal = MimeUtility.encodeWord(this.personal);
            } catch (UnsupportedEncodingException unused) {
            }
        }
        if (this.encodedPersonal != null) {
            return String.valueOf(quotePhrase(this.encodedPersonal)) + " <" + this.address + ">";
        }
        if (isGroup() || isSimple()) {
            return this.address;
        }
        return "<" + this.address + ">";
    }

    public String toUnicodeString() {
        String personal = getPersonal();
        if (personal != null) {
            return String.valueOf(quotePhrase(personal)) + " <" + this.address + ">";
        }
        if (isGroup() || isSimple()) {
            return this.address;
        }
        return "<" + this.address + ">";
    }

    private static String quotePhrase(String str) {
        int length = str.length();
        boolean z = false;
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\"' || cCharAt == '\\') {
                StringBuffer stringBuffer = new StringBuffer(length + 3);
                stringBuffer.append('\"');
                for (int i2 = 0; i2 < length; i2++) {
                    char cCharAt2 = str.charAt(i2);
                    if (cCharAt2 == '\"' || cCharAt2 == '\\') {
                        stringBuffer.append(IOUtils.DIR_SEPARATOR_WINDOWS);
                    }
                    stringBuffer.append(cCharAt2);
                }
                stringBuffer.append('\"');
                return stringBuffer.toString();
            }
            if ((cCharAt < ' ' && cCharAt != '\r' && cCharAt != '\n' && cCharAt != '\t') || cCharAt >= 127 || rfc822phrase.indexOf(cCharAt) >= 0) {
                z = true;
            }
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

    private static String unquote(String str) {
        if (!str.startsWith("\"") || !str.endsWith("\"")) {
            return str;
        }
        String strSubstring = str.substring(1, str.length() - 1);
        if (strSubstring.indexOf(92) < 0) {
            return strSubstring;
        }
        StringBuffer stringBuffer = new StringBuffer(strSubstring.length());
        int i = 0;
        while (i < strSubstring.length()) {
            char cCharAt = strSubstring.charAt(i);
            if (cCharAt == '\\' && i < strSubstring.length() - 1) {
                i++;
                cCharAt = strSubstring.charAt(i);
            }
            stringBuffer.append(cCharAt);
            i++;
        }
        return stringBuffer.toString();
    }

    @Override // javax.mail.Address
    public boolean equals(Object obj) {
        if (!(obj instanceof InternetAddress)) {
            return false;
        }
        String address = ((InternetAddress) obj).getAddress();
        if (address == this.address) {
            return true;
        }
        return this.address != null && this.address.equalsIgnoreCase(address);
    }

    public int hashCode() {
        if (this.address == null) {
            return 0;
        }
        return this.address.toLowerCase(Locale.ENGLISH).hashCode();
    }

    public static String toString(Address[] addressArr) {
        return toString(addressArr, 0);
    }

    public static String toString(Address[] addressArr, int i) {
        if (addressArr == null || addressArr.length == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i2 = 0; i2 < addressArr.length; i2++) {
            if (i2 != 0) {
                stringBuffer.append(", ");
                i += 2;
            }
            String string = addressArr[i2].toString();
            if (lengthOfFirstSegment(string) + i > 76) {
                stringBuffer.append("\r\n\t");
                i = 8;
            }
            stringBuffer.append(string);
            i = lengthOfLastSegment(string, i);
        }
        return stringBuffer.toString();
    }

    private static int lengthOfFirstSegment(String str) {
        int iIndexOf = str.indexOf(IOUtils.LINE_SEPARATOR_WINDOWS);
        return iIndexOf != -1 ? iIndexOf : str.length();
    }

    private static int lengthOfLastSegment(String str, int i) {
        int iLastIndexOf = str.lastIndexOf(IOUtils.LINE_SEPARATOR_WINDOWS);
        if (iLastIndexOf != -1) {
            return (str.length() - iLastIndexOf) - 2;
        }
        return str.length() + i;
    }

    public static InternetAddress getLocalAddress(Session session) {
        String property;
        String property2;
        String property3;
        InetAddress localHost;
        try {
            if (session == null) {
                property3 = System.getProperty("user.name");
                property2 = InetAddress.getLocalHost().getHostName();
                property = null;
            } else {
                property = session.getProperty(EmailConstants.MAIL_FROM);
                if (property == null) {
                    property3 = session.getProperty("mail.user");
                    if (property3 == null || property3.length() == 0) {
                        property3 = session.getProperty("user.name");
                    }
                    if (property3 == null || property3.length() == 0) {
                        property3 = System.getProperty("user.name");
                    }
                    property2 = session.getProperty("mail.host");
                    if ((property2 == null || property2.length() == 0) && (localHost = InetAddress.getLocalHost()) != null) {
                        property2 = localHost.getHostName();
                    }
                } else {
                    property2 = null;
                    property3 = null;
                }
            }
            if (property == null && property3 != null && property3.length() != 0 && property2 != null && property2.length() != 0) {
                property = String.valueOf(property3) + "@" + property2;
            }
            if (property != null) {
                return new InternetAddress(property);
            }
        } catch (SecurityException | UnknownHostException | AddressException unused) {
        }
        return null;
    }

    public static InternetAddress[] parse(String str) throws AddressException {
        return parse(str, true);
    }

    public static InternetAddress[] parse(String str, boolean z) throws AddressException {
        return parse(str, z, false);
    }

    public static InternetAddress[] parseHeader(String str, boolean z) throws AddressException {
        return parse(str, z, true);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:162:0x0213  */
    /* JADX WARN: Code duplicated, block: B:175:0x020b A[SYNTHETIC] */
    private static InternetAddress[] parse(String str, boolean z, boolean z2) throws AddressException {
        int i;
        int i2;
        int i3;
        int length = str.length();
        Vector vector = new Vector();
        boolean z3 = false;
        int i4 = -1;
        int i5 = 0;
        int i6 = -1;
        int i7 = -1;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        int i8 = -1;
        int i9 = -1;
        while (i5 < length) {
            switch (str.charAt(i5)) {
                case '\t':
                case '\n':
                case '\r':
                case ' ':
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                case '\"':
                    if (i6 == -1) {
                        i6 = i5;
                    }
                    i5++;
                    while (i5 < length) {
                        char cCharAt = str.charAt(i5);
                        if (cCharAt != '\"') {
                            if (cCharAt == '\\') {
                                i5++;
                            }
                            i5++;
                        } else {
                            if (i5 >= length) {
                                throw new AddressException("Missing '\"'", str, i5);
                            }
                            z6 = true;
                            i5++;
                            z3 = false;
                            i4 = -1;
                        }
                        break;
                    }
                    if (i5 >= length) {
                        throw new AddressException("Missing '\"'", str, i5);
                    }
                    z6 = true;
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                case '(':
                    if (i6 >= 0) {
                        i = -1;
                        if (i7 == -1) {
                            i7 = i5;
                        }
                    } else {
                        i = -1;
                    }
                    if (i8 == i) {
                        i8 = i5 + 1;
                    }
                    int i10 = i5 + 1;
                    int i11 = 1;
                    while (i10 < length && i11 > 0) {
                        char cCharAt2 = str.charAt(i10);
                        if (cCharAt2 != '\\') {
                            switch (cCharAt2) {
                                case '(':
                                    i11++;
                                    break;
                                case ')':
                                    i11--;
                                    break;
                            }
                        } else {
                            i10++;
                        }
                        i10++;
                    }
                    if (i11 > 0) {
                        throw new AddressException("Missing ')'", str, i10);
                    }
                    i5 = i10 - 1;
                    if (i9 == -1) {
                        i9 = i5;
                    }
                    z6 = true;
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                case ')':
                    throw new AddressException("Missing '('", str, i5);
                case ',':
                    if (i6 == -1) {
                        i6 = -1;
                        i7 = -1;
                        z5 = false;
                        z6 = false;
                    } else if (z4) {
                        z5 = false;
                    } else {
                        if (i7 == -1) {
                            i7 = i5;
                        }
                        String strTrim = str.substring(i6, i7).trim();
                        if (z6 || z || z2) {
                            if (z || !z2) {
                                checkAddress(strTrim, z5, false);
                            }
                            InternetAddress internetAddress = new InternetAddress();
                            internetAddress.setAddress(strTrim);
                            if (i8 >= 0) {
                                internetAddress.encodedPersonal = unquote(str.substring(i8, i9).trim());
                                i8 = -1;
                                i9 = -1;
                            }
                            vector.addElement(internetAddress);
                        } else {
                            StringTokenizer stringTokenizer = new StringTokenizer(strTrim);
                            while (stringTokenizer.hasMoreTokens()) {
                                String strNextToken = stringTokenizer.nextToken();
                                checkAddress(strNextToken, false, false);
                                InternetAddress internetAddress2 = new InternetAddress();
                                internetAddress2.setAddress(strNextToken);
                                vector.addElement(internetAddress2);
                            }
                        }
                        i6 = -1;
                        i7 = -1;
                        z5 = false;
                        z6 = false;
                    }
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                case ':':
                    if (z4) {
                        throw new AddressException("Nested group", str, i5);
                    }
                    if (i6 == -1) {
                        i6 = i5;
                    }
                    z4 = true;
                    z6 = true;
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                case ';':
                    if (i6 == -1) {
                        i6 = i5;
                    }
                    if (!z4) {
                        throw new AddressException("Illegal semicolon, not in group", str, i5);
                    }
                    if (i6 == -1) {
                        i6 = i5;
                    }
                    InternetAddress internetAddress3 = new InternetAddress();
                    internetAddress3.setAddress(str.substring(i6, i5 + 1).trim());
                    vector.addElement(internetAddress3);
                    i6 = -1;
                    i7 = -1;
                    z4 = false;
                    z5 = false;
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                    break;
                case '<':
                    if (z5) {
                        throw new AddressException("Extra route-addr", str, i5);
                    }
                    if (z4) {
                        i2 = i6;
                        i3 = i8;
                    } else {
                        if (i6 >= 0) {
                            i9 = i5;
                        }
                        i2 = i5 + 1;
                        i3 = i6;
                    }
                    int i12 = i9;
                    boolean z7 = false;
                    while (true) {
                        i5++;
                        if (i5 < length) {
                            char cCharAt3 = str.charAt(i5);
                            if (cCharAt3 == '\"') {
                                z7 = !z7;
                            } else if (cCharAt3 != '>') {
                                if (cCharAt3 == '\\') {
                                    i5++;
                                }
                            } else if (z7) {
                            }
                        }
                    }
                    if (i5 >= length) {
                        if (z7) {
                            throw new AddressException("Missing '\"'", str, i5);
                        }
                        throw new AddressException("Missing '>'", str, i5);
                    }
                    i6 = i2;
                    i8 = i3;
                    i9 = i12;
                    z5 = true;
                    z6 = true;
                    i7 = i5;
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                case '>':
                    throw new AddressException("Missing '<'", str, i5);
                case '[':
                    while (true) {
                        i5++;
                        if (i5 < length) {
                            switch (str.charAt(i5)) {
                                case '\\':
                                    i5++;
                                    break;
                                case ']':
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    if (i5 >= length) {
                        throw new AddressException("Missing ']'", str, i5);
                    }
                    z6 = true;
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
                default:
                    if (i6 == -1) {
                        i6 = i5;
                    }
                    i5++;
                    z3 = false;
                    i4 = -1;
                    break;
            }
        }
        if (i6 >= 0) {
            if (i7 != i4) {
                i5 = i7;
            }
            String strTrim2 = str.substring(i6, i5).trim();
            if (z6 || z || z2) {
                if (z || !z2) {
                    checkAddress(strTrim2, z5, z3);
                }
                InternetAddress internetAddress4 = new InternetAddress();
                internetAddress4.setAddress(strTrim2);
                if (i8 >= 0) {
                    internetAddress4.encodedPersonal = unquote(str.substring(i8, i9).trim());
                }
                vector.addElement(internetAddress4);
            } else {
                StringTokenizer stringTokenizer2 = new StringTokenizer(strTrim2);
                while (stringTokenizer2.hasMoreTokens()) {
                    String strNextToken2 = stringTokenizer2.nextToken();
                    checkAddress(strNextToken2, z3, z3);
                    InternetAddress internetAddress5 = new InternetAddress();
                    internetAddress5.setAddress(strNextToken2);
                    vector.addElement(internetAddress5);
                }
            }
        }
        InternetAddress[] internetAddressArr = new InternetAddress[vector.size()];
        vector.copyInto(internetAddressArr);
        return internetAddressArr;
    }

    public void validate() throws AddressException {
        checkAddress(getAddress(), true, true);
    }

    private static void checkAddress(String str, boolean z, boolean z2) throws AddressException {
        String strSubstring;
        String strSubstring2;
        if (str.indexOf(34) >= 0) {
            return;
        }
        int i = 0;
        if (z) {
            while (true) {
                int iIndexOfAny = indexOfAny(str, ",:", i);
                if (iIndexOfAny < 0) {
                    break;
                }
                if (str.charAt(i) != '@') {
                    throw new AddressException("Illegal route-addr", str);
                }
                if (str.charAt(iIndexOfAny) == ':') {
                    i = iIndexOfAny + 1;
                    break;
                }
                i = iIndexOfAny + 1;
            }
        }
        int iIndexOf = str.indexOf(64, i);
        if (iIndexOf >= 0) {
            if (iIndexOf == i) {
                throw new AddressException("Missing local name", str);
            }
            if (iIndexOf == str.length() - 1) {
                throw new AddressException("Missing domain", str);
            }
            strSubstring2 = str.substring(i, iIndexOf);
            strSubstring = str.substring(iIndexOf + 1);
        } else {
            if (z2) {
                throw new AddressException("Missing final '@domain'", str);
            }
            strSubstring = null;
            strSubstring2 = str;
        }
        if (indexOfAny(str, " \t\n\r") >= 0) {
            throw new AddressException("Illegal whitespace in address", str);
        }
        if (indexOfAny(strSubstring2, specialsNoDot) >= 0) {
            throw new AddressException("Illegal character in local name", str);
        }
        if (strSubstring != null && strSubstring.indexOf(91) < 0 && indexOfAny(strSubstring, specialsNoDot) >= 0) {
            throw new AddressException("Illegal character in domain", str);
        }
    }

    private boolean isSimple() {
        return this.address == null || indexOfAny(this.address, specialsNoDotNoAt) < 0;
    }

    public boolean isGroup() {
        return this.address != null && this.address.endsWith(";") && this.address.indexOf(58) > 0;
    }

    public InternetAddress[] getGroup(boolean z) throws AddressException {
        int iIndexOf;
        String address = getAddress();
        if (address.endsWith(";") && (iIndexOf = address.indexOf(58)) >= 0) {
            return parseHeader(address.substring(iIndexOf + 1, address.length() - 1), z);
        }
        return null;
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
}

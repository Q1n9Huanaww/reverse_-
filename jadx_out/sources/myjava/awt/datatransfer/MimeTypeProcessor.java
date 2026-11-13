package myjava.awt.datatransfer;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;
import net.lingala.zip4j.util.InternalZipConstants;

/* JADX INFO: loaded from: classes.dex */
final class MimeTypeProcessor {
    private static MimeTypeProcessor instance;

    private static boolean isMeaningfulChar(char c) {
        return c >= '!' && c <= '~';
    }

    private static boolean isTSpecialChar(char c) {
        return c == '(' || c == ')' || c == '[' || c == ']' || c == '<' || c == '>' || c == '@' || c == ',' || c == ';' || c == ':' || c == '\\' || c == '\"' || c == '/' || c == '?' || c == '=';
    }

    private MimeTypeProcessor() {
    }

    static MimeType parse(String str) {
        if (instance == null) {
            instance = new MimeTypeProcessor();
        }
        MimeType mimeType = new MimeType();
        if (str != null) {
            StringPosition stringPosition = new StringPosition(null);
            retrieveType(str, mimeType, stringPosition);
            retrieveParams(str, mimeType, stringPosition);
        }
        return mimeType;
    }

    static String assemble(MimeType mimeType) {
        StringBuilder sb = new StringBuilder();
        sb.append(mimeType.getFullType());
        Enumeration enumerationKeys = mimeType.parameters.keys();
        while (enumerationKeys.hasMoreElements()) {
            String str = (String) enumerationKeys.nextElement();
            String str2 = (String) mimeType.parameters.get(str);
            sb.append("; ");
            sb.append(str);
            sb.append("=\"");
            sb.append(str2);
            sb.append('\"');
        }
        return sb.toString();
    }

    private static void retrieveType(String str, MimeType mimeType, StringPosition stringPosition) {
        mimeType.primaryType = retrieveToken(str, stringPosition).toLowerCase();
        stringPosition.f5033i = getNextMeaningfulIndex(str, stringPosition.f5033i);
        if (stringPosition.f5033i >= str.length() || str.charAt(stringPosition.f5033i) != '/') {
            throw new IllegalArgumentException();
        }
        stringPosition.f5033i++;
        mimeType.subType = retrieveToken(str, stringPosition).toLowerCase();
    }

    private static void retrieveParams(String str, MimeType mimeType, StringPosition stringPosition) {
        mimeType.parameters = new Hashtable();
        mimeType.systemParameters = new Hashtable();
        while (true) {
            stringPosition.f5033i = getNextMeaningfulIndex(str, stringPosition.f5033i);
            if (stringPosition.f5033i >= str.length()) {
                return;
            }
            if (str.charAt(stringPosition.f5033i) != ';') {
                throw new IllegalArgumentException();
            }
            stringPosition.f5033i++;
            retrieveParam(str, mimeType, stringPosition);
        }
    }

    private static void retrieveParam(String str, MimeType mimeType, StringPosition stringPosition) {
        String strRetrieveToken;
        String lowerCase = retrieveToken(str, stringPosition).toLowerCase();
        stringPosition.f5033i = getNextMeaningfulIndex(str, stringPosition.f5033i);
        if (stringPosition.f5033i >= str.length() || str.charAt(stringPosition.f5033i) != '=') {
            throw new IllegalArgumentException();
        }
        stringPosition.f5033i++;
        stringPosition.f5033i = getNextMeaningfulIndex(str, stringPosition.f5033i);
        if (stringPosition.f5033i >= str.length()) {
            throw new IllegalArgumentException();
        }
        if (str.charAt(stringPosition.f5033i) == '\"') {
            strRetrieveToken = retrieveQuoted(str, stringPosition);
        } else {
            strRetrieveToken = retrieveToken(str, stringPosition);
        }
        mimeType.parameters.put(lowerCase, strRetrieveToken);
    }

    private static String retrieveQuoted(String str, StringPosition stringPosition) {
        StringBuilder sb = new StringBuilder();
        stringPosition.f5033i++;
        boolean z = true;
        do {
            if (str.charAt(stringPosition.f5033i) != '\"' || !z) {
                int i = stringPosition.f5033i;
                stringPosition.f5033i = i + 1;
                char cCharAt = str.charAt(i);
                if (!z) {
                    z = true;
                } else if (cCharAt == '\\') {
                    z = false;
                }
                if (z) {
                    sb.append(cCharAt);
                }
            } else {
                stringPosition.f5033i++;
                return sb.toString();
            }
        } while (stringPosition.f5033i != str.length());
        throw new IllegalArgumentException();
    }

    private static String retrieveToken(String str, StringPosition stringPosition) {
        StringBuilder sb = new StringBuilder();
        stringPosition.f5033i = getNextMeaningfulIndex(str, stringPosition.f5033i);
        if (stringPosition.f5033i >= str.length() || isTSpecialChar(str.charAt(stringPosition.f5033i))) {
            throw new IllegalArgumentException();
        }
        do {
            int i = stringPosition.f5033i;
            stringPosition.f5033i = i + 1;
            sb.append(str.charAt(i));
            if (stringPosition.f5033i >= str.length() || !isMeaningfulChar(str.charAt(stringPosition.f5033i))) {
                break;
            }
        } while (!isTSpecialChar(str.charAt(stringPosition.f5033i)));
        return sb.toString();
    }

    private static int getNextMeaningfulIndex(String str, int i) {
        while (i < str.length() && !isMeaningfulChar(str.charAt(i))) {
            i++;
        }
        return i;
    }

    private static final class StringPosition {

        /* JADX INFO: renamed from: i */
        int f5033i;

        private StringPosition() {
            this.f5033i = 0;
        }

        /* synthetic */ StringPosition(StringPosition stringPosition) {
            this();
        }
    }

    static final class MimeType implements Serializable, Cloneable {
        private static final long serialVersionUID = -6693571907475992044L;
        private Hashtable<String, String> parameters;
        private String primaryType;
        private String subType;
        private Hashtable<String, Object> systemParameters;

        MimeType() {
            this.primaryType = null;
            this.subType = null;
            this.parameters = null;
            this.systemParameters = null;
        }

        MimeType(String str, String str2) {
            this.primaryType = str;
            this.subType = str2;
            this.parameters = new Hashtable<>();
            this.systemParameters = new Hashtable<>();
        }

        final boolean equals(MimeType mimeType) {
            if (mimeType == null) {
                return false;
            }
            return getFullType().equals(mimeType.getFullType());
        }

        final String getPrimaryType() {
            return this.primaryType;
        }

        final String getSubType() {
            return this.subType;
        }

        final String getFullType() {
            return String.valueOf(this.primaryType) + InternalZipConstants.ZIP_FILE_SEPARATOR + this.subType;
        }

        final String getParameter(String str) {
            return this.parameters.get(str);
        }

        final void addParameter(String str, String str2) {
            if (str2 == null) {
                return;
            }
            if (str2.charAt(0) == '\"' && str2.charAt(str2.length() - 1) == '\"') {
                str2 = str2.substring(1, str2.length() - 2);
            }
            if (str2.length() == 0) {
                return;
            }
            this.parameters.put(str, str2);
        }

        final void removeParameter(String str) {
            this.parameters.remove(str);
        }

        final Object getSystemParameter(String str) {
            return this.systemParameters.get(str);
        }

        final void addSystemParameter(String str, Object obj) {
            this.systemParameters.put(str, obj);
        }

        public final Object clone() {
            MimeType mimeType = new MimeType(this.primaryType, this.subType);
            mimeType.parameters = (Hashtable) this.parameters.clone();
            mimeType.systemParameters = (Hashtable) this.systemParameters.clone();
            return mimeType;
        }
    }
}

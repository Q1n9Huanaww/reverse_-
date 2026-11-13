package javax.mail.internet;

/* JADX INFO: loaded from: classes.dex */
public class HeaderTokenizer {
    private static final Token EOFToken = new Token(-4, null);
    public static final String MIME = "()<>@,;:\\\"\t []/?=";
    public static final String RFC822 = "()<>@,;:\\\"\t .[]";
    private int currentPos;
    private String delimiters;
    private int maxPos;
    private int nextPos;
    private int peekPos;
    private boolean skipComments;
    private String string;

    public static class Token {
        public static final int ATOM = -1;
        public static final int COMMENT = -3;
        public static final int EOF = -4;
        public static final int QUOTEDSTRING = -2;
        private int type;
        private String value;

        public Token(int i, String str) {
            this.type = i;
            this.value = str;
        }

        public int getType() {
            return this.type;
        }

        public String getValue() {
            return this.value;
        }
    }

    public HeaderTokenizer(String str, String str2, boolean z) {
        this.string = str == null ? "" : str;
        this.skipComments = z;
        this.delimiters = str2;
        this.peekPos = 0;
        this.nextPos = 0;
        this.currentPos = 0;
        this.maxPos = this.string.length();
    }

    public HeaderTokenizer(String str, String str2) {
        this(str, str2, true);
    }

    public HeaderTokenizer(String str) {
        this(str, RFC822);
    }

    public Token next() throws ParseException {
        this.currentPos = this.nextPos;
        Token next = getNext();
        int i = this.currentPos;
        this.peekPos = i;
        this.nextPos = i;
        return next;
    }

    public Token peek() throws ParseException {
        this.currentPos = this.peekPos;
        Token next = getNext();
        this.peekPos = this.currentPos;
        return next;
    }

    public String getRemainder() {
        return this.string.substring(this.nextPos);
    }

    private Token getNext() throws ParseException {
        char cCharAt;
        String strSubstring;
        String strSubstring2;
        if (this.currentPos >= this.maxPos) {
            return EOFToken;
        }
        if (skipWhiteSpace() == -4) {
            return EOFToken;
        }
        boolean z = false;
        while (true) {
            char cCharAt2 = this.string.charAt(this.currentPos);
            if (cCharAt2 != '(') {
                if (cCharAt2 == '\"') {
                    int i = this.currentPos + 1;
                    this.currentPos = i;
                    while (this.currentPos < this.maxPos) {
                        char cCharAt3 = this.string.charAt(this.currentPos);
                        if (cCharAt3 != '\\') {
                            if (cCharAt3 != '\r') {
                                if (cCharAt3 == '\"') {
                                    this.currentPos++;
                                    if (z) {
                                        strSubstring = filterToken(this.string, i, this.currentPos - 1);
                                    } else {
                                        strSubstring = this.string.substring(i, this.currentPos - 1);
                                    }
                                    return new Token(-2, strSubstring);
                                }
                            }
                            this.currentPos++;
                        } else {
                            this.currentPos++;
                        }
                        z = true;
                        this.currentPos++;
                    }
                    throw new ParseException("Unbalanced quoted string");
                }
                if (cCharAt2 < ' ' || cCharAt2 >= 127 || this.delimiters.indexOf(cCharAt2) >= 0) {
                    this.currentPos++;
                    return new Token(cCharAt2, new String(new char[]{cCharAt2}));
                }
                int i2 = this.currentPos;
                while (this.currentPos < this.maxPos && (cCharAt = this.string.charAt(this.currentPos)) >= ' ' && cCharAt < 127 && cCharAt != '(' && cCharAt != ' ' && cCharAt != '\"' && this.delimiters.indexOf(cCharAt) < 0) {
                    this.currentPos++;
                }
                return new Token(-1, this.string.substring(i2, this.currentPos));
            }
            int i3 = this.currentPos + 1;
            this.currentPos = i3;
            boolean z2 = z;
            int i4 = 1;
            while (i4 > 0 && this.currentPos < this.maxPos) {
                char cCharAt4 = this.string.charAt(this.currentPos);
                if (cCharAt4 != '\\') {
                    if (cCharAt4 != '\r') {
                        if (cCharAt4 == '(') {
                            i4++;
                        } else if (cCharAt4 == ')') {
                            i4--;
                        }
                    }
                    this.currentPos++;
                } else {
                    this.currentPos++;
                }
                z2 = true;
                this.currentPos++;
            }
            if (i4 != 0) {
                throw new ParseException("Unbalanced comments");
            }
            if (!this.skipComments) {
                if (z2) {
                    strSubstring2 = filterToken(this.string, i3, this.currentPos - 1);
                } else {
                    strSubstring2 = this.string.substring(i3, this.currentPos - 1);
                }
                return new Token(-3, strSubstring2);
            }
            if (skipWhiteSpace() == -4) {
                return EOFToken;
            }
            z = z2;
        }
    }

    private int skipWhiteSpace() {
        while (this.currentPos < this.maxPos) {
            char cCharAt = this.string.charAt(this.currentPos);
            if (cCharAt == ' ' || cCharAt == '\t' || cCharAt == '\r' || cCharAt == '\n') {
                this.currentPos++;
            } else {
                return this.currentPos;
            }
        }
        return -4;
    }

    private static String filterToken(String str, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        boolean z = false;
        boolean z2 = false;
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt == '\n' && z) {
                z = false;
            } else if (z2) {
                stringBuffer.append(cCharAt);
                z = false;
                z2 = false;
            } else if (cCharAt == '\\') {
                z = false;
                z2 = true;
            } else if (cCharAt == '\r') {
                z = true;
            } else {
                stringBuffer.append(cCharAt);
                z = false;
            }
            i++;
        }
        return stringBuffer.toString();
    }
}

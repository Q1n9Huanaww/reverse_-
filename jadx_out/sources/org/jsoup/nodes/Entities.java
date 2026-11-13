package org.jsoup.nodes;

import java.io.IOException;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.HashMap;
import org.jsoup.SerializationException;
import org.jsoup.helper.Validate;
import org.jsoup.internal.StringUtil;
import org.jsoup.parser.CharacterReader;
import org.jsoup.parser.Parser;

/* JADX INFO: loaded from: classes2.dex */
public class Entities {
    static final int codepointRadix = 36;
    private static final int empty = -1;
    private static final String emptyName = "";
    private static final char[] codeDelims = {',', ';'};
    private static final HashMap<String, String> multipoints = new HashMap<>();
    private static final Document.OutputSettings DefaultOutput = new Document.OutputSettings();

    public enum EscapeMode {
        xhtml(EntitiesData.xmlPoints, 4),
        base(EntitiesData.basePoints, 106),
        extended(EntitiesData.fullPoints, 2125);

        private int[] codeKeys;
        private int[] codeVals;
        private String[] nameKeys;
        private String[] nameVals;

        EscapeMode(String file, int size) {
            Entities.load(this, file, size);
        }

        int codepointForName(String name) {
            int index = Arrays.binarySearch(this.nameKeys, name);
            if (index >= 0) {
                return this.codeVals[index];
            }
            return -1;
        }

        String nameForCodepoint(int codepoint) {
            int index = Arrays.binarySearch(this.codeKeys, codepoint);
            if (index >= 0) {
                return (index >= this.nameVals.length + (-1) || this.codeKeys[index + 1] != codepoint) ? this.nameVals[index] : this.nameVals[index + 1];
            }
            return "";
        }

        private int size() {
            return this.nameKeys.length;
        }
    }

    private Entities() {
    }

    public static boolean isNamedEntity(String name) {
        return EscapeMode.extended.codepointForName(name) != -1;
    }

    public static boolean isBaseNamedEntity(String name) {
        return EscapeMode.base.codepointForName(name) != -1;
    }

    public static String getByName(String name) {
        String val = multipoints.get(name);
        if (val == null) {
            int codepoint = EscapeMode.extended.codepointForName(name);
            if (codepoint != -1) {
                return new String(new int[]{codepoint}, 0, 1);
            }
            return "";
        }
        return val;
    }

    public static int codepointsForName(String name, int[] codepoints) {
        String val = multipoints.get(name);
        if (val != null) {
            codepoints[0] = val.codePointAt(0);
            codepoints[1] = val.codePointAt(1);
            return 2;
        }
        int codepoint = EscapeMode.extended.codepointForName(name);
        if (codepoint == -1) {
            return 0;
        }
        codepoints[0] = codepoint;
        return 1;
    }

    public static String escape(String string, Document.OutputSettings out) {
        if (string == null) {
            return "";
        }
        StringBuilder accum = StringUtil.borrowBuilder();
        try {
            escape(accum, string, out, false, false, false);
            return StringUtil.releaseBuilder(accum);
        } catch (IOException e) {
            throw new SerializationException(e);
        }
    }

    public static String escape(String string) {
        return escape(string, DefaultOutput);
    }

    /* JADX WARN: Code duplicated, block: B:16:0x0038  */
    /* JADX WARN: Code duplicated, block: B:18:0x003c  */
    /* JADX WARN: Code duplicated, block: B:22:0x0046  */
    /* JADX WARN: Code duplicated, block: B:23:0x004a  */
    /* JADX WARN: Code duplicated, block: B:24:0x0050  */
    /* JADX WARN: Code duplicated, block: B:26:0x0054  */
    /* JADX WARN: Code duplicated, block: B:27:0x005a  */
    /* JADX WARN: Code duplicated, block: B:28:0x0060  */
    /* JADX WARN: Code duplicated, block: B:33:0x006e  */
    /* JADX WARN: Code duplicated, block: B:35:0x0078 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x007a  */
    /* JADX WARN: Code duplicated, block: B:37:0x0080  */
    /* JADX WARN: Code duplicated, block: B:38:0x0084 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:39:0x0086  */
    /* JADX WARN: Code duplicated, block: B:40:0x008c  */
    /* JADX WARN: Code duplicated, block: B:41:0x0090  */
    /* JADX WARN: Code duplicated, block: B:43:0x0098  */
    /* JADX WARN: Code duplicated, block: B:45:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:46:0x00ac  */
    static void escape(Appendable accum, String string, Document.OutputSettings out, boolean inAttribute, boolean normaliseWhite, boolean stripLeadingWhite) throws IOException {
        String c;
        char c2;
        boolean lastWasWhite = false;
        boolean reachedNonWhite = false;
        EscapeMode escapeMode = out.escapeMode();
        CharsetEncoder encoder = out.encoder();
        CoreCharset coreCharset = out.coreCharset;
        int length = string.length();
        int offset = 0;
        while (offset < length) {
            int codePoint = string.codePointAt(offset);
            if (normaliseWhite) {
                if (StringUtil.isWhitespace(codePoint)) {
                    if ((!stripLeadingWhite || reachedNonWhite) && !lastWasWhite) {
                        accum.append(' ');
                        lastWasWhite = true;
                    }
                } else {
                    lastWasWhite = false;
                    reachedNonWhite = true;
                    if (codePoint < 65536) {
                        c2 = (char) codePoint;
                        switch (c2) {
                            case '\t':
                            case '\n':
                            case '\r':
                                accum.append(c2);
                                break;
                            case '\"':
                                if (inAttribute) {
                                    accum.append("&quot;");
                                } else {
                                    accum.append(c2);
                                }
                                break;
                            case '&':
                                accum.append("&amp;");
                                break;
                            case '<':
                                if (inAttribute) {
                                    accum.append("&lt;");
                                } else {
                                    accum.append("&lt;");
                                }
                                break;
                            case '>':
                                if (!inAttribute) {
                                    accum.append("&gt;");
                                } else {
                                    accum.append(c2);
                                }
                                break;
                            case 160:
                                if (escapeMode != EscapeMode.xhtml) {
                                    accum.append("&nbsp;");
                                } else {
                                    accum.append("&#xa0;");
                                }
                                break;
                            default:
                                if (c2 >= ' ') {
                                    appendEncoded(accum, escapeMode, codePoint);
                                } else {
                                    appendEncoded(accum, escapeMode, codePoint);
                                }
                                break;
                        }
                    } else {
                        c = new String(Character.toChars(codePoint));
                        if (encoder.canEncode(c)) {
                            accum.append(c);
                        } else {
                            appendEncoded(accum, escapeMode, codePoint);
                        }
                    }
                }
            } else if (codePoint < 65536) {
                c2 = (char) codePoint;
                switch (c2) {
                    case '\t':
                    case '\n':
                    case '\r':
                        accum.append(c2);
                        break;
                    case '\"':
                        if (inAttribute) {
                            accum.append("&quot;");
                        } else {
                            accum.append(c2);
                        }
                        break;
                    case '&':
                        accum.append("&amp;");
                        break;
                    case '<':
                        if (inAttribute || escapeMode == EscapeMode.xhtml || out.syntax() == Document.OutputSettings.Syntax.xml) {
                            accum.append("&lt;");
                        } else {
                            accum.append(c2);
                        }
                        break;
                    case '>':
                        if (!inAttribute) {
                            accum.append("&gt;");
                        } else {
                            accum.append(c2);
                        }
                        break;
                    case 160:
                        if (escapeMode != EscapeMode.xhtml) {
                            accum.append("&nbsp;");
                        } else {
                            accum.append("&#xa0;");
                        }
                        break;
                    default:
                        if (c2 >= ' ' || !canEncode(coreCharset, c2, encoder)) {
                            appendEncoded(accum, escapeMode, codePoint);
                        } else {
                            accum.append(c2);
                        }
                        break;
                }
            } else {
                c = new String(Character.toChars(codePoint));
                if (encoder.canEncode(c)) {
                    accum.append(c);
                } else {
                    appendEncoded(accum, escapeMode, codePoint);
                }
            }
            offset += Character.charCount(codePoint);
        }
    }

    private static void appendEncoded(Appendable accum, EscapeMode escapeMode, int codePoint) throws IOException {
        String name = escapeMode.nameForCodepoint(codePoint);
        if (!"".equals(name)) {
            accum.append('&').append(name).append(';');
        } else {
            accum.append("&#x").append(Integer.toHexString(codePoint)).append(';');
        }
    }

    public static String unescape(String string) {
        return unescape(string, false);
    }

    static String unescape(String string, boolean strict) {
        return Parser.unescapeEntities(string, strict);
    }

    private static boolean canEncode(CoreCharset charset, char c, CharsetEncoder fallback) {
        switch (charset) {
            case ascii:
                return c < 128;
            case utf:
                return true;
            default:
                return fallback.canEncode(c);
        }
    }

    enum CoreCharset {
        ascii,
        utf,
        fallback;

        static CoreCharset byName(String name) {
            if (name.equals("US-ASCII")) {
                return ascii;
            }
            if (name.startsWith("UTF-")) {
                return utf;
            }
            return fallback;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void load(EscapeMode e, String pointsData, int size) {
        int cp2;
        e.nameKeys = new String[size];
        e.codeVals = new int[size];
        e.codeKeys = new int[size];
        e.nameVals = new String[size];
        int i = 0;
        CharacterReader reader = new CharacterReader(pointsData);
        while (!reader.isEmpty()) {
            try {
                String name = reader.consumeTo('=');
                reader.advance();
                int cp1 = Integer.parseInt(reader.consumeToAny(codeDelims), 36);
                char codeDelim = reader.current();
                reader.advance();
                if (codeDelim == ',') {
                    cp2 = Integer.parseInt(reader.consumeTo(';'), 36);
                    reader.advance();
                } else {
                    cp2 = -1;
                }
                String indexS = reader.consumeTo('&');
                int index = Integer.parseInt(indexS, 36);
                reader.advance();
                e.nameKeys[i] = name;
                e.codeVals[i] = cp1;
                e.codeKeys[index] = cp1;
                e.nameVals[index] = name;
                if (cp2 != -1) {
                    multipoints.put(name, new String(new int[]{cp1, cp2}, 0, 2));
                }
                i++;
            } catch (Throwable th) {
                reader.close();
                throw th;
            }
        }
        Validate.isTrue(i == size, "Unexpected count of entities loaded");
        reader.close();
    }
}

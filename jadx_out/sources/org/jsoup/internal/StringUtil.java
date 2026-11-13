package org.jsoup.internal;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import net.lingala.zip4j.util.InternalZipConstants;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes2.dex */
public final class StringUtil {
    private static final int MaxCachedBuilderSize = 8192;
    private static final int MaxIdleBuilders = 8;
    private static final int maxPaddingWidth = 30;
    static final String[] padding = {"", " ", "  ", "   ", "    ", "     ", "      ", "       ", "        ", "         ", "          ", "           ", "            ", "             ", "              ", "               ", "                ", "                 ", "                  ", "                   ", "                    "};
    private static final Pattern extraDotSegmentsPattern = Pattern.compile("^/((\\.{1,2}/)+)");
    private static final Pattern validUriScheme = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+-.]*:");
    private static final ThreadLocal<Stack<StringBuilder>> threadLocalBuilders = new ThreadLocal<Stack<StringBuilder>>() { // from class: org.jsoup.internal.StringUtil.1
        /* JADX INFO: Access modifiers changed from: protected */
        @Override // java.lang.ThreadLocal
        public Stack<StringBuilder> initialValue() {
            return new Stack<>();
        }
    };

    public static String join(Collection<?> strings, String sep) {
        return join(strings.iterator(), sep);
    }

    public static String join(Iterator<?> strings, String sep) {
        if (!strings.hasNext()) {
            return "";
        }
        String start = strings.next().toString();
        if (strings.hasNext()) {
            StringJoiner j = new StringJoiner(sep);
            j.add(start);
            while (strings.hasNext()) {
                j.add(strings.next());
            }
            return j.complete();
        }
        return start;
    }

    public static String join(String[] strings, String sep) {
        return join(Arrays.asList(strings), sep);
    }

    public static class StringJoiner {
        final String separator;

        /* JADX INFO: renamed from: sb */
        @Nullable
        StringBuilder f5068sb = StringUtil.borrowBuilder();
        boolean first = true;

        public StringJoiner(String separator) {
            this.separator = separator;
        }

        public StringJoiner add(Object stringy) {
            Validate.notNull(this.f5068sb);
            if (!this.first) {
                this.f5068sb.append(this.separator);
            }
            this.f5068sb.append(stringy);
            this.first = false;
            return this;
        }

        public StringJoiner append(Object stringy) {
            Validate.notNull(this.f5068sb);
            this.f5068sb.append(stringy);
            return this;
        }

        public String complete() {
            String string = StringUtil.releaseBuilder(this.f5068sb);
            this.f5068sb = null;
            return string;
        }
    }

    public static String padding(int width) {
        if (width < 0) {
            throw new IllegalArgumentException("width must be > 0");
        }
        if (width < padding.length) {
            return padding[width];
        }
        int width2 = Math.min(width, 30);
        char[] out = new char[width2];
        for (int i = 0; i < width2; i++) {
            out[i] = ' ';
        }
        return String.valueOf(out);
    }

    public static boolean isBlank(String string) {
        if (string == null || string.length() == 0) {
            return true;
        }
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!isWhitespace(string.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNumeric(String string) {
        if (string == null || string.length() == 0) {
            return false;
        }
        int l = string.length();
        for (int i = 0; i < l; i++) {
            if (!Character.isDigit(string.codePointAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isWhitespace(int c) {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13;
    }

    public static boolean isActuallyWhitespace(int c) {
        return c == 32 || c == 9 || c == 10 || c == 12 || c == 13 || c == 160;
    }

    public static boolean isInvisibleChar(int c) {
        return c == 8203 || c == 173;
    }

    public static String normaliseWhitespace(String string) {
        StringBuilder sb = borrowBuilder();
        appendNormalisedWhitespace(sb, string, false);
        return releaseBuilder(sb);
    }

    public static void appendNormalisedWhitespace(StringBuilder accum, String string, boolean stripLeading) {
        boolean lastWasWhite = false;
        boolean reachedNonWhite = false;
        int len = string.length();
        int i = 0;
        while (i < len) {
            int c = string.codePointAt(i);
            if (isActuallyWhitespace(c)) {
                if ((!stripLeading || reachedNonWhite) && !lastWasWhite) {
                    accum.append(' ');
                    lastWasWhite = true;
                }
            } else if (!isInvisibleChar(c)) {
                accum.appendCodePoint(c);
                lastWasWhite = false;
                reachedNonWhite = true;
            }
            i += Character.charCount(c);
        }
    }

    /* JADX INFO: renamed from: in */
    public static boolean m5147in(String needle, String... haystack) {
        for (String str : haystack) {
            if (str.equals(needle)) {
                return true;
            }
        }
        return false;
    }

    public static boolean inSorted(String needle, String[] haystack) {
        return Arrays.binarySearch(haystack, needle) >= 0;
    }

    public static boolean isAscii(String string) {
        Validate.notNull(string);
        for (int i = 0; i < string.length(); i++) {
            int c = string.charAt(i);
            if (c > 127) {
                return false;
            }
        }
        return true;
    }

    public static URL resolve(URL base, String relUrl) throws MalformedURLException {
        if (relUrl.startsWith("?")) {
            relUrl = base.getPath() + relUrl;
        }
        URL url = new URL(base, relUrl);
        String fixedFile = extraDotSegmentsPattern.matcher(url.getFile()).replaceFirst(InternalZipConstants.ZIP_FILE_SEPARATOR);
        if (url.getRef() != null) {
            fixedFile = fixedFile + "#" + url.getRef();
        }
        return new URL(url.getProtocol(), url.getHost(), url.getPort(), fixedFile);
    }

    public static String resolve(String baseUrl, String relUrl) {
        try {
            try {
                URL base = new URL(baseUrl);
                relUrl = resolve(base, relUrl).toExternalForm();
            } catch (MalformedURLException e) {
                URL abs = new URL(relUrl);
                relUrl = abs.toExternalForm();
            }
            return relUrl;
        } catch (MalformedURLException e2) {
            return !validUriScheme.matcher(relUrl).find() ? "" : relUrl;
        }
    }

    public static StringBuilder borrowBuilder() {
        Stack<StringBuilder> builders = threadLocalBuilders.get();
        if (builders.empty()) {
            return new StringBuilder(8192);
        }
        return builders.pop();
    }

    public static String releaseBuilder(StringBuilder sb) {
        Validate.notNull(sb);
        String string = sb.toString();
        if (sb.length() > 8192) {
            sb = new StringBuilder(8192);
        } else {
            sb.delete(0, sb.length());
        }
        Stack<StringBuilder> builders = threadLocalBuilders.get();
        builders.push(sb);
        while (builders.size() > 8) {
            builders.pop();
        }
        return string;
    }
}

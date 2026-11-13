package org.jsoup.parser;

import android.support.v4.app.FrameMetricsAggregator;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Locale;
import org.jsoup.UncheckedIOException;
import org.jsoup.helper.Validate;

/* JADX INFO: loaded from: classes2.dex */
public final class CharacterReader {
    static final char EOF = 65535;
    static final int maxBufferLen = 32768;
    private static final int maxStringCacheLen = 12;
    private static final int minReadAheadLen = 1024;
    static final int readAheadLimit = 24576;
    private static final int stringCacheSize = 512;
    private int bufLength;
    private int bufMark;
    private int bufPos;
    private int bufSplitPoint;
    private char[] charBuf;
    private boolean readFully;
    private Reader reader;
    private int readerPos;
    private String[] stringCache;

    public CharacterReader(Reader input, int sz) {
        this.bufMark = -1;
        this.stringCache = new String[512];
        Validate.notNull(input);
        Validate.isTrue(input.markSupported());
        this.reader = input;
        this.charBuf = new char[Math.min(sz, 32768)];
        bufferUp();
    }

    public CharacterReader(Reader input) {
        this(input, 32768);
    }

    public CharacterReader(String input) {
        this(new StringReader(input), input.length());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void close() {
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (IOException e) {
            } finally {
                this.reader = null;
                this.charBuf = null;
                this.stringCache = null;
            }
        }
    }

    private void bufferUp() {
        int pos;
        int offset;
        if (!this.readFully && this.bufPos >= this.bufSplitPoint) {
            if (this.bufMark != -1) {
                pos = this.bufMark;
                offset = this.bufPos - this.bufMark;
            } else {
                pos = this.bufPos;
                offset = 0;
            }
            try {
                long skipped = this.reader.skip(pos);
                this.reader.mark(32768);
                int read = 0;
                while (read <= 1024) {
                    int thisRead = this.reader.read(this.charBuf, read, this.charBuf.length - read);
                    if (thisRead == -1) {
                        this.readFully = true;
                    }
                    if (thisRead <= 0) {
                        break;
                    } else {
                        read += thisRead;
                    }
                }
                this.reader.reset();
                if (read > 0) {
                    Validate.isTrue(skipped == ((long) pos));
                    this.bufLength = read;
                    this.readerPos += pos;
                    this.bufPos = offset;
                    if (this.bufMark != -1) {
                        this.bufMark = 0;
                    }
                    this.bufSplitPoint = Math.min(this.bufLength, readAheadLimit);
                }
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }

    public int pos() {
        return this.readerPos + this.bufPos;
    }

    public boolean isEmpty() {
        bufferUp();
        return this.bufPos >= this.bufLength;
    }

    private boolean isEmptyNoBufferUp() {
        return this.bufPos >= this.bufLength;
    }

    public char current() {
        bufferUp();
        return isEmptyNoBufferUp() ? EOF : this.charBuf[this.bufPos];
    }

    char consume() {
        bufferUp();
        char val = isEmptyNoBufferUp() ? EOF : this.charBuf[this.bufPos];
        this.bufPos++;
        return val;
    }

    void unconsume() {
        if (this.bufPos < 1) {
            throw new UncheckedIOException(new IOException("WTF: No buffer left to unconsume."));
        }
        this.bufPos--;
    }

    public void advance() {
        this.bufPos++;
    }

    void mark() {
        if (this.bufLength - this.bufPos < 1024) {
            this.bufSplitPoint = 0;
        }
        bufferUp();
        this.bufMark = this.bufPos;
    }

    void unmark() {
        this.bufMark = -1;
    }

    void rewindToMark() {
        if (this.bufMark == -1) {
            throw new UncheckedIOException(new IOException("Mark invalid"));
        }
        this.bufPos = this.bufMark;
        unmark();
    }

    int nextIndexOf(char c) {
        bufferUp();
        for (int i = this.bufPos; i < this.bufLength; i++) {
            if (c == this.charBuf[i]) {
                return i - this.bufPos;
            }
        }
        return -1;
    }

    int nextIndexOf(CharSequence seq) {
        bufferUp();
        char startChar = seq.charAt(0);
        int offset = this.bufPos;
        while (offset < this.bufLength) {
            if (startChar != this.charBuf[offset]) {
                do {
                    offset++;
                    if (offset >= this.bufLength) {
                        break;
                    }
                } while (startChar != this.charBuf[offset]);
            }
            int i = offset + 1;
            int last = (seq.length() + i) - 1;
            if (offset < this.bufLength && last <= this.bufLength) {
                for (int j = 1; i < last && seq.charAt(j) == this.charBuf[i]; j++) {
                    i++;
                }
                if (i == last) {
                    return offset - this.bufPos;
                }
            }
            offset++;
        }
        return -1;
    }

    public String consumeTo(char c) {
        int offset = nextIndexOf(c);
        if (offset != -1) {
            String consumed = cacheString(this.charBuf, this.stringCache, this.bufPos, offset);
            this.bufPos += offset;
            return consumed;
        }
        String consumed2 = consumeToEnd();
        return consumed2;
    }

    String consumeTo(String seq) {
        int offset = nextIndexOf(seq);
        if (offset != -1) {
            String consumed = cacheString(this.charBuf, this.stringCache, this.bufPos, offset);
            this.bufPos += offset;
            return consumed;
        }
        if (this.bufLength - this.bufPos < seq.length()) {
            String consumed2 = consumeToEnd();
            return consumed2;
        }
        int endPos = (this.bufLength - seq.length()) + 1;
        String consumed3 = cacheString(this.charBuf, this.stringCache, this.bufPos, endPos - this.bufPos);
        this.bufPos = endPos;
        return consumed3;
    }

    public String consumeToAny(char... chars) {
        bufferUp();
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        loop0: while (pos < remaining) {
            for (char c : chars) {
                if (val[pos] == c) {
                    break loop0;
                }
            }
            pos++;
        }
        this.bufPos = pos;
        if (pos > pos) {
            return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
        }
        return "";
    }

    String consumeToAnySorted(char... chars) {
        bufferUp();
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining && Arrays.binarySearch(chars, val[pos]) < 0) {
            pos++;
        }
        this.bufPos = pos;
        if (this.bufPos > pos) {
            return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
        }
        return "";
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0020 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0015  */
    String consumeData() {
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining) {
            switch (val[pos]) {
                case 0:
                case '&':
                case '<':
                    break;
                default:
                    pos++;
                    break;
            }
            this.bufPos = pos;
            if (pos > pos) {
                return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
            }
            return "";
        }
        this.bufPos = pos;
        if (pos > pos) {
            return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
        }
        return "";
    }

    /* JADX WARN: Code duplicated, block: B:10:0x0017  */
    /* JADX WARN: Code duplicated, block: B:14:0x0025 A[RETURN, SYNTHETIC] */
    String consumeAttributeQuoted(boolean single) {
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining) {
            switch (val[pos]) {
                case 0:
                case '&':
                    this.bufPos = pos;
                    if (pos > pos) {
                        return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
                    }
                    return "";
                case '\"':
                    break;
                case '\'':
                    if (single) {
                    }
                    this.bufPos = pos;
                    if (pos > pos) {
                        return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
                    }
                    return "";
                default:
                    continue;
                    pos++;
                    break;
            }
            if (single) {
                pos++;
            } else {
                this.bufPos = pos;
                if (pos > pos) {
                    return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
                }
                return "";
            }
        }
        this.bufPos = pos;
        if (pos > pos) {
            return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
        }
        return "";
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0020 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0015  */
    String consumeRawData() {
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining) {
            switch (val[pos]) {
                case 0:
                case '<':
                    break;
                default:
                    pos++;
                    break;
            }
            this.bufPos = pos;
            if (pos > pos) {
                return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
            }
            return "";
        }
        this.bufPos = pos;
        if (pos > pos) {
            return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
        }
        return "";
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0023 A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:9:0x0018  */
    String consumeTagName() {
        bufferUp();
        int pos = this.bufPos;
        int remaining = this.bufLength;
        char[] val = this.charBuf;
        while (pos < remaining) {
            switch (val[pos]) {
                case '\t':
                case '\n':
                case '\f':
                case '\r':
                case ' ':
                case '/':
                case '<':
                case '>':
                    break;
                default:
                    pos++;
                    break;
            }
            this.bufPos = pos;
            if (pos > pos) {
                return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
            }
            return "";
        }
        this.bufPos = pos;
        if (pos > pos) {
            return cacheString(this.charBuf, this.stringCache, pos, pos - pos);
        }
        return "";
    }

    String consumeToEnd() {
        bufferUp();
        String data = cacheString(this.charBuf, this.stringCache, this.bufPos, this.bufLength - this.bufPos);
        this.bufPos = this.bufLength;
        return data;
    }

    String consumeLetterSequence() {
        char c;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (((c = this.charBuf[this.bufPos]) >= 'A' && c <= 'Z') || ((c >= 'a' && c <= 'z') || Character.isLetter(c)))) {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeLetterThenDigitSequence() {
        char c;
        char c2;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (((c2 = this.charBuf[this.bufPos]) >= 'A' && c2 <= 'Z') || ((c2 >= 'a' && c2 <= 'z') || Character.isLetter(c2)))) {
            this.bufPos++;
        }
        while (!isEmptyNoBufferUp() && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9') {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeHexSequence() {
        char c;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (((c = this.charBuf[this.bufPos]) >= '0' && c <= '9') || ((c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')))) {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    String consumeDigitSequence() {
        char c;
        bufferUp();
        int start = this.bufPos;
        while (this.bufPos < this.bufLength && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9') {
            this.bufPos++;
        }
        return cacheString(this.charBuf, this.stringCache, start, this.bufPos - start);
    }

    boolean matches(char c) {
        return !isEmpty() && this.charBuf[this.bufPos] == c;
    }

    boolean matches(String seq) {
        bufferUp();
        int scanLength = seq.length();
        if (scanLength > this.bufLength - this.bufPos) {
            return false;
        }
        for (int offset = 0; offset < scanLength; offset++) {
            if (seq.charAt(offset) != this.charBuf[this.bufPos + offset]) {
                return false;
            }
        }
        return true;
    }

    boolean matchesIgnoreCase(String seq) {
        bufferUp();
        int scanLength = seq.length();
        if (scanLength > this.bufLength - this.bufPos) {
            return false;
        }
        for (int offset = 0; offset < scanLength; offset++) {
            char upScan = Character.toUpperCase(seq.charAt(offset));
            char upTarget = Character.toUpperCase(this.charBuf[this.bufPos + offset]);
            if (upScan != upTarget) {
                return false;
            }
        }
        return true;
    }

    boolean matchesAny(char... seq) {
        if (isEmpty()) {
            return false;
        }
        bufferUp();
        char c = this.charBuf[this.bufPos];
        for (char seek : seq) {
            if (seek == c) {
                return true;
            }
        }
        return false;
    }

    boolean matchesAnySorted(char[] seq) {
        bufferUp();
        return !isEmpty() && Arrays.binarySearch(seq, this.charBuf[this.bufPos]) >= 0;
    }

    boolean matchesLetter() {
        if (isEmpty()) {
            return false;
        }
        char c = this.charBuf[this.bufPos];
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || Character.isLetter(c);
    }

    boolean matchesAsciiAlpha() {
        if (isEmpty()) {
            return false;
        }
        char c = this.charBuf[this.bufPos];
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    boolean matchesDigit() {
        char c;
        return !isEmpty() && (c = this.charBuf[this.bufPos]) >= '0' && c <= '9';
    }

    boolean matchConsume(String seq) {
        bufferUp();
        if (!matches(seq)) {
            return false;
        }
        this.bufPos += seq.length();
        return true;
    }

    boolean matchConsumeIgnoreCase(String seq) {
        if (!matchesIgnoreCase(seq)) {
            return false;
        }
        this.bufPos += seq.length();
        return true;
    }

    boolean containsIgnoreCase(String seq) {
        String loScan = seq.toLowerCase(Locale.ENGLISH);
        String hiScan = seq.toUpperCase(Locale.ENGLISH);
        return nextIndexOf(loScan) > -1 || nextIndexOf(hiScan) > -1;
    }

    public String toString() {
        return this.bufLength - this.bufPos < 0 ? "" : new String(this.charBuf, this.bufPos, this.bufLength - this.bufPos);
    }

    private static String cacheString(char[] charBuf, String[] stringCache, int start, int count) {
        if (count > 12) {
            return new String(charBuf, start, count);
        }
        if (count < 1) {
            return "";
        }
        int hash = 0;
        for (int i = 0; i < count; i++) {
            hash = (hash * 31) + charBuf[start + i];
        }
        int index = hash & FrameMetricsAggregator.EVERY_DURATION;
        String cached = stringCache[index];
        if (cached == null || !rangeEquals(charBuf, start, count, cached)) {
            String cached2 = new String(charBuf, start, count);
            stringCache[index] = cached2;
            return cached2;
        }
        return cached;
    }

    static boolean rangeEquals(char[] charBuf, int start, int count, String cached) {
        int j;
        int i;
        if (count != cached.length()) {
            return false;
        }
        int i2 = start;
        int j2 = 0;
        do {
            j = j2;
            i = i2;
            int count2 = count;
            count = count2 - 1;
            if (count2 != 0) {
                i2 = i + 1;
                j2 = j + 1;
            } else {
                return true;
            }
        } while (charBuf[i] == cached.charAt(j));
        return false;
    }

    boolean rangeEquals(int start, int count, String cached) {
        return rangeEquals(this.charBuf, start, count, cached);
    }
}

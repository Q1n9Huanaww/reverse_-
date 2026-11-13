package com.sun.mail.iap;

import com.sun.mail.util.ASCIIUtility;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class Response {
    public static final int BAD = 12;
    public static final int BYE = 16;
    public static final int CONTINUATION = 1;

    /* JADX INFO: renamed from: NO */
    public static final int f3598NO = 8;

    /* JADX INFO: renamed from: OK */
    public static final int f3599OK = 4;
    public static final int SYNTHETIC = 32;
    public static final int TAGGED = 2;
    public static final int TAG_MASK = 3;
    public static final int TYPE_MASK = 28;
    public static final int UNTAGGED = 3;
    private static final int increment = 100;
    protected byte[] buffer;
    protected int index;
    protected int pindex;
    protected int size;
    protected String tag;
    protected int type;

    public Response(String str) {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        this.buffer = ASCIIUtility.getBytes(str);
        this.size = this.buffer.length;
        parse();
    }

    public Response(Protocol protocol) throws ProtocolException, IOException {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        ByteArray response = protocol.getInputStream().readResponse(protocol.getResponseBuffer());
        this.buffer = response.getBytes();
        this.size = response.getCount() - 2;
        parse();
    }

    public Response(Response response) {
        this.buffer = null;
        this.type = 0;
        this.tag = null;
        this.index = response.index;
        this.size = response.size;
        this.buffer = response.buffer;
        this.type = response.type;
        this.tag = response.tag;
    }

    public static Response byeResponse(Exception exc) {
        Response response = new Response(("* BYE JavaMail Exception: " + exc.toString()).replace('\r', ' ').replace('\n', ' '));
        response.type = response.type | 32;
        return response;
    }

    private void parse() {
        this.index = 0;
        if (this.buffer[this.index] == 43) {
            this.type |= 1;
            this.index++;
            return;
        }
        if (this.buffer[this.index] == 42) {
            this.type |= 3;
            this.index++;
        } else {
            this.type |= 2;
            this.tag = readAtom();
        }
        int i = this.index;
        String atom = readAtom();
        if (atom == null) {
            atom = "";
        }
        if (atom.equalsIgnoreCase("OK")) {
            this.type |= 4;
        } else if (atom.equalsIgnoreCase("NO")) {
            this.type |= 8;
        } else if (atom.equalsIgnoreCase("BAD")) {
            this.type |= 12;
        } else if (atom.equalsIgnoreCase("BYE")) {
            this.type |= 16;
        } else {
            this.index = i;
        }
        this.pindex = this.index;
    }

    public void skipSpaces() {
        while (this.index < this.size && this.buffer[this.index] == 32) {
            this.index++;
        }
    }

    public void skipToken() {
        while (this.index < this.size && this.buffer[this.index] != 32) {
            this.index++;
        }
    }

    public void skip(int i) {
        this.index += i;
    }

    public byte peekByte() {
        if (this.index < this.size) {
            return this.buffer[this.index];
        }
        return (byte) 0;
    }

    public byte readByte() {
        if (this.index >= this.size) {
            return (byte) 0;
        }
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr[i];
    }

    public String readAtom() {
        return readAtom((char) 0);
    }

    public String readAtom(char c) {
        byte b;
        skipSpaces();
        if (this.index >= this.size) {
            return null;
        }
        int i = this.index;
        while (this.index < this.size && (b = this.buffer[this.index]) > 32 && b != 40 && b != 41 && b != 37 && b != 42 && b != 34 && b != 92 && b != 127 && (c == 0 || b != c)) {
            this.index++;
        }
        return ASCIIUtility.toString(this.buffer, i, this.index);
    }

    public String readString(char c) {
        skipSpaces();
        if (this.index >= this.size) {
            return null;
        }
        int i = this.index;
        while (this.index < this.size && this.buffer[this.index] != c) {
            this.index++;
        }
        return ASCIIUtility.toString(this.buffer, i, this.index);
    }

    public String[] readStringList() {
        byte[] bArr;
        int i;
        skipSpaces();
        if (this.buffer[this.index] != 40) {
            return null;
        }
        this.index++;
        Vector vector = new Vector();
        do {
            vector.addElement(readString());
            bArr = this.buffer;
            i = this.index;
            this.index = i + 1;
        } while (bArr[i] != 41);
        int size = vector.size();
        if (size <= 0) {
            return null;
        }
        String[] strArr = new String[size];
        vector.copyInto(strArr);
        return strArr;
    }

    public int readNumber() {
        skipSpaces();
        int i = this.index;
        while (this.index < this.size && Character.isDigit((char) this.buffer[this.index])) {
            this.index++;
        }
        if (this.index <= i) {
            return -1;
        }
        try {
            return ASCIIUtility.parseInt(this.buffer, i, this.index);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    public long readLong() {
        skipSpaces();
        int i = this.index;
        while (this.index < this.size && Character.isDigit((char) this.buffer[this.index])) {
            this.index++;
        }
        if (this.index <= i) {
            return -1L;
        }
        try {
            return ASCIIUtility.parseLong(this.buffer, i, this.index);
        } catch (NumberFormatException unused) {
            return -1L;
        }
    }

    public String readString() {
        return (String) parseString(false, true);
    }

    public ByteArrayInputStream readBytes() {
        ByteArray byteArray = readByteArray();
        if (byteArray != null) {
            return byteArray.toByteArrayInputStream();
        }
        return null;
    }

    public ByteArray readByteArray() {
        if (isContinuation()) {
            skipSpaces();
            return new ByteArray(this.buffer, this.index, this.size - this.index);
        }
        return (ByteArray) parseString(false, false);
    }

    public String readAtomString() {
        return (String) parseString(true, true);
    }

    private Object parseString(boolean z, boolean z2) {
        skipSpaces();
        byte b = this.buffer[this.index];
        if (b == 34) {
            this.index++;
            int i = this.index;
            int i2 = this.index;
            while (true) {
                byte b2 = this.buffer[this.index];
                if (b2 == 34) {
                    break;
                }
                if (b2 == 92) {
                    this.index++;
                }
                if (this.index != i2) {
                    this.buffer[i2] = this.buffer[this.index];
                }
                i2++;
                this.index++;
            }
            this.index++;
            if (z2) {
                return ASCIIUtility.toString(this.buffer, i, i2);
            }
            return new ByteArray(this.buffer, i, i2 - i);
        }
        if (b != 123) {
            if (z) {
                return z2 ? readAtom() : new ByteArray(this.buffer, this.index, this.index);
            }
            if (b != 78 && b != 110) {
                return null;
            }
            this.index += 3;
            return null;
        }
        int i3 = this.index + 1;
        this.index = i3;
        while (this.buffer[this.index] != 125) {
            this.index++;
        }
        try {
            int i4 = ASCIIUtility.parseInt(this.buffer, i3, this.index);
            int i5 = this.index + 3;
            int i6 = i5 + i4;
            this.index = i6;
            if (z2) {
                return ASCIIUtility.toString(this.buffer, i5, i6);
            }
            return new ByteArray(this.buffer, i5, i4);
        } catch (NumberFormatException unused) {
            return null;
        }
    }

    public int getType() {
        return this.type;
    }

    public boolean isContinuation() {
        return (this.type & 3) == 1;
    }

    public boolean isTagged() {
        return (this.type & 3) == 2;
    }

    public boolean isUnTagged() {
        return (this.type & 3) == 3;
    }

    public boolean isOK() {
        return (this.type & 28) == 4;
    }

    public boolean isNO() {
        return (this.type & 28) == 8;
    }

    public boolean isBAD() {
        return (this.type & 28) == 12;
    }

    public boolean isBYE() {
        return (this.type & 28) == 16;
    }

    public boolean isSynthetic() {
        return (this.type & 32) == 32;
    }

    public String getTag() {
        return this.tag;
    }

    public String getRest() {
        skipSpaces();
        return ASCIIUtility.toString(this.buffer, this.index, this.size);
    }

    public void reset() {
        this.index = this.pindex;
    }

    public String toString() {
        return ASCIIUtility.toString(this.buffer, 0, this.size);
    }
}

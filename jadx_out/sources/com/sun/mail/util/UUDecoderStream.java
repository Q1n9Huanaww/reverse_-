package com.sun.mail.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: classes.dex */
public class UUDecoderStream extends FilterInputStream {
    private byte[] buffer;
    private int bufsize;
    private boolean gotEnd;
    private boolean gotPrefix;
    private int index;
    private LineInputStream lin;
    private int mode;
    private String name;

    @Override // java.io.FilterInputStream, java.io.InputStream
    public boolean markSupported() {
        return false;
    }

    public UUDecoderStream(InputStream inputStream) {
        super(inputStream);
        this.bufsize = 0;
        this.index = 0;
        this.gotPrefix = false;
        this.gotEnd = false;
        this.lin = new LineInputStream(inputStream);
        this.buffer = new byte[45];
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read() throws IOException {
        if (this.index >= this.bufsize) {
            readPrefix();
            if (!decode()) {
                return -1;
            }
            this.index = 0;
        }
        byte[] bArr = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr[i] & 255;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = 0;
        while (i3 < i2) {
            int i4 = read();
            if (i4 == -1) {
                if (i3 == 0) {
                    return -1;
                }
                return i3;
            }
            bArr[i + i3] = (byte) i4;
            i3++;
        }
        return i3;
    }

    @Override // java.io.FilterInputStream, java.io.InputStream
    public int available() throws IOException {
        return ((this.in.available() * 3) / 4) + (this.bufsize - this.index);
    }

    public String getName() throws IOException {
        readPrefix();
        return this.name;
    }

    public int getMode() throws IOException {
        readPrefix();
        return this.mode;
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003b, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0053, code lost:
    
        throw new java.io.IOException("UUDecoder error: " + r0.toString());
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void readPrefix() throws IOException {
        String line;
        if (this.gotPrefix) {
            return;
        }
        do {
            line = this.lin.readLine();
            if (line == null) {
                throw new IOException("UUDecoder error: No Begin");
            }
        } while (!line.regionMatches(true, 0, "begin", 0, 5));
        this.mode = Integer.parseInt(line.substring(6, 9));
        this.name = line.substring(10);
        this.gotPrefix = true;
    }

    private boolean decode() throws IOException {
        String line;
        if (this.gotEnd) {
            return false;
        }
        this.bufsize = 0;
        do {
            line = this.lin.readLine();
            if (line == null) {
                throw new IOException("Missing End");
            }
            if (line.regionMatches(true, 0, "end", 0, 3)) {
                this.gotEnd = true;
                return false;
            }
        } while (line.length() == 0);
        char cCharAt = line.charAt(0);
        if (cCharAt < ' ') {
            throw new IOException("Buffer format error");
        }
        int i = (cCharAt - ' ') & 63;
        if (i == 0) {
            String line2 = this.lin.readLine();
            if (line2 == null || !line2.regionMatches(true, 0, "end", 0, 3)) {
                throw new IOException("Missing End");
            }
            this.gotEnd = true;
            return false;
        }
        if (line.length() < (((i * 8) + 5) / 6) + 1) {
            throw new IOException("Short buffer error");
        }
        int i2 = 1;
        while (this.bufsize < i) {
            int i3 = i2 + 1;
            byte bCharAt = (byte) ((line.charAt(i2) - ' ') & 63);
            int i4 = i3 + 1;
            byte bCharAt2 = (byte) ((line.charAt(i3) - ' ') & 63);
            byte[] bArr = this.buffer;
            int i5 = this.bufsize;
            this.bufsize = i5 + 1;
            bArr[i5] = (byte) (((bCharAt << 2) & 252) | ((bCharAt2 >>> 4) & 3));
            if (this.bufsize < i) {
                i2 = i4 + 1;
                byte bCharAt3 = (byte) ((line.charAt(i4) - ' ') & 63);
                byte[] bArr2 = this.buffer;
                int i6 = this.bufsize;
                this.bufsize = i6 + 1;
                bArr2[i6] = (byte) (((bCharAt2 << 4) & 240) | ((bCharAt3 >>> 2) & 15));
                bCharAt2 = bCharAt3;
            } else {
                i2 = i4;
            }
            if (this.bufsize < i) {
                int i7 = i2 + 1;
                byte bCharAt4 = (byte) ((line.charAt(i2) - ' ') & 63);
                byte[] bArr3 = this.buffer;
                int i8 = this.bufsize;
                this.bufsize = i8 + 1;
                bArr3[i8] = (byte) ((bCharAt4 & 63) | ((bCharAt2 << 6) & 192));
                i2 = i7;
            }
        }
        return true;
    }
}
